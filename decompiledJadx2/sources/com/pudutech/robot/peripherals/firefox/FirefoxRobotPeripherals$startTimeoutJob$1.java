package com.pudutech.robot.peripherals.firefox;

import com.pudutech.base.Pdlog;
import java.util.ArrayList;
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
/* compiled from: FirefoxRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.firefox.FirefoxRobotPeripherals$startTimeoutJob$1", m3970f = "FirefoxRobotPeripherals.kt", m3971i = {0, 0}, m3972l = {167}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "currentTimeMillis"}, m3975s = {"L$0", "J$0"})
/* loaded from: classes6.dex */
public final class FirefoxRobotPeripherals$startTimeoutJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7356p$;
    final /* synthetic */ FirefoxRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirefoxRobotPeripherals$startTimeoutJob$1(FirefoxRobotPeripherals firefoxRobotPeripherals, Continuation continuation) {
        super(2, continuation);
        this.this$0 = firefoxRobotPeripherals;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FirefoxRobotPeripherals$startTimeoutJob$1 firefoxRobotPeripherals$startTimeoutJob$1 = new FirefoxRobotPeripherals$startTimeoutJob$1(this.this$0, completion);
        firefoxRobotPeripherals$startTimeoutJob$1.f7356p$ = (CoroutineScope) obj;
        return firefoxRobotPeripherals$startTimeoutJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FirefoxRobotPeripherals$startTimeoutJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x003a -> B:5:0x003d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        long j;
        CoroutineScope coroutineScope;
        FirefoxRobotPeripherals$startTimeoutJob$1 firefoxRobotPeripherals$startTimeoutJob$1;
        ArrayList arrayList;
        boolean z;
        boolean z2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            j = 0;
            coroutineScope = this.f7356p$;
            firefoxRobotPeripherals$startTimeoutJob$1 = this;
            if (j < 20000) {
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j = this.J$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            firefoxRobotPeripherals$startTimeoutJob$1 = this;
            j += 1000;
            if (j < 20000) {
                firefoxRobotPeripherals$startTimeoutJob$1.L$0 = coroutineScope;
                firefoxRobotPeripherals$startTimeoutJob$1.J$0 = j;
                firefoxRobotPeripherals$startTimeoutJob$1.label = 1;
                if (DelayKt.delay(1000L, firefoxRobotPeripherals$startTimeoutJob$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                j += 1000;
                if (j < 20000) {
                    arrayList = firefoxRobotPeripherals$startTimeoutJob$1.this$0.currentControlHatchs;
                    if (arrayList != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("舱门操作超时 isOpen:");
                        z = firefoxRobotPeripherals$startTimeoutJob$1.this$0.isOpen;
                        sb.append(z);
                        Pdlog.m3273d("FirefoxRobotPeripherals", sb.toString());
                        FirefoxRobotPeripherals firefoxRobotPeripherals = firefoxRobotPeripherals$startTimeoutJob$1.this$0;
                        z2 = firefoxRobotPeripherals.isOpen;
                        firefoxRobotPeripherals.callbackHatchsControlState(arrayList, z2 ? HatchesStatus.OpenFailed : HatchesStatus.CloseFailed);
                    }
                    firefoxRobotPeripherals$startTimeoutJob$1.this$0.stopTimeoutJob();
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
