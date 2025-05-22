package com.pudutech.factory_test;

import android.content.Context;
import android.widget.Button;
import androidx.appcompat.widget.LinearLayoutCompat;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayoutHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\u001aD\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0007j\b\u0012\u0004\u0012\u00020\u000e`\t2\b\b\u0002\u0010\u000f\u001a\u00020\u0010\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u0011"}, m3961d2 = {"MATCH_PARENT", "", "getMATCH_PARENT", "()I", "WRAP_CONTENT", "getWRAP_CONTENT", "layoutBtnOptions", "Ljava/util/ArrayList;", "Landroid/widget/Button;", "Lkotlin/collections/ArrayList;", "Landroidx/appcompat/widget/LinearLayoutCompat;", "context", "Landroid/content/Context;", "options", "", "stringSize", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LayoutHelperKt {
    private static final int MATCH_PARENT = -1;
    private static final int WRAP_CONTENT = -2;

    public static final int getWRAP_CONTENT() {
        return WRAP_CONTENT;
    }

    public static final int getMATCH_PARENT() {
        return MATCH_PARENT;
    }

    public static /* synthetic */ ArrayList layoutBtnOptions$default(LinearLayoutCompat linearLayoutCompat, Context context, ArrayList arrayList, float f, int i, Object obj) {
        if ((i & 4) != 0) {
            f = 40.0f;
        }
        return layoutBtnOptions(linearLayoutCompat, context, arrayList, f);
    }

    public static final ArrayList<Button> layoutBtnOptions(LinearLayoutCompat layoutBtnOptions, Context context, ArrayList<String> options, float f) {
        Intrinsics.checkParameterIsNotNull(layoutBtnOptions, "$this$layoutBtnOptions");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(options, "options");
        layoutBtnOptions.removeAllViews();
        ArrayList<Button> arrayList = new ArrayList<>();
        for (String str : options) {
            Button button = new Button(context);
            button.setTextSize(f);
            button.setText(str);
            button.setSingleLine(true);
            button.setTextColor(-1);
            button.setPadding(10, 20, 10, 20);
            arrayList.add(button);
            LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(0, WRAP_CONTENT);
            layoutParams.weight = 1.0f;
            layoutParams.setMargins(50, 0, 50, 0);
            button.setLayoutParams(layoutParams);
            layoutBtnOptions.addView(button);
        }
        return arrayList;
    }
}
