package com.pudutech.gatecontrollerlib;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BluetoothHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0006\u0010\u0011\u001a\u00020\u0000J\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/gatecontrollerlib/BluetoothHelper;", "", "context", "Landroid/content/Context;", "listener", "Lcom/pudutech/gatecontrollerlib/BluetoothHelperListener;", "(Landroid/content/Context;Lcom/pudutech/gatecontrollerlib/BluetoothHelperListener;)V", "isRequiredPermission", "", "mBluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "getMBluetoothAdapter", "()Landroid/bluetooth/BluetoothAdapter;", "mBluetoothAdapter$delegate", "Lkotlin/Lazy;", "checkBTPermissions", "", "create", "enableBluetooth", "getDevice", "Landroid/bluetooth/BluetoothDevice;", "mac", "", "isBluetoothEnabled", "setPermissionRequired", "isRequired", "GateControllerLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BluetoothHelper {
    private final Context context;
    private boolean isRequiredPermission;
    private final BluetoothHelperListener listener;

    /* renamed from: mBluetoothAdapter$delegate, reason: from kotlin metadata */
    private final Lazy mBluetoothAdapter;

    private final BluetoothAdapter getMBluetoothAdapter() {
        return (BluetoothAdapter) this.mBluetoothAdapter.getValue();
    }

    public final BluetoothHelper create() {
        return this;
    }

    public BluetoothHelper(Context context, BluetoothHelperListener bluetoothHelperListener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.listener = bluetoothHelperListener;
        this.mBluetoothAdapter = LazyKt.lazy(new Function0<BluetoothAdapter>() { // from class: com.pudutech.gatecontrollerlib.BluetoothHelper$mBluetoothAdapter$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BluetoothAdapter invoke() {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    return defaultAdapter;
                }
                BluetoothHelper bluetoothHelper = BluetoothHelper.this;
                throw new RuntimeException("Bluetooth is not supported on this hardware platform. Make sure you try it from the real device\n You could more information from here:\nhttps://developer.android.com/reference/android/bluetooth/BluetoothAdapter");
            }
        });
    }

    public final boolean isBluetoothEnabled() {
        return getMBluetoothAdapter().isEnabled();
    }

    public final void enableBluetooth() {
        if (isBluetoothEnabled()) {
            return;
        }
        getMBluetoothAdapter().enable();
    }

    public final BluetoothDevice getDevice(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        BluetoothDevice remoteDevice = getMBluetoothAdapter().getRemoteDevice(mac);
        Intrinsics.checkExpressionValueIsNotNull(remoteDevice, "mBluetoothAdapter.getRemoteDevice(mac)");
        return remoteDevice;
    }

    private final void checkBTPermissions() {
        this.context.checkSelfPermission(BluetoothHelperConstant.ACCESS_FINE_LOCATION);
        this.context.checkSelfPermission(BluetoothHelperConstant.ACCESS_COARSE_LOCATION);
    }

    public final BluetoothHelper setPermissionRequired(boolean isRequired) {
        this.isRequiredPermission = isRequired;
        return this;
    }
}
