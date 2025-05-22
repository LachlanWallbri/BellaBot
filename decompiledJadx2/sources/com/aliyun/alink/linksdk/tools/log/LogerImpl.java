package com.aliyun.alink.linksdk.tools.log;

import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LogerImpl implements ILogger {
    private String preTag;

    public LogerImpl(String str) {
        this.preTag = null;
        this.preTag = str;
    }

    @Override // com.aliyun.alink.linksdk.tools.log.ILogger
    /* renamed from: d */
    public void mo485d(String str, String str2) {
        ALog.m479d(this.preTag + str, str2);
    }

    @Override // com.aliyun.alink.linksdk.tools.log.ILogger
    /* renamed from: i */
    public void mo488i(String str, String str2) {
        ALog.m483i(this.preTag + str, str2);
    }

    @Override // com.aliyun.alink.linksdk.tools.log.ILogger
    /* renamed from: w */
    public void mo489w(String str, String str2) {
        ALog.m484w(this.preTag + str, str2);
    }

    @Override // com.aliyun.alink.linksdk.tools.log.ILogger
    /* renamed from: e */
    public void mo486e(String str, String str2) {
        ALog.m480e(this.preTag + str, str2);
    }

    @Override // com.aliyun.alink.linksdk.tools.log.ILogger
    /* renamed from: e */
    public void mo487e(String str, String str2, Exception exc) {
        ALog.m481e(this.preTag + str, str2, exc);
    }
}
