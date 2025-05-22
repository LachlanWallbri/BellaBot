package com.iflytek.location.result;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class GPSLocResult extends LocResult {
    private double lon = 0.0d;
    private double lat = 0.0d;
    private float accuracy = 0.0f;
    private String bearing = "0";
    private String altitude = "0";
    private String speed = "0";

    private void setAccuracy(String str) {
        this.accuracy = Float.parseFloat(str);
    }

    public float getAccuracy() {
        return this.accuracy;
    }

    public String getAltitude() {
        return this.altitude;
    }

    public String getBearing() {
        return this.bearing;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    public String getSpeed() {
        return this.speed;
    }

    public void setAccuracy(float f) {
        setAccuracy(String.valueOf(Math.round(f)));
    }

    public void setAltitude(String str) {
        this.altitude = str;
    }

    public void setBearing(String str) {
        this.bearing = str;
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

    public void setSpeed(String str) {
        this.speed = str;
    }
}
