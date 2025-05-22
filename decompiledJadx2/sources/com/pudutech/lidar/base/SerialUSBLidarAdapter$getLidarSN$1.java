package com.pudutech.lidar.base;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SerialUSBLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class SerialUSBLidarAdapter$getLidarSN$1 extends MutablePropertyReference0 {
    SerialUSBLidarAdapter$getLidarSN$1(SerialUSBLidarAdapter serialUSBLidarAdapter) {
        super(serialUSBLidarAdapter);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "lidar";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(SerialUSBLidarAdapter.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getLidar()Lcom/pudutech/lidar/base/SerialLidar;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return ((SerialUSBLidarAdapter) this.receiver).getLidar();
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ((SerialUSBLidarAdapter) this.receiver).setLidar((SerialLidar) obj);
    }
}
