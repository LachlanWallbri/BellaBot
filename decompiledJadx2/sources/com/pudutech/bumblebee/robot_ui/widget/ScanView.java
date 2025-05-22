package com.pudutech.bumblebee.robot_ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.graphics.drawable.DrawableKt;
import com.pudutech.bumblebee.robot_ui.C4188R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScanView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 <2\u00020\u0001:\u0002<=B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\bH\u0002J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010*\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010+\u001a\u00020&2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020&H\u0002J\u0010\u0010/\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0014J\u0018\u00100\u001a\u00020&2\u0006\u00101\u001a\u00020\b2\u0006\u00102\u001a\u00020\bH\u0014J(\u00103\u001a\u00020&2\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020\b2\u0006\u00107\u001a\u00020\bH\u0014J\u0010\u00108\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020-H\u0016J\u001a\u00109\u001a\u00020&2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010:\u001a\u00020&2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0006\u0010;\u001a\u00020&R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000¨\u0006>"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/widget/ScanView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "centerIconHeight", "", "centerIconWidth", "centerX", "centerY", "innerRadius", "", "<set-?>", "", "isWaveRunning", "()Z", "listener", "Lcom/pudutech/bumblebee/robot_ui/widget/ScanView$OnCenterWaveClickListener;", "getListener", "()Lcom/pudutech/bumblebee/robot_ui/widget/ScanView$OnCenterWaveClickListener;", "setListener", "(Lcom/pudutech/bumblebee/robot_ui/widget/ScanView$OnCenterWaveClickListener;)V", "mHeight", "mWidth", "paint", "Landroid/graphics/Paint;", "radius", "waveCenterIcon", "Landroid/graphics/Bitmap;", "waveColor", "waveCount", "waveDegreeArr", "", "dp2Px", "dpValue", "drawCenterCircle", "", "canvas", "Landroid/graphics/Canvas;", "drawCenterIcon", "drawWave", "handleEvent", "event", "Landroid/view/MotionEvent;", "init", "onDraw", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onSizeChanged", "w", "h", "oldw", "oldh", "onTouchEvent", "readAttrs", "setOnCenterWaveClickListener", "toggle", "Companion", "OnCenterWaveClickListener", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ScanView extends View {
    private static final String TAG = "WaveView";
    private HashMap _$_findViewCache;
    private int centerIconHeight;
    private int centerIconWidth;
    private int centerX;
    private int centerY;
    private float innerRadius;
    private boolean isWaveRunning;
    private OnCenterWaveClickListener listener;
    private int mHeight;
    private int mWidth;
    private Paint paint;
    private float radius;
    private Bitmap waveCenterIcon;
    private int waveColor;
    private int waveCount;
    private float[] waveDegreeArr;

    /* compiled from: ScanView.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/widget/ScanView$OnCenterWaveClickListener;", "", "onCenterWaveClick", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface OnCenterWaveClickListener {
        void onCenterWaveClick();
    }

    public ScanView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
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

    public /* synthetic */ ScanView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? (AttributeSet) null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.isWaveRunning = true;
        readAttrs(context, attributeSet);
        init();
    }

    /* renamed from: isWaveRunning, reason: from getter */
    public final boolean getIsWaveRunning() {
        return this.isWaveRunning;
    }

    private final void init() {
        this.paint = new Paint(1);
        Paint paint = this.paint;
        if (paint == null) {
            Intrinsics.throwNpe();
        }
        paint.setColor(this.waveColor);
        Paint paint2 = this.paint;
        if (paint2 == null) {
            Intrinsics.throwNpe();
        }
        paint2.setStyle(Paint.Style.FILL);
        this.waveDegreeArr = new float[this.waveCount];
    }

    private final void readAttrs(Context context, AttributeSet attrs) {
        try {
            this.waveColor = (int) 4278216447L;
            this.waveCount = 3;
            Drawable drawable = context.getDrawable(C4188R.drawable.ic_setting_call2);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            Intrinsics.checkExpressionValueIsNotNull(drawable, "drawable");
            this.waveCenterIcon = DrawableKt.toBitmap$default(drawable, dp2Px(54), dp2Px(45), null, 4, null);
        } catch (Exception unused) {
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
        this.centerX = this.mWidth / 2;
        this.centerY = this.mHeight / 2;
        this.radius = Math.min(r3, r4) / 2.0f;
        Bitmap bitmap = this.waveCenterIcon;
        if (bitmap == null) {
            Intrinsics.throwNpe();
        }
        this.centerIconWidth = bitmap.getWidth();
        Bitmap bitmap2 = this.waveCenterIcon;
        if (bitmap2 == null) {
            Intrinsics.throwNpe();
        }
        this.centerIconHeight = bitmap2.getHeight();
        this.innerRadius = Math.max(this.centerIconWidth, this.centerIconHeight) * 1.2f;
        int i = this.waveCount;
        for (int i2 = 0; i2 < i; i2++) {
            float[] fArr = this.waveDegreeArr;
            if (fArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("waveDegreeArr");
            }
            float f = this.innerRadius;
            fArr[i2] = f + (((this.radius - f) / this.waveCount) * i2);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        if (mode == 0 || mode == Integer.MIN_VALUE) {
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(dp2Px(120), 1073741824);
        }
        if (mode2 == 0 || mode2 == Integer.MIN_VALUE) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(dp2Px(120), 1073741824);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        drawWave(canvas);
        drawCenterCircle(canvas);
        drawCenterIcon(canvas);
    }

    private final void drawCenterCircle(Canvas canvas) {
        canvas.drawCircle(this.centerX, this.centerY, this.innerRadius, this.paint);
    }

    private final void drawWave(Canvas canvas) {
        int i = this.waveCount;
        for (int i2 = 0; i2 < i; i2++) {
            Paint paint = this.paint;
            if (paint == null) {
                Intrinsics.throwNpe();
            }
            float f = 255;
            float[] fArr = this.waveDegreeArr;
            if (fArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("waveDegreeArr");
            }
            paint.setAlpha((int) (f - ((fArr[i2] * f) / this.radius)));
            float f2 = this.centerX;
            float f3 = this.centerY;
            float[] fArr2 = this.waveDegreeArr;
            if (fArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("waveDegreeArr");
            }
            canvas.drawCircle(f2, f3, fArr2[i2], this.paint);
        }
        float[] fArr3 = this.waveDegreeArr;
        if (fArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("waveDegreeArr");
        }
        int length = fArr3.length;
        for (int i3 = 0; i3 < length; i3++) {
            float[] fArr4 = this.waveDegreeArr;
            if (fArr4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("waveDegreeArr");
            }
            float[] fArr5 = this.waveDegreeArr;
            if (fArr5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("waveDegreeArr");
            }
            fArr4[i3] = fArr5[i3] + 4;
            float[] fArr6 = this.waveDegreeArr;
            if (fArr6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("waveDegreeArr");
            }
            if (fArr6[i3] > this.radius) {
                float[] fArr7 = this.waveDegreeArr;
                if (fArr7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("waveDegreeArr");
                }
                fArr7[i3] = this.innerRadius;
            }
        }
        if (this.isWaveRunning) {
            postInvalidateDelayed(50L);
        }
    }

    private final void drawCenterIcon(Canvas canvas) {
        Paint paint = this.paint;
        if (paint == null) {
            Intrinsics.throwNpe();
        }
        paint.setAlpha(255);
        canvas.drawBitmap(this.waveCenterIcon, this.centerX - (this.centerIconWidth / 2), this.centerY - (this.centerIconHeight / 2), this.paint);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getAction() != 1) {
            return true;
        }
        handleEvent(event);
        return true;
    }

    private final void handleEvent(MotionEvent event) {
        OnCenterWaveClickListener onCenterWaveClickListener;
        float x = event.getX();
        float y = event.getY();
        Log.i(TAG, "handleEvent: (" + x + ',' + y + ')');
        float abs = Math.abs(x - ((float) this.centerX));
        float abs2 = Math.abs(y - ((float) this.centerY));
        if (((float) Math.sqrt((abs * abs) + (abs2 * abs2))) >= this.innerRadius || (onCenterWaveClickListener = this.listener) == null) {
            return;
        }
        if (onCenterWaveClickListener == null) {
            Intrinsics.throwNpe();
        }
        onCenterWaveClickListener.onCenterWaveClick();
    }

    public final OnCenterWaveClickListener getListener() {
        return this.listener;
    }

    public final void setListener(OnCenterWaveClickListener onCenterWaveClickListener) {
        this.listener = onCenterWaveClickListener;
    }

    public final void setOnCenterWaveClickListener(OnCenterWaveClickListener listener) {
        this.listener = listener;
    }

    public final void toggle() {
        this.isWaveRunning = !this.isWaveRunning;
        invalidate();
    }

    private final int dp2Px(int dpValue) {
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return (int) TypedValue.applyDimension(1, dpValue, resources.getDisplayMetrics());
    }
}
