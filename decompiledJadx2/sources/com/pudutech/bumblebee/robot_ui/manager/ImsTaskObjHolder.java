package com.pudutech.bumblebee.robot_ui.manager;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: ImsTaskObjHolder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00068F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/ImsTaskObjHolder;", "", "()V", "remoteCallPointBuilder", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$CallPoint$Builder;", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "remoteMsg", "getRemoteMsg", "()Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "setRemoteMsg", "(Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;)V", "remoteTaskBuilder", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Task$Builder;", "buildCallPoint", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$CallPoint;", "callpointId", "", "type", "", "buildTask", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Task;", "taskId", "state", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ImsTaskObjHolder {
    public static final ImsTaskObjHolder INSTANCE = new ImsTaskObjHolder();
    private static MessageProtobuf.CallPoint.Builder remoteCallPointBuilder;
    private static MessageProtobuf.Msg remoteMsg;
    private static MessageProtobuf.Task.Builder remoteTaskBuilder;

    static {
        MessageProtobuf.Task.Builder newBuilder = MessageProtobuf.Task.newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(newBuilder, "MessageProtobuf.Task.newBuilder()");
        remoteTaskBuilder = newBuilder;
        MessageProtobuf.CallPoint.Builder newBuilder2 = MessageProtobuf.CallPoint.newBuilder();
        Intrinsics.checkExpressionValueIsNotNull(newBuilder2, "MessageProtobuf.CallPoint.newBuilder()");
        remoteCallPointBuilder = newBuilder2;
    }

    private ImsTaskObjHolder() {
    }

    public final MessageProtobuf.Task buildTask(String taskId, int state) {
        Intrinsics.checkParameterIsNotNull(taskId, "taskId");
        MessageProtobuf.Task build = remoteTaskBuilder.setTaskId(taskId).setState(state).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "remoteTaskBuilder.setTas…).setState(state).build()");
        return build;
    }

    public final MessageProtobuf.CallPoint buildCallPoint(String callpointId, int type) {
        Intrinsics.checkParameterIsNotNull(callpointId, "callpointId");
        MessageProtobuf.CallPoint build = remoteCallPointBuilder.setCallPointId(callpointId).setType(type).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "remoteCallPointBuilder.s…Id).setType(type).build()");
        return build;
    }

    public final synchronized MessageProtobuf.Msg getRemoteMsg() {
        return remoteMsg;
    }

    public final synchronized void setRemoteMsg(MessageProtobuf.Msg msg) {
        remoteMsg = msg;
        Pdlog.m3275i("ImsTaskObjHolder", "set value=" + msg);
    }
}
