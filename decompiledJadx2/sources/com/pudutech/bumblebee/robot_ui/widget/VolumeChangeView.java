package com.pudutech.bumblebee.robot_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.pudutech.voiceinteraction.component.C5767R;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VolumeChangeView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\"H\u0002J\u0010\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020&H\u0014J\u000e\u0010'\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\tR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/widget/VolumeChangeView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "amplitude", "", "curveCount", "curvePathList", "Ljava/util/ArrayList;", "Landroid/graphics/Path;", "Lkotlin/collections/ArrayList;", "curveSpeed", "fineness", "isSet", "", "lastTime", "", "maxVolume", "paint", "Landroid/graphics/Paint;", "sensibility", "targetVolume", "translateX", "volume", "volumeCurveColor", "volumeCurveWidth", "curveChange", "", "init", "onDraw", "canvas", "Landroid/graphics/Canvas;", "setVolume", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VolumeChangeView extends View {
    private HashMap _$_findViewCache;
    private float amplitude;
    private int curveCount;
    private ArrayList<Path> curvePathList;
    private int curveSpeed;
    private int fineness;
    private boolean isSet;
    private long lastTime;
    private int maxVolume;
    private Paint paint;
    private int sensibility;
    private float targetVolume;
    private float translateX;
    private float volume;
    private int volumeCurveColor;
    private int volumeCurveWidth;

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

    public VolumeChangeView(Context context) {
        this(context, null);
    }

    public VolumeChangeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VolumeChangeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.sensibility = 4;
        this.maxVolume = 100;
        this.targetVolume = 1.0f;
        this.volume = 10.0f;
        this.amplitude = 1.0f;
        this.fineness = 1;
        this.curveSpeed = 90;
        TypedArray obtainStyledAttributes = context != null ? context.obtainStyledAttributes(attributeSet, C5767R.styleable.VolumeChangeView) : null;
        this.volumeCurveColor = obtainStyledAttributes != null ? obtainStyledAttributes.getColor(C5767R.styleable.VolumeChangeView_vcv_volume_curve_color, -16776961) : -16776961;
        this.volumeCurveWidth = obtainStyledAttributes != null ? obtainStyledAttributes.getDimensionPixelOffset(C5767R.styleable.VolumeChangeView_vcv_volume_curve_width, -16776961) : -16776961;
        this.curveCount = obtainStyledAttributes != null ? obtainStyledAttributes.getInt(C5767R.styleable.VolumeChangeView_vcv_curve_count, 20) : 20;
        this.sensibility = obtainStyledAttributes != null ? obtainStyledAttributes.getInt(C5767R.styleable.VolumeChangeView_vcv_sensibility, 4) : 4;
        this.curveSpeed = obtainStyledAttributes != null ? obtainStyledAttributes.getInt(C5767R.styleable.VolumeChangeView_vcv_curveSpeed, 90) : 90;
        this.fineness = obtainStyledAttributes != null ? obtainStyledAttributes.getInt(C5767R.styleable.VolumeChangeView_vcv_fineness, 1) : 1;
        if (obtainStyledAttributes != null) {
            obtainStyledAttributes.recycle();
        }
        init();
    }

    private final void init() {
        this.paint = new Paint();
        Paint paint = this.paint;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paint");
        }
        paint.setColor(this.volumeCurveColor);
        Paint paint2 = this.paint;
        if (paint2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paint");
        }
        paint2.setAntiAlias(true);
        Paint paint3 = this.paint;
        if (paint3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paint");
        }
        paint3.setStyle(Paint.Style.STROKE);
        Paint paint4 = this.paint;
        if (paint4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paint");
        }
        paint4.setStrokeWidth(2.0f);
        this.curvePathList = new ArrayList<>(this.curveCount);
        int i = this.curveCount;
        for (int i2 = 0; i2 < i; i2++) {
            ArrayList<Path> arrayList = this.curvePathList;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
            }
            arrayList.add(new Path());
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        curveChange();
        canvas.save();
        int height = getHeight() / 2;
        ArrayList<Path> arrayList = this.curvePathList;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
        }
        int size = arrayList.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<Path> arrayList2 = this.curvePathList;
            if (arrayList2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
            }
            arrayList2.get(i2).reset();
            ArrayList<Path> arrayList3 = this.curvePathList;
            if (arrayList3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
            }
            arrayList3.get(i2).moveTo(getWidth(), getHeight() / 2);
        }
        int i3 = 1;
        float width = getWidth() - 1;
        while (width >= i) {
            float f = 2;
            this.amplitude = (((this.volume * f) * width) / getWidth()) - (((((f * this.volume) * width) * width) / getWidth()) / getWidth());
            ArrayList<Path> arrayList4 = this.curvePathList;
            if (arrayList4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
            }
            int size2 = arrayList4.size();
            if (i3 <= size2) {
                int i4 = i3;
                while (true) {
                    float sin = this.amplitude * ((float) Math.sin((((width - Math.pow(1.22d, i4)) * 3.141592653589793d) / 180) - this.translateX));
                    ArrayList<Path> arrayList5 = this.curvePathList;
                    if (arrayList5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
                    }
                    Path path = arrayList5.get(i4 - 1);
                    float f2 = i4 * 2.0f * sin;
                    if (this.curvePathList == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
                    }
                    float size3 = f2 / r7.size();
                    float f3 = 15 * sin;
                    if (this.curvePathList == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
                    }
                    path.lineTo(width, (size3 - (f3 / r10.size())) + height);
                    if (i4 != size2) {
                        i4++;
                    }
                }
            }
            width -= this.fineness;
            i = 0;
            i3 = 1;
        }
        ArrayList<Path> arrayList6 = this.curvePathList;
        if (arrayList6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
        }
        int size4 = arrayList6.size();
        for (int i5 = 0; i5 < size4; i5++) {
            ArrayList<Path> arrayList7 = this.curvePathList;
            if (arrayList7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
            }
            if (i5 == arrayList7.size() - 1) {
                Paint paint = this.paint;
                if (paint == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("paint");
                }
                paint.setAlpha(255);
            } else {
                Paint paint2 = this.paint;
                if (paint2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("paint");
                }
                int i6 = i5 * 130;
                ArrayList<Path> arrayList8 = this.curvePathList;
                if (arrayList8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
                }
                paint2.setAlpha(i6 / arrayList8.size());
            }
            Paint paint3 = this.paint;
            if (paint3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("paint");
            }
            if (paint3.getAlpha() > 0) {
                ArrayList<Path> arrayList9 = this.curvePathList;
                if (arrayList9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("curvePathList");
                }
                Path path2 = arrayList9.get(i5);
                Paint paint4 = this.paint;
                if (paint4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("paint");
                }
                canvas.drawPath(path2, paint4);
            }
        }
        canvas.restore();
        invalidate();
    }

    public final void setVolume(int volume) {
        Log.d("VolumeChangedView", "volume[" + volume + ']');
        if (volume > (this.maxVolume * this.sensibility) / 25) {
            this.isSet = true;
            this.targetVolume = (getHeight() * volume) / this.maxVolume;
        }
    }

    private final void curveChange() {
        int height;
        if (this.lastTime == 0) {
            this.lastTime = System.currentTimeMillis();
            this.translateX += 1.5f;
        } else {
            if (System.currentTimeMillis() - this.lastTime <= this.curveSpeed) {
                return;
            }
            this.lastTime = System.currentTimeMillis();
            this.translateX += 1.5f;
        }
        float f = this.volume;
        if (f < this.targetVolume && this.isSet) {
            this.volume = f + (getHeight() / 30);
            return;
        }
        this.isSet = false;
        float f2 = this.volume;
        if (f2 <= 10) {
            this.volume = 10.0f;
            return;
        }
        if (f2 < getHeight() / 30) {
            height = getHeight() / 60;
        } else {
            height = getHeight() / 30;
        }
        this.volume = f2 - height;
    }
}
