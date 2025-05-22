package com.aliyun.alink.linksdk.alcs.data.ica;

import com.aliyun.alink.linksdk.alcs.pal.ica.ICADiscoveryListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICADiscoveryDeviceInfo {
    public String addr;
    public ICADeviceInfo deviceInfo;
    public String pal;
    public int port;
    public String tslData;

    public boolean isDataToCloud() {
        return ICADiscoveryListener.PAL_DLCP_RAW.equalsIgnoreCase(this.pal);
    }

    public boolean isDataNeedConvert() {
        return ICADiscoveryListener.PAL_DLCP_RAW.equalsIgnoreCase(this.pal) || ICADiscoveryListener.PAL_LINKKIT_RAW.equalsIgnoreCase(this.pal);
    }

    public boolean isPkDnNeedConvert() {
        return ICADiscoveryListener.PAL_DLCP_RAW.equalsIgnoreCase(this.pal);
    }

    public ICADiscoveryDeviceInfo(ICADeviceInfo iCADeviceInfo, String str, int i, String str2) {
        this.deviceInfo = iCADeviceInfo;
        this.addr = str;
        this.port = i;
        this.pal = str2;
    }
}
