package com.pudutech.mirsdk.bluetooth;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: BluetoothBleHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class BluetoothBleHelper$initHelper$1 extends MutablePropertyReference0 {
    BluetoothBleHelper$initHelper$1(BluetoothBleHelper bluetoothBleHelper) {
        super(bluetoothBleHelper);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "mContext";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(BluetoothBleHelper.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getMContext()Landroid/content/Context;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return BluetoothBleHelper.access$getMContext$p((BluetoothBleHelper) this.receiver);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        BluetoothBleHelper.mContext = (Context) obj;
    }
}
