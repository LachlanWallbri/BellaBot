package org.slf4j.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/* loaded from: classes9.dex */
class AndroidLoggerFactory implements ILoggerFactory {
    static final String ANONYMOUS_TAG = "null";
    static final int TAG_MAX_LENGTH = 23;
    private final ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap();

    @Override // org.slf4j.ILoggerFactory
    public Logger getLogger(String str) {
        String loggerNameToTag = loggerNameToTag(str);
        Logger logger = this.loggerMap.get(loggerNameToTag);
        if (logger != null) {
            return logger;
        }
        AndroidLoggerAdapter androidLoggerAdapter = new AndroidLoggerAdapter(loggerNameToTag);
        Logger putIfAbsent = this.loggerMap.putIfAbsent(loggerNameToTag, androidLoggerAdapter);
        return putIfAbsent == null ? androidLoggerAdapter : putIfAbsent;
    }

    static String loggerNameToTag(String str) {
        if (str == null) {
            return "null";
        }
        int length = str.length();
        if (length <= 23) {
            return str;
        }
        StringBuilder sb = new StringBuilder(26);
        int i = 0;
        int i2 = 0;
        do {
            int indexOf = str.indexOf(46, i);
            if (indexOf == -1) {
                int i3 = length - i;
                if (i2 == 0 || i2 + i3 > 23) {
                    return getSimpleName(str);
                }
                sb.append((CharSequence) str, i, length);
                return sb.toString();
            }
            sb.append(str.charAt(i));
            if (indexOf - i > 1) {
                sb.append('*');
            }
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            i = indexOf + 1;
            i2 = sb.length();
        } while (i2 <= 23);
        return getSimpleName(str);
    }

    private static String getSimpleName(String str) {
        int length = str.length();
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            int i = lastIndexOf + 1;
            if (length - i <= 23) {
                return str.substring(i);
            }
        }
        return '*' + str.substring((length - 23) + 1);
    }
}
