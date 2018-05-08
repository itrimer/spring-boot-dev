package com.demo.task;

import com.demo.dao.BaAreaDao;
import com.demo.manager.BaAreaManager;
import com.demo.model.BaArea;
import com.demo.util.HanyuPinyinHelper;
import com.demo.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FillPinyinTask
 */
@Component
public class FillPinyinTask {
	public static Log log = LogFactory.getLog(FillPinyinTask.class);

	@Resource
	private BaAreaManager baAreaManager;
	@Resource
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Scheduled(cron = "0 0/1 * * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
	public void fillPinyin() throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("prePinYin", "null");
		Page<BaArea> baAreas = baAreaManager.baseQuery(params, 1, 1000);
		log.error("查询区域数量="+ baAreas.getNumberOfElements());

		for (BaArea baArea : baAreas) {
			v(baArea.getAreaId());
		}
	}

	private void v(String areaId){
		threadPoolTaskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				BaArea baArea = baAreaManager.findOne(areaId);
				if(StringUtils.isNotEmpty(baArea.getPrePinYin())){
					return;
				}
				String prePinyin = HanyuPinyinHelper.getFirstLetter(baArea.getAreaName());
				String smPinyin = HanyuPinyinHelper.getFirstLettersUp(baArea.getAreaName());
				String pinyin = HanyuPinyinHelper.getPinyinString(baArea.getAreaName());

				int i = baAreaManager.updatePinyin(baArea.getAreaId(), pinyin, prePinyin, smPinyin);
				log.error("修改pinyin:" + areaId + ",i=" + i);
			}
		});
	}

}