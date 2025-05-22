package com.pudutech.mirsdk.robotsdk;

import com.pudutech.mirsdk.elv.proto.Elevator;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElvROS2Connection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0012\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eJ \u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J\b\u0010\u0011\u001a\u00020\u0004H\u0016J(\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J(\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J0\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J \u0010\u0016\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J \u0010\u0017\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J(\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bH\u0002¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/ElvROS2Connection;", "Lcom/pudutech/mirsdk/robotsdk/ElevatorCommunicateInterface;", "()V", "callElv", "", "curr", "", "dst", "seq", "", "robotId", "elvId", MqttServiceConstants.CONNECT_ACTION, "listener", "Lcom/pudutech/mirsdk/robotsdk/ProtobufMsgListener;", "createElvMsgHead", "Lcom/pudutech/mirsdk/elv/proto/Elevator$Elv$Builder;", MqttServiceConstants.DISCONNECT_ACTION, "elvEntered", "elvLeft", "elvLeftState", "stat", "enterElvAck", "leaveElvAck", "prepareRide", "sendMsg", "data", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ElvROS2Connection implements ElevatorCommunicateInterface {
    private final void sendMsg(byte[] data) {
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void callElv(String curr, String dst, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
    }

    public final void connect(ProtobufMsgListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void elvEntered(String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void elvLeft(String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void elvLeftState(String stat, String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(stat, "stat");
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void enterElvAck(long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void leaveElvAck(long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void prepareRide(String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void disconnect() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    private final Elevator.Elv.Builder createElvMsgHead(long seq, String robotId, String elvId) {
        Elevator.Elv.Builder elv = Elevator.Elv.newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(elv, "elv");
        elv.setSeq(seq);
        elv.setTs(System.currentTimeMillis());
        elv.setRobotId(robotId);
        elv.setElvId(elvId);
        return elv;
    }
}
