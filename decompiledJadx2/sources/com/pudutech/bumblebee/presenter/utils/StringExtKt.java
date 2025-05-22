package com.pudutech.bumblebee.presenter.utils;

import com.google.gson.Gson;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StringExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002¨\u0006\u0003"}, m3961d2 = {"toJson", "", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class StringExtKt {
    public static final String toJson(Object obj) {
        String json = new Gson().toJson(obj);
        Intrinsics.checkExpressionValueIsNotNull(json, "Gson().toJson(this)");
        return json;
    }
}
