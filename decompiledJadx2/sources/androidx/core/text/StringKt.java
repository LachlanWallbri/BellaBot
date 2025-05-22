package androidx.core.text;

import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: String.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0086\b¨\u0006\u0002"}, m3961d2 = {"htmlEncode", "", "core-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class StringKt {
    public static final String htmlEncode(String htmlEncode) {
        Intrinsics.checkParameterIsNotNull(htmlEncode, "$this$htmlEncode");
        String htmlEncode2 = TextUtils.htmlEncode(htmlEncode);
        Intrinsics.checkExpressionValueIsNotNull(htmlEncode2, "TextUtils.htmlEncode(this)");
        return htmlEncode2;
    }
}
