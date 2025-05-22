package com.pudutech.bluetooth;

import android.bluetooth.BluetoothDevice;
import com.pudutech.bluetooth.DeviceConnectState;
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
import org.apache.http.HttpStatus;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PdBluetoothManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/bluetooth/DeviceWithConnectState;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bluetooth.PdBluetoothManager$realConnect$2", m3970f = "PdBluetoothManager.kt", m3971i = {0}, m3972l = {HttpStatus.SC_MULTI_STATUS}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class PdBluetoothManager$realConnect$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DeviceWithConnectState>, Object> {
    final /* synthetic */ boolean $connect;
    final /* synthetic */ BluetoothDevice $device;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4505p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PdBluetoothManager$realConnect$2(BluetoothDevice bluetoothDevice, boolean z, Continuation continuation) {
        super(2, continuation);
        this.$device = bluetoothDevice;
        this.$connect = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PdBluetoothManager$realConnect$2 pdBluetoothManager$realConnect$2 = new PdBluetoothManager$realConnect$2(this.$device, this.$connect, completion);
        pdBluetoothManager$realConnect$2.f4505p$ = (CoroutineScope) obj;
        return pdBluetoothManager$realConnect$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super DeviceWithConnectState> continuation) {
        return ((PdBluetoothManager$realConnect$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PdBluetoothManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/bluetooth/DeviceWithConnectState;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bluetooth.PdBluetoothManager$realConnect$2$1", m3970f = "PdBluetoothManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bluetooth.PdBluetoothManager$realConnect$2$1 */
    /* loaded from: classes4.dex */
    public static final class C39951 extends SuspendLambda implements Function2<DeviceWithConnectState, Continuation<? super Boolean>, Object> {
        int label;
        private DeviceWithConnectState p$0;

        C39951(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C39951 c39951 = new C39951(completion);
            c39951.p$0 = (DeviceWithConnectState) obj;
            return c39951;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DeviceWithConnectState deviceWithConnectState, Continuation<? super Boolean> continuation) {
            return ((C39951) create(deviceWithConnectState, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            boolean z;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DeviceWithConnectState deviceWithConnectState = this.p$0;
            if (deviceWithConnectState != null && Intrinsics.areEqual(deviceWithConnectState.getDevice().getAddress(), PdBluetoothManager$realConnect$2.this.$device.getAddress())) {
                if (Intrinsics.areEqual(deviceWithConnectState.getState(), PdBluetoothManager$realConnect$2.this.$connect ? DeviceConnectState.Connected.INSTANCE : DeviceConnectState.Disconnected.INSTANCE)) {
                    z = true;
                    return Boxing.boxBoolean(z);
                }
            }
            z = false;
            return Boxing.boxBoolean(z);
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BluetoothStateReceiver bluetoothStateReceiver;
        MutableStateFlow<DeviceWithConnectState> deviceConnectStateChangeFlow;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4505p$;
            PdBluetoothManager pdBluetoothManager = PdBluetoothManager.INSTANCE;
            bluetoothStateReceiver = PdBluetoothManager.receiver;
            if (bluetoothStateReceiver == null || (deviceConnectStateChangeFlow = bluetoothStateReceiver.getDeviceConnectStateChangeFlow()) == null) {
                return null;
            }
            C39951 c39951 = new C39951(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = FlowKt.first(deviceConnectStateChangeFlow, c39951, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return (DeviceWithConnectState) obj;
    }
}
