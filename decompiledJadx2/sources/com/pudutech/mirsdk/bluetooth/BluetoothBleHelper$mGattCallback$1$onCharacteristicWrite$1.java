package com.pudutech.mirsdk.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import java.lang.ref.WeakReference;
import java.util.HashMap;
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

/* compiled from: BluetoothBleHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.bluetooth.BluetoothBleHelper$mGattCallback$1$onCharacteristicWrite$1", m3970f = "BluetoothBleHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class BluetoothBleHelper$mGattCallback$1$onCharacteristicWrite$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BluetoothGattCharacteristic $characteristic;
    final /* synthetic */ BluetoothGatt $gatt;
    final /* synthetic */ int $status;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5723p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothBleHelper$mGattCallback$1$onCharacteristicWrite$1(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, Continuation continuation) {
        super(2, continuation);
        this.$gatt = bluetoothGatt;
        this.$characteristic = bluetoothGattCharacteristic;
        this.$status = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BluetoothBleHelper$mGattCallback$1$onCharacteristicWrite$1 bluetoothBleHelper$mGattCallback$1$onCharacteristicWrite$1 = new BluetoothBleHelper$mGattCallback$1$onCharacteristicWrite$1(this.$gatt, this.$characteristic, this.$status, completion);
        bluetoothBleHelper$mGattCallback$1$onCharacteristicWrite$1.f5723p$ = (CoroutineScope) obj;
        return bluetoothBleHelper$mGattCallback$1$onCharacteristicWrite$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BluetoothBleHelper$mGattCallback$1$onCharacteristicWrite$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        HashMap hashMap;
        List<WeakReference<BluetoothBleListener>> bleListener;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5723p$;
        BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
        hashMap = BluetoothBleHelper.deviceInfoMap;
        BluetoothDevice device = this.$gatt.getDevice();
        String address = device != null ? device.getAddress() : null;
        if (address == null) {
            Intrinsics.throwNpe();
        }
        BluetoothDeviceInfo bluetoothDeviceInfo = (BluetoothDeviceInfo) hashMap.get(address);
        if (bluetoothDeviceInfo != null && (bleListener = bluetoothDeviceInfo.getBleListener()) != null) {
            Iterator<T> it = bleListener.iterator();
            while (it.hasNext()) {
                BluetoothBleListener bluetoothBleListener = (BluetoothBleListener) ((WeakReference) it.next()).get();
                if (bluetoothBleListener != null) {
                    byte[] value = this.$characteristic.getValue();
                    Intrinsics.checkExpressionValueIsNotNull(value, "characteristic.value");
                    bluetoothBleListener.onDataSendState(value, this.$status == 0);
                }
            }
        }
        return Unit.INSTANCE;
    }
}
