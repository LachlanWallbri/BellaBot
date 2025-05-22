package com.aliyun.alink.linksdk.tmp.connect.entity.cmp;

import com.aliyun.alink.linksdk.cmp.connect.channel.MqttRrpcRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcHandle;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.payload.cloud.ResponsePayload;
import com.aliyun.alink.linksdk.tmp.device.payload.cloud.UpdatePrefixRequestPayload;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class UpdatePrefixHandler implements IConnectRrpcListener {
    protected static final String TAG = "[Tmp]UpdatePrefixHandler";
    protected WeakReference<DeviceImpl> mImplRef;

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
    public void onResponseFailed(ARequest aRequest, AError aError) {
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
    public void onResponseSuccess(ARequest aRequest) {
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
    public void onSubscribeFailed(ARequest aRequest, AError aError) {
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
    public void onSubscribeSuccess(ARequest aRequest) {
    }

    public UpdatePrefixHandler(DeviceImpl deviceImpl) {
        this.mImplRef = new WeakReference<>(deviceImpl);
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
    public void onReceived(ARequest aRequest, IConnectRrpcHandle iConnectRrpcHandle) {
        ALog.m479d(TAG, "onReceive aRequest:" + aRequest + " iConnectRrpcHandle:" + iConnectRrpcHandle);
        if (aRequest != null && (aRequest instanceof MqttRrpcRequest)) {
            UpdatePrefixRequestPayload updatePrefixRequestPayload = (UpdatePrefixRequestPayload) GsonUtils.fromJson(String.valueOf(((MqttRrpcRequest) aRequest).payloadObj), new TypeToken<UpdatePrefixRequestPayload>() { // from class: com.aliyun.alink.linksdk.tmp.connect.entity.cmp.UpdatePrefixHandler.1
            }.getType());
            if (updatePrefixRequestPayload != null && updatePrefixRequestPayload.params != null) {
                DeviceImpl deviceImpl = this.mImplRef.get();
                if (deviceImpl != null) {
                    deviceImpl.updatePrefix(updatePrefixRequestPayload.params.prefix);
                }
                iConnectRrpcHandle.onRrpcResponse(null, new ResponsePayload(updatePrefixRequestPayload.f1031id, 200));
                return;
            }
            iConnectRrpcHandle.onRrpcResponse(null, new ResponsePayload(updatePrefixRequestPayload.f1031id, 300));
            return;
        }
        ALog.m480e(TAG, "onNotify aMessage  error");
    }

    public void unSubTopic() {
        ALog.m479d(TAG, "unSubTopic");
    }
}
