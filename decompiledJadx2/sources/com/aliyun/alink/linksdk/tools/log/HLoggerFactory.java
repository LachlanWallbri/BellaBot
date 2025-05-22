package com.aliyun.alink.linksdk.tools.log;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class HLoggerFactory {
    private ILogger logger = null;

    public void setLogger(ILogger iLogger) {
        this.logger = iLogger;
    }

    public ILogger getInstance(String str) {
        ILogger iLogger = this.logger;
        return iLogger != null ? iLogger : new LogerImpl(str);
    }
}
