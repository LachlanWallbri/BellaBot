package com.aliyun.alink.dm.p006a;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.apiclient.CommonRequest;
import com.aliyun.alink.apiclient.CommonResponse;
import com.aliyun.alink.apiclient.InitializeConfig;
import com.aliyun.alink.apiclient.IoTAPIClientFactory;
import com.aliyun.alink.apiclient.IoTApiClient;
import com.aliyun.alink.apiclient.IoTCallback;
import com.aliyun.alink.apiclient.utils.StringUtils;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.IApiClient;
import com.aliyun.alink.dm.api.IoTApiClientConfig;
import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.linksdk.cmp.connect.hubapi.HubApiRequest;
import com.aliyun.alink.linksdk.cmp.connect.hubapi.HubApiResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tools.AError;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DMApiClientImpl.java */
/* renamed from: com.aliyun.alink.dm.a.a */
/* loaded from: classes.dex */
public class C0838a implements IApiClient {

    /* renamed from: a */
    private AtomicBoolean f324a = new AtomicBoolean(false);

    /* renamed from: b */
    private AtomicBoolean f325b = new AtomicBoolean(false);

    /* renamed from: c */
    private IoTApiClient f326c;

    public C0838a() {
        this.f326c = null;
        this.f326c = new IoTAPIClientFactory().getClient();
    }

    @Override // com.aliyun.alink.dm.api.IApiClient
    public void init(Context context, IoTApiClientConfig ioTApiClientConfig, DeviceInfo deviceInfo) {
        try {
            if (deviceInfo == null) {
                C0859a.m134c("DMApiClientImpl", "init apiclient failed.  di empty. ");
                return;
            }
            if (TextUtils.isEmpty(deviceInfo.productSecret) && TextUtils.isEmpty(deviceInfo.deviceSecret)) {
                C0859a.m134c("DMApiClientImpl", "init apiclient failed. ds or ps empty. ApiClient not support.");
                return;
            }
            if (!this.f325b.get() && !this.f324a.get()) {
                this.f325b.set(true);
                InitializeConfig initializeConfig = new InitializeConfig();
                initializeConfig.productKey = deviceInfo.productKey;
                initializeConfig.deviceName = deviceInfo.deviceName;
                initializeConfig.productSecret = deviceInfo.productSecret;
                initializeConfig.deviceSecret = deviceInfo.deviceSecret;
                initializeConfig.domain = ioTApiClientConfig.domain;
                this.f326c.init(initializeConfig);
                this.f325b.set(false);
                this.f324a.set(true);
                return;
            }
            C0859a.m134c("DMApiClientImpl", "IoTApiClient is initing or inited.");
        } catch (Exception e) {
            C0859a.m134c("DMApiClientImpl", "IoTApiClient init: e" + e);
            this.f324a.set(false);
            this.f325b.set(false);
        }
    }

    @Override // com.aliyun.alink.dm.api.IApiClient
    public void sendIoTHTTPRequest(final ARequest aRequest, final IConnectSendListener iConnectSendListener) {
        C0859a.m131a("DMApiClientImpl", "Deprecated method -> sendIoTHTTPRequest() called with: request = [" + aRequest + "], listener = [" + iConnectSendListener + "]");
        if (!this.f324a.get()) {
            C0859a.m134c("DMApiClientImpl", "sendIoTHTTPRequest failed, init first.");
            if (iConnectSendListener != null) {
                AError aError = new AError();
                aError.setCode(-4);
                aError.setSubCode(401);
                aError.setMsg("init apiclient first");
                iConnectSendListener.onFailure(aRequest, aError);
                return;
            }
            return;
        }
        if (!(aRequest instanceof HubApiRequest)) {
            if (iConnectSendListener != null) {
                AError aError2 = new AError();
                aError2.setCode(-4);
                aError2.setSubCode(402);
                aError2.setMsg("request should be instance of HubApiRequest");
                iConnectSendListener.onFailure(aRequest, aError2);
                return;
            }
            return;
        }
        CommonRequest channelRequest = ((HubApiRequest) aRequest).toChannelRequest();
        if (channelRequest != null && !StringUtils.isEmptyString(channelRequest.getDomain()) && !StringUtils.isEmptyString(channelRequest.getPath())) {
            this.f326c.send(channelRequest, new IoTCallback() { // from class: com.aliyun.alink.dm.a.a.1
                @Override // com.aliyun.alink.apiclient.IoTCallback
                public void onFailure(CommonRequest commonRequest, Exception exc) {
                    C0859a.m131a("DMApiClientImpl", "onFailure() called with: commonRequest = [" + commonRequest + "], e = [" + exc + "]");
                    if (iConnectSendListener != null) {
                        AError aError3 = new AError();
                        aError3.setCode(-4);
                        aError3.setMsg("onFailure " + exc);
                        iConnectSendListener.onFailure(aRequest, aError3);
                    }
                }

                @Override // com.aliyun.alink.apiclient.IoTCallback
                public void onResponse(CommonRequest commonRequest, CommonResponse commonResponse) {
                    C0859a.m131a("DMApiClientImpl", "onResponse() called with: commonRequest = [" + commonRequest + "], commonResponse = [" + commonResponse + "]");
                    if (commonResponse != null) {
                        HubApiResponse hubApiResponse = new HubApiResponse();
                        hubApiResponse.data = commonResponse.getData();
                        IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                        if (iConnectSendListener2 != null) {
                            iConnectSendListener2.onResponse(aRequest, hubApiResponse);
                            return;
                        }
                        return;
                    }
                    if (iConnectSendListener != null) {
                        AError aError3 = new AError();
                        aError3.setCode(-4);
                        aError3.setMsg("onResponse response empty");
                        iConnectSendListener.onFailure(aRequest, aError3);
                    }
                }
            });
            return;
        }
        if (iConnectSendListener != null) {
            AError aError3 = new AError();
            aError3.setCode(-4);
            aError3.setSubCode(402);
            aError3.setMsg("domain or path cannot be empty.");
            iConnectSendListener.onFailure(aRequest, aError3);
        }
    }

    @Override // com.aliyun.alink.dm.api.IApiClient
    public void sendIoTHTTPRequest(CommonRequest commonRequest, final IoTCallback ioTCallback) {
        C0859a.m131a("DMApiClientImpl", "sendIoTHTTPRequest() called with: request = [" + commonRequest + "], listener = [" + ioTCallback + "]");
        if (!this.f324a.get()) {
            C0859a.m134c("DMApiClientImpl", "sendIoTHTTPRequest failed, init first.");
            if (ioTCallback != null) {
                ioTCallback.onFailure(commonRequest, new IllegalStateException("init first"));
                return;
            }
            return;
        }
        if (commonRequest == null || StringUtils.isEmptyString(commonRequest.getDomain()) || StringUtils.isEmptyString(commonRequest.getPath())) {
            C0859a.m134c("DMApiClientImpl", "sendIoTHTTPRequest failed, domain and path cannot be empty.");
            if (ioTCallback != null) {
                ioTCallback.onFailure(commonRequest, new IllegalArgumentException("domain or path is null"));
                return;
            }
            return;
        }
        this.f326c.send(commonRequest, new IoTCallback() { // from class: com.aliyun.alink.dm.a.a.2
            @Override // com.aliyun.alink.apiclient.IoTCallback
            public void onFailure(CommonRequest commonRequest2, Exception exc) {
                C0859a.m131a("DMApiClientImpl", "onFailure() called with: commonRequest = [" + commonRequest2 + "], e = [" + exc + "]");
                IoTCallback ioTCallback2 = ioTCallback;
                if (ioTCallback2 != null) {
                    ioTCallback2.onFailure(commonRequest2, exc);
                }
            }

            @Override // com.aliyun.alink.apiclient.IoTCallback
            public void onResponse(CommonRequest commonRequest2, CommonResponse commonResponse) {
                C0859a.m131a("DMApiClientImpl", "onResponse() called with: commonRequest = [" + commonRequest2 + "], commonResponse = [" + commonResponse + "]");
                IoTCallback ioTCallback2 = ioTCallback;
                if (ioTCallback2 != null) {
                    ioTCallback2.onResponse(commonRequest2, commonResponse);
                }
            }
        });
    }

    /* renamed from: a */
    public void m86a() {
        try {
            this.f324a.set(false);
            this.f325b.set(false);
            if (this.f326c != null) {
                this.f326c.deinit();
            }
        } catch (Exception unused) {
        }
    }
}
