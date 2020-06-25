package com.hull.utils;

import com.hull.utils.FileUtils2;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * TODO 来点注释
 *
 * @author
 * @create 2019-07-23 11:20
 **/

@Slf4j
public class FileOperator {


    public static void main(String[] args) throws IOException {
        boolean result = copyFile("/Users/hill/Downloads/bigEntry.txt","/Users/hill/Downloads/bigEntry2.txt");
        System.out.println(result);

//        long start = System.currentTimeMillis();
//        FileUtils2.copyFile("/Users/hill/Downloads/entry.txt","/Users/hill/Downloads/entry3.txt");
//        long end = System.currentTimeMillis();
//        System.out.println("耗费时间为：" + (end - start)+" ms");
    }


    /**
     * 复制文件
     * @param source
     * @param target
     * @throws IOException
     */
    public static boolean copyFile(String source,String target) throws IOException {
        long start = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get(source), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(target), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        log.info("file size={},maxValue={}",inChannel.size(),Integer.MAX_VALUE);
        if(inChannel.size()>Integer.MAX_VALUE){
            log.warn("file too big!");
            return false;
        }

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作 （此处会占用内存，如果 堆内存设置不够大，会报异常！！！）
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start)+" ms");
        return true;
    }

}
