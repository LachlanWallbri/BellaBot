package com.pudutech.robot.opensdk;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.p022h2.api.Constraint;
import com.pudutech.base.Pdlog;
import com.pudutech.pdmqtt.config.AliyunMqttConfig;
import com.pudutech.pdmqtt.config.MosquittoMqttConfig;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack;
import com.pudutech.robot.opensdk.interf.IOnOpenSdkEventListener;
import com.pudutech.robot.opensdk.interf.IPubMsg;
import com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: RobotOpenSdk.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001CB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u000eJ\"\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(J\"\u0010)\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020*2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(J%\u0010+\u001a\u00020!\"\b\b\u0000\u0010,*\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H,0/H\u0000¢\u0006\u0002\b0J\u001a\u00101\u001a\u00020!2\u0006\u00102\u001a\u0002032\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(J\"\u00101\u001a\u00020!2\u0006\u00104\u001a\u00020\u00042\u0006\u00102\u001a\u0002032\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(J\u0006\u00105\u001a\u00020!J\u000e\u00106\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u000eJ&\u00107\u001a\u00020!2\n\u0010.\u001a\u0006\u0012\u0002\b\u00030/2\u0006\u00108\u001a\u00020-2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(J\u0006\u00109\u001a\u00020!J\u000e\u0010:\u001a\u00020!2\u0006\u0010;\u001a\u00020\u0016J\u0010\u0010<\u001a\u00020!2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u000e\u0010=\u001a\u00020!2\u0006\u0010>\u001a\u00020?J\u000e\u0010@\u001a\u00020!2\u0006\u0010A\u001a\u00020BR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0016@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0012\"\u0004\b\u001e\u0010\u001f¨\u0006D"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/RobotOpenSdk;", "", "()V", "TAG", "", "appContext", "Landroid/content/Context;", "<set-?>", "Lcom/pudutech/robot/opensdk/RemoteConnectState;", "connectState", "getConnectState", "()Lcom/pudutech/robot/opensdk/RemoteConnectState;", "connectStateListeners", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/opensdk/interf/IRemoteConnectStateListener;", "Lkotlin/collections/ArrayList;", "deviceId", "getDeviceId", "()Ljava/lang/String;", "iotAdapter", "Lcom/pudutech/robot/opensdk/IotAdapter;", "isEnable", "", "isInit", "()Z", "onConnectState", "onOpenSdkEventListener", "Lcom/pudutech/robot/opensdk/interf/IOnOpenSdkEventListener;", "original", "getOriginal", "setOriginal", "(Ljava/lang/String;)V", "addConnectStateListener", "", "listener", "connectAliyun", "context", "config", "Lcom/pudutech/pdmqtt/config/AliyunMqttConfig;", "callBack", "Lcom/pudutech/robot/opensdk/interf/ICallback;", "connectMosquitto", "Lcom/pudutech/pdmqtt/config/MosquittoMqttConfig;", "notifyEvent", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/robot/opensdk/interf/IBody;", "msgContext", "Lcom/pudutech/robot/opensdk/MsgContext;", "notifyEvent$robot_open_sdk_release", "publishMsg", "data", "Lcom/pudutech/robot/opensdk/interf/IPubMsg;", TypedValues.Attributes.S_TARGET, "release", "removeConnectStateListener", "responseMsg", "body", "setDebugLog", "setIotEnable", "b", "setOnEventListener", "setOnMQTTMessageListener", "onMqttMessage", "Lcom/pudutech/robot/opensdk/RobotOpenSdk$IOnMQTTMessage;", "startBind", "iGenBindCodeCallBack", "Lcom/pudutech/robot/opensdk/interf/IGenBindCodeCallBack;", "IOnMQTTMessage", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class RobotOpenSdk {
    private static Context appContext;
    private static boolean isInit;
    private static IOnOpenSdkEventListener onOpenSdkEventListener;
    public static final RobotOpenSdk INSTANCE = new RobotOpenSdk();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static IotAdapter iotAdapter = new IotAdapter();
    private static RemoteConnectState connectState = RemoteConnectState.DISCONNECTED;
    private static String deviceId = "";
    private static volatile boolean isEnable = true;
    private static final IRemoteConnectStateListener onConnectState = new IRemoteConnectStateListener() { // from class: com.pudutech.robot.opensdk.RobotOpenSdk$onConnectState$1
        @Override // com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener
        public void onConnectState(RemoteConnectState state) {
            String str;
            ArrayList arrayList;
            Intrinsics.checkParameterIsNotNull(state, "state");
            RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
            str = RobotOpenSdk.TAG;
            Pdlog.m3273d(str, "onConnectState : state = " + state + "; ");
            RobotOpenSdk robotOpenSdk2 = RobotOpenSdk.INSTANCE;
            RobotOpenSdk.connectState = state;
            RobotOpenSdk robotOpenSdk3 = RobotOpenSdk.INSTANCE;
            arrayList = RobotOpenSdk.connectStateListeners;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((IRemoteConnectStateListener) it.next()).onConnectState(state);
            }
        }
    };
    private static final ArrayList<IRemoteConnectStateListener> connectStateListeners = new ArrayList<>();
    private static String original = "";

    /* compiled from: RobotOpenSdk.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/RobotOpenSdk$IOnMQTTMessage;", "", "onReceive", "", "topic", "", MqttServiceConstants.PAYLOAD, "onSend", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public interface IOnMQTTMessage {
        void onReceive(String topic, String payload);

        void onSend(String topic, String payload);
    }

    public final void setDebugLog() {
    }

    private RobotOpenSdk() {
    }

    public final boolean isInit() {
        return isInit;
    }

    public final RemoteConnectState getConnectState() {
        return connectState;
    }

    public final String getDeviceId() {
        return deviceId;
    }

    public final String getOriginal() {
        return original;
    }

    public final void setOriginal(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        original = str;
    }

    public static /* synthetic */ boolean connectAliyun$default(RobotOpenSdk robotOpenSdk, Context context, AliyunMqttConfig aliyunMqttConfig, ICallback iCallback, int i, Object obj) {
        if ((i & 4) != 0) {
            iCallback = (ICallback) null;
        }
        return robotOpenSdk.connectAliyun(context, aliyunMqttConfig, iCallback);
    }

    public final synchronized boolean connectAliyun(Context context, AliyunMqttConfig config, ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(config, "config");
        appContext = context;
        original = config.getOriginal();
        iotAdapter.setOnConnectListener$robot_open_sdk_release(onConnectState);
        iotAdapter.connect(context, config, callBack);
        isInit = true;
        deviceId = config.getDeviceName();
        return true;
    }

    public static /* synthetic */ boolean connectMosquitto$default(RobotOpenSdk robotOpenSdk, Context context, MosquittoMqttConfig mosquittoMqttConfig, ICallback iCallback, int i, Object obj) {
        if ((i & 4) != 0) {
            iCallback = (ICallback) null;
        }
        return robotOpenSdk.connectMosquitto(context, mosquittoMqttConfig, iCallback);
    }

    public final synchronized boolean connectMosquitto(Context context, MosquittoMqttConfig config, ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(config, "config");
        appContext = context;
        iotAdapter.setOnConnectListener$robot_open_sdk_release(onConnectState);
        iotAdapter.connect(context, config, callBack);
        isInit = true;
        deviceId = config.getDeviceName();
        return true;
    }

    public final void setIotEnable(boolean b) {
        Pdlog.m3273d(TAG, "enable : b = " + b);
        isEnable = b;
        iotAdapter.enable(b);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new RobotOpenSdk$setIotEnable$1(b, null), 2, null);
    }

    public final synchronized void release() {
        Pdlog.m3273d(TAG, "release ");
        iotAdapter.release();
        appContext = (Context) null;
        isInit = false;
    }

    public final void startBind(IGenBindCodeCallBack iGenBindCodeCallBack) {
        Intrinsics.checkParameterIsNotNull(iGenBindCodeCallBack, "iGenBindCodeCallBack");
        iotAdapter.setBindCode$robot_open_sdk_release(original, iGenBindCodeCallBack);
    }

    public final void addConnectStateListener(IRemoteConnectStateListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "addConnectStateListener : listener = " + listener + "; ");
        if (connectStateListeners.contains(listener)) {
            return;
        }
        connectStateListeners.add(listener);
    }

    public final void removeConnectStateListener(IRemoteConnectStateListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "removeConnectStateListener : listener = " + listener + "; ");
        connectStateListeners.remove(listener);
    }

    public final void setOnEventListener(IOnOpenSdkEventListener onOpenSdkEventListener2) {
        onOpenSdkEventListener = onOpenSdkEventListener2;
    }

    public final void setOnMQTTMessageListener(IOnMQTTMessage onMqttMessage) {
        Intrinsics.checkParameterIsNotNull(onMqttMessage, "onMqttMessage");
        iotAdapter.setOnMQTTMessage(onMqttMessage);
    }

    public final <T extends IBody> void notifyEvent$robot_open_sdk_release(MsgContext<T> msgContext) {
        Intrinsics.checkParameterIsNotNull(msgContext, "msgContext");
        Pdlog.m3273d(TAG, "notifyEvent : " + msgContext);
        IOnOpenSdkEventListener iOnOpenSdkEventListener = onOpenSdkEventListener;
        if (iOnOpenSdkEventListener != null) {
            iOnOpenSdkEventListener.onEvent(msgContext);
        }
    }

    /* compiled from: RobotOpenSdk.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/RobotOpenSdk$Config;", "", "productKey", "", "deviceName", Constraint.PARAM_DEVICE_SECRET, "original", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDeviceName", "()Ljava/lang/String;", "setDeviceName", "(Ljava/lang/String;)V", "getDeviceSecret", "setDeviceSecret", "getOriginal", "setOriginal", "getProductKey", "setProductKey", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class Config {
        private String deviceName;
        private String deviceSecret;
        private String original;
        private String productKey;

        public static /* synthetic */ Config copy$default(Config config, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = config.productKey;
            }
            if ((i & 2) != 0) {
                str2 = config.deviceName;
            }
            if ((i & 4) != 0) {
                str3 = config.deviceSecret;
            }
            if ((i & 8) != 0) {
                str4 = config.original;
            }
            return config.copy(str, str2, str3, str4);
        }

        /* renamed from: component1, reason: from getter */
        public final String getProductKey() {
            return this.productKey;
        }

        /* renamed from: component2, reason: from getter */
        public final String getDeviceName() {
            return this.deviceName;
        }

        /* renamed from: component3, reason: from getter */
        public final String getDeviceSecret() {
            return this.deviceSecret;
        }

        /* renamed from: component4, reason: from getter */
        public final String getOriginal() {
            return this.original;
        }

        public final Config copy(String productKey, String deviceName, String deviceSecret, String original) {
            Intrinsics.checkParameterIsNotNull(productKey, "productKey");
            Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
            Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
            Intrinsics.checkParameterIsNotNull(original, "original");
            return new Config(productKey, deviceName, deviceSecret, original);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Config)) {
                return false;
            }
            Config config = (Config) other;
            return Intrinsics.areEqual(this.productKey, config.productKey) && Intrinsics.areEqual(this.deviceName, config.deviceName) && Intrinsics.areEqual(this.deviceSecret, config.deviceSecret) && Intrinsics.areEqual(this.original, config.original);
        }

        public int hashCode() {
            String str = this.productKey;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.deviceName;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.deviceSecret;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.original;
            return hashCode3 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            return "Config(productKey=" + this.productKey + ", deviceName=" + this.deviceName + ", deviceSecret=" + this.deviceSecret + ", original=" + this.original + ")";
        }

        public Config(String productKey, String deviceName, String deviceSecret, String original) {
            Intrinsics.checkParameterIsNotNull(productKey, "productKey");
            Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
            Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
            Intrinsics.checkParameterIsNotNull(original, "original");
            this.productKey = productKey;
            this.deviceName = deviceName;
            this.deviceSecret = deviceSecret;
            this.original = original;
        }

        public final String getProductKey() {
            return this.productKey;
        }

        public final void setProductKey(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.productKey = str;
        }

        public final String getDeviceName() {
            return this.deviceName;
        }

        public final void setDeviceName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.deviceName = str;
        }

        public final String getDeviceSecret() {
            return this.deviceSecret;
        }

        public final void setDeviceSecret(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.deviceSecret = str;
        }

        public /* synthetic */ Config(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i & 8) != 0 ? "cn-shanghai" : str4);
        }

        public final String getOriginal() {
            return this.original;
        }

        public final void setOriginal(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.original = str;
        }
    }

    public static /* synthetic */ void responseMsg$default(RobotOpenSdk robotOpenSdk, MsgContext msgContext, IBody iBody, ICallback iCallback, int i, Object obj) {
        if ((i & 4) != 0) {
            iCallback = (ICallback) null;
        }
        robotOpenSdk.responseMsg(msgContext, iBody, iCallback);
    }

    public final void responseMsg(MsgContext<?> msgContext, IBody body, ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(msgContext, "msgContext");
        Intrinsics.checkParameterIsNotNull(body, "body");
        iotAdapter.responseMsg$robot_open_sdk_release(msgContext, body, callBack);
    }

    public static /* synthetic */ void publishMsg$default(RobotOpenSdk robotOpenSdk, String str, IPubMsg iPubMsg, ICallback iCallback, int i, Object obj) {
        if ((i & 4) != 0) {
            iCallback = (ICallback) null;
        }
        robotOpenSdk.publishMsg(str, iPubMsg, iCallback);
    }

    public final void publishMsg(String target, IPubMsg data, ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(data, "data");
        iotAdapter.pub$robot_open_sdk_release(target, data, callBack);
    }

    public static /* synthetic */ void publishMsg$default(RobotOpenSdk robotOpenSdk, IPubMsg iPubMsg, ICallback iCallback, int i, Object obj) {
        if ((i & 2) != 0) {
            iCallback = (ICallback) null;
        }
        robotOpenSdk.publishMsg(iPubMsg, iCallback);
    }

    public final void publishMsg(IPubMsg data, ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        publishMsg("", data, callBack);
    }
}
