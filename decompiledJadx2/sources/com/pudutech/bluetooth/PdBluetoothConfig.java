package com.pudutech.bluetooth;

import kotlin.Metadata;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bluetooth/PdBluetoothConfig;", "", "()V", "autoConnect", "", "getAutoConnect", "()Z", "setAutoConnect", "(Z)V", "openBluetooth", "getOpenBluetooth", "setOpenBluetooth", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PdBluetoothConfig {
    private boolean autoConnect;
    private boolean openBluetooth = true;

    public final boolean getOpenBluetooth() {
        return this.openBluetooth;
    }

    public final void setOpenBluetooth(boolean z) {
        this.openBluetooth = z;
    }

    public final boolean getAutoConnect() {
        return this.autoConnect;
    }

    public final void setAutoConnect(boolean z) {
        this.autoConnect = z;
    }
}
