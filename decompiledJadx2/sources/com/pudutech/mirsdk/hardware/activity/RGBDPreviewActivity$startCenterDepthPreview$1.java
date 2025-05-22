package com.pudutech.mirsdk.hardware.activity;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import androidx.core.view.ViewCompat;
import com.pudutech.mirsdk.hardware.ICenterRgbdData;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: RGBDPreviewActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/mirsdk/hardware/activity/RGBDPreviewActivity$startCenterDepthPreview$1", "Lcom/pudutech/mirsdk/hardware/ICenterRgbdData$Stub;", "onCenterFrameDescriptor", "", "p0", "Landroid/os/ParcelFileDescriptor;", "p1", "", "p2", "p3", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RGBDPreviewActivity$startCenterDepthPreview$1 extends ICenterRgbdData.Stub {
    final /* synthetic */ RGBDPreviewActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RGBDPreviewActivity$startCenterDepthPreview$1(RGBDPreviewActivity rGBDPreviewActivity) {
        this.this$0 = rGBDPreviewActivity;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [T, android.graphics.Bitmap] */
    @Override // com.pudutech.mirsdk.hardware.ICenterRgbdData
    public void onCenterFrameDescriptor(ParcelFileDescriptor p0, int p1, int p2, int p3) {
        int i;
        int i2;
        int i3;
        int i4;
        ArrayList arrayList;
        ArrayList arrayList2;
        int[][] iArr;
        int[][] iArr2;
        int[][] iArr3;
        if (p0 != null) {
            byte[] bArr = new byte[p3];
            int read = new FileInputStream(p0.getFileDescriptor()).read(bArr);
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            if (read > 64) {
                byteBuffer.put(bArr, 0, 64);
                byteBuffer.flip();
                Intrinsics.checkExpressionValueIsNotNull(byteBuffer, "byteBuffer");
                RGBDData rGBDData = new RGBDData(UInt.m4595constructorimpl(byteBuffer.getInt()), UInt.m4595constructorimpl(byteBuffer.getInt()), byteBuffer.getFloat(), byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getFloat(), byteBuffer.getFloat(), byteBuffer.getFloat(), byteBuffer.getFloat(), UInt.m4595constructorimpl(byteBuffer.getInt()), new float[]{byteBuffer.getFloat(), byteBuffer.getFloat(), byteBuffer.getFloat(), byteBuffer.getFloat(), byteBuffer.getFloat()}, byteBuffer.getInt(), null);
                int serial_length = rGBDData.getSerial_length() + 64;
                int width = rGBDData.getWidth() * rGBDData.getHeight();
                int[] iArr4 = new int[width];
                int i5 = width * 2;
                ByteBuffer byteBuffer2 = ByteBuffer.allocate(i5);
                byteBuffer2.order(ByteOrder.LITTLE_ENDIAN);
                byteBuffer2.put(bArr, serial_length, i5);
                byteBuffer2.flip();
                for (int i6 = 0; i6 < width; i6++) {
                    Intrinsics.checkExpressionValueIsNotNull(byteBuffer2, "byteBuffer");
                    short s = byteBuffer2.getShort();
                    i = this.this$0.minDepth;
                    int i7 = s - i;
                    if (i7 >= 0) {
                        arrayList = this.this$0.depthColorTable;
                        if (i7 < arrayList.size()) {
                            arrayList2 = this.this$0.depthColorTable;
                            Object obj = arrayList2.get(i7);
                            Intrinsics.checkExpressionValueIsNotNull(obj, "depthColorTable[depth]");
                            int intValue = ((Number) obj).intValue();
                            iArr = this.this$0.depthColorMap;
                            i3 = iArr[intValue][0];
                            iArr2 = this.this$0.depthColorMap;
                            i4 = iArr2[intValue][1];
                            iArr3 = this.this$0.depthColorMap;
                            i2 = iArr3[intValue][2];
                            iArr4[(width - i6) - 1] = UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(i2 << 16) | ViewCompat.MEASURED_STATE_MASK) | UInt.m4595constructorimpl(i4 << 8)) | UInt.m4595constructorimpl(i3));
                        }
                    }
                    i2 = 0;
                    i3 = 0;
                    i4 = 0;
                    iArr4[(width - i6) - 1] = UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(i2 << 16) | ViewCompat.MEASURED_STATE_MASK) | UInt.m4595constructorimpl(i4 << 8)) | UInt.m4595constructorimpl(i3));
                }
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = Bitmap.createBitmap(iArr4, rGBDData.getWidth(), rGBDData.getHeight(), Bitmap.Config.ARGB_8888);
                this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.hardware.activity.RGBDPreviewActivity$startCenterDepthPreview$1$onCenterFrameDescriptor$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.lang.Runnable
                    public final void run() {
                        RGBDPreviewActivity rGBDPreviewActivity = RGBDPreviewActivity$startCenterDepthPreview$1.this.this$0;
                        Bitmap bmp = (Bitmap) objectRef.element;
                        Intrinsics.checkExpressionValueIsNotNull(bmp, "bmp");
                        rGBDPreviewActivity.updateCenterImage(bmp);
                    }
                });
            }
        }
    }
}
