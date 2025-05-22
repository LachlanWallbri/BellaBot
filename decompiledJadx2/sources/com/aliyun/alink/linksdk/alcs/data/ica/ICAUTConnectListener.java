package com.aliyun.alink.linksdk.alcs.data.ica;

import com.aliyun.alink.linksdk.alcs.api.ICAConnectListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICAUTConnectListener implements ICAConnectListener {
    private ICAConnectListener mICAConnectListener;
    private ICAUTPointEx mICAUTPointEx;

    public ICAUTConnectListener(ICAConnectListener iCAConnectListener, ICAUTPointEx iCAUTPointEx) {
        this.mICAConnectListener = iCAConnectListener;
        this.mICAUTPointEx = iCAUTPointEx;
        if (iCAUTPointEx != null) {
            iCAUTPointEx.trackStart();
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.api.ICAConnectListener
    public void onLoad(int i, String str, ICADeviceInfo iCADeviceInfo) {
        ICAUTPointEx iCAUTPointEx = this.mICAUTPointEx;
        if (iCAUTPointEx != null) {
            if (i == 200) {
                iCAUTPointEx.trackEnd(0);
            } else {
                iCAUTPointEx.trackEnd(i);
            }
        }
        ICAConnectListener iCAConnectListener = this.mICAConnectListener;
        if (iCAConnectListener != null) {
            iCAConnectListener.onLoad(i, str, iCADeviceInfo);
        }
    }
}
