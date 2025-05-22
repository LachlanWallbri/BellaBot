package com.pudutech.robot.module.openapi;

import com.pudutech.base.Pdlog;
import java.io.IOException;
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
/* compiled from: RobotOpenHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.module.openapi.RobotOpenHelper$recheckRegion$1$1", m3970f = "RobotOpenHelper.kt", m3971i = {0, 1, 1}, m3972l = {152, 159}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "throwable"}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes6.dex */
public final class RobotOpenHelper$recheckRegion$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $it;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7196p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotOpenHelper$recheckRegion$1$1(String str, Continuation continuation) {
        super(2, continuation);
        this.$it = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotOpenHelper$recheckRegion$1$1 robotOpenHelper$recheckRegion$1$1 = new RobotOpenHelper$recheckRegion$1$1(this.$it, completion);
        robotOpenHelper$recheckRegion$1$1.f7196p$ = (CoroutineScope) obj;
        return robotOpenHelper$recheckRegion$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotOpenHelper$recheckRegion$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object obj2 = this.label;
        try {
        } catch (Exception e) {
            if ((e instanceof IOException) || RobotOpenHelper.INSTANCE.getRequestFailureCount() > 8) {
                RobotOpenHelper robotOpenHelper = RobotOpenHelper.INSTANCE;
                robotOpenHelper.setRequestFailureCount(robotOpenHelper.getRequestFailureCount() + 1);
                RobotOpenHelper robotOpenHelper2 = RobotOpenHelper.INSTANCE;
                str = RobotOpenHelper.TAG;
                Pdlog.m3277w(str, "检测源失败，第" + RobotOpenHelper.INSTANCE.getRequestFailureCount() + "次请求失败，正在重试，throwable = " + e);
                this.L$0 = obj2;
                this.L$1 = e;
                this.label = 2;
                if (DelayKt.delay(8000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                RobotOpenHelper.INSTANCE.setRequestFailureCount(0);
                RobotOpenHelper robotOpenHelper3 = RobotOpenHelper.INSTANCE;
                str2 = RobotOpenHelper.TAG;
                Pdlog.m3277w(str2, "检测源失败，第" + RobotOpenHelper.INSTANCE.getRequestFailureCount() + "次请求失败，不重试了，throwable = " + e);
            }
        }
        if (obj2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7196p$;
            RobotOpenHelper robotOpenHelper4 = RobotOpenHelper.INSTANCE;
            String str3 = this.$it;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj2 = coroutineScope;
            if (robotOpenHelper4.doGetEdgeHost(str3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (obj2 != 1) {
                if (obj2 == 2) {
                    ResultKt.throwOnFailure(obj);
                    RobotOpenHelper.INSTANCE.recheckRegion();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = coroutineScope2;
        }
        return Unit.INSTANCE;
    }
}
