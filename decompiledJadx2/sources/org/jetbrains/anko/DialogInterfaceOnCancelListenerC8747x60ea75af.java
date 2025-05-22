package org.jetbrains.anko;

import android.content.DialogInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: AndroidAlertBuilder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 13})
/* renamed from: org.jetbrains.anko.AndroidAlertBuilderKt$sam$android_content_DialogInterface_OnCancelListener$0 */
/* loaded from: classes9.dex */
final class DialogInterfaceOnCancelListenerC8747x60ea75af implements DialogInterface.OnCancelListener {
    private final /* synthetic */ Function1 function;

    DialogInterfaceOnCancelListenerC8747x60ea75af(Function1 function1) {
        this.function = function1;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final /* synthetic */ void onCancel(DialogInterface dialogInterface) {
        Intrinsics.checkExpressionValueIsNotNull(this.function.invoke(dialogInterface), "invoke(...)");
    }
}
