package com.pudutech.mirsdk.activity;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ChargeSetActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/mirsdk/activity/ChargeSetActivity$scanCallback$1", "Landroid/bluetooth/le/ScanCallback;", "onScanFailed", "", AUserTrack.UTKEY_ERROR_CODE, "", "onScanResult", "callbackType", SpeechUtility.TAG_RESOURCE_RESULT, "Landroid/bluetooth/le/ScanResult;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargeSetActivity$scanCallback$1 extends ScanCallback {
    final /* synthetic */ ChargeSetActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChargeSetActivity$scanCallback$1(ChargeSetActivity chargeSetActivity) {
        this.this$0 = chargeSetActivity;
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int callbackType, ScanResult result) {
        String str;
        BluetoothDevice device;
        BluetoothDevice device2;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("mScanCallback == onScanResult type：");
        sb.append(callbackType);
        sb.append(" result name:");
        sb.append((result == null || (device2 = result.getDevice()) == null) ? null : device2.getName());
        sb.append(" mac:");
        sb.append((result == null || (device = result.getDevice()) == null) ? null : device.getAddress());
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        super.onScanResult(callbackType, result);
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new ChargeSetActivity$scanCallback$1$onScanResult$1(this, null), 2, null);
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanFailed(int errorCode) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "mScanCallback == onScanFailed errorCode:" + errorCode);
        super.onScanFailed(errorCode);
    }
}
