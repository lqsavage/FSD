package com.iihtibm.lab08.junitpractice;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestCalculation {

    @Before
    public void init(){

    }

    @Test
    public void testAdd() {
        Calculation calculation = new Calculation();
        assertEquals(calculation.add(11, 5), 16);     //11+5=16
        assertEquals(calculation.add(3, 5), 8);       //3+5=8
        assertThat("7 + 8 = 15.", calculation.add(7, 8), equalTo(15));
    }

    @Test
    public void testSub() {
        Calculation calculation = new Calculation();
        assertEquals(calculation.sub(11, 5), 6);     //11-5=6
        assertEquals(calculation.sub(33, 5), 28);       //33-5=-28
        assertThat("17 - 8 = 9.", calculation.sub(17, 8), equalTo(9));
    }

}
