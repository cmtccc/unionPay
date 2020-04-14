package com.ebaiyihui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program service-feign-client
 * @description:
 * @author: chenmet
 * @create: 2020/04/08 14:57
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
