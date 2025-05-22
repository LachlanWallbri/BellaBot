package com.pudutech.lidar.base;

import kotlin.Metadata;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: SerialLidarInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/lidar/base/SerialLidarInterface;", "", MqttServiceConstants.SEND_ACTION, "", "data", "", "setDTR", "onOrOff", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface SerialLidarInterface {
    void send(byte[] data);

    void setDTR(boolean onOrOff);
}
