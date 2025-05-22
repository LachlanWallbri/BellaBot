package org.ros.android.android_tutorial_pubsub;

import android.util.Log;

public final class Gyroscope {
    private double CONVERT_TO_METRIC = 2.6631610446506074E-4d;
    private final double PERIOD = 0.025d;
    private final Data last = new Data();
    private final Data accumulate = new Data();

    public static final class Data {
        private double x;
        private double y;
        private double z;

        public double getX() {
            return this.x;
        }

        public void setX(double d) {
            this.x = d;
        }

        public double getY() {
            return this.y;
        }

        public void setY(double d) {
            this.y = d;
        }

        public double getZ() {
            return this.z;
        }

        public void setZ(double d) {
            this.z = d;
        }

        public String toString() {
            return "x:" + this.x + " y:" + this.y + " z:" +this.z;
        }
    }
    
    public Data getLast() {
        return this.last;
    }

    public Data getAccumulate() {
        return this.accumulate;
    }

    public void update(short x, short y, short z) {
        this.last.setX(x * this.CONVERT_TO_METRIC);
        this.last.setY(y * this.CONVERT_TO_METRIC);
        this.last.setZ(z * this.CONVERT_TO_METRIC);
        Data data = this.accumulate;
        data.setX(data.getX() + (this.last.getX() * this.PERIOD));
        data.setY(data.getY() + (this.last.getY() * this.PERIOD));
        data.setZ(data.getZ() + (this.last.getZ() * this.PERIOD));
        Log.d("Hardware", "Gyroscope current:" + getLast() + " accumulate:" + getAccumulate());
    }
}
