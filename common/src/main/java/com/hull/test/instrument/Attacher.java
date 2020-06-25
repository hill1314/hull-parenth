package com.hull.test.instrument;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * https://www.toutiao.com/i6733163252718502414/?tt_from=weixin&utm_campaign=client_share&wxshare_count=1&timestamp=1593049661&app=news_article&utm_source=weixin&utm_medium=toutiao_android&use_new_style=1&req_id=202006250947410101310742173936AD42&group_id=6733163252718502414
 *
 *
 * @author
 * @create 2020-06-25 下午7:52
 **/

public class Attacher {
    public static void main(String[] args) throws Exception {
        // 传入目标 JVM pid
        VirtualMachine vm = VirtualMachine.attach("39333");
        vm.loadAgent("/Users/zen/operation_server_jar/operation-server.jar");
    }
}
