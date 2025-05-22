package com.aliyun.alink.linksdk.alcs.api.client;

import com.aliyun.alink.linksdk.alcs.coap.IAuthHandler;
import com.aliyun.alink.linksdk.tools.ALog;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ClientAuthHandler implements IAuthHandler {
    protected static final String TAG = "ClientAuthHandler";
    protected WeakReference<AlcsClient> mClientRef;
    protected IDeviceHandler mHandler;

    public ClientAuthHandler(AlcsClient alcsClient, IDeviceHandler iDeviceHandler) {
        this.mClientRef = new WeakReference<>(alcsClient);
        this.mHandler = iDeviceHandler;
    }

    @Override // com.aliyun.alink.linksdk.alcs.coap.IAuthHandler
    public void onAuthResult(String str, int i, int i2) {
        AlcsClient alcsClient = this.mClientRef.get();
        if (alcsClient == null) {
            ALog.m480e(TAG, "onAuthResult error client null");
        } else {
            alcsClient.onAuth(i2 == 200, i2, this.mHandler);
        }
    }
}
