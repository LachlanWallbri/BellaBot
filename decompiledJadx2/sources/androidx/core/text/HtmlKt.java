package androidx.core.text;

import android.text.Html;
import android.text.Spanned;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: Html.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0086\b\u001a\u0017\u0010\t\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u0004H\u0086\b¨\u0006\u000b"}, m3961d2 = {"parseAsHtml", "Landroid/text/Spanned;", "", "flags", "", "imageGetter", "Landroid/text/Html$ImageGetter;", "tagHandler", "Landroid/text/Html$TagHandler;", "toHtml", "option", "core-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class HtmlKt {
    public static /* synthetic */ Spanned parseAsHtml$default(String parseAsHtml, int i, Html.ImageGetter imageGetter, Html.TagHandler tagHandler, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            imageGetter = (Html.ImageGetter) null;
        }
        if ((i2 & 4) != 0) {
            tagHandler = (Html.TagHandler) null;
        }
        Intrinsics.checkParameterIsNotNull(parseAsHtml, "$this$parseAsHtml");
        Spanned fromHtml = HtmlCompat.fromHtml(parseAsHtml, i, imageGetter, tagHandler);
        Intrinsics.checkExpressionValueIsNotNull(fromHtml, "HtmlCompat.fromHtml(this… imageGetter, tagHandler)");
        return fromHtml;
    }

    public static final Spanned parseAsHtml(String parseAsHtml, int i, Html.ImageGetter imageGetter, Html.TagHandler tagHandler) {
        Intrinsics.checkParameterIsNotNull(parseAsHtml, "$this$parseAsHtml");
        Spanned fromHtml = HtmlCompat.fromHtml(parseAsHtml, i, imageGetter, tagHandler);
        Intrinsics.checkExpressionValueIsNotNull(fromHtml, "HtmlCompat.fromHtml(this… imageGetter, tagHandler)");
        return fromHtml;
    }

    public static /* synthetic */ String toHtml$default(Spanned toHtml, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(toHtml, "$this$toHtml");
        String html = HtmlCompat.toHtml(toHtml, i);
        Intrinsics.checkExpressionValueIsNotNull(html, "HtmlCompat.toHtml(this, option)");
        return html;
    }

    public static final String toHtml(Spanned toHtml, int i) {
        Intrinsics.checkParameterIsNotNull(toHtml, "$this$toHtml");
        String html = HtmlCompat.toHtml(toHtml, i);
        Intrinsics.checkExpressionValueIsNotNull(html, "HtmlCompat.toHtml(this, option)");
        return html;
    }
}
