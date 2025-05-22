package com.pudutech.bumblebee.robot_ui.widget.loopview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.disinfect.baselib.util.UiUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class LoopView extends View {
    private static final float DEFAULT_LINE_SPACE = 1.0f;
    private static final int DEFAULT_TEXT_SIZE = (int) (Resources.getSystem().getDisplayMetrics().density * 15.0f);
    private static final int DEFAULT_VISIBIE_ITEMS = 9;
    public static final int SCROLL_STATE_DRAGGING = 2;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SCROLLING = 3;
    public static final int SCROLL_STATE_SETTING = 1;
    int centerTextColor;
    int change;
    private Context context;
    int currentScrollState;
    int defaultTextSize;
    int dividerColor;
    HashMap<Integer, IndexString> drawingStrings;
    int firstLineY;
    private GestureDetector flingGestureDetector;
    int halfCircumference;
    Handler handler;
    int initPosition;
    boolean isLoop;
    int itemTextHeight;
    List<IndexString> items;
    int itemsVisibleCount;
    int lastScrollState;
    float lineSpacingMultiplier;
    ScheduledExecutorService mExecutor;
    private ScheduledFuture<?> mFuture;
    private int mOffset;
    OnItemScrollListener mOnItemScrollListener;
    int measuredHeight;
    int measuredWidth;
    OnItemSelectedListener onItemSelectedListener;
    int outerTextColor;
    private int paddingEnd;
    private int paddingStart;
    private Paint paintCenterText;
    private Paint paintIndicator;
    private Paint paintOuterText;
    int preCurrentIndex;
    private float previousY;
    int radius;
    private float scaleX;
    private float secondHeight;
    int secondLineY;
    private Paint secondPaint;
    private float secondWidth;
    private float secondX;
    long startTime;
    private Rect tempRect;
    int textHeight;
    int textSize;
    String tipsText;
    int totalScrollY;
    private Typeface typeface;

    /* loaded from: classes4.dex */
    public enum ACTION {
        CLICK,
        FLING,
        DRAG
    }

    public void setLineSpacingMultiplier(float f) {
        if (f > 1.0f) {
            this.lineSpacingMultiplier = f;
        }
    }

    public void setCenterTextColor(int i) {
        this.centerTextColor = i;
        Paint paint = this.paintCenterText;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public void setOuterTextColor(int i) {
        this.outerTextColor = i;
        Paint paint = this.paintOuterText;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public void setDividerColor(int i) {
        this.dividerColor = i;
        Paint paint = this.paintIndicator;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public LoopView(Context context) {
        super(context);
        this.scaleX = 1.05f;
        this.lastScrollState = 0;
        this.currentScrollState = 1;
        this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mOffset = 0;
        this.startTime = 0L;
        this.tempRect = new Rect();
        this.typeface = Typeface.MONOSPACE;
        initLoopView(context, null);
    }

    public LoopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scaleX = 1.05f;
        this.lastScrollState = 0;
        this.currentScrollState = 1;
        this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mOffset = 0;
        this.startTime = 0L;
        this.tempRect = new Rect();
        this.typeface = Typeface.MONOSPACE;
        initLoopView(context, attributeSet);
    }

    public LoopView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.scaleX = 1.05f;
        this.lastScrollState = 0;
        this.currentScrollState = 1;
        this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mOffset = 0;
        this.startTime = 0L;
        this.tempRect = new Rect();
        this.typeface = Typeface.MONOSPACE;
        initLoopView(context, attributeSet);
    }

    private void initLoopView(Context context, AttributeSet attributeSet) {
        this.context = context;
        this.handler = new MessageHandler(this);
        this.flingGestureDetector = new GestureDetector(context, new LoopViewGestureListener(this));
        this.flingGestureDetector.setIsLongpressEnabled(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4188R.styleable.LoopView);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (obtainStyledAttributes != null) {
            this.tipsText = obtainStyledAttributes.getString(C4188R.styleable.LoopView_awv_tips_text);
            this.textSize = (int) obtainStyledAttributes.getDimension(C4188R.styleable.LoopView_awv_textsize, TypedValue.applyDimension(2, DEFAULT_TEXT_SIZE, displayMetrics));
            this.defaultTextSize = (int) obtainStyledAttributes.getDimension(C4188R.styleable.LoopView_default_text_size, TypedValue.applyDimension(2, DEFAULT_TEXT_SIZE, displayMetrics));
            this.lineSpacingMultiplier = obtainStyledAttributes.getFloat(C4188R.styleable.LoopView_awv_lineSpace, 1.0f);
            this.centerTextColor = obtainStyledAttributes.getInteger(C4188R.styleable.LoopView_awv_centerTextColor, -13553359);
            this.outerTextColor = obtainStyledAttributes.getInteger(C4188R.styleable.LoopView_awv_outerTextColor, -5263441);
            this.dividerColor = obtainStyledAttributes.getInteger(C4188R.styleable.LoopView_awv_dividerTextColor, -3815995);
            this.itemsVisibleCount = obtainStyledAttributes.getInteger(C4188R.styleable.LoopView_awv_itemsVisibleCount, 9);
            if (this.itemsVisibleCount % 2 == 0) {
                this.itemsVisibleCount = 9;
            }
            this.isLoop = obtainStyledAttributes.getBoolean(C4188R.styleable.LoopView_awv_isLoop, true);
            obtainStyledAttributes.recycle();
        }
        this.drawingStrings = new HashMap<>();
        this.totalScrollY = 0;
        this.initPosition = -1;
    }

    public void setItemsVisibleCount(int i) {
        if (i % 2 == 0 || i == this.itemsVisibleCount) {
            return;
        }
        this.itemsVisibleCount = i;
        this.drawingStrings = new HashMap<>();
    }

    private void initPaintsIfPossible() {
        String str;
        if (this.paintOuterText == null) {
            this.paintOuterText = new Paint();
            this.paintOuterText.setColor(this.outerTextColor);
            this.paintOuterText.setAntiAlias(true);
            this.paintOuterText.setTypeface(this.typeface);
            this.paintOuterText.setTextSize(this.defaultTextSize);
        }
        if (this.paintCenterText == null) {
            this.paintCenterText = new Paint();
            this.paintCenterText.setColor(this.centerTextColor);
            this.paintCenterText.setAntiAlias(true);
            this.paintCenterText.setTextScaleX(this.scaleX);
            this.paintCenterText.setTypeface(this.typeface);
            this.paintCenterText.setTextSize(this.textSize);
        }
        if (this.paintIndicator == null) {
            this.paintIndicator = new Paint();
            this.paintIndicator.setColor(this.dividerColor);
            this.paintIndicator.setAntiAlias(true);
        }
        if (this.secondPaint != null || (str = this.tipsText) == null || str.isEmpty()) {
            return;
        }
        this.secondPaint = new Paint();
        this.secondPaint.setColor(this.centerTextColor);
        this.secondPaint.setTypeface(this.typeface);
        this.secondPaint.setAntiAlias(true);
        this.secondPaint.setTextSize(UiUtils.sp2px(24.0f));
        this.secondWidth = this.secondPaint.measureText(this.tipsText);
        Rect rect = new Rect();
        Paint paint = this.secondPaint;
        String str2 = this.tipsText;
        paint.getTextBounds(str2, 0, str2.length(), rect);
        this.secondHeight = rect.height();
    }

    private void remeasure() {
        List<IndexString> list = this.items;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.measuredWidth = getMeasuredWidth();
        this.measuredHeight = getMeasuredHeight();
        if (this.measuredWidth == 0 || this.measuredHeight == 0) {
            return;
        }
        this.paddingStart = getPaddingStart();
        this.paddingEnd = getPaddingEnd();
        this.measuredWidth -= this.paddingEnd;
        this.paintCenterText.getTextBounds("星期", 0, 2, this.tempRect);
        this.textHeight = this.tempRect.height();
        this.halfCircumference = (int) ((this.measuredHeight * 3.141592653589793d) / 2.0d);
        this.itemTextHeight = (int) (this.halfCircumference / (this.lineSpacingMultiplier * (this.itemsVisibleCount - 1)));
        this.secondX = ((this.measuredWidth + this.paintCenterText.measureText(ErrorCode.UNKNOWN_ERROR_CODE)) / 2.0f) + UiUtils.dip2px(18.0f);
        int i = this.measuredHeight;
        this.radius = i / 2;
        float f = this.lineSpacingMultiplier;
        int i2 = this.itemTextHeight;
        this.firstLineY = (int) ((i - (i2 * f)) / 2.0f);
        this.secondLineY = (int) ((i + (f * i2)) / 2.0f);
        if (this.initPosition == -1) {
            if (this.isLoop) {
                this.initPosition = (this.items.size() + 1) / 2;
            } else {
                this.initPosition = 0;
            }
        }
        this.preCurrentIndex = this.initPosition;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void smoothScroll(ACTION action) {
        cancelFuture();
        if (action == ACTION.FLING || action == ACTION.DRAG) {
            float f = this.lineSpacingMultiplier * this.itemTextHeight;
            this.mOffset = (int) (((this.totalScrollY % f) + f) % f);
            int i = this.mOffset;
            if (i > f / 2.0f) {
                this.mOffset = (int) (f - i);
            } else {
                this.mOffset = -i;
            }
        }
        this.mFuture = this.mExecutor.scheduleWithFixedDelay(new SmoothScrollTimerTask(this, this.mOffset), 0L, 10L, TimeUnit.MILLISECONDS);
        changeScrollState(3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void scrollBy(float f) {
        cancelFuture();
        this.mFuture = this.mExecutor.scheduleWithFixedDelay(new InertiaTimerTask(this, f), 0L, 10, TimeUnit.MILLISECONDS);
        changeScrollState(2);
    }

    public void cancelFuture() {
        ScheduledFuture<?> scheduledFuture = this.mFuture;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            return;
        }
        this.mFuture.cancel(true);
        this.mFuture = null;
        changeScrollState(0);
    }

    private void printMethodStackTrace(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder("printMethodStackTrace ");
        sb.append(str);
        sb.append(" ");
        for (int length = stackTrace.length - 1; length >= 4; length--) {
            StackTraceElement stackTraceElement = stackTrace[length];
            sb.append(String.format("%s(%d).%s", stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber()), stackTraceElement.getMethodName()));
            if (length > 4) {
                sb.append("-->");
            }
        }
        Log.i("printMethodStackTrace", sb.toString());
    }

    private void changeScrollState(int i) {
        if (i == this.currentScrollState || this.handler.hasMessages(MessageHandler.WHAT_SMOOTH_SCROLL_INERTIA)) {
            return;
        }
        this.lastScrollState = this.currentScrollState;
        this.currentScrollState = i;
    }

    public void setNotLoop() {
        this.isLoop = false;
    }

    public final void setTextSize(float f) {
        if (f > 0.0f) {
            this.textSize = (int) (this.context.getResources().getDisplayMetrics().density * f);
            Paint paint = this.paintOuterText;
            if (paint != null) {
                paint.setTextSize(this.defaultTextSize);
            }
            Paint paint2 = this.paintCenterText;
            if (paint2 != null) {
                paint2.setTextSize(this.textSize);
            }
        }
    }

    public final void setInitPosition(int i) {
        if (i < 0) {
            this.initPosition = 0;
            return;
        }
        List<IndexString> list = this.items;
        if (list == null || list.size() <= i) {
            return;
        }
        this.initPosition = i;
    }

    public final void setListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public final void setOnItemScrollListener(OnItemScrollListener onItemScrollListener) {
        this.mOnItemScrollListener = onItemScrollListener;
    }

    public final void setItems(List<String> list) {
        this.items = convertData(list);
        remeasure();
        invalidate();
    }

    public List<IndexString> convertData(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new IndexString(i, list.get(i)));
        }
        return arrayList;
    }

    public final int getSelectedItem() {
        return this.preCurrentIndex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onItemSelected() {
        if (this.onItemSelectedListener != null) {
            postDelayed(new OnItemSelectedRunnable(this), 200L);
        }
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        this.scaleX = f;
    }

    public void setCurrentPosition(int i) {
        List<IndexString> list = this.items;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.items.size();
        if (i < 0 || i >= size || i == getSelectedItem()) {
            return;
        }
        this.initPosition = i;
        this.totalScrollY = 0;
        this.mOffset = 0;
        changeScrollState(1);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        OnItemScrollListener onItemScrollListener;
        super.onDraw(canvas);
        List<IndexString> list = this.items;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.change = (int) (this.totalScrollY / (this.lineSpacingMultiplier * this.itemTextHeight));
        this.preCurrentIndex = this.initPosition + (this.change % this.items.size());
        if (!this.isLoop) {
            if (this.preCurrentIndex < 0) {
                this.preCurrentIndex = 0;
            }
            if (this.preCurrentIndex > this.items.size() - 1) {
                this.preCurrentIndex = this.items.size() - 1;
            }
        } else {
            if (this.preCurrentIndex < 0) {
                this.preCurrentIndex = this.items.size() + this.preCurrentIndex;
            }
            if (this.preCurrentIndex > this.items.size() - 1) {
                this.preCurrentIndex -= this.items.size();
            }
        }
        int i = (int) (this.totalScrollY % (this.lineSpacingMultiplier * this.itemTextHeight));
        int i2 = 0;
        while (true) {
            int i3 = this.itemsVisibleCount;
            if (i2 >= i3) {
                break;
            }
            int i4 = this.preCurrentIndex - ((i3 / 2) - i2);
            if (this.isLoop) {
                while (i4 < 0) {
                    i4 += this.items.size();
                }
                while (i4 > this.items.size() - 1) {
                    i4 -= this.items.size();
                }
                this.drawingStrings.put(Integer.valueOf(i2), this.items.get(i4));
            } else if (i4 < 0) {
                this.drawingStrings.put(Integer.valueOf(i2), new IndexString());
            } else if (i4 > this.items.size() - 1) {
                this.drawingStrings.put(Integer.valueOf(i2), new IndexString());
            } else {
                this.drawingStrings.put(Integer.valueOf(i2), this.items.get(i4));
            }
            i2++;
        }
        float f = this.paddingStart;
        int i5 = this.firstLineY;
        canvas.drawLine(f, i5, this.measuredWidth, i5, this.paintIndicator);
        float f2 = this.paddingStart;
        int i6 = this.secondLineY;
        canvas.drawLine(f2, i6, this.measuredWidth, i6, this.paintIndicator);
        canvas.drawText(this.tipsText, this.secondX, getDrawingSecondY(), this.secondPaint);
        for (int i7 = 0; i7 < this.itemsVisibleCount; i7++) {
            canvas.save();
            float f3 = this.itemTextHeight * this.lineSpacingMultiplier;
            double d = (((i7 * f3) - i) * 3.141592653589793d) / this.halfCircumference;
            if (d >= 3.141592653589793d || d <= 0.0d) {
                canvas.restore();
            } else {
                int cos = (int) ((this.radius - (Math.cos(d) * this.radius)) - ((Math.sin(d) * this.itemTextHeight) / 2.0d));
                canvas.translate(0.0f, cos);
                canvas.scale(1.0f, (float) Math.sin(d));
                int i8 = this.firstLineY;
                if (cos <= i8 && this.itemTextHeight + cos >= i8) {
                    canvas.save();
                    canvas.clipRect(0, 0, this.measuredWidth, this.firstLineY - cos);
                    drawOuterText(canvas, i7);
                    canvas.restore();
                    canvas.save();
                    canvas.clipRect(0, this.firstLineY - cos, this.measuredWidth, (int) f3);
                    drawCenterText(canvas, i7);
                    canvas.restore();
                } else {
                    int i9 = this.secondLineY;
                    if (cos <= i9 && this.itemTextHeight + cos >= i9) {
                        canvas.save();
                        canvas.clipRect(0, 0, this.measuredWidth, this.secondLineY - cos);
                        drawCenterText(canvas, i7);
                        canvas.restore();
                        canvas.save();
                        canvas.clipRect(0, this.secondLineY - cos, this.measuredWidth, (int) f3);
                        drawOuterText(canvas, i7);
                        canvas.restore();
                    } else if (cos >= this.firstLineY && this.itemTextHeight + cos <= this.secondLineY) {
                        canvas.clipRect(0, 0, this.measuredWidth, (int) f3);
                        drawCenterText(canvas, i7);
                    } else {
                        canvas.clipRect(0, 0, this.measuredWidth, (int) f3);
                        drawOuterText(canvas, i7);
                    }
                }
                canvas.restore();
            }
        }
        int i10 = this.currentScrollState;
        int i11 = this.lastScrollState;
        if (i10 != i11) {
            this.lastScrollState = i10;
            OnItemScrollListener onItemScrollListener2 = this.mOnItemScrollListener;
            if (onItemScrollListener2 != null) {
                onItemScrollListener2.onItemScrollStateChanged(this, getSelectedItem(), i11, this.currentScrollState, this.totalScrollY);
            }
        }
        int i12 = this.currentScrollState;
        if ((i12 == 2 || i12 == 3) && (onItemScrollListener = this.mOnItemScrollListener) != null) {
            onItemScrollListener.onItemScrolling(this, getSelectedItem(), this.currentScrollState, this.totalScrollY);
        }
    }

    private void drawOuterText(Canvas canvas, int i) {
        canvas.drawText(this.drawingStrings.get(Integer.valueOf(i)).string, getTextX(this.drawingStrings.get(Integer.valueOf(i)).string, this.paintOuterText, this.tempRect), getDrawingY(), this.paintOuterText);
    }

    private void drawCenterText(Canvas canvas, int i) {
        canvas.drawText(this.drawingStrings.get(Integer.valueOf(i)).string, getTextX(this.drawingStrings.get(Integer.valueOf(i)).string, this.paintCenterText, this.tempRect), getDrawingY(), this.paintCenterText);
    }

    private int getDrawingY() {
        int i = this.itemTextHeight;
        int i2 = this.textHeight;
        return i > i2 ? i - ((i - i2) / 2) : i;
    }

    private float getDrawingSecondY() {
        return (this.measuredHeight + this.secondHeight) / 2.0f;
    }

    private int getTextX(String str, Paint paint, Rect rect) {
        paint.getTextBounds(str, 0, str.length(), rect);
        int width = (int) (rect.width() * this.scaleX);
        int i = this.measuredWidth;
        int i2 = this.paddingStart;
        return (((i - i2) - width) / 2) + i2;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        initPaintsIfPossible();
        remeasure();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = this.flingGestureDetector.onTouchEvent(motionEvent);
        float f = this.lineSpacingMultiplier * this.itemTextHeight;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startTime = System.currentTimeMillis();
            cancelFuture();
            this.previousY = motionEvent.getRawY();
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        } else if (action == 2) {
            float rawY = this.previousY - motionEvent.getRawY();
            this.previousY = motionEvent.getRawY();
            this.totalScrollY = (int) (this.totalScrollY + rawY);
            if (!this.isLoop) {
                float f2 = (-this.initPosition) * f;
                float size = ((this.items.size() - 1) - this.initPosition) * f;
                int i = this.totalScrollY;
                if (i < f2) {
                    this.totalScrollY = (int) f2;
                } else if (i > size) {
                    this.totalScrollY = (int) size;
                }
            }
            changeScrollState(2);
        } else {
            if (!onTouchEvent) {
                float y = motionEvent.getY();
                int i2 = this.radius;
                int acos = (int) (((Math.acos((i2 - y) / i2) * this.radius) + (f / 2.0f)) / f);
                this.mOffset = (int) (((acos - (this.itemsVisibleCount / 2)) * f) - (((this.totalScrollY % f) + f) % f));
                if (System.currentTimeMillis() - this.startTime > 120) {
                    smoothScroll(ACTION.DRAG);
                } else {
                    smoothScroll(ACTION.CLICK);
                }
            }
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        invalidate();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class IndexString {
        private int index;
        private String string;

        public IndexString() {
            this.string = "";
        }

        public IndexString(int i, String str) {
            this.index = i;
            this.string = str;
        }
    }
}
