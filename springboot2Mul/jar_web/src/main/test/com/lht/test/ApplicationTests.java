package com.lht.test;

import com.alibaba.fastjson.JSON;
import com.lht.bService.UserService;
import com.lht.cDao.MybatisDao;
import com.lht.cDao.UserDao;
import com.lht.dModel.JdbcTaskModel;
import com.lht.dModel.User;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    // 实现
    @Test
    public void test1() {
        logger.info("---------------dao start---------------");
        User user=this.userService.getUserByAccountAndPassword("lht","77ed66e2fa056ebe82d54b84ae24c451");
        logger.info(user.toString());
        logger.info("---------------dao start**********---------------");
    }



    @Test
    public void testHttp() {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            String url = "http://127.0.0.1:9090/jdbcService/startJdbcTask";
            HttpPost request = new HttpPost(url);
            request.setHeader("Authorization", "Basic bWFzdGVyOm1vZGVsX21hc3Rlcg==");
            JdbcTaskModel model=new JdbcTaskModel();
            model.setTaskId("1111");
            model.setCountSql("select * from  t_table where t.name=\"hha\"");
            StringEntity entity = new StringEntity(JSON.toJSONString(model));
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            request.setEntity(entity);

            logger.info(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                Boolean result = JSON.parseObject(strResult, Boolean.class);
                logger.info(result.toString());
            } else {
                logger.error("-------{} 调用jdbcService 启动抽取出错");

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("-------{} 调用jdbcService 启动抽取出错 错误信息: ");
        }

    }





}
