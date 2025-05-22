package com.pudutech.robot.module.report.track2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: call.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseCallConfig;", "", "()V", "PeanutCallConfig", "PhoenixCallConfig", "Lcom/pudutech/robot/module/report/track2/BaseCallConfig$PeanutCallConfig;", "Lcom/pudutech/robot/module/report/track2/BaseCallConfig$PhoenixCallConfig;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseCallConfig {

    /* compiled from: call.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseCallConfig$PeanutCallConfig;", "Lcom/pudutech/robot/module/report/track2/BaseCallConfig;", "speed", "", "(Ljava/lang/String;)V", "getSpeed", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class PeanutCallConfig extends BaseCallConfig {
        private final String speed;

        public static /* synthetic */ PeanutCallConfig copy$default(PeanutCallConfig peanutCallConfig, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = peanutCallConfig.speed;
            }
            return peanutCallConfig.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getSpeed() {
            return this.speed;
        }

        public final PeanutCallConfig copy(String speed) {
            Intrinsics.checkParameterIsNotNull(speed, "speed");
            return new PeanutCallConfig(speed);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof PeanutCallConfig) && Intrinsics.areEqual(this.speed, ((PeanutCallConfig) other).speed);
            }
            return true;
        }

        public int hashCode() {
            String str = this.speed;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "PeanutCallConfig(speed=" + this.speed + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PeanutCallConfig(String speed) {
            super(null);
            Intrinsics.checkParameterIsNotNull(speed, "speed");
            this.speed = speed;
        }

        public final String getSpeed() {
            return this.speed;
        }
    }

    private BaseCallConfig() {
    }

    public /* synthetic */ BaseCallConfig(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: call.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseCallConfig$PhoenixCallConfig;", "Lcom/pudutech/robot/module/report/track2/BaseCallConfig;", "speed", "", "(Ljava/lang/String;)V", "getSpeed", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class PhoenixCallConfig extends BaseCallConfig {
        private final String speed;

        public static /* synthetic */ PhoenixCallConfig copy$default(PhoenixCallConfig phoenixCallConfig, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = phoenixCallConfig.speed;
            }
            return phoenixCallConfig.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getSpeed() {
            return this.speed;
        }

        public final PhoenixCallConfig copy(String speed) {
            Intrinsics.checkParameterIsNotNull(speed, "speed");
            return new PhoenixCallConfig(speed);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof PhoenixCallConfig) && Intrinsics.areEqual(this.speed, ((PhoenixCallConfig) other).speed);
            }
            return true;
        }

        public int hashCode() {
            String str = this.speed;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "PhoenixCallConfig(speed=" + this.speed + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PhoenixCallConfig(String speed) {
            super(null);
            Intrinsics.checkParameterIsNotNull(speed, "speed");
            this.speed = speed;
        }

        public final String getSpeed() {
            return this.speed;
        }
    }
}
