package com.amap.api.location;

import com.loc.C3880f;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes.dex */
public class AMapLocationClientOption implements Cloneable {

    /* renamed from: b */
    private long f1104b = 2000;

    /* renamed from: c */
    private long f1105c = C3880f.f4181c;

    /* renamed from: d */
    private boolean f1106d = false;

    /* renamed from: e */
    private boolean f1107e = false;

    /* renamed from: f */
    private boolean f1108f = true;

    /* renamed from: g */
    private boolean f1109g = true;

    /* renamed from: h */
    private boolean f1110h = true;

    /* renamed from: i */
    private AMapLocationMode f1111i = AMapLocationMode.Hight_Accuracy;

    /* renamed from: k */
    private boolean f1112k = false;

    /* renamed from: l */
    private boolean f1113l = false;

    /* renamed from: m */
    private boolean f1114m = true;

    /* renamed from: n */
    private boolean f1115n = true;

    /* renamed from: o */
    private boolean f1116o = false;

    /* renamed from: p */
    private boolean f1117p = false;

    /* renamed from: q */
    private boolean f1118q = true;

    /* renamed from: j */
    private static AMapLocationProtocol f1103j = AMapLocationProtocol.HTTP;

    /* renamed from: a */
    static String f1102a = "";

    /* loaded from: classes.dex */
    public enum AMapLocationMode {
        Battery_Saving,
        Device_Sensors,
        Hight_Accuracy
    }

    /* loaded from: classes.dex */
    public enum AMapLocationProtocol {
        HTTP(0),
        HTTPS(1);


        /* renamed from: a */
        private int f1121a;

        AMapLocationProtocol(int i) {
            this.f1121a = i;
        }

        public final int getValue() {
            return this.f1121a;
        }
    }

    public static String getAPIKEY() {
        return f1102a;
    }

    public static void setLocationProtocol(AMapLocationProtocol aMapLocationProtocol) {
        f1103j = aMapLocationProtocol;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public AMapLocationClientOption m4268clone() {
        try {
            super.clone();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.f1104b = this.f1104b;
        aMapLocationClientOption.f1106d = this.f1106d;
        aMapLocationClientOption.f1111i = this.f1111i;
        aMapLocationClientOption.f1107e = this.f1107e;
        aMapLocationClientOption.f1112k = this.f1112k;
        aMapLocationClientOption.f1113l = this.f1113l;
        aMapLocationClientOption.f1108f = this.f1108f;
        aMapLocationClientOption.f1109g = this.f1109g;
        aMapLocationClientOption.f1105c = this.f1105c;
        aMapLocationClientOption.f1114m = this.f1114m;
        aMapLocationClientOption.f1115n = this.f1115n;
        aMapLocationClientOption.f1116o = this.f1116o;
        aMapLocationClientOption.f1117p = isSensorEnable();
        aMapLocationClientOption.f1118q = isWifiScan();
        return aMapLocationClientOption;
    }

    public long getHttpTimeOut() {
        return this.f1105c;
    }

    public long getInterval() {
        return this.f1104b;
    }

    public AMapLocationMode getLocationMode() {
        return this.f1111i;
    }

    public AMapLocationProtocol getLocationProtocol() {
        return f1103j;
    }

    public boolean isGpsFirst() {
        return this.f1113l;
    }

    public boolean isKillProcess() {
        return this.f1112k;
    }

    public boolean isLocationCacheEnable() {
        return this.f1115n;
    }

    public boolean isMockEnable() {
        return this.f1107e;
    }

    public boolean isNeedAddress() {
        return this.f1108f;
    }

    public boolean isOffset() {
        return this.f1114m;
    }

    public boolean isOnceLocation() {
        if (this.f1116o) {
            return true;
        }
        return this.f1106d;
    }

    public boolean isOnceLocationLatest() {
        return this.f1116o;
    }

    public boolean isSensorEnable() {
        return this.f1117p;
    }

    public boolean isWifiActiveScan() {
        return this.f1109g;
    }

    public boolean isWifiScan() {
        return this.f1118q;
    }

    public AMapLocationClientOption setGpsFirst(boolean z) {
        this.f1113l = z;
        return this;
    }

    public void setHttpTimeOut(long j) {
        this.f1105c = j;
    }

    public AMapLocationClientOption setInterval(long j) {
        if (j <= 800) {
            j = 800;
        }
        this.f1104b = j;
        return this;
    }

    public AMapLocationClientOption setKillProcess(boolean z) {
        this.f1112k = z;
        return this;
    }

    public void setLocationCacheEnable(boolean z) {
        this.f1115n = z;
    }

    public AMapLocationClientOption setLocationMode(AMapLocationMode aMapLocationMode) {
        this.f1111i = aMapLocationMode;
        return this;
    }

    public void setMockEnable(boolean z) {
        this.f1107e = z;
    }

    public AMapLocationClientOption setNeedAddress(boolean z) {
        this.f1108f = z;
        return this;
    }

    public AMapLocationClientOption setOffset(boolean z) {
        this.f1114m = z;
        return this;
    }

    public AMapLocationClientOption setOnceLocation(boolean z) {
        this.f1106d = z;
        return this;
    }

    public void setOnceLocationLatest(boolean z) {
        this.f1116o = z;
    }

    public void setSensorEnable(boolean z) {
        this.f1117p = z;
    }

    public void setWifiActiveScan(boolean z) {
        this.f1109g = z;
        this.f1110h = z;
    }

    public void setWifiScan(boolean z) {
        this.f1118q = z;
        this.f1109g = this.f1118q ? this.f1110h : false;
    }

    public String toString() {
        return "interval:" + String.valueOf(this.f1104b) + MqttTopic.MULTI_LEVEL_WILDCARD + "isOnceLocation:" + String.valueOf(this.f1106d) + MqttTopic.MULTI_LEVEL_WILDCARD + "locationMode:" + String.valueOf(this.f1111i) + MqttTopic.MULTI_LEVEL_WILDCARD + "isMockEnable:" + String.valueOf(this.f1107e) + MqttTopic.MULTI_LEVEL_WILDCARD + "isKillProcess:" + String.valueOf(this.f1112k) + MqttTopic.MULTI_LEVEL_WILDCARD + "isGpsFirst:" + String.valueOf(this.f1113l) + MqttTopic.MULTI_LEVEL_WILDCARD + "isNeedAddress:" + String.valueOf(this.f1108f) + MqttTopic.MULTI_LEVEL_WILDCARD + "isWifiActiveScan:" + String.valueOf(this.f1109g) + MqttTopic.MULTI_LEVEL_WILDCARD + "httpTimeOut:" + String.valueOf(this.f1105c) + MqttTopic.MULTI_LEVEL_WILDCARD + "isOffset:" + String.valueOf(this.f1114m) + MqttTopic.MULTI_LEVEL_WILDCARD + "isLocationCacheEnable:" + String.valueOf(this.f1115n) + MqttTopic.MULTI_LEVEL_WILDCARD + "isLocationCacheEnable:" + String.valueOf(this.f1115n) + MqttTopic.MULTI_LEVEL_WILDCARD + "isOnceLocationLatest:" + String.valueOf(this.f1116o) + MqttTopic.MULTI_LEVEL_WILDCARD + "sensorEnable:" + String.valueOf(this.f1117p) + MqttTopic.MULTI_LEVEL_WILDCARD;
    }
}
