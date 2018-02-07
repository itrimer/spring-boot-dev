package com.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zhigang.huang on 2017/12/4.
 */
public class MyElasticJob implements SimpleJob {
    Logger logger = LoggerFactory.getLogger(MyElasticJob.class);
    private static String pi = "0";
    private String vvvvv = "0";
    private String thread_name = "0";
    static int cnt = 0;
    long rt = 0L;

    public MyElasticJob() {
        synchronized (pi) {
            try {
                Thread.sleep((long) (Math.random() * 100000L));
            } catch (InterruptedException e) {

            }

            logger.error("运行构造方法。。。");
            rt = System.currentTimeMillis();
            ++cnt;
        }
    }

    @Override
    public void execute(ShardingContext context) {
        if ("0".equals(vvvvv)) {
            vvvvv = "1000000020=" + context.getShardingItem();
        }
        if ("0".equals(thread_name)) {
            thread_name = Thread.currentThread().getId() + "," + Thread.currentThread().getName();
        }

        thread_name += " rt=" + Thread.currentThread().getId() + "," + Thread.currentThread().getName()
                + "=item=" + context.getShardingItem();
        logger.error(thread_name);
    }
}