package com.hull.test.compile;

import java.io.*;

/**
 * 自定义类加载器
 * 代码生成、编译、重新动态load 到JVM
 *
 * @author
 * @create 2018-08-04 下午9:23
 **/

public class MyClassLoader extends ClassLoader{
    private static String baseDir;

    public MyClassLoader() {
        this.baseDir = MyClassLoader.class.getResource("").getPath();
    }

    public Class<?> loadClass(String name) {
        String className = MyClassLoader.class.getName() + "." + name;
        File classFile = new File(baseDir,name.replaceAll("\\.","/")+".class");
        if(classFile.exists()){
            FileInputStream in = null;
            try {
                in = new FileInputStream(classFile);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buff = new byte[1024];
                int len;
                while ((len=in.read())!=-1){
                    out.write(buff,0,len);
                }
                return defineClass(className,out.toByteArray(),0,out.size());
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
