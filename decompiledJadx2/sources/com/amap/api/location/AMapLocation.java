package com.amap.api.location;

import android.location.Location;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.loc.C3876cx;
import com.loc.C3880f;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AMapLocation extends Location {
    public static final int ERROR_CODE_FAILURE_AUTH = 7;
    public static final int ERROR_CODE_FAILURE_CELL = 11;
    public static final int ERROR_CODE_FAILURE_CONNECTION = 4;
    public static final int ERROR_CODE_FAILURE_INIT = 9;
    public static final int ERROR_CODE_FAILURE_LOCATION = 6;
    public static final int ERROR_CODE_FAILURE_LOCATION_PARAMETER = 3;
    public static final int ERROR_CODE_FAILURE_LOCATION_PERMISSION = 12;
    public static final int ERROR_CODE_FAILURE_NOENOUGHSATELLITES = 14;
    public static final int ERROR_CODE_FAILURE_NOWIFIANDAP = 13;
    public static final int ERROR_CODE_FAILURE_PARSER = 5;
    public static final int ERROR_CODE_FAILURE_SIMULATION_LOCATION = 15;
    public static final int ERROR_CODE_FAILURE_WIFI_INFO = 2;
    public static final int ERROR_CODE_INVALID_PARAMETER = 1;
    public static final int ERROR_CODE_SERVICE_FAIL = 10;
    public static final int ERROR_CODE_UNKNOWN = 8;
    public static final int GPS_ACCURACY_BAD = 0;
    public static final int GPS_ACCURACY_GOOD = 1;
    public static final int GPS_ACCURACY_UNKNOWN = -1;
    public static final int LOCATION_SUCCESS = 0;
    public static final int LOCATION_TYPE_AMAP = 7;
    public static final int LOCATION_TYPE_CELL = 6;
    public static final int LOCATION_TYPE_FAST = 3;
    public static final int LOCATION_TYPE_FIX_CACHE = 4;
    public static final int LOCATION_TYPE_GPS = 1;
    public static final int LOCATION_TYPE_OFFLINE = 8;
    public static final int LOCATION_TYPE_SAME_REQ = 2;
    public static final int LOCATION_TYPE_WIFI = 5;

    /* renamed from: a */
    protected String f1076a;

    /* renamed from: b */
    protected String f1077b;

    /* renamed from: c */
    protected String f1078c;

    /* renamed from: d */
    private String f1079d;

    /* renamed from: e */
    private String f1080e;

    /* renamed from: f */
    private String f1081f;

    /* renamed from: g */
    private String f1082g;

    /* renamed from: h */
    private String f1083h;

    /* renamed from: i */
    private String f1084i;

    /* renamed from: j */
    private String f1085j;

    /* renamed from: k */
    private String f1086k;

    /* renamed from: l */
    private String f1087l;

    /* renamed from: m */
    private String f1088m;

    /* renamed from: n */
    private String f1089n;

    /* renamed from: o */
    private boolean f1090o;

    /* renamed from: p */
    private int f1091p;

    /* renamed from: q */
    private String f1092q;

    /* renamed from: r */
    private String f1093r;

    /* renamed from: s */
    private int f1094s;

    /* renamed from: t */
    private double f1095t;

    /* renamed from: u */
    private double f1096u;

    /* renamed from: v */
    private int f1097v;

    /* renamed from: w */
    private String f1098w;

    /* renamed from: x */
    private int f1099x;

    public AMapLocation(Location location) {
        super(location);
        this.f1079d = "";
        this.f1080e = "";
        this.f1081f = "";
        this.f1082g = "";
        this.f1083h = "";
        this.f1084i = "";
        this.f1085j = "";
        this.f1086k = "";
        this.f1087l = "";
        this.f1088m = "";
        this.f1089n = "";
        this.f1090o = true;
        this.f1091p = 0;
        this.f1092q = "success";
        this.f1093r = "";
        this.f1094s = 0;
        this.f1095t = 0.0d;
        this.f1096u = 0.0d;
        this.f1097v = 0;
        this.f1098w = "";
        this.f1099x = -1;
        this.f1076a = "";
        this.f1077b = "";
        this.f1078c = "";
        this.f1095t = location.getLatitude();
        this.f1096u = location.getLongitude();
    }

    public AMapLocation(String str) {
        super(str);
        this.f1079d = "";
        this.f1080e = "";
        this.f1081f = "";
        this.f1082g = "";
        this.f1083h = "";
        this.f1084i = "";
        this.f1085j = "";
        this.f1086k = "";
        this.f1087l = "";
        this.f1088m = "";
        this.f1089n = "";
        this.f1090o = true;
        this.f1091p = 0;
        this.f1092q = "success";
        this.f1093r = "";
        this.f1094s = 0;
        this.f1095t = 0.0d;
        this.f1096u = 0.0d;
        this.f1097v = 0;
        this.f1098w = "";
        this.f1099x = -1;
        this.f1076a = "";
        this.f1077b = "";
        this.f1078c = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public JSONObject mo490a(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (i == 1) {
                try {
                    jSONObject.put("altitude", getAltitude());
                    jSONObject.put("speed", getSpeed());
                    jSONObject.put("bearing", getBearing());
                } catch (Throwable unused) {
                }
                jSONObject.put("citycode", this.f1082g);
                jSONObject.put(TmpConstant.SERVICE_DESC, this.f1078c);
                jSONObject.put("adcode", this.f1083h);
                jSONObject.put("country", this.f1086k);
                jSONObject.put("province", this.f1079d);
                jSONObject.put("city", this.f1080e);
                jSONObject.put("district", this.f1081f);
                jSONObject.put("road", this.f1087l);
                jSONObject.put("street", this.f1088m);
                jSONObject.put("number", this.f1089n);
                jSONObject.put("poiname", this.f1085j);
                jSONObject.put(AUserTrack.UTKEY_ERROR_CODE, this.f1091p);
                jSONObject.put("errorInfo", this.f1092q);
                jSONObject.put("locationType", this.f1094s);
                jSONObject.put("locationDetail", this.f1093r);
                jSONObject.put("aoiname", this.f1098w);
                jSONObject.put("address", this.f1084i);
                jSONObject.put("poiid", this.f1076a);
                jSONObject.put("floor", this.f1077b);
            } else if (i != 2) {
                if (i != 3) {
                    return jSONObject;
                }
                jSONObject.put("provider", getProvider());
                jSONObject.put("lon", getLongitude());
                jSONObject.put("lat", getLatitude());
                jSONObject.put("accuracy", getAccuracy());
                jSONObject.put("isOffset", this.f1090o);
                return jSONObject;
            }
            jSONObject.put("time", getTime());
            jSONObject.put("provider", getProvider());
            jSONObject.put("lon", getLongitude());
            jSONObject.put("lat", getLatitude());
            jSONObject.put("accuracy", getAccuracy());
            jSONObject.put("isOffset", this.f1090o);
            return jSONObject;
        } catch (Throwable th) {
            C3880f.m3097a(th, "AmapLoc", "toStr");
            return null;
        }
    }

    @Override // android.location.Location
    public float getAccuracy() {
        return super.getAccuracy();
    }

    public String getAdCode() {
        return this.f1083h;
    }

    public String getAddress() {
        return this.f1084i;
    }

    @Override // android.location.Location
    public double getAltitude() {
        return super.getAltitude();
    }

    public String getAoiName() {
        return this.f1098w;
    }

    @Override // android.location.Location
    public float getBearing() {
        return super.getBearing();
    }

    public String getBuildingId() {
        return this.f1076a;
    }

    public String getCity() {
        return this.f1080e;
    }

    public String getCityCode() {
        return this.f1082g;
    }

    public String getCountry() {
        return this.f1086k;
    }

    public String getDistrict() {
        return this.f1081f;
    }

    public int getErrorCode() {
        return this.f1091p;
    }

    public String getErrorInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1092q);
        if (this.f1091p != 0) {
            sb.append(" 请到http://lbs.amap.com/api/android-location-sdk/guide/utilities/errorcode/查看错误码说明");
            sb.append(",错误详细信息:" + this.f1093r);
        }
        this.f1092q = sb.toString();
        return this.f1092q;
    }

    public String getFloor() {
        return this.f1077b;
    }

    public int getGpsAccuracyStatus() {
        return this.f1099x;
    }

    @Override // android.location.Location
    public double getLatitude() {
        return this.f1095t;
    }

    public String getLocationDetail() {
        return this.f1093r;
    }

    public int getLocationType() {
        return this.f1094s;
    }

    @Override // android.location.Location
    public double getLongitude() {
        return this.f1096u;
    }

    public String getPoiName() {
        return this.f1085j;
    }

    @Override // android.location.Location
    public String getProvider() {
        return super.getProvider();
    }

    public String getProvince() {
        return this.f1079d;
    }

    public String getRoad() {
        return this.f1087l;
    }

    public int getSatellites() {
        return this.f1097v;
    }

    @Override // android.location.Location
    public float getSpeed() {
        return super.getSpeed();
    }

    public String getStreet() {
        return this.f1088m;
    }

    public String getStreetNum() {
        return this.f1089n;
    }

    public boolean isOffset() {
        return this.f1090o;
    }

    public void setAdCode(String str) {
        this.f1083h = str;
    }

    public void setAddress(String str) {
        this.f1084i = str;
    }

    public void setAoiName(String str) {
        this.f1098w = str;
    }

    public void setBuildingId(String str) {
        this.f1076a = str;
    }

    public void setCity(String str) {
        this.f1080e = str;
    }

    public void setCityCode(String str) {
        this.f1082g = str;
    }

    public void setCountry(String str) {
        this.f1086k = str;
    }

    public void setDistrict(String str) {
        this.f1081f = str;
    }

    public void setErrorCode(int i) {
        if (this.f1091p != 0) {
            return;
        }
        this.f1092q = C3876cx.m2986b(i);
        this.f1091p = i;
    }

    public void setErrorInfo(String str) {
        this.f1092q = str;
    }

    public void setFloor(String str) {
        this.f1077b = str;
    }

    public void setGpsAccuracyStatus(int i) {
        this.f1099x = i;
    }

    @Override // android.location.Location
    public void setLatitude(double d) {
        this.f1095t = d;
    }

    public void setLocationDetail(String str) {
        this.f1093r = str;
    }

    public void setLocationType(int i) {
        this.f1094s = i;
    }

    @Override // android.location.Location
    public void setLongitude(double d) {
        this.f1096u = d;
    }

    public void setNumber(String str) {
        this.f1089n = str;
    }

    public void setOffset(boolean z) {
        this.f1090o = z;
    }

    public void setPoiName(String str) {
        this.f1085j = str;
    }

    public void setProvince(String str) {
        this.f1079d = str;
    }

    public void setRoad(String str) {
        this.f1087l = str;
    }

    public void setSatellites(int i) {
        this.f1097v = i;
    }

    public void setStreet(String str) {
        this.f1088m = str;
    }

    public String toStr() {
        return toStr(1);
    }

    public String toStr(int i) {
        JSONObject jSONObject;
        try {
            jSONObject = mo490a(i);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocation", "toStr part2");
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }

    @Override // android.location.Location
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append("latitude=" + this.f1095t + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("longitude=" + this.f1096u + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("province=" + this.f1079d + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("city=" + this.f1080e + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("district=" + this.f1081f + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("cityCode=" + this.f1082g + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("adCode=" + this.f1083h + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("address=" + this.f1084i + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("country=" + this.f1086k + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("road=" + this.f1087l + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("poiName=" + this.f1085j + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("street=" + this.f1088m + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("streetNum=" + this.f1089n + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("aoiName=" + this.f1098w + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("poiid=" + this.f1076a + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("floor=" + this.f1077b + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("errorCode=" + this.f1091p + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("errorInfo=" + this.f1092q + MqttTopic.MULTI_LEVEL_WILDCARD);
            stringBuffer.append("locationDetail=" + this.f1093r + MqttTopic.MULTI_LEVEL_WILDCARD);
            StringBuilder sb = new StringBuilder("locationType=");
            sb.append(this.f1094s);
            stringBuffer.append(sb.toString());
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }
}
