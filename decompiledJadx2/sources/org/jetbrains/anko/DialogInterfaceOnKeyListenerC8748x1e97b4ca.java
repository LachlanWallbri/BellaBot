package org.jetbrains.anko;

import android.content.DialogInterface;
import android.view.KeyEvent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: AndroidAlertBuilder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 13})
/* renamed from: org.jetbrains.anko.AndroidAlertBuilderKt$sam$android_content_DialogInterface_OnKeyListener$0 */
/* loaded from: classes9.dex */
final class DialogInterfaceOnKeyListenerC8748x1e97b4ca implements DialogInterface.OnKeyListener {
    private final /* synthetic */ Function3 function;

    DialogInterfaceOnKeyListenerC8748x1e97b4ca(Function3 function3) {
        this.function = function3;
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final /* synthetic */ boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Object invoke = this.function.invoke(dialogInterface, Integer.valueOf(i), keyEvent);
        Intrinsics.checkExpressionValueIsNotNull(invoke, "invoke(...)");
        return ((Boolean) invoke).booleanValue();
    }
}
