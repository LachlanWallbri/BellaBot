package com.aliyun.alink.linksdk.tools;

import android.util.Log;
import com.aliyun.alink.linksdk.tools.log.ILogDispatcher;
import com.aliyun.alink.linksdk.tools.log.ILogStrategy;
import com.aliyun.alink.linksdk.tools.log.ILogUpload;
import com.aliyun.alink.linksdk.tools.log.LogHelper;
import com.aliyun.alink.linksdk.tools.log.LogStrategyFactory;
import com.aliyun.alink.linksdk.tools.log.LogStrategyType;
import com.aliyun.alink.linksdk.tools.log.LogcatLogStrategy;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ALog {
    public static final byte LEVEL_DEBUG = 1;
    public static final byte LEVEL_ERROR = 4;
    public static final byte LEVEL_INFO = 2;
    public static final byte LEVEL_WARNING = 3;
    private static final String LOG_PREFIX = "linksdk_";
    private static final int LOG_PREFIX_LENGTH = 8;
    private static final int MAX_LENGTH = 900;
    private static final int MAX_LOG_TAG_LENGTH = 36;
    private static final String TAG = "ALog";
    private static byte level = 3;
    private static byte uploadLevel = 4;
    private static ILogStrategy logStrategy = new LogcatLogStrategy();
    private static ILogUpload logUpload = null;
    private static LogStrategyType logStrategyType = null;
    private static ILogDispatcher logDispatcher = null;

    public static void setLogDispatcher(ILogDispatcher iLogDispatcher) {
        logDispatcher = iLogDispatcher;
    }

    public static byte getUploadLevel() {
        return uploadLevel;
    }

    public static void setUploadLevel(byte b) {
        Log.d(TAG, "setUploadLevel uploadLevel:" + ((int) b));
        if (b < 2) {
            b = 2;
        }
        uploadLevel = b;
    }

    public static LogStrategyType getLogStrategyType() {
        return logStrategyType;
    }

    public static void setLogStrategyType(LogStrategyType logStrategyType2) {
        if (logStrategyType2 == null) {
            throw new RuntimeException("LogStrategyType cannot be null.");
        }
        Log.d(TAG, "setLogStrategyType type:" + logStrategyType2);
        logStrategyType = logStrategyType2;
        logStrategy = LogStrategyFactory.getInstance().createStrategy(logStrategyType);
    }

    public static void setLogUpload(ILogUpload iLogUpload) {
        Log.d(TAG, "setLogUpload upload:" + iLogUpload);
        logUpload = iLogUpload;
    }

    public static ILogUpload getLogUpload() {
        return logUpload;
    }

    public static String makeLogTag(String str) {
        if (str.length() > 36 - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, (36 - LOG_PREFIX_LENGTH) - 1);
        }
        return LOG_PREFIX + str;
    }

    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void setLevel(byte b) {
        level = b;
    }

    public static byte getLevel() {
        return level;
    }

    private static void log(int i, String str, String str2) {
        ILogStrategy iLogStrategy;
        ILogDispatcher iLogDispatcher = logDispatcher;
        if (iLogDispatcher != null) {
            iLogDispatcher.log(i, str, str2);
        }
        if (level + 2 > i) {
            return;
        }
        if (6 < i) {
            i = 6;
        }
        if (3 > i) {
            i = 3;
        }
        if (str == null) {
            LogHelper.print(i, str, str2);
            return;
        }
        if (str.startsWith("AWSS-")) {
            String str3 = null;
            try {
                String targetStackTraceMSg = LogHelper.targetStackTraceMSg();
                if (str.length() + targetStackTraceMSg.length() > 36) {
                    str3 = str.substring(0, 36 - targetStackTraceMSg.length()) + targetStackTraceMSg;
                } else {
                    str3 = str + targetStackTraceMSg;
                }
                if (i >= uploadLevel + 2 && logStrategy != null && logStrategy.isSupport() && str != null && str.startsWith("AWSS-")) {
                    logStrategy.log(i, str3, str2);
                    if (logStrategy instanceof LogcatLogStrategy) {
                        return;
                    }
                    LogHelper.print(i, str, str2);
                    return;
                }
            } catch (Exception unused) {
                LogHelper.print(5, str, str2);
            }
            LogHelper.print(i, str3, str2);
            return;
        }
        if (i >= uploadLevel && (iLogStrategy = logStrategy) != null && iLogStrategy.isSupport() && str != null && str.toLowerCase().contains("performancetag".toLowerCase())) {
            logStrategy.log(i, "android-" + str, str2);
            LogHelper.print(i, str, str2);
            return;
        }
        LogHelper.print(i, str, str2);
    }

    public static void llog(byte b, String str, String str2) {
        if (level + 2 <= b && str2 != null) {
            int length = str2.length();
            int i = 0;
            while (i < length - 900) {
                int i2 = i + 900;
                log(b, str, str2.substring(i, i2));
                i = i2;
            }
            log(b, str, str2.substring(i));
        }
    }

    /* renamed from: d */
    public static void m479d(String str, String str2) {
        log(3, str, str2);
    }

    /* renamed from: i */
    public static void m483i(String str, String str2) {
        log(4, str, str2);
    }

    /* renamed from: w */
    public static void m484w(String str, String str2) {
        log(5, str, str2);
    }

    /* renamed from: e */
    public static void m480e(String str, String str2) {
        log(6, str, str2);
    }

    /* renamed from: e */
    public static void m482e(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(" ERROR: ");
        sb.append(str3);
        log(6, str, sb.toString());
    }

    /* renamed from: e */
    public static void m481e(String str, String str2, Exception exc) {
        if (exc != null) {
            StringBuilder sb = new StringBuilder();
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            sb.append(" EXCEPTION: ");
            sb.append(exc.getMessage());
            log(6, str, sb.toString());
            exc.printStackTrace();
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb2.append(str2);
        sb2.append(" EXCEPTION: unknown");
        log(6, str, sb2.toString());
    }
}
