package com.pudutech.mirsdk.map;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: StringBase64.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u000e\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001¨\u0006\u0005"}, m3961d2 = {"decodeMapName", "", "enName", "encodeMapName", "name", "MirFunction_packRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class StringBase64Kt {
    public static final String encodeMapName(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        byte[] bytes = name.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        String base64Encode2String = EncodeUtils.base64Encode2String(bytes);
        Intrinsics.checkExpressionValueIsNotNull(base64Encode2String, "base64Encode2String");
        return StringsKt.replace$default(StringsKt.replace$default(base64Encode2String, "+", "_", false, 4, (Object) null), "/", "-", false, 4, (Object) null);
    }

    public static final String decodeMapName(String enName) {
        Intrinsics.checkParameterIsNotNull(enName, "enName");
        byte[] base64Decode = EncodeUtils.base64Decode(StringsKt.replace$default(StringsKt.replace$default(enName, "_", "+", false, 4, (Object) null), "-", "/", false, 4, (Object) null));
        Intrinsics.checkExpressionValueIsNotNull(base64Decode, "EncodeUtils.base64Decode(name)");
        return new String(base64Decode, Charsets.UTF_8);
    }
}
