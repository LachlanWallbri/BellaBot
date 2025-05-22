package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import com.aliyun.alink.linksdk.alcs.data.ica.ICADeviceInfo;
import com.aliyun.alink.linksdk.alcs.data.ica.ICADiscoveryDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.api.AlcsPalConst;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.ica.ICAPalDiscoveryDevInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener;
import com.aliyun.alink.linksdk.alcs.pal.ica.ICADiscoveryListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICADisHandlerWrapper.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.i */
/* loaded from: classes.dex */
public class C0958i implements ICADiscoveryListener {

    /* renamed from: a */
    private static final String f850a = "[AlcsLPBS]ICADisHandlerWrapper";

    /* renamed from: b */
    private PalDiscoveryListener f851b;

    /* renamed from: c */
    private C0952c f852c;

    public C0958i(C0952c c0952c, PalDiscoveryListener palDiscoveryListener) {
        this.f851b = palDiscoveryListener;
        this.f852c = c0952c;
    }

    @Override // com.aliyun.alink.linksdk.alcs.pal.ica.ICADiscoveryListener
    public void onDiscoveryDevice(String str, int i, String str2, ICADeviceInfo iCADeviceInfo) {
        if (iCADeviceInfo == null) {
            ALog.m480e(f850a, "onDiscoveryDevice deviceInfo null");
            return;
        }
        ALog.m479d(f850a, "onDiscoveryDevice addr:" + str + " port:" + i + " deviceInfo:" + iCADeviceInfo.toString() + " pal:" + str2);
        ICADiscoveryDeviceInfo iCADiscoveryDeviceInfo = new ICADiscoveryDeviceInfo(iCADeviceInfo, str, i, str2);
        this.f852c.m382a(iCADeviceInfo.getDevId(), iCADiscoveryDeviceInfo);
        ICAPalDiscoveryDevInfo iCAPalDiscoveryDevInfo = new ICAPalDiscoveryDevInfo(new PalDeviceInfo(iCADiscoveryDeviceInfo.deviceInfo.productKey, iCADiscoveryDeviceInfo.deviceInfo.deviceName), iCADiscoveryDeviceInfo.isPkDnNeedConvert());
        iCAPalDiscoveryDevInfo.modelType = m384b(iCADiscoveryDeviceInfo);
        iCAPalDiscoveryDevInfo.dataFormat = m383a(iCADiscoveryDeviceInfo);
        iCAPalDiscoveryDevInfo.isDataToCloud = iCADiscoveryDeviceInfo.isDataToCloud();
        iCAPalDiscoveryDevInfo.tslData = iCADiscoveryDeviceInfo.tslData;
        PalDiscoveryListener palDiscoveryListener = this.f851b;
        if (palDiscoveryListener != null) {
            palDiscoveryListener.onDiscoveryDevice(iCAPalDiscoveryDevInfo);
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.pal.ica.ICADiscoveryListener
    public void onDiscoveryFinish() {
        ALog.m479d(f850a, "onDiscoveryFinish mListener:" + this.f851b);
        PalDiscoveryListener palDiscoveryListener = this.f851b;
        if (palDiscoveryListener != null) {
            palDiscoveryListener.onDiscoveryFinish();
        }
    }

    /* renamed from: a */
    public String m383a(ICADiscoveryDeviceInfo iCADiscoveryDeviceInfo) {
        return iCADiscoveryDeviceInfo.isDataNeedConvert() ? AlcsPalConst.DATA_FORMAT_CUNSTOM : "ALINK_FORMAT";
    }

    /* renamed from: b */
    public String m384b(ICADiscoveryDeviceInfo iCADiscoveryDeviceInfo) {
        return (ICADiscoveryListener.PAL_LINKKIT_RAW.equalsIgnoreCase(iCADiscoveryDeviceInfo.pal) || ICADiscoveryListener.PAL_LINKKIT_ICA.equalsIgnoreCase(iCADiscoveryDeviceInfo.pal)) ? "1" : "3";
    }
}
