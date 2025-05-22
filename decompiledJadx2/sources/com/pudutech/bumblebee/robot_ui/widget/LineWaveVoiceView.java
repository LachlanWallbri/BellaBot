package com.pudutech.bumblebee.robot_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: LineWaveVoiceView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 52\u00020\u0001:\u00015B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010&\u001a\u00020'H\u0014J\u0010\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*H\u0014J\u0018\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020\tH\u0014J\b\u0010.\u001a\u00020'H\u0003J\u001e\u0010/\u001a\u00020'2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u00182\u0006\u00100\u001a\u00020\fH\u0002J\u000e\u00101\u001a\u00020'2\u0006\u00102\u001a\u00020\u0016J\u0006\u00103\u001a\u00020'J\u0006\u00104\u001a\u00020'R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00066"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/widget/LineWaveVoiceView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "DEFAULT_WAVE_HEIGHT", "", "LINE_W", "MAX_WAVE_H", "MIN_WAVE_H", "TAG", "", "isStart", "", "lineColor", "lineWidth", "", "list", "Ljava/util/concurrent/CopyOnWriteArrayList;", "getList", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "setList", "(Ljava/util/concurrent/CopyOnWriteArrayList;)V", "mRunable", "Ljava/lang/Runnable;", "paint", "Landroid/graphics/Paint;", "rectRight", "Landroid/graphics/RectF;", "textColor", "textSize", "volumeValue", "onDetachedFromWindow", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "refreshElement", "resetList", TmpConstant.TYPE_VALUE_ARRAY, "setVolume", ES6Iterator.VALUE_PROPERTY, "startRecord", "stopRecord", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LineWaveVoiceView extends View {
    private static final int UPDATE_INTERVAL_TIME = 100;
    private final int[] DEFAULT_WAVE_HEIGHT;
    private final int LINE_W;
    private final int MAX_WAVE_H;
    private final int MIN_WAVE_H;
    private final String TAG;
    private HashMap _$_findViewCache;
    private boolean isStart;
    private int lineColor;
    private float lineWidth;
    private CopyOnWriteArrayList<Integer> list;
    private Runnable mRunable;
    private Paint paint;
    private final RectF rectRight;
    private int textColor;
    private float textSize;
    private float volumeValue;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final CopyOnWriteArrayList<Integer> getList() {
        return this.list;
    }

    public final void setList(CopyOnWriteArrayList<Integer> copyOnWriteArrayList) {
        Intrinsics.checkParameterIsNotNull(copyOnWriteArrayList, "<set-?>");
        this.list = copyOnWriteArrayList;
    }

    public LineWaveVoiceView(Context context) {
        super(context);
        this.TAG = "LineWaveVoiceView";
        this.LINE_W = 5;
        this.MIN_WAVE_H = 1;
        this.MAX_WAVE_H = 5;
        this.volumeValue = 0.05f;
        this.DEFAULT_WAVE_HEIGHT = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        this.rectRight = new RectF();
        this.list = new CopyOnWriteArrayList<>();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LineWaveVoiceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "LineWaveVoiceView";
        this.LINE_W = 5;
        this.MIN_WAVE_H = 1;
        this.MAX_WAVE_H = 5;
        this.volumeValue = 0.05f;
        this.DEFAULT_WAVE_HEIGHT = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        this.rectRight = new RectF();
        this.list = new CopyOnWriteArrayList<>();
        this.paint = new Paint();
        resetList(this.list, this.DEFAULT_WAVE_HEIGHT);
        this.mRunable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.widget.LineWaveVoiceView.1
            @Override // java.lang.Runnable
            public final void run() {
                LineWaveVoiceView.this.refreshElement();
                LineWaveVoiceView.this.postInvalidate();
                if (LineWaveVoiceView.this.isStart) {
                    LineWaveVoiceView lineWaveVoiceView = LineWaveVoiceView.this;
                    lineWaveVoiceView.postDelayed(lineWaveVoiceView.mRunable, 100);
                }
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4188R.styleable.LineWaveVoiceView);
        this.lineColor = obtainStyledAttributes.getColor(C4188R.styleable.LineWaveVoiceView_voiceLineColor, Color.parseColor("#ff9c00"));
        this.lineWidth = obtainStyledAttributes.getDimension(C4188R.styleable.LineWaveVoiceView_voiceLineWidth, this.LINE_W);
        this.textSize = obtainStyledAttributes.getDimension(C4188R.styleable.LineWaveVoiceView_voiceTextSize, 42.0f);
        this.textColor = obtainStyledAttributes.getColor(C4188R.styleable.LineWaveVoiceView_voiceTextColor, Color.parseColor("#666666"));
        obtainStyledAttributes.recycle();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LineWaveVoiceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "LineWaveVoiceView";
        this.LINE_W = 5;
        this.MIN_WAVE_H = 1;
        this.MAX_WAVE_H = 5;
        this.volumeValue = 0.05f;
        this.DEFAULT_WAVE_HEIGHT = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        this.rectRight = new RectF();
        this.list = new CopyOnWriteArrayList<>();
    }

    public /* synthetic */ LineWaveVoiceView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        float f = 2;
        float f2 = this.lineWidth * f;
        int height = getHeight() / 2;
        Paint paint = this.paint;
        if (paint == null) {
            Intrinsics.throwNpe();
        }
        paint.setColor(this.lineColor);
        Paint paint2 = this.paint;
        if (paint2 == null) {
            Intrinsics.throwNpe();
        }
        paint2.setStyle(Paint.Style.FILL);
        Paint paint3 = this.paint;
        if (paint3 == null) {
            Intrinsics.throwNpe();
        }
        paint3.setStrokeWidth(this.lineWidth);
        Paint paint4 = this.paint;
        if (paint4 == null) {
            Intrinsics.throwNpe();
        }
        paint4.setAntiAlias(true);
        Pdlog.m3273d(this.TAG, "onDraw " + this.list.size());
        int size = this.list.size();
        for (int i = 0; i < size; i++) {
            RectF rectF = this.rectRight;
            float f3 = i * 2;
            float f4 = this.lineWidth;
            rectF.left = (f3 * f4) + f2 + f4;
            float f5 = height;
            float floatValue = this.list.get(i).floatValue();
            float f6 = this.lineWidth;
            rectF.top = f5 - ((floatValue * f6) / f);
            RectF rectF2 = this.rectRight;
            rectF2.right = (f3 * f6) + f2 + (f6 * f);
            rectF2.bottom = f5 + ((this.list.get(i).floatValue() * this.lineWidth) / f);
            canvas.drawRoundRect(this.rectRight, 2.0f, 2.0f, this.paint);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(140, 140);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshElement() {
        this.list.add(0, Integer.valueOf(this.MIN_WAVE_H + MathKt.roundToInt(this.volumeValue * (this.MAX_WAVE_H - 2))));
        CollectionsKt.removeLast(this.list);
    }

    public final void setVolume(float value) {
        this.volumeValue = value;
    }

    public final void startRecord() {
        if (this.isStart) {
            return;
        }
        this.isStart = true;
        this.volumeValue = 0.05f;
        post(this.mRunable);
    }

    public final void stopRecord() {
        if (this.isStart) {
            this.isStart = false;
            removeCallbacks(this.mRunable);
            resetList(this.list, this.DEFAULT_WAVE_HEIGHT);
            postInvalidate();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mRunable);
    }

    private final void resetList(CopyOnWriteArrayList<Integer> list, int[] array) {
        list.clear();
        Pdlog.m3273d(this.TAG, "resetList " + list.size());
        for (int i : array) {
            list.add(Integer.valueOf(i));
        }
    }
}
