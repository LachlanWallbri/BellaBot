package org.ros.android.android_tutorial_pubsub;

import android.util.Log;

public final class Encoder {
    private double AXIAL_MIN_ZERO;
    private double CONVERT_TO_METRIC;
    private double TANGENTIAL_MIN_ZERO;
    private double PERIOD = 0.025d;
    private float wheelSpacing = 0.3836f;
    private int cpr = 2400;
    private double wheelPerimeter = 0.44d;
    private final DoubleWheel distance = new DoubleWheel(0.0d, 0.0d);
    private Speed speed = new Speed(0.0d, 0.0d);

    public static final class DoubleWheel {
        private double left;
        private double right;

        public DoubleWheel copy(double left, double right) {
            return new DoubleWheel(left, right);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DoubleWheel)) {
                return false;
            }
            DoubleWheel doubleWheel = (DoubleWheel) other;
            return Double.compare(this.left, doubleWheel.left) == 0 && Double.compare(this.right, doubleWheel.right) == 0;
        }

        public DoubleWheel(double d, double d2) {
            this.left = d;
            this.right = d2;
        }

        public double getLeft() {
            return this.left;
        }

        public double getRight() {
            return this.right;
        }

        public void setLeft(double d) {
            this.left = d;
        }

        public void setRight(double d) {
            this.right = d;
        }

        public String toString() {
            return "left:" + this.left + " right:" + this.right;
        }
    }

    public Encoder() {
        double conversionFactor = wheelPerimeter / cpr;
        this.CONVERT_TO_METRIC = conversionFactor;
        this.TANGENTIAL_MIN_ZERO = Math.abs(conversionFactor);
        this.AXIAL_MIN_ZERO = Math.abs((this.CONVERT_TO_METRIC * 2) / this.wheelSpacing);
    }

    public static final class Speed {
        private double angularSpeed;
        private double lineSpeed;

        public Speed copy(double lineSpeed, double angularSpeed) {
            return new Speed(lineSpeed, angularSpeed);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Speed)) {
                return false;
            }
            Speed speed = (Speed) other;
            return Double.compare(this.lineSpeed, speed.lineSpeed) == 0 && Double.compare(this.angularSpeed, speed.angularSpeed) == 0;
        }

        public String toString() {
            return "Speed(lineSpeed=" + this.lineSpeed + ", angularSpeed=" + this.angularSpeed + ")";
        }

        public Speed(double d, double d2) {
            this.lineSpeed = d;
            this.angularSpeed = d2;
        }

        public double getAngularSpeed() {
            return this.angularSpeed;
        }

        public double getLineSpeed() {
            return this.lineSpeed;
        }

        public void setAngularSpeed(double d) {
            this.angularSpeed = d;
        }

        public void setLineSpeed(double d) {
            this.lineSpeed = d;
        }
    }

    public float getWheelSpacing() {
        return this.wheelSpacing;
    }

    public void setWheelSpacing(float f) {
        this.wheelSpacing = f;
    }

    public DoubleWheel getDistance() {
        return this.distance;
    }

    public Speed getSpeed() {
        return this.speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public void update(short encoderLeft, short encoderRight) {
        this.distance.setLeft(encoderLeft * this.CONVERT_TO_METRIC);
        this.distance.setRight(encoderRight * this.CONVERT_TO_METRIC);
        this.speed.setLineSpeed(((this.distance.getRight() + this.distance.getLeft()) / 2) / PERIOD);
        this.speed.setAngularSpeed(((this.distance.getRight() - this.distance.getLeft()) / this.wheelSpacing) / PERIOD);
        Log.d("Hardware", "Encoder " + this.distance.getLeft() + ',' + this.distance.getRight() + " origin:" + ((int) encoderLeft) + ',' + ((int) encoderRight));
    }

    public void setConfigure(float encoderCpr, float ratio, float simple, float newPerimeter, float newWheelSpacing) {
        int newCpr = (int) (encoderCpr * ratio * simple);
        this.cpr = newCpr;
        this.wheelPerimeter = newPerimeter;
        double conversionMetric = newPerimeter / newCpr;
        this.CONVERT_TO_METRIC = conversionMetric;
        this.TANGENTIAL_MIN_ZERO = Math.abs(conversionMetric);
        if (newWheelSpacing == 0.0f) {
            Log.e("Hardware", "Wheel Spacing can not be 0");
        } else {
            this.wheelSpacing = newWheelSpacing;
        }
        this.AXIAL_MIN_ZERO = Math.abs((this.CONVERT_TO_METRIC * 2) / this.wheelSpacing);
    }
}
