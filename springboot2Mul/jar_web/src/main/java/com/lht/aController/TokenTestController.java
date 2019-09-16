package com.lht.aController;

import com.lht.dModel.BaseResponse;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("token测试")
@RequestMapping("/tokentest/")
public class TokenTestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation(value = "测试",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
            }
    )
    public BaseResponse test(@RequestParam String message) {
        try {
            logger.info("测试成功");

        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.buildResponse().setCode(200).setMessage("测试失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setObj("好无聊").setCode(200).setMessage("发送成功").build();
        return response;
    }


}
