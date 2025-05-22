package com.pudutech.mirsdk.mircore.tools;

import android.util.Base64;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: EncodeUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/tools/EncodeUtils;", "", "()V", "decodeMapName", "", "enName", "encodeMapName", "name", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class EncodeUtils {
    public static final EncodeUtils INSTANCE = new EncodeUtils();

    private EncodeUtils() {
    }

    public final String encodeMapName(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        byte[] bytes = name.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        String str = "";
        if (bytes != null && bytes.length != 0) {
            str = Base64.encodeToString(bytes, 2);
        }
        String base64Encode2String = str;
        Intrinsics.checkExpressionValueIsNotNull(base64Encode2String, "base64Encode2String");
        return StringsKt.replace$default(StringsKt.replace$default(base64Encode2String, "+", "_", false, 4, (Object) null), "/", "-", false, 4, (Object) null);
    }

    public final String decodeMapName(String enName) {
        Intrinsics.checkParameterIsNotNull(enName, "enName");
        byte[] decode = Base64.decode(StringsKt.replace$default(StringsKt.replace$default(enName, "_", "+", false, 4, (Object) null), "-", "/", false, 4, (Object) null), 2);
        Intrinsics.checkExpressionValueIsNotNull(decode, "Base64.decode(name, Base64.NO_WRAP)");
        return new String(decode, Charsets.UTF_8);
    }
}
