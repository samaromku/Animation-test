package ru.appngo.animationstest;

import org.junit.Test;

import java.util.HashMap;

public class TestMap {
    @Test
    public void test() {
        HashMap map = new HashMap<String, String>();
        map.put("test", "test1");
        map.get("test");

        for (;;){
            System.out.println("hui");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
