package com.lht.test;

import com.lht.bService.UserService;
import com.lht.cDao.MybatisDao;
import com.lht.cDao.UserDao;
import com.lht.dModel.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    // 实现
    @Test
    public void test1() {
        logger.info("---------------dao start---------------");
        User user=this.userService.getUserByAccountAndPassword("lht","77ed66e2fa056ebe82d54b84ae24c451");
        logger.info(user.toString());
        logger.info("---------------dao start**********---------------");
    }

}
