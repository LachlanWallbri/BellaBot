package com.pudutech.robot.module.report.track2;

import android.os.SystemClock;
import androidx.collection.LruCache;
import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.pd_network.log.NetWorkLog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MileageHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0004J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bJ\u001e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/MileageHelper;", "", "()V", "TAG", "", "currPose", "Lcom/pudutech/robot/module/report/track2/PoseData;", "lastSpeedTimestamp", "", "lruMap", "Landroidx/collection/LruCache;", "", "<set-?>", "mileages_m", "getMileages_m", "()D", "preReportNum", "speed", "getAndPut", "id", "getPose", "onMove", "", "left", "right", "onPose", "x", "y", "yaw", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class MileageHelper {
    public static final MileageHelper INSTANCE;
    private static final String TAG;
    private static PoseData currPose;
    private static long lastSpeedTimestamp;
    private static final LruCache<String, Double> lruMap;
    private static double mileages_m;
    private static double preReportNum;
    private static double speed;

    static {
        MileageHelper mileageHelper = new MileageHelper();
        INSTANCE = mileageHelper;
        String simpleName = mileageHelper.getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this.javaClass.simpleName");
        TAG = simpleName;
        lruMap = new LruCache<>(10);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new C56971(null), 3, null);
    }

    private MileageHelper() {
    }

    public static final /* synthetic */ double access$getPreReportNum$p(MileageHelper mileageHelper) {
        return preReportNum;
    }

    public static final /* synthetic */ String access$getTAG$p(MileageHelper mileageHelper) {
        return TAG;
    }

    public final double getMileages_m() {
        return mileages_m;
    }

    public final void onMove(double left, double right) {
        mileages_m += ((SystemClock.elapsedRealtime() - lastSpeedTimestamp) / 1000.0d) * speed;
        lastSpeedTimestamp = SystemClock.elapsedRealtime();
        if (left < 0.2d) {
            left = 0.0d;
        }
        speed = left;
    }

    public final double getAndPut(String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Double d = lruMap.get(id);
        if (d == null) {
            lruMap.put(id, Double.valueOf(mileages_m));
            return 0.0d;
        }
        return mileages_m - d.doubleValue();
    }

    /* compiled from: MileageHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.robot.module.report.track2.MileageHelper$1", m3970f = "MileageHelper.kt", m3971i = {0}, m3972l = {47}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.robot.module.report.track2.MileageHelper$1 */
    /* loaded from: classes7.dex */
    static final class C56971 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7207p$;

        C56971(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C56971 c56971 = new C56971(completion);
            c56971.f7207p$ = (CoroutineScope) obj;
            return c56971;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C56971) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x009a  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0069  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0034 -> B:5:0x0037). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            C56971 c56971;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f7207p$;
                c56971 = this;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
            } else if (i == 1) {
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c56971 = this;
                NetWorkLog.INSTANCE.mo3280i(MileageHelper.access$getTAG$p(MileageHelper.INSTANCE), "report move distance > " + MileageHelper.INSTANCE.getMileages_m());
                if (MileageHelper.access$getPreReportNum$p(MileageHelper.INSTANCE) != MileageHelper.INSTANCE.getMileages_m()) {
                    MileageHelper mileageHelper = MileageHelper.INSTANCE;
                    MileageHelper.preReportNum = MileageHelper.INSTANCE.getMileages_m();
                    PuduEventTrackingManager.INSTANCE.customEvent(new CustomArgs(TrackType.MOVE.toString(), MapsKt.mapOf(TuplesKt.m3968to("move_distance", Boxing.boxDouble(MileageHelper.INSTANCE.getMileages_m()))), 0));
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    c56971.L$0 = coroutineScope;
                    c56971.label = 1;
                    if (DelayKt.delay(600000L, c56971) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    NetWorkLog.INSTANCE.mo3280i(MileageHelper.access$getTAG$p(MileageHelper.INSTANCE), "report move distance > " + MileageHelper.INSTANCE.getMileages_m());
                    if (MileageHelper.access$getPreReportNum$p(MileageHelper.INSTANCE) != MileageHelper.INSTANCE.getMileages_m()) {
                    }
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        return Unit.INSTANCE;
                    }
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final void onPose(double x, double y, double yaw) {
        currPose = new PoseData(x, y, yaw);
    }

    public final PoseData getPose() {
        PoseData poseData = currPose;
        if (poseData != null) {
            return poseData;
        }
        return null;
    }
}
