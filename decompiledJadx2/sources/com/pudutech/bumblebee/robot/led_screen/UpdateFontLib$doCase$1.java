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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0086@"}, m3961d2 = {"doCase", "", "targetVersion", "", "fileBytes", "", "isForce", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.led_screen.UpdateFontLib", m3970f = "UpdateFontLib.kt", m3971i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, m3972l = {63, 87, 107}, m3973m = "doCase", m3974n = {"this", "targetVersion", "fileBytes", "isForce", "this", "targetVersion", "fileBytes", "isForce", "response", "version", "this", "targetVersion", "fileBytes", "isForce", "response", "version", "segments", "$this$forEachIndexed$iv", "index$iv", "item$iv", "bytes", "index", "isSuccess", "i", "crc"}, m3975s = {"L$0", "I$0", "L$1", "Z$0", "L$0", "I$0", "L$1", "Z$0", "L$2", "L$3", "L$0", "I$0", "L$1", "Z$0", "L$2", "L$3", "L$4", "L$5", "I$1", "L$7", "L$8", "I$2", "I$3", "I$4", "I$6"})
/* loaded from: classes.dex */
public final class UpdateFontLib$doCase$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    int I$5;
    int I$6;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ UpdateFontLib this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateFontLib$doCase$1(UpdateFontLib updateFontLib, Continuation continuation) {
        super(continuation);
        this.this$0 = updateFontLib;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doCase(0, null, false, this);
    }
}
