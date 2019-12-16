package com.lht.myquartz.fJob;


import com.lht.myquartz.bService.LoverService;
import com.lht.myquartz.dModel.JobEntity;
import com.lht.myquartz.util.JSONUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyScheduleJob implements Job {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoverService loverService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        JobKey jobKey = null;
        try {
            jobKey = jobExecutionContext.getJobDetail().getKey();
            String jobName=jobKey.getName();
            String jobGroup=jobKey.getGroup();
            JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
            JobEntity  jobEntity=(JobEntity)map.get("jobEntity");
            logger.info("{},{}: 任务启动了，参数为:{}",jobName,jobGroup, JSONUtils.jsonFormattedStr(map));
            loverService.sayLove();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
