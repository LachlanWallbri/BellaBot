package com.pudutech.schedulerlib.p065ui;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.schedulerlib.connection.ESPScheduleNative;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SchedulerPressTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerPressTestActivity$openSwitchTest$1$openResult$1", m3970f = "SchedulerPressTestActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes7.dex */
final class SchedulerPressTestActivity$openSwitchTest$1$openResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7481p$;
    final /* synthetic */ SchedulerPressTestActivity$openSwitchTest$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerPressTestActivity$openSwitchTest$1$openResult$1(SchedulerPressTestActivity$openSwitchTest$1 schedulerPressTestActivity$openSwitchTest$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = schedulerPressTestActivity$openSwitchTest$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerPressTestActivity$openSwitchTest$1$openResult$1 schedulerPressTestActivity$openSwitchTest$1$openResult$1 = new SchedulerPressTestActivity$openSwitchTest$1$openResult$1(this.this$0, completion);
        schedulerPressTestActivity$openSwitchTest$1$openResult$1.f7481p$ = (CoroutineScope) obj;
        return schedulerPressTestActivity$openSwitchTest$1$openResult$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((SchedulerPressTestActivity$openSwitchTest$1$openResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        MachineInfo.ESP32Type eSP32Type;
        boolean openEspForPressTest;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7481p$;
        str = this.this$0.this$0.TAG;
        Pdlog.m3273d(str, "switch test init ...");
        SchedulerPressTestActivity schedulerPressTestActivity = this.this$0.this$0;
        eSP32Type = this.this$0.this$0.espType;
        openEspForPressTest = schedulerPressTestActivity.openEspForPressTest(eSP32Type);
        return Boxing.boxBoolean(openEspForPressTest && ESPScheduleNative.checkHardwareHandshakeFortest$default(ESPScheduleNative.INSTANCE, false, 1, null));
    }
}
