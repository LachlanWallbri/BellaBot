package com.pudutech.peanut.robot_ui.bluetooth.bt;

import android.content.Intent;

/* loaded from: classes5.dex */
public interface BtInterface {
    void btBondStatusChange(Intent intent);

    void btFinishDiscovery(Intent intent);

    void btFoundDevice(Intent intent);

    void btPairingRequest(Intent intent);

    void btStartDiscovery(Intent intent);

    void btStatusChanged(Intent intent);
}
