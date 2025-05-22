package com.aliyun.alink.linksdk.cmp.connect.hubapi;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.apiclient.CommonRequest;
import com.aliyun.alink.apiclient.CommonResponse;
import com.aliyun.alink.apiclient.InitializeConfig;
import com.aliyun.alink.apiclient.IoTAPIClientFactory;
import com.aliyun.alink.apiclient.IoTApiClient;
import com.aliyun.alink.apiclient.IoTCallback;
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
import com.aliyun.alink.linksdk.cmp.core.util.CallbackHelper;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class HubApiClientConnect extends AConnect {
    public static final String CONNECT_ID = "LINK_HUB_API_CLIENT";
    private static final String TAG = "HubApiClientConnect";
    private static IoTApiClient ioTAPIClient;
    private HubApiClientConnectConfig config;
    private Context context;

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void init(Context context, AConnectConfig aConnectConfig, IConnectInitListener iConnectInitListener) {
        ALog.m479d(TAG, "init()");
        if (context == null || aConnectConfig == null || !(aConnectConfig instanceof HubApiClientConnectConfig) || !aConnectConfig.checkVaild()) {
            ALog.m479d(TAG, "init()，params error");
            CallbackHelper.paramError(iConnectInitListener, "init, cxt or config is invalid");
            return;
        }
        this.connectId = CONNECT_ID;
        this.context = context;
        this.config = (HubApiClientConnectConfig) aConnectConfig;
        ioTAPIClient = new IoTAPIClientFactory().getClient();
        InitializeConfig initializeConfig = new InitializeConfig();
        initializeConfig.productKey = this.config.productKey;
        initializeConfig.deviceName = this.config.deviceName;
        if (!TextUtils.isEmpty(this.config.domain)) {
            initializeConfig.domain = this.config.domain;
        }
        if (!TextUtils.isEmpty(this.config.productSecret)) {
            initializeConfig.productSecret = this.config.productSecret;
        }
        if (!TextUtils.isEmpty(this.config.deviceSecret)) {
            initializeConfig.deviceSecret = this.config.deviceSecret;
        }
        ioTAPIClient.init(initializeConfig);
        this.connectState = ConnectState.CONNECTED;
        updateConnectState(this.connectState);
        if (iConnectInitListener != null) {
            iConnectInitListener.onSuccess();
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void onDestroy() {
        ALog.m479d(TAG, "onDestory()");
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void send(final ARequest aRequest, final IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "send()");
        if (aRequest == null || !(aRequest instanceof HubApiRequest)) {
            ALog.m479d(TAG, "request is invalid ");
            if (iConnectSendListener == null) {
                return;
            }
            CmpError PARAMS_ERROR = CmpError.PARAMS_ERROR();
            PARAMS_ERROR.setSubMsg("send, request is invalid");
            iConnectSendListener.onFailure(aRequest, PARAMS_ERROR);
            return;
        }
        ioTAPIClient.send(((HubApiRequest) aRequest).toChannelRequest(), new IoTCallback() { // from class: com.aliyun.alink.linksdk.cmp.connect.hubapi.HubApiClientConnect.1
            @Override // com.aliyun.alink.apiclient.IoTCallback
            public void onFailure(CommonRequest commonRequest, Exception exc) {
                if (iConnectSendListener == null) {
                    return;
                }
                CmpError HUB_API_SEND_FAIL = CmpError.HUB_API_SEND_FAIL();
                HUB_API_SEND_FAIL.setSubMsg(exc.toString());
                iConnectSendListener.onFailure(aRequest, HUB_API_SEND_FAIL);
            }

            @Override // com.aliyun.alink.apiclient.IoTCallback
            public void onResponse(CommonRequest commonRequest, CommonResponse commonResponse) {
                StringBuilder sb = new StringBuilder();
                sb.append("onResponse(),rsp = ");
                sb.append((commonResponse == null || commonResponse.getData() == null) ? "" : commonResponse.getData());
                ALog.m479d(HubApiClientConnect.TAG, sb.toString());
                if (iConnectSendListener == null) {
                    return;
                }
                if (commonResponse == null || commonResponse.getData() == null) {
                    CmpError HUB_API_SEND_FAIL = CmpError.HUB_API_SEND_FAIL();
                    HUB_API_SEND_FAIL.setSubMsg("empty response or fail status");
                    HUB_API_SEND_FAIL.setSubCode(717);
                    iConnectSendListener.onFailure(aRequest, HUB_API_SEND_FAIL);
                    return;
                }
                HubApiResponse hubApiResponse = new HubApiResponse();
                hubApiResponse.data = commonResponse.getData();
                iConnectSendListener.onResponse(aRequest, hubApiResponse);
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
}
