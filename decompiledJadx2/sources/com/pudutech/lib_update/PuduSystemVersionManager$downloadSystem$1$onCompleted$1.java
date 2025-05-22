package com.pudutech.lib_update;

import com.loc.C3898x;
import com.pudutech.lib_update.listener.SystemInstallCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: PuduSystemVersionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, m3961d2 = {"com/pudutech/lib_update/PuduSystemVersionManager$downloadSystem$1$onCompleted$1", "Lcom/pudutech/lib_update/listener/SystemInstallCallback;", "onFail", "", C3898x.f4338g, "", "onFinish", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PuduSystemVersionManager$downloadSystem$1$onCompleted$1 implements SystemInstallCallback {
    final /* synthetic */ PuduSystemVersionManager$downloadSystem$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PuduSystemVersionManager$downloadSystem$1$onCompleted$1(PuduSystemVersionManager$downloadSystem$1 puduSystemVersionManager$downloadSystem$1) {
        this.this$0 = puduSystemVersionManager$downloadSystem$1;
    }

    @Override // com.pudutech.lib_update.listener.SystemInstallCallback
    public void onFail(Throwable e) {
        CoroutineScope coroutineScope;
        Intrinsics.checkParameterIsNotNull(e, "e");
        PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
        coroutineScope = PuduSystemVersionManager.scope;
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new PuduSystemVersionManager$downloadSystem$1$onCompleted$1$onFail$1(this, e, null), 2, null);
    }

    @Override // com.pudutech.lib_update.listener.SystemInstallCallback
    public void onFinish() {
        CoroutineScope coroutineScope;
        PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
        coroutineScope = PuduSystemVersionManager.scope;
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new C4656xa6139b60(this, null), 2, null);
    }
}
