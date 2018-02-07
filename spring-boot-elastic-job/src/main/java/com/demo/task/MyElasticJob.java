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
    private String vvvvv = "0";
    private String thread_name = "0";


    @Override
    public void execute(ShardingContext context) {
        if ("0".equals(vvvvv)) {
            vvvvv = "1000000020=" + context.getShardingItem();
        }
        if ("0".equals(thread_name)) {
            thread_name = Thread.currentThread().getId() + "," + Thread.currentThread().getName();
        }

        System.out.println(thread_name + "==alive==" + Thread.currentThread().getId() + "," + Thread.currentThread().getName()
                + "=item=" + context.getShardingItem()
        );
    }
}