package com.pudutech.bumblebee.presenter.report_cloud.protocol.delivery;

import com.pudutech.bumblebee.presenter.report_cloud.protocol.Head;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class Delivery extends Head {
    public long task_id = 0;
    public double battery = 0.0d;
    public List<Info> info = new ArrayList();
    public double mileage = 0.0d;
    public double average = 0.0d;
    public long total_time = 0;

    public Delivery() {
        this.type = "delivery";
    }

    public String toString() {
        return "Delivery{mac='" + this.mac + "', timestamp=" + this.timestamp + ", type='" + this.type + "', report_number=" + this.report_number + ", softver='" + this.softver + "', hardver='" + this.hardver + "', task_id=" + this.task_id + ", battery=" + this.battery + ", info=" + this.info + ", mileage=" + this.mileage + ", average=" + this.average + ", total_time=" + this.total_time + '}';
    }
}
