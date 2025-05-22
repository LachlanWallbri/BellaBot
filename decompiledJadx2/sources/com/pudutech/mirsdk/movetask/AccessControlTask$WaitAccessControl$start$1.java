package com.pudutech.mirsdk.movetask;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.AccessDoorListener;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegment;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import com.pudutech.mirsdk.movetask.AccessControlTask;
import java.util.List;
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
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AccessControlTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.AccessControlTask$WaitAccessControl$start$1", m3970f = "AccessControlTask.kt", m3971i = {0, 1}, m3972l = {445, 453}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes6.dex */
public final class AccessControlTask$WaitAccessControl$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6313p$;
    final /* synthetic */ AccessControlTask.WaitAccessControl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlTask$WaitAccessControl$start$1(AccessControlTask.WaitAccessControl waitAccessControl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitAccessControl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AccessControlTask$WaitAccessControl$start$1 accessControlTask$WaitAccessControl$start$1 = new AccessControlTask$WaitAccessControl$start$1(this.this$0, completion);
        accessControlTask$WaitAccessControl$start$1.f6313p$ = (CoroutineScope) obj;
        return accessControlTask$WaitAccessControl$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AccessControlTask$WaitAccessControl$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Job job;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6313p$;
            Pdlog.m3273d(AccessControlTask.this.TAG, "robot start call access control, and continue to access wait point");
            MoveAction action = AccessControlTask.this.getAction();
            this.L$0 = coroutineScope;
            this.label = 1;
            if (MoveAction.actionStop$default(action, false, this, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    Pdlog.m3273d(AccessControlTask.this.TAG, "notice access: " + AccessDoorControlState.CallingAccessDoor);
                    AccessControlTask.this.getAccessControlListener().notify(new Function2<AccessDoorListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.AccessControlTask$WaitAccessControl$start$1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(AccessDoorListener accessDoorListener, String str) {
                            invoke2(accessDoorListener, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(AccessDoorListener it, String str) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            it.informAccessDoorControlState(AccessDoorControlState.CallingAccessDoor, AccessControlTask.this.acrossAccessControlId);
                        }
                    });
                    this.this$0.runCallAccessControlJob();
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        AccessControlTask.this.moveToPoint(AccessControlTask.this.taskIndex);
        PathSegments pathSegments = AccessControlTask.this.getPathSegments();
        List<PathSegment> segments = pathSegments != null ? pathSegments.getSegments() : null;
        if (segments == null) {
            Intrinsics.throwNpe();
        }
        if (segments.get(AccessControlTask.this.taskIndex + 1).getNavMode() == NavigationMode.AccessControl) {
            AccessControlTask accessControlTask = AccessControlTask.this;
            PathSegments pathSegments2 = AccessControlTask.this.getPathSegments();
            List<PathSegment> segments2 = pathSegments2 != null ? pathSegments2.getSegments() : null;
            if (segments2 == null) {
                Intrinsics.throwNpe();
            }
            accessControlTask.acrossAccessControlId = segments2.get(AccessControlTask.this.taskIndex + 1).getDeviceId();
        }
        job = AccessControlTask.this.accessControlJob;
        if (job != null && job.isActive()) {
            Pdlog.m3273d(AccessControlTask.this.TAG, "accessControlJob is also active, not need re-start inform work");
            return Unit.INSTANCE;
        }
        this.L$0 = coroutineScope;
        this.label = 2;
        if (DelayKt.delay(10L, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        Pdlog.m3273d(AccessControlTask.this.TAG, "notice access: " + AccessDoorControlState.CallingAccessDoor);
        AccessControlTask.this.getAccessControlListener().notify(new Function2<AccessDoorListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.AccessControlTask$WaitAccessControl$start$1.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(AccessDoorListener accessDoorListener, String str) {
                invoke2(accessDoorListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AccessDoorListener it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.informAccessDoorControlState(AccessDoorControlState.CallingAccessDoor, AccessControlTask.this.acrossAccessControlId);
            }
        });
        this.this$0.runCallAccessControlJob();
        return Unit.INSTANCE;
    }
}
