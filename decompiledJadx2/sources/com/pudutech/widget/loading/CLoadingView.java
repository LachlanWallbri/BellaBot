package com.pudutech.widget.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* compiled from: CLoadingView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 72\u00020\u0001:\u00017B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020%H\u0002J\u0018\u0010&\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u0014H\u0002J\u0018\u0010(\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u0014H\u0002J\u0006\u0010)\u001a\u00020*J\b\u0010+\u001a\u00020*H\u0002J\u0010\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020.H\u0014J\u0018\u0010/\u001a\u00020*2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\tH\u0014J\u0006\u00102\u001a\u00020*J\u0010\u00103\u001a\u00020\t2\u0006\u00104\u001a\u00020%H\u0002J\b\u00105\u001a\u00020*H\u0002J\b\u00106\u001a\u00020*H\u0002R\"\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\r\u0018\u00010\fj\n\u0012\u0004\u0012\u00020\r\u0018\u0001`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000¨\u00068"}, m3961d2 = {"Lcom/pudutech/widget/loading/CLoadingView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "animators", "Ljava/util/ArrayList;", "Landroid/animation/ValueAnimator;", "Lkotlin/collections/ArrayList;", "indicatorAnimDuration", "indicatorAnimMode", "indicatorColor", "indicatorCount", "indicatorPaint", "Landroid/graphics/Paint;", "indicatorSize", "indicatorSpacing", "isShow", "", "scales", "", "text", "", "textColor", "textPaint", "textRect", "Landroid/graphics/Rect;", "textSize", "translateYs", "dp2Px", "dp", "", "getTextHeight", "paint", "getTextWidth", "hide", "", "init", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "show", "sp2px", "spVal", "startAnimators", "stopAnimators", "Companion", "widget_loading_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CLoadingView extends View {
    public static final int ANIM_MODE_HORIZONTAL = 0;
    public static final int ANIM_MODE_VERTICAL = 1;
    private HashMap _$_findViewCache;
    private ArrayList<ValueAnimator> animators;
    private int indicatorAnimDuration;
    private int indicatorAnimMode;
    private int indicatorColor;
    private int indicatorCount;
    private Paint indicatorPaint;
    private int indicatorSize;
    private int indicatorSpacing;
    private boolean isShow;
    private float[] scales;
    private String text;
    private int textColor;
    private Paint textPaint;
    private Rect textRect;
    private int textSize;
    private float[] translateYs;

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

    public static final /* synthetic */ float[] access$getScales$p(CLoadingView cLoadingView) {
        float[] fArr = cLoadingView.scales;
        if (fArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scales");
        }
        return fArr;
    }

    public static final /* synthetic */ float[] access$getTranslateYs$p(CLoadingView cLoadingView) {
        float[] fArr = cLoadingView.translateYs;
        if (fArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("translateYs");
        }
        return fArr;
    }

    public CLoadingView(Context context) {
        this(context, null);
    }

    public CLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int dp2Px;
        int dp2Px2;
        int sp2px;
        TypedArray obtainStyledAttributes = context != null ? context.obtainStyledAttributes(attributeSet, C5774R.styleable.CLoadingView) : null;
        this.indicatorCount = obtainStyledAttributes != null ? obtainStyledAttributes.getInt(C5774R.styleable.CLoadingView_clv_indicator_count, 3) : 3;
        if (obtainStyledAttributes != null) {
            dp2Px = obtainStyledAttributes.getDimensionPixelSize(C5774R.styleable.CLoadingView_clv_indicator_size, dp2Px(10.0f));
        } else {
            dp2Px = dp2Px(10.0f);
        }
        this.indicatorSize = dp2Px;
        this.indicatorColor = obtainStyledAttributes != null ? obtainStyledAttributes.getColor(C5774R.styleable.CLoadingView_clv_indicator_color, -16776961) : -16776961;
        if (obtainStyledAttributes != null) {
            dp2Px2 = obtainStyledAttributes.getDimensionPixelSize(C5774R.styleable.CLoadingView_clv_indicator_spacing, dp2Px(8.0f));
        } else {
            dp2Px2 = dp2Px(8.0f);
        }
        this.indicatorSpacing = dp2Px2;
        this.indicatorAnimMode = obtainStyledAttributes != null ? obtainStyledAttributes.getInt(C5774R.styleable.CLoadingView_clv_indicator_anim_mode, 0) : 0;
        this.indicatorAnimDuration = obtainStyledAttributes != null ? obtainStyledAttributes.getInt(C5774R.styleable.CLoadingView_clv_indicator_anim_duration, 1000) : 1000;
        this.text = obtainStyledAttributes != null ? obtainStyledAttributes.getString(C5774R.styleable.CLoadingView_clv_text) : null;
        if (obtainStyledAttributes != null) {
            sp2px = obtainStyledAttributes.getDimensionPixelOffset(C5774R.styleable.CLoadingView_clv_textSize, sp2px(14.0f));
        } else {
            sp2px = sp2px(14.0f);
        }
        this.textSize = sp2px;
        this.textColor = obtainStyledAttributes != null ? obtainStyledAttributes.getColor(C5774R.styleable.CLoadingView_clv_textColor, -16776961) : -16776961;
        if (obtainStyledAttributes != null) {
            obtainStyledAttributes.recycle();
        }
        init();
    }

    private final void init() {
        if (this.indicatorAnimMode == 1) {
            this.translateYs = new float[this.indicatorCount];
        } else {
            this.scales = new float[this.indicatorCount];
        }
        Paint paint = new Paint();
        this.indicatorPaint = paint;
        if (paint == null) {
            Intrinsics.throwUninitializedPropertyAccessException("indicatorPaint");
        }
        paint.setColor(this.indicatorColor);
        Paint paint2 = this.indicatorPaint;
        if (paint2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("indicatorPaint");
        }
        paint2.setStyle(Paint.Style.FILL);
        Paint paint3 = this.indicatorPaint;
        if (paint3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("indicatorPaint");
        }
        paint3.setAntiAlias(true);
        setVisibility(4);
        Paint paint4 = new Paint();
        this.textPaint = paint4;
        if (paint4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textPaint");
        }
        paint4.setColor(this.textColor);
        Paint paint5 = this.textPaint;
        if (paint5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textPaint");
        }
        paint5.setTextSize(this.textSize * 1.0f);
        Paint paint6 = this.textPaint;
        if (paint6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textPaint");
        }
        paint6.setStyle(Paint.Style.FILL);
        Paint paint7 = this.textPaint;
        if (paint7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textPaint");
        }
        paint7.setAntiAlias(true);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        if (this.isShow) {
            float f = this.indicatorSize / 2.0f;
            int i = this.indicatorCount;
            for (int i2 = 0; i2 < i; i2++) {
                canvas.save();
                if (this.indicatorAnimMode == 1) {
                    float f2 = ((this.indicatorSize + this.indicatorSpacing) * i2) + f;
                    float[] fArr = this.translateYs;
                    if (fArr == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("translateYs");
                    }
                    canvas.translate(f2, fArr[i2]);
                } else {
                    canvas.translate(((this.indicatorSize + this.indicatorSpacing) * i2) + (f * 2.0f), getHeight() / 2.0f);
                    float[] fArr2 = this.scales;
                    if (fArr2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("scales");
                    }
                    float f3 = fArr2[i2];
                    float[] fArr3 = this.scales;
                    if (fArr3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("scales");
                    }
                    canvas.scale(f3, fArr3[i2]);
                }
                float f4 = (this.indicatorSize / 2) * 1.0f;
                Paint paint = this.indicatorPaint;
                if (paint == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("indicatorPaint");
                }
                canvas.drawCircle(0.0f, 0.0f, f4, paint);
                canvas.restore();
            }
            if (TextUtils.isEmpty(this.text)) {
                return;
            }
            canvas.save();
            String str = this.text;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            Paint paint2 = this.textPaint;
            if (paint2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("textPaint");
            }
            int textWidth = getTextWidth(str, paint2);
            String str2 = this.text;
            if (str2 == null) {
                Intrinsics.throwNpe();
            }
            Paint paint3 = this.textPaint;
            if (paint3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("textPaint");
            }
            int textHeight = getTextHeight(str2, paint3);
            String str3 = this.text;
            float width = (getWidth() - textWidth) / 2.0f;
            float measuredHeight = getMeasuredHeight() - (textHeight / 4.0f);
            Paint paint4 = this.textPaint;
            if (paint4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("textPaint");
            }
            canvas.drawText(str3, width, measuredHeight, paint4);
            canvas.restore();
        }
    }

    private final int getTextWidth(String text, Paint paint) {
        if (this.textRect == null) {
            this.textRect = new Rect();
            paint.getTextBounds(text, 0, text.length(), this.textRect);
        }
        Rect rect = this.textRect;
        if (rect != null) {
            return rect.width();
        }
        return 0;
    }

    private final int getTextHeight(String text, Paint paint) {
        if (this.textRect == null) {
            this.textRect = new Rect();
            paint.getTextBounds(text, 0, text.length(), this.textRect);
        }
        Rect rect = this.textRect;
        if (rect != null) {
            return rect.height();
        }
        return 0;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode == Integer.MIN_VALUE) {
            int i = this.indicatorSize;
            int i2 = this.indicatorCount;
            size = (i * i2) + ((i2 - 1) * this.indicatorSpacing) + getPaddingLeft() + getPaddingRight();
        } else if (mode == 0) {
            size = RangesKt.coerceAtLeast(0, size);
        } else if (mode != 1073741824) {
            size = 0;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = this.indicatorSize + getPaddingTop() + getPaddingBottom();
        } else if (mode2 == 0) {
            size2 = RangesKt.coerceAtLeast(0, size2);
        } else if (mode2 != 1073741824) {
            size2 = 0;
        }
        if (this.indicatorAnimMode == 0) {
            size += this.indicatorSize;
        }
        int i3 = this.indicatorCount;
        for (int i4 = 0; i4 < i3; i4++) {
            if (this.indicatorAnimMode == 1) {
                float[] fArr = this.translateYs;
                if (fArr == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("translateYs");
                }
                fArr[i4] = size2 / 2.0f;
            } else {
                float[] fArr2 = this.scales;
                if (fArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("scales");
                }
                fArr2[i4] = 1.0f;
            }
        }
        setMeasuredDimension(size, size2);
    }

    public final void show() {
        if (this.isShow) {
            return;
        }
        this.isShow = true;
        postDelayed(new Runnable() { // from class: com.pudutech.widget.loading.CLoadingView$show$1
            @Override // java.lang.Runnable
            public final void run() {
                CLoadingView.this.setVisibility(0);
                CLoadingView.this.startAnimators();
                CLoadingView.this.postInvalidate();
            }
        }, 250L);
    }

    public final void hide() {
        if (this.isShow) {
            this.isShow = false;
            setVisibility(8);
            stopAnimators();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v17, types: [T, java.lang.Object, android.animation.ValueAnimator] */
    /* JADX WARN: Type inference failed for: r6v2, types: [T, java.lang.Object, android.animation.ValueAnimator] */
    public final void startAnimators() {
        if (this.animators == null) {
            this.animators = new ArrayList<>();
        }
        ArrayList<ValueAnimator> arrayList = this.animators;
        if (arrayList != null) {
            arrayList.clear();
        }
        int i = this.indicatorCount;
        long[] jArr = new long[i];
        for (int i2 = 0; i2 < i; i2++) {
            if (this.indicatorAnimMode == 1) {
                jArr[i2] = (long) (((this.indicatorAnimDuration * i2) / i) / 1.5d);
            } else {
                jArr[i2] = (long) (((this.indicatorAnimDuration * i2) / i) / 1.5d);
            }
        }
        int i3 = this.indicatorCount;
        for (final int i4 = 0; i4 < i3; i4++) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            if (this.indicatorAnimMode == 1) {
                ?? ofFloat = ValueAnimator.ofFloat(getHeight() / 2.0f, getHeight() / 3.5f, getHeight() / 2.0f);
                Intrinsics.checkExpressionValueIsNotNull(ofFloat, "ValueAnimator.ofFloat(he…ht / 3.5f, height / 2.0f)");
                objectRef.element = ofFloat;
                ((ValueAnimator) objectRef.element).setDuration(this.indicatorAnimDuration + 0);
                ((ValueAnimator) objectRef.element).setRepeatCount(-1);
                ((ValueAnimator) objectRef.element).setInterpolator(new OvershootInterpolator());
                ((ValueAnimator) objectRef.element).setStartDelay(jArr[i4]);
                ((ValueAnimator) objectRef.element).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.pudutech.widget.loading.CLoadingView$startAnimators$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float[] access$getTranslateYs$p = CLoadingView.access$getTranslateYs$p(CLoadingView.this);
                        int i5 = i4;
                        Object animatedValue = ((ValueAnimator) objectRef.element).getAnimatedValue();
                        if (animatedValue == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                        }
                        access$getTranslateYs$p[i5] = ((Float) animatedValue).floatValue();
                        CLoadingView.this.postInvalidate();
                    }
                });
            } else {
                ?? ofFloat2 = ValueAnimator.ofFloat(1.0f, 2.0f, 1.0f);
                Intrinsics.checkExpressionValueIsNotNull(ofFloat2, "ValueAnimator.ofFloat(1.0f, 2.0f, 1.0f)");
                objectRef.element = ofFloat2;
                ((ValueAnimator) objectRef.element).setDuration(this.indicatorAnimDuration + 0);
                ((ValueAnimator) objectRef.element).setRepeatCount(-1);
                ((ValueAnimator) objectRef.element).setInterpolator(new OvershootInterpolator());
                ((ValueAnimator) objectRef.element).setStartDelay(jArr[i4]);
                ((ValueAnimator) objectRef.element).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.pudutech.widget.loading.CLoadingView$startAnimators$2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float[] access$getScales$p = CLoadingView.access$getScales$p(CLoadingView.this);
                        int i5 = i4;
                        Object animatedValue = ((ValueAnimator) objectRef.element).getAnimatedValue();
                        if (animatedValue == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                        }
                        access$getScales$p[i5] = ((Float) animatedValue).floatValue();
                        CLoadingView.this.postInvalidate();
                    }
                });
            }
            ((ValueAnimator) objectRef.element).start();
            ArrayList<ValueAnimator> arrayList2 = this.animators;
            if (arrayList2 != null) {
                arrayList2.add((ValueAnimator) objectRef.element);
            }
        }
    }

    private final void stopAnimators() {
        ArrayList<ValueAnimator> arrayList = this.animators;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        ArrayList<ValueAnimator> arrayList2 = this.animators;
        if (arrayList2 == null) {
            Intrinsics.throwNpe();
        }
        Iterator<ValueAnimator> it = arrayList2.iterator();
        while (it.hasNext()) {
            ValueAnimator animator = it.next();
            Intrinsics.checkExpressionValueIsNotNull(animator, "animator");
            if (animator.isStarted()) {
                animator.removeAllUpdateListeners();
                animator.end();
            }
        }
    }

    private final int dp2Px(float dp) {
        Resources system = Resources.getSystem();
        Intrinsics.checkExpressionValueIsNotNull(system, "Resources.getSystem()");
        return (int) ((dp * system.getDisplayMetrics().density) + 0.5f);
    }

    private final int sp2px(float spVal) {
        Resources system = Resources.getSystem();
        Intrinsics.checkExpressionValueIsNotNull(system, "Resources.getSystem()");
        return (int) TypedValue.applyDimension(2, spVal, system.getDisplayMetrics());
    }
}
