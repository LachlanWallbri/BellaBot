package com.pudutech.mirsdk.hardware;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class HardwareInterfaceStub$init$9 extends MutablePropertyReference0 {
    HardwareInterfaceStub$init$9(HardwareInterfaceStub hardwareInterfaceStub) {
        super(hardwareInterfaceStub);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "cameraImpl";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(HardwareInterfaceStub.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getCameraImpl()Lcom/pudutech/mirsdk/hardware/CameraImpl;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        CameraImpl cameraImpl;
        cameraImpl = HardwareInterfaceStub.cameraImpl;
        return cameraImpl;
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        HardwareInterfaceStub.access$setCameraImpl$p((HardwareInterfaceStub) this.receiver, (CameraImpl) obj);
    }
}
