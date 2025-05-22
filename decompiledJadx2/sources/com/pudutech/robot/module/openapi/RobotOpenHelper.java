package com.pudutech.robot.module.openapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.util.Log;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.AliyunIotSecretReq;
import com.pudutech.disinfect.baselib.network.req.IotHostReq;
import com.pudutech.disinfect.baselib.network.response.AliyunIotSecretResp2;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.IotRegionResp;
import com.pudutech.disinfect.baselib.network.response.TokenBean;
import com.pudutech.disinfect.baselib.network.response.TokenResp;
import com.pudutech.disinfect.baselib.util.EncryptUtils;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import com.pudutech.robot.opensdk.interf.IOnOpenSdkEventListener;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: RobotOpenHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010(\u001a\u00020)H\u0002J\r\u0010*\u001a\u00020)H\u0000¢\u0006\u0002\b+J\u0019\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010.J\n\u0010/\u001a\u0004\u0018\u000100H\u0002J\b\u00101\u001a\u00020\u0004H\u0002J\b\u00102\u001a\u00020\u0004H\u0002J\b\u00103\u001a\u00020\u0004H\u0002J\u001d\u00104\u001a\u00020)2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001fH\u0000¢\u0006\u0002\b5J\b\u00106\u001a\u00020)H\u0002J\r\u00107\u001a\u00020)H\u0000¢\u0006\u0002\b8J\b\u00109\u001a\u00020)H\u0002J\b\u0010:\u001a\u00020)H\u0002J\u0010\u0010;\u001a\u00020)2\u0006\u0010<\u001a\u000200H\u0002J\b\u0010=\u001a\u00020)H\u0002J\u0010\u0010>\u001a\u00020)2\u0006\u0010?\u001a\u00020\u0004H\u0002J\u0018\u0010@\u001a\u00020)2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010<\u001a\u000200H\u0002J!\u0010A\u001a\u00020)2\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010DJ\b\u0010E\u001a\u00020)H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00148@@BX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006F"}, m3961d2 = {"Lcom/pudutech/robot/module/openapi/RobotOpenHelper;", "", "()V", "AES_PADDING", "", "DEFAULT_REGION", "FILE_NAME", "IOT_PRODUCTKEY", "IOT_PRODUCTKEY_TEST", "IOT_SECRET_PATH", "IOT_SECRET_PATH_TEST", "IV", "", "KEY", "KEY_BEEPER_CALL_SWITCH", "KEY_REGION", "TAG", "context", "Landroid/content/Context;", "isGetIotSecretReqing", "", ES6Iterator.VALUE_PROPERTY, "isOpen", "isOpen$module_robot_open_api_release", "()Z", "setOpen", "(Z)V", "isSdkInit", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "onOpenSdkEventListener", "Lcom/pudutech/robot/opensdk/interf/IOnOpenSdkEventListener;", "requestFailureCount", "", "getRequestFailureCount", "()I", "setRequestFailureCount", "(I)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "chechIsOpen", "", "close", "close$module_robot_open_api_release", "doGetEdgeHost", "mac", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getConfigFromLocal", "Lcom/pudutech/robot/opensdk/RobotOpenSdk$Config;", "getConfigPath", "getIotProductKey", "getRegion", "init", "init$module_robot_open_api_release", "initByServer", "open", "open$module_robot_open_api_release", "recheckRegion", "robotOpenClose", "saveConfig", "config", "setNetworkCallback", "setRegion", OSSConfig.PARAM_REGION, "startInit", "startRegisterFromServer", "deviceName", "url", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unRegisterNetworkCallback", "module_robot_open_api_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotOpenHelper {
    private static final String AES_PADDING = "AES/CBC/PKCS5Padding";
    private static final String KEY = "AE3G3Mj9rdTNKil9";
    private static Context context;
    private static volatile boolean isGetIotSecretReqing;
    private static boolean isOpen;
    private static volatile boolean isSdkInit;
    private static ConnectivityManager.NetworkCallback networkCallback;
    private static IOnOpenSdkEventListener onOpenSdkEventListener;
    private static int requestFailureCount;
    private static SharedPreferences sharedPreferences;
    public static final RobotOpenHelper INSTANCE = new RobotOpenHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String KEY_REGION = KEY_REGION;
    private static final String KEY_REGION = KEY_REGION;
    private static final String DEFAULT_REGION = "cn-shanghai";
    private static final String KEY_BEEPER_CALL_SWITCH = KEY_BEEPER_CALL_SWITCH;
    private static final String KEY_BEEPER_CALL_SWITCH = KEY_BEEPER_CALL_SWITCH;
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

    private RobotOpenHelper() {
    }

    public static final /* synthetic */ Context access$getContext$p(RobotOpenHelper robotOpenHelper) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final boolean isOpen$module_robot_open_api_release() {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 != null) {
            return sharedPreferences2.getBoolean(KEY_BEEPER_CALL_SWITCH, false);
        }
        return false;
    }

    private final void setOpen(boolean z) {
        SharedPreferences.Editor edit;
        SharedPreferences.Editor putBoolean;
        isOpen = z;
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null || (edit = sharedPreferences2.edit()) == null || (putBoolean = edit.putBoolean(KEY_BEEPER_CALL_SWITCH, z)) == null) {
            return;
        }
        putBoolean.apply();
    }

    public final void init$module_robot_open_api_release(Context context2, IOnOpenSdkEventListener onOpenSdkEventListener2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(onOpenSdkEventListener2, "onOpenSdkEventListener");
        context = context2;
        onOpenSdkEventListener = onOpenSdkEventListener2;
        sharedPreferences = context2.getSharedPreferences(FILE_NAME, 0);
        if (isOpen$module_robot_open_api_release()) {
            Pdlog.m3273d(TAG, "init : isOpen , need start open ");
            open$module_robot_open_api_release();
        }
    }

    public final synchronized void close$module_robot_open_api_release() {
        Pdlog.m3273d(TAG, "close ");
        setOpen(false);
        isSdkInit = false;
        requestFailureCount = 0;
        robotOpenClose();
    }

    private final void robotOpenClose() {
        Pdlog.m3273d(TAG, "robotOpenClose ");
        if (RobotOpenSdk.INSTANCE.isInit()) {
            RobotOpenSdk.INSTANCE.setIotEnable(false);
        }
    }

    public final synchronized void open$module_robot_open_api_release() {
        Pdlog.m3273d(TAG, "open " + WifiUtil.INSTANCE.getMac());
        setOpen(true);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RobotOpenHelper$open$1(null), 2, null);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object doGetEdgeHost(String str, Continuation<? super Unit> continuation) {
        RobotOpenHelper$doGetEdgeHost$1 robotOpenHelper$doGetEdgeHost$1;
        int i;
        RobotOpenHelper robotOpenHelper;
        ApiResponse apiResponse;
        RobotOpenHelper robotOpenHelper2;
        String str2;
        if (continuation instanceof RobotOpenHelper$doGetEdgeHost$1) {
            robotOpenHelper$doGetEdgeHost$1 = (RobotOpenHelper$doGetEdgeHost$1) continuation;
            if ((robotOpenHelper$doGetEdgeHost$1.label & Integer.MIN_VALUE) != 0) {
                robotOpenHelper$doGetEdgeHost$1.label -= Integer.MIN_VALUE;
                Object obj = robotOpenHelper$doGetEdgeHost$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = robotOpenHelper$doGetEdgeHost$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetWorkApiManager.NormalApiService normal = NetWorkApiManager.INSTANCE.getNormal();
                    if (str == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String upperCase = str.toUpperCase();
                    Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
                    IotHostReq iotHostReq = new IotHostReq(upperCase);
                    robotOpenHelper$doGetEdgeHost$1.L$0 = this;
                    robotOpenHelper$doGetEdgeHost$1.L$1 = str;
                    robotOpenHelper$doGetEdgeHost$1.label = 1;
                    obj = NetWorkApiManager.NormalApiService.DefaultImpls.getEdgeHost$default(normal, null, iotHostReq, robotOpenHelper$doGetEdgeHost$1, 1, null);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    robotOpenHelper = this;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            str2 = (String) robotOpenHelper$doGetEdgeHost$1.L$4;
                            robotOpenHelper2 = (RobotOpenHelper) robotOpenHelper$doGetEdgeHost$1.L$0;
                            ResultKt.throwOnFailure(obj);
                            robotOpenHelper2.setRegion(str2);
                            isSdkInit = false;
                            RobotOpenSdk.INSTANCE.release();
                            if (robotOpenHelper2.isOpen$module_robot_open_api_release()) {
                                robotOpenHelper2.open$module_robot_open_api_release();
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    str = (String) robotOpenHelper$doGetEdgeHost$1.L$1;
                    robotOpenHelper = (RobotOpenHelper) robotOpenHelper$doGetEdgeHost$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                apiResponse = (ApiResponse) obj;
                Pdlog.m3273d(TAG, "recheckRegion " + str + " , data " + apiResponse);
                if (apiResponse.getCode() == 0) {
                    String region = robotOpenHelper.getRegion();
                    IotRegionResp iotRegionResp = (IotRegionResp) apiResponse.getData();
                    String regionId = iotRegionResp != null ? iotRegionResp.getRegionId() : null;
                    String str3 = regionId;
                    if (!(str3 == null || StringsKt.isBlank(str3))) {
                        if (!Intrinsics.areEqual(regionId, region != null ? region : DEFAULT_REGION)) {
                            robotOpenHelper$doGetEdgeHost$1.L$0 = robotOpenHelper;
                            robotOpenHelper$doGetEdgeHost$1.L$1 = str;
                            robotOpenHelper$doGetEdgeHost$1.L$2 = apiResponse;
                            robotOpenHelper$doGetEdgeHost$1.L$3 = region;
                            robotOpenHelper$doGetEdgeHost$1.L$4 = regionId;
                            robotOpenHelper$doGetEdgeHost$1.label = 2;
                            if (DelayKt.delay(10000L, robotOpenHelper$doGetEdgeHost$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            robotOpenHelper2 = robotOpenHelper;
                            str2 = regionId;
                            robotOpenHelper2.setRegion(str2);
                            isSdkInit = false;
                            RobotOpenSdk.INSTANCE.release();
                            if (robotOpenHelper2.isOpen$module_robot_open_api_release()) {
                            }
                        }
                    }
                }
                return Unit.INSTANCE;
            }
        }
        robotOpenHelper$doGetEdgeHost$1 = new RobotOpenHelper$doGetEdgeHost$1(this, continuation);
        Object obj2 = robotOpenHelper$doGetEdgeHost$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = robotOpenHelper$doGetEdgeHost$1.label;
        if (i != 0) {
        }
        apiResponse = (ApiResponse) obj2;
        Pdlog.m3273d(TAG, "recheckRegion " + str + " , data " + apiResponse);
        if (apiResponse.getCode() == 0) {
        }
        return Unit.INSTANCE;
    }

    public final int getRequestFailureCount() {
        return requestFailureCount;
    }

    public final void setRequestFailureCount(int i) {
        requestFailureCount = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recheckRegion() {
        String mac;
        Pdlog.m3273d(TAG, "recheckRegion ", Integer.valueOf(requestFailureCount));
        if (requestFailureCount == 0 && (mac = WifiUtil.INSTANCE.getMac()) != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotOpenHelper$recheckRegion$1$1(mac, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RobotOpenSdk.Config getConfigFromLocal() {
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
            return (RobotOpenSdk.Config) gson.fromJson(new String(decb, Charsets.UTF_8), RobotOpenSdk.Config.class);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initByServer() {
        if (isGetIotSecretReqing) {
            Pdlog.m3274e(TAG, "initByServer is requesting");
            return;
        }
        String mac = WifiUtil.INSTANCE.getMac();
        Pdlog.m3273d(TAG, "initByServer : mac = " + mac);
        String str = mac;
        if (str == null || str.length() == 0) {
            setNetworkCallback();
            return;
        }
        String replace$default = StringsKt.replace$default(mac, ":", "", false, 4, (Object) null);
        if (replace$default == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        Intrinsics.checkExpressionValueIsNotNull(replace$default.toLowerCase(), "(this as java.lang.String).toLowerCase()");
        isGetIotSecretReqing = true;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RobotOpenHelper$initByServer$1(mac, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object startRegisterFromServer(String str, String str2, Continuation<? super Unit> continuation) {
        RobotOpenHelper$startRegisterFromServer$1 robotOpenHelper$startRegisterFromServer$1;
        int i;
        String str3;
        RobotOpenHelper robotOpenHelper;
        String str4;
        TokenResp tokenResp;
        String str5;
        String str6;
        RobotOpenHelper robotOpenHelper2;
        ApiResponse apiResponse;
        Object data;
        Object data2;
        Context context2;
        String str7 = str2;
        if (continuation instanceof RobotOpenHelper$startRegisterFromServer$1) {
            robotOpenHelper$startRegisterFromServer$1 = (RobotOpenHelper$startRegisterFromServer$1) continuation;
            if ((robotOpenHelper$startRegisterFromServer$1.label & Integer.MIN_VALUE) != 0) {
                robotOpenHelper$startRegisterFromServer$1.label -= Integer.MIN_VALUE;
                Object obj = robotOpenHelper$startRegisterFromServer$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = robotOpenHelper$startRegisterFromServer$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    String region = getRegion();
                    robotOpenHelper$startRegisterFromServer$1.L$0 = this;
                    robotOpenHelper$startRegisterFromServer$1.L$1 = str;
                    robotOpenHelper$startRegisterFromServer$1.L$2 = str7;
                    robotOpenHelper$startRegisterFromServer$1.L$3 = region;
                    robotOpenHelper$startRegisterFromServer$1.label = 1;
                    Object genToken$default = NetWorkApiManager.NormalApiService.DefaultImpls.genToken$default(NetWorkApiManager.INSTANCE.getNormal(), str7 + "/token", null, null, null, robotOpenHelper$startRegisterFromServer$1, 14, null);
                    if (genToken$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    str3 = str;
                    robotOpenHelper = this;
                    str4 = region;
                    obj = genToken$default;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        str5 = (String) robotOpenHelper$startRegisterFromServer$1.L$3;
                        String str8 = (String) robotOpenHelper$startRegisterFromServer$1.L$1;
                        RobotOpenHelper robotOpenHelper3 = (RobotOpenHelper) robotOpenHelper$startRegisterFromServer$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        robotOpenHelper2 = robotOpenHelper3;
                        str6 = str8;
                        apiResponse = (ApiResponse) obj;
                        Pdlog.m3273d(TAG, "startRegisterFromServer : " + apiResponse);
                        if (apiResponse.getCode() == 0 && apiResponse.getData() != null) {
                            data = apiResponse.getData();
                            if (data == null) {
                                Intrinsics.throwNpe();
                            }
                            String productKey = ((AliyunIotSecretResp2) data).getProductKey();
                            data2 = apiResponse.getData();
                            if (data2 == null) {
                                Intrinsics.throwNpe();
                            }
                            RobotOpenSdk.Config config = new RobotOpenSdk.Config(productKey, str6, ((AliyunIotSecretResp2) data2).getDeviceSecret(), str5);
                            context2 = context;
                            if (context2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("context");
                            }
                            if (context2 != null) {
                                INSTANCE.startInit(context2, config);
                                INSTANCE.saveConfig(config);
                            }
                        }
                        robotOpenHelper2.unRegisterNetworkCallback();
                        return Unit.INSTANCE;
                    }
                    String str9 = (String) robotOpenHelper$startRegisterFromServer$1.L$3;
                    String str10 = (String) robotOpenHelper$startRegisterFromServer$1.L$2;
                    String str11 = (String) robotOpenHelper$startRegisterFromServer$1.L$1;
                    RobotOpenHelper robotOpenHelper4 = (RobotOpenHelper) robotOpenHelper$startRegisterFromServer$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    str4 = str9;
                    str7 = str10;
                    str3 = str11;
                    robotOpenHelper = robotOpenHelper4;
                }
                tokenResp = (TokenResp) obj;
                if (tokenResp.getCode() == 0 || tokenResp.getToken() == null) {
                    Pdlog.m3274e(TAG, "startRegisterFromServer : token get failed");
                    return Unit.INSTANCE;
                }
                NetWorkApiManager.NormalApiService normal = NetWorkApiManager.INSTANCE.getNormal();
                String str12 = str7 + "/api/v1/robot/register";
                AliyunIotSecretReq aliyunIotSecretReq = new AliyunIotSecretReq(str3);
                StringBuilder sb = new StringBuilder();
                sb.append("Bearer ");
                TokenBean token = tokenResp.getToken();
                if (token == null) {
                    Intrinsics.throwNpe();
                }
                sb.append(token.getAccessToken());
                String sb2 = sb.toString();
                robotOpenHelper$startRegisterFromServer$1.L$0 = robotOpenHelper;
                robotOpenHelper$startRegisterFromServer$1.L$1 = str3;
                robotOpenHelper$startRegisterFromServer$1.L$2 = str7;
                robotOpenHelper$startRegisterFromServer$1.L$3 = str4;
                robotOpenHelper$startRegisterFromServer$1.L$4 = tokenResp;
                robotOpenHelper$startRegisterFromServer$1.label = 2;
                str5 = str4;
                RobotOpenHelper$startRegisterFromServer$1 robotOpenHelper$startRegisterFromServer$12 = robotOpenHelper$startRegisterFromServer$1;
                RobotOpenHelper robotOpenHelper5 = robotOpenHelper;
                str6 = str3;
                Object aliyunIOTSecret$default = NetWorkApiManager.NormalApiService.DefaultImpls.getAliyunIOTSecret$default(normal, str12, aliyunIotSecretReq, sb2, null, robotOpenHelper$startRegisterFromServer$12, 8, null);
                if (aliyunIOTSecret$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
                robotOpenHelper2 = robotOpenHelper5;
                obj = aliyunIOTSecret$default;
                apiResponse = (ApiResponse) obj;
                Pdlog.m3273d(TAG, "startRegisterFromServer : " + apiResponse);
                if (apiResponse.getCode() == 0) {
                    data = apiResponse.getData();
                    if (data == null) {
                    }
                    String productKey2 = ((AliyunIotSecretResp2) data).getProductKey();
                    data2 = apiResponse.getData();
                    if (data2 == null) {
                    }
                    RobotOpenSdk.Config config2 = new RobotOpenSdk.Config(productKey2, str6, ((AliyunIotSecretResp2) data2).getDeviceSecret(), str5);
                    context2 = context;
                    if (context2 == null) {
                    }
                    if (context2 != null) {
                    }
                }
                robotOpenHelper2.unRegisterNetworkCallback();
                return Unit.INSTANCE;
            }
        }
        robotOpenHelper$startRegisterFromServer$1 = new RobotOpenHelper$startRegisterFromServer$1(this, continuation);
        Object obj2 = robotOpenHelper$startRegisterFromServer$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = robotOpenHelper$startRegisterFromServer$1.label;
        if (i != 0) {
        }
        tokenResp = (TokenResp) obj2;
        if (tokenResp.getCode() == 0) {
        }
        Pdlog.m3274e(TAG, "startRegisterFromServer : token get failed");
        return Unit.INSTANCE;
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
            networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.pudutech.robot.module.openapi.RobotOpenHelper$setNetworkCallback$1
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

    private final void saveConfig(RobotOpenSdk.Config config) {
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
    public final void startInit(Context context2, RobotOpenSdk.Config config) {
        try {
            if (isSdkInit) {
                Pdlog.m3274e(TAG, "startInit : sdk is inited");
                return;
            }
            if (!isOpen$module_robot_open_api_release()) {
                Pdlog.m3273d(TAG, "startInit : isOpen = false ");
                return;
            }
            isSdkInit = true;
            Pdlog.m3273d(TAG, "startInit pudu_iot =  : " + config);
            if (RobotOpenSdk.INSTANCE.isInit()) {
                RobotOpenSdk.INSTANCE.setIotEnable(true);
                return;
            }
            RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
            Context applicationContext = context2.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
            robotOpenSdk.init(applicationContext, config, new ICallback() { // from class: com.pudutech.robot.module.openapi.RobotOpenHelper$startInit$1
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
            RobotOpenSdk robotOpenSdk2 = RobotOpenSdk.INSTANCE;
            IOnOpenSdkEventListener iOnOpenSdkEventListener = onOpenSdkEventListener;
            if (iOnOpenSdkEventListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onOpenSdkEventListener");
            }
            robotOpenSdk2.setOnEventListener(iOnOpenSdkEventListener);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void chechIsOpen() {
        if (isOpen$module_robot_open_api_release()) {
            return;
        }
        Pdlog.m3273d(TAG, "chechIsOpen : onSuccess isOpen = false ");
        robotOpenClose();
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
        if (!NetWorkApiManager.INSTANCE.isTestServer()) {
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

    private final String getRegion() {
        String string;
        SharedPreferences sharedPreferences2 = sharedPreferences;
        return (sharedPreferences2 == null || (string = sharedPreferences2.getString(KEY_REGION, DEFAULT_REGION)) == null) ? DEFAULT_REGION : string;
    }

    private final String getIotProductKey() {
        if (!NetWorkApiManager.INSTANCE.isTestServer()) {
            return IOT_PRODUCTKEY;
        }
        Pdlog.m3273d(TAG, "getIotProductKey is test");
        return IOT_PRODUCTKEY_TEST;
    }
}
