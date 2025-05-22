package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.iflytek.cloud.msc.util.log.DebugLog;

/* renamed from: com.iflytek.cloud.thirdparty.av */
/* loaded from: classes3.dex */
public class C3710av extends View {

    /* renamed from: a */
    Path f3089a;

    /* renamed from: b */
    private Drawable f3090b;

    /* renamed from: c */
    private Drawable f3091c;

    /* renamed from: d */
    private PaintFlagsDrawFilter f3092d;

    public C3710av(Context context) {
        super(context);
        this.f3092d = new PaintFlagsDrawFilter(1, 2);
        try {
            this.f3090b = C3706ar.m1970a(getContext(), "voice_empty");
            this.f3091c = C3706ar.m1970a(getContext(), "voice_full");
            this.f3090b.setBounds(new Rect((-this.f3090b.getIntrinsicWidth()) / 2, (-this.f3090b.getIntrinsicHeight()) / 2, this.f3090b.getIntrinsicWidth() / 2, this.f3090b.getIntrinsicHeight() / 2));
            this.f3091c.setBounds(new Rect((-this.f3091c.getIntrinsicWidth()) / 2, (-this.f3091c.getIntrinsicHeight()) / 2, this.f3091c.getIntrinsicWidth() / 2, this.f3091c.getIntrinsicHeight() / 2));
            this.f3089a = new Path();
            setVolume(0);
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
    }

    public void setVolume(int i) {
        this.f3089a.reset();
        this.f3089a.addCircle(0.0f, 0.0f, (this.f3090b.getIntrinsicWidth() * i) / 12, Path.Direction.CCW);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        canvas.save();
        canvas.setDrawFilter(this.f3092d);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        this.f3090b.draw(canvas);
        canvas.clipPath(this.f3089a);
        this.f3091c.draw(canvas);
        canvas.restore();
    }

    public void finalize() throws Throwable {
        this.f3090b = null;
        this.f3091c = null;
        super.finalize();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        Drawable background = getBackground();
        if (background != null) {
            size = background.getMinimumWidth();
            size2 = background.getMinimumHeight();
        }
        setMeasuredDimension(resolveSize(size, i), resolveSize(size2, i2));
    }
}
