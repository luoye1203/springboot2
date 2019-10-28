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
@Api("登陆管理模块")
@RequestMapping("/loginmange/")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "登陆",notes = "")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "account",paramType = "query",value = "账号",dataType = "string",defaultValue = "com/lht"),
                    @ApiImplicitParam(name = "password",paramType = "query",value = "登录密码",dataType = "string",defaultValue = "测试消息...........")
            }
    )
    public BaseResponse test( @RequestParam() String account,@RequestParam String password) {
        try {
            logger.info(account+" "+password);



        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.buildResponse().setCode(200).setMessage("发送失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setObj("好无聊").setCode(200).setMessage("发送成功").build();
        return response;
    }


}
