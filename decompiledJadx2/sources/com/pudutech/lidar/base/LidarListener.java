package com.pudutech.lidar.base;

import androidx.core.app.NotificationCompat;
import com.pudutech.lidar.LidarNode;
import java.util.List;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: LidarListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/lidar/base/LidarListener;", "", "onOneFrameComplete", "", "list", "", "Lcom/pudutech/lidar/LidarNode;", "onProtocolError", NotificationCompat.CATEGORY_MESSAGE, "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface LidarListener {
    void onOneFrameComplete(List<? extends LidarNode> list);

    void onProtocolError(String msg);
}
