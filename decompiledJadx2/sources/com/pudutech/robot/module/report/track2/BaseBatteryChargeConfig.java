package com.pudutech.robot.module.report.track2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: charge.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseBatteryChargeConfig;", "", "()V", "PeanutBatteryChargeConfig", "Lcom/pudutech/robot/module/report/track2/BaseBatteryChargeConfig$PeanutBatteryChargeConfig;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseBatteryChargeConfig {
    private BaseBatteryChargeConfig() {
    }

    public /* synthetic */ BaseBatteryChargeConfig(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: charge.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseBatteryChargeConfig$PeanutBatteryChargeConfig;", "Lcom/pudutech/robot/module/report/track2/BaseBatteryChargeConfig;", "charging_type", "", "(Ljava/lang/String;)V", "getCharging_type", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class PeanutBatteryChargeConfig extends BaseBatteryChargeConfig {
        private final String charging_type;

        public static /* synthetic */ PeanutBatteryChargeConfig copy$default(PeanutBatteryChargeConfig peanutBatteryChargeConfig, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = peanutBatteryChargeConfig.charging_type;
            }
            return peanutBatteryChargeConfig.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getCharging_type() {
            return this.charging_type;
        }

        public final PeanutBatteryChargeConfig copy(String charging_type) {
            Intrinsics.checkParameterIsNotNull(charging_type, "charging_type");
            return new PeanutBatteryChargeConfig(charging_type);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof PeanutBatteryChargeConfig) && Intrinsics.areEqual(this.charging_type, ((PeanutBatteryChargeConfig) other).charging_type);
            }
            return true;
        }

        public int hashCode() {
            String str = this.charging_type;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "PeanutBatteryChargeConfig(charging_type=" + this.charging_type + ")";
        }

        public final String getCharging_type() {
            return this.charging_type;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PeanutBatteryChargeConfig(String charging_type) {
            super(null);
            Intrinsics.checkParameterIsNotNull(charging_type, "charging_type");
            this.charging_type = charging_type;
        }
    }
}
