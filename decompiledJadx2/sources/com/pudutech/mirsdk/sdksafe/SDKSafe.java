package com.pudutech.mirsdk.sdksafe;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import com.aliyun.alink.p022h2.api.Constraint;
import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import com.google.gson.GsonBuilder;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.MD5Util;
import com.pudutech.base.Pdlog;
import com.pudutech.light_network.PuduHttpsUtils;
import com.pudutech.mirsdk.sdksafe.LoginActivity;
import com.pudutech.remotemaintenance.config.IoTConfig;
import java.io.ByteArrayInputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import okhttp3.OkHttpClient;
import org.mozilla.javascript.ES6Iterator;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: SDKSafe.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0013H\u0000¢\u0006\u0002\b$J9\u0010%\u001a\u00020\"2\u0006\u0010#\u001a\u00020 2\u0006\u0010&\u001a\u00020\u00042!\u0010'\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\"0(J@\u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042!\u0010.\u001a\u001d\u0012\u0013\u0012\u00110/¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\"0(H\u0000¢\u0006\u0002\b1JY\u00102\u001a\u00020\"2\u0006\u00103\u001a\u0002042\u0006\u0010&\u001a\u00020\u000426\u00105\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(7\u0012\u0004\u0012\u00020\"06H\u0082@ø\u0001\u0000¢\u0006\u0002\u00108JP\u00109\u001a\u00020\"2\u0006\u0010#\u001a\u00020 2\u0006\u0010&\u001a\u00020\u000426\u00105\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(7\u0012\u0004\u0012\u00020\"06H\u0002J\u0006\u0010:\u001a\u00020\"J\u0010\u0010;\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u0004H\u0002J\u0015\u0010=\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0004H\u0000¢\u0006\u0002\b>J\u0018\u0010?\u001a\u00020@2\u0006\u00103\u001a\u0002042\u0006\u0010\r\u001a\u00020\u0004H\u0002J \u0010A\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u001dH\u0002J\u0018\u0010E\u001a\u00020\"2\u0006\u0010#\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0004H\u0002J\b\u0010F\u001a\u00020\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006G"}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/SDKSafe;", "", "()V", "TAG", "", "aesKey", "", "appId", "", Constraint.PARAM_APP_SECRET, "authMatched", "", "basePage", "baseUrl", "closeChecked", "job", "Lkotlinx/coroutines/Job;", "loginActivity", "Ljava/lang/ref/WeakReference;", "Lcom/pudutech/mirsdk/sdksafe/LoginActivity;", "loginWay", "notProductVersion", "Ljava/util/concurrent/atomic/AtomicBoolean;", "page", "getPage$sdksafe_release", "()Ljava/lang/String;", "setPage$sdksafe_release", "(Ljava/lang/String;)V", "tokenBirth", "", "tokenKey", "weakActivity", "Landroid/app/Activity;", "addLoginActivity", "", "activity", "addLoginActivity$sdksafe_release", "checkControlAuth", "node", SpeechUtility.TAG_RESOURCE_RESULT, "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "code", "checkUrl", "url", "callback", "Lcom/pudutech/mirsdk/sdksafe/LoginActivity$LoginResult;", "state", "checkUrl$sdksafe_release", "chkNode", "context", "Landroid/content/Context;", IoTConfig.PARAM_ERROR_MSG, "Lkotlin/Function2;", "error", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "chkTask", "closeCheckFunc", "encodeValue", ES6Iterator.VALUE_PROPERTY, "finishActivity", "finishActivity$sdksafe_release", "getRequestInterface", "Lcom/pudutech/mirsdk/sdksafe/SDKSafeRequestInterface;", "getSign", "tick", "id", "time", "loginTask", "reloadWebViewProvider", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SDKSafe {
    private static final byte[] aesKey;
    private static int appId = 0;
    private static boolean authMatched = false;
    private static final String basePage;
    private static String baseUrl = null;
    private static boolean closeChecked = false;
    private static Job job = null;
    private static WeakReference<LoginActivity> loginActivity = null;
    private static final String loginWay;
    private static AtomicBoolean notProductVersion;
    private static String page;
    private static long tokenBirth;
    private static String tokenKey;
    private static WeakReference<Activity> weakActivity;
    public static final SDKSafe INSTANCE = new SDKSafe();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static String appSecret = "";

    static {
        byte[] bytes = "f2eaea8ee524219c8240b7a2daa8c666".getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        aesKey = bytes;
        tokenKey = "";
        baseUrl = "";
        basePage = basePage;
        loginWay = loginWay;
        page = "";
    }

    private SDKSafe() {
    }

    public static final /* synthetic */ AtomicBoolean access$getNotProductVersion$p(SDKSafe sDKSafe) {
        AtomicBoolean atomicBoolean = notProductVersion;
        if (atomicBoolean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notProductVersion");
        }
        return atomicBoolean;
    }

    public final String getPage$sdksafe_release() {
        return page;
    }

    public final void setPage$sdksafe_release(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        page = str;
    }

    public final void closeCheckFunc() {
        closeChecked = true;
    }

    public final void checkControlAuth(Activity activity, String node, Function1<? super Integer, Unit> result) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(node, "node");
        Intrinsics.checkParameterIsNotNull(result, "result");
        BuildersKt__BuildersKt.runBlocking$default(null, new SDKSafe$checkControlAuth$1(null), 1, null);
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SDKSafe$checkControlAuth$2(result, activity, node, null), 3, null);
        job = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reloadWebViewProvider() {
        Method getProviderClassMethod;
        int i = Build.VERSION.SDK_INT;
        try {
            Pdlog.m3273d(TAG, "start reload webview provider");
            Class<?> cls = Class.forName("android.webkit.WebViewFactory");
            Field providerField = cls.getDeclaredField("sProviderInstance");
            Intrinsics.checkExpressionValueIsNotNull(providerField, "providerField");
            providerField.setAccessible(true);
            Object obj = providerField.get(null);
            if (obj != null) {
                Pdlog.m3273d(TAG, "sprovider instance is not null");
                return;
            }
            Pdlog.m3273d(TAG, "get provider is null");
            if (i > 22) {
                getProviderClassMethod = cls.getDeclaredMethod("getProviderClass", new Class[0]);
            } else {
                if (i == 22) {
                    getProviderClassMethod = cls.getDeclaredMethod("getFactoryClass", new Class[0]);
                } else {
                    Pdlog.m3275i(TAG, "Don't need hook WebView");
                    getProviderClassMethod = null;
                }
                if (getProviderClassMethod == null) {
                    return;
                }
            }
            Pdlog.m3273d(TAG, "start create provider constructor");
            Intrinsics.checkExpressionValueIsNotNull(getProviderClassMethod, "getProviderClassMethod");
            getProviderClassMethod.setAccessible(true);
            Object invoke = getProviderClassMethod.invoke(cls, new Object[0]);
            if (invoke == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<*>");
            }
            Class cls2 = (Class) invoke;
            Pdlog.m3273d(TAG, "has get provider class");
            Class<?> cls3 = Class.forName("android.webkit.WebViewDelegate");
            Constructor<?> delegateConstructor = cls3.getDeclaredConstructor(new Class[0]);
            Intrinsics.checkExpressionValueIsNotNull(delegateConstructor, "delegateConstructor");
            delegateConstructor.setAccessible(true);
            Pdlog.m3273d(TAG, "has get delegate class");
            if (i < 26) {
                Constructor constructor = cls2.getConstructor(cls3);
                Pdlog.m3273d(TAG, "finish create provider constructor " + constructor);
                if (constructor != null) {
                    constructor.setAccessible(true);
                    obj = constructor.newInstance(delegateConstructor.newInstance(new Object[0]));
                }
            } else {
                Field chromeMethodName = cls.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD");
                Intrinsics.checkExpressionValueIsNotNull(chromeMethodName, "chromeMethodName");
                chromeMethodName.setAccessible(true);
                String str = (String) chromeMethodName.get(null);
                if (str == null) {
                    str = "create";
                }
                Method method = cls2.getMethod(str, cls3);
                if (method != null) {
                    obj = method.invoke(null, delegateConstructor.newInstance(new Object[0]));
                }
            }
            if (obj != null) {
                providerField.set("sProviderInstance", obj);
                Pdlog.m3273d(TAG, "hook provider success");
            } else {
                Pdlog.m3273d(TAG, "hook provider fail");
            }
        } catch (Exception e) {
            String str2 = TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("hook WebView exception: ");
            Throwable cause = e.getCause();
            sb.append(cause != null ? cause.getMessage() : null);
            sb.append(' ');
            sb.append(e.getMessage());
            sb.append(", stack trace\n ");
            StackTraceElement[] stackTrace = e.getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
            sb.append(ArraysKt.contentDeepToString(stackTrace));
            objArr[0] = sb.toString();
            Pdlog.m3277w(str2, objArr);
        }
    }

    public final void addLoginActivity$sdksafe_release(LoginActivity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        loginActivity = new WeakReference<>(activity);
    }

    public final void finishActivity$sdksafe_release(String node) {
        WeakReference<Activity> weakReference;
        Activity activity;
        Intrinsics.checkParameterIsNotNull(node, "node");
        if (Intrinsics.areEqual(node, "debug") && (weakReference = weakActivity) != null && (activity = weakReference.get()) != null) {
            activity.finish();
        }
        BuildersKt__BuildersKt.runBlocking$default(null, new SDKSafe$finishActivity$1(null), 1, null);
    }

    public final void checkUrl$sdksafe_release(String url, String node, Function1<? super LoginActivity.LoginResult, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(node, "node");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (StringsKt.contains$default((CharSequence) url, (CharSequence) "https://rbotctrl", false, 2, (Object) null)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SDKSafe$checkUrl$1(callback, url, node, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loginTask(Activity activity, String node) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new SDKSafe$loginTask$1(node, activity, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void chkTask(Activity activity, String node, Function2<? super Integer, ? super String, Unit> errorMsg) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SDKSafe$chkTask$1(activity, node, errorMsg, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00c0 A[Catch: Exception -> 0x004d, TryCatch #0 {Exception -> 0x004d, blocks: (B:11:0x0049, B:12:0x00b2, B:13:0x00ba, B:15:0x00c0, B:17:0x00cc, B:21:0x00d8), top: B:10:0x0049 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object chkNode(Context context, String str, Function2<? super Integer, ? super String, Unit> function2, Continuation<? super Unit> continuation) {
        SDKSafe$chkNode$1 sDKSafe$chkNode$1;
        int i;
        String str2;
        ResponseMsg responseMsg;
        if (continuation instanceof SDKSafe$chkNode$1) {
            sDKSafe$chkNode$1 = (SDKSafe$chkNode$1) continuation;
            if ((sDKSafe$chkNode$1.label & Integer.MIN_VALUE) != 0) {
                sDKSafe$chkNode$1.label -= Integer.MIN_VALUE;
                Object obj = sDKSafe$chkNode$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = sDKSafe$chkNode$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    AtomicBoolean atomicBoolean = notProductVersion;
                    if (atomicBoolean == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("notProductVersion");
                    }
                    String str3 = atomicBoolean.get() ? "https://auth-test.pudutech.com/" : "https://auth.pudutech.com/";
                    SDKSafeRequestInterface requestInterface = getRequestInterface(context, str3);
                    ChkNodeRequest chkNodeRequest = new ChkNodeRequest();
                    chkNodeRequest.setAppid(appId);
                    chkNodeRequest.setToken(tokenKey);
                    chkNodeRequest.setNode(str);
                    if (requestInterface != null) {
                        try {
                            sDKSafe$chkNode$1.L$0 = this;
                            sDKSafe$chkNode$1.L$1 = context;
                            sDKSafe$chkNode$1.L$2 = str;
                            sDKSafe$chkNode$1.L$3 = function2;
                            sDKSafe$chkNode$1.L$4 = "api/inner/check_node/v1";
                            sDKSafe$chkNode$1.L$5 = str3;
                            sDKSafe$chkNode$1.L$6 = requestInterface;
                            sDKSafe$chkNode$1.L$7 = chkNodeRequest;
                            sDKSafe$chkNode$1.label = 1;
                            obj = requestInterface.chkNode(chkNodeRequest, str3 + "api/inner/check_node/v1", sDKSafe$chkNode$1);
                            if (obj == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } catch (Exception e) {
                            e = e;
                            str2 = str3;
                            Pdlog.m3275i(TAG, "connect " + str2 + " failed: " + e.getMessage() + ", " + e.getStackTrace());
                            function2.invoke(Boxing.boxInt(-1), String.valueOf(e.getMessage()));
                            return Unit.INSTANCE;
                        }
                    } else {
                        responseMsg = null;
                        if (responseMsg.getCode() != 0 && Intrinsics.areEqual(responseMsg.getMsg(), "success")) {
                            function2.invoke(Boxing.boxInt(0), "");
                            authMatched = true;
                        } else {
                            String str4 = "auth error: " + responseMsg.getMsg();
                            Pdlog.m3275i(TAG, str4);
                            function2.invoke(Boxing.boxInt(-1), str4);
                        }
                        return Unit.INSTANCE;
                    }
                } else if (i == 1) {
                    str2 = (String) sDKSafe$chkNode$1.L$5;
                    function2 = (Function2) sDKSafe$chkNode$1.L$3;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Exception e2) {
                        e = e2;
                        Pdlog.m3275i(TAG, "connect " + str2 + " failed: " + e.getMessage() + ", " + e.getStackTrace());
                        function2.invoke(Boxing.boxInt(-1), String.valueOf(e.getMessage()));
                        return Unit.INSTANCE;
                    }
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                responseMsg = (ResponseMsg) obj;
                if (responseMsg.getCode() != 0) {
                }
                String str42 = "auth error: " + responseMsg.getMsg();
                Pdlog.m3275i(TAG, str42);
                function2.invoke(Boxing.boxInt(-1), str42);
                return Unit.INSTANCE;
            }
        }
        sDKSafe$chkNode$1 = new SDKSafe$chkNode$1(this, continuation);
        Object obj2 = sDKSafe$chkNode$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = sDKSafe$chkNode$1.label;
        if (i != 0) {
        }
        responseMsg = (ResponseMsg) obj2;
        if (responseMsg.getCode() != 0) {
        }
        String str422 = "auth error: " + responseMsg.getMsg();
        Pdlog.m3275i(TAG, str422);
        function2.invoke(Boxing.boxInt(-1), str422);
        return Unit.INSTANCE;
    }

    private final String encodeValue(String value) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, secretKeySpec);
        Charset charset = Charsets.UTF_8;
        if (value == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = value.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        new CipherInputStream(new ByteArrayInputStream(bytes), cipher);
        Charset charset2 = Charsets.UTF_8;
        if (value == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes2 = value.getBytes(charset2);
        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
        String encodeToString = Base64.encodeToString(cipher.doFinal(bytes2), 0);
        Intrinsics.checkExpressionValueIsNotNull(encodeToString, "Base64.encodeToString(result, Base64.DEFAULT)");
        return encodeToString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSign(String tick, String id, long time) {
        StringBuilder sb = new StringBuilder();
        sb.append("appid=" + id);
        sb.append("&ticket=" + tick + "&timestamp=" + time);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("&secret=");
        sb2.append(appSecret);
        sb.append(sb2.toString());
        String md5 = MD5Util.toMD5(sb.toString());
        Intrinsics.checkExpressionValueIsNotNull(md5, "MD5Util.toMD5(sign.toString())");
        if (md5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = md5.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        return lowerCase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SDKSafeRequestInterface getRequestInterface(Context context, String baseUrl2) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            builder = PuduHttpsUtils.INSTANCE.setSuportHttpsParams(context, builder, context.getAssets().open("plat.cert"), false);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
        builder.hostnameVerifier(new HostnameVerifier() { // from class: com.pudutech.mirsdk.sdksafe.SDKSafe$getRequestInterface$1
            @Override // javax.net.ssl.HostnameVerifier
            public final boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        });
        builder.connectTimeout(5L, TimeUnit.SECONDS).readTimeout(5L, TimeUnit.SECONDS).writeTimeout(5L, TimeUnit.SECONDS);
        Object create = new Retrofit.Builder().baseUrl(baseUrl2).client(builder.build()).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build().create(SDKSafeRequestInterface.class);
        Intrinsics.checkExpressionValueIsNotNull(create, "Retrofit.Builder().baseU…estInterface::class.java)");
        return (SDKSafeRequestInterface) create;
    }
}
