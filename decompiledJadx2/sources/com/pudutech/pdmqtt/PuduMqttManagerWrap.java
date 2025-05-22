package com.pudutech.pdmqtt;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.pdmqtt.GetClientCallback;
import com.pudutech.pdmqtt.client.IFetchClientCallback;
import com.pudutech.pdmqtt.client.IMqttClient;
import com.pudutech.pdmqtt.client.LocalMqttClientImpl;
import com.pudutech.pdmqtt.config.BaseMqttConfig;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PuduMqttManagerWrap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b*\u0002\u0006\u0015\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u000bH\u0002J'\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001b0\"¢\u0006\u0002\b#J\u000e\u0010$\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 J\u0016\u0010\u000e\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0004J\u0014\u0010\u0019\u001a\u00020\u001b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aJ\u000e\u0010'\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 J\b\u0010(\u001a\u00020\u001bH\u0002J\u000e\u0010)\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, m3961d2 = {"Lcom/pudutech/pdmqtt/PuduMqttManagerWrap;", "", "()V", "TAG", "", "connection", "com/pudutech/pdmqtt/PuduMqttManagerWrap$connection$1", "Lcom/pudutech/pdmqtt/PuduMqttManagerWrap$connection$1;", "fetchClientBuilderMap", "", "", "Lcom/pudutech/pdmqtt/MqttClientBuilder;", "localClientMap", "Lcom/pudutech/pdmqtt/client/IMqttClient;", "localLog", "Lcom/pudutech/pdmqtt/MqttLogger;", "getLocalLog", "()Lcom/pudutech/pdmqtt/MqttLogger;", "setLocalLog", "(Lcom/pudutech/pdmqtt/MqttLogger;)V", "mGetClientCallback", "com/pudutech/pdmqtt/PuduMqttManagerWrap$mGetClientCallback$1", "Lcom/pudutech/pdmqtt/PuduMqttManagerWrap$mGetClientCallback$1;", "mMqttManager", "Lcom/pudutech/pdmqtt/MqttManager;", "onServiceUnbind", "Lkotlin/Function0;", "", "_newClient", "builder", "fetchClient", "context", "Landroid/content/Context;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "init", AIUIConstant.KEY_TAG, NotificationCompat.CATEGORY_MESSAGE, "release", "releaseService", "setLog", "logger", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PuduMqttManagerWrap {
    private MqttLogger localLog;
    private MqttManager mMqttManager;
    private Function0<Unit> onServiceUnbind;
    private final String TAG = "PuduMqttManagerWrap";
    private final Map<String, List<MqttClientBuilder>> fetchClientBuilderMap = new LinkedHashMap();
    private final Map<String, IMqttClient> localClientMap = new LinkedHashMap();
    private final PuduMqttManagerWrap$connection$1 connection = new PuduMqttManagerWrap$connection$1(this);
    private final PuduMqttManagerWrap$mGetClientCallback$1 mGetClientCallback = new GetClientCallback.Stub() { // from class: com.pudutech.pdmqtt.PuduMqttManagerWrap$mGetClientCallback$1
        @Override // com.pudutech.pdmqtt.GetClientCallback
        public void onSuccess(String key) {
            String str;
            Map map;
            MqttManager mqttManager;
            MqttManager mqttManager2;
            Map map2;
            String str2;
            String str3;
            String str4;
            Intrinsics.checkParameterIsNotNull(key, "key");
            PuduMqttManagerWrap puduMqttManagerWrap = PuduMqttManagerWrap.this;
            str = puduMqttManagerWrap.TAG;
            puduMqttManagerWrap.localLog(str, "mGetClientCallback.onSuccess");
            map = PuduMqttManagerWrap.this.fetchClientBuilderMap;
            List list = (List) map.remove(key);
            if (list == null) {
                PuduMqttManagerWrap puduMqttManagerWrap2 = PuduMqttManagerWrap.this;
                str4 = puduMqttManagerWrap2.TAG;
                puduMqttManagerWrap2.localLog(str4, "mGetClientCallback.onSuccess builder == null");
                return;
            }
            if (!list.isEmpty()) {
                mqttManager = PuduMqttManagerWrap.this.mMqttManager;
                if (mqttManager == null) {
                    PuduMqttManagerWrap puduMqttManagerWrap3 = PuduMqttManagerWrap.this;
                    str2 = puduMqttManagerWrap3.TAG;
                    puduMqttManagerWrap3.localLog(str2, "mGetClientCallback.onSuccess mMqttManager == null");
                    return;
                }
                BaseMqttConfig mqttConfig = ((MqttClientBuilder) list.get(0)).getMqttConfig();
                if (mqttConfig == null) {
                    Intrinsics.throwNpe();
                }
                IMqttClient.Companion companion = IMqttClient.INSTANCE;
                mqttManager2 = PuduMqttManagerWrap.this.mMqttManager;
                if (mqttManager2 == null) {
                    Intrinsics.throwNpe();
                }
                IMqttClient clientProxy = companion.clientProxy(mqttManager2, mqttConfig);
                map2 = PuduMqttManagerWrap.this.localClientMap;
                map2.put(mqttConfig.key(), clientProxy);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    IFetchClientCallback clientCallback = ((MqttClientBuilder) it.next()).getClientCallback();
                    if (clientCallback != null) {
                        clientCallback.onSuccess(mqttConfig, clientProxy);
                    }
                }
                return;
            }
            PuduMqttManagerWrap puduMqttManagerWrap4 = PuduMqttManagerWrap.this;
            str3 = puduMqttManagerWrap4.TAG;
            puduMqttManagerWrap4.localLog(str3, "mGetClientCallback.onSuccess builder.isEmpty()");
        }

        @Override // com.pudutech.pdmqtt.GetClientCallback
        public void onError(String key, int code, String message) {
            String str;
            Map map;
            String str2;
            String str3;
            Intrinsics.checkParameterIsNotNull(key, "key");
            Intrinsics.checkParameterIsNotNull(message, "message");
            PuduMqttManagerWrap puduMqttManagerWrap = PuduMqttManagerWrap.this;
            str = puduMqttManagerWrap.TAG;
            puduMqttManagerWrap.localLog(str, "mGetClientCallback.onError");
            map = PuduMqttManagerWrap.this.fetchClientBuilderMap;
            List list = (List) map.remove(key);
            if (list == null) {
                PuduMqttManagerWrap puduMqttManagerWrap2 = PuduMqttManagerWrap.this;
                str3 = puduMqttManagerWrap2.TAG;
                puduMqttManagerWrap2.localLog(str3, "mGetClientCallback.onSuccess builder == null");
            } else {
                if (list.isEmpty()) {
                    PuduMqttManagerWrap puduMqttManagerWrap3 = PuduMqttManagerWrap.this;
                    str2 = puduMqttManagerWrap3.TAG;
                    puduMqttManagerWrap3.localLog(str2, "mGetClientCallback.onSuccess builder.isEmpty()");
                    return;
                }
                BaseMqttConfig mqttConfig = ((MqttClientBuilder) list.get(0)).getMqttConfig();
                if (mqttConfig == null) {
                    Intrinsics.throwNpe();
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    IFetchClientCallback clientCallback = ((MqttClientBuilder) it.next()).getClientCallback();
                    if (clientCallback != null) {
                        clientCallback.onError(mqttConfig, code, message);
                    }
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void releaseService() {
        if (this.mMqttManager == null) {
            return;
        }
        this.mMqttManager = (MqttManager) null;
        Iterator<Map.Entry<String, List<MqttClientBuilder>>> it = this.fetchClientBuilderMap.entrySet().iterator();
        while (it.hasNext()) {
            for (MqttClientBuilder mqttClientBuilder : it.next().getValue()) {
                IFetchClientCallback clientCallback = mqttClientBuilder.getClientCallback();
                if (clientCallback != null) {
                    BaseMqttConfig mqttConfig = mqttClientBuilder.getMqttConfig();
                    if (mqttConfig == null) {
                        Intrinsics.throwNpe();
                    }
                    clientCallback.onError(mqttConfig, -1, "ServiceDisconnected");
                }
            }
        }
        this.fetchClientBuilderMap.clear();
        Iterator<Map.Entry<String, IMqttClient>> it2 = this.localClientMap.entrySet().iterator();
        while (it2.hasNext()) {
            IMqttClient value = it2.next().getValue();
            if (!(value instanceof LocalMqttClientImpl)) {
                value = null;
            }
            LocalMqttClientImpl localMqttClientImpl = (LocalMqttClientImpl) value;
            if (localMqttClientImpl != null) {
                localMqttClientImpl.handlerServiceUnbind();
            }
        }
        this.localClientMap.clear();
        Function0<Unit> function0 = this.onServiceUnbind;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void init(Context context) {
        ComponentName startService;
        Intrinsics.checkParameterIsNotNull(context, "context");
        localLog(this.TAG, "================init");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MyMqttService");
        intent.setPackage(context.getPackageName());
        if (Build.VERSION.SDK_INT >= 26) {
            startService = context.startForegroundService(intent);
        } else {
            startService = context.startService(intent);
        }
        if (startService != null) {
            context.bindService(intent, this.connection, 1);
        }
        localLog(this.TAG, String.valueOf(startService));
    }

    public final synchronized void fetchClient(Context context, Function1<? super MqttClientBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(block, "block");
        localLog(this.TAG, "================fetchClient");
        MqttClientBuilder mqttClientBuilder = new MqttClientBuilder();
        block.invoke(mqttClientBuilder);
        mqttClientBuilder.checkConfig();
        List<MqttClientBuilder> list = this.fetchClientBuilderMap.get(mqttClientBuilder.key());
        if (list != null && (!list.isEmpty())) {
            localLog(this.TAG, "================fetchClient 重复获取");
            if (!list.contains(mqttClientBuilder)) {
                list.add(mqttClientBuilder);
            }
        } else {
            this.fetchClientBuilderMap.put(mqttClientBuilder.key(), CollectionsKt.mutableListOf(mqttClientBuilder));
            if (this.mMqttManager == null) {
                init(context);
            } else {
                _newClient(mqttClientBuilder);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void _newClient(MqttClientBuilder builder) {
        if (this.mMqttManager == null) {
            localLog(this.TAG, "mMqttManager==null");
        } else if (this.localClientMap.get(builder.key()) != null) {
            IFetchClientCallback clientCallback = builder.getClientCallback();
            if (clientCallback != null) {
                BaseMqttConfig mqttConfig = builder.getMqttConfig();
                if (mqttConfig == null) {
                    Intrinsics.throwNpe();
                }
                IMqttClient iMqttClient = this.localClientMap.get(builder.key());
                if (iMqttClient == null) {
                    Intrinsics.throwNpe();
                }
                clientCallback.onSuccess(mqttConfig, iMqttClient);
            }
            this.fetchClientBuilderMap.remove(builder.key());
        } else {
            BaseMqttConfig mqttConfig2 = builder.getMqttConfig();
            if (mqttConfig2 != null) {
                MqttManager mqttManager = this.mMqttManager;
                if (mqttManager == null) {
                    Intrinsics.throwNpe();
                }
                mqttManager.fetchClient(mqttConfig2.key(), mqttConfig2.serverURI(), mqttConfig2.getDevice_name(), mqttConfig2.username(), mqttConfig2.password(), mqttConfig2.getCertificateInfo(), this.mGetClientCallback);
            }
        }
    }

    public final void release(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        localLog(this.TAG, "================release");
        MqttManager mqttManager = this.mMqttManager;
        if (mqttManager != null) {
            mqttManager.release(CollectionsKt.toList(this.localClientMap.keySet()));
        }
        this.localClientMap.clear();
        context.unbindService(this.connection);
        releaseService();
    }

    public final void setLog(MqttLogger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        localLog(this.TAG, "================setLog");
        this.localLog = logger;
        MqttManager mqttManager = this.mMqttManager;
        if (mqttManager != null) {
            mqttManager.setLogger(this.localLog);
        }
    }

    public final MqttLogger getLocalLog() {
        return this.localLog;
    }

    public final void setLocalLog(MqttLogger mqttLogger) {
        this.localLog = mqttLogger;
    }

    public final void localLog(String tag, String msg) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        MqttLogger mqttLogger = this.localLog;
        if (mqttLogger == null) {
            Log.i(tag, msg);
        } else if (mqttLogger != null) {
            mqttLogger.log(tag, msg);
        }
    }

    public final void onServiceUnbind(Function0<Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.onServiceUnbind = block;
    }
}
