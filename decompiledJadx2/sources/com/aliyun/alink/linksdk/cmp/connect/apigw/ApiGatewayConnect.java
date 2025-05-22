package com.aliyun.alink.linksdk.cmp.connect.apigw;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.core.base.AConnect;
import com.aliyun.alink.linksdk.cmp.core.base.AConnectConfig;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.CmpError;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectInitListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectUnscribeListener;
import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.iot.aep.sdk.apiclient.IoTAPIClient;
import com.aliyun.iot.aep.sdk.apiclient.IoTAPIClientFactory;
import com.aliyun.iot.aep.sdk.apiclient.IoTAPIClientImpl;
import com.aliyun.iot.aep.sdk.apiclient.callback.IoTCallback;
import com.aliyun.iot.aep.sdk.apiclient.callback.IoTResponse;
import com.aliyun.iot.aep.sdk.apiclient.emuns.Env;
import com.aliyun.iot.aep.sdk.apiclient.request.IoTRequest;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ApiGatewayConnect extends AConnect {
    public static String CONFIGE_HOST = null;
    public static final String CONNECT_ID = "LINK_API_GATEWAY";
    public static final String PerformanceTag = "PerformanceTag";
    private static final String TAG = "ApiGatewayConnect";
    private static IoTAPIClient ioTAPIClient;
    private ApiGatewayConnectConfig config;
    private Context context;

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void init(Context context, AConnectConfig aConnectConfig, IConnectInitListener iConnectInitListener) {
        ALog.m479d(TAG, "init()");
        this.connectId = CONNECT_ID;
        this.context = context;
        ApiGatewayConnectConfig apiGatewayConnectConfig = (ApiGatewayConnectConfig) aConnectConfig;
        this.config = apiGatewayConnectConfig;
        this.connectState = ConnectState.CONNECTED;
        updateConnectState(this.connectState);
        if (aConnectConfig != null) {
            CONFIGE_HOST = apiGatewayConnectConfig.host;
        }
        if (iConnectInitListener != null) {
            iConnectInitListener.onSuccess();
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void onDestroy() {
        ALog.m479d(TAG, "onDestory()");
        if (isSupport()) {
            return;
        }
        ALog.m479d(TAG, " not support");
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void send(final ARequest aRequest, final IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "send()");
        if (!isSupport()) {
            ALog.m479d(TAG, " not support");
            return;
        }
        if (aRequest == null || !(aRequest instanceof ApiGatewayRequest)) {
            ALog.m479d(TAG, "request is invalid ");
            if (iConnectSendListener == null) {
                return;
            }
            CmpError PARAMS_ERROR = CmpError.PARAMS_ERROR();
            PARAMS_ERROR.setSubMsg("send, request is invalid");
            iConnectSendListener.onFailure(aRequest, PARAMS_ERROR);
            return;
        }
        if (ioTAPIClient == null) {
            try {
                IoTAPIClientImpl.InitializeConfig initializeConfig = new IoTAPIClientImpl.InitializeConfig();
                initializeConfig.appKey = this.config.appkey;
                initializeConfig.host = this.config.host;
                initializeConfig.apiEnv = Env.RELEASE;
                IoTAPIClientImpl.getInstance().init(this.context, initializeConfig);
            } catch (Exception e) {
                ALog.m479d(TAG, "init api gateway error," + e.toString());
            }
            ioTAPIClient = new IoTAPIClientFactory().getClient();
            ALog.m479d(TAG, "register tracker");
        }
        ApiGatewayRequest apiGatewayRequest = (ApiGatewayRequest) aRequest;
        IoTRequest iotRequest = apiGatewayRequest.toIotRequest();
        final String str = apiGatewayRequest.traceId != null ? apiGatewayRequest.traceId : "";
        String str2 = apiGatewayRequest.alinkIdForTracker != null ? apiGatewayRequest.alinkIdForTracker : "";
        if (!TextUtils.isEmpty(str)) {
            ALog.m479d("PerformanceTag", "{\"mod\":\"cmp\",\"tunnel\":\"cloud\",\"id\":\"_id_\",\"alinkid\":\"_alinkid_\",\"event\":\"req\"}".replace("_id_", str).replace("_alinkid_", str2));
        }
        ioTAPIClient.send(iotRequest, new IoTCallback() { // from class: com.aliyun.alink.linksdk.cmp.connect.apigw.ApiGatewayConnect.1
            public void onFailure(IoTRequest ioTRequest, Exception exc) {
                if (!TextUtils.isEmpty(str)) {
                    ALog.m479d("PerformanceTag", "{\"mod\":\"cmp\",\"tunnel\":\"cloud\",\"id\":\"_id_\",\"event\":\"fail\"}".replace("_id_", str));
                }
                if (iConnectSendListener == null) {
                    return;
                }
                CmpError APIGW_SEND_FAIL = CmpError.APIGW_SEND_FAIL();
                APIGW_SEND_FAIL.setSubMsg(exc.toString());
                iConnectSendListener.onFailure(aRequest, APIGW_SEND_FAIL);
            }

            public void onResponse(IoTRequest ioTRequest, IoTResponse ioTResponse) {
                StringBuilder sb = new StringBuilder();
                sb.append("onResponse(),rsp = ");
                sb.append((ioTResponse == null || ioTResponse.getRawData() == null) ? "" : new String(ioTResponse.getRawData()));
                ALog.m479d(ApiGatewayConnect.TAG, sb.toString());
                if (!TextUtils.isEmpty(str)) {
                    ALog.m479d("PerformanceTag", "{\"mod\":\"cmp\",\"tunnel\":\"cloud\",\"id\":\"_id_\",\"event\":\"res\"}".replace("_id_", str));
                }
                if (iConnectSendListener == null) {
                    return;
                }
                if (ioTResponse == null || ioTResponse.getRawData() == null) {
                    CmpError APIGW_SEND_FAIL = CmpError.APIGW_SEND_FAIL();
                    APIGW_SEND_FAIL.setSubMsg("empty response");
                    iConnectSendListener.onFailure(aRequest, APIGW_SEND_FAIL);
                } else {
                    ApiGatewayResponse apiGatewayResponse = new ApiGatewayResponse();
                    apiGatewayResponse.data = new String(ioTResponse.getRawData());
                    iConnectSendListener.onResponse(aRequest, apiGatewayResponse);
                }
            }
        });
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void subscribe(ARequest aRequest, IConnectSubscribeListener iConnectSubscribeListener) {
        ALog.m479d(TAG, "subscribe,unsupport");
        if (iConnectSubscribeListener != null) {
            iConnectSubscribeListener.onFailure(CmpError.UNSUPPORT());
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void unsubscribe(ARequest aRequest, IConnectUnscribeListener iConnectUnscribeListener) {
        ALog.m479d(TAG, "unsubscribe,unsupport");
        if (iConnectUnscribeListener != null) {
            iConnectUnscribeListener.onFailure(CmpError.UNSUPPORT());
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void setNotifyListener(IConnectNotifyListener iConnectNotifyListener) {
        ALog.m479d(TAG, "setNotifyListener,unsupport");
    }

    private boolean isSupport() {
        try {
            return Class.forName("com.aliyun.iot.aep.sdk.apiclient.IoTAPIClientFactory") != null;
        } catch (Throwable unused) {
            return false;
        }
    }
}
