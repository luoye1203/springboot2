package com.lht.aController;

import com.lht.bService.DmJdbcService;
import com.lht.dModel.DmJdbcParamsModel;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("测试达梦数据库")
@RequestMapping("/dmJdbcService/")
public class DmJdbcController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Value("${spring.kafka.producer.topic}")
    private String topicName;


    @Autowired
    private DmJdbcService dmJdbcService;

    @Autowired
    private KafkaTemplate kafkaTemplate;



    @RequestMapping(value = "/testConnect", method = RequestMethod.POST)
    @ApiOperation(value = "测试联通",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "model",paramType = "body",value = "组合参数",dataType = "DmJdbcParamsModel")
            }
    )
    public boolean startJdbcTask(@RequestBody DmJdbcParamsModel model) {
        try {

            logger.info(model.toString());
            dmJdbcService.testJdbcConnect(model);


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }







    @RequestMapping(value = "/testKafkaProduct", method = RequestMethod.POST)
    @ApiOperation(value = "测试kafka发送",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "message",paramType = "query",value = "",dataType = "string")
            }
    )
    public boolean testKafkaProduct(@RequestParam("message") String message) {
        try {

            logger.info(message);
            kafkaTemplate.send(topicName, "key", message);
            logger.info("发送完毕");


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }




    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ApiOperation(value = "获取数据",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
//                    @ApiImplicitParam(name = "message",paramType = "query",value = "",dataType = "string")
            }
    )
    public boolean getData() {
        try {

            List list=this.dmJdbcService.getData();
            logger.info(list.toString());


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @RequestMapping(value = "/addData", method = RequestMethod.GET)
    @ApiOperation(value = "增加数据",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
//                    @ApiImplicitParam(name = "message",paramType = "query",value = "",dataType = "string")
            }
    )
    public boolean addData() {
        try {

            this.dmJdbcService.addData();



        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
