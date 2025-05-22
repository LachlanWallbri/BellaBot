package com.pudutech.bumblebee.robot_ui.manager;

import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.ims.IMSKit;
import com.pudutech.bumblebee.business.ims.config.CallPoint;
import com.pudutech.bumblebee.business.ims.config.MsgType;
import com.pudutech.bumblebee.business.ims.config.RobotStatus;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.hola_ims.IMSPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.robot_ui.config.RemoteTaskState;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: ImsPresenterHolder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\rJ\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u001a\u0010\u0019\u001a\u0016\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aj\n\u0012\u0004\u0012\u00020\u001b\u0018\u0001`\u001cJ\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020!Jk\u0010\"\u001a\u00020\r2%\u0010#\u001a!\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010$2<\u0010(\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(*\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010)Jk\u0010+\u001a\u00020\r2%\u0010#\u001a!\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010$2<\u0010(\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(*\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010)J\u000e\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020\u0004J\u000e\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u00020!J}\u00100\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162%\u0010#\u001a!\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010$2<\u0010(\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b(*\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010)H\u0002J\u0018\u00100\u001a\u00020\r2\u0006\u00101\u001a\u00020\u000f2\b\u00102\u001a\u0004\u0018\u00010\u0012J\u0012\u00103\u001a\u00020\r2\b\u0010'\u001a\u0004\u0018\u00010\u0012H\u0002J\u000e\u00104\u001a\u00020\r2\u0006\u0010'\u001a\u00020\u0012J\u000e\u00105\u001a\u00020\r2\u0006\u00101\u001a\u00020\u001fR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u00066"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/ImsPresenterHolder;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "imsPresenter", "Lcom/pudutech/bumblebee/presenter/hola_ims/IMSPresenter;", "getImsPresenter", "()Lcom/pudutech/bumblebee/presenter/hola_ims/IMSPresenter;", "imsPresenter$delegate", "Lkotlin/Lazy;", "changeReportInterval", "", "delayTime", "", "clearLocalCurrentTask", "createMsg", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "task", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Task;", "callPoint", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$CallPoint;", "createMsgBuilder", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg$Builder;", "getCallPointList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/business/ims/config/CallPoint;", "Lkotlin/collections/ArrayList;", "getMapVersionMD5", "getRobotState", "Lcom/pudutech/bumblebee/business/ims/config/RobotStatus;", "isReportingRobotStatus", "", "queryHasUnallocatedTask", "onSentSucceed", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", NotificationCompat.CATEGORY_MESSAGE, "onSentFailed", "Lkotlin/Function2;", "reason", "queryMapVersion", "removeMsg", "msgId", "reportRobotStatus", "isOpen", "reportTaskStatus", "state", "remoteMsg", "retryReportTaskState", "sendWatchMsg", "setRobotState", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ImsPresenterHolder {
    public static final ImsPresenterHolder INSTANCE;
    private static final String TAG;

    /* renamed from: imsPresenter$delegate, reason: from kotlin metadata */
    private static final Lazy imsPresenter;

    private final IMSPresenter getImsPresenter() {
        return (IMSPresenter) imsPresenter.getValue();
    }

    static {
        ImsPresenterHolder imsPresenterHolder = new ImsPresenterHolder();
        INSTANCE = imsPresenterHolder;
        TAG = imsPresenterHolder.getClass().getSimpleName();
        imsPresenter = LazyKt.lazy(new Function0<IMSPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.manager.ImsPresenterHolder$imsPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final IMSPresenter invoke() {
                IMSPresenter iMSPresenter;
                PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
                BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(IMSPresenter.class).toString());
                Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(IMSPresenter.class) + ' ' + basePresenterInterface);
                if (basePresenterInterface == null) {
                    iMSPresenter = new IMSPresenter();
                    presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(IMSPresenter.class).toString(), iMSPresenter);
                } else {
                    if (!(basePresenterInterface instanceof IMSPresenter)) {
                        basePresenterInterface = null;
                    }
                    iMSPresenter = (IMSPresenter) basePresenterInterface;
                }
                if (iMSPresenter != null) {
                    return iMSPresenter;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.hola_ims.IMSPresenter");
            }
        });
    }

    private ImsPresenterHolder() {
    }

    public final String getMapVersionMD5() {
        return getImsPresenter().getMapVersionMD5();
    }

    public final ArrayList<CallPoint> getCallPointList() {
        return getImsPresenter().getCallPointList();
    }

    public final boolean isReportingRobotStatus() {
        return getImsPresenter().isReportingRobotStatus();
    }

    public final void removeMsg(String msgId) {
        Intrinsics.checkParameterIsNotNull(msgId, "msgId");
        getImsPresenter().removeMsg(msgId);
    }

    public final void queryMapVersion(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        getImsPresenter().queryMapVersion(onSentSucceed, onSentFailed);
    }

    public final void reportRobotStatus(boolean isOpen) {
        getImsPresenter().reportRobotStatus(isOpen);
    }

    public final void changeReportInterval(int delayTime) {
        getImsPresenter().changeReportInterval(delayTime);
    }

    private final void reportTaskStatus(MessageProtobuf.Task task, MessageProtobuf.CallPoint callPoint, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        getImsPresenter().reportTaskStatus(task, callPoint, onSentSucceed, onSentFailed);
    }

    public final void reportTaskStatus(int state, MessageProtobuf.Msg remoteMsg) {
        MessageProtobuf.Msg remoteMsg2;
        MessageProtobuf.Task task;
        MessageProtobuf.CallPoint callPoint;
        MessageProtobuf.CallPoint callPoint2;
        MessageProtobuf.Task task2;
        Pdlog.m3273d(TAG, "try reportTaskStatus " + state);
        String str = null;
        String taskId = (remoteMsg == null || (task2 = remoteMsg.getTask()) == null) ? null : task2.getTaskId();
        if (remoteMsg != null && (callPoint2 = remoteMsg.getCallPoint()) != null) {
            str = callPoint2.getCallPointId();
        }
        int type = (remoteMsg == null || (callPoint = remoteMsg.getCallPoint()) == null) ? 1 : callPoint.getType();
        String str2 = taskId;
        if (!(str2 == null || str2.length() == 0)) {
            String str3 = str;
            if (!(str3 == null || str3.length() == 0)) {
                Pdlog.m3273d(TAG, "reportTaskStatus new taskState " + state + " msg " + remoteMsg);
                if (Intrinsics.areEqual(ImsTaskObjHolder.INSTANCE.getRemoteMsg(), remoteMsg) && ((remoteMsg2 = ImsTaskObjHolder.INSTANCE.getRemoteMsg()) == null || (task = remoteMsg2.getTask()) == null || task.getState() != state)) {
                    ImsTaskObjHolder.INSTANCE.setRemoteMsg(remoteMsg.toBuilder().setTask(MessageProtobuf.Task.newBuilder().setTaskId(taskId).setState(state).build()).build());
                }
                IMSKit.sendMsg$default(IMSKit.INSTANCE.getInstance(), remoteMsg, new Function1<MessageProtobuf.Msg, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.ImsPresenterHolder$reportTaskStatus$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MessageProtobuf.Msg msg) {
                        invoke2(msg);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MessageProtobuf.Msg it) {
                        String str4;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        ImsPresenterHolder imsPresenterHolder = ImsPresenterHolder.INSTANCE;
                        str4 = ImsPresenterHolder.TAG;
                        Pdlog.m3275i(str4, "sendArriveMsg: " + it);
                    }
                }, new Function2<MessageProtobuf.Msg, String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.ImsPresenterHolder$reportTaskStatus$2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(MessageProtobuf.Msg msg, String str4) {
                        invoke2(msg, str4);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MessageProtobuf.Msg msg, String reason) {
                        String str4;
                        Intrinsics.checkParameterIsNotNull(reason, "reason");
                        ImsPresenterHolder imsPresenterHolder = ImsPresenterHolder.INSTANCE;
                        str4 = ImsPresenterHolder.TAG;
                        Pdlog.m3275i(str4, "sendArriveError: " + msg);
                    }
                }, false, false, 24, null);
                return;
            }
        }
        Pdlog.m3273d(TAG, "reportTaskStatus taskId or callPointId is null or empty, taskId " + taskId + ", taskState " + state + ", callPointId " + str + ", callpointType: " + type);
    }

    public final void clearLocalCurrentTask() {
        MessageProtobuf.Task task;
        MessageProtobuf.Msg remoteMsg = ImsTaskObjHolder.INSTANCE.getRemoteMsg();
        if (remoteMsg == null || (task = remoteMsg.getTask()) == null || task.getState() != RemoteTaskState.FINISHED.getStateId()) {
            return;
        }
        Pdlog.m3273d(TAG, "reportTaskStatus current task has completely finished, just clear the local task msg ");
        ImsTaskObjHolder.INSTANCE.setRemoteMsg((MessageProtobuf.Msg) null);
    }

    private final void retryReportTaskState(MessageProtobuf.Msg msg) {
        if (msg != null) {
            MessageProtobuf.Task task = msg.getTask();
            Intrinsics.checkExpressionValueIsNotNull(task, "it.task");
            int state = task.getState();
            if (state == RemoteTaskState.PENDING.getStateId() || state == RemoteTaskState.CALLPOINTILLEGAL.getStateId()) {
                return;
            }
            ImsPresenterHolder imsPresenterHolder = INSTANCE;
            MessageProtobuf.Task task2 = msg.getTask();
            Intrinsics.checkExpressionValueIsNotNull(task2, "it.task");
            MessageProtobuf.CallPoint callPoint = msg.getCallPoint();
            Intrinsics.checkExpressionValueIsNotNull(callPoint, "it.callPoint");
            imsPresenterHolder.reportTaskStatus(task2, callPoint, new Function1<MessageProtobuf.Msg, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.ImsPresenterHolder$retryReportTaskState$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MessageProtobuf.Msg msg2) {
                    invoke2(msg2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MessageProtobuf.Msg msg2) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(msg2, "msg");
                    ImsPresenterHolder imsPresenterHolder2 = ImsPresenterHolder.INSTANCE;
                    str = ImsPresenterHolder.TAG;
                    Pdlog.m3273d(str, "retry ReportTaskState successfully msg  " + msg2);
                }
            }, new Function2<MessageProtobuf.Msg, String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.ImsPresenterHolder$retryReportTaskState$1$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(MessageProtobuf.Msg msg2, String str) {
                    invoke2(msg2, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MessageProtobuf.Msg msg2, String reason) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(reason, "reason");
                    ImsPresenterHolder imsPresenterHolder2 = ImsPresenterHolder.INSTANCE;
                    str = ImsPresenterHolder.TAG;
                    Pdlog.m3273d(str, "retry ReportTaskState failly msg " + msg2 + ", reason " + reason);
                }
            });
        }
    }

    public final void queryHasUnallocatedTask(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        getImsPresenter().queryHasUnallocatedTask(onSentSucceed, onSentFailed);
    }

    public final void setRobotState(RobotStatus state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Pdlog.m3273d(TAG, "setRobotState " + state);
        com.pudutech.bumblebee.business.ims.manager.RobotStatusManager.INSTANCE.getINSTANCE().setRobotStatus(state);
    }

    public final RobotStatus getRobotState() {
        return com.pudutech.bumblebee.business.ims.manager.RobotStatusManager.INSTANCE.getINSTANCE().getRobotStatus();
    }

    public final void sendWatchMsg(MessageProtobuf.Msg msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        getImsPresenter().sendWatchMsg(msg);
    }

    public final MessageProtobuf.Msg createMsg(MessageProtobuf.Task task, MessageProtobuf.CallPoint callPoint) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(callPoint, "callPoint");
        return getImsPresenter().createMsg(task, callPoint);
    }

    public final MessageProtobuf.Msg.Builder createMsgBuilder(MessageProtobuf.Task task, MessageProtobuf.CallPoint callPoint) {
        MessageProtobuf.Msg.Builder task2;
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(callPoint, "callPoint");
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = IMSKit.INSTANCE.getInstance().generateCommonMsgBuilder(MsgType.SyncTaskState);
        if (generateCommonMsgBuilder == null || (task2 = generateCommonMsgBuilder.setTask(task)) == null) {
            return null;
        }
        return task2.setCallPoint(callPoint);
    }
}
