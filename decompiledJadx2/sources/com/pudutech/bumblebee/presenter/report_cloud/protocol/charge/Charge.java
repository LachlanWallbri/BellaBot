package com.pudutech.bumblebee.presenter.report_cloud.protocol.charge;

import com.pudutech.bumblebee.presenter.report_cloud.protocol.Head;

/* loaded from: classes4.dex */
public class Charge extends Head {
    public double start_battery = 0.0d;
    public double end_battery = 0.0d;
    public long start_timestamp = 0;
    public long end_timestamp = 0;

    public Charge() {
        this.type = "charge";
    }
}
