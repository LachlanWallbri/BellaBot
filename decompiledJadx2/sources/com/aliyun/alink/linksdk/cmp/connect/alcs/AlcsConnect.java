package com.aliyun.alink.linksdk.cmp.connect.alcs;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.api.client.IDeviceHandler;
import com.aliyun.alink.linksdk.alcs.api.utils.ErrorInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalRspMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.cmp.core.base.AConnect;
import com.aliyun.alink.linksdk.cmp.core.base.AConnectConfig;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.CmpError;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectAuth;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectInitListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectUnscribeListener;
import com.aliyun.alink.linksdk.cmp.core.util.CallbackHelper;
import com.aliyun.alink.linksdk.cmp.core.util.ClassSwitchHelper;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.linksdk.alcs.AlcsCmpSDK;
import com.aliyun.linksdk.alcs.IAlcsClient;
import com.aliyun.linksdk.alcs.IClientNotify;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsConnect extends AConnect implements IConnectAuth<Map<String, String>> {
    public static final String PerformanceTag = "PerformanceTag";
    private static final String TAG = "AlcsConnect";
    private IAlcsClient alcsClient = null;
    private ArrayList<CacheAction> cacheActionList = null;
    private AlcsConnectConfig config;
    private Context context;
    private IConnectInitListener initListener;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum ActionEnum {
        Send,
        Subscribe,
        Unsubscribe
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void init(Context context, AConnectConfig aConnectConfig, IConnectInitListener iConnectInitListener) {
        ALog.m479d(TAG, "init()");
        if (context == null || aConnectConfig == null || !(aConnectConfig instanceof AlcsConnectConfig) || !aConnectConfig.checkVaild()) {
            ALog.m479d(TAG, "init()，params error");
            CallbackHelper.paramError(iConnectInitListener, "init, cxt or config is invalid");
            return;
        }
        this.context = context;
        AlcsConnectConfig alcsConnectConfig = (AlcsConnectConfig) aConnectConfig;
        this.config = alcsConnectConfig;
        this.initListener = iConnectInitListener;
        updateConnectState(ConnectState.CONNECTING);
        if (alcsConnectConfig.isNeedAuthInfo()) {
            iConnectInitListener.onPrepareAuth(this);
        } else {
            initClientConnect();
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void onDestroy() {
        ALog.m479d(TAG, "onDestroy()");
        IAlcsClient iAlcsClient = this.alcsClient;
        if (iAlcsClient != null) {
            iAlcsClient.destroy();
            this.alcsClient = null;
        }
        updateConnectState(ConnectState.DISCONNECTED);
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public ConnectState getConnectState() {
        if (this.alcsClient != null && this.connectState != ConnectState.CONNECTING) {
            if (this.alcsClient.isServerOnline()) {
                updateConnectState(ConnectState.CONNECTED);
            } else {
                updateConnectState(ConnectState.DISCONNECTED);
            }
        }
        return this.connectState;
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
        if (map == null || !map.containsKey("PK") || !map.containsKey("DN") || !map.containsKey("TOKEN") || !map.containsKey("KEY")) {
            IConnectInitListener iConnectInitListener = this.initListener;
            if (iConnectInitListener != null) {
                iConnectInitListener.onFailure(CmpError.CONNECT_AUTH_INFO_ERROR());
                return;
            }
            return;
        }
        this.config.setProductKey(map.get("PK"));
        this.config.setDeviceName(map.get("DN"));
        this.config.setAccessKey(map.get("KEY"));
        this.config.setAccessToken(map.get("TOKEN"));
        initClientConnect();
    }

    private void initClientConnect() {
        ALog.m479d(TAG, "initClientConnect()");
        IAlcsClient initClientConnect = AlcsCmpSDK.initClientConnect(ClassSwitchHelper.alcsConfigTransfer(this.config), new IDeviceHandler() { // from class: com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsConnect.1
            @Override // com.aliyun.alink.linksdk.alcs.api.client.IDeviceHandler
            public void onSuccess(Object obj) {
                ALog.m479d(AlcsConnect.TAG, "initClientConnect(), onSuccess");
                AlcsConnect.this.updateConnectState(ConnectState.CONNECTED);
                if (AlcsConnect.this.initListener != null) {
                    AlcsConnect.this.initListener.onSuccess();
                }
                AlcsConnect.this.handleCacheActions(true);
            }

            @Override // com.aliyun.alink.linksdk.alcs.api.client.IDeviceHandler
            public void onFail(Object obj, ErrorInfo errorInfo) {
                String str;
                ALog.m479d(AlcsConnect.TAG, "initClientConnect(), onFail");
                AlcsConnect.this.updateConnectState(ConnectState.CONNECTFAIL);
                if (AlcsConnect.this.initListener != null) {
                    CmpError ALCS_INIT_ERROR = CmpError.ALCS_INIT_ERROR();
                    ALCS_INIT_ERROR.setSubCode(errorInfo != null ? errorInfo.getErrorCode() : 0);
                    if (errorInfo != null) {
                        str = errorInfo.getErrorCode() + "," + errorInfo.getErrorMsg();
                    } else {
                        str = "";
                    }
                    ALCS_INIT_ERROR.setSubMsg(str);
                    AlcsConnect.this.initListener.onFailure(ALCS_INIT_ERROR);
                }
                AlcsConnect.this.handleCacheActions(false);
            }
        });
        this.alcsClient = initClientConnect;
        initClientConnect.setNotifyListener(new IClientNotify() { // from class: com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsConnect.2
            @Override // com.aliyun.linksdk.alcs.IClientNotify
            public void onServerStateChange(boolean z) {
                ALog.m479d(AlcsConnect.TAG, "onServerStateChange(), isConnected = " + z);
                if (z) {
                    AlcsConnect.this.updateConnectState(ConnectState.CONNECTED);
                } else {
                    AlcsConnect.this.updateConnectState(ConnectState.DISCONNECTED);
                }
            }

            @Override // com.aliyun.linksdk.alcs.IClientNotify
            public void onNotify(String str, PalRspMessage palRspMessage) {
                ALog.m479d(AlcsConnect.TAG, "onNotify(), topic  = " + str);
                if (AlcsConnect.this.notifyListener == null) {
                    return;
                }
                try {
                    str = new URI(str).getPath();
                } catch (Exception e) {
                    ALog.m479d(AlcsConnect.TAG, "onNotify(), e = " + e.toString());
                }
                ALog.m479d(AlcsConnect.TAG, "onNotify(), path = " + str);
                if (AlcsConnect.this.notifyListener.shouldHandle(AlcsConnect.this.getConnectId(), str)) {
                    ALog.m479d(AlcsConnect.TAG, "onNotify(), notify");
                    AMessage aMessage = new AMessage();
                    aMessage.setData(palRspMessage.payload);
                    AlcsConnect.this.notifyListener.onNotify(AlcsConnect.this.getConnectId(), str, aMessage);
                }
            }
        });
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void send(final ARequest aRequest, final IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "send()");
        if (this.alcsClient == null || !(this.connectState == ConnectState.CONNECTED || this.connectState == ConnectState.CONNECTING)) {
            if (iConnectSendListener != null) {
                iConnectSendListener.onFailure(aRequest, CmpError.CONNECT_FAIL_DISCONNECT());
            }
        } else {
            if (this.connectState == ConnectState.CONNECTING) {
                cacheAction(ActionEnum.Send, aRequest, iConnectSendListener);
                return;
            }
            CoAPRequest coAPRequest = (CoAPRequest) aRequest;
            final String str = coAPRequest.traceId != null ? coAPRequest.traceId : "";
            String str2 = coAPRequest.alinkIdForTracker != null ? coAPRequest.alinkIdForTracker : "";
            if (!TextUtils.isEmpty(str)) {
                ALog.m479d("PerformanceTag", "{\"mod\":\"cmp\",\"tunnel\":\"alcs\",\"id\":\"_id_\",\"alinkid\":\"_alinkid_\",\"event\":\"req\"}".replace("_id_", str).replace("_alinkid_", str2));
            }
            this.alcsClient.sendRequest(coAPRequest.isSecurity, ClassSwitchHelper.alcsRequestToIotReqMsg(this.config.getProductKey(), this.config.getDeviceName(), coAPRequest), new PalMsgListener() { // from class: com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsConnect.3
                @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener
                public void onLoad(PalRspMessage palRspMessage) {
                    if (iConnectSendListener == null) {
                        return;
                    }
                    if (palRspMessage == null || palRspMessage.code != 0) {
                        if (!TextUtils.isEmpty(str)) {
                            ALog.m479d("PerformanceTag", "{\"mod\":\"cmp\",\"tunnel\":\"alcs\",\"id\":\"_id_\",\"event\":\"fail\"}".replace("_id_", str));
                        }
                        iConnectSendListener.onFailure(aRequest, CmpError.ALCS_SEND_FAIL());
                    } else {
                        if (!TextUtils.isEmpty(str)) {
                            ALog.m479d("PerformanceTag", "{\"mod\":\"cmp\",\"tunnel\":\"alcs\",\"id\":\"_id_\",\"event\":\"res\"}".replace("_id_", str));
                        }
                        iConnectSendListener.onResponse(aRequest, ClassSwitchHelper.IotResTransfer(palRspMessage));
                    }
                }
            });
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void subscribe(ARequest aRequest, final IConnectSubscribeListener iConnectSubscribeListener) {
        ALog.m479d(TAG, "subscribe()");
        if (this.alcsClient == null || !(this.connectState == ConnectState.CONNECTED || this.connectState == ConnectState.CONNECTING)) {
            if (iConnectSubscribeListener != null) {
                iConnectSubscribeListener.onFailure(CmpError.CONNECT_FAIL_DISCONNECT());
            }
        } else {
            if (this.connectState == ConnectState.CONNECTING) {
                cacheAction(ActionEnum.Subscribe, aRequest, iConnectSubscribeListener);
                return;
            }
            CoAPRequest coAPRequest = (CoAPRequest) aRequest;
            this.alcsClient.subscribe(coAPRequest.isSecurity, ClassSwitchHelper.alcsRequestToIotSubMsg(this.config.getProductKey(), this.config.getDeviceName(), coAPRequest), new PalMsgListener() { // from class: com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsConnect.4
                @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener
                public void onLoad(PalRspMessage palRspMessage) {
                    if (iConnectSubscribeListener != null) {
                        if (palRspMessage.code == 0) {
                            iConnectSubscribeListener.onSuccess();
                            return;
                        }
                        CmpError ALCS_SUBSCRIBE_FAIL = CmpError.ALCS_SUBSCRIBE_FAIL();
                        ALCS_SUBSCRIBE_FAIL.setSubMsg(String.valueOf(palRspMessage.code));
                        iConnectSubscribeListener.onFailure(ALCS_SUBSCRIBE_FAIL);
                    }
                }
            });
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void unsubscribe(ARequest aRequest, final IConnectUnscribeListener iConnectUnscribeListener) {
        ALog.m479d(TAG, "unsubscribe()");
        if (aRequest == null || this.alcsClient == null || !(this.connectState == ConnectState.CONNECTED || this.connectState == ConnectState.CONNECTING)) {
            if (iConnectUnscribeListener != null) {
                iConnectUnscribeListener.onFailure(CmpError.CONNECT_FAIL_DISCONNECT());
            }
        } else {
            if (this.connectState == ConnectState.CONNECTING) {
                cacheAction(ActionEnum.Unsubscribe, aRequest, iConnectUnscribeListener);
                return;
            }
            try {
                this.alcsClient.unsubscribe(((CoAPRequest) aRequest).isSecurity, ClassSwitchHelper.alcsRequestToIotSubMsg(this.config.getProductKey(), this.config.getDeviceName(), (CoAPRequest) aRequest), new PalMsgListener() { // from class: com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsConnect.5
                    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener
                    public void onLoad(PalRspMessage palRspMessage) {
                        if (palRspMessage != null && palRspMessage.code == 0) {
                            ALog.m479d(AlcsConnect.TAG, "unsubscribe(),onSuccess");
                            IConnectUnscribeListener iConnectUnscribeListener2 = iConnectUnscribeListener;
                            if (iConnectUnscribeListener2 != null) {
                                iConnectUnscribeListener2.onSuccess();
                                return;
                            }
                            return;
                        }
                        ALog.m479d(AlcsConnect.TAG, "unsubscribe(),onFail");
                        if (iConnectUnscribeListener == null) {
                            return;
                        }
                        CmpError ALCS_UNSUBSCRIBE_FAIL = CmpError.ALCS_UNSUBSCRIBE_FAIL();
                        ALCS_UNSUBSCRIBE_FAIL.setSubMsg(palRspMessage != null ? String.valueOf(palRspMessage.code) : "1");
                        iConnectUnscribeListener.onFailure(ALCS_UNSUBSCRIBE_FAIL);
                    }
                });
            } catch (Exception e) {
                ALog.m479d(TAG, "unsubscribe(), error" + e.toString());
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public class CacheAction {
        public ActionEnum action;
        public Object listener;
        public ARequest request;

        private CacheAction() {
        }
    }

    private void cacheAction(ActionEnum actionEnum, ARequest aRequest, Object obj) {
        ALog.m479d(TAG, "cacheAction");
        CacheAction cacheAction = new CacheAction();
        cacheAction.action = actionEnum;
        cacheAction.request = aRequest;
        cacheAction.listener = obj;
        if (this.cacheActionList == null) {
            this.cacheActionList = new ArrayList<>();
        }
        this.cacheActionList.add(cacheAction);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCacheActions(boolean z) {
        ALog.m479d(TAG, "handleCacheActions(),isConnect = " + z);
        ArrayList<CacheAction> arrayList = this.cacheActionList;
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        Iterator<CacheAction> it = this.cacheActionList.iterator();
        while (it.hasNext()) {
            CacheAction next = it.next();
            ALog.m479d(TAG, "handleCacheActions(),item");
            if (next == null) {
                ALog.m480e(TAG, "handleCacheActions(),action null");
            } else {
                int i = C09986.f1012x59a3ed23[next.action.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        if (i == 3) {
                            if (!z && next.listener != null) {
                                ((IConnectUnscribeListener) next.listener).onFailure(CmpError.CONNECT_FAIL_DISCONNECT());
                            } else {
                                unsubscribe(next.request, (IConnectUnscribeListener) next.listener);
                            }
                        }
                    } else if (!z && next.listener != null) {
                        ((IConnectSubscribeListener) next.listener).onFailure(CmpError.CONNECT_FAIL_DISCONNECT());
                    } else {
                        subscribe(next.request, (IConnectSubscribeListener) next.listener);
                    }
                } else if (!z && next.listener != null) {
                    ((IConnectSendListener) next.listener).onFailure(next.request, CmpError.CONNECT_FAIL_DISCONNECT());
                } else {
                    send(next.request, (IConnectSendListener) next.listener);
                }
            }
        }
        this.cacheActionList.clear();
        this.cacheActionList = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.aliyun.alink.linksdk.cmp.connect.alcs.AlcsConnect$6 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C09986 {

        /* renamed from: $SwitchMap$com$aliyun$alink$linksdk$cmp$connect$alcs$AlcsConnect$ActionEnum */
        static final /* synthetic */ int[] f1012x59a3ed23;

        static {
            int[] iArr = new int[ActionEnum.values().length];
            f1012x59a3ed23 = iArr;
            try {
                iArr[ActionEnum.Send.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1012x59a3ed23[ActionEnum.Subscribe.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1012x59a3ed23[ActionEnum.Unsubscribe.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
