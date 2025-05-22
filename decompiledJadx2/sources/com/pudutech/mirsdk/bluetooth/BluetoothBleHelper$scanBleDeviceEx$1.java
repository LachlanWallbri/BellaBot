package com.pudutech.mirsdk.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.SolicitService;
import java.lang.ref.WeakReference;
import java.util.Iterator;
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
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BluetoothBleHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.bluetooth.BluetoothBleHelper$scanBleDeviceEx$1", m3970f = "BluetoothBleHelper.kt", m3971i = {0}, m3972l = {269}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class BluetoothBleHelper$scanBleDeviceEx$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $outTime;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5727p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothBleHelper$scanBleDeviceEx$1(long j, Continuation continuation) {
        super(2, continuation);
        this.$outTime = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BluetoothBleHelper$scanBleDeviceEx$1 bluetoothBleHelper$scanBleDeviceEx$1 = new BluetoothBleHelper$scanBleDeviceEx$1(this.$outTime, completion);
        bluetoothBleHelper$scanBleDeviceEx$1.f5727p$ = (CoroutineScope) obj;
        return bluetoothBleHelper$scanBleDeviceEx$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BluetoothBleHelper$scanBleDeviceEx$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BluetoothAdapter bluetoothAdapter;
        List list;
        BluetoothBleHelper$mScanCallback$1 bluetoothBleHelper$mScanCallback$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5727p$;
            long max = Math.max(this.$outTime, SolicitService.CAMERA_OPEN_TIME_OUT);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(max, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Pdlog.m3273d("BluetoothBleHelper", "scan ble timeout");
        BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
        BluetoothBleHelper.isScanning = false;
        BluetoothBleHelper bluetoothBleHelper2 = BluetoothBleHelper.INSTANCE;
        bluetoothAdapter = BluetoothBleHelper.mBluetoothAdapter;
        BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner != null) {
            BluetoothBleHelper bluetoothBleHelper3 = BluetoothBleHelper.INSTANCE;
            bluetoothBleHelper$mScanCallback$1 = BluetoothBleHelper.mScanCallback;
            bluetoothLeScanner.stopScan(bluetoothBleHelper$mScanCallback$1);
        }
        BluetoothBleHelper bluetoothBleHelper4 = BluetoothBleHelper.INSTANCE;
        list = BluetoothBleHelper.blueScanBleListener;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            BluetoothBleScanStateListener bluetoothBleScanStateListener = (BluetoothBleScanStateListener) ((WeakReference) it.next()).get();
            if (bluetoothBleScanStateListener != null) {
                bluetoothBleScanStateListener.onScanState(false);
            }
        }
        return Unit.INSTANCE;
    }
}
