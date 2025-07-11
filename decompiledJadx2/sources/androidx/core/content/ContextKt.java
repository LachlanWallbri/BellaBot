package androidx.core.content;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: Context.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001aN\u0010\u0005\u001a\u00020\u0006*\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\f2\b\b\u0003\u0010\r\u001a\u00020\f2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000f¢\u0006\u0002\b\u0011H\u0086\b\u001a8\u0010\u0005\u001a\u00020\u0006*\u00020\u00032\b\b\u0001\u0010\u0012\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000f¢\u0006\u0002\b\u0011H\u0086\b¨\u0006\u0013"}, m3961d2 = {"getSystemService", ExifInterface.GPS_DIRECTION_TRUE, "", "Landroid/content/Context;", "(Landroid/content/Context;)Ljava/lang/Object;", "withStyledAttributes", "", TmpConstant.PROPERTY_IDENTIFIER_SET, "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "", "defStyleRes", "block", "Lkotlin/Function1;", "Landroid/content/res/TypedArray;", "Lkotlin/ExtensionFunctionType;", "resourceId", "core-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class ContextKt {
    public static final /* synthetic */ <T> T getSystemService(Context getSystemService) {
        Intrinsics.checkParameterIsNotNull(getSystemService, "$this$getSystemService");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) ContextCompat.getSystemService(getSystemService, Object.class);
    }

    public static /* synthetic */ void withStyledAttributes$default(Context withStyledAttributes, AttributeSet attributeSet, int[] attrs, int i, int i2, Function1 block, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            attributeSet = (AttributeSet) null;
        }
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        Intrinsics.checkParameterIsNotNull(withStyledAttributes, "$this$withStyledAttributes");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        Intrinsics.checkParameterIsNotNull(block, "block");
        TypedArray obtainStyledAttributes = withStyledAttributes.obtainStyledAttributes(attributeSet, attrs, i, i2);
        block.invoke(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    public static final void withStyledAttributes(Context withStyledAttributes, AttributeSet attributeSet, int[] attrs, int i, int i2, Function1<? super TypedArray, Unit> block) {
        Intrinsics.checkParameterIsNotNull(withStyledAttributes, "$this$withStyledAttributes");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        Intrinsics.checkParameterIsNotNull(block, "block");
        TypedArray obtainStyledAttributes = withStyledAttributes.obtainStyledAttributes(attributeSet, attrs, i, i2);
        block.invoke(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    public static final void withStyledAttributes(Context withStyledAttributes, int i, int[] attrs, Function1<? super TypedArray, Unit> block) {
        Intrinsics.checkParameterIsNotNull(withStyledAttributes, "$this$withStyledAttributes");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        Intrinsics.checkParameterIsNotNull(block, "block");
        TypedArray obtainStyledAttributes = withStyledAttributes.obtainStyledAttributes(i, attrs);
        block.invoke(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }
}
