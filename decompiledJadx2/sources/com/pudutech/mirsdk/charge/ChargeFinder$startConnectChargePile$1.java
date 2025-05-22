package com.pudutech.mirsdk.charge;

import com.pudutech.base.Pdlog;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ChargeFinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.charge.ChargeFinder$startConnectChargePile$1", m3970f = "ChargeFinder.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class ChargeFinder$startConnectChargePile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mac;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5740p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeFinder$startConnectChargePile$1(String str, Continuation continuation) {
        super(2, continuation);
        this.$mac = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ChargeFinder$startConnectChargePile$1 chargeFinder$startConnectChargePile$1 = new ChargeFinder$startConnectChargePile$1(this.$mac, completion);
        chargeFinder$startConnectChargePile$1.f5740p$ = (CoroutineScope) obj;
        return chargeFinder$startConnectChargePile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChargeFinder$startConnectChargePile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0076, code lost:
    
        if (r9 != null) goto L16;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        BluetoothBleListener bluetoothBleListener;
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5740p$;
        ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
        ChargeFinder.isRecStatusReply = false;
        BluetoothBleHelper.scanBleDevice$default(BluetoothBleHelper.INSTANCE, false, 0L, null, 6, null);
        ChargeFinder.INSTANCE.initCallBack(this.$mac);
        ChargeFinder chargeFinder2 = ChargeFinder.INSTANCE;
        bluetoothBleListener = ChargeFinder.callback;
        if (bluetoothBleListener != null) {
            if (!Intrinsics.areEqual(bluetoothBleListener.getMac(), this.$mac)) {
                BluetoothBleHelper.INSTANCE.disconnectDevice(bluetoothBleListener.getMac());
                BluetoothBleHelper.INSTANCE.connectDevice(this.$mac);
            } else if (BluetoothBleHelper.INSTANCE.getConnectState(this.$mac) == 2) {
                ChargeFinder chargeFinder3 = ChargeFinder.INSTANCE;
                str = ChargeFinder.TAG;
                Pdlog.m3273d(str, "device already connected");
                ChargeFinder.INSTANCE.doBluetoothChargeState(bluetoothBleListener.getMac());
            } else {
                BluetoothBleHelper.INSTANCE.connectDevice(this.$mac);
            }
        }
        BluetoothBleHelper.INSTANCE.connectDevice(this.$mac);
        Unit unit = Unit.INSTANCE;
        return Unit.INSTANCE;
    }
}
