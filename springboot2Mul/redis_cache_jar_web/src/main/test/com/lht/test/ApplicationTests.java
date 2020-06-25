package com.lht.test;

import com.lht.cDao.CacheDao;
import com.lht.dModel.RecordModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Scanner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CacheDao cacheDao;

    // 实现
    @Test
    public void test1() {
        logger.info("---------------dao start---------------");
        RecordModel recordModel=this.cacheDao.getData("dwPnfXToatIZE9EE7uX8");
        logger.info(recordModel.toString());
        logger.info("---------------dao start**********---------------");
    }

    @Test
    public  void test2(){
        Scanner in=new Scanner(System.in);
        int[] arr=new int[3];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=in.nextInt();
        }
        Arrays.sort(arr);
        logger.info(arr.toString());
    }







}
