package com.pudutech.robot.module.report.track2;

import com.iflytek.cloud.SpeechEvent;
import com.pudutech.robot.module.report.ExtKt;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: deliver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001\u001dB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\u0016¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005J\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0005J\u0016\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\tJ\u0006\u0010\u001a\u001a\u00020\u0014J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\rJ\u0006\u0010\u001c\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/DeliverTask;", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask;", "config", "Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig;", "sessionId", "", "(Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig;Ljava/lang/String;)V", "finishOneCauseList", "", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$FinishOneCause;", "getSessionId", "()Ljava/lang/String;", "stopCauseList", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$StopCause;", "commonArgs", "", "Lkotlin/Pair;", "", "()[Lkotlin/Pair;", "onAdSwitch", "", "scene", "onArrive", "destination", "onFinishOne", "cause", "onStart", "onStop", "onVoice", "BaseDeliverConfig", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class DeliverTask extends BaseMoveTrackTask {
    private final BaseDeliverConfig config;
    private final List<BaseMoveTrackTask.FinishOneCause> finishOneCauseList;
    private final String sessionId;
    private final List<BaseMoveTrackTask.StopCause> stopCauseList;

    public final void onVoice() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliverTask(BaseDeliverConfig config, String sessionId) {
        super(TrackType.DELIVERY);
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        this.config = config;
        this.sessionId = sessionId;
        this.stopCauseList = CollectionsKt.listOf((Object[]) new BaseMoveTrackTask.StopCause[]{BaseMoveTrackTask.StopCause.Normal, BaseMoveTrackTask.StopCause.CountDown, BaseMoveTrackTask.StopCause.Charge, BaseMoveTrackTask.StopCause.InAdvance, BaseMoveTrackTask.StopCause.Cancel, BaseMoveTrackTask.StopCause.LostLocation, BaseMoveTrackTask.StopCause.LocalModification, BaseMoveTrackTask.StopCause.RemoteModification, BaseMoveTrackTask.StopCause.Modification, BaseMoveTrackTask.StopCause.RemoteCancel});
        this.finishOneCauseList = CollectionsKt.listOf((Object[]) new BaseMoveTrackTask.FinishOneCause[]{BaseMoveTrackTask.FinishOneCause.Normal, BaseMoveTrackTask.FinishOneCause.Click, BaseMoveTrackTask.FinishOneCause.CountDown, BaseMoveTrackTask.FinishOneCause.Wave, BaseMoveTrackTask.FinishOneCause.PowerBtn, BaseMoveTrackTask.FinishOneCause.InAdvance, BaseMoveTrackTask.FinishOneCause.Voice, BaseMoveTrackTask.FinishOneCause.Remote, BaseMoveTrackTask.FinishOneCause.LotteryClick, BaseMoveTrackTask.FinishOneCause.LotteryHide, BaseMoveTrackTask.FinishOneCause.TraySensor});
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final void onStart() {
        Pair<String, Object>[] pairArray = ExtKt.toPairArray(this.config);
        customEvent$module_robot_report_release(BusinessStatus.ON_START, (Pair[]) Arrays.copyOf(pairArray, pairArray.length));
    }

    @Override // com.pudutech.robot.module.report.track2.BaseMoveTrackTask
    public Pair<String, Object>[] commonArgs() {
        return new Pair[]{TuplesKt.m3968to(SpeechEvent.KEY_EVENT_SESSION_ID, this.sessionId)};
    }

    public final void onStop(BaseMoveTrackTask.StopCause cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        if (!this.stopCauseList.contains(cause)) {
            TrackingReportManager.INSTANCE.throwOrLog(getEvent() + " stopError.causeError cause=" + cause);
        }
        customEvent$module_robot_report_release(BusinessStatus.ON_STOP, TuplesKt.m3968to("cause", Integer.valueOf(cause.getType())));
        DeliverKt.crtDeliverTask = (DeliverTask) null;
    }

    public final void onArrive(String destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        customEvent$module_robot_report_release(BusinessStatus.ON_ARRIVE, TuplesKt.m3968to("destination", destination));
    }

    public final void onFinishOne(String destination, BaseMoveTrackTask.FinishOneCause cause) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        if (!this.finishOneCauseList.contains(cause)) {
            TrackingReportManager.INSTANCE.throwOrLog(getEvent() + " finishOneError.causeError cause=" + cause);
        }
        customEvent$module_robot_report_release(BusinessStatus.ON_FINISH_ONE, TuplesKt.m3968to("destination", destination), TuplesKt.m3968to("cause", Integer.valueOf(cause.getType())));
    }

    public final void onAdSwitch(String scene) {
        Intrinsics.checkParameterIsNotNull(scene, "scene");
        customEvent$module_robot_report_release(BusinessStatus.ON_AD_SWITCH, TuplesKt.m3968to("scene", scene));
    }

    /* compiled from: deliver.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig;", "", "()V", "BellaDeliverConfig", "PeanutDeliverConfig", "PhoenixDeliveryConfig", "Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig$PeanutDeliverConfig;", "Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig$BellaDeliverConfig;", "Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig$PhoenixDeliveryConfig;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static abstract class BaseDeliverConfig {
        private BaseDeliverConfig() {
        }

        public /* synthetic */ BaseDeliverConfig(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: deliver.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0002\u0010\u0015J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\u000fHÆ\u0003J\t\u0010+\u001a\u00020\u0011HÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0006HÆ\u0003J\t\u0010/\u001a\u00020\u0006HÆ\u0003J\t\u00100\u001a\u00020\u0006HÆ\u0003J\t\u00101\u001a\u00020\u0006HÆ\u0003J\t\u00102\u001a\u00020\u0006HÆ\u0003J\t\u00103\u001a\u00020\u0006HÆ\u0003J\t\u00104\u001a\u00020\u0006HÆ\u0003J\u0091\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013HÆ\u0001J\u0013\u00106\u001a\u00020\u00062\b\u00107\u001a\u0004\u0018\u000108HÖ\u0003J\t\u00109\u001a\u00020\u0003HÖ\u0001J\t\u0010:\u001a\u00020;HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u0006<"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig$PeanutDeliverConfig;", "Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig;", "from", "", "mode", "steady", "", "deliver_music", "deliver_cmd", "arrive_music", "back_music", "take_number", "deliver_face", "auto_complete", "auto_time", "", "speed", "", "task_info", "", "Lcom/pudutech/robot/module/report/track2/TaskInfo;", "(IIZZZZZZZZJFLjava/util/List;)V", "getArrive_music", "()Z", "getAuto_complete", "getAuto_time", "()J", "getBack_music", "getDeliver_cmd", "getDeliver_face", "getDeliver_music", "getFrom", "()I", "getMode", "getSpeed", "()F", "getSteady", "getTake_number", "getTask_info", "()Ljava/util/List;", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes7.dex */
        public static final /* data */ class PeanutDeliverConfig extends BaseDeliverConfig {
            private final boolean arrive_music;
            private final boolean auto_complete;
            private final long auto_time;
            private final boolean back_music;
            private final boolean deliver_cmd;
            private final boolean deliver_face;
            private final boolean deliver_music;
            private final int from;
            private final int mode;
            private final float speed;
            private final boolean steady;
            private final boolean take_number;
            private final List<TaskInfo> task_info;

            /* renamed from: component1, reason: from getter */
            public final int getFrom() {
                return this.from;
            }

            /* renamed from: component10, reason: from getter */
            public final boolean getAuto_complete() {
                return this.auto_complete;
            }

            /* renamed from: component11, reason: from getter */
            public final long getAuto_time() {
                return this.auto_time;
            }

            /* renamed from: component12, reason: from getter */
            public final float getSpeed() {
                return this.speed;
            }

            public final List<TaskInfo> component13() {
                return this.task_info;
            }

            /* renamed from: component2, reason: from getter */
            public final int getMode() {
                return this.mode;
            }

            /* renamed from: component3, reason: from getter */
            public final boolean getSteady() {
                return this.steady;
            }

            /* renamed from: component4, reason: from getter */
            public final boolean getDeliver_music() {
                return this.deliver_music;
            }

            /* renamed from: component5, reason: from getter */
            public final boolean getDeliver_cmd() {
                return this.deliver_cmd;
            }

            /* renamed from: component6, reason: from getter */
            public final boolean getArrive_music() {
                return this.arrive_music;
            }

            /* renamed from: component7, reason: from getter */
            public final boolean getBack_music() {
                return this.back_music;
            }

            /* renamed from: component8, reason: from getter */
            public final boolean getTake_number() {
                return this.take_number;
            }

            /* renamed from: component9, reason: from getter */
            public final boolean getDeliver_face() {
                return this.deliver_face;
            }

            public final PeanutDeliverConfig copy(int from, int mode, boolean steady, boolean deliver_music, boolean deliver_cmd, boolean arrive_music, boolean back_music, boolean take_number, boolean deliver_face, boolean auto_complete, long auto_time, float speed, List<TaskInfo> task_info) {
                Intrinsics.checkParameterIsNotNull(task_info, "task_info");
                return new PeanutDeliverConfig(from, mode, steady, deliver_music, deliver_cmd, arrive_music, back_music, take_number, deliver_face, auto_complete, auto_time, speed, task_info);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof PeanutDeliverConfig)) {
                    return false;
                }
                PeanutDeliverConfig peanutDeliverConfig = (PeanutDeliverConfig) other;
                return this.from == peanutDeliverConfig.from && this.mode == peanutDeliverConfig.mode && this.steady == peanutDeliverConfig.steady && this.deliver_music == peanutDeliverConfig.deliver_music && this.deliver_cmd == peanutDeliverConfig.deliver_cmd && this.arrive_music == peanutDeliverConfig.arrive_music && this.back_music == peanutDeliverConfig.back_music && this.take_number == peanutDeliverConfig.take_number && this.deliver_face == peanutDeliverConfig.deliver_face && this.auto_complete == peanutDeliverConfig.auto_complete && this.auto_time == peanutDeliverConfig.auto_time && Float.compare(this.speed, peanutDeliverConfig.speed) == 0 && Intrinsics.areEqual(this.task_info, peanutDeliverConfig.task_info);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public int hashCode() {
                int i = ((this.from * 31) + this.mode) * 31;
                boolean z = this.steady;
                int i2 = z;
                if (z != 0) {
                    i2 = 1;
                }
                int i3 = (i + i2) * 31;
                boolean z2 = this.deliver_music;
                int i4 = z2;
                if (z2 != 0) {
                    i4 = 1;
                }
                int i5 = (i3 + i4) * 31;
                boolean z3 = this.deliver_cmd;
                int i6 = z3;
                if (z3 != 0) {
                    i6 = 1;
                }
                int i7 = (i5 + i6) * 31;
                boolean z4 = this.arrive_music;
                int i8 = z4;
                if (z4 != 0) {
                    i8 = 1;
                }
                int i9 = (i7 + i8) * 31;
                boolean z5 = this.back_music;
                int i10 = z5;
                if (z5 != 0) {
                    i10 = 1;
                }
                int i11 = (i9 + i10) * 31;
                boolean z6 = this.take_number;
                int i12 = z6;
                if (z6 != 0) {
                    i12 = 1;
                }
                int i13 = (i11 + i12) * 31;
                boolean z7 = this.deliver_face;
                int i14 = z7;
                if (z7 != 0) {
                    i14 = 1;
                }
                int i15 = (i13 + i14) * 31;
                boolean z8 = this.auto_complete;
                int i16 = z8;
                if (z8 != 0) {
                    i16 = 1;
                }
                int i17 = (i15 + i16) * 31;
                long j = this.auto_time;
                int floatToIntBits = (((i17 + ((int) (j ^ (j >>> 32)))) * 31) + Float.floatToIntBits(this.speed)) * 31;
                List<TaskInfo> list = this.task_info;
                return floatToIntBits + (list != null ? list.hashCode() : 0);
            }

            public String toString() {
                return "PeanutDeliverConfig(from=" + this.from + ", mode=" + this.mode + ", steady=" + this.steady + ", deliver_music=" + this.deliver_music + ", deliver_cmd=" + this.deliver_cmd + ", arrive_music=" + this.arrive_music + ", back_music=" + this.back_music + ", take_number=" + this.take_number + ", deliver_face=" + this.deliver_face + ", auto_complete=" + this.auto_complete + ", auto_time=" + this.auto_time + ", speed=" + this.speed + ", task_info=" + this.task_info + ")";
            }

            public final int getFrom() {
                return this.from;
            }

            public final int getMode() {
                return this.mode;
            }

            public final boolean getSteady() {
                return this.steady;
            }

            public final boolean getDeliver_music() {
                return this.deliver_music;
            }

            public final boolean getDeliver_cmd() {
                return this.deliver_cmd;
            }

            public final boolean getArrive_music() {
                return this.arrive_music;
            }

            public final boolean getBack_music() {
                return this.back_music;
            }

            public final boolean getTake_number() {
                return this.take_number;
            }

            public final boolean getDeliver_face() {
                return this.deliver_face;
            }

            public final boolean getAuto_complete() {
                return this.auto_complete;
            }

            public final long getAuto_time() {
                return this.auto_time;
            }

            public final float getSpeed() {
                return this.speed;
            }

            public final List<TaskInfo> getTask_info() {
                return this.task_info;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PeanutDeliverConfig(int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, long j, float f, List<TaskInfo> task_info) {
                super(null);
                Intrinsics.checkParameterIsNotNull(task_info, "task_info");
                this.from = i;
                this.mode = i2;
                this.steady = z;
                this.deliver_music = z2;
                this.deliver_cmd = z3;
                this.arrive_music = z4;
                this.back_music = z5;
                this.take_number = z6;
                this.deliver_face = z7;
                this.auto_complete = z8;
                this.auto_time = j;
                this.speed = f;
                this.task_info = task_info;
            }
        }

        /* compiled from: deliver.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b9\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BÉ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\n\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\n\u0012\u0006\u0010\u0016\u001a\u00020\n\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0007\u0012\u0006\u0010\u001a\u001a\u00020\n\u0012\u0006\u0010\u001b\u001a\u00020\n\u0012\u0006\u0010\u001c\u001a\u00020\n\u0012\u0006\u0010\u001d\u001a\u00020\n\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u0012\u0006\u0010 \u001a\u00020\u0003¢\u0006\u0002\u0010!J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0010HÆ\u0003J\t\u0010A\u001a\u00020\nHÆ\u0003J\t\u0010B\u001a\u00020\u0013HÆ\u0003J\t\u0010C\u001a\u00020\u0013HÆ\u0003J\t\u0010D\u001a\u00020\nHÆ\u0003J\t\u0010E\u001a\u00020\nHÆ\u0003J\t\u0010F\u001a\u00020\u0003HÆ\u0003J\u000f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00190\u0007HÆ\u0003J\t\u0010H\u001a\u00020\nHÆ\u0003J\t\u0010I\u001a\u00020\nHÆ\u0003J\t\u0010J\u001a\u00020\u0003HÆ\u0003J\t\u0010K\u001a\u00020\nHÆ\u0003J\t\u0010L\u001a\u00020\nHÆ\u0003J\t\u0010M\u001a\u00020\u001fHÆ\u0003J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\t\u0010O\u001a\u00020\u0003HÆ\u0003J\u000f\u0010P\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010Q\u001a\u00020\nHÆ\u0003J\t\u0010R\u001a\u00020\nHÆ\u0003J\t\u0010S\u001a\u00020\nHÆ\u0003J\t\u0010T\u001a\u00020\nHÆ\u0003J\t\u0010U\u001a\u00020\nHÆ\u0003Jû\u0001\u0010V\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\n2\b\b\u0002\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u0017\u001a\u00020\u00032\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00072\b\b\u0002\u0010\u001a\u001a\u00020\n2\b\b\u0002\u0010\u001b\u001a\u00020\n2\b\b\u0002\u0010\u001c\u001a\u00020\n2\b\b\u0002\u0010\u001d\u001a\u00020\n2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u0003HÆ\u0001J\u0013\u0010W\u001a\u00020\n2\b\u0010X\u001a\u0004\u0018\u00010YHÖ\u0003J\t\u0010Z\u001a\u00020\u0003HÖ\u0001J\t\u0010[\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u000e\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010\u0011\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010 \u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b*\u0010#R\u0011\u0010\u0014\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b+\u0010'R\u0011\u0010\u001a\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b,\u0010#R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010)R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b.\u0010#R\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u001b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b1\u0010#R\u0011\u0010\u001c\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b2\u0010#R\u0011\u0010\u001d\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b3\u0010#R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b6\u0010#R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010)R\u0011\u0010\u0017\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b:\u0010)R\u0011\u0010\u0015\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b;\u0010#R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0007¢\u0006\b\n\u0000\u001a\u0004\b<\u00108R\u0011\u0010\u0016\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b=\u0010#R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b>\u0010)¨\u0006\\"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig$BellaDeliverConfig;", "Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig;", "mode", "", TrackKey.TASK_NUMBER, TrackKey.TRAY_USED_NUMBER, "task_info", "", "Lcom/pudutech/robot/module/report/track2/TaskInfo;", "steady", "", "music", "arrive_music", "custom_voice", "animation", "speed", "", "auto_complete", "auto_time", "", TrackKey.FREEZE_TIME, "tray_sensor", "tray_take_error_tip", TrackKey.TRAY_NUMBER, "tray_state", "Lcom/pudutech/robot/module/report/track2/TrayState;", "lottery_interaction", "pw_protection", TrackKey.SPECIAL_CONFIG_MUSIC, TrackKey.SPECIAL_CONFIG_VOICE, "page_key", "", "call_from", "(IIILjava/util/List;ZZZZZFZJJZZILjava/util/List;ZZZZLjava/lang/String;I)V", "getAnimation", "()Z", "getArrive_music", "getAuto_complete", "getAuto_time", "()J", "getCall_from", "()I", "getCustom_voice", "getFreeze_time", "getLottery_interaction", "getMode", "getMusic", "getPage_key", "()Ljava/lang/String;", "getPw_protection", "getSpecial_config_music", "getSpecial_config_voice", "getSpeed", "()F", "getSteady", "getTask_info", "()Ljava/util/List;", "getTask_number", "getTray_number", "getTray_sensor", "getTray_state", "getTray_take_error_tip", "getTray_used_number", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes7.dex */
        public static final /* data */ class BellaDeliverConfig extends BaseDeliverConfig {
            private final boolean animation;
            private final boolean arrive_music;
            private final boolean auto_complete;
            private final long auto_time;
            private final int call_from;
            private final boolean custom_voice;
            private final long freeze_time;
            private final boolean lottery_interaction;
            private final int mode;
            private final boolean music;
            private final String page_key;
            private final boolean pw_protection;
            private final boolean special_config_music;
            private final boolean special_config_voice;
            private final float speed;
            private final boolean steady;
            private final List<TaskInfo> task_info;
            private final int task_number;
            private final int tray_number;
            private final boolean tray_sensor;
            private final List<TrayState> tray_state;
            private final boolean tray_take_error_tip;
            private final int tray_used_number;

            /* renamed from: component1, reason: from getter */
            public final int getMode() {
                return this.mode;
            }

            /* renamed from: component10, reason: from getter */
            public final float getSpeed() {
                return this.speed;
            }

            /* renamed from: component11, reason: from getter */
            public final boolean getAuto_complete() {
                return this.auto_complete;
            }

            /* renamed from: component12, reason: from getter */
            public final long getAuto_time() {
                return this.auto_time;
            }

            /* renamed from: component13, reason: from getter */
            public final long getFreeze_time() {
                return this.freeze_time;
            }

            /* renamed from: component14, reason: from getter */
            public final boolean getTray_sensor() {
                return this.tray_sensor;
            }

            /* renamed from: component15, reason: from getter */
            public final boolean getTray_take_error_tip() {
                return this.tray_take_error_tip;
            }

            /* renamed from: component16, reason: from getter */
            public final int getTray_number() {
                return this.tray_number;
            }

            public final List<TrayState> component17() {
                return this.tray_state;
            }

            /* renamed from: component18, reason: from getter */
            public final boolean getLottery_interaction() {
                return this.lottery_interaction;
            }

            /* renamed from: component19, reason: from getter */
            public final boolean getPw_protection() {
                return this.pw_protection;
            }

            /* renamed from: component2, reason: from getter */
            public final int getTask_number() {
                return this.task_number;
            }

            /* renamed from: component20, reason: from getter */
            public final boolean getSpecial_config_music() {
                return this.special_config_music;
            }

            /* renamed from: component21, reason: from getter */
            public final boolean getSpecial_config_voice() {
                return this.special_config_voice;
            }

            /* renamed from: component22, reason: from getter */
            public final String getPage_key() {
                return this.page_key;
            }

            /* renamed from: component23, reason: from getter */
            public final int getCall_from() {
                return this.call_from;
            }

            /* renamed from: component3, reason: from getter */
            public final int getTray_used_number() {
                return this.tray_used_number;
            }

            public final List<TaskInfo> component4() {
                return this.task_info;
            }

            /* renamed from: component5, reason: from getter */
            public final boolean getSteady() {
                return this.steady;
            }

            /* renamed from: component6, reason: from getter */
            public final boolean getMusic() {
                return this.music;
            }

            /* renamed from: component7, reason: from getter */
            public final boolean getArrive_music() {
                return this.arrive_music;
            }

            /* renamed from: component8, reason: from getter */
            public final boolean getCustom_voice() {
                return this.custom_voice;
            }

            /* renamed from: component9, reason: from getter */
            public final boolean getAnimation() {
                return this.animation;
            }

            public final BellaDeliverConfig copy(int mode, int task_number, int tray_used_number, List<TaskInfo> task_info, boolean steady, boolean music, boolean arrive_music, boolean custom_voice, boolean animation, float speed, boolean auto_complete, long auto_time, long freeze_time, boolean tray_sensor, boolean tray_take_error_tip, int tray_number, List<TrayState> tray_state, boolean lottery_interaction, boolean pw_protection, boolean special_config_music, boolean special_config_voice, String page_key, int call_from) {
                Intrinsics.checkParameterIsNotNull(task_info, "task_info");
                Intrinsics.checkParameterIsNotNull(tray_state, "tray_state");
                Intrinsics.checkParameterIsNotNull(page_key, "page_key");
                return new BellaDeliverConfig(mode, task_number, tray_used_number, task_info, steady, music, arrive_music, custom_voice, animation, speed, auto_complete, auto_time, freeze_time, tray_sensor, tray_take_error_tip, tray_number, tray_state, lottery_interaction, pw_protection, special_config_music, special_config_voice, page_key, call_from);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof BellaDeliverConfig)) {
                    return false;
                }
                BellaDeliverConfig bellaDeliverConfig = (BellaDeliverConfig) other;
                return this.mode == bellaDeliverConfig.mode && this.task_number == bellaDeliverConfig.task_number && this.tray_used_number == bellaDeliverConfig.tray_used_number && Intrinsics.areEqual(this.task_info, bellaDeliverConfig.task_info) && this.steady == bellaDeliverConfig.steady && this.music == bellaDeliverConfig.music && this.arrive_music == bellaDeliverConfig.arrive_music && this.custom_voice == bellaDeliverConfig.custom_voice && this.animation == bellaDeliverConfig.animation && Float.compare(this.speed, bellaDeliverConfig.speed) == 0 && this.auto_complete == bellaDeliverConfig.auto_complete && this.auto_time == bellaDeliverConfig.auto_time && this.freeze_time == bellaDeliverConfig.freeze_time && this.tray_sensor == bellaDeliverConfig.tray_sensor && this.tray_take_error_tip == bellaDeliverConfig.tray_take_error_tip && this.tray_number == bellaDeliverConfig.tray_number && Intrinsics.areEqual(this.tray_state, bellaDeliverConfig.tray_state) && this.lottery_interaction == bellaDeliverConfig.lottery_interaction && this.pw_protection == bellaDeliverConfig.pw_protection && this.special_config_music == bellaDeliverConfig.special_config_music && this.special_config_voice == bellaDeliverConfig.special_config_voice && Intrinsics.areEqual(this.page_key, bellaDeliverConfig.page_key) && this.call_from == bellaDeliverConfig.call_from;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public int hashCode() {
                int i = ((((this.mode * 31) + this.task_number) * 31) + this.tray_used_number) * 31;
                List<TaskInfo> list = this.task_info;
                int hashCode = (i + (list != null ? list.hashCode() : 0)) * 31;
                boolean z = this.steady;
                int i2 = z;
                if (z != 0) {
                    i2 = 1;
                }
                int i3 = (hashCode + i2) * 31;
                boolean z2 = this.music;
                int i4 = z2;
                if (z2 != 0) {
                    i4 = 1;
                }
                int i5 = (i3 + i4) * 31;
                boolean z3 = this.arrive_music;
                int i6 = z3;
                if (z3 != 0) {
                    i6 = 1;
                }
                int i7 = (i5 + i6) * 31;
                boolean z4 = this.custom_voice;
                int i8 = z4;
                if (z4 != 0) {
                    i8 = 1;
                }
                int i9 = (i7 + i8) * 31;
                boolean z5 = this.animation;
                int i10 = z5;
                if (z5 != 0) {
                    i10 = 1;
                }
                int floatToIntBits = (((i9 + i10) * 31) + Float.floatToIntBits(this.speed)) * 31;
                boolean z6 = this.auto_complete;
                int i11 = z6;
                if (z6 != 0) {
                    i11 = 1;
                }
                long j = this.auto_time;
                int i12 = (((floatToIntBits + i11) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
                long j2 = this.freeze_time;
                int i13 = (i12 + ((int) (j2 ^ (j2 >>> 32)))) * 31;
                boolean z7 = this.tray_sensor;
                int i14 = z7;
                if (z7 != 0) {
                    i14 = 1;
                }
                int i15 = (i13 + i14) * 31;
                boolean z8 = this.tray_take_error_tip;
                int i16 = z8;
                if (z8 != 0) {
                    i16 = 1;
                }
                int i17 = (((i15 + i16) * 31) + this.tray_number) * 31;
                List<TrayState> list2 = this.tray_state;
                int hashCode2 = (i17 + (list2 != null ? list2.hashCode() : 0)) * 31;
                boolean z9 = this.lottery_interaction;
                int i18 = z9;
                if (z9 != 0) {
                    i18 = 1;
                }
                int i19 = (hashCode2 + i18) * 31;
                boolean z10 = this.pw_protection;
                int i20 = z10;
                if (z10 != 0) {
                    i20 = 1;
                }
                int i21 = (i19 + i20) * 31;
                boolean z11 = this.special_config_music;
                int i22 = z11;
                if (z11 != 0) {
                    i22 = 1;
                }
                int i23 = (i21 + i22) * 31;
                boolean z12 = this.special_config_voice;
                int i24 = z12;
                if (z12 != 0) {
                    i24 = 1;
                }
                int i25 = (i23 + i24) * 31;
                String str = this.page_key;
                return ((i25 + (str != null ? str.hashCode() : 0)) * 31) + this.call_from;
            }

            public String toString() {
                return "BellaDeliverConfig(mode=" + this.mode + ", task_number=" + this.task_number + ", tray_used_number=" + this.tray_used_number + ", task_info=" + this.task_info + ", steady=" + this.steady + ", music=" + this.music + ", arrive_music=" + this.arrive_music + ", custom_voice=" + this.custom_voice + ", animation=" + this.animation + ", speed=" + this.speed + ", auto_complete=" + this.auto_complete + ", auto_time=" + this.auto_time + ", freeze_time=" + this.freeze_time + ", tray_sensor=" + this.tray_sensor + ", tray_take_error_tip=" + this.tray_take_error_tip + ", tray_number=" + this.tray_number + ", tray_state=" + this.tray_state + ", lottery_interaction=" + this.lottery_interaction + ", pw_protection=" + this.pw_protection + ", special_config_music=" + this.special_config_music + ", special_config_voice=" + this.special_config_voice + ", page_key=" + this.page_key + ", call_from=" + this.call_from + ")";
            }

            public final int getMode() {
                return this.mode;
            }

            public final int getTask_number() {
                return this.task_number;
            }

            public final int getTray_used_number() {
                return this.tray_used_number;
            }

            public final List<TaskInfo> getTask_info() {
                return this.task_info;
            }

            public final boolean getSteady() {
                return this.steady;
            }

            public final boolean getMusic() {
                return this.music;
            }

            public final boolean getArrive_music() {
                return this.arrive_music;
            }

            public final boolean getCustom_voice() {
                return this.custom_voice;
            }

            public final boolean getAnimation() {
                return this.animation;
            }

            public final float getSpeed() {
                return this.speed;
            }

            public final boolean getAuto_complete() {
                return this.auto_complete;
            }

            public final long getAuto_time() {
                return this.auto_time;
            }

            public final long getFreeze_time() {
                return this.freeze_time;
            }

            public final boolean getTray_sensor() {
                return this.tray_sensor;
            }

            public final boolean getTray_take_error_tip() {
                return this.tray_take_error_tip;
            }

            public final int getTray_number() {
                return this.tray_number;
            }

            public final List<TrayState> getTray_state() {
                return this.tray_state;
            }

            public final boolean getLottery_interaction() {
                return this.lottery_interaction;
            }

            public final boolean getPw_protection() {
                return this.pw_protection;
            }

            public final boolean getSpecial_config_music() {
                return this.special_config_music;
            }

            public final boolean getSpecial_config_voice() {
                return this.special_config_voice;
            }

            public final String getPage_key() {
                return this.page_key;
            }

            public final int getCall_from() {
                return this.call_from;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BellaDeliverConfig(int i, int i2, int i3, List<TaskInfo> task_info, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, float f, boolean z6, long j, long j2, boolean z7, boolean z8, int i4, List<TrayState> tray_state, boolean z9, boolean z10, boolean z11, boolean z12, String page_key, int i5) {
                super(null);
                Intrinsics.checkParameterIsNotNull(task_info, "task_info");
                Intrinsics.checkParameterIsNotNull(tray_state, "tray_state");
                Intrinsics.checkParameterIsNotNull(page_key, "page_key");
                this.mode = i;
                this.task_number = i2;
                this.tray_used_number = i3;
                this.task_info = task_info;
                this.steady = z;
                this.music = z2;
                this.arrive_music = z3;
                this.custom_voice = z4;
                this.animation = z5;
                this.speed = f;
                this.auto_complete = z6;
                this.auto_time = j;
                this.freeze_time = j2;
                this.tray_sensor = z7;
                this.tray_take_error_tip = z8;
                this.tray_number = i4;
                this.tray_state = tray_state;
                this.lottery_interaction = z9;
                this.pw_protection = z10;
                this.special_config_music = z11;
                this.special_config_voice = z12;
                this.page_key = page_key;
                this.call_from = i5;
            }
        }

        /* compiled from: deliver.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\u0010\u0014J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0010HÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\fHÆ\u0003J\t\u00101\u001a\u00020\fHÆ\u0003J\u0087\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012HÆ\u0001J\u0013\u00103\u001a\u00020\u00052\b\u00104\u001a\u0004\u0018\u000105HÖ\u0003J\t\u00106\u001a\u00020\u0003HÖ\u0001J\t\u00107\u001a\u000208HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001d¨\u00069"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig$PhoenixDeliveryConfig;", "Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig;", "mode", "", "steady", "", "deliver_face", "deliver_music", "arrive_voice", "multi_table", "auto_complete", "auto_time", "", "pause_resume_time", "task_priority", "speed", "", "task_info", "", "Lcom/pudutech/robot/module/report/track2/TaskInfo;", "(IZZZZZZJJIFLjava/util/List;)V", "getArrive_voice", "()Z", "getAuto_complete", "getAuto_time", "()J", "getDeliver_face", "getDeliver_music", "getMode", "()I", "getMulti_table", "getPause_resume_time", "getSpeed", "()F", "getSteady", "getTask_info", "()Ljava/util/List;", "getTask_priority", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes7.dex */
        public static final /* data */ class PhoenixDeliveryConfig extends BaseDeliverConfig {
            private final boolean arrive_voice;
            private final boolean auto_complete;
            private final long auto_time;
            private final boolean deliver_face;
            private final boolean deliver_music;
            private final int mode;
            private final boolean multi_table;
            private final long pause_resume_time;
            private final float speed;
            private final boolean steady;
            private final List<TaskInfo> task_info;
            private final int task_priority;

            /* renamed from: component1, reason: from getter */
            public final int getMode() {
                return this.mode;
            }

            /* renamed from: component10, reason: from getter */
            public final int getTask_priority() {
                return this.task_priority;
            }

            /* renamed from: component11, reason: from getter */
            public final float getSpeed() {
                return this.speed;
            }

            public final List<TaskInfo> component12() {
                return this.task_info;
            }

            /* renamed from: component2, reason: from getter */
            public final boolean getSteady() {
                return this.steady;
            }

            /* renamed from: component3, reason: from getter */
            public final boolean getDeliver_face() {
                return this.deliver_face;
            }

            /* renamed from: component4, reason: from getter */
            public final boolean getDeliver_music() {
                return this.deliver_music;
            }

            /* renamed from: component5, reason: from getter */
            public final boolean getArrive_voice() {
                return this.arrive_voice;
            }

            /* renamed from: component6, reason: from getter */
            public final boolean getMulti_table() {
                return this.multi_table;
            }

            /* renamed from: component7, reason: from getter */
            public final boolean getAuto_complete() {
                return this.auto_complete;
            }

            /* renamed from: component8, reason: from getter */
            public final long getAuto_time() {
                return this.auto_time;
            }

            /* renamed from: component9, reason: from getter */
            public final long getPause_resume_time() {
                return this.pause_resume_time;
            }

            public final PhoenixDeliveryConfig copy(int mode, boolean steady, boolean deliver_face, boolean deliver_music, boolean arrive_voice, boolean multi_table, boolean auto_complete, long auto_time, long pause_resume_time, int task_priority, float speed, List<TaskInfo> task_info) {
                Intrinsics.checkParameterIsNotNull(task_info, "task_info");
                return new PhoenixDeliveryConfig(mode, steady, deliver_face, deliver_music, arrive_voice, multi_table, auto_complete, auto_time, pause_resume_time, task_priority, speed, task_info);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof PhoenixDeliveryConfig)) {
                    return false;
                }
                PhoenixDeliveryConfig phoenixDeliveryConfig = (PhoenixDeliveryConfig) other;
                return this.mode == phoenixDeliveryConfig.mode && this.steady == phoenixDeliveryConfig.steady && this.deliver_face == phoenixDeliveryConfig.deliver_face && this.deliver_music == phoenixDeliveryConfig.deliver_music && this.arrive_voice == phoenixDeliveryConfig.arrive_voice && this.multi_table == phoenixDeliveryConfig.multi_table && this.auto_complete == phoenixDeliveryConfig.auto_complete && this.auto_time == phoenixDeliveryConfig.auto_time && this.pause_resume_time == phoenixDeliveryConfig.pause_resume_time && this.task_priority == phoenixDeliveryConfig.task_priority && Float.compare(this.speed, phoenixDeliveryConfig.speed) == 0 && Intrinsics.areEqual(this.task_info, phoenixDeliveryConfig.task_info);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public int hashCode() {
                int i = this.mode * 31;
                boolean z = this.steady;
                int i2 = z;
                if (z != 0) {
                    i2 = 1;
                }
                int i3 = (i + i2) * 31;
                boolean z2 = this.deliver_face;
                int i4 = z2;
                if (z2 != 0) {
                    i4 = 1;
                }
                int i5 = (i3 + i4) * 31;
                boolean z3 = this.deliver_music;
                int i6 = z3;
                if (z3 != 0) {
                    i6 = 1;
                }
                int i7 = (i5 + i6) * 31;
                boolean z4 = this.arrive_voice;
                int i8 = z4;
                if (z4 != 0) {
                    i8 = 1;
                }
                int i9 = (i7 + i8) * 31;
                boolean z5 = this.multi_table;
                int i10 = z5;
                if (z5 != 0) {
                    i10 = 1;
                }
                int i11 = (i9 + i10) * 31;
                boolean z6 = this.auto_complete;
                int i12 = z6;
                if (z6 != 0) {
                    i12 = 1;
                }
                int i13 = (i11 + i12) * 31;
                long j = this.auto_time;
                int i14 = (i13 + ((int) (j ^ (j >>> 32)))) * 31;
                long j2 = this.pause_resume_time;
                int floatToIntBits = (((((i14 + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.task_priority) * 31) + Float.floatToIntBits(this.speed)) * 31;
                List<TaskInfo> list = this.task_info;
                return floatToIntBits + (list != null ? list.hashCode() : 0);
            }

            public String toString() {
                return "PhoenixDeliveryConfig(mode=" + this.mode + ", steady=" + this.steady + ", deliver_face=" + this.deliver_face + ", deliver_music=" + this.deliver_music + ", arrive_voice=" + this.arrive_voice + ", multi_table=" + this.multi_table + ", auto_complete=" + this.auto_complete + ", auto_time=" + this.auto_time + ", pause_resume_time=" + this.pause_resume_time + ", task_priority=" + this.task_priority + ", speed=" + this.speed + ", task_info=" + this.task_info + ")";
            }

            public final int getMode() {
                return this.mode;
            }

            public final boolean getSteady() {
                return this.steady;
            }

            public final boolean getDeliver_face() {
                return this.deliver_face;
            }

            public final boolean getDeliver_music() {
                return this.deliver_music;
            }

            public final boolean getArrive_voice() {
                return this.arrive_voice;
            }

            public final boolean getMulti_table() {
                return this.multi_table;
            }

            public final boolean getAuto_complete() {
                return this.auto_complete;
            }

            public final long getAuto_time() {
                return this.auto_time;
            }

            public final long getPause_resume_time() {
                return this.pause_resume_time;
            }

            public final int getTask_priority() {
                return this.task_priority;
            }

            public final float getSpeed() {
                return this.speed;
            }

            public final List<TaskInfo> getTask_info() {
                return this.task_info;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PhoenixDeliveryConfig(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, long j, long j2, int i2, float f, List<TaskInfo> task_info) {
                super(null);
                Intrinsics.checkParameterIsNotNull(task_info, "task_info");
                this.mode = i;
                this.steady = z;
                this.deliver_face = z2;
                this.deliver_music = z3;
                this.arrive_voice = z4;
                this.multi_table = z5;
                this.auto_complete = z6;
                this.auto_time = j;
                this.pause_resume_time = j2;
                this.task_priority = i2;
                this.speed = f;
                this.task_info = task_info;
            }
        }
    }
}
