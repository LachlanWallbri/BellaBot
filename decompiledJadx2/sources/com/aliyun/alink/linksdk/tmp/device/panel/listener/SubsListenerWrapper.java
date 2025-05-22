package com.aliyun.alink.linksdk.tmp.device.panel.listener;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SubsListenerWrapper implements IConnectNotifyListener {
    private static final String TAG = "SubsListenerWrapper[Tmp]";
    protected String mIotId;
    protected IPanelEventCallback mSubListener;

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onConnectStateChange(String str, ConnectState connectState) {
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public boolean shouldHandle(String str, String str2) {
        return true;
    }

    public SubsListenerWrapper(String str, IPanelEventCallback iPanelEventCallback) {
        this.mSubListener = iPanelEventCallback;
        this.mIotId = str;
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onNotify(String str, String str2, AMessage aMessage) {
        String obj;
        ALog.m479d(TAG, "onNotify mIotId:" + this.mIotId + " s:" + str + " s1:" + str2);
        if (this.mSubListener != null) {
            String str3 = null;
            try {
                if (aMessage.data instanceof byte[]) {
                    obj = new String((byte[]) aMessage.data, "UTF-8");
                } else {
                    obj = aMessage.data.toString();
                }
                JSONObject jSONObject = JSONObject.parseObject(obj).getJSONObject("params");
                String string = jSONObject.getString(TmpConstant.DEVICE_IOTID);
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(this.mIotId) || this.mIotId.equalsIgnoreCase(string)) {
                    str3 = jSONObject.toString();
                    this.mSubListener.onNotify(this.mIotId, str2, str3);
                }
                ALog.m479d(TAG, "notifyIotId:" + string + " params:" + str3);
            } catch (Exception e) {
                ALog.m480e(TAG, "onNotify e:" + e.toString());
            }
        }
    }
}
