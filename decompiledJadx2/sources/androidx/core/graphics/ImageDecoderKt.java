package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ImageDecoder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aR\u0010\u0000\u001a\u00020\u0001*\u00020\u00022C\b\u0004\u0010\u0003\u001a=\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\u0002\b\fH\u0087\b\u001aR\u0010\r\u001a\u00020\u000e*\u00020\u00022C\b\u0004\u0010\u0003\u001a=\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\u0002\b\fH\u0087\b¨\u0006\u000f"}, m3961d2 = {"decodeBitmap", "Landroid/graphics/Bitmap;", "Landroid/graphics/ImageDecoder$Source;", "action", "Lkotlin/Function3;", "Landroid/graphics/ImageDecoder;", "Landroid/graphics/ImageDecoder$ImageInfo;", "Lkotlin/ParameterName;", "name", "info", MapElement.Source.SOURCE, "", "Lkotlin/ExtensionFunctionType;", "decodeDrawable", "Landroid/graphics/drawable/Drawable;", "core-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class ImageDecoderKt {
    public static final Bitmap decodeBitmap(ImageDecoder.Source decodeBitmap, final Function3<? super ImageDecoder, ? super ImageDecoder.ImageInfo, ? super ImageDecoder.Source, Unit> action) {
        Intrinsics.checkParameterIsNotNull(decodeBitmap, "$this$decodeBitmap");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Bitmap decodeBitmap2 = ImageDecoder.decodeBitmap(decodeBitmap, new ImageDecoder.OnHeaderDecodedListener() { // from class: androidx.core.graphics.ImageDecoderKt$decodeBitmap$1
            @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
            public final void onHeaderDecoded(ImageDecoder decoder, ImageDecoder.ImageInfo info, ImageDecoder.Source source) {
                Intrinsics.checkParameterIsNotNull(decoder, "decoder");
                Intrinsics.checkParameterIsNotNull(info, "info");
                Intrinsics.checkParameterIsNotNull(source, "source");
                Function3.this.invoke(decoder, info, source);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(decodeBitmap2, "ImageDecoder.decodeBitma…ction(info, source)\n    }");
        return decodeBitmap2;
    }

    public static final Drawable decodeDrawable(ImageDecoder.Source decodeDrawable, final Function3<? super ImageDecoder, ? super ImageDecoder.ImageInfo, ? super ImageDecoder.Source, Unit> action) {
        Intrinsics.checkParameterIsNotNull(decodeDrawable, "$this$decodeDrawable");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Drawable decodeDrawable2 = ImageDecoder.decodeDrawable(decodeDrawable, new ImageDecoder.OnHeaderDecodedListener() { // from class: androidx.core.graphics.ImageDecoderKt$decodeDrawable$1
            @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
            public final void onHeaderDecoded(ImageDecoder decoder, ImageDecoder.ImageInfo info, ImageDecoder.Source source) {
                Intrinsics.checkParameterIsNotNull(decoder, "decoder");
                Intrinsics.checkParameterIsNotNull(info, "info");
                Intrinsics.checkParameterIsNotNull(source, "source");
                Function3.this.invoke(decoder, info, source);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(decodeDrawable2, "ImageDecoder.decodeDrawa…ction(info, source)\n    }");
        return decodeDrawable2;
    }
}
