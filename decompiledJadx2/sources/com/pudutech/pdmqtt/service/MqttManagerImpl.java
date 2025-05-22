package com.pudutech.pdmqtt.service;

import android.content.Context;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.pdmqtt.ActionCallback;
import com.pudutech.pdmqtt.GetClientCallback;
import com.pudutech.pdmqtt.MqttLogger;
import com.pudutech.pdmqtt.MqttManager;
import com.pudutech.pdmqtt.OnConnectStateChangeLis;
import com.pudutech.pdmqtt.OnMessageChangeListener;
import com.pudutech.pdmqtt.OnPublishCallback;
import com.pudutech.pdmqtt.client.IMqttClient;
import com.pudutech.pdmqtt.config.DefaultMqttConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: MqttManagerImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008b\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0016\u0018\u0000 >2\u00020\u0001:\u0001>B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J(\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020 H\u0016J@\u0010!\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020(H\u0016J\u0012\u0010)\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0006H\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010\u001a\u001a\u00020\u0006H\u0016J2\u0010,\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u0002002\b\u0010'\u001a\u0004\u0018\u000101H\u0016J\u0018\u00102\u001a\u00020\u00192\u000e\u00103\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\rH\u0016J\u0010\u00104\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0006H\u0016J\u0010\u00105\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0006H\u0016J\u0010\u00106\u001a\u00020\u00192\u0006\u00107\u001a\u000208H\u0016J0\u00109\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u000e\u0010:\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r2\u0006\u0010;\u001a\u00020<H\u0016J0\u0010=\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u000e\u0010:\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r2\u0006\u0010;\u001a\u00020<H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017¨\u0006?"}, m3961d2 = {"Lcom/pudutech/pdmqtt/service/MqttManagerImpl;", "Lcom/pudutech/pdmqtt/MqttManager$Stub;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "clientMap", "", "Lcom/pudutech/pdmqtt/client/IMqttClient;", "getContext", "()Landroid/content/Context;", "mConnectStateChangeEntryList", "", "Lcom/pudutech/pdmqtt/service/OnConnectChangeEntry;", "mOnMessageChangeEntryList", "Lcom/pudutech/pdmqtt/service/OnMessageChangeEntry;", "mSubscribeTopicMap", "Lcom/pudutech/pdmqtt/service/TopicExt;", "messageChangeLisMap", "Lcom/pudutech/pdmqtt/service/OnMessageChangeListenerWrap;", "onMessageChangeCallback", "com/pudutech/pdmqtt/service/MqttManagerImpl$onMessageChangeCallback$1", "Lcom/pudutech/pdmqtt/service/MqttManagerImpl$onMessageChangeCallback$1;", "addOnConnectStateChangeLis", "", TransferTable.COLUMN_KEY, "lisId", "lis", "Lcom/pudutech/pdmqtt/OnConnectStateChangeLis;", "addOnMessageChangeListener", "uniqueKey", "Lcom/pudutech/pdmqtt/OnMessageChangeListener;", "fetchClient", "serverURI", TmpConstant.KEY_CLIENT_ID, CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME, CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "certificateJsonInfo", "callback", "Lcom/pudutech/pdmqtt/GetClientCallback;", "getClient", "isConnect", "", "publish", "topic", MqttServiceConstants.PAYLOAD, MqttServiceConstants.QOS, "", "Lcom/pudutech/pdmqtt/OnPublishCallback;", "release", "keys", "removeOnConnectStateChangeLis", "removeOnMessageChangeListener", "setLogger", "log", "Lcom/pudutech/pdmqtt/MqttLogger;", MqttServiceConstants.SUBSCRIBE_ACTION, "topics", "actionCallback", "Lcom/pudutech/pdmqtt/ActionCallback;", MqttServiceConstants.UNSUBSCRIBE_ACTION, "Companion", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MqttManagerImpl extends MqttManager.Stub {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static MqttLogger log;
    private final String TAG;
    private final Map<String, IMqttClient> clientMap;
    private final Context context;
    private final List<OnConnectChangeEntry> mConnectStateChangeEntryList;
    private final List<OnMessageChangeEntry> mOnMessageChangeEntryList;
    private final Map<String, List<TopicExt>> mSubscribeTopicMap;
    private final Map<String, OnMessageChangeListenerWrap> messageChangeLisMap;
    private final MqttManagerImpl$onMessageChangeCallback$1 onMessageChangeCallback;

    /* JADX WARN: Type inference failed for: r2v12, types: [com.pudutech.pdmqtt.service.MqttManagerImpl$onMessageChangeCallback$1] */
    public MqttManagerImpl(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "MqttManagerImpl";
        this.clientMap = new LinkedHashMap();
        this.messageChangeLisMap = new LinkedHashMap();
        this.mOnMessageChangeEntryList = new ArrayList();
        this.mConnectStateChangeEntryList = new ArrayList();
        this.mSubscribeTopicMap = new LinkedHashMap();
        this.onMessageChangeCallback = new OnMessageChangeListenerExt() { // from class: com.pudutech.pdmqtt.service.MqttManagerImpl$onMessageChangeCallback$1
            /* JADX WARN: Removed duplicated region for block: B:29:0x0091 A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:33:0x003d A[SYNTHETIC] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            private final List<OnMessageChangeEntry> filterSubscribeEntry(String clientKey, String topic) {
                List list;
                Map map;
                boolean z;
                Object obj;
                Set<String> topicSet;
                list = MqttManagerImpl.this.mOnMessageChangeEntryList;
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : list) {
                    if (Intrinsics.areEqual(((OnMessageChangeEntry) obj2).getClientKey(), clientKey)) {
                        arrayList.add(obj2);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj3 : arrayList) {
                    OnMessageChangeEntry onMessageChangeEntry = (OnMessageChangeEntry) obj3;
                    map = MqttManagerImpl.this.mSubscribeTopicMap;
                    List list2 = (List) map.get(onMessageChangeEntry.getClientKey());
                    if (list2 != null) {
                        Iterator it = list2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it.next();
                            if (Intrinsics.areEqual(((TopicExt) obj).getUniqueKey(), onMessageChangeEntry.getUniqueKey())) {
                                break;
                            }
                        }
                        TopicExt topicExt = (TopicExt) obj;
                        if (topicExt != null && (topicSet = topicExt.getTopicSet()) != null) {
                            z = topicSet.contains(topic);
                            if (!z) {
                                arrayList2.add(obj3);
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                    }
                }
                return arrayList2;
            }

            @Override // com.pudutech.pdmqtt.service.OnMessageChangeListenerExt
            public void onReceive(String clientKey, String topic, String payload) {
                Intrinsics.checkParameterIsNotNull(clientKey, "clientKey");
                Intrinsics.checkParameterIsNotNull(topic, "topic");
                Intrinsics.checkParameterIsNotNull(payload, "payload");
                Iterator<T> it = filterSubscribeEntry(clientKey, topic).iterator();
                while (it.hasNext()) {
                    ((OnMessageChangeEntry) it.next()).getLis().onReceive(topic, payload);
                }
            }

            @Override // com.pudutech.pdmqtt.service.OnMessageChangeListenerExt
            public void onSend(String clientKey, String topic, String payload) {
                Intrinsics.checkParameterIsNotNull(clientKey, "clientKey");
                Intrinsics.checkParameterIsNotNull(topic, "topic");
                Intrinsics.checkParameterIsNotNull(payload, "payload");
                Iterator<T> it = filterSubscribeEntry(clientKey, topic).iterator();
                while (it.hasNext()) {
                    ((OnMessageChangeEntry) it.next()).getLis().onSend(topic, payload);
                }
            }
        };
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.pudutech.pdmqtt.MqttManager
    public void fetchClient(String key, String serverURI, String clientId, String username, String password, String certificateJsonInfo, GetClientCallback callback) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(serverURI, "serverURI");
        Intrinsics.checkParameterIsNotNull(clientId, "clientId");
        Intrinsics.checkParameterIsNotNull(username, "username");
        Intrinsics.checkParameterIsNotNull(password, "password");
        Intrinsics.checkParameterIsNotNull(certificateJsonInfo, "certificateJsonInfo");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        MqttManagerImplKt.remoteLog(this.TAG, "binder.fetchClient--------------------");
        IMqttClient client = getClient(key);
        if (client == null) {
            client = IMqttClient.INSTANCE.remoteClientWrap(this.context, new DefaultMqttConfig(serverURI, clientId, username, password, certificateJsonInfo, key));
            this.clientMap.put(key, client);
        }
        if (client.isConnect()) {
            callback.onSuccess(key);
        } else {
            client.connect(callback);
        }
    }

    @Override // com.pudutech.pdmqtt.MqttManager
    public void release(List<String> keys) {
        if (keys != null) {
            for (final String str : keys) {
                this.messageChangeLisMap.remove(str);
                CollectionsKt.removeAll((List) this.mOnMessageChangeEntryList, (Function1) new Function1<OnMessageChangeEntry, Boolean>() { // from class: com.pudutech.pdmqtt.service.MqttManagerImpl$release$1$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(OnMessageChangeEntry onMessageChangeEntry) {
                        return Boolean.valueOf(invoke2(onMessageChangeEntry));
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final boolean invoke2(OnMessageChangeEntry entry) {
                        Intrinsics.checkParameterIsNotNull(entry, "entry");
                        return Intrinsics.areEqual(entry.getClientKey(), str);
                    }
                });
                CollectionsKt.removeAll((List) this.mConnectStateChangeEntryList, (Function1) new Function1<OnConnectChangeEntry, Boolean>() { // from class: com.pudutech.pdmqtt.service.MqttManagerImpl$release$1$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(OnConnectChangeEntry onConnectChangeEntry) {
                        return Boolean.valueOf(invoke2(onConnectChangeEntry));
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final boolean invoke2(OnConnectChangeEntry entry) {
                        Intrinsics.checkParameterIsNotNull(entry, "entry");
                        return Intrinsics.areEqual(entry.getClientKey(), str);
                    }
                });
                this.mSubscribeTopicMap.remove(str);
                IMqttClient client = getClient(str);
                if (client != null) {
                    client.release();
                }
                this.clientMap.remove(str);
            }
        }
    }

    @Override // com.pudutech.pdmqtt.MqttManager
    public void setLogger(MqttLogger log2) {
        Intrinsics.checkParameterIsNotNull(log2, "log");
        log = log2;
    }

    @Override // com.pudutech.pdmqtt.MqttManager
    public void addOnMessageChangeListener(String key, String uniqueKey, String lisId, OnMessageChangeListener lis) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(uniqueKey, "uniqueKey");
        Intrinsics.checkParameterIsNotNull(lisId, "lisId");
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        MqttManagerImplKt.remoteLog(this.TAG, "binder.addOnMessageChangeListener " + lis);
        if (this.messageChangeLisMap.get(key) == null) {
            OnMessageChangeListenerWrap onMessageChangeListenerWrap = new OnMessageChangeListenerWrap(key, this.onMessageChangeCallback);
            this.messageChangeLisMap.put(key, onMessageChangeListenerWrap);
            IMqttClient client = getClient(key);
            if (client != null) {
                client.addOnMessageChangeListener(onMessageChangeListenerWrap);
            }
        }
        this.mOnMessageChangeEntryList.add(new OnMessageChangeEntry(key, uniqueKey, lisId, lis));
    }

    @Override // com.pudutech.pdmqtt.MqttManager
    public void removeOnMessageChangeListener(String lisId) {
        Object obj;
        Object obj2;
        IMqttClient client;
        Intrinsics.checkParameterIsNotNull(lisId, "lisId");
        MqttManagerImplKt.remoteLog(this.TAG, "binder.removeOnMessageChangeListener " + lisId);
        Iterator<T> it = this.mOnMessageChangeEntryList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((OnMessageChangeEntry) obj).getLisId(), lisId)) {
                    break;
                }
            }
        }
        OnMessageChangeEntry onMessageChangeEntry = (OnMessageChangeEntry) obj;
        if (onMessageChangeEntry != null) {
            this.mOnMessageChangeEntryList.remove(onMessageChangeEntry);
            Iterator<T> it2 = this.mOnMessageChangeEntryList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj2 = null;
                    break;
                } else {
                    obj2 = it2.next();
                    if (Intrinsics.areEqual(onMessageChangeEntry.getClientKey(), ((OnMessageChangeEntry) obj2).getClientKey())) {
                        break;
                    }
                }
            }
            if (((OnMessageChangeEntry) obj2) != null) {
                return;
            }
            OnMessageChangeListenerWrap onMessageChangeListenerWrap = this.messageChangeLisMap.get(onMessageChangeEntry.getClientKey());
            if (onMessageChangeListenerWrap != null && (client = getClient(onMessageChangeEntry.getClientKey())) != null) {
                client.removeOnMessageChangeListener(onMessageChangeListenerWrap);
            }
            this.messageChangeLisMap.remove(onMessageChangeEntry.getClientKey());
        }
    }

    @Override // com.pudutech.pdmqtt.MqttManager
    public void addOnConnectStateChangeLis(String key, String lisId, OnConnectStateChangeLis lis) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(lisId, "lisId");
        Intrinsics.checkParameterIsNotNull(lis, "lis");
        MqttManagerImplKt.remoteLog(this.TAG, "binder.addOnConnectStateChangeLis " + key + ' ' + lisId);
        OnConnectChangeEntry onConnectChangeEntry = new OnConnectChangeEntry(key, lisId, lis);
        this.mConnectStateChangeEntryList.add(onConnectChangeEntry);
        IMqttClient client = getClient(key);
        if (client != null) {
            client.addOnConnectStateChangeLis(onConnectChangeEntry);
        }
    }

    @Override // com.pudutech.pdmqtt.MqttManager
    public void removeOnConnectStateChangeLis(String lisId) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(lisId, "lisId");
        Iterator<T> it = this.mConnectStateChangeEntryList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((OnConnectChangeEntry) obj).getLisId(), lisId)) {
                    break;
                }
            }
        }
        OnConnectChangeEntry onConnectChangeEntry = (OnConnectChangeEntry) obj;
        if (onConnectChangeEntry != null) {
            this.mConnectStateChangeEntryList.remove(onConnectChangeEntry);
            IMqttClient client = getClient(onConnectChangeEntry.getClientKey());
            if (client != null) {
                client.removeOnConnectStateChangeLis(onConnectChangeEntry);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0064, code lost:
    
        if (r1 != null) goto L20;
     */
    @Override // com.pudutech.pdmqtt.MqttManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void subscribe(String key, String uniqueKey, List<String> topics, ActionCallback actionCallback) {
        TopicExt topicExt;
        Object obj;
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(uniqueKey, "uniqueKey");
        Intrinsics.checkParameterIsNotNull(actionCallback, "actionCallback");
        MqttManagerImplKt.remoteLog(this.TAG, "subscribe " + topics);
        if (this.mSubscribeTopicMap.get(key) == null) {
            this.mSubscribeTopicMap.put(key, new ArrayList());
        }
        List<TopicExt> list = this.mSubscribeTopicMap.get(key);
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (Intrinsics.areEqual(((TopicExt) obj).getUniqueKey(), uniqueKey)) {
                        break;
                    }
                }
            }
            topicExt = (TopicExt) obj;
        }
        topicExt = new TopicExt(uniqueKey);
        List<TopicExt> list2 = this.mSubscribeTopicMap.get(key);
        if (list2 != null) {
            list2.add(topicExt);
        }
        Set<String> topicSet = topicExt.getTopicSet();
        Collection collection = topics;
        if (topics == null) {
            collection = SetsKt.emptySet();
        }
        topicSet.addAll(collection);
        IMqttClient client = getClient(key);
        if (client != null) {
            client.subscribe(topicExt.getTopicSet(), actionCallback);
        }
    }

    @Override // com.pudutech.pdmqtt.MqttManager
    public void unsubscribe(String key, String uniqueKey, List<String> topics, ActionCallback actionCallback) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(uniqueKey, "uniqueKey");
        Intrinsics.checkParameterIsNotNull(actionCallback, "actionCallback");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        if (topics != null) {
            for (String str : topics) {
                booleanRef.element = true;
                List<TopicExt> list = this.mSubscribeTopicMap.get(key);
                if (list != null) {
                    for (TopicExt topicExt : list) {
                        if (Intrinsics.areEqual(uniqueKey, topicExt.getUniqueKey())) {
                            topicExt.getTopicSet().remove(str);
                        } else if (topicExt.getTopicSet().contains(str)) {
                            booleanRef.element = false;
                        }
                    }
                }
                if (booleanRef.element) {
                    linkedHashSet.add(str);
                }
            }
        }
        IMqttClient client = getClient(key);
        if (client != null) {
            client.unsubscribe(linkedHashSet);
        }
    }

    @Override // com.pudutech.pdmqtt.MqttManager
    public boolean isConnect(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        IMqttClient client = getClient(key);
        if (client != null) {
            return client.isConnect();
        }
        return false;
    }

    @Override // com.pudutech.pdmqtt.MqttManager
    public void publish(String key, String topic, String payload, int qos, OnPublishCallback callback) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        MqttManagerImplKt.remoteLog(this.TAG, "publish " + topic + ' ' + payload);
        IMqttClient client = getClient(key);
        if (client != null) {
            client.publish(topic, payload, qos, callback);
        }
    }

    private final IMqttClient getClient(String key) {
        return this.clientMap.get(key);
    }

    /* compiled from: MqttManagerImpl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/pdmqtt/service/MqttManagerImpl$Companion;", "", "()V", "log", "Lcom/pudutech/pdmqtt/MqttLogger;", "getLog", "()Lcom/pudutech/pdmqtt/MqttLogger;", "setLog", "(Lcom/pudutech/pdmqtt/MqttLogger;)V", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MqttLogger getLog() {
            return MqttManagerImpl.log;
        }

        public final void setLog(MqttLogger mqttLogger) {
            MqttManagerImpl.log = mqttLogger;
        }
    }
}
