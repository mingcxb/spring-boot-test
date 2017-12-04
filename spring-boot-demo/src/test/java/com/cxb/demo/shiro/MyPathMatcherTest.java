package com.cxb.demo.shiro;

import org.junit.Assert;
import org.junit.Test;
import org.pac4j.core.profile.CommonProfile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class MyPathMatcherTest {

    @Test
    public void testCss() {
        String str= "/base/css/bootstrap.min.css";
        Pattern pattern = Pattern.compile("^*.*.css$");
        Matcher matcher = pattern.matcher(str);
        boolean matches = matcher.matches();
        Assert.assertTrue(matches);
    }

    @Test
    public void testMinmap() {
        String str= "/base/js/jquery.min.map";
        Pattern pattern = Pattern.compile("^*.*.map$");
        Matcher matcher = pattern.matcher(str);
        boolean matches = matcher.matches();
        Assert.assertTrue(matches);
    }

}