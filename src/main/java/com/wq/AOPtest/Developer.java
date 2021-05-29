package com.wq.AOPtest;

public class Developer implements IDeveloper{
    private int cnt;
    @Override
    public void developSoftware() {
        System.out.println("I am working!" + cnt++);
    }
}
