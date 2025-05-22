package com.aliyun.alink.p022h2.utils;

import com.aliyun.alink.p022h2.p025b.C0880b;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class HLoggerFactory {

    /* renamed from: a */
    private ILogger f695a = null;

    public void setLogger(ILogger iLogger) {
        this.f695a = iLogger;
    }

    public ILogger getInstance(String str) {
        ILogger iLogger = this.f695a;
        return iLogger != null ? iLogger : new C0880b(str);
    }
}
