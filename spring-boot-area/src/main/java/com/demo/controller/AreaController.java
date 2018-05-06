package com.demo.controller;

import com.demo.manager.BaAreaManager;
import com.demo.vo.AreaInfo;
import com.demo.model.BaArea;
import com.demo.vo.ResponseObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
	@RequestMapping("/get/{areaId}")
	public ResponseObject<AreaInfo> get(@PathVariable(value = "areaId") String areaId) {
		ResponseObject<AreaInfo> resp = new ResponseObject<>();

		AreaInfo areaInfo = buildArea(areaId, null);
		if (areaInfo == null) {
			return resp.setResult(404, "地址不存在或被删除");
		}

		resp.setData(areaInfo);
		return resp;
	}

	private AreaInfo buildArea(String areaId, AreaInfo areaInfo) {
		BaArea baArea = baAreaManager.findOne(areaId);
		if(baArea == null){
			return areaInfo;
		}

		if(areaInfo == null) {
			areaInfo = new AreaInfo();
		}
		if(null == areaInfo.getId()){
			BeanUtils.copyProperties(baArea, areaInfo);
		}
		areaInfo.setWholeName(buildWholeName(areaInfo.getWholeName(), baArea.getAreaName()));
		if(baArea.getLevel() == 1){
			areaInfo.setProvinceId(baArea.getId());
		}
		if(baArea.getLevel() == 2){
			areaInfo.setCityId(baArea.getId());
		}
		if(baArea.getLevel() == 3){
			areaInfo.setCountyId(baArea.getId());
		}
		if("0".equals(baArea.getParentId())){
			return areaInfo;
		}
		return buildArea(baArea.getParentId(), areaInfo);
	}

	private String buildWholeName(String targetName, String parentAreaName) {
		if(StringUtils.isEmpty(targetName)){
			return parentAreaName;
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(parentAreaName).append(" ").append(targetName);
		return stringBuilder.toString();
	}

	@ResponseBody
	@RequestMapping("/save")
	public String save() {
		// 内存数据库操作
		BaArea article = baAreaManager.save(new BaArea("330000000000", "浙江省", "0", 1));
		article = baAreaManager.save(new BaArea("330100000000", "杭州市", "330000000000", 2));
		article = baAreaManager.save(new BaArea("330101000000", "市辖区", "330100000000", 3));
		article = baAreaManager.save(new BaArea("330102000000", "上城区", "330100000000", 3));
		article = baAreaManager.save(new BaArea("330110000000", "余杭区", "330100000000", 3));

		return "save ok";
	}
}
