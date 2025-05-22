package com.aliyun.alink.linksdk.cmp.connect.channel;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.channel.core.base.AResponse;
import com.aliyun.alink.linksdk.channel.core.base.IOnCallListener;
import com.aliyun.alink.linksdk.channel.core.persistent.IOnRrpcResponseHandle;
import com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener;
import com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentConnectState;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentInitParams;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentNet;
import com.aliyun.alink.linksdk.channel.core.persistent.event.IConnectionStateListener;
import com.aliyun.alink.linksdk.channel.core.persistent.event.IOnPushListener;
import com.aliyun.alink.linksdk.channel.core.persistent.event.PersistentEventDispatcher;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttConfigure;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttInitParams;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttSubscribeRequestParams;
import com.aliyun.alink.linksdk.cmp.api.CommonResource;
import com.aliyun.alink.linksdk.cmp.api.ResourceRequest;
import com.aliyun.alink.linksdk.cmp.core.base.AConnect;
import com.aliyun.alink.linksdk.cmp.core.base.AConnectConfig;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResource;
import com.aliyun.alink.linksdk.cmp.core.base.CmpError;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectAuth;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectInitListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectResourceRegister;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcHandle;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectUnscribeListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IResourceRequestListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IResourceResponseListener;
import com.aliyun.alink.linksdk.cmp.core.util.CallbackHelper;
import com.aliyun.alink.linksdk.cmp.core.util.ClassSwitchHelper;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PersistentConnect extends AConnect implements IConnectResourceRegister, IConnectAuth<Map<String, String>> {
    public static final String CONNECT_ID = "LINK_PERSISTENT";
    private static final String TAG = "PersistentConnect";
    private PersistentConnectConfig config;
    private Context context;
    private IConnectInitListener initListener;
    private MqttInitParams initParams;
    private Queue<String> pushMsgIdQueue;
    private ChannelStateListener channelStateListener = null;
    private DownstreamListener downstreamListener = null;
    private PersistentConnectInfo persistentConnectInfo = null;
    private int PUSH_MSGID_QUEUE_MAX = 100;

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void init(Context context, AConnectConfig aConnectConfig, IConnectInitListener iConnectInitListener) {
        ALog.m479d(TAG, "init(),call");
        if (context == null || aConnectConfig == null || !(aConnectConfig instanceof PersistentConnectConfig) || !aConnectConfig.checkVaild()) {
            ALog.m479d(TAG, "init()，params error");
            CallbackHelper.paramError(iConnectInitListener, "init, cxt or config is invalid");
            return;
        }
        this.connectId = CONNECT_ID;
        if (this.channelStateListener == null) {
            this.channelStateListener = new ChannelStateListener();
        }
        PersistentEventDispatcher.getInstance().registerOnTunnelStateListener(this.channelStateListener, true);
        if (this.downstreamListener == null) {
            this.downstreamListener = new DownstreamListener();
            PersistentEventDispatcher.getInstance().registerOnPushListener(this.downstreamListener, true);
        }
        if (PersistentNet.getInstance().getConnectState() == PersistentConnectState.CONNECTED) {
            ALog.m479d(TAG, "initChannel(), already connected");
            updateConnectState(ConnectState.CONNECTED);
            updateConnectInfo();
            if (iConnectInitListener != null) {
                iConnectInitListener.onSuccess();
                return;
            }
            return;
        }
        this.context = context;
        PersistentConnectConfig persistentConnectConfig = (PersistentConnectConfig) aConnectConfig;
        this.config = persistentConnectConfig;
        this.initListener = iConnectInitListener;
        if (persistentConnectConfig.isInitUpdateFlag) {
            if (iConnectInitListener != null) {
                iConnectInitListener.onFailure(null);
                return;
            }
            return;
        }
        updateConnectState(ConnectState.CONNECTING);
        if (!TextUtils.isEmpty(persistentConnectConfig.channelHost)) {
            ALog.m479d(TAG, "init(),update host env config");
            MqttConfigure.mqttHost = persistentConnectConfig.channelHost;
            MqttConfigure.isCheckRootCrt = persistentConnectConfig.isCheckChannelRootCrt;
            MqttConfigure.mqttRootCrtFile = persistentConnectConfig.channelRootCrtFile;
        }
        if (TextUtils.isEmpty(persistentConnectConfig.productKey) || TextUtils.isEmpty(persistentConnectConfig.deviceName)) {
            ALog.m479d(TAG, "init(),need prepare auth");
            if (iConnectInitListener != null) {
                iConnectInitListener.onPrepareAuth(this);
                return;
            }
            return;
        }
        MqttInitParams mqttInitParams = new MqttInitParams(persistentConnectConfig.productKey, persistentConnectConfig.productSecret, persistentConnectConfig.deviceName, persistentConnectConfig.deviceSecret, persistentConnectConfig.secureMode);
        this.initParams = mqttInitParams;
        mqttInitParams.receiveOfflineMsg = persistentConnectConfig.receiveOfflineMsg;
        initChannel();
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void onDestroy() {
        ALog.m479d(TAG, "onDestroy()");
        if (this.channelStateListener != null) {
            PersistentEventDispatcher.getInstance().unregisterOnTunnelStateListener(this.channelStateListener);
            this.channelStateListener = null;
        }
        if (this.downstreamListener != null) {
            PersistentEventDispatcher.getInstance().unregisterOnPushListener(this.downstreamListener);
            this.downstreamListener = null;
        }
        PersistentNet.getInstance().destroy();
        updateConnectState(ConnectState.DISCONNECTED);
        this.connectInfo = null;
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void setNotifyListener(IConnectNotifyListener iConnectNotifyListener) {
        ALog.m479d(TAG, "setNotifyListener(), listener = " + iConnectNotifyListener);
        if (this.downstreamListener == null) {
            this.downstreamListener = new DownstreamListener();
            PersistentEventDispatcher.getInstance().registerOnPushListener(this.downstreamListener, true);
        }
        super.setNotifyListener(iConnectNotifyListener);
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectAuth
    public void onAuth(Map<String, String> map) {
        ALog.m479d(TAG, "auth()");
        if (map == null || !map.containsKey("PK") || !map.containsKey("DN") || !map.containsKey("DS")) {
            IConnectInitListener iConnectInitListener = this.initListener;
            if (iConnectInitListener != null) {
                iConnectInitListener.onFailure(CmpError.CONNECT_AUTH_INFO_ERROR());
                this.initListener = null;
                return;
            }
            return;
        }
        ALog.m479d(TAG, "onAuth(), connect");
        this.initParams = new MqttInitParams(map.get("PK"), map.get("DN"), map.get("DS"));
        initChannel();
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectAuth
    public void onPrepareAuthFail(AError aError) {
        ALog.m479d(TAG, "onPrepareFail()");
        IConnectInitListener iConnectInitListener = this.initListener;
        if (iConnectInitListener != null) {
            iConnectInitListener.onFailure(aError);
            this.initListener = null;
        }
    }

    private void initChannel() {
        ALog.m479d(TAG, "initChannel()");
        if (PersistentNet.getInstance().getConnectState() == PersistentConnectState.CONNECTED) {
            ALog.m479d(TAG, "initChannel(), already connected");
            updateConnectState(ConnectState.CONNECTED);
            updateConnectInfo();
            IConnectInitListener iConnectInitListener = this.initListener;
            if (iConnectInitListener != null) {
                iConnectInitListener.onSuccess();
                return;
            }
            return;
        }
        PersistentNet.getInstance().init(this.context, this.initParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectInfo() {
        ALog.m479d(TAG, "updateConnectInfo()");
        PersistentInitParams initParams = PersistentNet.getInstance().getInitParams();
        if (initParams == null || !(initParams instanceof MqttInitParams)) {
            return;
        }
        if (this.persistentConnectInfo == null) {
            this.persistentConnectInfo = new PersistentConnectInfo();
        }
        MqttInitParams mqttInitParams = (MqttInitParams) initParams;
        this.persistentConnectInfo.productKey = mqttInitParams.productKey;
        this.persistentConnectInfo.deviceName = mqttInitParams.deviceName;
        this.connectInfo = this.persistentConnectInfo;
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void send(final ARequest aRequest, final IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "send()");
        if (aRequest instanceof MqttPublishRequest) {
            PersistentNet.getInstance().asyncSend(ClassSwitchHelper.mqttPubReqCmpToChannel((MqttPublishRequest) aRequest), new IOnCallListener() { // from class: com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnect.1
                @Override // com.aliyun.alink.linksdk.channel.core.base.IOnCallListener
                public boolean needUISafety() {
                    return true;
                }

                @Override // com.aliyun.alink.linksdk.channel.core.base.IOnCallListener
                public void onSuccess(com.aliyun.alink.linksdk.channel.core.base.ARequest aRequest2, AResponse aResponse) {
                    IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                    if (iConnectSendListener2 == null) {
                        return;
                    }
                    iConnectSendListener2.onResponse(aRequest, ClassSwitchHelper.aRspChannelToCmp(aResponse));
                }

                @Override // com.aliyun.alink.linksdk.channel.core.base.IOnCallListener
                public void onFailed(com.aliyun.alink.linksdk.channel.core.base.ARequest aRequest2, com.aliyun.alink.linksdk.channel.core.base.AError aError) {
                    IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                    if (iConnectSendListener2 == null) {
                        return;
                    }
                    iConnectSendListener2.onFailure(aRequest, ClassSwitchHelper.aErrorChannelToCmp(aError));
                }
            });
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void subscribe(ARequest aRequest, final IConnectSubscribeListener iConnectSubscribeListener) {
        ALog.m479d(TAG, "subscribe()");
        if (aRequest instanceof MqttSubscribeRequest) {
            MqttSubscribeRequestParams mqttSubscribeRequestParams = new MqttSubscribeRequestParams();
            MqttSubscribeRequest mqttSubscribeRequest = (MqttSubscribeRequest) aRequest;
            mqttSubscribeRequestParams.qos = mqttSubscribeRequest.qos;
            PersistentNet.getInstance().subscribe(mqttSubscribeRequest.topic, mqttSubscribeRequestParams, new IOnSubscribeListener() { // from class: com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnect.2
                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener
                public boolean needUISafety() {
                    return true;
                }

                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener
                public void onSuccess(String str) {
                    IConnectSubscribeListener iConnectSubscribeListener2 = iConnectSubscribeListener;
                    if (iConnectSubscribeListener2 != null) {
                        iConnectSubscribeListener2.onSuccess();
                    }
                }

                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener
                public void onFailed(String str, com.aliyun.alink.linksdk.channel.core.base.AError aError) {
                    IConnectSubscribeListener iConnectSubscribeListener2 = iConnectSubscribeListener;
                    if (iConnectSubscribeListener2 != null) {
                        iConnectSubscribeListener2.onFailure(ClassSwitchHelper.aErrorChannelToCmp(aError));
                    }
                }
            });
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void unsubscribe(ARequest aRequest, final IConnectUnscribeListener iConnectUnscribeListener) {
        ALog.m479d(TAG, "unsubscribe()");
        if (aRequest instanceof MqttSubscribeRequest) {
            PersistentNet.getInstance().unSubscribe(((MqttSubscribeRequest) aRequest).topic, new IOnSubscribeListener() { // from class: com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnect.3
                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener
                public boolean needUISafety() {
                    return true;
                }

                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener
                public void onSuccess(String str) {
                    IConnectUnscribeListener iConnectUnscribeListener2 = iConnectUnscribeListener;
                    if (iConnectUnscribeListener2 != null) {
                        iConnectUnscribeListener2.onSuccess();
                    }
                }

                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeListener
                public void onFailed(String str, com.aliyun.alink.linksdk.channel.core.base.AError aError) {
                    IConnectUnscribeListener iConnectUnscribeListener2 = iConnectUnscribeListener;
                    if (iConnectUnscribeListener2 != null) {
                        iConnectUnscribeListener2.onFailure(ClassSwitchHelper.aErrorChannelToCmp(aError));
                    }
                }
            });
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnect
    public void subscribeRrpc(final ARequest aRequest, final IConnectRrpcListener iConnectRrpcListener) {
        ALog.m479d(TAG, "subscribeRrpc()");
        if (aRequest instanceof MqttRrpcRegisterRequest) {
            PersistentNet.getInstance().subscribeRrpc(((MqttRrpcRegisterRequest) aRequest).topic, new IOnSubscribeRrpcListener() { // from class: com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnect.4
                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
                public boolean needUISafety() {
                    return true;
                }

                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
                public void onSubscribeSuccess(String str) {
                    IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                    if (iConnectRrpcListener2 != null) {
                        iConnectRrpcListener2.onSubscribeSuccess(aRequest);
                    }
                }

                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
                public void onSubscribeFailed(String str, com.aliyun.alink.linksdk.channel.core.base.AError aError) {
                    IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                    if (iConnectRrpcListener2 != null) {
                        iConnectRrpcListener2.onSubscribeFailed(aRequest, ClassSwitchHelper.aErrorChannelToCmp(aError));
                    }
                }

                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
                public void onReceived(String str, com.aliyun.alink.linksdk.channel.core.persistent.PersistentRequest persistentRequest, final IOnRrpcResponseHandle iOnRrpcResponseHandle) {
                    if (iConnectRrpcListener != null) {
                        MqttRrpcRequest mqttRrpcRequest = new MqttRrpcRequest();
                        mqttRrpcRequest.topic = str;
                        mqttRrpcRequest.payloadObj = persistentRequest.payloadObj;
                        if (!TextUtils.isEmpty(((MqttRrpcRegisterRequest) aRequest).replyTopic)) {
                            mqttRrpcRequest.replyTopic = ((MqttRrpcRegisterRequest) aRequest).replyTopic;
                        } else if (persistentRequest instanceof com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttRrpcRequest) {
                            mqttRrpcRequest.replyTopic = ((com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttRrpcRequest) persistentRequest).replyTopic;
                        }
                        iConnectRrpcListener.onReceived(mqttRrpcRequest, new IConnectRrpcHandle() { // from class: com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnect.4.1
                            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcHandle
                            public void onRrpcResponse(String str2, com.aliyun.alink.linksdk.cmp.core.base.AResponse aResponse) {
                                iOnRrpcResponseHandle.onRrpcResponse(str2, ClassSwitchHelper.aRspCmpToChannel(aResponse));
                            }
                        });
                    }
                }

                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
                public void onResponseSuccess(String str) {
                    IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                    if (iConnectRrpcListener2 != null) {
                        iConnectRrpcListener2.onResponseSuccess(aRequest);
                    }
                }

                @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
                public void onResponseFailed(String str, com.aliyun.alink.linksdk.channel.core.base.AError aError) {
                    IConnectRrpcListener iConnectRrpcListener2 = iConnectRrpcListener;
                    if (iConnectRrpcListener2 != null) {
                        iConnectRrpcListener2.onResponseFailed(aRequest, ClassSwitchHelper.aErrorChannelToCmp(aError));
                    }
                }
            });
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private class ChannelStateListener implements IConnectionStateListener {
        private ChannelStateListener() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v8, types: [com.aliyun.alink.linksdk.tools.AError] */
        @Override // com.aliyun.alink.linksdk.channel.core.persistent.event.IConnectionStateListener
        public void onConnectFail(String str) {
            ALog.m479d(PersistentConnect.TAG, "onConnectFail()");
            if (PersistentConnect.this.initListener != null) {
                CmpError MQTT_CONNECT_FAIL = CmpError.MQTT_CONNECT_FAIL();
                MQTT_CONNECT_FAIL.setSubMsg(str);
                try {
                    MQTT_CONNECT_FAIL = (AError) JSON.parseObject(str, AError.class);
                } catch (Exception e) {
                    ALog.m479d(PersistentConnect.TAG, "onConnectFail() e:" + e.toString());
                }
                PersistentConnect.this.initListener.onFailure(MQTT_CONNECT_FAIL);
            }
            PersistentConnect.this.updateConnectState(ConnectState.CONNECTFAIL);
        }

        @Override // com.aliyun.alink.linksdk.channel.core.persistent.event.IConnectionStateListener
        public void onConnected() {
            ALog.m479d(PersistentConnect.TAG, "onConnected()");
            if (PersistentConnect.this.initListener != null) {
                PersistentConnect.this.initListener.onSuccess();
                PersistentConnect.this.initListener = null;
            }
            PersistentConnect.this.updateConnectState(ConnectState.CONNECTED);
            PersistentConnect.this.updateConnectInfo();
        }

        @Override // com.aliyun.alink.linksdk.channel.core.persistent.event.IConnectionStateListener
        public void onDisconnect() {
            ALog.m479d(PersistentConnect.TAG, "onDisconnect()");
            PersistentConnect.this.updateConnectState(ConnectState.DISCONNECTED);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private class DownstreamListener implements IOnPushListener {
        private DownstreamListener() {
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0052  */
        @Override // com.aliyun.alink.linksdk.channel.core.persistent.event.IOnPushListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onCommand(String str, byte[] bArr) {
            String str2;
            String msgId;
            ALog.m479d(PersistentConnect.TAG, "onCommand(),topic = " + str);
            if (PersistentConnect.this.notifyListener == null) {
                return;
            }
            String str3 = "";
            try {
                str2 = new String(bArr, "UTF-8");
                try {
                    ALog.m479d(PersistentConnect.TAG, "onCommand(),raw data = " + Arrays.toString(bArr));
                } catch (Exception unused) {
                    str3 = str2;
                    ALog.m479d(PersistentConnect.TAG, "onCommand(), to data error");
                    str2 = str3;
                    msgId = getMsgId(str2);
                    if (!TextUtils.isEmpty(msgId)) {
                    }
                    AMessage aMessage = new AMessage();
                    aMessage.data = bArr;
                    PersistentConnect.this.notifyListener.onNotify(PersistentConnect.CONNECT_ID, str, aMessage);
                }
            } catch (Exception unused2) {
            }
            msgId = getMsgId(str2);
            if (!TextUtils.isEmpty(msgId)) {
                if (PersistentConnect.this.pushMsgIdQueue == null) {
                    PersistentConnect.this.pushMsgIdQueue = new LinkedList();
                }
                String str4 = str + "_" + msgId;
                if (PersistentConnect.this.pushMsgIdQueue.contains(str4)) {
                    return;
                }
                if (PersistentConnect.this.pushMsgIdQueue.size() < PersistentConnect.this.PUSH_MSGID_QUEUE_MAX) {
                    PersistentConnect.this.pushMsgIdQueue.offer(str4);
                } else {
                    PersistentConnect.this.pushMsgIdQueue.poll();
                    PersistentConnect.this.pushMsgIdQueue.offer(str4);
                }
            }
            AMessage aMessage2 = new AMessage();
            aMessage2.data = bArr;
            PersistentConnect.this.notifyListener.onNotify(PersistentConnect.CONNECT_ID, str, aMessage2);
        }

        @Override // com.aliyun.alink.linksdk.channel.core.persistent.event.IOnPushListener
        public boolean shouldHandle(String str) {
            ALog.m479d(PersistentConnect.TAG, "shouldHandle(),topic =" + str);
            if (PersistentConnect.this.notifyListener == null) {
                return false;
            }
            return PersistentConnect.this.notifyListener.shouldHandle(PersistentConnect.CONNECT_ID, str);
        }

        private String getShortTopic(String str) {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            String str2 = "/sys/" + PersistentConnect.this.initParams.productKey + "/" + PersistentConnect.this.initParams.deviceName;
            if (str.contains(str2)) {
                str = str.replace(str2, "");
            }
            return str.contains("/app/down") ? str.replace("/app/down", "") : str;
        }

        private String getMsgId(String str) {
            try {
                JSONObject parseObject = JSONObject.parseObject(str);
                if (parseObject == null || !parseObject.containsKey("id")) {
                    return null;
                }
                return parseObject.getString("id");
            } catch (Exception e) {
                ALog.m479d(PersistentConnect.TAG, "getMsgId(),error = " + e.toString());
                return null;
            }
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectResourceRegister
    public void registerResource(AResource aResource, IResourceRequestListener iResourceRequestListener) {
        ALog.m479d(TAG, "registerResource()");
        boolean z = aResource instanceof MqttResource;
        if (!z && !(aResource instanceof CommonResource)) {
            if (iResourceRequestListener != null) {
                iResourceRequestListener.onFailure(CmpError.UNSUPPORT());
            }
        } else {
            MqttResource mqttResource = null;
            if (z) {
                mqttResource = (MqttResource) aResource;
            } else if (aResource instanceof CommonResource) {
                mqttResource = ClassSwitchHelper.commonResToMqttRes((CommonResource) aResource);
            }
            PersistentNet.getInstance().subscribeRrpc(mqttResource.topic, new SubscribeRrpcListener(aResource, iResourceRequestListener));
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectResourceRegister
    public void unregisterResource(AResource aResource, IBaseListener iBaseListener) {
        ALog.m479d(TAG, "unregisterResource()");
        if (iBaseListener != null) {
            iBaseListener.onFailure(CmpError.UNSUPPORT());
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectResourceRegister
    public void publishResource(AResource aResource, final IBaseListener iBaseListener) {
        ALog.m479d(TAG, "publishResource()");
        boolean z = aResource instanceof MqttResource;
        if (!z && !(aResource instanceof CommonResource)) {
            if (iBaseListener != null) {
                iBaseListener.onFailure(CmpError.UNSUPPORT());
                return;
            }
            return;
        }
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.isRPC = false;
        if (z) {
            MqttResource mqttResource = (MqttResource) aResource;
            mqttPublishRequest.topic = mqttResource.topic;
            mqttPublishRequest.payloadObj = mqttResource.content;
        } else if (aResource instanceof CommonResource) {
            CommonResource commonResource = (CommonResource) aResource;
            mqttPublishRequest.topic = commonResource.topic;
            mqttPublishRequest.payloadObj = commonResource.payload;
        }
        ALog.m479d(TAG, "send()");
        send(mqttPublishRequest, new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnect.5
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, com.aliyun.alink.linksdk.cmp.core.base.AResponse aResponse) {
                IBaseListener iBaseListener2 = iBaseListener;
                if (iBaseListener2 != null) {
                    iBaseListener2.onSuccess();
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                IBaseListener iBaseListener2 = iBaseListener;
                if (iBaseListener2 != null) {
                    iBaseListener2.onFailure(aError);
                }
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private class SubscribeRrpcListener implements IOnSubscribeRrpcListener {
        private IResourceRequestListener listener;
        private AResource resource;

        @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
        public boolean needUISafety() {
            return true;
        }

        public SubscribeRrpcListener(AResource aResource, IResourceRequestListener iResourceRequestListener) {
            this.resource = aResource;
            this.listener = iResourceRequestListener;
        }

        @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
        public void onSubscribeSuccess(String str) {
            ALog.m479d(PersistentConnect.TAG, "SubscribeRrpcListener, onSubscribeSuccess(), topic = " + str);
            IResourceRequestListener iResourceRequestListener = this.listener;
            if (iResourceRequestListener != null) {
                iResourceRequestListener.onSuccess();
            }
        }

        @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
        public void onSubscribeFailed(String str, com.aliyun.alink.linksdk.channel.core.base.AError aError) {
            ALog.m479d(PersistentConnect.TAG, "SubscribeRrpcListener, onSubscribeFailed(), topic = " + str);
            IResourceRequestListener iResourceRequestListener = this.listener;
            if (iResourceRequestListener != null) {
                iResourceRequestListener.onFailure(ClassSwitchHelper.aErrorChannelToCmp(aError));
            }
        }

        @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
        public void onReceived(String str, com.aliyun.alink.linksdk.channel.core.persistent.PersistentRequest persistentRequest, final IOnRrpcResponseHandle iOnRrpcResponseHandle) {
            ALog.m479d(PersistentConnect.TAG, "SubscribeRrpcListener, onReceived(), topic = " + str);
            if (this.listener != null) {
                ResourceRequest resourceRequest = new ResourceRequest();
                resourceRequest.topic = str;
                resourceRequest.payloadObj = persistentRequest.payloadObj;
                resourceRequest.context = persistentRequest;
                this.listener.onHandleRequest(this.resource, resourceRequest, new IResourceResponseListener() { // from class: com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnect.SubscribeRrpcListener.1
                    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IResourceResponseListener
                    public void onResponse(AResource aResource, ResourceRequest resourceRequest2, Object obj) {
                        String str2;
                        ALog.m479d(PersistentConnect.TAG, "SubscribeRrpcListener, onReceived(),onResponse() call");
                        if (aResource != null) {
                            boolean z = aResource instanceof MqttResource;
                            if ((z || (aResource instanceof CommonResource)) && obj != null && (obj instanceof com.aliyun.alink.linksdk.cmp.core.base.AResponse)) {
                                if (z) {
                                    str2 = ((MqttResource) aResource).replyTopic;
                                } else {
                                    str2 = aResource instanceof CommonResource ? ((CommonResource) aResource).replyTopic : "";
                                }
                                ALog.m479d(PersistentConnect.TAG, "SubscribeRrpcListener, onReceived(), onResponse(), rrpc rsp, replytopic = " + str2);
                                iOnRrpcResponseHandle.onRrpcResponse(str2, ClassSwitchHelper.aRspCmpToChannel((com.aliyun.alink.linksdk.cmp.core.base.AResponse) obj));
                            }
                        }
                    }
                });
            }
        }

        @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
        public void onResponseSuccess(String str) {
            ALog.m479d(PersistentConnect.TAG, "SubscribeRrpcListener, onResponseSuccess(), topic = " + str);
            IResourceRequestListener iResourceRequestListener = this.listener;
            if (iResourceRequestListener != null) {
                iResourceRequestListener.onSuccess();
            }
        }

        @Override // com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener
        public void onResponseFailed(String str, com.aliyun.alink.linksdk.channel.core.base.AError aError) {
            ALog.m479d(PersistentConnect.TAG, "SubscribeRrpcListener, onResponseFailed(), topic = " + str);
            IResourceRequestListener iResourceRequestListener = this.listener;
            if (iResourceRequestListener != null) {
                iResourceRequestListener.onFailure(ClassSwitchHelper.aErrorChannelToCmp(aError));
            }
        }
    }
}
