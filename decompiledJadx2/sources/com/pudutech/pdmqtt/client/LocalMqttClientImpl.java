package com.pudutech.pdmqtt.client;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.pdmqtt.ActionCallback;
import com.pudutech.pdmqtt.GetClientCallback;
import com.pudutech.pdmqtt.MqttManager;
import com.pudutech.pdmqtt.OnMessageChangeListener;
import com.pudutech.pdmqtt.OnPublishCallback;
import com.pudutech.pdmqtt.PuduMqttManager;
import com.pudutech.pdmqtt.config.BaseMqttConfig;
import com.pudutech.pdmqtt.service.LocalConnectStateLis;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: LocalMqttClientImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0018\u001a\u00020\u00142\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012j\u0002`\u0015H\u0016J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\b\u0010\u001b\u001a\u00020\u0005H\u0016J\u0012\u0010\u001c\u001a\u00020\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020\u0014J\b\u0010 \u001a\u00020\u000bH\u0016J\"\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010$H\u0016J*\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b2\u0006\u0010%\u001a\u00020&2\b\u0010\u001d\u001a\u0004\u0018\u00010$H\u0016J\b\u0010'\u001a\u00020\u0014H\u0016J \u0010(\u001a\u00020\u00142\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012j\u0002`\u0015H\u0016J\u0010\u0010)\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\u001e\u0010*\u001a\u00020\u00142\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010\u001d\u001a\u00020-H\u0016J\u0016\u0010.\u001a\u00020\u00142\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,H\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0010\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012j\u0002`\u0015\u0012\u0004\u0012\u00020\b0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\b0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, m3961d2 = {"Lcom/pudutech/pdmqtt/client/LocalMqttClientImpl;", "Lcom/pudutech/pdmqtt/client/IMqttClient;", "manager", "Lcom/pudutech/pdmqtt/MqttManager;", "config", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "(Lcom/pudutech/pdmqtt/MqttManager;Lcom/pudutech/pdmqtt/config/BaseMqttConfig;)V", "TAG", "", "kotlin.jvm.PlatformType", "clientCanUse", "", "crtKey", "crtSubscribeTopic", "", TransferTable.COLUMN_KEY, "mOnConnectChangeLisMap", "Ljava/util/concurrent/ConcurrentHashMap;", "Lkotlin/Function1;", "Lcom/pudutech/pdmqtt/client/MqttConnectState;", "", "Lcom/pudutech/pdmqtt/client/OnConnectStateChangeListener;", "mOnMessageChangeLisMap", "Lcom/pudutech/pdmqtt/OnMessageChangeListener;", "addOnConnectStateChangeLis", "lis", "addOnMessageChangeListener", "clientConfig", MqttServiceConstants.CONNECT_ACTION, "callback", "Lcom/pudutech/pdmqtt/GetClientCallback;", "handlerServiceUnbind", "isConnect", "publish", "topic", MqttServiceConstants.PAYLOAD, "Lcom/pudutech/pdmqtt/OnPublishCallback;", MqttServiceConstants.QOS, "", "release", "removeOnConnectStateChangeLis", "removeOnMessageChangeListener", MqttServiceConstants.SUBSCRIBE_ACTION, "topicSet", "", "Lcom/pudutech/pdmqtt/ActionCallback;", MqttServiceConstants.UNSUBSCRIBE_ACTION, "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LocalMqttClientImpl implements IMqttClient {
    private final String TAG;
    private boolean clientCanUse;
    private final BaseMqttConfig config;
    private final String crtKey;
    private final Set<String> crtSubscribeTopic;
    private final String key;
    private final ConcurrentHashMap<Function1<MqttConnectState, Unit>, String> mOnConnectChangeLisMap;
    private final ConcurrentHashMap<OnMessageChangeListener, String> mOnMessageChangeLisMap;
    private final MqttManager manager;

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void connect(GetClientCallback callback) {
    }

    public LocalMqttClientImpl(MqttManager manager, BaseMqttConfig config) {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        Intrinsics.checkParameterIsNotNull(config, "config");
        this.manager = manager;
        this.config = config;
        this.TAG = getClass().getSimpleName();
        this.clientCanUse = true;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.crtKey = uuid;
        this.crtSubscribeTopic = new LinkedHashSet();
        this.mOnMessageChangeLisMap = new ConcurrentHashMap<>();
        this.mOnConnectChangeLisMap = new ConcurrentHashMap<>();
        this.key = this.config.key();
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public synchronized void release() {
        PuduMqttManager puduMqttManager = PuduMqttManager.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        puduMqttManager.localLog(TAG, "release " + this.clientCanUse);
        if (this.clientCanUse) {
            unsubscribe(this.crtSubscribeTopic);
            Set<OnMessageChangeListener> keySet = this.mOnMessageChangeLisMap.keySet();
            Intrinsics.checkExpressionValueIsNotNull(keySet, "mOnMessageChangeLisMap.keys");
            for (OnMessageChangeListener it : keySet) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                removeOnMessageChangeListener(it);
            }
            Set<Function1<MqttConnectState, Unit>> keySet2 = this.mOnConnectChangeLisMap.keySet();
            Intrinsics.checkExpressionValueIsNotNull(keySet2, "mOnConnectChangeLisMap.keys");
            Iterator<T> it2 = keySet2.iterator();
            while (it2.hasNext()) {
                Function1<? super MqttConnectState, Unit> it3 = (Function1) it2.next();
                Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                removeOnConnectStateChangeLis(it3);
            }
        }
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    /* renamed from: clientConfig, reason: from getter */
    public BaseMqttConfig getConfig() {
        return this.config;
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public boolean isConnect() {
        try {
            return this.manager.isConnect(this.key);
        } catch (Exception e) {
            PuduMqttManager puduMqttManager = PuduMqttManager.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            puduMqttManager.localLog(TAG, "isConnect.error > " + e);
            return false;
        }
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void addOnMessageChangeListener(OnMessageChangeListener lis) {
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        PuduMqttManager puduMqttManager = PuduMqttManager.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        puduMqttManager.localLog(TAG, "addOnMessageChangeListener " + this.clientCanUse);
        if (this.clientCanUse && this.mOnMessageChangeLisMap.get(lis) == null) {
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
            this.mOnMessageChangeLisMap.put(lis, uuid);
            this.manager.addOnMessageChangeListener(this.key, this.crtKey, uuid, lis);
        }
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void removeOnMessageChangeListener(OnMessageChangeListener lis) {
        String remove;
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        PuduMqttManager puduMqttManager = PuduMqttManager.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        puduMqttManager.localLog(TAG, "removeOnMessageChangeListener " + this.clientCanUse);
        if (this.clientCanUse && (remove = this.mOnMessageChangeLisMap.remove(lis)) != null) {
            this.manager.removeOnMessageChangeListener(remove);
        }
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void publish(String topic, String payload, OnPublishCallback callback) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        PuduMqttManager puduMqttManager = PuduMqttManager.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        puduMqttManager.localLog(TAG, "publish " + this.clientCanUse);
        publish(topic, payload, 0, callback);
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void publish(String topic, String payload, int qos, OnPublishCallback callback) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        PuduMqttManager puduMqttManager = PuduMqttManager.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        puduMqttManager.localLog(TAG, "publish " + this.clientCanUse);
        if (this.clientCanUse) {
            this.manager.publish(this.key, topic, payload, qos, callback);
        } else if (callback != null) {
            callback.onFailue(topic, payload, -1, "service disconnect");
        }
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void subscribe(Set<String> topicSet, ActionCallback callback) {
        Intrinsics.checkParameterIsNotNull(topicSet, "topicSet");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        PuduMqttManager puduMqttManager = PuduMqttManager.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        puduMqttManager.localLog(TAG, "subscribe " + this.clientCanUse);
        if (!this.clientCanUse) {
            callback.actionState(-1, "service disconnect");
        } else {
            this.crtSubscribeTopic.addAll(topicSet);
            this.manager.subscribe(this.key, this.crtKey, CollectionsKt.toList(topicSet), callback);
        }
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void unsubscribe(Set<String> topicSet) {
        Intrinsics.checkParameterIsNotNull(topicSet, "topicSet");
        PuduMqttManager puduMqttManager = PuduMqttManager.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        puduMqttManager.localLog(TAG, "unsubscribe " + this.clientCanUse);
        if (this.clientCanUse) {
            this.crtSubscribeTopic.retainAll(topicSet);
            this.manager.unsubscribe(this.key, this.crtKey, CollectionsKt.toList(topicSet), new ActionCallback.Stub() { // from class: com.pudutech.pdmqtt.client.LocalMqttClientImpl$unsubscribe$1
                @Override // com.pudutech.pdmqtt.ActionCallback
                public void actionState(int state, String message) {
                }
            });
        }
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void addOnConnectStateChangeLis(Function1<? super MqttConnectState, Unit> lis) {
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        PuduMqttManager puduMqttManager = PuduMqttManager.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        puduMqttManager.localLog(TAG, "addOnConnectStateChangeLis " + this.clientCanUse);
        if (this.clientCanUse && this.mOnConnectChangeLisMap.get(lis) == null) {
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
            this.mOnConnectChangeLisMap.put(lis, uuid);
            this.manager.addOnConnectStateChangeLis(this.key, uuid, new LocalConnectStateLis(lis));
        }
    }

    @Override // com.pudutech.pdmqtt.client.IMqttClient
    public void removeOnConnectStateChangeLis(Function1<? super MqttConnectState, Unit> lis) {
        String remove;
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        PuduMqttManager puduMqttManager = PuduMqttManager.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        puduMqttManager.localLog(TAG, "removeOnConnectStateChangeLis " + this.clientCanUse);
        if (this.clientCanUse && (remove = this.mOnConnectChangeLisMap.remove(lis)) != null) {
            this.manager.removeOnConnectStateChangeLis(remove);
        }
    }

    public final void handlerServiceUnbind() {
        this.clientCanUse = false;
        Iterator<Map.Entry<Function1<MqttConnectState, Unit>, String>> it = this.mOnConnectChangeLisMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getKey().invoke(MqttConnectState.DISCONNECTED);
        }
    }
}
