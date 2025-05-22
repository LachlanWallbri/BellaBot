package com.pudutech.mirsdk.activity;

import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.FillInStateListener;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$onResume$1", "Lcom/pudutech/mirsdk/aidl/FillInStateListener$Stub;", "onFillIn", "", "p0", "", "p1", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$onResume$1 extends FillInStateListener.Stub {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$onResume$1(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // com.pudutech.mirsdk.aidl.FillInStateListener
    public void onFillIn(boolean p0, Destination p1) {
        if (!p0) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$onResume$1$onFillIn$1(this, null), 2, null);
        } else if (p1 == null) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$onResume$1$onFillIn$2(this, null), 2, null);
        } else {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$onResume$1$onFillIn$3(this, p1, null), 2, null);
        }
    }
}
