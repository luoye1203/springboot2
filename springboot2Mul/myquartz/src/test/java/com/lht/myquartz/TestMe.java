package com.lht.myquartz;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;

public class TestMe {
    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Method add = list.getClass().getMethod("add", Object.class);

        add.invoke(list, "str");
        System.out.println(list);
        System.out.println(list.get(1));


        Thread t = new Thread() {
            public void run() {
                TestMe.pong();
            }
        };
        t.start();
//        t.start();
//        t.run();
        System.out.println("ping");


    }

    static void pong() {
        System.out.println("pang");

    }



//    final Condition reachThreeCondition = lock.newCondition();

//    ConcurrentHashMap

}
