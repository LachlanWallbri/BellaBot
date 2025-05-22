package com.pudutech.peanut.robot_ui.extend;

import com.pudutech.resources.language.Option;
import com.pudutech.resources.language.SupportedLocale;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SupportedLocaleExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003*\u00020\u0004¨\u0006\u0005"}, m3961d2 = {"getList", "Ljava/util/ArrayList;", "Lcom/pudutech/resources/language/Option;", "Lkotlin/collections/ArrayList;", "Lcom/pudutech/resources/language/SupportedLocale;", "robot_ui_peanutRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SupportedLocaleExtKt {
    public static final ArrayList<Option> getList(SupportedLocale getList) {
        Intrinsics.checkParameterIsNotNull(getList, "$this$getList");
        return CollectionsKt.arrayListOf(getList.getCHINESE(), getList.getENGLISH(), getList.getCHINESE_HK());
    }
}
