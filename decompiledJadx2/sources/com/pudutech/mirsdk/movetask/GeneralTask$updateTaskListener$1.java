package com.pudutech.mirsdk.movetask;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener;
import com.pudutech.mirsdk.aidl.serialize.BluetoothChargeUpdateErrorType;
import com.pudutech.mirsdk.movetask.GeneralTask;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\"\u0010\t\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u001c\u0010\r\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0016J&\u0010\u000e\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0012"}, m3961d2 = {"com/pudutech/mirsdk/movetask/GeneralTask$updateTaskListener$1", "Lcom/pudutech/mirsdk/aidl/BluetoothChargeUpdateListener$Stub;", "onCheckingVersionBefore", "", "p0", "", "onConnectingDevice", "onRequestToAPPMode", "onRequestToIAPMode", "onSendData", "p1", "", "p2", "onStartUpdate", "onUpdateError", "Lcom/pudutech/mirsdk/aidl/serialize/BluetoothChargeUpdateErrorType;", "onUpdateSuccess", "mac", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class GeneralTask$updateTaskListener$1 extends BluetoothChargeUpdateListener.Stub {
    final /* synthetic */ GeneralTask this$0;

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onCheckingVersionBefore(String p0) {
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onConnectingDevice(String p0) {
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onRequestToAPPMode(String p0) {
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onRequestToIAPMode(String p0) {
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onSendData(String p0, int p1, int p2) {
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onStartUpdate(String p0, String p1) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeneralTask$updateTaskListener$1(GeneralTask generalTask) {
        this.this$0 = generalTask;
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onUpdateSuccess(String mac, String p1) {
        String str;
        GeneralTask.MoveState moveState;
        str = this.this$0.targetMac;
        if (Intrinsics.areEqual(str, mac)) {
            moveState = this.this$0.moveState;
            if (moveState != null && (GeneralTask.access$getMoveState$p(this.this$0) instanceof GeneralTask.GeneralState) && this.this$0.getBusinessType() == BusinessType.GoCharging) {
                this.this$0.isRequestBluetooth = false;
                GeneralTask.MoveState access$getMoveState$p = GeneralTask.access$getMoveState$p(this.this$0);
                if (access$getMoveState$p == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.movetask.GeneralTask.GeneralState");
                }
                if (((GeneralTask.GeneralState) access$getMoveState$p).getIsArrived()) {
                    if (this.this$0.callback != null) {
                        this.this$0.startConnectChargePile(mac);
                    }
                } else {
                    Pdlog.m3277w(this.this$0.TAG, "update success but not arrived ");
                }
            }
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$updateTaskListener$1$onUpdateSuccess$3(this, null), 3, null);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.BluetoothChargeUpdateListener
    public void onUpdateError(String p0, BluetoothChargeUpdateErrorType p1, String p2) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$updateTaskListener$1$onUpdateError$1(this, null), 3, null);
    }
}
