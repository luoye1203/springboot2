package com.lht.bService;


import com.lht.cDao.DaMengDataDao;
import com.lht.dModel.DmJdbcParamsModel;
import com.lht.dModel.DmModel;
import com.lht.util.CreateIDCardNo;
import com.lht.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private DaMengDataDao daMengDataDao;

    @Autowired
    private CreateIDCardNo createIDCardNo;
    public List<Map<String,String>> getData(){
        return daMengDataDao.getData();
    }

    public  void addData(){

        for (int i = 0; i <1000000 ; i++) {
            DmModel dmModel=new DmModel();
            String name="李"+i;
            Date now=new Date();
            SimpleDateFormat formatnow = new SimpleDateFormat("yyyy-MM-dd");
            Date date = DateUtil.randomDate("2019-10-01", formatnow.format(now));


            String sfz=createIDCardNo.getRandomID();
            dmModel.setName(name);
            dmModel.setBirth(date);
            dmModel.setSfz(sfz);
            this.daMengDataDao.addData(dmModel);
            logger.info("插入第{}条",i);
        }

    }

}
