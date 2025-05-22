package com.aliyun.alink.linksdk.alcs.lpbs.data;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PalInitData {
    public static final int ALCS_ROLE_BOTH = 3;
    public static final int ALCS_ROLE_CLIENT = 1;
    public static final int ALCS_ROLE_SERVER = 2;
    public PalDeviceInfo deviceInfo;
    public int role;

    public PalInitData(String str, String str2) {
        this.deviceInfo = new PalDeviceInfo(str, str2);
    }

    public PalInitData(int i) {
        this.role = i;
    }
}
