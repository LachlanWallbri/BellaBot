package com.aliyun.alink.linksdk.tmp.connect.entity.cmp;

import android.text.TextUtils;
import android.util.Log;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpSyncRequestHandler;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CpNotifyHandler extends TmpSyncRequestHandler implements IConnectNotifyListener {
    protected static final String TAG = "[Tmp]CpNotifyHandler";
    protected String mConnectedId;
    protected TmpCommonRequest mRequest;

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onConnectStateChange(String str, ConnectState connectState) {
    }

    public CpNotifyHandler(String str, TmpCommonRequest tmpCommonRequest, INotifyHandler iNotifyHandler) {
        super(iNotifyHandler);
        this.mConnectedId = str;
        this.mRequest = tmpCommonRequest;
        setRequest(tmpCommonRequest);
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onNotify(String str, String str2, AMessage aMessage) {
        AResponse aResponse = new AResponse();
        aResponse.data = aMessage.data;
        super.onMessage(this.mRequest, new CpResponse(aResponse));
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public boolean shouldHandle(String str, String str2) {
        Log.d(TAG, "shouldHandle connetId:" + str + " topic:" + str2 + " mConnectedId:" + this.mConnectedId + " requtopic:" + this.mRequest.getTopic());
        return !TextUtils.isEmpty(str) && str.equalsIgnoreCase(this.mConnectedId) && !TextUtils.isEmpty(str2) && str2.equalsIgnoreCase(this.mRequest.getTopic());
    }
}
