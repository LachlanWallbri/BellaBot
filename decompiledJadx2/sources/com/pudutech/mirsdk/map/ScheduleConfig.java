package com.pudutech.mirsdk.map;

import com.pudutech.mirsdk.compat.topo.SchedulingConfigKey;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ScheduleConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0010\u0013\n\u0002\b\f\n\u0002\u0010\b\n\u0002\bG\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR&\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010\"\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR\u001c\u0010%\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001e\u0010*\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR\u001e\u0010-\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b.\u0010\u0006\"\u0004\b/\u0010\bR\u001e\u00100\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b1\u0010\u0006\"\u0004\b2\u0010\bR&\u00103\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b4\u0010\u0011\"\u0004\b5\u0010\u0013R\u001e\u00106\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b7\u0010\u0006\"\u0004\b8\u0010\bR\u001e\u00109\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b:\u0010\u0006\"\u0004\b;\u0010\bR\u001e\u0010<\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b=\u0010\u001e\"\u0004\b>\u0010 R\u001e\u0010?\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b@\u0010\u0006\"\u0004\bA\u0010\bR\u001e\u0010B\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\bC\u0010\u0006\"\u0004\bD\u0010\bR.\u0010E\u001a\u0014\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000e\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010J\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR.\u0010K\u001a\u0014\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000e\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010J\u001a\u0004\bL\u0010G\"\u0004\bM\u0010IR\u001e\u0010N\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\bO\u0010\u0006\"\u0004\bP\u0010\bR\u001e\u0010Q\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\bR\u0010\u001e\"\u0004\bS\u0010 R\u001e\u0010T\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\bU\u0010\u0006\"\u0004\bV\u0010\bR\u001e\u0010W\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\bX\u0010\u0006\"\u0004\bY\u0010\bR\u001e\u0010Z\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b[\u0010\u0006\"\u0004\b\\\u0010\bR.\u0010]\u001a\u0014\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000e\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010J\u001a\u0004\b^\u0010G\"\u0004\b_\u0010IR\u001e\u0010`\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\ba\u0010\u001e\"\u0004\bb\u0010 R$\u0010c\u001a\n\u0012\u0004\u0012\u00020d\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\u0010i\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR\u001e\u0010j\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\bk\u0010\u001e\"\u0004\bl\u0010 R\u001e\u0010m\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\bn\u0010\u0006\"\u0004\bo\u0010\b¨\u0006p"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/ScheduleConfig;", "", "()V", "arrive_threshold", "", "getArrive_threshold", "()Ljava/lang/Double;", "setArrive_threshold", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "avoid_duration", "getAvoid_duration", "setAvoid_duration", "back_pose", "", "", "getBack_pose", "()[[D", "setBack_pose", "([[D)V", "[[D", "change_parking_dist_threshold", "getChange_parking_dist_threshold", "setChange_parking_dist_threshold", "close_node_threshold", "getClose_node_threshold", "setClose_node_threshold", "delete_wait_take", "", "getDelete_wait_take", "()Ljava/lang/Integer;", "setDelete_wait_take", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "deliver_radius", "getDeliver_radius", "setDeliver_radius", "deliver_start_pose", "getDeliver_start_pose", "()[D", "setDeliver_start_pose", "([D)V", "distance_node_threshold", "getDistance_node_threshold", "setDistance_node_threshold", "distance_pos_goal_threshold", "getDistance_pos_goal_threshold", "setDistance_pos_goal_threshold", "distance_robot_path_point_threshold", "getDistance_robot_path_point_threshold", "setDistance_robot_path_point_threshold", "goto_pose", "getGoto_pose", "setGoto_pose", "map_scale", "getMap_scale", "setMap_scale", "moving_robot_dist_threshold", "getMoving_robot_dist_threshold", "setMoving_robot_dist_threshold", "one_robot_weight", "getOne_robot_weight", "setOne_robot_weight", "parking_point_height", "getParking_point_height", "setParking_point_height", "parking_point_width", "getParking_point_width", "setParking_point_width", SchedulingConfigKey.PARKING_POSE, "getParking_pose", "()[[[D", "setParking_pose", "([[[D)V", "[[[D", "parking_regions", "getParking_regions", "setParking_regions", "robot_info_timeout", "getRobot_info_timeout", "setRobot_info_timeout", "robot_number", "getRobot_number", "setRobot_number", "startup_delay_time", "getStartup_delay_time", "setStartup_delay_time", "takeing_point_height", "getTakeing_point_height", "setTakeing_point_height", "takeing_point_width", "getTakeing_point_width", "setTakeing_point_width", SchedulingConfigKey.TAKING_POSE, "getTaking_pose", "setTaking_pose", "use_change_park", "getUse_change_park", "setUse_change_park", "use_change_robot_ids", "", "getUse_change_robot_ids", "()[Ljava/lang/String;", "setUse_change_robot_ids", "([Ljava/lang/String;)V", "[Ljava/lang/String;", SchedulingConfigKey.USE_PARKING_FIND, "getUse_parking_find", "setUse_parking_find", "wait_duration", "getWait_duration", "setWait_duration", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ScheduleConfig {
    private Double arrive_threshold;
    private Double avoid_duration;
    private double[][] back_pose;
    private Double change_parking_dist_threshold;
    private Double close_node_threshold;
    private Integer delete_wait_take;
    private Double deliver_radius;
    private double[] deliver_start_pose;
    private Double distance_node_threshold;
    private Double distance_pos_goal_threshold;
    private Double distance_robot_path_point_threshold;
    private double[][] goto_pose;
    private Double map_scale;
    private Double moving_robot_dist_threshold;
    private Integer one_robot_weight;
    private Double parking_point_height;
    private Double parking_point_width;
    private double[][][] parking_pose;
    private double[][][] parking_regions;
    private Double robot_info_timeout;
    private Integer robot_number;
    private Double startup_delay_time;
    private Double takeing_point_height;
    private Double takeing_point_width;
    private double[][][] taking_pose;
    private Integer use_change_park;
    private String[] use_change_robot_ids;
    private Integer use_parking_find;
    private Double wait_duration;

    public final Double getMap_scale() {
        return this.map_scale;
    }

    public final void setMap_scale(Double d) {
        this.map_scale = d;
    }

    public final double[] getDeliver_start_pose() {
        return this.deliver_start_pose;
    }

    public final void setDeliver_start_pose(double[] dArr) {
        this.deliver_start_pose = dArr;
    }

    public final Double getDeliver_radius() {
        return this.deliver_radius;
    }

    public final void setDeliver_radius(Double d) {
        this.deliver_radius = d;
    }

    public final Double getDistance_robot_path_point_threshold() {
        return this.distance_robot_path_point_threshold;
    }

    public final void setDistance_robot_path_point_threshold(Double d) {
        this.distance_robot_path_point_threshold = d;
    }

    public final Double getDistance_pos_goal_threshold() {
        return this.distance_pos_goal_threshold;
    }

    public final void setDistance_pos_goal_threshold(Double d) {
        this.distance_pos_goal_threshold = d;
    }

    public final Double getClose_node_threshold() {
        return this.close_node_threshold;
    }

    public final void setClose_node_threshold(Double d) {
        this.close_node_threshold = d;
    }

    public final Double getRobot_info_timeout() {
        return this.robot_info_timeout;
    }

    public final void setRobot_info_timeout(Double d) {
        this.robot_info_timeout = d;
    }

    public final Double getDistance_node_threshold() {
        return this.distance_node_threshold;
    }

    public final void setDistance_node_threshold(Double d) {
        this.distance_node_threshold = d;
    }

    public final Double getWait_duration() {
        return this.wait_duration;
    }

    public final void setWait_duration(Double d) {
        this.wait_duration = d;
    }

    public final Double getAvoid_duration() {
        return this.avoid_duration;
    }

    public final void setAvoid_duration(Double d) {
        this.avoid_duration = d;
    }

    public final Double getArrive_threshold() {
        return this.arrive_threshold;
    }

    public final void setArrive_threshold(Double d) {
        this.arrive_threshold = d;
    }

    public final Integer getDelete_wait_take() {
        return this.delete_wait_take;
    }

    public final void setDelete_wait_take(Integer num) {
        this.delete_wait_take = num;
    }

    public final Integer getUse_parking_find() {
        return this.use_parking_find;
    }

    public final void setUse_parking_find(Integer num) {
        this.use_parking_find = num;
    }

    public final String[] getUse_change_robot_ids() {
        return this.use_change_robot_ids;
    }

    public final void setUse_change_robot_ids(String[] strArr) {
        this.use_change_robot_ids = strArr;
    }

    public final Double getChange_parking_dist_threshold() {
        return this.change_parking_dist_threshold;
    }

    public final void setChange_parking_dist_threshold(Double d) {
        this.change_parking_dist_threshold = d;
    }

    public final Double getTakeing_point_width() {
        return this.takeing_point_width;
    }

    public final void setTakeing_point_width(Double d) {
        this.takeing_point_width = d;
    }

    public final Double getTakeing_point_height() {
        return this.takeing_point_height;
    }

    public final void setTakeing_point_height(Double d) {
        this.takeing_point_height = d;
    }

    public final Double getParking_point_width() {
        return this.parking_point_width;
    }

    public final void setParking_point_width(Double d) {
        this.parking_point_width = d;
    }

    public final Double getParking_point_height() {
        return this.parking_point_height;
    }

    public final void setParking_point_height(Double d) {
        this.parking_point_height = d;
    }

    public final Integer getUse_change_park() {
        return this.use_change_park;
    }

    public final void setUse_change_park(Integer num) {
        this.use_change_park = num;
    }

    public final Double getStartup_delay_time() {
        return this.startup_delay_time;
    }

    public final void setStartup_delay_time(Double d) {
        this.startup_delay_time = d;
    }

    public final Double getMoving_robot_dist_threshold() {
        return this.moving_robot_dist_threshold;
    }

    public final void setMoving_robot_dist_threshold(Double d) {
        this.moving_robot_dist_threshold = d;
    }

    public final Integer getRobot_number() {
        return this.robot_number;
    }

    public final void setRobot_number(Integer num) {
        this.robot_number = num;
    }

    public final Integer getOne_robot_weight() {
        return this.one_robot_weight;
    }

    public final void setOne_robot_weight(Integer num) {
        this.one_robot_weight = num;
    }

    public final double[][][] getTaking_pose() {
        return this.taking_pose;
    }

    public final void setTaking_pose(double[][][] dArr) {
        this.taking_pose = dArr;
    }

    public final double[][][] getParking_pose() {
        return this.parking_pose;
    }

    public final void setParking_pose(double[][][] dArr) {
        this.parking_pose = dArr;
    }

    public final double[][][] getParking_regions() {
        return this.parking_regions;
    }

    public final void setParking_regions(double[][][] dArr) {
        this.parking_regions = dArr;
    }

    public final double[][] getBack_pose() {
        return this.back_pose;
    }

    public final void setBack_pose(double[][] dArr) {
        this.back_pose = dArr;
    }

    public final double[][] getGoto_pose() {
        return this.goto_pose;
    }

    public final void setGoto_pose(double[][] dArr) {
        this.goto_pose = dArr;
    }
}
