package com.ebaiyihui.config;

import com.ebaiyihui.sdk.SDKConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @program service-feign-client
 * @description:
 * @author: chenmet
 * @create: 2020/04/08 14:41
 */
@Component
public class UnionPayInitializer implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        SDKConfig.getConfig().loadPropertiesFromSrc();
    }
}
