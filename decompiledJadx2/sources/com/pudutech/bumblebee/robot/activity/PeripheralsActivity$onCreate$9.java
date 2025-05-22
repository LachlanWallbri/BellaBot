package com.pudutech.bumblebee.robot.activity;

import android.widget.CompoundButton;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class PeripheralsActivity$onCreate$9 implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ PeripheralsActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeripheralsActivity$onCreate$9(PeripheralsActivity peripheralsActivity) {
        this.this$0 = peripheralsActivity;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        CameraInterface camera;
        PeripheralsActivity$onMonocularCameraStateListener$1 peripheralsActivity$onMonocularCameraStateListener$1;
        PeripheralsActivity$onMonocularCameraListener$1 peripheralsActivity$onMonocularCameraListener$1;
        HardwareInterface hardwareInterface = this.this$0.getHardwareInterface();
        if (hardwareInterface == null || (camera = hardwareInterface.getCamera()) == null) {
            return;
        }
        if (z) {
            camera.openMonocularCamera();
            peripheralsActivity$onMonocularCameraStateListener$1 = this.this$0.onMonocularCameraStateListener;
            camera.addMonocularCameraStateListener("monocularCameraStateListener", peripheralsActivity$onMonocularCameraStateListener$1);
            peripheralsActivity$onMonocularCameraListener$1 = this.this$0.onMonocularCameraListener;
            camera.addMonocularCameraListener("monocularCameraListener", peripheralsActivity$onMonocularCameraListener$1);
            return;
        }
        camera.closeMarkerCamera();
        camera.removeMonocularCameraStateListener("monocularCameraStateListener");
        camera.removeMonocularCameraListener("monocularCameraListener");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new PeripheralsActivity$onCreate$9$$special$$inlined$apply$lambda$1(null, this, z), 2, null);
    }
}
