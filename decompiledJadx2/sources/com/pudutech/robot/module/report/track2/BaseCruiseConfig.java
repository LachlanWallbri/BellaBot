package com.pudutech.robot.module.report.track2;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: cruise.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig;", "", "()V", "BellCruiseConfig", "PeanutCruiseConfig", "PhoenixCruiseConfig", "Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig$PeanutCruiseConfig;", "Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig$BellCruiseConfig;", "Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig$PhoenixCruiseConfig;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseCruiseConfig {
    private BaseCruiseConfig() {
    }

    public /* synthetic */ BaseCruiseConfig(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: cruise.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\tHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig$PeanutCruiseConfig;", "Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig;", "auto_solicit", "", "can_interaction", "is_cruise_music", "speed", "", "type", "", "(ZZZLjava/lang/String;I)V", "getAuto_solicit", "()Z", "getCan_interaction", "getSpeed", "()Ljava/lang/String;", "getType", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class PeanutCruiseConfig extends BaseCruiseConfig {
        private final boolean auto_solicit;
        private final boolean can_interaction;
        private final boolean is_cruise_music;
        private final String speed;
        private final int type;

        public static /* synthetic */ PeanutCruiseConfig copy$default(PeanutCruiseConfig peanutCruiseConfig, boolean z, boolean z2, boolean z3, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = peanutCruiseConfig.auto_solicit;
            }
            if ((i2 & 2) != 0) {
                z2 = peanutCruiseConfig.can_interaction;
            }
            boolean z4 = z2;
            if ((i2 & 4) != 0) {
                z3 = peanutCruiseConfig.is_cruise_music;
            }
            boolean z5 = z3;
            if ((i2 & 8) != 0) {
                str = peanutCruiseConfig.speed;
            }
            String str2 = str;
            if ((i2 & 16) != 0) {
                i = peanutCruiseConfig.type;
            }
            return peanutCruiseConfig.copy(z, z4, z5, str2, i);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getAuto_solicit() {
            return this.auto_solicit;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getCan_interaction() {
            return this.can_interaction;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getIs_cruise_music() {
            return this.is_cruise_music;
        }

        /* renamed from: component4, reason: from getter */
        public final String getSpeed() {
            return this.speed;
        }

        /* renamed from: component5, reason: from getter */
        public final int getType() {
            return this.type;
        }

        public final PeanutCruiseConfig copy(boolean auto_solicit, boolean can_interaction, boolean is_cruise_music, String speed, int type) {
            Intrinsics.checkParameterIsNotNull(speed, "speed");
            return new PeanutCruiseConfig(auto_solicit, can_interaction, is_cruise_music, speed, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PeanutCruiseConfig)) {
                return false;
            }
            PeanutCruiseConfig peanutCruiseConfig = (PeanutCruiseConfig) other;
            return this.auto_solicit == peanutCruiseConfig.auto_solicit && this.can_interaction == peanutCruiseConfig.can_interaction && this.is_cruise_music == peanutCruiseConfig.is_cruise_music && Intrinsics.areEqual(this.speed, peanutCruiseConfig.speed) && this.type == peanutCruiseConfig.type;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v11 */
        /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
        public int hashCode() {
            boolean z = this.auto_solicit;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            ?? r2 = this.can_interaction;
            int i2 = r2;
            if (r2 != 0) {
                i2 = 1;
            }
            int i3 = (i + i2) * 31;
            boolean z2 = this.is_cruise_music;
            int i4 = (i3 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
            String str = this.speed;
            return ((i4 + (str != null ? str.hashCode() : 0)) * 31) + this.type;
        }

        public String toString() {
            return "PeanutCruiseConfig(auto_solicit=" + this.auto_solicit + ", can_interaction=" + this.can_interaction + ", is_cruise_music=" + this.is_cruise_music + ", speed=" + this.speed + ", type=" + this.type + ")";
        }

        public final boolean getAuto_solicit() {
            return this.auto_solicit;
        }

        public final boolean getCan_interaction() {
            return this.can_interaction;
        }

        public final boolean is_cruise_music() {
            return this.is_cruise_music;
        }

        public final String getSpeed() {
            return this.speed;
        }

        public final int getType() {
            return this.type;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PeanutCruiseConfig(boolean z, boolean z2, boolean z3, String speed, int i) {
            super(null);
            Intrinsics.checkParameterIsNotNull(speed, "speed");
            this.auto_solicit = z;
            this.can_interaction = z2;
            this.is_cruise_music = z3;
            this.speed = speed;
            this.type = i;
        }
    }

    /* compiled from: cruise.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b%\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0010¢\u0006\u0002\u0010\u0014J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0010HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\tHÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\tHÆ\u0003J\t\u00101\u001a\u00020\rHÆ\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fHÆ\u0003J\u0087\u0001\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u0010HÆ\u0001J\u0013\u00104\u001a\u00020\u00032\b\u00105\u001a\u0004\u0018\u000106HÖ\u0003J\t\u00107\u001a\u00020\rHÖ\u0001J\t\u00108\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\u0013\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0016¨\u00069"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig$BellCruiseConfig;", "Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig;", "music", "", "animation", "speed", "", "have_cruise_display_content", TrackKey.CRUISE_TIME_OF_STAY, "", "cruise_custom_voice", TrackKey.CRUISE_CUSTOM_VOICE_INTERVAL, TrackKey.CRUISE_PATH_ID, "", TrackKey.STAY_POINT_LIST, "", "", "steady", "pw_protection", "page_key", "(ZZFZJZJILjava/util/List;ZZLjava/lang/String;)V", "getAnimation", "()Z", "getCruise_custom_voice", "getCruise_custom_voice_interval", "()J", "getCruise_path_id", "()I", "getCruise_time_of_stay", "getHave_cruise_display_content", "getMusic", "getPage_key", "()Ljava/lang/String;", "getPw_protection", "getSpeed", "()F", "getStay_point_list", "()Ljava/util/List;", "getSteady", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class BellCruiseConfig extends BaseCruiseConfig {
        private final boolean animation;
        private final boolean cruise_custom_voice;
        private final long cruise_custom_voice_interval;
        private final int cruise_path_id;
        private final long cruise_time_of_stay;
        private final boolean have_cruise_display_content;
        private final boolean music;
        private final String page_key;
        private final boolean pw_protection;
        private final float speed;
        private final List<String> stay_point_list;
        private final boolean steady;

        /* renamed from: component1, reason: from getter */
        public final boolean getMusic() {
            return this.music;
        }

        /* renamed from: component10, reason: from getter */
        public final boolean getSteady() {
            return this.steady;
        }

        /* renamed from: component11, reason: from getter */
        public final boolean getPw_protection() {
            return this.pw_protection;
        }

        /* renamed from: component12, reason: from getter */
        public final String getPage_key() {
            return this.page_key;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getAnimation() {
            return this.animation;
        }

        /* renamed from: component3, reason: from getter */
        public final float getSpeed() {
            return this.speed;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getHave_cruise_display_content() {
            return this.have_cruise_display_content;
        }

        /* renamed from: component5, reason: from getter */
        public final long getCruise_time_of_stay() {
            return this.cruise_time_of_stay;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getCruise_custom_voice() {
            return this.cruise_custom_voice;
        }

        /* renamed from: component7, reason: from getter */
        public final long getCruise_custom_voice_interval() {
            return this.cruise_custom_voice_interval;
        }

        /* renamed from: component8, reason: from getter */
        public final int getCruise_path_id() {
            return this.cruise_path_id;
        }

        public final List<String> component9() {
            return this.stay_point_list;
        }

        public final BellCruiseConfig copy(boolean music, boolean animation, float speed, boolean have_cruise_display_content, long cruise_time_of_stay, boolean cruise_custom_voice, long cruise_custom_voice_interval, int cruise_path_id, List<String> stay_point_list, boolean steady, boolean pw_protection, String page_key) {
            Intrinsics.checkParameterIsNotNull(stay_point_list, "stay_point_list");
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            return new BellCruiseConfig(music, animation, speed, have_cruise_display_content, cruise_time_of_stay, cruise_custom_voice, cruise_custom_voice_interval, cruise_path_id, stay_point_list, steady, pw_protection, page_key);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BellCruiseConfig)) {
                return false;
            }
            BellCruiseConfig bellCruiseConfig = (BellCruiseConfig) other;
            return this.music == bellCruiseConfig.music && this.animation == bellCruiseConfig.animation && Float.compare(this.speed, bellCruiseConfig.speed) == 0 && this.have_cruise_display_content == bellCruiseConfig.have_cruise_display_content && this.cruise_time_of_stay == bellCruiseConfig.cruise_time_of_stay && this.cruise_custom_voice == bellCruiseConfig.cruise_custom_voice && this.cruise_custom_voice_interval == bellCruiseConfig.cruise_custom_voice_interval && this.cruise_path_id == bellCruiseConfig.cruise_path_id && Intrinsics.areEqual(this.stay_point_list, bellCruiseConfig.stay_point_list) && this.steady == bellCruiseConfig.steady && this.pw_protection == bellCruiseConfig.pw_protection && Intrinsics.areEqual(this.page_key, bellCruiseConfig.page_key);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v24 */
        /* JADX WARN: Type inference failed for: r0v25 */
        /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v18, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v9, types: [boolean] */
        public int hashCode() {
            boolean z = this.music;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            ?? r2 = this.animation;
            int i2 = r2;
            if (r2 != 0) {
                i2 = 1;
            }
            int floatToIntBits = (((i + i2) * 31) + Float.floatToIntBits(this.speed)) * 31;
            ?? r22 = this.have_cruise_display_content;
            int i3 = r22;
            if (r22 != 0) {
                i3 = 1;
            }
            int i4 = (floatToIntBits + i3) * 31;
            long j = this.cruise_time_of_stay;
            int i5 = (i4 + ((int) (j ^ (j >>> 32)))) * 31;
            ?? r23 = this.cruise_custom_voice;
            int i6 = r23;
            if (r23 != 0) {
                i6 = 1;
            }
            int i7 = (i5 + i6) * 31;
            long j2 = this.cruise_custom_voice_interval;
            int i8 = (((i7 + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.cruise_path_id) * 31;
            List<String> list = this.stay_point_list;
            int hashCode = (i8 + (list != null ? list.hashCode() : 0)) * 31;
            ?? r24 = this.steady;
            int i9 = r24;
            if (r24 != 0) {
                i9 = 1;
            }
            int i10 = (hashCode + i9) * 31;
            boolean z2 = this.pw_protection;
            int i11 = (i10 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
            String str = this.page_key;
            return i11 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "BellCruiseConfig(music=" + this.music + ", animation=" + this.animation + ", speed=" + this.speed + ", have_cruise_display_content=" + this.have_cruise_display_content + ", cruise_time_of_stay=" + this.cruise_time_of_stay + ", cruise_custom_voice=" + this.cruise_custom_voice + ", cruise_custom_voice_interval=" + this.cruise_custom_voice_interval + ", cruise_path_id=" + this.cruise_path_id + ", stay_point_list=" + this.stay_point_list + ", steady=" + this.steady + ", pw_protection=" + this.pw_protection + ", page_key=" + this.page_key + ")";
        }

        public final boolean getMusic() {
            return this.music;
        }

        public final boolean getAnimation() {
            return this.animation;
        }

        public final float getSpeed() {
            return this.speed;
        }

        public final boolean getHave_cruise_display_content() {
            return this.have_cruise_display_content;
        }

        public final long getCruise_time_of_stay() {
            return this.cruise_time_of_stay;
        }

        public final boolean getCruise_custom_voice() {
            return this.cruise_custom_voice;
        }

        public final long getCruise_custom_voice_interval() {
            return this.cruise_custom_voice_interval;
        }

        public final int getCruise_path_id() {
            return this.cruise_path_id;
        }

        public final List<String> getStay_point_list() {
            return this.stay_point_list;
        }

        public final boolean getSteady() {
            return this.steady;
        }

        public final boolean getPw_protection() {
            return this.pw_protection;
        }

        public final String getPage_key() {
            return this.page_key;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BellCruiseConfig(boolean z, boolean z2, float f, boolean z3, long j, boolean z4, long j2, int i, List<String> stay_point_list, boolean z5, boolean z6, String page_key) {
            super(null);
            Intrinsics.checkParameterIsNotNull(stay_point_list, "stay_point_list");
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            this.music = z;
            this.animation = z2;
            this.speed = f;
            this.have_cruise_display_content = z3;
            this.cruise_time_of_stay = j;
            this.cruise_custom_voice = z4;
            this.cruise_custom_voice_interval = j2;
            this.cruise_path_id = i;
            this.stay_point_list = stay_point_list;
            this.steady = z5;
            this.pw_protection = z6;
            this.page_key = page_key;
        }
    }

    /* compiled from: cruise.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b \n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0007¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0010HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0007HÆ\u0003J\t\u0010(\u001a\u00020\u0007HÆ\u0003J\t\u0010)\u001a\u00020\u0007HÆ\u0003J\t\u0010*\u001a\u00020\u0007HÆ\u0003J\t\u0010+\u001a\u00020\u0007HÆ\u0003J\t\u0010,\u001a\u00020\rHÆ\u0003J\t\u0010-\u001a\u00020\rHÆ\u0003J}\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0007HÆ\u0001J\u0013\u0010/\u001a\u00020\u00072\b\u00100\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014¨\u00065"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig$PhoenixCruiseConfig;", "Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig;", "path_name", "", TrackKey.STAY_POINT_LIST, "", "steady", "", "cruise_voice", "cruise_music", "arrive_voice", "auto_cruise", TrackKey.CRUISE_TIME_OF_STAY, "", "pause_resume_time", "speed", "", "pw_protection", "(Ljava/lang/String;Ljava/util/List;ZZZZZJJFZ)V", "getArrive_voice", "()Z", "getAuto_cruise", "getCruise_music", "getCruise_time_of_stay", "()J", "getCruise_voice", "getPath_name", "()Ljava/lang/String;", "getPause_resume_time", "getPw_protection", "getSpeed", "()F", "getStay_point_list", "()Ljava/util/List;", "getSteady", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class PhoenixCruiseConfig extends BaseCruiseConfig {
        private final boolean arrive_voice;
        private final boolean auto_cruise;
        private final boolean cruise_music;
        private final long cruise_time_of_stay;
        private final boolean cruise_voice;
        private final String path_name;
        private final long pause_resume_time;
        private final boolean pw_protection;
        private final float speed;
        private final List<String> stay_point_list;
        private final boolean steady;

        /* renamed from: component1, reason: from getter */
        public final String getPath_name() {
            return this.path_name;
        }

        /* renamed from: component10, reason: from getter */
        public final float getSpeed() {
            return this.speed;
        }

        /* renamed from: component11, reason: from getter */
        public final boolean getPw_protection() {
            return this.pw_protection;
        }

        public final List<String> component2() {
            return this.stay_point_list;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getSteady() {
            return this.steady;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getCruise_voice() {
            return this.cruise_voice;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getCruise_music() {
            return this.cruise_music;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getArrive_voice() {
            return this.arrive_voice;
        }

        /* renamed from: component7, reason: from getter */
        public final boolean getAuto_cruise() {
            return this.auto_cruise;
        }

        /* renamed from: component8, reason: from getter */
        public final long getCruise_time_of_stay() {
            return this.cruise_time_of_stay;
        }

        /* renamed from: component9, reason: from getter */
        public final long getPause_resume_time() {
            return this.pause_resume_time;
        }

        public final PhoenixCruiseConfig copy(String path_name, List<String> stay_point_list, boolean steady, boolean cruise_voice, boolean cruise_music, boolean arrive_voice, boolean auto_cruise, long cruise_time_of_stay, long pause_resume_time, float speed, boolean pw_protection) {
            Intrinsics.checkParameterIsNotNull(path_name, "path_name");
            Intrinsics.checkParameterIsNotNull(stay_point_list, "stay_point_list");
            return new PhoenixCruiseConfig(path_name, stay_point_list, steady, cruise_voice, cruise_music, arrive_voice, auto_cruise, cruise_time_of_stay, pause_resume_time, speed, pw_protection);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PhoenixCruiseConfig)) {
                return false;
            }
            PhoenixCruiseConfig phoenixCruiseConfig = (PhoenixCruiseConfig) other;
            return Intrinsics.areEqual(this.path_name, phoenixCruiseConfig.path_name) && Intrinsics.areEqual(this.stay_point_list, phoenixCruiseConfig.stay_point_list) && this.steady == phoenixCruiseConfig.steady && this.cruise_voice == phoenixCruiseConfig.cruise_voice && this.cruise_music == phoenixCruiseConfig.cruise_music && this.arrive_voice == phoenixCruiseConfig.arrive_voice && this.auto_cruise == phoenixCruiseConfig.auto_cruise && this.cruise_time_of_stay == phoenixCruiseConfig.cruise_time_of_stay && this.pause_resume_time == phoenixCruiseConfig.pause_resume_time && Float.compare(this.speed, phoenixCruiseConfig.speed) == 0 && this.pw_protection == phoenixCruiseConfig.pw_protection;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            String str = this.path_name;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            List<String> list = this.stay_point_list;
            int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
            boolean z = this.steady;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode2 + i) * 31;
            boolean z2 = this.cruise_voice;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            int i4 = (i2 + i3) * 31;
            boolean z3 = this.cruise_music;
            int i5 = z3;
            if (z3 != 0) {
                i5 = 1;
            }
            int i6 = (i4 + i5) * 31;
            boolean z4 = this.arrive_voice;
            int i7 = z4;
            if (z4 != 0) {
                i7 = 1;
            }
            int i8 = (i6 + i7) * 31;
            boolean z5 = this.auto_cruise;
            int i9 = z5;
            if (z5 != 0) {
                i9 = 1;
            }
            long j = this.cruise_time_of_stay;
            int i10 = (((i8 + i9) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
            long j2 = this.pause_resume_time;
            int floatToIntBits = (((i10 + ((int) (j2 ^ (j2 >>> 32)))) * 31) + Float.floatToIntBits(this.speed)) * 31;
            boolean z6 = this.pw_protection;
            int i11 = z6;
            if (z6 != 0) {
                i11 = 1;
            }
            return floatToIntBits + i11;
        }

        public String toString() {
            return "PhoenixCruiseConfig(path_name=" + this.path_name + ", stay_point_list=" + this.stay_point_list + ", steady=" + this.steady + ", cruise_voice=" + this.cruise_voice + ", cruise_music=" + this.cruise_music + ", arrive_voice=" + this.arrive_voice + ", auto_cruise=" + this.auto_cruise + ", cruise_time_of_stay=" + this.cruise_time_of_stay + ", pause_resume_time=" + this.pause_resume_time + ", speed=" + this.speed + ", pw_protection=" + this.pw_protection + ")";
        }

        public final String getPath_name() {
            return this.path_name;
        }

        public final List<String> getStay_point_list() {
            return this.stay_point_list;
        }

        public final boolean getSteady() {
            return this.steady;
        }

        public final boolean getCruise_voice() {
            return this.cruise_voice;
        }

        public final boolean getCruise_music() {
            return this.cruise_music;
        }

        public final boolean getArrive_voice() {
            return this.arrive_voice;
        }

        public final boolean getAuto_cruise() {
            return this.auto_cruise;
        }

        public final long getCruise_time_of_stay() {
            return this.cruise_time_of_stay;
        }

        public final long getPause_resume_time() {
            return this.pause_resume_time;
        }

        public final float getSpeed() {
            return this.speed;
        }

        public final boolean getPw_protection() {
            return this.pw_protection;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PhoenixCruiseConfig(String path_name, List<String> stay_point_list, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, long j, long j2, float f, boolean z6) {
            super(null);
            Intrinsics.checkParameterIsNotNull(path_name, "path_name");
            Intrinsics.checkParameterIsNotNull(stay_point_list, "stay_point_list");
            this.path_name = path_name;
            this.stay_point_list = stay_point_list;
            this.steady = z;
            this.cruise_voice = z2;
            this.cruise_music = z3;
            this.arrive_voice = z4;
            this.auto_cruise = z5;
            this.cruise_time_of_stay = j;
            this.pause_resume_time = j2;
            this.speed = f;
            this.pw_protection = z6;
        }
    }
}
