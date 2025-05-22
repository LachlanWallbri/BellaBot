package com.pudutech.mirsdk.config;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ToolsUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086@"}, m3961d2 = {"chkMapifyAndRGBDViewer", "", "isSilent", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.config.ToolsUpgrade", m3970f = "ToolsUpgrade.kt", m3971i = {0, 0, 0, 0, 0}, m3972l = {78}, m3973m = "chkMapifyAndRGBDViewer", m3974n = {"this", "isSilent", "curClickTime", "file", "needUpdate"}, m3975s = {"L$0", "Z$0", "J$0", "L$1", "Z$1"})
/* loaded from: classes5.dex */
public final class ToolsUpgrade$chkMapifyAndRGBDViewer$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ToolsUpgrade this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToolsUpgrade$chkMapifyAndRGBDViewer$1(ToolsUpgrade toolsUpgrade, Continuation continuation) {
        super(continuation);
        this.this$0 = toolsUpgrade;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.chkMapifyAndRGBDViewer(false, this);
    }
}
