package com.pudutech.robot.opensdk.aliyun;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.linkkit.api.ILinkKit;
import com.aliyun.alink.linkkit.api.IoTDMConfig;
import com.aliyun.alink.linkkit.api.IoTMqttClientConfig;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linkkit.api.LinkKitInitParams;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentNet;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.C0978b;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttConfigure;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.log.IDGenerater;
import com.aliyun.linksdk.alcs.AlcsConstant;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.opensdk.MsgContext;
import com.pudutech.robot.opensdk.RemoteConnectState;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.SDKConfig;
import com.pudutech.robot.opensdk.aliyun.IotRequest;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.bean.BindCodeData;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowStateValue;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import com.pudutech.robot.opensdk.aliyun.topic.IotSubBeeperTopic;
import com.pudutech.robot.opensdk.aliyun.topic.IotSubDisinfectionTopic;
import com.pudutech.robot.opensdk.aliyun.topic.IotSubGroupTopic;
import com.pudutech.robot.opensdk.aliyun.topic.IotSubSdkTopic;
import com.pudutech.robot.opensdk.aliyun.topic.IotSubSeviceTopic;
import com.pudutech.robot.opensdk.bean.pub.BaseNotifyPub;
import com.pudutech.robot.opensdk.bean.pub.IPubServiceMsg;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack;
import com.pudutech.robot.opensdk.interf.IPubMsg;
import com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener;
import com.pudutech.robot.opensdk.interf.ISubTopic;
import com.pudutech.robot.opensdk.utils.GenRandomUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;

/* compiled from: AliyunIot.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u00ad\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0012\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u0017J\u001c\u00102\u001a\u0002032\n\u00104\u001a\u0006\u0012\u0002\b\u0003052\u0006\u00106\u001a\u000207H\u0002J \u00108\u001a\u0002002\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\b\u00109\u001a\u0004\u0018\u00010:J\u0012\u0010;\u001a\u0002002\b\u00109\u001a\u0004\u0018\u00010:H\u0002J\b\u0010<\u001a\u000200H\u0002J\b\u0010=\u001a\u00020\u0017H\u0002J\u0012\u0010>\u001a\u0002002\b\u0010?\u001a\u0004\u0018\u00010\nH\u0002J'\u0010@\u001a\u0002002\u0006\u0010A\u001a\u00020\u00062\u0006\u0010B\u001a\u00020C2\b\u00109\u001a\u0004\u0018\u00010:H\u0000¢\u0006\u0002\bDJ\"\u0010E\u001a\u0002002\u0006\u0010A\u001a\u00020\u00062\u0006\u0010B\u001a\u00020C2\b\u00109\u001a\u0004\u0018\u00010:H\u0002J\b\u0010F\u001a\u000200H\u0002J\b\u0010G\u001a\u000200H\u0002J\u0006\u0010H\u001a\u000200J-\u0010I\u001a\u0002002\n\u00104\u001a\u0006\u0012\u0002\b\u0003052\u0006\u00106\u001a\u0002072\n\b\u0002\u00109\u001a\u0004\u0018\u00010:H\u0000¢\u0006\u0002\bJJ\u0015\u0010K\u001a\u0002002\u0006\u00109\u001a\u00020LH\u0000¢\u0006\u0002\bMJ\b\u0010N\u001a\u000200H\u0002J\b\u0010O\u001a\u000200H\u0002J\b\u0010P\u001a\u00020\u0017H\u0002J\b\u0010Q\u001a\u000200H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R*\u0010$\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020&0%j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020&`'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010+\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020,0%j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020,`'¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.¨\u0006R"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/AliyunIot;", "", "()V", "RETRY_CONNECT", "", "TAG", "", "config", "Lcom/pudutech/robot/opensdk/RobotOpenSdk$Config;", "connectState", "Lcom/aliyun/alink/linksdk/cmp/core/base/ConnectState;", "context", "Landroid/content/Context;", "gson", "Lcom/google/gson/Gson;", "handler", "Landroid/os/Handler;", "iotNotifyListener", "com/pudutech/robot/opensdk/aliyun/AliyunIot$iotNotifyListener$1", "Lcom/pudutech/robot/opensdk/aliyun/AliyunIot$iotNotifyListener$1;", "iotWork", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "isDestroyIot", "", "isEnable", "isInit", "isIniting", "isRelease", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "onConnectListener", "Lcom/pudutech/robot/opensdk/interf/IRemoteConnectStateListener;", "getOnConnectListener$robot_open_sdk_release", "()Lcom/pudutech/robot/opensdk/interf/IRemoteConnectStateListener;", "setOnConnectListener$robot_open_sdk_release", "(Lcom/pudutech/robot/opensdk/interf/IRemoteConnectStateListener;)V", "subTopics", "Ljava/util/HashMap;", "Lcom/pudutech/robot/opensdk/interf/ISubTopic;", "Lkotlin/collections/HashMap;", "waitRespReqs", "Ljava/util/concurrent/CopyOnWriteArraySet;", "Lcom/pudutech/robot/opensdk/aliyun/IotRequest;", "whiteDevices", "", "getWhiteDevices", "()Ljava/util/HashMap;", "enable", "", "b", "genResp", "Lcom/aliyun/alink/linksdk/cmp/connect/channel/MqttPublishRequest;", "msgContext", "Lcom/pudutech/robot/opensdk/MsgContext;", "body", "Lcom/pudutech/robot/opensdk/interf/IBody;", "init", "callBack", "Lcom/pudutech/robot/opensdk/interf/ICallback;", "initIot", "initSub", "isNetActive", "notifyConnectByIot", "p1", AlcsConstant.EVENT_PUB, TypedValues.Attributes.S_TARGET, NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/robot/opensdk/interf/IPubMsg;", "pub$robot_open_sdk_release", "pubTarget", "reInit", "reconnectIfNeed", "release", "responseMsg", "responseMsg$robot_open_sdk_release", "setBindCode", "Lcom/pudutech/robot/opensdk/interf/IGenBindCodeCallBack;", "setBindCode$robot_open_sdk_release", "setNetworkCallback", "subscribeTopic", "tryReconnectIotTask", "unRegisterNetworkCallback", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AliyunIot {
    private RobotOpenSdk.Config config;
    private Context context;
    private volatile boolean isDestroyIot;
    private boolean isInit;
    private boolean isIniting;
    private boolean isRelease;
    private ConnectivityManager.NetworkCallback networkCallback;
    private IRemoteConnectStateListener onConnectListener;
    private final String TAG = "AliyunIot";
    private final HashMap<String, ISubTopic> subTopics = new HashMap<>();
    private final Gson gson = new Gson();
    private ConnectState connectState = ConnectState.DISCONNECTED;
    private final CopyOnWriteArraySet<IotRequest> waitRespReqs = new CopyOnWriteArraySet<>();
    private final ExecutorCoroutineDispatcher iotWork = ThreadPoolDispatcherKt.newSingleThreadContext("AliyunIot");
    private final HashMap<String, Long> whiteDevices = new HashMap<>();
    private volatile boolean isEnable = true;
    private final AliyunIot$iotNotifyListener$1 iotNotifyListener = new AliyunIot$iotNotifyListener$1(this);
    private final int RETRY_CONNECT = 101;
    private final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.robot.opensdk.aliyun.AliyunIot$handler$1
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
        
            if (r6 == com.aliyun.alink.linksdk.cmp.core.base.ConnectState.CONNECTFAIL) goto L17;
         */
        @Override // android.os.Handler.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean handleMessage(Message message) {
            int i;
            boolean z;
            String str;
            boolean z2;
            boolean z3;
            boolean z4;
            boolean isNetActive;
            ConnectState connectState;
            String str2;
            ConnectState connectState2;
            boolean tryReconnectIotTask;
            String str3;
            ConnectState connectState3;
            String str4;
            int i2 = message.what;
            i = AliyunIot.this.RETRY_CONNECT;
            if (i2 == i) {
                z = AliyunIot.this.isDestroyIot;
                if (!z) {
                    z4 = AliyunIot.this.isRelease;
                    if (!z4) {
                        isNetActive = AliyunIot.this.isNetActive();
                        if (!isNetActive) {
                            str4 = AliyunIot.this.TAG;
                            Pdlog.m3273d(str4, "handler isNetActive false ");
                            return true;
                        }
                        connectState = AliyunIot.this.connectState;
                        if (connectState != ConnectState.DISCONNECTED) {
                            connectState3 = AliyunIot.this.connectState;
                        }
                        str2 = AliyunIot.this.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("handler check connct state is ");
                        connectState2 = AliyunIot.this.connectState;
                        sb.append(connectState2);
                        sb.append(" , need retry");
                        Pdlog.m3277w(str2, sb.toString());
                        tryReconnectIotTask = AliyunIot.this.tryReconnectIotTask();
                        if (!tryReconnectIotTask) {
                            str3 = AliyunIot.this.TAG;
                            Pdlog.m3273d(str3, "RETRY_CONNECT , need reinit ");
                            AliyunIot.this.reInit();
                        }
                    }
                }
                str = AliyunIot.this.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("handler isDestroyIot = ");
                z2 = AliyunIot.this.isDestroyIot;
                sb2.append(z2);
                sb2.append(" || isRelease = ");
                z3 = AliyunIot.this.isRelease;
                sb2.append(z3);
                sb2.append(' ');
                Pdlog.m3273d(str, sb2.toString());
            }
            return true;
        }
    });

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ConnectState.values().length];

        static {
            $EnumSwitchMapping$0[ConnectState.CONNECTED.ordinal()] = 1;
            $EnumSwitchMapping$0[ConnectState.DISCONNECTED.ordinal()] = 2;
            $EnumSwitchMapping$0[ConnectState.CONNECTING.ordinal()] = 3;
            $EnumSwitchMapping$0[ConnectState.CONNECTFAIL.ordinal()] = 4;
        }
    }

    public static final /* synthetic */ RobotOpenSdk.Config access$getConfig$p(AliyunIot aliyunIot) {
        RobotOpenSdk.Config config = aliyunIot.config;
        if (config == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        return config;
    }

    /* renamed from: getOnConnectListener$robot_open_sdk_release, reason: from getter */
    public final IRemoteConnectStateListener getOnConnectListener() {
        return this.onConnectListener;
    }

    public final void setOnConnectListener$robot_open_sdk_release(IRemoteConnectStateListener iRemoteConnectStateListener) {
        this.onConnectListener = iRemoteConnectStateListener;
    }

    public final HashMap<String, Long> getWhiteDevices() {
        return this.whiteDevices;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyConnectByIot(ConnectState p1) {
        IRemoteConnectStateListener iRemoteConnectStateListener;
        if (p1 != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[p1.ordinal()];
            if (i == 1) {
                IRemoteConnectStateListener iRemoteConnectStateListener2 = this.onConnectListener;
                if (iRemoteConnectStateListener2 != null) {
                    iRemoteConnectStateListener2.onConnectState(RemoteConnectState.CONNECTED);
                    return;
                }
                return;
            }
            if (i == 2) {
                IRemoteConnectStateListener iRemoteConnectStateListener3 = this.onConnectListener;
                if (iRemoteConnectStateListener3 != null) {
                    iRemoteConnectStateListener3.onConnectState(RemoteConnectState.DISCONNECTED);
                    return;
                }
                return;
            }
            if (i != 3) {
                if (i == 4 && (iRemoteConnectStateListener = this.onConnectListener) != null) {
                    iRemoteConnectStateListener.onConnectState(RemoteConnectState.DISCONNECTED);
                    return;
                }
                return;
            }
            IRemoteConnectStateListener iRemoteConnectStateListener4 = this.onConnectListener;
            if (iRemoteConnectStateListener4 != null) {
                iRemoteConnectStateListener4.onConnectState(RemoteConnectState.CONNECTEDING);
                return;
            }
            return;
        }
        IRemoteConnectStateListener iRemoteConnectStateListener5 = this.onConnectListener;
        if (iRemoteConnectStateListener5 != null) {
            iRemoteConnectStateListener5.onConnectState(RemoteConnectState.DISCONNECTED);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isNetActive() {
        Object systemService;
        try {
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            systemService = context.getSystemService("connectivity");
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "isNetActive : " + Log.getStackTraceString(e));
        }
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        NetworkInfo[] allNetworkInfo = ((ConnectivityManager) systemService).getAllNetworkInfo();
        if (allNetworkInfo != null) {
            for (NetworkInfo it : allNetworkInfo) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void init(Context context, RobotOpenSdk.Config config, ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Pdlog.m3273d(this.TAG, "init : config = " + config + "; ");
        this.config = config;
        this.context = context;
        initSub();
        initIot(callBack);
    }

    private final void initSub() {
        RobotOpenSdk.Config config = this.config;
        if (config == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        String productKey = config.getProductKey();
        RobotOpenSdk.Config config2 = this.config;
        if (config2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        IotSubBeeperTopic iotSubBeeperTopic = new IotSubBeeperTopic(productKey, config2.getDeviceName(), this);
        this.subTopics.put(iotSubBeeperTopic.getTopic(), iotSubBeeperTopic);
        RobotOpenSdk.Config config3 = this.config;
        if (config3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        String productKey2 = config3.getProductKey();
        RobotOpenSdk.Config config4 = this.config;
        if (config4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        IotSubSdkTopic iotSubSdkTopic = new IotSubSdkTopic(productKey2, config4.getDeviceName(), this);
        this.subTopics.put(iotSubSdkTopic.getTopic(), iotSubSdkTopic);
        RobotOpenSdk.Config config5 = this.config;
        if (config5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        String productKey3 = config5.getProductKey();
        RobotOpenSdk.Config config6 = this.config;
        if (config6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        IotSubGroupTopic iotSubGroupTopic = new IotSubGroupTopic(productKey3, config6.getDeviceName(), this);
        this.subTopics.put(iotSubGroupTopic.getTopic(), iotSubGroupTopic);
        RobotOpenSdk.Config config7 = this.config;
        if (config7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        String productKey4 = config7.getProductKey();
        RobotOpenSdk.Config config8 = this.config;
        if (config8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        IotSubSeviceTopic iotSubSeviceTopic = new IotSubSeviceTopic(productKey4, config8.getDeviceName());
        this.subTopics.put(iotSubSeviceTopic.getTopic(), iotSubSeviceTopic);
        RobotOpenSdk.Config config9 = this.config;
        if (config9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        String productKey5 = config9.getProductKey();
        RobotOpenSdk.Config config10 = this.config;
        if (config10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        IotSubDisinfectionTopic iotSubDisinfectionTopic = new IotSubDisinfectionTopic(productKey5, config10.getDeviceName(), this);
        this.subTopics.put(iotSubDisinfectionTopic.getTopic(), iotSubDisinfectionTopic);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void initIot(ICallback callBack) {
        if (!this.isIniting && !this.isInit && !this.isRelease) {
            this.isIniting = true;
            MqttConfigure.automaticReconnect = false;
            DeviceInfo deviceInfo = new DeviceInfo();
            RobotOpenSdk.Config config = this.config;
            if (config == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            deviceInfo.deviceName = config.getDeviceName();
            RobotOpenSdk.Config config2 = this.config;
            if (config2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            deviceInfo.productKey = config2.getProductKey();
            RobotOpenSdk.Config config3 = this.config;
            if (config3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            deviceInfo.deviceSecret = config3.getDeviceSecret();
            HashMap hashMap = new HashMap();
            LinkKitInitParams linkKitInitParams = new LinkKitInitParams();
            RobotOpenSdk.Config config4 = this.config;
            if (config4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            String productKey = config4.getProductKey();
            RobotOpenSdk.Config config5 = this.config;
            if (config5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            String deviceName = config5.getDeviceName();
            RobotOpenSdk.Config config6 = this.config;
            if (config6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            IoTMqttClientConfig ioTMqttClientConfig = new IoTMqttClientConfig(productKey, deviceName, config6.getDeviceSecret());
            linkKitInitParams.deviceInfo = deviceInfo;
            linkKitInitParams.propertyValues = hashMap;
            linkKitInitParams.mqttClientConfig = ioTMqttClientConfig;
            linkKitInitParams.ioTDMConfig = new IoTDMConfig();
            linkKitInitParams.ioTDMConfig.enableLocalCommunication = false;
            linkKitInitParams.ioTDMConfig.enableGateway = false;
            StringBuilder sb = new StringBuilder();
            RobotOpenSdk.Config config7 = this.config;
            if (config7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            sb.append(config7.getProductKey());
            sb.append(".iot-as-mqtt.");
            RobotOpenSdk.Config config8 = this.config;
            if (config8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            sb.append(config8.getOriginal());
            sb.append(".aliyuncs.com:1883");
            MqttConfigure.mqttHost = sb.toString();
            ILinkKit linkKit = LinkKit.getInstance();
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            linkKit.init(context, linkKitInitParams, new AliyunIot$initIot$1(this, callBack));
            setNetworkCallback();
            return;
        }
        Pdlog.m3274e(this.TAG, "initIot isIniting = " + this.isIniting + " , isInit = " + this.isInit + " , isRelease = " + this.isRelease);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reconnectIfNeed() {
        Pdlog.m3273d(this.TAG, "reconnectIfNeed isInit = " + this.isInit);
        if (this.isRelease) {
            Pdlog.m3274e(this.TAG, "responseMsg : isRelease");
            return;
        }
        if (this.isInit) {
            if ((this.connectState == ConnectState.CONNECTFAIL || this.connectState == ConnectState.DISCONNECTED) && !tryReconnectIotTask()) {
                reInit();
                return;
            }
            return;
        }
        initIot(null);
    }

    private final synchronized void setNetworkCallback() {
        if (this.networkCallback == null) {
            this.networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.pudutech.robot.opensdk.aliyun.AliyunIot$setNetworkCallback$1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    String str;
                    boolean z;
                    String str2;
                    super.onAvailable(network);
                    str = AliyunIot.this.TAG;
                    Pdlog.m3273d(str, "networkCallback onAvailable");
                    z = AliyunIot.this.isRelease;
                    if (z) {
                        str2 = AliyunIot.this.TAG;
                        Pdlog.m3273d(str2, "networkCallback onAvailable : isRelease ");
                    } else {
                        AliyunIot.this.reconnectIfNeed();
                    }
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    String str;
                    super.onLost(network);
                    str = AliyunIot.this.TAG;
                    Pdlog.m3273d(str, "networkCallback onLost");
                }
            };
            NetworkRequest build = new NetworkRequest.Builder().build();
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            Object systemService = context.getSystemService("connectivity");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
            ConnectivityManager.NetworkCallback networkCallback = this.networkCallback;
            if (networkCallback == null) {
                Intrinsics.throwNpe();
            }
            connectivityManager.registerNetworkCallback(build, networkCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reInit() {
        Pdlog.m3273d(this.TAG, "reInit ");
        if (this.isIniting || this.isRelease || this.isDestroyIot) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.iotWork, null, new AliyunIot$reInit$1(this, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean tryReconnectIotTask() {
        Pdlog.m3273d(this.TAG, "tryReconnectIotTask ");
        this.handler.removeMessages(this.RETRY_CONNECT);
        if (this.isRelease) {
            return false;
        }
        try {
            Method declaredMethod = PersistentNet.class.getDeclaredMethod("a", new Class[0]);
            Intrinsics.checkExpressionValueIsNotNull(declaredMethod, "pClass.getDeclaredMethod(\"a\")");
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(PersistentNet.getInstance(), new Object[0]);
            if (invoke == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.aliyun.alink.linksdk.channel.core.persistent.mqtt.b");
            }
            C0978b c0978b = (C0978b) invoke;
            if (c0978b.m434d()) {
                Pdlog.m3273d(this.TAG, "tryReconnectIot ,already connected");
                return true;
            }
            IMqttAsyncClient m433c = c0978b.m433c();
            if (m433c == null) {
                throw new TypeCastException("null cannot be cast to non-null type org.eclipse.paho.client.mqttv3.MqttAsyncClient");
            }
            ((MqttAsyncClient) m433c).reconnect();
            this.handler.sendEmptyMessageDelayed(this.RETRY_CONNECT, 10000L);
            return true;
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "tryReconnectIot : " + Log.getStackTraceString(e));
            return false;
        }
    }

    private final void unRegisterNetworkCallback() {
        Pdlog.m3273d(this.TAG, "unRegisterNetworkCallback");
        if (this.networkCallback != null) {
            try {
                Context context = this.context;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                Object systemService = context != null ? context.getSystemService("connectivity") : null;
                if (systemService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
                }
                ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
                if (connectivityManager != null) {
                    connectivityManager.unregisterNetworkCallback(this.networkCallback);
                }
            } catch (Exception e) {
                Pdlog.m3273d(this.TAG, Log.getStackTraceString(e));
            }
        }
        this.networkCallback = (ConnectivityManager.NetworkCallback) null;
    }

    public final void setBindCode$robot_open_sdk_release(final IGenBindCodeCallBack callBack) {
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        if (this.isRelease || !this.isEnable) {
            Pdlog.m3273d(this.TAG, "setBindCode : isRelease = " + this.isRelease + " , isEnable = " + this.isEnable);
            return;
        }
        HashMap hashMap = new HashMap();
        String str = GenRandomUtils.getRandom$default(SDKConfig.getBindCodeLength(), false, 2, null) + ":";
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        RobotOpenSdk.Config config = this.config;
        if (config == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        sb.append(config.getDeviceName());
        String sb2 = sb.toString();
        hashMap.put("bind_code", new ValueWrapper(sb2));
        Pdlog.m3273d(this.TAG, "setBindCode : code = " + sb2 + "; ");
        final BindCodeData bindCodeData = new BindCodeData(sb2, System.currentTimeMillis() + ((long) 300000));
        try {
            ILinkKit linkKit = LinkKit.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(linkKit, "LinkKit.getInstance()");
            linkKit.getDeviceThing().thingPropertyPost(hashMap, new IPublishResourceListener() { // from class: com.pudutech.robot.opensdk.aliyun.AliyunIot$setBindCode$1
                @Override // com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener
                public void onSuccess(String resID, Object any) {
                    String str2;
                    String str3;
                    str2 = AliyunIot.this.TAG;
                    Pdlog.m3273d(str2, "onSuccess : resID = " + resID + "; any = " + any + "; ");
                    BindCodeData bindCodeData2 = bindCodeData;
                    bindCodeData2.setCode(bindCodeData2.getCode() + ':' + AliyunIot.access$getConfig$p(AliyunIot.this).getOriginal());
                    str3 = AliyunIot.this.TAG;
                    Pdlog.m3273d(str3, "onSuccess : " + bindCodeData);
                    callBack.onSuccess(bindCodeData);
                }

                @Override // com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener
                public void onError(String resID, AError error) {
                    String str2;
                    str2 = AliyunIot.this.TAG;
                    Pdlog.m3274e(str2, "reportProperty onError() resId=" + resID + ", error=" + error);
                    callBack.onFailed(new Exception(String.valueOf(error)));
                }
            });
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "setBindCode : " + Log.getStackTraceString(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void subscribeTopic() {
        Pdlog.m3273d(this.TAG, "subscribeTopic");
        for (Map.Entry<String, ISubTopic> entry : this.subTopics.entrySet()) {
            LinkKit.getInstance().subscribe(entry.getValue().getSubRequest(), (IConnectSubscribeListener) entry.getValue());
        }
        IotShadow.INSTANCE.initShadow();
    }

    public static /* synthetic */ void responseMsg$robot_open_sdk_release$default(AliyunIot aliyunIot, MsgContext msgContext, IBody iBody, ICallback iCallback, int i, Object obj) {
        if ((i & 4) != 0) {
            iCallback = (ICallback) null;
        }
        aliyunIot.responseMsg$robot_open_sdk_release(msgContext, iBody, iCallback);
    }

    public final void responseMsg$robot_open_sdk_release(MsgContext<?> msgContext, IBody body, final ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(msgContext, "msgContext");
        Intrinsics.checkParameterIsNotNull(body, "body");
        if (this.isRelease || !this.isEnable) {
            Pdlog.m3273d(this.TAG, "responseMsg : isRelease = " + this.isRelease + " , isEnable = " + this.isEnable);
            return;
        }
        Pdlog.m3273d(this.TAG, "responseMsg : msgContext = " + msgContext + "; body = " + body + "; callBack = " + callBack + "; ");
        LinkKit.getInstance().publish(genResp(msgContext, body), new IConnectSendListener() { // from class: com.pudutech.robot.opensdk.aliyun.AliyunIot$responseMsg$1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest request, AResponse response) {
                String str;
                Intrinsics.checkParameterIsNotNull(request, "request");
                Intrinsics.checkParameterIsNotNull(response, "response");
                str = AliyunIot.this.TAG;
                Pdlog.m3273d(str, "responseMsg onResponse() success");
                ICallback iCallback = callBack;
                if (iCallback != null) {
                    ICallback.DefaultImpls.onSuccess$default(iCallback, null, 1, null);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest request, AError error) {
                String str;
                Intrinsics.checkParameterIsNotNull(request, "request");
                Intrinsics.checkParameterIsNotNull(error, "error");
                str = AliyunIot.this.TAG;
                Pdlog.m3274e(str, "responseMsg onFailure() request=" + request + ", errorCode=" + error.getCode() + ", errorMsg=" + error.getMsg());
                ICallback iCallback = callBack;
                if (iCallback != null) {
                    iCallback.onFailed(new Exception(error.toString()));
                }
            }
        });
    }

    public final void pub$robot_open_sdk_release(String target, IPubMsg msg, ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (this.isRelease || !this.isEnable) {
            Pdlog.m3273d(this.TAG, "pub : isRelease = " + this.isRelease + " , isEnable = " + this.isEnable);
            return;
        }
        if (target.length() > 0) {
            pubTarget(target, msg, callBack);
            return;
        }
        if (!(msg instanceof BaseNotifyPub)) {
            if (msg instanceof IPubServiceMsg) {
                pubTarget(target, msg, callBack);
                return;
            } else {
                Pdlog.m3274e(this.TAG, "pub : no target ???");
                return;
            }
        }
        for (String str : ((BaseNotifyPub) msg).getNotifyTarget()) {
            Pdlog.m3273d(this.TAG, "pub BaseNotifyPub , target = " + str);
            pubTarget(str, msg, callBack);
        }
    }

    private final void pubTarget(String target, final IPubMsg msg, final ICallback callBack) {
        RobotOpenSdk.Config config = this.config;
        if (config == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        String productKey = config.getProductKey();
        RobotOpenSdk.Config config2 = this.config;
        if (config2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        String deviceName = config2.getDeviceName();
        ShadowStateValue shadowConfig = IotShadow.INSTANCE.getShadowConfig();
        final IotRequest iotRequest = new IotRequest(target, productKey, deviceName, shadowConfig != null ? shadowConfig.getGroupId() : null, msg, new IotRequest.CallBack() { // from class: com.pudutech.robot.opensdk.aliyun.AliyunIot$pubTarget$iotRequest$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v2, types: [com.pudutech.robot.opensdk.interf.IBody] */
            @Override // com.pudutech.robot.opensdk.aliyun.IotRequest.CallBack
            public void onSuccess(String msgStr) {
                String str;
                Intrinsics.checkParameterIsNotNull(msgStr, "msgStr");
                BaseMsgBean<?> baseMsgBean = (BaseMsgBean) null;
                try {
                    baseMsgBean = msg.parseObj(msgStr);
                } catch (Exception unused) {
                    str = AliyunIot.this.TAG;
                    Pdlog.m3273d(str, "onSuccess : msgStr = " + msgStr + "; ");
                }
                if (baseMsgBean != null) {
                    ICallback iCallback = callBack;
                    if (iCallback != 0) {
                        iCallback.onSuccess(baseMsgBean.getBody());
                        return;
                    }
                    return;
                }
                ICallback iCallback2 = callBack;
                if (iCallback2 != null) {
                    ICallback.DefaultImpls.onSuccess$default(iCallback2, null, 1, null);
                }
            }

            @Override // com.pudutech.robot.opensdk.aliyun.IotRequest.CallBack
            public void onFailed(Exception e) {
                Intrinsics.checkParameterIsNotNull(e, "e");
                ICallback iCallback = callBack;
                if (iCallback != null) {
                    iCallback.onFailed(e);
                }
            }
        });
        if (iotRequest.needWaitResp()) {
            Iterator<T> it = this.waitRespReqs.iterator();
            while (it.hasNext()) {
                ((IotRequest) it.next()).stopRetryIfNeed$robot_open_sdk_release(target, msg);
            }
            this.waitRespReqs.add(iotRequest);
            iotRequest.setOnCallbackFinish(new Function0<Unit>() { // from class: com.pudutech.robot.opensdk.aliyun.AliyunIot$pubTarget$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    CopyOnWriteArraySet copyOnWriteArraySet;
                    copyOnWriteArraySet = AliyunIot.this.waitRespReqs;
                    copyOnWriteArraySet.remove(iotRequest);
                }
            });
        }
        iotRequest.doRequest();
    }

    private final MqttPublishRequest genResp(MsgContext<?> msgContext, IBody body) {
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.isRPC = false;
        mqttPublishRequest.qos = 0;
        mqttPublishRequest.msgId = String.valueOf(IDGenerater.generateId());
        if (Intrinsics.areEqual(msgContext.getRole(), Constant.INSTANCE.getROLE_SDK())) {
            Constant constant = Constant.INSTANCE;
            RobotOpenSdk.Config config = this.config;
            if (config == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            String productKey = config.getProductKey();
            RobotOpenSdk.Config config2 = this.config;
            if (config2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            mqttPublishRequest.topic = constant.pubSdkTopicTemplate(productKey, config2.getDeviceName());
        } else if (Intrinsics.areEqual(msgContext.getRole(), Constant.INSTANCE.getROLE_BEEPER())) {
            Constant constant2 = Constant.INSTANCE;
            RobotOpenSdk.Config config3 = this.config;
            if (config3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            String productKey2 = config3.getProductKey();
            RobotOpenSdk.Config config4 = this.config;
            if (config4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            mqttPublishRequest.topic = constant2.pubBeeperTopicTemplate(productKey2, config4.getDeviceName());
        } else if (Intrinsics.areEqual(msgContext.getRole(), Constant.INSTANCE.getROLE_DISINFECTION())) {
            Constant constant3 = Constant.INSTANCE;
            RobotOpenSdk.Config config5 = this.config;
            if (config5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            String productKey3 = config5.getProductKey();
            RobotOpenSdk.Config config6 = this.config;
            if (config6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            }
            mqttPublishRequest.topic = constant3.pubDisinfectionTopicTemplate(productKey3, config6.getDeviceName());
        }
        String msgType = msgContext.getMsgType();
        RobotOpenSdk.Config config7 = this.config;
        if (config7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        String deviceName = config7.getDeviceName();
        String target = msgContext.getTarget();
        String msgId = msgContext.getMsgId();
        ShadowStateValue shadowConfig = IotShadow.INSTANCE.getShadowConfig();
        mqttPublishRequest.payloadObj = this.gson.toJson(new BaseMsgBean(msgType, deviceName, target, msgId, body, shadowConfig != null ? shadowConfig.getGroupId() : null));
        Pdlog.m3273d(this.TAG, "genResp : payloadObj = " + mqttPublishRequest.payloadObj);
        return mqttPublishRequest;
    }

    public final synchronized void release() {
        Pdlog.m3273d(this.TAG, "release ");
        this.isRelease = true;
        this.handler.removeMessages(this.RETRY_CONNECT);
        LinkKit.getInstance().unRegisterOnPushListener(this.iotNotifyListener);
        Iterator<T> it = this.waitRespReqs.iterator();
        while (it.hasNext()) {
            ((IotRequest) it.next()).destroy$robot_open_sdk_release();
        }
        this.waitRespReqs.clear();
        unRegisterNetworkCallback();
        this.isInit = false;
        this.isIniting = false;
        LinkKit.getInstance().deinit();
    }

    public final void enable(boolean b) {
        Pdlog.m3273d(this.TAG, "enable : b = " + b + "; ");
        this.isEnable = b;
        if (b) {
            this.iotNotifyListener.onConnectStateChange("", this.connectState);
        }
    }
}
