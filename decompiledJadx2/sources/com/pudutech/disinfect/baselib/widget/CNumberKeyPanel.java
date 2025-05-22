package com.pudutech.disinfect.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.C4429R;
import com.pudutech.disinfect.baselib.util.UiUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: CNumberKeyPanel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010G\u001a\u00020;J\u0018\u0010H\u001a\u00020;2\u0006\u0010I\u001a\u00020\t2\u0006\u0010J\u001a\u00020\tH\u0002J\u0018\u0010K\u001a\u00020;2\u0006\u0010L\u001a\u00020\t2\u0006\u0010J\u001a\u00020\tH\u0002J\u0010\u0010M\u001a\u00020;2\u0006\u0010N\u001a\u00020OH\u0002J\u0010\u0010P\u001a\u00020;2\u0006\u0010Q\u001a\u00020OH\u0002J\u0010\u0010R\u001a\u00020;2\u0006\u0010Q\u001a\u00020OH\u0002J\u0010\u0010S\u001a\u00020;2\u0006\u0010N\u001a\u00020OH\u0002J\b\u0010T\u001a\u00020$H\u0002J\u0018\u0010U\u001a\u00020;2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010V\u001a\u00020;2\b\u0010Q\u001a\u0004\u0018\u00010OH\u0014J\u0018\u0010W\u001a\u00020;2\u0006\u0010X\u001a\u00020\t2\u0006\u0010Y\u001a\u00020\tH\u0014J\u0012\u0010Z\u001a\u00020\"2\b\u0010[\u001a\u0004\u0018\u00010\\H\u0016R+\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u001b\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u0013\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010'\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010\u0013\u001a\u0004\b(\u0010\u000f\"\u0004\b)\u0010\u0011R\u001a\u0010+\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R+\u00100\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b3\u0010\u0013\u001a\u0004\b1\u0010\u000f\"\u0004\b2\u0010\u0011Ri\u00104\u001aQ\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(8\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(9\u0012\u0013\u0012\u00110\"¢\u0006\f\b6\u0012\b\b7\u0012\u0004\b\b(:\u0012\u0004\u0012\u00020;\u0018\u000105X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u000e\u0010@\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00109\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006]"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/widget/CNumberKeyPanel;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "<set-?>", "Landroid/graphics/Paint;", "bgPaint", "getBgPaint", "()Landroid/graphics/Paint;", "setBgPaint", "(Landroid/graphics/Paint;)V", "bgPaint$delegate", "Lkotlin/properties/ReadWriteProperty;", "bgRect", "Landroid/graphics/Rect;", "data", "", "", "delIcon", "Landroid/graphics/drawable/Drawable;", "delPaint", "getDelPaint", "setDelPaint", "delPaint$delegate", "gradientDrawable", "Landroid/graphics/drawable/GradientDrawable;", "isPress", "", "itemHeight", "", "itemWidth", "lineColor", "linePaint", "getLinePaint", "setLinePaint", "linePaint$delegate", "maxLength", "getMaxLength", "()I", "setMaxLength", "(I)V", "numPaint", "getNumPaint", "setNumPaint", "numPaint$delegate", "onItemClickListener", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "currentText", "sumText", "isDel", "", "getOnItemClickListener", "()Lkotlin/jvm/functions/Function3;", "setOnItemClickListener", "(Lkotlin/jvm/functions/Function3;)V", "onTouchPosition", "rect", "round", "selectColor", "textColor", "textData", "textSize", "clearAll", "dragPosition", "x", "y", "dragY", "i", "drawClickBg", "it", "Landroid/graphics/Canvas;", "drawDelIcon", "canvas", "drawNumLine", "drawNumText", "getTextLength", "initAttrs", "onDraw", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CNumberKeyPanel extends View {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CNumberKeyPanel.class), "bgPaint", "getBgPaint()Landroid/graphics/Paint;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CNumberKeyPanel.class), "linePaint", "getLinePaint()Landroid/graphics/Paint;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CNumberKeyPanel.class), "numPaint", "getNumPaint()Landroid/graphics/Paint;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(CNumberKeyPanel.class), "delPaint", "getDelPaint()Landroid/graphics/Paint;"))};
    private HashMap _$_findViewCache;

    /* renamed from: bgPaint$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty bgPaint;
    private final Rect bgRect;
    private final List<String> data;
    private Drawable delIcon;

    /* renamed from: delPaint$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty delPaint;
    private final GradientDrawable gradientDrawable;
    private boolean isPress;
    private float itemHeight;
    private float itemWidth;
    private int lineColor;

    /* renamed from: linePaint$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty linePaint;
    private int maxLength;

    /* renamed from: numPaint$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty numPaint;
    private Function3<? super String, ? super List<String>, ? super Boolean, Unit> onItemClickListener;
    private int onTouchPosition;
    private final Rect rect;
    private final float round;
    private int selectColor;
    private List<String> sumText;
    private int textColor;
    private String textData;
    private float textSize;

    private final Paint getBgPaint() {
        return (Paint) this.bgPaint.getValue(this, $$delegatedProperties[0]);
    }

    private final Paint getDelPaint() {
        return (Paint) this.delPaint.getValue(this, $$delegatedProperties[3]);
    }

    private final Paint getLinePaint() {
        return (Paint) this.linePaint.getValue(this, $$delegatedProperties[1]);
    }

    private final Paint getNumPaint() {
        return (Paint) this.numPaint.getValue(this, $$delegatedProperties[2]);
    }

    private final void setBgPaint(Paint paint) {
        this.bgPaint.setValue(this, $$delegatedProperties[0], paint);
    }

    private final void setDelPaint(Paint paint) {
        this.delPaint.setValue(this, $$delegatedProperties[3], paint);
    }

    private final void setLinePaint(Paint paint) {
        this.linePaint.setValue(this, $$delegatedProperties[1], paint);
    }

    private final void setNumPaint(Paint paint) {
        this.numPaint.setValue(this, $$delegatedProperties[2], paint);
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CNumberKeyPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.textColor = -1;
        this.textSize = UiUtils.px2sp(30.0f);
        this.textData = "1,2,3,4,5,6,7,8,9,0";
        this.lineColor = ViewCompat.MEASURED_STATE_MASK;
        this.selectColor = Color.parseColor("#252628");
        this.itemWidth = UiUtils.px2sp(30.0f);
        this.itemHeight = UiUtils.px2sp(20.0f);
        this.data = new ArrayList();
        this.onTouchPosition = -3;
        this.sumText = new ArrayList();
        this.round = UiUtils.dip2px(12.0f);
        this.maxLength = 12;
        this.bgPaint = Delegates.INSTANCE.notNull();
        this.linePaint = Delegates.INSTANCE.notNull();
        this.numPaint = Delegates.INSTANCE.notNull();
        this.delPaint = Delegates.INSTANCE.notNull();
        setBgPaint(new Paint(1));
        setLinePaint(new Paint(1));
        setNumPaint(new Paint(1));
        setDelPaint(new Paint(1));
        this.gradientDrawable = new GradientDrawable();
        this.bgRect = new Rect();
        this.rect = new Rect();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CNumberKeyPanel(Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CNumberKeyPanel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (attributeSet != null) {
            initAttrs(context, attributeSet);
        }
    }

    public final int getMaxLength() {
        return this.maxLength;
    }

    public final void setMaxLength(int i) {
        this.maxLength = i;
    }

    private final void initAttrs(Context context, AttributeSet attrs) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, C4429R.styleable.CNumberKeyPanel);
        this.textColor = obtainStyledAttributes.getColor(C4429R.styleable.CNumberKeyPanel_num_text_color, this.textColor);
        this.textSize = obtainStyledAttributes.getDimension(C4429R.styleable.CNumberKeyPanel_num_text_size, this.textSize);
        if (obtainStyledAttributes.hasValue(C4429R.styleable.CNumberKeyPanel_text_data)) {
            String string = obtainStyledAttributes.getString(C4429R.styleable.CNumberKeyPanel_text_data);
            if (string == null) {
                string = this.textData;
            }
            this.textData = string;
        }
        this.lineColor = obtainStyledAttributes.getColor(C4429R.styleable.CNumberKeyPanel_line_color, this.lineColor);
        this.selectColor = obtainStyledAttributes.getColor(C4429R.styleable.CNumberKeyPanel_select_bg_color, this.selectColor);
        this.delIcon = obtainStyledAttributes.getDrawable(C4429R.styleable.CNumberKeyPanel_del_src);
        obtainStyledAttributes.recycle();
        this.data.clear();
        Iterator it = StringsKt.split$default((CharSequence) this.textData, new String[]{","}, false, 0, 6, (Object) null).iterator();
        while (it.hasNext()) {
            this.data.add((String) it.next());
        }
        getLinePaint().setColor(this.lineColor);
        getNumPaint().setColor(this.textColor);
        getNumPaint().setTextSize(this.textSize);
        getNumPaint().setTextAlign(Paint.Align.CENTER);
        getBgPaint().setColor(this.selectColor);
        this.gradientDrawable.setShape(0);
        this.gradientDrawable.setColor(this.selectColor);
        setClickable(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x001d, code lost:
    
        if (r0 != 1073741824) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0043, code lost:
    
        if (r1 != 1073741824) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0041  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float f;
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                size = (int) (this.itemWidth * 3);
            }
            if (mode2 != Integer.MIN_VALUE) {
                if (mode2 == 0) {
                    size2 = (int) (this.itemHeight * 4);
                }
                setMeasuredDimension(size, size2);
            }
            f = size2;
            if (f >= this.itemHeight * 4) {
                Pdlog.m3274e("CNumber", "请给与更大的高度或者缩小每项高度");
            } else {
                this.itemHeight = f / 4.0f;
            }
            setMeasuredDimension(size, size2);
        }
        float f2 = size;
        if (f2 < this.itemWidth * 3) {
            Pdlog.m3274e("CNumber", "请给与更大的宽度或者缩小每项宽度");
        } else {
            this.itemWidth = f2 / 3.0f;
        }
        if (mode2 != Integer.MIN_VALUE) {
        }
        f = size2;
        if (f >= this.itemHeight * 4) {
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (canvas != null) {
            drawClickBg(canvas);
            drawNumLine(canvas);
            drawDelIcon(canvas);
            drawNumText(canvas);
        }
    }

    private final void drawClickBg(Canvas it) {
        if (this.onTouchPosition == -2) {
            return;
        }
        if (this.isPress) {
            this.gradientDrawable.setColor(this.selectColor);
            getBgPaint().setColor(this.selectColor);
        } else {
            this.gradientDrawable.setColor(0);
            getBgPaint().setColor(0);
        }
        int i = this.onTouchPosition;
        if (i >= 0 && 2 >= i) {
            Rect rect = this.bgRect;
            float f = this.itemWidth;
            rect.left = ((int) f) * i;
            rect.top = 0;
            rect.right = (i + 1) * ((int) f);
            rect.bottom = (int) this.itemHeight;
            if (i == 0) {
                GradientDrawable gradientDrawable = this.gradientDrawable;
                float f2 = this.round;
                gradientDrawable.setCornerRadii(new float[]{f2, f2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});
                this.gradientDrawable.setBounds(this.bgRect);
                this.gradientDrawable.draw(it);
                return;
            }
            if (i == 2) {
                GradientDrawable gradientDrawable2 = this.gradientDrawable;
                float f3 = this.round;
                gradientDrawable2.setCornerRadii(new float[]{0.0f, 0.0f, f3, f3, 0.0f, 0.0f, 0.0f, 0.0f});
                this.gradientDrawable.setBounds(this.bgRect);
                this.gradientDrawable.draw(it);
                return;
            }
        } else if (3 <= i && 5 >= i) {
            Rect rect2 = this.bgRect;
            int i2 = this.onTouchPosition;
            float f4 = this.itemWidth;
            rect2.left = (i2 - 3) * ((int) f4);
            float f5 = this.itemHeight;
            rect2.top = (int) f5;
            rect2.right = (i2 - 2) * ((int) f4);
            rect2.bottom = ((int) f5) * 2;
        } else if (6 <= i && 8 >= i) {
            Rect rect3 = this.bgRect;
            int i3 = this.onTouchPosition;
            float f6 = this.itemWidth;
            rect3.left = (i3 - 6) * ((int) f6);
            float f7 = this.itemHeight;
            rect3.top = ((int) f7) * 2;
            rect3.right = (i3 - 5) * ((int) f6);
            rect3.bottom = ((int) f7) * 3;
        } else {
            if (i == 9) {
                Rect rect4 = this.bgRect;
                rect4.left = 0;
                rect4.top = ((int) this.itemHeight) * 3;
                rect4.right = (int) this.itemWidth;
                rect4.bottom = getHeight();
                GradientDrawable gradientDrawable3 = this.gradientDrawable;
                float f8 = this.round;
                gradientDrawable3.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f8, f8});
                this.gradientDrawable.setBounds(this.bgRect);
                this.gradientDrawable.draw(it);
                return;
            }
            if (i == -1) {
                Rect rect5 = this.bgRect;
                rect5.left = (int) this.itemWidth;
                rect5.top = ((int) this.itemHeight) * 3;
                rect5.right = getWidth();
                this.bgRect.bottom = getHeight();
                GradientDrawable gradientDrawable4 = this.gradientDrawable;
                float f9 = this.round;
                gradientDrawable4.setCornerRadii(new float[]{0.0f, 0.0f, 0.0f, 0.0f, f9, f9, 0.0f, 0.0f});
                this.gradientDrawable.setBounds(this.bgRect);
                this.gradientDrawable.draw(it);
                return;
            }
        }
        it.drawRect(this.bgRect, getBgPaint());
    }

    private final void drawDelIcon(Canvas canvas) {
        Drawable drawable = this.delIcon;
        if (drawable != null) {
            Rect rect = this.rect;
            float f = this.itemWidth;
            float f2 = 2;
            float f3 = 44;
            rect.left = (int) ((f * f2) - f3);
            rect.right = (int) ((f * f2) + f3);
            rect.top = (int) ((getHeight() - (this.itemHeight / f2)) - f3);
            this.rect.bottom = (int) ((getHeight() - (this.itemHeight / f2)) + f3);
            drawable.setBounds(this.rect);
            drawable.draw(canvas);
        }
    }

    private final void drawNumText(Canvas it) {
        if (this.data.isEmpty()) {
            return;
        }
        int i = 0;
        for (String str : this.data) {
            if (i >= 0 && 2 >= i) {
                float f = this.itemWidth;
                float f2 = 2;
                it.drawText(str, (i * f) + (f / f2), (this.itemHeight / f2) + getTextLength(), getNumPaint());
            } else if (3 <= i && 5 >= i) {
                float f3 = this.itemWidth;
                float f4 = 2;
                float f5 = ((i - 3) * f3) + (f3 / f4);
                float f6 = this.itemHeight;
                it.drawText(str, f5, f6 + (f6 / f4) + getTextLength(), getNumPaint());
            } else if (6 <= i && 8 >= i) {
                float f7 = this.itemWidth;
                float f8 = 2;
                float f9 = ((i - 6) * f7) + (f7 / f8);
                float f10 = this.itemHeight;
                it.drawText(str, f9, (f8 * f10) + (f10 / f8) + getTextLength(), getNumPaint());
            } else if (i == 9) {
                float f11 = 2;
                float f12 = this.itemWidth / f11;
                float f13 = this.itemHeight;
                it.drawText(str, f12, ((4 * f13) - (f13 / f11)) + getTextLength(), getNumPaint());
            }
            i++;
        }
    }

    private final float getTextLength() {
        Paint.FontMetrics fontMetrics = getNumPaint().getFontMetrics();
        Intrinsics.checkExpressionValueIsNotNull(fontMetrics, "numPaint.getFontMetrics()");
        return ((fontMetrics.bottom - fontMetrics.top) / 2) - fontMetrics.bottom;
    }

    private final void drawNumLine(Canvas canvas) {
        for (int i = 1; i <= 2; i++) {
            if (i == 1) {
                float f = this.itemWidth;
                float f2 = i;
                canvas.drawLine(f * f2, 0.0f, f * f2, getHeight(), getLinePaint());
            } else {
                float f3 = this.itemWidth;
                float f4 = i;
                canvas.drawLine(f3 * f4, 0.0f, f3 * f4, getHeight() - this.itemHeight, getLinePaint());
            }
        }
        for (int i2 = 1; i2 <= 3; i2++) {
            float f5 = i2;
            canvas.drawLine(0.0f, this.itemHeight * f5, getWidth(), this.itemHeight * f5, getLinePaint());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int x = event != null ? (int) event.getX() : 0;
        int y = event != null ? (int) event.getY() : 0;
        Integer valueOf = event != null ? Integer.valueOf(event.getAction()) : null;
        if (valueOf != null && valueOf.intValue() == 0) {
            this.isPress = true;
            dragPosition(x, y);
            invalidate();
            Function3<? super String, ? super List<String>, ? super Boolean, Unit> function3 = this.onItemClickListener;
            if (function3 != null) {
                int i = this.onTouchPosition;
                function3.invoke(i >= 0 ? this.data.get(i) : null, this.sumText, Boolean.valueOf(this.onTouchPosition == -1));
            }
        } else if (valueOf != null && valueOf.intValue() == 1) {
            this.isPress = false;
            invalidate();
        }
        return true;
    }

    public final Function3<String, List<String>, Boolean, Unit> getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(Function3<? super String, ? super List<String>, ? super Boolean, Unit> function3) {
        this.onItemClickListener = function3;
    }

    private final void dragPosition(int x, int y) {
        int i = (int) this.itemWidth;
        if (x >= 0 && i > x) {
            dragY(1, y);
        } else {
            float f = this.itemWidth;
            int i2 = (int) f;
            int i3 = ((int) f) * 2;
            if (i2 <= x && i3 > x) {
                dragY(2, y);
            } else {
                float f2 = this.itemWidth;
                int i4 = (int) f2;
                int i5 = ((int) f2) * 3;
                if (i4 <= x && i5 > x) {
                    dragY(3, y);
                }
            }
        }
        if (this.onTouchPosition >= 0 && this.sumText.size() < this.maxLength) {
            this.sumText.add(this.data.get(this.onTouchPosition));
        } else if (this.onTouchPosition == -1 && (!this.sumText.isEmpty())) {
            List<String> list = this.sumText;
            list.remove(CollectionsKt.getLastIndex(list));
        }
    }

    private final void dragY(int i, int y) {
        int i2 = (int) this.itemHeight;
        if (y >= 0 && i2 > y) {
            if (i == 1) {
                this.onTouchPosition = 0;
                return;
            } else if (i == 2) {
                this.onTouchPosition = 1;
                return;
            } else {
                if (i != 3) {
                    return;
                }
                this.onTouchPosition = 2;
                return;
            }
        }
        float f = this.itemHeight;
        int i3 = (int) f;
        int i4 = ((int) f) * 2;
        if (i3 <= y && i4 > y) {
            if (i == 1) {
                this.onTouchPosition = 3;
                return;
            } else if (i == 2) {
                this.onTouchPosition = 4;
                return;
            } else {
                if (i != 3) {
                    return;
                }
                this.onTouchPosition = 5;
                return;
            }
        }
        float f2 = this.itemHeight;
        int i5 = (int) f2;
        int i6 = ((int) f2) * 3;
        if (i5 <= y && i6 > y) {
            if (i == 1) {
                this.onTouchPosition = 6;
                return;
            } else if (i == 2) {
                this.onTouchPosition = 7;
                return;
            } else {
                if (i != 3) {
                    return;
                }
                this.onTouchPosition = 8;
                return;
            }
        }
        float f3 = this.itemHeight;
        int i7 = (int) f3;
        int i8 = ((int) f3) * 4;
        if (i7 <= y && i8 > y) {
            if (i == 1) {
                this.onTouchPosition = 9;
            } else if (i == 2 || i == 3) {
                this.onTouchPosition = -1;
            }
        }
    }

    public final void clearAll() {
        this.sumText.clear();
        this.onTouchPosition = -3;
    }
}
