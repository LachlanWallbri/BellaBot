package com.pudutech.mirsdk.hardware.activity;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IMarkerCameraState;
import com.pudutech.mirsdk.hardware.library.C5193R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\f"}, m3961d2 = {"com/pudutech/mirsdk/hardware/activity/HardwareActivity$setupMarkerCameraListener$1", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraState$Stub;", "showFrame", "", "getShowFrame", "()Z", "setShowFrame", "(Z)V", "onCameraFrameTick", "", "onOpened", "opened", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class HardwareActivity$setupMarkerCameraListener$1 extends IMarkerCameraState.Stub {
    private boolean showFrame;
    final /* synthetic */ HardwareActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareActivity$setupMarkerCameraListener$1(HardwareActivity hardwareActivity) {
        this.this$0 = hardwareActivity;
    }

    public final boolean getShowFrame() {
        return this.showFrame;
    }

    public final void setShowFrame(boolean z) {
        this.showFrame = z;
    }

    @Override // com.pudutech.mirsdk.hardware.IMarkerCameraState
    public void onOpened(final boolean opened) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "addMarkerCameraStateListener onOpened:" + opened);
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMarkerCameraListener$1$onOpened$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView textViewMarkerCameraState = (TextView) HardwareActivity$setupMarkerCameraListener$1.this.this$0._$_findCachedViewById(C5193R.id.textViewMarkerCameraState);
                Intrinsics.checkExpressionValueIsNotNull(textViewMarkerCameraState, "textViewMarkerCameraState");
                textViewMarkerCameraState.setText("opened: " + opened);
            }
        });
    }

    @Override // com.pudutech.mirsdk.hardware.IMarkerCameraState
    public void onCameraFrameTick() {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "onCameraFrameTick");
        if (this.showFrame) {
            return;
        }
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupMarkerCameraListener$1$onCameraFrameTick$1
            @Override // java.lang.Runnable
            public final void run() {
                TextView textViewMarkerCameraData = (TextView) HardwareActivity$setupMarkerCameraListener$1.this.this$0._$_findCachedViewById(C5193R.id.textViewMarkerCameraData);
                Intrinsics.checkExpressionValueIsNotNull(textViewMarkerCameraData, "textViewMarkerCameraData");
                textViewMarkerCameraData.setText("recv data");
            }
        });
    }
}
