package com.pudutech.peanut.robot_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.util.DensityUtil;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: CBatteryView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 O2\u00020\u0001:\u0001OB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010D\u001a\u00020EH\u0002J\u0006\u00101\u001a\u000202J\u0010\u0010F\u001a\u00020E2\u0006\u0010G\u001a\u00020HH\u0014J\u0018\u0010I\u001a\u00020E2\u0006\u0010J\u001a\u00020\t2\u0006\u0010K\u001a\u00020\tH\u0014J\u000e\u0010L\u001a\u00020E2\u0006\u00101\u001a\u000202J/\u0010M\u001a\u00020E2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010NR+\u0010\f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R+\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R+\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\u001a8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b \u0010\u0012\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010#\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010\u0012\u001a\u0004\b$\u0010\u0016\"\u0004\b%\u0010\u0018R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010)\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b,\u0010\u0012\u001a\u0004\b*\u0010\u000e\"\u0004\b+\u0010\u0010R+\u0010-\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b0\u0010\u0012\u001a\u0004\b.\u0010\u000e\"\u0004\b/\u0010\u0010R\u000e\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R+\u00103\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b6\u0010\u0012\u001a\u0004\b4\u0010\u000e\"\u0004\b5\u0010\u0010R+\u00107\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010\u0012\u001a\u0004\b8\u0010\u0016\"\u0004\b9\u0010\u0018R+\u0010;\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\u001a8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b>\u0010\u0012\u001a\u0004\b<\u0010\u001d\"\u0004\b=\u0010\u001fR\u0010\u0010?\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010@\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bC\u0010\u0012\u001a\u0004\bA\u0010\u000e\"\u0004\bB\u0010\u0010¨\u0006P"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/widget/CBatteryView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "<set-?>", "borderColor", "getBorderColor", "()I", "setBorderColor", "(I)V", "borderColor$delegate", "Lkotlin/properties/ReadWriteProperty;", "", "borderCornersRadius", "getBorderCornersRadius", "()F", "setBorderCornersRadius", "(F)V", "borderCornersRadius$delegate", "Landroid/graphics/Paint;", "borderPaint", "getBorderPaint", "()Landroid/graphics/Paint;", "setBorderPaint", "(Landroid/graphics/Paint;)V", "borderPaint$delegate", "borderRect", "Landroid/graphics/RectF;", "borderStrokeWidth", "getBorderStrokeWidth", "setBorderStrokeWidth", "borderStrokeWidth$delegate", "chargingJob", "Lkotlinx/coroutines/Job;", "chargingPowerValue", "getChargingPowerValue", "setChargingPowerValue", "chargingPowerValue$delegate", "insideMargin", "getInsideMargin", "setInsideMargin", "insideMargin$delegate", "isCharging", "", "powerColor", "getPowerColor", "setPowerColor", "powerColor$delegate", "powerCornersRadius", "getPowerCornersRadius", "setPowerCornersRadius", "powerCornersRadius$delegate", "powerPaint", "getPowerPaint", "setPowerPaint", "powerPaint$delegate", "powerRect", "powerValue", "getPowerValue", "setPowerValue", "powerValue$delegate", "init", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "setCharging", "update", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CBatteryView extends View {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CBatteryView.class), "borderStrokeWidth", "getBorderStrokeWidth()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CBatteryView.class), "borderCornersRadius", "getBorderCornersRadius()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CBatteryView.class), "powerCornersRadius", "getPowerCornersRadius()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CBatteryView.class), "insideMargin", "getInsideMargin()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CBatteryView.class), "powerValue", "getPowerValue()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CBatteryView.class), "borderColor", "getBorderColor()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CBatteryView.class), "powerColor", "getPowerColor()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CBatteryView.class), "borderPaint", "getBorderPaint()Landroid/graphics/Paint;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CBatteryView.class), "powerPaint", "getPowerPaint()Landroid/graphics/Paint;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CBatteryView.class), "chargingPowerValue", "getChargingPowerValue()I"))};
    public static final int MAX_POWER_VALUE = 100;
    private HashMap _$_findViewCache;

    /* renamed from: borderColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty borderColor;

    /* renamed from: borderCornersRadius$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty borderCornersRadius;

    /* renamed from: borderPaint$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty borderPaint;
    private RectF borderRect;

    /* renamed from: borderStrokeWidth$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty borderStrokeWidth;
    private Job chargingJob;

    /* renamed from: chargingPowerValue$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty chargingPowerValue;

    /* renamed from: insideMargin$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty insideMargin;
    private boolean isCharging;

    /* renamed from: powerColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty powerColor;

    /* renamed from: powerCornersRadius$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty powerCornersRadius;

    /* renamed from: powerPaint$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty powerPaint;
    private RectF powerRect;

    /* renamed from: powerValue$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty powerValue;

    private final int getBorderColor() {
        return ((Number) this.borderColor.getValue(this, $$delegatedProperties[5])).intValue();
    }

    private final float getBorderCornersRadius() {
        return ((Number) this.borderCornersRadius.getValue(this, $$delegatedProperties[1])).floatValue();
    }

    private final Paint getBorderPaint() {
        return (Paint) this.borderPaint.getValue(this, $$delegatedProperties[7]);
    }

    private final float getBorderStrokeWidth() {
        return ((Number) this.borderStrokeWidth.getValue(this, $$delegatedProperties[0])).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getChargingPowerValue() {
        return ((Number) this.chargingPowerValue.getValue(this, $$delegatedProperties[9])).intValue();
    }

    private final int getInsideMargin() {
        return ((Number) this.insideMargin.getValue(this, $$delegatedProperties[3])).intValue();
    }

    private final int getPowerColor() {
        return ((Number) this.powerColor.getValue(this, $$delegatedProperties[6])).intValue();
    }

    private final float getPowerCornersRadius() {
        return ((Number) this.powerCornersRadius.getValue(this, $$delegatedProperties[2])).floatValue();
    }

    private final Paint getPowerPaint() {
        return (Paint) this.powerPaint.getValue(this, $$delegatedProperties[8]);
    }

    private final void setBorderColor(int i) {
        this.borderColor.setValue(this, $$delegatedProperties[5], Integer.valueOf(i));
    }

    private final void setBorderCornersRadius(float f) {
        this.borderCornersRadius.setValue(this, $$delegatedProperties[1], Float.valueOf(f));
    }

    private final void setBorderPaint(Paint paint) {
        this.borderPaint.setValue(this, $$delegatedProperties[7], paint);
    }

    private final void setBorderStrokeWidth(float f) {
        this.borderStrokeWidth.setValue(this, $$delegatedProperties[0], Float.valueOf(f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setChargingPowerValue(int i) {
        this.chargingPowerValue.setValue(this, $$delegatedProperties[9], Integer.valueOf(i));
    }

    private final void setInsideMargin(int i) {
        this.insideMargin.setValue(this, $$delegatedProperties[3], Integer.valueOf(i));
    }

    private final void setPowerColor(int i) {
        this.powerColor.setValue(this, $$delegatedProperties[6], Integer.valueOf(i));
    }

    private final void setPowerCornersRadius(float f) {
        this.powerCornersRadius.setValue(this, $$delegatedProperties[2], Float.valueOf(f));
    }

    private final void setPowerPaint(Paint paint) {
        this.powerPaint.setValue(this, $$delegatedProperties[8], paint);
    }

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

    public final int getPowerValue() {
        return ((Number) this.powerValue.getValue(this, $$delegatedProperties[4])).intValue();
    }

    public final void setPowerValue(int i) {
        this.powerValue.setValue(this, $$delegatedProperties[4], Integer.valueOf(i));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CBatteryView(Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CBatteryView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CBatteryView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.borderStrokeWidth = Delegates.INSTANCE.notNull();
        this.borderCornersRadius = Delegates.INSTANCE.notNull();
        this.powerCornersRadius = Delegates.INSTANCE.notNull();
        this.insideMargin = Delegates.INSTANCE.notNull();
        this.powerValue = Delegates.INSTANCE.notNull();
        this.borderColor = Delegates.INSTANCE.notNull();
        this.powerColor = Delegates.INSTANCE.notNull();
        this.borderPaint = Delegates.INSTANCE.notNull();
        this.powerPaint = Delegates.INSTANCE.notNull();
        this.chargingPowerValue = Delegates.INSTANCE.notNull();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5508R.styleable.CBatteryView, i, 0);
        setBorderStrokeWidth(obtainStyledAttributes.getDimensionPixelSize(C5508R.styleable.CBatteryView_cbv_borderStrokeWidth, DensityUtil.dip2px(context, 1.0f)));
        setBorderCornersRadius(obtainStyledAttributes.getDimensionPixelSize(C5508R.styleable.CBatteryView_cbv_borderCornersRadius, DensityUtil.dip2px(context, 3.0f)));
        setPowerCornersRadius(obtainStyledAttributes.getDimensionPixelSize(C5508R.styleable.CBatteryView_cbv_powerCornersRadius, DensityUtil.dip2px(context, 3.0f)));
        setInsideMargin(obtainStyledAttributes.getDimensionPixelSize(C5508R.styleable.CBatteryView_cbv_insideMargin, DensityUtil.dip2px(context, 1.0f)));
        setPowerValue(obtainStyledAttributes.getInt(C5508R.styleable.CBatteryView_cbv_powerValue, 100));
        if (getPowerValue() <= 0) {
            setPowerValue(0);
        } else if (getPowerValue() >= 100) {
            setPowerValue(100);
        }
        obtainStyledAttributes.recycle();
        init();
    }

    private final void init() {
        setBorderColor(ContextCompat.getColor(getContext(), C5508R.color.white));
        setPowerColor(ContextCompat.getColor(getContext(), C5508R.color.white));
        setBorderPaint(new Paint());
        getBorderPaint().setColor(getBorderColor());
        getBorderPaint().setAntiAlias(true);
        getBorderPaint().setStyle(Paint.Style.STROKE);
        getBorderPaint().setStrokeWidth(getBorderStrokeWidth());
        setPowerPaint(new Paint());
        getPowerPaint().setColor(getPowerColor());
        getPowerPaint().setAntiAlias(true);
        getPowerPaint().setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        if (this.borderRect == null) {
            this.borderRect = new RectF(getBorderStrokeWidth() / 2.0f, getBorderStrokeWidth() / 2.0f, getWidth() - (getBorderStrokeWidth() / 2.0f), getHeight() - (getBorderStrokeWidth() / 2.0f));
        }
        RectF rectF = this.borderRect;
        if (rectF == null) {
            Intrinsics.throwNpe();
        }
        canvas.drawRoundRect(rectF, getBorderCornersRadius(), getBorderCornersRadius(), getBorderPaint());
        if (this.powerRect == null) {
            this.powerRect = new RectF();
        }
        float width = getWidth() - ((getBorderStrokeWidth() + getInsideMargin()) * 2);
        if (this.isCharging) {
            RectF rectF2 = this.powerRect;
            if (rectF2 == null) {
                Intrinsics.throwNpe();
            }
            rectF2.set(getBorderStrokeWidth() + getInsideMargin(), getBorderStrokeWidth() + getInsideMargin(), getBorderStrokeWidth() + getInsideMargin() + (width * (getChargingPowerValue() / 100)), (getHeight() - getBorderStrokeWidth()) - getInsideMargin());
        } else {
            RectF rectF3 = this.powerRect;
            if (rectF3 == null) {
                Intrinsics.throwNpe();
            }
            rectF3.set(getBorderStrokeWidth() + getInsideMargin(), getBorderStrokeWidth() + getInsideMargin(), getBorderStrokeWidth() + getInsideMargin() + (width * (getPowerValue() / 100)), (getHeight() - getBorderStrokeWidth()) - getInsideMargin());
        }
        RectF rectF4 = this.powerRect;
        if (rectF4 == null) {
            Intrinsics.throwNpe();
        }
        canvas.drawRoundRect(rectF4, getPowerCornersRadius(), getPowerCornersRadius(), getPowerPaint());
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode == Integer.MIN_VALUE) {
            size = size + getPaddingStart() + getPaddingEnd();
        } else if (mode == 0) {
            size = RangesKt.coerceAtLeast(0, size);
        } else if (mode != 1073741824) {
            size = 0;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = size2 + getPaddingTop() + getPaddingBottom();
        } else if (mode2 == 0) {
            size2 = RangesKt.coerceAtLeast(0, size2);
        } else if (mode2 != 1073741824) {
            size2 = 0;
        }
        setMeasuredDimension(size, size2);
    }

    public static /* synthetic */ void update$default(CBatteryView cBatteryView, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 4) != 0) {
            num3 = (Integer) null;
        }
        cBatteryView.update(num, num2, num3);
    }

    public final void update(Integer borderColor, Integer powerColor, Integer powerValue) {
        if (borderColor != null) {
            int intValue = borderColor.intValue();
            setBorderColor(intValue);
            getBorderPaint().setColor(intValue);
        }
        if (powerColor != null) {
            int intValue2 = powerColor.intValue();
            setPowerColor(intValue2);
            getPowerPaint().setColor(intValue2);
        }
        if (powerValue != null) {
            setPowerValue(powerValue.intValue());
            if (getPowerValue() <= 0) {
                setPowerValue(0);
            } else if (getPowerValue() >= 100) {
                setPowerValue(100);
            }
        }
        postInvalidate();
    }

    public final void setCharging(boolean isCharging) {
        Job launch$default;
        this.isCharging = isCharging;
        setChargingPowerValue(getPowerValue());
        if (isCharging) {
            Job job = this.chargingJob;
            if (job != null) {
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                this.chargingJob = (Job) null;
            }
            launch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new CBatteryView$setCharging$1(this, null), 3, null);
            this.chargingJob = launch$default;
            return;
        }
        if (isCharging) {
            return;
        }
        Job job2 = this.chargingJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.chargingJob = (Job) null;
        postInvalidate();
    }

    /* renamed from: isCharging, reason: from getter */
    public final boolean getIsCharging() {
        return this.isCharging;
    }
}
