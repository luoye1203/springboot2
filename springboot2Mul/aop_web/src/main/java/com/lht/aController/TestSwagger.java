package com.lht.aController;


import com.lht.bService.MybatisService;
import com.lht.dAnnotation.VisitLog;
import com.lht.model.BaseResponse;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api("测试swagger集成服务")
@RequestMapping("/swagger/")
@VisitLog("test")
public class TestSwagger {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MybatisService mybatisService;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ApiOperation(value = "测试是否成功",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "message",paramType = "query",value = "消息内容",dataType = "string",defaultValue = "测试消息...........")
            }
    )
    @VisitLog("")
    public BaseResponse sendKafka(@RequestParam String message) {
        try {
            logger.info("测试成功");
            logger.info(env.getProperty("catalina.home"));
            logger.info(mybatisService.getDic().toString());

        } catch (Exception e) {
            logger.error("发送kafka失败",e);
            return BaseResponse.buildResponse().setCode(200).setMessage("发送失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("发送成功").build();
        return response;
    }








    @RequestMapping(value = "/send1", method = RequestMethod.GET)
    @ApiOperation(value = "测试是否成功",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "message",paramType = "query",value = "消息内容",dataType = "string",defaultValue = "测试消息...........")
            }
    )
    @VisitLog("")
    public BaseResponse sendKafka1(@RequestParam String message) {
        try {
            logger.info("测试成功");
            logger.info(env.getProperty("catalina.home"));
            logger.info(mybatisService.getDic().toString());

        } catch (Exception e) {
            logger.error("发送kafka失败",e);
            return BaseResponse.buildResponse().setCode(200).setMessage("发送失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setCode(200).setMessage("发送成功").build();
        return response;
    }

}
