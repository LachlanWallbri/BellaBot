package com.pudutech.factory_test.test_pack.cloud_server;

import android.content.Context;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.test_pack.WifiUtil;
import com.pudutech.light_network.BuildConfig;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: CloudServerUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000  2\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0011\u0010\u0019\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\n\u0010\u001b\u001a\u0004\u0018\u00010\u000bH\u0002J\u0011\u0010\u001c\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0011\u0010\u001d\u001a\u00020\u0017H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u000bH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R,\u0010\u0011\u001a\u0014\u0012\b\u0012\u00060\u0012j\u0002`\u0013\u0012\u0004\u0012\u00020\f\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000e\"\u0004\b\u0015\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, m3961d2 = {"Lcom/pudutech/factory_test/test_pack/cloud_server/CloudServerUtil;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "client", "Lokhttp3/OkHttpClient;", "jsonType", "Lokhttp3/MediaType;", "onCloudFailResponse", "Lkotlin/Function1;", "", "", "getOnCloudFailResponse", "()Lkotlin/jvm/functions/Function1;", "setOnCloudFailResponse", "(Lkotlin/jvm/functions/Function1;)V", "onException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "getOnException", "setOnException", "checkInStorage", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkTestingStatus", "getOKHttpClient", "getState", "putInStorage", "registerTesting", "uploadState", "string", "Companion", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CloudServerUtil {
    private static boolean isTest;
    private OkHttpClient client;
    private final MediaType jsonType;
    private Function1<? super String, Unit> onCloudFailResponse;
    private Function1<? super Exception, Unit> onException;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static String url = "https://rmp.pudutech.com/api/v5/report";

    public CloudServerUtil(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.jsonType = MediaType.Companion.get("application/json;charset=utf-8");
        this.client = getOKHttpClient(context);
    }

    /* compiled from: CloudServerUtil.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/factory_test/test_pack/cloud_server/CloudServerUtil$Companion;", "", "()V", "TAG", "", ES6Iterator.VALUE_PROPERTY, "", "isTest", "()Z", "setTest", "(Z)V", "url", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isTest() {
            return CloudServerUtil.isTest;
        }

        public final void setTest(boolean z) {
            Pdlog.m3273d(CloudServerUtil.TAG, "set isTestSever=" + z);
            CloudServerUtil.isTest = z;
            if (z) {
                CloudServerUtil.url = "https://rmp-test.pudutech.com/api/v5/report";
            } else {
                CloudServerUtil.url = "https://rmp.pudutech.com/api/v5/report";
            }
        }
    }

    public final Function1<Exception, Unit> getOnException() {
        return this.onException;
    }

    public final void setOnException(Function1<? super Exception, Unit> function1) {
        this.onException = function1;
    }

    public final Function1<String, Unit> getOnCloudFailResponse() {
        return this.onCloudFailResponse;
    }

    public final void setOnCloudFailResponse(Function1<? super String, Unit> function1) {
        this.onCloudFailResponse = function1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object checkTestingStatus(Continuation<? super Boolean> continuation) {
        CloudServerUtil$checkTestingStatus$1 cloudServerUtil$checkTestingStatus$1;
        int i;
        boolean z;
        Ref.ObjectRef objectRef;
        if (continuation instanceof CloudServerUtil$checkTestingStatus$1) {
            cloudServerUtil$checkTestingStatus$1 = (CloudServerUtil$checkTestingStatus$1) continuation;
            if ((cloudServerUtil$checkTestingStatus$1.label & Integer.MIN_VALUE) != 0) {
                cloudServerUtil$checkTestingStatus$1.label -= Integer.MIN_VALUE;
                Object obj = cloudServerUtil$checkTestingStatus$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = cloudServerUtil$checkTestingStatus$1.label;
                z = true;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (String) 0;
                    CoroutineDispatcher io2 = Dispatchers.getIO();
                    CloudServerUtil$checkTestingStatus$2 cloudServerUtil$checkTestingStatus$2 = new CloudServerUtil$checkTestingStatus$2(this, objectRef2, null);
                    cloudServerUtil$checkTestingStatus$1.L$0 = this;
                    cloudServerUtil$checkTestingStatus$1.L$1 = objectRef2;
                    cloudServerUtil$checkTestingStatus$1.label = 1;
                    if (BuildersKt.withContext(io2, cloudServerUtil$checkTestingStatus$2, cloudServerUtil$checkTestingStatus$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef = (Ref.ObjectRef) cloudServerUtil$checkTestingStatus$1.L$1;
                    ResultKt.throwOnFailure(obj);
                }
                if (!Intrinsics.areEqual((String) objectRef.element, "testing") && !Intrinsics.areEqual((String) objectRef.element, "normal")) {
                    z = false;
                }
                return Boxing.boxBoolean(z);
            }
        }
        cloudServerUtil$checkTestingStatus$1 = new CloudServerUtil$checkTestingStatus$1(this, continuation);
        Object obj2 = cloudServerUtil$checkTestingStatus$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = cloudServerUtil$checkTestingStatus$1.label;
        z = true;
        if (i != 0) {
        }
        if (!Intrinsics.areEqual((String) objectRef.element, "testing")) {
            z = false;
        }
        return Boxing.boxBoolean(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0069 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object checkInStorage(Continuation<? super Boolean> continuation) {
        CloudServerUtil$checkInStorage$1 cloudServerUtil$checkInStorage$1;
        int i;
        Ref.ObjectRef objectRef;
        if (continuation instanceof CloudServerUtil$checkInStorage$1) {
            cloudServerUtil$checkInStorage$1 = (CloudServerUtil$checkInStorage$1) continuation;
            if ((cloudServerUtil$checkInStorage$1.label & Integer.MIN_VALUE) != 0) {
                cloudServerUtil$checkInStorage$1.label -= Integer.MIN_VALUE;
                Object obj = cloudServerUtil$checkInStorage$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = cloudServerUtil$checkInStorage$1.label;
                boolean z = true;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    objectRef2.element = (String) 0;
                    CoroutineDispatcher io2 = Dispatchers.getIO();
                    CloudServerUtil$checkInStorage$2 cloudServerUtil$checkInStorage$2 = new CloudServerUtil$checkInStorage$2(this, objectRef2, null);
                    cloudServerUtil$checkInStorage$1.L$0 = this;
                    cloudServerUtil$checkInStorage$1.L$1 = objectRef2;
                    cloudServerUtil$checkInStorage$1.label = 1;
                    if (BuildersKt.withContext(io2, cloudServerUtil$checkInStorage$2, cloudServerUtil$checkInStorage$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    objectRef = objectRef2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    objectRef = (Ref.ObjectRef) cloudServerUtil$checkInStorage$1.L$1;
                    ResultKt.throwOnFailure(obj);
                }
                if (((String) objectRef.element) != null) {
                    return null;
                }
                if (!Intrinsics.areEqual((String) objectRef.element, "await_delivey") && !Intrinsics.areEqual((String) objectRef.element, "normal")) {
                    z = false;
                }
                return Boxing.boxBoolean(z);
            }
        }
        cloudServerUtil$checkInStorage$1 = new CloudServerUtil$checkInStorage$1(this, continuation);
        Object obj2 = cloudServerUtil$checkInStorage$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = cloudServerUtil$checkInStorage$1.label;
        boolean z2 = true;
        if (i != 0) {
        }
        if (((String) objectRef.element) != null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object registerTesting(Continuation<? super Boolean> continuation) {
        CloudServerUtil$registerTesting$1 cloudServerUtil$registerTesting$1;
        int i;
        Ref.BooleanRef booleanRef;
        if (continuation instanceof CloudServerUtil$registerTesting$1) {
            cloudServerUtil$registerTesting$1 = (CloudServerUtil$registerTesting$1) continuation;
            if ((cloudServerUtil$registerTesting$1.label & Integer.MIN_VALUE) != 0) {
                cloudServerUtil$registerTesting$1.label -= Integer.MIN_VALUE;
                Object obj = cloudServerUtil$registerTesting$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = cloudServerUtil$registerTesting$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                    booleanRef2.element = false;
                    CoroutineDispatcher io2 = Dispatchers.getIO();
                    CloudServerUtil$registerTesting$2 cloudServerUtil$registerTesting$2 = new CloudServerUtil$registerTesting$2(this, booleanRef2, null);
                    cloudServerUtil$registerTesting$1.L$0 = this;
                    cloudServerUtil$registerTesting$1.L$1 = booleanRef2;
                    cloudServerUtil$registerTesting$1.label = 1;
                    if (BuildersKt.withContext(io2, cloudServerUtil$registerTesting$2, cloudServerUtil$registerTesting$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    booleanRef = booleanRef2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    booleanRef = (Ref.BooleanRef) cloudServerUtil$registerTesting$1.L$1;
                    ResultKt.throwOnFailure(obj);
                }
                return Boxing.boxBoolean(booleanRef.element);
            }
        }
        cloudServerUtil$registerTesting$1 = new CloudServerUtil$registerTesting$1(this, continuation);
        Object obj2 = cloudServerUtil$registerTesting$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = cloudServerUtil$registerTesting$1.label;
        if (i != 0) {
        }
        return Boxing.boxBoolean(booleanRef.element);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object putInStorage(Continuation<? super Boolean> continuation) {
        CloudServerUtil$putInStorage$1 cloudServerUtil$putInStorage$1;
        int i;
        Ref.BooleanRef booleanRef;
        if (continuation instanceof CloudServerUtil$putInStorage$1) {
            cloudServerUtil$putInStorage$1 = (CloudServerUtil$putInStorage$1) continuation;
            if ((cloudServerUtil$putInStorage$1.label & Integer.MIN_VALUE) != 0) {
                cloudServerUtil$putInStorage$1.label -= Integer.MIN_VALUE;
                Object obj = cloudServerUtil$putInStorage$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = cloudServerUtil$putInStorage$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                    booleanRef2.element = false;
                    CoroutineDispatcher io2 = Dispatchers.getIO();
                    CloudServerUtil$putInStorage$2 cloudServerUtil$putInStorage$2 = new CloudServerUtil$putInStorage$2(this, booleanRef2, null);
                    cloudServerUtil$putInStorage$1.L$0 = this;
                    cloudServerUtil$putInStorage$1.L$1 = booleanRef2;
                    cloudServerUtil$putInStorage$1.label = 1;
                    if (BuildersKt.withContext(io2, cloudServerUtil$putInStorage$2, cloudServerUtil$putInStorage$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    booleanRef = booleanRef2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    booleanRef = (Ref.BooleanRef) cloudServerUtil$putInStorage$1.L$1;
                    ResultKt.throwOnFailure(obj);
                }
                return Boxing.boxBoolean(booleanRef.element);
            }
        }
        cloudServerUtil$putInStorage$1 = new CloudServerUtil$putInStorage$1(this, continuation);
        Object obj2 = cloudServerUtil$putInStorage$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = cloudServerUtil$putInStorage$1.label;
        if (i != 0) {
        }
        return Boxing.boxBoolean(booleanRef.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getState() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mac", WifiUtil.INSTANCE.getMac());
        jSONObject.put("timestamp", System.currentTimeMillis() / 1000);
        jSONObject.put("type", "life_state");
        Pdlog.m3273d(TAG, "checkInTesting " + jSONObject + " ，url:" + url);
        Request.Builder url2 = new Request.Builder().url(url);
        RequestBody.Companion companion = RequestBody.Companion;
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "json.toString()");
        Request build = url2.post(companion.create(jSONObject2, this.jsonType)).build();
        try {
            OkHttpClient okHttpClient = this.client;
            if (okHttpClient == null) {
                Intrinsics.throwNpe();
            }
            Response execute = okHttpClient.newCall(build).execute();
            Pdlog.m3275i(TAG, "checkInTesting response=" + execute + " body=" + execute.body());
            if (!execute.isSuccessful()) {
                Function1<? super String, Unit> function1 = this.onCloudFailResponse;
                if (function1 != null) {
                    function1.invoke("应答失败 " + execute);
                }
                return null;
            }
            ResponseBody body = execute.body();
            if (body == null) {
                Intrinsics.throwNpe();
            }
            JSONObject jSONObject3 = new JSONObject(body.string());
            Pdlog.m3275i(TAG, String.valueOf(jSONObject3));
            if (jSONObject3.getInt("code") == 0 && !(!Intrinsics.areEqual(jSONObject3.getString(NotificationCompat.CATEGORY_MESSAGE), "success"))) {
                return jSONObject3.getJSONObject("data").getString("robot_type");
            }
            Function1<? super String, Unit> function12 = this.onCloudFailResponse;
            if (function12 != null) {
                function12.invoke("协议错误 " + jSONObject3);
            }
            return null;
        } catch (Exception e) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("返回值不合法 ");
            sb.append(e);
            sb.append(' ');
            e.printStackTrace();
            sb.append(Unit.INSTANCE);
            Pdlog.m3277w(str, sb.toString());
            Function1<? super Exception, Unit> function13 = this.onException;
            if (function13 != null) {
                function13.invoke(e);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean uploadState(String string) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mac", WifiUtil.INSTANCE.getMac());
        jSONObject.put("timestamp", SystemClock.elapsedRealtime() / 1000);
        jSONObject.put("type", "update_state");
        jSONObject.put("state", string);
        Pdlog.m3273d(TAG, "uploadState " + jSONObject);
        Request.Builder url2 = new Request.Builder().url(url);
        RequestBody.Companion companion = RequestBody.Companion;
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "json.toString()");
        Request build = url2.post(companion.create(jSONObject2, this.jsonType)).build();
        try {
            OkHttpClient okHttpClient = this.client;
            if (okHttpClient == null) {
                Intrinsics.throwNpe();
            }
            Response execute = okHttpClient.newCall(build).execute();
            Pdlog.m3275i(TAG, "uploadState response=" + execute + " body=" + execute.body());
            if (!execute.isSuccessful()) {
                Function1<? super String, Unit> function1 = this.onCloudFailResponse;
                if (function1 != null) {
                    function1.invoke("服务器应答失败 " + execute);
                }
                return false;
            }
            ResponseBody body = execute.body();
            if (body == null) {
                Intrinsics.throwNpe();
            }
            JSONObject jSONObject3 = new JSONObject(body.string());
            Pdlog.m3275i(TAG, "result=" + jSONObject3);
            if (jSONObject3.getInt("code") == 0 && !(!Intrinsics.areEqual(jSONObject3.getString(NotificationCompat.CATEGORY_MESSAGE), "success"))) {
                return !(Intrinsics.areEqual(jSONObject3.getString("data"), string) ^ true);
            }
            Function1<? super String, Unit> function12 = this.onCloudFailResponse;
            if (function12 != null) {
                function12.invoke("服务器返回数据不合法 " + jSONObject3);
            }
            return false;
        } catch (Exception e) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("返回值不合法 ");
            sb.append(e);
            sb.append(' ');
            e.printStackTrace();
            sb.append(Unit.INSTANCE);
            Pdlog.m3277w(str, sb.toString());
            Function1<? super Exception, Unit> function13 = this.onException;
            if (function13 != null) {
                function13.invoke(e);
            }
            return false;
        }
    }

    private final OkHttpClient getOKHttpClient(Context context) {
        try {
            if (!isTest) {
                Pdlog.m3273d(TAG, "getOKHttpClient context=" + context + ". for release");
                OkHttpClient.Builder suportHttpsParams = HttpsUtils.INSTANCE.setSuportHttpsParams(context, new OkHttpClient.Builder(), context.getAssets().open(BuildConfig.CloudCA_FILE), true);
                suportHttpsParams.hostnameVerifier(new HostnameVerifier() { // from class: com.pudutech.factory_test.test_pack.cloud_server.CloudServerUtil$getOKHttpClient$1
                    @Override // javax.net.ssl.HostnameVerifier
                    public final boolean verify(String str, SSLSession session) {
                        String str2 = CloudServerUtil.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("getOKHttpClient hostname=");
                        sb.append(str);
                        sb.append("  session.protocol=");
                        Intrinsics.checkExpressionValueIsNotNull(session, "session");
                        sb.append(session.getProtocol());
                        Pdlog.m3273d(str2, sb.toString());
                        return true;
                    }
                });
                return suportHttpsParams.build();
            }
            Pdlog.m3273d(TAG, "getOKHttpClient context=" + context + ". for debug");
            return new OkHttpClient();
        } catch (Exception e) {
            String str = TAG;
            e.printStackTrace();
            Pdlog.m3274e(str, String.valueOf(Unit.INSTANCE));
            return null;
        }
    }
}
