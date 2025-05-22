package com.pudutech.bumblebee.business.ims;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: IMSKit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0019\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m3961d2 = {"main", "", "args", "", "", "([Ljava/lang/String;)V", "module_bumblebee_business_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IMSKitKt {
    public static final void main(String[] args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        byte[] bytes = "中华abc人民cas共ass和sdf国".getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        if (bytes.length >= 20) {
            System.out.println((Object) ("字节数：" + bytes.length + "\t结果：" + new String(ArraysKt.copyOfRange(bytes, 0, 20), Charsets.UTF_8)));
        }
    }
}
