package com.pudutech.antichannelconflict.upgrade;

import com.pudutech.base.Pdlog;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PeriodOf4GManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager$sendBTSShell$1", m3970f = "PeriodOf4GManager.kt", m3971i = {0}, m3972l = {512}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class PeriodOf4GManager$sendBTSShell$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mDevice;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4472p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeriodOf4GManager$sendBTSShell$1(String str, Continuation continuation) {
        super(2, continuation);
        this.$mDevice = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PeriodOf4GManager$sendBTSShell$1 periodOf4GManager$sendBTSShell$1 = new PeriodOf4GManager$sendBTSShell$1(this.$mDevice, completion);
        periodOf4GManager$sendBTSShell$1.f4472p$ = (CoroutineScope) obj;
        return periodOf4GManager$sendBTSShell$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PeriodOf4GManager$sendBTSShell$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4472p$;
            Pdlog.m3273d(PeriodOf4GManager.access$getTAG$p(PeriodOf4GManager.INSTANCE), " sendBTSShell begin");
            PeriodOf4GManager.INSTANCE.getBTSFromShell(this.$mDevice);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(5000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (StringsKt.isBlank(PeriodOf4GManager.INSTANCE.getBts$AntiChannelConflict_release())) {
            PeriodOf4GManager.INSTANCE.sendBTSShell(this.$mDevice);
        }
        return Unit.INSTANCE;
    }
}
