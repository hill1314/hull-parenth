package com.hull.test.instrument;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * 参考：https://www.toutiao.com/i6733163252718502414/?tt_from=weixin&utm_campaign=client_share&wxshare_count=1&timestamp=1593049661&app=news_article&utm_source=weixin&utm_medium=toutiao_android&use_new_style=1&req_id=202006250947410101310742173936AD42&group_id=6733163252718502414
 *
 * Instrument是JVM提供的一个可以修改已加载类的类库，专门为Java语言编写的插桩服务提供支持。它需要依赖JVMTI的Attach API机制实现
 *
 * @author
 * @create 2020-06-25 下午7:35
 **/

public class TestTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        System.out.println("Transforming " + className);
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("com.hull.test.asm.Base");
            CtMethod m = cc.getDeclaredMethod("process");
            m.insertBefore("{ System.out.println(\"start\"); }");
            m.insertAfter("{ System.out.println(\"end\"); }");
            return cc.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
