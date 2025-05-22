package com.pudutech.mirsdk.bluetooth;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BluetoothBleListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\nH&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/bluetooth/BluetoothBleListener;", "", "mac", "", "(Ljava/lang/String;)V", "getMac", "()Ljava/lang/String;", "onConnectStateChange", "", "state", "", "onDataRead", "data", "", "onDataSendState", "isSuccess", "", "onServicesDiscovered", "status", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public abstract class BluetoothBleListener {
    private final String mac;

    public abstract void onConnectStateChange(int state);

    public abstract void onDataRead(byte[] data);

    public abstract void onDataSendState(byte[] data, boolean isSuccess);

    public abstract void onServicesDiscovered(int status);

    public BluetoothBleListener(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        this.mac = mac;
    }

    public final String getMac() {
        return this.mac;
    }
}
