package com.pudutech.bumblebee.robot.led_screen;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: UpdateFontLib.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, m3961d2 = {"prepare", "", "size", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.led_screen.UpdateFontLib", m3970f = "UpdateFontLib.kt", m3971i = {0, 0}, m3972l = {27}, m3973m = "prepare", m3974n = {"this", "size"}, m3975s = {"L$0", "I$0"})
/* loaded from: classes.dex */
public final class UpdateFontLib$prepare$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ UpdateFontLib this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateFontLib$prepare$1(UpdateFontLib updateFontLib, Continuation continuation) {
        super(continuation);
        this.this$0 = updateFontLib;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.prepare(0, this);
    }
}
