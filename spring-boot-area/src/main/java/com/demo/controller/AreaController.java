package com.demo.controller;

import com.demo.manager.BaAreaManager;
import com.demo.util.StringUtils;
import com.demo.vo.AreaInfo;
import com.demo.model.BaArea;
import com.demo.vo.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zhigang.huang on 2018/05/04.
 */
@Controller
@RequestMapping("/area")
public class AreaController {
	private static Logger logger = LoggerFactory.getLogger(AreaController.class);
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

	protected Map<String, String> getParameterMap(HttpServletRequest request) throws Exception {
		Map<String, String> resultMap = new HashMap<>();
		Map<String, String[]> tempMap = request.getParameterMap();
		Set<String> keys = tempMap.keySet();
		for (String key : keys) {
			String value = request.getParameter(key);
			resultMap.put(key, value);
		}
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "/search")
	public Map<String, Object> search(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		Page<AreaInfo> pagination = null;

		try {
			String spageNo = request.getParameter("pageNo");
			String spageSize = request.getParameter("pageSize");
			int pageNo = StringUtils.isNumeric(spageNo) ? Integer.parseInt(spageNo) : 1;
			if(pageNo < 0){
				pageNo = 1;
			}
			int pageSize = StringUtils.isNumeric(spageSize) ? Integer.parseInt(spageSize) : 10;
			if(pageSize <= 1){
				pageSize = 10;
			}

			Map<String, String> params = getParameterMap(request);
			pagination = baAreaManager.query(params, pageNo, pageSize);

			result.put("code", "0");
			result.put("msg", "OK");
			result.put("data", pagination);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}


	@ResponseBody
	@RequestMapping("/get/{areaId}")
	public ResponseObject<AreaInfo> get(@PathVariable(value = "areaId") String areaId) {
		ResponseObject<AreaInfo> resp = new ResponseObject<>();

		AreaInfo areaInfo = baAreaManager.buildArea(areaId, null);
		if (areaInfo == null) {
			return resp.setResult(404, "地址不存在或被删除");
		}

		resp.setData(areaInfo);
		return resp;
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
