package org.apache.commons.compress.utils;

import java.io.File;

/* loaded from: classes9.dex */
public class FileNameUtils {
    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf < 0 ? "" : name.substring(lastIndexOf + 1);
    }

    public static String getBaseName(String str) {
        if (str == null) {
            return null;
        }
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf < 0 ? name : name.substring(0, lastIndexOf);
    }
}
