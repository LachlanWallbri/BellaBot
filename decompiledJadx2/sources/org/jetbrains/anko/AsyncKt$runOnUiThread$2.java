package org.jetbrains.anko;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Async.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class AsyncKt$runOnUiThread$2 implements Runnable {

    /* renamed from: $f */
    final /* synthetic */ Function0 f10073$f;

    public AsyncKt$runOnUiThread$2(Function0 function0) {
        this.f10073$f = function0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f10073$f.invoke();
    }
}
