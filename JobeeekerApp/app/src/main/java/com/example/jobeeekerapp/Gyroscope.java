package com.example.jobeeekerapp;

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

        /**
         * Gets the x-coordinate value.
         *
         * @return The x-coordinate as a {@code double}.
         */
        public double getX() {
            return this.x;
        }

        /**
         * Sets the x-coordinate value.
         *
         * @param d The new x-coordinate value to set, specified as a {@code double}.
         */
        public void setX(double d) {
            this.x = d;
        }

        /**
         * Gets the y-coordinate value.
         *
         * @return The y-coordinate as a {@code double}.
         */
        public double getY() {
            return this.y;
        }

        /**
         * Sets the y-coordinate value.
         *
         * @param d The new y-coordinate value to set, specified as a {@code double}.
         */
        public void setY(double d) {
            this.y = d;
        }

        /**
         * Gets the z-coordinate value.
         *
         * @return The z-coordinate as a {@code double}.
         */
        public double getZ() {
            return this.z;
        }

        /**
         * Sets the z-coordinate value.
         *
         * @param d The new z-coordinate value to set, specified as a {@code double}.
         */
        public void setZ(double d) {
            this.z = d;
        }

        /**
         * Returns a string representation of the coordinates in the format {@code "x:<x> y:<y> z:<z>"}.
         *
         * @return A String that represents the coordinates with their respective values.
         */
        public String toString() {
            return "x:" + this.x + " y:" + this.y + " z:" +this.z;
        }
    }

    /**
     * Retrieves the last recorded Data object.
     *
     * @return The Data object representing the last recorded data.
     */
    public Data getLast() {
        return this.last;
    }

    /**
     * Retrieves the accumulated Data object.
     *
     * @return The Data object representing the accumulated data.
     */
    public Data getAccumulate() {
        return this.accumulate;
    }

    /**
     * Updates the Data objects with new gyroscope measurements.
     *
     * This method processes the provided gyroscope readings (x, y, z), converts them to metric units,
     * and updates both the last and accumulative Data objects.
     * The `last` object is updated with the most recent measurement, while the `accumulate` object
     * aggregates the measurements over time.
     *
     * @param x The new gyroscope reading for the x-axis.
     * @param y The new gyroscope reading for the y-axis.
     * @param z The new gyroscope reading for the z-axis.
     */
    public void update(short x, short y, short z) {
        // Update the 'last' Data object with the most recent gyroscope readings
        // Convert the raw sensor data to metric units using the conversion factor
        this.last.setX(x * this.CONVERT_TO_METRIC);
        this.last.setY(y * this.CONVERT_TO_METRIC);
        this.last.setZ(z * this.CONVERT_TO_METRIC);

        // Retrieve the current accumulated data
        Data data = this.accumulate;

        // Update the accumulated values by adding the new measurements
        // Multiply the last measurement by the period to aggregate over time
        data.setX(data.getX() + (this.last.getX() * this.PERIOD));
        data.setY(data.getY() + (this.last.getY() * this.PERIOD));
        data.setZ(data.getZ() + (this.last.getZ() * this.PERIOD));

        Log.d("Hardware", "Gyroscope current:" + getLast() + " accumulate:" + getAccumulate());
    }
}