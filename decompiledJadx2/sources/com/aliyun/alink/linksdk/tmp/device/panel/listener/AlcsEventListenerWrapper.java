package com.aliyun.alink.linksdk.tmp.device.panel.listener;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.device.panel.PanelDevice;
import com.aliyun.alink.linksdk.tmp.device.panel.data.EventNotifyPayload;
import com.aliyun.alink.linksdk.tmp.device.panel.data.LocalConnectionChangePayload;
import com.aliyun.alink.linksdk.tmp.listener.IDevStateChangeListener;
import com.aliyun.alink.linksdk.tmp.listener.IEventListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.ResponseUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tmp.utils.TmpEnum;
import com.aliyun.alink.linksdk.tools.ALog;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsEventListenerWrapper implements IDevStateChangeListener, IEventListener {
    protected static final String TAG = "[Tmp]AlcsEventListenerWrapper";
    protected DeviceBasicData mBasicData;
    protected IPanelEventCallback mCallback;
    protected IPanelCallback mListener;
    protected WeakReference<PanelDevice> mPanelDeviceRef;

    public AlcsEventListenerWrapper(PanelDevice panelDevice, DeviceBasicData deviceBasicData, IPanelCallback iPanelCallback, IPanelEventCallback iPanelEventCallback) {
        this.mCallback = iPanelEventCallback;
        this.mBasicData = deviceBasicData;
        this.mListener = iPanelCallback;
        this.mPanelDeviceRef = new WeakReference<>(panelDevice);
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IEventListener
    public void onMessage(String str, String str2, OutputParams outputParams) {
        String json;
        String str3;
        ALog.m479d(TAG, "onMessage identifier:" + str + " topic:" + str2 + " mCallback:" + this.mCallback);
        if (this.mCallback == null || TextUtils.isEmpty(str2)) {
            return;
        }
        if (str2.contains(TmpConstant.EVENT_PROPERTY_URI_PRE)) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TmpConstant.DEVICE_IOTID, (Object) this.mBasicData.getIotId());
            jSONObject.put("productKey", (Object) this.mBasicData.getProductKey());
            jSONObject.put("deviceName", (Object) this.mBasicData.getDeviceName());
            jSONObject.put("items", (Object) outputParams.get("params"));
            json = GsonUtils.toJson(jSONObject);
            str3 = TmpConstant.MQTT_TOPIC_PROPERTIES;
        } else {
            EventNotifyPayload eventNotifyPayload = new EventNotifyPayload();
            EventNotifyPayload.EventParams eventParams = new EventNotifyPayload.EventParams();
            eventParams.iotId = this.mBasicData.getIotId();
            eventParams.productKey = this.mBasicData.getProductKey();
            eventParams.deviceName = this.mBasicData.getDeviceName();
            eventParams.identifier = str;
            eventParams.value = outputParams;
            eventParams.time = System.currentTimeMillis();
            eventNotifyPayload.params = eventParams;
            eventNotifyPayload.method = "thing.events";
            json = GsonUtils.toJson(eventNotifyPayload.params);
            str3 = TmpConstant.MQTT_TOPIC_EVENTS;
        }
        ALog.m479d(TAG, "onMessage  boneTopic:" + str3 + " data:" + json);
        this.mCallback.onNotify(this.mBasicData.getIotId(), str3, json);
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onSuccess(Object obj, OutputParams outputParams) {
        IPanelCallback iPanelCallback = this.mListener;
        if (iPanelCallback != null) {
            iPanelCallback.onComplete(true, ResponseUtils.getSuccessRspJson(new org.json.JSONObject()));
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onFail(Object obj, ErrorInfo errorInfo) {
        IPanelCallback iPanelCallback = this.mListener;
        if (iPanelCallback != null) {
            iPanelCallback.onComplete(false, null);
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevStateChangeListener
    public void onDevStateChange(final TmpEnum.DeviceState deviceState) {
        TmpSdk.mHandler.post(new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.device.panel.listener.AlcsEventListenerWrapper.1
            @Override // java.lang.Runnable
            public void run() {
                LocalConnectionChangePayload localConnectionChangePayload = new LocalConnectionChangePayload();
                localConnectionChangePayload.localConnectionState = deviceState.getValue();
                String json = GsonUtils.toJson(localConnectionChangePayload);
                ALog.m479d(AlcsEventListenerWrapper.TAG, "onDevStateChange deviceState:" + deviceState + " mCallback:" + AlcsEventListenerWrapper.this.mCallback + " data:" + json);
                if (TmpEnum.DeviceState.DISCONNECTED == deviceState) {
                    AlcsEventListenerWrapper.this.stopLocalConnect();
                }
                if (AlcsEventListenerWrapper.this.mCallback != null) {
                    AlcsEventListenerWrapper.this.mCallback.onNotify(AlcsEventListenerWrapper.this.mBasicData.getIotId(), "BoneThingLocalConnectionChange", json);
                }
            }
        });
    }

    public void stopLocalConnect() {
        PanelDevice panelDevice = this.mPanelDeviceRef.get();
        if (panelDevice != null) {
            panelDevice.stopLocalConnect(null);
        }
    }
}
