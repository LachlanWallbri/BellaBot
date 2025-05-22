package com.pudutech.mirsdk.activity;

import android.os.ParcelFileDescriptor;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.CliffDistanceStateListener;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\nH\u0016¨\u0006\u000b"}, m3961d2 = {"com/pudutech/mirsdk/activity/MirSDKActivity$initDetDropSwitch$1", "Lcom/pudutech/mirsdk/aidl/CliffDistanceStateListener$Stub;", "cliffDistance", "", "p0", "", "cliffIrImg", "Landroid/os/ParcelFileDescriptor;", "p1", "enterSpeedLimitArea", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$initDetDropSwitch$1 extends CliffDistanceStateListener.Stub {
    final /* synthetic */ MirSDKActivity this$0;

    @Override // com.pudutech.mirsdk.aidl.CliffDistanceStateListener
    public void cliffIrImg(ParcelFileDescriptor p0, int p1) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$initDetDropSwitch$1(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // com.pudutech.mirsdk.aidl.CliffDistanceStateListener
    public void cliffDistance(int p0) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$initDetDropSwitch$1$cliffDistance$1(this, p0, null), 2, null);
    }

    @Override // com.pudutech.mirsdk.aidl.CliffDistanceStateListener
    public void enterSpeedLimitArea(boolean p0) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new MirSDKActivity$initDetDropSwitch$1$enterSpeedLimitArea$1(this, p0, null), 2, null);
    }
}
