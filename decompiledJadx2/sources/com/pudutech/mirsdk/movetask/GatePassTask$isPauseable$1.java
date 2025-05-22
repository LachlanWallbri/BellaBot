package com.pudutech.mirsdk.movetask;

import com.pudutech.mirsdk.movetask.GatePassTask;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: GatePassTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final /* synthetic */ class GatePassTask$isPauseable$1 extends MutablePropertyReference0 {
    GatePassTask$isPauseable$1(GatePassTask gatePassTask) {
        super(gatePassTask);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "moveState";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(GatePassTask.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getMoveState()Lcom/pudutech/mirsdk/movetask/GatePassTask$MoveState;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return GatePassTask.access$getMoveState$p((GatePassTask) this.receiver);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ((GatePassTask) this.receiver).moveState = (GatePassTask.MoveState) obj;
    }
}
