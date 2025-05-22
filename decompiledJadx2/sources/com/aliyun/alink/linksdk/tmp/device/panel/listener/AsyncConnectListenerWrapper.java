package com.aliyun.alink.linksdk.tmp.device.panel.listener;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CmpNotifyManager;
import com.aliyun.alink.linksdk.tmp.device.payload.CommonResponsePayload;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import java.lang.ref.WeakReference;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AsyncConnectListenerWrapper extends ConnectListenerWrapper implements IConnectNotifyListener {
    private static final String TAG = "[Tmp]AsyncConnectListenerWrapper";
    public static final int TIME_OUT = 15000;
    private String mMessageId;
    private TimeOutRunnable mTimeoutRunnable;

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onConnectStateChange(String str, ConnectState connectState) {
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public boolean shouldHandle(String str, String str2) {
        return true;
    }

    public AsyncConnectListenerWrapper(IPanelCallback iPanelCallback) {
        super(iPanelCallback);
        this.mTimeoutRunnable = new TimeOutRunnable(this);
        TmpSdk.mHandler.postDelayed(this.mTimeoutRunnable, 15000L);
        CmpNotifyManager.getInstance().addHandler(hashCode(), ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_SERVICE_REPLY, this);
    }

    public void removeHandler() {
        CmpNotifyManager.getInstance().removeHandler(hashCode(), ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_SERVICE_REPLY);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.ConnectListenerWrapper, com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
    public void onResponse(ARequest aRequest, AResponse aResponse) {
        if (aResponse == null || aResponse.data == null) {
            ALog.m480e(TAG, "onResponse error");
            return;
        }
        try {
            this.mMessageId = JSON.parseObject(aResponse.data.toString()).getJSONObject("data").getString("messageId");
        } catch (Exception e) {
            ALog.m480e(TAG, "onResponse error : " + e.toString());
        }
        ALog.m479d(TAG, "onResponse ignore callback wait notify mMessageId:" + this.mMessageId + " data:" + aResponse.data);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.panel.listener.ConnectListenerWrapper, com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
    public void onFailure(ARequest aRequest, AError aError) {
        removeHandler();
        TmpSdk.mHandler.removeCallbacks(this.mTimeoutRunnable);
        super.onFailure(aRequest, aError);
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onNotify(String str, String str2, AMessage aMessage) {
        String obj;
        try {
            if (aMessage.data instanceof byte[]) {
                obj = new String((byte[]) aMessage.data, "UTF-8");
            } else {
                obj = aMessage.data.toString();
            }
            ALog.m479d(TAG, "onNotify s:" + str + " s1:" + str2 + " mMessageId:" + this.mMessageId + " payloadStr:" + obj);
            JSONObject jSONObject = JSONObject.parseObject(obj).getJSONObject("params");
            String string = jSONObject.getString("messageId");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(this.mMessageId) || !this.mMessageId.equalsIgnoreCase(string)) {
                return;
            }
            removeHandler();
            TmpSdk.mHandler.removeCallbacks(this.mTimeoutRunnable);
            JSONObject jSONObject2 = jSONObject.getJSONObject("output").getJSONObject(ES6Iterator.VALUE_PROPERTY);
            CommonResponsePayload commonResponsePayload = new CommonResponsePayload();
            commonResponsePayload.setCode(200);
            commonResponsePayload.setData(jSONObject2);
            AResponse aResponse = new AResponse();
            aResponse.data = JSON.toJSONString(commonResponsePayload);
            super.onResponse(null, aResponse);
        } catch (Exception e) {
            ALog.m480e(TAG, "onNotify e:" + e.toString());
        }
    }

    public void onTimeOut() {
        ALog.m480e(TAG, "onTimeOut");
        AError aError = new AError();
        aError.setCode(300);
        aError.setMsg("timeout");
        onFailure(null, aError);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class TimeOutRunnable implements Runnable {
        private WeakReference<AsyncConnectListenerWrapper> mListener;

        public TimeOutRunnable(AsyncConnectListenerWrapper asyncConnectListenerWrapper) {
            this.mListener = new WeakReference<>(asyncConnectListenerWrapper);
        }

        @Override // java.lang.Runnable
        public void run() {
            AsyncConnectListenerWrapper asyncConnectListenerWrapper = this.mListener.get();
            if (asyncConnectListenerWrapper != null) {
                asyncConnectListenerWrapper.onTimeOut();
            }
        }
    }
}
