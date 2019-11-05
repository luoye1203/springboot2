package com.lht.bController;

import com.lht.bService.DmJdbcService;
import com.lht.dModel.DmJdbcParamsModel;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "达梦数据库测试")
@RequestMapping("/dmJdbcService/")
public class DmJdbcController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private DmJdbcService dmJdbcService;



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


}
