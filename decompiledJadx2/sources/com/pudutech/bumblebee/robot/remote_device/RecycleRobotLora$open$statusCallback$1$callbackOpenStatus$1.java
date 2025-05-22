package com.pudutech.bumblebee.robot.remote_device;

import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import com.pudutech.bumblebee.robot.remote_device.RecycleRobotLora;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RecycleRobotLora.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.remote_device.RecycleRobotLora$open$statusCallback$1$callbackOpenStatus$1", m3970f = "RecycleRobotLora.kt", m3971i = {0}, m3972l = {54}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
final class RecycleRobotLora$open$statusCallback$1$callbackOpenStatus$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $opened;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4796p$;
    final /* synthetic */ RecycleRobotLora$open$statusCallback$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecycleRobotLora$open$statusCallback$1$callbackOpenStatus$1(RecycleRobotLora$open$statusCallback$1 recycleRobotLora$open$statusCallback$1, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = recycleRobotLora$open$statusCallback$1;
        this.$opened = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RecycleRobotLora$open$statusCallback$1$callbackOpenStatus$1 recycleRobotLora$open$statusCallback$1$callbackOpenStatus$1 = new RecycleRobotLora$open$statusCallback$1$callbackOpenStatus$1(this.this$0, this.$opened, completion);
        recycleRobotLora$open$statusCallback$1$callbackOpenStatus$1.f4796p$ = (CoroutineScope) obj;
        return recycleRobotLora$open$statusCallback$1$callbackOpenStatus$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RecycleRobotLora$open$statusCallback$1$callbackOpenStatus$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        PeripheralDeviceStatus peripheralDeviceStatus;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4796p$;
            if (this.$opened) {
                this.this$0.this$0.exportAndSetupDirectionOut(131);
                this.this$0.this$0.exportAndSetupDirectionOut(134);
                this.this$0.this$0.exportAndSetupDirectionOut(132);
                this.this$0.this$0.switchLevelMode(RecycleRobotLora.LevelMode.Normal);
                RecycleRobotLora recycleRobotLora = this.this$0.this$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (recycleRobotLora.resetDevice(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Function1 function1 = this.this$0.$statueListener;
        peripheralDeviceStatus = this.this$0.this$0.status;
        function1.invoke(peripheralDeviceStatus);
        return Unit.INSTANCE;
    }
}
