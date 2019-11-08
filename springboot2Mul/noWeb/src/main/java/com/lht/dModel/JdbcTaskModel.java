package com.lht.dModel;

import lombok.Data;

@Data
public class JdbcTaskModel {
    private String taskId;
    private String taskVerisonId; 
    private String dataBaseUrl; 
    private String dataBaseUser; 
    private String dataBasePwd; 
    private String countSql; 
    private String extractSql; 
    private String dataBaseDriverName; 
    private String tableNameHdfs;
}
