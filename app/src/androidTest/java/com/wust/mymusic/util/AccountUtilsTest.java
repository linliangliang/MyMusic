package com.wust.mymusic.util;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class AccountUtilsTest {

    @Test
    public void testCheckLogin() {
        Assert.assertEquals(false, AccountUtils.checkLogin("",""));
        Assert.assertEquals(false, AccountUtils.checkLogin("",""));
        Assert.assertEquals(false, AccountUtils.checkLogin("",""));
        Assert.assertEquals(true, AccountUtils.checkLogin("12345678911","1"));
    }

    @Test
    public void testRestore() {
        Assert.assertEquals(false, AccountUtils.checkLogin("",""));
    }

    @Test
    public void testStore() {
    }
}