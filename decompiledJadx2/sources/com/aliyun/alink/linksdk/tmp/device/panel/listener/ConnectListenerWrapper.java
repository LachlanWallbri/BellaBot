package com.aliyun.alink.linksdk.tmp.device.panel.listener;

import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.service.UTPoint;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ConnectListenerWrapper implements IConnectSendListener {
    protected static final String TAG = "[Tmp]ConnectListenerWrapper";
    protected IPanelCallback mCallback;
    protected UTPoint mUTPoint;

    public ConnectListenerWrapper(UTPoint uTPoint, IPanelCallback iPanelCallback) {
        this.mCallback = iPanelCallback;
        this.mUTPoint = uTPoint;
        if (uTPoint != null) {
            uTPoint.trackStart();
        }
    }

    public ConnectListenerWrapper(IPanelCallback iPanelCallback) {
        this(null, iPanelCallback);
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
    public void onResponse(ARequest aRequest, AResponse aResponse) {
        ALog.m479d(TAG, "onResponse aResponse");
        UTPoint uTPoint = this.mUTPoint;
        if (uTPoint != null) {
            uTPoint.trackEnd();
        }
        IPanelCallback iPanelCallback = this.mCallback;
        if (iPanelCallback != null) {
            iPanelCallback.onComplete(true, aResponse.data);
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
    public void onFailure(ARequest aRequest, AError aError) {
        ALog.m479d(TAG, "onFailure aError");
        UTPoint uTPoint = this.mUTPoint;
        if (uTPoint != null) {
            uTPoint.trackEnd();
        }
        IPanelCallback iPanelCallback = this.mCallback;
        if (iPanelCallback != null) {
            iPanelCallback.onComplete(false, aError);
        }
    }
}
