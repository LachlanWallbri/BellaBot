package org.jboss.netty.util;

import org.jboss.netty.util.internal.SystemPropertyUtil;

/* loaded from: classes7.dex */
public final class DebugUtil {
    public static boolean isDebugEnabled() {
        String str;
        try {
            str = SystemPropertyUtil.get("org.jboss.netty.debug");
        } catch (Exception unused) {
            str = null;
        }
        if (str == null) {
            return false;
        }
        String upperCase = str.trim().toUpperCase();
        return (upperCase.startsWith("N") || upperCase.startsWith("F") || upperCase.equals("0")) ? false : true;
    }

    private DebugUtil() {
    }
}
