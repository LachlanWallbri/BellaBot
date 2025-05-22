package com.pudutech.mirsdk.movetask;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.bluetooth.BluetoothBleListener;
import com.pudutech.mirsdk.movetask.GeneralTask;
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
import kotlinx.coroutines.DelayKt;

/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GeneralTask$dockListener$1$callDockerEstimateTransform$1", m3970f = "GeneralTask.kt", m3971i = {0, 0, 0}, m3972l = {858}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "distance", "$this$apply"}, m3975s = {"L$0", "D$0", "L$2"})
/* loaded from: classes6.dex */
final class GeneralTask$dockListener$1$callDockerEstimateTransform$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $p1;
    final /* synthetic */ double $p2;
    double D$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6410p$;
    final /* synthetic */ GeneralTask$dockListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeneralTask$dockListener$1$callDockerEstimateTransform$1(GeneralTask$dockListener$1 generalTask$dockListener$1, double d, double d2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = generalTask$dockListener$1;
        this.$p1 = d;
        this.$p2 = d2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GeneralTask$dockListener$1$callDockerEstimateTransform$1 generalTask$dockListener$1$callDockerEstimateTransform$1 = new GeneralTask$dockListener$1$callDockerEstimateTransform$1(this.this$0, this.$p1, this.$p2, completion);
        generalTask$dockListener$1$callDockerEstimateTransform$1.f6410p$ = (CoroutineScope) obj;
        return generalTask$dockListener$1$callDockerEstimateTransform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeneralTask$dockListener$1$callDockerEstimateTransform$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        BluetoothBleListener bluetoothBleListener;
        BluetoothBleListener bluetoothBleListener2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6410p$;
            double hypot = Math.hypot(this.$p1, this.$p2);
            Pdlog.m3273d(this.this$0.this$0.TAG, "callDockerEstimateTransform x:" + this.$p1 + " y:" + this.$p2 + " distance:" + hypot);
            if (GeneralTask.access$getMoveState$p(this.this$0.this$0) instanceof GeneralTask.GoBluetoothChargingState) {
                if (hypot < 0.45d) {
                    z = this.this$0.this$0.isNeedCallStart;
                    if (z && (bluetoothBleListener = this.this$0.this$0.callback) != null) {
                        this.L$0 = coroutineScope;
                        this.D$0 = hypot;
                        this.L$1 = bluetoothBleListener;
                        this.L$2 = bluetoothBleListener;
                        this.label = 1;
                        if (DelayKt.delay(5000L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        bluetoothBleListener2 = bluetoothBleListener;
                    }
                    this.this$0.this$0.isNeedCallStart = false;
                } else {
                    this.this$0.this$0.isNeedCallStart = true;
                }
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        bluetoothBleListener2 = (BluetoothBleListener) this.L$2;
        double d = this.D$0;
        ResultKt.throwOnFailure(obj);
        this.this$0.this$0.doBluetoothStartCharge(bluetoothBleListener2.getMac());
        this.this$0.this$0.isNeedCallStart = false;
        return Unit.INSTANCE;
    }
}
