package com.iflytek.location.result;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class NetLocResult extends LocResult {
    private String errorInfo;
    private double lon = 0.0d;
    private double lat = 0.0d;
    private float accuracy = 0.0f;
    private long time = 0;
    private String retype = "";
    private String rdesc = "";
    private String citycode = "";
    private String desc = "";
    private String adcode = "";
    private String country = "";
    private String province = "";
    private String city = "";
    private String district = "";
    private String road = "";
    private String street = "";
    private String poiname = "";
    private String cens = null;
    private String poiid = "";
    private String floor = "";
    private int errorCode = 0;
    private int coord = -1;
    private String mcell = "";
    private String number = "";
    private String aoiname = "";
    private boolean isOffset = true;
    private boolean isReversegeo = true;
    private String altitude = "0";
    private String speed = "0";
    private String bearing = "0";
    private JSONObject extra = null;

    private void setAccuracy(String str) {
        this.accuracy = Float.parseFloat(str);
    }

    public float getAccuracy() {
        return this.accuracy;
    }

    public String getAdcode() {
        return this.adcode;
    }

    public String getAltitude() {
        return this.altitude;
    }

    public String getAoiname() {
        return this.aoiname;
    }

    public String getBearing() {
        return this.bearing;
    }

    public String getCens() {
        return this.cens;
    }

    public String getCity() {
        return this.city;
    }

    public String getCitycode() {
        return this.citycode;
    }

    public int getCoord() {
        return this.coord;
    }

    public String getCountry() {
        return this.country;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getDistrict() {
        return this.district;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorInfo() {
        return this.errorInfo;
    }

    public JSONObject getExtra() {
        return this.extra;
    }

    public String getFloor() {
        return this.floor;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    public String getMcell() {
        return this.mcell;
    }

    public String getNumber() {
        return this.number;
    }

    public String getPoiid() {
        return this.poiid;
    }

    public String getPoiname() {
        return this.poiname;
    }

    public String getProvince() {
        return this.province;
    }

    public String getRdesc() {
        return this.rdesc;
    }

    public String getRetype() {
        return this.retype;
    }

    public String getRoad() {
        return this.road;
    }

    public String getSpeed() {
        return this.speed;
    }

    public String getStreet() {
        return this.street;
    }

    public long getTime() {
        return this.time;
    }

    public boolean hasAccuracy() {
        return this.accuracy > 0.0f;
    }

    public boolean isOffset() {
        return this.isOffset;
    }

    public boolean isReversegeo() {
        return this.isReversegeo;
    }

    public void setAccuracy(float f) {
        setAccuracy(String.valueOf(Math.round(f)));
    }

    public void setAdcode(String str) {
        this.adcode = str;
    }

    public void setAltitude(String str) {
        this.altitude = str;
    }

    public void setAoiname(String str) {
        this.aoiname = str;
    }

    public void setBearing(String str) {
        this.bearing = str;
    }

    public void setCens(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String[] split = str.split("\\*");
        int length = split.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String str2 = split[i];
            if (!TextUtils.isEmpty(str2)) {
                String[] split2 = str2.split(",");
                setLon(Double.parseDouble(split2[0]));
                setLat(Double.parseDouble(split2[1]));
                setAccuracy(Integer.parseInt(split2[2]));
                break;
            }
            i++;
        }
        this.cens = str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setCitycode(String str) {
        this.citycode = str;
    }

    public void setCoord(int i) {
        setCoord(String.valueOf(i));
    }

    public void setCoord(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            this.coord = -1;
            return;
        }
        if (str.equals("0")) {
            i = 0;
        } else {
            if (!str.equals("1")) {
                this.coord = -1;
                return;
            }
            i = 1;
        }
        this.coord = i;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public void setErrorInfo(String str) {
        this.errorInfo = str;
    }

    public void setExtra(JSONObject jSONObject) {
        this.extra = jSONObject;
    }

    public void setFloor(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("F", "");
            try {
                Integer.parseInt(str);
            } catch (Throwable unused) {
                str = null;
            }
        }
        this.floor = str;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public void setLat(String str) {
        this.lat = Double.parseDouble(str);
    }

    public void setLon(double d) {
        this.lon = d;
    }

    public void setLon(String str) {
        this.lon = Double.parseDouble(str);
    }

    public void setMcell(String str) {
        this.mcell = str;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public void setOffset(boolean z) {
        this.isOffset = z;
    }

    public void setPoiid(String str) {
        this.poiid = str;
    }

    public void setPoiname(String str) {
        this.poiname = str;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public void setRdesc(String str) {
        this.rdesc = str;
    }

    public void setRetype(String str) {
        this.retype = str;
    }

    public void setReversegeo(boolean z) {
        this.isReversegeo = z;
    }

    public void setRoad(String str) {
        this.road = str;
    }

    public void setSpeed(String str) {
        this.speed = str;
    }

    public void setStreet(String str) {
        this.street = str;
    }

    public void setTime(long j) {
        this.time = j;
    }
}
