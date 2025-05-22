package com.pudutech.mirsdk.hardware.activity;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.mirsdk.hardware.Encoder;
import com.pudutech.mirsdk.hardware.Gyroscope;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$moveWithRoad$1", m3970f = "HardwareActivity.kt", m3971i = {0}, m3972l = {552}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class HardwareActivity$moveWithRoad$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5961p$;
    final /* synthetic */ HardwareActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareActivity$moveWithRoad$1(HardwareActivity hardwareActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = hardwareActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareActivity$moveWithRoad$1 hardwareActivity$moveWithRoad$1 = new HardwareActivity$moveWithRoad$1(this.this$0, completion);
        hardwareActivity$moveWithRoad$1.f5961p$ = (CoroutineScope) obj;
        return hardwareActivity$moveWithRoad$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareActivity$moveWithRoad$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
    
        r3 = r17.this$0.moveJob;
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
            CoroutineScope coroutineScope = this.f5961p$;
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
        launch$default = BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new C50881(0.2d, 2.0d, 0.4d, null), 3, null);
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
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$moveWithRoad$1$1", m3970f = "HardwareActivity.kt", m3971i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11}, m3972l = {563, 566, 571, 576, 578, 583, 587, 589, 594, 600, TypedValues.Motion.TYPE_ANIMATE_RELATIVE_TO, TypedValues.Motion.TYPE_PATHMOTION_ARC}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "startGyros", "pi2", "$this$launch", "startGyros", "pi2", "$this$launch", "startGyros", "pi2", "$this$launch", "startGyros", "pi2", "$this$launch", "startGyros", "pi2", "$this$launch", "startGyros", "pi2", "startEncoder", "$this$launch", "startGyros", "pi2", "startEncoder", "$this$launch", "startGyros", "pi2", "startEncoder", "$this$launch", "startGyros", "pi2", "startEncoder", "endGyros", "$this$launch", "startGyros", "pi2", "startEncoder", "endGyros", "$this$launch", "startGyros", "pi2", "startEncoder", "endGyros", "$this$launch", "startGyros", "pi2", "startEncoder", "endGyros"}, m3975s = {"L$0", "D$0", "D$1", "L$0", "D$0", "D$1", "L$0", "D$0", "D$1", "L$0", "D$0", "D$1", "L$0", "D$0", "D$1", "L$0", "D$0", "D$1", "D$2", "L$0", "D$0", "D$1", "D$2", "L$0", "D$0", "D$1", "D$2", "L$0", "D$0", "D$1", "D$2", "D$3", "L$0", "D$0", "D$1", "D$2", "D$3", "L$0", "D$0", "D$1", "D$2", "D$3", "L$0", "D$0", "D$1", "D$2", "D$3"})
    /* renamed from: com.pudutech.mirsdk.hardware.activity.HardwareActivity$moveWithRoad$1$1 */
    /* loaded from: classes2.dex */
    public static final class C50881 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ double $ang_speed;
        final /* synthetic */ double $l_line_speed;
        final /* synthetic */ double $r_line_speed;
        double D$0;
        double D$1;
        double D$2;
        double D$3;
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5962p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C50881(double d, double d2, double d3, Continuation continuation) {
            super(2, continuation);
            this.$r_line_speed = d;
            this.$ang_speed = d2;
            this.$l_line_speed = d3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C50881 c50881 = new C50881(this.$r_line_speed, this.$ang_speed, this.$l_line_speed, completion);
            c50881.f5962p$ = (CoroutineScope) obj;
            return c50881;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C50881) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000a. Please report as an issue. */
        /* JADX WARN: Incorrect condition in loop: B:109:0x0127 */
        /* JADX WARN: Incorrect condition in loop: B:62:0x021f */
        /* JADX WARN: Incorrect condition in loop: B:90:0x016f */
        /* JADX WARN: Removed duplicated region for block: B:102:0x01b1  */
        /* JADX WARN: Removed duplicated region for block: B:105:0x01cb A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:110:0x0129  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x03b6  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x03d5 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0337  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0372  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x02d6  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0315  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0290  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x02ad A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:63:0x0221  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0262  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x027e A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:83:0x01dd  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x01f7 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:91:0x0171  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x03e7  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x036d -> B:20:0x0370). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x030f -> B:38:0x0310). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Gyroscope.Data data;
            double z;
            double d;
            HardwareConnection hardwareConnection;
            C50881 c50881;
            Gyroscope.Data data2;
            HardwareConnection hardwareConnection2;
            Gyroscope.Data data3;
            HardwareConnection hardwareConnection3;
            HardwareInterface hardwareInterface;
            HardwareConnection hardwareConnection4;
            HardwareConnection hardwareConnection5;
            HardwareInterface hardwareInterface2;
            Encoder.DoubleWheel doubleWheel;
            double d2;
            double d3;
            double d4;
            Encoder.DoubleWheel doubleWheel2;
            HardwareConnection hardwareConnection6;
            HardwareInterface hardwareInterface3;
            HardwareConnection hardwareConnection7;
            HardwareConnection hardwareConnection8;
            HardwareInterface hardwareInterface4;
            Gyroscope.Data data4;
            double z2;
            double d5;
            double d6;
            double d7;
            Gyroscope.Data data5;
            CoroutineScope coroutineScope2;
            double d8;
            double d9;
            double d10;
            HardwareConnection hardwareConnection9;
            Object obj2;
            Gyroscope.Data data6;
            HardwareConnection hardwareConnection10;
            HardwareConnection hardwareConnection11;
            Object obj3;
            HardwareConnection hardwareConnection12;
            HardwareInterface hardwareInterface5;
            HardwareConnection hardwareConnection13;
            HardwareInterface hardwareInterface6;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = this.f5962p$;
                    data = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    z = data.getZ();
                    d = 6.283185307179586d;
                    hardwareConnection = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    HardwareInterface hardwareInterface7 = hardwareConnection.getInterface();
                    if (hardwareInterface7 != null) {
                        hardwareInterface7.controlWheel(this.$r_line_speed, this.$ang_speed, true);
                    }
                    this.L$0 = coroutineScope;
                    this.D$0 = z;
                    this.D$1 = 6.283185307179586d;
                    this.label = 1;
                    if (DelayKt.delay(50L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    c50881 = this;
                    while (Math.abs(data2.getZ() - z) < d) {
                        hardwareConnection2 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                        HardwareInterface hardwareInterface8 = hardwareConnection2.getInterface();
                        if (hardwareInterface8 != null) {
                            hardwareInterface8.controlWheel(c50881.$r_line_speed, c50881.$ang_speed, true);
                        }
                        c50881.L$0 = coroutineScope;
                        c50881.D$0 = z;
                        c50881.D$1 = d;
                        c50881.label = 2;
                        if (DelayKt.delay(50L, c50881) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    while (Math.abs(data3.getZ() - z) > 0.05d) {
                        hardwareConnection4 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                        HardwareInterface hardwareInterface9 = hardwareConnection4.getInterface();
                        if (hardwareInterface9 != null) {
                            hardwareInterface9.controlWheel(c50881.$r_line_speed, -c50881.$ang_speed, true);
                        }
                        c50881.L$0 = coroutineScope;
                        c50881.D$0 = z;
                        c50881.D$1 = d;
                        c50881.label = 3;
                        if (DelayKt.delay(50L, c50881) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    hardwareConnection3 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface = hardwareConnection3.getInterface();
                    if (hardwareInterface != null) {
                        hardwareInterface.controlWheel(0.0d, 0.0d, true);
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = z;
                    c50881.D$1 = d;
                    c50881.label = 4;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    hardwareConnection5 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface2 = hardwareConnection5.getInterface();
                    if (hardwareInterface2 != null) {
                        hardwareInterface2.controlWheel(0.0d, 0.0d, true);
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = z;
                    c50881.D$1 = d;
                    c50881.label = 5;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    doubleWheel = HardwareActivity$moveWithRoad$1.this.this$0.encoderAccumulate;
                    double left = doubleWheel.getLeft();
                    d2 = z;
                    d3 = d;
                    d4 = left;
                    while (Math.abs(doubleWheel2.getLeft() - d4) < 1.0d) {
                        hardwareConnection7 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                        HardwareInterface hardwareInterface10 = hardwareConnection7.getInterface();
                        if (hardwareInterface10 != null) {
                            hardwareInterface10.controlWheel(c50881.$l_line_speed, 0.0d, true);
                        }
                        c50881.L$0 = coroutineScope;
                        c50881.D$0 = d2;
                        c50881.D$1 = d3;
                        c50881.D$2 = d4;
                        c50881.label = 6;
                        if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    hardwareConnection6 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface3 = hardwareConnection6.getInterface();
                    if (hardwareInterface3 != null) {
                        hardwareInterface3.controlWheel(0.0d, 0.0d, true);
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 7;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    hardwareConnection8 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface4 = hardwareConnection8.getInterface();
                    if (hardwareInterface4 != null) {
                        hardwareInterface4.controlWheel(0.0d, 0.0d, true);
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 8;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    data4 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    double d11 = d4;
                    z2 = data4.getZ();
                    d5 = d2;
                    d6 = d3;
                    d7 = d11;
                    data5 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    if (Math.abs(data5.getZ() - z2) >= d6) {
                        hardwareConnection9 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                        HardwareInterface hardwareInterface11 = hardwareConnection9.getInterface();
                        if (hardwareInterface11 != null) {
                            obj2 = coroutine_suspended;
                            hardwareInterface11.controlWheel(c50881.$r_line_speed, -c50881.$ang_speed, true);
                        } else {
                            obj2 = coroutine_suspended;
                        }
                        c50881.L$0 = coroutineScope;
                        c50881.D$0 = d5;
                        c50881.D$1 = d6;
                        c50881.D$2 = d7;
                        c50881.D$3 = z2;
                        c50881.label = 9;
                        if (DelayKt.delay(50L, c50881) == obj2) {
                            return obj2;
                        }
                        coroutine_suspended = obj2;
                        data5 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                        if (Math.abs(data5.getZ() - z2) >= d6) {
                            coroutineScope2 = coroutineScope;
                            d8 = d7;
                            d9 = d6;
                            d10 = z2;
                            data6 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                            if (Math.abs(data6.getZ() - d5) > 0.05d) {
                                hardwareConnection11 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                                HardwareInterface hardwareInterface12 = hardwareConnection11.getInterface();
                                if (hardwareInterface12 != null) {
                                    obj3 = coroutine_suspended;
                                    hardwareInterface12.controlWheel(c50881.$r_line_speed, c50881.$ang_speed, true);
                                } else {
                                    obj3 = coroutine_suspended;
                                }
                                c50881.L$0 = coroutineScope2;
                                c50881.D$0 = d5;
                                c50881.D$1 = d9;
                                c50881.D$2 = d8;
                                c50881.D$3 = d10;
                                c50881.label = 10;
                                if (DelayKt.delay(50L, c50881) == obj3) {
                                    return obj3;
                                }
                                coroutine_suspended = obj3;
                                data6 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                                if (Math.abs(data6.getZ() - d5) > 0.05d) {
                                    Object obj4 = coroutine_suspended;
                                    hardwareConnection10 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                                    HardwareInterface hardwareInterface13 = hardwareConnection10.getInterface();
                                    if (hardwareInterface13 != null) {
                                        hardwareInterface13.controlWheel(0.0d, 0.0d, true);
                                    }
                                    c50881.L$0 = coroutineScope2;
                                    c50881.D$0 = d5;
                                    c50881.D$1 = d9;
                                    c50881.D$2 = d8;
                                    c50881.D$3 = d10;
                                    c50881.label = 11;
                                    if (DelayKt.delay(100L, c50881) == obj4) {
                                        return obj4;
                                    }
                                    coroutine_suspended = obj4;
                                    hardwareConnection12 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                                    hardwareInterface5 = hardwareConnection12.getInterface();
                                    if (hardwareInterface5 != null) {
                                        hardwareInterface5.controlWheel(0.0d, 0.0d, true);
                                    }
                                    c50881.L$0 = coroutineScope2;
                                    c50881.D$0 = d5;
                                    c50881.D$1 = d9;
                                    c50881.D$2 = d8;
                                    c50881.D$3 = d10;
                                    c50881.label = 12;
                                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    hardwareConnection13 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                                    hardwareInterface6 = hardwareConnection13.getInterface();
                                    if (hardwareInterface6 != null) {
                                        hardwareInterface6.controlWheel(0.0d, 0.0d, false);
                                    }
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                    }
                    break;
                case 1:
                case 2:
                    d = this.D$1;
                    z = this.D$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    c50881 = this;
                    while (Math.abs(data2.getZ() - z) < d) {
                    }
                    while (Math.abs(data3.getZ() - z) > 0.05d) {
                    }
                    hardwareConnection3 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface = hardwareConnection3.getInterface();
                    if (hardwareInterface != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = z;
                    c50881.D$1 = d;
                    c50881.label = 4;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    hardwareConnection5 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface2 = hardwareConnection5.getInterface();
                    if (hardwareInterface2 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = z;
                    c50881.D$1 = d;
                    c50881.label = 5;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    doubleWheel = HardwareActivity$moveWithRoad$1.this.this$0.encoderAccumulate;
                    double left2 = doubleWheel.getLeft();
                    d2 = z;
                    d3 = d;
                    d4 = left2;
                    while (Math.abs(doubleWheel2.getLeft() - d4) < 1.0d) {
                    }
                    hardwareConnection6 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface3 = hardwareConnection6.getInterface();
                    if (hardwareInterface3 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 7;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    hardwareConnection8 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface4 = hardwareConnection8.getInterface();
                    if (hardwareInterface4 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 8;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    data4 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    double d112 = d4;
                    z2 = data4.getZ();
                    d5 = d2;
                    d6 = d3;
                    d7 = d112;
                    data5 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    if (Math.abs(data5.getZ() - z2) >= d6) {
                    }
                    break;
                case 3:
                    d = this.D$1;
                    z = this.D$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    c50881 = this;
                    while (Math.abs(data3.getZ() - z) > 0.05d) {
                    }
                    hardwareConnection3 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface = hardwareConnection3.getInterface();
                    if (hardwareInterface != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = z;
                    c50881.D$1 = d;
                    c50881.label = 4;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    hardwareConnection5 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface2 = hardwareConnection5.getInterface();
                    if (hardwareInterface2 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = z;
                    c50881.D$1 = d;
                    c50881.label = 5;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    doubleWheel = HardwareActivity$moveWithRoad$1.this.this$0.encoderAccumulate;
                    double left22 = doubleWheel.getLeft();
                    d2 = z;
                    d3 = d;
                    d4 = left22;
                    while (Math.abs(doubleWheel2.getLeft() - d4) < 1.0d) {
                    }
                    hardwareConnection6 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface3 = hardwareConnection6.getInterface();
                    if (hardwareInterface3 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 7;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    hardwareConnection8 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface4 = hardwareConnection8.getInterface();
                    if (hardwareInterface4 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 8;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    data4 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    double d1122 = d4;
                    z2 = data4.getZ();
                    d5 = d2;
                    d6 = d3;
                    d7 = d1122;
                    data5 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    if (Math.abs(data5.getZ() - z2) >= d6) {
                    }
                    break;
                case 4:
                    d = this.D$1;
                    z = this.D$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    c50881 = this;
                    hardwareConnection5 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface2 = hardwareConnection5.getInterface();
                    if (hardwareInterface2 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = z;
                    c50881.D$1 = d;
                    c50881.label = 5;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    doubleWheel = HardwareActivity$moveWithRoad$1.this.this$0.encoderAccumulate;
                    double left222 = doubleWheel.getLeft();
                    d2 = z;
                    d3 = d;
                    d4 = left222;
                    while (Math.abs(doubleWheel2.getLeft() - d4) < 1.0d) {
                    }
                    hardwareConnection6 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface3 = hardwareConnection6.getInterface();
                    if (hardwareInterface3 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 7;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    hardwareConnection8 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface4 = hardwareConnection8.getInterface();
                    if (hardwareInterface4 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 8;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    data4 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    double d11222 = d4;
                    z2 = data4.getZ();
                    d5 = d2;
                    d6 = d3;
                    d7 = d11222;
                    data5 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    if (Math.abs(data5.getZ() - z2) >= d6) {
                    }
                    break;
                case 5:
                    d = this.D$1;
                    z = this.D$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    c50881 = this;
                    doubleWheel = HardwareActivity$moveWithRoad$1.this.this$0.encoderAccumulate;
                    double left2222 = doubleWheel.getLeft();
                    d2 = z;
                    d3 = d;
                    d4 = left2222;
                    while (Math.abs(doubleWheel2.getLeft() - d4) < 1.0d) {
                    }
                    hardwareConnection6 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface3 = hardwareConnection6.getInterface();
                    if (hardwareInterface3 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 7;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    hardwareConnection8 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface4 = hardwareConnection8.getInterface();
                    if (hardwareInterface4 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 8;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    data4 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    double d112222 = d4;
                    z2 = data4.getZ();
                    d5 = d2;
                    d6 = d3;
                    d7 = d112222;
                    data5 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    if (Math.abs(data5.getZ() - z2) >= d6) {
                    }
                    break;
                case 6:
                    d4 = this.D$2;
                    d3 = this.D$1;
                    double d12 = this.D$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    d2 = d12;
                    c50881 = this;
                    while (Math.abs(doubleWheel2.getLeft() - d4) < 1.0d) {
                    }
                    hardwareConnection6 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface3 = hardwareConnection6.getInterface();
                    if (hardwareInterface3 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 7;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    hardwareConnection8 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface4 = hardwareConnection8.getInterface();
                    if (hardwareInterface4 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 8;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    data4 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    double d1122222 = d4;
                    z2 = data4.getZ();
                    d5 = d2;
                    d6 = d3;
                    d7 = d1122222;
                    data5 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    if (Math.abs(data5.getZ() - z2) >= d6) {
                    }
                    break;
                case 7:
                    d4 = this.D$2;
                    d3 = this.D$1;
                    double d13 = this.D$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    d2 = d13;
                    c50881 = this;
                    hardwareConnection8 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface4 = hardwareConnection8.getInterface();
                    if (hardwareInterface4 != null) {
                    }
                    c50881.L$0 = coroutineScope;
                    c50881.D$0 = d2;
                    c50881.D$1 = d3;
                    c50881.D$2 = d4;
                    c50881.label = 8;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    data4 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    double d11222222 = d4;
                    z2 = data4.getZ();
                    d5 = d2;
                    d6 = d3;
                    d7 = d11222222;
                    data5 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    if (Math.abs(data5.getZ() - z2) >= d6) {
                    }
                    break;
                case 8:
                    d4 = this.D$2;
                    d3 = this.D$1;
                    double d14 = this.D$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    d2 = d14;
                    c50881 = this;
                    data4 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    double d112222222 = d4;
                    z2 = data4.getZ();
                    d5 = d2;
                    d6 = d3;
                    d7 = d112222222;
                    data5 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    if (Math.abs(data5.getZ() - z2) >= d6) {
                    }
                    break;
                case 9:
                    z2 = this.D$3;
                    d7 = this.D$2;
                    double d15 = this.D$1;
                    d5 = this.D$0;
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    d6 = d15;
                    c50881 = this;
                    data5 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    if (Math.abs(data5.getZ() - z2) >= d6) {
                    }
                    break;
                case 10:
                    double d16 = this.D$3;
                    double d17 = this.D$2;
                    double d18 = this.D$1;
                    double d19 = this.D$0;
                    CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    c50881 = this;
                    obj3 = coroutine_suspended;
                    d8 = d17;
                    coroutineScope2 = coroutineScope3;
                    d10 = d16;
                    d9 = d18;
                    d5 = d19;
                    coroutine_suspended = obj3;
                    data6 = HardwareActivity$moveWithRoad$1.this.this$0.gyroAccumulate;
                    if (Math.abs(data6.getZ() - d5) > 0.05d) {
                    }
                    break;
                case 11:
                    d10 = this.D$3;
                    d8 = this.D$2;
                    d9 = this.D$1;
                    double d20 = this.D$0;
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    d5 = d20;
                    c50881 = this;
                    hardwareConnection12 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface5 = hardwareConnection12.getInterface();
                    if (hardwareInterface5 != null) {
                    }
                    c50881.L$0 = coroutineScope2;
                    c50881.D$0 = d5;
                    c50881.D$1 = d9;
                    c50881.D$2 = d8;
                    c50881.D$3 = d10;
                    c50881.label = 12;
                    if (DelayKt.delay(100L, c50881) == coroutine_suspended) {
                    }
                    hardwareConnection13 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface6 = hardwareConnection13.getInterface();
                    if (hardwareInterface6 != null) {
                    }
                    return Unit.INSTANCE;
                case 12:
                    ResultKt.throwOnFailure(obj);
                    c50881 = this;
                    hardwareConnection13 = HardwareActivity$moveWithRoad$1.this.this$0.hardware;
                    hardwareInterface6 = hardwareConnection13.getInterface();
                    if (hardwareInterface6 != null) {
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
