package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.graphics.Bitmap;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Extensions.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003\u001a\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0007\u001a<\u0010\u0013\u001a\u00020\u0012\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00012\u0014\b\u0004\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00120\u0016H\u0086\b¢\u0006\u0002\u0010\u0017\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"2\u0010\u0005\u001a\u00020\u0001\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\u0006\u0010\u0004\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0018"}, m3961d2 = {"DEFAULT_CLICK_INTERVAL", "", "TAG_CLICK_EVENT", "", ES6Iterator.VALUE_PROPERTY, "lastClickTime", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "getLastClickTime", "(Landroid/view/View;)J", "setLastClickTime", "(Landroid/view/View;J)V", "generateBarCode", "Landroid/graphics/Bitmap;", "", "width", "height", "invisible", "", "singleClick", "delayMillisecond", "block", "Lkotlin/Function1;", "(Landroid/view/View;JLkotlin/jvm/functions/Function1;)V", "robot_ui_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ExtensionsKt {
    public static final long DEFAULT_CLICK_INTERVAL = 800;
    private static final int TAG_CLICK_EVENT = 1766613352;

    public static final <T extends View> void setLastClickTime(T lastClickTime, long j) {
        Intrinsics.checkParameterIsNotNull(lastClickTime, "$this$lastClickTime");
        lastClickTime.setTag(TAG_CLICK_EVENT, Long.valueOf(j));
    }

    public static final <T extends View> long getLastClickTime(T lastClickTime) {
        Intrinsics.checkParameterIsNotNull(lastClickTime, "$this$lastClickTime");
        Object tag = lastClickTime.getTag(TAG_CLICK_EVENT);
        if (!(tag instanceof Long)) {
            tag = null;
        }
        Long l = (Long) tag;
        if (l != null) {
            return l.longValue();
        }
        return 0L;
    }

    public static /* synthetic */ void singleClick$default(View singleClick, long j, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 800;
        }
        Intrinsics.checkParameterIsNotNull(singleClick, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(block, "block");
        singleClick.setOnClickListener(new ExtensionsKt$singleClick$1(singleClick, j, block));
    }

    public static final <T extends View> void singleClick(T singleClick, long j, Function1<? super T, Unit> block) {
        Intrinsics.checkParameterIsNotNull(singleClick, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(block, "block");
        singleClick.setOnClickListener(new ExtensionsKt$singleClick$1(singleClick, j, block));
    }

    public static final void invisible(View invisible) {
        Intrinsics.checkParameterIsNotNull(invisible, "$this$invisible");
        invisible.setVisibility(4);
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
