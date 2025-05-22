package com.pudutech.event_tracking;

import com.pudutech.event_tracking.bean.Event;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PuduEventTrackingManagerImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00062\u0015\u0010\u0007\u001a\u00110\b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00052!\u0010\t\u001a\u001d\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\f¢\u0006\u0002\b\r"}, m3961d2 = {"<anonymous>", "", "p1", "Lcom/pudutech/event_tracking/bean/Event;", "Lkotlin/ParameterName;", "name", "event", "p2", "", "p3", "", "", "params", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* synthetic */ class PuduEventTrackingManagerImpl$init$1$1 extends FunctionReference implements Function3<Event, String, Map<String, ? extends Object>, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public PuduEventTrackingManagerImpl$init$1$1(PuduEventTrackingManagerImpl puduEventTrackingManagerImpl) {
        super(3, puduEventTrackingManagerImpl);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "onBrowseEvent";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(PuduEventTrackingManagerImpl.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "onBrowseEvent(Lcom/pudutech/event_tracking/bean/Event;Ljava/lang/String;Ljava/util/Map;)V";
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(Event event, String str, Map<String, ? extends Object> map) {
        invoke2(event, str, map);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Event p1, String p2, Map<String, ? extends Object> p3) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        Intrinsics.checkParameterIsNotNull(p2, "p2");
        Intrinsics.checkParameterIsNotNull(p3, "p3");
        ((PuduEventTrackingManagerImpl) this.receiver).onBrowseEvent(p1, p2, p3);
    }
}
