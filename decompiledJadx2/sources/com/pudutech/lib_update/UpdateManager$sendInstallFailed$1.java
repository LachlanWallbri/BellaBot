package com.pudutech.lib_update;

import com.pudutech.lib_update.listener.IShowProgress;
import kotlin.Metadata;

/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class UpdateManager$sendInstallFailed$1 implements Runnable {

    /* renamed from: $e */
    final /* synthetic */ Throwable f5432$e;

    UpdateManager$sendInstallFailed$1(Throwable th) {
        this.f5432$e = th;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IShowProgress access$getShowDownFileProgress$p = UpdateManager.access$getShowDownFileProgress$p(UpdateManager.INSTANCE);
        if (access$getShowDownFileProgress$p != null) {
            access$getShowDownFileProgress$p.onFail(this.f5432$e);
        }
        UpdateManager.access$setShowDownFileProgress$p(UpdateManager.INSTANCE, (IShowProgress) null);
    }
}
