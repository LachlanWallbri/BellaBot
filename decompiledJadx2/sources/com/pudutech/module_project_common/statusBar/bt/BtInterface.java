package com.pudutech.module_project_common.statusBar.bt;

import android.content.Intent;

/* loaded from: classes3.dex */
public interface BtInterface {
    void btBondStatusChange(Intent intent);

    void btFinishDiscovery(Intent intent);

    void btFoundDevice(Intent intent);

    void btPairingRequest(Intent intent);

    void btStartDiscovery(Intent intent);

    void btStatusChanged(Intent intent);
}
