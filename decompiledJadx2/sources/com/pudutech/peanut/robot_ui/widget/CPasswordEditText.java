package com.pudutech.peanut.robot_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.pudutech.disinfect.baselib.util.DensityUtil;
import com.pudutech.peanut.robot_ui.C5508R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.anko.CustomViewPropertiesKt;

/* compiled from: CPasswordEditText.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010+\u001a\u00020,H\u0002J\u0010\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020/H\u0014J*\u00100\u001a\u00020,2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u00020\t2\u0006\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\tH\u0014R+\u0010\f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R+\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R+\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\u001a8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b \u0010\u0012\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR+\u0010!\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010\u0012\u001a\u0004\b\"\u0010\u0016\"\u0004\b#\u0010\u0018R+\u0010%\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010\u0012\u001a\u0004\b&\u0010\u000e\"\u0004\b'\u0010\u0010R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00066"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/widget/CPasswordEditText;", "Landroidx/appcompat/widget/AppCompatEditText;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "<set-?>", "dotColor", "getDotColor", "()I", "setDotColor", "(I)V", "dotColor$delegate", "Lkotlin/properties/ReadWriteProperty;", "", "dotInterval", "getDotInterval", "()F", "setDotInterval", "(F)V", "dotInterval$delegate", "Landroid/graphics/Paint;", "dotPaint", "getDotPaint", "()Landroid/graphics/Paint;", "setDotPaint", "(Landroid/graphics/Paint;)V", "dotPaint$delegate", "dotRadius", "getDotRadius", "setDotRadius", "dotRadius$delegate", "maxLength", "getMaxLength", "setMaxLength", "maxLength$delegate", CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "", "init", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onTextChanged", "text", "", "start", "lengthBefore", "lengthAfter", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CPasswordEditText extends AppCompatEditText {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CPasswordEditText.class), "maxLength", "getMaxLength()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CPasswordEditText.class), "dotColor", "getDotColor()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CPasswordEditText.class), "dotRadius", "getDotRadius()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CPasswordEditText.class), "dotInterval", "getDotInterval()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CPasswordEditText.class), "dotPaint", "getDotPaint()Landroid/graphics/Paint;"))};
    private HashMap _$_findViewCache;

    /* renamed from: dotColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty dotColor;

    /* renamed from: dotInterval$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty dotInterval;

    /* renamed from: dotPaint$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty dotPaint;

    /* renamed from: dotRadius$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty dotRadius;

    /* renamed from: maxLength$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty maxLength;
    private String password;

    private final int getDotColor() {
        return ((Number) this.dotColor.getValue(this, $$delegatedProperties[1])).intValue();
    }

    private final float getDotInterval() {
        return ((Number) this.dotInterval.getValue(this, $$delegatedProperties[3])).floatValue();
    }

    private final Paint getDotPaint() {
        return (Paint) this.dotPaint.getValue(this, $$delegatedProperties[4]);
    }

    private final float getDotRadius() {
        return ((Number) this.dotRadius.getValue(this, $$delegatedProperties[2])).floatValue();
    }

    private final void setDotColor(int i) {
        this.dotColor.setValue(this, $$delegatedProperties[1], Integer.valueOf(i));
    }

    private final void setDotInterval(float f) {
        this.dotInterval.setValue(this, $$delegatedProperties[3], Float.valueOf(f));
    }

    private final void setDotPaint(Paint paint) {
        this.dotPaint.setValue(this, $$delegatedProperties[4], paint);
    }

    private final void setDotRadius(float f) {
        this.dotRadius.setValue(this, $$delegatedProperties[2], Float.valueOf(f));
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

    public final int getMaxLength() {
        return ((Number) this.maxLength.getValue(this, $$delegatedProperties[0])).intValue();
    }

    public final void setMaxLength(int i) {
        this.maxLength.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CPasswordEditText(Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CPasswordEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CPasswordEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.maxLength = Delegates.INSTANCE.notNull();
        this.dotColor = Delegates.INSTANCE.notNull();
        this.dotRadius = Delegates.INSTANCE.notNull();
        this.dotInterval = Delegates.INSTANCE.notNull();
        this.dotPaint = Delegates.INSTANCE.notNull();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5508R.styleable.CPasswordEditText, i, 0);
        setMaxLength(obtainStyledAttributes.getInt(C5508R.styleable.CPasswordEditText_cpet_maxLength, 4));
        setDotColor(obtainStyledAttributes.getColor(C5508R.styleable.CPasswordEditText_cpet_dotColor, ContextCompat.getColor(context, C5508R.color.c_4d4d4d)));
        setDotRadius(obtainStyledAttributes.getDimension(C5508R.styleable.CPasswordEditText_cpet_dotRadius, DensityUtil.INSTANCE.dip2px(context, 12.0f)));
        setDotInterval(obtainStyledAttributes.getDimension(C5508R.styleable.CPasswordEditText_cpet_dotInterval, DensityUtil.INSTANCE.dip2px(context, 20.0f)));
        obtainStyledAttributes.recycle();
        init();
    }

    private final void init() {
        setFocusable(false);
        setFocusableInTouchMode(false);
        setTextColor(ContextCompat.getColor(getContext(), C5508R.color.transparent00));
        CustomViewPropertiesKt.setBackgroundDrawable(this, (Drawable) null);
        setLongClickable(false);
        setGravity(17);
        setTextIsSelectable(false);
        setCursorVisible(false);
        setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(getMaxLength())});
        setInputType(2);
        setDotPaint(new Paint());
        getDotPaint().setColor(getDotColor());
        getDotPaint().setAntiAlias(true);
        getDotPaint().setStyle(Paint.Style.FILL);
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        this.password = String.valueOf(text);
        invalidate();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        canvas.translate(getScrollX(), 0.0f);
        canvas.save();
        String str = this.password;
        int length = str != null ? str.length() : 0;
        float height = getHeight() / 2.0f;
        float f = 2;
        float width = ((getWidth() - (((getDotRadius() * 2.0f) * length) + (getDotInterval() * Math.max(0, length - 1)))) / f) + getDotRadius();
        for (int i = 0; i < length; i++) {
            canvas.drawCircle((i * ((getDotRadius() * f) + getDotInterval())) + width, height, getDotRadius(), getDotPaint());
        }
        canvas.restore();
    }
}
