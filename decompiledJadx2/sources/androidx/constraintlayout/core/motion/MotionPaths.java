package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.utils.Easing;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class MotionPaths implements Comparable<MotionPaths> {
    public static final int CARTESIAN = 0;
    public static final boolean DEBUG = false;
    static final int OFF_HEIGHT = 4;
    static final int OFF_PATH_ROTATE = 5;
    static final int OFF_POSITION = 0;
    static final int OFF_WIDTH = 3;
    static final int OFF_X = 1;
    static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    public static final int PERPENDICULAR = 1;
    public static final int SCREEN = 2;
    public static final String TAG = "MotionPaths";
    static String[] names = {RequestParameters.POSITION, "x", "y", "width", "height", "pathRotate"};
    HashMap<String, CustomVariable> customAttributes;
    float height;
    int mAnimateCircleAngleTo;
    int mAnimateRelativeTo;
    int mDrawPath;
    Easing mKeyFrameEasing;
    int mMode;
    int mPathMotionArc;
    float mPathRotate;
    float mProgress;
    float mRelativeAngle;
    Motion mRelativeToController;
    double[] mTempDelta;
    double[] mTempValue;
    float position;
    float time;
    float width;

    /* renamed from: x */
    float f18x;

    /* renamed from: y */
    float f19y;

    private static final float xRotate(float f, float f2, float f3, float f4, float f5, float f6) {
        return (((f5 - f3) * f2) - ((f6 - f4) * f)) + f3;
    }

    private static final float yRotate(float f, float f2, float f3, float f4, float f5, float f6) {
        return ((f5 - f3) * f) + ((f6 - f4) * f2) + f4;
    }

    public MotionPaths() {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = -1;
        this.mAnimateRelativeTo = -1;
        this.mRelativeAngle = Float.NaN;
        this.mRelativeToController = null;
        this.customAttributes = new HashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
    }

    void initCartesian(MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f = motionKeyPosition.mFramePosition / 100.0f;
        this.time = f;
        this.mDrawPath = motionKeyPosition.mDrawPath;
        float f2 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f : motionKeyPosition.mPercentWidth;
        float f3 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f : motionKeyPosition.mPercentHeight;
        float f4 = motionPaths2.width;
        float f5 = motionPaths.width;
        float f6 = motionPaths2.height;
        float f7 = motionPaths.height;
        this.position = this.time;
        float f8 = motionPaths.f18x;
        float f9 = motionPaths.f19y;
        float f10 = (motionPaths2.f18x + (f4 / 2.0f)) - ((f5 / 2.0f) + f8);
        float f11 = (motionPaths2.f19y + (f6 / 2.0f)) - (f9 + (f7 / 2.0f));
        float f12 = ((f4 - f5) * f2) / 2.0f;
        this.f18x = (int) ((f8 + (f10 * f)) - f12);
        float f13 = ((f6 - f7) * f3) / 2.0f;
        this.f19y = (int) ((f9 + (f11 * f)) - f13);
        this.width = (int) (f5 + r9);
        this.height = (int) (f7 + r12);
        float f14 = Float.isNaN(motionKeyPosition.mPercentX) ? f : motionKeyPosition.mPercentX;
        float f15 = Float.isNaN(motionKeyPosition.mAltPercentY) ? 0.0f : motionKeyPosition.mAltPercentY;
        if (!Float.isNaN(motionKeyPosition.mPercentY)) {
            f = motionKeyPosition.mPercentY;
        }
        float f16 = Float.isNaN(motionKeyPosition.mAltPercentX) ? 0.0f : motionKeyPosition.mAltPercentX;
        this.mMode = 0;
        this.f18x = (int) (((motionPaths.f18x + (f14 * f10)) + (f16 * f11)) - f12);
        this.f19y = (int) (((motionPaths.f19y + (f10 * f15)) + (f11 * f)) - f13);
        this.mKeyFrameEasing = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.mPathMotionArc = motionKeyPosition.mPathMotionArc;
    }

    public MotionPaths(int i, int i2, MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = -1;
        this.mAnimateRelativeTo = -1;
        this.mRelativeAngle = Float.NaN;
        this.mRelativeToController = null;
        this.customAttributes = new HashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
        if (motionPaths.mAnimateRelativeTo != -1) {
            initPolar(i, i2, motionKeyPosition, motionPaths, motionPaths2);
            return;
        }
        int i3 = motionKeyPosition.mPositionType;
        if (i3 == 1) {
            initPath(motionKeyPosition, motionPaths, motionPaths2);
        } else if (i3 == 2) {
            initScreen(i, i2, motionKeyPosition, motionPaths, motionPaths2);
        } else {
            initCartesian(motionKeyPosition, motionPaths, motionPaths2);
        }
    }

    void initPolar(int i, int i2, MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float min;
        float f;
        float f2 = motionKeyPosition.mFramePosition / 100.0f;
        this.time = f2;
        this.mDrawPath = motionKeyPosition.mDrawPath;
        this.mMode = motionKeyPosition.mPositionType;
        float f3 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f2 : motionKeyPosition.mPercentWidth;
        float f4 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f2 : motionKeyPosition.mPercentHeight;
        float f5 = motionPaths2.width;
        float f6 = motionPaths.width;
        float f7 = motionPaths2.height;
        float f8 = motionPaths.height;
        this.position = this.time;
        this.width = (int) (f6 + ((f5 - f6) * f3));
        this.height = (int) (f8 + ((f7 - f8) * f4));
        int i3 = motionKeyPosition.mPositionType;
        if (i3 == 1) {
            float f9 = Float.isNaN(motionKeyPosition.mPercentX) ? f2 : motionKeyPosition.mPercentX;
            float f10 = motionPaths2.f18x;
            float f11 = motionPaths.f18x;
            this.f18x = (f9 * (f10 - f11)) + f11;
            if (!Float.isNaN(motionKeyPosition.mPercentY)) {
                f2 = motionKeyPosition.mPercentY;
            }
            float f12 = motionPaths2.f19y;
            float f13 = motionPaths.f19y;
            this.f19y = (f2 * (f12 - f13)) + f13;
        } else if (i3 == 2) {
            if (Float.isNaN(motionKeyPosition.mPercentX)) {
                float f14 = motionPaths2.f18x;
                float f15 = motionPaths.f18x;
                min = ((f14 - f15) * f2) + f15;
            } else {
                min = Math.min(f4, f3) * motionKeyPosition.mPercentX;
            }
            this.f18x = min;
            if (Float.isNaN(motionKeyPosition.mPercentY)) {
                float f16 = motionPaths2.f19y;
                float f17 = motionPaths.f19y;
                f = (f2 * (f16 - f17)) + f17;
            } else {
                f = motionKeyPosition.mPercentY;
            }
            this.f19y = f;
        } else {
            float f18 = Float.isNaN(motionKeyPosition.mPercentX) ? f2 : motionKeyPosition.mPercentX;
            float f19 = motionPaths2.f18x;
            float f20 = motionPaths.f18x;
            this.f18x = (f18 * (f19 - f20)) + f20;
            if (!Float.isNaN(motionKeyPosition.mPercentY)) {
                f2 = motionKeyPosition.mPercentY;
            }
            float f21 = motionPaths2.f19y;
            float f22 = motionPaths.f19y;
            this.f19y = (f2 * (f21 - f22)) + f22;
        }
        this.mAnimateRelativeTo = motionPaths.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.mPathMotionArc = motionKeyPosition.mPathMotionArc;
    }

    public void setupRelative(Motion motion, MotionPaths motionPaths) {
        double d = ((this.f18x + (this.width / 2.0f)) - motionPaths.f18x) - (motionPaths.width / 2.0f);
        double d2 = ((this.f19y + (this.height / 2.0f)) - motionPaths.f19y) - (motionPaths.height / 2.0f);
        this.mRelativeToController = motion;
        this.f18x = (float) Math.hypot(d2, d);
        if (Float.isNaN(this.mRelativeAngle)) {
            this.f19y = (float) (Math.atan2(d2, d) + 1.5707963267948966d);
        } else {
            this.f19y = (float) Math.toRadians(this.mRelativeAngle);
        }
    }

    void initScreen(int i, int i2, MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f = motionKeyPosition.mFramePosition / 100.0f;
        this.time = f;
        this.mDrawPath = motionKeyPosition.mDrawPath;
        float f2 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f : motionKeyPosition.mPercentWidth;
        float f3 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f : motionKeyPosition.mPercentHeight;
        float f4 = motionPaths2.width;
        float f5 = motionPaths.width;
        float f6 = motionPaths2.height;
        float f7 = motionPaths.height;
        this.position = this.time;
        float f8 = motionPaths.f18x;
        float f9 = motionPaths.f19y;
        float f10 = motionPaths2.f18x + (f4 / 2.0f);
        float f11 = motionPaths2.f19y + (f6 / 2.0f);
        float f12 = (f4 - f5) * f2;
        this.f18x = (int) ((f8 + ((f10 - ((f5 / 2.0f) + f8)) * f)) - (f12 / 2.0f));
        float f13 = (f6 - f7) * f3;
        this.f19y = (int) ((f9 + ((f11 - (f9 + (f7 / 2.0f))) * f)) - (f13 / 2.0f));
        this.width = (int) (f5 + f12);
        this.height = (int) (f7 + f13);
        this.mMode = 2;
        if (!Float.isNaN(motionKeyPosition.mPercentX)) {
            this.f18x = (int) (motionKeyPosition.mPercentX * ((int) (i - this.width)));
        }
        if (!Float.isNaN(motionKeyPosition.mPercentY)) {
            this.f19y = (int) (motionKeyPosition.mPercentY * ((int) (i2 - this.height)));
        }
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.mPathMotionArc = motionKeyPosition.mPathMotionArc;
    }

    void initPath(MotionKeyPosition motionKeyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f = motionKeyPosition.mFramePosition / 100.0f;
        this.time = f;
        this.mDrawPath = motionKeyPosition.mDrawPath;
        float f2 = Float.isNaN(motionKeyPosition.mPercentWidth) ? f : motionKeyPosition.mPercentWidth;
        float f3 = Float.isNaN(motionKeyPosition.mPercentHeight) ? f : motionKeyPosition.mPercentHeight;
        float f4 = motionPaths2.width - motionPaths.width;
        float f5 = motionPaths2.height - motionPaths.height;
        this.position = this.time;
        if (!Float.isNaN(motionKeyPosition.mPercentX)) {
            f = motionKeyPosition.mPercentX;
        }
        float f6 = motionPaths.f18x;
        float f7 = motionPaths.width;
        float f8 = motionPaths.f19y;
        float f9 = motionPaths.height;
        float f10 = (motionPaths2.f18x + (motionPaths2.width / 2.0f)) - ((f7 / 2.0f) + f6);
        float f11 = (motionPaths2.f19y + (motionPaths2.height / 2.0f)) - ((f9 / 2.0f) + f8);
        float f12 = f10 * f;
        float f13 = (f4 * f2) / 2.0f;
        this.f18x = (int) ((f6 + f12) - f13);
        float f14 = f * f11;
        float f15 = (f5 * f3) / 2.0f;
        this.f19y = (int) ((f8 + f14) - f15);
        this.width = (int) (f7 + r7);
        this.height = (int) (f9 + r8);
        float f16 = Float.isNaN(motionKeyPosition.mPercentY) ? 0.0f : motionKeyPosition.mPercentY;
        this.mMode = 1;
        this.f18x = (int) ((motionPaths.f18x + f12) - f13);
        this.f19y = (int) ((motionPaths.f19y + f14) - f15);
        this.f18x += (-f11) * f16;
        this.f19y += f10 * f16;
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(motionKeyPosition.mTransitionEasing);
        this.mPathMotionArc = motionKeyPosition.mPathMotionArc;
    }

    private boolean diff(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void different(MotionPaths motionPaths, boolean[] zArr, String[] strArr, boolean z) {
        boolean diff = diff(this.f18x, motionPaths.f18x);
        boolean diff2 = diff(this.f19y, motionPaths.f19y);
        zArr[0] = zArr[0] | diff(this.position, motionPaths.position);
        boolean z2 = diff | diff2 | z;
        zArr[1] = zArr[1] | z2;
        zArr[2] = z2 | zArr[2];
        zArr[3] = zArr[3] | diff(this.width, motionPaths.width);
        zArr[4] = diff(this.height, motionPaths.height) | zArr[4];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getCenter(double d, int[] iArr, double[] dArr, float[] fArr, int i) {
        float f;
        float f2 = this.f18x;
        float f3 = this.f19y;
        float f4 = this.width;
        float f5 = this.height;
        float f6 = f4;
        float f7 = f3;
        float f8 = f2;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f9 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f8 = f9;
            } else if (i3 == 2) {
                f7 = f9;
            } else if (i3 == 3) {
                f6 = f9;
            } else if (i3 == 4) {
                f5 = f9;
            }
        }
        Motion motion = this.mRelativeToController;
        if (motion != null) {
            float[] fArr2 = new float[2];
            motion.getCenter(d, fArr2, new float[2]);
            float f10 = fArr2[0];
            float f11 = fArr2[1];
            double d2 = f8;
            double d3 = f7;
            f = (float) ((f10 + (Math.sin(d3) * d2)) - (f6 / 2.0f));
            f7 = (float) ((f11 - (d2 * Math.cos(d3))) - (f5 / 2.0f));
        } else {
            f = f8;
        }
        fArr[i] = f + (f6 / 2.0f) + 0.0f;
        fArr[i + 1] = f7 + (f5 / 2.0f) + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getCenter(double d, int[] iArr, double[] dArr, float[] fArr, double[] dArr2, float[] fArr2) {
        float f;
        float f2;
        float f3 = this.f18x;
        float f4 = f3;
        float f5 = this.f19y;
        float f6 = this.width;
        float f7 = this.height;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f12 = (float) dArr[i];
            float f13 = (float) dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f4 = f12;
                f8 = f13;
            } else if (i2 == 2) {
                f5 = f12;
                f10 = f13;
            } else if (i2 == 3) {
                f6 = f12;
                f9 = f13;
            } else if (i2 == 4) {
                f7 = f12;
                f11 = f13;
            }
        }
        float f14 = 2.0f;
        float f15 = (f9 / 2.0f) + f8;
        float f16 = (f11 / 2.0f) + f10;
        Motion motion = this.mRelativeToController;
        if (motion != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            motion.getCenter(d, fArr3, fArr4);
            float f17 = fArr3[0];
            float f18 = fArr3[1];
            float f19 = fArr4[0];
            float f20 = fArr4[1];
            double d2 = f4;
            double d3 = f5;
            float sin = (float) ((f17 + (Math.sin(d3) * d2)) - (f6 / 2.0f));
            float cos = (float) ((f18 - (d2 * Math.cos(d3))) - (f7 / 2.0f));
            double d4 = f8;
            double sin2 = f19 + (Math.sin(d3) * d4);
            f2 = sin;
            double d5 = f10;
            float cos2 = (float) (sin2 + (Math.cos(d3) * d5));
            f = (float) ((f20 - (d4 * Math.cos(d3))) + (Math.sin(d3) * d5));
            f5 = cos;
            f15 = cos2;
            f14 = 2.0f;
        } else {
            f = f16;
            f2 = f4;
        }
        fArr[0] = f2 + (f6 / f14) + 0.0f;
        fArr[1] = f5 + (f7 / f14) + 0.0f;
        fArr2[0] = f15;
        fArr2[1] = f;
    }

    void getCenterVelocity(double d, int[] iArr, double[] dArr, float[] fArr, int i) {
        float f;
        float f2 = this.f18x;
        float f3 = this.f19y;
        float f4 = this.width;
        float f5 = this.height;
        float f6 = f4;
        float f7 = f3;
        float f8 = f2;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f9 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f8 = f9;
            } else if (i3 == 2) {
                f7 = f9;
            } else if (i3 == 3) {
                f6 = f9;
            } else if (i3 == 4) {
                f5 = f9;
            }
        }
        Motion motion = this.mRelativeToController;
        if (motion != null) {
            float[] fArr2 = new float[2];
            motion.getCenter(d, fArr2, new float[2]);
            float f10 = fArr2[0];
            float f11 = fArr2[1];
            double d2 = f8;
            double d3 = f7;
            f = (float) ((f10 + (Math.sin(d3) * d2)) - (f6 / 2.0f));
            f7 = (float) ((f11 - (d2 * Math.cos(d3))) - (f5 / 2.0f));
        } else {
            f = f8;
        }
        fArr[i] = f + (f6 / 2.0f) + 0.0f;
        fArr[i + 1] = f7 + (f5 / 2.0f) + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getBounds(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.f18x;
        float f2 = this.f19y;
        float f3 = this.width;
        float f4 = this.height;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 != 1 && i3 != 2) {
                if (i3 == 3) {
                    f3 = f5;
                } else if (i3 == 4) {
                    f4 = f5;
                }
            }
        }
        fArr[i] = f3;
        fArr[i + 1] = f4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setView(float f, MotionWidget motionWidget, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3) {
        float f2;
        float f3;
        float f4 = this.f18x;
        float f5 = this.f19y;
        float f6 = this.width;
        float f7 = this.height;
        if (iArr.length != 0 && this.mTempValue.length <= iArr[iArr.length - 1]) {
            int i = iArr[iArr.length - 1] + 1;
            this.mTempValue = new double[i];
            this.mTempDelta = new double[i];
        }
        Arrays.fill(this.mTempValue, Double.NaN);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.mTempValue[iArr[i2]] = dArr[i2];
            this.mTempDelta[iArr[i2]] = dArr2[i2];
        }
        float f8 = Float.NaN;
        float f9 = f6;
        float f10 = f7;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = f5;
        float f16 = f4;
        int i3 = 0;
        while (true) {
            double[] dArr4 = this.mTempValue;
            if (i3 >= dArr4.length) {
                break;
            }
            if (Double.isNaN(dArr4[i3]) && (dArr3 == null || dArr3[i3] == 0.0d)) {
                f3 = f8;
            } else {
                double d = dArr3 != null ? dArr3[i3] : 0.0d;
                if (!Double.isNaN(this.mTempValue[i3])) {
                    d = this.mTempValue[i3] + d;
                }
                f3 = f8;
                float f17 = (float) d;
                float f18 = (float) this.mTempDelta[i3];
                if (i3 != 0) {
                    if (i3 == 1) {
                        f8 = f3;
                        f11 = f18;
                        f16 = f17;
                    } else if (i3 == 2) {
                        f8 = f3;
                        f12 = f18;
                        f15 = f17;
                    } else if (i3 == 3) {
                        f8 = f3;
                        f13 = f18;
                        f9 = f17;
                    } else if (i3 == 4) {
                        f8 = f3;
                        f14 = f18;
                        f10 = f17;
                    } else if (i3 == 5) {
                        f8 = f17;
                    }
                    i3++;
                }
            }
            f8 = f3;
            i3++;
        }
        float f19 = f8;
        Motion motion = this.mRelativeToController;
        if (motion != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            motion.getCenter(f, fArr, fArr2);
            float f20 = fArr[0];
            float f21 = fArr[1];
            float f22 = fArr2[0];
            float f23 = fArr2[1];
            double d2 = f16;
            double d3 = f15;
            float sin = (float) ((f20 + (Math.sin(d3) * d2)) - (f9 / 2.0f));
            f2 = (float) ((f21 - (Math.cos(d3) * d2)) - (f10 / 2.0f));
            double d4 = f11;
            double d5 = f12;
            float sin2 = (float) (f22 + (Math.sin(d3) * d4) + (Math.cos(d3) * d2 * d5));
            float cos = (float) ((f23 - (d4 * Math.cos(d3))) + (d2 * Math.sin(d3) * d5));
            if (dArr2.length >= 2) {
                dArr2[0] = sin2;
                dArr2[1] = cos;
            }
            if (!Float.isNaN(f19)) {
                motionWidget.setRotationZ((float) (f19 + Math.toDegrees(Math.atan2(cos, sin2))));
            }
            f16 = sin;
        } else {
            if (!Float.isNaN(f19)) {
                motionWidget.setRotationZ((float) (0.0f + f19 + Math.toDegrees(Math.atan2(f12 + (f14 / 2.0f), f11 + (f13 / 2.0f)))));
            }
            f2 = f15;
        }
        float f24 = f16 + 0.5f;
        float f25 = f2 + 0.5f;
        motionWidget.layout((int) f24, (int) f25, (int) (f24 + f9), (int) (f25 + f10));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getRect(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.f18x;
        float f2 = this.f19y;
        float f3 = this.width;
        float f4 = this.height;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 != 0) {
                if (i3 == 1) {
                    f = f5;
                } else if (i3 == 2) {
                    f2 = f5;
                } else if (i3 == 3) {
                    f3 = f5;
                } else if (i3 == 4) {
                    f4 = f5;
                }
            }
        }
        Motion motion = this.mRelativeToController;
        if (motion != null) {
            float centerX = motion.getCenterX();
            float centerY = this.mRelativeToController.getCenterY();
            double d = f;
            double d2 = f2;
            float sin = (float) ((centerX + (Math.sin(d2) * d)) - (f3 / 2.0f));
            f2 = (float) ((centerY - (d * Math.cos(d2))) - (f4 / 2.0f));
            f = sin;
        }
        float f6 = f3 + f;
        float f7 = f4 + f2;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i4 = i + 1;
        fArr[i] = f + 0.0f;
        int i5 = i4 + 1;
        fArr[i4] = f2 + 0.0f;
        int i6 = i5 + 1;
        fArr[i5] = f6 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = f2 + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f6 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f7 + 0.0f;
        fArr[i9] = f + 0.0f;
        fArr[i9 + 1] = f7 + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDpDt(float f, float f2, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f7 = (float) dArr[i];
            double d = dArr2[i];
            int i2 = iArr[i];
            if (i2 != 0) {
                if (i2 == 1) {
                    f3 = f7;
                } else if (i2 == 2) {
                    f5 = f7;
                } else if (i2 == 3) {
                    f4 = f7;
                } else if (i2 == 4) {
                    f6 = f7;
                }
            }
        }
        float f8 = f3 - ((0.0f * f4) / 2.0f);
        float f9 = f5 - ((0.0f * f6) / 2.0f);
        fArr[0] = (f8 * (1.0f - f)) + (((f4 * 1.0f) + f8) * f) + 0.0f;
        fArr[1] = (f9 * (1.0f - f2)) + (((f6 * 1.0f) + f9) * f2) + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fillStandard(double[] dArr, int[] iArr) {
        float[] fArr = {this.position, this.f18x, this.f19y, this.width, this.height, this.mPathRotate};
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < fArr.length) {
                dArr[i] = fArr[iArr[i2]];
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasCustomData(String str) {
        return this.customAttributes.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCustomDataCount(String str) {
        CustomVariable customVariable = this.customAttributes.get(str);
        if (customVariable == null) {
            return 0;
        }
        return customVariable.numberOfInterpolatedValues();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCustomData(String str, double[] dArr, int i) {
        CustomVariable customVariable = this.customAttributes.get(str);
        int i2 = 0;
        if (customVariable == null) {
            return 0;
        }
        if (customVariable.numberOfInterpolatedValues() == 1) {
            dArr[i] = customVariable.getValueToInterpolate();
            return 1;
        }
        int numberOfInterpolatedValues = customVariable.numberOfInterpolatedValues();
        customVariable.getValuesToInterpolate(new float[numberOfInterpolatedValues]);
        while (i2 < numberOfInterpolatedValues) {
            dArr[i] = r2[i2];
            i2++;
            i++;
        }
        return numberOfInterpolatedValues;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBounds(float f, float f2, float f3, float f4) {
        this.f18x = f;
        this.f19y = f2;
        this.width = f3;
        this.height = f4;
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionPaths motionPaths) {
        return Float.compare(this.position, motionPaths.position);
    }

    public void applyParameters(MotionWidget motionWidget) {
        this.mKeyFrameEasing = Easing.getInterpolator(motionWidget.motion.mTransitionEasing);
        this.mPathMotionArc = motionWidget.motion.mPathMotionArc;
        this.mAnimateRelativeTo = motionWidget.motion.mAnimateRelativeTo;
        this.mPathRotate = motionWidget.motion.mPathRotate;
        this.mDrawPath = motionWidget.motion.mDrawPath;
        this.mAnimateCircleAngleTo = motionWidget.motion.mAnimateCircleAngleTo;
        this.mProgress = motionWidget.propertySet.mProgress;
        this.mRelativeAngle = 0.0f;
        for (String str : motionWidget.getCustomAttributeNames()) {
            CustomVariable customAttribute = motionWidget.getCustomAttribute(str);
            if (customAttribute != null && customAttribute.isContinuous()) {
                this.customAttributes.put(str, customAttribute);
            }
        }
    }

    public void configureRelativeTo(Motion motion) {
        motion.getPos(this.mProgress);
    }
}
