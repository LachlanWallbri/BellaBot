package com.pudutech.mirsdk.mircore.mirmapping;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* compiled from: OdometryCenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0004\"#$%B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\u000fJ\u001e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007J&\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007J\u001e\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "encoder_left_value", "", "encoder_right_value", "fusionEncoderAndGyro", "Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter$FusionEncoderAndGyro;", "gyroscope_angvel", AUserTrack.UTKEY_MODULE, "Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter$MoveModel;", "odom_data", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "odom_pose", "Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter$Vector2d;", "odom_theta", "speeds", "Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "getOdom", "updateEncoder", "", "left", "right", "interval", "updateIMU", "x", "y", CompressorStreamFactory.f8930Z, "updateSpeed", "line_speed", "angle_speed", "FusionEncoderAndGyro", "MoveModel", "Transform2d", "Vector2d", "MirMapping_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class OdometryCenter {
    private double encoder_left_value;
    private double encoder_right_value;
    private double gyroscope_angvel;
    private double odom_theta;
    private final String TAG = getClass().getSimpleName();
    private final FusionEncoderAndGyro fusionEncoderAndGyro = new FusionEncoderAndGyro();
    private final MoveModel module = new MoveModel();
    private final Vector2d odom_pose = new Vector2d();
    private final com.pudutech.mirsdk.hardware.serialize.Vector2d speeds = new com.pudutech.mirsdk.hardware.serialize.Vector2d(0.0d, 0.0d, 3, null);
    private Vector3d odom_data = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);

    public final void updateEncoder(double left, double right, double interval) {
        this.encoder_left_value = left;
        this.encoder_right_value = right;
    }

    public final void updateIMU(double x, double y, double z, double interval) {
        synchronized (Double.valueOf(this.gyroscope_angvel)) {
            this.gyroscope_angvel = z * interval;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final com.pudutech.mirsdk.hardware.serialize.Vector2d updateSpeed(double line_speed, double angle_speed, double interval) {
        double[] currentEncoder;
        double[] dArr = {line_speed * interval, angle_speed * interval};
        synchronized (Double.valueOf(this.gyroscope_angvel)) {
            currentEncoder = this.fusionEncoderAndGyro.currentEncoder(dArr, this.gyroscope_angvel);
            Unit unit = Unit.INSTANCE;
        }
        this.speeds.setX(currentEncoder[0] / interval);
        this.speeds.setY(currentEncoder[1] / interval);
        this.module.update(currentEncoder[0], currentEncoder[1]);
        this.odom_pose.setX(this.module.getPosition().getX());
        this.odom_pose.setY(this.module.getPosition().getY());
        this.odom_theta = this.module.getDirInRad();
        this.odom_data.setX(this.odom_pose.getX());
        this.odom_data.setY(this.odom_pose.getY());
        this.odom_data.setZ(this.odom_theta);
        MappingNative.INSTANCE.setOdomData(this.odom_pose.getX(), this.odom_pose.getY(), this.odom_theta, line_speed, angle_speed);
        return this.speeds;
    }

    /* renamed from: getOdom, reason: from getter */
    public final Vector3d getOdom_data() {
        return this.odom_data;
    }

    /* compiled from: OdometryCenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter$FusionEncoderAndGyro;", "", "()V", "cnt", "", "errorBuffer", "", "errorSum", "", "gyroCompensation", "index", "zeroCnt", "currentEncoder", "encoderPath", "gyroAxisZ", "delayCheckStop", "", "isStopThisTime", "Companion", "MirMapping_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class FusionEncoderAndGyro {
        private int cnt;
        private final double[] errorBuffer = new double[GYRO_ERROR_BUFFER_SIZE];
        private double errorSum;
        private double gyroCompensation;
        private int index;
        private int zeroCnt;
        private static final int GYRO_FREQUENCY_HZ = 40;
        private static final int GYRO_ERROR_BUFFER_SIZE = GYRO_FREQUENCY_HZ * 5;

        public FusionEncoderAndGyro() {
            Arrays.fill(this.errorBuffer, 0.0d);
        }

        private final boolean delayCheckStop(boolean isStopThisTime) {
            int i;
            if (isStopThisTime) {
                this.zeroCnt++;
                i = this.zeroCnt;
            } else {
                i = 0;
            }
            this.zeroCnt = i;
            return this.zeroCnt > 5;
        }

        public final double[] currentEncoder(double[] encoderPath, double gyroAxisZ) {
            Intrinsics.checkParameterIsNotNull(encoderPath, "encoderPath");
            double d = encoderPath[0];
            double d2 = encoderPath[1];
            if (Math.abs(d) < 3.7E-4d && Math.abs(d2) < 3.7E-4d) {
                if (delayCheckStop(true)) {
                    double[] dArr = this.errorBuffer;
                    int i = this.index;
                    dArr[i] = gyroAxisZ;
                    this.errorSum += gyroAxisZ;
                    this.cnt++;
                    this.index = i + 1;
                    if (this.index == GYRO_ERROR_BUFFER_SIZE) {
                        this.index = 0;
                    }
                    int i2 = this.cnt;
                    if (i2 == GYRO_ERROR_BUFFER_SIZE) {
                        double d3 = this.errorSum;
                        this.gyroCompensation = d3 / i2;
                        this.errorSum = d3 - this.errorBuffer[this.index];
                        this.cnt = i2 - 1;
                    }
                }
            } else {
                delayCheckStop(false);
                double d4 = this.gyroCompensation;
                if (d4 != 0.0d) {
                    d2 = gyroAxisZ - d4;
                }
            }
            return new double[]{d, d2};
        }
    }

    /* compiled from: OdometryCenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter$MoveModel;", "", "()V", "dirInRad", "", "getDirInRad", "()D", "originCenter", "Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter$Vector2d;", "originDir", RequestParameters.POSITION, "getPosition", "()Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter$Vector2d;", "transform", "Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter$Transform2d;", "update", "", "odomPathLine", "odomPathAngle", "Companion", "MirMapping_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class MoveModel {
        private final Vector2d originCenter = new Vector2d(0.0d, 0.0d);
        private final Vector2d originDir = new Vector2d(1.0d, 0.0d);
        private Transform2d transform = new Transform2d();
        private static final Vector2d AXIS_X = new Vector2d(1.0d, 0.0d);

        public MoveModel() {
            this.transform.setIdentity();
        }

        public final Vector2d getPosition() {
            return this.transform.times(this.originCenter);
        }

        public final double getDirInRad() {
            return AXIS_X.angleToVector(this.transform.times(this.originDir).minus(this.transform.times(this.originCenter)));
        }

        public final void update(double odomPathLine, double odomPathAngle) {
            Transform2d transform2d = new Transform2d();
            transform2d.setIdentity();
            if (Math.abs(odomPathAngle) < 1.0E-10d) {
                transform2d.translate(odomPathLine, 0.0d);
            } else {
                double d = odomPathLine / odomPathAngle;
                transform2d.translate(0.0d, d);
                transform2d.rotate(odomPathAngle);
                transform2d.translate(0.0d, -d);
            }
            this.transform = this.transform.times(transform2d);
        }
    }

    /* compiled from: OdometryCenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0000J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0000J\u0011\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0000H\u0086\u0002J\u0006\u0010\u0012\u001a\u00020\u0004J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter$Vector2d;", "", "()V", "x", "", "y", "(DD)V", "getX", "()D", "setX", "(D)V", "getY", "setY", TmpConstant.GROUP_OP_ADD, "v", "angleToVector", "minus", "subtrahend", "norm", "normalize", "", "setUnit", "toString", "", "MirMapping_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Vector2d {
        private double x;
        private double y;

        public final double getX() {
            return this.x;
        }

        public final void setX(double d) {
            this.x = d;
        }

        public final double getY() {
            return this.y;
        }

        public final void setY(double d) {
            this.y = d;
        }

        public Vector2d() {
            this.x = 0.0d;
            this.y = 0.0d;
        }

        public Vector2d(double d, double d2) {
            this.x = d;
            this.y = d2;
        }

        public final void setUnit() {
            this.x = 1.0d;
            this.y = 0.0d;
        }

        public final double norm() {
            double d = this.x;
            double d2 = this.y;
            return Math.sqrt((d * d) + (d2 * d2));
        }

        public final void normalize() {
            double norm = norm();
            boolean z = norm > ((double) 0);
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Assertion failed");
            }
            this.x /= norm;
            this.y /= norm;
        }

        public final Vector2d add(Vector2d v) {
            Intrinsics.checkParameterIsNotNull(v, "v");
            Vector2d vector2d = new Vector2d();
            vector2d.x = this.x + v.x;
            vector2d.y = this.y + v.y;
            return vector2d;
        }

        public final Vector2d minus(Vector2d subtrahend) {
            Intrinsics.checkParameterIsNotNull(subtrahend, "subtrahend");
            Vector2d vector2d = new Vector2d();
            vector2d.x = this.x - subtrahend.x;
            vector2d.y = this.y - subtrahend.y;
            return vector2d;
        }

        public final double angleToVector(Vector2d v) {
            Intrinsics.checkParameterIsNotNull(v, "v");
            Vector2d vector2d = this;
            vector2d.normalize();
            v.normalize();
            double d = vector2d.x;
            double d2 = v.y;
            double d3 = v.x;
            double d4 = vector2d.y;
            double d5 = (d * d2) - (d3 * d4);
            double d6 = (d * d3) + (d4 * d2);
            if (d6 > 1.0d) {
                d6 = 1.0d;
            }
            if (d6 < -1.0d) {
                d6 = -1.0d;
            }
            double acos = Math.acos(d6);
            if (d5 < 0) {
                acos = -acos;
            }
            while (acos > 3.141592653589793d) {
                acos -= 6.283185307179586d;
            }
            while (acos < -3.141592653589793d) {
                acos += 6.283185307179586d;
            }
            return acos;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            sb.append(this.x);
            sb.append(' ');
            sb.append(this.y);
            sb.append(']');
            return sb.toString();
        }
    }

    /* compiled from: OdometryCenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\bJ\u0011\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0000H\u0086\u0002J\u0011\u0010\f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0086\u0002J\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000eJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter$Transform2d;", "", "()V", "matrix", "", "", "[[D", "rotate", "", "theta", "", "setIdentity", "times", "trans", "Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter$Vector2d;", "v", "translate", "x", "y", "Companion", "MirMapping_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Transform2d {
        private static final int THREE_DIMENSIONAL = 3;
        private final double[][] matrix;

        public Transform2d() {
            int i = THREE_DIMENSIONAL;
            double[][] dArr = new double[i];
            for (int i2 = 0; i2 < i; i2++) {
                dArr[i2] = new double[THREE_DIMENSIONAL];
            }
            this.matrix = dArr;
            int i3 = THREE_DIMENSIONAL;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = THREE_DIMENSIONAL;
                for (int i6 = 0; i6 < i5; i6++) {
                    this.matrix[i4][i6] = 0.0d;
                }
            }
        }

        public final void setIdentity() {
            int i = THREE_DIMENSIONAL;
            int i2 = 0;
            while (i2 < i) {
                int i3 = THREE_DIMENSIONAL;
                int i4 = 0;
                while (i4 < i3) {
                    this.matrix[i2][i4] = i2 == i4 ? 1 : 0;
                    i4++;
                }
                i2++;
            }
        }

        public final void rotate(double theta) {
            double cos = Math.cos(theta);
            double sin = Math.sin(theta);
            int i = THREE_DIMENSIONAL;
            for (int i2 = 0; i2 < i; i2++) {
                double[][] dArr = this.matrix;
                double d = dArr[i2][0];
                double d2 = dArr[i2][1];
                dArr[i2][0] = (d * cos) + (d2 * sin);
                dArr[i2][1] = (d2 * cos) - (d * sin);
            }
        }

        public final void translate(double x, double y) {
            int i = THREE_DIMENSIONAL;
            for (int i2 = 0; i2 < i; i2++) {
                double[][] dArr = this.matrix;
                double[] dArr2 = dArr[i2];
                dArr2[2] = dArr2[2] + (dArr[i2][0] * x) + (dArr[i2][1] * y);
            }
        }

        public final void translate(Vector2d v) {
            Intrinsics.checkParameterIsNotNull(v, "v");
            translate(v.getX(), v.getY());
        }

        public final Vector2d times(Vector2d v) {
            Intrinsics.checkParameterIsNotNull(v, "v");
            Vector2d vector2d = new Vector2d();
            vector2d.setX((this.matrix[0][0] * v.getX()) + (this.matrix[0][1] * v.getY()) + this.matrix[0][2]);
            vector2d.setY((this.matrix[1][0] * v.getX()) + (this.matrix[1][1] * v.getY()) + this.matrix[1][2]);
            return vector2d;
        }

        public final Transform2d times(Transform2d trans) {
            Intrinsics.checkParameterIsNotNull(trans, "trans");
            Transform2d transform2d = new Transform2d();
            int i = THREE_DIMENSIONAL;
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = THREE_DIMENSIONAL;
                for (int i4 = 0; i4 < i3; i4++) {
                    transform2d.matrix[i2][i4] = 0.0d;
                    int i5 = THREE_DIMENSIONAL;
                    for (int i6 = 0; i6 < i5; i6++) {
                        double[] dArr = transform2d.matrix[i2];
                        dArr[i4] = dArr[i4] + (this.matrix[i2][i6] * trans.matrix[i6][i4]);
                    }
                }
            }
            return transform2d;
        }
    }
}
