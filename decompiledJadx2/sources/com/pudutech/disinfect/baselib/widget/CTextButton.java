package com.pudutech.disinfect.baselib.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.C4429R;
import com.pudutech.disinfect.baselib.util.UiUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: CTextButton.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000k\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005*\u0001B\b\u0016\u0018\u0000 j2\u00020\u0001:\u0001jB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010X\u001a\u00020@H\u0002J\u0010\u0010Y\u001a\u00020@2\u0006\u0010Z\u001a\u00020[H\u0014J\b\u0010\\\u001a\u00020@H\u0014J\u0010\u0010]\u001a\u00020@2\b\b\u0001\u0010^\u001a\u00020\tJ\u0010\u0010_\u001a\u00020@2\u0006\u0010`\u001a\u00020aH\u0016J\u0018\u0010b\u001a\u00020@2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020fH\u0002JB\u0010g\u001a\u00020@2:\u0010h\u001a6\u0012\u0013\u0012\u00110:¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(=\u0012\u0013\u0012\u00110>¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(?\u0012\u0006\u0012\u0004\u0018\u00010@\u0018\u000109J\b\u0010i\u001a\u00020@H\u0002R+\u0010\f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R+\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R+\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u0012\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018R+\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\u0012\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010\u0018R+\u0010\"\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010\u0012\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010\u0010R+\u0010&\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b)\u0010\u0012\u001a\u0004\b'\u0010\u000e\"\u0004\b(\u0010\u0010R\u0012\u0010*\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010+R\u0012\u0010,\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010+R\u0012\u0010-\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010+R\u0012\u0010.\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010+R+\u0010/\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010\u0012\u001a\u0004\b0\u0010\u000e\"\u0004\b1\u0010\u0010R+\u00103\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b6\u0010\u0012\u001a\u0004\b4\u0010\u000e\"\u0004\b5\u0010\u0010R\u0012\u00107\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010+RB\u00108\u001a6\u0012\u0013\u0012\u00110:¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(=\u0012\u0013\u0012\u00110>¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(?\u0012\u0006\u0012\u0004\u0018\u00010@\u0018\u000109X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u00020BX\u0082\u000e¢\u0006\u0004\n\u0002\u0010CR+\u0010D\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bG\u0010\u0012\u001a\u0004\bE\u0010\u000e\"\u0004\bF\u0010\u0010R+\u0010H\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bK\u0010\u0012\u001a\u0004\bI\u0010\u000e\"\u0004\bJ\u0010\u0010R+\u0010L\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bO\u0010\u0012\u001a\u0004\bM\u0010\u000e\"\u0004\bN\u0010\u0010R+\u0010P\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bS\u0010\u0012\u001a\u0004\bQ\u0010\u000e\"\u0004\bR\u0010\u0010R+\u0010T\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bW\u0010\u0012\u001a\u0004\bU\u0010\u000e\"\u0004\bV\u0010\u0010¨\u0006k"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/widget/CTextButton;", "Landroidx/appcompat/widget/AppCompatTextView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "<set-?>", "cancelInterval", "getCancelInterval", "()I", "setCancelInterval", "(I)V", "cancelInterval$delegate", "Lkotlin/properties/ReadWriteProperty;", "", "cornerRadius", "getCornerRadius", "()F", "setCornerRadius", "(F)V", "cornerRadius$delegate", "dashGap", "getDashGap", "setDashGap", "dashGap$delegate", "dashWidth", "getDashWidth", "setDashWidth", "dashWidth$delegate", "disabledBackgroundColor", "getDisabledBackgroundColor", "setDisabledBackgroundColor", "disabledBackgroundColor$delegate", "disabledStrokeColor", "getDisabledStrokeColor", "setDisabledStrokeColor", "disabledStrokeColor$delegate", "disabledTextColor", "Ljava/lang/Integer;", "gradientAngle", "gradientEndColor", "gradientStartColor", "normalBackgroundColor", "getNormalBackgroundColor", "setNormalBackgroundColor", "normalBackgroundColor$delegate", "normalStrokeColor", "getNormalStrokeColor", "setNormalStrokeColor", "normalStrokeColor$delegate", "normalTextColor", "onCTouchListener", "Lkotlin/Function2;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "v", "Landroid/view/MotionEvent;", "event", "", "onTouchListener", "com/pudutech/disinfect/baselib/widget/CTextButton$onTouchListener$1", "Lcom/pudutech/disinfect/baselib/widget/CTextButton$onTouchListener$1;", "pressedBackgroundColor", "getPressedBackgroundColor", "setPressedBackgroundColor", "pressedBackgroundColor$delegate", "pressedStrokeColor", "getPressedStrokeColor", "setPressedStrokeColor", "pressedStrokeColor$delegate", "pressedTextColor", "getPressedTextColor", "setPressedTextColor", "pressedTextColor$delegate", "shape", "getShape", "setShape", "shape$delegate", "strokeWidth", "getStrokeWidth", "setStrokeWidth", "strokeWidth$delegate", "init", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onFinishInflate", "setBgDraw", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "setEnabled", "enabled", "", "setGradient", "normal", "Landroid/graphics/drawable/GradientDrawable;", "colors", "", "setOnCTouchListener", "listener", "updateBackgroundDrawable", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class CTextButton extends AppCompatTextView {
    private static final int BL_TR = 15;
    private static final int BOTTOM_TOP = 14;
    private static final int BR_TL = 13;
    private static final int LEFT_RIGHT = 16;
    private static final int LINE = 2;
    private static final int OVAL = 1;
    private static final int RECTANGLE = 0;
    private static final int RIGHT_LEFT = 12;
    private static final int RING = 3;
    private static final int TL_BR = 17;
    private static final int TOP_BOTTOM = 10;
    private static final int TR_BL = 11;
    private HashMap _$_findViewCache;

    /* renamed from: cancelInterval$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty cancelInterval;

    /* renamed from: cornerRadius$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty cornerRadius;

    /* renamed from: dashGap$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty dashGap;

    /* renamed from: dashWidth$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty dashWidth;

    /* renamed from: disabledBackgroundColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty disabledBackgroundColor;

    /* renamed from: disabledStrokeColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty disabledStrokeColor;
    private Integer disabledTextColor;
    private Integer gradientAngle;
    private Integer gradientEndColor;
    private Integer gradientStartColor;

    /* renamed from: normalBackgroundColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty normalBackgroundColor;

    /* renamed from: normalStrokeColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty normalStrokeColor;
    private Integer normalTextColor;
    private Function2<? super View, ? super MotionEvent, Unit> onCTouchListener;
    private CTextButton$onTouchListener$1 onTouchListener;

    /* renamed from: pressedBackgroundColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty pressedBackgroundColor;

    /* renamed from: pressedStrokeColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty pressedStrokeColor;

    /* renamed from: pressedTextColor$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty pressedTextColor;

    /* renamed from: shape$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty shape;

    /* renamed from: strokeWidth$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty strokeWidth;
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "normalBackgroundColor", "getNormalBackgroundColor()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "pressedBackgroundColor", "getPressedBackgroundColor()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "disabledBackgroundColor", "getDisabledBackgroundColor()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "pressedTextColor", "getPressedTextColor()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "normalStrokeColor", "getNormalStrokeColor()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "pressedStrokeColor", "getPressedStrokeColor()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "disabledStrokeColor", "getDisabledStrokeColor()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "strokeWidth", "getStrokeWidth()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "dashWidth", "getDashWidth()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "dashGap", "getDashGap()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "cornerRadius", "getCornerRadius()F")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "shape", "getShape()I")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CTextButton.class), "cancelInterval", "getCancelInterval()I"))};

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCancelInterval() {
        return ((Number) this.cancelInterval.getValue(this, $$delegatedProperties[12])).intValue();
    }

    private final float getCornerRadius() {
        return ((Number) this.cornerRadius.getValue(this, $$delegatedProperties[10])).floatValue();
    }

    private final float getDashGap() {
        return ((Number) this.dashGap.getValue(this, $$delegatedProperties[9])).floatValue();
    }

    private final float getDashWidth() {
        return ((Number) this.dashWidth.getValue(this, $$delegatedProperties[8])).floatValue();
    }

    private final int getDisabledBackgroundColor() {
        return ((Number) this.disabledBackgroundColor.getValue(this, $$delegatedProperties[2])).intValue();
    }

    private final int getDisabledStrokeColor() {
        return ((Number) this.disabledStrokeColor.getValue(this, $$delegatedProperties[6])).intValue();
    }

    private final int getNormalBackgroundColor() {
        return ((Number) this.normalBackgroundColor.getValue(this, $$delegatedProperties[0])).intValue();
    }

    private final int getNormalStrokeColor() {
        return ((Number) this.normalStrokeColor.getValue(this, $$delegatedProperties[4])).intValue();
    }

    private final int getPressedBackgroundColor() {
        return ((Number) this.pressedBackgroundColor.getValue(this, $$delegatedProperties[1])).intValue();
    }

    private final int getPressedStrokeColor() {
        return ((Number) this.pressedStrokeColor.getValue(this, $$delegatedProperties[5])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getPressedTextColor() {
        return ((Number) this.pressedTextColor.getValue(this, $$delegatedProperties[3])).intValue();
    }

    private final int getShape() {
        return ((Number) this.shape.getValue(this, $$delegatedProperties[11])).intValue();
    }

    private final int getStrokeWidth() {
        return ((Number) this.strokeWidth.getValue(this, $$delegatedProperties[7])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCancelInterval(int i) {
        this.cancelInterval.setValue(this, $$delegatedProperties[12], Integer.valueOf(i));
    }

    private final void setCornerRadius(float f) {
        this.cornerRadius.setValue(this, $$delegatedProperties[10], Float.valueOf(f));
    }

    private final void setDashGap(float f) {
        this.dashGap.setValue(this, $$delegatedProperties[9], Float.valueOf(f));
    }

    private final void setDashWidth(float f) {
        this.dashWidth.setValue(this, $$delegatedProperties[8], Float.valueOf(f));
    }

    private final void setDisabledBackgroundColor(int i) {
        this.disabledBackgroundColor.setValue(this, $$delegatedProperties[2], Integer.valueOf(i));
    }

    private final void setDisabledStrokeColor(int i) {
        this.disabledStrokeColor.setValue(this, $$delegatedProperties[6], Integer.valueOf(i));
    }

    private final void setNormalBackgroundColor(int i) {
        this.normalBackgroundColor.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    private final void setNormalStrokeColor(int i) {
        this.normalStrokeColor.setValue(this, $$delegatedProperties[4], Integer.valueOf(i));
    }

    private final void setPressedBackgroundColor(int i) {
        this.pressedBackgroundColor.setValue(this, $$delegatedProperties[1], Integer.valueOf(i));
    }

    private final void setPressedStrokeColor(int i) {
        this.pressedStrokeColor.setValue(this, $$delegatedProperties[5], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPressedTextColor(int i) {
        this.pressedTextColor.setValue(this, $$delegatedProperties[3], Integer.valueOf(i));
    }

    private final void setShape(int i) {
        this.shape.setValue(this, $$delegatedProperties[11], Integer.valueOf(i));
    }

    private final void setStrokeWidth(int i) {
        this.strokeWidth.setValue(this, $$delegatedProperties[7], Integer.valueOf(i));
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

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CTextButton(Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CTextButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v29, types: [com.pudutech.disinfect.baselib.widget.CTextButton$onTouchListener$1] */
    public CTextButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.normalBackgroundColor = Delegates.INSTANCE.notNull();
        this.pressedBackgroundColor = Delegates.INSTANCE.notNull();
        this.disabledBackgroundColor = Delegates.INSTANCE.notNull();
        this.pressedTextColor = Delegates.INSTANCE.notNull();
        this.normalStrokeColor = Delegates.INSTANCE.notNull();
        this.pressedStrokeColor = Delegates.INSTANCE.notNull();
        this.disabledStrokeColor = Delegates.INSTANCE.notNull();
        this.strokeWidth = Delegates.INSTANCE.notNull();
        this.dashWidth = Delegates.INSTANCE.notNull();
        this.dashGap = Delegates.INSTANCE.notNull();
        this.cornerRadius = Delegates.INSTANCE.notNull();
        this.shape = Delegates.INSTANCE.notNull();
        this.cancelInterval = Delegates.INSTANCE.notNull();
        setCancelInterval(UiUtils.dip2px(8.0f));
        this.onTouchListener = new View.OnTouchListener() { // from class: com.pudutech.disinfect.baselib.widget.CTextButton$onTouchListener$1
            /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
            
                if (r0 != 3) goto L29;
             */
            /* JADX WARN: Code restructure failed: missing block: B:24:0x0069, code lost:
            
                if (r2 > (r0 + r3)) goto L22;
             */
            @Override // android.view.View.OnTouchListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public boolean onTouch(View v, MotionEvent event) {
                int pressedTextColor;
                Function2 function2;
                Integer num;
                int cancelInterval;
                Integer num2;
                int cancelInterval2;
                int cancelInterval3;
                int cancelInterval4;
                Intrinsics.checkParameterIsNotNull(v, "v");
                Intrinsics.checkParameterIsNotNull(event, "event");
                if (!CTextButton.this.isEnabled()) {
                    return false;
                }
                int action = event.getAction();
                if (action != 0) {
                    if (action != 1) {
                        if (action == 2) {
                            float x = event.getX();
                            float y = event.getY();
                            cancelInterval = CTextButton.this.getCancelInterval();
                            if (x >= 0 - cancelInterval) {
                                int width = CTextButton.this.getWidth();
                                cancelInterval2 = CTextButton.this.getCancelInterval();
                                if (x <= width + cancelInterval2) {
                                    cancelInterval3 = CTextButton.this.getCancelInterval();
                                    if (y >= 0 - cancelInterval3) {
                                        int height = CTextButton.this.getHeight();
                                        cancelInterval4 = CTextButton.this.getCancelInterval();
                                    }
                                }
                            }
                            num2 = CTextButton.this.normalTextColor;
                            if (num2 != null) {
                                CTextButton.this.setTextColor(num2.intValue());
                            }
                        }
                    }
                    num = CTextButton.this.normalTextColor;
                    if (num != null) {
                        CTextButton.this.setTextColor(num.intValue());
                    }
                } else {
                    CTextButton cTextButton = CTextButton.this;
                    pressedTextColor = cTextButton.getPressedTextColor();
                    cTextButton.setTextColor(pressedTextColor);
                }
                function2 = CTextButton.this.onCTouchListener;
                if (function2 != null) {
                }
                return false;
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4429R.styleable.CTextButton, i, 0);
        setNormalBackgroundColor(obtainStyledAttributes.getColor(C4429R.styleable.CTextButton_ctb_normalBackgroundColor, Color.parseColor("#0072ff")));
        setPressedBackgroundColor(obtainStyledAttributes.getColor(C4429R.styleable.CTextButton_ctb_pressedBackgroundColor, Color.parseColor("#0a54bc")));
        setDisabledBackgroundColor(obtainStyledAttributes.getColor(C4429R.styleable.CTextButton_ctb_disabledBackgroundColor, Color.parseColor("#c9c9c9")));
        this.normalTextColor = Integer.valueOf(obtainStyledAttributes.getColor(C4429R.styleable.CTextButton_ctb_normalTextColor, Color.parseColor("#ffffff")));
        setPressedTextColor(obtainStyledAttributes.getColor(C4429R.styleable.CTextButton_ctb_pressedTextColor, Color.parseColor("#fefeff")));
        this.disabledTextColor = Integer.valueOf(obtainStyledAttributes.getColor(C4429R.styleable.CTextButton_ctb_disabledTextColor, Color.parseColor("#a7a7a7")));
        if (obtainStyledAttributes.hasValue(C4429R.styleable.CTextButton_ctb_gradient_end_color)) {
            this.gradientEndColor = Integer.valueOf(obtainStyledAttributes.getColor(C4429R.styleable.CTextButton_ctb_gradient_end_color, Color.parseColor("#ffffff")));
        }
        if (obtainStyledAttributes.hasValue(C4429R.styleable.CTextButton_ctb_gradient_start_color)) {
            this.gradientStartColor = Integer.valueOf(obtainStyledAttributes.getColor(C4429R.styleable.CTextButton_ctb_gradient_start_color, Color.parseColor("#ffffff")));
        }
        if (obtainStyledAttributes.hasValue(C4429R.styleable.CTextButton_ctb_gradient_orientation)) {
            this.gradientAngle = Integer.valueOf(obtainStyledAttributes.getInt(C4429R.styleable.CTextButton_ctb_gradient_orientation, 10));
        }
        setNormalStrokeColor(obtainStyledAttributes.getColor(C4429R.styleable.CTextButton_ctb_normalStrokeColor, -1));
        setPressedStrokeColor(obtainStyledAttributes.getColor(C4429R.styleable.CTextButton_ctb_pressedStrokeColor, -1));
        setDisabledStrokeColor(obtainStyledAttributes.getColor(C4429R.styleable.CTextButton_ctb_disabledStrokeColor, -1));
        setStrokeWidth(obtainStyledAttributes.getDimensionPixelSize(C4429R.styleable.CTextButton_ctb_strokeWidth, 0));
        setDashWidth(obtainStyledAttributes.getDimensionPixelSize(C4429R.styleable.CTextButton_ctb_dashWidth, 0));
        setDashGap(obtainStyledAttributes.getDimensionPixelSize(C4429R.styleable.CTextButton_ctb_dashGap, 0));
        setCornerRadius(obtainStyledAttributes.getDimensionPixelSize(C4429R.styleable.CTextButton_ctb_cornerRadius, UiUtils.dip2px(6.0f)));
        setShape(obtainStyledAttributes.getInt(C4429R.styleable.CTextButton_ctb_shape, 0));
        obtainStyledAttributes.recycle();
        init();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.normalTextColor == null || this.disabledTextColor == null) {
            return;
        }
        if (isEnabled()) {
            Integer num = this.normalTextColor;
            return;
        }
        Integer num2 = this.disabledTextColor;
        if (num2 != null) {
            setTextColor(num2.intValue());
        }
    }

    private final void init() {
        Integer num = this.normalTextColor;
        if (num != null) {
            setTextColor(num.intValue());
        }
        try {
            updateBackgroundDrawable();
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3273d("CTextButton", "updateBackgroundDrawable occur error is " + e.getMessage());
        }
        setOnTouchListener(this.onTouchListener);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawable;
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        Drawable[] compoundDrawables = getCompoundDrawables();
        Intrinsics.checkExpressionValueIsNotNull(compoundDrawables, "compoundDrawables");
        if ((!(compoundDrawables.length == 0)) && (drawable = compoundDrawables[0]) != null) {
            canvas.translate(((getWidth() - ((getPaint().measureText(getText().toString()) + drawable.getIntrinsicWidth()) + getCompoundDrawablePadding())) * 1.0f) / 2, 0.0f);
        }
        super.onDraw(canvas);
    }

    private final void updateBackgroundDrawable() {
        Integer num;
        GradientDrawable gradientDrawable = new GradientDrawable();
        if (this.gradientEndColor != null && (num = this.gradientStartColor) != null && this.gradientAngle != null) {
            int[] iArr = new int[2];
            if (num == null) {
                Intrinsics.throwNpe();
            }
            iArr[0] = num.intValue();
            Integer num2 = this.gradientEndColor;
            if (num2 == null) {
                Intrinsics.throwNpe();
            }
            iArr[1] = num2.intValue();
            setGradient(gradientDrawable, iArr);
        } else {
            gradientDrawable.setColor(getNormalBackgroundColor());
        }
        gradientDrawable.setCornerRadius(getCornerRadius());
        gradientDrawable.setStroke(getStrokeWidth(), getNormalStrokeColor(), getDashWidth(), getDashGap());
        gradientDrawable.setShape(getShape());
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadius(getCornerRadius());
        gradientDrawable2.setColor(getPressedBackgroundColor());
        gradientDrawable2.setStroke(getStrokeWidth(), getPressedStrokeColor(), getDashWidth(), getDashGap());
        gradientDrawable2.setShape(getShape());
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setCornerRadius(getCornerRadius());
        gradientDrawable3.setColor(getDisabledBackgroundColor());
        gradientDrawable3.setStroke(getStrokeWidth(), getDisabledStrokeColor(), getDashWidth(), getDashGap());
        gradientDrawable3.setShape(getShape());
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, gradientDrawable2);
        stateListDrawable.addState(new int[]{-16842910}, gradientDrawable3);
        stateListDrawable.addState(new int[0], gradientDrawable);
        setBackground(stateListDrawable);
    }

    public final void setBgDraw(int res) {
        setBackground(ContextCompat.getDrawable(getContext(), res));
    }

    private final void setGradient(GradientDrawable normal, int[] colors) {
        if (Build.VERSION.SDK_INT >= 16) {
            normal.setColors(colors);
            Integer num = this.gradientAngle;
            if (num != null && num.intValue() == 10) {
                normal.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                return;
            }
            if (num != null && num.intValue() == 11) {
                normal.setOrientation(GradientDrawable.Orientation.TR_BL);
                return;
            }
            if (num != null && num.intValue() == 12) {
                normal.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
                return;
            }
            if (num != null && num.intValue() == 13) {
                normal.setOrientation(GradientDrawable.Orientation.BR_TL);
                return;
            }
            if (num != null && num.intValue() == 14) {
                normal.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
                return;
            }
            if (num != null && num.intValue() == 15) {
                normal.setOrientation(GradientDrawable.Orientation.BL_TR);
                return;
            }
            if (num != null && num.intValue() == 16) {
                normal.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                return;
            } else {
                if (num != null && num.intValue() == 17) {
                    normal.setOrientation(GradientDrawable.Orientation.TL_BR);
                    return;
                }
                return;
            }
        }
        normal.setColor(colors[0]);
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean enabled) {
        Integer num;
        if (this.normalTextColor == null || (num = this.disabledTextColor) == null) {
            super.setEnabled(enabled);
            return;
        }
        if (!enabled && num != null) {
            setTextColor(num.intValue());
        }
        super.setEnabled(enabled);
    }

    public final void setOnCTouchListener(Function2<? super View, ? super MotionEvent, Unit> listener) {
        this.onCTouchListener = listener;
    }
}
