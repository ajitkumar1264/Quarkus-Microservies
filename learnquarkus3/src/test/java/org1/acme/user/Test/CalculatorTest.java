package org1.acme.user.Test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    Calculator newcal=new Calculator();

    @Test
    @DisplayName("Test Addition")
    public void TestCalculator()
    {
        int result=newcal.add(2,3);

        Assertions.assertEquals(5,result);
    }
}
