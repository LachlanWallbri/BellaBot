package com.aliyun.alink.dm.p016j;

import android.text.TextUtils;
import com.aliyun.alink.dm.api.BaseInfo;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.aliyun.alink.dm.api.IDeviceLabel;
import com.aliyun.alink.dm.model.RequestModel;
import com.aliyun.alink.dm.p008c.C0847a;
import com.aliyun.alink.dm.p010d.C0852a;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DeviceLabelImpl.java */
/* renamed from: com.aliyun.alink.dm.j.a */
/* loaded from: classes.dex */
public class C0858a implements IDeviceLabel {

    /* renamed from: a */
    private BaseInfo f408a;

    public C0858a(BaseInfo baseInfo) {
        this.f408a = null;
        this.f408a = baseInfo;
    }

    @Override // com.aliyun.alink.dm.api.IDeviceLabel
    public void labelUpdate(RequestModel requestModel, IConnectSendListener iConnectSendListener) {
        if (requestModel == null || requestModel.params == 0 || TextUtils.isEmpty(requestModel.f462id)) {
            if (iConnectSendListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_GATEWAY_LABEL_PARAMS_INVALID);
                aError.setMsg("labelUpdateParamsError");
                iConnectSendListener.onFailure(null, aError);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(requestModel.method)) {
            requestModel.method = "thing.deviceinfo.update";
        }
        if (TextUtils.isEmpty(requestModel.version)) {
            requestModel.version = "1.0";
        }
        String replace = C0852a.f374c.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, this.f408a.productKey).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, this.f408a.deviceName);
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.topic = replace;
        mqttPublishRequest.payloadObj = requestModel.toString();
        mqttPublishRequest.isRPC = true;
        C0847a.m89a().m92a(mqttPublishRequest, iConnectSendListener);
    }

    @Override // com.aliyun.alink.dm.api.IDeviceLabel
    public void labelDelete(RequestModel requestModel, IConnectSendListener iConnectSendListener) {
        if (requestModel == null || requestModel.params == 0 || TextUtils.isEmpty(requestModel.f462id)) {
            if (iConnectSendListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_GATEWAY_LABEL_PARAMS_INVALID);
                aError.setMsg("labelDeleteParamsError");
                iConnectSendListener.onFailure(null, aError);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(requestModel.method)) {
            requestModel.method = "thing.deviceinfo.delete";
        }
        if (TextUtils.isEmpty(requestModel.version)) {
            requestModel.version = "1.0";
        }
        String replace = C0852a.f375d.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, this.f408a.productKey).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, this.f408a.deviceName);
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.topic = replace;
        mqttPublishRequest.payloadObj = requestModel.toString();
        mqttPublishRequest.isRPC = true;
        C0847a.m89a().m92a(mqttPublishRequest, iConnectSendListener);
    }
}
