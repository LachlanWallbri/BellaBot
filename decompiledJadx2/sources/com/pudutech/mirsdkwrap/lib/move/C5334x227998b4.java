package com.pudutech.mirsdkwrap.lib.move;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener;
import com.pudutech.mirsdkwrap.lib.move.bean.ElevatorEventParam;
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

/* compiled from: BaseSolicitMove.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/mirsdkwrap/lib/interf/BaseRobotMoveStateListener;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.BaseSolicitMove$elevatorRequestListener$1$informElevatorUtilizeState$1", m3970f = "BaseSolicitMove.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* renamed from: com.pudutech.mirsdkwrap.lib.move.BaseSolicitMove$elevatorRequestListener$1$informElevatorUtilizeState$1 */
/* loaded from: classes4.dex */
final class C5334x227998b4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ElevatorUtilizeState $p0;
    final /* synthetic */ ElevatorEventParam $parserElevator;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6538p$;
    final /* synthetic */ BaseSolicitMove$elevatorRequestListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C5334x227998b4(BaseSolicitMove$elevatorRequestListener$1 baseSolicitMove$elevatorRequestListener$1, ElevatorUtilizeState elevatorUtilizeState, ElevatorEventParam elevatorEventParam, Continuation continuation) {
        super(2, continuation);
        this.this$0 = baseSolicitMove$elevatorRequestListener$1;
        this.$p0 = elevatorUtilizeState;
        this.$parserElevator = elevatorEventParam;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C5334x227998b4 c5334x227998b4 = new C5334x227998b4(this.this$0, this.$p0, this.$parserElevator, completion);
        c5334x227998b4.f6538p$ = (CoroutineScope) obj;
        return c5334x227998b4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C5334x227998b4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6538p$;
        BaseRobotMoveStateListener onMoveStateListener = this.this$0.this$0.getOnMoveStateListener();
        if (onMoveStateListener != null) {
            onMoveStateListener.onElevatorUtilizeState(this.$p0, this.$parserElevator);
        }
        return Unit.INSTANCE;
    }
}
