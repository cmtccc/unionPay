package com.ebaiyihui.vo;

import com.ebaiyihui.sdk.CertUtil;
import com.ebaiyihui.sdk.SDKConfig;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @program service-feign-client
 * @description: 支付参数
 * @author: chenmet
 * @create: 2020/04/10 10:02
 */
@Data
public class PayParam {

    private String merId;

    private String frontUrl="http://47.105.52.207:8080/";

    private String backUrl="http://47.105.52.207:8080/union/notifyurl";

    private String signCert;

    private String signCertPwd;

    private String encryptCert;

    private String middleCert;

    private String rootCert;


    public static Map<String,PayParam> getConfig(){
        Map<String,PayParam> config=new HashMap<>();
        PayParam payParam=new PayParam();
        payParam.setMerId("777290058180131");
        payParam.setEncryptCert("/Users/mac/Documents/pay/acp_test_enc.cer");
        payParam.setMiddleCert("/Users/mac/Documents/pay/acp_test_middle.cer");
        payParam.setRootCert("/Users/mac/Documents/pay/acp_test_middle.cer");
        payParam.setSignCert("/Users/mac/Documents/pay/acp_test_sign.pfx");
        payParam.setSignCertPwd("000000");
        config.put(payParam.merId,payParam);

        PayParam payParam1=new PayParam();
        payParam1.setMerId("777290058180236");
        payParam1.setEncryptCert("/Users/mac/Documents/pay/acp_test_enc1.cer");
        payParam1.setMiddleCert("/Users/mac/Documents/pay/acp_test_middle1.cer");
        payParam1.setRootCert("/Users/mac/Documents/pay/acp_test_middle1.cer");
        payParam1.setSignCert("/Users/mac/Documents/pay/acp_test_sign1.pfx");
        payParam1.setSignCertPwd("000000");
        config.put(payParam1.merId,payParam1);
        return  config;
    }


    public static void setConfig(PayParam payParam){
        SDKConfig.getConfig().setSignCertPath(payParam.getSignCert());
        SDKConfig.getConfig().setSignCertPwd(payParam.getSignCertPwd());
        SDKConfig.getConfig().setEncryptCertPath(payParam.getEncryptCert());
        SDKConfig.getConfig().setMiddleCertPath(payParam.getMiddleCert());
        SDKConfig.getConfig().setRootCertPath(payParam.getRootCert());
        CertUtil.initCert();
    }

}
