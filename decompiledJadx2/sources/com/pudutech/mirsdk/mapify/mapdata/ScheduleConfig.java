package com.pudutech.mirsdk.mapify.mapdata;

import com.pudutech.mirsdk.compat.topo.SchedulingConfigKey;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScheduleConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0013\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\u001b\u0018\u00002\u00020\u0001B-\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003¢\u0006\u0002\u0010\u0006R&\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R&\u0010$\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b%\u0010\n\"\u0004\b&\u0010\fR\u001e\u0010'\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b(\u0010\u0011\"\u0004\b)\u0010\u0013R&\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R&\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R&\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010+\"\u0004\b2\u0010-R\u001e\u00103\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b4\u0010\u0011\"\u0004\b5\u0010\u0013R\u001e\u00106\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b7\u0010\u0011\"\u0004\b8\u0010\u0013¨\u00069"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/mapdata/ScheduleConfig;", "", SchedulingConfigKey.PARKING_POSE, "", "", SchedulingConfigKey.TAKING_POSE, "(Ljava/util/List;Ljava/util/List;)V", "back_pose", "", "getBack_pose", "()[[D", "setBack_pose", "([[D)V", "[[D", "delete_wait_take", "", "getDelete_wait_take", "()Ljava/lang/Integer;", "setDelete_wait_take", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "deliver_radius", "getDeliver_radius", "setDeliver_radius", "deliver_start_pose", "getDeliver_start_pose", "()[D", "setDeliver_start_pose", "([D)V", "dual_carriageway_width", "", "getDual_carriageway_width", "()Ljava/lang/Float;", "setDual_carriageway_width", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "goto_pose", "getGoto_pose", "setGoto_pose", "moving_robot_dist_threshold", "getMoving_robot_dist_threshold", "setMoving_robot_dist_threshold", "getParking_pose", "()Ljava/util/List;", "setParking_pose", "(Ljava/util/List;)V", "parking_regions", "getParking_regions", "setParking_regions", "getTaking_pose", "setTaking_pose", "use_change_park", "getUse_change_park", "setUse_change_park", SchedulingConfigKey.USE_PARKING_FIND, "getUse_parking_find", "setUse_parking_find", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ScheduleConfig {
    private double[][] back_pose;
    private Integer delete_wait_take;
    private Integer deliver_radius;
    private double[] deliver_start_pose;
    private Float dual_carriageway_width;
    private double[][] goto_pose;
    private Integer moving_robot_dist_threshold;
    private List<List<double[]>> parking_pose;
    private List<List<double[]>> parking_regions;
    private List<List<double[]>> taking_pose;
    private Integer use_change_park;
    private Integer use_parking_find;

    public ScheduleConfig(List<List<double[]>> parking_pose, List<List<double[]>> taking_pose) {
        Intrinsics.checkParameterIsNotNull(parking_pose, "parking_pose");
        Intrinsics.checkParameterIsNotNull(taking_pose, "taking_pose");
        this.parking_pose = parking_pose;
        this.taking_pose = taking_pose;
        this.back_pose = new double[0];
        this.delete_wait_take = 0;
        this.deliver_radius = -1;
        this.deliver_start_pose = new double[]{0.0d, 0.0d, 0.0d};
        this.dual_carriageway_width = Float.valueOf(1.59f);
        this.goto_pose = new double[0];
        this.moving_robot_dist_threshold = 2;
        this.parking_regions = new ArrayList();
        this.use_change_park = 0;
        this.use_parking_find = 0;
    }

    public final List<List<double[]>> getParking_pose() {
        return this.parking_pose;
    }

    public final void setParking_pose(List<List<double[]>> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.parking_pose = list;
    }

    public final List<List<double[]>> getTaking_pose() {
        return this.taking_pose;
    }

    public final void setTaking_pose(List<List<double[]>> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.taking_pose = list;
    }

    public final double[][] getBack_pose() {
        return this.back_pose;
    }

    public final void setBack_pose(double[][] dArr) {
        this.back_pose = dArr;
    }

    public final Integer getDelete_wait_take() {
        return this.delete_wait_take;
    }

    public final void setDelete_wait_take(Integer num) {
        this.delete_wait_take = num;
    }

    public final Integer getDeliver_radius() {
        return this.deliver_radius;
    }

    public final void setDeliver_radius(Integer num) {
        this.deliver_radius = num;
    }

    public final double[] getDeliver_start_pose() {
        return this.deliver_start_pose;
    }

    public final void setDeliver_start_pose(double[] dArr) {
        this.deliver_start_pose = dArr;
    }

    public final Float getDual_carriageway_width() {
        return this.dual_carriageway_width;
    }

    public final void setDual_carriageway_width(Float f) {
        this.dual_carriageway_width = f;
    }

    public final double[][] getGoto_pose() {
        return this.goto_pose;
    }

    public final void setGoto_pose(double[][] dArr) {
        this.goto_pose = dArr;
    }

    public final Integer getMoving_robot_dist_threshold() {
        return this.moving_robot_dist_threshold;
    }

    public final void setMoving_robot_dist_threshold(Integer num) {
        this.moving_robot_dist_threshold = num;
    }

    public final List<List<double[]>> getParking_regions() {
        return this.parking_regions;
    }

    public final void setParking_regions(List<List<double[]>> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.parking_regions = list;
    }

    public final Integer getUse_change_park() {
        return this.use_change_park;
    }

    public final void setUse_change_park(Integer num) {
        this.use_change_park = num;
    }

    public final Integer getUse_parking_find() {
        return this.use_parking_find;
    }

    public final void setUse_parking_find(Integer num) {
        this.use_parking_find = num;
    }
}
