package com.pudutech.mirsdk.mircore.module;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.ScheduleListener;
import com.pudutech.mirsdk.mircore.coreparcel.ScheduleFillInState;
import com.pudutech.mirsdk.mircore.mirschedulemaster.ScheduleMaster;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: HardwareLinker.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.module.HardwareLinker$sendScheduleInfo$1", m3970f = "HardwareLinker.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class HardwareLinker$sendScheduleInfo$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ RobotScheduleInfo $currentSchInfo;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6259p$;
    final /* synthetic */ HardwareLinker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareLinker$sendScheduleInfo$1(HardwareLinker hardwareLinker, RobotScheduleInfo robotScheduleInfo, Continuation continuation) {
        super(2, continuation);
        this.this$0 = hardwareLinker;
        this.$currentSchInfo = robotScheduleInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareLinker$sendScheduleInfo$1 hardwareLinker$sendScheduleInfo$1 = new HardwareLinker$sendScheduleInfo$1(this.this$0, this.$currentSchInfo, completion);
        hardwareLinker$sendScheduleInfo$1.f6259p$ = (CoroutineScope) obj;
        return hardwareLinker$sendScheduleInfo$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareLinker$sendScheduleInfo$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Vector3d vector3d;
        String str;
        Vector3d vector3d2;
        Vector3d vector3d3;
        Vector3d vector3d4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6259p$;
        ScheduleMaster scheduleMaster = ScheduleMaster.INSTANCE;
        vector3d = this.this$0.fillInGoal;
        boolean[] changeTakeInfo = scheduleMaster.getChangeTakeInfo(vector3d);
        if (changeTakeInfo[0]) {
            if (changeTakeInfo[1]) {
                str = this.this$0.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Current robot fill in to (");
                vector3d2 = this.this$0.fillInGoal;
                sb.append(vector3d2.getX());
                sb.append(", ");
                vector3d3 = this.this$0.fillInGoal;
                sb.append(vector3d3.getY());
                sb.append(", ");
                vector3d4 = this.this$0.fillInGoal;
                sb.append(vector3d4.getZ());
                sb.append(')');
                Pdlog.m3275i(str, sb.toString());
                this.this$0.getScheduleListeners().notify(new Function2<ScheduleListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.HardwareLinker$sendScheduleInfo$1.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ScheduleListener scheduleListener, String str2) {
                        invoke2(scheduleListener, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ScheduleListener it, String str2) {
                        ScheduleFillInState scheduleFillInState;
                        ScheduleFillInState scheduleFillInState2;
                        Vector3d vector3d5;
                        ScheduleFillInState scheduleFillInState3;
                        ScheduleFillInState scheduleFillInState4;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        scheduleFillInState = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                        scheduleFillInState.setChanged(true);
                        scheduleFillInState2 = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                        vector3d5 = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInGoal;
                        scheduleFillInState2.setFinal_goal(vector3d5);
                        scheduleFillInState3 = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                        scheduleFillInState3.setScheduling_mode(HardwareLinker$sendScheduleInfo$1.this.$currentSchInfo.getScheduling_mode());
                        scheduleFillInState4 = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                        it.onScheduleFillIn(scheduleFillInState4);
                    }
                });
            } else {
                this.this$0.getScheduleListeners().notify(new Function2<ScheduleListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.HardwareLinker$sendScheduleInfo$1.2
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ScheduleListener scheduleListener, String str2) {
                        invoke2(scheduleListener, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ScheduleListener it, String str2) {
                        ScheduleFillInState scheduleFillInState;
                        ScheduleFillInState scheduleFillInState2;
                        ScheduleFillInState scheduleFillInState3;
                        ScheduleFillInState scheduleFillInState4;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        scheduleFillInState = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                        scheduleFillInState.setChanged(true);
                        scheduleFillInState2 = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                        scheduleFillInState2.setFinal_goal((Vector3d) null);
                        scheduleFillInState3 = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                        scheduleFillInState3.setScheduling_mode(HardwareLinker$sendScheduleInfo$1.this.$currentSchInfo.getScheduling_mode());
                        scheduleFillInState4 = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                        it.onScheduleFillIn(scheduleFillInState4);
                    }
                });
            }
        } else {
            this.this$0.getScheduleListeners().notify(new Function2<ScheduleListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.HardwareLinker$sendScheduleInfo$1.3
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(ScheduleListener scheduleListener, String str2) {
                    invoke2(scheduleListener, str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ScheduleListener it, String str2) {
                    ScheduleFillInState scheduleFillInState;
                    ScheduleFillInState scheduleFillInState2;
                    ScheduleFillInState scheduleFillInState3;
                    ScheduleFillInState scheduleFillInState4;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                    scheduleFillInState = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                    scheduleFillInState.setChanged(false);
                    scheduleFillInState2 = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                    scheduleFillInState2.setFinal_goal((Vector3d) null);
                    scheduleFillInState3 = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                    scheduleFillInState3.setScheduling_mode(HardwareLinker$sendScheduleInfo$1.this.$currentSchInfo.getScheduling_mode());
                    scheduleFillInState4 = HardwareLinker$sendScheduleInfo$1.this.this$0.fillInState;
                    it.onScheduleFillIn(scheduleFillInState4);
                }
            });
        }
        return Unit.INSTANCE;
    }
}
