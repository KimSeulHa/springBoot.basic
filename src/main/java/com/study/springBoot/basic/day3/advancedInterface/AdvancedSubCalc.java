package com.study.springBoot.basic.day3.advancedInterface;

import org.springframework.stereotype.Component;

@Component
public class AdvancedSubCalc implements AdvancedCalculator{
    @Override
    public Integer calc(Integer a, Integer b) {
        return a-b;
    }
}
