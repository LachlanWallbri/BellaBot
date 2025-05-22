package com.pudutech.peanut.robot_ui.bluetooth.bt;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* loaded from: classes5.dex */
public class BluetoothActivity extends Activity implements BtInterface {
    protected BroadcastReceiver mBtReceiver = new BroadcastReceiver() { // from class: com.pudutech.peanut.robot_ui.bluetooth.bt.BluetoothActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if ("android.bluetooth.adapter.action.DISCOVERY_STARTED".equals(action)) {
                BluetoothActivity.this.btStartDiscovery(intent);
                return;
            }
            if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                BluetoothActivity.this.btFinishDiscovery(intent);
                return;
            }
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                BluetoothActivity.this.btStatusChanged(intent);
                return;
            }
            if ("android.bluetooth.device.action.FOUND".equals(action)) {
                BluetoothActivity.this.btFoundDevice(intent);
            } else if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(action)) {
                BluetoothActivity.this.btBondStatusChange(intent);
            } else if ("android.bluetooth.device.action.PAIRING_REQUEST".equals(action)) {
                BluetoothActivity.this.btPairingRequest(intent);
            }
        }
    };

    public void btBondStatusChange(Intent intent) {
    }

    public void btFinishDiscovery(Intent intent) {
    }

    public void btFoundDevice(Intent intent) {
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btPairingRequest(Intent intent) {
    }

    public void btStartDiscovery(Intent intent) {
    }

    public void btStatusChanged(Intent intent) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        BtUtil.registerBluetoothReceiver(this.mBtReceiver, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        BtUtil.unregisterBluetoothReceiver(this.mBtReceiver, this);
    }
}
