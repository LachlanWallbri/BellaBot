package com.pudutech.rgbdlib.activity;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import com.pudutech.rgbdlib.C5657R;
import com.pudutech.rgbdlib.RGBDData;
import com.pudutech.rgbdlib.RGBDDataCatcher;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* compiled from: CheckToolActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/rgbdlib/activity/CheckToolActivity$onCreate$3", "Lcom/pudutech/rgbdlib/RGBDDataCatcher;", "onFrameDescriptor", "", "p0", "Landroid/os/ParcelFileDescriptor;", "p1", "", "p2", "p3", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CheckToolActivity$onCreate$3 implements RGBDDataCatcher {
    final /* synthetic */ CheckToolActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CheckToolActivity$onCreate$3(CheckToolActivity checkToolActivity) {
        this.this$0 = checkToolActivity;
    }

    /* JADX WARN: Type inference failed for: r2v10, types: [T, android.graphics.Bitmap] */
    @Override // com.pudutech.rgbdlib.RGBDDataCatcher
    public void onFrameDescriptor(ParcelFileDescriptor p0, int p1, int p2, int p3) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
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
            int width = rGBDData.getWidth() * rGBDData.getHeight() * 4;
            final ByteBuffer allocate = ByteBuffer.allocate(width);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.put(bArr, serial_length, width);
            allocate.flip();
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = Bitmap.createBitmap(rGBDData.getWidth(), rGBDData.getHeight(), Bitmap.Config.ARGB_8888);
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.rgbdlib.activity.CheckToolActivity$onCreate$3$onFrameDescriptor$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public final void run() {
                    ((Bitmap) objectRef.element).copyPixelsFromBuffer(allocate.rewind());
                    ImageView imageView = (ImageView) CheckToolActivity$onCreate$3.this.this$0._$_findCachedViewById(C5657R.id.imageView);
                    if (imageView != null) {
                        imageView.setImageBitmap((Bitmap) objectRef.element);
                    }
                    ImageView imageView2 = (ImageView) CheckToolActivity$onCreate$3.this.this$0._$_findCachedViewById(C5657R.id.imageView);
                    if (imageView2 != null) {
                        imageView2.postInvalidate();
                    }
                }
            });
        }
    }
}
