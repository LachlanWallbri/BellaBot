package com.pudutech.mirsdk.mircore.localization;

import com.pudutech.mirsdk.mircore.PuduLocateInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Localization.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final /* synthetic */ class Localization$initModules$1 extends MutablePropertyReference0 {
    Localization$initModules$1(Localization localization) {
        super(localization);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "locateCase";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(Localization.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getLocateCase()Lcom/pudutech/mirsdk/mircore/PuduLocateInterface$LocateCase;";
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return Localization.access$getLocateCase$p((Localization) this.receiver);
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        ((Localization) this.receiver).locateCase = (PuduLocateInterface.LocateCase) obj;
    }
}
