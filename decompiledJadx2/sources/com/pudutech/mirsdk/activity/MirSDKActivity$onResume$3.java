package com.pudutech.mirsdk.activity;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.AccessDoorListener;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$onResume$3", "Lcom/pudutech/mirsdk/aidl/AccessDoorListener$Stub;", "informAccessDoorControlState", "", "p0", "Lcom/pudutech/mirsdk/aidl/serialize/AccessDoorControlState;", "p1", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$onResume$3 extends AccessDoorListener.Stub {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$onResume$3(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // com.pudutech.mirsdk.aidl.AccessDoorListener
    public void informAccessDoorControlState(AccessDoorControlState p0, String p1) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "AccessDoorControlState is " + p0);
    }
}
