package com.pudutech.bumblebee.robot.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.ParcelFileDescriptor;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/bumblebee/robot/activity/PeripheralsActivity$onMonocularCameraListener$1", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData$Stub;", "onFrame", "", TmpConstant.SERVICE_DESC, "Landroid/os/ParcelFileDescriptor;", "rows", "", "cols", "memorySize", "elementSize", "scan_time_stamp", "", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class PeripheralsActivity$onMonocularCameraListener$1 extends IMarkerCameraData.Stub {
    final /* synthetic */ PeripheralsActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PeripheralsActivity$onMonocularCameraListener$1(PeripheralsActivity peripheralsActivity) {
        this.this$0 = peripheralsActivity;
    }

    @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
    public void onFrame(ParcelFileDescriptor desc, int rows, int cols, int memorySize, int elementSize, long scan_time_stamp) {
        String str;
        String str2;
        String str3;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onMonocularCameraListener onFrame() desc = " + desc + ", rows = " + rows + ", cols = " + cols + ", memorySize = " + memorySize + ", elementSize = " + elementSize + ", scan_time_stamp = " + scan_time_stamp);
        if (desc == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        byte[] bArr = new byte[memorySize];
        if (new FileInputStream(desc.getFileDescriptor()).read(bArr) > 12) {
            PeripheralsActivity peripheralsActivity = this.this$0;
            peripheralsActivity.setIndex(peripheralsActivity.getIndex() + 1);
            str2 = this.this$0.TAG;
            Pdlog.m3273d(str2, "on monocular camera frame width = " + cols + ", height = " + rows);
            int i = rows * cols;
            int[] iArr = new int[i];
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2 * 3;
                iArr[i2] = Color.rgb(UByte.m4528constructorimpl(bArr[i3 + 2 + 12]) & 255, UByte.m4528constructorimpl(bArr[i3 + 1 + 12]) & 255, UByte.m4528constructorimpl(bArr[i3 + 12]) & 255);
            }
            Bitmap bitmap = Bitmap.createBitmap(iArr, cols, rows, Bitmap.Config.ARGB_8888);
            Matrix matrix = new Matrix();
            Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
            matrix.setRotate(90.0f, bitmap.getWidth() * 4.0f, bitmap.getHeight() * 4.0f);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new PeripheralsActivity$onMonocularCameraListener$1$onFrame$1(this, Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true), null), 2, null);
            long currentTimeMillis2 = System.currentTimeMillis();
            str3 = this.this$0.TAG;
            Pdlog.m3273d(str3, "needTime = " + (currentTimeMillis2 - currentTimeMillis) + "ms");
            if (this.this$0.getIndex() % 10 != 0) {
                return;
            }
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new PeripheralsActivity$onMonocularCameraListener$1$onFrame$2(this, bitmap, null), 3, null);
        }
    }
}
