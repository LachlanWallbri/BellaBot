package com.pudutech.peanut.presenter;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BusinessSetting.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"getNum", "", "str", "", "invoke", "(Ljava/lang/String;)Ljava/lang/Long;"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BusinessSetting$oldFileCompatibility$1 extends Lambda implements Function1<String, Long> {
    public static final BusinessSetting$oldFileCompatibility$1 INSTANCE = new BusinessSetting$oldFileCompatibility$1();

    BusinessSetting$oldFileCompatibility$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Long invoke(String str) {
        String str2;
        Intrinsics.checkParameterIsNotNull(str, "str");
        BusinessSetting businessSetting = BusinessSetting.INSTANCE;
        str2 = BusinessSetting.TAG;
        Pdlog.m3273d(str2, "getNum str=" + str);
        String str3 = (String) StringsKt.split$default((CharSequence) str, new String[]{"//"}, false, 0, 6, (Object) null).get(0);
        if (!(str3.length() > 0) || !(!StringsKt.isBlank(r12))) {
            return null;
        }
        long parseLong = Long.parseLong(StringsKt.replace$default(str3, " ", "", false, 4, (Object) null));
        if (parseLong > 0) {
            return Long.valueOf(parseLong);
        }
        return null;
    }
}
