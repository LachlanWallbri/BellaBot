package com.pudutech.bumblebee.presenter.report_cloud.protocol.cruise;

import com.pudutech.bumblebee.presenter.report_cloud.protocol.Head;

/* loaded from: classes4.dex */
public class Cruise extends Head {
    public long task_id = 0;
    public double battery = 0.0d;
    public Boolean status = false;
    public String theme = "";
    public long duration = 0;
    public long duration_pause = 0;
    public int duration_count = 0;
    public double mileage = 0.0d;
    public double average = 0.0d;
    public long total_time = 0;

    public Cruise() {
        this.type = "cruise";
    }

    public String toString() {
        return "Cruise{task_id=" + this.task_id + ", battery=" + this.battery + ", status=" + this.status + ", theme='" + this.theme + "', duration=" + this.duration + ", duration_pause=" + this.duration_pause + ", duration_count=" + this.duration_count + ", mileage=" + this.mileage + ", average=" + this.average + ", total_time=" + this.total_time + '}';
    }
}
