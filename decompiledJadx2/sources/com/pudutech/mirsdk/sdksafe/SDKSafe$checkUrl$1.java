package com.pudutech.mirsdk.sdksafe;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.sdksafe.LoginActivity;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.bouncycastle.math.Primes;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: SDKSafe.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.sdksafe.SDKSafe$checkUrl$1", m3970f = "SDKSafe.kt", m3971i = {0, 0, 0, 0, 0}, m3972l = {Primes.SMALL_FACTOR_LIMIT}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "ticket", "tokenApi", "retrofit", "token"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes2.dex */
public final class SDKSafe$checkUrl$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $callback;
    final /* synthetic */ String $node;
    final /* synthetic */ String $url;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6459p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SDKSafe$checkUrl$1(Function1 function1, String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.$callback = function1;
        this.$url = str;
        this.$node = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKSafe$checkUrl$1 sDKSafe$checkUrl$1 = new SDKSafe$checkUrl$1(this.$callback, this.$url, this.$node, completion);
        sDKSafe$checkUrl$1.f6459p$ = (CoroutineScope) obj;
        return sDKSafe$checkUrl$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKSafe$checkUrl$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        WeakReference weakReference;
        String str;
        SDKSafeRequestInterface requestInterface;
        int i;
        String sign;
        String str2;
        Activity activity;
        String str3;
        WeakReference weakReference2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6459p$;
            this.$callback.invoke(LoginActivity.LoginResult.MatchUrl);
            String substringAfter$default = StringsKt.substringAfter$default(this.$url, "ticket=", (String) null, 2, (Object) null);
            SDKSafe sDKSafe = SDKSafe.INSTANCE;
            SDKSafe sDKSafe2 = SDKSafe.INSTANCE;
            weakReference = SDKSafe.weakActivity;
            Context applicationContext = (weakReference == null || (activity = (Activity) weakReference.get()) == null) ? null : activity.getApplicationContext();
            if (applicationContext == null) {
                Intrinsics.throwNpe();
            }
            SDKSafe sDKSafe3 = SDKSafe.INSTANCE;
            str = SDKSafe.baseUrl;
            requestInterface = sDKSafe.getRequestInterface(applicationContext, str);
            TokenRequest tokenRequest = new TokenRequest();
            SDKSafe sDKSafe4 = SDKSafe.INSTANCE;
            i = SDKSafe.appId;
            tokenRequest.setAppid(String.valueOf(i));
            tokenRequest.setTicket(substringAfter$default);
            tokenRequest.setTimestamp(System.currentTimeMillis());
            sign = SDKSafe.INSTANCE.getSign(tokenRequest.getTicket(), tokenRequest.getAppid(), tokenRequest.getTimestamp());
            tokenRequest.setSignature(sign);
            StringBuilder sb = new StringBuilder();
            SDKSafe sDKSafe5 = SDKSafe.INSTANCE;
            str2 = SDKSafe.baseUrl;
            sb.append(str2);
            sb.append("api/get_token/v1");
            String sb2 = sb.toString();
            this.L$0 = coroutineScope;
            this.L$1 = substringAfter$default;
            this.L$2 = "api/get_token/v1";
            this.L$3 = requestInterface;
            this.L$4 = tokenRequest;
            this.label = 1;
            obj = requestInterface.token(tokenRequest, sb2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        ResponseMsg responseMsg = (ResponseMsg) obj;
        if (responseMsg.getCode() == 0) {
            SDKSafe sDKSafe6 = SDKSafe.INSTANCE;
            SDKSafe.tokenKey = ((TokenResponse) responseMsg.getData()).getToken();
            SDKSafe sDKSafe7 = SDKSafe.INSTANCE;
            SDKSafe.tokenBirth = SystemClock.elapsedRealtime();
            SDKSafe sDKSafe8 = SDKSafe.INSTANCE;
            SDKSafe sDKSafe9 = SDKSafe.INSTANCE;
            weakReference2 = SDKSafe.weakActivity;
            Activity activity2 = weakReference2 != null ? (Activity) weakReference2.get() : null;
            if (activity2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(activity2, "weakActivity?.get()!!");
            sDKSafe8.chkTask(activity2, this.$node, new Function2<Integer, String, Unit>() { // from class: com.pudutech.mirsdk.sdksafe.SDKSafe$checkUrl$1.1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Integer num, String str4) {
                    invoke(num.intValue(), str4);
                    return Unit.INSTANCE;
                }

                public final void invoke(int i3, String error) {
                    WeakReference weakReference3;
                    LoginActivity loginActivity;
                    Intrinsics.checkParameterIsNotNull(error, "error");
                    if (i3 == 0) {
                        SDKSafe sDKSafe10 = SDKSafe.INSTANCE;
                        weakReference3 = SDKSafe.loginActivity;
                        if (weakReference3 == null || (loginActivity = (LoginActivity) weakReference3.get()) == null) {
                            return;
                        }
                        loginActivity.finish();
                        return;
                    }
                    SDKSafe$checkUrl$1.this.$callback.invoke(LoginActivity.LoginResult.AuthFailed);
                }
            });
        } else {
            SDKSafe sDKSafe10 = SDKSafe.INSTANCE;
            str3 = SDKSafe.TAG;
            Pdlog.m3275i(str3, "登录异常结果： " + responseMsg.getMsg());
            this.$callback.invoke(LoginActivity.LoginResult.LoginError);
        }
        return Unit.INSTANCE;
    }
}
