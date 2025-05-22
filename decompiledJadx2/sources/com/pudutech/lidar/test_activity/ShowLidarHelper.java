package com.pudutech.lidar.test_activity;

import android.os.SystemClock;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.LidarVersion;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ShowLidarHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010$\u001a\u00020\u0004J\u0014\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010&J\u0014\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\u001e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0005\u001a\u00020\u001b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001e\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020!0 j\b\u0012\u0004\u0012\u00020!`\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, m3961d2 = {"Lcom/pudutech/lidar/test_activity/ShowLidarHelper;", "", "()V", "TAG", "", "<set-?>", "", "frameRate", "getFrameRate", "()D", "lastTimestamp", "", "lidarVersion", "Lcom/pudutech/lidar/LidarVersion;", "getLidarVersion", "()Lcom/pudutech/lidar/LidarVersion;", "setLidarVersion", "(Lcom/pudutech/lidar/LidarVersion;)V", "originEndAngle_rad", "getOriginEndAngle_rad", "()Ljava/lang/Double;", "setOriginEndAngle_rad", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "originStartAngle_rad", "getOriginStartAngle_rad", "setOriginStartAngle_rad", "", "pointNum", "getPointNum", "()I", "specialPoints", "Ljava/util/ArrayList;", "Lcom/pudutech/lidar/test_activity/ShowLidarHelper$SpecialAngle;", "Lkotlin/collections/ArrayList;", "specialPointsString", "getLidarInfo", "loadAngle", "Lkotlin/Pair;", "updateLidar", "", "list", "", "Lcom/pudutech/lidar/LidarNode;", "SpecialAngle", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ShowLidarHelper {
    private double frameRate;
    private long lastTimestamp;
    private LidarVersion lidarVersion;
    private Double originEndAngle_rad;
    private Double originStartAngle_rad;
    private int pointNum;
    private final String TAG = "ShowLidarHelper";
    private ArrayList<SpecialAngle> specialPoints = CollectionsKt.arrayListOf(new SpecialAngle(0, 0.0d, 0.0d, 10.0d), new SpecialAngle(90, Math.toRadians(90.0d), 0.0d, 10.0d), new SpecialAngle(180, Math.toRadians(180.0d), 0.0d, 10.0d), new SpecialAngle(270, Math.toRadians(270.0d), 0.0d, 10.0d));
    private String specialPointsString = "";

    public final Double getOriginStartAngle_rad() {
        return this.originStartAngle_rad;
    }

    public final void setOriginStartAngle_rad(Double d) {
        this.originStartAngle_rad = d;
    }

    public final Double getOriginEndAngle_rad() {
        return this.originEndAngle_rad;
    }

    public final void setOriginEndAngle_rad(Double d) {
        this.originEndAngle_rad = d;
    }

    public final Pair<Double, Double> loadAngle() {
        try {
            List readLines$default = FilesKt.readLines$default(new File("sdcard/debug_lidar_angle"), null, 1, null);
            return new Pair<>(Double.valueOf(Double.parseDouble((String) readLines$default.get(0))), Double.valueOf(Double.parseDouble((String) readLines$default.get(1))));
        } catch (Exception e) {
            Pdlog.m3277w(this.TAG, String.valueOf(e));
            return null;
        }
    }

    public final LidarVersion getLidarVersion() {
        return this.lidarVersion;
    }

    public final void setLidarVersion(LidarVersion lidarVersion) {
        this.lidarVersion = lidarVersion;
    }

    public final double getFrameRate() {
        return this.frameRate;
    }

    public final int getPointNum() {
        return this.pointNum;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ShowLidarHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/lidar/test_activity/ShowLidarHelper$SpecialAngle;", "", AIUIConstant.KEY_TAG, "", "anlge", "", "distance", "angleDiff", "(IDDD)V", "getAngleDiff", "()D", "setAngleDiff", "(D)V", "getAnlge", "getDistance", "setDistance", "getTag", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    private static final /* data */ class SpecialAngle {
        private double angleDiff;
        private final double anlge;
        private double distance;
        private final int tag;

        public static /* synthetic */ SpecialAngle copy$default(SpecialAngle specialAngle, int i, double d, double d2, double d3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = specialAngle.tag;
            }
            if ((i2 & 2) != 0) {
                d = specialAngle.anlge;
            }
            double d4 = d;
            if ((i2 & 4) != 0) {
                d2 = specialAngle.distance;
            }
            double d5 = d2;
            if ((i2 & 8) != 0) {
                d3 = specialAngle.angleDiff;
            }
            return specialAngle.copy(i, d4, d5, d3);
        }

        /* renamed from: component1, reason: from getter */
        public final int getTag() {
            return this.tag;
        }

        /* renamed from: component2, reason: from getter */
        public final double getAnlge() {
            return this.anlge;
        }

        /* renamed from: component3, reason: from getter */
        public final double getDistance() {
            return this.distance;
        }

        /* renamed from: component4, reason: from getter */
        public final double getAngleDiff() {
            return this.angleDiff;
        }

        public final SpecialAngle copy(int tag, double anlge, double distance, double angleDiff) {
            return new SpecialAngle(tag, anlge, distance, angleDiff);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SpecialAngle)) {
                return false;
            }
            SpecialAngle specialAngle = (SpecialAngle) other;
            return this.tag == specialAngle.tag && Double.compare(this.anlge, specialAngle.anlge) == 0 && Double.compare(this.distance, specialAngle.distance) == 0 && Double.compare(this.angleDiff, specialAngle.angleDiff) == 0;
        }

        public int hashCode() {
            int i = this.tag * 31;
            long doubleToLongBits = Double.doubleToLongBits(this.anlge);
            int i2 = (i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
            long doubleToLongBits2 = Double.doubleToLongBits(this.distance);
            int i3 = (i2 + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31;
            long doubleToLongBits3 = Double.doubleToLongBits(this.angleDiff);
            return i3 + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)));
        }

        public String toString() {
            return "SpecialAngle(tag=" + this.tag + ", anlge=" + this.anlge + ", distance=" + this.distance + ", angleDiff=" + this.angleDiff + ")";
        }

        public SpecialAngle(int i, double d, double d2, double d3) {
            this.tag = i;
            this.anlge = d;
            this.distance = d2;
            this.angleDiff = d3;
        }

        public /* synthetic */ SpecialAngle(int i, double d, double d2, double d3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, d, d2, (i2 & 8) != 0 ? 10.0d : d3);
        }

        public final double getAngleDiff() {
            return this.angleDiff;
        }

        public final double getAnlge() {
            return this.anlge;
        }

        public final double getDistance() {
            return this.distance;
        }

        public final int getTag() {
            return this.tag;
        }

        public final void setAngleDiff(double d) {
            this.angleDiff = d;
        }

        public final void setDistance(double d) {
            this.distance = d;
        }
    }

    public final void updateLidar(List<? extends LidarNode> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        this.pointNum = list.size();
        if (this.lastTimestamp > 0) {
            this.frameRate = ((int) ((1000.0d / (SystemClock.elapsedRealtime() - this.lastTimestamp)) * 10)) / 10.0d;
        }
        this.lastTimestamp = SystemClock.elapsedRealtime();
        double radians = Math.toRadians(5.0d);
        for (LidarNode lidarNode : list) {
            for (SpecialAngle specialAngle : this.specialPoints) {
                double abs = Math.abs(lidarNode.angleInRad - specialAngle.getAnlge());
                if (abs < specialAngle.getAngleDiff() && abs < radians) {
                    specialAngle.setAngleDiff(abs);
                    specialAngle.setDistance(lidarNode.distance_m);
                }
            }
        }
        this.specialPointsString = "";
        for (SpecialAngle specialAngle2 : this.specialPoints) {
            this.specialPointsString = this.specialPointsString + specialAngle2.getTag() + "° : " + specialAngle2.getDistance() + '\n';
            specialAngle2.setAngleDiff(10.0d);
            specialAngle2.setDistance(0.0d);
        }
    }

    public final String getLidarInfo() {
        return this.specialPointsString + this.lidarVersion + "  帧率:" + this.frameRate + "  点数：" + this.pointNum;
    }
}
