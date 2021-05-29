package com.wq.AOPtest;

public class Tester implements ITester{
    private int cnt;
    @Override
    public void testSoftware() {
        System.out.println("Testing Software!" + cnt++);
    }
}
