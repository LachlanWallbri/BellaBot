package com.pudutech.mirsdk.hardware;

import com.pudutech.lidar.base.BaseLidarAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LidarInterfaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final /* synthetic */ class LidarInterfaceImpl$initSecondLidar$1 extends MutablePropertyReference0 {
    LidarInterfaceImpl$initSecondLidar$1(LidarInterfaceImpl lidarInterfaceImpl) {
        super(lidarInterfaceImpl);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "secondLidar";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(LidarInterfaceImpl.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getSecondLidar()Lcom/pudutech/lidar/base/BaseLidarAdapter;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return LidarInterfaceImpl.access$getSecondLidar$p((LidarInterfaceImpl) this.receiver);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ((LidarInterfaceImpl) this.receiver).secondLidar = (BaseLidarAdapter) obj;
    }
}
