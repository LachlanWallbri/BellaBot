package com.pudutech.schedulerlib.p065ui;

import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.schedulerlib.connection.ESPScheduleNative;
import java.util.concurrent.atomic.AtomicLong;
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
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SchedulerPressTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerPressTestActivity$switchTestStart$1", m3970f = "SchedulerPressTestActivity.kt", m3971i = {0}, m3972l = {117}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes7.dex */
public final class SchedulerPressTestActivity$switchTestStart$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MachineInfo.ESP32Type $esp;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7486p$;
    final /* synthetic */ SchedulerPressTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerPressTestActivity$switchTestStart$1(SchedulerPressTestActivity schedulerPressTestActivity, MachineInfo.ESP32Type eSP32Type, Continuation continuation) {
        super(2, continuation);
        this.this$0 = schedulerPressTestActivity;
        this.$esp = eSP32Type;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerPressTestActivity$switchTestStart$1 schedulerPressTestActivity$switchTestStart$1 = new SchedulerPressTestActivity$switchTestStart$1(this.this$0, this.$esp, completion);
        schedulerPressTestActivity$switchTestStart$1.f7486p$ = (CoroutineScope) obj;
        return schedulerPressTestActivity$switchTestStart$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SchedulerPressTestActivity$switchTestStart$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x005b -> B:5:0x005e). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        SchedulerPressTestActivity$switchTestStart$1 schedulerPressTestActivity$switchTestStart$1;
        boolean z;
        AtomicLong atomicLong;
        boolean openEspForPressTest;
        AtomicLong atomicLong2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7486p$;
            schedulerPressTestActivity$switchTestStart$1 = this;
            z = schedulerPressTestActivity$switchTestStart$1.this$0.switchButtonFlag;
            if (!z) {
            }
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            schedulerPressTestActivity$switchTestStart$1 = this;
            schedulerPressTestActivity$switchTestStart$1.this$0.closeEspForPressTest();
            z = schedulerPressTestActivity$switchTestStart$1.this$0.switchButtonFlag;
            if (!z) {
                atomicLong = schedulerPressTestActivity$switchTestStart$1.this$0.switchOpenCnt;
                atomicLong.getAndIncrement();
                openEspForPressTest = schedulerPressTestActivity$switchTestStart$1.this$0.openEspForPressTest(schedulerPressTestActivity$switchTestStart$1.$esp);
                if (openEspForPressTest && ESPScheduleNative.checkHardwareHandshakeFortest$default(ESPScheduleNative.INSTANCE, false, 1, null)) {
                    atomicLong2 = schedulerPressTestActivity$switchTestStart$1.this$0.switchOpenSuccess;
                    atomicLong2.getAndIncrement();
                }
                schedulerPressTestActivity$switchTestStart$1.L$0 = coroutineScope;
                schedulerPressTestActivity$switchTestStart$1.label = 1;
                if (DelayKt.delay(200L, schedulerPressTestActivity$switchTestStart$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                schedulerPressTestActivity$switchTestStart$1.this$0.closeEspForPressTest();
                z = schedulerPressTestActivity$switchTestStart$1.this$0.switchButtonFlag;
                if (!z) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
