package com.pudutech.bluetooth;

import android.bluetooth.BluetoothDevice;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "Landroid/bluetooth/BluetoothDevice;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bluetooth.PdBluetoothManager$findDevice$device$1", m3970f = "PdBluetoothManager.kt", m3971i = {0}, m3972l = {175}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class PdBluetoothManager$findDevice$device$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BluetoothDevice>, Object> {
    final /* synthetic */ String $mac;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4503p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PdBluetoothManager$findDevice$device$1(String str, Continuation continuation) {
        super(2, continuation);
        this.$mac = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PdBluetoothManager$findDevice$device$1 pdBluetoothManager$findDevice$device$1 = new PdBluetoothManager$findDevice$device$1(this.$mac, completion);
        pdBluetoothManager$findDevice$device$1.f4503p$ = (CoroutineScope) obj;
        return pdBluetoothManager$findDevice$device$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super BluetoothDevice> continuation) {
        return ((PdBluetoothManager$findDevice$device$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PdBluetoothManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/bluetooth/BluetoothDevice;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bluetooth.PdBluetoothManager$findDevice$device$1$1", m3970f = "PdBluetoothManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bluetooth.PdBluetoothManager$findDevice$device$1$1 */
    /* loaded from: classes4.dex */
    public static final class C39931 extends SuspendLambda implements Function2<BluetoothDevice, Continuation<? super Boolean>, Object> {
        int label;
        private BluetoothDevice p$0;

        C39931(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C39931 c39931 = new C39931(completion);
            c39931.p$0 = (BluetoothDevice) obj;
            return c39931;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(BluetoothDevice bluetoothDevice, Continuation<? super Boolean> continuation) {
            return ((C39931) create(bluetoothDevice, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BluetoothDevice bluetoothDevice = this.p$0;
            return Boxing.boxBoolean(bluetoothDevice != null && Intrinsics.areEqual(bluetoothDevice.getAddress(), PdBluetoothManager$findDevice$device$1.this.$mac));
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BluetoothStateReceiver bluetoothStateReceiver;
        MutableStateFlow<BluetoothDevice> scanDeviceFlow;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4503p$;
            PdBluetoothManager pdBluetoothManager = PdBluetoothManager.INSTANCE;
            bluetoothStateReceiver = PdBluetoothManager.receiver;
            if (bluetoothStateReceiver == null || (scanDeviceFlow = bluetoothStateReceiver.getScanDeviceFlow()) == null) {
                return null;
            }
            C39931 c39931 = new C39931(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = FlowKt.first(scanDeviceFlow, c39931, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return (BluetoothDevice) obj;
    }
}
