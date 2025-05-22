package com.aliyun.alink.dm.p007b;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.aliyun.alink.dm.model.RequestModel;
import com.aliyun.alink.dm.model.ResponseModel;
import com.aliyun.alink.dm.p008c.C0847a;
import com.aliyun.alink.dm.p010d.C0852a;
import com.aliyun.alink.dm.p012f.C0854a;
import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.log.IDGenerater;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ResetDeviceImpl.java */
/* renamed from: com.aliyun.alink.dm.b.a */
/* loaded from: classes.dex */
public class C0846a {
    /* renamed from: a */
    public void m87a(final String str, final String str2, final IConnectSendListener iConnectSendListener) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (iConnectSendListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_DM_RESET_FAILED);
                aError.setMsg("params error, pk or dn empty.");
                iConnectSendListener.onFailure(null, aError);
                return;
            }
            return;
        }
        RequestModel requestModel = new RequestModel();
        requestModel.f462id = String.valueOf(IDGenerater.generateId());
        requestModel.method = C0852a.f387p;
        requestModel.version = "1.0";
        requestModel.params = "{}";
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.topic = C0852a.f386o.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, str).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, str2);
        mqttPublishRequest.isRPC = true;
        mqttPublishRequest.payloadObj = requestModel.toString();
        C0854a.m116a().m119a("reset-" + str + "-" + str2, "1");
        C0847a.m89a().m92a(mqttPublishRequest, new IConnectSendListener() { // from class: com.aliyun.alink.dm.b.a.1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                if (aResponse == null || aResponse.getData() == null) {
                    AError aError2 = new AError();
                    aError2.setCode(DMErrorCode.ERROR_DM_RESET_FAILED);
                    aError2.setMsg("reset reponse null.");
                    C0859a.m131a("ResetDeviceImpl", "reset onResponse fail, code=1101204, msg=" + aError2.getMsg());
                    onFailure(aRequest, aError2);
                    return;
                }
                try {
                    ResponseModel responseModel = (ResponseModel) JSONObject.parseObject(aResponse.getData().toString(), new TypeReference<ResponseModel<String>>() { // from class: com.aliyun.alink.dm.b.a.1.1
                    }.getType(), new Feature[0]);
                    if (responseModel != null && ErrorCode.UNKNOWN_SUCCESS_CODE.equals(responseModel.code)) {
                        C0854a.m116a().m119a("reset-" + str + "-" + str2, "0");
                        if (iConnectSendListener != null) {
                            iConnectSendListener.onResponse(aRequest, aResponse);
                            return;
                        }
                        return;
                    }
                } catch (Exception unused) {
                }
                AError aError3 = new AError();
                aError3.setCode(DMErrorCode.ERROR_DM_RESET_FAILED);
                aError3.setMsg("reset reponse data invalid." + aResponse.getData());
                C0859a.m131a("ResetDeviceImpl", "reset onResponse fail, code=1101204, msg=" + aError3.getMsg());
                onFailure(aRequest, aError3);
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError2) {
                C0854a.m116a().m119a("reset-" + str + "-" + str2, "1");
                IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                if (iConnectSendListener2 != null) {
                    iConnectSendListener2.onFailure(aRequest, aError2);
                }
            }
        });
    }
}
