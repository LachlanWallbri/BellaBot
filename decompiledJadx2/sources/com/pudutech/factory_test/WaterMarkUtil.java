package com.pudutech.factory_test;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.pudutech.factory_test.activity.WaterMarkView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WaterMarkUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/factory_test/WaterMarkUtil;", "", "()V", "VIEW_TAG", "", "enable", "", "getEnable", "()Z", "setEnable", "(Z)V", "text", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "onActivityStart", "", "activity", "Landroid/app/Activity;", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class WaterMarkUtil {
    private static final String VIEW_TAG = "view_tag";
    private static boolean enable;
    public static final WaterMarkUtil INSTANCE = new WaterMarkUtil();
    private static String text = "水印";

    private WaterMarkUtil() {
    }

    public final boolean getEnable() {
        return enable;
    }

    public final void setEnable(boolean z) {
        enable = z;
    }

    public final String getText() {
        return text;
    }

    public final void setText(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        text = str;
    }

    public final void onActivityStart(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Window window = activity.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "activity.window");
        ViewGroup viewGroup = (ViewGroup) window.getDecorView().findViewById(android.R.id.content);
        if (enable) {
            if ((text.length() > 0) && viewGroup.findViewWithTag(VIEW_TAG) == null) {
                View inflate = LayoutInflater.from(activity).inflate(2131427399, (ViewGroup) null);
                if (inflate == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.factory_test.activity.WaterMarkView");
                }
                WaterMarkView waterMarkView = (WaterMarkView) inflate;
                waterMarkView.setTag(VIEW_TAG);
                waterMarkView.setMarkText(text);
                viewGroup.addView(waterMarkView);
            }
        }
    }
}
