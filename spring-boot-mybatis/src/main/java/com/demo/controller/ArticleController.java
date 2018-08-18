package com.demo.controller;

import com.demo.dao.ArticleDao;
import com.demo.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by zhigang.huang on 2017/12/4.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleDao articleDao;

    @ResponseBody
    @RequestMapping("/")
    public List<Article> index(Model model) {
        List<Article> iterator = articleDao.findByTitle("");

        return iterator;
    }

    @ResponseBody
    @RequestMapping("/save")
    public String save() {
        // 内存数据库操作
        int i = articleDao.insert(new Article("title1", "content1"));
        i = articleDao.insert(new Article("title2", "content2"));
        i = articleDao.insert(new Article("title3", "content3"));
        i = articleDao.insert(new Article("title4", "content4"));
        i = articleDao.insert(new Article("title5", "content5"));

        return "save ok";
    }

}
