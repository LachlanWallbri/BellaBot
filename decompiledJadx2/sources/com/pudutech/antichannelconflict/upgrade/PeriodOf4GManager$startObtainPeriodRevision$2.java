package com.pudutech.antichannelconflict.upgrade;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PeriodOf4GManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$startObtainPeriodRevision$2", m3970f = "PeriodOf4GManager.kt", m3971i = {0, 1, 1, 2, 2, 2, 3, 3}, m3972l = {359, 375, 377, 387}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, C3898x.f4338g, "$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES}, m3975s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1"})
/* loaded from: classes4.dex */
public final class PeriodOf4GManager$startObtainPeriodRevision$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4477p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeriodOf4GManager$startObtainPeriodRevision$2(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PeriodOf4GManager$startObtainPeriodRevision$2 periodOf4GManager$startObtainPeriodRevision$2 = new PeriodOf4GManager$startObtainPeriodRevision$2(completion);
        periodOf4GManager$startObtainPeriodRevision$2.f4477p$ = (CoroutineScope) obj;
        return periodOf4GManager$startObtainPeriodRevision$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PeriodOf4GManager$startObtainPeriodRevision$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x01c0 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        long j;
        Object withTimeoutOrNull;
        String str;
        Job job;
        Job job2;
        Job job3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4477p$;
            Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), "startCheckBTSRevision readShell begin");
            PeriodOf4GManager periodOf4GManager = PeriodOf4GManager.INSTANCE;
            j = PeriodOf4GManager.readTime;
            PeriodOf4GManager$startObtainPeriodRevision$2$res$1 periodOf4GManager$startObtainPeriodRevision$2$res$1 = new PeriodOf4GManager$startObtainPeriodRevision$2$res$1(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(j, periodOf4GManager$startObtainPeriodRevision$2$res$1, this);
            if (withTimeoutOrNull == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    String str2 = (String) this.L$1;
                    coroutineScope = (CoroutineScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Exception e) {
                        e = e;
                        str = str2;
                        this.L$0 = coroutineScope;
                        this.L$1 = str;
                        this.L$2 = e;
                        this.label = 3;
                        if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        String access$getTAG$p = PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE);
                        PeriodOf4GManager periodOf4GManager2 = PeriodOf4GManager.INSTANCE;
                        job2 = PeriodOf4GManager.jobRead;
                        Pdlog.m3273d(access$getTAG$p, "startCheckBTSRevision convert Exception", e, job2);
                        PeriodOf4GManager.INSTANCE.startObtainPeriodRevision();
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
                if (i == 3) {
                    e = (Exception) this.L$2;
                    ResultKt.throwOnFailure(obj);
                    String access$getTAG$p2 = PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE);
                    PeriodOf4GManager periodOf4GManager22 = PeriodOf4GManager.INSTANCE;
                    job2 = PeriodOf4GManager.jobRead;
                    Pdlog.m3273d(access$getTAG$p2, "startCheckBTSRevision convert Exception", e, job2);
                    PeriodOf4GManager.INSTANCE.startObtainPeriodRevision();
                    return Unit.INSTANCE;
                }
                if (i == 4) {
                    ResultKt.throwOnFailure(obj);
                    String access$getTAG$p3 = PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE);
                    PeriodOf4GManager periodOf4GManager3 = PeriodOf4GManager.INSTANCE;
                    job3 = PeriodOf4GManager.jobRead;
                    Pdlog.m3273d(access$getTAG$p3, "startCheckBTSRevision timeout ", job3);
                    PeriodOf4GManager.INSTANCE.startObtainPeriodRevision();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            withTimeoutOrNull = obj;
        }
        str = (String) withTimeoutOrNull;
        PeriodOf4GManager periodOf4GManager4 = PeriodOf4GManager.INSTANCE;
        job = PeriodOf4GManager.jobSet;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        String str3 = str;
        if (!(str3 == null || StringsKt.isBlank(str3))) {
            try {
                Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), "startCheckPeriodRevision shell result:", str);
                if (StringsKt.contains$default((CharSequence) str, (CharSequence) "Model:", false, 2, (Object) null) && StringsKt.contains$default((CharSequence) str, (CharSequence) "Revision:", false, 2, (Object) null)) {
                    PeriodOf4GManager periodOf4GManager5 = PeriodOf4GManager.INSTANCE;
                    int indexOf$default = StringsKt.indexOf$default((CharSequence) str, ExifInterface.TAG_MODEL, 0, false, 6, (Object) null);
                    int indexOf$default2 = StringsKt.indexOf$default((CharSequence) str, "Revision", 0, false, 6, (Object) null) - 1;
                    if (str == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String substring = str.substring(indexOf$default, indexOf$default2);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    List split$default = StringsKt.split$default((CharSequence) substring, new String[]{":"}, false, 0, 6, (Object) null);
                    String str4 = split$default != null ? (String) split$default.get(1) : null;
                    if (str4 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                    periodOf4GManager5.setModel_current$AntiChannelConflict_release(StringsKt.trim((CharSequence) str4).toString());
                    PeriodOf4GManager periodOf4GManager6 = PeriodOf4GManager.INSTANCE;
                    int indexOf$default3 = StringsKt.indexOf$default((CharSequence) str, "Revision", 0, false, 6, (Object) null);
                    int length = str.length() - 1;
                    if (str == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String substring2 = str.substring(indexOf$default3, length);
                    Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    List split$default2 = StringsKt.split$default((CharSequence) substring2, new String[]{":"}, false, 0, 6, (Object) null);
                    String str5 = split$default2 != null ? (String) split$default2.get(1) : null;
                    if (str5 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                    periodOf4GManager6.setRevision_current$AntiChannelConflict_release(StringsKt.trim((CharSequence) str5).toString());
                }
                PeriodOf4GManager periodOf4GManager7 = PeriodOf4GManager.INSTANCE;
                this.L$0 = coroutineScope;
                this.L$1 = str;
                this.label = 2;
                if (periodOf4GManager7.checkPeriodVersion(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } catch (Exception e2) {
                e = e2;
                this.L$0 = coroutineScope;
                this.L$1 = str;
                this.L$2 = e;
                this.label = 3;
                if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                }
                String access$getTAG$p22 = PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE);
                PeriodOf4GManager periodOf4GManager222 = PeriodOf4GManager.INSTANCE;
                job2 = PeriodOf4GManager.jobRead;
                Pdlog.m3273d(access$getTAG$p22, "startCheckBTSRevision convert Exception", e, job2);
                PeriodOf4GManager.INSTANCE.startObtainPeriodRevision();
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        this.L$0 = coroutineScope;
        this.L$1 = str;
        this.label = 4;
        if (DelayKt.delay(1000L, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        String access$getTAG$p32 = PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE);
        PeriodOf4GManager periodOf4GManager32 = PeriodOf4GManager.INSTANCE;
        job3 = PeriodOf4GManager.jobRead;
        Pdlog.m3273d(access$getTAG$p32, "startCheckBTSRevision timeout ", job3);
        PeriodOf4GManager.INSTANCE.startObtainPeriodRevision();
        return Unit.INSTANCE;
    }
}
