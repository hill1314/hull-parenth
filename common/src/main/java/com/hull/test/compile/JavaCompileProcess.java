package com.hull.test.compile;


import com.hull.test.proxy.jdk.JinJiRenProxy;
import com.hull.test.proxy.jdk.LiuDeHua;
import com.hull.test.proxy.jdk.Singer;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

/**
 * java的编译过程
 *
 * @author
 * @create 2018-08-04 下午8:56
 **/

public class JavaCompileProcess {


    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new MyClassLoader();
        InvocationHandler handler = new JinJiRenProxy(new LiuDeHua("DH"));

        Singer singer = (Singer) newProxyInstance(classLoader,new Class[]{Singer.class},handler);
        singer.dance();

    }

    public static Object newProxyInstance(ClassLoader classLoader,
                                          Class[] interfaces,
                                          InvocationHandler handler) throws Exception{
        //1 构造源码
        String javaSrc = prepareSrc();

        //2、将生成的源码输出道磁盘，保存为.java 文件
        String filePath = JavaCompileProcess.class.getResource("").getPath();
        System.out.println("filePath:"+filePath);
        File javaFile = new File(filePath+"$Proxy0.java");
        FileWriter fw = new FileWriter(javaFile);
        fw.write(javaSrc);
        fw.flush();
        fw.close();


        //3、编译源码，生成.class 文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(
                null,null,null);
        Iterable iterable = manager.getJavaFileObjects(javaFile);

        JavaCompiler.CompilationTask task = compiler.getTask(
                null,manager,null,null,null,iterable);
        task.call();
        manager.close();

        //4、将class文件中的内容，动态加载到jvm中


        //5、返回被代理后的代理对象
        Class proxyClass = classLoader.loadClass(filePath+"$Proxy0");
        Constructor constructor = proxyClass.getConstructor(InvocationHandler.class);

        return constructor.newInstance(handler);
    }


    private static String prepareSrc() {
        String src = "package com.hull.test.proxy;\n" +
                "\n" +
                "\n" +
                "public class ZhangManYu implements Singer {\n" +
                "\n" +
                "    private String name;\n" +
                "\n" +
                "    public ZhangManYu(String name){\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "\n" +
                "    public String sing(){\n" +
                "        System.out.println(name + \" 在唱歌...\");\n" +
                "        return \"success\";\n" +
                "    }\n" +
                "\n" +
                "    public String dance(){\n" +
                "        System.out.println(name + \" 在跳舞...\");\n" +
                "        return \"success\";\n" +
                "    }\n" +
                "\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    public void setName(String name) {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "}\n";

        return src;
    }


}
