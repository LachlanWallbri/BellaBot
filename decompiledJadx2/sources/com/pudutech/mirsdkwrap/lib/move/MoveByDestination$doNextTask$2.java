package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByDestination.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByDestination$doNextTask$2", m3970f = "MoveByDestination.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class MoveByDestination$doNextTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $d */
    final /* synthetic */ Destination f6560$d;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6561p$;
    final /* synthetic */ MoveByDestination this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByDestination$doNextTask$2(MoveByDestination moveByDestination, Destination destination, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveByDestination;
        this.f6560$d = destination;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveByDestination$doNextTask$2 moveByDestination$doNextTask$2 = new MoveByDestination$doNextTask$2(this.this$0, this.f6560$d, completion);
        moveByDestination$doNextTask$2.f6561p$ = (CoroutineScope) obj;
        return moveByDestination$doNextTask$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveByDestination$doNextTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6561p$;
        Pdlog.m3273d(this.this$0.getTAG(), "doNextTask1 " + this.f6560$d.getName());
        MoveByDestinationStateListener onMoveStateListener = this.this$0.getOnMoveStateListener();
        if (onMoveStateListener != null) {
            onMoveStateListener.onDone(this.f6560$d);
        }
        return Unit.INSTANCE;
    }
}
