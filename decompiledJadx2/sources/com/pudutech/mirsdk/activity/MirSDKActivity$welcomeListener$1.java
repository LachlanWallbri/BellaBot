package com.pudutech.mirsdk.activity;

import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.IWelcomeListener;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$welcomeListener$1", "Lcom/pudutech/mirsdk/aidl/IWelcomeListener$Stub;", "onPersonApproaching", "", "id", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MirSDKActivity$welcomeListener$1 extends IWelcomeListener.Stub {
    final /* synthetic */ MirSDKActivity this$0;

    MirSDKActivity$welcomeListener$1(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // com.pudutech.mirsdk.aidl.IWelcomeListener
    public void onPersonApproaching(int id) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$welcomeListener$1$onPersonApproaching$1(this, id, null), 2, null);
    }
}
