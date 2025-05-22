package org.hash.mock.debug.view.wave;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.pudutech.disinfect.baselib.C4429R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class WaterView extends View {
    public static final int TYPE_CIRCLE = 1;
    public static final int TYPE_RECT = 2;
    private List<Bubble> bubbles;
    private Path canvasPath;
    private float centerX;
    private float centerY;
    private int[] color;
    private Paint firstPaint;
    private int firstPaintColor;
    private Path firstPath;
    private int frameColor;
    private Paint framePaint;
    private float frameWidth;

    /* renamed from: h */
    float f10012h;
    private boolean isCustomSpeedY;
    private boolean isKeepHeight;
    private boolean isPause;
    private boolean isShowBubble;
    private int isShowBubbleCount;
    private boolean isShowFrame;
    private boolean isStart;
    private boolean isStop;
    private float keep_percent;
    Listener listener;
    private int mHeight;
    private int mWidth;
    private Random random;
    private RunThread runThread;
    private Paint secondPaint;
    private int secondPaintColor;
    private Path secondPath;
    private int sin_amplitude;
    private float sin_cycle;
    float sin_offset;
    private float sin_offset_increment_value;
    private int sin_up_velocity;
    private int sleep_time;
    private boolean starting;
    private int type;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public interface Listener {
        void finish();
    }

    public WaterView(Context context) {
        this(context, null);
    }

    public WaterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WaterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.canvasPath = new Path();
        this.firstPath = new Path();
        this.secondPath = new Path();
        this.sin_cycle = 0.01f;
        this.sin_offset = 0.0f;
        this.f10012h = 0.0f;
        this.secondPaintColor = Color.parseColor("#9292DA");
        this.firstPaintColor = Color.parseColor("#5353C7");
        this.frameColor = Color.parseColor("#dd0000");
        this.sin_amplitude = 30;
        this.sin_offset_increment_value = 0.4f;
        this.sin_up_velocity = 5;
        this.sleep_time = 100;
        this.keep_percent = 0.5f;
        this.isStart = true;
        this.isStop = false;
        this.isKeepHeight = false;
        this.color = new int[2];
        this.bubbles = new ArrayList();
        this.random = new Random();
        this.starting = false;
        this.isPause = false;
        this.isShowBubble = true;
        this.isShowBubbleCount = 0;
        this.isCustomSpeedY = false;
        this.secondPaintColor = Color.parseColor("#9292DA");
        this.firstPaintColor = Color.parseColor("#5353C7");
        this.sin_amplitude = 20;
        this.sin_offset_increment_value = 0.4f;
        this.sin_up_velocity = 5;
        this.sleep_time = 150;
        this.frameColor = Color.parseColor("#DD0000");
        this.color[0] = Color.parseColor("#DD0000");
        this.color[1] = Color.parseColor("#FF8888");
        this.frameWidth = dip2px(context, 2.0f);
        this.type = 1;
        this.isShowFrame = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4429R.styleable.WaterView);
        this.firstPaintColor = obtainStyledAttributes.getColor(C4429R.styleable.WaterView_waterview_paint_color_first, this.firstPaintColor);
        this.secondPaintColor = obtainStyledAttributes.getColor(C4429R.styleable.WaterView_waterview_paint_color_second, this.secondPaintColor);
        this.sin_amplitude = obtainStyledAttributes.getInt(C4429R.styleable.WaterView_waterview_amplitude, this.sin_amplitude);
        this.sin_offset_increment_value = obtainStyledAttributes.getFloat(C4429R.styleable.WaterView_waterview_offset_increment_value, this.sin_offset_increment_value);
        this.sin_up_velocity = obtainStyledAttributes.getInt(C4429R.styleable.WaterView_waterview_up_velocity, this.sin_up_velocity);
        this.sleep_time = obtainStyledAttributes.getInt(C4429R.styleable.WaterView_waterview_sleep_time, this.sleep_time);
        this.frameWidth = obtainStyledAttributes.getDimension(C4429R.styleable.WaterView_waterview_frame_width, this.frameWidth);
        this.frameColor = obtainStyledAttributes.getColor(C4429R.styleable.WaterView_waterview_frame_color, this.frameColor);
        this.isShowFrame = obtainStyledAttributes.getBoolean(C4429R.styleable.WaterView_waterview_frame_color, false);
        obtainStyledAttributes.recycle();
        if (this.frameWidth == 0.0f) {
            this.isShowFrame = false;
        }
        this.firstPaint = new Paint();
        this.firstPaint.setColor(this.firstPaintColor);
        this.firstPaint.setAntiAlias(true);
        this.secondPaint = new Paint();
        this.secondPaint.setColor(this.secondPaintColor);
        this.secondPaint.setAntiAlias(true);
        this.framePaint = new Paint();
        this.framePaint.setStrokeWidth(this.frameWidth);
        this.framePaint.setAntiAlias(true);
        this.framePaint.setStyle(Paint.Style.STROKE);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f10012h = this.mHeight;
        this.canvasPath.addCircle(this.centerX, this.centerY, (r2 + 1) / 2, Path.Direction.CCW);
        reset();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            this.mWidth = size;
        } else {
            this.mWidth = dip2px(getContext(), 208.0f);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode2 == 1073741824) {
            this.mHeight = size2;
        } else {
            this.mHeight = dip2px(getContext(), 208.0f);
        }
        int i3 = this.mWidth;
        this.centerX = i3 / 2;
        int i4 = this.mHeight;
        this.centerY = i4 / 2;
        setMeasuredDimension(i3, i4);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.clipPath(this.canvasPath, Region.Op.INTERSECT);
        if (this.isStart) {
            canvas.drawPath(secondPath(), this.secondPaint);
            canvas.drawPath(firstPath(), this.firstPaint);
        }
        if (this.isShowFrame) {
            Paint paint = this.framePaint;
            int i = this.mWidth;
            float f = i / 2;
            float f2 = this.mHeight;
            float f3 = i / 2;
            int[] iArr = this.color;
            paint.setShader(new LinearGradient(f, f2, f3, 0.0f, iArr[0], iArr[1], Shader.TileMode.CLAMP));
            canvas.drawCircle(this.centerX, this.centerY, (this.mHeight / 2) - (this.frameWidth / 2.0f), this.framePaint);
        }
        if (this.isStart && this.isShowBubble) {
            drawBubble(canvas);
            this.isShowBubbleCount++;
            if (25 == this.isShowBubbleCount) {
                this.isShowBubbleCount = 0;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [org.hash.mock.debug.view.wave.WaterView$1] */
    private void drawBubble(Canvas canvas) {
        this.isPause = false;
        new Thread() { // from class: org.hash.mock.debug.view.wave.WaterView.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                int i;
                float f;
                if (WaterView.this.isPause || WaterView.this.isShowBubbleCount != 1) {
                    return;
                }
                Bubble bubble = new Bubble();
                int nextInt = WaterView.this.random.nextInt(10);
                while (true) {
                    i = nextInt + 10;
                    if (i != 0) {
                        break;
                    } else {
                        nextInt = WaterView.this.random.nextInt(10);
                    }
                }
                float nextFloat = WaterView.this.random.nextFloat();
                while (true) {
                    f = nextFloat * 5.0f;
                    if (f >= 1.0f) {
                        break;
                    } else {
                        nextFloat = WaterView.this.random.nextFloat();
                    }
                }
                bubble.setRadius(i);
                bubble.setSpeedY(f);
                bubble.setX(WaterView.this.mWidth / 2);
                bubble.setY(WaterView.this.mHeight - (WaterView.this.frameWidth * 2.0f));
                float nextFloat2 = WaterView.this.random.nextFloat();
                while (true) {
                    float f2 = nextFloat2 - 0.5f;
                    if (f2 == 0.0f) {
                        nextFloat2 = WaterView.this.random.nextFloat();
                    } else {
                        bubble.setSpeedX(f2 * 2.0f);
                        WaterView.this.bubbles.add(bubble);
                        return;
                    }
                }
            }
        }.start();
        Paint paint = new Paint();
        paint.setColor(0);
        canvas.drawRect(0.0f, 0.0f, this.mWidth, this.mHeight, paint);
        paint.reset();
        paint.setColor(-1);
        paint.setAlpha(200);
        for (Bubble bubble : new ArrayList(this.bubbles)) {
            if (bubble.getY() + bubble.getSpeedY() < 0.0f) {
                this.bubbles.remove(bubble);
            } else {
                int indexOf = this.bubbles.indexOf(bubble);
                if (bubble.getX() + bubble.getSpeedX() <= bubble.getRadius()) {
                    bubble.setX(bubble.getRadius());
                } else if (bubble.getX() + bubble.getSpeedX() >= this.mWidth - bubble.getRadius()) {
                    bubble.setX(this.mWidth - bubble.getRadius());
                } else {
                    bubble.setX(bubble.getX() + bubble.getSpeedX());
                }
                bubble.setY(bubble.getY() - bubble.getSpeedY());
                this.bubbles.set(indexOf, bubble);
                canvas.drawCircle(bubble.getX(), bubble.getY(), bubble.getRadius(), paint);
            }
        }
    }

    private Path firstPath() {
        this.firstPath.reset();
        this.firstPath.moveTo(0.0f, this.mHeight);
        float f = 0.0f;
        while (true) {
            int i = this.mWidth;
            if (f <= i) {
                this.firstPath.lineTo(f, ((float) (this.sin_amplitude * Math.sin((this.sin_cycle * f) + this.sin_offset + 40.0f))) + this.f10012h);
                f += 1.0f;
            } else {
                this.firstPath.lineTo(i, this.mHeight);
                this.firstPath.lineTo(0.0f, this.mHeight);
                this.firstPath.close();
                return this.firstPath;
            }
        }
    }

    private Path secondPath() {
        this.secondPath.reset();
        this.secondPath.moveTo(0.0f, this.mHeight);
        float f = 0.0f;
        while (true) {
            int i = this.mWidth;
            if (f <= i) {
                this.secondPath.lineTo(f, ((float) (this.sin_amplitude * Math.sin((this.sin_cycle * f) + this.sin_offset))) + this.f10012h);
                f += 1.0f;
            } else {
                this.secondPath.lineTo(i, this.mHeight);
                this.secondPath.lineTo(0.0f, this.mHeight);
                this.secondPath.close();
                return this.secondPath;
            }
        }
    }

    @Override // android.view.View
    public void postInvalidate() {
        super.postInvalidate();
        this.isPause = true;
    }

    public int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void reset() {
        if (this.runThread != null) {
            this.runThread = null;
        }
        this.isStop = false;
        this.isKeepHeight = false;
        this.f10012h = this.mHeight;
        this.sin_offset = 0.0f;
        invalidate();
    }

    public void start() {
        this.isStart = true;
        this.runThread = new RunThread();
        this.runThread.start();
        this.isCustomSpeedY = true;
    }

    public void stop() {
        this.isStop = true;
        this.isKeepHeight = false;
    }

    public void recover() {
        this.isStop = false;
    }

    public void keepHeight() {
        this.isStop = true;
        this.isKeepHeight = true;
        this.isCustomSpeedY = false;
    }

    public void setKeepPercent(float f) {
        this.keep_percent = f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public class RunThread extends Thread {
        private RunThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (WaterView.this.isStart) {
                if (WaterView.this.isStop) {
                    if (WaterView.this.isKeepHeight) {
                        try {
                            Thread.sleep(WaterView.this.sleep_time);
                            WaterView.this.sin_offset += WaterView.this.sin_offset_increment_value;
                            WaterView.this.postInvalidate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        Thread.sleep(WaterView.this.sleep_time);
                        WaterView.this.f10012h -= WaterView.this.sin_up_velocity;
                        WaterView.this.sin_offset += WaterView.this.sin_offset_increment_value;
                        if (WaterView.this.f10012h < WaterView.this.mHeight * (1.0f - WaterView.this.keep_percent)) {
                            WaterView.this.keepHeight();
                        }
                        WaterView.this.postInvalidate();
                        if (WaterView.this.f10012h + WaterView.this.sin_amplitude < 0.0f) {
                            if (WaterView.this.listener != null) {
                                WaterView.this.post(new Runnable() { // from class: org.hash.mock.debug.view.wave.WaterView.RunThread.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        WaterView.this.listener.finish();
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        continue;
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public class Bubble {
        private int radius;
        private float speedX;
        private float speedY;

        /* renamed from: x */
        private float f10013x;

        /* renamed from: y */
        private float f10014y;

        private Bubble() {
        }

        public int getRadius() {
            return this.radius;
        }

        public void setRadius(int i) {
            this.radius = i;
        }

        public float getX() {
            return this.f10013x;
        }

        public void setX(float f) {
            this.f10013x = f;
        }

        public float getY() {
            return this.f10014y;
        }

        public void setY(float f) {
            this.f10014y = f;
        }

        public float getSpeedY() {
            return this.speedY;
        }

        public void setSpeedY(float f) {
            this.speedY = f;
        }

        public float getSpeedX() {
            return this.speedX;
        }

        public void setSpeedX(float f) {
            this.speedX = f;
        }
    }
}
