package com.aliyun.alink.p022h2.p025b;

import android.util.Log;
import com.aliyun.alink.p022h2.api.HLog;
import com.aliyun.alink.p022h2.utils.ILogger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: HLoger.java */
/* renamed from: com.aliyun.alink.h2.b.b */
/* loaded from: classes.dex */
public class C0880b implements ILogger {

    /* renamed from: a */
    private String f527a;

    public C0880b(String str) {
        this.f527a = null;
        this.f527a = str;
    }

    @Override // com.aliyun.alink.p022h2.utils.ILogger
    /* renamed from: d */
    public void mo212d(String str, String str2) {
        m211a(3, str, str2);
    }

    @Override // com.aliyun.alink.p022h2.utils.ILogger
    /* renamed from: i */
    public void mo215i(String str, String str2) {
        m211a(4, str, str2);
    }

    @Override // com.aliyun.alink.p022h2.utils.ILogger
    /* renamed from: w */
    public void mo216w(String str, String str2) {
        m211a(5, str, str2);
    }

    @Override // com.aliyun.alink.p022h2.utils.ILogger
    /* renamed from: e */
    public void mo213e(String str, String str2) {
        m211a(6, str, str2);
    }

    @Override // com.aliyun.alink.p022h2.utils.ILogger
    /* renamed from: e */
    public void mo214e(String str, String str2, Exception exc) {
        if (exc != null) {
            StringBuilder sb = new StringBuilder();
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            sb.append(" EXCEPTION: ");
            sb.append(exc.getMessage());
            m211a(6, str, sb.toString());
            exc.printStackTrace();
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb2.append(str2);
        sb2.append(" EXCEPTION: unknown");
        m211a(6, str, sb2.toString());
    }

    /* renamed from: a */
    private void m211a(int i, String str, String str2) {
        if (i >= HLog.getLogLevel()) {
            Log.println(i, this.f527a + str, str2);
        }
    }
}
