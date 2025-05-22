package com.pudutech.factory_test.single_test;

import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.test_pack.TestCanvas;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LidarTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, m3961d2 = {"com/pudutech/factory_test/single_test/LidarTestActivity$setListener$1", "Lcom/pudutech/mirsdk/hardware/ILidarData$Stub;", "onFrame", "", "p0", "", "Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;", "p1", "", "([Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;J)V", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LidarTestActivity$setListener$1 extends ILidarData.Stub {
    final /* synthetic */ LidarTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LidarTestActivity$setListener$1(LidarTestActivity lidarTestActivity) {
        this.this$0 = lidarTestActivity;
    }

    @Override // com.pudutech.mirsdk.hardware.ILidarData
    public void onFrame(final PolarCoordinates[] p0, long p1) {
        String str;
        int i;
        int i2;
        int i3;
        int i4;
        String str2;
        int i5;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onFrame size=");
        sb.append(p0 != null ? Integer.valueOf(p0.length) : null);
        sb.append("  timestamp=");
        sb.append(p1);
        objArr[0] = sb.toString();
        Pdlog.m3276v(str, objArr);
        if (p0 != null) {
            int i6 = 0;
            for (PolarCoordinates polarCoordinates : p0) {
                if (polarCoordinates.getDistance_m() > 0.05d && polarCoordinates.getDistance_m() < 0.35d) {
                    i6++;
                }
            }
            LidarTestActivity lidarTestActivity = this.this$0;
            i = lidarTestActivity.frameCounter;
            lidarTestActivity.frameCounter = i + 1;
            LidarTestActivity lidarTestActivity2 = this.this$0;
            i2 = lidarTestActivity2.checkLidarSum;
            lidarTestActivity2.checkLidarSum = i2 + i6;
            LidarTestActivity lidarTestActivity3 = this.this$0;
            i3 = lidarTestActivity3.checkLidarSum;
            i4 = this.this$0.frameCounter;
            lidarTestActivity3.checkLidarAvg = i3 / i4;
            str2 = this.this$0.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("checkLidarAvg=");
            i5 = this.this$0.checkLidarAvg;
            sb2.append(i5);
            Pdlog.m3276v(str2, sb2.toString());
        }
        TestCanvas canvasLidar = (TestCanvas) this.this$0._$_findCachedViewById(C4491R.id.canvasLidar);
        Intrinsics.checkExpressionValueIsNotNull(canvasLidar, "canvasLidar");
        if (canvasLidar.getWidth() == 0) {
            return;
        }
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.LidarTestActivity$setListener$1$onFrame$2
            @Override // java.lang.Runnable
            public final void run() {
                TestCanvas canvasLidar2 = (TestCanvas) LidarTestActivity$setListener$1.this.this$0._$_findCachedViewById(C4491R.id.canvasLidar);
                Intrinsics.checkExpressionValueIsNotNull(canvasLidar2, "canvasLidar");
                float width = canvasLidar2.getWidth() / 2.0f;
                TestCanvas canvasLidar3 = (TestCanvas) LidarTestActivity$setListener$1.this.this$0._$_findCachedViewById(C4491R.id.canvasLidar);
                Intrinsics.checkExpressionValueIsNotNull(canvasLidar3, "canvasLidar");
                float height = canvasLidar3.getHeight() / 2.0f;
                LidarTestActivity$setListener$1.this.this$0.drawAxis();
                if (LidarTestActivity$setListener$1.this.this$0.getIsOpen()) {
                    PolarCoordinates[] polarCoordinatesArr = p0;
                    if (polarCoordinatesArr == null) {
                        Intrinsics.throwNpe();
                    }
                    for (PolarCoordinates polarCoordinates2 : polarCoordinatesArr) {
                        double d = 200.0f;
                        ((TestCanvas) LidarTestActivity$setListener$1.this.this$0._$_findCachedViewById(C4491R.id.canvasLidar)).getMCanvas().drawCircle((float) ((Math.sin(polarCoordinates2.getAngle_rad()) * polarCoordinates2.getDistance_m() * d) + width), (float) ((Math.cos(polarCoordinates2.getAngle_rad()) * polarCoordinates2.getDistance_m() * d) + height), 3.0f, ((TestCanvas) LidarTestActivity$setListener$1.this.this$0._$_findCachedViewById(C4491R.id.canvasLidar)).getPainter());
                    }
                }
                ((TestCanvas) LidarTestActivity$setListener$1.this.this$0._$_findCachedViewById(C4491R.id.canvasLidar)).refreshScreen();
            }
        });
    }
}
