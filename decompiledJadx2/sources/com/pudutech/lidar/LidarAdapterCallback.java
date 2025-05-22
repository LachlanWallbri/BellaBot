package com.pudutech.lidar;

import com.pudutech.lidar.base.LidarError;
import com.pudutech.remotemaintenance.config.IoTConfig;
import java.util.List;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: LidarAdapterCallback.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/lidar/LidarAdapterCallback;", "", "onError", "", IoTConfig.PARAM_ERROR_MSG, "Lcom/pudutech/lidar/base/LidarError;", "onOneFrame", "list", "", "Lcom/pudutech/lidar/LidarNode;", "onPowerRequest", "isOn", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface LidarAdapterCallback {
    void onError(LidarError errorMsg);

    void onOneFrame(List<? extends LidarNode> list);

    void onPowerRequest(boolean isOn);
}
