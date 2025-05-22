package com.pudutech.freeinstall_ui.dialog;

import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.IMarkerCameraListener;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: CameraPreviewDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/freeinstall_ui/dialog/CameraPreviewDialog$setMarkerCameraListener$1", "Lcom/pudutech/mirsdk/aidl/IMarkerCameraListener$Stub;", "onFrame", "", "p0", "Landroid/os/ParcelFileDescriptor;", "p1", "", "p2", "p3", "p4", "p5", "", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CameraPreviewDialog$setMarkerCameraListener$1 extends IMarkerCameraListener.Stub {
    final /* synthetic */ CameraPreviewDialog this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CameraPreviewDialog$setMarkerCameraListener$1(CameraPreviewDialog cameraPreviewDialog) {
        this.this$0 = cameraPreviewDialog;
    }

    @Override // com.pudutech.mirsdk.aidl.IMarkerCameraListener
    public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
        Pdlog.m3273d(CameraPreviewDialog.TAG, "addMarkerCameraListener: onFrame--" + p0);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CameraPreviewDialog$setMarkerCameraListener$1$onFrame$1(this, p0, p1, p2, p3, p4, p5, null), 3, null);
    }
}
