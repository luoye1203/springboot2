package com.lht.dModel;

import lombok.Data;

@Data
public class DmJdbcParamsModel {
    private String username;
    private String password;
    private String ip;
    private String port;
    private String dbname;

}
