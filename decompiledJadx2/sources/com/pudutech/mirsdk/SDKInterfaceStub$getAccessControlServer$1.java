package com.pudutech.mirsdk;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class SDKInterfaceStub$getAccessControlServer$1 extends MutablePropertyReference0 {
    SDKInterfaceStub$getAccessControlServer$1(SDKInterfaceStub sDKInterfaceStub) {
        super(sDKInterfaceStub);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "moveActionInterface";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(SDKInterfaceStub.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getMoveActionInterface()Lcom/pudutech/mirsdk/MoveAction;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return SDKInterfaceStub.access$getMoveActionInterface$p((SDKInterfaceStub) this.receiver);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        SDKInterfaceStub.moveActionInterface = (MoveAction) obj;
    }
}
