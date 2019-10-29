package com.lht.aController;


import com.lht.bService.ExportService;
import com.lht.dModel.BaseResponse;
import com.lht.dModel.JdbcTaskModel;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Api("测试swagger集成服务")
@RequestMapping("/jdbcService/")
public class JdbcTestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



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





    @RequestMapping(value = "/getIp", method = RequestMethod.GET)
    @ApiOperation(value = "获取Ip",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
//                    @ApiImplicitParam(name = "taskId",paramType = "query",value = "任务Id",dataType = "string",defaultValue = "1001")
            }
    )
    public boolean getIp( HttpServletRequest request) {
        try {
            String ip = request.getRemoteAddr();
            logger.info(ip);
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            logger.info(dateFormat.format(date));


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }







}
