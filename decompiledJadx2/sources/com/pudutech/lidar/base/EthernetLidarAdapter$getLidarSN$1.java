package com.pudutech.lidar.base;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: EthernetLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class EthernetLidarAdapter$getLidarSN$1 extends MutablePropertyReference0 {
    EthernetLidarAdapter$getLidarSN$1(EthernetLidarAdapter ethernetLidarAdapter) {
        super(ethernetLidarAdapter);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "lidar";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(EthernetLidarAdapter.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getLidar()Lcom/pudutech/lidar/base/EthernetLidar;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return ((EthernetLidarAdapter) this.receiver).getLidar();
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ((EthernetLidarAdapter) this.receiver).setLidar((EthernetLidar) obj);
    }
}
