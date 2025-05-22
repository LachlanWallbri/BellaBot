package com.pudutech.lidar.rplidar_15d;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.pudutech.lidar.rplidar_50c.LDS_50C;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.HttpStatus;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LDS_15D.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8T@TX\u0094\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e8T@TX\u0094\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/lidar/rplidar_15d/LDS_15D;", "Lcom/pudutech/lidar/rplidar_50c/LDS_50C;", "()V", "baudRate", "", "getBaudRate", "()I", ES6Iterator.VALUE_PROPERTY, "", "dataHead", "getDataHead", "()[B", "setDataHead", "([B)V", "", "isAdjustAngle", "()Z", "setAdjustAngle", "(Z)V", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LDS_15D extends LDS_50C {
    @Override // com.pudutech.lidar.rplidar_50c.LDS_50C, com.pudutech.lidar.base.SerialLidar
    public int getBaudRate() {
        return 1000000;
    }

    @Override // com.pudutech.lidar.rplidar_50c.LDS_50C
    protected byte[] getDataHead() {
        return new byte[]{(byte) HttpStatus.SC_MULTI_STATUS, (byte) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION};
    }

    @Override // com.pudutech.lidar.rplidar_50c.LDS_50C
    /* renamed from: isAdjustAngle */
    protected boolean getIsAdjustAngle() {
        return true;
    }

    @Override // com.pudutech.lidar.rplidar_50c.LDS_50C
    protected void setAdjustAngle(boolean z) {
        setAdjustAngle(true);
    }

    @Override // com.pudutech.lidar.rplidar_50c.LDS_50C
    protected void setDataHead(byte[] value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        setDataHead(new byte[]{(byte) HttpStatus.SC_MULTI_STATUS, (byte) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION});
    }
}
