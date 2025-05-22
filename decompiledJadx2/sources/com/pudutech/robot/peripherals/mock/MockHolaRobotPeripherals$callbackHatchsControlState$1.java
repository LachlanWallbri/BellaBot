package com.pudutech.robot.peripherals.mock;

import com.pudutech.robot.peripherals.firefox.HatchesStatus;
import com.pudutech.robot.peripherals.firefox.IHatchsControlListener;
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
/* compiled from: MockHolaRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.mock.MockHolaRobotPeripherals$callbackHatchsControlState$1", m3970f = "MockHolaRobotPeripherals.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class MockHolaRobotPeripherals$callbackHatchsControlState$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ArrayList $hatchs;
    final /* synthetic */ HatchesStatus $state;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7370p$;
    final /* synthetic */ MockHolaRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MockHolaRobotPeripherals$callbackHatchsControlState$1(MockHolaRobotPeripherals mockHolaRobotPeripherals, ArrayList arrayList, HatchesStatus hatchesStatus, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mockHolaRobotPeripherals;
        this.$hatchs = arrayList;
        this.$state = hatchesStatus;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MockHolaRobotPeripherals$callbackHatchsControlState$1 mockHolaRobotPeripherals$callbackHatchsControlState$1 = new MockHolaRobotPeripherals$callbackHatchsControlState$1(this.this$0, this.$hatchs, this.$state, completion);
        mockHolaRobotPeripherals$callbackHatchsControlState$1.f7370p$ = (CoroutineScope) obj;
        return mockHolaRobotPeripherals$callbackHatchsControlState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MockHolaRobotPeripherals$callbackHatchsControlState$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IHatchsControlListener iHatchsControlListener;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7370p$;
        iHatchsControlListener = this.this$0.hatchsControlListener;
        if (iHatchsControlListener != null) {
            iHatchsControlListener.callbackState(this.$hatchs, this.$state);
        }
        switch (this.$state) {
            case Opening:
            case Closing:
                this.this$0.startTimeoutJob();
                break;
            case Opened:
            case OpenFailed:
            case Closed:
            case CloseFailed:
                this.this$0.resetField();
                break;
        }
        return Unit.INSTANCE;
    }
}
