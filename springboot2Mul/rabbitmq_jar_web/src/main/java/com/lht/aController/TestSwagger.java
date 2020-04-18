package com.lht.aController;


import com.lht.dModel.BaseResponse;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Api("测试swagger集成服务")
@RequestMapping("/swagger/")
public class TestSwagger {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private Environment env;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ApiOperation(value = "测试是否成功",notes = "")
    @ApiParam(required = true)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "message",paramType = "query",value = "消息内容",dataType = "string",defaultValue = "测试消息...........")
            }
    )
    public BaseResponse sendKafka(HttpServletRequest request, @RequestParam String message) {
        try {
            logger.info("测试成功");
            logger.info("123");



        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.buildResponse().setCode(200).setMessage("发送失败").build();
        }
        BaseResponse response=BaseResponse.buildResponse().setObj("好无聊").setCode(200).setMessage("发送成功").build();
        return response;
    }



    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @RequestMapping(value = "/sendDirectMessage" , method = RequestMethod.GET)
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }






}
