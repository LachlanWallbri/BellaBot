package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.ElevatorConnectionType;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.movetask.ElevatorTask;
import java.util.List;
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
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$initElvCommuicateScheme$1", m3970f = "MoveAction.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class MoveAction$initElvCommuicateScheme$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5538p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$initElvCommuicateScheme$1(MoveAction moveAction, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$initElvCommuicateScheme$1 moveAction$initElvCommuicateScheme$1 = new MoveAction$initElvCommuicateScheme$1(this.this$0, completion);
        moveAction$initElvCommuicateScheme$1.f5538p$ = (CoroutineScope) obj;
        return moveAction$initElvCommuicateScheme$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$initElvCommuicateScheme$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        ElevatorConnectionType elevatorConnectionType;
        List list;
        ElevatorTask elevatorTask;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5538p$;
        String string = SDKConfig.INSTANCE.getPreferences().getString("elevator_conn_type", ElevatorConnectionType.Lora.name());
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "last select ElvatorServer is " + string + ' ');
        ElevatorTask.Companion companion = ElevatorTask.INSTANCE;
        if (Intrinsics.areEqual(string, ElevatorConnectionType.CommCat.name())) {
            elevatorConnectionType = ElevatorConnectionType.CommCat;
        } else if (Intrinsics.areEqual(string, ElevatorConnectionType.Lora.name())) {
            elevatorConnectionType = ElevatorConnectionType.Lora;
        } else if (Intrinsics.areEqual(string, ElevatorConnectionType.Mqtt.name())) {
            elevatorConnectionType = ElevatorConnectionType.Mqtt;
        } else {
            elevatorConnectionType = Intrinsics.areEqual(string, ElevatorConnectionType.ROS2.name()) ? ElevatorConnectionType.ROS2 : ElevatorConnectionType.Lora;
        }
        companion.setElevatorConnectionType(elevatorConnectionType);
        list = this.this$0.elevators;
        if (!list.isEmpty()) {
            elevatorTask = this.this$0.elevatorTask;
            elevatorTask.initClient(SDKConfig.INSTANCE.getProcessContext());
        }
        return Unit.INSTANCE;
    }
}
