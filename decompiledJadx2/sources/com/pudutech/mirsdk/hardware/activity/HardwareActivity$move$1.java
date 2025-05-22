package com.pudutech.mirsdk.hardware.activity;

import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.HardwareScopeKt;
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
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$move$1", m3970f = "HardwareActivity.kt", m3971i = {0}, m3972l = {531}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class HardwareActivity$move$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $angular;
    final /* synthetic */ double $line;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5959p$;
    final /* synthetic */ HardwareActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareActivity$move$1(HardwareActivity hardwareActivity, double d, double d2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = hardwareActivity;
        this.$line = d;
        this.$angular = d2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareActivity$move$1 hardwareActivity$move$1 = new HardwareActivity$move$1(this.this$0, this.$line, this.$angular, completion);
        hardwareActivity$move$1.f5959p$ = (CoroutineScope) obj;
        return hardwareActivity$move$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareActivity$move$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0028, code lost:
    
        r1 = r6.this$0.moveJob;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Job job;
        Job job2;
        Job launch$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5959p$;
            job = this.this$0.moveJob;
            if (job != null && job2 != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        HardwareActivity hardwareActivity = this.this$0;
        launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new C50871(null), 3, null);
        hardwareActivity.moveJob = launch$default;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$move$1$1", m3970f = "HardwareActivity.kt", m3971i = {0, 1, 2}, m3972l = {536, 539, 541}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0"})
    /* renamed from: com.pudutech.mirsdk.hardware.activity.HardwareActivity$move$1$1 */
    /* loaded from: classes2.dex */
    public static final class C50871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5960p$;

        C50871(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C50871 c50871 = new C50871(completion);
            c50871.f5960p$ = (CoroutineScope) obj;
            return c50871;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C50871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x00a0  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00b2 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x00c4  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            C50871 c50871;
            HardwareConnection hardwareConnection;
            HardwareConnection hardwareConnection2;
            HardwareConnection hardwareConnection3;
            HardwareInterface hardwareInterface;
            HardwareConnection hardwareConnection4;
            HardwareInterface hardwareInterface2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5960p$;
            } else {
                if (i != 1) {
                    if (i != 2) {
                        if (i == 3) {
                            ResultKt.throwOnFailure(obj);
                            c50871 = this;
                            hardwareConnection4 = HardwareActivity$move$1.this.this$0.hardware;
                            hardwareInterface2 = hardwareConnection4.getInterface();
                            if (hardwareInterface2 != null) {
                                hardwareInterface2.controlWheel(0.0d, 0.0d, false);
                            }
                            HardwareActivity$move$1.this.this$0.moveJob = (Job) null;
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    c50871 = this;
                    hardwareConnection3 = HardwareActivity$move$1.this.this$0.hardware;
                    hardwareInterface = hardwareConnection3.getInterface();
                    if (hardwareInterface != null) {
                        hardwareInterface.controlWheel(0.0d, 0.0d, true);
                    }
                    c50871.L$0 = coroutineScope;
                    c50871.label = 3;
                    if (DelayKt.delay(100L, c50871) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    hardwareConnection4 = HardwareActivity$move$1.this.this$0.hardware;
                    hardwareInterface2 = hardwareConnection4.getInterface();
                    if (hardwareInterface2 != null) {
                    }
                    HardwareActivity$move$1.this.this$0.moveJob = (Job) null;
                    return Unit.INSTANCE;
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            c50871 = this;
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                hardwareConnection2 = HardwareActivity$move$1.this.this$0.hardware;
                HardwareInterface hardwareInterface3 = hardwareConnection2.getInterface();
                if (hardwareInterface3 != null) {
                    hardwareInterface3.controlWheel(HardwareActivity$move$1.this.$line, HardwareActivity$move$1.this.$angular, true);
                }
                c50871.L$0 = coroutineScope;
                c50871.label = 1;
                if (DelayKt.delay(100L, c50871) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            hardwareConnection = HardwareActivity$move$1.this.this$0.hardware;
            HardwareInterface hardwareInterface4 = hardwareConnection.getInterface();
            if (hardwareInterface4 != null) {
                hardwareInterface4.controlWheel(0.0d, 0.0d, true);
            }
            c50871.L$0 = coroutineScope;
            c50871.label = 2;
            if (DelayKt.delay(100L, c50871) == coroutine_suspended) {
                return coroutine_suspended;
            }
            hardwareConnection3 = HardwareActivity$move$1.this.this$0.hardware;
            hardwareInterface = hardwareConnection3.getInterface();
            if (hardwareInterface != null) {
            }
            c50871.L$0 = coroutineScope;
            c50871.label = 3;
            if (DelayKt.delay(100L, c50871) == coroutine_suspended) {
            }
            hardwareConnection4 = HardwareActivity$move$1.this.this$0.hardware;
            hardwareInterface2 = hardwareConnection4.getInterface();
            if (hardwareInterface2 != null) {
            }
            HardwareActivity$move$1.this.this$0.moveJob = (Job) null;
            return Unit.INSTANCE;
        }
    }
}
