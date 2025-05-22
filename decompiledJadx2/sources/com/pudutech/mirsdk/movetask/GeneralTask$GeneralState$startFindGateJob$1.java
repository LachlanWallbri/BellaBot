package com.pudutech.mirsdk.movetask;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.map.elements.FireFoxGateSource;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import com.pudutech.mirsdk.movetask.GeneralTask;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GeneralTask$GeneralState$startFindGateJob$1", m3970f = "GeneralTask.kt", m3971i = {0}, m3972l = {287}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class GeneralTask$GeneralState$startFindGateJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6395p$;
    final /* synthetic */ GeneralTask.GeneralState this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeneralTask$GeneralState$startFindGateJob$1(GeneralTask.GeneralState generalState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = generalState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GeneralTask$GeneralState$startFindGateJob$1 generalTask$GeneralState$startFindGateJob$1 = new GeneralTask$GeneralState$startFindGateJob$1(this.this$0, completion);
        generalTask$GeneralState$startFindGateJob$1.f6395p$ = (CoroutineScope) obj;
        return generalTask$GeneralState$startFindGateJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeneralTask$GeneralState$startFindGateJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0033 -> B:5:0x0036). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        GeneralTask$GeneralState$startFindGateJob$1 generalTask$GeneralState$startFindGateJob$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6395p$;
            generalTask$GeneralState$startFindGateJob$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            generalTask$GeneralState$startFindGateJob$1 = this;
            MirCoreInterface mirCoreInterface = GeneralTask.this.getCoreService().getInterface();
            ScheduleInterface scheduler = mirCoreInterface == null ? mirCoreInterface.getScheduler() : null;
            if (scheduler == null) {
                Intrinsics.throwNpe();
            }
            DestinationWithAccRange findNextGateOnPath = scheduler.findNextGateOnPath();
            if (findNextGateOnPath.getRange() >= 0) {
                if (!(findNextGateOnPath.getId().length() == 0)) {
                    double range = findNextGateOnPath.getRange();
                    FireFoxGateSource fireFoxGateSource = GeneralTask.this.getAction().getAtlas().getMapDecode().getFireFoxGates().get(findNextGateOnPath.getId());
                    if (fireFoxGateSource == null) {
                        Intrinsics.throwNpe();
                    }
                    if (range <= fireFoxGateSource.getUp_slow_distance()) {
                        GeneralTask.this.setGateID(findNextGateOnPath.getId());
                        Pdlog.m3273d(GeneralTask.this.TAG, "find gate " + GeneralTask.this.getGateID() + " in general Task, dist " + findNextGateOnPath.getRange());
                        return Unit.INSTANCE;
                    }
                }
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                generalTask$GeneralState$startFindGateJob$1.L$0 = coroutineScope;
                generalTask$GeneralState$startFindGateJob$1.label = 1;
                if (DelayKt.delay(1000L, generalTask$GeneralState$startFindGateJob$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                MirCoreInterface mirCoreInterface2 = GeneralTask.this.getCoreService().getInterface();
                if (mirCoreInterface2 == null) {
                }
                if (scheduler == null) {
                }
                DestinationWithAccRange findNextGateOnPath2 = scheduler.findNextGateOnPath();
                if (findNextGateOnPath2.getRange() >= 0) {
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
