package com.pudutech.mirsdk.activity;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.bluetooth.BluetoothBleListener;
import com.pudutech.mirsdk.charge.ChargeMessageParser;
import com.pudutech.mirsdk.function.C4946R;
import com.pudutech.mirsdk.utiles.BluetoothRecyclerAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChargeSetActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0005H\u0016¨\u0006\u000e"}, m3961d2 = {"com/pudutech/mirsdk/activity/ChargeSetActivity$initCallBack$3", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleListener;", "onConnectStateChange", "", "state", "", "onDataRead", "data", "", "onDataSendState", "isSuccess", "", "onServicesDiscovered", "status", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargeSetActivity$initCallBack$3 extends BluetoothBleListener {
    final /* synthetic */ String $mac;
    final /* synthetic */ ChargeSetActivity this$0;

    @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
    public void onDataSendState(byte[] data, boolean isSuccess) {
        Intrinsics.checkParameterIsNotNull(data, "data");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeSetActivity$initCallBack$3(ChargeSetActivity chargeSetActivity, String str, String str2) {
        super(str2);
        this.this$0 = chargeSetActivity;
        this.$mac = str;
    }

    @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
    public void onConnectStateChange(int state) {
        String str;
        BluetoothBleListener bluetoothBleListener;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        bluetoothBleListener = this.this$0.callback;
        sb.append(bluetoothBleListener != null ? bluetoothBleListener.getMac() : null);
        sb.append(" onConnectStateChange -->");
        sb.append(state);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (state == 0) {
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initCallBack$3$onConnectStateChange$1
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothBleListener bluetoothBleListener2;
                    if (ChargeSetActivity$initCallBack$3.this.this$0.getIsRecMessage()) {
                        return;
                    }
                    TextView tv_docker = (TextView) ChargeSetActivity$initCallBack$3.this.this$0._$_findCachedViewById(C4946R.id.tv_docker);
                    Intrinsics.checkExpressionValueIsNotNull(tv_docker, "tv_docker");
                    tv_docker.setText("bluetooth connect fail");
                    bluetoothBleListener2 = ChargeSetActivity$initCallBack$3.this.this$0.callback;
                    if (bluetoothBleListener2 != null) {
                        BluetoothBleHelper.INSTANCE.removeBluetoothBleListeners(bluetoothBleListener2);
                    }
                    ChargeSetActivity$initCallBack$3.this.this$0.callback = (BluetoothBleListener) null;
                }
            });
        }
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initCallBack$3$onConnectStateChange$2
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothRecyclerAdapter bluetoothRecyclerAdapter;
                bluetoothRecyclerAdapter = ChargeSetActivity$initCallBack$3.this.this$0.deviceAdapter;
                if (bluetoothRecyclerAdapter != null) {
                    bluetoothRecyclerAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
    public void onServicesDiscovered(int status) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onServicesDiscovered -->" + status);
        if (status == 0) {
            this.this$0.startVersionReq(getMac());
        } else if (status == 257) {
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$initCallBack$3$onServicesDiscovered$1
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothBleListener bluetoothBleListener;
                    TextView tv_docker = (TextView) ChargeSetActivity$initCallBack$3.this.this$0._$_findCachedViewById(C4946R.id.tv_docker);
                    Intrinsics.checkExpressionValueIsNotNull(tv_docker, "tv_docker");
                    tv_docker.setText("bluetooth connect fail");
                    bluetoothBleListener = ChargeSetActivity$initCallBack$3.this.this$0.callback;
                    if (bluetoothBleListener != null) {
                        BluetoothBleHelper.INSTANCE.removeBluetoothBleListeners(bluetoothBleListener);
                    }
                    ChargeSetActivity$initCallBack$3.this.this$0.callback = (BluetoothBleListener) null;
                }
            });
        }
    }

    @Override // com.pudutech.mirsdk.bluetooth.BluetoothBleListener
    public void onDataRead(byte[] data) {
        ChargeMessageParser chargeMessageParser;
        Intrinsics.checkParameterIsNotNull(data, "data");
        chargeMessageParser = this.this$0.parser;
        if (chargeMessageParser != null) {
            chargeMessageParser.parseMessage(data);
        }
    }
}
