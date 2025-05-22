package com.pudutech.schedulerlib;

import com.pudutech.schedulerlib.ScheduleController;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ScheduleController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/schedulerlib/ScheduleController$FPSCallback;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
final class ScheduleController$debug$3 extends Lambda implements Function2<ScheduleController.FPSCallback, String, Unit> {
    final /* synthetic */ LinkedHashMap $eps;
    final /* synthetic */ LinkedHashMap $fps;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScheduleController$debug$3(LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2) {
        super(2);
        this.$fps = linkedHashMap;
        this.$eps = linkedHashMap2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(ScheduleController.FPSCallback fPSCallback, String str) {
        invoke2(fPSCallback, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(ScheduleController.FPSCallback it, String str) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
        it.updateFPS(this.$fps, this.$eps);
    }
}
