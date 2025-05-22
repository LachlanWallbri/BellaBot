package com.pudutech.mirsdk.map;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class PuduJsonData {
    private String alias;
    private Double align_dist;
    private Double dir;
    private Integer doubleDir;
    private Double down_slow_distance;
    private Double down_speed;
    private Double down_stop_distance;
    private String group;
    private boolean high_precision = false;

    /* renamed from: id */
    private String f6133id;
    private String mac;
    private Double maxSpeed;
    private String mode;
    private ArrayList<double[]> nodes;
    private Double opened_spend_time;
    private Integer sort_weight;
    private String type;
    private Double up_slow_distance;
    private Double up_speed;
    private Double up_stop_distance;
    private double[] vector;
    private String waiter;
    private double weight;
    private Double width;

    public Double getUp_stop_distance() {
        return this.up_stop_distance;
    }

    public void setUp_stop_distance(Double d) {
        this.up_stop_distance = d;
    }

    public Double getDown_stop_distance() {
        return this.down_stop_distance;
    }

    public void setDown_stop_distance(Double d) {
        this.down_stop_distance = d;
    }

    public Double getUp_slow_distance() {
        return this.up_slow_distance;
    }

    public void setUp_slow_distance(Double d) {
        this.up_slow_distance = d;
    }

    public Double getDown_slow_distance() {
        return this.down_slow_distance;
    }

    public void setDown_slow_distance(Double d) {
        this.down_slow_distance = d;
    }

    public Double getUp_speed() {
        return this.up_speed;
    }

    public void setUp_speed(Double d) {
        this.up_speed = d;
    }

    public Double getDown_speed() {
        return this.down_speed;
    }

    public void setDown_speed(Double d) {
        this.down_speed = d;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public double[] getVector() {
        return this.vector;
    }

    public void setVector(double[] dArr) {
        this.vector = dArr;
    }

    public String getId() {
        return this.f6133id;
    }

    public Integer getDoubleDir() {
        return this.doubleDir;
    }

    public Double getDir() {
        return this.dir;
    }

    public Double getMaxSpeed() {
        return this.maxSpeed;
    }

    public Double getPathWidth() {
        return this.width;
    }

    public void setId(String str) {
        this.f6133id = str;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double d) {
        this.weight = d;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public String getWaiter() {
        return this.waiter;
    }

    public void setWaiter(String str) {
        this.waiter = str;
    }

    public Double getOpened_spend_time() {
        return this.opened_spend_time;
    }

    public void setOpened_spend_time(Double d) {
        this.opened_spend_time = d;
    }

    public void setAlign_dist(Double d) {
        this.align_dist = d;
    }

    public Double getAlign_dist() {
        return this.align_dist;
    }

    public Integer getSortWeight() {
        return this.sort_weight;
    }

    public void setSortWeight(Integer num) {
        this.sort_weight = num;
    }

    public boolean isHigh_precision() {
        return this.high_precision;
    }

    public void setHigh_precision(boolean z) {
        this.high_precision = z;
    }

    public ArrayList<double[]> getNodes() {
        return this.nodes;
    }

    public void setNodes(ArrayList<double[]> arrayList) {
        this.nodes = arrayList;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }
}
