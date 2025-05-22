package com.pudutech.peanut.robot_ui.p063ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BlueToothBaseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\u000e\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\u000f\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\u0010\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\u0011\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\u0012\u001a\u00020\nH\u0014J\b\u0010\u0013\u001a\u00020\nH\u0014J\b\u0010\u0014\u001a\u00020\nH\u0014J\b\u0010\u0015\u001a\u00020\nH\u0014R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/base/BlueToothBaseActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "Lcom/pudutech/peanut/robot_ui/bluetooth/bt/BtInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "mBtReceiver", "Landroid/content/BroadcastReceiver;", "btBondStatusChange", "", "intent", "Landroid/content/Intent;", "btFinishDiscovery", "btFoundDevice", "btPairingRequest", "btStartDiscovery", "btStatusChanged", "onDestroy", "onResume", "onStart", "onStop", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class BlueToothBaseActivity extends BatteryBaseActivity implements BtInterface {
    private HashMap _$_findViewCache;
    private final String TAG = getClass().getName();
    private BroadcastReceiver mBtReceiver = new BroadcastReceiver() { // from class: com.pudutech.peanut.robot_ui.ui.base.BlueToothBaseActivity$mBtReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.adapter.action.DISCOVERY_STARTED", action)) {
                BlueToothBaseActivity.this.btStartDiscovery(intent);
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.adapter.action.DISCOVERY_FINISHED", action)) {
                BlueToothBaseActivity.this.btFinishDiscovery(intent);
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.adapter.action.STATE_CHANGED", action)) {
                BlueToothBaseActivity.this.btStatusChanged(intent);
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.device.action.FOUND", action)) {
                BlueToothBaseActivity.this.btFoundDevice(intent);
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.device.action.ACL_CONNECTED", action)) {
                BlueToothBaseActivity.this.btBondStatusChange(intent);
                return;
            }
            if (Intrinsics.areEqual("android.bluetooth.device.action.ACL_DISCONNECTED", action)) {
                BlueToothBaseActivity.this.btBondStatusChange(intent);
            } else if (Intrinsics.areEqual("android.bluetooth.device.action.BOND_STATE_CHANGED", action)) {
                BlueToothBaseActivity.this.btBondStatusChange(intent);
            } else if (Intrinsics.areEqual("android.bluetooth.device.action.PAIRING_REQUEST", action)) {
                BlueToothBaseActivity.this.btPairingRequest(intent);
            }
        }
    };

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public void btBondStatusChange(Intent intent) {
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btFinishDiscovery(Intent intent) {
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btFoundDevice(Intent intent) {
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btPairingRequest(Intent intent) {
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.bt.BtInterface
    public void btStartDiscovery(Intent intent) {
    }

    public void btStatusChanged(Intent intent) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }
}
