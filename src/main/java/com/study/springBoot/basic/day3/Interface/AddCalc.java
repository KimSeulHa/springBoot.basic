package com.study.springBoot.basic.day3.Interface;

public class AddCalc implements Calculator{
    @Override
    public Integer calc(Integer a, Integer b) {
        return a+b;
    }
}
