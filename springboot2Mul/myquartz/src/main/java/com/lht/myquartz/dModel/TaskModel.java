package com.lht.myquartz.dModel;

import lombok.Data;

@Data
public class TaskModel {
    private String taskId;
    private String taskName;
    private String createTime;
    private String jobEntityStr;
    private JobEntity jobEntity;
}
