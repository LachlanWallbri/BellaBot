package com.pudutech.event_tracking.click;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ViewClickHook.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086@"}, m3961d2 = {"hook", "", "view", "Landroid/view/View;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.event_tracking.click.ViewClickHookManager", m3970f = "ViewClickHook.kt", m3971i = {0, 0, 0, 0, 0, 1, 1}, m3972l = {43, 46}, m3973m = "hook", m3974n = {"this", "view", "$this$forEach$iv", "element$iv", "it", "this", "view"}, m3975s = {"L$0", "L$1", "L$2", "L$4", "L$5", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class ViewClickHookManager$hook$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ViewClickHookManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewClickHookManager$hook$1(ViewClickHookManager viewClickHookManager, Continuation continuation) {
        super(continuation);
        this.this$0 = viewClickHookManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.hook(null, this);
    }
}
