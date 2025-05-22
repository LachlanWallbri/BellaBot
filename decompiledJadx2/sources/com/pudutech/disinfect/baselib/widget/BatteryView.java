package com.pudutech.disinfect.baselib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import com.pudutech.base.Pdlog;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BatteryView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 +2\u00020\u0001:\u0001+B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0018\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000fH\u0014J\u000e\u0010#\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020%J\u000e\u0010'\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020%J\u000e\u0010(\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\u000fR\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/widget/BatteryView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attr", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "batteryHeadPaint", "Landroid/graphics/Paint;", "batteryOutLinePaint", "batteryPaint", "chargingJob", "Lkotlinx/coroutines/Job;", "chargingPower", "", "isCharge", "", "mPower", "powerBorder", "", "powerHeight", "powerMargin", "powerRect", "Landroid/graphics/RectF;", "powerWidth", "radius", "init", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "setBatteryHeadPaintColor", TypedValues.Custom.S_COLOR, "", "setBatteryOutlinesPaintColor", "setBatteryPaintColor", "setCharge", "setPower", "power", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BatteryView extends View {
    private static final int MAX_POWER_VALUE = 100;
    private static final String TAG = "BatteryView";
    private static final float outline_stroke = 2.0f;
    private HashMap _$_findViewCache;
    private Paint batteryHeadPaint;
    private Paint batteryOutLinePaint;
    private Paint batteryPaint;
    private Job chargingJob;
    private int chargingPower;
    private boolean isCharge;
    private int mPower;
    private float powerBorder;
    private int powerHeight;
    private float powerMargin;
    private RectF powerRect;
    private int powerWidth;
    private float radius;

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
    public BatteryView(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mPower = 50;
        this.powerMargin = outline_stroke;
        this.powerBorder = 4.0f;
        this.radius = outline_stroke;
        this.chargingPower = 50;
        init();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryView(Context context, AttributeSet attr) {
        super(context, attr);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attr, "attr");
        this.mPower = 50;
        this.powerMargin = outline_stroke;
        this.powerBorder = 4.0f;
        this.radius = outline_stroke;
        this.chargingPower = 50;
        init();
    }

    private final void init() {
        this.powerHeight = getMeasuredHeight();
        this.powerWidth = getMeasuredWidth();
        boolean z = this.powerHeight == 0;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Assertion failed");
        }
        boolean z2 = this.powerWidth == 0;
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError("Assertion failed");
        }
        this.batteryOutLinePaint = new Paint();
        Paint paint = this.batteryOutLinePaint;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryOutLinePaint");
        }
        paint.setColor(Color.parseColor("#222222"));
        Paint paint2 = this.batteryOutLinePaint;
        if (paint2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryOutLinePaint");
        }
        paint2.setAntiAlias(true);
        Paint paint3 = this.batteryOutLinePaint;
        if (paint3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryOutLinePaint");
        }
        paint3.setStrokeWidth(outline_stroke);
        Paint paint4 = this.batteryOutLinePaint;
        if (paint4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryOutLinePaint");
        }
        paint4.setStyle(Paint.Style.STROKE);
        this.batteryPaint = new Paint();
        Paint paint5 = this.batteryPaint;
        if (paint5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryPaint");
        }
        paint5.setColor(ViewCompat.MEASURED_STATE_MASK);
        Paint paint6 = this.batteryPaint;
        if (paint6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryPaint");
        }
        paint6.setAntiAlias(true);
        Paint paint7 = this.batteryPaint;
        if (paint7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryPaint");
        }
        paint7.setStyle(Paint.Style.FILL_AND_STROKE);
        this.batteryHeadPaint = new Paint();
        Paint paint8 = this.batteryHeadPaint;
        if (paint8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryHeadPaint");
        }
        paint8.setColor(Color.parseColor("#222222"));
        Paint paint9 = this.batteryHeadPaint;
        if (paint9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryHeadPaint");
        }
        paint9.setAntiAlias(true);
        Paint paint10 = this.batteryHeadPaint;
        if (paint10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryHeadPaint");
        }
        paint10.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.powerWidth = getMeasuredWidth();
        this.powerHeight = getMeasuredHeight();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.powerWidth / 20.0f;
        float f2 = this.powerHeight / 4.0f;
        RectF rectF = new RectF();
        float f3 = this.powerBorder;
        rectF.left = f3 / outline_stroke;
        rectF.top = f3 / outline_stroke;
        rectF.right = (this.powerWidth - f) - (f3 / outline_stroke);
        rectF.bottom = this.powerHeight - (f3 / outline_stroke);
        if (canvas != null) {
            float f4 = this.radius;
            Paint paint = this.batteryOutLinePaint;
            if (paint == null) {
                Intrinsics.throwUninitializedPropertyAccessException("batteryOutLinePaint");
            }
            canvas.drawRoundRect(rectF, f4, f4, paint);
        }
        int i = (int) (this.powerWidth - f);
        float f5 = 2;
        int i2 = (int) ((this.powerHeight / 2) - (f2 / f5));
        Rect rect = new Rect(i, i2, (int) (i + f), (int) (i2 + f2));
        if (canvas != null) {
            Paint paint2 = this.batteryHeadPaint;
            if (paint2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("batteryHeadPaint");
            }
            canvas.drawRect(rect, paint2);
        }
        float f6 = this.powerWidth - ((this.powerBorder + this.powerMargin) * f5);
        if (this.powerRect == null) {
            this.powerRect = new RectF();
        }
        if (this.isCharge) {
            RectF rectF2 = this.powerRect;
            if (rectF2 != null) {
                rectF2.left = this.powerBorder + this.powerMargin;
            }
            RectF rectF3 = this.powerRect;
            if (rectF3 != null) {
                rectF3.top = this.powerBorder + this.powerMargin;
            }
            RectF rectF4 = this.powerRect;
            if (rectF4 != null) {
                rectF4.right = this.powerBorder + this.powerMargin + (f6 * (this.chargingPower / 100));
            }
            RectF rectF5 = this.powerRect;
            if (rectF5 != null) {
                rectF5.bottom = (this.powerHeight - this.powerBorder) - this.powerMargin;
            }
        } else {
            RectF rectF6 = this.powerRect;
            if (rectF6 != null) {
                rectF6.left = this.powerBorder + this.powerMargin;
            }
            RectF rectF7 = this.powerRect;
            if (rectF7 != null) {
                rectF7.top = this.powerBorder + this.powerMargin;
            }
            RectF rectF8 = this.powerRect;
            if (rectF8 != null) {
                rectF8.right = this.powerBorder + this.powerMargin + (f6 * (this.mPower / 100));
            }
            RectF rectF9 = this.powerRect;
            if (rectF9 != null) {
                rectF9.bottom = (this.powerHeight - this.powerBorder) - this.powerMargin;
            }
        }
        if (canvas != null) {
            RectF rectF10 = this.powerRect;
            if (rectF10 == null) {
                Intrinsics.throwNpe();
            }
            float f7 = this.radius;
            Paint paint3 = this.batteryPaint;
            if (paint3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("batteryPaint");
            }
            canvas.drawRoundRect(rectF10, f7, f7, paint3);
        }
    }

    public final void setBatteryOutlinesPaintColor(String color) {
        Intrinsics.checkParameterIsNotNull(color, "color");
        Paint paint = this.batteryOutLinePaint;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryOutLinePaint");
        }
        paint.setColor(Color.parseColor(color));
        invalidate();
    }

    public final void setBatteryPaintColor(String color) {
        Intrinsics.checkParameterIsNotNull(color, "color");
        Pdlog.m3273d(TAG, "setBatteryPaintColor ");
        if (this.isCharge) {
            Paint paint = this.batteryPaint;
            if (paint == null) {
                Intrinsics.throwUninitializedPropertyAccessException("batteryPaint");
            }
            if (paint != null) {
                paint.setColor(Color.parseColor("#ff1cc33d"));
            }
        } else {
            int i = this.mPower;
            if (i < 10) {
                Paint paint2 = this.batteryPaint;
                if (paint2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("batteryPaint");
                }
                if (paint2 != null) {
                    paint2.setColor(Color.parseColor("#fffb313b"));
                }
            } else if (i < 30) {
                Paint paint3 = this.batteryPaint;
                if (paint3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("batteryPaint");
                }
                if (paint3 != null) {
                    paint3.setColor(Color.parseColor("#ffffa247"));
                }
            } else {
                Paint paint4 = this.batteryPaint;
                if (paint4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("batteryPaint");
                }
                if (paint4 != null) {
                    paint4.setColor(Color.parseColor(color));
                }
            }
        }
        invalidate();
    }

    public final void setBatteryHeadPaintColor(String color) {
        Intrinsics.checkParameterIsNotNull(color, "color");
        Paint paint = this.batteryHeadPaint;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("batteryHeadPaint");
        }
        paint.setColor(Color.parseColor(color));
        invalidate();
    }

    public final void setPower(int power) {
        this.mPower = power;
        if (this.mPower < 0) {
            this.mPower = 0;
        }
        if (this.mPower > 100) {
            this.mPower = 100;
        }
        postInvalidate();
    }

    public final void setCharge(boolean isCharge) {
        Job launch$default;
        this.isCharge = isCharge;
        this.chargingPower = this.mPower;
        if (isCharge) {
            Job job = this.chargingJob;
            if (job != null) {
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                this.chargingJob = (Job) null;
            }
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new BatteryView$setCharge$1(this, null), 2, null);
            this.chargingJob = launch$default;
            return;
        }
        if (isCharge) {
            return;
        }
        Job job2 = this.chargingJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.chargingJob = (Job) null;
        postInvalidate();
    }
}
