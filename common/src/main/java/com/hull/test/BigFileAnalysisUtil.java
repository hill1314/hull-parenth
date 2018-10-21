package com.hull.test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Scanner;


/**
 * java读取大文件 的相关方法
 * 源于阿里一道笔试题 ：读取一个大的log文件，找出里面的关键字的个数 ，需要考虑不能发生 OOM ，并尽可能有好的效率
 *
 * @author
 * @create 2018-10-20 上午11:58
 **/

public class BigFileAnalysisUtil {


    public static void main(String[] args) throws IOException {

        //生成一个大文件
//        createBigFile();

        //拆分文件
        splitFileBySize("/Users/hill/file/bigFile.txt");
//        splitFile("/Users/hill/file/bigFile.log",8);


        //各种方式读取文件内存
//        baseFileRead("/Users/hill/yoolifin_0108.sql");
    }

    private static void createBigFile() throws IOException {
        File file = new File("/Users/hill/file/bigFile.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String[] types = {"ERROR","WARN","INFO"};
        String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaawwwwwwwwwwwwwwwwwwwww";
        for (int i = 0; i < 1000000; i++) {
            bufferedWriter.write(i+" "+types[RandomUtils.nextInt(0,3)]+" "+str);
            bufferedWriter.newLine();
        }
        printVM();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /**
     * 将文件平均切割成 指定的个数
     * @param filePath
     * @param fileCount
     * @throws IOException
     */
    public static void splitFile(String filePath,int fileCount) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        FileChannel inputChannel = fis.getChannel();
        final long fileSize = inputChannel.size();
        long average = fileSize / fileCount;//平均值
        long bufferSize = 200; //缓存块大小，自行调整

        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.valueOf(bufferSize + "")); // 申请一个缓存区
        long startPosition = 0; //子文件开始位置
        long endPosition = average < bufferSize ? 0 : average - bufferSize;//子文件结束位置
        for (int i = 0; i < fileCount; i++) {
            if (i + 1 != fileCount) {
                int read = inputChannel.read(byteBuffer, endPosition);// 读取数据
                readW:
                while (read != -1) {
                    byteBuffer.flip();//切换读模式
                    byte[] array = byteBuffer.array();
                    for (int j = 0; j < array.length; j++) {
                        byte b = array[j];
                        if (b == 10 || b == 13) { //判断\n\r
                            endPosition += j;
                            break readW;
                        }
                    }
                    endPosition += bufferSize;
                    byteBuffer.clear(); //重置缓存块指针
                    read = inputChannel.read(byteBuffer, endPosition);
                }
            }else{
                endPosition = fileSize; //最后一个文件直接指向文件末尾
            }

            FileOutputStream fos = new FileOutputStream(filePath + (i + 1));
            FileChannel outputChannel = fos.getChannel();
            inputChannel.transferTo(startPosition, endPosition - startPosition, outputChannel);//通道传输文件数据
            outputChannel.close();
            fos.close();
            startPosition = endPosition + 1;
            endPosition += average;
        }
        inputChannel.close();
        fis.close();

    }

    /**
     * 将文件切割成指定的大小
     * @param filePath
     * @throws IOException
     */
    public static void splitFileBySize(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        FileChannel inputChannel = fis.getChannel();
        final long fileSize = inputChannel.size();
        long bufferSize = 200; //缓存块大小，自行调整
        long limitSize = 10*1024*1024; //允许的最大文件大小，超过就做拆分

        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.valueOf(bufferSize + "")); // 申请一个缓存区
        long startPosition = 0; //子文件开始位置
        long endPosition = limitSize;//子文件结束位置

        int fileIdx = 0;

        do{
            //因为要考虑换行，而 直接取的 endPosition 很可能在一行的中间，那么需要基于这个数字再向后读取一个buffer，
            // 直到找到一个换行符，然后把这个位置当做此次切割的子文件的结束位
            if(startPosition + limitSize <= fileSize){
                int read = inputChannel.read(byteBuffer, endPosition);// 读取数据
                readW:
                while (read != -1) {
                    byteBuffer.flip();//切换读模式
                    byte[] array = byteBuffer.array();
                    for (int j = 0; j < array.length; j++) {
                        byte b = array[j];
                        if (b == 10 || b == 13) { //判断\n\r
                            endPosition += j;
                            break readW;
                        }
                    }
                    endPosition += bufferSize;
                    byteBuffer.clear(); //重置缓存块指针
                    read = inputChannel.read(byteBuffer, endPosition);
                }
            }else{
                endPosition = fileSize; //最后一个文件直接指向文件末尾
            }

            fileIdx++;
            System.out.println("fileIdx="+fileIdx+",startPosition="+startPosition+",endPosition="+endPosition);
            FileOutputStream fos = new FileOutputStream(filePath + fileIdx +".txt");
            FileChannel outputChannel = fos.getChannel();
            inputChannel.transferTo(startPosition, endPosition - startPosition, outputChannel);//通道传输文件数据
            outputChannel.close();
            fos.close();
            startPosition = endPosition + 1;
            endPosition += limitSize;
            printVM();
        }while (startPosition <= fileSize);         //起始位置 <=  总文件大小


        inputChannel.close();
        fis.close();

    }


    /**
     * 用常规BIO 的 file stream 流 操作文件，也会全部放入内存，所以不可行
     * @param fileName
     * @return
     */
    public static Integer baseFileRead(String fileName) {
        File file = new File(fileName);
        System.out.println("fileSize="+file.length()/1024/1024+"M");

        int bufferSize = 2 * 1024 * 1024;//设读取文件的缓存
        int num = 0;

        FileInputStream fileInputStream = null;
        BufferedReader reader = null;
        InputStreamReader isr = null;
        try {
            fileInputStream = new FileInputStream(file);
            isr = new InputStreamReader(fileInputStream,"UTF-8");
            //TODO  这里bufferReader 也会把整个文件内容放入内存，所以也是不可行的
            reader = new BufferedReader(isr,bufferSize);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isEmpty(line.trim())) {
                    continue;   //跳过空行
                }

                if(line.trim().startsWith("error")){
                    num++;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            printVM();
            try {
                if(fileInputStream!=null)
                    fileInputStream.close();
                if(reader!=null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return num;
    }

    /**
     * guava的 Files.readLines() 会将文件内存全部放入内存，读取大文件时 会导致内存溢出
     * @param fileName
     * @throws IOException
     */
    public static void guavaFileUtil(String fileName) throws IOException {
        List<String> list = Files.readLines(new File(fileName), Charsets.UTF_8);
        printVM();
        System.out.println("");
        System.out.println(list.get(0));
    }

    /**
     * Apache Commons IO 的 FileUtils.readLines() 也会将文件内存全部放入内存，读取大文件时 会导致内存溢出
     * @param fileName
     * @throws IOException
     */
    public static void commonsFileUtil(String fileName) throws IOException {
        List<String> list = FileUtils.readLines(new File(fileName));

        printVM();
        System.out.println("");
        System.out.println(list.get(0));
    }

    /**
     * Apache Commons IO 的 FileUtils.lineIterator() 提供的自定义LineIterator,也不会把整个文件放入内存
     * @param fileName
     * @throws IOException
     */
    public static void commonsFileUtil2(String fileName) throws IOException {
        LineIterator it = FileUtils.lineIterator(new File(fileName), "UTF-8");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                // do something with line
            }
        } finally {
            printVM();
//            jvm 最大内存大小 = 27 M,总内存大小=27 M, 使用内存大小= 5 M,空闲内存大小=21 M
            LineIterator.closeQuietly(it);
        }
    }

    /**
     * 使用java.util.Scanner类扫描文件的内容，一行一行连续地读取
     * 这种方案将会遍历文件中的所有行——允许对每一行进行处理，而不保持对它的引用。总之没有把它们存放在内存中
     * @param fileName
     * @throws IOException
     */
    public static void scannerFileUtil(String fileName) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(fileName);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // System.out.println(line);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            printVM();
//            jvm 最大内存大小 = 27 M,总内存大小=27 M, 使用内存大小= 10 M,空闲内存大小=16 M
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }


    /**
     * 打印JVM 内存信息
     */
    public static void printVM(){
        System.out.println("=====================================");
        System.out.printf("jvm 最大内存大小 = %s M,总内存大小=%s M, 使用内存大小= %s M,空闲内存大小=%s M ",
                Runtime.getRuntime().maxMemory()/1024/1024, //最大可用内存，对应-Xmx
                Runtime.getRuntime().totalMemory()/1024/1024, //当前JVM占用的内存总数，= 已使用的内存+ freeMemory()
                (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1024/1024, //当前JVM占用内存
                Runtime.getRuntime().freeMemory()/1024/1024 //当前JVM空闲内存
        );
        System.out.println();
        System.out.println("=====================================");
    }

}
