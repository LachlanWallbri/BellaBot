package com.pudutech.mirsdk.movetask;

import com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final /* synthetic */ class ElevatorTask$startMoveAction$1 extends MutablePropertyReference0 {
    ElevatorTask$startMoveAction$1(ElevatorTask elevatorTask) {
        super(elevatorTask);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "elvRemoteCommunicate";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ElevatorTask.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getElvRemoteCommunicate()Lcom/pudutech/mirsdk/robotsdk/ElevatorCommunicateInterface;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return ElevatorTask.access$getElvRemoteCommunicate$p((ElevatorTask) this.receiver);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ((ElevatorTask) this.receiver).elvRemoteCommunicate = (ElevatorCommunicateInterface) obj;
    }
}
