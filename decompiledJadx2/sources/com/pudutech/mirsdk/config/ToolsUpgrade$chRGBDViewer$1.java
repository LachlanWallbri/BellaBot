package com.pudutech.mirsdk.config;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: ToolsUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0086@"}, m3961d2 = {"chRGBDViewer", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.config.ToolsUpgrade", m3970f = "ToolsUpgrade.kt", m3971i = {0}, m3972l = {108}, m3973m = "chRGBDViewer", m3974n = {"this"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
final class ToolsUpgrade$chRGBDViewer$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ToolsUpgrade this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ToolsUpgrade$chRGBDViewer$1(ToolsUpgrade toolsUpgrade, Continuation continuation) {
        super(continuation);
        this.this$0 = toolsUpgrade;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.chRGBDViewer(this);
    }
}
