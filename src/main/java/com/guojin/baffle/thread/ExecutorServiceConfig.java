package com.guojin.baffle.thread;

/**
 * 线程池配置类
 */
public class ExecutorServiceConfig {

    public   static final  int   EXECUTOR_SERVICE_SIZE = Runtime.getRuntime().availableProcessors() ;//默认线程池大小(CPU 个数)

}
