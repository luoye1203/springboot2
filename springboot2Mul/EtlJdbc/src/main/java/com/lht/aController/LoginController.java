package com.lht.aController;

import com.lht.bService.UserService;
import com.lht.dModel.BaseResponse;
import com.lht.dModel.User;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api("登陆管理模块")
@RequestMapping("/loginmange/")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "登陆",notes = "")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "account",paramType = "query",value = "账号",dataType = "string",defaultValue = "com/lht"),
                    @ApiImplicitParam(name = "password",paramType = "query",value = "登录密码",dataType = "string",defaultValue = "测试消息...........")
            }
    )
    public BaseResponse test( @RequestParam() String account,@RequestParam String password) {

        User user=null;
        try {
            logger.info(account+" "+password);
            user=this.userService.getUserByAccountAndPassword(account, password);



        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.buildResponse().setCode(200).setMessage("发送失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setObj(user).setCode(200).setMessage("发送成功").build();
        return response;
    }


}
