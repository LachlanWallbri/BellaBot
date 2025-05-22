package com.slamtec.slamware.robot;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class PointPDF {
    private float circularErrorProbability;

    /* renamed from: id */
    private int f7604id;
    private Location location;
    private List<String> tags;

    public PointPDF(int i, Location location, float f, List<String> list) {
        this.f7604id = i;
        this.location = location;
        this.circularErrorProbability = f;
        this.tags = list;
    }

    public int getId() {
        return this.f7604id;
    }

    public void setId(int i) {
        this.f7604id = i;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getCircularErrorProbability() {
        return this.circularErrorProbability;
    }

    public void setCircularErrorProbability(float f) {
        this.circularErrorProbability = f;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> list) {
        this.tags = list;
    }
}
