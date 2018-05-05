package com.demo.controller;

import com.demo.manager.BaAreaManager;
import com.demo.model.AreaInfo;
import com.demo.model.BaArea;
import com.demo.vo.ResponseObject;
import org.springframework.beans.BeanUtils;
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
	private BaAreaManager baAreaManager;

	@ResponseBody
	@RequestMapping("/")
	public List<BaArea> index(Model model) {
		Iterable<BaArea> iterator = baAreaManager.findAll();

		List<BaArea> list = new ArrayList<>();
		iterator.forEach(single -> {
			list.add(single);
		});
		return list;
	}

	@ResponseBody
	@RequestMapping("/{areaId}")
	public ResponseObject<AreaInfo> get(@PathVariable(value = "areaId") String areaId) {
		ResponseObject<AreaInfo> resp = new ResponseObject<>();
		BaArea baArea = baAreaManager.findOne(areaId);
		if (baArea == null) {
			return resp.setResult(404, "地址不存在或被删除");
		}

		AreaInfo areaInfo = new AreaInfo();
		BeanUtils.copyProperties(baArea, areaInfo);

		resp.setData(areaInfo);
		return resp;
	}
}
