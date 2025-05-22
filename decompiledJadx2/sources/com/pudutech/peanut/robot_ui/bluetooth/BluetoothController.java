package com.pudutech.peanut.robot_ui.bluetooth;

import android.bluetooth.BluetoothAdapter;
import com.pudutech.peanut.robot_ui.p063ui.RowNumberActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BluetoothController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/bluetooth/BluetoothController;", "", "()V", "init", "", "activity", "Lcom/pudutech/peanut/robot_ui/ui/RowNumberActivity;", "turnOnBluetooth", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BluetoothController {
    public static final BluetoothController INSTANCE = new BluetoothController();

    private BluetoothController() {
    }

    public final void init(RowNumberActivity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        if (activity.getMAdapter() == null) {
            activity.setMAdapter(BluetoothAdapter.getDefaultAdapter());
        }
        if (activity.getMAdapter() == null) {
            return;
        }
        BluetoothAdapter mAdapter = activity.getMAdapter();
        if (mAdapter == null) {
            Intrinsics.throwNpe();
        }
        if (mAdapter.isEnabled()) {
            return;
        }
        BluetoothAdapter mAdapter2 = activity.getMAdapter();
        if (mAdapter2 == null) {
            Intrinsics.throwNpe();
        }
        if (mAdapter2.getState() == 10) {
            BluetoothAdapter mAdapter3 = activity.getMAdapter();
            if (mAdapter3 == null) {
                Intrinsics.throwNpe();
            }
            mAdapter3.enable();
        }
    }

    public final boolean turnOnBluetooth() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            return defaultAdapter.enable();
        }
        return false;
    }
}
