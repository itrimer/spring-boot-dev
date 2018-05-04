package com.demo.controller;

import com.demo.dao.AreaDao;
import com.demo.model.AreaInfo;
import com.demo.model.BaArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhigang.huang on 2018/05/04.
 */
@Controller
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaDao areaDao;

    @ResponseBody
    @RequestMapping("/")
    public List<BaArea> index(Model model) {
        Iterable<BaArea> iterator = areaDao.findAll();

        List<BaArea> list = new ArrayList<>();
        iterator.forEach(single -> {
            list.add(single);
        });
        return list;
    }

    @ResponseBody
    @RequestMapping("/save")
    public String save() {
        // 内存数据库操作
        BaArea article = areaDao.save(new BaArea("title1", "content1"));
        article = areaDao.save(new BaArea("title2", "content2"));
        article = areaDao.save(new BaArea("title3", "content3"));
        article = areaDao.save(new BaArea("title4", "content4"));
        article = areaDao.save(new BaArea("title5", "content5"));

        return "save ok";
    }

    @ResponseBody
    @RequestMapping("/save/{areaId}")
    public AreaInfo get(@PathVariable(value = "areaId") String areaId) {
        BaArea areaInfo = areaDao.findOne(areaId);

        return null;
    }

}
