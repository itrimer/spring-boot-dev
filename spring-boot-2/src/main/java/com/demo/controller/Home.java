package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

/**
 * Created by zhigang.huang on 2017/12/4.
 */
@Controller
public class Home {
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/")
    public String index(Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        String feature = messageSource.getMessage("spring.cloud.feature", null, locale);
        model.addAttribute("spring_cloud_feature", feature);
        return "index";
    }

}
