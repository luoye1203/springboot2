package com.lht.myquartz.bService;

import com.lht.myquartz.cDao.TaskDao;
import com.lht.myquartz.dModel.JobEntity;
import com.lht.myquartz.dModel.TaskModel;
import com.lht.myquartz.util.ClazzUtils;
import com.lht.myquartz.util.JSONUtils;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    private QuartzJobService quartzJobService;

    @Autowired
    private TaskDao taskDao;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean addTask(JobEntity jobEntity) throws Exception{
        boolean successFlag = false;
        if (jobEntity.isCycleJob()) {
            successFlag = this.addCycleTask(jobEntity);
        } else {
            successFlag = this.addSimpleTask(jobEntity);
        }

        return successFlag;
    }


    public boolean addCycleTask(JobEntity jobEntity)  throws Exception{
        boolean successFlag = false;
        jobEntity.forbiddenSimpleTriggerVar();
        //
        if (CronExpression.isValidExpression(jobEntity.getCronExpression())) {
            String taskid = jobEntity.getJobId();
            if(StringUtils.isEmpty(taskid)){

            }
            String jobName="myjob_cycle__"+taskid;
            String jobGroup="default";
            jobEntity.setJobName(jobName);
            jobEntity.setJobGroup(jobGroup);

            String cronExpression = jobEntity.getCronExpression();

            Map<String, Class> classMap = ClazzUtils.getClazzNameAndObject("com.lht.myquartz.fJob", false);
            Class jobClazz = classMap.get(jobEntity.getJobClazzName());
            this.quartzJobService.addCycleJobByDynamic(jobName,jobGroup,cronExpression,jobClazz,jobEntity);
            successFlag=true;

        } else {
            throw new RuntimeException("invalid cron expression,please confirm...");
        }


        return successFlag;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean addSimpleTask(JobEntity jobEntity) throws Exception{
        boolean successFlag = false;
        jobEntity.forbiddenCycleTriggerVar();
        String taskid = jobEntity.getJobId();
        String jobName="myjob_simple_";
        String jobGroup="default";

        Map<String, Class> classMap = ClazzUtils.getClazzNameAndObject("com.lht.myquartz.fJob", false);
        Class jobClazz = classMap.get(jobEntity.getJobClazzName());

        Date startAtDate=jobEntity.getStartAtDate();
        int intervalInSeconds=jobEntity.getIntervalInSeconds();
        int repeatCount=jobEntity.getRepeatCount();
        TaskModel taskModel=new TaskModel();
        if(StringUtils.isEmpty(taskid)){
            taskid=this.getTaskId();
            taskModel.setTaskId(taskid);
            jobName=jobName+taskid;
            taskModel.setTaskName(jobName);
            jobEntity.setJobName(jobName);
            jobEntity.setJobGroup(jobGroup);
            jobEntity.setJobId(taskid);
            taskModel.setJobEntityStr(JSONUtils.jsonFormattedStr(jobEntity));
            this.saveTask(taskModel);
            taskid=this.getTaskId();
        }else{
            this.taskDao.updateTsk(taskModel);
        }
        int i=Integer.parseInt("1.25");
        successFlag=this.quartzJobService.addSimpleJobByDynamic(jobName,jobGroup,intervalInSeconds,repeatCount,startAtDate,jobClazz,jobEntity);

        return successFlag;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public boolean saveTask(TaskModel taskModel){
        boolean successFlag = false;
        int i=this.taskDao.saveTask(taskModel);
        if(i==1){
            successFlag=true;
        }
        return successFlag;
    }

    public boolean updateTsk(){
        boolean successFlag = false;



        return successFlag;
    }
    @Transactional(propagation = Propagation.NEVER)
//    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public String getTaskId(){

        return this.taskDao.getTaskId();
    }



}
