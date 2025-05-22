package com.pudutech.bumblebee.presenter.robot_open_task;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.util.Log;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.battery.PowerState;
import com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener;
import com.pudutech.bumblebee.presenter.net.IOTHubServerManager;
import com.pudutech.bumblebee.presenter.net.IotServerApiManager;
import com.pudutech.bumblebee.presenter.net.req.AliyunIotSecretReq;
import com.pudutech.bumblebee.presenter.net.req.IotHostReq;
import com.pudutech.bumblebee.presenter.net.resp.AliyunIotSecretResp;
import com.pudutech.bumblebee.presenter.net.resp.HttpResult;
import com.pudutech.bumblebee.presenter.net.resp.IotRegionResp;
import com.pudutech.bumblebee.presenter.net.resp.TokenBean;
import com.pudutech.bumblebee.presenter.net.resp.TokenResp;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.leaselib.utils.EncryptUtils;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.pdmqtt.config.AliyunMqttConfig;
import com.pudutech.pdmqtt.config.BaseKt;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.bean.pub.PubRobotPowerData;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import com.pudutech.robot.opensdk.interf.IOnOpenSdkEventListener;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: RobotOpenHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000k\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015*\u00015\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u00107\u001a\u000208H\u0002J\u0006\u00109\u001a\u000208J\b\u0010:\u001a\u000208H\u0002J\n\u0010;\u001a\u0004\u0018\u00010<H\u0002J\b\u0010=\u001a\u00020\u0004H\u0002J\b\u0010>\u001a\u00020\u0004H\u0002J\b\u0010?\u001a\u00020\u0004H\u0002J\u0016\u0010@\u001a\u0002082\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&J\b\u0010A\u001a\u000208H\u0002J\b\u0010B\u001a\u000208H\u0002J\u000e\u0010C\u001a\u0002082\u0006\u0010D\u001a\u00020\u0016J\b\u0010E\u001a\u000208H\u0002J\b\u0010F\u001a\u000208H\u0002J\u0010\u0010G\u001a\u0002082\u0006\u0010H\u001a\u00020<H\u0002J\b\u0010I\u001a\u000208H\u0002J\u0010\u0010J\u001a\u0002082\u0006\u0010K\u001a\u00020\u0004H\u0002J\u0018\u0010L\u001a\u0002082\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010H\u001a\u00020<H\u0002J\u0018\u0010M\u001a\u0002082\u0006\u0010N\u001a\u00020\u00042\u0006\u0010O\u001a\u00020\u0004H\u0002J\b\u0010P\u001a\u000208H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R&\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR&\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010*\u001a\u00020+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010.\u001a\u00020/¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0004\n\u0002\u00106¨\u0006Q"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotOpenHelper;", "", "()V", "AES_PADDING", "", "DEFAULT_REGION", "FILE_NAME", "IOT_PRODUCTKEY", "IOT_PRODUCTKEY_TEST", "IOT_SECRET_PATH", "IOT_SECRET_PATH_TEST", "IV", "", "KEY", "KEY_BEEPER_CALL_SWITCH", "KEY_LOCAL_HOST_SWITCH", "KEY_LOCAL_SERVER_SWITCH", "KEY_REGION", "TAG", "context", "Landroid/content/Context;", ES6Iterator.VALUE_PROPERTY, "", "isLocal", "()Z", "setLocal", "(Z)V", "isOpen", "setOpen", "isSdkInit", "localHost", "getLocalHost", "()Ljava/lang/String;", "setLocalHost", "(Ljava/lang/String;)V", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "onOpenSdkEventListener", "Lcom/pudutech/robot/opensdk/interf/IOnOpenSdkEventListener;", "reqHubServerDisposable", "Lio/reactivex/disposables/Disposable;", "reqServerDisposable", "robotMoveStateNotify", "Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotMoveStateNotify;", "getRobotMoveStateNotify", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotMoveStateNotify;", "robotPoseNotify", "Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotPoseNotify;", "getRobotPoseNotify", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotPoseNotify;", "sharedPreferences", "Landroid/content/SharedPreferences;", "systemBatteryListener", "com/pudutech/bumblebee/presenter/robot_open_task/RobotOpenHelper$systemBatteryListener$1", "Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotOpenHelper$systemBatteryListener$1;", "chechIsOpen", "", "close", "connectionLocalServer", "getConfigFromLocal", "Lcom/pudutech/pdmqtt/config/AliyunMqttConfig;", "getConfigPath", "getIotProductKey", "getRegion", "init", "initByServer", "notifyPowerChange", "open", "local", "recheckRegion", "robotOpenClose", "saveConfig", "config", "setNetworkCallback", "setRegion", OSSConfig.PARAM_REGION, "startInit", "startRegisterFromServer", "deviceName", "url", "unRegisterNetworkCallback", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotOpenHelper {
    private static final String AES_PADDING = "AES/CBC/PKCS5Padding";
    private static final String KEY = "AE3G3Mj9rdTNKil9";
    private static volatile Context context;
    private static boolean isLocal;
    private static boolean isOpen;
    private static volatile boolean isSdkInit;
    private static ConnectivityManager.NetworkCallback networkCallback;
    private static IOnOpenSdkEventListener onOpenSdkEventListener;
    private static volatile Disposable reqHubServerDisposable;
    private static volatile Disposable reqServerDisposable;
    private static SharedPreferences sharedPreferences;
    public static final RobotOpenHelper INSTANCE = new RobotOpenHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String KEY_BEEPER_CALL_SWITCH = KEY_BEEPER_CALL_SWITCH;
    private static final String KEY_BEEPER_CALL_SWITCH = KEY_BEEPER_CALL_SWITCH;
    private static final String KEY_LOCAL_SERVER_SWITCH = KEY_LOCAL_SERVER_SWITCH;
    private static final String KEY_LOCAL_SERVER_SWITCH = KEY_LOCAL_SERVER_SWITCH;
    private static final String KEY_LOCAL_HOST_SWITCH = KEY_LOCAL_HOST_SWITCH;
    private static final String KEY_LOCAL_HOST_SWITCH = KEY_LOCAL_HOST_SWITCH;
    private static final String KEY_REGION = KEY_REGION;
    private static final String KEY_REGION = KEY_REGION;
    private static final String DEFAULT_REGION = "cn-shanghai";
    private static final String IOT_SECRET_PATH = IOT_SECRET_PATH;
    private static final String IOT_SECRET_PATH = IOT_SECRET_PATH;
    private static final String IOT_PRODUCTKEY = IOT_PRODUCTKEY;
    private static final String IOT_PRODUCTKEY = IOT_PRODUCTKEY;
    private static final String IOT_SECRET_PATH_TEST = IOT_SECRET_PATH_TEST;
    private static final String IOT_SECRET_PATH_TEST = IOT_SECRET_PATH_TEST;
    private static final String IOT_PRODUCTKEY_TEST = IOT_PRODUCTKEY_TEST;
    private static final String IOT_PRODUCTKEY_TEST = IOT_PRODUCTKEY_TEST;
    private static final byte[] IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final String FILE_NAME = FILE_NAME;
    private static final String FILE_NAME = FILE_NAME;
    private static final RobotMoveStateNotify robotMoveStateNotify = new RobotMoveStateNotify();
    private static final RobotPoseNotify robotPoseNotify = new RobotPoseNotify();
    private static String localHost = "127.0.0.1";
    private static final RobotOpenHelper$systemBatteryListener$1 systemBatteryListener = new SystemBatteryListener() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$systemBatteryListener$1
        @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
        public void onStateChange(int powerPercent, PowerState powerState, ChargeState chargeState) {
            if (chargeState != null) {
                RobotOpenHelper.INSTANCE.notifyPowerChange();
            }
        }

        @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
        public void onPowerChange(int powerPercent) {
            RobotOpenHelper.INSTANCE.notifyPowerChange();
        }
    };

    private RobotOpenHelper() {
    }

    public static final /* synthetic */ Context access$getContext$p(RobotOpenHelper robotOpenHelper) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public static final /* synthetic */ IOnOpenSdkEventListener access$getOnOpenSdkEventListener$p(RobotOpenHelper robotOpenHelper) {
        IOnOpenSdkEventListener iOnOpenSdkEventListener = onOpenSdkEventListener;
        if (iOnOpenSdkEventListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onOpenSdkEventListener");
        }
        return iOnOpenSdkEventListener;
    }

    public final RobotMoveStateNotify getRobotMoveStateNotify() {
        return robotMoveStateNotify;
    }

    public final RobotPoseNotify getRobotPoseNotify() {
        return robotPoseNotify;
    }

    public final boolean isOpen() {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 != null) {
            return sharedPreferences2.getBoolean(KEY_BEEPER_CALL_SWITCH, false);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setOpen(boolean z) {
        SharedPreferences.Editor edit;
        SharedPreferences.Editor putBoolean;
        isOpen = z;
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null || (edit = sharedPreferences2.edit()) == null || (putBoolean = edit.putBoolean(KEY_BEEPER_CALL_SWITCH, z)) == null) {
            return;
        }
        putBoolean.apply();
    }

    public final boolean isLocal() {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 != null) {
            return sharedPreferences2.getBoolean(KEY_LOCAL_SERVER_SWITCH, false);
        }
        return false;
    }

    private final void setLocal(boolean z) {
        SharedPreferences.Editor edit;
        SharedPreferences.Editor putBoolean;
        isLocal = z;
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null || (edit = sharedPreferences2.edit()) == null || (putBoolean = edit.putBoolean(KEY_LOCAL_SERVER_SWITCH, z)) == null) {
            return;
        }
        putBoolean.apply();
    }

    public final String getLocalHost() {
        String string;
        SharedPreferences sharedPreferences2 = sharedPreferences;
        return (sharedPreferences2 == null || (string = sharedPreferences2.getString(KEY_LOCAL_HOST_SWITCH, "127.0.0.1")) == null) ? "127.0.0.1" : string;
    }

    public final void setLocalHost(String value) {
        SharedPreferences.Editor edit;
        SharedPreferences.Editor putString;
        Intrinsics.checkParameterIsNotNull(value, "value");
        localHost = value;
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null || (edit = sharedPreferences2.edit()) == null || (putString = edit.putString(KEY_LOCAL_HOST_SWITCH, value)) == null) {
            return;
        }
        putString.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyPowerChange() {
        String name;
        int powerPercent = CoreDevices.INSTANCE.getBattery().getPowerPercent();
        ChargeState chargerState = CoreDevices.INSTANCE.getBattery().getChargerState();
        if (chargerState == null || (name = chargerState.name()) == null) {
            name = ChargeState.Idle.name();
        }
        RobotOpenSdk.INSTANCE.publishMsg(new PubRobotPowerData(powerPercent, name), new ICallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$notifyPowerChange$1
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onFailed(Exception e) {
                String str;
                Intrinsics.checkParameterIsNotNull(e, "e");
                RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                str = RobotOpenHelper.TAG;
                Pdlog.m3274e(str, "notifyPowerChange onFailed  ");
            }

            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onSuccess(IBody result) {
                String str;
                RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                str = RobotOpenHelper.TAG;
                Pdlog.m3274e(str, "notifyPowerChange onSuccess  ");
            }
        });
    }

    public final void init(Context context2, IOnOpenSdkEventListener onOpenSdkEventListener2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(onOpenSdkEventListener2, "onOpenSdkEventListener");
        context = context2;
        onOpenSdkEventListener = onOpenSdkEventListener2;
        sharedPreferences = context2.getSharedPreferences(FILE_NAME, 0);
        if (isOpen()) {
            Pdlog.m3273d(TAG, "init : isOpen , need start open ");
            open(isLocal());
        }
    }

    public final synchronized void close() {
        Pdlog.m3273d(TAG, "close ");
        setOpen(false);
        isSdkInit = false;
        RobotOpenSdk.INSTANCE.release();
        CoreDevices.INSTANCE.getBattery().removeListener(systemBatteryListener);
        robotMoveStateNotify.destroy();
        robotPoseNotify.destroy();
    }

    private final void robotOpenClose() {
        Pdlog.m3273d(TAG, "robotOpenClose ");
        if (RobotOpenSdk.INSTANCE.isInit()) {
            RobotOpenSdk.INSTANCE.setIotEnable(false);
        }
    }

    public final synchronized void open(boolean local) {
        Pdlog.m3273d(TAG, "open " + WifiUtil.INSTANCE.getMac());
        setOpen(true);
        setLocal(local);
        if (!isLocal()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RobotOpenHelper$open$1(null), 2, null);
        } else {
            connectionLocalServer();
        }
    }

    private final void connectionLocalServer() {
        Pdlog.m3273d(TAG, "connectionLocalServer ");
        if (isSdkInit) {
            Pdlog.m3274e(TAG, "startInit : sdk is inited");
            return;
        }
        if (!isOpen() || !isLocal()) {
            Pdlog.m3273d(TAG, "startInit : isOpen = false ");
            return;
        }
        isSdkInit = true;
        if (!RobotOpenSdk.INSTANCE.isInit()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RobotOpenHelper$connectionLocalServer$1(null), 2, null);
            return;
        }
        RobotOpenSdk.INSTANCE.setIotEnable(true);
        CoreDevices.INSTANCE.getBattery().addListener(systemBatteryListener);
        robotMoveStateNotify.init();
        robotPoseNotify.init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recheckRegion() {
        Pdlog.m3273d(TAG, "recheckRegion ");
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac != null) {
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = 0;
            IOTHubServerManager.ServerApi serverApi = IOTHubServerManager.INSTANCE.getServerApi();
            if (mac == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String upperCase = mac.toUpperCase();
            Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
            IOTHubServerManager.ServerApi.DefaultImpls.getEdgeHost$default(serverApi, null, new IotHostReq(upperCase), 1, null).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$recheckRegion$1$1
                @Override // io.reactivex.functions.Function
                public final Observable<Long> apply(Observable<Throwable> observable) {
                    Intrinsics.checkParameterIsNotNull(observable, "observable");
                    return observable.flatMap(new Function<T, ObservableSource<? extends R>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$recheckRegion$1$1.1
                        @Override // io.reactivex.functions.Function
                        public final Observable<Long> apply(Throwable throwable) {
                            String str;
                            String str2;
                            Intrinsics.checkParameterIsNotNull(throwable, "throwable");
                            Ref.IntRef.this.element++;
                            if ((throwable instanceof IOException) || Ref.IntRef.this.element > 8) {
                                RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                                str = RobotOpenHelper.TAG;
                                Pdlog.m3277w(str, "检测源失败，第" + Ref.IntRef.this.element + "次请求失败，正在重试，throwable = " + throwable);
                                return Observable.timer(8000L, TimeUnit.MILLISECONDS);
                            }
                            RobotOpenHelper robotOpenHelper2 = RobotOpenHelper.INSTANCE;
                            str2 = RobotOpenHelper.TAG;
                            Pdlog.m3277w(str2, "检测源失败，第" + Ref.IntRef.this.element + "次请求失败，不重试了，throwable = " + throwable);
                            return Observable.error(throwable);
                        }
                    });
                }
            }).subscribeOn(Schedulers.m3958io()).observeOn(Schedulers.m3958io()).subscribe(new Consumer<HttpResult<IotRegionResp>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$recheckRegion$1$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(HttpResult<IotRegionResp> httpResult) {
                    String str;
                    String region;
                    RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                    str = RobotOpenHelper.TAG;
                    Pdlog.m3273d(str, "recheckRegion " + httpResult);
                    if (httpResult.getCode() == 0) {
                        region = RobotOpenHelper.INSTANCE.getRegion();
                        IotRegionResp data = httpResult.getData();
                        String regionId = data != null ? data.getRegionId() : null;
                        String str2 = regionId;
                        if (str2 == null || StringsKt.isBlank(str2)) {
                            return;
                        }
                        if (region == null) {
                            RobotOpenHelper robotOpenHelper2 = RobotOpenHelper.INSTANCE;
                            region = RobotOpenHelper.DEFAULT_REGION;
                        }
                        if (!Intrinsics.areEqual(regionId, region)) {
                            Thread.sleep(10000L);
                            RobotOpenHelper.INSTANCE.setRegion(regionId);
                            RobotOpenHelper robotOpenHelper3 = RobotOpenHelper.INSTANCE;
                            RobotOpenHelper.isSdkInit = false;
                            RobotOpenSdk.INSTANCE.release();
                            if (RobotOpenHelper.INSTANCE.isOpen()) {
                                RobotOpenHelper.INSTANCE.open(false);
                            }
                        }
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$recheckRegion$1$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    String str;
                    RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                    str = RobotOpenHelper.TAG;
                    Pdlog.m3274e(str, "recheckRegion : " + Log.getStackTraceString(th));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getRegion() {
        String string;
        SharedPreferences sharedPreferences2 = sharedPreferences;
        return (sharedPreferences2 == null || (string = sharedPreferences2.getString(KEY_REGION, DEFAULT_REGION)) == null) ? DEFAULT_REGION : string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRegion(String region) {
        SharedPreferences.Editor edit;
        SharedPreferences.Editor putString;
        Pdlog.m3273d(TAG, "setRegion : region = " + region + "; ");
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null || (edit = sharedPreferences2.edit()) == null || (putString = edit.putString(KEY_REGION, region)) == null) {
            return;
        }
        putString.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AliyunMqttConfig getConfigFromLocal() {
        try {
            File file = new File(getConfigPath());
            if (!file.exists()) {
                return null;
            }
            Gson gson = new Gson();
            byte[] readBytes = FilesKt.readBytes(file);
            if (readBytes.length == 0) {
                return null;
            }
            byte[] bytes = KEY.getBytes(Charsets.UTF_8);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            byte[] decb = EncryptUtils.decryptAES(readBytes, bytes, "AES/CBC/PKCS5Padding", IV);
            Intrinsics.checkExpressionValueIsNotNull(decb, "decb");
            String str = new String(decb, Charsets.UTF_8);
            Pdlog.m3273d(TAG, "getConfigFromLocal : " + str);
            return (AliyunMqttConfig) gson.fromJson(new String(decb, Charsets.UTF_8), AliyunMqttConfig.class);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0022, code lost:
    
        if (r0.isDisposed() == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0011, code lost:
    
        if (r0.isDisposed() != false) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0024, code lost:
    
        com.pudutech.base.Pdlog.m3274e(com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper.TAG, "initByServer is requesting");
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002f, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void initByServer() {
        if (reqHubServerDisposable != null) {
            Disposable disposable = reqHubServerDisposable;
            if (disposable == null) {
                Intrinsics.throwNpe();
            }
        }
        if (reqServerDisposable != null) {
            Disposable disposable2 = reqServerDisposable;
            if (disposable2 == null) {
                Intrinsics.throwNpe();
            }
        }
        final String mac = WifiUtil.INSTANCE.getMac();
        Pdlog.m3273d(TAG, "initByServer : mac = " + mac);
        String str = mac;
        if (str == null || str.length() == 0) {
            setNetworkCallback();
            return;
        }
        IOTHubServerManager.ServerApi serverApi = IOTHubServerManager.INSTANCE.getServerApi();
        if (mac != null) {
            String upperCase = mac.toUpperCase();
            Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
            reqHubServerDisposable = IOTHubServerManager.ServerApi.DefaultImpls.getEdgeHost$default(serverApi, null, new IotHostReq(upperCase), 1, null).subscribeOn(Schedulers.m3958io()).observeOn(Schedulers.m3958io()).subscribe(new Consumer<HttpResult<IotRegionResp>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$initByServer$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(HttpResult<IotRegionResp> httpResult) {
                    String str2;
                    RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                    str2 = RobotOpenHelper.TAG;
                    boolean z = true;
                    Pdlog.m3273d(str2, "getEdgeHost " + httpResult);
                    if (httpResult.getCode() == 0) {
                        IotRegionResp data = httpResult.getData();
                        String regionId = data != null ? data.getRegionId() : null;
                        if (regionId != null && !StringsKt.isBlank(regionId)) {
                            z = false;
                        }
                        if (z) {
                            return;
                        }
                        RobotOpenHelper robotOpenHelper2 = RobotOpenHelper.INSTANCE;
                        IotRegionResp data2 = httpResult.getData();
                        if (data2 == null) {
                            Intrinsics.throwNpe();
                        }
                        String regionId2 = data2.getRegionId();
                        if (regionId2 == null) {
                            Intrinsics.throwNpe();
                        }
                        robotOpenHelper2.setRegion(regionId2);
                        String replace$default = StringsKt.replace$default(mac, ":", "", false, 4, (Object) null);
                        if (replace$default == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        String lowerCase = replace$default.toLowerCase();
                        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                        RobotOpenHelper robotOpenHelper3 = RobotOpenHelper.INSTANCE;
                        IotRegionResp data3 = httpResult.getData();
                        if (data3 == null) {
                            Intrinsics.throwNpe();
                        }
                        String endpoint = data3.getEndpoint();
                        if (endpoint == null) {
                            endpoint = "";
                        }
                        robotOpenHelper3.startRegisterFromServer(lowerCase, endpoint);
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$initByServer$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    String str2;
                    RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                    str2 = RobotOpenHelper.TAG;
                    Pdlog.m3274e(str2, "getEdgeHost : " + Log.getStackTraceString(th));
                    if (th instanceof IOException) {
                        RobotOpenHelper.INSTANCE.setNetworkCallback();
                    }
                }
            }, new Action() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$initByServer$3
                @Override // io.reactivex.functions.Action
                public final void run() {
                    RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                    RobotOpenHelper.reqHubServerDisposable = (Disposable) null;
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startRegisterFromServer(final String deviceName, final String url) {
        final String region = getRegion();
        reqServerDisposable = IOTHubServerManager.ServerApi.DefaultImpls.genToken$default(IOTHubServerManager.INSTANCE.getServerApi(), url + "/token", null, null, null, 14, null).flatMap(new Function<T, ObservableSource<? extends R>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$startRegisterFromServer$1
            @Override // io.reactivex.functions.Function
            public final Observable<HttpResult<AliyunIotSecretResp>> apply(TokenResp it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it.getCode() == 0 && it.getToken() != null) {
                    IOTHubServerManager.ServerApi serverApi = IOTHubServerManager.INSTANCE.getServerApi();
                    String str = url + "/api/v1/robot/register";
                    AliyunIotSecretReq aliyunIotSecretReq = new AliyunIotSecretReq(deviceName);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Bearer ");
                    TokenBean token = it.getToken();
                    if (token == null) {
                        Intrinsics.throwNpe();
                    }
                    sb.append(token.getAccessToken());
                    return IOTHubServerManager.ServerApi.DefaultImpls.getAliyunIOTSecret$default(serverApi, str, aliyunIotSecretReq, sb.toString(), null, 8, null);
                }
                Observable<HttpResult<AliyunIotSecretResp>> error = Observable.error(new Exception("token get failed"));
                Intrinsics.checkExpressionValueIsNotNull(error, "Observable.error(Exception(\"token get failed\"))");
                return error;
            }
        }).observeOn(Schedulers.m3958io()).subscribeOn(Schedulers.m3958io()).subscribe(new Consumer<HttpResult<AliyunIotSecretResp>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$startRegisterFromServer$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(HttpResult<AliyunIotSecretResp> httpResult) {
                String str;
                RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                str = RobotOpenHelper.TAG;
                Pdlog.m3273d(str, "initByServer : " + httpResult);
                if (httpResult.getCode() == 0 && httpResult.getData() != null) {
                    AliyunIotSecretResp data = httpResult.getData();
                    if (data == null) {
                        Intrinsics.throwNpe();
                    }
                    String productKey = data.getProductKey();
                    String str2 = deviceName;
                    AliyunIotSecretResp data2 = httpResult.getData();
                    if (data2 == null) {
                        Intrinsics.throwNpe();
                    }
                    AliyunMqttConfig aliyunMqttConfig = new AliyunMqttConfig(productKey, str2, data2.getDeviceSecret(), region);
                    Context access$getContext$p = RobotOpenHelper.access$getContext$p(RobotOpenHelper.INSTANCE);
                    if (access$getContext$p != null) {
                        RobotOpenHelper.INSTANCE.startInit(access$getContext$p, aliyunMqttConfig);
                        RobotOpenHelper.INSTANCE.saveConfig(aliyunMqttConfig);
                    }
                }
                RobotOpenHelper.INSTANCE.unRegisterNetworkCallback();
            }
        }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$startRegisterFromServer$3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                String str;
                RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                str = RobotOpenHelper.TAG;
                Pdlog.m3274e(str, "initByServer : " + Log.getStackTraceString(th));
                if (th instanceof IOException) {
                    RobotOpenHelper.INSTANCE.setNetworkCallback();
                }
            }
        }, new Action() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$startRegisterFromServer$4
            @Override // io.reactivex.functions.Action
            public final void run() {
                String str;
                RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                str = RobotOpenHelper.TAG;
                Pdlog.m3273d(str, "initByServer finish");
                RobotOpenHelper robotOpenHelper2 = RobotOpenHelper.INSTANCE;
                RobotOpenHelper.reqServerDisposable = (Disposable) null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unRegisterNetworkCallback() {
        Pdlog.m3273d(TAG, "unRegisterNetworkCallback");
        if (networkCallback != null) {
            try {
                Context context2 = context;
                if (context2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                }
                Object systemService = context2 != null ? context2.getSystemService("connectivity") : null;
                if (systemService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
                }
                ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
                if (connectivityManager != null) {
                    connectivityManager.unregisterNetworkCallback(networkCallback);
                }
            } catch (Exception e) {
                Pdlog.m3273d(TAG, Log.getStackTraceString(e));
            }
        }
        networkCallback = (ConnectivityManager.NetworkCallback) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNetworkCallback() {
        if (networkCallback == null) {
            networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$setNetworkCallback$1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    String str;
                    super.onAvailable(network);
                    RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                    str = RobotOpenHelper.TAG;
                    Pdlog.m3273d(str, "networkCallback onAvailable");
                    RobotOpenHelper.INSTANCE.initByServer();
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    String str;
                    super.onLost(network);
                    RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                    str = RobotOpenHelper.TAG;
                    Pdlog.m3273d(str, "networkCallback onLost");
                }
            };
            NetworkRequest build = new NetworkRequest.Builder().build();
            Context context2 = context;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            Object systemService = context2 != null ? context2.getSystemService("connectivity") : null;
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
            ConnectivityManager.NetworkCallback networkCallback2 = networkCallback;
            if (networkCallback2 == null) {
                Intrinsics.throwNpe();
            }
            connectivityManager.registerNetworkCallback(build, networkCallback2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveConfig(AliyunMqttConfig config) {
        Pdlog.m3273d(TAG, "saveConfig : config = " + config + "; ");
        try {
            File file = new File(getConfigPath());
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            String json = new Gson().toJson(config);
            Intrinsics.checkExpressionValueIsNotNull(json, "Gson().toJson(config)");
            Charset charset = Charsets.UTF_8;
            if (json == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            byte[] bytes = json.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            byte[] bytes2 = KEY.getBytes(Charsets.UTF_8);
            Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
            byte[] encryptAES = EncryptUtils.encryptAES(bytes, bytes2, "AES/CBC/PKCS5Padding", IV);
            Intrinsics.checkExpressionValueIsNotNull(encryptAES, "encryptAES");
            FilesKt.writeBytes(file, encryptAES);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0076 A[Catch: Exception -> 0x012a, TryCatch #0 {Exception -> 0x012a, blocks: (B:3:0x0002, B:5:0x0006, B:8:0x0012, B:10:0x0018, B:13:0x0020, B:15:0x0044, B:17:0x0062, B:19:0x006a, B:24:0x0076, B:25:0x007b, B:27:0x00d9, B:28:0x00f0, B:30:0x00f6, B:31:0x00fb, B:34:0x00ff), top: B:2:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00d9 A[Catch: Exception -> 0x012a, TryCatch #0 {Exception -> 0x012a, blocks: (B:3:0x0002, B:5:0x0006, B:8:0x0012, B:10:0x0018, B:13:0x0020, B:15:0x0044, B:17:0x0062, B:19:0x006a, B:24:0x0076, B:25:0x007b, B:27:0x00d9, B:28:0x00f0, B:30:0x00f6, B:31:0x00fb, B:34:0x00ff), top: B:2:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00f6 A[Catch: Exception -> 0x012a, TryCatch #0 {Exception -> 0x012a, blocks: (B:3:0x0002, B:5:0x0006, B:8:0x0012, B:10:0x0018, B:13:0x0020, B:15:0x0044, B:17:0x0062, B:19:0x006a, B:24:0x0076, B:25:0x007b, B:27:0x00d9, B:28:0x00f0, B:30:0x00f6, B:31:0x00fb, B:34:0x00ff), top: B:2:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void startInit(Context context2, AliyunMqttConfig config) {
        boolean z;
        IOnOpenSdkEventListener iOnOpenSdkEventListener;
        try {
            if (isSdkInit) {
                Pdlog.m3274e(TAG, "startInit : sdk is inited");
                return;
            }
            if (isOpen() && !isLocal()) {
                isSdkInit = true;
                Pdlog.m3273d(TAG, "startInit pudu_iot =  : " + config);
                if (RobotOpenSdk.INSTANCE.isInit()) {
                    RobotOpenSdk.INSTANCE.setIotEnable(true);
                    CoreDevices.INSTANCE.getBattery().addListener(systemBatteryListener);
                    robotMoveStateNotify.init();
                    robotPoseNotify.init();
                    return;
                }
                String original = config.getOriginal();
                if (original != null && !StringsKt.isBlank(original)) {
                    z = false;
                    if (z) {
                        config.setOriginal(DEFAULT_REGION);
                    }
                    CoreDevices.INSTANCE.getBattery().addListener(systemBatteryListener);
                    robotMoveStateNotify.init();
                    robotPoseNotify.init();
                    config.setTimestamp(String.valueOf(System.currentTimeMillis()));
                    Pdlog.m3273d(TAG, "startInit config: " + BaseKt.toStr(config));
                    RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
                    Context applicationContext = context2.getApplicationContext();
                    Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
                    robotOpenSdk.connectAliyun(applicationContext, config, new ICallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$startInit$1
                        @Override // com.pudutech.robot.opensdk.interf.ICallback
                        public void onSuccess(IBody result) {
                            String str;
                            RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                            str = RobotOpenHelper.TAG;
                            Pdlog.m3273d(str, "startInit onSuccess");
                            RobotOpenHelper.INSTANCE.chechIsOpen();
                        }

                        @Override // com.pudutech.robot.opensdk.interf.ICallback
                        public void onFailed(Exception e) {
                            String str;
                            Intrinsics.checkParameterIsNotNull(e, "e");
                            RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                            str = RobotOpenHelper.TAG;
                            Pdlog.m3273d(str, "startInit onFailed : e = " + e + "; ");
                            RobotOpenHelper.INSTANCE.chechIsOpen();
                        }
                    });
                    if (isOpen()) {
                        CoreDevices.INSTANCE.getBattery().addListener(systemBatteryListener);
                        robotMoveStateNotify.init();
                        robotPoseNotify.init();
                    }
                    RobotOpenSdk robotOpenSdk2 = RobotOpenSdk.INSTANCE;
                    iOnOpenSdkEventListener = onOpenSdkEventListener;
                    if (iOnOpenSdkEventListener == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("onOpenSdkEventListener");
                    }
                    robotOpenSdk2.setOnEventListener(iOnOpenSdkEventListener);
                    return;
                }
                z = true;
                if (z) {
                }
                CoreDevices.INSTANCE.getBattery().addListener(systemBatteryListener);
                robotMoveStateNotify.init();
                robotPoseNotify.init();
                config.setTimestamp(String.valueOf(System.currentTimeMillis()));
                Pdlog.m3273d(TAG, "startInit config: " + BaseKt.toStr(config));
                RobotOpenSdk robotOpenSdk3 = RobotOpenSdk.INSTANCE;
                Context applicationContext2 = context2.getApplicationContext();
                Intrinsics.checkExpressionValueIsNotNull(applicationContext2, "context.applicationContext");
                robotOpenSdk3.connectAliyun(applicationContext2, config, new ICallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenHelper$startInit$1
                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onSuccess(IBody result) {
                        String str;
                        RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                        str = RobotOpenHelper.TAG;
                        Pdlog.m3273d(str, "startInit onSuccess");
                        RobotOpenHelper.INSTANCE.chechIsOpen();
                    }

                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onFailed(Exception e) {
                        String str;
                        Intrinsics.checkParameterIsNotNull(e, "e");
                        RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                        str = RobotOpenHelper.TAG;
                        Pdlog.m3273d(str, "startInit onFailed : e = " + e + "; ");
                        RobotOpenHelper.INSTANCE.chechIsOpen();
                    }
                });
                if (isOpen()) {
                }
                RobotOpenSdk robotOpenSdk22 = RobotOpenSdk.INSTANCE;
                iOnOpenSdkEventListener = onOpenSdkEventListener;
                if (iOnOpenSdkEventListener == null) {
                }
                robotOpenSdk22.setOnEventListener(iOnOpenSdkEventListener);
                return;
            }
            Pdlog.m3273d(TAG, "startInit : isOpen = " + isOpen() + " , isLocal= " + isLocal());
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void chechIsOpen() {
        if (isOpen()) {
            return;
        }
        Pdlog.m3273d(TAG, "chechIsOpen : onSuccess isOpen = false ");
        RobotOpenSdk.INSTANCE.release();
        isSdkInit = false;
    }

    private final String getConfigPath() {
        String str;
        if (Intrinsics.areEqual(getRegion(), DEFAULT_REGION)) {
            str = "";
        } else {
            str = "_" + getRegion();
        }
        Pdlog.m3273d(TAG, "getConfigPath " + str);
        if (!IotServerApiManager.INSTANCE.isTest()) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {str};
            String format = String.format(IOT_SECRET_PATH, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            return format;
        }
        Pdlog.m3273d(TAG, "getConfigPath is test");
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Object[] objArr2 = {str};
        String format2 = String.format(IOT_SECRET_PATH_TEST, Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
        return format2;
    }

    private final String getIotProductKey() {
        if (!IotServerApiManager.INSTANCE.isTest()) {
            return IOT_PRODUCTKEY;
        }
        Pdlog.m3273d(TAG, "getIotProductKey is test");
        return IOT_PRODUCTKEY_TEST;
    }
}
