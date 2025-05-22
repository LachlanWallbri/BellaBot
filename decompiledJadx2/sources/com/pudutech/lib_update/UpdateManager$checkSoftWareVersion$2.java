package com.pudutech.lib_update;

import com.pudutech.base.Pdlog;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import kotlin.Metadata;

/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "throwable", "", "kotlin.jvm.PlatformType", "accept"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class UpdateManager$checkSoftWareVersion$2<T> implements Consumer<Throwable> {
    final /* synthetic */ Function $onError;

    UpdateManager$checkSoftWareVersion$2(Function function) {
        this.$onError = function;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(Throwable th) {
        Pdlog.m3274e("UpdateManager", "uri e:" + th);
        Function function = this.$onError;
        if (function != null) {
        }
    }
}
