package com.datasource.springdatas.config;

/**
 * @author xuechaofu
 * @date 2019/5/30 20:44
 */
public class HandlerDataSource {
    private static ThreadLocal<String> handlerThreadLocal = new ThreadLocal<>();

    /**
     * @desction: 提供给AOP去设置当前的线程的数据源的信息
     */
    public static void putDataSource(String datasource) {
        handlerThreadLocal.set(datasource);
    }

    /**
     * @desction: 提供给AbstractRoutingDataSource的实现类，通过key选择数据源
     */
    public static String getDataSource() {
        return handlerThreadLocal.get();
    }

    /**
     * @desction: 使用默认的数据源
     */
    public static void clear() {
        handlerThreadLocal.remove();
    }
}
