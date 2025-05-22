package com.pudutech.mirsdk.mircore.p057ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: DrawNoiseDetectView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010&\u001a\u00020\u001b2\b\u0010'\u001a\u0004\u0018\u00010\u0014H\u0014J\u0006\u0010(\u001a\u00020\u001bJ\u001c\u0010)\u001a\u00020\u001b*\u00020!2\u0006\u0010*\u001a\u00020\t2\b\b\u0002\u0010+\u001a\u00020,R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001b\u0010 \u001a\u00020!8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#¨\u0006-"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/DrawNoiseDetectView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "bitmap", "Landroid/graphics/Bitmap;", "<set-?>", "", "isLayoutDrawDone", "()Z", "mCanvas", "Landroid/graphics/Canvas;", "getMCanvas", "()Landroid/graphics/Canvas;", "setMCanvas", "(Landroid/graphics/Canvas;)V", "onLayoutDrawDone", "Lkotlin/Function0;", "", "getOnLayoutDrawDone", "()Lkotlin/jvm/functions/Function0;", "setOnLayoutDrawDone", "(Lkotlin/jvm/functions/Function0;)V", "painter", "Landroid/graphics/Paint;", "getPainter", "()Landroid/graphics/Paint;", "painter$delegate", "Lkotlin/Lazy;", "onDraw", "canvas", "refreshScreen", "switch", TypedValues.Custom.S_COLOR, "width", "", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DrawNoiseDetectView extends View {
    private final String TAG;
    private HashMap _$_findViewCache;
    private Bitmap bitmap;
    private boolean isLayoutDrawDone;
    private Canvas mCanvas;
    private Function0<Unit> onLayoutDrawDone;

    /* renamed from: painter$delegate, reason: from kotlin metadata */
    private final Lazy painter;

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

    public final Paint getPainter() {
        return (Paint) this.painter.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DrawNoiseDetectView(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "DrawNoiseDetectView";
        this.painter = LazyKt.lazy(DrawNoiseDetectView$painter$2.INSTANCE);
        this.mCanvas = new Canvas();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DrawNoiseDetectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        this.TAG = "DrawNoiseDetectView";
        this.painter = LazyKt.lazy(DrawNoiseDetectView$painter$2.INSTANCE);
        this.mCanvas = new Canvas();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DrawNoiseDetectView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        this.TAG = "DrawNoiseDetectView";
        this.painter = LazyKt.lazy(DrawNoiseDetectView$painter$2.INSTANCE);
        this.mCanvas = new Canvas();
    }

    public static /* synthetic */ void switch$default(DrawNoiseDetectView drawNoiseDetectView, Paint paint, int i, float f, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f = 5.0f;
        }
        drawNoiseDetectView.m4461switch(paint, i, f);
    }

    /* renamed from: switch, reason: not valid java name */
    public final void m4461switch(Paint paint, int i, float f) {
        Intrinsics.checkParameterIsNotNull(paint, "$this$switch");
        paint.setColor(i);
        paint.setStrokeWidth(f);
    }

    public final Canvas getMCanvas() {
        return this.mCanvas;
    }

    public final void setMCanvas(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "<set-?>");
        this.mCanvas = canvas;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Pdlog.m3273d(this.TAG, "onDraw canvas=" + canvas + ' ' + getWidth() + ' ' + getHeight());
        if (this.bitmap == null) {
            this.bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            this.mCanvas.setBitmap(this.bitmap);
        }
        if (!this.isLayoutDrawDone) {
            this.isLayoutDrawDone = true;
            Function0<Unit> function0 = this.onLayoutDrawDone;
            if (function0 != null) {
                function0.invoke();
            }
        }
        if (canvas != null) {
            Bitmap bitmap = this.bitmap;
            if (bitmap == null) {
                Intrinsics.throwNpe();
            }
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, getPainter());
        }
    }

    public final void refreshScreen() {
        Pdlog.m3273d(this.TAG, "refreshScreen " + this.bitmap);
        invalidate();
    }

    /* renamed from: isLayoutDrawDone, reason: from getter */
    public final boolean getIsLayoutDrawDone() {
        return this.isLayoutDrawDone;
    }

    public final Function0<Unit> getOnLayoutDrawDone() {
        return this.onLayoutDrawDone;
    }

    public final void setOnLayoutDrawDone(Function0<Unit> function0) {
        this.onLayoutDrawDone = function0;
    }
}
