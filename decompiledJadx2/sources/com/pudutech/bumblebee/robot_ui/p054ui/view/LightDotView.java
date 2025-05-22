package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.loc.C3898x;
import com.pudutech.bumblebee.robot_ui.p054ui.view.LightDotView;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: LightDotView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0011\u0018\u00002\u00020\u0001:\u00012B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u0016H\u0002J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\"H\u0002J(\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u0016H\u0002J\u0018\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u0016H\u0002J\u0018\u0010+\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u0016H\u0002J\u0012\u0010,\u001a\u00020\u00162\b\b\u0002\u0010-\u001a\u00020\u0016H\u0002J\b\u0010.\u001a\u00020\u001bH\u0002J\b\u0010/\u001a\u00020\u001bH\u0002J\u0006\u00100\u001a\u00020\u001bJ\u0006\u00101\u001a\u00020\u001bR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/LightDotView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "animator", "Landroid/animation/ValueAnimator;", "bigCircleSize", "circleSize", "currentV", "density", "dotLists", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/ui/view/LightDotView$DotParam;", "Lkotlin/collections/ArrayList;", "maxDotSize", "", "minDotSize", "paint", "Landroid/graphics/Paint;", "draw", "", "canvas", "Landroid/graphics/Canvas;", "getAlpha", "x", "endR", "getCenterX", "", "getCenterY", "getDecelerateV", "fraction", "start", "end", "getStartDotMaxS", "s", C3898x.f4338g, "getStartDotMinS", "getStartR", "from", "initDotIfNeed", "initView", "startDotAnimation", "stopDotAnimation", "DotParam", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LightDotView extends View {
    private HashMap _$_findViewCache;
    private ValueAnimator animator;
    private final int bigCircleSize;
    private final int circleSize;
    private int currentV;
    private final int density;
    private final ArrayList<DotParam> dotLists;
    private final double maxDotSize;
    private final double minDotSize;
    private final Paint paint;

    private final double getDecelerateV(double fraction, double start, double end, double endR) {
        double d = 1.0d - fraction;
        return ((end - start) * (1.0d - (d * d)) * endR) + start;
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

    public LightDotView(Context context) {
        super(context);
        this.paint = new Paint(1);
        this.circleSize = DimensionsKt.XXHDPI;
        this.bigCircleSize = 700;
        this.minDotSize = 2.0d;
        this.maxDotSize = 11.0d;
        this.density = 45;
        this.dotLists = new ArrayList<>();
        initView();
    }

    public LightDotView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint(1);
        this.circleSize = DimensionsKt.XXHDPI;
        this.bigCircleSize = 700;
        this.minDotSize = 2.0d;
        this.maxDotSize = 11.0d;
        this.density = 45;
        this.dotLists = new ArrayList<>();
        initView();
    }

    public LightDotView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.paint = new Paint(1);
        this.circleSize = DimensionsKt.XXHDPI;
        this.bigCircleSize = 700;
        this.minDotSize = 2.0d;
        this.maxDotSize = 11.0d;
        this.density = 45;
        this.dotLists = new ArrayList<>();
        initView();
    }

    private final void initView() {
        this.paint.setColor(Color.parseColor("#0072ff"));
        initDotIfNeed();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (DotParam dotParam : this.dotLists) {
            double endR = dotParam.getEndR();
            double r = dotParam.getR();
            if (r >= 0.0d && r <= endR) {
                double decelerateV = getDecelerateV(dotParam.getR(), dotParam.getStartX(), dotParam.getEndX(), dotParam.getEndR()) + getCenterX();
                double decelerateV2 = getDecelerateV(dotParam.getR(), dotParam.getStartY(), dotParam.getEndY(), dotParam.getEndR()) + getCenterY();
                this.paint.setAlpha(255 - ((int) (getAlpha(dotParam.getR(), dotParam.getEndR()) * 255)));
                if (canvas != null) {
                    canvas.drawCircle((float) decelerateV, (float) decelerateV2, (float) getDecelerateV(dotParam.getR(), dotParam.getMinSize(), dotParam.getMaxSize(), dotParam.getEndR()), this.paint);
                }
            }
        }
    }

    private final double getAlpha(double x, double endR) {
        return Math.pow(x / endR, 2 * 1.4d);
    }

    private final void initDotIfNeed() {
        if (this.dotLists.isEmpty()) {
            int i = 360 / this.density;
            this.dotLists.clear();
            int i2 = this.density;
            for (int i3 = 0; i3 < i2; i3++) {
                double d = i * i3;
                double cos = this.circleSize * Math.cos(Math.toRadians(d));
                double sin = this.circleSize * Math.sin(Math.toRadians(d));
                double cos2 = this.bigCircleSize * Math.cos(Math.toRadians(d));
                double sin2 = this.bigCircleSize * Math.sin(Math.toRadians(d));
                double startDotMinS = getStartDotMinS(this.minDotSize, this.maxDotSize - 1);
                this.dotLists.add(new DotParam(cos, sin, cos2, sin2, getStartR(-2.5d), Random.INSTANCE.nextDouble(0.2d, 1.0d), getStartDotMaxS(startDotMinS, this.maxDotSize), startDotMinS));
            }
        }
    }

    public final void startDotAnimation() {
        if (this.animator != null) {
            stopDotAnimation();
        }
        Log.d("LightDotView", this.dotLists.toString());
        this.animator = ValueAnimator.ofInt(0, 30);
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.setDuration(1000L);
        }
        ValueAnimator valueAnimator2 = this.animator;
        if (valueAnimator2 != null) {
            valueAnimator2.setRepeatCount(-1);
        }
        ValueAnimator valueAnimator3 = this.animator;
        if (valueAnimator3 != null) {
            valueAnimator3.setInterpolator(new LinearInterpolator());
        }
        ValueAnimator valueAnimator4 = this.animator;
        if (valueAnimator4 != null) {
            valueAnimator4.setRepeatMode(1);
        }
        ValueAnimator valueAnimator5 = this.animator;
        if (valueAnimator5 != null) {
            valueAnimator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.LightDotView$startDotAnimation$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator animation) {
                    int i;
                    ArrayList<LightDotView.DotParam> arrayList;
                    ValueAnimator valueAnimator6;
                    double startR;
                    double d;
                    double d2;
                    double startDotMinS;
                    double d3;
                    double startDotMaxS;
                    Intrinsics.checkExpressionValueIsNotNull(animation, "animation");
                    Object animatedValue = animation.getAnimatedValue();
                    if (animatedValue != null) {
                        int intValue = ((Integer) animatedValue).intValue();
                        i = LightDotView.this.currentV;
                        if (i != intValue) {
                            arrayList = LightDotView.this.dotLists;
                            for (LightDotView.DotParam dotParam : arrayList) {
                                dotParam.setR(dotParam.getR() + 0.013d);
                                if (dotParam.getR() > 1.1d) {
                                    startR = LightDotView.this.getStartR(-1.6d);
                                    dotParam.setR(startR);
                                    LightDotView lightDotView = LightDotView.this;
                                    d = lightDotView.minDotSize;
                                    d2 = LightDotView.this.maxDotSize;
                                    startDotMinS = lightDotView.getStartDotMinS(d, d2 - 1);
                                    dotParam.setMinSize(startDotMinS);
                                    LightDotView lightDotView2 = LightDotView.this;
                                    double minSize = dotParam.getMinSize();
                                    d3 = LightDotView.this.maxDotSize;
                                    startDotMaxS = lightDotView2.getStartDotMaxS(minSize, d3);
                                    dotParam.setMaxSize(startDotMaxS);
                                    dotParam.setEndR(Random.INSTANCE.nextDouble(0.5d, 1.0d));
                                }
                            }
                            if (LightDotView.this.getContext() == null) {
                                valueAnimator6 = LightDotView.this.animator;
                                if (valueAnimator6 != null) {
                                    valueAnimator6.cancel();
                                }
                            } else {
                                LightDotView.this.invalidate();
                            }
                        }
                        LightDotView.this.currentV = intValue;
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
            });
        }
        ValueAnimator valueAnimator6 = this.animator;
        if (valueAnimator6 != null) {
            valueAnimator6.start();
        }
    }

    public final void stopDotAnimation() {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.animator = (ValueAnimator) null;
    }

    static /* synthetic */ double getStartR$default(LightDotView lightDotView, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            d = -0.7d;
        }
        return lightDotView.getStartR(d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double getStartR(double from) {
        return Random.INSTANCE.nextDouble(-0.7d, 0.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double getStartDotMinS(double s, double e) {
        return Random.INSTANCE.nextDouble(s, e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double getStartDotMaxS(double s, double e) {
        return Random.INSTANCE.nextDouble(s, e);
    }

    private final float getCenterX() {
        return getX() + (getWidth() / 2);
    }

    private final float getCenterY() {
        return getY() + (getHeight() / 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LightDotView.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003JY\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020-HÖ\u0001R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\r\"\u0004\b\u001d\u0010\u000f¨\u0006."}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/LightDotView$DotParam;", "", "startX", "", "startY", "endX", "endY", "r", "endR", "maxSize", "minSize", "(DDDDDDDD)V", "getEndR", "()D", "setEndR", "(D)V", "getEndX", "setEndX", "getEndY", "setEndY", "getMaxSize", "setMaxSize", "getMinSize", "setMinSize", "getR", "setR", "getStartX", "setStartX", "getStartY", "setStartY", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class DotParam {
        private double endR;
        private double endX;
        private double endY;
        private double maxSize;
        private double minSize;
        private double r;
        private double startX;
        private double startY;

        /* renamed from: component1, reason: from getter */
        public final double getStartX() {
            return this.startX;
        }

        /* renamed from: component2, reason: from getter */
        public final double getStartY() {
            return this.startY;
        }

        /* renamed from: component3, reason: from getter */
        public final double getEndX() {
            return this.endX;
        }

        /* renamed from: component4, reason: from getter */
        public final double getEndY() {
            return this.endY;
        }

        /* renamed from: component5, reason: from getter */
        public final double getR() {
            return this.r;
        }

        /* renamed from: component6, reason: from getter */
        public final double getEndR() {
            return this.endR;
        }

        /* renamed from: component7, reason: from getter */
        public final double getMaxSize() {
            return this.maxSize;
        }

        /* renamed from: component8, reason: from getter */
        public final double getMinSize() {
            return this.minSize;
        }

        public final DotParam copy(double startX, double startY, double endX, double endY, double r, double endR, double maxSize, double minSize) {
            return new DotParam(startX, startY, endX, endY, r, endR, maxSize, minSize);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DotParam)) {
                return false;
            }
            DotParam dotParam = (DotParam) other;
            return Double.compare(this.startX, dotParam.startX) == 0 && Double.compare(this.startY, dotParam.startY) == 0 && Double.compare(this.endX, dotParam.endX) == 0 && Double.compare(this.endY, dotParam.endY) == 0 && Double.compare(this.r, dotParam.r) == 0 && Double.compare(this.endR, dotParam.endR) == 0 && Double.compare(this.maxSize, dotParam.maxSize) == 0 && Double.compare(this.minSize, dotParam.minSize) == 0;
        }

        public int hashCode() {
            return (((((((((((((Double.hashCode(this.startX) * 31) + Double.hashCode(this.startY)) * 31) + Double.hashCode(this.endX)) * 31) + Double.hashCode(this.endY)) * 31) + Double.hashCode(this.r)) * 31) + Double.hashCode(this.endR)) * 31) + Double.hashCode(this.maxSize)) * 31) + Double.hashCode(this.minSize);
        }

        public String toString() {
            return "DotParam(startX=" + this.startX + ", startY=" + this.startY + ", endX=" + this.endX + ", endY=" + this.endY + ", r=" + this.r + ", endR=" + this.endR + ", maxSize=" + this.maxSize + ", minSize=" + this.minSize + ")";
        }

        public DotParam(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
            this.startX = d;
            this.startY = d2;
            this.endX = d3;
            this.endY = d4;
            this.r = d5;
            this.endR = d6;
            this.maxSize = d7;
            this.minSize = d8;
        }

        public final double getStartX() {
            return this.startX;
        }

        public final void setStartX(double d) {
            this.startX = d;
        }

        public final double getStartY() {
            return this.startY;
        }

        public final void setStartY(double d) {
            this.startY = d;
        }

        public final double getEndX() {
            return this.endX;
        }

        public final void setEndX(double d) {
            this.endX = d;
        }

        public final double getEndY() {
            return this.endY;
        }

        public final void setEndY(double d) {
            this.endY = d;
        }

        public final double getR() {
            return this.r;
        }

        public final void setR(double d) {
            this.r = d;
        }

        public final double getEndR() {
            return this.endR;
        }

        public final void setEndR(double d) {
            this.endR = d;
        }

        public final double getMaxSize() {
            return this.maxSize;
        }

        public final void setMaxSize(double d) {
            this.maxSize = d;
        }

        public final double getMinSize() {
            return this.minSize;
        }

        public final void setMinSize(double d) {
            this.minSize = d;
        }
    }
}
