package com.lht.test;

import com.lht.cDao.MybatisDao;
import com.lht.dModel.TestMODEL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MybatisDao mybatisDao;

    // 实现
    @Test
    public void test1() {
        logger.info("---------------dao start---------------");
        mybatisDao.getDic();
        logger.info("---------------dao start**********---------------");
        mybatisDao.getLog();
    }


    @Test
    public void test2() {


        String s1 = "疯狂Java";
        String s2 = "疯狂";
        String s3 = "Java";
        String s4 = "疯" + "狂" + "Java";
        String s5 = s2 + s3;

        System.out.println(s1 == s4); //返回true
//s5无法引用常量池中的字符串，没有在编译时就确定下来
//        System.out.println(s1 == s5); //返回false


        TestMODEL testMODEL = new TestMODEL();
        TestMODEL testMODEL3 = new TestMODEL();
        TestMODEL testMODEL1 = testMODEL;
        TestMODEL testMODEL2 = testMODEL;
        logger.info(testMODEL.equals(testMODEL3) + "");


        Set set = new HashSet();
        set.add(null);
        set.add(null);
        set.add("111");
        for (Object o : set) {
            logger.info(o + "");
        }
    }



}
