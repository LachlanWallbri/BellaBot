package com.pudutech.robot.module.report.protocol.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DeliveryInfo {
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
}
