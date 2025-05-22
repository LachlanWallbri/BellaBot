package com.loc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.pudutech.mirsdk.compat.topo.ConfigJson;

/* compiled from: AMapSensorManager.java */
/* renamed from: com.loc.bz */
/* loaded from: classes4.dex */
public final class C3851bz implements SensorEventListener {

    /* renamed from: a */
    SensorManager f3844a;

    /* renamed from: b */
    Sensor f3845b;

    /* renamed from: c */
    Sensor f3846c;

    /* renamed from: d */
    Sensor f3847d;

    /* renamed from: p */
    private Context f3859p;

    /* renamed from: e */
    public boolean f3848e = false;

    /* renamed from: f */
    public double f3849f = 0.0d;

    /* renamed from: g */
    public float f3850g = 0.0f;

    /* renamed from: q */
    private float f3860q = 1013.25f;

    /* renamed from: r */
    private float f3861r = 0.0f;

    /* renamed from: h */
    double f3851h = 0.0d;

    /* renamed from: i */
    double f3852i = 0.0d;

    /* renamed from: j */
    double f3853j = 0.0d;

    /* renamed from: k */
    double f3854k = 0.0d;

    /* renamed from: l */
    double[] f3855l = new double[3];

    /* renamed from: m */
    volatile double f3856m = 0.0d;

    /* renamed from: n */
    long f3857n = 0;

    /* renamed from: o */
    long f3858o = 0;

    public C3851bz(Context context) {
        this.f3859p = null;
        this.f3844a = null;
        this.f3845b = null;
        this.f3846c = null;
        this.f3847d = null;
        try {
            this.f3859p = context;
            if (this.f3844a == null) {
                this.f3844a = (SensorManager) this.f3859p.getSystemService(ConfigJson.SENSOR);
            }
            try {
                this.f3845b = this.f3844a.getDefaultSensor(6);
            } catch (Throwable unused) {
            }
            try {
                this.f3846c = this.f3844a.getDefaultSensor(11);
            } catch (Throwable unused2) {
            }
            try {
                this.f3847d = this.f3844a.getDefaultSensor(1);
            } catch (Throwable unused3) {
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapSensorManager", "<init>");
        }
    }

    /* renamed from: a */
    public final double m2733a(double d) {
        return d + this.f3849f;
    }

    /* renamed from: a */
    public final void m2734a() {
        SensorManager sensorManager = this.f3844a;
        if (sensorManager == null || this.f3848e) {
            return;
        }
        this.f3848e = true;
        try {
            if (this.f3845b != null) {
                sensorManager.registerListener(this, this.f3845b, 3);
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapSensorManager", "registerListener mPressure");
        }
        try {
            if (this.f3846c != null) {
                this.f3844a.registerListener(this, this.f3846c, 3);
            }
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "AMapSensorManager", "registerListener mRotationVector");
        }
        try {
            if (this.f3847d != null) {
                this.f3844a.registerListener(this, this.f3847d, 1);
            }
        } catch (Throwable th3) {
            C3880f.m3097a(th3, "AMapSensorManager", "registerListener mAcceleroMeterVector");
        }
    }

    /* renamed from: a */
    public final void m2735a(float f) {
        if (f <= 0.0f) {
            this.f3860q = 1013.25f;
        } else {
            this.f3860q = f;
        }
    }

    /* renamed from: b */
    public final float m2736b() {
        return this.f3850g;
    }

    /* renamed from: c */
    public final void m2737c() {
        SensorManager sensorManager = this.f3844a;
        if (sensorManager == null || !this.f3848e) {
            return;
        }
        this.f3848e = false;
        try {
            if (this.f3845b != null) {
                sensorManager.unregisterListener(this, this.f3845b);
            }
        } catch (Throwable unused) {
        }
        try {
            if (this.f3846c != null) {
                this.f3844a.unregisterListener(this, this.f3846c);
            }
        } catch (Throwable unused2) {
        }
        try {
            if (this.f3847d != null) {
                this.f3844a.unregisterListener(this, this.f3847d);
            }
        } catch (Throwable unused3) {
        }
    }

    /* renamed from: d */
    public final float m2738d() {
        return this.f3861r;
    }

    /* renamed from: e */
    public final double m2739e() {
        return this.f3854k;
    }

    /* renamed from: f */
    public final void m2740f() {
        try {
            m2737c();
            this.f3845b = null;
            this.f3846c = null;
            this.f3844a = null;
            this.f3847d = null;
            this.f3848e = false;
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapSensorManager", "destroy");
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        String str;
        float[] fArr;
        if (sensorEvent == null) {
            return;
        }
        int type = sensorEvent.sensor.getType();
        if (type == 1) {
            try {
                if (this.f3847d != null) {
                    float[] fArr2 = (float[]) sensorEvent.values.clone();
                    this.f3855l[0] = (this.f3855l[0] * 0.800000011920929d) + (fArr2[0] * 0.19999999f);
                    this.f3855l[1] = (this.f3855l[1] * 0.800000011920929d) + (fArr2[1] * 0.19999999f);
                    this.f3855l[2] = (this.f3855l[2] * 0.800000011920929d) + (fArr2[2] * 0.19999999f);
                    this.f3851h = fArr2[0] - this.f3855l[0];
                    this.f3852i = fArr2[1] - this.f3855l[1];
                    this.f3853j = fArr2[2] - this.f3855l[2];
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.f3857n >= 100) {
                        double sqrt = Math.sqrt((this.f3851h * this.f3851h) + (this.f3852i * this.f3852i) + (this.f3853j * this.f3853j));
                        this.f3858o++;
                        this.f3857n = currentTimeMillis;
                        this.f3856m += sqrt;
                        if (this.f3858o >= 30) {
                            this.f3854k = this.f3856m / this.f3858o;
                            this.f3856m = 0.0d;
                            this.f3858o = 0L;
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            } catch (Throwable th) {
                C3880f.m3097a(th, "AMapSensorManager", "accelerometer");
                return;
            }
        }
        if (type == 6) {
            try {
                if (this.f3845b != null) {
                    float[] fArr3 = (float[]) sensorEvent.values.clone();
                    if (fArr3 != null) {
                        this.f3850g = fArr3[0];
                    }
                    if (fArr3 != null) {
                        this.f3849f = C3876cx.m2961a(SensorManager.getAltitude(this.f3860q, fArr3[0]));
                        return;
                    }
                    return;
                }
                return;
            } catch (Throwable th2) {
                th = th2;
                str = "doComputeAltitude";
            }
        } else {
            if (type != 11) {
                return;
            }
            try {
                if (this.f3846c == null || (fArr = (float[]) sensorEvent.values.clone()) == null) {
                    return;
                }
                float[] fArr4 = new float[9];
                SensorManager.getRotationMatrixFromVector(fArr4, fArr);
                SensorManager.getOrientation(fArr4, new float[3]);
                this.f3861r = (float) Math.toDegrees(r13[0]);
                this.f3861r = (float) Math.floor(this.f3861r > 0.0f ? this.f3861r : this.f3861r + 360.0f);
                return;
            } catch (Throwable th3) {
                th = th3;
                str = "doComputeBearing";
            }
        }
        C3880f.m3097a(th, "AMapSensorManager", str);
    }
}
