package com.pudutech.mirsdk.robotsdk;

import kotlin.Metadata;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AccConnectionInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J \u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/AccConnectionInterface;", "", "callCloseDoor", "", "seq", "", "robotId", "accId", "callOpenDoor", MqttServiceConstants.DISCONNECT_ACTION, "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AccConnectionInterface {
    void callCloseDoor(String seq, String robotId, String accId);

    void callOpenDoor(String seq, String robotId, String accId);

    void disconnect();
}
