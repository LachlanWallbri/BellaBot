package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveCruiseTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveMode;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveCruise.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveCruise$active$1", m3970f = "MoveCruise.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class MoveCruise$active$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6598p$;
    final /* synthetic */ MoveCruise this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveCruise$active$1(MoveCruise moveCruise, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveCruise;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveCruise$active$1 moveCruise$active$1 = new MoveCruise$active$1(this.this$0, completion);
        moveCruise$active$1.f6598p$ = (CoroutineScope) obj;
        return moveCruise$active$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveCruise$active$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator;
        MoveCruiseTask moveCruiseTask;
        MoveTaskMode moveTaskMode;
        MoveCruiseTask moveCruiseTask2;
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator2;
        MoveCruiseTask moveCruiseTask3;
        MoveCruiseTask moveCruiseTask4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6598p$;
        if (this.this$0.getCurrentMoveState() == RobotState.Pause || this.this$0.getCurrentMoveState() == RobotState.Arrive || this.this$0.getCurrentMoveState() == RobotState.Error) {
            robotMoveInterfaceDecorator = this.this$0.moveInterfaceDecorator;
            robotMoveInterfaceDecorator.resume();
        } else {
            moveCruiseTask = this.this$0.task;
            if (moveCruiseTask.getMoveMode() == MoveMode.Steady) {
                moveTaskMode = MoveTaskMode.Steady;
            } else {
                moveTaskMode = MoveTaskMode.Normal;
            }
            ArrayList arrayList = new ArrayList();
            moveCruiseTask2 = this.this$0.task;
            ArrayList<String> stayPoint = moveCruiseTask2.getStayPoint();
            if (stayPoint != null) {
                Boxing.boxBoolean(arrayList.addAll(stayPoint));
            }
            robotMoveInterfaceDecorator2 = this.this$0.moveInterfaceDecorator;
            moveCruiseTask3 = this.this$0.task;
            robotMoveInterfaceDecorator2.goCruisePath(moveCruiseTask3.getId(), moveTaskMode, arrayList);
            MoveCruise moveCruise = this.this$0;
            moveCruiseTask4 = moveCruise.task;
            moveCruise.startRecodeReport(String.valueOf(moveCruiseTask4.getId()));
        }
        return Unit.INSTANCE;
    }
}
