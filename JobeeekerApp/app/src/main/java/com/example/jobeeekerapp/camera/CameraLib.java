package com.unsw.bellabot;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.Log;

import com.pudutech.mirsdk.hardware.cameralib.CameraNative;
import com.pudutech.mirsdk.hardware.serialize.SensorImageContainer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import kotlin.jvm.internal.Intrinsics;


/**
 * The {@code CameraLib} class provides methods to manage and interact with the
 * top-facing marker camera and the monocular front-facing camera.
 * <p>
 * This class is designed to handle operations such as opening, closing,
 * and capturing frames from the cameras. It also provides utility functions
 * for converting captured frames to an integer array and (Android) Bitmap.
 */
public class CameraLib {
    private static final CameraLib instance = new CameraLib();
    private static String lastError;
    private static final String TAG = "CameraLib";
    static {
        System.loadLibrary("markernative");
    }

    public static CameraLib getInstance() {
        return instance;
    }

    /****************************************
     *  MARKER TOP-FACING CAMERA FUNCTIONS  *
     ****************************************/

    /**
     * Opens the top-facing marker camera stream using the specified hardcoded configuration files.
     * @returns a {@link Result} indicating the success or failure of the operation.
     */
    public final synchronized Result openMarkerCamera() {
        String cameraConfigFile = "/sdcard/pudu/config/camera.config";
        String oldConfigFile = "/sdcard/PuduRobotMap/camera.config";
        if (!new File(cameraConfigFile).exists()) {
            File file = new File("/sdcard/pudu/config/");
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!new File(oldConfigFile).exists()) {
                lastError = "missing file " + cameraConfigFile;
                return new Result(false, lastError);
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(cameraConfigFile);
                try {
                    FileInputStream fileInputStream = new FileInputStream(oldConfigFile);
                    try {
                        byte[] bArr = new byte[256];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.close();
                        fileInputStream.close();
                    } catch (Throwable t) {
                        fileOutputStream.close();
                        fileInputStream.close();
                        String error = "copy camera config file throwable: " + t;
                        Log.d(TAG, error);
                        return new Result(false, error);
                    }
                } catch (Throwable t) {
                    fileOutputStream.close();
                    String error = "Error opening camera input config file " + oldConfigFile + ": " + t;
                    Log.d(TAG, error);
                    return new Result(false, error);
                }
            } catch (Exception e) {
                String error = "Error opening camera output config file " + cameraConfigFile + ": " + e;
                Log.d(TAG, error);
                return new Result(false, error);
            }
        }
        int openMarker = CameraNative.openMarker(cameraConfigFile);
        if (openMarker == 0) {
            return new Result(true, "camera open success");
        }
        Log.d(TAG, "camera open fail");
        String lastError2 = CameraNative.getLastError(openMarker);
        Intrinsics.checkExpressionValueIsNotNull(lastError2, "CameraNative.getLastError(ret)");
        return new Result(false, lastError2);
    }

    /**
     * Closes the top-facing marker camera if it is currently open.
     */
    public final void closeMarkerCamera() {
        if (CameraNative.isOpenedMarker()) {
            CameraNative.closeMarker();
        }
    }

    /**
     * Retrieves the current frame from the top-facing marker camera buffer .
     *
     * @return a {@link SensorImageContainer} containing the captured frame data,
     *         or {@code null} if the frame could not be read
     */
    public final SensorImageContainer getMarkerFrame() {
        SensorImageContainer markerFrame = CameraNative.getMarkerFrame();
        if (markerFrame == null) {
            Log.d(TAG, "read marker frame null");
        }
        return markerFrame;
    }

    /**
     * Retrieves the current frame from the top-facing marker camera and converts it to an
     * integer array representing the pixel data in RGB format.
     *
     * <p>This method acts as a convenience wrapper that combines capturing a frame from
     * the marker camera and converting it into an RGB integer array format. The resulting
     * array contains the pixel data of the frame, where each element represents a pixel's RGB value.</p>
     *
     * @return an integer array containing the pixel data in RGB format, or {@code null} if the frame
     *         is {@code null} or the data could not be read.
     * @throws IOException if an error occurs while reading the frame data.
     * @see #getMarkerFrame()
     * @see #getFrameIArr(SensorImageContainer)
     */
    public final int[] getMarkerIArr() throws IOException {
        return getFrameIArr(getMarkerFrame());
    }

    /**
     * Gets the pixel width of the marker camera frame.
     * 
     * @returns an integer of the number of columns of pixels (width) of the marker camera frame.
     */
    public final int getMarkerRows() {
        return getMonocularFrame().getRows();
    }

    /**
     * Gets the pixel width of the marker camera frame.
     * 
     * @returns an integer of the number of columns of pixels (width) of the marker camera frame.
     */
    public final int getMarkerCols() {
        return getMonocularFrame().getCols();
    }

    /*********************************************
     *  MONOCULAR FRONT-FACING CAMERA FUNCTIONS  *
     *********************************************/

    /**
     * Opens the front-facing monocular camera stream using the specified hardcoded configuration file.
     * @return a {@link Result} indicating the success or failure of the operation
     */
    public final synchronized Result openMonocularCamera() {
        // Not sure what the second string is used for. Thought it might be for rotation, but
        // the decompiled strings used don't particularly make sense: "1bcf0b09" and "0bda3035".
        int openMonocular = CameraNative.openMonocular("/sdcard/pudu/config/Monocular_camera.config", "0bda3035");
        if (openMonocular == 0) {
            return new Result(true, "camera open success");
        }
        Log.d(TAG, "camera open fail");
        String lastError2 = CameraNative.getLastError(openMonocular);
        Intrinsics.checkExpressionValueIsNotNull(lastError2, "CameraNative.getLastError(ret)");
        return new Result(false, lastError2);
    }

    /**
     * Closes the front-facing monocular camera if it is currently open.
     */
    public final void closeMonocularCamera() {
        if (CameraNative.isOpenedMonocular()) {
            CameraNative.closeMonocular();
        }
    }

    /**
     * Retrieves the current frame from the front-facing monocular camera buffer.
     *
     * @return a {@link SensorImageContainer} containing the captured frame data,
     * or {@code null} if the frame could not be read
     */
    public final SensorImageContainer getMonocularFrame() {
        SensorImageContainer monocularFrame = CameraNative.getMonocularFrame();
        if (monocularFrame == null) {
            Log.d(TAG, "read monocular frame null");
        }
        return monocularFrame;
    }

    /**
     * Retrieves the current frame from the front-facing monocular camera and converts it to an
     * integer array representing the pixel data in RGB format.
     *
     * <p>This method is a convenience wrapper that combines the process of capturing a frame from
     * the monocular camera and converting it into an RGB integer array format. The returned array
     * contains the pixel data of the frame, where each element represents a pixel's RGB value.</p>
     *
     * @return an integer array containing the pixel data in RGB format, or {@code null} if the frame
     *         is {@code null} or the data could not be read
     * @throws IOException if an error occurs while reading the frame data
     * @see #getMonocularFrame()
     * @see #getFrameIArr(SensorImageContainer)
     */
    public final int[] getMonocularIArr() throws IOException {
        return getFrameIArr(getMonocularFrame());
    }

    /**
     * Gets the pixel height of the monocular camera frame.
     * 
     * @returns an integer of the number of rows of pixels (height) of the monocular camera frame.
     */
    public final int getMonocularRows() {
        return getMonocularFrame().getRows();
    }

    /**
     * Gets the pixel width of the monocular camera frame.
     * 
     * @returns an integer of the number of columns of pixels (width) of the monocular camera frame.
     */
    public final int getMonocularCols() {
        return getMonocularFrame().getCols();
    }

    /***********************
     *  UTILITY FUNCTIONS  *
     ***********************/

    /**
     * Converts a {@link SensorImageContainer} frame to an integer array representing
     * the pixel data in RGB format (assuming each pixel is stored on 4 bytes).
     *
     * @param frame the {@link SensorImageContainer} containing the frame data
     * @return an integer array containing the pixel data in RGB format,
     *         or {@code null} if the frame is {@code null} or the data could not be read
     * @throws IOException if an error occurs while reading the frame data
     */
    public final int[] getFrameIArr(SensorImageContainer frame) throws IOException {
        if (frame == null) {
            return null;
        }
        int rows = frame.getRows();
        int cols = frame.getCols();
        int memSize = frame.getMemorySize();
        int elementSize = frame.getElementSize();
        long time = SystemClock.elapsedRealtime(); // time (ms) since Android OS boot

        FileInputStream fileInputStream = new FileInputStream(frame.getFileDescriptor());
        byte[] bArr = new byte[memSize];
        int fileHeaderOffset = 12;
        if (fileInputStream.read(bArr) <= fileHeaderOffset) {
            return null;
        }
        Log.d(TAG, "on rgb camera frame width:" + cols + " height:" + rows);
        int pixels = rows * cols;
        int[] iArr = new int[pixels];
        for (int pixIndex = 0; pixIndex < pixels; pixIndex++) {
            int pixRgbIndex = pixIndex * 3;
            if (pixRgbIndex >= memSize - (fileHeaderOffset + 3)) break; // to account for colour offset and fileheader offset
            iArr[pixIndex] = Color.rgb(Byte.toUnsignedInt(bArr[pixRgbIndex + 2 + fileHeaderOffset]) & 255,
                                Byte.toUnsignedInt(bArr[pixRgbIndex + 1 + fileHeaderOffset]) & 255,
                                 Byte.toUnsignedInt(bArr[pixRgbIndex + fileHeaderOffset]) & 255);
        }
        return iArr;
    }

    /**
     * Converts an integer array of pixel data to a {@link Bitmap}.
     *
     * @param iArr an integer array containing pixel data in RGB format
     * @param cols the number of columns (width) of the image
     * @param rows the number of rows (height) of the image
     * @return a {@link Bitmap} representing the image
     */
    public final Bitmap getFrameBmp(int[] iArr, int cols, int rows) {
        if (iArr == null) {
            return Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        }
        return Bitmap.createBitmap(iArr, rows, cols, Bitmap.Config.ARGB_8888);
    }

    /**
     * The {@code Result} class represents the result of a camera operation,
     * including whether it was successful and a description of the result.
     */
    public static final class Result {
        private final String description;
        private final boolean isSuccess;

        /**
         * Constructs a {@code Result} with the specified success flag and description.
         *
         * @param isSuccess a boolean indicating whether the operation was successful
         * @param description a string describing the result
         */
        public Result(boolean isSuccess, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            this.isSuccess = isSuccess;
            this.description = description;
        }

        /**
         * Determines whether this {@code Result} is equal to another object.
         *
         * @param other the object to compare with
         * @return {@code true} if the objects are equal, {@code false} otherwise
         */
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return this.isSuccess == result.isSuccess && Intrinsics.areEqual(this.description, result.description);
        }

        /**
         * Computes the hash code for this {@code Result}.
         *
         * @return the hash code
         */
        public int hashCode() {
            int successBit = this.isSuccess ? 1 : 0;
            int i = successBit * 31;
            String str = this.description;
            return i + (str != null ? str.hashCode() : 0);
        }

        /**
         * Returns a string representation of this {@code Result}.
         *
         * @return a string describing this {@code Result}
         */
        public String toString() {
            return "Result(isSuccess=" + this.isSuccess + ", description=" + this.description + ")";
        }

        /**
         * Returns whether the operation was successful.
         *
         * @return {@code true} if the operation was successful, {@code false} otherwise
         */
        public boolean isSuccess() {
            return this.isSuccess;
        }
    }

    /**
     * Returns the last error message encountered during a camera operation.
     *
     * @return the last error message, or {@code null} if no error occurred
     */
    public final String getLastError() {
        return lastError;
    }

    /**
     * Sets the last error message encountered during a camera operation.
     *
     * @param str the error message to set
     */
    public final void setLastError(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        lastError = str;
    }
}