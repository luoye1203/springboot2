package com.lht;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wang Chong
 * @Date: 2019/9/22 9:37
 * @Version: V1.0
 */
public class HeapOutMemoryTest {
    static class ChongGuo {

    }
    public static void main(String[] args) {
        List<ChongGuo> chongGuos = new ArrayList<>();
        while (true) {
            chongGuos.add(new ChongGuo());
        }
    }
}