package com.example.jobeeekerapp;

import android.util.Log;

public final class Encoder {
    private double AXIAL_MIN_ZERO;
    private double CONVERT_TO_METRIC;
    private double TANGENTIAL_MIN_ZERO;
    private double PERIOD = 0.025d;
    private float wheelSpacing = 0.3836f;
    private int cpr = 2400;
    private double wheelPerimeter = 0.44d;
    private final DoubleWheelDistance distance = new DoubleWheelDistance(0.0d, 0.0d);
    private Speed speed = new Speed(0.0d, 0.0d);

    public static final class DoubleWheelDistance {
        private double left;
        private double right;

        /**
         * Creates a copy of this DoubleWheelDistance instance with new values.
         *
         * @param left The new value for the left wheel.
         * @param right The new value for the right wheel.
         * @return A new DoubleWheelDistance instance with the specified values.
         */
        public DoubleWheelDistance copy(double left, double right) {
            return new DoubleWheelDistance(left, right);
        }

        /**
         * Compares this DoubleWheelDistance object with the specified object for equality.
         *
         * @param other The object to compare with this DoubleWheelDistance object.
         * @return true if the specified object is equal to this DoubleWheelDistance object; false otherwise.
         */
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DoubleWheelDistance)) {
                return false;
            }
            DoubleWheelDistance doubleWheel = (DoubleWheelDistance) other;
            return Double.compare(this.left, doubleWheel.left) == 0 && Double.compare(this.right, doubleWheel.right) == 0;
        }

        /**
         * Constructs a new DoubleWheelDistance object with the specified left and right values.
         *
         * @param left The value for the left wheel.
         * @param right The value for the right wheel.
         */
        public DoubleWheelDistance(double left, double right) {
            this.left = left;
            this.right = right;
        }

        /**
         * Returns the value of the left wheel.
         *
         * @return The value of the left wheel.
         */
        public double getLeft() {
            return this.left;
        }

        /**
         * Returns the value of the right wheel.
         *
         * @return The value of the right wheel.
         */
        public double getRight() {
            return this.right;
        }

        /**
         * Sets the value of the left wheel.
         *
         * @param left The new value for the left wheel.
         */
        public void setLeft(double left) {
            this.left = left;
        }

        /**
         * Sets the value of the right wheel.
         *
         * @param right The new value for the right wheel.
         */
        public void setRight(double right) {
            this.right = right;
        }

        /**
         * Returns a string representation of the DoubleWheelDistance object.
         *
         * @return A string representation of the DoubleWheelDistance object.
         */
        @Override
        public String toString() {
            return "left: " + this.left + ", right: " + this.right;
        }
    }

    /**
     * Constructs an Encoder with the known parameters.
     *
     */
    public Encoder() {
        double conversionFactor = wheelPerimeter / cpr;
        this.CONVERT_TO_METRIC = conversionFactor;
        this.TANGENTIAL_MIN_ZERO = Math.abs(conversionFactor);
        this.AXIAL_MIN_ZERO = Math.abs((this.CONVERT_TO_METRIC * 2) / this.wheelSpacing);
    }

    public static final class Speed {
        private double angularSpeed;
        private double lineSpeed;

        /**
         * Creates a new Speed instance with the specified linear and angular speeds.
         *
         * @param lineSpeed the linear speed for the new instance
         * @param angularSpeed the angular speed for the new instance
         * @return a new Speed object with the specified speeds
         */
        public Speed copy(double lineSpeed, double angularSpeed) {
            return new Speed(lineSpeed, angularSpeed);
        }

        /**
         * Checks if this Speed object is equal to another object.
         * Two Speed objects are considered equal if their linear and angular speeds are the same.
         *
         * @param other the object to compare this Speed object to
         * @return true if this Speed object is equal to the other object, false otherwise
         */
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

        /**
         * Returns a string representation of the Speed object.
         *
         * @return a string representation of the Speed object in the format "lineSpeed: {lineSpeed}, angularSpeed: {angularSpeed}"
         */
        public String toString() {
            return "Speed(lineSpeed=" + this.lineSpeed + ", angularSpeed=" + this.angularSpeed + ")";
        }

        /**
         * Constructs a Speed object with specified linear and angular speeds.
         *
         * @param lineSpeed the linear speed
         * @param angularSpeed the angular speed
         */
        public Speed(double lineSpeed, double angularSpeed) {
            this.lineSpeed = lineSpeed;
            this.angularSpeed = angularSpeed;
        }

        /**
         * Gets the angular speed.
         *
         * @return the angular speed
         */
        public double getAngularSpeed() {
            return this.angularSpeed;
        }

        /**
         * Gets the linear speed.
         *
         * @return the linear speed
         */
        public double getLineSpeed() {
            return this.lineSpeed;
        }

        /**
         * Sets the angular speed.
         *
         * @param angularSpeed the angular speed to set
         */
        public void setAngularSpeed(double angularSpeed) {
            this.angularSpeed = angularSpeed;
        }

        /**
         * Sets the linear speed.
         *
         * @param lineSpeed the linear speed to set
         */
        public void setLineSpeed(double lineSpeed) {
            this.lineSpeed = lineSpeed;
        }
    }

    /**
     * Returns the spacing between the wheels.
     *
     * @return the wheel spacing in meters or units as specified by the system
     */
    public float getWheelSpacing() {
        return this.wheelSpacing;
    }

    /**
     * Retrieves the current distance measured by the wheel system.
     *
     * @return a DoubleWheelDistance object representing the distance measured by the wheels.
     */
    public DoubleWheelDistance getDistance() {
        return this.distance;
    }

    /**
     * Retrieves the current speed of the system.
     *
     * @return a Speed object representing the current speed, including linear and angular velocities.
     */
    public Speed getSpeed() {
        return this.speed;
    }

    /**
     * Updates the distance and speed based on the encoder readings.
     *
     * @param encoderLeft  The encoder reading for the left wheel, representing the distance covered.
     * @param encoderRight The encoder reading for the right wheel, representing the distance covered.
     *
     * This method updates the distance for both wheels using the encoder readings, calculates the linear
     * and angular speeds, and logs the updated values.
     */
    public void update(short encoderLeft, short encoderRight) {
        // Update the distance for the left wheel.
        this.distance.setLeft(encoderLeft * this.CONVERT_TO_METRIC);
        // Update the distance for the right wheel.
        this.distance.setRight(encoderRight * this.CONVERT_TO_METRIC);

        // Calculate the linear speed.
        this.speed.setLineSpeed(((this.distance.getRight() + this.distance.getLeft()) / 2) / PERIOD);
        // Calculate the angular speed
        this.speed.setAngularSpeed(((this.distance.getRight() - this.distance.getLeft()) / this.wheelSpacing) / PERIOD);

        Log.d("Hardware", "Encoder " + this.distance.getLeft() + ',' + this.distance.getRight() + " origin:" + ((int) encoderLeft) + ',' + ((int) encoderRight));
    }

    /**
     * Configures the encoder settings and recalculates related metrics based on the provided parameters.
     *
     * @param encoderCpr       The counts per revolution (CPR) of the encoder. This represents the number of
     *                         pulses or counts generated by the encoder per revolution of the wheel.
     * @param ratio            The ratio used to adjust the encoder counts. This is used to account for
     *                         gear ratios or other modifications.
     * @param simple           A scaling factor applied to the encoder counts, used to fine-tune the
     *                         counts per revolution.
     * @param newPerimeter     The new wheel perimeter. This is the distance around the wheel and is used to
     *                         convert encoder counts into actual distance traveled.
     * @param newWheelSpacing  The new wheel spacing. This is the distance between the left and right wheels,
     *                         which is used in calculations involving wheel separation, like angular velocity.
     *
     * This method recalculates the encoder counts per revolution (CPR), updates the wheel perimeter, and
     * recalculates the conversion factors and minimum thresholds for tangential and axial measurements.
     *
     * If the provided wheel spacing is zero, an error is logged as this value must be non-zero for accurate
     * calculations. The method also updates the minimum thresholds based on the new wheel perimeter and spacing.
     */
    public void setConfigure(float encoderCpr, float ratio, float simple, float newPerimeter, float newWheelSpacing) {
        // Calculate the new counts per revolution (CPR) based on the provided encoderCpr, ratio, and simple factors
        int newCpr = (int) (encoderCpr * ratio * simple);
        this.cpr = newCpr;

        // Update the wheel perimeter with the new value
        this.wheelPerimeter = newPerimeter;

        // Recalculate the conversion factor from encoder counts to metric units based on the new wheel perimeter and CPR
        double conversionMetric = newPerimeter / newCpr;
        this.CONVERT_TO_METRIC = conversionMetric;

        // Update the minimum threshold for tangential measurements
        this.TANGENTIAL_MIN_ZERO = Math.abs(conversionMetric);

        // Check if the new wheel spacing is zero, which is invalid. Log an error if so
        if (newWheelSpacing == 0.0f) {
            Log.e("Hardware", "Wheel Spacing can not be 0");
        } else {
            // Update the wheel spacing with the new value
            this.wheelSpacing = newWheelSpacing;
        }

        // Recalculate the minimum threshold for axial measurements based on the updated conversion factor and wheel spacing
        this.AXIAL_MIN_ZERO = Math.abs((this.CONVERT_TO_METRIC * 2) / this.wheelSpacing);
    }
}
