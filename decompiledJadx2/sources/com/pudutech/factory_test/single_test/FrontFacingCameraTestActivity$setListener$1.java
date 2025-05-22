package com.pudutech.factory_test.single_test;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Ref;

/* compiled from: FrontFacingCameraTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/factory_test/single_test/FrontFacingCameraTestActivity$setListener$1", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData$Stub;", "onFrame", "", "p0", "Landroid/os/ParcelFileDescriptor;", "p1", "", "p2", "p3", "p4", "p5", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class FrontFacingCameraTestActivity$setListener$1 extends IMarkerCameraData.Stub {
    final /* synthetic */ FrontFacingCameraTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrontFacingCameraTestActivity$setListener$1(FrontFacingCameraTestActivity frontFacingCameraTestActivity) {
        this.this$0 = frontFacingCameraTestActivity;
    }

    /* JADX WARN: Type inference failed for: r7v6, types: [T, android.graphics.Bitmap] */
    @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
    public void onFrame(ParcelFileDescriptor p0, final int p1, final int p2, int p3, int p4, long p5) {
        String str;
        if (p0 == null) {
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(p0.getFileDescriptor());
        byte[] bArr = new byte[p3];
        if (fileInputStream.read(bArr) > 12) {
            str = this.this$0.TAG;
            Pdlog.m3276v(str, "on rgb camera frame width:" + p2 + " height:" + p1);
            int i = p1 * p2;
            int[] iArr = new int[i];
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2 * 3;
                iArr[i2] = Color.rgb(UByte.m4528constructorimpl(bArr[i3 + 2 + 12]) & 255, UByte.m4528constructorimpl(bArr[i3 + 1 + 12]) & 255, UByte.m4528constructorimpl(bArr[i3 + 12]) & 255);
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = Bitmap.createBitmap(iArr, p2, p1, Bitmap.Config.ARGB_8888);
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.FrontFacingCameraTestActivity$setListener$1$onFrame$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public final void run() {
                    if (FrontFacingCameraTestActivity$setListener$1.this.this$0.getIsOpen()) {
                        ((ImageView) FrontFacingCameraTestActivity$setListener$1.this.this$0._$_findCachedViewById(C4491R.id.imgCamera)).setImageBitmap((Bitmap) objectRef.element);
                    } else {
                        ((ImageView) FrontFacingCameraTestActivity$setListener$1.this.this$0._$_findCachedViewById(C4491R.id.imgCamera)).setImageBitmap(Bitmap.createBitmap(p2, p1, Bitmap.Config.ARGB_8888));
                    }
                    ((ImageView) FrontFacingCameraTestActivity$setListener$1.this.this$0._$_findCachedViewById(C4491R.id.imgCamera)).postInvalidate();
                }
            });
        }
    }
}
