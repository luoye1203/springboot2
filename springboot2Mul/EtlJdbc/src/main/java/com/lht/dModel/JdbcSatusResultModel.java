package com.lht.dModel;


import lombok.Data;

@Data
public class JdbcSatusResultModel {

    private String taskId;
    private String taskVersionId;
    private String countHdfs;
    private String statusCode;
    
}
