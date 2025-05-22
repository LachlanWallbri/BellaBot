package com.pudutech.mirsdk.hardware.activity;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.HardwareScopeKt;
import com.pudutech.mirsdk.hardware.IRgbdStatus;
import com.pudutech.mirsdk.hardware.library.C5193R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, m3961d2 = {"com/pudutech/mirsdk/hardware/activity/HardwareActivity$setupRGBDListener$1", "Lcom/pudutech/mirsdk/hardware/IRgbdStatus$Stub;", "showFrameTick", "", "getShowFrameTick", "()Z", "setShowFrameTick", "(Z)V", "onParkingMode", "", DebugKt.DEBUG_PROPERTY_VALUE_ON, "onRGBDFrameTick", "onRGBDOpened", "opened", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class HardwareActivity$setupRGBDListener$1 extends IRgbdStatus.Stub {
    private boolean showFrameTick;
    final /* synthetic */ HardwareActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareActivity$setupRGBDListener$1(HardwareActivity hardwareActivity) {
        this.this$0 = hardwareActivity;
    }

    public final boolean getShowFrameTick() {
        return this.showFrameTick;
    }

    public final void setShowFrameTick(boolean z) {
        this.showFrameTick = z;
    }

    @Override // com.pudutech.mirsdk.hardware.IRgbdStatus
    public void onRGBDFrameTick() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "onRGBDFrameTick");
        if (this.showFrameTick) {
            return;
        }
        this.showFrameTick = true;
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupRGBDListener$1$onRGBDFrameTick$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView textViewRGBDState = (TextView) HardwareActivity$setupRGBDListener$1.this.this$0._$_findCachedViewById(C5193R.id.textViewRGBDState);
                Intrinsics.checkExpressionValueIsNotNull(textViewRGBDState, "textViewRGBDState");
                textViewRGBDState.setText("recv data");
            }
        });
    }

    @Override // com.pudutech.mirsdk.hardware.IRgbdStatus
    public void onRGBDOpened(boolean opened) {
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$setupRGBDListener$1$onRGBDOpened$1(this, opened, null), 2, null);
    }

    @Override // com.pudutech.mirsdk.hardware.IRgbdStatus
    public void onParkingMode(boolean on) {
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$setupRGBDListener$1$onParkingMode$1(this, on, null), 2, null);
    }
}
