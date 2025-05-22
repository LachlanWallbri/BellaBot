package com.pudutech.bumblebee.presenter.robot_open_task;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.base.ListenerList;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.battery.PowerState;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryMode;
import com.pudutech.bumblebee.presenter.delivery_task.ViewModel;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.ActionPayLoad;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallCompletePayLoad;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallCompleteUpPayload;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallFromType;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallTaskDownPayLoad;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallTaskUpPayload;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.Info;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.MqttReq;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.MqttResp;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.PayloadStatus;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.PayloadWorkDetail;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.PositionInfo;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.PositionVector;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.PublishPayload;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.Schedule;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.SubTopic;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.Work;
import com.pudutech.bumblebee.presenter.robot_open_task.config.ActionConfig;
import com.pudutech.bumblebee.presenter.robot_open_task.config.MqttConfig;
import com.pudutech.bumblebee.presenter.robot_open_task.config.Topic;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.BeeperAction;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.BeeperTaskListener;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.FreezeStateListener;
import com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager;
import com.pudutech.bumblebee.presenter.robot_open_task.provider.PositionDataManager;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.robot.module.report.protocol.bean.RobotPositionInfo;
import java.nio.charset.Charset;
import java.util.concurrent.LinkedBlockingDeque;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.internal.websocket.Base64;
import org.json.JSONObject;

/* compiled from: RobotNewOpenManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010H\u001a\u00020 2\u0006\u0010I\u001a\u00020\u0011J\b\u0010J\u001a\u00020 H\u0002J\b\u0010K\u001a\u00020 H\u0002J\u0010\u0010L\u001a\u00020 2\u0006\u0010M\u001a\u00020\u000eH\u0002J\u001b\u0010N\u001a\u00020\u000e\"\u0004\b\u0000\u0010O2\u0006\u0010F\u001a\u0002HOH\u0002¢\u0006\u0002\u0010PJ#\u0010Q\u001a\u00020\u000e\"\u0004\b\u0000\u0010O2\u0006\u0010M\u001a\u00020\u000e2\u0006\u0010F\u001a\u0002HOH\u0002¢\u0006\u0002\u0010RJ\u000e\u0010S\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\b\u0010T\u001a\u00020 H\u0002J\u000e\u0010U\u001a\u00020 2\u0006\u0010V\u001a\u00020\u000eJ\u0010\u0010W\u001a\u00020 2\u0006\u0010F\u001a\u00020XH\u0002J\u0010\u0010Y\u001a\u00020 2\u0006\u0010Z\u001a\u00020[H\u0002J\u0010\u0010\\\u001a\u00020 2\u0006\u0010Z\u001a\u00020]H\u0002J'\u0010^\u001a\u00020 2\b\u0010_\u001a\u0004\u0018\u00010`2\u0006\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020dH\u0000¢\u0006\u0002\beJ\u0016\u0010f\u001a\u00020 2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020 0&H\u0002J8\u0010h\u001a\u00020 2\u0006\u0010E\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020\u000e2\u001e\b\u0002\u0010i\u001a\u0018\u0012\u0004\u0012\u000202\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0004\u0012\u00020 \u0018\u00010BH\u0002J(\u0010h\u001a\u00020 2\u0006\u0010E\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020\u000e2\u0006\u0010j\u001a\u0002022\u0006\u0010k\u001a\u000202H\u0002J\u0006\u0010l\u001a\u00020 J\u000e\u0010m\u001a\u00020 2\u0006\u0010I\u001a\u00020\u0011J\b\u0010n\u001a\u00020 H\u0002J\u0010\u0010o\u001a\u00020 2\u0006\u0010M\u001a\u00020\u000eH\u0002J\u0006\u0010p\u001a\u00020 J\u000e\u0010q\u001a\u00020 2\u0006\u0010a\u001a\u00020\u0006J\u0016\u0010r\u001a\u00020 2\u0006\u0010a\u001a\u00020\u00062\u0006\u0010G\u001a\u00020\u000eJ\b\u0010s\u001a\u00020 H\u0002J\b\u0010t\u001a\u00020 H\u0002J\u0018\u0010u\u001a\u00020 2\u0006\u0010a\u001a\u00020\u00042\u0006\u0010v\u001a\u00020\u000eH\u0002J\u0010\u0010w\u001a\u00020 2\u0006\u0010a\u001a\u00020\u0006H\u0002J\u0010\u0010x\u001a\u00020 2\u0006\u0010y\u001a\u00020\u000eH\u0002J3\u0010z\u001a\u00020 2\u0006\u0010{\u001a\u00020\u000e2\b\b\u0002\u0010|\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0002\u0010}J\b\u0010~\u001a\u00020 H\u0002J\u0010\u0010\u007f\u001a\u00020 2\u0006\u0010F\u001a\u000209H\u0002J\t\u0010\u0080\u0001\u001a\u00020 H\u0002J\t\u0010\u0081\u0001\u001a\u00020 H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R!\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00178FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001cR\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001cR\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020 0&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010'\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020706X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000109X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R>\u0010A\u001a2\u0012\u0013\u0012\u00110\u000e¢\u0006\f\bC\u0012\b\bD\u0012\u0004\b\b(E\u0012\u0013\u0012\u00110\u000e¢\u0006\f\bC\u0012\b\bD\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020 0BX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0082\u0001"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotNewOpenManager;", "", "()V", "DELAY_INIT_TIME", "", "PUBLISH_COUNT_MAX", "", "PUBLISH_STATUS_TIME_OUT_WHAT", "PUBLISH_TOPIC_ROBOT_STATUS_TIME", "PUBLISH_TOPIC_ROBOT_STATUS_WHAT", "PUBLISH_TOPIC_TASK_TIMEOUT", "PUBLISH_TOPIC_TASK_TIMEOUT_WHAT", "PUBLISH_TOPIC_TASK_WHAT", "TAG", "", "beeperListenerList", "Lcom/pudutech/bumblebee/business/base/ListenerList;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/BeeperTaskListener;", "getBeeperListenerList", "()Lcom/pudutech/bumblebee/business/base/ListenerList;", "beeperListenerList$delegate", "Lkotlin/Lazy;", "beeperTaskListener", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "getBeeperTaskListener", "()Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "beeperTaskListener$delegate", "callRequestId", "Ljava/lang/Long;", "callTaskId", "connectStatusListener", "Lkotlin/Function1;", "", "context", "Landroid/content/Context;", "curTarget", "currentWorkStatus", "delayInitMqtt", "Lkotlin/Function0;", "freezeStateListener", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/FreezeStateListener;", "getFreezeStateListener", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/FreezeStateListener;", "gson", "Lcom/google/gson/Gson;", "handler", "Landroid/os/Handler;", "handlerThread", "Landroid/os/HandlerThread;", "isPublishingRobotStatus", "", "lastScheduleStatus", "lastWorkStatus", "publishTask", "Ljava/util/concurrent/LinkedBlockingDeque;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/PublishPayload;", "robotStatus", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/PayloadStatus;", "robotStatusNotify", "Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotStatusNotify;", "shopId", "getShopId", "()J", "setShopId", "(J)V", "subscribeListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "topic", MqttServiceConstants.PAYLOAD, "workDescribe", "addBeeperListener", "listener", "countPublishTimes", "delayInit", "dispatchMessage", "subTopic", "getReq", ExifInterface.GPS_DIRECTION_TRUE, "(Ljava/lang/Object;)Ljava/lang/String;", "getSubTopicReq", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;", "init", "initMqtt", "onArrive", "tableName", "onCallCompleteDown", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallCompletePayLoad;", "onCallDown", "playLoad", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallTaskDownPayLoad;", "onCmdDown", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ActionPayLoad;", "onRobotDeliveryStatus", "mode", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryMode;", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "viewModel", "Lcom/pudutech/bumblebee/presenter/delivery_task/ViewModel;", "onRobotDeliveryStatus$module_bumblebee_presenter_robotRelease", "postAsync", "black", "publishTopic", "callback", "ensurePublish", "isWait", "release", "removeBeeperListener", "removePublishStatusTimeout", "reportPosition", "reportRobotStatus", "reportRobotTaskStatus", "reportRobotWorkStatus", "republishStatus", "resetTaskTimeout", "sendCallCompleteUp", NotificationCompat.CATEGORY_MESSAGE, "sendCallDetailUp", "sendCallTaskUp", AIUIConstant.KEY_CONTENT, "sendCallTaskUpNo", "error", "code", "(Ljava/lang/String;JLjava/lang/Long;Ljava/lang/Long;)V", "sendCallTaskUpYes", "sendCommonStatusUpMsg", "setPublishStatusTimeout", "timeoutRepublishStatus", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotNewOpenManager {
    private static final long DELAY_INIT_TIME = 5000;
    public static final RobotNewOpenManager INSTANCE;
    private static final int PUBLISH_COUNT_MAX = 3;
    private static final int PUBLISH_STATUS_TIME_OUT_WHAT;
    private static final long PUBLISH_TOPIC_ROBOT_STATUS_TIME = 5000;
    private static final int PUBLISH_TOPIC_ROBOT_STATUS_WHAT = 2;
    private static final long PUBLISH_TOPIC_TASK_TIMEOUT = 5000;
    private static final int PUBLISH_TOPIC_TASK_TIMEOUT_WHAT = 3;
    private static final int PUBLISH_TOPIC_TASK_WHAT = 1;
    private static final String TAG;

    /* renamed from: beeperListenerList$delegate, reason: from kotlin metadata */
    private static final Lazy beeperListenerList;

    /* renamed from: beeperTaskListener$delegate, reason: from kotlin metadata */
    private static final Lazy beeperTaskListener;
    private static volatile Long callRequestId;
    private static volatile Long callTaskId;
    private static final Function1<Integer, Unit> connectStatusListener;
    private static Context context;
    private static String curTarget;
    private static int currentWorkStatus;
    private static final Function0<Unit> delayInitMqtt;
    private static final FreezeStateListener freezeStateListener;
    private static final Gson gson;
    private static Handler handler;
    private static final HandlerThread handlerThread;
    private static boolean isPublishingRobotStatus;
    private static int lastScheduleStatus;
    private static int lastWorkStatus;
    private static final LinkedBlockingDeque<PublishPayload> publishTask;
    private static PayloadStatus robotStatus;
    private static final RobotStatusNotify robotStatusNotify;
    private static long shopId;
    private static final Function2<String, String, Unit> subscribeListener;
    private static String workDescribe;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TaskStatus.values().length];

        static {
            $EnumSwitchMapping$0[TaskStatus.ON_THE_WAY.ordinal()] = 1;
            $EnumSwitchMapping$0[TaskStatus.DONE.ordinal()] = 2;
            $EnumSwitchMapping$0[TaskStatus.DONE_BEFORE_ARRIVAL.ordinal()] = 3;
            $EnumSwitchMapping$0[TaskStatus.CANCEL.ordinal()] = 4;
            $EnumSwitchMapping$0[TaskStatus.ARRIVAL.ordinal()] = 5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<BeeperTaskListener> getBeeperListenerList() {
        return (ListenerList) beeperListenerList.getValue();
    }

    public final BaseMultiListenerImpl<BeeperTaskListener> getBeeperTaskListener() {
        return (BaseMultiListenerImpl) beeperTaskListener.getValue();
    }

    static {
        RobotNewOpenManager robotNewOpenManager = new RobotNewOpenManager();
        INSTANCE = robotNewOpenManager;
        TAG = TAG;
        workDescribe = "";
        robotStatusNotify = new RobotStatusNotify();
        gson = new Gson();
        handlerThread = new HandlerThread(TAG);
        freezeStateListener = new FreezeStateListener() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$freezeStateListener$1
            @Override // com.pudutech.bumblebee.presenter.robot_open_task.listener.FreezeStateListener
            public void onFreezeStateChange(boolean state) {
                Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "onFreezeStateChange state:" + state + ' ');
                RobotNewOpenManager.INSTANCE.reportRobotStatus();
            }
        };
        beeperListenerList = LazyKt.lazy(new Function0<ListenerList<BeeperTaskListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$beeperListenerList$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ListenerList<BeeperTaskListener> invoke() {
                return new ListenerList<>();
            }
        });
        beeperTaskListener = LazyKt.lazy(new Function0<BaseMultiListenerImpl<BeeperTaskListener>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$beeperTaskListener$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseMultiListenerImpl<BeeperTaskListener> invoke() {
                ListenerList beeperListenerList2;
                beeperListenerList2 = RobotNewOpenManager.INSTANCE.getBeeperListenerList();
                return new BaseMultiListenerImpl<>(beeperListenerList2);
            }
        });
        publishTask = new LinkedBlockingDeque<>();
        subscribeListener = new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$subscribeListener$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String topic, final String payload) {
                Gson gson2;
                Intrinsics.checkParameterIsNotNull(topic, "topic");
                Intrinsics.checkParameterIsNotNull(payload, "payload");
                Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "subscribeListener topic:" + topic + ",payload:" + payload + ' ');
                if (Intrinsics.areEqual(topic, Topic.INSTANCE.getCallDown())) {
                    RobotNewOpenManager robotNewOpenManager2 = RobotNewOpenManager.INSTANCE;
                    gson2 = RobotNewOpenManager.gson;
                    final MqttResp mqttResp = (MqttResp) gson2.fromJson(payload, new TypeToken<MqttResp<CallTaskDownPayLoad>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$subscribeListener$1.1
                    }.getType());
                    if (mqttResp != null) {
                        RobotNewOpenManager.INSTANCE.postAsync(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$subscribeListener$1$2$1
                            /* JADX INFO: Access modifiers changed from: package-private */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                int i;
                                int i2;
                                Long l;
                                Long l2;
                                RobotNewOpenManager robotNewOpenManager3 = RobotNewOpenManager.INSTANCE;
                                i = RobotNewOpenManager.currentWorkStatus;
                                if (i != 1) {
                                    RobotNewOpenManager robotNewOpenManager4 = RobotNewOpenManager.INSTANCE;
                                    i2 = RobotNewOpenManager.currentWorkStatus;
                                    if (i2 != 0 && !FreezeStateManger.INSTANCE.isFreezeState()) {
                                        RobotNewOpenManager robotNewOpenManager5 = RobotNewOpenManager.INSTANCE;
                                        l = RobotNewOpenManager.callRequestId;
                                        if (l == null) {
                                            RobotNewOpenManager robotNewOpenManager6 = RobotNewOpenManager.INSTANCE;
                                            l2 = RobotNewOpenManager.callTaskId;
                                            if (l2 == null) {
                                                RobotNewOpenManager robotNewOpenManager7 = RobotNewOpenManager.INSTANCE;
                                                RobotNewOpenManager.callRequestId = Long.valueOf(MqttResp.this.getMsg_id());
                                                RobotNewOpenManager robotNewOpenManager8 = RobotNewOpenManager.INSTANCE;
                                                RobotNewOpenManager.callTaskId = Long.valueOf(((CallTaskDownPayLoad) MqttResp.this.getPayload()).getTask_id());
                                                RobotNewOpenManager.INSTANCE.onCallDown((CallTaskDownPayLoad) MqttResp.this.getPayload());
                                                return;
                                            }
                                        }
                                        RobotNewOpenManager.sendCallTaskUpNo$default(RobotNewOpenManager.INSTANCE, "机器人已经有其他任务", 0L, Long.valueOf(MqttResp.this.getMsg_id()), Long.valueOf(((CallTaskDownPayLoad) MqttResp.this.getPayload()).getTask_id()), 2, null);
                                        return;
                                    }
                                }
                                RobotNewOpenManager.sendCallTaskUpNo$default(RobotNewOpenManager.INSTANCE, "机器人工作中", 0L, Long.valueOf(MqttResp.this.getMsg_id()), Long.valueOf(((CallTaskDownPayLoad) MqttResp.this.getPayload()).getTask_id()), 2, null);
                            }
                        });
                        return;
                    }
                    return;
                }
                if (Intrinsics.areEqual(topic, Topic.INSTANCE.getCmdDown())) {
                    RobotNewOpenManager.INSTANCE.postAsync(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$subscribeListener$1.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            Gson gson3;
                            Long l;
                            Long l2;
                            Long l3;
                            Long l4;
                            RobotNewOpenManager robotNewOpenManager3 = RobotNewOpenManager.INSTANCE;
                            gson3 = RobotNewOpenManager.gson;
                            MqttResp mqttResp2 = (MqttResp) gson3.fromJson(payload, new TypeToken<MqttResp<ActionPayLoad>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager.subscribeListener.1.3.1
                            }.getType());
                            if (mqttResp2 != null) {
                                RobotNewOpenManager robotNewOpenManager4 = RobotNewOpenManager.INSTANCE;
                                l = RobotNewOpenManager.callRequestId;
                                if (l != null) {
                                    RobotNewOpenManager robotNewOpenManager5 = RobotNewOpenManager.INSTANCE;
                                    l4 = RobotNewOpenManager.callTaskId;
                                    if (l4 != null) {
                                        RobotNewOpenManager.INSTANCE.onCmdDown((ActionPayLoad) mqttResp2.getPayload());
                                        return;
                                    }
                                }
                                String access$getTAG$p = RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE);
                                StringBuilder sb = new StringBuilder();
                                sb.append("Topic.cmdDown :callRequestId:");
                                RobotNewOpenManager robotNewOpenManager6 = RobotNewOpenManager.INSTANCE;
                                l2 = RobotNewOpenManager.callRequestId;
                                sb.append(l2);
                                sb.append(" or callTaskId:");
                                RobotNewOpenManager robotNewOpenManager7 = RobotNewOpenManager.INSTANCE;
                                l3 = RobotNewOpenManager.callTaskId;
                                sb.append(l3);
                                sb.append(" is null ");
                                Pdlog.m3273d(access$getTAG$p, sb.toString());
                            }
                        }
                    });
                    return;
                }
                if (Intrinsics.areEqual(topic, Topic.INSTANCE.getCallCompleteDown())) {
                    RobotNewOpenManager.INSTANCE.postAsync(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$subscribeListener$1.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            Gson gson3;
                            RobotNewOpenManager robotNewOpenManager3 = RobotNewOpenManager.INSTANCE;
                            gson3 = RobotNewOpenManager.gson;
                            MqttResp mqttResp2 = (MqttResp) gson3.fromJson(payload, new TypeToken<MqttResp<CallCompletePayLoad>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager.subscribeListener.1.4.1
                            }.getType());
                            if (mqttResp2 != null) {
                                RobotNewOpenManager.INSTANCE.onCallCompleteDown((CallCompletePayLoad) mqttResp2.getPayload());
                            }
                        }
                    });
                    return;
                }
                if (Intrinsics.areEqual(topic, Topic.INSTANCE.getCommonStatusDown())) {
                    RobotNewOpenManager.INSTANCE.postAsync(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$subscribeListener$1.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            Gson gson3;
                            RobotNewOpenManager robotNewOpenManager3 = RobotNewOpenManager.INSTANCE;
                            gson3 = RobotNewOpenManager.gson;
                            if (((MqttResp) gson3.fromJson(payload, new TypeToken<MqttResp<CallCompletePayLoad>>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager.subscribeListener.1.5.1
                            }.getType())) != null) {
                                RobotNewOpenManager.INSTANCE.removePublishStatusTimeout();
                                RobotNewOpenManager.INSTANCE.republishStatus();
                            }
                        }
                    });
                    return;
                }
                if (Intrinsics.areEqual(topic, Topic.INSTANCE.getCommonMessageDown())) {
                    try {
                        String subTopic = new JSONObject(payload).getString("sub_topic");
                        RobotNewOpenManager robotNewOpenManager3 = RobotNewOpenManager.INSTANCE;
                        Intrinsics.checkExpressionValueIsNotNull(subTopic, "subTopic");
                        robotNewOpenManager3.dispatchMessage(subTopic);
                    } catch (Exception e) {
                        String access$getTAG$p = RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE);
                        e.printStackTrace();
                        Pdlog.m3274e(access$getTAG$p, Unit.INSTANCE);
                    }
                }
            }
        };
        connectStatusListener = new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$connectStatusListener$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final int i) {
                Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "connectStatusListener status: " + i + ' ');
                RobotNewOpenManager.INSTANCE.postAsync(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$connectStatusListener$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        if (i == 0 && (!RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).isEmpty())) {
                            RobotNewOpenManager.access$getHandler$p(RobotNewOpenManager.INSTANCE).removeMessages(1);
                            RobotNewOpenManager.access$getHandler$p(RobotNewOpenManager.INSTANCE).sendEmptyMessage(1);
                        }
                        if (i == 0) {
                            RobotNewOpenManager.INSTANCE.reportRobotStatus();
                        }
                    }
                });
            }
        };
        delayInitMqtt = new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$delayInitMqtt$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                RobotNewOpenManager.INSTANCE.initMqtt();
            }
        };
        FreezeStateManger.INSTANCE.getFreezeStateListener().addListener(freezeStateListener);
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "handleMessage msg: " + msg);
                Integer valueOf = msg != null ? Integer.valueOf(msg.what) : null;
                if (valueOf != null && valueOf.intValue() == 1) {
                    final PublishPayload publishPayload = (PublishPayload) RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).peek();
                    Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "handleMessage topicTask: " + publishPayload);
                    if (publishPayload == null) {
                        if ((!RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).isEmpty()) && Intrinsics.areEqual((Object) MqttManager.INSTANCE.isConnect(), (Object) true)) {
                            sendEmptyMessage(1);
                            return;
                        }
                        return;
                    }
                    sendEmptyMessageDelayed(3, 5000L);
                    RobotNewOpenManager.INSTANCE.publishTopic(publishPayload.getTopic(), publishPayload.getPayload(), new Function2<Boolean, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$1$handleMessage$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                            invoke(bool.booleanValue(), str);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z, String str) {
                            Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "handleMessage publishTopic callback b:" + z + ",s:" + str + ' ');
                            if (z && !publishPayload.isWait()) {
                                RobotNewOpenManager.INSTANCE.resetTaskTimeout();
                                RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).poll();
                                if ((!RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).isEmpty()) && Intrinsics.areEqual((Object) MqttManager.INSTANCE.isConnect(), (Object) true)) {
                                    sendEmptyMessage(1);
                                    return;
                                }
                                return;
                            }
                            if (z) {
                                return;
                            }
                            RobotNewOpenManager.INSTANCE.countPublishTimes();
                            RobotNewOpenManager.INSTANCE.resetTaskTimeout();
                            if ((!RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).isEmpty()) && Intrinsics.areEqual((Object) MqttManager.INSTANCE.isConnect(), (Object) true)) {
                                sendEmptyMessage(1);
                            }
                        }
                    });
                    return;
                }
                if (valueOf != null && valueOf.intValue() == 3) {
                    RobotNewOpenManager.INSTANCE.countPublishTimes();
                    if ((!RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).isEmpty()) && Intrinsics.areEqual((Object) MqttManager.INSTANCE.isConnect(), (Object) true)) {
                        RobotNewOpenManager.access$getHandler$p(RobotNewOpenManager.INSTANCE).sendEmptyMessage(1);
                        return;
                    }
                    return;
                }
                int access$getPUBLISH_STATUS_TIME_OUT_WHAT$p = RobotNewOpenManager.access$getPUBLISH_STATUS_TIME_OUT_WHAT$p(RobotNewOpenManager.INSTANCE);
                if (valueOf != null && valueOf.intValue() == access$getPUBLISH_STATUS_TIME_OUT_WHAT$p) {
                    RobotNewOpenManager.INSTANCE.timeoutRepublishStatus();
                }
            }
        };
        curTarget = "";
        PUBLISH_STATUS_TIME_OUT_WHAT = 4;
    }

    private RobotNewOpenManager() {
    }

    public static final /* synthetic */ Handler access$getHandler$p(RobotNewOpenManager robotNewOpenManager) {
        Handler handler2 = handler;
        if (handler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handler");
        }
        return handler2;
    }

    public static final /* synthetic */ int access$getPUBLISH_STATUS_TIME_OUT_WHAT$p(RobotNewOpenManager robotNewOpenManager) {
        return PUBLISH_STATUS_TIME_OUT_WHAT;
    }

    public static final /* synthetic */ LinkedBlockingDeque access$getPublishTask$p(RobotNewOpenManager robotNewOpenManager) {
        return publishTask;
    }

    public static final /* synthetic */ String access$getTAG$p(RobotNewOpenManager robotNewOpenManager) {
        return TAG;
    }

    public final long getShopId() {
        return shopId;
    }

    public final void setShopId(long j) {
        shopId = j;
    }

    public final FreezeStateListener getFreezeStateListener() {
        return freezeStateListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dispatchMessage(String subTopic) {
        Pdlog.m3273d(TAG, "sub_topic:" + subTopic);
        if (subTopic.hashCode() == -1016828756 && subTopic.equals("map_position")) {
            reportPosition(subTopic);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void countPublishTimes() {
        PublishPayload peek = publishTask.peek();
        Pdlog.m3273d(TAG, "countPublishTimes task: " + peek);
        if (peek == null) {
            return;
        }
        if (peek.getPublishTimes() < 3) {
            peek.setPublishTimes(peek.getPublishTimes() + 1);
        } else {
            Pdlog.m3273d(TAG, "countPublishTimes 任务连续发送失败 ");
            publishTask.poll();
        }
    }

    public final void init(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        context = context2.getApplicationContext();
        robotStatusNotify.init();
        initMqtt();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initMqtt() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new RobotNewOpenManager$initMqtt$1(null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$sam$java_lang_Runnable$0] */
    public final void delayInit() {
        Handler handler2 = handler;
        if (handler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handler");
        }
        final Function0<Unit> function0 = delayInitMqtt;
        if (function0 != null) {
            function0 = new Runnable() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$sam$java_lang_Runnable$0
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    Intrinsics.checkExpressionValueIsNotNull(Function0.this.invoke(), "invoke(...)");
                }
            };
        }
        handler2.postDelayed((Runnable) function0, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCmdDown(ActionPayLoad playLoad) {
        Info info;
        Pdlog.m3273d(TAG, "onCmdDown playLoad:" + playLoad + ",callTaskId:" + callTaskId + ' ');
        if (!Intrinsics.areEqual(playLoad.getAction(), ActionConfig.ACTION_CANCEL_CALL) || (info = (Info) gson.fromJson(playLoad.getInfo(), new TypeToken<Info>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$onCmdDown$1
        }.getType())) == null) {
            return;
        }
        Pdlog.m3273d(TAG, "onCmdDown: " + info);
        long task_id = info.getTask_id();
        Long l = callTaskId;
        if (l != null && task_id == l.longValue()) {
            INSTANCE.getBeeperListenerList().forEach(new Function1<BeeperTaskListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$onCmdDown$2$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(BeeperTaskListener beeperTaskListener2) {
                    invoke2(beeperTaskListener2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(BeeperTaskListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onTask(BeeperAction.CANCEL, "", CallFromType.CLOUD);
                }
            });
            INSTANCE.sendCallCompleteUp(0L, "App取消任务");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCallDown(final CallTaskDownPayLoad playLoad) {
        Pdlog.m3273d(TAG, "onCallDown playLoad:" + playLoad + ",MqttManager.shopId:" + shopId + ",MapHelper.mapChosen:" + RobotMapManager.INSTANCE.getDefaultPdmap());
        if (!Intrinsics.areEqual(RobotMapManager.INSTANCE.getDefaultPdmap(), playLoad.getMap_name())) {
            Pdlog.m3273d(TAG, "onCallDown: 和机器地图不匹配");
            sendCallTaskUpNo$default(this, "和机器地图不匹配", 0L, callRequestId, callTaskId, 2, null);
            Long l = (Long) null;
            callRequestId = l;
            callTaskId = l;
            return;
        }
        if (!RobotMapManager.INSTANCE.checkDestinationExist(playLoad.getPoint())) {
            Pdlog.m3273d(TAG, "onCallDown: 没有这个点位");
            sendCallTaskUpNo$default(this, "没有这个点位", 0L, callRequestId, callTaskId, 2, null);
            Long l2 = (Long) null;
            callRequestId = l2;
            callTaskId = l2;
            return;
        }
        if (playLoad.getShop_id() != shopId) {
            Pdlog.m3273d(TAG, "onCallDown: 和机器店铺不匹配");
            sendCallTaskUpNo$default(this, "和机器店铺不匹配", 0L, callRequestId, callTaskId, 2, null);
            Long l3 = (Long) null;
            callRequestId = l3;
            callTaskId = l3;
            return;
        }
        sendCallTaskUpYes();
        curTarget = playLoad.getTarget();
        getBeeperListenerList().forEach(new Function1<BeeperTaskListener, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$onCallDown$1
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
            public final void invoke2(BeeperTaskListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onTask(BeeperAction.CALL, CallTaskDownPayLoad.this.getPoint(), CallFromType.CLOUD);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCallCompleteDown(final CallCompletePayLoad payload) {
        postAsync(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$onCallCompleteDown$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String payload2;
                PublishPayload publishPayload = (PublishPayload) RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).peek();
                Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "onCallCompleteDown payload:" + CallCompletePayLoad.this + ",task:" + publishPayload + ' ');
                if (publishPayload == null || (payload2 = publishPayload.getPayload()) == null || !StringsKt.contains$default((CharSequence) payload2, (CharSequence) String.valueOf(CallCompletePayLoad.this.getRequest_id()), false, 2, (Object) null)) {
                    return;
                }
                RobotNewOpenManager.INSTANCE.resetTaskTimeout();
                RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).poll();
                if ((!RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).isEmpty()) && Intrinsics.areEqual((Object) MqttManager.INSTANCE.isConnect(), (Object) true)) {
                    RobotNewOpenManager.access$getHandler$p(RobotNewOpenManager.INSTANCE).sendEmptyMessage(1);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void postAsync(final Function0<Unit> black) {
        Handler handler2 = handler;
        if (handler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handler");
        }
        handler2.post(new Runnable() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$postAsync$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetTaskTimeout() {
        Handler handler2 = handler;
        if (handler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handler");
        }
        handler2.removeMessages(3);
    }

    private final void sendCallTaskUp(String content) {
        publishTopic(Topic.INSTANCE.getCallUp(), content, true, false);
    }

    private final void sendCallTaskUpYes() {
        if (callRequestId == null || callTaskId == null) {
            Pdlog.m3273d(TAG, "sendCallTaskUpYes callRequestId或者callTaskId为空");
            return;
        }
        Long l = callRequestId;
        if (l != null) {
            CallTaskUpPayload callTaskUpPayload = new CallTaskUpPayload(l.longValue(), 200L, "OK");
            RobotNewOpenManager robotNewOpenManager = INSTANCE;
            robotNewOpenManager.sendCallTaskUp(robotNewOpenManager.getReq(callTaskUpPayload));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void sendCallTaskUpNo$default(RobotNewOpenManager robotNewOpenManager, String str, long j, Long l, Long l2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 400;
        }
        robotNewOpenManager.sendCallTaskUpNo(str, j, l, l2);
    }

    private final void sendCallTaskUpNo(String error, long code, Long callRequestId2, Long callTaskId2) {
        if (callRequestId2 == null || callTaskId2 == null) {
            Pdlog.m3273d(TAG, "sendCallTaskUpNo callRequestId或者callTaskId为空");
            return;
        }
        CallTaskUpPayload callTaskUpPayload = new CallTaskUpPayload(callRequestId2.longValue(), code != 200 ? code : 400L, error);
        RobotNewOpenManager robotNewOpenManager = INSTANCE;
        robotNewOpenManager.sendCallTaskUp(robotNewOpenManager.getReq(callTaskUpPayload));
    }

    private final void sendCallDetailUp(int status) {
        if (callRequestId == null || callTaskId == null) {
            Pdlog.m3273d(TAG, "sendCallDetailUp callRequestId或者callTaskId为空");
            return;
        }
        String str = MqttConfig.INSTANCE.getWorkMap().get(Integer.valueOf(status));
        if (str != null) {
            PayloadWorkDetail payloadWorkDetail = new PayloadWorkDetail(str, status, curTarget);
            Pdlog.m3273d(TAG, "sendCallDetailUp playLoad:" + payloadWorkDetail);
            publishTopic(Topic.INSTANCE.getCallDetailUp(), getReq(payloadWorkDetail), true, false);
        }
    }

    public final void onArrive(String tableName) {
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        String encode = Base64.encode("{'title':'" + tableName + "','content':'arrived'}");
        Intrinsics.checkExpressionValueIsNotNull(encode, "Base64.encode(\"{'title':…}','content':'arrived'}\")");
        Charset charset = Charsets.UTF_8;
        if (encode == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = encode.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        String g4WatchArrive = Topic.INSTANCE.getG4WatchArrive();
        String json = gson.toJson(MapsKt.mapOf(TuplesKt.m3968to("notify_type", "point_type"), TuplesKt.m3968to("notify_id", "point_arrival"), TuplesKt.m3968to("body", bytes)));
        Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(mapOf(\n     … body\n        )\n        )");
        publishTopic(g4WatchArrive, json, true, false);
    }

    private final void sendCallCompleteUp(long status, String msg) {
        if (callRequestId == null || callTaskId == null) {
            Pdlog.m3273d(TAG, "sendCallCompleteUp callRequestId或者callTaskId为空");
            return;
        }
        Long l = callTaskId;
        if (l != null) {
            INSTANCE.publishTopic(Topic.INSTANCE.getCallCompleteUp(), INSTANCE.getReq(new CallCompleteUpPayload(l.longValue(), status, msg)), true, true);
            Long l2 = (Long) null;
            callTaskId = l2;
            callRequestId = l2;
        }
    }

    public final void reportRobotWorkStatus(int status, String workDescribe2) {
        Intrinsics.checkParameterIsNotNull(workDescribe2, "workDescribe");
        Pdlog.m3273d(TAG, "reportRobotWorkStatus status: " + status + " , workDescribe:" + workDescribe2);
        workDescribe = workDescribe2;
        currentWorkStatus = status;
        if (lastWorkStatus == 0) {
            lastWorkStatus = currentWorkStatus;
        }
        reportRobotStatus();
        lastWorkStatus = currentWorkStatus;
    }

    public final void reportRobotStatus() {
        String str;
        int i;
        if (CoreDevices.INSTANCE.getBattery().getPowerState() == PowerState.EMPTY) {
            str = "电量低于2%";
            i = -1;
        } else {
            str = "可调度";
            i = 1;
        }
        if (CoreDevices.INSTANCE.getBattery().getChargerState() != ChargeState.Idle) {
            str = "充电中";
            i = -1;
        }
        if (lastScheduleStatus == 0) {
            lastScheduleStatus = i;
        }
        String str2 = "";
        if (currentWorkStatus == 0) {
            lastWorkStatus = 1;
            String str3 = MqttConfig.INSTANCE.getWorkMap().get(1);
            if (str3 == null) {
                str3 = "";
            }
            workDescribe = str3;
            i = 1;
        }
        Schedule schedule = new Schedule(i, lastScheduleStatus, str);
        Pdlog.m3273d(TAG, "reportRobotStatus isFreezeState: " + FreezeStateManger.INSTANCE.isFreezeState());
        int i2 = FreezeStateManger.INSTANCE.isFreezeState() ? 1 : currentWorkStatus;
        int i3 = lastWorkStatus;
        if (FreezeStateManger.INSTANCE.isFreezeState()) {
            String str4 = MqttConfig.INSTANCE.getWorkMap().get(1);
            if (str4 != null) {
                str2 = str4;
            }
        } else {
            str2 = workDescribe;
        }
        PayloadStatus payloadStatus = new PayloadStatus(schedule, new Work(i2, i3, str2), Integer.valueOf(CoreDevices.INSTANCE.getBattery().getPowerPercent()), shopId, RobotMapManager.INSTANCE.getDefaultPdmap(), CoreDevices.INSTANCE.getBattery().getChargerState() != ChargeState.Idle ? 1L : -1L);
        Pdlog.m3273d(TAG, "reportRobotStatus payloadStatus: " + payloadStatus);
        sendCommonStatusUpMsg(payloadStatus);
        lastScheduleStatus = i;
    }

    public final void reportRobotTaskStatus(int status) {
        Pdlog.m3273d(TAG, "reportRobotTaskStatus status: " + status);
        sendCallDetailUp(status);
    }

    private final void reportPosition(String subTopic) {
        Pdlog.m3273d(TAG, "Robot reportLocation");
        RobotPositionInfo positionInfo = PositionDataManager.INSTANCE.getPositionInfo();
        PositionVector positionVector = new PositionVector(positionInfo.getVector().getX(), positionInfo.getVector().getY(), positionInfo.getVector().getZ(), positionInfo.getVector().getFloor());
        long shopId2 = positionInfo.getShopId();
        String mapName = positionInfo.getMapName();
        String mac = WifiUtil.INSTANCE.getMac();
        if (mac == null) {
            mac = "";
        }
        PositionInfo positionInfo2 = new PositionInfo(shopId2, mapName, mac, positionInfo.getTimestamp(), positionVector);
        Pdlog.m3273d(TAG, "Robot position info=" + positionInfo2);
        MqttManager.INSTANCE.sendMessage(Topic.INSTANCE.getCommonMessageUp(), getSubTopicReq(subTopic, positionInfo2), new Function2<Boolean, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$reportPosition$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                invoke(bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, String str) {
                Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "sendCommonMessageUpMsg b:" + z + ",s:" + str + ' ');
            }
        });
    }

    public final void onRobotDeliveryStatus$module_bumblebee_presenter_robotRelease(DeliveryMode mode, TaskStatus status, ViewModel viewModel) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(viewModel, "viewModel");
        Pdlog.m3273d(TAG, "onRobotDeliveryStatus : mode:" + mode + " , status = " + status + "; target = " + viewModel.getDestination() + "; ");
        if (mode == DeliveryMode.CALL_DIRECT) {
            int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
            if (i == 1) {
                reportRobotTaskStatus(1);
                return;
            }
            if (i == 2 || i == 3) {
                FreezeStateManger.INSTANCE.setLastFinishTime(SystemClock.elapsedRealtime());
                sendCallCompleteUp(2L, "完成任务");
            } else {
                if (i != 4) {
                    return;
                }
                reportRobotTaskStatus(5);
                sendCallCompleteUp(0L, "任务被取消");
            }
        }
    }

    private final <T> String getReq(T payload) {
        long currentTimeMillis = System.currentTimeMillis();
        String json = gson.toJson(new MqttReq(Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), Topic.INSTANCE.getDeviceName(), MqttConfig.INSTANCE.getPRODUCT(), 1, payload));
        Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(req)");
        return json;
    }

    private final <T> String getSubTopicReq(String subTopic, T payload) {
        String json = gson.toJson(new SubTopic(subTopic, payload));
        Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(req)");
        return json;
    }

    private final void publishTopic(final String topic, final String payload, boolean ensurePublish, final boolean isWait) {
        if (ensurePublish) {
            postAsync(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$publishTopic$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).offer(new PublishPayload(topic, payload, isWait, 0, 8, null));
                    int size = RobotNewOpenManager.access$getPublishTask$p(RobotNewOpenManager.INSTANCE).size();
                    Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "publishObserver publishTask.size:" + size + " MqttManager.isConnect():" + MqttManager.INSTANCE.isConnect());
                    if (size == 1 && Intrinsics.areEqual((Object) MqttManager.INSTANCE.isConnect(), (Object) true)) {
                        RobotNewOpenManager.access$getHandler$p(RobotNewOpenManager.INSTANCE).sendEmptyMessage(1);
                    }
                }
            });
        } else {
            publishTopic(topic, payload, new Function2<Boolean, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$publishTopic$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                    invoke(bool.booleanValue(), str);
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z, String str) {
                    Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "init publishTopic b" + z + ", s:" + str + ' ');
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void publishTopic$default(RobotNewOpenManager robotNewOpenManager, String str, String str2, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            function2 = (Function2) null;
        }
        robotNewOpenManager.publishTopic(str, str2, function2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void publishTopic(String topic, String payload, Function2<? super Boolean, ? super String, Unit> callback) {
        Pdlog.m3273d(TAG, "publishTopic topic:" + topic + ",payload:" + payload + ",callback:" + callback + ' ');
        MqttManager.INSTANCE.sendMessage(topic, payload, callback);
    }

    private final void setPublishStatusTimeout() {
        Handler handler2 = handler;
        if (handler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handler");
        }
        handler2.removeMessages(PUBLISH_STATUS_TIME_OUT_WHAT);
        Handler handler3 = handler;
        if (handler3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handler");
        }
        handler3.sendEmptyMessageDelayed(PUBLISH_STATUS_TIME_OUT_WHAT, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removePublishStatusTimeout() {
        Handler handler2 = handler;
        if (handler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handler");
        }
        handler2.removeMessages(PUBLISH_STATUS_TIME_OUT_WHAT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void republishStatus() {
        isPublishingRobotStatus = false;
        if (robotStatus != null) {
            robotStatus = (PayloadStatus) null;
            reportRobotStatus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void timeoutRepublishStatus() {
        isPublishingRobotStatus = false;
        robotStatus = (PayloadStatus) null;
        reportRobotStatus();
    }

    private final synchronized void sendCommonStatusUpMsg(PayloadStatus payload) {
        Boolean isConnect = MqttManager.INSTANCE.isConnect();
        Pdlog.m3273d(TAG, "sendCommonStatusUpMsg isPublishingRobotStatus:" + isPublishingRobotStatus + ",payload:" + payload + ",robotStatus:" + robotStatus + ",connect:" + isConnect + ' ');
        if (!Intrinsics.areEqual((Object) isConnect, (Object) true)) {
            return;
        }
        if (!isPublishingRobotStatus) {
            isPublishingRobotStatus = true;
            setPublishStatusTimeout();
            publishTopic(Topic.INSTANCE.getCommonStatusUp(), getReq(payload), new Function2<Boolean, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$sendCommonStatusUpMsg$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                    invoke(bool.booleanValue(), str);
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z, String str) {
                    Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "sendCommonStatusUpMsg b:" + z + ",s:" + str + ' ');
                }
            });
        } else {
            robotStatus = payload;
        }
    }

    public final void addBeeperListener(BeeperTaskListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        getBeeperTaskListener().addListener(listener);
    }

    public final void removeBeeperListener(BeeperTaskListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        getBeeperTaskListener().removeListener(listener);
    }

    public final void release() {
        MqttManager.INSTANCE.destroy();
        handlerThread.quitSafely();
        Handler handler2 = handler;
        if (handler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handler");
        }
        handler2.removeCallbacksAndMessages(null);
        robotStatusNotify.release();
        MqttManager.INSTANCE.setConnectStatusListener((Function1) null);
        MqttManager.INSTANCE.setSubscribeListener((Function2) null);
        FreezeStateManger.INSTANCE.getFreezeStateListener().removeListener(freezeStateListener);
    }
}
