package com.pudutech.bumblebee.robot_ui.util;

import com.pudutech.base.ProcStat;
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

/* compiled from: AppUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.util.AppUtil$initMain$1", m3970f = "AppUtil.kt", m3971i = {0, 0, 1, 1}, m3972l = {374, 375}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "proc", "$this$launch", "proc"}, m3975s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes4.dex */
final class AppUtil$initMain$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4964p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppUtil$initMain$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AppUtil$initMain$1 appUtil$initMain$1 = new AppUtil$initMain$1(completion);
        appUtil$initMain$1.f4964p$ = (CoroutineScope) obj;
        return appUtil$initMain$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppUtil$initMain$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0062 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0043  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0063 -> B:7:0x003d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ProcStat procStat;
        AppUtil$initMain$1 appUtil$initMain$1;
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4964p$;
            procStat = new ProcStat();
        } else if (i == 1) {
            procStat = (ProcStat) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            Object obj3 = coroutine_suspended;
            appUtil$initMain$1 = this;
            appUtil$initMain$1.L$0 = coroutineScope2;
            appUtil$initMain$1.L$1 = procStat;
            appUtil$initMain$1.label = 2;
            if (DelayKt.delay(60000L, appUtil$initMain$1) != obj3) {
                return obj3;
            }
            CoroutineScope coroutineScope3 = coroutineScope2;
            obj2 = obj3;
            coroutineScope = coroutineScope3;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                appUtil$initMain$1.L$0 = coroutineScope;
                appUtil$initMain$1.L$1 = procStat;
                appUtil$initMain$1.label = 1;
                if (procStat.procState(appUtil$initMain$1) == obj2) {
                    return obj2;
                }
                Object obj4 = obj2;
                coroutineScope2 = coroutineScope;
                obj3 = obj4;
                appUtil$initMain$1.L$0 = coroutineScope2;
                appUtil$initMain$1.L$1 = procStat;
                appUtil$initMain$1.label = 2;
                if (DelayKt.delay(60000L, appUtil$initMain$1) != obj3) {
                }
            } else {
                return Unit.INSTANCE;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            procStat = (ProcStat) this.L$1;
            CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope4;
        }
        obj2 = coroutine_suspended;
        appUtil$initMain$1 = this;
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
        }
    }
}
