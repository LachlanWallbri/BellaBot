package com.pudutech.mirsdk.movetask;

import com.pudutech.mirsdk.FunctionScope;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AccessControlTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.AccessControlTask$MoveAcrossAccessControl$switch$1", m3970f = "AccessControlTask.kt", m3971i = {0}, m3972l = {661}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class AccessControlTask$MoveAcrossAccessControl$switch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6303p$;
    final /* synthetic */ AccessControlTask.MoveAcrossAccessControl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlTask$MoveAcrossAccessControl$switch$1(AccessControlTask.MoveAcrossAccessControl moveAcrossAccessControl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAcrossAccessControl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AccessControlTask$MoveAcrossAccessControl$switch$1 accessControlTask$MoveAcrossAccessControl$switch$1 = new AccessControlTask$MoveAcrossAccessControl$switch$1(this.this$0, completion);
        accessControlTask$MoveAcrossAccessControl$switch$1.f6303p$ = (CoroutineScope) obj;
        return accessControlTask$MoveAcrossAccessControl$switch$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AccessControlTask$MoveAcrossAccessControl$switch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Job job;
        Job launch$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6303p$;
            job = AccessControlTask.this.accessControlJob;
            if (job != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (AccessControlTask.this.acrossAccessControlId != null) {
            AccessControlTask accessControlTask = AccessControlTask.this;
            launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C52781(null), 3, null);
            accessControlTask.accessControlCloseJob = launch$default;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccessControlTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.AccessControlTask$MoveAcrossAccessControl$switch$1$1", m3970f = "AccessControlTask.kt", m3971i = {0, 0}, m3972l = {672}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "sendTime"}, m3975s = {"L$0", "I$0"})
    /* renamed from: com.pudutech.mirsdk.movetask.AccessControlTask$MoveAcrossAccessControl$switch$1$1 */
    /* loaded from: classes6.dex */
    public static final class C52781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6304p$;

        C52781(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C52781 c52781 = new C52781(completion);
            c52781.f6304p$ = (CoroutineScope) obj;
            return c52781;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C52781) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x003d  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0081 -> B:5:0x0084). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            int i;
            CoroutineScope coroutineScope;
            C52781 c52781;
            String str;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = this.f6304p$;
                i = 0;
                AccessControlTask accessControlTask = AccessControlTask.this;
                accessControlTask.seq++;
                int unused = accessControlTask.seq;
                coroutineScope = coroutineScope2;
                c52781 = this;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
                return Unit.INSTANCE;
            }
            if (i2 == 1) {
                i = this.I$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c52781 = this;
                i++;
                if (!CoroutineScopeKt.isActive(coroutineScope) && i < 20) {
                    AccConnectionInterface access$getAccessControlClient$p = AccessControlTask.access$getAccessControlClient$p(AccessControlTask.this);
                    String valueOf = String.valueOf(AccessControlTask.this.seq);
                    str = AccessControlTask.this.robotID;
                    String str2 = AccessControlTask.this.acrossAccessControlId;
                    if (str2 == null) {
                        Intrinsics.throwNpe();
                    }
                    access$getAccessControlClient$p.callCloseDoor(valueOf, str, str2);
                    c52781.L$0 = coroutineScope;
                    c52781.I$0 = i;
                    c52781.label = 1;
                    if (DelayKt.delay(200L, c52781) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    i++;
                    if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    }
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
