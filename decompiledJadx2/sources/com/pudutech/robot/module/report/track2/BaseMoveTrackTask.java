package com.pudutech.robot.module.report.track2;

import com.google.logging.type.LogSeverity;
import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.pd_network.log.NetWorkLog;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.text.StringsKt;
import org.apache.http.HttpStatus;

/* compiled from: BaseMoveTrackTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001:\u0002&'B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\bH\u0002J\u001f\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u001a0\u0019H\u0016¢\u0006\u0002\u0010\u001bJC\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\b2*\u0010\u001f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u001a0\u0019\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u001aH\u0000¢\u0006\u0004\b \u0010!J\u0006\u0010\"\u001a\u00020\u001dJ\u0006\u0010#\u001a\u00020\u001dJ\u0006\u0010$\u001a\u00020\u001dJ\u0006\u0010%\u001a\u00020\u001dR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000f¨\u0006("}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask;", "", "type", "Lcom/pudutech/robot/module/report/track2/TrackType;", "(Lcom/pudutech/robot/module/report/track2/TrackType;)V", "TAG", "", "crtStatus", "Lcom/pudutech/robot/module/report/track2/BusinessStatus;", "getCrtStatus$module_robot_report_release", "()Lcom/pudutech/robot/module/report/track2/BusinessStatus;", "setCrtStatus$module_robot_report_release", "(Lcom/pudutech/robot/module/report/track2/BusinessStatus;)V", "event", "getEvent$module_robot_report_release", "()Ljava/lang/String;", "needStopSchedule", "", "getType", "()Lcom/pudutech/robot/module/report/track2/TrackType;", "uniqueId", "getUniqueId", "checkAndFill", "s", "commonArgs", "", "Lkotlin/Pair;", "()[Lkotlin/Pair;", "customEvent", "", "status", "pairs", "customEvent$module_robot_report_release", "(Lcom/pudutech/robot/module/report/track2/BusinessStatus;[Lkotlin/Pair;)V", "onMove", "onPause", "onStartScheduling", "onStopScheduling", "FinishOneCause", "StopCause", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseMoveTrackTask {
    private final String TAG;
    private BusinessStatus crtStatus;
    private final String event;
    private boolean needStopSchedule;
    private final TrackType type;
    private final String uniqueId;

    public Pair<String, Object>[] commonArgs() {
        return new Pair[0];
    }

    public BaseMoveTrackTask(TrackType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.type = type;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this.javaClass.simpleName");
        this.TAG = simpleName;
        this.event = this.type.toString();
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.uniqueId = StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
    }

    public final TrackType getType() {
        return this.type;
    }

    /* renamed from: getEvent$module_robot_report_release, reason: from getter */
    public final String getEvent() {
        return this.event;
    }

    public final String getUniqueId() {
        return this.uniqueId;
    }

    /* renamed from: getCrtStatus$module_robot_report_release, reason: from getter */
    public final BusinessStatus getCrtStatus() {
        return this.crtStatus;
    }

    public final void setCrtStatus$module_robot_report_release(BusinessStatus businessStatus) {
        this.crtStatus = businessStatus;
    }

    public final void onMove() {
        if (this.crtStatus == BusinessStatus.ON_MOVE) {
            return;
        }
        customEvent$module_robot_report_release(BusinessStatus.ON_MOVE, new Pair[0]);
    }

    public final void onPause() {
        if (this.crtStatus == BusinessStatus.ON_PAUSE) {
            return;
        }
        customEvent$module_robot_report_release(BusinessStatus.ON_PAUSE, new Pair[0]);
    }

    public final void onStartScheduling() {
        this.needStopSchedule = true;
        customEvent$module_robot_report_release(BusinessStatus.ON_START_SCHEDULING, new Pair[0]);
    }

    public final void onStopScheduling() {
        if (this.needStopSchedule) {
            this.needStopSchedule = false;
            customEvent$module_robot_report_release(BusinessStatus.ON_STOP_SCHEDULING, new Pair[0]);
        }
    }

    public final void customEvent$module_robot_report_release(BusinessStatus status, Pair<String, ? extends Object>... pairs) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(pairs, "pairs");
        boolean checkAndFill = checkAndFill(status);
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "customEvent: check = " + checkAndFill + ' ' + this.event + "  status = " + status + " args = " + pairs);
        if (checkAndFill) {
            PuduEventTrackingManager puduEventTrackingManager = PuduEventTrackingManager.INSTANCE;
            String str = this.event;
            SpreadBuilder spreadBuilder = new SpreadBuilder(5);
            spreadBuilder.add(TuplesKt.m3968to(TrackKey.BUSINESS_STATUS, status));
            spreadBuilder.add(TuplesKt.m3968to(TrackKey.SCENE_ID, this.uniqueId));
            spreadBuilder.add(TuplesKt.m3968to(TrackKey.CURRENT_MILEAGE, Double.valueOf(MileageHelper.INSTANCE.getAndPut(this.uniqueId))));
            spreadBuilder.addSpread(commonArgs());
            spreadBuilder.addSpread(pairs);
            puduEventTrackingManager.customEvent(new CustomArgs(str, MapsKt.mapOf((Pair[]) spreadBuilder.toArray(new Pair[spreadBuilder.size()])), 0));
        }
    }

    private final boolean checkAndFill(BusinessStatus s) {
        if (this.crtStatus == s) {
            NetWorkLog.INSTANCE.mo3279e(this.TAG, "同样的状态 不重复上报 : " + this.crtStatus);
            return false;
        }
        this.crtStatus = s;
        return true;
    }

    /* compiled from: BaseMoveTrackTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$StopCause;", "", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "Normal", "InAdvance", "CountDown", "Cancel", "Charge", "Exit", "GoToInventory", "RemoteCancel", "LocalCancel", "Modification", "LocalModification", "RemoteModification", "Abnormal", "LostLocation", "NoInventory", "NoTarget", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public enum StopCause {
        Normal(100),
        InAdvance(101),
        CountDown(102),
        Cancel(200),
        Charge(201),
        Exit(202),
        GoToInventory(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION),
        RemoteCancel(204),
        LocalCancel(HttpStatus.SC_RESET_CONTENT),
        Modification(300),
        LocalModification(301),
        RemoteModification(302),
        Abnormal(400),
        LostLocation(401),
        NoInventory(402),
        NoTarget(403);

        private final int type;

        StopCause(int i) {
            this.type = i;
        }

        public final int getType() {
            return this.type;
        }
    }

    /* compiled from: BaseMoveTrackTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$FinishOneCause;", "", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "Normal", "Click", "CountDown", "Wave", "PowerBtn", "InAdvance", "Voice", "Remote", "LotteryClick", "LotteryHide", "TraySensor", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public enum FinishOneCause {
        Normal(0),
        Click(100),
        CountDown(200),
        Wave(300),
        PowerBtn(400),
        InAdvance(500),
        Voice(600),
        Remote(700),
        LotteryClick(LogSeverity.EMERGENCY_VALUE),
        LotteryHide(900),
        TraySensor(1000);

        private final int type;

        FinishOneCause(int i) {
            this.type = i;
        }

        public final int getType() {
            return this.type;
        }
    }
}
