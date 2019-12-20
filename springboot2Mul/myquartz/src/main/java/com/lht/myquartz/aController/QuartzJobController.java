package com.lht.myquartz.aController;

import com.lht.myquartz.bService.QuartzJobService;
import com.lht.myquartz.bService.TaskService;
import com.lht.myquartz.dModel.BaseResponse;
import com.lht.myquartz.dModel.JobEntity;
import com.lht.myquartz.util.JSONUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("定时任务")
@RequestMapping("/quartz/")
public class QuartzJobController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private QuartzJobService quartzJobService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/addJob", method = RequestMethod.POST)
    @ApiOperation(value = "添加作业",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "jobEntity",paramType = "body",value = "作业内容",dataType = "JobEntity")
            }
    )
    public BaseResponse addJob(@RequestBody JobEntity jobEntity) {
        logger.info("作业参数：{}", JSONUtils.jsonFormattedStr(jobEntity));

        try {
            quartzJobService.addJobByDynamic(jobEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BaseResponse response=BaseResponse.buildResponse().setObj(true).setCode(200).setMessage("添加成功").build();

        return response;

    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    @ApiOperation(value = "添加任务",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "jobEntity",paramType = "body",value = "作业内容",dataType = "JobEntity")
            }
    )
    public BaseResponse addTask(@RequestBody JobEntity jobEntity) {
        logger.info("作业参数：{}", JSONUtils.jsonFormattedStr(jobEntity));
        boolean successFlag=false;
        try {
            successFlag=taskService.addTask(jobEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BaseResponse response=BaseResponse.buildResponse().setObj(successFlag).setCode(200).setMessage("添加成功").build();

        return response;

    }


    @RequestMapping(value = "/delJob", method = RequestMethod.POST)
    @ApiOperation(value = "删除作业",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "jobEntity",paramType = "body",value = "作业内容",dataType = "JobEntity")
            }
    )
    public BaseResponse delJob(@RequestBody JobEntity jobEntity) {
        logger.info("作业参数：{}", JSONUtils.jsonFormattedStr(jobEntity));
        boolean delFlag=false;
        try {
            delFlag=quartzJobService.delJob(jobEntity.getJobName(),jobEntity.getJobGroup());
        } catch (Exception e) {
            e.printStackTrace();
            BaseResponse response=BaseResponse.buildResponse().setObj(delFlag).setCode(202).setMessage("添加失败").build();
            return response;
        }

        BaseResponse response=BaseResponse.buildResponse().setObj(delFlag).setCode(200).setMessage("添加成功").build();

        return response;

    }


}
