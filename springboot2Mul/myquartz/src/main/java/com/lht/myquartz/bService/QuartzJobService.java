package com.lht.myquartz.bService;

import com.lht.myquartz.dModel.JobEntity;
import com.lht.myquartz.fJob.MyScheduleJob;
import com.lht.myquartz.util.ClazzUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class QuartzJobService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public void addJob(JobEntity jobEntity) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobEntity", jobDataMap);

        JobDetail jobDetail = JobBuilder.newJob(MyScheduleJob.class)
                .withIdentity(jobEntity.getJobName(), jobEntity.getJobGroup())
                .usingJobData("jobId", jobEntity.getJobId())
                .build();
        jobDetail.getJobDataMap().put("jobEntity", jobEntity);
        //周期任务
        if (jobEntity.isCycleJob()) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                    .cronSchedule(jobEntity.getCronExpression());
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobEntity.getJobName(), jobEntity.getJobGroup())
                    .withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
            logger.info("周期任务添加成功");
        } else {//非周期任务
            SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobEntity.getJobName(), jobEntity.getJobGroup())
                    .usingJobData("jobId", jobEntity.getJobId())
                    .startAt(new Date())
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0))
                    .build();
            scheduler.scheduleJob(jobDetail, simpleTrigger);
            logger.info("单次任务添加成功");
        }
    }


    public void addJobByDynamic(JobEntity jobEntity) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();


        Map<String, Class> classMap = ClazzUtils.getClazzNameAndObject("com.lht.myquartz.fJob", false);

        JobDetail jobDetail = JobBuilder.newJob(classMap.get(jobEntity.getJobClazzName()))
                .withIdentity(jobEntity.getJobName(), jobEntity.getJobGroup())
                .usingJobData("jobId", jobEntity.getJobId())
                .build();
        jobDetail.getJobDataMap().put("jobEntity", jobEntity);
        //周期任务
        if (jobEntity.isCycleJob()) {

            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                    .cronSchedule(jobEntity.getCronExpression());
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobEntity.getJobName(), jobEntity.getJobGroup())
                    .withSchedule(cronScheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, cronTrigger);
            logger.info("周期任务添加成功");
        } else {//非周期任务
            SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobEntity.getJobName(), jobEntity.getJobGroup())
                    .usingJobData("jobId", jobEntity.getJobId())
                    .startAt(new Date())
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).withRepeatCount(1))
                    .build();
            scheduler.scheduleJob(jobDetail, simpleTrigger);
            logger.info("简单任务添加成功");
        }
    }


    public boolean addSimpleJobByDynamic(String jobName, String jobGroup,int intervalInSeconds,int repeatCount,Date startAtDate, Class jobClazz, Object jobEntityObject ) throws Exception{
        boolean successFlag = false;

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(jobClazz).withIdentity(jobName, jobGroup).build();

        jobDetail.getJobDataMap().put("jobEntityObject", jobEntityObject);

        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, jobGroup)
                .startAt(startAtDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(intervalInSeconds).withRepeatCount(repeatCount))
                .build();

        scheduler.scheduleJob(jobDetail, simpleTrigger);
        successFlag=true;
        logger.info("简单任务添加成功");

        return successFlag;
    }

    public boolean addCycleJobByDynamic(String jobName, String jobGroup, String cronExpression, Class jobClazz, Object jobEntityObject) throws Exception {
        boolean successFlag = false;

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(jobClazz).withIdentity(jobName, jobGroup).build();

        jobDetail.getJobDataMap().put("jobEntityObject", jobEntityObject);

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(cronScheduleBuilder).build();

        scheduler.scheduleJob(jobDetail, cronTrigger);

        successFlag = true;

        logger.info("周期任务添加成功");

        return successFlag;
    }

    public boolean delJob(String jobName, String jobGroup) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        JobKey jobKey = new JobKey(jobName, jobGroup);
        boolean delFlag = false;
        try {
            scheduler.pauseJob(jobKey);
            if (scheduler.unscheduleJob(triggerKey) && scheduler.deleteJob(jobKey)) {
                delFlag = true;
            } else {
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException(jobName + "" + jobGroup + "删除作业出错");

        }

        return delFlag;
    }
}
