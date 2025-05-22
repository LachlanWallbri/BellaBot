package com.pudutech.antichannelconflict.upgrade;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.loc.C3898x;
import com.pudutech.antichannelconflict.escape.util.GsonUtils;
import com.pudutech.antichannelconflict.location.network.LocationBTS;
import com.pudutech.antichannelconflict.upgrade.listener.PeriodStatusListener;
import com.pudutech.base.Pdlog;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
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
@DebugMetadata(m3969c = "com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$startObtainBTS$1", m3970f = "PeriodOf4GManager.kt", m3971i = {0, 1, 1, 1, 2, 2}, m3972l = {467, 488, 494}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, C3898x.f4338g, "$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES}, m3975s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1"})
/* loaded from: classes4.dex */
public final class PeriodOf4GManager$startObtainBTS$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4474p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeriodOf4GManager$startObtainBTS$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PeriodOf4GManager$startObtainBTS$1 periodOf4GManager$startObtainBTS$1 = new PeriodOf4GManager$startObtainBTS$1(completion);
        periodOf4GManager$startObtainBTS$1.f4474p$ = (CoroutineScope) obj;
        return periodOf4GManager$startObtainBTS$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PeriodOf4GManager$startObtainBTS$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        long j;
        Object withTimeoutOrNull;
        Job job;
        Job job2;
        Job job3;
        PeriodStatusListener periodStatusListener;
        Job job4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4474p$;
            Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), "startObtainBTS readShell begin");
            PeriodOf4GManager periodOf4GManager = PeriodOf4GManager.INSTANCE;
            j = PeriodOf4GManager.readTime;
            PeriodOf4GManager$startObtainBTS$1$res$1 periodOf4GManager$startObtainBTS$1$res$1 = new PeriodOf4GManager$startObtainBTS$1$res$1(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(j, periodOf4GManager$startObtainBTS$1$res$1, this);
            if (withTimeoutOrNull == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    PeriodOf4GManager.INSTANCE.startObtainBTS();
                    return Unit.INSTANCE;
                }
                if (i == 3) {
                    ResultKt.throwOnFailure(obj);
                    String access$getTAG$p = PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE);
                    PeriodOf4GManager periodOf4GManager2 = PeriodOf4GManager.INSTANCE;
                    job4 = PeriodOf4GManager.jobRead;
                    Pdlog.m3273d(access$getTAG$p, "startObtainBTS timeout ", job4);
                    PeriodOf4GManager.INSTANCE.startObtainBTS();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            withTimeoutOrNull = obj;
        }
        CoroutineScope coroutineScope2 = coroutineScope;
        String str = (String) withTimeoutOrNull;
        PeriodOf4GManager periodOf4GManager3 = PeriodOf4GManager.INSTANCE;
        job = PeriodOf4GManager.jobSet;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        String str2 = str;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            try {
                PeriodOf4GManager.INSTANCE.setBts$AntiChannelConflict_release(((LocationBTS) GsonUtils.fromJson('{' + StringsKt.replace$default(str, ",,", ",", false, 4, (Object) null) + '}', LocationBTS.class)).toString());
                String access$getTAG$p2 = PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE);
                PeriodOf4GManager periodOf4GManager4 = PeriodOf4GManager.INSTANCE;
                job3 = PeriodOf4GManager.jobRead;
                Pdlog.m3273d(access$getTAG$p2, "startObtainBTS success", PeriodOf4GManager.INSTANCE.getBts$AntiChannelConflict_release(), job3);
                PeriodOf4GManager periodOf4GManager5 = PeriodOf4GManager.INSTANCE;
                periodStatusListener = PeriodOf4GManager.myStatusListener;
                periodStatusListener.onObtainBTS(PeriodOf4GManager.INSTANCE.getBts$AntiChannelConflict_release());
            } catch (Exception e) {
                String access$getTAG$p3 = PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE);
                PeriodOf4GManager periodOf4GManager6 = PeriodOf4GManager.INSTANCE;
                job2 = PeriodOf4GManager.jobRead;
                Pdlog.m3273d(access$getTAG$p3, "startObtainBTS convert Exception", e, job2);
                this.L$0 = coroutineScope2;
                this.L$1 = str;
                this.L$2 = e;
                this.label = 2;
                if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        this.L$0 = coroutineScope2;
        this.L$1 = str;
        this.label = 3;
        if (DelayKt.delay(1000L, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        String access$getTAG$p4 = PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE);
        PeriodOf4GManager periodOf4GManager22 = PeriodOf4GManager.INSTANCE;
        job4 = PeriodOf4GManager.jobRead;
        Pdlog.m3273d(access$getTAG$p4, "startObtainBTS timeout ", job4);
        PeriodOf4GManager.INSTANCE.startObtainBTS();
        return Unit.INSTANCE;
    }
}
