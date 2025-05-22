package com.pudutech.mirsdk.movetask;

import com.pudutech.mirsdk.movetask.GeneralTask;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final /* synthetic */ class GeneralTask$isGoChargingTask$1 extends MutablePropertyReference0 {
    GeneralTask$isGoChargingTask$1(GeneralTask generalTask) {
        super(generalTask);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "moveState";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(GeneralTask.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getMoveState()Lcom/pudutech/mirsdk/movetask/GeneralTask$MoveState;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return GeneralTask.access$getMoveState$p((GeneralTask) this.receiver);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ((GeneralTask) this.receiver).moveState = (GeneralTask.MoveState) obj;
    }
}
