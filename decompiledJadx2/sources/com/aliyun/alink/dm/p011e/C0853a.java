package com.aliyun.alink.dm.p011e;

import android.text.TextUtils;
import com.aliyun.alink.dm.api.BaseInfo;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.aliyun.alink.dm.api.IDeviceCOTA;
import com.aliyun.alink.dm.model.RequestModel;
import com.aliyun.alink.dm.p008c.C0847a;
import com.aliyun.alink.dm.p010d.C0852a;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttRrpcRegisterRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcHandle;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DeviceCOTAImpl.java */
/* renamed from: com.aliyun.alink.dm.e.a */
/* loaded from: classes.dex */
public class C0853a implements IDeviceCOTA {

    /* renamed from: a */
    private BaseInfo f398a;

    public C0853a(BaseInfo baseInfo) {
        this.f398a = null;
        this.f398a = baseInfo;
    }

    @Override // com.aliyun.alink.dm.api.IDeviceCOTA
    public void COTAGet(RequestModel requestModel, IConnectSendListener iConnectSendListener) {
        if (requestModel == null || requestModel.params == 0 || TextUtils.isEmpty(requestModel.f462id)) {
            if (iConnectSendListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_COTA_GET_PARAMS_ERROR);
                aError.setMsg("COTAGetParamsError");
                iConnectSendListener.onFailure(null, aError);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(requestModel.method)) {
            requestModel.method = "thing.config.get";
        }
        if (TextUtils.isEmpty(requestModel.version)) {
            requestModel.method = "1.0";
        }
        String replace = C0852a.f373b.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, this.f398a.productKey).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, this.f398a.deviceName);
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.topic = replace;
        mqttPublishRequest.payloadObj = requestModel.toString();
        mqttPublishRequest.isRPC = true;
        C0847a.m89a().m92a(mqttPublishRequest, iConnectSendListener);
    }

    @Override // com.aliyun.alink.dm.api.IDeviceCOTA
    public void setCOTAChangeListener(final IConnectRrpcListener iConnectRrpcListener) {
        String replace = C0852a.f372a.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, this.f398a.productKey).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, this.f398a.deviceName);
        final MqttRrpcRegisterRequest mqttRrpcRegisterRequest = new MqttRrpcRegisterRequest();
        mqttRrpcRegisterRequest.topic = replace;
        mqttRrpcRegisterRequest.replyTopic = replace + TmpConstant.URI_TOPIC_REPLY_POST;
        ConnectSDK.getInstance().subscribeRrpc(ConnectSDK.getInstance().getPersistentConnectId(), mqttRrpcRegisterRequest, new IConnectRrpcListener() { // from class: com.aliyun.alink.dm.e.a.1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onSubscribeSuccess(ARequest aRequest) {
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onSubscribeSuccess(aRequest);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onSubscribeFailed(ARequest aRequest, AError aError) {
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onSubscribeFailed(aRequest, aError);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onReceived(ARequest aRequest, IConnectRrpcHandle iConnectRrpcHandle) {
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onReceived(aRequest, null);
                }
                if (iConnectRrpcHandle != null) {
                    try {
                        AResponse aResponse = new AResponse();
                        aResponse.data = "{\"id\":\"123\", \"code\":\"200\",\"data\":{} }";
                        iConnectRrpcHandle.onRrpcResponse(mqttRrpcRegisterRequest.replyTopic, aResponse);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onResponseSuccess(ARequest aRequest) {
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onResponseSuccess(aRequest);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onResponseFailed(ARequest aRequest, AError aError) {
                IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                if (iConnectRrpcListener2 != null) {
                    iConnectRrpcListener2.onResponseFailed(aRequest, aError);
                }
            }
        });
    }
}
