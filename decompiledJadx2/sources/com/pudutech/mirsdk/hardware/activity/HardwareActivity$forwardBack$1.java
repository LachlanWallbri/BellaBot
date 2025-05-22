package com.pudutech.mirsdk.hardware.activity;

import android.os.SystemClock;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$forwardBack$1", m3970f = "HardwareActivity.kt", m3971i = {0}, m3972l = {618}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class HardwareActivity$forwardBack$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $loopTime;
    final /* synthetic */ double $speed;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5957p$;
    final /* synthetic */ HardwareActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareActivity$forwardBack$1(HardwareActivity hardwareActivity, double d, double d2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = hardwareActivity;
        this.$speed = d;
        this.$loopTime = d2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareActivity$forwardBack$1 hardwareActivity$forwardBack$1 = new HardwareActivity$forwardBack$1(this.this$0, this.$speed, this.$loopTime, completion);
        hardwareActivity$forwardBack$1.f5957p$ = (CoroutineScope) obj;
        return hardwareActivity$forwardBack$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareActivity$forwardBack$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            CoroutineScope coroutineScope = this.f5957p$;
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
        launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new C50861(null), 3, null);
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
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$forwardBack$1$1", m3970f = "HardwareActivity.kt", m3971i = {0, 0, 0, 0, 1, 1, 1, 2, 2, 2}, m3972l = {630, 633, 635}, m3973m = "invokeSuspend", m3974n = {"$this$launch", AUserTrack.UTKEY_START_TIME, "realSpeed", "curTime", "$this$launch", AUserTrack.UTKEY_START_TIME, "realSpeed", "$this$launch", AUserTrack.UTKEY_START_TIME, "realSpeed"}, m3975s = {"L$0", "J$0", "D$0", "J$1", "L$0", "J$0", "D$0", "L$0", "J$0", "D$0"})
    /* renamed from: com.pudutech.mirsdk.hardware.activity.HardwareActivity$forwardBack$1$1 */
    /* loaded from: classes2.dex */
    public static final class C50861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        double D$0;
        long J$0;
        long J$1;
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5958p$;

        C50861(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C50861 c50861 = new C50861(completion);
            c50861.f5958p$ = (CoroutineScope) obj;
            return c50861;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C50861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x00f0  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x010a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00ae  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x011c  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00a9 -> B:23:0x00ab). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            long elapsedRealtime;
            double d;
            C50861 c50861;
            HardwareConnection hardwareConnection;
            long j;
            double d2;
            double d3;
            long j2;
            HardwareConnection hardwareConnection2;
            long j3;
            HardwareConnection hardwareConnection3;
            HardwareInterface hardwareInterface;
            HardwareConnection hardwareConnection4;
            HardwareInterface hardwareInterface2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5958p$;
                elapsedRealtime = SystemClock.elapsedRealtime();
                d = HardwareActivity$forwardBack$1.this.$speed;
                c50861 = this;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        if (i == 3) {
                            ResultKt.throwOnFailure(obj);
                            c50861 = this;
                            hardwareConnection4 = HardwareActivity$forwardBack$1.this.this$0.hardware;
                            hardwareInterface2 = hardwareConnection4.getInterface();
                            if (hardwareInterface2 != null) {
                                hardwareInterface2.controlWheel(0.0d, 0.0d, false);
                            }
                            HardwareActivity$forwardBack$1.this.this$0.moveJob = (Job) null;
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    d2 = this.D$0;
                    j = this.J$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    c50861 = this;
                    hardwareConnection3 = HardwareActivity$forwardBack$1.this.this$0.hardware;
                    hardwareInterface = hardwareConnection3.getInterface();
                    if (hardwareInterface != null) {
                        hardwareInterface.controlWheel(0.0d, 0.0d, true);
                    }
                    c50861.L$0 = coroutineScope;
                    c50861.J$0 = j;
                    c50861.D$0 = d2;
                    c50861.label = 3;
                    if (DelayKt.delay(100L, c50861) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    hardwareConnection4 = HardwareActivity$forwardBack$1.this.this$0.hardware;
                    hardwareInterface2 = hardwareConnection4.getInterface();
                    if (hardwareInterface2 != null) {
                    }
                    HardwareActivity$forwardBack$1.this.this$0.moveJob = (Job) null;
                    return Unit.INSTANCE;
                }
                double d4 = this.D$0;
                long j4 = this.J$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c50861 = this;
                elapsedRealtime = j4;
                d = d4;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    long elapsedRealtime2 = SystemClock.elapsedRealtime();
                    long j5 = elapsedRealtime;
                    if (elapsedRealtime2 - elapsedRealtime > HardwareActivity$forwardBack$1.this.$loopTime * 1000) {
                        d3 = -d;
                        j2 = elapsedRealtime2;
                    } else {
                        d3 = d;
                        j2 = j5;
                    }
                    hardwareConnection2 = HardwareActivity$forwardBack$1.this.this$0.hardware;
                    HardwareInterface hardwareInterface3 = hardwareConnection2.getInterface();
                    if (hardwareInterface3 != null) {
                        j3 = j2;
                        hardwareInterface3.controlWheel(d3, 0.0d, true);
                    } else {
                        j3 = j2;
                    }
                    c50861.L$0 = coroutineScope;
                    long j6 = j3;
                    c50861.J$0 = j6;
                    c50861.D$0 = d3;
                    c50861.J$1 = elapsedRealtime2;
                    c50861.label = 1;
                    if (DelayKt.delay(100L, c50861) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    d = d3;
                    elapsedRealtime = j6;
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        long j7 = elapsedRealtime;
                        hardwareConnection = HardwareActivity$forwardBack$1.this.this$0.hardware;
                        HardwareInterface hardwareInterface4 = hardwareConnection.getInterface();
                        if (hardwareInterface4 != null) {
                            hardwareInterface4.controlWheel(0.0d, 0.0d, true);
                        }
                        c50861.L$0 = coroutineScope;
                        j = j7;
                        c50861.J$0 = j;
                        c50861.D$0 = d;
                        c50861.label = 2;
                        if (DelayKt.delay(100L, c50861) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        d2 = d;
                        hardwareConnection3 = HardwareActivity$forwardBack$1.this.this$0.hardware;
                        hardwareInterface = hardwareConnection3.getInterface();
                        if (hardwareInterface != null) {
                        }
                        c50861.L$0 = coroutineScope;
                        c50861.J$0 = j;
                        c50861.D$0 = d2;
                        c50861.label = 3;
                        if (DelayKt.delay(100L, c50861) == coroutine_suspended) {
                        }
                        hardwareConnection4 = HardwareActivity$forwardBack$1.this.this$0.hardware;
                        hardwareInterface2 = hardwareConnection4.getInterface();
                        if (hardwareInterface2 != null) {
                        }
                        HardwareActivity$forwardBack$1.this.this$0.moveJob = (Job) null;
                        return Unit.INSTANCE;
                    }
                }
            }
        }
    }
}
