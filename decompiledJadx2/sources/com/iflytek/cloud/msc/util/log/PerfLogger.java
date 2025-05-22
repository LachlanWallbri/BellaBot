package com.iflytek.cloud.msc.util.log;

import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import org.apache.commons.io.IOUtils;

/* loaded from: classes3.dex */
public class PerfLogger {
    public static final String CREATE_DOWNLOAD = "CreateDownload";
    public static final String DOWNLOAD_ONFINISH = "DownloadonFinish";
    public static final String DOWNLOAD_ONSTART = "DownloadonStart";
    public static final String GET_RESULT = "GetNotifyResult";
    public static final String LAST_DATA_FLAG = "LastDataFlag";
    public static final String MSC_SESSION_BIGNE = "MSCSessionBegin";
    public static final String REQUEST_RESULT = "RequestResult";
    public static final String SDK_SESSION_BIGNE = "SDKSessionBegin";
    public static final String SENT_REQUEST = "SendRequest";
    public static final String SESSION_BEGIN_END = "SessionBeginEnd";
    public static final String SESSION_END_BEGIN = "SessionEndBegin";
    public static final String SESSION_END_END = "SessionEndEnd";
    public static final String TYPE_MSC = "msc";
    public static final String TYPE_PRE = "pre";
    public static LinkedHashMap<String, String> mTimes = new LinkedHashMap<>();
    private static String SYMBOL_EQUAL = "=";
    private static String SYMBOL_COLON = ":";
    private static String SYMBOL_SEMICOLON = VoiceWakeuperAidl.PARAMS_SEPARATE;
    private static String SYMBOL_DIVISION = "=========================================================\r\n";
    private static boolean isLogSaved = false;

    public static void setLogSaved(boolean z) {
        isLogSaved = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00de A[Catch: Exception -> 0x00e2, all -> 0x00e9, TRY_LEAVE, TryCatch #9 {Exception -> 0x00e2, blocks: (B:59:0x00da, B:63:0x00de), top: B:56:0x00d6, outer: #6 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized void saveLogFile(String str) {
        FileWriter fileWriter;
        Throwable th;
        BufferedWriter bufferedWriter;
        Exception e;
        synchronized (PerfLogger.class) {
            if (!isLogSaved || mTimes.size() < 1) {
                return;
            }
            try {
                fileWriter = new FileWriter(str, true);
            } catch (Exception e2) {
                fileWriter = null;
                e = e2;
                bufferedWriter = null;
            } catch (Throwable th2) {
                fileWriter = null;
                th = th2;
                bufferedWriter = null;
            }
            try {
                bufferedWriter = new BufferedWriter(fileWriter);
            } catch (Exception e3) {
                e = e3;
                bufferedWriter = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = null;
                try {
                    if (bufferedWriter == null) {
                    }
                } catch (Exception e4) {
                    DebugLog.LogE(e4);
                }
                throw th;
            }
            try {
                try {
                    bufferedWriter.write(SYMBOL_DIVISION);
                    for (String str2 : mTimes.keySet()) {
                        String str3 = mTimes.get(str2);
                        if (str3 == null) {
                            str3 = "null";
                        }
                        if (str3.contains(SYMBOL_SEMICOLON)) {
                            String str4 = "[" + str3 + "]";
                        } else if (str3.contains(SYMBOL_EQUAL)) {
                            bufferedWriter.write(str2 + str3);
                        } else {
                            bufferedWriter.write(str2 + SYMBOL_EQUAL + str3);
                        }
                        bufferedWriter.write(IOUtils.LINE_SEPARATOR_WINDOWS);
                    }
                    bufferedWriter.write(SYMBOL_DIVISION);
                    bufferedWriter.close();
                    fileWriter.close();
                    try {
                        bufferedWriter.close();
                    } catch (Exception e5) {
                        e = e5;
                        DebugLog.LogE(e);
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (bufferedWriter == null) {
                        bufferedWriter.close();
                    } else {
                        if (fileWriter != null) {
                            fileWriter.close();
                        }
                        throw th;
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                DebugLog.LogE(e);
                try {
                } catch (Exception e7) {
                    e = e7;
                    DebugLog.LogE(e);
                }
                if (bufferedWriter == null) {
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                }
                bufferedWriter.close();
            }
        }
    }

    public static synchronized void clear() {
        synchronized (PerfLogger.class) {
            if (isLogSaved && mTimes != null) {
                mTimes.clear();
            }
        }
    }

    public static synchronized void appendInfo(String str, String str2) {
        String str3;
        synchronized (PerfLogger.class) {
            if (isLogSaved) {
                DebugLog.LogS("appendInfo:" + str + "," + str2);
                if (TextUtils.isEmpty(str2)) {
                    str3 = "";
                } else {
                    str3 = str2 + SYMBOL_COLON;
                }
                mTimes.put(str, str3 + System.currentTimeMillis());
            }
        }
    }

    public static synchronized void appendInfoByValue(String str, String str2) {
        synchronized (PerfLogger.class) {
            if (isLogSaved) {
                if (mTimes.containsKey(str) && !TextUtils.isEmpty(mTimes.get(str))) {
                    mTimes.put(str, mTimes.get(str) + SYMBOL_SEMICOLON + str2);
                }
                mTimes.put(str, str2);
            }
        }
    }
}
