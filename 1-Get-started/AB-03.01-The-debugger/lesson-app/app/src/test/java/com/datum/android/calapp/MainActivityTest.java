/*
 * Created by mutairibassam on 1/2/21 10:29 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/2/21 10:29 PM
 */

package com.datum.android.calapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void percentage() {

        double actual = 8.0 / 100.0;
        assertEquals(0.08, actual, 0);

        double actual5 = 5.0 / 100.0;
        assertEquals(0.05, actual5, 0);

    }



}