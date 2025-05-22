package com.pudutech.robot.module.report.track2;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: recycler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseRecyclerTaskConfig;", "", "()V", "BellaRecyclerTaskConfig", "PeanutRecyclerTaskConfig", "Lcom/pudutech/robot/module/report/track2/BaseRecyclerTaskConfig$PeanutRecyclerTaskConfig;", "Lcom/pudutech/robot/module/report/track2/BaseRecyclerTaskConfig$BellaRecyclerTaskConfig;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseRecyclerTaskConfig {
    private BaseRecyclerTaskConfig() {
    }

    public /* synthetic */ BaseRecyclerTaskConfig(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: recycler.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0002\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u000bHÆ\u0003J\t\u0010'\u001a\u00020\rHÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fHÆ\u0003Ji\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fHÆ\u0001J\u0013\u0010*\u001a\u00020\u00052\b\u0010+\u001a\u0004\u0018\u00010,HÖ\u0003J\t\u0010-\u001a\u00020\u000bHÖ\u0001J\t\u0010.\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006/"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseRecyclerTaskConfig$PeanutRecyclerTaskConfig;", "Lcom/pudutech/robot/module/report/track2/BaseRecyclerTaskConfig;", "speed", "", "loop", "", "arrive_stay_point_voice", "leave_stay_point_voice", "arrive_recycle_point_voice", "num_of_stay", "count", "", "stay_point_time", "", "destinationList", "", "", "(FZZZZZIJLjava/util/List;)V", "getArrive_recycle_point_voice", "()Z", "getArrive_stay_point_voice", "getCount", "()I", "getDestinationList", "()Ljava/util/List;", "getLeave_stay_point_voice", "getLoop", "getNum_of_stay", "getSpeed", "()F", "getStay_point_time", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class PeanutRecyclerTaskConfig extends BaseRecyclerTaskConfig {
        private final boolean arrive_recycle_point_voice;
        private final boolean arrive_stay_point_voice;
        private final int count;
        private final List<String> destinationList;
        private final boolean leave_stay_point_voice;
        private final boolean loop;
        private final boolean num_of_stay;
        private final float speed;
        private final long stay_point_time;

        /* renamed from: component1, reason: from getter */
        public final float getSpeed() {
            return this.speed;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getLoop() {
            return this.loop;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getArrive_stay_point_voice() {
            return this.arrive_stay_point_voice;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getLeave_stay_point_voice() {
            return this.leave_stay_point_voice;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getArrive_recycle_point_voice() {
            return this.arrive_recycle_point_voice;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getNum_of_stay() {
            return this.num_of_stay;
        }

        /* renamed from: component7, reason: from getter */
        public final int getCount() {
            return this.count;
        }

        /* renamed from: component8, reason: from getter */
        public final long getStay_point_time() {
            return this.stay_point_time;
        }

        public final List<String> component9() {
            return this.destinationList;
        }

        public final PeanutRecyclerTaskConfig copy(float speed, boolean loop, boolean arrive_stay_point_voice, boolean leave_stay_point_voice, boolean arrive_recycle_point_voice, boolean num_of_stay, int count, long stay_point_time, List<String> destinationList) {
            Intrinsics.checkParameterIsNotNull(destinationList, "destinationList");
            return new PeanutRecyclerTaskConfig(speed, loop, arrive_stay_point_voice, leave_stay_point_voice, arrive_recycle_point_voice, num_of_stay, count, stay_point_time, destinationList);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PeanutRecyclerTaskConfig)) {
                return false;
            }
            PeanutRecyclerTaskConfig peanutRecyclerTaskConfig = (PeanutRecyclerTaskConfig) other;
            return Float.compare(this.speed, peanutRecyclerTaskConfig.speed) == 0 && this.loop == peanutRecyclerTaskConfig.loop && this.arrive_stay_point_voice == peanutRecyclerTaskConfig.arrive_stay_point_voice && this.leave_stay_point_voice == peanutRecyclerTaskConfig.leave_stay_point_voice && this.arrive_recycle_point_voice == peanutRecyclerTaskConfig.arrive_recycle_point_voice && this.num_of_stay == peanutRecyclerTaskConfig.num_of_stay && this.count == peanutRecyclerTaskConfig.count && this.stay_point_time == peanutRecyclerTaskConfig.stay_point_time && Intrinsics.areEqual(this.destinationList, peanutRecyclerTaskConfig.destinationList);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int floatToIntBits = Float.floatToIntBits(this.speed) * 31;
            boolean z = this.loop;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (floatToIntBits + i) * 31;
            boolean z2 = this.arrive_stay_point_voice;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            int i4 = (i2 + i3) * 31;
            boolean z3 = this.leave_stay_point_voice;
            int i5 = z3;
            if (z3 != 0) {
                i5 = 1;
            }
            int i6 = (i4 + i5) * 31;
            boolean z4 = this.arrive_recycle_point_voice;
            int i7 = z4;
            if (z4 != 0) {
                i7 = 1;
            }
            int i8 = (i6 + i7) * 31;
            boolean z5 = this.num_of_stay;
            int i9 = z5;
            if (z5 != 0) {
                i9 = 1;
            }
            int i10 = (((i8 + i9) * 31) + this.count) * 31;
            long j = this.stay_point_time;
            int i11 = (i10 + ((int) (j ^ (j >>> 32)))) * 31;
            List<String> list = this.destinationList;
            return i11 + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "PeanutRecyclerTaskConfig(speed=" + this.speed + ", loop=" + this.loop + ", arrive_stay_point_voice=" + this.arrive_stay_point_voice + ", leave_stay_point_voice=" + this.leave_stay_point_voice + ", arrive_recycle_point_voice=" + this.arrive_recycle_point_voice + ", num_of_stay=" + this.num_of_stay + ", count=" + this.count + ", stay_point_time=" + this.stay_point_time + ", destinationList=" + this.destinationList + ")";
        }

        public final float getSpeed() {
            return this.speed;
        }

        public final boolean getLoop() {
            return this.loop;
        }

        public final boolean getArrive_stay_point_voice() {
            return this.arrive_stay_point_voice;
        }

        public final boolean getLeave_stay_point_voice() {
            return this.leave_stay_point_voice;
        }

        public final boolean getArrive_recycle_point_voice() {
            return this.arrive_recycle_point_voice;
        }

        public final boolean getNum_of_stay() {
            return this.num_of_stay;
        }

        public final int getCount() {
            return this.count;
        }

        public final long getStay_point_time() {
            return this.stay_point_time;
        }

        public final List<String> getDestinationList() {
            return this.destinationList;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PeanutRecyclerTaskConfig(float f, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, long j, List<String> destinationList) {
            super(null);
            Intrinsics.checkParameterIsNotNull(destinationList, "destinationList");
            this.speed = f;
            this.loop = z;
            this.arrive_stay_point_voice = z2;
            this.leave_stay_point_voice = z3;
            this.arrive_recycle_point_voice = z4;
            this.num_of_stay = z5;
            this.count = i;
            this.stay_point_time = j;
            this.destinationList = destinationList;
        }
    }

    /* compiled from: recycler.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b&\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0010¢\u0006\u0002\u0010\u0014J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fHÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0010HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\fHÆ\u0003J\t\u00103\u001a\u00020\fHÆ\u0003J\u0091\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u0010HÆ\u0001J\u0013\u00105\u001a\u00020\u00052\b\u00106\u001a\u0004\u0018\u000107HÖ\u0003J\t\u00108\u001a\u000209HÖ\u0001J\t\u0010:\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\u0013\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u0006;"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseRecyclerTaskConfig$BellaRecyclerTaskConfig;", "Lcom/pudutech/robot/module/report/track2/BaseRecyclerTaskConfig;", "speed", "", "sort_sw", "", "repeat_sw", "arrive_stay_point_voice", "leave_stay_point_voice", "arrive_recycle_point_voice", "auto_complete_sw", "auto_complete_time", "", "pause_resume_time", "destinationList", "", "", "pw_protection", "animation", "page_key", "(FZZZZZZJJLjava/util/List;ZZLjava/lang/String;)V", "getAnimation", "()Z", "getArrive_recycle_point_voice", "getArrive_stay_point_voice", "getAuto_complete_sw", "getAuto_complete_time", "()J", "getDestinationList", "()Ljava/util/List;", "getLeave_stay_point_voice", "getPage_key", "()Ljava/lang/String;", "getPause_resume_time", "getPw_protection", "getRepeat_sw", "getSort_sw", "getSpeed", "()F", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class BellaRecyclerTaskConfig extends BaseRecyclerTaskConfig {
        private final boolean animation;
        private final boolean arrive_recycle_point_voice;
        private final boolean arrive_stay_point_voice;
        private final boolean auto_complete_sw;
        private final long auto_complete_time;
        private final List<String> destinationList;
        private final boolean leave_stay_point_voice;
        private final String page_key;
        private final long pause_resume_time;
        private final boolean pw_protection;
        private final boolean repeat_sw;
        private final boolean sort_sw;
        private final float speed;

        /* renamed from: component1, reason: from getter */
        public final float getSpeed() {
            return this.speed;
        }

        public final List<String> component10() {
            return this.destinationList;
        }

        /* renamed from: component11, reason: from getter */
        public final boolean getPw_protection() {
            return this.pw_protection;
        }

        /* renamed from: component12, reason: from getter */
        public final boolean getAnimation() {
            return this.animation;
        }

        /* renamed from: component13, reason: from getter */
        public final String getPage_key() {
            return this.page_key;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getSort_sw() {
            return this.sort_sw;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getRepeat_sw() {
            return this.repeat_sw;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getArrive_stay_point_voice() {
            return this.arrive_stay_point_voice;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getLeave_stay_point_voice() {
            return this.leave_stay_point_voice;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getArrive_recycle_point_voice() {
            return this.arrive_recycle_point_voice;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getAuto_complete_sw() {
            return this.auto_complete_sw;
        }

        /* renamed from: component8, reason: from getter */
        public final long getAuto_complete_time() {
            return this.auto_complete_time;
        }

        /* renamed from: component9, reason: from getter */
        public final long getPause_resume_time() {
            return this.pause_resume_time;
        }

        public final BellaRecyclerTaskConfig copy(float speed, boolean sort_sw, boolean repeat_sw, boolean arrive_stay_point_voice, boolean leave_stay_point_voice, boolean arrive_recycle_point_voice, boolean auto_complete_sw, long auto_complete_time, long pause_resume_time, List<String> destinationList, boolean pw_protection, boolean animation, String page_key) {
            Intrinsics.checkParameterIsNotNull(destinationList, "destinationList");
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            return new BellaRecyclerTaskConfig(speed, sort_sw, repeat_sw, arrive_stay_point_voice, leave_stay_point_voice, arrive_recycle_point_voice, auto_complete_sw, auto_complete_time, pause_resume_time, destinationList, pw_protection, animation, page_key);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BellaRecyclerTaskConfig)) {
                return false;
            }
            BellaRecyclerTaskConfig bellaRecyclerTaskConfig = (BellaRecyclerTaskConfig) other;
            return Float.compare(this.speed, bellaRecyclerTaskConfig.speed) == 0 && this.sort_sw == bellaRecyclerTaskConfig.sort_sw && this.repeat_sw == bellaRecyclerTaskConfig.repeat_sw && this.arrive_stay_point_voice == bellaRecyclerTaskConfig.arrive_stay_point_voice && this.leave_stay_point_voice == bellaRecyclerTaskConfig.leave_stay_point_voice && this.arrive_recycle_point_voice == bellaRecyclerTaskConfig.arrive_recycle_point_voice && this.auto_complete_sw == bellaRecyclerTaskConfig.auto_complete_sw && this.auto_complete_time == bellaRecyclerTaskConfig.auto_complete_time && this.pause_resume_time == bellaRecyclerTaskConfig.pause_resume_time && Intrinsics.areEqual(this.destinationList, bellaRecyclerTaskConfig.destinationList) && this.pw_protection == bellaRecyclerTaskConfig.pw_protection && this.animation == bellaRecyclerTaskConfig.animation && Intrinsics.areEqual(this.page_key, bellaRecyclerTaskConfig.page_key);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int floatToIntBits = Float.floatToIntBits(this.speed) * 31;
            boolean z = this.sort_sw;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (floatToIntBits + i) * 31;
            boolean z2 = this.repeat_sw;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            int i4 = (i2 + i3) * 31;
            boolean z3 = this.arrive_stay_point_voice;
            int i5 = z3;
            if (z3 != 0) {
                i5 = 1;
            }
            int i6 = (i4 + i5) * 31;
            boolean z4 = this.leave_stay_point_voice;
            int i7 = z4;
            if (z4 != 0) {
                i7 = 1;
            }
            int i8 = (i6 + i7) * 31;
            boolean z5 = this.arrive_recycle_point_voice;
            int i9 = z5;
            if (z5 != 0) {
                i9 = 1;
            }
            int i10 = (i8 + i9) * 31;
            boolean z6 = this.auto_complete_sw;
            int i11 = z6;
            if (z6 != 0) {
                i11 = 1;
            }
            long j = this.auto_complete_time;
            int i12 = (((i10 + i11) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
            long j2 = this.pause_resume_time;
            int i13 = (i12 + ((int) (j2 ^ (j2 >>> 32)))) * 31;
            List<String> list = this.destinationList;
            int hashCode = (i13 + (list != null ? list.hashCode() : 0)) * 31;
            boolean z7 = this.pw_protection;
            int i14 = z7;
            if (z7 != 0) {
                i14 = 1;
            }
            int i15 = (hashCode + i14) * 31;
            boolean z8 = this.animation;
            int i16 = z8;
            if (z8 != 0) {
                i16 = 1;
            }
            int i17 = (i15 + i16) * 31;
            String str = this.page_key;
            return i17 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "BellaRecyclerTaskConfig(speed=" + this.speed + ", sort_sw=" + this.sort_sw + ", repeat_sw=" + this.repeat_sw + ", arrive_stay_point_voice=" + this.arrive_stay_point_voice + ", leave_stay_point_voice=" + this.leave_stay_point_voice + ", arrive_recycle_point_voice=" + this.arrive_recycle_point_voice + ", auto_complete_sw=" + this.auto_complete_sw + ", auto_complete_time=" + this.auto_complete_time + ", pause_resume_time=" + this.pause_resume_time + ", destinationList=" + this.destinationList + ", pw_protection=" + this.pw_protection + ", animation=" + this.animation + ", page_key=" + this.page_key + ")";
        }

        public final float getSpeed() {
            return this.speed;
        }

        public final boolean getSort_sw() {
            return this.sort_sw;
        }

        public final boolean getRepeat_sw() {
            return this.repeat_sw;
        }

        public final boolean getArrive_stay_point_voice() {
            return this.arrive_stay_point_voice;
        }

        public final boolean getLeave_stay_point_voice() {
            return this.leave_stay_point_voice;
        }

        public final boolean getArrive_recycle_point_voice() {
            return this.arrive_recycle_point_voice;
        }

        public final boolean getAuto_complete_sw() {
            return this.auto_complete_sw;
        }

        public final long getAuto_complete_time() {
            return this.auto_complete_time;
        }

        public final long getPause_resume_time() {
            return this.pause_resume_time;
        }

        public final List<String> getDestinationList() {
            return this.destinationList;
        }

        public final boolean getPw_protection() {
            return this.pw_protection;
        }

        public final boolean getAnimation() {
            return this.animation;
        }

        public final String getPage_key() {
            return this.page_key;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BellaRecyclerTaskConfig(float f, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, long j, long j2, List<String> destinationList, boolean z7, boolean z8, String page_key) {
            super(null);
            Intrinsics.checkParameterIsNotNull(destinationList, "destinationList");
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            this.speed = f;
            this.sort_sw = z;
            this.repeat_sw = z2;
            this.arrive_stay_point_voice = z3;
            this.leave_stay_point_voice = z4;
            this.arrive_recycle_point_voice = z5;
            this.auto_complete_sw = z6;
            this.auto_complete_time = j;
            this.pause_resume_time = j2;
            this.destinationList = destinationList;
            this.pw_protection = z7;
            this.animation = z8;
            this.page_key = page_key;
        }
    }
}
