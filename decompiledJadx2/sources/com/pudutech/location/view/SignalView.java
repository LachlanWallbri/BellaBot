package com.pudutech.location.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.location.C4766R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SignalView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u001a\u001a\u00020\u000eH\u0002J\u0010\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000eH\u0002J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000eH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0014J\u0018\u0010#\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000eH\u0014J\u0010\u0010$\u001a\u00020 2\b\u0010\u0018\u001a\u0004\u0018\u00010\tJ\u000e\u0010%\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u000eR\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, m3961d2 = {"Lcom/pudutech/location/view/SignalView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "TAG", "", "kotlin.jvm.PlatformType", "mPaint", "Landroid/graphics/Paint;", "mRectBorderWidth", "", "mRectCount", "mRectHeight", "", "mRectWidth", "mSignalColor", "mSignalDefColor", "mSignalTypeTextColor", "mSignalTypeTextSize", "rectOffset", "signalType", "signalValue", "measureDefWidth", "measureHeight", "heightMeasureSpec", "measureWidth", "widthMeasureSpec", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "setSignalTypeText", "setSignalValue", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SignalView extends View {
    private final String TAG;
    private HashMap _$_findViewCache;
    private Paint mPaint;
    private int mRectBorderWidth;
    private int mRectCount;
    private float mRectHeight;
    private int mRectWidth;
    private int mSignalColor;
    private int mSignalDefColor;
    private int mSignalTypeTextColor;
    private float mSignalTypeTextSize;
    private int rectOffset;
    private String signalType;
    private int signalValue;

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

    public SignalView(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SignalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4766R.styleable.SignalView);
        this.mRectCount = obtainStyledAttributes.getInt(C4766R.styleable.SignalView_signalCount, 5);
        this.rectOffset = obtainStyledAttributes.getInt(C4766R.styleable.SignalView_signalRectInterval, 2);
        this.mRectWidth = (int) obtainStyledAttributes.getDimension(C4766R.styleable.SignalView_signalWidth, 10.0f);
        this.mRectHeight = obtainStyledAttributes.getDimension(C4766R.styleable.SignalView_signalLastHeight, 20.0f);
        this.mRectBorderWidth = obtainStyledAttributes.getInt(C4766R.styleable.SignalView_rectBorderWidth, 1);
        this.mSignalDefColor = obtainStyledAttributes.getColor(C4766R.styleable.SignalView_signalDefColor, getResources().getColor(C4766R.color.signalDefColor));
        this.mSignalColor = obtainStyledAttributes.getColor(C4766R.styleable.SignalView_signalColor, getResources().getColor(C4766R.color.signalColor));
        this.mSignalTypeTextColor = obtainStyledAttributes.getColor(C4766R.styleable.SignalView_signalTypeTextColor, getResources().getColor(C4766R.color.signalColor));
        this.mSignalTypeTextSize = obtainStyledAttributes.getDimension(C4766R.styleable.SignalView_signalTypeTextSize, 20.0f);
        obtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        Paint paint = this.mPaint;
        if (paint == null) {
            Intrinsics.throwNpe();
        }
        paint.setAntiAlias(true);
    }

    public final void setSignalValue(int signalValue) {
        if (this.signalValue == signalValue) {
            Pdlog.m3273d(this.TAG, "相同signalValue=" + signalValue);
            return;
        }
        if (signalValue > this.mRectCount) {
            Pdlog.m3273d(this.TAG, "大于 signalValue=" + signalValue + " > mRectCount=" + this.mRectCount);
            return;
        }
        this.signalValue = signalValue;
        invalidate();
        Pdlog.m3273d(this.TAG, "绘制 signalValue=" + signalValue);
    }

    public final void setSignalTypeText(String signalType) {
        if (StringsKt.equals$default(this.signalType, signalType, false, 2, null)) {
            Pdlog.m3273d(this.TAG, "相同 signalType=" + signalType);
            return;
        }
        this.signalType = signalType;
        requestLayout();
        invalidate();
        Pdlog.m3273d(this.TAG, "绘制 signalType=" + signalType);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private final int measureHeight(int heightMeasureSpec) {
        int mode = View.MeasureSpec.getMode(heightMeasureSpec);
        int size = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode == 1073741824) {
            return size;
        }
        if (mode == Integer.MIN_VALUE) {
            return Math.min(50, size);
        }
        return 50;
    }

    private final int measureWidth(int widthMeasureSpec) {
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        if (mode == 1073741824) {
            return size;
        }
        if (mode != Integer.MIN_VALUE) {
            return 80;
        }
        int measureDefWidth = measureDefWidth();
        Pdlog.m3273d(this.TAG, "measureDefWidth = " + measureDefWidth);
        return Math.min(80, measureDefWidth);
    }

    private final int measureDefWidth() {
        int i;
        int i2;
        if (!TextUtils.isEmpty(this.signalType)) {
            i = this.mRectWidth * 6;
            i2 = (int) (this.mSignalTypeTextSize * 2);
        } else {
            i = this.mRectWidth * 5;
            i2 = this.rectOffset * 4;
        }
        return i + i2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        if (!TextUtils.isEmpty(this.signalType)) {
            Paint paint = this.mPaint;
            if (paint == null) {
                Intrinsics.throwNpe();
            }
            paint.setColor(this.mSignalTypeTextColor);
            Paint paint2 = this.mPaint;
            if (paint2 == null) {
                Intrinsics.throwNpe();
            }
            paint2.setTextSize(this.mSignalTypeTextSize);
            Paint paint3 = this.mPaint;
            if (paint3 == null) {
                Intrinsics.throwNpe();
            }
            paint3.setStrokeWidth(1.0f);
            Paint paint4 = this.mPaint;
            if (paint4 == null) {
                Intrinsics.throwNpe();
            }
            paint4.setStyle(Paint.Style.FILL);
            Paint paint5 = this.mPaint;
            if (paint5 == null) {
                Intrinsics.throwNpe();
            }
            paint5.setTypeface(Typeface.DEFAULT);
            String str = this.signalType;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            float f = this.mRectWidth * 6;
            float f2 = this.mSignalTypeTextSize;
            Paint paint6 = this.mPaint;
            if (paint6 == null) {
                Intrinsics.throwNpe();
            }
            canvas.drawText(str, f, f2, paint6);
        }
        int i = 0;
        int i2 = this.mRectCount;
        while (i < i2) {
            if (i < this.signalValue) {
                Paint paint7 = this.mPaint;
                if (paint7 == null) {
                    Intrinsics.throwNpe();
                }
                paint7.setColor(this.mSignalColor);
            } else {
                Paint paint8 = this.mPaint;
                if (paint8 == null) {
                    Intrinsics.throwNpe();
                }
                paint8.setColor(this.mSignalDefColor);
            }
            int i3 = this.mRectWidth;
            float f3 = (i3 * i) + this.rectOffset;
            float f4 = this.mRectHeight;
            float f5 = (this.mRectCount - i) * f4 * 0.15f;
            i++;
            float f6 = i3 * i;
            Paint paint9 = this.mPaint;
            if (paint9 == null) {
                Intrinsics.throwNpe();
            }
            canvas.drawRect(f3, f5, f6, f4, paint9);
        }
    }
}
