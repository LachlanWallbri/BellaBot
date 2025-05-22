package com.pudutech.mirsdk.hardware;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final /* synthetic */ class HardwareInterfaceStub$switchCarpetMode$1 extends MutablePropertyReference0 {
    HardwareInterfaceStub$switchCarpetMode$1(HardwareInterfaceStub hardwareInterfaceStub) {
        super(hardwareInterfaceStub);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "carpetModeConfig";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(HardwareInterfaceStub.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getCarpetModeConfig()Lcom/pudutech/mirsdk/hardware/CarpetModeConfig;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return HardwareInterfaceStub.access$getCarpetModeConfig$p((HardwareInterfaceStub) this.receiver);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        HardwareInterfaceStub.carpetModeConfig = (CarpetModeConfig) obj;
    }
}
