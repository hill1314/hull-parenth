package com.hull.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * 系统属性工具类
 *
 * @author
 * @create 2019-07-26 15:29
 **/

public class SystemUtil {

    private static final Log logger = LogFactory.getLog(SystemUtil.class);

    private static class SystemInfo{
        /**
         * 当前进程运行的主机名
         */
        private String host;
        /**
         * 当前进程所在的IP地址
         */
        private String ipAddress;
        /**
         * 空闲内存
         */
        private long freeMemory;
        /**
         * 内存总量
         */
        private long totalMemory;
        /**
         * java虚拟机允许开启的最大的内存
         */
        private long maxMemory;
        /**
         * Java虚拟机的可用的处理器数量
         */
        private int availableProcessors;
        /**
         * 操作系统名称
         */
        private String osName;
        /**
         * 进程号
         */
        private long pid;
        /**
         * 程序启动时间
         */
        private Date startTime;
        /**
         * 类所在路径
         */
        private String classPath;

        private String projectPath;

        /**
         * 程序运行时间，单位毫秒
         */
        private long runtime;
        /**
         * 线程总量
         */
        private int threadCount;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public long getFreeMemory() {
            return freeMemory;
        }

        public void setFreeMemory(long freeMemory) {
            this.freeMemory = freeMemory;
        }

        public long getTotalMemory() {
            return totalMemory;
        }

        public void setTotalMemory(long totalMemory) {
            this.totalMemory = totalMemory;
        }

        public long getMaxMemory() {
            return maxMemory;
        }

        public void setMaxMemory(long maxMemory) {
            this.maxMemory = maxMemory;
        }

        public int getAvailableProcessors() {
            return availableProcessors;
        }

        public void setAvailableProcessors(int availableProcessors) {
            this.availableProcessors = availableProcessors;
        }

        public String getOsName() {
            return osName;
        }

        public void setOsName(String osName) {
            this.osName = osName;
        }

        public long getPid() {
            return pid;
        }

        public void setPid(long pid) {
            this.pid = pid;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public String getClassPath() {
            return classPath;
        }

        public void setClassPath(String classPath) {
            this.classPath = classPath;
        }

        public String getProjectPath() {
            return projectPath;
        }

        public void setProjectPath(String projectPath) {
            this.projectPath = projectPath;
        }

        public long getRuntime() {
            return runtime;
        }

        public void setRuntime(long runtime) {
            this.runtime = runtime;
        }

        public int getThreadCount() {
            return threadCount;
        }

        public void setThreadCount(int threadCount) {
            this.threadCount = threadCount;
        }

        @Override
        public String toString() {
            return "SystemInfo{" +
                    "host='" + host + '\'' +
                    ", ipAddress='" + ipAddress + '\'' +
                    ", freeMemory=" + freeMemory +
                    ", totalMemory=" + totalMemory +
                    ", maxMemory=" + maxMemory +
                    ", osName='" + osName + '\'' +
                    ", pid=" + pid +
                    ", startTime=" + startTime +
                    ", classPath='" + classPath + '\'' +
                    ", projectPath='" + projectPath + '\'' +
                    ", runtime=" + runtime +
                    ", threadCount=" + threadCount +
                    '}';
        }
    }

    public static SystemInfo getSysInfo(){
        Runtime runtime = Runtime.getRuntime();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        SystemInfo systemInfo = new SystemInfo();

        //内存总量
        long totalMemory = runtime.totalMemory();
        systemInfo.setTotalMemory(byteToM(totalMemory));

        //空闲内存
        long freeMemory = runtime.freeMemory();
        systemInfo.setFreeMemory(byteToM(freeMemory));

        //最大允许使用的内存
        long maxMemory = runtime.maxMemory();
        systemInfo.setMaxMemory(byteToM(maxMemory));

        //可用的处理器数量
        systemInfo.setAvailableProcessors(runtime.availableProcessors());

        //操作系统
        String osName = System.getProperty("os.name");
        systemInfo.setOsName(osName);

        //程序启动时间
        long startTime = runtimeMXBean.getStartTime();
        Date startDate = new Date(startTime);
        systemInfo.setStartTime(startDate);
        //进程号
        systemInfo.setPid(parsePid());

        //类所在路径
        systemInfo.setClassPath(runtimeMXBean.getBootClassPath());
        //程序运行时间
        systemInfo.setRuntime(runtimeMXBean.getUptime());
        //线程总数
        systemInfo.setThreadCount(ManagementFactory.getThreadMXBean().getThreadCount());
        //项目路径
        systemInfo.setProjectPath(new File("").getAbsolutePath());

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            systemInfo.setHost(localHost.getHostName());
            systemInfo.setIpAddress(localHost.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.error("无法获取当前主机的主机名与Ip地址");
        }
        return systemInfo;
    }

    /**
     * 获取进程号，适用于windows与linux
     * @return
     */
    private static long parsePid(){
        try {
            String name = ManagementFactory.getRuntimeMXBean().getName();
            String pid = name.split("@")[0];
            return Long.parseLong(pid);
        } catch (NumberFormatException e) {
            logger.warn("无法获取进程Id");
            return 0;
        }
    }

    /**
     * 把byte转换成M
     * @param bytes
     * @return
     */
    private static long byteToM(long bytes){
        long kb =  (bytes / 1024 / 1024);
        return kb;
    }

    public static void main(String[] args) {
        System.out.println(getSysInfo());
    }
}
