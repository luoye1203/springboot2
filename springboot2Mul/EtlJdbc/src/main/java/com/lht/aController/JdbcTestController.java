package com.lht.aController;


import com.lht.bService.KafkaProduceService;
import com.lht.dModel.DataSourceModel;
import com.lht.dModel.JdbcTaskModel;
import com.lht.thread.KafkaSendThread;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api("测试swagger集成服务")
@RequestMapping("/etl/")
public class JdbcTestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private KafkaProduceService kafkaProduceService;


    @RequestMapping(value = "/sendTask", method = RequestMethod.POST)
    @ApiOperation(value = "模拟启动任务", notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "model", paramType = "body", value = "任务ti", dataType = "JdbcTaskModel")
            }
    )
    public Map startJdbcTask(@RequestBody JdbcTaskModel model1) {
        Map map=new HashMap<>();
        map.put("flag",true);
        try {

//            logger.info(model1.toString());
            model1.setStatusCode("1");
            KafkaSendThread kafkaSendThread = new KafkaSendThread(kafkaProduceService, model1);
            Thread thread = new Thread(kafkaSendThread);
            thread.start();

            JdbcTaskModel model2 = new JdbcTaskModel();
            BeanUtils.copyProperties(model1, model2);
            model2.setStatusCode("2");
            KafkaSendThread kafkaSendThread2 = new KafkaSendThread(kafkaProduceService, model2);
            Thread thread2 = new Thread(kafkaSendThread2);
            thread2.start();


        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag",false);
            return map;
        }
        return map;
    }

    @RequestMapping(value = "/delTask", method = RequestMethod.POST)
    @ApiOperation(value = "删除任务", notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "model", paramType = "body", value = "任务ti", dataType = "JdbcTaskModel")
            }
    )
    public Map delJdbcJobByTaskId(@RequestBody JdbcTaskModel model) {
        Map map=new HashMap<>();
        map.put("flag",true);
        try {
            logger.info(model.toString());
            logger.info("模拟删除任务成功");

        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag",false);
            return map;
        }
        return map;
    }






    @RequestMapping(value = "/saveDataSource", method = RequestMethod.POST)
    @ApiOperation(value = "保存数据源", notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "model", paramType = "body", value = "任务ti", dataType = "DataSourceModel")
            }
    )
    public Map saveDataSource(@RequestBody DataSourceModel model) {
        Map map=new HashMap<>();
        map.put("flag",true);
        try {
            logger.info(model.toString());
            logger.info("模拟保存数据源成功");

        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag",false);
            return map;
        }
        return map;
    }


    @RequestMapping(value = "/delDataSource", method = RequestMethod.POST)
    @ApiOperation(value = "删除数据源", notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "model", paramType = "body", value = "任务ti", dataType = "DataSourceModel")
            }
    )
    public Map delDataSource(@RequestBody DataSourceModel model) {
        Map map=new HashMap<>();
        map.put("flag",true);
        try {
            logger.info(model.toString());
            logger.info("模拟删除数据源成功");

        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag",false);
            return map;
        }
        return map;
    }

}
