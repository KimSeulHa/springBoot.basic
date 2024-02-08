package com.study.springBoot.basic;

import com.study.springBoot.basic.day3.Interface.AddCalc;
import com.study.springBoot.basic.day3.Interface.Calculator;
import com.study.springBoot.basic.day3.Interface.SubCalc;
import com.study.springBoot.basic.day3.advancedInterface.AdvancedCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Day3Test {
    @Autowired
    AdvancedCalculator advancedCalculator;

    @Test
    void 인터페이스를_사용하는것과_안하는것의_차이(){
        /**
         * 1-1. without interface
         * new 생성자를 통해 클래스 하나하나를 객체화를 해줘야 한다는 불편함이 있다.
         */
        AddCalc addCalc = new AddCalc(); //인스턴스 화
        SubCalc subCalc = new SubCalc();

        System.out.println(addCalc.calc(10,5));
        System.out.println(subCalc.calc(10,5));

        /**
         * 1-2. about basic interface
         * 인터페이스의 메소드를 호출함으로써
         * 코드의 유지보수성의 효율을 높였고 간소화 되었다.
         */
        Calculator calculator = new SubCalc();
        //Calculator calculator = new AddCalc();

        System.out.println(calculator.calc(10,5));
    }

    @Test
    void DI컨테이너를_활용한_인터페이스_사용(){
        /**
         * 2. about advanced interface
         * 해당 인터페이스를 상속받는 클래스에 Component 어노테이션을 부여함으로써
         * 인스턴스화 하여 사용할 수 있다.
         */
        System.out.println(advancedCalculator.calc(5,5));
    }
}
