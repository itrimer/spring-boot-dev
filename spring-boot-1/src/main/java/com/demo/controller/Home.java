package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhigang.huang on 2017/12/4.
 */
@Controller
public class Home {

    @ResponseBody
    @RequestMapping("/")
    String index() {
        return "Hello World!!!";
    }

}
