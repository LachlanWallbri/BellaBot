package com.pudutech.lidar.base;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SlamwareLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class SlamwareLidarAdapter$getLidarSN$1 extends MutablePropertyReference0 {
    SlamwareLidarAdapter$getLidarSN$1(SlamwareLidarAdapter slamwareLidarAdapter) {
        super(slamwareLidarAdapter);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "lidar";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(SlamwareLidarAdapter.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getLidar()Lcom/pudutech/lidar/base/SlamwareLidar;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return ((SlamwareLidarAdapter) this.receiver).getLidar();
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ((SlamwareLidarAdapter) this.receiver).setLidar((SlamwareLidar) obj);
    }
}
