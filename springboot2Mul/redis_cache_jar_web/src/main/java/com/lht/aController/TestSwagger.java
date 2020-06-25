package com.lht.aController;


import com.lht.bService.CacherService;
import com.lht.dModel.BaseResponse;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api("测试swagger集成服务")
@RequestMapping("/swagger/")
public class TestSwagger {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CacherService cacherService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ApiOperation(value = "测试是否成功",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "user_id",paramType = "query",value = "消息内容",dataType = "string",defaultValue = "dwPnfXToatIZE9EE7uX8")
            }
    )
    public BaseResponse sendKafka( @RequestParam String user_id) {
        Object redata=null;
        try {

            redata=cacherService.getData(user_id);


        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.buildResponse().setCode(200).setMessage("发送失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setObj(redata).setCode(200).setMessage("发送成功").build();
        return response;
    }










}
