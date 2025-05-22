package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupReport;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByCharge.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByCharge$genMoveReport$2", m3970f = "MoveByCharge.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class MoveByCharge$genMoveReport$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $r */
    final /* synthetic */ Ref.ObjectRef f6548$r;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6549p$;
    final /* synthetic */ MoveByCharge this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByCharge$genMoveReport$2(MoveByCharge moveByCharge, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveByCharge;
        this.f6548$r = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveByCharge$genMoveReport$2 moveByCharge$genMoveReport$2 = new MoveByCharge$genMoveReport$2(this.this$0, this.f6548$r, completion);
        moveByCharge$genMoveReport$2.f6549p$ = (CoroutineScope) obj;
        return moveByCharge$genMoveReport$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveByCharge$genMoveReport$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MoveByGroupReport moveByGroupReport;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6549p$;
        moveByGroupReport = this.this$0.moveByGroupReport;
        moveByGroupReport.setMoveReportData((MoveReportData) this.f6548$r.element);
        return Unit.INSTANCE;
    }
}
