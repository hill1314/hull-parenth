package com.hull.test.javassist;

import com.hull.test.asm.Base;
import javassist.*;

import java.io.IOException;

/**
 * Javassist 测试
 *
 * @author
 * @create 2020-06-25 下午7:13
 **/

public class JavassistTest {

    public static void main(String[] args) throws Exception{
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.hull.test.asm.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"start\"); }");
        m.insertAfter("{ System.out.println(\"end\"); }");
        Class c = cc.toClass();
        cc.writeFile("/Users/hill/Downloads/");
        Base h = (Base)c.newInstance();
        h.process();
    }
}
