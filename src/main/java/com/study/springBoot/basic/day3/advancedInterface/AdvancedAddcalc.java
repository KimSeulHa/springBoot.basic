package com.study.springBoot.basic.day3.advancedInterface;

/*
 * 인스턴스 화 하기 위해서는 component 어노테이션의 주석을 해제하고
 * 더 이상 이 클래스를 사용하지 않지만, 이후에 사용할 수 있거나 히스토리로 남겨 두고 싶은 경우는
 * 주석처리 하여 사용하지 않음을 명시한다.
 * */
//@Component
public class AdvancedAddcalc implements AdvancedCalculator{
    @Override
    public Integer calc(Integer a, Integer b) {
        return a+b;
    }
}
