package com.pudutech.lib_update;

import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.listener.IShowProgress;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PuduSystemVersionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class PuduSystemVersionManager$checkSystemUpdate$disposable$2<T> implements Consumer<Throwable> {
    final /* synthetic */ IShowProgress $checkSystemListener;

    PuduSystemVersionManager$checkSystemUpdate$disposable$2(IShowProgress iShowProgress) {
        this.$checkSystemListener = iShowProgress;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(Throwable it) {
        Pdlog.m3274e("PuduSystemVersionManager", "checkSystemUpdate error");
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        Pdlog.m3274e("PuduSystemVersionManager", it.getLocalizedMessage());
        IShowProgress iShowProgress = this.$checkSystemListener;
        if (iShowProgress != null) {
            iShowProgress.onFail(it);
        }
    }
}
