package com.aliyun.alink.linksdk.alcs.api;

import com.aliyun.alink.linksdk.alcs.data.ica.ICADeviceInfo;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ICAProbeListener {
    public static final int ALCS_SEND_OK = 0;
    public static final int ALCS_SEND_RSPERROR = 2;
    public static final int ALCS_SEND_TIMEOUT = 1;

    void onComplete(ICADeviceInfo iCADeviceInfo, int i);
}
