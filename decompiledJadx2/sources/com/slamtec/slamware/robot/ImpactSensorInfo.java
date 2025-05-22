package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class ImpactSensorInfo {
    private SensorType coreSensorType;
    private Pose pose;
    private float refreshFreq;
    private int sensorId;
    private ImpactSensorType type;

    public ImpactSensorInfo(int i, Pose pose, ImpactSensorType impactSensorType, SensorType sensorType, float f) {
        this.sensorId = i;
        this.pose = pose;
        this.type = impactSensorType;
        this.coreSensorType = sensorType;
        this.refreshFreq = f;
    }

    public int getSensorId() {
        return this.sensorId;
    }

    public Pose getPose() {
        return this.pose;
    }

    public ImpactSensorType getType() {
        return this.type;
    }

    public SensorType getKind() {
        return this.coreSensorType;
    }

    public float getRefreshFreq() {
        return this.refreshFreq;
    }
}
