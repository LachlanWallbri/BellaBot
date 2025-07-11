package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes.dex */
public class SpringStopEngine implements StopEngine {
    private static final double UNSET = Double.MAX_VALUE;
    private float mLastTime;
    private double mLastVelocity;
    private float mMass;
    private float mPos;
    private double mStiffness;
    private float mStopThreshold;
    private double mTargetPos;

    /* renamed from: mV */
    private float f34mV;
    double mDamping = 0.5d;
    private boolean mInitialized = false;
    private int mBoundaryMode = 0;

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public String debug(String str, float f) {
        return null;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity() {
        return 0.0f;
    }

    void log(String str) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String str2 = ".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName() + "() ";
        System.out.println(str2 + str);
    }

    public void springConfig(float f, float f2, float f3, float f4, float f5, float f6, float f7, int i) {
        this.mTargetPos = f2;
        this.mDamping = f6;
        this.mInitialized = false;
        this.mPos = f;
        this.mLastVelocity = f3;
        this.mStiffness = f5;
        this.mMass = f4;
        this.mStopThreshold = f7;
        this.mBoundaryMode = i;
        this.mLastTime = 0.0f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity(float f) {
        return this.f34mV;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getInterpolation(float f) {
        compute(f - this.mLastTime);
        this.mLastTime = f;
        return this.mPos;
    }

    public float getAcceleration() {
        return ((float) (((-this.mStiffness) * (this.mPos - this.mTargetPos)) - (this.mDamping * this.f34mV))) / this.mMass;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public boolean isStopped() {
        double d = this.mPos - this.mTargetPos;
        double d2 = this.mStiffness;
        double d3 = this.f34mV;
        return Math.sqrt((((d3 * d3) * ((double) this.mMass)) + ((d2 * d) * d)) / d2) <= ((double) this.mStopThreshold);
    }

    private void compute(double d) {
        double d2 = this.mStiffness;
        double d3 = this.mDamping;
        int sqrt = (int) ((9.0d / ((Math.sqrt(d2 / this.mMass) * d) * 4.0d)) + 1.0d);
        double d4 = d / sqrt;
        int i = 0;
        while (i < sqrt) {
            float f = this.mPos;
            double d5 = this.mTargetPos;
            float f2 = this.f34mV;
            double d6 = d2;
            double d7 = ((-d2) * (f - d5)) - (f2 * d3);
            float f3 = this.mMass;
            double d8 = d3;
            double d9 = f2 + (((d7 / f3) * d4) / 2.0d);
            double d10 = ((((-((f + ((d4 * d9) / 2.0d)) - d5)) * d6) - (d9 * d8)) / f3) * d4;
            this.f34mV = (float) (f2 + d10);
            this.mPos = (float) (f + ((f2 + (d10 / 2.0d)) * d4));
            int i2 = this.mBoundaryMode;
            if (i2 > 0) {
                float f4 = this.mPos;
                if (f4 < 0.0f && (i2 & 1) == 1) {
                    this.mPos = -f4;
                    this.f34mV = -this.f34mV;
                }
                float f5 = this.mPos;
                if (f5 > 1.0f && (this.mBoundaryMode & 2) == 2) {
                    this.mPos = 2.0f - f5;
                    this.f34mV = -this.f34mV;
                }
            }
            i++;
            d2 = d6;
            d3 = d8;
        }
    }
}
