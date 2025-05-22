package com.pudutech.bumblebee.robot_ui.widget.random_layout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;

/* loaded from: classes4.dex */
public class WaveLoadingView extends View {
    private static final float DEFAULT_AMPLITUDE_RATIO = 0.1f;
    private static final float DEFAULT_AMPLITUDE_VALUE = 50.0f;
    private static final float DEFAULT_BORDER_WIDTH = 0.0f;
    private static final int DEFAULT_ROUND_RECTANGLE_X_AND_Y = 30;
    private static final int DEFAULT_STROKE_COLOR = 0;
    private static final float DEFAULT_TITLE_BOTTOM_SIZE = 18.0f;
    private static final float DEFAULT_TITLE_CENTER_SIZE = 22.0f;
    private static final float DEFAULT_TITLE_STROKE_WIDTH = 0.0f;
    private static final float DEFAULT_TITLE_TOP_SIZE = 18.0f;
    private static final float DEFAULT_WATER_LEVEL_RATIO = 0.5f;
    private static final float DEFAULT_WAVE_LENGTH_RATIO = 1.0f;
    private static final int DEFAULT_WAVE_PROGRESS_VALUE = 50;
    private static final float DEFAULT_WAVE_SHIFT_RATIO = 0.0f;
    private Bitmap bitmapBuffer;
    private float mAmplitudeRatio;
    private AnimatorSet mAnimatorSet;
    private Paint mBorderPaint;
    private String mBottomTitle;
    private Paint mBottomTitlePaint;
    private Paint mBottomTitleStrokePaint;
    private int mCanvasHeight;
    private int mCanvasSize;
    private int mCanvasWidth;
    private String mCenterTitle;
    private Paint mCenterTitlePaint;
    private Paint mCenterTitleStrokePaint;
    private Context mContext;
    private float mDefaultWaterLevel;
    private boolean mIsRoundRectangle;
    private Paint mPercebtPaint;
    private int mProgressValue;
    private int mRoundRectangleXY;
    private Matrix mShaderMatrix;
    private int mShapeType;
    private String mTopTitle;
    private Paint mTopTitlePaint;
    private Paint mTopTitleStrokePaint;
    private int mTriangleDirection;
    private float mWaterLevelRatio;
    private int mWaveBgColor;
    private Paint mWaveBgPaint;
    private int mWaveColor;
    private Paint mWavePaint;
    private BitmapShader mWaveShader;
    private float mWaveShiftRatio;
    private static final int DEFAULT_WAVE_COLOR = Color.parseColor("#212121");
    private static final int DEFAULT_WAVE_BACKGROUND_COLOR = Color.parseColor("#00000000");
    private static final int DEFAULT_TITLE_COLOR = Color.parseColor("#212121");
    private static final int DEFAULT_WAVE_SHAPE = ShapeType.CIRCLE.ordinal();
    private static final int DEFAULT_TRIANGLE_DIRECTION = TriangleDirection.NORTH.ordinal();

    /* loaded from: classes4.dex */
    public enum ShapeType {
        TRIANGLE,
        CIRCLE,
        SQUARE,
        RECTANGLE
    }

    /* loaded from: classes4.dex */
    public enum TriangleDirection {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public WaveLoadingView(Context context) {
        this(context, null);
    }

    public WaveLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WaveLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mWaterLevelRatio = 1.0f;
        this.mWaveShiftRatio = 0.0f;
        this.mProgressValue = 50;
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        this.mContext = context;
        this.mShaderMatrix = new Matrix();
        this.mWavePaint = new Paint();
        this.mWavePaint.setAntiAlias(true);
        this.mWaveBgPaint = new Paint();
        this.mWaveBgPaint.setAntiAlias(true);
        initAnimation();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4188R.styleable.WaveLoadingView, i, 0);
        this.mShapeType = obtainStyledAttributes.getInteger(C4188R.styleable.WaveLoadingView_wlv_shapeType, DEFAULT_WAVE_SHAPE);
        this.mWaveColor = obtainStyledAttributes.getColor(C4188R.styleable.WaveLoadingView_wlv_waveColor, DEFAULT_WAVE_COLOR);
        this.mWaveBgColor = obtainStyledAttributes.getColor(C4188R.styleable.WaveLoadingView_wlv_wave_background_Color, DEFAULT_WAVE_BACKGROUND_COLOR);
        this.mWaveBgPaint.setColor(this.mWaveBgColor);
        float f = obtainStyledAttributes.getFloat(C4188R.styleable.WaveLoadingView_wlv_waveAmplitude, 50.0f) / 1000.0f;
        if (f > 0.1f) {
            f = 0.1f;
        }
        this.mAmplitudeRatio = f;
        this.mProgressValue = obtainStyledAttributes.getInteger(C4188R.styleable.WaveLoadingView_wlv_progressValue, 50);
        setProgressValue(this.mProgressValue);
        this.mIsRoundRectangle = obtainStyledAttributes.getBoolean(C4188R.styleable.WaveLoadingView_wlv_round_rectangle, false);
        this.mRoundRectangleXY = obtainStyledAttributes.getInteger(C4188R.styleable.WaveLoadingView_wlv_round_rectangle_x_and_y, 30);
        this.mTriangleDirection = obtainStyledAttributes.getInteger(C4188R.styleable.WaveLoadingView_wlv_triangle_direction, DEFAULT_TRIANGLE_DIRECTION);
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setStrokeWidth(obtainStyledAttributes.getDimension(C4188R.styleable.WaveLoadingView_wlv_borderWidth, dp2px(0.0f)));
        this.mBorderPaint.setColor(obtainStyledAttributes.getColor(C4188R.styleable.WaveLoadingView_wlv_borderColor, DEFAULT_WAVE_COLOR));
        this.mTopTitlePaint = new Paint();
        this.mTopTitlePaint.setColor(obtainStyledAttributes.getColor(C4188R.styleable.WaveLoadingView_wlv_titleTopColor, DEFAULT_TITLE_COLOR));
        this.mTopTitlePaint.setStyle(Paint.Style.FILL);
        this.mTopTitlePaint.setAntiAlias(true);
        this.mTopTitlePaint.setTextSize(obtainStyledAttributes.getDimension(C4188R.styleable.WaveLoadingView_wlv_titleTopSize, sp2px(18.0f)));
        this.mTopTitleStrokePaint = new Paint();
        this.mTopTitleStrokePaint.setColor(obtainStyledAttributes.getColor(C4188R.styleable.WaveLoadingView_wlv_titleTopStrokeColor, 0));
        this.mTopTitleStrokePaint.setStrokeWidth(obtainStyledAttributes.getDimension(C4188R.styleable.WaveLoadingView_wlv_titleTopStrokeWidth, dp2px(0.0f)));
        this.mTopTitleStrokePaint.setStyle(Paint.Style.STROKE);
        this.mTopTitleStrokePaint.setAntiAlias(true);
        this.mTopTitleStrokePaint.setTextSize(this.mTopTitlePaint.getTextSize());
        this.mTopTitle = obtainStyledAttributes.getString(C4188R.styleable.WaveLoadingView_wlv_titleTop);
        this.mCenterTitlePaint = new Paint();
        this.mCenterTitlePaint.setColor(obtainStyledAttributes.getColor(C4188R.styleable.WaveLoadingView_wlv_titleCenterColor, DEFAULT_TITLE_COLOR));
        this.mCenterTitlePaint.setStyle(Paint.Style.FILL);
        this.mCenterTitlePaint.setAntiAlias(true);
        this.mCenterTitlePaint.setTextSize(obtainStyledAttributes.getDimension(C4188R.styleable.WaveLoadingView_wlv_titleCenterSize, sp2px(DEFAULT_TITLE_CENTER_SIZE)));
        this.mPercebtPaint = new Paint();
        this.mPercebtPaint.setColor(obtainStyledAttributes.getColor(C4188R.styleable.WaveLoadingView_wlv_titleCenterColor, DEFAULT_TITLE_COLOR));
        this.mPercebtPaint.setStyle(Paint.Style.FILL);
        this.mPercebtPaint.setAntiAlias(true);
        this.mPercebtPaint.setTextSize(28.0f);
        this.mCenterTitleStrokePaint = new Paint();
        this.mCenterTitleStrokePaint.setColor(obtainStyledAttributes.getColor(C4188R.styleable.WaveLoadingView_wlv_titleCenterStrokeColor, 0));
        this.mCenterTitleStrokePaint.setStrokeWidth(obtainStyledAttributes.getDimension(C4188R.styleable.WaveLoadingView_wlv_titleCenterStrokeWidth, dp2px(0.0f)));
        this.mCenterTitleStrokePaint.setStyle(Paint.Style.STROKE);
        this.mCenterTitleStrokePaint.setAntiAlias(true);
        this.mCenterTitleStrokePaint.setTextSize(this.mCenterTitlePaint.getTextSize());
        this.mCenterTitle = obtainStyledAttributes.getString(C4188R.styleable.WaveLoadingView_wlv_titleCenter);
        this.mBottomTitlePaint = new Paint();
        this.mBottomTitlePaint.setColor(obtainStyledAttributes.getColor(C4188R.styleable.WaveLoadingView_wlv_titleBottomColor, DEFAULT_TITLE_COLOR));
        this.mBottomTitlePaint.setStyle(Paint.Style.FILL);
        this.mBottomTitlePaint.setAntiAlias(true);
        this.mBottomTitlePaint.setTextSize(obtainStyledAttributes.getDimension(C4188R.styleable.WaveLoadingView_wlv_titleBottomSize, sp2px(18.0f)));
        this.mBottomTitleStrokePaint = new Paint();
        this.mBottomTitleStrokePaint.setColor(obtainStyledAttributes.getColor(C4188R.styleable.WaveLoadingView_wlv_titleBottomStrokeColor, 0));
        this.mBottomTitleStrokePaint.setStrokeWidth(obtainStyledAttributes.getDimension(C4188R.styleable.WaveLoadingView_wlv_titleBottomStrokeWidth, dp2px(0.0f)));
        this.mBottomTitleStrokePaint.setStyle(Paint.Style.STROKE);
        this.mBottomTitleStrokePaint.setAntiAlias(true);
        this.mBottomTitleStrokePaint.setTextSize(this.mBottomTitlePaint.getTextSize());
        this.mBottomTitle = obtainStyledAttributes.getString(C4188R.styleable.WaveLoadingView_wlv_titleBottom);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        this.mCanvasSize = canvas.getWidth();
        if (canvas.getHeight() < this.mCanvasSize) {
            this.mCanvasSize = canvas.getHeight();
        }
        if (this.mWaveShader != null) {
            if (this.mWavePaint.getShader() == null) {
                this.mWavePaint.setShader(this.mWaveShader);
            }
            this.mShaderMatrix.setScale(1.0f, this.mAmplitudeRatio / 0.1f, 0.0f, this.mDefaultWaterLevel);
            this.mShaderMatrix.postTranslate(this.mWaveShiftRatio * getWidth(), (0.5f - this.mWaterLevelRatio) * getHeight());
            this.mWaveShader.setLocalMatrix(this.mShaderMatrix);
            float strokeWidth = this.mBorderPaint.getStrokeWidth();
            int i = this.mShapeType;
            if (i == 0) {
                Path equilateralTriangle = getEquilateralTriangle(new Point(0, getHeight()), getWidth(), getHeight(), this.mTriangleDirection);
                canvas.drawPath(equilateralTriangle, this.mWaveBgPaint);
                canvas.drawPath(equilateralTriangle, this.mWavePaint);
            } else if (i == 1) {
                if (strokeWidth > 0.0f) {
                    canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, ((getWidth() - strokeWidth) / 2.0f) - 1.0f, this.mBorderPaint);
                }
                float width = (getWidth() / 2.0f) - strokeWidth;
                canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, width, this.mWaveBgPaint);
                canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, width, this.mWavePaint);
            } else if (i == 2) {
                if (strokeWidth > 0.0f) {
                    float f = strokeWidth / 2.0f;
                    canvas.drawRect(f, f, (getWidth() - f) - 0.5f, (getHeight() - f) - 0.5f, this.mBorderPaint);
                }
                canvas.drawRect(strokeWidth, strokeWidth, getWidth() - strokeWidth, getHeight() - strokeWidth, this.mWaveBgPaint);
                canvas.drawRect(strokeWidth, strokeWidth, getWidth() - strokeWidth, getHeight() - strokeWidth, this.mWavePaint);
            } else if (i == 3) {
                if (this.mIsRoundRectangle) {
                    if (strokeWidth > 0.0f) {
                        float f2 = strokeWidth / 2.0f;
                        RectF rectF = new RectF(f2, f2, (getWidth() - f2) - 0.5f, (getHeight() - f2) - 0.5f);
                        int i2 = this.mRoundRectangleXY;
                        canvas.drawRoundRect(rectF, i2, i2, this.mWaveBgPaint);
                        int i3 = this.mRoundRectangleXY;
                        canvas.drawRoundRect(rectF, i3, i3, this.mWavePaint);
                    } else {
                        RectF rectF2 = new RectF(0.0f, 0.0f, getWidth(), getHeight());
                        int i4 = this.mRoundRectangleXY;
                        canvas.drawRoundRect(rectF2, i4, i4, this.mWaveBgPaint);
                        int i5 = this.mRoundRectangleXY;
                        canvas.drawRoundRect(rectF2, i5, i5, this.mWavePaint);
                    }
                } else if (strokeWidth > 0.0f) {
                    float f3 = strokeWidth / 2.0f;
                    canvas.drawRect(f3, f3, (getWidth() - f3) - 0.5f, (getHeight() - f3) - 0.5f, this.mWaveBgPaint);
                    canvas.drawRect(f3, f3, (getWidth() - f3) - 0.5f, (getHeight() - f3) - 0.5f, this.mWavePaint);
                } else {
                    canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.mWaveBgPaint);
                    canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.mWavePaint);
                }
            }
            if (!TextUtils.isEmpty(this.mTopTitle)) {
                float measureText = this.mTopTitlePaint.measureText(this.mTopTitle);
                canvas.drawText(this.mTopTitle, (getWidth() - measureText) / 2.0f, (getHeight() * 2) / 10.0f, this.mTopTitleStrokePaint);
                canvas.drawText(this.mTopTitle, (getWidth() - measureText) / 2.0f, (getHeight() * 2) / 10.0f, this.mTopTitlePaint);
            }
            if (!TextUtils.isEmpty(this.mCenterTitle)) {
                float measureText2 = this.mCenterTitlePaint.measureText(this.mCenterTitle);
                canvas.drawText(this.mCenterTitle, (getWidth() - measureText2) / 2.0f, (getHeight() / 2) - ((this.mCenterTitleStrokePaint.descent() + this.mCenterTitleStrokePaint.ascent()) / 2.0f), this.mCenterTitleStrokePaint);
                if (Integer.parseInt(this.mCenterTitle) == 100) {
                    Paint paint = this.mCenterTitlePaint;
                    paint.setTextSize(103.0f);
                    canvas.drawText(this.mCenterTitle, (getWidth() - measureText2) / 2.0f, (getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2.0f), paint);
                    canvas.drawText("%", ((getWidth() + measureText2) / 2.0f) + 20.0f, (getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2.0f), this.mPercebtPaint);
                } else {
                    canvas.drawText(this.mCenterTitle, (getWidth() - measureText2) / 2.0f, (getHeight() / 2) - ((this.mCenterTitlePaint.descent() + this.mCenterTitlePaint.ascent()) / 2.0f), this.mCenterTitlePaint);
                    canvas.drawText("%", ((getWidth() + measureText2) / 2.0f) + 20.0f, (getHeight() / 2) - ((this.mCenterTitlePaint.descent() + this.mCenterTitlePaint.ascent()) / 2.0f), this.mPercebtPaint);
                }
            }
            if (TextUtils.isEmpty(this.mBottomTitle)) {
                return;
            }
            float measureText3 = this.mBottomTitlePaint.measureText(this.mBottomTitle);
            canvas.drawText(this.mBottomTitle, (getWidth() - measureText3) / 2.0f, ((getHeight() * 8) / 10.0f) - ((this.mBottomTitleStrokePaint.descent() + this.mBottomTitleStrokePaint.ascent()) / 2.0f), this.mBottomTitleStrokePaint);
            canvas.drawText(this.mBottomTitle, (getWidth() - measureText3) / 2.0f, ((getHeight() * 8) / 10.0f) - ((this.mBottomTitlePaint.descent() + this.mBottomTitlePaint.ascent()) / 2.0f), this.mBottomTitlePaint);
            return;
        }
        this.mWavePaint.setShader(null);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (getShapeType() == 3) {
            this.mCanvasWidth = i;
            this.mCanvasHeight = i2;
        } else {
            this.mCanvasSize = i;
            if (i2 < this.mCanvasSize) {
                this.mCanvasSize = i2;
            }
        }
        updateWaveShader();
    }

    private void updateWaveShader() {
        if (this.bitmapBuffer == null || haveBoundsChanged()) {
            Bitmap bitmap = this.bitmapBuffer;
            if (bitmap != null) {
                bitmap.recycle();
            }
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            if (measuredWidth <= 0 || measuredHeight <= 0) {
                return;
            }
            double d = 6.283185307179586d / measuredWidth;
            float f = measuredHeight;
            float f2 = 0.1f * f;
            this.mDefaultWaterLevel = f * 0.5f;
            float f3 = measuredWidth;
            Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            paint.setStrokeWidth(2.0f);
            paint.setAntiAlias(true);
            int i = measuredWidth + 1;
            int i2 = measuredHeight + 1;
            float[] fArr = new float[i];
            paint.setColor(adjustAlpha(this.mWaveColor, 0.3f));
            int i3 = 0;
            while (i3 < i) {
                double d2 = d;
                float sin = (float) (this.mDefaultWaterLevel + (f2 * Math.sin(i3 * d)));
                float f4 = i3;
                int i4 = i3;
                float[] fArr2 = fArr;
                canvas.drawLine(f4, sin, f4, i2, paint);
                fArr2[i4] = sin;
                i3 = i4 + 1;
                fArr = fArr2;
                d = d2;
            }
            float[] fArr3 = fArr;
            paint.setColor(this.mWaveColor);
            int i5 = (int) (f3 / 4.0f);
            for (int i6 = 0; i6 < i; i6++) {
                float f5 = i6;
                canvas.drawLine(f5, fArr3[(i6 + i5) % i], f5, i2, paint);
            }
            this.mWaveShader = new BitmapShader(createBitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
            this.mWavePaint.setShader(this.mWaveShader);
        }
    }

    private boolean haveBoundsChanged() {
        return (getMeasuredWidth() == this.bitmapBuffer.getWidth() && getMeasuredHeight() == this.bitmapBuffer.getHeight()) ? false : true;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measureWidth = measureWidth(i);
        int measureHeight = measureHeight(i2);
        if (getShapeType() == 3) {
            setMeasuredDimension(measureWidth, measureHeight);
            return;
        }
        if (measureWidth >= measureHeight) {
            measureWidth = measureHeight;
        }
        setMeasuredDimension(measureWidth, measureWidth);
    }

    private int measureWidth(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return (mode == 1073741824 || mode == Integer.MIN_VALUE) ? size : this.mCanvasWidth;
    }

    private int measureHeight(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode != 1073741824 && mode != Integer.MIN_VALUE) {
            size = this.mCanvasHeight;
        }
        return size + 2;
    }

    public void setWaveBgColor(int i) {
        this.mWaveBgColor = i;
        this.mWaveBgPaint.setColor(this.mWaveBgColor);
        updateWaveShader();
        invalidate();
    }

    public int getWaveBgColor() {
        return this.mWaveBgColor;
    }

    public void setWaveColor(int i) {
        this.mWaveColor = i;
        updateWaveShader();
        invalidate();
    }

    public int getWaveColor() {
        return this.mWaveColor;
    }

    public void setBorderWidth(float f) {
        this.mBorderPaint.setStrokeWidth(f);
        invalidate();
    }

    public float getBorderWidth() {
        return this.mBorderPaint.getStrokeWidth();
    }

    public void setBorderColor(int i) {
        this.mBorderPaint.setColor(i);
        updateWaveShader();
        invalidate();
    }

    public int getBorderColor() {
        return this.mBorderPaint.getColor();
    }

    public void setShapeType(ShapeType shapeType) {
        this.mShapeType = shapeType.ordinal();
        invalidate();
    }

    public int getShapeType() {
        return this.mShapeType;
    }

    public void setAmplitudeRatio(int i) {
        float f = i / 1000.0f;
        if (this.mAmplitudeRatio != f) {
            this.mAmplitudeRatio = f;
            invalidate();
        }
    }

    public float getAmplitudeRatio() {
        return this.mAmplitudeRatio;
    }

    public void setProgressValue(int i) {
        this.mProgressValue = i;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "waterLevelRatio", this.mWaterLevelRatio, this.mProgressValue / 100.0f);
        ofFloat.setDuration(1000L);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat);
        animatorSet.start();
    }

    public int getProgressValue() {
        return this.mProgressValue;
    }

    public void setWaveShiftRatio(float f) {
        if (this.mWaveShiftRatio != f) {
            this.mWaveShiftRatio = f;
            invalidate();
        }
    }

    public float getWaveShiftRatio() {
        return this.mWaveShiftRatio;
    }

    public void setWaterLevelRatio(float f) {
        if (this.mWaterLevelRatio != f) {
            this.mWaterLevelRatio = f;
            invalidate();
        }
    }

    public float getWaterLevelRatio() {
        return this.mWaterLevelRatio;
    }

    public void setTopTitle(String str) {
        this.mTopTitle = str;
    }

    public String getTopTitle() {
        return this.mTopTitle;
    }

    public void setCenterTitle(String str) {
        this.mCenterTitle = str;
        postInvalidate();
    }

    public String getCenterTitle() {
        return this.mCenterTitle;
    }

    public void setBottomTitle(String str) {
        this.mBottomTitle = str;
    }

    public String getBottomTitle() {
        return this.mBottomTitle;
    }

    public void setTopTitleColor(int i) {
        this.mTopTitlePaint.setColor(i);
    }

    public int getTopTitleColor() {
        return this.mTopTitlePaint.getColor();
    }

    public void setCenterTitleColor(int i) {
        this.mCenterTitlePaint.setColor(i);
    }

    public int getCenterTitleColor() {
        return this.mCenterTitlePaint.getColor();
    }

    public void setBottomTitleColor(int i) {
        this.mBottomTitlePaint.setColor(i);
    }

    public int getBottomTitleColor() {
        return this.mBottomTitlePaint.getColor();
    }

    public void setTopTitleSize(float f) {
        this.mTopTitlePaint.setTextSize(sp2px(f));
    }

    public float getsetTopTitleSize() {
        return this.mTopTitlePaint.getTextSize();
    }

    public void setCenterTitleSize(float f) {
        this.mCenterTitlePaint.setTextSize(sp2px(f));
    }

    public float getCenterTitleSize() {
        return this.mCenterTitlePaint.getTextSize();
    }

    public void setBottomTitleSize(float f) {
        this.mBottomTitlePaint.setTextSize(sp2px(f));
    }

    public float getBottomTitleSize() {
        return this.mBottomTitlePaint.getTextSize();
    }

    public void setTopTitleStrokeWidth(float f) {
        this.mTopTitleStrokePaint.setStrokeWidth(dp2px(f));
    }

    public void setTopTitleStrokeColor(int i) {
        this.mTopTitleStrokePaint.setColor(i);
    }

    public void setBottomTitleStrokeWidth(float f) {
        this.mBottomTitleStrokePaint.setStrokeWidth(dp2px(f));
    }

    public void setBottomTitleStrokeColor(int i) {
        this.mBottomTitleStrokePaint.setColor(i);
    }

    public void setCenterTitleStrokeWidth(float f) {
        this.mCenterTitleStrokePaint.setStrokeWidth(dp2px(f));
    }

    public void setCenterTitleStrokeColor(int i) {
        this.mCenterTitleStrokePaint.setColor(i);
    }

    private void startAnimation() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.start();
        }
    }

    private void initAnimation() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "waveShiftRatio", 0.0f, 1.0f);
        ofFloat.setRepeatCount(-1);
        ofFloat.setDuration(1000L);
        ofFloat.setInterpolator(new LinearInterpolator());
        this.mAnimatorSet = new AnimatorSet();
        this.mAnimatorSet.play(ofFloat);
    }

    private void cancel() {
        Pdlog.m3275i("wave", "wave is cancel");
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.end();
            Pdlog.m3275i("wave", "wave is cancel");
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        startAnimation();
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        cancel();
        super.onDetachedFromWindow();
    }

    private int adjustAlpha(int i, float f) {
        return Color.argb(Math.round(Color.alpha(i) * f), Color.red(i), Color.green(i), Color.blue(i));
    }

    private int sp2px(float f) {
        return (int) ((f * this.mContext.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    private int dp2px(float f) {
        return (int) ((f * this.mContext.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private Path getEquilateralTriangle(Point point, int i, int i2, int i3) {
        Point point2;
        Point point3;
        Point point4 = null;
        if (i3 == 0) {
            point4 = new Point(point.x + i, point.y);
            int i4 = point.x + (i / 2);
            double d = i2;
            point3 = new Point(i4, (int) (d - ((Math.sqrt(3.0d) / 2.0d) * d)));
        } else if (i3 == 1) {
            point4 = new Point(point.x, point.y - i2);
            point3 = new Point(point.x + i, point.y - i2);
            point.x += i / 2;
            point.y = (int) ((Math.sqrt(3.0d) / 2.0d) * i2);
        } else {
            if (i3 == 2) {
                point4 = new Point(point.x, point.y - i2);
                point2 = new Point((int) ((Math.sqrt(3.0d) / 2.0d) * i), point.y / 2);
            } else if (i3 == 3) {
                point4 = new Point(point.x + i, point.y - i2);
                point2 = new Point(point.x + i, point.y);
                double d2 = i;
                point.x = (int) (d2 - ((Math.sqrt(3.0d) / 2.0d) * d2));
                point.y /= 2;
            } else {
                point2 = null;
            }
            Path path = new Path();
            path.moveTo(point.x, point.y);
            path.lineTo(point4.x, point4.y);
            path.lineTo(point2.x, point2.y);
            return path;
        }
        point2 = point3;
        Path path2 = new Path();
        path2.moveTo(point.x, point.y);
        path2.lineTo(point4.x, point4.y);
        path2.lineTo(point2.x, point2.y);
        return path2;
    }
}
