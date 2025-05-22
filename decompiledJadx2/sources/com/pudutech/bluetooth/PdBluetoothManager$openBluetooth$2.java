package com.pudutech.bluetooth;

import com.pudutech.bluetooth.BluetoothState;
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
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PdBluetoothManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/bluetooth/BluetoothState;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bluetooth.PdBluetoothManager$openBluetooth$2", m3970f = "PdBluetoothManager.kt", m3971i = {0}, m3972l = {134}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class PdBluetoothManager$openBluetooth$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BluetoothState>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4504p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PdBluetoothManager$openBluetooth$2(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PdBluetoothManager$openBluetooth$2 pdBluetoothManager$openBluetooth$2 = new PdBluetoothManager$openBluetooth$2(completion);
        pdBluetoothManager$openBluetooth$2.f4504p$ = (CoroutineScope) obj;
        return pdBluetoothManager$openBluetooth$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super BluetoothState> continuation) {
        return ((PdBluetoothManager$openBluetooth$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PdBluetoothManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/bluetooth/BluetoothState;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bluetooth.PdBluetoothManager$openBluetooth$2$1", m3970f = "PdBluetoothManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bluetooth.PdBluetoothManager$openBluetooth$2$1 */
    /* loaded from: classes4.dex */
    public static final class C39941 extends SuspendLambda implements Function2<BluetoothState, Continuation<? super Boolean>, Object> {
        int label;
        private BluetoothState p$0;

        C39941(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C39941 c39941 = new C39941(completion);
            c39941.p$0 = (BluetoothState) obj;
            return c39941;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(BluetoothState bluetoothState, Continuation<? super Boolean> continuation) {
            return ((C39941) create(bluetoothState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(Intrinsics.areEqual(this.p$0, BluetoothState.TurnOn.INSTANCE));
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BluetoothStateReceiver bluetoothStateReceiver;
        MutableStateFlow<BluetoothState> bluetoothStateFlow;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4504p$;
            PdBluetoothManager pdBluetoothManager = PdBluetoothManager.INSTANCE;
            bluetoothStateReceiver = PdBluetoothManager.receiver;
            if (bluetoothStateReceiver == null || (bluetoothStateFlow = bluetoothStateReceiver.getBluetoothStateFlow()) == null) {
                return null;
            }
            C39941 c39941 = new C39941(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = FlowKt.first(bluetoothStateFlow, c39941, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return (BluetoothState) obj;
    }
}
