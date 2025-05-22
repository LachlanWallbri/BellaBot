package com.pudutech.mirsdk.charge;

import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.bluetooth.BluetoothBleListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: ChargeUpdateTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0005H\u0016¨\u0006\u000e"}, m3961d2 = {"com/pudutech/mirsdk/charge/ChargeUpdateTask$callback$1", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleListener;", "onConnectStateChange", "", "state", "", "onDataRead", "data", "", "onDataSendState", "isSuccess", "", "onServicesDiscovered", "status", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargeUpdateTask$callback$1 extends BluetoothBleListener {
    final /* synthetic */ ChargeUpdateTask this$0;

    @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
    public void onDataSendState(byte[] data, boolean isSuccess) {
        Intrinsics.checkParameterIsNotNull(data, "data");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeUpdateTask$callback$1(ChargeUpdateTask chargeUpdateTask, String str) {
        super(str);
        this.this$0 = chargeUpdateTask;
    }

    @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
    public void onConnectStateChange(int state) {
        if (state == 0) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ChargeUpdateTask$callback$1$onConnectStateChange$1(this, null), 3, null);
        }
    }

    @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
    public void onServicesDiscovered(int status) {
        if (status == 0) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ChargeUpdateTask$callback$1$onServicesDiscovered$1(this, null), 3, null);
        }
    }

    @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
    public void onDataRead(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.this$0.parseMessage(data);
    }
}
