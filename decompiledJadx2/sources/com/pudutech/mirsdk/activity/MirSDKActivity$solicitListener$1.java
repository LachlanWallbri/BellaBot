package com.pudutech.mirsdk.activity;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.ISolicitListener;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J(\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0005H\u0016¨\u0006\u000e"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$solicitListener$1", "Lcom/pudutech/mirsdk/aidl/ISolicitListener$Stub;", "onFaceDetectResult", "", "flag", "", "yaw", "", "pitch", "distance", "onPersonDetection", SpeechUtility.TAG_RESOURCE_RESULT, "degree", "roteStatus", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MirSDKActivity$solicitListener$1 extends ISolicitListener.Stub {
    final /* synthetic */ MirSDKActivity this$0;

    MirSDKActivity$solicitListener$1(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // com.pudutech.mirsdk.aidl.ISolicitListener
    public void onFaceDetectResult(int flag, double yaw, double pitch, double distance) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$solicitListener$1$onFaceDetectResult$1(this, flag, yaw, pitch, distance, null), 2, null);
    }

    @Override // com.pudutech.mirsdk.aidl.ISolicitListener
    public void onPersonDetection(int result, double degree, double distance, int roteStatus) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$solicitListener$1$onPersonDetection$1(this, result, degree, distance, roteStatus, null), 2, null);
    }
}
