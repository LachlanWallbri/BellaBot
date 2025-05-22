package com.pudutech.mirsdk.activity;

import android.widget.TextView;
import com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener;
import com.pudutech.mirsdk.aidl.serialize.BluetoothChargeUpdateErrorType;
import com.pudutech.mirsdk.function.C4946R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\"\u0010\t\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u001c\u0010\r\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0016J&\u0010\u000e\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u0010\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0011"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$onResume$4", "Lcom/pudutech/mirsdk/aidl/BluetoothChargeUpdateListener$Stub;", "onCheckingVersionBefore", "", "p0", "", "onConnectingDevice", "onRequestToAPPMode", "onRequestToIAPMode", "onSendData", "p1", "", "p2", "onStartUpdate", "onUpdateError", "Lcom/pudutech/mirsdk/aidl/serialize/BluetoothChargeUpdateErrorType;", "onUpdateSuccess", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$onResume$4 extends BluetoothChargeUpdateListener.Stub {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$onResume$4(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onStartUpdate(final String p0, final String p1) {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onResume$4$onStartUpdate$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView charge_update_state = (TextView) MirSDKActivity$onResume$4.this.this$0._$_findCachedViewById(C4946R.id.charge_update_state);
                Intrinsics.checkExpressionValueIsNotNull(charge_update_state, "charge_update_state");
                charge_update_state.setText("bluetooth: onStartUpdate " + p0 + ' ' + p1);
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onConnectingDevice(final String p0) {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onResume$4$onConnectingDevice$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView charge_update_state = (TextView) MirSDKActivity$onResume$4.this.this$0._$_findCachedViewById(C4946R.id.charge_update_state);
                Intrinsics.checkExpressionValueIsNotNull(charge_update_state, "charge_update_state");
                charge_update_state.setText("bluetooth: onConnectingDevice " + p0 + ' ');
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onCheckingVersionBefore(final String p0) {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onResume$4$onCheckingVersionBefore$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView charge_update_state = (TextView) MirSDKActivity$onResume$4.this.this$0._$_findCachedViewById(C4946R.id.charge_update_state);
                Intrinsics.checkExpressionValueIsNotNull(charge_update_state, "charge_update_state");
                charge_update_state.setText("bluetooth: onCheckingVersionBefore " + p0 + ' ');
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onRequestToIAPMode(final String p0) {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onResume$4$onRequestToIAPMode$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView charge_update_state = (TextView) MirSDKActivity$onResume$4.this.this$0._$_findCachedViewById(C4946R.id.charge_update_state);
                Intrinsics.checkExpressionValueIsNotNull(charge_update_state, "charge_update_state");
                charge_update_state.setText("bluetooth: onRequestToIAPMode " + p0 + ' ');
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onSendData(final String p0, final int p1, final int p2) {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onResume$4$onSendData$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView charge_update_state = (TextView) MirSDKActivity$onResume$4.this.this$0._$_findCachedViewById(C4946R.id.charge_update_state);
                Intrinsics.checkExpressionValueIsNotNull(charge_update_state, "charge_update_state");
                charge_update_state.setText("bluetooth: onSendData " + p0 + " total:" + p1 + " progress:" + p2);
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onRequestToAPPMode(final String p0) {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onResume$4$onRequestToAPPMode$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView charge_update_state = (TextView) MirSDKActivity$onResume$4.this.this$0._$_findCachedViewById(C4946R.id.charge_update_state);
                Intrinsics.checkExpressionValueIsNotNull(charge_update_state, "charge_update_state");
                charge_update_state.setText("bluetooth: onRequestToAPPMode " + p0 + ' ');
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onUpdateSuccess(final String p0, String p1) {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onResume$4$onUpdateSuccess$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView charge_update_state = (TextView) MirSDKActivity$onResume$4.this.this$0._$_findCachedViewById(C4946R.id.charge_update_state);
                Intrinsics.checkExpressionValueIsNotNull(charge_update_state, "charge_update_state");
                charge_update_state.setText("bluetooth: onUpdateSuccess " + p0 + ' ');
            }
        });
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onUpdateError(final String p0, final BluetoothChargeUpdateErrorType p1, final String p2) {
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$onResume$4$onUpdateError$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView charge_update_state = (TextView) MirSDKActivity$onResume$4.this.this$0._$_findCachedViewById(C4946R.id.charge_update_state);
                Intrinsics.checkExpressionValueIsNotNull(charge_update_state, "charge_update_state");
                charge_update_state.setText("bluetooth: onUpdateError " + p0 + " type:" + p1 + " msg:" + p2);
            }
        });
    }
}
