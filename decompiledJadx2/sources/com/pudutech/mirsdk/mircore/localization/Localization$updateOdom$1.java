package com.pudutech.mirsdk.mircore.localization;

import android.os.SystemClock;
import com.pudu.slamware_localization.SlamwareLocalization;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.LocalizationListener;
import com.pudutech.mirsdk.mircore.localization.Localization;
import com.pudutech.mirsdk.mircore.localization.laserLocateNative.LaserLocateNative;
import com.pudutech.mirsdk.mircore.localization.markerLocateNative.MarkerLocateNative;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Localization.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.localization.Localization$updateOdom$1", m3970f = "Localization.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class Localization$updateOdom$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $angle_speed;
    final /* synthetic */ double $interval;
    final /* synthetic */ double $line_speed;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6219p$;
    final /* synthetic */ Localization this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Localization$updateOdom$1(Localization localization, double d, double d2, double d3, Continuation continuation) {
        super(2, continuation);
        this.this$0 = localization;
        this.$line_speed = d;
        this.$angle_speed = d2;
        this.$interval = d3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        Localization$updateOdom$1 localization$updateOdom$1 = new Localization$updateOdom$1(this.this$0, this.$line_speed, this.$angle_speed, this.$interval, completion);
        localization$updateOdom$1.f6219p$ = (CoroutineScope) obj;
        return localization$updateOdom$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Localization$updateOdom$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        OdometryCenter odometryCenter;
        ThreadSafeListener threadSafeListener;
        SlamwareLocalization slamwareLocalization;
        Vector3d position;
        String str2;
        Vector3d vector3d;
        Vector3d vector3d2;
        Vector3d vector3d3;
        Vector3d vector3d4;
        Vector3d vector3d5;
        ThreadSafeListener threadSafeListener2;
        Vector3d vector3d6;
        Vector3d vector3d7;
        Vector3d vector3d8;
        Vector3d vector3d9;
        OdometryCenter odometryCenter2;
        ThreadSafeListener threadSafeListener3;
        long j;
        Localization$LocalizationScope$1 localization$LocalizationScope$1;
        Localization$LocalizationScope$1 localization$LocalizationScope$12;
        String str3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6219p$;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "start update odom");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Localization localization = this.this$0;
        odometryCenter = localization.odometry;
        localization.speeds = odometryCenter.updateSpeed(this.$line_speed, this.$angle_speed, this.$interval);
        threadSafeListener = this.this$0.speedListeners;
        threadSafeListener.notify(new Function2<Function1<? super Vector2d, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.localization.Localization$updateOdom$1.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Vector2d, ? extends Unit> function1, String str4) {
                invoke2((Function1<? super Vector2d, Unit>) function1, str4);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super Vector2d, Unit> it, String str4) {
                Vector2d vector2d;
                Vector2d vector2d2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                vector2d = Localization$updateOdom$1.this.this$0.speeds;
                double x = vector2d.getX();
                vector2d2 = Localization$updateOdom$1.this.this$0.speeds;
                it.invoke(new Vector2d(x, vector2d2.getY()));
            }
        });
        Localization localization2 = this.this$0;
        int i = Localization.WhenMappings.$EnumSwitchMapping$32[Localization.access$getLocateCase$p(localization2).ordinal()];
        if (i == 1) {
            slamwareLocalization = this.this$0.slamwareLocation;
            position = slamwareLocalization.getPosition();
        } else if (i == 2) {
            position = MarkerLocateNative.INSTANCE.getRobotPosition();
        } else if (i == 3 || i == 4) {
            position = LaserLocateNative.INSTANCE.getRobotPosition();
        } else {
            position = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        }
        localization2.position = position;
        str2 = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("get pose (");
        vector3d = this.this$0.position;
        sb.append(vector3d.getX());
        sb.append(", ");
        vector3d2 = this.this$0.position;
        sb.append(vector3d2.getY());
        sb.append(", ");
        vector3d3 = this.this$0.position;
        sb.append(vector3d3.getZ());
        sb.append(')');
        Pdlog.m3273d(str2, sb.toString());
        Localization localization3 = this.this$0;
        vector3d4 = localization3.position;
        double cos = Math.cos(vector3d4.getZ());
        vector3d5 = this.this$0.position;
        localization3.orientation = new Vector3d(cos, Math.sin(vector3d5.getZ()), 0.0d);
        threadSafeListener2 = this.this$0.poseListeners;
        threadSafeListener2.notify(new Function2<Function2<? super Vector3d, ? super Vector3d, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.localization.Localization$updateOdom$1.2
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Vector3d, ? super Vector3d, ? extends Unit> function2, String str4) {
                invoke2((Function2<? super Vector3d, ? super Vector3d, Unit>) function2, str4);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super Vector3d, ? super Vector3d, Unit> it, String str4) {
                Vector3d vector3d10;
                Vector3d vector3d11;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                vector3d10 = Localization$updateOdom$1.this.this$0.position;
                vector3d11 = Localization$updateOdom$1.this.this$0.orientation;
                it.invoke(vector3d10, vector3d11);
            }
        });
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("{\"dir\":{\"x\":");
        vector3d6 = this.this$0.orientation;
        sb2.append(vector3d6.getX());
        sb2.append(",\"y\":");
        vector3d7 = this.this$0.orientation;
        sb2.append(vector3d7.getY());
        sb2.append("},\"pos\":{\"x\":");
        vector3d8 = this.this$0.position;
        sb2.append(vector3d8.getX());
        sb2.append(",\"y\":");
        vector3d9 = this.this$0.position;
        sb2.append(vector3d9.getY());
        sb2.append("},\"type\":\"pose\"}");
        Pdlog.m3273d("debug_bridge", sb2.toString());
        Localization localization4 = this.this$0;
        odometryCenter2 = localization4.odometry;
        localization4.odom = odometryCenter2.getOdom_data();
        threadSafeListener3 = this.this$0.odomListeners;
        threadSafeListener3.notify(new Function2<Function1<? super Vector3d, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.localization.Localization$updateOdom$1.3
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Vector3d, ? extends Unit> function1, String str4) {
                invoke2((Function1<? super Vector3d, Unit>) function1, str4);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super Vector3d, Unit> it, String str4) {
                Vector3d vector3d10;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                vector3d10 = Localization$updateOdom$1.this.this$0.odom;
                it.invoke(vector3d10);
            }
        });
        j = this.this$0.lastBroadTime;
        if (elapsedRealtime2 - j > 100) {
            localization$LocalizationScope$1 = this.this$0.LocalizationScope;
            Localization$LocalizationScope$1 localization$LocalizationScope$13 = localization$LocalizationScope$1;
            localization$LocalizationScope$12 = this.this$0.LocalizationScope;
            BuildersKt__Builders_commonKt.launch$default(localization$LocalizationScope$13, localization$LocalizationScope$12.getBroadcastWorker(), null, new C52294(elapsedRealtime2, null), 2, null);
            long elapsedRealtime3 = SystemClock.elapsedRealtime();
            str3 = this.this$0.TAG;
            Pdlog.m3273d(str3, "update odom and send speed and location info use time " + (elapsedRealtime3 - elapsedRealtime) + "ms");
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: Localization.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.localization.Localization$updateOdom$1$4", m3970f = "Localization.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.mircore.localization.Localization$updateOdom$1$4 */
    /* loaded from: classes6.dex */
    public static final class C52294 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $current_time;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6220p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C52294(long j, Continuation continuation) {
            super(2, continuation);
            this.$current_time = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C52294 c52294 = new C52294(this.$current_time, completion);
            c52294.f6220p$ = (CoroutineScope) obj;
            return c52294;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C52294) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Vector3d vector3d;
            Vector3d vector3d2;
            Vector3d vector3d3;
            Vector3d vector3d4;
            Vector3d vector3d5;
            Vector3d vector3d6;
            Vector3d vector3d7;
            Vector3d vector3d8;
            Vector3d vector3d9;
            Vector3d vector3d10;
            Vector3d vector3d11;
            Vector3d vector3d12;
            ThreadSafeListener threadSafeListener;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6220p$;
            vector3d = Localization$updateOdom$1.this.this$0.pose_broad;
            vector3d2 = Localization$updateOdom$1.this.this$0.position;
            vector3d.setX(vector3d2.getX());
            vector3d3 = Localization$updateOdom$1.this.this$0.pose_broad;
            vector3d4 = Localization$updateOdom$1.this.this$0.position;
            vector3d3.setY(vector3d4.getY());
            vector3d5 = Localization$updateOdom$1.this.this$0.pose_broad;
            vector3d6 = Localization$updateOdom$1.this.this$0.position;
            vector3d5.setZ(vector3d6.getZ());
            vector3d7 = Localization$updateOdom$1.this.this$0.dir_broad;
            vector3d8 = Localization$updateOdom$1.this.this$0.orientation;
            vector3d7.setX(vector3d8.getX());
            vector3d9 = Localization$updateOdom$1.this.this$0.dir_broad;
            vector3d10 = Localization$updateOdom$1.this.this$0.orientation;
            vector3d9.setY(vector3d10.getY());
            vector3d11 = Localization$updateOdom$1.this.this$0.dir_broad;
            vector3d12 = Localization$updateOdom$1.this.this$0.orientation;
            vector3d11.setZ(vector3d12.getZ());
            threadSafeListener = Localization$updateOdom$1.this.this$0.localizationListeners;
            threadSafeListener.notify(new Function2<LocalizationListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.localization.Localization.updateOdom.1.4.1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LocalizationListener localizationListener, String str) {
                    invoke2(localizationListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LocalizationListener it, String name) {
                    Vector3d vector3d13;
                    Vector3d vector3d14;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    vector3d13 = Localization$updateOdom$1.this.this$0.pose_broad;
                    it.updateRobotPosition(vector3d13);
                    vector3d14 = Localization$updateOdom$1.this.this$0.dir_broad;
                    it.updateRobotDirection(vector3d14);
                }
            });
            Localization$updateOdom$1.this.this$0.lastBroadTime = this.$current_time;
            return Unit.INSTANCE;
        }
    }
}
