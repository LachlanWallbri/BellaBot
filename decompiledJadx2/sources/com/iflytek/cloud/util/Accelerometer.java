package com.iflytek.cloud.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.pudutech.mirsdk.compat.topo.ConfigJson;

/* loaded from: classes3.dex */
public class Accelerometer {

    /* renamed from: c */
    private static CLOCKWISE_ANGLE f3481c;

    /* renamed from: a */
    private SensorManager f3482a;

    /* renamed from: b */
    private boolean f3483b = false;

    /* renamed from: d */
    private SensorEventListener f3484d = new SensorEventListener() { // from class: com.iflytek.cloud.util.Accelerometer.1
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == 1) {
                float f = sensorEvent.values[0];
                float f2 = sensorEvent.values[1];
                float f3 = sensorEvent.values[2];
                if (Math.abs(f) > 3.0f || Math.abs(f2) > 3.0f) {
                    if (Math.abs(f) > Math.abs(f2)) {
                        if (f > 0.0f) {
                            CLOCKWISE_ANGLE unused = Accelerometer.f3481c = CLOCKWISE_ANGLE.Deg0;
                            return;
                        } else {
                            CLOCKWISE_ANGLE unused2 = Accelerometer.f3481c = CLOCKWISE_ANGLE.Deg180;
                            return;
                        }
                    }
                    if (f2 > 0.0f) {
                        CLOCKWISE_ANGLE unused3 = Accelerometer.f3481c = CLOCKWISE_ANGLE.Deg90;
                    } else {
                        CLOCKWISE_ANGLE unused4 = Accelerometer.f3481c = CLOCKWISE_ANGLE.Deg270;
                    }
                }
            }
        }
    };

    /* loaded from: classes3.dex */
    public enum CLOCKWISE_ANGLE {
        Deg0(0),
        Deg90(1),
        Deg180(2),
        Deg270(3);

        private int value;

        CLOCKWISE_ANGLE(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public Accelerometer(Context context) {
        this.f3482a = null;
        this.f3482a = (SensorManager) context.getSystemService(ConfigJson.SENSOR);
        f3481c = CLOCKWISE_ANGLE.Deg0;
    }

    public void start() {
        if (this.f3483b) {
            return;
        }
        this.f3483b = true;
        f3481c = CLOCKWISE_ANGLE.Deg0;
        SensorManager sensorManager = this.f3482a;
        sensorManager.registerListener(this.f3484d, sensorManager.getDefaultSensor(1), 3);
    }

    public void stop() {
        if (this.f3483b) {
            this.f3483b = false;
            this.f3482a.unregisterListener(this.f3484d);
        }
    }

    public static int getDirection() {
        return f3481c.getValue();
    }
}
