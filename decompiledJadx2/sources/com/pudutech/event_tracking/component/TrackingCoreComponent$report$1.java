package com.pudutech.event_tracking.component;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TrackingCoreComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0082@"}, m3961d2 = {"report", "", AIUIConstant.KEY_CONTENT, "", "Lcom/pudutech/event_tracking/bean/TrackEvent;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.event_tracking.component.TrackingCoreComponent", m3970f = "TrackingCoreComponent.kt", m3971i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, m3972l = {132, 140, 142}, m3973m = "report", m3974n = {"this", AIUIConstant.KEY_CONTENT, "this", AIUIConstant.KEY_CONTENT, TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "succ", "this", AIUIConstant.KEY_CONTENT, TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "succ"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2", "Z$0"})
/* loaded from: classes5.dex */
public final class TrackingCoreComponent$report$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TrackingCoreComponent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrackingCoreComponent$report$1(TrackingCoreComponent trackingCoreComponent, Continuation continuation) {
        super(continuation);
        this.this$0 = trackingCoreComponent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.report(null, this);
    }
}
