package com.pudutech.mirsdk.activity;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.PersonDetectListener;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J@\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$personDetectListener$1", "Lcom/pudutech/mirsdk/aidl/PersonDetectListener$Stub;", "onPersonDetection", "", SpeechUtility.TAG_RESOURCE_RESULT, "", "id", "degree", "", "distance", "direction", "vx", "vy", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MirSDKActivity$personDetectListener$1 extends PersonDetectListener.Stub {
    final /* synthetic */ MirSDKActivity this$0;

    MirSDKActivity$personDetectListener$1(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // com.pudutech.mirsdk.aidl.PersonDetectListener
    public void onPersonDetection(int result, int id, double degree, double distance, double direction, double vx, double vy) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$personDetectListener$1$onPersonDetection$1(this, result, id, degree, distance, null), 2, null);
    }
}
