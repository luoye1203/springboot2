package com.lht.myquartz.dModel;

import lombok.Data;

import java.io.*;

@Data
public class JobEntity implements Serializable {
    private static final long serialVersionUID = 1099L;

    private String jobId;
    private String jobName;
    private String jobGroup;
    private String cronExpression;
    private boolean cycleJob;
    private String jobClazzName;

    @Override
    public String toString() {
        return "JobEntity{" +
                "jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", cycleJob=" + cycleJob +

                '}';
    }


}
