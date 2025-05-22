package com.pudutech.bumblebee.presenter.report_cloud.protocol.delivery;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class Info {
    public double average;
    public long duration;
    public long duration_pause;
    public long duration_wait;
    public double mileage;
    public long order_id;
    public int task_type;
    public String goal_id = "";
    public List<Integer> tray_list = new ArrayList();
    public boolean status = false;
    public String theme = "";

    public String toString() {
        return "Info{goal_id='" + this.goal_id + "', tray_list=" + this.tray_list + ", task_type=" + this.task_type + ", order_id=" + this.order_id + ", status=" + this.status + ", theme='" + this.theme + "', mileage=" + this.mileage + ", duration=" + this.duration + ", average=" + this.average + ", duration_wait=" + this.duration_wait + ", duration_pause=" + this.duration_pause + '}';
    }
}
