package com.pudutech.bluetooth;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: PdBluetoothManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final /* synthetic */ class PdBluetoothManager$check$1 extends MutablePropertyReference0 {
    PdBluetoothManager$check$1(PdBluetoothManager pdBluetoothManager) {
        super(pdBluetoothManager);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "context";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(PdBluetoothManager.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getContext()Landroid/content/Context;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return PdBluetoothManager.access$getContext$p((PdBluetoothManager) this.receiver);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        PdBluetoothManager.context = (Context) obj;
    }
}
