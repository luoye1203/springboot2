package com.lht.myquartz.dModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.*;
import java.util.Date;

@Data
@ApiModel
public class JobEntity implements Serializable {
    private static final long serialVersionUID = 1099L;

    private String jobId;
    @ApiModelProperty(example = "")
    private String jobName;
    @ApiModelProperty(example = "")
    private String jobGroup;
    @ApiModelProperty( example = "0 * * * * ?")
    private String cronExpression;


    private Date startAtDate=new Date();

    @ApiModelProperty( example = "3")
    private int intervalInSeconds;
    @ApiModelProperty( example = "1")
    private int repeatCount;

    @ApiModelProperty( example = "false")
    private boolean cycleJob;

    @ApiModelProperty(example = "com.lht.myquartz.fJob.MyScheduleJob")
    private String jobClazzName;


    public void forbiddenSimpleTriggerVar(){
        this.intervalInSeconds=0;
        this.repeatCount=0;
        this.startAtDate=null;
    }

    public void forbiddenCycleTriggerVar(){
        this.cronExpression="";
    }




}
