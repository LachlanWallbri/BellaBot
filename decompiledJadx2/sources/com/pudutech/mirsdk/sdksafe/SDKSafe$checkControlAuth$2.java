package com.pudutech.mirsdk.sdksafe;

import android.app.Activity;
import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: SDKSafe.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.sdksafe.SDKSafe$checkControlAuth$2", m3970f = "SDKSafe.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class SDKSafe$checkControlAuth$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ String $node;
    final /* synthetic */ Function1 $result;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6457p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SDKSafe$checkControlAuth$2(Function1 function1, Activity activity, String str, Continuation continuation) {
        super(2, continuation);
        this.$result = function1;
        this.$activity = activity;
        this.$node = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKSafe$checkControlAuth$2 sDKSafe$checkControlAuth$2 = new SDKSafe$checkControlAuth$2(this.$result, this.$activity, this.$node, completion);
        sDKSafe$checkControlAuth$2.f6457p$ = (CoroutineScope) obj;
        return sDKSafe$checkControlAuth$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKSafe$checkControlAuth$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        WeakReference weakReference;
        AtomicBoolean atomicBoolean;
        long j;
        long j2;
        String str;
        boolean z2;
        String str2;
        String str3;
        String str4;
        String str5;
        int i;
        String str6;
        String str7;
        String str8;
        int i2;
        String str9;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6457p$;
        SDKSafe sDKSafe = SDKSafe.INSTANCE;
        z = SDKSafe.closeChecked;
        if (!z) {
            SDKSafe.INSTANCE.reloadWebViewProvider();
            SDKSafe sDKSafe2 = SDKSafe.INSTANCE;
            SDKSafe.authMatched = false;
            SDKSafe sDKSafe3 = SDKSafe.INSTANCE;
            weakReference = SDKSafe.weakActivity;
            if (!Intrinsics.areEqual(weakReference != null ? (Activity) weakReference.get() : null, this.$activity)) {
                SDKSafe sDKSafe4 = SDKSafe.INSTANCE;
                SDKSafe.weakActivity = new WeakReference(this.$activity);
            }
            SDKSafe sDKSafe5 = SDKSafe.INSTANCE;
            atomicBoolean = SDKSafe.notProductVersion;
            if (atomicBoolean == null) {
                SDKSafe sDKSafe6 = SDKSafe.INSTANCE;
                SDKSafe.notProductVersion = new AtomicBoolean(new File("/sdcard/TestServer").exists());
                SDKSafe sDKSafe7 = SDKSafe.INSTANCE;
                SDKSafe.appId = SDKSafe.access$getNotProductVersion$p(SDKSafe.INSTANCE).get() ? 9544359 : 64382648;
                SDKSafe sDKSafe8 = SDKSafe.INSTANCE;
                SDKSafe.appSecret = SDKSafe.access$getNotProductVersion$p(SDKSafe.INSTANCE).get() ? "VPeWFpD99slYm1RQ" : "R3WGe4TaW4HPJvOC";
                SDKSafe sDKSafe9 = SDKSafe.INSTANCE;
                SDKSafe.baseUrl = SDKSafe.access$getNotProductVersion$p(SDKSafe.INSTANCE).get() ? "https://oauth2-test.pudutech.com/" : "https://oauth2.pudutech.com/";
                Locale locale = Locale.getDefault();
                Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
                String language = locale.getLanguage();
                SDKSafe sDKSafe10 = SDKSafe.INSTANCE;
                str3 = SDKSafe.TAG;
                Pdlog.m3273d(str3, "language " + language);
                if (Intrinsics.areEqual(language, "zh")) {
                    SDKSafe sDKSafe11 = SDKSafe.INSTANCE;
                    StringBuilder sb = new StringBuilder();
                    SDKSafe sDKSafe12 = SDKSafe.INSTANCE;
                    str7 = SDKSafe.baseUrl;
                    sb.append(str7);
                    SDKSafe sDKSafe13 = SDKSafe.INSTANCE;
                    str8 = SDKSafe.basePage;
                    sb.append(str8);
                    sb.append("&appid=");
                    SDKSafe sDKSafe14 = SDKSafe.INSTANCE;
                    i2 = SDKSafe.appId;
                    sb.append(i2);
                    sb.append("&locale=zh-cn");
                    SDKSafe sDKSafe15 = SDKSafe.INSTANCE;
                    str9 = SDKSafe.loginWay;
                    sb.append(str9);
                    sDKSafe11.setPage$sdksafe_release(sb.toString());
                } else {
                    SDKSafe sDKSafe16 = SDKSafe.INSTANCE;
                    StringBuilder sb2 = new StringBuilder();
                    SDKSafe sDKSafe17 = SDKSafe.INSTANCE;
                    str4 = SDKSafe.baseUrl;
                    sb2.append(str4);
                    SDKSafe sDKSafe18 = SDKSafe.INSTANCE;
                    str5 = SDKSafe.basePage;
                    sb2.append(str5);
                    sb2.append("&appid=");
                    SDKSafe sDKSafe19 = SDKSafe.INSTANCE;
                    i = SDKSafe.appId;
                    sb2.append(i);
                    sb2.append("&locale=en-us");
                    SDKSafe sDKSafe20 = SDKSafe.INSTANCE;
                    str6 = SDKSafe.loginWay;
                    sb2.append(str6);
                    sDKSafe16.setPage$sdksafe_release(sb2.toString());
                }
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            SDKSafe sDKSafe21 = SDKSafe.INSTANCE;
            j = SDKSafe.tokenBirth;
            if (j == 0) {
                SDKSafe sDKSafe22 = SDKSafe.INSTANCE;
                SDKSafe.tokenBirth = elapsedRealtime;
            }
            SDKSafe sDKSafe23 = SDKSafe.INSTANCE;
            j2 = SDKSafe.tokenBirth;
            if (elapsedRealtime - j2 >= 10800000) {
                SDKSafe sDKSafe24 = SDKSafe.INSTANCE;
                SDKSafe.tokenKey = "";
                SDKSafe sDKSafe25 = SDKSafe.INSTANCE;
                str2 = SDKSafe.TAG;
                Pdlog.m3273d(str2, "clear token, pls relogin");
            }
            SDKSafe sDKSafe26 = SDKSafe.INSTANCE;
            str = SDKSafe.tokenKey;
            if (Intrinsics.areEqual(str, "")) {
                SDKSafe.INSTANCE.loginTask(this.$activity, this.$node);
            } else {
                SDKSafe.INSTANCE.chkTask(this.$activity, this.$node, new Function2<Integer, String, Unit>() { // from class: com.pudutech.mirsdk.sdksafe.SDKSafe$checkControlAuth$2.2
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, String str10) {
                        invoke(num.intValue(), str10);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(int i3, String err) {
                        Intrinsics.checkParameterIsNotNull(err, "err");
                        if (i3 != 0) {
                            SDKSafe.INSTANCE.loginTask(SDKSafe$checkControlAuth$2.this.$activity, SDKSafe$checkControlAuth$2.this.$node);
                        }
                    }
                });
            }
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                SDKSafe sDKSafe27 = SDKSafe.INSTANCE;
                z2 = SDKSafe.authMatched;
                if (z2) {
                    break;
                }
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                this.$result.invoke(Boxing.boxInt(0));
            } else {
                this.$result.invoke(Boxing.boxInt(-1));
            }
            return Unit.INSTANCE;
        }
        this.$result.invoke(Boxing.boxInt(0));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes6.dex
     */
    /* compiled from: SDKSafe.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.sdksafe.SDKSafe$checkControlAuth$2$1 */
    /* loaded from: classes2.dex */
    final /* synthetic */ class C53101 extends MutablePropertyReference0 {
        C53101(SDKSafe sDKSafe) {
            super(sDKSafe);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public String getName() {
            return "notProductVersion";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(SDKSafe.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public String getSignature() {
            return "getNotProductVersion()Ljava/util/concurrent/atomic/AtomicBoolean;";
        }

        @Override // kotlin.reflect.KProperty0
        public Object get() {
            return SDKSafe.access$getNotProductVersion$p((SDKSafe) this.receiver);
        }

        @Override // kotlin.reflect.KMutableProperty0
        public void set(Object obj) {
            SDKSafe.notProductVersion = (AtomicBoolean) obj;
        }

        /* compiled from: SDKSafe.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.mirsdk.sdksafe.SDKSafe$checkControlAuth$2$1$1", m3970f = "SDKSafe.kt", m3971i = {0}, m3972l = {105}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking"}, m3975s = {"L$0"})
        /* renamed from: com.pudutech.mirsdk.sdksafe.SDKSafe$checkControlAuth$2$1$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            Object L$0;
            int label;

            /* renamed from: p$ */
            private CoroutineScope f6458p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                anonymousClass1.f6458p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Job job;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f6458p$;
                    SDKSafe sDKSafe = SDKSafe.INSTANCE;
                    job = SDKSafe.job;
                    if (job == null) {
                        return null;
                    }
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }
}
