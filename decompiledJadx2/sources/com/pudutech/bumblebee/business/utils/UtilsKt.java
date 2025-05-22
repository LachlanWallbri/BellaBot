package com.pudutech.bumblebee.business.utils;

import com.pudutech.mirsdk.map.EncodeUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: Utils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0001¨\u0006\u0003"}, m3961d2 = {"decodeMapName", "", "encodeMapName", "module_bumblebee_business_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class UtilsKt {
    public static final String encodeMapName(String encodeMapName) {
        Intrinsics.checkParameterIsNotNull(encodeMapName, "$this$encodeMapName");
        byte[] bytes = encodeMapName.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        String base64Encode2String = EncodeUtils.base64Encode2String(bytes);
        Intrinsics.checkExpressionValueIsNotNull(base64Encode2String, "base64Encode2String");
        return StringsKt.replace$default(StringsKt.replace$default(base64Encode2String, "+", "_", false, 4, (Object) null), "/", "-", false, 4, (Object) null);
    }

    public static final String decodeMapName(String decodeMapName) {
        Intrinsics.checkParameterIsNotNull(decodeMapName, "$this$decodeMapName");
        return EncodeUtils.base64Encode(StringsKt.replace$default(StringsKt.replace$default(decodeMapName, "_", "+", false, 4, (Object) null), "-", "/", false, 4, (Object) null)).toString();
    }
}
