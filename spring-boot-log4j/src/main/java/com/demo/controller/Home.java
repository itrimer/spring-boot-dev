package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhigang.huang on 2017/12/4.
 */
@Controller
public class Home {
    Logger logger = LoggerFactory.getLogger(Home.class);

    @ResponseBody
    @RequestMapping("/")
    String index() {
        logger.error("hello world!!!");
        return "Hello World!!!";
    }

}
