package com.pudutech.antichannelconflict.location;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PDLocationManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082@"}, m3961d2 = {"locateFromHardware", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.antichannelconflict.location.PDLocationManager", m3970f = "PDLocationManager.kt", m3971i = {0, 1, 1, 2, 2}, m3972l = {121, 127, 128}, m3973m = "locateFromHardware", m3974n = {"this", "this", "wifiMacs", "this", "wifiMacs"}, m3975s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes4.dex */
public final class PDLocationManager$locateFromHardware$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PDLocationManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PDLocationManager$locateFromHardware$1(PDLocationManager pDLocationManager, Continuation continuation) {
        super(continuation);
        this.this$0 = pDLocationManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.locateFromHardware(this);
    }
}
