/*
 * Created by mutairibassam on 1/2/21 10:29 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 10:29 PM
 */

package com.datum.android.calapp;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void percentage() {

        double actual9 = 9.0 / 100.0;
        assertEquals(0.09, actual9, 0);

        double actual8 = 8.0 / 100.0;
        assertEquals(0.08, actual8, 0);

        double actual7 = 7.0 / 100.0;
        assertEquals(0.07, actual7, 0);

        double actual6 = 6.0 / 100.0;
        assertEquals(0.06, actual6, 0);

    }

    @Test
    public void negative() {
        int current_number = 8;
        int converted = - (current_number * 2) + current_number;

                    // - ( 8 * 2 ) + 8
                    // - ( 16 ) + 8
                    // - 16 + 8
                    // - 8

        assertEquals(-8, converted);

        int current_number1 = 9;
        int converted1 = - (current_number1 * 2) + current_number1;
        assertEquals(-9, converted1);

        int current_number2 = 3;
        int converted2 = - (current_number2 * 2) + current_number2;
        assertEquals(-3, converted2);


    }

    @Test
    public void positive() {
        int current_number = -8;
        int converted = - (current_number * 2) + current_number;

                    // - ( -8 * -2 ) + ( -8 )
                    // - ( -16 ) + ( -8 )
                    // + 16 + - 8
                    // + 8

        assertEquals(8, converted);

        int current_number4 = -4;
        int converted4 = - (current_number4 * 2) + current_number4;
        assertEquals(4, converted4);

        int current_number5 = -5;
        int converted5 = - (current_number5 * 2) + current_number5;
        assertEquals(5, converted5);

        int current_number7 = -7;
        int converted7 = - (current_number7 * 2) + current_number7;
        assertEquals(7, converted7);
    }
}