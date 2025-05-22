package com.aliyun.alink.apiclient;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IoTCallback {
    void onFailure(CommonRequest commonRequest, Exception exc);

    void onResponse(CommonRequest commonRequest, CommonResponse commonResponse);
}
