package com.lht.dModel;

import lombok.Data;

import java.util.List;

@Data
public class JdbcTaskModel {
    private String taskId;
    private String taskVersionId;
    private String extractSql;
    private String tableNameHdfs;
    private String dbId;
    private String startTime;
    private String endTime;
    private List<String> columnList ;


}
