package com.pudutech.factory_test.activity;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.pudutech.factory_test.C4491R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WaterMarkView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 32\u00020\u0001:\u00013B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\u0015H\u0002J\u0018\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\tH\u0002J\u0018\u0010)\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\tH\u0002J\b\u0010*\u001a\u00020\u0015H\u0002J\b\u0010+\u001a\u00020,H\u0002J\u0018\u0010-\u001a\u00020.2\u0006\u0010'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\tH\u0002J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0015R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, m3961d2 = {"Lcom/pudutech/factory_test/activity/WaterMarkView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "deltaFixSpace", "lineHeight", "markText", "", "getMarkText", "()Ljava/lang/CharSequence;", "setMarkText", "(Ljava/lang/CharSequence;)V", "markerSpace", "markerTextSize", "", "paint", "Landroid/graphics/Paint;", "path", "Landroid/graphics/Path;", "radian", "getRadian", "()F", "setRadian", "(F)V", "repeatCountX", "repeatCountY", "repeatSpace", "singleMarkerHeight", "singleMarkerWidth", "dp2px", "dipValue", "getEndX", "itemHeight", "times", "getEndY", "getItemHeight", "getLineText", "", "isEnoughHeight", "", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "Companion", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class WaterMarkView extends View {
    public static final float DEFAULT_RADIAN = 0.5235988f;
    public static final String TAG = "WaterMarkView";
    private HashMap _$_findViewCache;
    private int deltaFixSpace;
    private int lineHeight;
    private CharSequence markText;
    private int markerSpace;
    private float markerTextSize;
    private final Paint paint;
    private final Path path;
    private float radian;
    private int repeatCountX;
    private int repeatCountY;
    private int repeatSpace;
    private int singleMarkerHeight;
    private int singleMarkerWidth;

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WaterMarkView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.markText = "水印";
        this.path = new Path();
        this.radian = 0.5235988f;
        this.repeatCountX = 1;
        this.repeatCountY = 1;
        this.repeatSpace = 1;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        this.paint = paint;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4491R.styleable.WaterMarkView);
        String string = obtainStyledAttributes.getString(3);
        this.markText = string != null ? string : "水印";
        this.lineHeight = obtainStyledAttributes.getDimensionPixelOffset(1, dp2px(50.0f));
        this.markerTextSize = obtainStyledAttributes.getDimension(4, 48.0f);
        int dimension = (int) obtainStyledAttributes.getDimension(2, dp2px(30.0f));
        this.markerSpace = dimension;
        this.deltaFixSpace = (int) obtainStyledAttributes.getDimension(0, dimension / 2);
        this.radian = obtainStyledAttributes.getFloat(5, 0.5235988f);
        obtainStyledAttributes.recycle();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WaterMarkView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WaterMarkView(Context context) {
        this(context, null, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final CharSequence getMarkText() {
        return this.markText;
    }

    public final void setMarkText(CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, "<set-?>");
        this.markText = charSequence;
    }

    public final float getRadian() {
        return this.radian;
    }

    public final void setRadian(float f) {
        this.radian = f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        Rect rect = new Rect();
        this.paint.setTextSize(this.markerTextSize);
        this.paint.getTextBounds(this.markText.toString(), 0, this.markText.toString().length(), rect);
        this.singleMarkerWidth = rect.width();
        this.singleMarkerHeight = rect.height();
        int i = 1;
        this.paint.getTextBounds("a", 0, 1, rect);
        this.repeatSpace = this.markerSpace / rect.width();
        this.repeatCountX = (int) Math.ceil(((getWidth() * 1.0d) / ((float) Math.cos(this.radian))) / (this.singleMarkerWidth + this.markerSpace));
        this.repeatCountY = (int) Math.floor((getHeight() * 1.0d) / this.lineHeight);
        float itemHeight = getItemHeight();
        int i2 = this.repeatCountY;
        if (1 > i2) {
            return;
        }
        while (true) {
            this.path.reset();
            this.path.moveTo(this.deltaFixSpace + 0.0f, i * this.lineHeight);
            this.path.lineTo(getEndX(itemHeight, i) + this.deltaFixSpace, getEndY(itemHeight, i));
            canvas.drawTextOnPath(getLineText(), this.path, 0.0f, 0.0f, this.paint);
            if (i == i2) {
                return;
            } else {
                i++;
            }
        }
    }

    private final boolean isEnoughHeight(float itemHeight, int times) {
        return itemHeight <= ((float) (times * this.lineHeight));
    }

    private final float getEndX(float itemHeight, int times) {
        return isEnoughHeight(itemHeight, times) ? getWidth() : (float) (((this.lineHeight * times) * 1.0f) / Math.tan(this.radian));
    }

    private final float getEndY(float itemHeight, int times) {
        if (isEnoughHeight(itemHeight, times)) {
            return (this.lineHeight * times) - itemHeight;
        }
        return 0.0f;
    }

    private final float getItemHeight() {
        return getWidth() * ((float) Math.tan(this.radian));
    }

    private final String getLineText() {
        StringBuilder sb = new StringBuilder();
        int i = this.repeatCountX;
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(this.markText);
            int i3 = this.repeatSpace;
            for (int i4 = 0; i4 < i3; i4++) {
                sb.append(" ");
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    private final int dp2px(float dipValue) {
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (int) ((dipValue * resources.getDisplayMetrics().density) + 0.5f);
    }
}
