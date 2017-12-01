package com.cxb.demo.shiro;

import org.pac4j.core.exception.TechnicalException;
import org.pac4j.core.matching.PathMatcher;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MyPathMatcher extends PathMatcher {

    public MyPathMatcher() {
    }

    public MyPathMatcher(String excludePattern) {
        this.setExcludePatterns(excludePattern);
    }

    public String getExcludePattern() {
        return ((Pattern)super.getExcludedPatterns().iterator().next()).pattern();
    }

    public void setExcludePatterns(String excludePattern) {
        if (!super.getExcludedPatterns().isEmpty()) {
            String msg = "ExcludedPathMatcher does not support excluding multiple paths. Use PathMatcher instead.";
            throw new TechnicalException(msg);
        } else {
            super.setExcludedPatterns(Arrays.stream(excludePattern.split(",")).collect(Collectors.toList()));
        }
    }
}
