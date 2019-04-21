package com.lht.test;

import com.lht.cDao.MybatisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private MybatisDao mybatisDao;

    // 实现
    @Test
    public void test1() {
        logger.info("---------------dao start---------------");
        mybatisDao.getDic();
        logger.info("---------------dao start**********---------------");
        mybatisDao.getLog();
    }

}
