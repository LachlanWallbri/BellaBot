package com.slamtec.slamware.discovery;

import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public abstract class Device {
    private UUID deviceId;
    private String deviceName;
    private String hardwareVersion;
    private int manufactureId;
    private String manufactureName;
    private int modelId;
    private String modelName;
    private String serialNumber;
    private String softwareVersion;

    public abstract boolean canBeFoundWith(DiscoveryMode discoveryMode);

    public int getManufactureId() {
        return this.manufactureId;
    }

    public void setManufactureId(int i) {
        this.manufactureId = i;
    }

    public int getModelId() {
        return this.modelId;
    }

    public void setModelId(int i) {
        this.modelId = i;
    }

    public String getManufactureName() {
        return this.manufactureName;
    }

    public void setManufactureName(String str) {
        this.manufactureName = str;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String str) {
        this.modelName = str;
    }

    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public void setHardwareVersion(String str) {
        this.hardwareVersion = str;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public void setSoftwareVersion(String str) {
        this.softwareVersion = str;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String str) {
        this.serialNumber = str;
    }

    public UUID getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(UUID uuid) {
        this.deviceId = uuid;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }
}
