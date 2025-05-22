package com.pudutech.bumblebee.presenter.robot_open_task;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.base.ListenerList;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.business.movementTask.GoHomeTask;
import com.pudutech.bumblebee.business.movementTask.IdleTask;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryMode;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.delivery_task.ViewModel;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallFromType;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallNotificationBean;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.BeeperAction;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.BeeperTaskListener;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.CustomCallAction;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.CustomCallListener;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.OrderInfoListener;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.RemoteCommandListener;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.RemoteTaskListener;
import com.pudutech.leaselib.LeaseSdk;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.robot.opensdk.MsgContext;
import com.pudutech.robot.opensdk.RemoteConnectState;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import com.pudutech.robot.opensdk.bean.ActionCommand;
import com.pudutech.robot.opensdk.bean.ActionCommandBody;
import com.pudutech.robot.opensdk.bean.CallBody;
import com.pudutech.robot.opensdk.bean.CustomCallBody;
import com.pudutech.robot.opensdk.bean.CustomCallCancelBody;
import com.pudutech.robot.opensdk.bean.CustomCallCompleteBody;
import com.pudutech.robot.opensdk.bean.CustomCallContentBody;
import com.pudutech.robot.opensdk.bean.DeliveryOrder;
import com.pudutech.robot.opensdk.bean.DeliveryTaskBody;
import com.pudutech.robot.opensdk.bean.Destination;
import com.pudutech.robot.opensdk.bean.DestinationTask;
import com.pudutech.robot.opensdk.bean.RobotDeliveryTrayOrderBody;
import com.pudutech.robot.opensdk.bean.TrayDestinationTasks;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import com.pudutech.robot.opensdk.bean.pub.DeliveryTaskState;
import com.pudutech.robot.opensdk.bean.pub.DeliveryTraysTaskState;
import com.pudutech.robot.opensdk.bean.pub.DoorControlCommand;
import com.pudutech.robot.opensdk.bean.pub.GoState;
import com.pudutech.robot.opensdk.bean.pub.OrderIdData;
import com.pudutech.robot.opensdk.bean.pub.OrderState;
import com.pudutech.robot.opensdk.bean.pub.PubCustomCallData;
import com.pudutech.robot.opensdk.bean.pub.PubDeliveryMode;
import com.pudutech.robot.opensdk.bean.pub.PubDeliveryState;
import com.pudutech.robot.opensdk.bean.pub.PubDeliveryTaskStateData;
import com.pudutech.robot.opensdk.bean.pub.PubDoorControlDate;
import com.pudutech.robot.opensdk.bean.pub.PubOrderStateData;
import com.pudutech.robot.opensdk.bean.pub.PubRobotGoStateData;
import com.pudutech.robot.opensdk.bean.pub.PubUnbindDeviceData;
import com.pudutech.robot.opensdk.bean.resp.ErrorCode;
import com.pudutech.robot.opensdk.bean.resp.RespCustomCallCompleteResultBody;
import com.pudutech.robot.opensdk.bean.resp.RespResultBody;
import com.pudutech.robot.opensdk.bean.resp.RespRobotStateBody;
import com.pudutech.robot.opensdk.bean.resp.RobotPose;
import com.pudutech.robot.opensdk.bean.resp.RobotState;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack;
import com.pudutech.robot.opensdk.interf.IOnOpenSdkEventListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: RobotOpenManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ý\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005*\u0001&\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010=\u001a\u00020>2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\u0016\u0010@\u001a\u00020A2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J,\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u00042\f\u0010E\u001a\b\u0012\u0004\u0012\u00020G0F2\u0006\u0010H\u001a\u00020IJ\"\u0010J\u001a\u00020A2\b\u0010K\u001a\u0004\u0018\u00010L2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0002J\u000f\u0010Q\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\bRJ\u0006\u0010S\u001a\u00020\u0004J\u0010\u0010T\u001a\u00020\u00042\u0006\u0010M\u001a\u00020NH\u0002J\u0006\u0010U\u001a\u00020>J\u0015\u0010V\u001a\u00020A2\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\bWJ\u0012\u0010X\u001a\u00020>2\b\u0010K\u001a\u0004\u0018\u00010LH\u0002J\u0006\u0010Y\u001a\u00020>J\u0015\u0010Z\u001a\u00020A2\u0006\u0010[\u001a\u00020\\H\u0000¢\u0006\u0002\b]J\u0018\u0010^\u001a\u00020A2\u0006\u0010O\u001a\u00020P2\u0006\u0010_\u001a\u00020`H\u0002J\u0010\u0010a\u001a\u00020A2\u0006\u0010_\u001a\u00020bH\u0002J'\u0010c\u001a\u00020A2\b\u0010K\u001a\u0004\u0018\u00010L2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0000¢\u0006\u0002\bdJ&\u0010e\u001a\u00020A2\u0006\u0010f\u001a\u00020g2\n\b\u0002\u0010h\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010i\u001a\u0004\u0018\u00010\u0004J\u0014\u0010j\u001a\u00020A2\n\u0010k\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J\u001c\u0010l\u001a\u00020A2\n\u0010k\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010_\u001a\u00020mH\u0002J\u000e\u0010n\u001a\u00020A2\u0006\u0010o\u001a\u00020\u0004J\u0010\u0010p\u001a\u00020A2\u0006\u0010D\u001a\u00020\u0004H\u0002J\u0016\u0010q\u001a\u00020A2\u0006\u0010r\u001a\u00020>2\u0006\u0010Y\u001a\u00020>J\u000e\u0010s\u001a\u00020A2\u0006\u0010H\u001a\u00020tJ\u0010\u0010u\u001a\u00020A2\u0006\u0010v\u001a\u00020wH\u0002J\r\u0010x\u001a\u00020AH\u0000¢\u0006\u0002\byJ\u0017\u0010z\u001a\u00020A2\b\u0010H\u001a\u0004\u0018\u00010IH\u0000¢\u0006\u0002\b{R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0011\u001a\u0004\b\u001d\u0010\u0015R!\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u0011\u001a\u0004\b \u0010\u000fR\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0004\n\u0002\u0010'R!\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\u0011\u001a\u0004\b*\u0010\u0015R!\u0010,\u001a\b\u0012\u0004\u0012\u00020)0\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\u0011\u001a\u0004\b-\u0010\u000fR!\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b2\u0010\u0011\u001a\u0004\b1\u0010\u0015R!\u00103\u001a\b\u0012\u0004\u0012\u0002000\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u0010\u0011\u001a\u0004\b4\u0010\u000fR!\u00106\u001a\b\u0012\u0004\u0012\u0002070\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\u0011\u001a\u0004\b8\u0010\u0015R!\u0010:\u001a\b\u0012\u0004\u0012\u0002070\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\u0011\u001a\u0004\b;\u0010\u000f¨\u0006|"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotOpenManager;", "", "()V", "TAG", "", "TIME_OUT_CALL", "", "allCallTaskList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/robot/opensdk/MsgContext;", "Lcom/pudutech/robot/opensdk/bean/CallBody;", "beeperListenerList", "Lcom/pudutech/bumblebee/business/base/ListenerList;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/BeeperTaskListener;", "getBeeperListenerList", "()Lcom/pudutech/bumblebee/business/base/ListenerList;", "beeperListenerList$delegate", "Lkotlin/Lazy;", "beeperTaskListener", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "getBeeperTaskListener", "()Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "beeperTaskListener$delegate", "context", "Landroid/content/Context;", "currentCallTasks", "currentCustomCallTaskId", "customCallListener", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/CustomCallListener;", "getCustomCallListener", "customCallListener$delegate", "customCallListenerList", "getCustomCallListenerList", "customCallListenerList$delegate", "mainHandler", "Landroid/os/Handler;", "nextCustomCallTaskId", "onOpenSdkEventListener", "com/pudutech/bumblebee/presenter/robot_open_task/RobotOpenManager$onOpenSdkEventListener$1", "Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotOpenManager$onOpenSdkEventListener$1;", "orderInfoListener", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/OrderInfoListener;", "getOrderInfoListener", "orderInfoListener$delegate", "orderInfoListenerList", "getOrderInfoListenerList", "orderInfoListenerList$delegate", "remoteCommandListener", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/RemoteCommandListener;", "getRemoteCommandListener", "remoteCommandListener$delegate", "remoteCommandListenerList", "getRemoteCommandListenerList", "remoteCommandListenerList$delegate", "remoteTaskListener", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/RemoteTaskListener;", "getRemoteTaskListener", "remoteTaskListener$delegate", "remoteTaskListenerList", "getRemoteTaskListenerList", "remoteTaskListenerList$delegate", "addCallTask", "", NotificationCompat.CATEGORY_MESSAGE, "cancelCallTask", "", "deliveryOrderStart", "employees", TypedValues.Attributes.S_TARGET, "ids", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/opensdk/bean/pub/OrderIdData;", "callBack", "Lcom/pudutech/robot/opensdk/interf/ICallback;", "deliveryTaskNotify", "mode", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryMode;", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "viewModel", "Lcom/pudutech/bumblebee/presenter/delivery_task/ViewModel;", "getCanRunCallTask", "getCanRunCallTask$module_bumblebee_presenter_robotRelease", "getHost", "getPubDeliveryState", "getSwitch", "init", "init$module_bumblebee_presenter_robotRelease", "isDeliveryMode", "isLocal", "notifyCustomCall", "notificationBean", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallNotificationBean;", "notifyCustomCall$module_bumblebee_presenter_robotRelease", "notifyDeliveryOrder", "state", "Lcom/pudutech/robot/opensdk/bean/pub/OrderState;", "onNotifyCallTaskStatus", "Lcom/pudutech/robot/opensdk/bean/pub/GoState;", "onRobotDeliveryStatus", "onRobotDeliveryStatus$module_bumblebee_presenter_robotRelease", "pudDoorControl", "command", "Lcom/pudutech/robot/opensdk/bean/pub/DoorControlCommand;", "id", "ext", "replayMapData", "msgContext", "respRobotState", "Lcom/pudutech/robot/opensdk/bean/resp/RobotState;", "setHost", "host", "setRunningCallTask", "setSwitch", "sw", "startBindBeeper", "Lcom/pudutech/robot/opensdk/interf/IGenBindCodeCallBack;", "startTimeoutCall", "i", "", "stopTimeoutCall", "stopTimeoutCall$module_bumblebee_presenter_robotRelease", "unBindAllDevice", "unBindAllDevice$module_bumblebee_presenter_robotRelease", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotOpenManager {
    private static Context context;
    private static volatile String currentCustomCallTaskId;
    private static volatile String nextCustomCallTaskId;
    public static final RobotOpenManager INSTANCE = new RobotOpenManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final CopyOnWriteArrayList<MsgContext<CallBody>> allCallTaskList = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<MsgContext<CallBody>> currentCallTasks = new CopyOnWriteArrayList<>();

    /* renamed from: orderInfoListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy orderInfoListenerList = LazyKt.lazy(new Function0<ListenerList<OrderInfoListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$orderInfoListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<OrderInfoListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: remoteCommandListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy remoteCommandListenerList = LazyKt.lazy(new Function0<ListenerList<RemoteCommandListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$remoteCommandListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<RemoteCommandListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: remoteTaskListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy remoteTaskListenerList = LazyKt.lazy(new Function0<ListenerList<RemoteTaskListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$remoteTaskListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<RemoteTaskListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: beeperListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy beeperListenerList = LazyKt.lazy(new Function0<ListenerList<BeeperTaskListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$beeperListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<BeeperTaskListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: customCallListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy customCallListenerList = LazyKt.lazy(new Function0<ListenerList<CustomCallListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$customCallListenerList$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<CustomCallListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: orderInfoListener$delegate, reason: from kotlin metadata */
    private static final Lazy orderInfoListener = LazyKt.lazy(new Function0<BaseMultiListenerImpl<OrderInfoListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$orderInfoListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<OrderInfoListener> invoke() {
            ListenerList orderInfoListenerList2;
            orderInfoListenerList2 = RobotOpenManager.INSTANCE.getOrderInfoListenerList();
            return new BaseMultiListenerImpl<>(orderInfoListenerList2);
        }
    });

    /* renamed from: remoteCommandListener$delegate, reason: from kotlin metadata */
    private static final Lazy remoteCommandListener = LazyKt.lazy(new Function0<BaseMultiListenerImpl<RemoteCommandListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$remoteCommandListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<RemoteCommandListener> invoke() {
            ListenerList remoteCommandListenerList2;
            remoteCommandListenerList2 = RobotOpenManager.INSTANCE.getRemoteCommandListenerList();
            return new BaseMultiListenerImpl<>(remoteCommandListenerList2);
        }
    });

    /* renamed from: remoteTaskListener$delegate, reason: from kotlin metadata */
    private static final Lazy remoteTaskListener = LazyKt.lazy(new Function0<BaseMultiListenerImpl<RemoteTaskListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$remoteTaskListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<RemoteTaskListener> invoke() {
            ListenerList remoteTaskListenerList2;
            remoteTaskListenerList2 = RobotOpenManager.INSTANCE.getRemoteTaskListenerList();
            return new BaseMultiListenerImpl<>(remoteTaskListenerList2);
        }
    });

    /* renamed from: beeperTaskListener$delegate, reason: from kotlin metadata */
    private static final Lazy beeperTaskListener = LazyKt.lazy(new Function0<BaseMultiListenerImpl<BeeperTaskListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$beeperTaskListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<BeeperTaskListener> invoke() {
            ListenerList beeperListenerList2;
            beeperListenerList2 = RobotOpenManager.INSTANCE.getBeeperListenerList();
            return new BaseMultiListenerImpl<>(beeperListenerList2);
        }
    });

    /* renamed from: customCallListener$delegate, reason: from kotlin metadata */
    private static final Lazy customCallListener = LazyKt.lazy(new Function0<BaseMultiListenerImpl<CustomCallListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$customCallListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseMultiListenerImpl<CustomCallListener> invoke() {
            ListenerList customCallListenerList2;
            customCallListenerList2 = RobotOpenManager.INSTANCE.getCustomCallListenerList();
            return new BaseMultiListenerImpl<>(customCallListenerList2);
        }
    });
    private static final int TIME_OUT_CALL = 10001;
    private static final Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$mainHandler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            CopyOnWriteArrayList copyOnWriteArrayList;
            String str;
            ListenerList beeperListenerList2;
            int i2 = message.what;
            RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
            i = RobotOpenManager.TIME_OUT_CALL;
            if (i2 == i) {
                RobotOpenManager robotOpenManager2 = RobotOpenManager.INSTANCE;
                copyOnWriteArrayList = RobotOpenManager.allCallTaskList;
                if (copyOnWriteArrayList.size() > 0) {
                    final String canRunCallTask$module_bumblebee_presenter_robotRelease = RobotOpenManager.INSTANCE.getCanRunCallTask$module_bumblebee_presenter_robotRelease();
                    String str2 = canRunCallTask$module_bumblebee_presenter_robotRelease;
                    if (!(str2 == null || str2.length() == 0)) {
                        RobotOpenManager robotOpenManager3 = RobotOpenManager.INSTANCE;
                        str = RobotOpenManager.TAG;
                        Pdlog.m3273d(str, "TIME_OUT_CALL : canRunCallTask = " + canRunCallTask$module_bumblebee_presenter_robotRelease);
                        beeperListenerList2 = RobotOpenManager.INSTANCE.getBeeperListenerList();
                        beeperListenerList2.forEach(new Function1<BeeperTaskListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$mainHandler$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(BeeperTaskListener beeperTaskListener2) {
                                invoke2(beeperTaskListener2);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(BeeperTaskListener it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                it.onTask(BeeperAction.CALL, canRunCallTask$module_bumblebee_presenter_robotRelease, CallFromType.OPEN_API);
                            }
                        });
                    }
                }
            }
            return true;
        }
    });
    private static final RobotOpenManager$onOpenSdkEventListener$1 onOpenSdkEventListener = new IOnOpenSdkEventListener() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.pudutech.robot.opensdk.interf.IOnOpenSdkEventListener
        public void onEvent(final MsgContext<?> msgContext) {
            String str;
            ListenerList customCallListenerList2;
            String str2;
            String str3;
            ListenerList customCallListenerList3;
            String str4;
            ListenerList customCallListenerList4;
            String str5;
            String str6;
            ListenerList customCallListenerList5;
            String str7;
            String str8;
            ListenerList remoteTaskListenerList2;
            ListenerList remoteCommandListenerList2;
            ListenerList orderInfoListenerList2;
            String str9;
            String str10;
            String str11;
            Intrinsics.checkParameterIsNotNull(msgContext, "msgContext");
            RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
            str = RobotOpenManager.TAG;
            boolean z = true;
            Pdlog.m3273d(str, "onEvent : msgContext = " + msgContext + "; ");
            if (LeaseSdk.INSTANCE.checkIsOverdue()) {
                RobotOpenManager robotOpenManager2 = RobotOpenManager.INSTANCE;
                str11 = RobotOpenManager.TAG;
                Pdlog.m3274e(str11, "onOpenSdkEventListener : robot Lease overdue , do not operate open msg");
                return;
            }
            String msgType = msgContext.getMsgType();
            final ActionCommand actionCommand = null;
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CALL())) {
                if (RobotOpenManager.INSTANCE.getSwitch()) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RobotOpenManager$onOpenSdkEventListener$1$onEvent$1(msgContext, null), 2, null);
                    return;
                } else {
                    RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(false, Integer.valueOf(ErrorCode.getCALL_FAILED_DISABLE()), null, 4, null), null, 4, null);
                    return;
                }
            }
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CANCEL_CALL())) {
                RobotOpenManager.INSTANCE.cancelCallTask(msgContext);
                RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(true, null, null, 6, null), null, 4, null);
                return;
            }
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_REQUEST_DATA())) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RobotOpenManager$onOpenSdkEventListener$1$onEvent$2(msgContext, null), 2, null);
                return;
            }
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_QUERY_STATE())) {
                if (Behavior.INSTANCE.isHaveLocked()) {
                    RobotOpenManager.INSTANCE.respRobotState(msgContext, RobotState.Busy);
                    RobotOpenManager robotOpenManager3 = RobotOpenManager.INSTANCE;
                    str10 = RobotOpenManager.TAG;
                    Pdlog.m3273d(str10, "HaveLocked:" + Behavior.INSTANCE.isHaveLocked());
                    return;
                }
                if (((Behavior.INSTANCE.getMovementTask() instanceof IdleTask) || (Behavior.INSTANCE.getMovementTask() instanceof GoHomeTask)) && CoreDevices.INSTANCE.getBattery().getChargerState() == ChargeState.Idle) {
                    RobotOpenManager robotOpenManager4 = RobotOpenManager.INSTANCE;
                    str9 = RobotOpenManager.currentCustomCallTaskId;
                    if (str9 == null && NotCanCallActivityLifecycle.INSTANCE.isNoCanCall$module_bumblebee_presenter_robotRelease() == 0) {
                        if (!FreezeStateManger.INSTANCE.isFreezeState()) {
                            RobotOpenManager.INSTANCE.respRobotState(msgContext, RobotState.Free);
                            return;
                        } else {
                            RobotOpenManager.INSTANCE.respRobotState(msgContext, RobotState.Busy);
                            return;
                        }
                    }
                }
                RobotOpenManager.INSTANCE.respRobotState(msgContext, RobotState.Busy);
                return;
            }
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_DELEVERY_TRAY_ORDER())) {
                RobotDeliveryTrayOrderBody robotDeliveryTrayOrderBody = (RobotDeliveryTrayOrderBody) msgContext.getReqData();
                ArrayList<DeliveryOrder> orders = robotDeliveryTrayOrderBody != null ? robotDeliveryTrayOrderBody.getOrders() : null;
                if (orders != null) {
                    final ArrayList arrayList = new ArrayList();
                    for (DeliveryOrder deliveryOrder : orders) {
                        arrayList.add(new InformationSystemContract.OrderInfo(deliveryOrder.getTableNo(), deliveryOrder.getTableName(), deliveryOrder.getName(), deliveryOrder.getAmount(), deliveryOrder.getId(), msgContext.getTarget(), null, 0L, InformationSystemContract.OrderInfo.Type.TrayOrder, 192, null));
                    }
                    final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                    booleanRef.element = false;
                    orderInfoListenerList2 = RobotOpenManager.INSTANCE.getOrderInfoListenerList();
                    orderInfoListenerList2.forEach(new Function1<OrderInfoListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$4
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(OrderInfoListener orderInfoListener2) {
                            invoke2(orderInfoListener2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(OrderInfoListener it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            ArrayList<InformationSystemContract.OrderInfo> arrayList2 = arrayList;
                            RobotDeliveryTrayOrderBody robotDeliveryTrayOrderBody2 = (RobotDeliveryTrayOrderBody) msgContext.getReqData();
                            if (it.onReceiveOrder(arrayList2, robotDeliveryTrayOrderBody2 != null ? robotDeliveryTrayOrderBody2.getTrayIndex() : 0)) {
                                booleanRef.element = true;
                            }
                        }
                    });
                    RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(booleanRef.element, null, null, 6, null), null, 4, null);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_ACTION_COMMAND())) {
                Object reqData = msgContext.getReqData();
                if (reqData == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.ActionCommandBody");
                }
                String action = ((ActionCommandBody) reqData).getAction();
                if (Intrinsics.areEqual(action, ActionCommand.START.getId())) {
                    actionCommand = ActionCommand.START;
                } else if (Intrinsics.areEqual(action, ActionCommand.COMPLETE.getId())) {
                    actionCommand = ActionCommand.COMPLETE;
                } else if (Intrinsics.areEqual(action, ActionCommand.CANCEL_ALL_DELIVERY.getId())) {
                    actionCommand = ActionCommand.CANCEL_ALL_DELIVERY;
                }
                if (actionCommand != null) {
                    final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                    booleanRef2.element = false;
                    remoteCommandListenerList2 = RobotOpenManager.INSTANCE.getRemoteCommandListenerList();
                    remoteCommandListenerList2.forEach(new Function1<RemoteCommandListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$5$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(RemoteCommandListener remoteCommandListener2) {
                            invoke2(remoteCommandListener2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(RemoteCommandListener it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            if (it.onAction(ActionCommand.this)) {
                                booleanRef2.element = true;
                            }
                        }
                    });
                    RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(booleanRef2.element, null, null, 6, null), null, 4, null);
                    Unit unit = Unit.INSTANCE;
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_DELIVERY_TASK())) {
                Object reqData2 = msgContext.getReqData();
                if (reqData2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.DeliveryTaskBody");
                }
                final DeliveryTaskBody deliveryTaskBody = (DeliveryTaskBody) reqData2;
                Iterator<T> it = deliveryTaskBody.getTrays().iterator();
                byte b = false;
                while (it.hasNext()) {
                    Iterator<T> it2 = ((TrayDestinationTasks) it.next()).getDestinations().iterator();
                    while (it2.hasNext()) {
                        if (!RobotMapManager.INSTANCE.checkDestinationExist(((DestinationTask) it2.next()).getDestination())) {
                            b = true;
                        }
                    }
                }
                if (b != false) {
                    RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(false, 10001, null, 4, null), null, 4, null);
                    return;
                }
                final Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
                booleanRef3.element = false;
                remoteTaskListenerList2 = RobotOpenManager.INSTANCE.getRemoteTaskListenerList();
                remoteTaskListenerList2.forEach(new Function1<RemoteTaskListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$7
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(RemoteTaskListener remoteTaskListener2) {
                        invoke2(remoteTaskListener2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(RemoteTaskListener it3) {
                        Intrinsics.checkParameterIsNotNull(it3, "it");
                        if (it3.onDeliveryTask(DeliveryTaskBody.this)) {
                            booleanRef3.element = true;
                        }
                    }
                });
                RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(booleanRef3.element, null, null, 6, null), null, 4, null);
                return;
            }
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_GET_ROBOT_CURRENT_MAP())) {
                RobotOpenManager.INSTANCE.replayMapData(msgContext);
                return;
            }
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CUSTOM_CALL())) {
                if (Behavior.INSTANCE.isHaveLocked()) {
                    RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(false, null, null, 6, null), null, 4, null);
                    RobotOpenManager robotOpenManager5 = RobotOpenManager.INSTANCE;
                    str8 = RobotOpenManager.TAG;
                    Pdlog.m3273d(str8, "MSG_TYPE_CUSTOM_CALL###HaveLocked:" + Behavior.INSTANCE.isHaveLocked());
                    return;
                }
                if (NotCanCallActivityLifecycle.INSTANCE.isNoCanCall$module_bumblebee_presenter_robotRelease() == 0 && (((Behavior.INSTANCE.getMovementTask() instanceof IdleTask) || (Behavior.INSTANCE.getMovementTask() instanceof GoHomeTask)) && CoreDevices.INSTANCE.getBattery().getChargerState() == ChargeState.Idle)) {
                    RobotOpenManager robotOpenManager6 = RobotOpenManager.INSTANCE;
                    str6 = RobotOpenManager.currentCustomCallTaskId;
                    if (str6 == null) {
                        RobotOpenManager robotOpenManager7 = RobotOpenManager.INSTANCE;
                        RobotOpenManager.currentCustomCallTaskId = UUID.randomUUID().toString();
                        Object reqData3 = msgContext.getReqData();
                        if (reqData3 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.CustomCallBody");
                        }
                        final CustomCallBody customCallBody = (CustomCallBody) reqData3;
                        if (RobotMapManager.INSTANCE.checkDestinationExist(customCallBody.getDestination().getName())) {
                            customCallListenerList5 = RobotOpenManager.INSTANCE.getCustomCallListenerList();
                            customCallListenerList5.forEach(new Function1<CustomCallListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$8
                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(CustomCallListener customCallListener2) {
                                    invoke2(customCallListener2);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(CustomCallListener it3) {
                                    Intrinsics.checkParameterIsNotNull(it3, "it");
                                    CustomCallListener.DefaultImpls.onAction$default(it3, CustomCallAction.CALL, CustomCallBody.this, null, 4, null);
                                }
                            });
                            RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
                            RobotOpenManager robotOpenManager8 = RobotOpenManager.INSTANCE;
                            str7 = RobotOpenManager.currentCustomCallTaskId;
                            RobotOpenSdk.responseMsg$default(robotOpenSdk, msgContext, new RespResultBody(true, null, str7, 2, null), null, 4, null);
                            return;
                        }
                        RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(false, null, null, 6, null), null, 4, null);
                        return;
                    }
                }
                RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(false, null, null, 6, null), null, 4, null);
                return;
            }
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CUSTOM_CALL_CANCEL())) {
                Object reqData4 = msgContext.getReqData();
                if (reqData4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.CustomCallCancelBody");
                }
                final CustomCallCancelBody customCallCancelBody = (CustomCallCancelBody) reqData4;
                String taskId = customCallCancelBody.getTaskId();
                if (taskId != null && taskId.length() != 0) {
                    z = false;
                }
                if (!z) {
                    String taskId2 = customCallCancelBody.getTaskId();
                    RobotOpenManager robotOpenManager9 = RobotOpenManager.INSTANCE;
                    str5 = RobotOpenManager.currentCustomCallTaskId;
                    if (!Intrinsics.areEqual(taskId2, str5)) {
                        RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(false, null, null, 6, null), null, 4, null);
                        return;
                    }
                }
                customCallListenerList4 = RobotOpenManager.INSTANCE.getCustomCallListenerList();
                customCallListenerList4.forEach(new Function1<CustomCallListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$9
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(CustomCallListener customCallListener2) {
                        invoke2(customCallListener2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(CustomCallListener it3) {
                        Intrinsics.checkParameterIsNotNull(it3, "it");
                        CustomCallListener.DefaultImpls.onAction$default(it3, CustomCallAction.CANCEL, CustomCallCancelBody.this, null, 4, null);
                    }
                });
                RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(true, null, null, 6, null), null, 4, null);
                RobotOpenManager robotOpenManager10 = RobotOpenManager.INSTANCE;
                String str12 = (String) null;
                RobotOpenManager.currentCustomCallTaskId = str12;
                RobotOpenManager robotOpenManager11 = RobotOpenManager.INSTANCE;
                RobotOpenManager.nextCustomCallTaskId = str12;
                return;
            }
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CUSTOM_CALL_CONTENT())) {
                Object reqData5 = msgContext.getReqData();
                if (reqData5 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.CustomCallContentBody");
                }
                final CustomCallContentBody customCallContentBody = (CustomCallContentBody) reqData5;
                String taskId3 = customCallContentBody.getTaskId();
                if (taskId3 != null && taskId3.length() != 0) {
                    z = false;
                }
                if (!z) {
                    String taskId4 = customCallContentBody.getTaskId();
                    RobotOpenManager robotOpenManager12 = RobotOpenManager.INSTANCE;
                    str4 = RobotOpenManager.currentCustomCallTaskId;
                    if (!Intrinsics.areEqual(taskId4, str4)) {
                        RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(false, null, null, 6, null), null, 4, null);
                        return;
                    }
                }
                customCallListenerList3 = RobotOpenManager.INSTANCE.getCustomCallListenerList();
                customCallListenerList3.forEach(new Function1<CustomCallListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$10
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(CustomCallListener customCallListener2) {
                        invoke2(customCallListener2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(CustomCallListener it3) {
                        Intrinsics.checkParameterIsNotNull(it3, "it");
                        it3.onAction(CustomCallAction.CONTENT, CustomCallContentBody.this, new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$10.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z2) {
                                RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespResultBody(z2, null, null, 6, null), null, 4, null);
                            }
                        });
                    }
                });
                return;
            }
            if (Intrinsics.areEqual(msgType, Constant.INSTANCE.getMSG_TYPE_CUSTOM_CALL_COMPLETE())) {
                Object reqData6 = msgContext.getReqData();
                if (reqData6 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.CustomCallCompleteBody");
                }
                final CustomCallCompleteBody customCallCompleteBody = (CustomCallCompleteBody) reqData6;
                String taskId5 = customCallCompleteBody.getTaskId();
                if (taskId5 != null && taskId5.length() != 0) {
                    z = false;
                }
                if (!z) {
                    String taskId6 = customCallCompleteBody.getTaskId();
                    RobotOpenManager robotOpenManager13 = RobotOpenManager.INSTANCE;
                    str3 = RobotOpenManager.currentCustomCallTaskId;
                    if (!Intrinsics.areEqual(taskId6, str3)) {
                        RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, new RespCustomCallCompleteResultBody(false, null, null, 6, null), null, 4, null);
                        return;
                    }
                }
                CustomCallBody nextCallTask = customCallCompleteBody.getNextCallTask();
                final RespCustomCallCompleteResultBody respCustomCallCompleteResultBody = new RespCustomCallCompleteResultBody(false, null, null, 6, null);
                if (nextCallTask != null) {
                    boolean checkDestinationExist = RobotMapManager.INSTANCE.checkDestinationExist(nextCallTask.getDestination().getName());
                    RespResultBody respResultBody = new RespResultBody(checkDestinationExist, null, null, 6, null);
                    if (checkDestinationExist) {
                        RobotOpenManager robotOpenManager14 = RobotOpenManager.INSTANCE;
                        RobotOpenManager.nextCustomCallTaskId = UUID.randomUUID().toString();
                        RobotOpenManager robotOpenManager15 = RobotOpenManager.INSTANCE;
                        str2 = RobotOpenManager.nextCustomCallTaskId;
                        respResultBody.setTaskId(str2);
                    }
                    respCustomCallCompleteResultBody.setNextTaskResult(respResultBody);
                }
                customCallListenerList2 = RobotOpenManager.INSTANCE.getCustomCallListenerList();
                customCallListenerList2.forEach(new Function1<CustomCallListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$11
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(CustomCallListener customCallListener2) {
                        invoke2(customCallListener2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(CustomCallListener it3) {
                        Intrinsics.checkParameterIsNotNull(it3, "it");
                        it3.onAction(CustomCallAction.COMPLETE, CustomCallCompleteBody.this, new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$onOpenSdkEventListener$1$onEvent$11.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                invoke(bool.booleanValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z2) {
                                String str13;
                                respCustomCallCompleteResultBody.setSuccess(z2);
                                RobotOpenManager robotOpenManager16 = RobotOpenManager.INSTANCE;
                                str13 = RobotOpenManager.TAG;
                                Pdlog.m3273d(str13, "onEvent MSG_TYPE_CUSTOM_CALL_COMPLETE respCustomCallCompleteResultBody: " + respCustomCallCompleteResultBody);
                                RobotOpenSdk.responseMsg$default(RobotOpenSdk.INSTANCE, msgContext, respCustomCallCompleteResultBody, null, 4, null);
                            }
                        });
                    }
                });
            }
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TaskStatus.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TaskStatus.ON_THE_WAY.ordinal()] = 1;
            $EnumSwitchMapping$0[TaskStatus.DONE.ordinal()] = 2;
            $EnumSwitchMapping$0[TaskStatus.DONE_BEFORE_ARRIVAL.ordinal()] = 3;
            $EnumSwitchMapping$0[TaskStatus.CANCEL.ordinal()] = 4;
            $EnumSwitchMapping$0[TaskStatus.ARRIVAL.ordinal()] = 5;
            $EnumSwitchMapping$1 = new int[DeliveryMode.values().length];
            $EnumSwitchMapping$1[DeliveryMode.GENERAL.ordinal()] = 1;
            $EnumSwitchMapping$1[DeliveryMode.DIRECT.ordinal()] = 2;
            $EnumSwitchMapping$1[DeliveryMode.BIRTHDAY.ordinal()] = 3;
            $EnumSwitchMapping$1[DeliveryMode.SPECIAL.ordinal()] = 4;
            $EnumSwitchMapping$2 = new int[TaskStatus.values().length];
            $EnumSwitchMapping$2[TaskStatus.AWAIT.ordinal()] = 1;
            $EnumSwitchMapping$2[TaskStatus.ON_THE_WAY.ordinal()] = 2;
            $EnumSwitchMapping$2[TaskStatus.APPROACHING.ordinal()] = 3;
            $EnumSwitchMapping$2[TaskStatus.ARRIVAL.ordinal()] = 4;
            $EnumSwitchMapping$2[TaskStatus.DONE.ordinal()] = 5;
            $EnumSwitchMapping$2[TaskStatus.CANCEL.ordinal()] = 6;
            $EnumSwitchMapping$2[TaskStatus.DONE_BEFORE_ARRIVAL.ordinal()] = 7;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<BeeperTaskListener> getBeeperListenerList() {
        return (ListenerList) beeperListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<CustomCallListener> getCustomCallListenerList() {
        return (ListenerList) customCallListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<OrderInfoListener> getOrderInfoListenerList() {
        return (ListenerList) orderInfoListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<RemoteCommandListener> getRemoteCommandListenerList() {
        return (ListenerList) remoteCommandListenerList.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<RemoteTaskListener> getRemoteTaskListenerList() {
        return (ListenerList) remoteTaskListenerList.getValue();
    }

    public final BaseMultiListenerImpl<BeeperTaskListener> getBeeperTaskListener() {
        return (BaseMultiListenerImpl) beeperTaskListener.getValue();
    }

    public final BaseMultiListenerImpl<CustomCallListener> getCustomCallListener() {
        return (BaseMultiListenerImpl) customCallListener.getValue();
    }

    public final BaseMultiListenerImpl<OrderInfoListener> getOrderInfoListener() {
        return (BaseMultiListenerImpl) orderInfoListener.getValue();
    }

    public final BaseMultiListenerImpl<RemoteCommandListener> getRemoteCommandListener() {
        return (BaseMultiListenerImpl) remoteCommandListener.getValue();
    }

    public final BaseMultiListenerImpl<RemoteTaskListener> getRemoteTaskListener() {
        return (BaseMultiListenerImpl) remoteTaskListener.getValue();
    }

    private RobotOpenManager() {
    }

    public final void notifyCustomCall$module_bumblebee_presenter_robotRelease(CustomCallNotificationBean notificationBean) {
        Intrinsics.checkParameterIsNotNull(notificationBean, "notificationBean");
        String str = currentCustomCallTaskId;
        if (str != null) {
            RobotOpenSdk.INSTANCE.publishMsg(new PubCustomCallData(notificationBean.getState().name(), notificationBean.getDestination(), notificationBean.getType().name(), str), new ICallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$notifyCustomCall$1$1
                @Override // com.pudutech.robot.opensdk.interf.ICallback
                public void onSuccess(IBody result) {
                    String str2;
                    RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                    str2 = RobotOpenManager.TAG;
                    Pdlog.m3273d(str2, "onSuccess: result=" + result);
                }

                @Override // com.pudutech.robot.opensdk.interf.ICallback
                public void onFailed(Exception e) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(e, "e");
                    RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                    str2 = RobotOpenManager.TAG;
                    Pdlog.m3273d(str2, "onFailed: e=" + e);
                }
            });
        }
        if (notificationBean.getState() == CustomCallState.Cancel || notificationBean.getState() == CustomCallState.Complete) {
            currentCustomCallTaskId = nextCustomCallTaskId;
            nextCustomCallTaskId = (String) null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void respRobotState(MsgContext<?> msgContext, RobotState state) {
        String name;
        Pdlog.m3273d(TAG, "respRobotState--- " + msgContext + "#### RobotState----" + state.name());
        RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
        String state2 = state.getState();
        String name2 = RobotOpenHelper.INSTANCE.getRobotMoveStateNotify().getRobotState().name();
        ChargeState chargerState = CoreDevices.INSTANCE.getBattery().getChargerState();
        if (chargerState == null || (name = chargerState.name()) == null) {
            name = ChargeState.Idle.name();
        }
        RobotOpenSdk.responseMsg$default(robotOpenSdk, msgContext, new RespRobotStateBody(state2, name2, name, CoreDevices.INSTANCE.getBattery().getPowerPercent(), new RobotPose(RobotOpenHelper.INSTANCE.getRobotPoseNotify().getX(), RobotOpenHelper.INSTANCE.getRobotPoseNotify().getY(), RobotOpenHelper.INSTANCE.getRobotPoseNotify().getAngle())), null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void replayMapData(MsgContext<?> msgContext) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RobotOpenManager$replayMapData$1(msgContext, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean addCallTask(MsgContext<CallBody> msg) {
        String str;
        Destination destination;
        Destination destination2;
        MsgContext msgContext;
        String name;
        Destination destination3;
        Destination destination4;
        Pdlog.m3273d(TAG, "addCallTask : msg = " + msg + "; currentCallTasks = " + currentCallTasks);
        Iterator<T> it = currentCallTasks.iterator();
        do {
            str = null;
            if (it.hasNext()) {
                msgContext = (MsgContext) it.next();
                CallBody callBody = (CallBody) msgContext.getReqData();
                name = (callBody == null || (destination4 = callBody.getDestination()) == null) ? null : destination4.getName();
                CallBody reqData = msg.getReqData();
                if (reqData != null && (destination3 = reqData.getDestination()) != null) {
                    str = destination3.getName();
                }
            } else {
                Iterator<T> it2 = allCallTaskList.iterator();
                while (it2.hasNext()) {
                    MsgContext msgContext2 = (MsgContext) it2.next();
                    CallBody callBody2 = (CallBody) msgContext2.getReqData();
                    String name2 = (callBody2 == null || (destination2 = callBody2.getDestination()) == null) ? null : destination2.getName();
                    CallBody reqData2 = msg.getReqData();
                    if (Intrinsics.areEqual(name2, (reqData2 == null || (destination = reqData2.getDestination()) == null) ? null : destination.getName())) {
                        return Intrinsics.areEqual(msgContext2.getTarget(), msg.getTarget());
                    }
                }
                allCallTaskList.add(msg);
                return true;
            }
        } while (!Intrinsics.areEqual(name, str));
        if (Intrinsics.areEqual(msgContext.getTarget(), msg.getTarget())) {
            return true;
        }
        Pdlog.m3273d(TAG, "addCallTask has same destination");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0067, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r7, r8 != null ? r8.getDestination() : null) != false) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0035 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void cancelCallTask(final MsgContext<CallBody> msg) {
        boolean z;
        Pdlog.m3273d(TAG, "cancelCallTask : msg = " + msg + "; currentCallTasks = " + currentCallTasks);
        CopyOnWriteArrayList<MsgContext<CallBody>> copyOnWriteArrayList = currentCallTasks;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = copyOnWriteArrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            MsgContext msgContext = (MsgContext) next;
            if (msg.getReqData() != null) {
                CallBody callBody = (CallBody) msgContext.getReqData();
                Destination destination = callBody != null ? callBody.getDestination() : null;
                CallBody reqData = msg.getReqData();
            }
            if (Intrinsics.areEqual(msgContext.getTarget(), msg.getTarget())) {
                z = true;
                if (!z) {
                    arrayList.add(next);
                }
            }
            z = false;
            if (!z) {
            }
        }
        ArrayList<MsgContext> arrayList2 = arrayList;
        if (!arrayList2.isEmpty()) {
            Pdlog.m3273d(TAG, "cancelCallTask : cTask = " + arrayList2 + "; ");
            for (MsgContext msgContext2 : arrayList2) {
                RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
                String target = msg.getTarget();
                String role = msgContext2.getRole();
                String state = GoState.Cancel.getState();
                CallBody callBody2 = (CallBody) msgContext2.getReqData();
                Destination destination2 = callBody2 != null ? callBody2.getDestination() : null;
                if (destination2 == null) {
                    Intrinsics.throwNpe();
                }
                robotOpenSdk.publishMsg(target, new PubRobotGoStateData(role, state, destination2), new ICallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$cancelCallTask$1$1
                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onFailed(Exception e) {
                        String str;
                        Intrinsics.checkParameterIsNotNull(e, "e");
                        RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                        str = RobotOpenManager.TAG;
                        Pdlog.m3274e(str, "cancelCallTask onFailed : " + Log.getStackTraceString(e));
                    }

                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onSuccess(IBody result) {
                        String str;
                        RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                        str = RobotOpenManager.TAG;
                        Pdlog.m3274e(str, "cancelCallTask onSuccess");
                    }
                });
                currentCallTasks.remove(msgContext2);
            }
            if (currentCallTasks.isEmpty()) {
                getBeeperListenerList().forEach(new Function1<BeeperTaskListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$cancelCallTask$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(BeeperTaskListener beeperTaskListener2) {
                        invoke2(beeperTaskListener2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(BeeperTaskListener it2) {
                        String str;
                        Destination destination3;
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        BeeperAction beeperAction = BeeperAction.CANCEL;
                        CallBody callBody3 = (CallBody) MsgContext.this.getReqData();
                        if (callBody3 == null || (destination3 = callBody3.getDestination()) == null || (str = destination3.getName()) == null) {
                            str = "";
                        }
                        it2.onTask(beeperAction, str, CallFromType.OPEN_API);
                    }
                });
            }
        }
        allCallTaskList.removeIf(new Predicate<MsgContext<CallBody>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$cancelCallTask$3
            @Override // java.util.function.Predicate
            public final boolean test(MsgContext<CallBody> msgContext3) {
                if (Intrinsics.areEqual(msgContext3.getTarget(), MsgContext.this.getTarget())) {
                    if (MsgContext.this.getReqData() != null) {
                        CallBody reqData2 = msgContext3.getReqData();
                        Destination destination3 = reqData2 != null ? reqData2.getDestination() : null;
                        CallBody callBody3 = (CallBody) MsgContext.this.getReqData();
                        if (Intrinsics.areEqual(destination3, callBody3 != null ? callBody3.getDestination() : null)) {
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public final String getCanRunCallTask$module_bumblebee_presenter_robotRelease() {
        Destination destination;
        Pdlog.m3273d(TAG, "getCanRunCallTask : lastFinishTime = " + FreezeStateManger.INSTANCE.getLastFinishTime() + " , allCallTaskList = " + allCallTaskList);
        if (allCallTaskList.size() > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - FreezeStateManger.INSTANCE.getLastFinishTime();
            if (!FreezeStateManger.INSTANCE.isFreezeState()) {
                CallBody reqData = allCallTaskList.get(0).getReqData();
                if (reqData == null || (destination = reqData.getDestination()) == null) {
                    return null;
                }
                return destination.getName();
            }
            startTimeoutCall((BusinessSetting.INSTANCE.getCallFreezeTime_ms() + 3000) - elapsedRealtime);
        } else {
            Pdlog.m3273d(TAG, "getCanRunCallTask not can run call task");
        }
        return null;
    }

    private final void startTimeoutCall(long i) {
        Pdlog.m3273d(TAG, "startTimeoutCall : i = " + i + "; ");
        stopTimeoutCall$module_bumblebee_presenter_robotRelease();
        mainHandler.sendEmptyMessageDelayed(TIME_OUT_CALL, i);
    }

    public final void stopTimeoutCall$module_bumblebee_presenter_robotRelease() {
        mainHandler.removeMessages(TIME_OUT_CALL);
    }

    public final void onRobotDeliveryStatus$module_bumblebee_presenter_robotRelease(DeliveryMode mode, TaskStatus status, ViewModel viewModel) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(viewModel, "viewModel");
        Pdlog.m3273d(TAG, "onRobotDeliveryStatus : status = " + status + "; target = " + viewModel.getDestination() + "; ");
        deliveryTaskNotify(mode, status, viewModel);
        int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
        if (i == 1) {
            if (mode == DeliveryMode.CALL_DIRECT) {
                setRunningCallTask(viewModel.getDestination());
                onNotifyCallTaskStatus(GoState.Arriving);
                return;
            }
            return;
        }
        if (i == 2 || i == 3) {
            if (mode == DeliveryMode.CALL_DIRECT) {
                FreezeStateManger.INSTANCE.setLastFinishTime(SystemClock.elapsedRealtime());
                onNotifyCallTaskStatus(GoState.Arrived);
                currentCallTasks.clear();
            }
            if (isDeliveryMode(mode)) {
                notifyDeliveryOrder(viewModel, OrderState.Complete);
                return;
            }
            return;
        }
        if (i != 4) {
            return;
        }
        if (mode == DeliveryMode.CALL_DIRECT) {
            onNotifyCallTaskStatus(GoState.Cancel);
            currentCallTasks.clear();
        }
        if (isDeliveryMode(mode)) {
            notifyDeliveryOrder(viewModel, OrderState.Cancel);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void deliveryTaskNotify(DeliveryMode mode, TaskStatus status, ViewModel viewModel) {
        String str;
        if (!isDeliveryMode(mode) && mode != DeliveryMode.DIRECT) {
            return;
        }
        if ((viewModel.getDestination().length() == 0) || status == TaskStatus.AWAIT) {
            return;
        }
        if (mode != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[mode.ordinal()];
            if (i == 1) {
                str = PubDeliveryMode.General.getId();
            } else if (i == 2) {
                str = PubDeliveryMode.Direct.getId();
            } else if (i == 3) {
                str = PubDeliveryMode.Birthday.getId();
            } else if (i == 4) {
                str = PubDeliveryMode.Special.getId();
            }
            if (str == null) {
                ArrayList arrayList = new ArrayList();
                for (TrayModel trayModel : viewModel.getTrays()) {
                    ArrayList arrayList2 = new ArrayList();
                    DeliveryTraysTaskState deliveryTraysTaskState = new DeliveryTraysTaskState(arrayList2);
                    for (DeliveryModel deliveryModel : trayModel.getAllDestinations()) {
                        String destination = deliveryModel.getDestination();
                        String type = deliveryModel.getType();
                        String id = deliveryModel.getId();
                        long estimatedTime = deliveryModel.getEstimatedTime();
                        long spendTime_ms = deliveryModel.getSpendTime_ms();
                        String pubDeliveryState = INSTANCE.getPubDeliveryState(deliveryModel.getStatus());
                        DeliveryModel.CompleteType completeType = deliveryModel.getCompleteType();
                        arrayList2.add(new DeliveryTaskState(destination, type, id, estimatedTime, spendTime_ms, pubDeliveryState, completeType != null ? completeType.name() : null));
                    }
                    arrayList.add(deliveryTraysTaskState);
                }
                PubDeliveryTaskStateData pubDeliveryTaskStateData = new PubDeliveryTaskStateData(arrayList, str);
                Pdlog.m3273d(TAG, "deliveryTaskNotify : pub = " + pubDeliveryTaskStateData);
                RobotOpenSdk.INSTANCE.publishMsg(pubDeliveryTaskStateData, new ICallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$deliveryTaskNotify$2
                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onSuccess(IBody result) {
                        String str2;
                        RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                        str2 = RobotOpenManager.TAG;
                        Pdlog.m3273d(str2, "deliveryTaskNotify onSuccess : result = " + result + "; ");
                    }

                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onFailed(Exception e) {
                        String str2;
                        Intrinsics.checkParameterIsNotNull(e, "e");
                        RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                        str2 = RobotOpenManager.TAG;
                        Pdlog.m3273d(str2, "deliveryTaskNotify onFailed : e = " + Log.getStackTraceString(e) + "; ");
                    }
                });
                return;
            }
            return;
        }
        str = null;
        if (str == null) {
        }
    }

    private final String getPubDeliveryState(TaskStatus status) {
        switch (status) {
            case AWAIT:
                return PubDeliveryState.Await.getId();
            case ON_THE_WAY:
                return PubDeliveryState.OnTheWay.getId();
            case APPROACHING:
                return PubDeliveryState.OnTheWay.getId();
            case ARRIVAL:
                return PubDeliveryState.Arrived.getId();
            case DONE:
                return PubDeliveryState.Complete.getId();
            case CANCEL:
                return PubDeliveryState.Cancel.getId();
            case DONE_BEFORE_ARRIVAL:
                return PubDeliveryState.Complete.getId();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final void deliveryOrderStart(String employees, String target, ArrayList<OrderIdData> ids, ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(employees, "employees");
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        RobotOpenSdk.INSTANCE.publishMsg(target, new PubOrderStateData(Constant.INSTANCE.getROLE_SDK(), OrderState.Start.name(), ids, employees), callBack);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void notifyDeliveryOrder(ViewModel viewModel, final OrderState state) {
        HashMap hashMap = new HashMap();
        long currentTimeMillis = System.currentTimeMillis();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        Iterator<T> it = viewModel.getTrays().iterator();
        while (true) {
            if (it.hasNext()) {
                DeliveryModel deliveryModel = ((TrayModel) it.next()).getDeliveryModel(viewModel.getDestination());
                if (deliveryModel != null) {
                    ArrayList<InformationSystemContract.OrderInfo> foodInfo = deliveryModel.getFoodInfo();
                    if (!(foodInfo == null || foodInfo.isEmpty())) {
                        String completeEmployeeId = deliveryModel.getCompleteEmployeeId();
                        T t = completeEmployeeId;
                        if (completeEmployeeId == null) {
                            t = "";
                        }
                        objectRef.element = t;
                        ArrayList<InformationSystemContract.OrderInfo> foodInfo2 = deliveryModel.getFoodInfo();
                        if (foodInfo2 != null) {
                            for (InformationSystemContract.OrderInfo orderInfo : foodInfo2) {
                                OrderIdData orderIdData = new OrderIdData(orderInfo.getScid(), orderInfo.getStartTime() != 0 ? currentTimeMillis - orderInfo.getStartTime() : 0L);
                                ArrayList arrayList = (ArrayList) hashMap.get(orderInfo.getNotifyTarget());
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                    hashMap.put(orderInfo.getNotifyTarget(), arrayList);
                                }
                                arrayList.add(orderIdData);
                            }
                        }
                    }
                }
            } else {
                Pdlog.m3273d(TAG, "notifyDeliveryOrder : targetPubMap = " + hashMap + "; state = " + state + "; ");
                hashMap.forEach(new BiConsumer<String, ArrayList<OrderIdData>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$notifyDeliveryOrder$2
                    @Override // java.util.function.BiConsumer
                    public final void accept(String t2, ArrayList<OrderIdData> u) {
                        Intrinsics.checkParameterIsNotNull(t2, "t");
                        Intrinsics.checkParameterIsNotNull(u, "u");
                        RobotOpenSdk.INSTANCE.publishMsg(t2, new PubOrderStateData(Constant.INSTANCE.getROLE_SDK(), OrderState.this.name(), u, (String) objectRef.element), null);
                    }
                });
                return;
            }
        }
    }

    private final boolean isDeliveryMode(DeliveryMode mode) {
        return mode == DeliveryMode.BIRTHDAY || mode == DeliveryMode.GENERAL || mode == DeliveryMode.SPECIAL;
    }

    private final void onNotifyCallTaskStatus(GoState state) {
        Iterator<T> it = currentCallTasks.iterator();
        while (it.hasNext()) {
            MsgContext msgContext = (MsgContext) it.next();
            Pdlog.m3273d(TAG, "onNotifyCallTaskStatus : state = " + state + " ; " + msgContext);
            RobotOpenSdk robotOpenSdk = RobotOpenSdk.INSTANCE;
            String target = msgContext.getTarget();
            String role = msgContext.getRole();
            String state2 = state.getState();
            CallBody callBody = (CallBody) msgContext.getReqData();
            Destination destination = callBody != null ? callBody.getDestination() : null;
            if (destination == null) {
                Intrinsics.throwNpe();
            }
            robotOpenSdk.publishMsg(target, new PubRobotGoStateData(role, state2, destination), null);
        }
    }

    private final void setRunningCallTask(String target) {
        Destination destination;
        if (!currentCallTasks.isEmpty()) {
            Pdlog.m3274e(TAG, "setRunningCallTask : currentCallTask is not empty = " + currentCallTasks + " !!");
        }
        CopyOnWriteArrayList<MsgContext<CallBody>> copyOnWriteArrayList = allCallTaskList;
        ArrayList arrayList = new ArrayList();
        for (Object obj : copyOnWriteArrayList) {
            CallBody callBody = (CallBody) ((MsgContext) obj).getReqData();
            if (Intrinsics.areEqual((callBody == null || (destination = callBody.getDestination()) == null) ? null : destination.getName(), target)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        currentCallTasks.addAll(arrayList2);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            allCallTaskList.remove((MsgContext) it.next());
        }
    }

    public final void init$module_bumblebee_presenter_robotRelease(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Pdlog.m3273d(TAG, "init " + context2);
        context = context2;
        RobotOpenHelper.INSTANCE.init(context2, onOpenSdkEventListener);
    }

    public final boolean getSwitch() {
        return RobotOpenHelper.INSTANCE.isOpen();
    }

    public final boolean isLocal() {
        return RobotOpenHelper.INSTANCE.isLocal();
    }

    public final String getHost() {
        return RobotOpenHelper.INSTANCE.getLocalHost();
    }

    public final void setHost(String host) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        RobotOpenHelper.INSTANCE.setLocalHost(host);
    }

    public final void setSwitch(boolean sw, boolean isLocal) {
        Pdlog.m3273d(TAG, "setSwitch : sw = " + sw + "; isLocal = " + isLocal);
        if (sw) {
            RobotOpenHelper.INSTANCE.open(isLocal);
        } else {
            RobotOpenHelper.INSTANCE.close();
        }
    }

    public static /* synthetic */ void pudDoorControl$default(RobotOpenManager robotOpenManager, DoorControlCommand doorControlCommand, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        robotOpenManager.pudDoorControl(doorControlCommand, str, str2);
    }

    public final void pudDoorControl(DoorControlCommand command, String id, String ext) {
        Intrinsics.checkParameterIsNotNull(command, "command");
        Pdlog.m3273d(TAG, "pudDoorControl : command = " + command + "; ");
        RobotOpenSdk.INSTANCE.publishMsg(new PubDoorControlDate(command.name(), id, ext), new ICallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager$pudDoorControl$1
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onFailed(Exception e) {
                String str;
                Intrinsics.checkParameterIsNotNull(e, "e");
                RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                str = RobotOpenManager.TAG;
                Pdlog.m3274e(str, "pudDoorControl onFailed : e = " + e + "; ");
            }

            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onSuccess(IBody result) {
                String str;
                RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
                str = RobotOpenManager.TAG;
                Pdlog.m3273d(str, "pudDoorControl onSuccess : result = " + result + "; ");
            }
        });
    }

    public final void startBindBeeper(IGenBindCodeCallBack callBack) {
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        if (RobotOpenSdk.INSTANCE.getConnectState() != RemoteConnectState.CONNECTED) {
            callBack.onFailed(new IOException("disconnected"));
        } else {
            RobotOpenSdk.INSTANCE.startBind(callBack);
        }
    }

    public final void unBindAllDevice$module_bumblebee_presenter_robotRelease(ICallback callBack) {
        if (RobotOpenSdk.INSTANCE.getConnectState() == RemoteConnectState.CONNECTED) {
            RobotOpenSdk.INSTANCE.publishMsg(new PubUnbindDeviceData(null, 1, null), callBack);
        } else if (callBack != null) {
            callBack.onFailed(new IOException("disconnected"));
        }
    }
}
