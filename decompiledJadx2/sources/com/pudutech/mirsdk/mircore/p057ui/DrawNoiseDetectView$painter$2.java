package com.pudutech.mirsdk.mircore.p057ui;

import android.graphics.Paint;
import androidx.core.internal.view.SupportMenu;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: DrawNoiseDetectView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "Landroid/graphics/Paint;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class DrawNoiseDetectView$painter$2 extends Lambda implements Function0<Paint> {
    public static final DrawNoiseDetectView$painter$2 INSTANCE = new DrawNoiseDetectView$painter$2();

    DrawNoiseDetectView$painter$2() {
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Paint invoke() {
        Paint paint = new Paint(4);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5.0f);
        paint.setColor(SupportMenu.CATEGORY_MASK);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        return paint;
    }
}
