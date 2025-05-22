package com.iflytek.cloud;

import com.iflytek.cloud.msc.util.log.DebugLog;

/* loaded from: classes3.dex */
public class TranscripterResult {
    protected String mResult;

    public TranscripterResult(String str) {
        this.mResult = null;
        try {
            this.mResult = new String(str);
        } catch (Exception e) {
            DebugLog.LogE("TranscripterResult exception:");
            DebugLog.LogE(e);
        }
    }

    public String getResultString() {
        return this.mResult;
    }
}
