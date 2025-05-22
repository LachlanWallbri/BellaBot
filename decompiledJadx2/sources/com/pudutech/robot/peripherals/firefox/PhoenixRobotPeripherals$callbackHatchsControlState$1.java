package com.pudutech.robot.peripherals.firefox;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
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
/* compiled from: PhoenixRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.firefox.PhoenixRobotPeripherals$callbackHatchsControlState$1", m3970f = "PhoenixRobotPeripherals.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class PhoenixRobotPeripherals$callbackHatchsControlState$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ArrayList $hatchs;
    final /* synthetic */ HatchesStatus $state;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7357p$;
    final /* synthetic */ PhoenixRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PhoenixRobotPeripherals$callbackHatchsControlState$1(PhoenixRobotPeripherals phoenixRobotPeripherals, ArrayList arrayList, HatchesStatus hatchesStatus, Continuation continuation) {
        super(2, continuation);
        this.this$0 = phoenixRobotPeripherals;
        this.$hatchs = arrayList;
        this.$state = hatchesStatus;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PhoenixRobotPeripherals$callbackHatchsControlState$1 phoenixRobotPeripherals$callbackHatchsControlState$1 = new PhoenixRobotPeripherals$callbackHatchsControlState$1(this.this$0, this.$hatchs, this.$state, completion);
        phoenixRobotPeripherals$callbackHatchsControlState$1.f7357p$ = (CoroutineScope) obj;
        return phoenixRobotPeripherals$callbackHatchsControlState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PhoenixRobotPeripherals$callbackHatchsControlState$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        WeakReference weakReference;
        int i;
        int i2;
        byte b;
        boolean z;
        WeakReference weakReference2;
        IHatchsControlListener iHatchsControlListener;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7357p$;
        weakReference = this.this$0.hatchsControlListener;
        if (weakReference != null && (iHatchsControlListener = (IHatchsControlListener) weakReference.get()) != null) {
            iHatchsControlListener.callbackState(this.$hatchs, this.$state);
        }
        switch (this.$state) {
            case Opening:
            case Closing:
                this.this$0.startTimeoutJob();
                break;
            case OpenFailed:
            case CloseFailed:
                i = this.this$0.curSendDataCount;
                if (i >= 3) {
                    this.this$0.resetField();
                    this.this$0.controlHatchsRespCount = 0;
                    this.this$0.curSendDataCount = 0;
                    break;
                } else {
                    i2 = this.this$0.curSendDataCount;
                    if (i2 != 0) {
                        PhoenixRobotPeripherals phoenixRobotPeripherals = this.this$0;
                        b = phoenixRobotPeripherals.curOperation;
                        z = this.this$0.isOpen;
                        weakReference2 = this.this$0.hatchsControlListener;
                        phoenixRobotPeripherals.m4494controlHatchjqtAJmk(b, z, weakReference2 != null ? (IHatchsControlListener) weakReference2.get() : null);
                        break;
                    }
                }
                break;
            case Closed:
            case Opened:
                this.this$0.controlHatchsRespCount = 0;
                this.this$0.curSendDataCount = 0;
                this.this$0.stopTimeoutJob();
                break;
        }
        return Unit.INSTANCE;
    }
}
