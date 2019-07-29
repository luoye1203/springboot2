package com.lht.fConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;

/**
 * 多线程执行定时任务
 * @author 王久印
 * 2018年3月1日
 */
@Configuration
//所有的定时任务都放在一个线程池中，定时任务启动时使用不同都线程。
public class ScheduleConfig implements SchedulingConfigurer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //设定一个长度10的定时任务线程池
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));



        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
//                () -> logger.info("动态定时任务: " + LocalDateTime.now().toLocalTime()),
                ()->{
                    logger.info("可动态设定的定时任务:{}",LocalDateTime.now().toLocalTime());
                },

                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = "0/10 * * * * ? ";
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
