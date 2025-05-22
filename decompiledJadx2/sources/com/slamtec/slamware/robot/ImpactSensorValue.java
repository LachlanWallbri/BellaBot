package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class ImpactSensorValue {
    private long time;
    private float value;

    public ImpactSensorValue(long j, float f) {
        this.time = j;
        this.value = f;
    }

    public long getTime() {
        return this.time;
    }

    public float getValue() {
        return this.value;
    }
}
