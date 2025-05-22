package com.pudutech.mirsdk.hardware.cameralib.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.cameralib.C5139R;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: CameraPreviewActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/mirsdk/hardware/cameralib/activity/CameraPreviewActivity$setPreCallback$1", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData$Stub;", "onFrame", "", "p0", "Landroid/os/ParcelFileDescriptor;", "p1", "", "p2", "p3", "p4", "p5", "", "MarkerCamera_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CameraPreviewActivity$setPreCallback$1 extends IMarkerCameraData.Stub {
    final /* synthetic */ CameraPreviewActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CameraPreviewActivity$setPreCallback$1(CameraPreviewActivity cameraPreviewActivity) {
        this.this$0 = cameraPreviewActivity;
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [T, android.graphics.Bitmap] */
    @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
    public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
        String str;
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        FileInputStream fileInputStream = new FileInputStream(p0.getFileDescriptor());
        byte[] bArr = new byte[p3];
        if (fileInputStream.read(bArr) > 12) {
            str = this.this$0.TAG;
            Pdlog.m3276v(str, "on camera frame width:" + p2 + " height:" + p1);
            int i = p1 * p2;
            int[] iArr = new int[i];
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2 * 3;
                iArr[i2] = Color.rgb(UByte.m4528constructorimpl(bArr[i3 + 2 + 12]) & 255, UByte.m4528constructorimpl(bArr[i3 + 1 + 12]) & 255, UByte.m4528constructorimpl(bArr[i3 + 12]) & 255);
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = Bitmap.createBitmap(iArr, p2, p1, Bitmap.Config.ARGB_8888);
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.hardware.cameralib.activity.CameraPreviewActivity$setPreCallback$1$onFrame$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public final void run() {
                    String str2;
                    str2 = CameraPreviewActivity$setPreCallback$1.this.this$0.TAG;
                    Pdlog.m3273d(str2, "update image");
                    CameraPreviewActivity cameraPreviewActivity = CameraPreviewActivity$setPreCallback$1.this.this$0;
                    ImageView imageView = (ImageView) CameraPreviewActivity$setPreCallback$1.this.this$0._$_findCachedViewById(C5139R.id.frontImage);
                    Bitmap bmp = (Bitmap) objectRef.element;
                    Intrinsics.checkExpressionValueIsNotNull(bmp, "bmp");
                    cameraPreviewActivity.updateImage(imageView, bmp);
                }
            });
        }
    }
}
