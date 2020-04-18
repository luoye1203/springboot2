package com.lht.myquartz;

public class Testme2 {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Testme2 test = new Testme2();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }

//        while(Thread.activeCount()>1) {
//            System.out.println(Thread.activeCount());
//            Thread.yield();
//        } //保证前面的线程都执行完
        System.out.println(test.inc);
    }
}
