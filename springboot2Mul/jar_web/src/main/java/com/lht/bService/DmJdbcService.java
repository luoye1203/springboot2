package com.lht.bService;


import com.lht.dModel.DmJdbcParamsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class DmJdbcService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    public void testJdbcConnect(DmJdbcParamsModel dmJdbcParamsModel) {

        try {
            Class.forName("dm.jdbc.driver.DmDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String url = "jdbc:dm://" + dmJdbcParamsModel.getIp() + ":" + dmJdbcParamsModel.getPort() + "/" + dmJdbcParamsModel.getDbname();
        try {
            connection = DriverManager.getConnection(url, dmJdbcParamsModel.getUsername(), dmJdbcParamsModel.getPassword());

            //3. 创建命令
            statement = connection.createStatement();

            //4. 准备SQL语句，执行
            String sql = "select 1";
            resultSet = statement.executeQuery(sql);
            logger.info("测试成功");
            while(resultSet.next()){
                logger.info(resultSet.getObject(1).toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeJdbc(connection, statement, resultSet);
        }
    }

    public void getAllTables(DmJdbcParamsModel dmJdbcParamsModel) {

        try {
            Class.forName("dm.jdbc.driver.DmDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String url = "jdbc:dm://" + dmJdbcParamsModel.getIp() + ":" + dmJdbcParamsModel.getPort() + "/" + dmJdbcParamsModel.getDbname();
        try {
            connection = DriverManager.getConnection(url, dmJdbcParamsModel.getUsername(), dmJdbcParamsModel.getPassword());

            //3. 创建命令
            statement = connection.createStatement();

            //4. 准备SQL语句，执行
            String sql = "select 1";
            resultSet = statement.executeQuery(sql);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeJdbc(connection, statement, resultSet);
        }
    }





    private void closeJdbc(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            //关闭结果集
            if (resultSet != null) {
                resultSet.close();
            }
            //关闭命令
            if (statement != null) {
                statement.close();
            }
            //关闭连接
            if (connection != null) {
                connection.close();
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}
