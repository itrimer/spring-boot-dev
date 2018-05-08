package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@ComponentScan
@Component
@Configuration
public class CustomThreadPool {

    @Bean
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(1800);

//        <!-- AbortPolicy:直接抛出java.util.concurrent.RejectedExecutionException异常 -->
//        <!-- CallerRunsPolicy:主线程直接执行该任务，执行完之后尝试添加下一个任务到线程池中，可以有效降低向线程池内添加任务的速度 -->
//        <!-- DiscardOldestPolicy:抛弃旧的任务、暂不支持；会导致被丢弃的任务无法再次被执行 -->
//        <!-- DiscardPolicy:抛弃当前任务、暂不支持；会导致被丢弃的任务无法再次被执行 -->
//        <bean class = "java.util.concurrent.ThreadPoolExecutor$DiscardPolicy"   />
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.initialize();
        return executor;
    }
}
