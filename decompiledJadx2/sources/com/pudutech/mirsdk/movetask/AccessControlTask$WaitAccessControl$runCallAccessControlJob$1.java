package com.pudutech.mirsdk.movetask;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.AccessDoorListener;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.movetask.AccessControlTask;
import com.pudutech.mirsdk.robotsdk.AccConnectionInterface;
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
import kotlinx.coroutines.Job;
import org.apache.http.HttpStatus;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AccessControlTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.AccessControlTask$WaitAccessControl$runCallAccessControlJob$1", m3970f = "AccessControlTask.kt", m3971i = {0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4}, m3972l = {401, HttpStatus.SC_GONE, HttpStatus.SC_REQUEST_URI_TOO_LONG, 427, 431}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "currentTime", "dura", "$this$launch", "currentTime", "dura", "$this$launch", "currentTime", "dura", "$this$launch", "currentTime", "dura"}, m3975s = {"L$0", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1"})
/* loaded from: classes6.dex */
public final class AccessControlTask$WaitAccessControl$runCallAccessControlJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    long J$1;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6312p$;
    final /* synthetic */ AccessControlTask.WaitAccessControl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlTask$WaitAccessControl$runCallAccessControlJob$1(AccessControlTask.WaitAccessControl waitAccessControl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitAccessControl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AccessControlTask$WaitAccessControl$runCallAccessControlJob$1 accessControlTask$WaitAccessControl$runCallAccessControlJob$1 = new AccessControlTask$WaitAccessControl$runCallAccessControlJob$1(this.this$0, completion);
        accessControlTask$WaitAccessControl$runCallAccessControlJob$1.f6312p$ = (CoroutineScope) obj;
        return accessControlTask$WaitAccessControl$runCallAccessControlJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AccessControlTask$WaitAccessControl$runCallAccessControlJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a7  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0228 -> B:16:0x022b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:64:0x0120 -> B:45:0x0123). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        AccessControlTask$WaitAccessControl$runCallAccessControlJob$1 accessControlTask$WaitAccessControl$runCallAccessControlJob$1;
        Job job;
        long j;
        Object obj2;
        long j2;
        int i;
        String str;
        int i2;
        boolean z;
        int i3;
        int i4;
        boolean z2;
        long j3;
        String str2;
        int i5;
        boolean z3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i6 = this.label;
        int i7 = 1;
        if (i6 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6312p$;
        } else {
            if (i6 != 1) {
                if (i6 == 2) {
                    long j4 = this.J$1;
                    long j5 = this.J$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj2 = coroutine_suspended;
                    j = j5;
                    accessControlTask$WaitAccessControl$runCallAccessControlJob$1 = this;
                    j2 = SystemClock.elapsedRealtime() - j;
                    i2 = AccessControlTask.this.CALL_ERRTIME;
                    if (j2 >= i2) {
                        z = accessControlTask$WaitAccessControl$runCallAccessControlJob$1.this$0.recvResponseFromAccess;
                        if (!z) {
                            MoveAction action = AccessControlTask.this.getAction();
                            accessControlTask$WaitAccessControl$runCallAccessControlJob$1.L$0 = coroutineScope;
                            accessControlTask$WaitAccessControl$runCallAccessControlJob$1.J$0 = j;
                            accessControlTask$WaitAccessControl$runCallAccessControlJob$1.J$1 = j2;
                            accessControlTask$WaitAccessControl$runCallAccessControlJob$1.label = 3;
                            i3 = 1;
                            if (MoveAction.actionStop$default(action, false, accessControlTask$WaitAccessControl$runCallAccessControlJob$1, 1, null) == obj2) {
                                return obj2;
                            }
                            String str3 = AccessControlTask.this.TAG;
                            Object[] objArr = new Object[i3];
                            objArr[0] = "notice access: " + AccessDoorControlState.CallingDoorFail;
                            Pdlog.m3273d(str3, objArr);
                            AccessControlTask.this.getAccessControlListener().notify(new Function2<AccessDoorListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.AccessControlTask$WaitAccessControl$runCallAccessControlJob$1.1
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(AccessDoorListener accessDoorListener, String str4) {
                                    invoke2(accessDoorListener, str4);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(AccessDoorListener it, String str4) {
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                                    it.informAccessDoorControlState(AccessDoorControlState.CallingDoorFail, AccessControlTask.this.acrossAccessControlId);
                                }
                            });
                            if (CoroutineScopeKt.isActive(coroutineScope)) {
                            }
                            return Unit.INSTANCE;
                        }
                    }
                    i7 = 1;
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        i = AccessControlTask.this.CALL_OVERTIME;
                        if (j2 < i) {
                            String str4 = AccessControlTask.this.TAG;
                            Object[] objArr2 = new Object[i7];
                            objArr2[0] = "call access control id " + AccessControlTask.this.acrossAccessControlId;
                            Pdlog.m3273d(str4, objArr2);
                            if (AccessControlTask.this.acrossAccessControlId != null) {
                                AccConnectionInterface access$getAccessControlClient$p = AccessControlTask.access$getAccessControlClient$p(AccessControlTask.this);
                                String valueOf = String.valueOf(AccessControlTask.this.seq);
                                str = AccessControlTask.this.robotID;
                                String str5 = AccessControlTask.this.acrossAccessControlId;
                                if (str5 == null) {
                                    Intrinsics.throwNpe();
                                }
                                access$getAccessControlClient$p.callOpenDoor(valueOf, str, str5);
                            }
                            accessControlTask$WaitAccessControl$runCallAccessControlJob$1.L$0 = coroutineScope;
                            accessControlTask$WaitAccessControl$runCallAccessControlJob$1.J$0 = j;
                            accessControlTask$WaitAccessControl$runCallAccessControlJob$1.J$1 = j2;
                            accessControlTask$WaitAccessControl$runCallAccessControlJob$1.label = 2;
                            if (DelayKt.delay(500L, accessControlTask$WaitAccessControl$runCallAccessControlJob$1) == obj2) {
                                return obj2;
                            }
                            j2 = SystemClock.elapsedRealtime() - j;
                            i2 = AccessControlTask.this.CALL_ERRTIME;
                            if (j2 >= i2) {
                            }
                            i7 = 1;
                            if (CoroutineScopeKt.isActive(coroutineScope)) {
                            }
                        }
                    }
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                    }
                    return Unit.INSTANCE;
                }
                if (i6 == 3) {
                    long j6 = this.J$1;
                    long j7 = this.J$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj2 = coroutine_suspended;
                    i3 = 1;
                    j = j7;
                    accessControlTask$WaitAccessControl$runCallAccessControlJob$1 = this;
                    j2 = j6;
                    String str32 = AccessControlTask.this.TAG;
                    Object[] objArr3 = new Object[i3];
                    objArr3[0] = "notice access: " + AccessDoorControlState.CallingDoorFail;
                    Pdlog.m3273d(str32, objArr3);
                    AccessControlTask.this.getAccessControlListener().notify(new Function2<AccessDoorListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.AccessControlTask$WaitAccessControl$runCallAccessControlJob$1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(AccessDoorListener accessDoorListener, String str42) {
                            invoke2(accessDoorListener, str42);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(AccessDoorListener it, String str42) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str42, "<anonymous parameter 1>");
                            it.informAccessDoorControlState(AccessDoorControlState.CallingDoorFail, AccessControlTask.this.acrossAccessControlId);
                        }
                    });
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        i4 = AccessControlTask.this.CALL_OVERTIME;
                        if (j2 >= i4) {
                            z2 = accessControlTask$WaitAccessControl$runCallAccessControlJob$1.this$0.recvResponseFromAccess;
                            if (!z2) {
                                Pdlog.m3273d(AccessControlTask.this.TAG, "notice access: " + AccessDoorControlState.CallingDoorOvertime);
                                AccessControlTask.this.getAccessControlListener().notify(new Function2<AccessDoorListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.AccessControlTask$WaitAccessControl$runCallAccessControlJob$1.2
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(AccessDoorListener accessDoorListener, String str6) {
                                        invoke2(accessDoorListener, str6);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(AccessDoorListener it, String str6) {
                                        Intrinsics.checkParameterIsNotNull(it, "it");
                                        Intrinsics.checkParameterIsNotNull(str6, "<anonymous parameter 1>");
                                        it.informAccessDoorControlState(AccessDoorControlState.CallingDoorOvertime, AccessControlTask.this.acrossAccessControlId);
                                    }
                                });
                                j3 = j;
                                if (CoroutineScopeKt.isActive(coroutineScope)) {
                                }
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
                if (i6 != 4) {
                    if (i6 == 5) {
                        long j8 = this.J$1;
                        long j9 = this.J$0;
                        ResultKt.throwOnFailure(obj);
                        accessControlTask$WaitAccessControl$runCallAccessControlJob$1 = this;
                        String str6 = AccessControlTask.this.TAG;
                        Object[] objArr4 = new Object[i7];
                        objArr4[0] = "notice access: " + AccessDoorControlState.CallingDoorFail;
                        Pdlog.m3273d(str6, objArr4);
                        AccessControlTask.this.getAccessControlListener().notify(new Function2<AccessDoorListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.AccessControlTask$WaitAccessControl$runCallAccessControlJob$1.3
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(AccessDoorListener accessDoorListener, String str7) {
                                invoke2(accessDoorListener, str7);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(AccessDoorListener it, String str7) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                                it.informAccessDoorControlState(AccessDoorControlState.CallingDoorFail, AccessControlTask.this.acrossAccessControlId);
                            }
                        });
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                long j10 = this.J$1;
                j3 = this.J$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                accessControlTask$WaitAccessControl$runCallAccessControlJob$1 = this;
                j2 = SystemClock.elapsedRealtime() - j3;
                i5 = AccessControlTask.this.CALL_ERRTIME;
                if (j2 >= i5) {
                    z3 = accessControlTask$WaitAccessControl$runCallAccessControlJob$1.this$0.recvResponseFromAccess;
                    if (!z3) {
                        MoveAction action2 = AccessControlTask.this.getAction();
                        accessControlTask$WaitAccessControl$runCallAccessControlJob$1.L$0 = coroutineScope;
                        accessControlTask$WaitAccessControl$runCallAccessControlJob$1.J$0 = j3;
                        accessControlTask$WaitAccessControl$runCallAccessControlJob$1.J$1 = j2;
                        accessControlTask$WaitAccessControl$runCallAccessControlJob$1.label = 5;
                        i7 = 1;
                        if (MoveAction.actionStop$default(action2, false, accessControlTask$WaitAccessControl$runCallAccessControlJob$1, 1, null) == obj2) {
                            return obj2;
                        }
                        String str62 = AccessControlTask.this.TAG;
                        Object[] objArr42 = new Object[i7];
                        objArr42[0] = "notice access: " + AccessDoorControlState.CallingDoorFail;
                        Pdlog.m3273d(str62, objArr42);
                        AccessControlTask.this.getAccessControlListener().notify(new Function2<AccessDoorListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.AccessControlTask$WaitAccessControl$runCallAccessControlJob$1.3
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(AccessDoorListener accessDoorListener, String str7) {
                                invoke2(accessDoorListener, str7);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(AccessDoorListener it, String str7) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str7, "<anonymous parameter 1>");
                                it.informAccessDoorControlState(AccessDoorControlState.CallingDoorFail, AccessControlTask.this.acrossAccessControlId);
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    if (AccessControlTask.this.acrossAccessControlId != null) {
                        AccConnectionInterface access$getAccessControlClient$p2 = AccessControlTask.access$getAccessControlClient$p(AccessControlTask.this);
                        String valueOf2 = String.valueOf(AccessControlTask.this.seq);
                        str2 = AccessControlTask.this.robotID;
                        String str7 = AccessControlTask.this.acrossAccessControlId;
                        if (str7 == null) {
                            Intrinsics.throwNpe();
                        }
                        access$getAccessControlClient$p2.callOpenDoor(valueOf2, str2, str7);
                    }
                    accessControlTask$WaitAccessControl$runCallAccessControlJob$1.L$0 = coroutineScope;
                    accessControlTask$WaitAccessControl$runCallAccessControlJob$1.J$0 = j3;
                    accessControlTask$WaitAccessControl$runCallAccessControlJob$1.J$1 = j2;
                    accessControlTask$WaitAccessControl$runCallAccessControlJob$1.label = 4;
                    if (DelayKt.delay(500L, accessControlTask$WaitAccessControl$runCallAccessControlJob$1) == obj2) {
                        return obj2;
                    }
                    j2 = SystemClock.elapsedRealtime() - j3;
                    i5 = AccessControlTask.this.CALL_ERRTIME;
                    if (j2 >= i5) {
                    }
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                    }
                }
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        accessControlTask$WaitAccessControl$runCallAccessControlJob$1 = this;
        do {
            job = AccessControlTask.this.accessControlCloseJob;
            if (job != null && job.isActive()) {
                accessControlTask$WaitAccessControl$runCallAccessControlJob$1.L$0 = coroutineScope;
                accessControlTask$WaitAccessControl$runCallAccessControlJob$1.label = 1;
            } else {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                accessControlTask$WaitAccessControl$runCallAccessControlJob$1.this$0.recvResponseFromAccess = false;
                j = elapsedRealtime;
                obj2 = coroutine_suspended;
                j2 = 0;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
                return Unit.INSTANCE;
            }
        } while (DelayKt.delay(50L, accessControlTask$WaitAccessControl$runCallAccessControlJob$1) != coroutine_suspended);
        return coroutine_suspended;
    }
}
