package com.pudutech.event_tracking;

import com.pudutech.event_tracking.custom.CustomArgs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PuduEventTrackingManagerImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, m3961d2 = {"<anonymous>", "", "p1", "Lcom/pudutech/event_tracking/custom/CustomArgs;", "Lkotlin/ParameterName;", "name", "event", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* synthetic */ class PuduEventTrackingManagerImpl$init$3$1 extends FunctionReference implements Function1<CustomArgs, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public PuduEventTrackingManagerImpl$init$3$1(PuduEventTrackingManagerImpl puduEventTrackingManagerImpl) {
        super(1, puduEventTrackingManagerImpl);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "customEvent";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(PuduEventTrackingManagerImpl.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "customEvent(Lcom/pudutech/event_tracking/custom/CustomArgs;)V";
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(CustomArgs customArgs) {
        invoke2(customArgs);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(CustomArgs p1) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        ((PuduEventTrackingManagerImpl) this.receiver).customEvent(p1);
    }
}
