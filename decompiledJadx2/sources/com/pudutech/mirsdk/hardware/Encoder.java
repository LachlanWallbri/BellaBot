package com.pudutech.mirsdk.hardware;

import androidx.room.FtsOptions;
import com.pudutech.base.CommonKt;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.module.report.track2.TrackKey;
import defpackage.C$r8$backportedMethods$utility$Double$1$hashCode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: Encoder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\n\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002()B\u0005¢\u0006\u0002\u0010\u0002J.\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0018J\u0016\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\fR\u000e\u0010\u0016\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006*"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/Encoder;", "", "()V", "AXIAL_MIN_ZERO", "", "CONVERT_TO_METRIC", "TANGENTIAL_MIN_ZERO", "_distance", "Lcom/pudutech/mirsdk/hardware/Encoder$DoubleWheel;", "_speed", "Lcom/pudutech/mirsdk/hardware/Encoder$Speed;", "get_speed", "()Lcom/pudutech/mirsdk/hardware/Encoder$Speed;", TrackKey.SET_SPEED, "(Lcom/pudutech/mirsdk/hardware/Encoder$Speed;)V", "cpr", "", "distance", "getDistance", "()Lcom/pudutech/mirsdk/hardware/Encoder$DoubleWheel;", "speed", "getSpeed", "wheelPerimeter", "wheelSpacing", "", "getWheelSpacing", "()F", "setWheelSpacing", "(F)V", "setConfigure", "", "encoderCpr", "ratio", FtsOptions.TOKENIZER_SIMPLE, "perimeter_m", "_wheelSpacing", "update", "encoderLeft", "", "encoderRight", "DoubleWheel", "Speed", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class Encoder {
    private double AXIAL_MIN_ZERO;
    private double CONVERT_TO_METRIC;
    private double TANGENTIAL_MIN_ZERO;
    private float wheelSpacing = 0.3836f;
    private int cpr = 2400;
    private double wheelPerimeter = 0.44d;
    private final DoubleWheel _distance = new DoubleWheel(0.0d, 0.0d, 3, null);
    private Speed _speed = new Speed(0.0d, 0.0d, 3, null);

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: Encoder.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/Encoder$DoubleWheel;", "", "left", "", "right", "(DD)V", "getLeft", "()D", "setLeft", "(D)V", "getRight", "setRight", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final /* data */ class DoubleWheel {
        private double left;
        private double right;

        public DoubleWheel() {
            this(0.0d, 0.0d, 3, null);
        }

        public static /* synthetic */ DoubleWheel copy$default(DoubleWheel doubleWheel, double d, double d2, int i, Object obj) {
            if ((i & 1) != 0) {
                d = doubleWheel.left;
            }
            if ((i & 2) != 0) {
                d2 = doubleWheel.right;
            }
            return doubleWheel.copy(d, d2);
        }

        /* renamed from: component1, reason: from getter */
        public final double getLeft() {
            return this.left;
        }

        /* renamed from: component2, reason: from getter */
        public final double getRight() {
            return this.right;
        }

        public final DoubleWheel copy(double left, double right) {
            return new DoubleWheel(left, right);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DoubleWheel)) {
                return false;
            }
            DoubleWheel doubleWheel = (DoubleWheel) other;
            return Double.compare(this.left, doubleWheel.left) == 0 && Double.compare(this.right, doubleWheel.right) == 0;
        }

        public int hashCode() {
            return (C$r8$backportedMethods$utility$Double$1$hashCode.hashCode(this.left) * 31) + C$r8$backportedMethods$utility$Double$1$hashCode.hashCode(this.right);
        }

        public DoubleWheel(double d, double d2) {
            this.left = d;
            this.right = d2;
        }

        public /* synthetic */ DoubleWheel(double d, double d2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 0.0d : d, (i & 2) != 0 ? 0.0d : d2);
        }

        public final double getLeft() {
            return this.left;
        }

        public final double getRight() {
            return this.right;
        }

        public final void setLeft(double d) {
            this.left = d;
        }

        public final void setRight(double d) {
            this.right = d;
        }

        public String toString() {
            return "left:" + this.left + " right:" + this.right;
        }
    }

    public Encoder() {
        double d = 0.44d / 2400;
        this.CONVERT_TO_METRIC = d;
        this.TANGENTIAL_MIN_ZERO = Math.abs(d);
        this.AXIAL_MIN_ZERO = Math.abs((this.CONVERT_TO_METRIC * 2) / this.wheelSpacing);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: Encoder.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/Encoder$Speed;", "", "lineSpeed", "", "angularSpeed", "(DD)V", "getAngularSpeed", "()D", "setAngularSpeed", "(D)V", "getLineSpeed", "setLineSpeed", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final /* data */ class Speed {
        private double angularSpeed;
        private double lineSpeed;

        public Speed() {
            this(0.0d, 0.0d, 3, null);
        }

        public static /* synthetic */ Speed copy$default(Speed speed, double d, double d2, int i, Object obj) {
            if ((i & 1) != 0) {
                d = speed.lineSpeed;
            }
            if ((i & 2) != 0) {
                d2 = speed.angularSpeed;
            }
            return speed.copy(d, d2);
        }

        /* renamed from: component1, reason: from getter */
        public final double getLineSpeed() {
            return this.lineSpeed;
        }

        /* renamed from: component2, reason: from getter */
        public final double getAngularSpeed() {
            return this.angularSpeed;
        }

        public final Speed copy(double lineSpeed, double angularSpeed) {
            return new Speed(lineSpeed, angularSpeed);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Speed)) {
                return false;
            }
            Speed speed = (Speed) other;
            return Double.compare(this.lineSpeed, speed.lineSpeed) == 0 && Double.compare(this.angularSpeed, speed.angularSpeed) == 0;
        }

        public int hashCode() {
            return (C$r8$backportedMethods$utility$Double$1$hashCode.hashCode(this.lineSpeed) * 31) + C$r8$backportedMethods$utility$Double$1$hashCode.hashCode(this.angularSpeed);
        }

        public String toString() {
            return "Speed(lineSpeed=" + this.lineSpeed + ", angularSpeed=" + this.angularSpeed + ")";
        }

        public Speed(double d, double d2) {
            this.lineSpeed = d;
            this.angularSpeed = d2;
        }

        public /* synthetic */ Speed(double d, double d2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 0.0d : d, (i & 2) != 0 ? 0.0d : d2);
        }

        public final double getAngularSpeed() {
            return this.angularSpeed;
        }

        public final double getLineSpeed() {
            return this.lineSpeed;
        }

        public final void setAngularSpeed(double d) {
            this.angularSpeed = d;
        }

        public final void setLineSpeed(double d) {
            this.lineSpeed = d;
        }
    }

    public final float getWheelSpacing() {
        return this.wheelSpacing;
    }

    public final void setWheelSpacing(float f) {
        this.wheelSpacing = f;
    }

    /* renamed from: getDistance, reason: from getter */
    public final DoubleWheel get_distance() {
        return this._distance;
    }

    public final Speed get_speed() {
        return this._speed;
    }

    public final void set_speed(Speed speed) {
        Intrinsics.checkParameterIsNotNull(speed, "<set-?>");
        this._speed = speed;
    }

    /* renamed from: getSpeed, reason: from getter */
    public final Speed get_speed() {
        return this._speed;
    }

    public final void update(short encoderLeft, short encoderRight) {
        this._distance.setLeft(encoderLeft * this.CONVERT_TO_METRIC);
        this._distance.setRight(encoderRight * this.CONVERT_TO_METRIC);
        this._speed.setLineSpeed(((this._distance.getRight() + this._distance.getLeft()) / 2) / 0.025d);
        this._speed.setAngularSpeed(((this._distance.getRight() - this._distance.getLeft()) / this.wheelSpacing) / 0.025d);
        Pdlog.m3273d("Hardware", "Encoder " + CommonKt.format(this._distance.getLeft(), 4) + ',' + CommonKt.format(this._distance.getRight(), 4) + " origin:" + ((int) encoderLeft) + ',' + ((int) encoderRight));
    }

    public final void setConfigure(float encoderCpr, float ratio, float simple, float perimeter_m, float _wheelSpacing) {
        int i = (int) (encoderCpr * ratio * simple);
        this.cpr = i;
        double d = perimeter_m;
        this.wheelPerimeter = d;
        double d2 = d / i;
        this.CONVERT_TO_METRIC = d2;
        this.TANGENTIAL_MIN_ZERO = Math.abs(d2);
        if (_wheelSpacing == 0.0f) {
            Pdlog.m3274e("Hardware", "Wheel Spacing can not be 0");
        } else {
            this.wheelSpacing = _wheelSpacing;
        }
        this.AXIAL_MIN_ZERO = Math.abs((this.CONVERT_TO_METRIC * 2) / this.wheelSpacing);
    }
}
