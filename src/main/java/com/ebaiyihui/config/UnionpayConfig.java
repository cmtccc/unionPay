package com.ebaiyihui.config;

/**
 * @program service-feign-client
 * @description:
 * @author: chenmet
 * @create: 2020/04/08 14:26
 */
public class UnionpayConfig {

    /**商户号*/
    public static final String MER_ID = "777290058180131";

    /**
     * 前端异步通知地址
     */
    public static String FRONT_URL = "http://47.105.52.207:8080";

    /**
     * 后台异步通知地址
     */
    public static String BACK_URL = "http://47.105.52.207:8080/union/notifyurl";



}
