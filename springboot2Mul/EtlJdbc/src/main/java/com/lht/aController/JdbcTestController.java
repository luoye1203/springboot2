package com.lht.aController;


import com.lht.bService.KafkaProduceService;
import com.lht.dModel.JdbcTaskModel;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("测试swagger集成服务")
@RequestMapping("/jdbcService/")
public class JdbcTestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private KafkaProduceService kafkaProduceService;


    @RequestMapping(value = "/startJdbcTask", method = RequestMethod.POST)
    @ApiOperation(value = "模拟启动任务",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "model",paramType = "body",value = "任务ti",dataType = "JdbcTaskModel")
            }
    )
    public boolean startJdbcTask(@RequestBody JdbcTaskModel model) {
        try {

            logger.info(model.toString());
            kafkaProduceService.monichulijdbstatus(model);



        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    @RequestMapping(value = "/delJdbcJobByTaskId", method = RequestMethod.GET)
    @ApiOperation(value = "删除任务",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "taskId",paramType = "query",value = "任务Id",dataType = "string",defaultValue = "1001")
            }
    )
    public boolean delJdbcJobByTaskId( @RequestParam String taskId) {
        try {
            logger.info(taskId);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }










}
