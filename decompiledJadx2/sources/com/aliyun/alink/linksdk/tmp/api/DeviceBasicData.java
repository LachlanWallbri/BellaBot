package com.aliyun.alink.linksdk.tmp.api;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DeviceBasicData implements Cloneable {
    private transient String addr;
    private transient String desc;
    private transient String deviceModelJson;
    private String deviceName;
    private transient String iotId;
    private transient boolean isLocal;
    private String modelType;
    private transient int port;
    private String productKey;

    public DeviceBasicData() {
        this.isLocal = false;
    }

    public DeviceBasicData(boolean z) {
        this.isLocal = z;
    }

    public DeviceBasicData(DeviceBasicData deviceBasicData) {
        if (deviceBasicData == null) {
            return;
        }
        this.modelType = deviceBasicData.modelType;
        this.productKey = deviceBasicData.getProductKey();
        this.deviceName = deviceBasicData.getDeviceName();
        this.desc = deviceBasicData.getDesc();
        this.addr = deviceBasicData.getAddr();
        this.deviceModelJson = deviceBasicData.getDeviceModelJson();
        this.iotId = deviceBasicData.getIotId();
        this.port = deviceBasicData.getPort();
        this.isLocal = deviceBasicData.isLocal;
    }

    @Deprecated
    public String getProdKey() {
        return getProductKey();
    }

    @Deprecated
    public void setProdKey(String str) {
        setProductKey(str);
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean isLocal() {
        return this.isLocal;
    }

    public void setLocal(boolean z) {
        this.isLocal = z;
    }

    public String getDeviceModelJson() {
        return this.deviceModelJson;
    }

    public void setDeviceModelJson(String str) {
        this.deviceModelJson = str;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String str) {
        this.addr = str;
    }

    public String getModelType() {
        return this.modelType;
    }

    public void setModelType(String str) {
        this.modelType = str;
    }

    public String getProductKey() {
        return this.productKey;
    }

    public void setProductKey(String str) {
        this.productKey = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public String getDevId() {
        return TextHelper.combineStr(getProductKey(), getDeviceName());
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getIotId() {
        return this.iotId;
    }

    public void setIotId(String str) {
        this.iotId = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TextUtils.isEmpty(this.productKey) ? "pk null" : this.productKey);
        sb.append(TextUtils.isEmpty(this.deviceName) ? "dn null" : this.deviceName);
        return sb.toString();
    }
}
