package com.ebaiyihui.controller;

import com.ebaiyihui.sdk.AcpService;
import com.ebaiyihui.sdk.LogUtil;
import com.ebaiyihui.sdk.SDKConfig;
import com.ebaiyihui.sdk.SDKConstants;
import com.ebaiyihui.vo.PayParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program service-feign-client
 * @description:
 * @author: chenmet
 * @create: 2020/04/08 14:29
 */
@RestController
@RequestMapping("/union")
public class NotifyUrlController {

    /**
     * 后台异步通知路径
     * @param req
     * @return
     */
    @PostMapping("/notifyurl")
    public ResponseEntity<Object> back(HttpServletRequest req){

        LogUtil.writeLog("BackRcvResponse接收后台通知开始");
        String encoding = req.getParameter(SDKConstants.param_encoding);

        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(req);

        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {

            Iterator<Map.Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap<String, String>(reqParam.size());

            while (it.hasNext()) {

                Map.Entry<String, String> e = it.next();
                String key = (String) e.getKey();
                String value = (String) e.getValue();
                valideData.put(key, value);
            }
        }

        String merId =valideData.get("merId"); //获取后台通知的数据，其他字段也可用类似方式获取
        PayParam.setConfig(PayParam.getConfig().get(merId));
//        SDKConfig.getConfig().loadPropertiesFromSrc();  //加载支付参数

        //重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!AcpService.validate(valideData, encoding)) {
            LogUtil.writeLog("验证签名结果[失败].");
            //验签失败，需解决验签问题
        } else {
            LogUtil.writeLog("验证签名结果[成功].");

            //【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

            String orderId =valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
            String respCode = valideData.get("respCode");
            System.out.println(orderId+respCode);
        }

        LogUtil.writeLog("BackRcvResponse接收后台通知结束");

        //返回给银联服务器http 200  状态码
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    /**
     * 获取参数集合
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(
            final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                // 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                if (res.get(en) == null || "".equals(res.get(en))) {
                    // System.out.println("======为空的字段名===="+en);
                    res.remove(en);
                }
            }
        }
        return res;
    }

}