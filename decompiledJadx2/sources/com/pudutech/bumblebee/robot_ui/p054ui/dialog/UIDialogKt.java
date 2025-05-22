package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.content.Context;
import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UIDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a7\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\b\t¨\u0006\n"}, m3961d2 = {"dialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/UIDialog;", "Landroid/content/Context;", "title", "", AIUIConstant.KEY_CONTENT, "init", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "robot_ui_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class UIDialogKt {
    public static /* synthetic */ UIDialog dialog$default(Context context, String str, String str2, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        return dialog(context, str, str2, function1);
    }

    public static final UIDialog dialog(Context dialog, String title, String content, Function1<? super UIDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(dialog, "$this$dialog");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(content, "content");
        UIDialog uIDialog = new UIDialog(dialog);
        uIDialog.setTitle(title);
        uIDialog.setContent(content);
        if (function1 != null) {
            function1.invoke(uIDialog);
        }
        return uIDialog;
    }
}
