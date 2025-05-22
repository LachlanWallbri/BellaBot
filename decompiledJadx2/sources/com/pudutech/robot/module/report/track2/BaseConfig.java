package com.pudutech.robot.module.report.track2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: config.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\u0003\u0004\u0005\u0006\u0007\b\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseConfig;", "", "()V", "DownRGBDConfig", "ESPConfig", "LegDetectionConfig", "LocalizationLostThresholdConfig", "MagnetFallDetConfig", "MarkerCameraConfig", "SlipConfig", "Lcom/pudutech/robot/module/report/track2/BaseConfig$DownRGBDConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig$MagnetFallDetConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig$ESPConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig$LegDetectionConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig$SlipConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig$MarkerCameraConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig$LocalizationLostThresholdConfig;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseConfig {

    /* compiled from: config.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseConfig$DownRGBDConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig;", "algorithm_type", "", "fall_det_status", "(II)V", "getAlgorithm_type", "()I", "getFall_det_status", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class DownRGBDConfig extends BaseConfig {
        private final int algorithm_type;
        private final int fall_det_status;

        public static /* synthetic */ DownRGBDConfig copy$default(DownRGBDConfig downRGBDConfig, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = downRGBDConfig.algorithm_type;
            }
            if ((i3 & 2) != 0) {
                i2 = downRGBDConfig.fall_det_status;
            }
            return downRGBDConfig.copy(i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getAlgorithm_type() {
            return this.algorithm_type;
        }

        /* renamed from: component2, reason: from getter */
        public final int getFall_det_status() {
            return this.fall_det_status;
        }

        public final DownRGBDConfig copy(int algorithm_type, int fall_det_status) {
            return new DownRGBDConfig(algorithm_type, fall_det_status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownRGBDConfig)) {
                return false;
            }
            DownRGBDConfig downRGBDConfig = (DownRGBDConfig) other;
            return this.algorithm_type == downRGBDConfig.algorithm_type && this.fall_det_status == downRGBDConfig.fall_det_status;
        }

        public int hashCode() {
            return (this.algorithm_type * 31) + this.fall_det_status;
        }

        public String toString() {
            return "DownRGBDConfig(algorithm_type=" + this.algorithm_type + ", fall_det_status=" + this.fall_det_status + ")";
        }

        public DownRGBDConfig(int i, int i2) {
            super(null);
            this.algorithm_type = i;
            this.fall_det_status = i2;
        }

        public final int getAlgorithm_type() {
            return this.algorithm_type;
        }

        public final int getFall_det_status() {
            return this.fall_det_status;
        }
    }

    private BaseConfig() {
    }

    public /* synthetic */ BaseConfig(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: config.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseConfig$MagnetFallDetConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig;", "have_magnet", "", "fall_det_status", "", "(ZI)V", "getFall_det_status", "()I", "getHave_magnet", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class MagnetFallDetConfig extends BaseConfig {
        private final int fall_det_status;
        private final boolean have_magnet;

        public static /* synthetic */ MagnetFallDetConfig copy$default(MagnetFallDetConfig magnetFallDetConfig, boolean z, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = magnetFallDetConfig.have_magnet;
            }
            if ((i2 & 2) != 0) {
                i = magnetFallDetConfig.fall_det_status;
            }
            return magnetFallDetConfig.copy(z, i);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getHave_magnet() {
            return this.have_magnet;
        }

        /* renamed from: component2, reason: from getter */
        public final int getFall_det_status() {
            return this.fall_det_status;
        }

        public final MagnetFallDetConfig copy(boolean have_magnet, int fall_det_status) {
            return new MagnetFallDetConfig(have_magnet, fall_det_status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MagnetFallDetConfig)) {
                return false;
            }
            MagnetFallDetConfig magnetFallDetConfig = (MagnetFallDetConfig) other;
            return this.have_magnet == magnetFallDetConfig.have_magnet && this.fall_det_status == magnetFallDetConfig.fall_det_status;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.have_magnet;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return (r0 * 31) + this.fall_det_status;
        }

        public String toString() {
            return "MagnetFallDetConfig(have_magnet=" + this.have_magnet + ", fall_det_status=" + this.fall_det_status + ")";
        }

        public MagnetFallDetConfig(boolean z, int i) {
            super(null);
            this.have_magnet = z;
            this.fall_det_status = i;
        }

        public final int getFall_det_status() {
            return this.fall_det_status;
        }

        public final boolean getHave_magnet() {
            return this.have_magnet;
        }
    }

    /* compiled from: config.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseConfig$ESPConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig;", "enable", "", "channel_id", "", "(ZI)V", "getChannel_id", "()I", "getEnable", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class ESPConfig extends BaseConfig {
        private final int channel_id;
        private final boolean enable;

        public static /* synthetic */ ESPConfig copy$default(ESPConfig eSPConfig, boolean z, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = eSPConfig.enable;
            }
            if ((i2 & 2) != 0) {
                i = eSPConfig.channel_id;
            }
            return eSPConfig.copy(z, i);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getEnable() {
            return this.enable;
        }

        /* renamed from: component2, reason: from getter */
        public final int getChannel_id() {
            return this.channel_id;
        }

        public final ESPConfig copy(boolean enable, int channel_id) {
            return new ESPConfig(enable, channel_id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ESPConfig)) {
                return false;
            }
            ESPConfig eSPConfig = (ESPConfig) other;
            return this.enable == eSPConfig.enable && this.channel_id == eSPConfig.channel_id;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.enable;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return (r0 * 31) + this.channel_id;
        }

        public String toString() {
            return "ESPConfig(enable=" + this.enable + ", channel_id=" + this.channel_id + ")";
        }

        public ESPConfig(boolean z, int i) {
            super(null);
            this.enable = z;
            this.channel_id = i;
        }

        public final int getChannel_id() {
            return this.channel_id;
        }

        public final boolean getEnable() {
            return this.enable;
        }
    }

    /* compiled from: config.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseConfig$LegDetectionConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig;", "near_dist", "", "max_dist", "min_valid_angle", "max_valid_angle", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMax_dist", "()Ljava/lang/String;", "getMax_valid_angle", "getMin_valid_angle", "getNear_dist", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class LegDetectionConfig extends BaseConfig {
        private final String max_dist;
        private final String max_valid_angle;
        private final String min_valid_angle;
        private final String near_dist;

        public static /* synthetic */ LegDetectionConfig copy$default(LegDetectionConfig legDetectionConfig, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = legDetectionConfig.near_dist;
            }
            if ((i & 2) != 0) {
                str2 = legDetectionConfig.max_dist;
            }
            if ((i & 4) != 0) {
                str3 = legDetectionConfig.min_valid_angle;
            }
            if ((i & 8) != 0) {
                str4 = legDetectionConfig.max_valid_angle;
            }
            return legDetectionConfig.copy(str, str2, str3, str4);
        }

        /* renamed from: component1, reason: from getter */
        public final String getNear_dist() {
            return this.near_dist;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMax_dist() {
            return this.max_dist;
        }

        /* renamed from: component3, reason: from getter */
        public final String getMin_valid_angle() {
            return this.min_valid_angle;
        }

        /* renamed from: component4, reason: from getter */
        public final String getMax_valid_angle() {
            return this.max_valid_angle;
        }

        public final LegDetectionConfig copy(String near_dist, String max_dist, String min_valid_angle, String max_valid_angle) {
            Intrinsics.checkParameterIsNotNull(near_dist, "near_dist");
            Intrinsics.checkParameterIsNotNull(max_dist, "max_dist");
            Intrinsics.checkParameterIsNotNull(min_valid_angle, "min_valid_angle");
            Intrinsics.checkParameterIsNotNull(max_valid_angle, "max_valid_angle");
            return new LegDetectionConfig(near_dist, max_dist, min_valid_angle, max_valid_angle);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LegDetectionConfig)) {
                return false;
            }
            LegDetectionConfig legDetectionConfig = (LegDetectionConfig) other;
            return Intrinsics.areEqual(this.near_dist, legDetectionConfig.near_dist) && Intrinsics.areEqual(this.max_dist, legDetectionConfig.max_dist) && Intrinsics.areEqual(this.min_valid_angle, legDetectionConfig.min_valid_angle) && Intrinsics.areEqual(this.max_valid_angle, legDetectionConfig.max_valid_angle);
        }

        public int hashCode() {
            String str = this.near_dist;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.max_dist;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.min_valid_angle;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.max_valid_angle;
            return hashCode3 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            return "LegDetectionConfig(near_dist=" + this.near_dist + ", max_dist=" + this.max_dist + ", min_valid_angle=" + this.min_valid_angle + ", max_valid_angle=" + this.max_valid_angle + ")";
        }

        public final String getNear_dist() {
            return this.near_dist;
        }

        public final String getMax_dist() {
            return this.max_dist;
        }

        public final String getMin_valid_angle() {
            return this.min_valid_angle;
        }

        public final String getMax_valid_angle() {
            return this.max_valid_angle;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LegDetectionConfig(String near_dist, String max_dist, String min_valid_angle, String max_valid_angle) {
            super(null);
            Intrinsics.checkParameterIsNotNull(near_dist, "near_dist");
            Intrinsics.checkParameterIsNotNull(max_dist, "max_dist");
            Intrinsics.checkParameterIsNotNull(min_valid_angle, "min_valid_angle");
            Intrinsics.checkParameterIsNotNull(max_valid_angle, "max_valid_angle");
            this.near_dist = near_dist;
            this.max_dist = max_dist;
            this.min_valid_angle = min_valid_angle;
            this.max_valid_angle = max_valid_angle;
        }
    }

    /* compiled from: config.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseConfig$SlipConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig;", "use_sm_opt_angle", "", "slip_use_scan_match", "(II)V", "getSlip_use_scan_match", "()I", "getUse_sm_opt_angle", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class SlipConfig extends BaseConfig {
        private final int slip_use_scan_match;
        private final int use_sm_opt_angle;

        public static /* synthetic */ SlipConfig copy$default(SlipConfig slipConfig, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = slipConfig.use_sm_opt_angle;
            }
            if ((i3 & 2) != 0) {
                i2 = slipConfig.slip_use_scan_match;
            }
            return slipConfig.copy(i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getUse_sm_opt_angle() {
            return this.use_sm_opt_angle;
        }

        /* renamed from: component2, reason: from getter */
        public final int getSlip_use_scan_match() {
            return this.slip_use_scan_match;
        }

        public final SlipConfig copy(int use_sm_opt_angle, int slip_use_scan_match) {
            return new SlipConfig(use_sm_opt_angle, slip_use_scan_match);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SlipConfig)) {
                return false;
            }
            SlipConfig slipConfig = (SlipConfig) other;
            return this.use_sm_opt_angle == slipConfig.use_sm_opt_angle && this.slip_use_scan_match == slipConfig.slip_use_scan_match;
        }

        public int hashCode() {
            return (this.use_sm_opt_angle * 31) + this.slip_use_scan_match;
        }

        public String toString() {
            return "SlipConfig(use_sm_opt_angle=" + this.use_sm_opt_angle + ", slip_use_scan_match=" + this.slip_use_scan_match + ")";
        }

        public SlipConfig(int i, int i2) {
            super(null);
            this.use_sm_opt_angle = i;
            this.slip_use_scan_match = i2;
        }

        public final int getSlip_use_scan_match() {
            return this.slip_use_scan_match;
        }

        public final int getUse_sm_opt_angle() {
            return this.use_sm_opt_angle;
        }
    }

    /* compiled from: config.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseConfig$MarkerCameraConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig;", "mark_enable", "", "(Z)V", "getMark_enable", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class MarkerCameraConfig extends BaseConfig {
        private final boolean mark_enable;

        public static /* synthetic */ MarkerCameraConfig copy$default(MarkerCameraConfig markerCameraConfig, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = markerCameraConfig.mark_enable;
            }
            return markerCameraConfig.copy(z);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getMark_enable() {
            return this.mark_enable;
        }

        public final MarkerCameraConfig copy(boolean mark_enable) {
            return new MarkerCameraConfig(mark_enable);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof MarkerCameraConfig) && this.mark_enable == ((MarkerCameraConfig) other).mark_enable;
            }
            return true;
        }

        public int hashCode() {
            boolean z = this.mark_enable;
            if (z) {
                return 1;
            }
            return z ? 1 : 0;
        }

        public String toString() {
            return "MarkerCameraConfig(mark_enable=" + this.mark_enable + ")";
        }

        public MarkerCameraConfig(boolean z) {
            super(null);
            this.mark_enable = z;
        }

        public final boolean getMark_enable() {
            return this.mark_enable;
        }
    }

    /* compiled from: config.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseConfig$LocalizationLostThresholdConfig;", "Lcom/pudutech/robot/module/report/track2/BaseConfig;", "lost_particle_score_threshold", "", "lost_particle_count_threshold", "", "(DI)V", "getLost_particle_count_threshold", "()I", "getLost_particle_score_threshold", "()D", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class LocalizationLostThresholdConfig extends BaseConfig {
        private final int lost_particle_count_threshold;
        private final double lost_particle_score_threshold;

        public static /* synthetic */ LocalizationLostThresholdConfig copy$default(LocalizationLostThresholdConfig localizationLostThresholdConfig, double d, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                d = localizationLostThresholdConfig.lost_particle_score_threshold;
            }
            if ((i2 & 2) != 0) {
                i = localizationLostThresholdConfig.lost_particle_count_threshold;
            }
            return localizationLostThresholdConfig.copy(d, i);
        }

        /* renamed from: component1, reason: from getter */
        public final double getLost_particle_score_threshold() {
            return this.lost_particle_score_threshold;
        }

        /* renamed from: component2, reason: from getter */
        public final int getLost_particle_count_threshold() {
            return this.lost_particle_count_threshold;
        }

        public final LocalizationLostThresholdConfig copy(double lost_particle_score_threshold, int lost_particle_count_threshold) {
            return new LocalizationLostThresholdConfig(lost_particle_score_threshold, lost_particle_count_threshold);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LocalizationLostThresholdConfig)) {
                return false;
            }
            LocalizationLostThresholdConfig localizationLostThresholdConfig = (LocalizationLostThresholdConfig) other;
            return Double.compare(this.lost_particle_score_threshold, localizationLostThresholdConfig.lost_particle_score_threshold) == 0 && this.lost_particle_count_threshold == localizationLostThresholdConfig.lost_particle_count_threshold;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.lost_particle_score_threshold);
            return (((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + this.lost_particle_count_threshold;
        }

        public String toString() {
            return "LocalizationLostThresholdConfig(lost_particle_score_threshold=" + this.lost_particle_score_threshold + ", lost_particle_count_threshold=" + this.lost_particle_count_threshold + ")";
        }

        public final double getLost_particle_score_threshold() {
            return this.lost_particle_score_threshold;
        }

        public final int getLost_particle_count_threshold() {
            return this.lost_particle_count_threshold;
        }

        public LocalizationLostThresholdConfig(double d, int i) {
            super(null);
            this.lost_particle_score_threshold = d;
            this.lost_particle_count_threshold = i;
        }
    }
}
