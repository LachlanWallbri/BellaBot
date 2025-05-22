package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveMode;
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
/* compiled from: MoveByGroup.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByGroup$active$1", m3970f = "MoveByGroup.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class MoveByGroup$active$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6574p$;
    final /* synthetic */ MoveByGroup this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByGroup$active$1(MoveByGroup moveByGroup, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveByGroup;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveByGroup$active$1 moveByGroup$active$1 = new MoveByGroup$active$1(this.this$0, completion);
        moveByGroup$active$1.f6574p$ = (CoroutineScope) obj;
        return moveByGroup$active$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveByGroup$active$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator;
        MoveByGroupTask moveByGroupTask;
        MoveTaskMode moveTaskMode;
        MoveByGroupTask moveByGroupTask2;
        MoveByGroupTask moveByGroupTask3;
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator2;
        MoveByGroupTask moveByGroupTask4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6574p$;
        if (this.this$0.getCurrentMoveState() == RobotState.Pause || this.this$0.getCurrentMoveState() == RobotState.Error) {
            robotMoveInterfaceDecorator = this.this$0.moveInterfaceDecorator;
            robotMoveInterfaceDecorator.resume();
        } else {
            moveByGroupTask = this.this$0.task;
            if (moveByGroupTask.getMoveMode() == MoveMode.Steady) {
                moveTaskMode = MoveTaskMode.Steady;
            } else {
                moveTaskMode = MoveTaskMode.Normal;
            }
            MoveByGroup moveByGroup = this.this$0;
            moveByGroupTask2 = moveByGroup.task;
            moveByGroup.startRecodeReport(moveByGroupTask2.getGroupName());
            String tag = this.this$0.getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("active moveByGroup:");
            moveByGroupTask3 = this.this$0.task;
            sb.append(moveByGroupTask3.getGroupName());
            Pdlog.m3273d(tag, sb.toString());
            robotMoveInterfaceDecorator2 = this.this$0.moveInterfaceDecorator;
            moveByGroupTask4 = this.this$0.task;
            robotMoveInterfaceDecorator2.goHome(moveByGroupTask4.getGroupName(), moveTaskMode);
        }
        return Unit.INSTANCE;
    }
}
