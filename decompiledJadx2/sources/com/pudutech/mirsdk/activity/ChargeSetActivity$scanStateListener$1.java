package com.pudutech.mirsdk.activity;

import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.bluetooth.BluetoothBleScanStateListener;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ChargeSetActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017¨\u0006\u0006"}, m3961d2 = {"com/pudutech/mirsdk/activity/ChargeSetActivity$scanStateListener$1", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleScanStateListener;", "onScanState", "", "isScanning", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargeSetActivity$scanStateListener$1 implements BluetoothBleScanStateListener {
    final /* synthetic */ ChargeSetActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChargeSetActivity$scanStateListener$1(ChargeSetActivity chargeSetActivity) {
        this.this$0 = chargeSetActivity;
    }

    @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleScanStateListener
    public void onScanState(boolean isScanning) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new ChargeSetActivity$scanStateListener$1$onScanState$1(this, isScanning, null), 2, null);
    }
}
