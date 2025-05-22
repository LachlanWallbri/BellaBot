package com.aliyun.alink.linksdk.cmp.connect.alcs;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.api.server.AlcsServerConfig;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPConstant;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPContext;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPRequest;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPResponse;
import com.aliyun.alink.linksdk.alcs.coap.IAlcsCoAPResHandler;
import com.aliyun.alink.linksdk.alcs.coap.resources.AlcsCoAPResource;
import com.aliyun.alink.linksdk.cmp.api.CommonResource;
import com.aliyun.alink.linksdk.cmp.api.ResourceRequest;
import com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsServerConnectOption;
import com.aliyun.alink.linksdk.cmp.core.base.AConnectConfig;
import com.aliyun.alink.linksdk.cmp.core.base.AConnectOption;
import com.aliyun.alink.linksdk.cmp.core.base.AMultiportConnect;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResource;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.CmpError;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectAuth;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectInitListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectResourceRegister;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectUnscribeListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IResourceRequestListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IResourceResponseListener;
import com.aliyun.alink.linksdk.cmp.core.util.CallbackHelper;
import com.aliyun.alink.linksdk.cmp.core.util.ClassSwitchHelper;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.linksdk.alcs.AlcsCmpSDK;
import com.aliyun.linksdk.alcs.IAlcsServer;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsServerConnect extends AMultiportConnect implements IConnectResourceRegister, IResourceResponseListener, IConnectAuth<Map<String, String>> {
    public static final String CONNECT_ID = "LINK_ALCS_MULTIPORT";
    private static final String TAG = "AlcsMultiportConnect";
    private IAlcsServer alcsServer = null;
    private AlcsServerConnectConfig config;
    private Context context;
    private IConnectInitListener initListener;

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void init(Context context, AConnectConfig aConnectConfig, IConnectInitListener iConnectInitListener) {
        ALog.m479d(TAG, "init()");
        if (context == null || aConnectConfig == null || !(aConnectConfig instanceof AlcsServerConnectConfig) || !aConnectConfig.checkVaild()) {
            ALog.m479d(TAG, "init()，params error");
            CallbackHelper.paramError(iConnectInitListener, "init, cxt or config is invalid");
            return;
        }
        this.connectId = CONNECT_ID;
        this.context = context;
        this.initListener = iConnectInitListener;
        AlcsServerConnectConfig alcsServerConnectConfig = (AlcsServerConnectConfig) aConnectConfig;
        this.config = alcsServerConnectConfig;
        updateConnectState(ConnectState.CONNECTING);
        if (alcsServerConnectConfig.isNeedAuthInfo()) {
            iConnectInitListener.onPrepareAuth(this);
        } else {
            initAndStart();
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void updateConnectOption(AConnectOption aConnectOption) {
        ALog.m479d(TAG, "updateConnectOption()");
        if (aConnectOption == null || !(aConnectOption instanceof AlcsServerConnectOption)) {
            return;
        }
        super.updateConnectOption(aConnectOption);
        if (this.alcsServer == null) {
            return;
        }
        AlcsServerConnectOption alcsServerConnectOption = (AlcsServerConnectOption) aConnectOption;
        String prefix = alcsServerConnectOption.getPrefix();
        String blackClients = alcsServerConnectOption.getBlackClients();
        String secrect = alcsServerConnectOption.getSecrect();
        int i = C10021.f1013x4aeafd7b[alcsServerConnectOption.getOptionFlag().ordinal()];
        if (i == 1) {
            if (TextUtils.isEmpty(prefix) || TextUtils.isEmpty(secrect)) {
                return;
            }
            this.alcsServer.addSvrAccessKey(prefix, secrect);
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            this.alcsServer.updateBlackList(blackClients);
        } else {
            if (TextUtils.isEmpty(prefix)) {
                return;
            }
            this.alcsServer.removeSvrKey(prefix);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsServerConnect$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C10021 {

        /* renamed from: $SwitchMap$com$aliyun$alink$linksdk$cmp$connect$alcs$AlcsServerConnectOption$OptionFlag */
        static final /* synthetic */ int[] f1013x4aeafd7b;

        static {
            int[] iArr = new int[AlcsServerConnectOption.OptionFlag.values().length];
            f1013x4aeafd7b = iArr;
            try {
                iArr[AlcsServerConnectOption.OptionFlag.ADD_PREFIX_SECRET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1013x4aeafd7b[AlcsServerConnectOption.OptionFlag.DELETE_PREFIX.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1013x4aeafd7b[AlcsServerConnectOption.OptionFlag.UPDATE_BLACK_LIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectAuth
    public void onPrepareAuthFail(AError aError) {
        ALog.m479d(TAG, "onPrepareFail()");
        IConnectInitListener iConnectInitListener = this.initListener;
        if (iConnectInitListener != null) {
            iConnectInitListener.onFailure(aError);
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectAuth
    public void onAuth(Map<String, String> map) {
        ALog.m479d(TAG, "auth()");
        if (map == null || !map.containsKey("PREFIX") || !map.containsKey("SECRET")) {
            IConnectInitListener iConnectInitListener = this.initListener;
            if (iConnectInitListener != null) {
                iConnectInitListener.onFailure(CmpError.CONNECT_AUTH_INFO_ERROR());
                return;
            }
            return;
        }
        this.config.setPrefix(map.get("PREFIX"));
        this.config.setSecret(map.get("SECRET"));
        initAndStart();
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void onDestroy() {
        ALog.m479d(TAG, "onDestroy()");
        IAlcsServer iAlcsServer = this.alcsServer;
        if (iAlcsServer != null) {
            iAlcsServer.stopServer();
        }
    }

    private void initAndStart() {
        ALog.m479d(TAG, "initAndStart()");
        try {
            AlcsCmpSDK.init(this.context);
            AlcsServerConfig.Builder builder = new AlcsServerConfig.Builder();
            builder.setProdKey(this.config.getProductKey());
            builder.setDevcieName(this.config.getDeviceName());
            builder.setBlackList(this.config.getBlackClients());
            builder.addPrefixSec(this.config.getPrefix(), this.config.getSecret());
            builder.setUnsafePort(5683);
            IAlcsServer initServer = AlcsCmpSDK.initServer(builder.build());
            this.alcsServer = initServer;
            initServer.startServer();
            IConnectInitListener iConnectInitListener = this.initListener;
            if (iConnectInitListener != null) {
                iConnectInitListener.onSuccess();
            }
            updateConnectState(ConnectState.CONNECTED);
        } catch (Exception e) {
            ALog.m479d(TAG, "init(),error");
            e.printStackTrace();
            if (this.initListener != null) {
                CmpError ALCS_INIT_MULTIPORT_FAIL = CmpError.ALCS_INIT_MULTIPORT_FAIL();
                ALCS_INIT_MULTIPORT_FAIL.setSubMsg(e.toString());
                this.initListener.onFailure(ALCS_INIT_MULTIPORT_FAIL);
            }
            updateConnectState(ConnectState.CONNECTFAIL);
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectResourceRegister
    public void registerResource(AResource aResource, IResourceRequestListener iResourceRequestListener) {
        ALog.m479d(TAG, "registerResource()");
        if (this.alcsServer == null) {
            return;
        }
        boolean z = aResource instanceof CoAPResource;
        if (!z && !(aResource instanceof CommonResource)) {
            if (iResourceRequestListener != null) {
                iResourceRequestListener.onFailure(CmpError.UNSUPPORT());
                return;
            }
            return;
        }
        CoAPResource coAPResource = null;
        if (z) {
            coAPResource = (CoAPResource) aResource;
        } else if (aResource instanceof CommonResource) {
            coAPResource = ClassSwitchHelper.commonResToCoapRes((CommonResource) aResource);
        }
        AlcsCoAPResource alcsCoAPResource = new AlcsCoAPResource(coAPResource.topic);
        alcsCoAPResource.setPath(coAPResource.topic);
        alcsCoAPResource.setPermission(3);
        alcsCoAPResource.setHandler(new AlcsCoAPResHandler(aResource, coAPResource.topic, iResourceRequestListener));
        this.alcsServer.registerAllResource(coAPResource.isNeedAuth, alcsCoAPResource);
        if (iResourceRequestListener != null) {
            iResourceRequestListener.onSuccess();
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IResourceResponseListener
    public void onResponse(AResource aResource, ResourceRequest resourceRequest, Object obj) {
        boolean z;
        boolean z2;
        AlcsCoAPResponse alcsCoAPResponse;
        ALog.m479d(TAG, "onResponse()");
        if (this.alcsServer == null) {
            ALog.m479d(TAG, "onResponse(),params error,alcsServer null, return");
            return;
        }
        if (aResource == null || !(((z = aResource instanceof CoAPResource)) || (aResource instanceof CommonResource))) {
            ALog.m479d(TAG, "onResponse(),params error,resource error, return");
            return;
        }
        if (resourceRequest == null) {
            ALog.m479d(TAG, "onResponse(),params error, resoucreReq is null");
            return;
        }
        if (resourceRequest.context == null || !(resourceRequest.context instanceof AlcsCoAPRequest)) {
            ALog.m479d(TAG, "onResponse(),params error,resoucre request context error, return" + resourceRequest.context);
            return;
        }
        if (obj == null || (!((z2 = obj instanceof AlcsCoAPResponse)) && !(obj instanceof AResponse))) {
            ALog.m479d(TAG, "onResponse(),params error,responseerror, return");
            return;
        }
        if (z2) {
            alcsCoAPResponse = (AlcsCoAPResponse) obj;
        } else {
            AlcsCoAPResponse createResponse = AlcsCoAPResponse.createResponse((AlcsCoAPRequest) resourceRequest.context, AlcsCoAPConstant.ResponseCode.CONTENT);
            Object data = ((AResponse) obj).getData();
            if (data instanceof String) {
                createResponse.setPayload((String) data);
            } else if (data instanceof byte[]) {
                createResponse.setPayload((byte[]) data);
            } else {
                try {
                    createResponse.setPayload(data.toString());
                } catch (Exception e) {
                    ALog.m484w(TAG, "onResponse(), send , toString error," + e.toString());
                    return;
                }
            }
            alcsCoAPResponse = createResponse;
        }
        boolean z3 = false;
        if (z) {
            z3 = ((CoAPResource) aResource).isNeedAuth;
        } else if (aResource instanceof CommonResource) {
            z3 = ((CommonResource) aResource).isNeedAuth;
        }
        ALog.m484w(TAG, "onResponse(), exe sendResponse, isNeedAuth = " + z3);
        this.alcsServer.sendResponse(z3, alcsCoAPResponse);
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectResourceRegister
    public void unregisterResource(AResource aResource, IBaseListener iBaseListener) {
        ALog.m479d(TAG, "unregisterResource()");
        if (iBaseListener != null) {
            iBaseListener.onFailure(CmpError.UNSUPPORT());
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectResourceRegister
    public void publishResource(AResource aResource, IBaseListener iBaseListener) {
        ALog.m479d(TAG, "publishResource");
        if (this.alcsServer == null) {
            return;
        }
        boolean z = aResource instanceof CoAPResource;
        if (!z && !(aResource instanceof CommonResource)) {
            if (iBaseListener != null) {
                iBaseListener.onFailure(CmpError.UNSUPPORT());
                return;
            }
            return;
        }
        CoAPResource coAPResource = null;
        if (z) {
            coAPResource = (CoAPResource) aResource;
        } else if (aResource instanceof CommonResource) {
            coAPResource = ClassSwitchHelper.commonResToCoapRes((CommonResource) aResource);
        }
        this.alcsServer.publishResoucre(coAPResource.topic, coAPResource.payload);
        if (iBaseListener != null) {
            iBaseListener.onSuccess();
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void send(ARequest aRequest, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "send()");
        if (iConnectSendListener != null) {
            iConnectSendListener.onFailure(aRequest, CmpError.UNSUPPORT());
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void subscribe(ARequest aRequest, IConnectSubscribeListener iConnectSubscribeListener) {
        ALog.m479d(TAG, "subscribe()");
        if (iConnectSubscribeListener != null) {
            iConnectSubscribeListener.onFailure(CmpError.UNSUPPORT());
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void unsubscribe(ARequest aRequest, IConnectUnscribeListener iConnectUnscribeListener) {
        ALog.m479d(TAG, "unsubscribe()");
        if (iConnectUnscribeListener != null) {
            iConnectUnscribeListener.onFailure(CmpError.UNSUPPORT());
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void setNotifyListener(IConnectNotifyListener iConnectNotifyListener) {
        ALog.m479d(TAG, "setNotifyListener()");
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private class AlcsCoAPResHandler implements IAlcsCoAPResHandler {
        private AResource resource;
        private IResourceRequestListener resourceRequestListener;
        private String topic;

        public AlcsCoAPResHandler(AResource aResource, String str, IResourceRequestListener iResourceRequestListener) {
            this.resource = null;
            this.topic = null;
            this.resourceRequestListener = null;
            this.resource = aResource;
            this.topic = str;
            this.resourceRequestListener = iResourceRequestListener;
        }

        @Override // com.aliyun.alink.linksdk.alcs.coap.IAlcsCoAPResHandler
        public void onRecRequest(AlcsCoAPContext alcsCoAPContext, AlcsCoAPRequest alcsCoAPRequest) {
            ALog.m479d(AlcsServerConnect.TAG, "onRecRequest(),topic = " + this.topic);
            if (this.resourceRequestListener == null) {
                return;
            }
            ResourceRequest resourceRequest = new ResourceRequest();
            resourceRequest.topic = this.topic;
            if (alcsCoAPContext != null) {
                resourceRequest.payloadObj = alcsCoAPRequest.getPayload();
            }
            resourceRequest.context = alcsCoAPRequest;
            this.resourceRequestListener.onHandleRequest(this.resource, resourceRequest, AlcsServerConnect.this);
        }
    }
}
