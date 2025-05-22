package com.pudutech.mirsdk.robotsdk;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AccROS2Connection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016J \u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\u0004H\u0016¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/AccROS2Connection;", "Lcom/pudutech/mirsdk/robotsdk/AccConnectionInterface;", "()V", "callCloseDoor", "", "seq", "", "robotId", "accId", "callOpenDoor", MqttServiceConstants.CONNECT_ACTION, "listener", "Lcom/pudutech/mirsdk/robotsdk/AccROS2Connection$AccROS2MsgInterface;", MqttServiceConstants.DISCONNECT_ACTION, "AccROS2MsgInterface", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccROS2Connection implements AccConnectionInterface {

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: AccROS2Connection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/AccROS2Connection$AccROS2MsgInterface;", "", "accState", "", "state", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface AccROS2MsgInterface {
        void accState(boolean state);
    }

    @Override // com.pudutech.mirsdk.robotsdk.AccConnectionInterface
    public void callCloseDoor(String seq, String robotId, String accId) {
        Intrinsics.checkParameterIsNotNull(seq, "seq");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(accId, "accId");
    }

    @Override // com.pudutech.mirsdk.robotsdk.AccConnectionInterface
    public void callOpenDoor(String seq, String robotId, String accId) {
        Intrinsics.checkParameterIsNotNull(seq, "seq");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(accId, "accId");
    }

    public final void connect(AccROS2MsgInterface listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.pudutech.mirsdk.robotsdk.AccConnectionInterface
    public void disconnect() {
    }
}
