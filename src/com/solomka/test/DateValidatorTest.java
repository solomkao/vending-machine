package com.solomka.test;

import com.solomka.utils.DateValidator;
import org.junit.Assert;
import org.junit.Test;

public class DateValidatorTest {

    @Test
    public void isValidShouldReturnTrue(){
        Assert.assertTrue(DateValidator.isValid("2014-05-05"));
    }

    @Test
    public void isValidShouldReturnFALSE(){
        Assert.assertFalse(DateValidator.isValid("20140505"));
    }
}
