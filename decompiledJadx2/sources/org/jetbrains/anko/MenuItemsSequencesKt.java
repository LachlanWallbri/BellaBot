package org.jetbrains.anko;

import android.view.Menu;
import android.view.MenuItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: menuItemsSequences.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003¨\u0006\u0004"}, m3961d2 = {"itemsSequence", "Lkotlin/sequences/Sequence;", "Landroid/view/MenuItem;", "Landroid/view/Menu;", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class MenuItemsSequencesKt {
    public static final Sequence<MenuItem> itemsSequence(Menu receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new MenuItemsSequence(receiver);
    }
}
