package com.pudutech.event_tracking.component;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LocationComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082@"}, m3961d2 = {"fetchIpApiInfo", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.event_tracking.component.LocationComponent", m3970f = "LocationComponent.kt", m3971i = {0}, m3972l = {80}, m3973m = "fetchIpApiInfo", m3974n = {"this"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class LocationComponent$fetchIpApiInfo$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LocationComponent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocationComponent$fetchIpApiInfo$1(LocationComponent locationComponent, Continuation continuation) {
        super(continuation);
        this.this$0 = locationComponent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.fetchIpApiInfo(this);
    }
}
