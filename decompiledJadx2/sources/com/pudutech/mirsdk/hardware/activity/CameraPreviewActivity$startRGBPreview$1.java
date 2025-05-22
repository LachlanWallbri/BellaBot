package com.pudutech.mirsdk.hardware.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: CameraPreviewActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/mirsdk/hardware/activity/CameraPreviewActivity$startRGBPreview$1", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData$Stub;", "onFrame", "", "p0", "Landroid/os/ParcelFileDescriptor;", "p1", "", "p2", "p3", "p4", "p5", "", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CameraPreviewActivity$startRGBPreview$1 extends IMarkerCameraData.Stub {
    final /* synthetic */ CameraPreviewActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CameraPreviewActivity$startRGBPreview$1(CameraPreviewActivity cameraPreviewActivity) {
        this.this$0 = cameraPreviewActivity;
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [T, android.graphics.Bitmap] */
    @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
    public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
        if (p0 == null) {
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(p0.getFileDescriptor());
        byte[] bArr = new byte[p3];
        if (fileInputStream.read(bArr) > 12) {
            Pdlog.m3276v("CameraPreviewActivity", "on rgb camera frame width:" + p2 + " height:" + p1);
            int i = p1 * p2;
            int[] iArr = new int[i];
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2 * 3;
                iArr[i2] = Color.rgb(UByte.m4528constructorimpl(bArr[i3 + 2 + 12]) & 255, UByte.m4528constructorimpl(bArr[i3 + 1 + 12]) & 255, UByte.m4528constructorimpl(bArr[i3 + 12]) & 255);
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = Bitmap.createBitmap(iArr, p2, p1, Bitmap.Config.ARGB_8888);
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.hardware.activity.CameraPreviewActivity$startRGBPreview$1$onFrame$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public final void run() {
                    CameraPreviewActivity cameraPreviewActivity = CameraPreviewActivity$startRGBPreview$1.this.this$0;
                    Bitmap bmp = (Bitmap) objectRef.element;
                    Intrinsics.checkExpressionValueIsNotNull(bmp, "bmp");
                    cameraPreviewActivity.updateImage(bmp);
                }
            });
        }
    }
}
