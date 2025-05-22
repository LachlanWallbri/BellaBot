package com.pudutech.mirsdk.movetask;

import android.os.SystemClock;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.map.elements.AccessControlPoint;
import com.pudutech.mirsdk.movetask.AccessControlTask;
import java.util.Iterator;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AccessControlTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.AccessControlTask$WaitAccessControl$parseLora$1", m3970f = "AccessControlTask.kt", m3971i = {0, 0, 1, 1}, m3972l = {583, 585}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "waitTime", "$this$launch", "waitTime"}, m3975s = {"L$0", "J$0", "L$0", "J$0"})
/* loaded from: classes6.dex */
final class AccessControlTask$WaitAccessControl$parseLora$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6309p$;
    final /* synthetic */ AccessControlTask.WaitAccessControl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlTask$WaitAccessControl$parseLora$1(AccessControlTask.WaitAccessControl waitAccessControl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitAccessControl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AccessControlTask$WaitAccessControl$parseLora$1 accessControlTask$WaitAccessControl$parseLora$1 = new AccessControlTask$WaitAccessControl$parseLora$1(this.this$0, completion);
        accessControlTask$WaitAccessControl$parseLora$1.f6309p$ = (CoroutineScope) obj;
        return accessControlTask$WaitAccessControl$parseLora$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AccessControlTask$WaitAccessControl$parseLora$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        long longValue;
        CoroutineScope coroutineScope;
        Double boxDouble;
        Long boxLong;
        AccessControlTask$WaitAccessControl$parseLora$1 accessControlTask$WaitAccessControl$parseLora$1;
        long j;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6309p$;
            Iterator it = AccessControlTask.access$getAccessControlPoints$p(AccessControlTask.this).iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj2 = null;
                    break;
                }
                obj2 = it.next();
                if (Boxing.boxBoolean(Intrinsics.areEqual(((AccessControlPoint) obj2).getId(), AccessControlTask.this.acrossAccessControlId)).booleanValue()) {
                    break;
                }
            }
            AccessControlPoint accessControlPoint = (AccessControlPoint) obj2;
            longValue = ((accessControlPoint == null || (boxDouble = Boxing.boxDouble(accessControlPoint.getOpenedSpendTime())) == null || (boxLong = Boxing.boxLong((long) boxDouble.doubleValue())) == null) ? 5L : boxLong.longValue()) * 1000;
            coroutineScope = coroutineScope2;
        } else {
            if (i != 1) {
                if (i == 2) {
                    long j2 = this.J$0;
                    ResultKt.throwOnFailure(obj);
                    accessControlTask$WaitAccessControl$parseLora$1 = this;
                    AccessControlTask.this.taskIndex++;
                    AccessControlTask.this.moveState = new AccessControlTask.MoveAcrossAccessControl();
                    AccessControlTask.access$getMoveState$p(AccessControlTask.this).start();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            longValue = this.J$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        accessControlTask$WaitAccessControl$parseLora$1 = this;
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            j = accessControlTask$WaitAccessControl$parseLora$1.this$0.recvResponseTime;
            if (elapsedRealtime - j >= longValue) {
                break;
            }
            accessControlTask$WaitAccessControl$parseLora$1.L$0 = coroutineScope;
            accessControlTask$WaitAccessControl$parseLora$1.J$0 = longValue;
            accessControlTask$WaitAccessControl$parseLora$1.label = 1;
            if (DelayKt.delay(500L, accessControlTask$WaitAccessControl$parseLora$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        MoveAction action = AccessControlTask.this.getAction();
        accessControlTask$WaitAccessControl$parseLora$1.L$0 = coroutineScope;
        accessControlTask$WaitAccessControl$parseLora$1.J$0 = longValue;
        accessControlTask$WaitAccessControl$parseLora$1.label = 2;
        if (MoveAction.actionStop$default(action, false, accessControlTask$WaitAccessControl$parseLora$1, 1, null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        AccessControlTask.this.taskIndex++;
        AccessControlTask.this.moveState = new AccessControlTask.MoveAcrossAccessControl();
        AccessControlTask.access$getMoveState$p(AccessControlTask.this).start();
        return Unit.INSTANCE;
    }
}
