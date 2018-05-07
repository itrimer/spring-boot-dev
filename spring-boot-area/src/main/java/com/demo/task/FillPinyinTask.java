package com.demo.task;

import com.demo.dao.BaAreaDao;
import com.demo.manager.BaseManager;
import com.demo.model.BaArea;
import com.demo.util.HanyuPinyinHelper;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * FillPinyinTask
 */
@Component
public class FillPinyinTask {
	public static Log log = LogFactory.getLog(FillPinyinTask.class);

	@Resource
	private BaAreaDao baAreaDao;

	@Scheduled(cron = "0/10 * * * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
	public void fillPinyin() {
		List<BaArea> baAreas = baAreaDao.selectPinyinIsNull();
		log.error("查询区域数量="+ baAreas.size());

		for (BaArea baArea : baAreas) {
			String prePinyin = HanyuPinyinHelper.getFirstLetter(baArea.getAreaName());
			String smPinyin = HanyuPinyinHelper.getFirstLettersUp(baArea.getAreaName());
			String pinyin = HanyuPinyinHelper.getPinyinString(baArea.getAreaName());

			int i = baAreaDao.updatePinyin(baArea.getId(), pinyin, prePinyin, smPinyin);
			log.error("修改pinyin:" + baArea.getId() + ",i=" + i);
		}
	}

}