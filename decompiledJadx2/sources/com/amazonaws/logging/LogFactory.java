package com.amazonaws.logging;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class LogFactory {
    private static final String TAG = LogFactory.class.getSimpleName();
    private static final Map<String, Log> logMap = new HashMap();
    private static Level globalLogLevel = null;

    public static synchronized Log getLog(Class<?> cls) {
        Log log;
        synchronized (LogFactory.class) {
            log = getLog(getTruncatedLogTag(cls.getSimpleName()));
        }
        return log;
    }

    public static synchronized Log getLog(String str) {
        Log androidLog;
        synchronized (LogFactory.class) {
            String truncatedLogTag = getTruncatedLogTag(str);
            Log log = logMap.get(truncatedLogTag);
            if (log != null) {
                return log;
            }
            if (Environment.isJUnitTest()) {
                androidLog = new ConsoleLog(truncatedLogTag);
            } else {
                androidLog = new AndroidLog(truncatedLogTag);
            }
            logMap.put(truncatedLogTag, androidLog);
            return androidLog;
        }
    }

    public static void setLevel(Level level) {
        globalLogLevel = level;
    }

    public static Level getLevel() {
        return globalLogLevel;
    }

    private static String getTruncatedLogTag(String str) {
        if (str.length() <= 23) {
            return str;
        }
        getLog(TAG).warn("Truncating log tag length as it exceed 23, the limit imposed by Android on certain API Levels");
        return str.substring(0, 23);
    }

    /* loaded from: classes.dex */
    public enum Level {
        ALL(Integer.MIN_VALUE),
        TRACE(0),
        DEBUG(1),
        INFO(2),
        WARN(3),
        ERROR(4),
        OFF(Integer.MAX_VALUE);

        private final int value;

        public int getValue() {
            return this.value;
        }

        Level(int i) {
            this.value = i;
        }
    }
}
