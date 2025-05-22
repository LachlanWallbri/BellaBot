package com.pudutech.rgbdlib.pointcloud.tangoutils;

import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes4.dex */
public class Renderer {
    protected static final float CAMERA_FAR = 200.0f;
    protected static final float CAMERA_FOV = 37.8f;
    protected static final float CAMERA_NEAR = 0.01f;
    protected static final int FIRST_PERSON = 0;
    protected static final int MATRIX_4X4 = 16;
    protected static final int THIRD_PERSON = 2;
    protected static final int THIRD_PERSON_FOV = 65;
    protected static final int TOPDOWN_FOV = 65;
    protected static final int TOP_DOWN = 1;
    protected float mCameraAspect;
    protected float[] mCameraUpVector;
    private float[] mDevicePosition;
    protected float[] mLookAtPosition;
    private float mPreviousRotationX;
    private float mPreviousRotationY;
    private float mPreviousTouchX;
    private float mPreviousTouchY;
    private float mStartCameraRadius;
    private float mTouch1X;
    private float mTouch1Y;
    private float mTouch2X;
    private float mTouch2Y;
    private float mTouchMoveDistance;
    private float mTouchStartDistance;
    protected float[] mProjectionMatrix = new float[16];
    private int viewId = 2;
    protected float[] mViewMatrix = new float[16];
    private ModelMatCalculator mModelMatCalculator = new ModelMatCalculator();
    private float mRotationX = 3.1415927f;
    private float mRotationY = -3.1415927f;
    private float mCameraOrbitRadius = -5.0f;
    protected float[] mCameraPosition = new float[3];

    public Renderer() {
        float[] fArr = this.mCameraPosition;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        this.mDevicePosition = new float[3];
        float[] fArr2 = this.mDevicePosition;
        fArr2[0] = 0.0f;
        fArr2[1] = 0.0f;
        fArr2[2] = 0.0f;
    }

    public void updateViewMatrix() {
        this.mDevicePosition = this.mModelMatCalculator.getTranslation();
        int i = this.viewId;
        if (i == 0) {
            float[] fArr = new float[16];
            Matrix.setIdentityM(fArr, 0);
            float[] fArr2 = new float[16];
            Matrix.setIdentityM(fArr2, 0);
            Matrix.setIdentityM(this.mViewMatrix, 0);
            Matrix.invertM(fArr, 0, this.mModelMatCalculator.getModelMatrix(), 0);
            Matrix.multiplyMM(fArr2, 0, this.mViewMatrix, 0, fArr, 0);
            System.arraycopy(fArr2, 0, this.mViewMatrix, 0, 16);
            return;
        }
        if (i == 1) {
            float[] fArr3 = this.mViewMatrix;
            float[] fArr4 = this.mDevicePosition;
            float f = fArr4[0];
            float[] fArr5 = this.mCameraPosition;
            Matrix.setLookAtM(fArr3, 0, f + fArr5[0], fArr5[1], fArr5[2] + fArr4[2], fArr4[0] + fArr5[0], fArr5[1] - 5.0f, fArr5[2] + fArr4[2], 0.0f, 0.0f, -1.0f);
            return;
        }
        if (i == 2) {
            float[] fArr6 = this.mViewMatrix;
            float[] fArr7 = this.mDevicePosition;
            float f2 = fArr7[0];
            float[] fArr8 = this.mCameraPosition;
            Matrix.setLookAtM(fArr6, 0, f2 + fArr8[0], fArr8[1] + fArr7[1], fArr7[2] + fArr8[2], fArr7[0], fArr7[1], fArr7[2], 0.0f, 1.0f, 0.0f);
            return;
        }
        this.viewId = 2;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d("PointCloud", "onTouchEvent: " + this.viewId + " end!");
        int i = this.viewId;
        if (i == 2) {
            int pointerCount = motionEvent.getPointerCount();
            Log.d("PointCloud", ": " + pointerCount + " end!");
            if (pointerCount == 1) {
                int action = motionEvent.getAction();
                Log.d("PointCloud", "getAction: " + action + " end!");
                if (action == 0) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    this.mPreviousTouchX = x;
                    this.mPreviousTouchY = y;
                    this.mPreviousRotationX = this.mRotationX;
                    this.mPreviousRotationY = this.mRotationY;
                } else if (action == 2) {
                    float x2 = motionEvent.getX();
                    float y2 = motionEvent.getY();
                    float f = this.mPreviousTouchX - x2;
                    float f2 = this.mPreviousTouchY - y2;
                    this.mRotationX = this.mPreviousRotationX + ((float) ((f * 3.141592653589793d) / 1900.0d));
                    this.mRotationY = this.mPreviousRotationY + ((float) ((f2 * 3.141592653589793d) / 1200.0d));
                    if (this.mRotationY > 3.1415927f) {
                        this.mRotationY = 3.1415927f;
                    }
                    if (this.mRotationY < 0.0f) {
                        this.mRotationY = 0.0f;
                    }
                    this.mCameraPosition[0] = (float) (this.mCameraOrbitRadius * Math.sin(this.mRotationX));
                    this.mCameraPosition[1] = (float) (this.mCameraOrbitRadius * Math.cos(this.mRotationY));
                    this.mCameraPosition[2] = (float) (this.mCameraOrbitRadius * Math.cos(this.mRotationX));
                }
            }
            if (pointerCount == 2) {
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked != 0) {
                    if (actionMasked == 2) {
                        this.mTouch1X = motionEvent.getX(0);
                        this.mTouch1Y = motionEvent.getY(0);
                        this.mTouch2X = motionEvent.getX(1);
                        this.mTouch2Y = motionEvent.getY(1);
                        this.mTouchMoveDistance = (float) Math.sqrt(Math.pow(this.mTouch1X - this.mTouch2X, 2.0d) + Math.pow(this.mTouch1Y - this.mTouch2Y, 2.0d));
                        this.mCameraOrbitRadius = this.mStartCameraRadius - ((this.mTouchMoveDistance - this.mTouchStartDistance) * 0.05f);
                        if (this.mCameraOrbitRadius < 1.0f) {
                            this.mCameraOrbitRadius = 1.0f;
                        }
                        this.mCameraPosition[0] = (float) (this.mCameraOrbitRadius * Math.sin(this.mRotationX));
                        this.mCameraPosition[1] = (float) (this.mCameraOrbitRadius * Math.cos(this.mRotationY));
                        this.mCameraPosition[2] = (float) (this.mCameraOrbitRadius * Math.cos(this.mRotationX));
                    } else if (actionMasked != 5) {
                        if (actionMasked == 6) {
                            int i2 = motionEvent.getActionIndex() == 0 ? 1 : 0;
                            float x3 = motionEvent.getX(i2);
                            float y3 = motionEvent.getY(i2);
                            this.mPreviousTouchX = x3;
                            this.mPreviousTouchY = y3;
                            this.mPreviousRotationX = this.mRotationX;
                            this.mPreviousRotationY = this.mRotationY;
                        }
                    }
                }
                this.mTouch1X = motionEvent.getX(0);
                this.mTouch1Y = motionEvent.getY(0);
                this.mTouch2X = motionEvent.getX(1);
                this.mTouch2Y = motionEvent.getY(1);
                this.mTouchStartDistance = (float) Math.sqrt(Math.pow(this.mTouch1X - this.mTouch2X, 2.0d) + Math.pow(this.mTouch1Y - this.mTouch2Y, 2.0d));
                this.mStartCameraRadius = this.mCameraOrbitRadius;
            }
        } else if (i == 1) {
            int pointerCount2 = motionEvent.getPointerCount();
            if (pointerCount2 == 1) {
                int action2 = motionEvent.getAction();
                if (action2 == 0) {
                    float x4 = motionEvent.getX();
                    float y4 = motionEvent.getY();
                    this.mPreviousTouchX = x4;
                    this.mPreviousTouchY = y4;
                    float[] fArr = this.mCameraPosition;
                    this.mPreviousRotationX = fArr[0];
                    this.mPreviousRotationY = fArr[2];
                } else if (action2 == 2) {
                    float x5 = motionEvent.getX();
                    float y5 = motionEvent.getY();
                    float f3 = this.mPreviousTouchX - x5;
                    float f4 = this.mPreviousTouchY - y5;
                    float[] fArr2 = this.mCameraPosition;
                    fArr2[0] = this.mPreviousRotationX + (f3 / 190.0f);
                    fArr2[2] = this.mPreviousRotationY + (f4 / 120.0f);
                }
            }
            if (pointerCount2 == 2) {
                int actionMasked2 = motionEvent.getActionMasked();
                if (actionMasked2 != 0) {
                    if (actionMasked2 == 2) {
                        this.mTouch1X = motionEvent.getX(0);
                        this.mTouch1Y = motionEvent.getY(0);
                        this.mTouch2X = motionEvent.getX(1);
                        this.mTouch2Y = motionEvent.getY(1);
                        this.mTouchMoveDistance = (float) Math.sqrt(Math.pow(this.mTouch1X - this.mTouch2X, 2.0d) + Math.pow(this.mTouch1Y - this.mTouch2Y, 2.0d));
                        this.mCameraPosition[1] = this.mStartCameraRadius - ((this.mTouchMoveDistance - this.mTouchStartDistance) * 0.05f);
                    } else if (actionMasked2 != 5) {
                        if (actionMasked2 == 6) {
                            int i3 = motionEvent.getActionIndex() == 0 ? 1 : 0;
                            float x6 = motionEvent.getX(i3);
                            float y6 = motionEvent.getY(i3);
                            this.mPreviousTouchX = x6;
                            this.mPreviousTouchY = y6;
                            float[] fArr3 = this.mCameraPosition;
                            this.mPreviousRotationX = fArr3[0];
                            this.mPreviousRotationY = fArr3[2];
                        }
                    }
                }
                this.mTouch1X = motionEvent.getX(0);
                this.mTouch1Y = motionEvent.getY(0);
                this.mTouch2X = motionEvent.getX(1);
                this.mTouch2Y = motionEvent.getY(1);
                this.mTouchStartDistance = (float) Math.sqrt(Math.pow(this.mTouch1X - this.mTouch2X, 2.0d) + Math.pow(this.mTouch1Y - this.mTouch2Y, 2.0d));
                this.mStartCameraRadius = this.mCameraPosition[1];
                Log.i("Start Radius is :", "" + this.mStartCameraRadius);
            }
        }
        updateViewMatrix();
        return true;
    }

    public void setFirstPersonView() {
        this.viewId = 0;
        Matrix.perspectiveM(this.mProjectionMatrix, 0, CAMERA_FOV, this.mCameraAspect, CAMERA_NEAR, 200.0f);
    }

    public void setThirdPersonView() {
        this.viewId = 2;
        float[] fArr = this.mCameraPosition;
        fArr[0] = 5.0f;
        fArr[1] = 5.0f;
        fArr[2] = 5.0f;
        this.mRotationY = 0.7853982f;
        this.mRotationX = 0.7853982f;
        this.mCameraOrbitRadius = 5.0f;
        Matrix.perspectiveM(this.mProjectionMatrix, 0, 65.0f, this.mCameraAspect, CAMERA_NEAR, 200.0f);
    }

    public void setTopDownView() {
        this.viewId = 1;
        float[] fArr = this.mCameraPosition;
        fArr[0] = 0.0f;
        fArr[1] = 5.0f;
        fArr[2] = 0.0f;
        Matrix.perspectiveM(this.mProjectionMatrix, 0, 65.0f, this.mCameraAspect, CAMERA_NEAR, 200.0f);
    }

    public void resetModelMatCalculator() {
        this.mModelMatCalculator = new ModelMatCalculator();
    }

    public ModelMatCalculator getModelMatCalculator() {
        return this.mModelMatCalculator;
    }

    public float[] getViewMatrix() {
        return this.mViewMatrix;
    }

    public float[] getProjectionMatrix() {
        return this.mProjectionMatrix;
    }
}
