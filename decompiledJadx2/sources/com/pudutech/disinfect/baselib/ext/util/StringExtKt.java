package com.pudutech.disinfect.baselib.ext.util;

import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: StringExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\f\u0010\u0006\u001a\u00020\u0002*\u0004\u0018\u00010\u0007¨\u0006\b"}, m3961d2 = {"generateBarCode", "Landroid/graphics/Bitmap;", "", "width", "", "height", "toJson", "", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class StringExtKt {
    public static final String toJson(Object obj) {
        return GsonSingleton.INSTANCE.getINSTANCE().toJson(obj);
    }

    public static final Bitmap generateBarCode(String generateBarCode, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(generateBarCode, "$this$generateBarCode");
        BitMatrix encode = new Code128Writer().encode(generateBarCode, BarcodeFormat.CODE_128, i, i2);
        Bitmap mBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                mBitmap.setPixel(i3, i4, encode.get(i3, i4) ? ViewCompat.MEASURED_STATE_MASK : 0);
            }
        }
        Intrinsics.checkExpressionValueIsNotNull(mBitmap, "mBitmap");
        return mBitmap;
    }
}
