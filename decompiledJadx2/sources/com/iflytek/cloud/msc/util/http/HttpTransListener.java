package com.iflytek.cloud.msc.util.http;

/* loaded from: classes3.dex */
public interface HttpTransListener {
    int onBuffer(byte[] bArr, int i);

    void onCancel();

    void onError(int i);

    void onFinish();

    int onStart(long j, String str, String str2, String str3, String str4, String str5);
}
