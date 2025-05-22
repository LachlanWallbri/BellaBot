package com.pudutech.mirsdk.mirhardware;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.base.Monitorable;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.LocalizationListener;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotStatus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002+,B\u0005¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\tR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\tR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\tR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\tR \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\t\"\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\tR\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\tR\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\tR\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u0006¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\t¨\u0006-"}, m3961d2 = {"Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "", "()V", "TAG", "", "battery", "Lcom/pudutech/mirsdk/base/Monitorable;", "Lcom/pudutech/mirsdk/mirhardware/BatteryState;", "getBattery", "()Lcom/pudutech/mirsdk/base/Monitorable;", "batteryFloorLevelLimit", "Lcom/pudutech/mirsdk/mirhardware/RobotStatus$RobotBatteryFloorLevelLimit;", "getBatteryFloorLevelLimit", "bumperCheckStatus", "", "getBumperCheckStatus", "cameraOpened", "getCameraOpened", "collisionStatus", "getCollisionStatus", "disinfectionOn", "getDisinfectionOn", "emergencyKeyPressed", "getEmergencyKeyPressed", "isCameraIRDLEDLightOn", "lidarOpened", "getLidarOpened", "setLidarOpened", "(Lcom/pudutech/mirsdk/base/Monitorable;)V", "localizationListener", "Lcom/pudutech/mirsdk/mircore/LocalizationListener$Stub;", "getLocalizationListener", "()Lcom/pudutech/mirsdk/mircore/LocalizationListener$Stub;", "lockMotorStatus", "getLockMotorStatus", "pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getPose", "rgbdOpened", "getRgbdOpened", "speed", "Lcom/pudutech/mirsdk/mirhardware/RobotStatus$RobotSpeed;", "getSpeed", "RobotBatteryFloorLevelLimit", "RobotSpeed", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotStatus {
    private final Monitorable<RobotBatteryFloorLevelLimit> batteryFloorLevelLimit;
    private final String TAG = "RobotStatus";
    private final Monitorable<BatteryState> battery = new Monitorable<>(new BatteryState());
    private final Monitorable<Boolean> isCameraIRDLEDLightOn = new Monitorable<>(false);
    private final Monitorable<RobotSpeed> speed = new Monitorable<>(new RobotSpeed(0.0d, 0.0d, 3, null));
    private Monitorable<Boolean> lidarOpened = new Monitorable<>(false);
    private final Monitorable<Boolean> rgbdOpened = new Monitorable<>(false);
    private final Monitorable<Boolean> cameraOpened = new Monitorable<>(false);
    private final Monitorable<Boolean> collisionStatus = new Monitorable<>(false);
    private final Monitorable<Boolean> disinfectionOn = new Monitorable<>(false);
    private final Monitorable<Boolean> emergencyKeyPressed = new Monitorable<>(false);
    private final Monitorable<Boolean> lockMotorStatus = new Monitorable<>(false);
    private final Monitorable<Boolean> bumperCheckStatus = new Monitorable<>(false);
    private final Monitorable<Vector3d> pose = new Monitorable<>(new Vector3d(0.0d, 0.0d, 0.0d, 7, null));
    private final LocalizationListener.Stub localizationListener = new LocalizationListener.Stub() { // from class: com.pudutech.mirsdk.mirhardware.RobotStatus$localizationListener$1
        @Override // com.pudutech.mirsdk.mircore.LocalizationListener
        public void updateRobotDirection(Vector3d direction) {
        }

        @Override // com.pudutech.mirsdk.mircore.LocalizationListener
        public void updateRobotPosition(Vector3d _pose) {
            String str;
            if (_pose != null) {
                str = RobotStatus.this.TAG;
                Pdlog.m3276v(str, "updateRobotPosition " + _pose.getX() + ',' + _pose.getY() + ',' + _pose.getZ());
                RobotStatus.this.getPose().setValue(_pose);
                RobotStatus.this.getPose().invokeChange();
            }
        }
    };

    public RobotStatus() {
        int i = 0;
        this.batteryFloorLevelLimit = new Monitorable<>(new RobotBatteryFloorLevelLimit(i, i, 3, null));
    }

    public final Monitorable<BatteryState> getBattery() {
        return this.battery;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: RobotStatus.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/mirsdk/mirhardware/RobotStatus$RobotSpeed;", "", "line", "", "angular", "(DD)V", "getAngular", "()D", "setAngular", "(D)V", "getLine", "setLine", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class RobotSpeed {
        private double angular;
        private double line;

        public RobotSpeed() {
            this(0.0d, 0.0d, 3, null);
        }

        public static /* synthetic */ RobotSpeed copy$default(RobotSpeed robotSpeed, double d, double d2, int i, Object obj) {
            if ((i & 1) != 0) {
                d = robotSpeed.line;
            }
            if ((i & 2) != 0) {
                d2 = robotSpeed.angular;
            }
            return robotSpeed.copy(d, d2);
        }

        /* renamed from: component1, reason: from getter */
        public final double getLine() {
            return this.line;
        }

        /* renamed from: component2, reason: from getter */
        public final double getAngular() {
            return this.angular;
        }

        public final RobotSpeed copy(double line, double angular) {
            return new RobotSpeed(line, angular);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RobotSpeed)) {
                return false;
            }
            RobotSpeed robotSpeed = (RobotSpeed) other;
            return Double.compare(this.line, robotSpeed.line) == 0 && Double.compare(this.angular, robotSpeed.angular) == 0;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.line);
            int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31;
            long doubleToLongBits2 = Double.doubleToLongBits(this.angular);
            return i + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
        }

        public String toString() {
            return "RobotSpeed(line=" + this.line + ", angular=" + this.angular + ")";
        }

        public RobotSpeed(double d, double d2) {
            this.line = d;
            this.angular = d2;
        }

        public /* synthetic */ RobotSpeed(double d, double d2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 0.0d : d, (i & 2) != 0 ? 0.0d : d2);
        }

        public final double getAngular() {
            return this.angular;
        }

        public final double getLine() {
            return this.line;
        }

        public final void setAngular(double d) {
            this.angular = d;
        }

        public final void setLine(double d) {
            this.line = d;
        }
    }

    public final Monitorable<Boolean> isCameraIRDLEDLightOn() {
        return this.isCameraIRDLEDLightOn;
    }

    public final Monitorable<RobotSpeed> getSpeed() {
        return this.speed;
    }

    public final Monitorable<Boolean> getLidarOpened() {
        return this.lidarOpened;
    }

    public final void setLidarOpened(Monitorable<Boolean> monitorable) {
        Intrinsics.checkParameterIsNotNull(monitorable, "<set-?>");
        this.lidarOpened = monitorable;
    }

    public final Monitorable<Boolean> getRgbdOpened() {
        return this.rgbdOpened;
    }

    public final Monitorable<Boolean> getCameraOpened() {
        return this.cameraOpened;
    }

    public final Monitorable<Boolean> getCollisionStatus() {
        return this.collisionStatus;
    }

    public final Monitorable<Boolean> getDisinfectionOn() {
        return this.disinfectionOn;
    }

    public final Monitorable<Boolean> getEmergencyKeyPressed() {
        return this.emergencyKeyPressed;
    }

    public final Monitorable<Boolean> getLockMotorStatus() {
        return this.lockMotorStatus;
    }

    /* compiled from: RobotStatus.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/mirhardware/RobotStatus$RobotBatteryFloorLevelLimit;", "", "status", "", "level", "(II)V", "getLevel", "()I", "setLevel", "(I)V", "getStatus", "setStatus", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final /* data */ class RobotBatteryFloorLevelLimit {
        private int level;
        private int status;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public RobotBatteryFloorLevelLimit() {
            this(r0, r0, 3, null);
            int i = 0;
        }

        public static /* synthetic */ RobotBatteryFloorLevelLimit copy$default(RobotBatteryFloorLevelLimit robotBatteryFloorLevelLimit, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = robotBatteryFloorLevelLimit.status;
            }
            if ((i3 & 2) != 0) {
                i2 = robotBatteryFloorLevelLimit.level;
            }
            return robotBatteryFloorLevelLimit.copy(i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getStatus() {
            return this.status;
        }

        /* renamed from: component2, reason: from getter */
        public final int getLevel() {
            return this.level;
        }

        public final RobotBatteryFloorLevelLimit copy(int status, int level) {
            return new RobotBatteryFloorLevelLimit(status, level);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RobotBatteryFloorLevelLimit)) {
                return false;
            }
            RobotBatteryFloorLevelLimit robotBatteryFloorLevelLimit = (RobotBatteryFloorLevelLimit) other;
            return this.status == robotBatteryFloorLevelLimit.status && this.level == robotBatteryFloorLevelLimit.level;
        }

        public int hashCode() {
            return (this.status * 31) + this.level;
        }

        public String toString() {
            return "RobotBatteryFloorLevelLimit(status=" + this.status + ", level=" + this.level + ")";
        }

        public RobotBatteryFloorLevelLimit(int i, int i2) {
            this.status = i;
            this.level = i2;
        }

        public /* synthetic */ RobotBatteryFloorLevelLimit(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 1 : i, (i3 & 2) != 0 ? 5 : i2);
        }

        public final int getLevel() {
            return this.level;
        }

        public final int getStatus() {
            return this.status;
        }

        public final void setLevel(int i) {
            this.level = i;
        }

        public final void setStatus(int i) {
            this.status = i;
        }
    }

    public final Monitorable<Boolean> getBumperCheckStatus() {
        return this.bumperCheckStatus;
    }

    public final Monitorable<RobotBatteryFloorLevelLimit> getBatteryFloorLevelLimit() {
        return this.batteryFloorLevelLimit;
    }

    public final Monitorable<Vector3d> getPose() {
        return this.pose;
    }

    public final LocalizationListener.Stub getLocalizationListener() {
        return this.localizationListener;
    }
}
