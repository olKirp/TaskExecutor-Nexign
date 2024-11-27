package com.example.taskexecutor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ExecutorConfig {

    private static final int DEFAULT_THREADS_NUM = 10;

    private int threadsNum;

    @Value("${threads.number}")
    public void setThreadsNum(String threadsNum) {
        this.threadsNum = parseThreadNum(threadsNum);
        if (this.threadsNum < 1)
            this.threadsNum = DEFAULT_THREADS_NUM;
    }

    @Bean
    public ThreadPoolExecutor executor() {
        return new ThreadPoolExecutor(threadsNum, threadsNum, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    private int parseThreadNum(String threadsNum) {
        try {
            return Integer.parseInt(threadsNum);
        } catch (NumberFormatException e) {
            return DEFAULT_THREADS_NUM;
        }
    }
}
