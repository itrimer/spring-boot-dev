package com.demo.task;

import com.demo.dao.BaAreaDao;
import com.demo.manager.BaAreaManager;
import com.demo.model.BaArea;
import com.demo.util.HanyuPinyinHelper;
import com.demo.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
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
    private BaAreaDao baAreaDao;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

//    @Scheduled(cron = "0 0/1 * * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
//    public void fillPinyin() throws Exception {
//        Map<String, String> params = new HashMap<>();
//        params.put("prePinYin", "null");
//        boolean did = false;
//        int pageNo = 1;
//        int pageSize = 1000;
//        int itemTotal = 0;
//        int pageTotal = 0;
//        do {
//            log.error("start pageSize=" + pageSize + ",pageNo=" + pageNo);
//            itemTotal = baAreaDao.countPinyinIsNull();
//            if (itemTotal > 0) {
//                List<String> areaIds = baAreaDao.selectPinyinIsNull();
//
//                if (pageNo == 1 && !did) {
//                    pageTotal = (itemTotal + pageSize - 1) / pageSize;
//                    pageNo = pageTotal;
//                    did = true;
//                }
//                log.error("end pageSize=" + pageSize + ",pageNo=" + pageNo + ",total_of_crt_page=" + pageTotal + ",total=" + itemTotal);
//
//                for (String areaId : areaIds) {
//                    fillPinYin(areaId);
//                }
//            }
//            log.error("put to threadpool pageSize=" + pageSize + ",pageNo=" + pageNo + ",total_of_crt_page=" + itemTotal);
//        } while (itemTotal > 1);
//    }

    @Scheduled(cron = "0 0/1 * * * ?")  //cron接受cron表达式，根据cron表达式确定定时规则
    public void fillPinyin2() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("prePinYin", "null");
        int pageNo = 1;
        boolean did = false;
        int pageSize = 1000;
        do {
            try {
                log.error("start pageSize=" + pageSize + ",pageNo=" + pageNo);
                Page<BaArea> baAreas = baAreaManager.baseQuery(params, pageNo, pageSize);
                log.error("end pageSize=" + pageSize + ",pageNo=" + pageNo + ",total_of_crt_page=" + baAreas.getNumberOfElements() + ",total=" + baAreas.getTotalElements());

                if (pageNo == 1 && !did) {
                    pageNo = baAreas.getTotalPages();
                    did = true;
                }

                for (BaArea baArea : baAreas) {
                    fillPinYin(baArea);
                }
                log.error("put to threadpool pageSize=" + pageSize + ",pageNo=" + pageNo + ",total_of_crt_page=" + baAreas.getNumberOfElements());
            } finally {
                pageNo--;
            }
        } while (pageNo > 1);
    }

    @Async
    private void fillPinYin(String areaId) {
        BaArea baArea = baAreaManager.findOne(areaId);
        if (StringUtils.isNotEmpty(baArea.getPrePinYin())) {
            return;
        }
        String prePinyin = HanyuPinyinHelper.getFirstLetter(baArea.getAreaName());
        String smPinyin = HanyuPinyinHelper.getFirstLettersUp(baArea.getAreaName());
        String pinyin = HanyuPinyinHelper.getPinyinString(baArea.getAreaName());

        int i = baAreaManager.updatePinyin(baArea.getAreaId(), pinyin, prePinyin, smPinyin);
//		log.error("修改pinyin:" + areaId + ",i=" + i);
    }

    private void fillPinYin(BaArea baArea) {
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (StringUtils.isNotEmpty(baArea.getPrePinYin())) {
                    return;
                }
                String prePinyin = HanyuPinyinHelper.getFirstLetter(baArea.getAreaName());
                String smPinyin = HanyuPinyinHelper.getFirstLettersUp(baArea.getAreaName());
                String pinyin = HanyuPinyinHelper.getPinyinString(baArea.getAreaName());

                int i = baAreaManager.updatePinyin(baArea.getAreaId(), pinyin, prePinyin, smPinyin);
//				log.error("修改pinyin:" + baArea.getAreaId() + ",i=" + i);
            }
        });
    }

}