package com.iflytek.cloud.msc.util.http;

/* loaded from: classes3.dex */
public interface HttpDownloadListener {
    void onError(int i, HttpDownloadImpl httpDownloadImpl);

    void onFinish(String str, HttpDownloadImpl httpDownloadImpl);

    void onProgress(long j, int i, HttpDownloadImpl httpDownloadImpl);

    void onStart(long j, String str, String str2, String str3, HttpDownloadImpl httpDownloadImpl);
}
