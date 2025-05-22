package com.pudutech.shared.lib_syntime;

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
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SntpTimeSyncManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.shared.lib_syntime.SntpTimeSyncManager$callMain$2", m3970f = "SntpTimeSyncManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes7.dex */
final class SntpTimeSyncManager$callMain$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $nowMills;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7504p$;
    final /* synthetic */ SntpTimeSyncManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SntpTimeSyncManager$callMain$2(SntpTimeSyncManager sntpTimeSyncManager, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sntpTimeSyncManager;
        this.$nowMills = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SntpTimeSyncManager$callMain$2 sntpTimeSyncManager$callMain$2 = new SntpTimeSyncManager$callMain$2(this.this$0, this.$nowMills, completion);
        sntpTimeSyncManager$callMain$2.f7504p$ = (CoroutineScope) obj;
        return sntpTimeSyncManager$callMain$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SntpTimeSyncManager$callMain$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7504p$;
        Function1<Long, Unit> onTimeSyncSuccess = this.this$0.getOnTimeSyncSuccess();
        if (onTimeSyncSuccess != null) {
            return onTimeSyncSuccess.invoke(Boxing.boxLong(this.$nowMills));
        }
        return null;
    }
}
