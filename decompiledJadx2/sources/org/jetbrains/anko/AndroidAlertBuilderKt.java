package org.jetbrains.anko;

import android.app.AlertDialog;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: AndroidAlertBuilder.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"-\u0010\u0000\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m3961d2 = {"Android", "Lkotlin/Function1;", "Landroid/content/Context;", "Lorg/jetbrains/anko/AlertBuilder;", "Landroid/app/AlertDialog;", "Lorg/jetbrains/anko/AlertBuilderFactory;", "getAndroid", "()Lkotlin/jvm/functions/Function1;", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class AndroidAlertBuilderKt {
    private static final Function1<Context, AlertBuilder<AlertDialog>> Android = AndroidAlertBuilderKt$Android$1.INSTANCE;

    public static final Function1<Context, AlertBuilder<AlertDialog>> getAndroid() {
        return Android;
    }
}
