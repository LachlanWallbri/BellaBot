package com.pudutech.mirsdk.hardware.activity;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: HardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "code", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class HardwareActivity$checkAuth$1 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ Function0 $callback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HardwareActivity$checkAuth$1(Function0 function0) {
        super(1);
        this.$callback = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
        invoke(num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        if (i != 0) {
            return;
        }
        this.$callback.invoke();
    }
}
