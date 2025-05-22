package com.pudutech.schedulerlib;

import com.pudutech.mirsdk.hardware.SchCommunicateInfoListener;
import com.pudutech.mirsdk.hardware.serialize.ScheduleCommunicateInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ScheduleController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/mirsdk/hardware/SchCommunicateInfoListener;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
final class ScheduleController$calculateFps$1 extends Lambda implements Function2<SchCommunicateInfoListener, String, Unit> {
    final /* synthetic */ ScheduleController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScheduleController$calculateFps$1(ScheduleController scheduleController) {
        super(2);
        this.this$0 = scheduleController;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(SchCommunicateInfoListener schCommunicateInfoListener, String str) {
        invoke2(schCommunicateInfoListener, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(SchCommunicateInfoListener it, String str) {
        ScheduleCommunicateInfo scheduleCommunicateInfo;
        Intrinsics.checkParameterIsNotNull(it, "it");
        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
        scheduleCommunicateInfo = this.this$0.commInfo;
        it.scheduleCommunicateInfo(scheduleCommunicateInfo);
    }
}
