package work.huangxin.collect.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import work.huangxin.collect.service.MessageService;

import java.text.SimpleDateFormat;

@Configuration
@EnableScheduling
@Component
/**
 * 执行定时清理数据库
 */
public class Task {

    @Autowired
    private MessageService messageService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 默认单线程，可能存在堵塞，多给几个线程
     *
     * @return
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

    @Scheduled(cron = "0 0 23 * * ?")
//    @Scheduled(fixedRate = 1000)
    private void reportCurrentTime() {
        messageService.delete(null);
    }
}
