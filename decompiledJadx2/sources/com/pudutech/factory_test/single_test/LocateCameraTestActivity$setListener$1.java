package com.pudutech.factory_test.single_test;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.jvm.internal.Ref;

/* compiled from: LocateCameraTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/factory_test/single_test/LocateCameraTestActivity$setListener$1", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData$Stub;", "onFrame", "", "p0", "Landroid/os/ParcelFileDescriptor;", "p1", "", "p2", "p3", "p4", "p5", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LocateCameraTestActivity$setListener$1 extends IMarkerCameraData.Stub {
    final /* synthetic */ LocateCameraTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocateCameraTestActivity$setListener$1(LocateCameraTestActivity locateCameraTestActivity) {
        this.this$0 = locateCameraTestActivity;
    }

    /* JADX WARN: Type inference failed for: r8v6, types: [T, android.graphics.Bitmap] */
    @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
    public void onFrame(ParcelFileDescriptor p0, final int p1, final int p2, int p3, int p4, long p5) {
        String str;
        if (p0 == null) {
            return;
        }
        if (new FileInputStream(p0.getFileDescriptor()).read(new byte[p3]) > 12) {
            str = this.this$0.TAG;
            Pdlog.m3276v(str, "on marker camera frame width:" + p2 + " height:" + p1);
            int i = p1 * p2;
            int[] iArr = new int[i];
            for (int i2 = 0; i2 < i; i2++) {
                long m4528constructorimpl = UByte.m4528constructorimpl(r5[i2 + 12]) & 255;
                iArr[i2] = UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl((-16777216) | UInt.m4595constructorimpl((int) (m4528constructorimpl << 16))) | UInt.m4595constructorimpl((int) (m4528constructorimpl << 8))) | UInt.m4595constructorimpl((int) m4528constructorimpl));
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = Bitmap.createBitmap(iArr, p2, p1, Bitmap.Config.ARGB_8888);
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.LocateCameraTestActivity$setListener$1$onFrame$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public final void run() {
                    if (LocateCameraTestActivity$setListener$1.this.this$0.getIsOpen()) {
                        ((ImageView) LocateCameraTestActivity$setListener$1.this.this$0._$_findCachedViewById(C4491R.id.imgCamera)).setImageBitmap((Bitmap) objectRef.element);
                    } else {
                        ((ImageView) LocateCameraTestActivity$setListener$1.this.this$0._$_findCachedViewById(C4491R.id.imgCamera)).setImageBitmap(Bitmap.createBitmap(p2, p1, Bitmap.Config.ARGB_8888));
                    }
                    ((ImageView) LocateCameraTestActivity$setListener$1.this.this$0._$_findCachedViewById(C4491R.id.imgCamera)).postInvalidate();
                }
            });
        }
    }
}
