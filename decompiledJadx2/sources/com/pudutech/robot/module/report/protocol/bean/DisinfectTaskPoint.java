package com.pudutech.robot.module.report.protocol.bean;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DisinfectTaskPoint {
    public String name = "";
    public Long duration_default = 0L;
    public Long duration = 0L;
    public int method = 0;
    public Long duration_spray = 0L;
    public Long duration_ray = 0L;

    public String toString() {
        return "DisinfectTaskPoint{point_name='" + this.name + "', point_total_time=" + this.duration_default + ", point_work_time=" + this.duration + ", point_method=" + this.method + ", point_spray_time=" + this.duration_spray + ", point_ray_time=" + this.duration_ray + '}';
    }
}
