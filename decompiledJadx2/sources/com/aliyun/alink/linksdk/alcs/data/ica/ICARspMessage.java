package com.aliyun.alink.linksdk.alcs.data.ica;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICARspMessage {
    public static final int IS_NOTIFY = 1;
    public static final int NOT_NOTIFY = 0;
    public int cbContext;
    public int code;
    public ICADeviceInfo deviceInfo;
    public byte[] payload;

    public ICARspMessage(int i) {
        this.code = i;
    }

    public ICARspMessage() {
    }
}
