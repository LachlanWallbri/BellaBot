package com.pudutech.mirsdk.charge;

import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.bluetooth.BluetoothBleListener;
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

/* compiled from: ChargeFinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.charge.ChargeFinder$initCallBack$2$onConnectStateChange$1", m3970f = "ChargeFinder.kt", m3971i = {0}, m3972l = {333}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
final class ChargeFinder$initCallBack$2$onConnectStateChange$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5738p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChargeFinder$initCallBack$2$onConnectStateChange$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ChargeFinder$initCallBack$2$onConnectStateChange$1 chargeFinder$initCallBack$2$onConnectStateChange$1 = new ChargeFinder$initCallBack$2$onConnectStateChange$1(completion);
        chargeFinder$initCallBack$2$onConnectStateChange$1.f5738p$ = (CoroutineScope) obj;
        return chargeFinder$initCallBack$2$onConnectStateChange$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChargeFinder$initCallBack$2$onConnectStateChange$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        int i2;
        BluetoothBleListener bluetoothBleListener;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.f5738p$;
            this.label = 1;
            if (DelayKt.delay(500L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
        i = ChargeFinder.reConnectCount;
        if (i < 1) {
            ChargeFinder chargeFinder2 = ChargeFinder.INSTANCE;
            i2 = ChargeFinder.reConnectCount;
            ChargeFinder.reConnectCount = i2 + 1;
            ChargeFinder chargeFinder3 = ChargeFinder.INSTANCE;
            bluetoothBleListener = ChargeFinder.callback;
            if (bluetoothBleListener != null) {
                BluetoothBleHelper.INSTANCE.connectDevice(bluetoothBleListener.getMac());
            }
        }
        return Unit.INSTANCE;
    }
}
