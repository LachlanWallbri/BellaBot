package com.pudutech.pdmqtt.client;

import android.content.Context;
import androidx.collection.LruCache;
import com.pudutech.pdmqtt.ActionCallback;
import com.pudutech.pdmqtt.GetClientCallback;
import com.pudutech.pdmqtt.OnMessageChangeListener;
import com.pudutech.pdmqtt.OnPublishCallback;
import com.pudutech.pdmqtt.config.BaseKt;
import com.pudutech.pdmqtt.config.BaseMqttConfig;
import com.pudutech.pdmqtt.service.MqttManagerImplKt;
import com.pudutech.pdmqtt.service.MyIMqttActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: RemoteMqttClientImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0002),\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u00107\u001a\u00020 2\u0016\u00108\u001a\u0012\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001ej\u0002`!H\u0016J\u0010\u00109\u001a\u00020 2\u0006\u00108\u001a\u00020#H\u0016J\b\u0010:\u001a\u00020\u0016H\u0002J\b\u0010;\u001a\u00020\u0005H\u0016J\u0012\u0010<\u001a\u00020 2\b\u0010=\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010>\u001a\u00020\bH\u0002J\b\u0010?\u001a\u00020\u0016H\u0016J\b\u0010@\u001a\u00020\u0016H\u0002J\u0010\u0010A\u001a\u00020 2\u0006\u0010B\u001a\u00020\u001fH\u0002J\"\u0010C\u001a\u00020 2\u0006\u0010D\u001a\u00020\u000b2\u0006\u0010E\u001a\u00020\u000b2\b\u0010=\u001a\u0004\u0018\u00010FH\u0016J*\u0010C\u001a\u00020 2\u0006\u0010D\u001a\u00020\u000b2\u0006\u0010E\u001a\u00020\u000b2\u0006\u0010G\u001a\u00020H2\b\u0010=\u001a\u0004\u0018\u00010FH\u0016J\u0006\u0010I\u001a\u00020 J\b\u0010J\u001a\u00020 H\u0002J\b\u0010K\u001a\u00020 H\u0002J\b\u00102\u001a\u00020 H\u0016J \u0010L\u001a\u00020 2\u0016\u00108\u001a\u0012\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001ej\u0002`!H\u0016J\u0010\u0010M\u001a\u00020 2\u0006\u00108\u001a\u00020#H\u0016J\u001e\u0010N\u001a\u00020 2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u000b0P2\u0006\u0010=\u001a\u00020QH\u0016J\u0016\u0010R\u001a\u00020 2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u000b0PH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001ej\u0002`!0\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0004\n\u0002\u0010*R\u0010\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0004\n\u0002\u0010-R\u000e\u0010.\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00102\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0019\"\u0004\b4\u0010\u001bR\u000e\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006S"}, m3961d2 = {"Lcom/pudutech/pdmqtt/client/RemoteMqttClientImpl;", "Lcom/pudutech/pdmqtt/client/IMqttClient;", "context", "Landroid/content/Context;", "config", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "(Landroid/content/Context;Lcom/pudutech/pdmqtt/config/BaseMqttConfig;)V", "DEFAULT_DELAY_TIME", "", "MAX_DELAY_TIME", "TAG", "", "getTAG", "()Ljava/lang/String;", "getConfig", "()Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "connectCallback", "Lcom/pudutech/pdmqtt/GetClientCallback;", "getContext", "()Landroid/content/Context;", "crtDelayTime", ES6Iterator.VALUE_PROPERTY, "", "enable", "getEnable", "()Z", "setEnable", "(Z)V", "mOnConnectStateChangeList", "", "Lkotlin/Function1;", "Lcom/pudutech/pdmqtt/client/MqttConnectState;", "", "Lcom/pudutech/pdmqtt/client/OnConnectStateChangeListener;", "mOnMessageChangeListenerList", "Lcom/pudutech/pdmqtt/OnMessageChangeListener;", "mSendMessageCache", "Landroidx/collection/LruCache;", "mqttClient", "Lorg/eclipse/paho/android/service/MqttAndroidClient;", "mqttClientCallback", "com/pudutech/pdmqtt/client/RemoteMqttClientImpl$mqttClientCallback$1", "Lcom/pudutech/pdmqtt/client/RemoteMqttClientImpl$mqttClientCallback$1;", "mqttConnectCallback", "com/pudutech/pdmqtt/client/RemoteMqttClientImpl$mqttConnectCallback$1", "Lcom/pudutech/pdmqtt/client/RemoteMqttClientImpl$mqttConnectCallback$1;", "mqttConnectOptions", "Lorg/eclipse/paho/client/mqttv3/MqttConnectOptions;", "reconnectJob", "Lkotlinx/coroutines/Job;", "release", "getRelease", "setRelease", "scope", "Lkotlinx/coroutines/CoroutineScope;", "addOnConnectStateChangeLis", "lis", "addOnMessageChangeListener", "canUse", "clientConfig", MqttServiceConstants.CONNECT_ACTION, "callback", "getDelayTimeMills", "isConnect", "needConnect", "notifyConnectState", "state", "publish", "topic", MqttServiceConstants.PAYLOAD, "Lcom/pudutech/pdmqtt/OnPublishCallback;", MqttServiceConstants.QOS, "", "realConnect", "realReconnect", "reconnect", "removeOnConnectStateChangeLis", "removeOnMessageChangeListener", MqttServiceConstants.SUBSCRIBE_ACTION, "topicSet", "", "Lcom/pudutech/pdmqtt/ActionCallback;", MqttServiceConstants.UNSUBSCRIBE_ACTION, "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RemoteMqttClientImpl implements IMqttClient {
    private final long DEFAULT_DELAY_TIME;
    private final long MAX_DELAY_TIME;
    private final String TAG;
    private final BaseMqttConfig config;
    private GetClientCallback connectCallback;
    private final Context context;
    private long crtDelayTime;
    private boolean enable;
    private List<Function1<MqttConnectState, Unit>> mOnConnectStateChangeList;
    private final List<OnMessageChangeListener> mOnMessageChangeListenerList;
    private final LruCache<String, String> mSendMessageCache;
    private MqttAndroidClient mqttClient;
    private final RemoteMqttClientImpl$mqttClientCallback$1 mqttClientCallback;
    private final RemoteMqttClientImpl$mqttConnectCallback$1 mqttConnectCallback;
    private MqttConnectOptions mqttConnectOptions;
    private Job reconnectJob;
    private boolean release;
    private final CoroutineScope scope;

    /* JADX WARN: Type inference failed for: r4v24, types: [com.pudutech.pdmqtt.client.RemoteMqttClientImpl$mqttClientCallback$1] */
    /* JADX WARN: Type inference failed for: r4v25, types: [com.pudutech.pdmqtt.client.RemoteMqttClientImpl$mqttConnectCallback$1] */
    public RemoteMqttClientImpl(Context context, BaseMqttConfig config) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(config, "config");
        this.context = context;
        this.config = config;
        this.TAG = "RemoteMqttClientImpl";
        this.mqttConnectOptions = new MqttConnectOptions();
        this.mOnMessageChangeListenerList = new ArrayList();
        this.mOnConnectStateChangeList = new ArrayList();
        this.mSendMessageCache = new LruCache<>(20);
        this.mqttConnectOptions.setAutomaticReconnect(false);
        this.mqttConnectOptions.setCleanSession(false);
        try {
            this.mqttConnectOptions.getMaxInflight();
            this.mqttConnectOptions.setSocketFactory(MqttSocketFactory.INSTANCE.createSocketFactory(this.config.getCertificateInfo()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String username = this.config.username();
        if (username.length() > 0) {
            this.mqttConnectOptions.setUserName(username);
        }
        String password = this.config.password();
        if (password.length() > 0) {
            MqttConnectOptions mqttConnectOptions = this.mqttConnectOptions;
            if (password == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            char[] charArray = password.toCharArray();
            Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
            mqttConnectOptions.setPassword(charArray);
        }
        this.mqttConnectOptions.setMaxInflight(50);
        MqttManagerImplKt.remoteLog(this.TAG, BaseKt.toStr(this.config));
        this.mqttClient = new MqttAndroidClient(this.context, this.config.serverURI(), this.config.getDeviceName());
        this.enable = true;
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        this.DEFAULT_DELAY_TIME = 10000L;
        this.MAX_DELAY_TIME = 60000L;
        this.crtDelayTime = this.DEFAULT_DELAY_TIME;
        this.mqttClientCallback = new MqttCallbackExtended() { // from class: com.pudutech.pdmqtt.client.RemoteMqttClientImpl$mqttClientCallback$1
            @Override // org.eclipse.paho.client.mqttv3.MqttCallbackExtended
            public void connectComplete(boolean reconnect, String serverURI) {
                Intrinsics.checkParameterIsNotNull(serverURI, "serverURI");
                MqttManagerImplKt.remoteLog(RemoteMqttClientImpl.this.getTAG(), "mqttClientCallback.connectComplete " + reconnect + ' ' + serverURI);
                RemoteMqttClientImpl.this.notifyConnectState(MqttConnectState.CONNECTED);
            }

            @Override // org.eclipse.paho.client.mqttv3.MqttCallback
            public void connectionLost(Throwable cause) {
                MqttManagerImplKt.remoteLog(RemoteMqttClientImpl.this.getTAG(), "mqttClientCallback.connectionLost " + cause);
                RemoteMqttClientImpl.this.notifyConnectState(MqttConnectState.DISCONNECTED);
                RemoteMqttClientImpl.this.reconnect();
            }

            @Override // org.eclipse.paho.client.mqttv3.MqttCallback
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                boolean canUse;
                List<OnMessageChangeListener> list;
                List list2;
                Intrinsics.checkParameterIsNotNull(topic, "topic");
                Intrinsics.checkParameterIsNotNull(message, "message");
                MqttManagerImplKt.remoteLog(RemoteMqttClientImpl.this.getTAG(), "mqttClientCallback.messageArrived " + topic + ' ' + message);
                try {
                    canUse = RemoteMqttClientImpl.this.canUse();
                    if (canUse) {
                        byte[] payload = message.getPayload();
                        Intrinsics.checkExpressionValueIsNotNull(payload, "message.payload");
                        String str = new String(payload, Charsets.UTF_8);
                        MqttManagerImplKt.remoteLog(RemoteMqttClientImpl.this.getTAG(), "messageArrived topic = " + topic + "; message = " + str + "; ");
                        ArrayList<OnMessageChangeListener> arrayList = new ArrayList();
                        list = RemoteMqttClientImpl.this.mOnMessageChangeListenerList;
                        for (OnMessageChangeListener onMessageChangeListener : list) {
                            try {
                                onMessageChangeListener.onReceive(topic, str);
                            } catch (Exception e2) {
                                MqttManagerImplKt.remoteLog(RemoteMqttClientImpl.this.getTAG(), "messageArrived.notify.error " + topic + ' ' + e2);
                                arrayList.add(onMessageChangeListener);
                            }
                        }
                        for (OnMessageChangeListener onMessageChangeListener2 : arrayList) {
                            list2 = RemoteMqttClientImpl.this.mOnMessageChangeListenerList;
                            list2.remove(onMessageChangeListener2);
                        }
                    }
                } catch (Exception e3) {
                    MqttManagerImplKt.remoteLog(RemoteMqttClientImpl.this.getTAG(), "messageArrived.error " + topic + ' ' + e3);
                }
            }

            @Override // org.eclipse.paho.client.mqttv3.MqttCallback
            public void deliveryComplete(IMqttDeliveryToken token) {
                LruCache lruCache;
                List list;
                MqttMessage message;
                String mqttMessage = (token == null || (message = token.getMessage()) == null) ? null : message.toString();
                MqttManagerImplKt.remoteLog(RemoteMqttClientImpl.this.getTAG(), "mqttClientCallback.deliveryComplete " + mqttMessage);
                if (mqttMessage != null) {
                    lruCache = RemoteMqttClientImpl.this.mSendMessageCache;
                    String str = (String) lruCache.get(mqttMessage);
                    if (str != null) {
                        Intrinsics.checkExpressionValueIsNotNull(str, "mSendMessageCache[payload] ?: return");
                        list = RemoteMqttClientImpl.this.mOnMessageChangeListenerList;
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            ((OnMessageChangeListener) it.next()).onSend(str, mqttMessage);
                        }
                    }
                }
            }
        };
        this.mqttConnectCallback = new IMqttActionListener() { // from class: com.pudutech.pdmqtt.client.RemoteMqttClientImpl$mqttConnectCallback$1
            @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
            public void onSuccess(IMqttToken asyncActionToken) {
                GetClientCallback getClientCallback;
                Intrinsics.checkParameterIsNotNull(asyncActionToken, "asyncActionToken");
                MqttManagerImplKt.remoteLog(RemoteMqttClientImpl.this.getTAG(), "connect.onSuccess " + asyncActionToken);
                getClientCallback = RemoteMqttClientImpl.this.connectCallback;
                if (getClientCallback != null) {
                    getClientCallback.onSuccess(RemoteMqttClientImpl.this.getConfig().key());
                }
                RemoteMqttClientImpl.this.connectCallback = (GetClientCallback) null;
            }

            @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                GetClientCallback getClientCallback;
                Intrinsics.checkParameterIsNotNull(asyncActionToken, "asyncActionToken");
                Intrinsics.checkParameterIsNotNull(exception, "exception");
                MqttManagerImplKt.remoteLog(RemoteMqttClientImpl.this.getTAG(), "connect.onFailure " + asyncActionToken + ' ' + exception);
                getClientCallback = RemoteMqttClientImpl.this.connectCallback;
                if (getClientCallback != null) {
                    getClientCallback.onError(RemoteMqttClientImpl.this.getConfig().key(), -1, exception.getMessage());
                }
                RemoteMqttClientImpl.this.connectCallback = (GetClientCallback) null;
            }
        };
    }

    public final BaseMqttConfig getConfig() {
        return this.config;
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void connect(GetClientCallback callback) {
        this.connectCallback = callback;
        realConnect();
    }

    public final void realConnect() {
        MqttManagerImplKt.remoteLog(this.TAG, "realReconnect");
        MqttAndroidClient mqttAndroidClient = this.mqttClient;
        if (mqttAndroidClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mqttClient");
        }
        if (mqttAndroidClient.isConnected()) {
            return;
        }
        MqttAndroidClient mqttAndroidClient2 = this.mqttClient;
        if (mqttAndroidClient2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mqttClient");
        }
        mqttAndroidClient2.setCallback(this.mqttClientCallback);
        notifyConnectState(MqttConnectState.CONNECTING);
        MqttAndroidClient mqttAndroidClient3 = this.mqttClient;
        if (mqttAndroidClient3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mqttClient");
        }
        mqttAndroidClient3.connect(this.mqttConnectOptions, null, this.mqttConnectCallback);
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void release() {
        this.release = true;
        Job job = this.reconnectJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        MqttAndroidClient mqttAndroidClient = this.mqttClient;
        if (mqttAndroidClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mqttClient");
        }
        mqttAndroidClient.disconnect();
        this.mOnMessageChangeListenerList.clear();
        this.mOnConnectStateChangeList.clear();
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    /* renamed from: clientConfig, reason: from getter */
    public BaseMqttConfig getConfig() {
        return this.config;
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public boolean isConnect() {
        MqttAndroidClient mqttAndroidClient = this.mqttClient;
        if (mqttAndroidClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mqttClient");
        }
        return mqttAndroidClient.isConnected();
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void addOnMessageChangeListener(OnMessageChangeListener lis) {
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        this.mOnMessageChangeListenerList.add(lis);
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void removeOnMessageChangeListener(OnMessageChangeListener lis) {
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        this.mOnMessageChangeListenerList.remove(lis);
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void publish(String topic, String payload, OnPublishCallback callback) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        publish(topic, payload, 0, callback);
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void publish(String topic, String payload, int qos, OnPublishCallback callback) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        MqttManagerImplKt.remoteLog(this.TAG, "publish " + topic + ' ' + payload + ' ' + isConnect());
        this.mSendMessageCache.put(payload, topic);
        MqttAndroidClient mqttAndroidClient = this.mqttClient;
        if (mqttAndroidClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mqttClient");
        }
        byte[] bytes = payload.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        MqttMessage mqttMessage = new MqttMessage(bytes);
        mqttMessage.setQos(qos);
        mqttAndroidClient.publish(topic, mqttMessage, (Object) null, new MyIMqttActionListener(topic, payload, callback));
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void subscribe(Set<String> topicSet, final ActionCallback callback) {
        Intrinsics.checkParameterIsNotNull(topicSet, "topicSet");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        MqttManagerImplKt.remoteLog(this.TAG, "subscribe " + topicSet);
        MqttAndroidClient mqttAndroidClient = this.mqttClient;
        if (mqttAndroidClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mqttClient");
        }
        Object[] array = topicSet.toArray(new String[0]);
        if (array != null) {
            String[] strArr = (String[]) array;
            int size = topicSet.size();
            int[] iArr = new int[size];
            for (int i = 0; i < size; i++) {
                iArr[i] = 0;
            }
            mqttAndroidClient.subscribe(strArr, iArr, (Object) null, new IMqttActionListener() { // from class: com.pudutech.pdmqtt.client.RemoteMqttClientImpl$subscribe$2
                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken asyncActionToken) {
                    MqttManagerImplKt.remoteLog(RemoteMqttClientImpl.this.getTAG(), "subscribe.onSuccess ");
                    callback.actionState(0, "subscribe success");
                }

                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    MqttManagerImplKt.remoteLog(RemoteMqttClientImpl.this.getTAG(), "subscribe.onFailure " + exception);
                    callback.actionState(1, exception != null ? exception.getMessage() : null);
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void unsubscribe(Set<String> topicSet) {
        Intrinsics.checkParameterIsNotNull(topicSet, "topicSet");
        MqttManagerImplKt.remoteLog(this.TAG, "unsubscribe " + topicSet);
        for (String str : topicSet) {
            MqttAndroidClient mqttAndroidClient = this.mqttClient;
            if (mqttAndroidClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mqttClient");
            }
            mqttAndroidClient.unsubscribe(str);
        }
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void addOnConnectStateChangeLis(Function1<? super MqttConnectState, Unit> lis) {
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        this.mOnConnectStateChangeList.add(lis);
        lis.invoke(isConnect() ? MqttConnectState.CONNECTED : MqttConnectState.DISCONNECTED);
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void removeOnConnectStateChangeLis(Function1<? super MqttConnectState, Unit> lis) {
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        this.mOnConnectStateChangeList.remove(lis);
    }

    public final boolean getRelease() {
        return this.release;
    }

    public final void setRelease(boolean z) {
        this.release = z;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final void setEnable(boolean z) {
        this.enable = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean needConnect() {
        MqttAndroidClient mqttAndroidClient = this.mqttClient;
        if (mqttAndroidClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mqttClient");
        }
        return (mqttAndroidClient.isConnected() || !this.enable || this.release) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean canUse() {
        return this.enable && !this.release;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reconnect() {
        if (needConnect()) {
            realReconnect();
        }
    }

    private final void realReconnect() {
        Job launch$default;
        Job job = this.reconnectJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.crtDelayTime = this.DEFAULT_DELAY_TIME;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new RemoteMqttClientImpl$realReconnect$1(this, null), 3, null);
        this.reconnectJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getDelayTimeMills, reason: from getter */
    public final long getDEFAULT_DELAY_TIME() {
        return this.DEFAULT_DELAY_TIME;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyConnectState(MqttConnectState state) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.mOnConnectStateChangeList.iterator();
        while (it.hasNext()) {
            Function1 function1 = (Function1) it.next();
            try {
                function1.invoke(state);
            } catch (Exception e) {
                MqttManagerImplKt.remoteLog(this.TAG, "notifyConnectState.error " + e);
                arrayList.add(function1);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            this.mOnConnectStateChangeList.remove((Function1) it2.next());
        }
    }
}
