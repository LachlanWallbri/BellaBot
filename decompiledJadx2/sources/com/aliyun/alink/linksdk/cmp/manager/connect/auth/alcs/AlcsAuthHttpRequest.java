package com.aliyun.alink.linksdk.cmp.manager.connect.auth.alcs;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.cmp.connect.apigw.ApiGatewayRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.core.base.AConnect;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.CmpError;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.manager.connect.ConnectManager;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsAuthHttpRequest {
    private static final String PATH_CLIENT = "/alcs/device/accessInfo/get";
    private static final String TAG = "AlcsAuthHttpRequest";
    private static final String TOPIC_SERVER = "/thing/lan/prefix/get";

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface IAlcsAuthCallback {
        void onFailed(AError aError);

        void onSuccess(Object obj);
    }

    public static void requestClientInfo(String str, final IAlcsAuthCallback iAlcsAuthCallback) {
        ALog.m479d(TAG, "requestClientInfo");
        if (iAlcsAuthCallback == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            if (iAlcsAuthCallback != null) {
                iAlcsAuthCallback.onFailed(CmpError.PARAMS_ERROR());
                return;
            }
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            AConnect apiGatewayConnect = ConnectManager.getInstance().getApiGatewayConnect();
            ApiGatewayRequest build = ApiGatewayRequest.build(PATH_CLIENT, "1.0.0", null);
            build.addParams("iotIdList", arrayList);
            apiGatewayConnect.send(build, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.cmp.manager.connect.auth.alcs.AlcsAuthHttpRequest.1
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("requestClientInfo, onResponse(),rsp = ");
                    sb.append((aResponse == null || aResponse.data == null) ? "" : (String) aResponse.data);
                    ALog.m479d(AlcsAuthHttpRequest.TAG, sb.toString());
                    try {
                        JSONObject parseObject = JSONObject.parseObject((String) aResponse.data);
                        String string = parseObject.getString(NotificationCompat.CATEGORY_MESSAGE);
                        if (parseObject.getIntValue("code") != 200) {
                            AlcsAuthHttpRequest.clientFailCallback(string, IAlcsAuthCallback.this);
                        } else {
                            AlcsClientAuthValue alcsClientAuthValue = (AlcsClientAuthValue) parseObject.getJSONObject("data").getJSONArray("alcsDeviceDTOList").getObject(0, AlcsClientAuthValue.class);
                            if (alcsClientAuthValue != null && alcsClientAuthValue.checkValid()) {
                                IAlcsAuthCallback.this.onSuccess(alcsClientAuthValue);
                            }
                        }
                    } catch (Exception e) {
                        ALog.m480e(AlcsAuthHttpRequest.TAG, "requestClientInfo,onResponse(), error = " + e.toString());
                        AlcsAuthHttpRequest.clientFailCallback(e.toString(), IAlcsAuthCallback.this);
                    }
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    ALog.m480e(AlcsAuthHttpRequest.TAG, "requestClientInfo,onErrorResponse(), error = " + aError.getMsg());
                    AlcsAuthHttpRequest.clientFailCallback(aError.getMsg(), IAlcsAuthCallback.this);
                }
            });
        } catch (Exception e) {
            ALog.m479d(TAG, "requestClientInfo, request error, e = " + e.toString());
            e.printStackTrace();
            clientFailCallback(e.toString(), iAlcsAuthCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void clientFailCallback(String str, IAlcsAuthCallback iAlcsAuthCallback) {
        CmpError ALCS_REQUEST_CLIENT_AUTH_FAIL = CmpError.ALCS_REQUEST_CLIENT_AUTH_FAIL();
        ALCS_REQUEST_CLIENT_AUTH_FAIL.setSubMsg(str);
        iAlcsAuthCallback.onFailed(ALCS_REQUEST_CLIENT_AUTH_FAIL);
    }

    public static void requestServerInfo(final IAlcsAuthCallback iAlcsAuthCallback) {
        ALog.m479d(TAG, "requestServerInfo");
        if (iAlcsAuthCallback == null) {
            ALog.m480e(TAG, "requestServerInfo callback null");
            return;
        }
        try {
            AConnect persistentConnect = ConnectManager.getInstance().getPersistentConnect();
            if (persistentConnect == null) {
                serverFailCallback("connect not found", iAlcsAuthCallback);
                return;
            }
            MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
            mqttPublishRequest.isRPC = true;
            mqttPublishRequest.topic = TOPIC_SERVER;
            persistentConnect.send(mqttPublishRequest, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.cmp.manager.connect.auth.alcs.AlcsAuthHttpRequest.2
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("requestServerInfo, onResponse(),rsp = ");
                    sb.append((aResponse == null || aResponse.data == null) ? "" : (String) aResponse.data);
                    ALog.m479d(AlcsAuthHttpRequest.TAG, sb.toString());
                    try {
                        JSONObject parseObject = JSONObject.parseObject((String) aResponse.data);
                        String string = parseObject.getString(NotificationCompat.CATEGORY_MESSAGE);
                        if (parseObject.getIntValue("code") != 200) {
                            AlcsAuthHttpRequest.serverFailCallback(string, IAlcsAuthCallback.this);
                        } else {
                            AlcsServerAuthValue alcsServerAuthValue = (AlcsServerAuthValue) parseObject.getObject("data", AlcsServerAuthValue.class);
                            if (alcsServerAuthValue != null && alcsServerAuthValue.checkValid()) {
                                IAlcsAuthCallback.this.onSuccess(alcsServerAuthValue);
                            }
                        }
                    } catch (Exception e) {
                        ALog.m480e(AlcsAuthHttpRequest.TAG, "requestServerInfo,onResponse(), error = " + e.toString());
                        AlcsAuthHttpRequest.serverFailCallback(e.toString(), IAlcsAuthCallback.this);
                    }
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    ALog.m480e(AlcsAuthHttpRequest.TAG, "requestServerInfo,onErrorResponse(), error = " + aError.getMsg());
                    AlcsAuthHttpRequest.serverFailCallback(aError.getMsg(), IAlcsAuthCallback.this);
                }
            });
        } catch (Exception e) {
            ALog.m479d(TAG, "requestServerInfo, request error, e = " + e.toString());
            e.printStackTrace();
            serverFailCallback(e.toString(), iAlcsAuthCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void serverFailCallback(String str, IAlcsAuthCallback iAlcsAuthCallback) {
        CmpError ALCS_REQUEST_SERVER_AUTH_FAIL = CmpError.ALCS_REQUEST_SERVER_AUTH_FAIL();
        ALCS_REQUEST_SERVER_AUTH_FAIL.setSubMsg(str);
        iAlcsAuthCallback.onFailed(ALCS_REQUEST_SERVER_AUTH_FAIL);
    }
}
