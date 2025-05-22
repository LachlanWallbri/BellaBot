package com.amazonaws.logging;

/* loaded from: classes.dex */
public final class Environment {
    private static final String JUNIT_PACKAGE_PREFIX = "org.junit.";

    private Environment() {
    }

    public static boolean isJUnitTest() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (stackTraceElement.getClassName().startsWith(JUNIT_PACKAGE_PREFIX)) {
                return true;
            }
        }
        return false;
    }
}
