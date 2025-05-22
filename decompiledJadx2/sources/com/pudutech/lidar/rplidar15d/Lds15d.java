package com.pudutech.lidar.rplidar15d;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.rplidar50c.Lds50c;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Lds15d.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J:\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u000bH\u0014R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u000e8T@TX\u0094\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00148T@TX\u0094\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006 "}, m3961d2 = {"Lcom/pudutech/lidar/rplidar15d/Lds15d;", "Lcom/pudutech/lidar/rplidar50c/Lds50c;", "()V", ES6Iterator.VALUE_PROPERTY, "", "TAG", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "baudRate", "", "getBaudRate", "()I", "", "dataHead", "getDataHead", "()[B", "setDataHead", "([B)V", "", "isAdjustAngle", "()Z", "setAdjustAngle", "(Z)V", "relParser", "lidarLen", "data", "sum", "angle", "span", "len", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Lds15d extends Lds50c {
    @Override // com.pudutech.lidar.rplidar50c.Lds50c, com.pudutech.lidar.base.SerialLidar
    public int getBaudRate() {
        return 500000;
    }

    @Override // com.pudutech.lidar.rplidar50c.Lds50c
    protected byte[] getDataHead() {
        return new byte[]{(byte) 157, (byte) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION};
    }

    @Override // com.pudutech.lidar.rplidar50c.Lds50c, com.pudutech.lidar.base.BaseLidar
    public String getTAG() {
        return "LDS_15D";
    }

    @Override // com.pudutech.lidar.rplidar50c.Lds50c
    /* renamed from: isAdjustAngle */
    protected boolean getIsAdjustAngle() {
        return true;
    }

    public void setTAG(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        setTAG("LDS_15D");
    }

    @Override // com.pudutech.lidar.rplidar50c.Lds50c
    protected void setAdjustAngle(boolean z) {
        setAdjustAngle(true);
    }

    @Override // com.pudutech.lidar.rplidar50c.Lds50c
    protected void setDataHead(byte[] value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        setDataHead(new byte[]{(byte) 157, (byte) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION});
    }

    @Override // com.pudutech.lidar.rplidar50c.Lds50c
    protected int relParser(int lidarLen, byte[] data, int sum, int angle, int span, int len) {
        int i = sum;
        for (int i2 = 0; i2 < lidarLen; i2 += 2) {
            LidarNode obtain = LidarNode.obtain();
            if (data == null) {
                Intrinsics.throwNpe();
            }
            int i3 = ((data[i2 + 1] & 255) << 8) | (data[i2] & 255);
            i += i3;
            obtain.distanceM = i3 / 1000.0d;
            obtain.angleInRad = ((angle + ((span * (i2 / 2.0d)) / len)) * 3.141592653589793d) / 1800.0d;
            getNodes().add(obtain);
        }
        return i;
    }
}
