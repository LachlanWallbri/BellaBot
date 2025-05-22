package com.aliyun.alink.linksdk.alcs.data.ica;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICAInitData {
    public ICADeviceInfo deviceInfo;
    public int role;

    public ICAInitData(String str, String str2, int i) {
        this.deviceInfo = new ICADeviceInfo(str, str2);
        this.role = i;
    }
}
