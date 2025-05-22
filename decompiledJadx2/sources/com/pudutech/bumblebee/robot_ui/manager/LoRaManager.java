package com.pudutech.bumblebee.robot_ui.manager;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.pudu.library.loracall.ArriveCancelMsg;
import com.pudu.library.loracall.BaseMsg;
import com.pudu.library.loracall.LoRaClient;
import com.pudu.library.loracall.LoraMsg;
import com.pudu.library.loracall.LoraNetState;
import com.pudu.library.loracall.LoraUpdateProgressState;
import com.pudu.library.loracall.LoraUpdateState;
import com.pudu.library.loracall.MsgReceiveHandle;
import com.pudu.library.loracall.ReceiveMsgType;
import com.pudu.library.loracall.bean.LoRaVersionParam;
import com.pudu.library.loracall.bean.LoraReceiveCall;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.presenter.robot_open_task.BeeperCallContract;
import com.pudutech.bumblebee.presenter.robot_open_task.FreezeStateManger;
import com.pudutech.bumblebee.presenter.robot_open_task.config.ActionConfig;
import com.pudutech.bumblebee.presenter.robot_open_task.interf.OnCallNaviStateChangeListener;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.FreezeStateListener;
import com.pudutech.bumblebee.robot_ui.manager.LoRaConnectState;
import com.pudutech.bumblebee.robot_ui.manager.LoRaUpdateState;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.disinfect.baselib.network.response.Gateway;
import com.pudutech.disinfect.baselib.network.response.KeyBtnWithDestination;
import com.pudutech.disinfect.baselib.network.response.RfSetting;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.pd_network.report.utils.GsonUtils;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: LoRaManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0004&,7:\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u0016H\u0002J\u0010\u0010B\u001a\u00020@2\u0006\u0010A\u001a\u00020\u0016H\u0002J\u0010\u0010C\u001a\u00020@2\u0006\u0010D\u001a\u00020\u0004H\u0002J\u0011\u0010E\u001a\u00020\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0010\u0010G\u001a\u00020)2\u0006\u0010D\u001a\u00020\u0004H\u0002J\u0010\u0010H\u001a\u00020)2\u0006\u0010A\u001a\u00020\u0016H\u0002J\u0010\u0010I\u001a\u00020@2\u0006\u0010D\u001a\u00020\u0004H\u0002J\u0019\u0010J\u001a\u00020)2\u0006\u0010K\u001a\u00020LH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u0011\u0010N\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u0012\u0010O\u001a\u0004\u0018\u00010\u00042\u0006\u0010A\u001a\u00020\u0016H\u0002J\u0010\u0010P\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u0016H\u0002J\u000e\u0010Q\u001a\u00020@2\u0006\u0010R\u001a\u00020SJ\b\u0010(\u001a\u00020)H\u0002J\u0006\u0010T\u001a\u00020)J\u0011\u0010U\u001a\u00020VH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010FJ\u001a\u0010W\u001a\u00020@2\u0006\u0010X\u001a\u00020Y2\b\u0010D\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010Z\u001a\u00020@2\u0006\u0010A\u001a\u00020\u0016H\u0002J\u0010\u0010[\u001a\u00020@2\u0006\u0010\\\u001a\u00020]H\u0002J\u0010\u0010^\u001a\u00020@2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\b\u0010_\u001a\u00020@H\u0002J\u0019\u0010`\u001a\u00020)2\u0006\u0010a\u001a\u00020bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010cJ\b\u0010d\u001a\u00020@H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nj\u0002`\r0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u00188F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR6\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e2\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e8F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0010\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0004\n\u0002\u0010'R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0004\n\u0002\u0010-R'\u0010.\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nj\u0002`\r0/¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u000f0/¢\u0006\b\n\u0000\u001a\u0004\b3\u00101R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\u000b0/¢\u0006\b\n\u0000\u001a\u0004\b5\u00101R\u0010\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0004\n\u0002\u00108R\u0010\u00109\u001a\u00020:X\u0082\u0004¢\u0006\u0004\n\u0002\u0010;R\u0010\u0010<\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020>X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaManager;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/interf/OnCallNaviStateChangeListener;", "()V", LoRaManager.CRT_LORA_GATE_WAY, "", LoRaManager.CRT_LORA_GATE_WAY_KEY_BTN_LIST, "TAG", "kotlin.jvm.PlatformType", "_loRaUpdateStateWithProgressFL", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlin/Pair;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState;", "", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateStateWithProgress;", "_loraConnectStateFL", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState;", "_loraUpdateStateFL", "callback", "Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperCallContract$ViewInterface;", "completeJob", "Lkotlinx/coroutines/Job;", "crtCall", "Lcom/pudu/library/loracall/bean/LoraReceiveCall;", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/disinfect/baselib/network/response/Gateway;", "crtGateway", "getCrtGateway", "()Lcom/pudutech/disinfect/baselib/network/response/Gateway;", "setCrtGateway", "(Lcom/pudutech/disinfect/baselib/network/response/Gateway;)V", "", "Lcom/pudutech/disinfect/baselib/network/response/KeyBtnWithDestination;", "crtKeyBtnList", "getCrtKeyBtnList", "()Ljava/util/List;", "setCrtKeyBtnList", "(Ljava/util/List;)V", "freezeStateListener", "com/pudutech/bumblebee/robot_ui/manager/LoRaManager$freezeStateListener$1", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaManager$freezeStateListener$1;", "isBusy", "", "isFreeze", "listener", "com/pudutech/bumblebee/robot_ui/manager/LoRaManager$listener$1", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaManager$listener$1;", "loRaUpdateStateWithProgressFL", "Lkotlinx/coroutines/flow/Flow;", "getLoRaUpdateStateWithProgressFL", "()Lkotlinx/coroutines/flow/Flow;", "loraConnectStateFL", "getLoraConnectStateFL", "loraUpdateStateFL", "getLoraUpdateStateFL", "mBusyListener", "com/pudutech/bumblebee/robot_ui/manager/LoRaManager$mBusyListener$1", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaManager$mBusyListener$1;", "mSwitchMapLis", "com/pudutech/bumblebee/robot_ui/manager/LoRaManager$mSwitchMapLis$1", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaManager$mSwitchMapLis$1;", "reportJob", "scope", "Lkotlinx/coroutines/CoroutineScope;", NotificationCompat.CATEGORY_CALL, "", "receiveCall", ActionConfig.ACTION_CANCEL_CALL, "cancelTask", TypedValues.Attributes.S_TARGET, "checkConnectState", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkIfNeedReport", "checkRobotState", "completeTask", MqttServiceConstants.CONNECT_ACTION, "config", "Lcom/pudutech/bumblebee/robot_ui/manager/GatewayConfig;", "(Lcom/pudutech/bumblebee/robot_ui/manager/GatewayConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchRssi", "findMapPointByCall", "getTargetByCall", "init", "context", "Landroid/content/Context;", "loraExit", "loraInfo", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaInfo;", "onChange", "state", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "preCall", "sendMsg", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudu/library/loracall/BaseMsg;", "setCallback", "startReportStatus", "startUpdate", "file", "Ljava/io/File;", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trueReportStatus", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LoRaManager implements OnCallNaviStateChangeListener {
    private static final String CRT_LORA_GATE_WAY = "CRT_LORA_GATE_WAY";
    private static final String CRT_LORA_GATE_WAY_KEY_BTN_LIST = "CRT_LORA_GATE_WAY_KEY_BTN_LIST";
    public static final LoRaManager INSTANCE;
    private static final String TAG;
    private static final MutableStateFlow<Pair<LoRaUpdateState, Integer>> _loRaUpdateStateWithProgressFL;
    private static final MutableStateFlow<LoRaConnectState> _loraConnectStateFL;
    private static final MutableStateFlow<LoRaUpdateState> _loraUpdateStateFL;
    private static BeeperCallContract.ViewInterface callback;
    private static Job completeJob;
    private static LoraReceiveCall crtCall;
    private static Gateway crtGateway;
    private static List<KeyBtnWithDestination> crtKeyBtnList;
    private static final LoRaManager$freezeStateListener$1 freezeStateListener;
    private static boolean isBusy;
    private static boolean isFreeze;
    private static final LoRaManager$listener$1 listener;
    private static final Flow<Pair<LoRaUpdateState, Integer>> loRaUpdateStateWithProgressFL;
    private static final Flow<LoRaConnectState> loraConnectStateFL;
    private static final Flow<LoRaUpdateState> loraUpdateStateFL;
    private static final LoRaManager$mBusyListener$1 mBusyListener;
    private static final LoRaManager$mSwitchMapLis$1 mSwitchMapLis;
    private static Job reportJob;
    private static final CoroutineScope scope;

    /* JADX WARN: Type inference failed for: r0v19, types: [com.pudutech.bumblebee.robot_ui.manager.LoRaManager$mBusyListener$1] */
    /* JADX WARN: Type inference failed for: r0v20, types: [com.pudutech.bumblebee.robot_ui.manager.LoRaManager$freezeStateListener$1] */
    /* JADX WARN: Type inference failed for: r0v21, types: [com.pudutech.bumblebee.robot_ui.manager.LoRaManager$mSwitchMapLis$1] */
    /* JADX WARN: Type inference failed for: r0v22, types: [com.pudutech.bumblebee.robot_ui.manager.LoRaManager$listener$1] */
    static {
        LoRaManager loRaManager = new LoRaManager();
        INSTANCE = loRaManager;
        TAG = loRaManager.getClass().getSimpleName();
        scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        _loraConnectStateFL = StateFlowKt.MutableStateFlow(LoRaConnectState.Default.INSTANCE);
        _loraUpdateStateFL = StateFlowKt.MutableStateFlow(LoRaUpdateState.Idle.INSTANCE);
        _loRaUpdateStateWithProgressFL = StateFlowKt.MutableStateFlow(TuplesKt.m3968to(LoRaUpdateState.Idle.INSTANCE, 0));
        loraConnectStateFL = _loraConnectStateFL;
        loraUpdateStateFL = _loraUpdateStateFL;
        loRaUpdateStateWithProgressFL = _loRaUpdateStateWithProgressFL;
        mBusyListener = new BusyListener() { // from class: com.pudutech.bumblebee.robot_ui.manager.LoRaManager$mBusyListener$1
            @Override // com.pudutech.bumblebee.robot_ui.manager.BusyListener
            public void onBusyState(boolean b) {
                boolean z;
                boolean z2;
                String access$getTAG$p = LoRaManager.access$getTAG$p(LoRaManager.INSTANCE);
                StringBuilder sb = new StringBuilder();
                sb.append("onBusyState > ");
                sb.append(b);
                sb.append(" crtState:");
                LoRaManager loRaManager2 = LoRaManager.INSTANCE;
                z = LoRaManager.isBusy;
                sb.append(z);
                Pdlog.m3275i(access$getTAG$p, sb.toString());
                LoRaManager loRaManager3 = LoRaManager.INSTANCE;
                z2 = LoRaManager.isBusy;
                if (z2 == b) {
                    return;
                }
                LoRaManager loRaManager4 = LoRaManager.INSTANCE;
                LoRaManager.isBusy = b;
                LoRaManager.INSTANCE.trueReportStatus();
            }
        };
        freezeStateListener = new FreezeStateListener() { // from class: com.pudutech.bumblebee.robot_ui.manager.LoRaManager$freezeStateListener$1
            @Override // com.pudutech.bumblebee.presenter.robot_open_task.listener.FreezeStateListener
            public void onFreezeStateChange(boolean state) {
                boolean z;
                boolean z2;
                String access$getTAG$p = LoRaManager.access$getTAG$p(LoRaManager.INSTANCE);
                StringBuilder sb = new StringBuilder();
                sb.append("onFreezeStateChange > ");
                sb.append(state);
                sb.append(" crtState:");
                LoRaManager loRaManager2 = LoRaManager.INSTANCE;
                z = LoRaManager.isFreeze;
                sb.append(z);
                Pdlog.m3275i(access$getTAG$p, sb.toString());
                LoRaManager loRaManager3 = LoRaManager.INSTANCE;
                z2 = LoRaManager.isFreeze;
                if (z2 == state) {
                    return;
                }
                LoRaManager loRaManager4 = LoRaManager.INSTANCE;
                LoRaManager.isFreeze = state;
                LoRaManager.INSTANCE.trueReportStatus();
            }
        };
        mSwitchMapLis = new RobotMapManager.SwitchMapResultListener() { // from class: com.pudutech.bumblebee.robot_ui.manager.LoRaManager$mSwitchMapLis$1
            @Override // com.pudutech.mirsdkwrap.lib.map.RobotMapManager.SwitchMapResultListener
            public void onResult(boolean b, String errorMsg) {
                Pdlog.m3275i(LoRaManager.access$getTAG$p(LoRaManager.INSTANCE), "mSwitchMapLis > " + b);
                if (b) {
                    LoRaManager.INSTANCE.sendMsg(new LoRaMsgRefreshMap());
                }
            }
        };
        listener = new MsgReceiveHandle.Listener() { // from class: com.pudutech.bumblebee.robot_ui.manager.LoRaManager$listener$1
            @Override // com.pudu.library.loracall.MsgReceiveHandle.Listener
            public void receive(ReceiveMsgType msgType) {
                MutableStateFlow mutableStateFlow;
                MutableStateFlow mutableStateFlow2;
                MutableStateFlow mutableStateFlow3;
                MutableStateFlow mutableStateFlow4;
                String addrHexStr;
                Job job;
                Intrinsics.checkParameterIsNotNull(msgType, "msgType");
                Pdlog.m3275i(LoRaManager.access$getTAG$p(LoRaManager.INSTANCE), "receive > " + msgType);
                if (msgType instanceof LoraMsg) {
                    LoraReceiveCall loraReceiveCall = ((LoraMsg) msgType).toLoraReceiveCall();
                    if (loraReceiveCall != null) {
                        String access$getTAG$p = LoRaManager.access$getTAG$p(LoRaManager.INSTANCE);
                        StringBuilder sb = new StringBuilder();
                        sb.append("msg.receive > callType: ");
                        sb.append(loraReceiveCall.getType());
                        sb.append(" addr:");
                        addrHexStr = LoRaManagerKt.addrHexStr(loraReceiveCall);
                        sb.append(addrHexStr);
                        Pdlog.m3273d(access$getTAG$p, sb.toString());
                        int type = loraReceiveCall.getType();
                        if (type != 1) {
                            if (type == 2) {
                                LoRaManager.INSTANCE.call(loraReceiveCall);
                                return;
                            }
                            if (type == 3) {
                                LoRaManager.INSTANCE.cancelCall(loraReceiveCall);
                                return;
                            }
                            if (type == 4) {
                                LoRaManager.INSTANCE.preCall(loraReceiveCall);
                                return;
                            }
                            if (type != 5) {
                                return;
                            }
                            LoRaManager loRaManager2 = LoRaManager.INSTANCE;
                            job = LoRaManager.completeJob;
                            if (job != null) {
                                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (msgType instanceof LoraUpdateProgressState) {
                    LoRaManager loRaManager3 = LoRaManager.INSTANCE;
                    mutableStateFlow3 = LoRaManager._loRaUpdateStateWithProgressFL;
                    LoRaManager loRaManager4 = LoRaManager.INSTANCE;
                    mutableStateFlow4 = LoRaManager._loraUpdateStateFL;
                    mutableStateFlow3.setValue(TuplesKt.m3968to(mutableStateFlow4.getValue(), Integer.valueOf(((LoraUpdateProgressState) msgType).getShowProgress())));
                    return;
                }
                if (msgType instanceof LoraUpdateState) {
                    LoRaManager loRaManager5 = LoRaManager.INSTANCE;
                    mutableStateFlow2 = LoRaManager._loraUpdateStateFL;
                    mutableStateFlow2.setValue(LoRaUpdateState.INSTANCE.fromCode(((LoraUpdateState) msgType).getState()));
                } else if (msgType instanceof LoraNetState) {
                    LoRaManager loRaManager6 = LoRaManager.INSTANCE;
                    mutableStateFlow = LoRaManager._loraConnectStateFL;
                    mutableStateFlow.setValue(LoRaConnectState.INSTANCE.fromCode(((LoraNetState) msgType).getState()));
                }
            }
        };
    }

    private LoRaManager() {
    }

    public static final /* synthetic */ String access$getTAG$p(LoRaManager loRaManager) {
        return TAG;
    }

    public final Flow<LoRaConnectState> getLoraConnectStateFL() {
        return loraConnectStateFL;
    }

    public final Flow<LoRaUpdateState> getLoraUpdateStateFL() {
        return loraUpdateStateFL;
    }

    public final Flow<Pair<LoRaUpdateState, Integer>> getLoRaUpdateStateWithProgressFL() {
        return loRaUpdateStateWithProgressFL;
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new LoRaManager$init$1(context, null), 3, null);
        RobotStatusManager.INSTANCE.getBusyListener().addListener(mBusyListener);
        RobotMapManager.INSTANCE.addSwitchMapResultListener(mSwitchMapLis);
        FreezeStateManger.INSTANCE.getFreezeStateListener().addListener(freezeStateListener);
        startReportStatus();
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.interf.OnCallNaviStateChangeListener
    public void onChange(TaskStatus state, String target) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Pdlog.m3275i(TAG, "onChange > " + state + ' ' + target);
        switch (state) {
            case AWAIT:
            case ON_THE_WAY:
            case APPROACHING:
            case ARRIVAL:
            case DONE_BEFORE_ARRIVAL:
            default:
                return;
            case DONE:
                if (target == null) {
                    target = "";
                }
                completeTask(target);
                return;
            case CANCEL:
                if (target == null) {
                    target = "";
                }
                cancelTask(target);
                return;
        }
    }

    public final boolean loraExit() {
        return LoRaClient.INSTANCE.getInstance().isExistLora();
    }

    public final void setCallback(BeeperCallContract.ViewInterface callback2) {
        callback = callback2;
    }

    public final Object fetchRssi(Continuation<? super Integer> continuation) {
        return LoRaClient.INSTANCE.getInstance().fetchLoRaRssi(continuation);
    }

    public final Gateway getCrtGateway() {
        boolean z = true;
        Pdlog.m3275i(TAG, "crtGateway.get > " + crtGateway);
        Gateway gateway = crtGateway;
        if (gateway != null) {
            return gateway;
        }
        Gateway gateway2 = null;
        try {
            Gateway gateway3 = (Gateway) GsonUtils.gsonToBean(MMKVManager.INSTANCE.getINSTANCE().getString(CRT_LORA_GATE_WAY), Gateway.class);
            String name = gateway3.getName();
            if (name != null) {
                if (name.length() != 0) {
                    z = false;
                }
            }
            if (!z) {
                gateway2 = gateway3;
            }
        } catch (Exception unused) {
        }
        crtGateway = gateway2;
        return crtGateway;
    }

    public final void setCrtGateway(Gateway gateway) {
        Pdlog.m3275i(TAG, "crtGateway.set > " + gateway);
        crtGateway = gateway;
        if (gateway == null) {
            MMKVManager.INSTANCE.getINSTANCE().set(CRT_LORA_GATE_WAY, "");
            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new LoRaManager$crtGateway$1(null), 3, null);
        } else {
            RfSetting rf_setting = gateway.getRf_setting();
            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new LoRaManager$crtGateway$2$1(new GatewayConfig(String.valueOf(rf_setting.getRF3_f1()), String.valueOf(rf_setting.getRF1_f1()), String.valueOf(rf_setting.getRF2_f1()), String.valueOf(rf_setting.getNetwork()), "10"), null), 3, null);
            MMKVManager.INSTANCE.getINSTANCE().set(CRT_LORA_GATE_WAY, GsonUtils.toJson(gateway));
        }
    }

    public final List<KeyBtnWithDestination> getCrtKeyBtnList() {
        boolean z = true;
        Pdlog.m3275i(TAG, "crtKeyBtnList.get > " + crtKeyBtnList);
        List<KeyBtnWithDestination> list = crtKeyBtnList;
        if (list != null) {
            return list;
        }
        List<KeyBtnWithDestination> list2 = null;
        try {
            List<KeyBtnWithDestination> jsonToList = GsonUtils.jsonToList(MMKVManager.INSTANCE.getINSTANCE().getString(CRT_LORA_GATE_WAY_KEY_BTN_LIST), KeyBtnWithDestination.class);
            List<KeyBtnWithDestination> list3 = jsonToList;
            if (list3 != null) {
                if (!list3.isEmpty()) {
                    z = false;
                }
            }
            if (!z) {
                list2 = jsonToList;
            }
        } catch (Exception unused) {
        }
        crtKeyBtnList = list2;
        return crtKeyBtnList;
    }

    public final void setCrtKeyBtnList(List<KeyBtnWithDestination> list) {
        Pdlog.m3275i(TAG, "crtKeyBtnList.set > " + list);
        crtKeyBtnList = list;
        MMKVManager.INSTANCE.getINSTANCE().set(CRT_LORA_GATE_WAY_KEY_BTN_LIST, GsonUtils.toJson(list));
        sendMsg(new LoRaMsgRefreshMap());
    }

    private final void startReportStatus() {
        Job launch$default;
        Job job = reportJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new LoRaManager$startReportStatus$1(null), 3, null);
        reportJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trueReportStatus() {
        if (loraExit()) {
            sendMsg(new LoRaMsgRobotState(isBusy()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendMsg(final BaseMsg msg) {
        Pdlog.m3275i(TAG, "msg.send > " + msg);
        LoRaClient.INSTANCE.getInstance().sendHaveAckMsg(msg, new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.LoRaManager$sendMsg$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                Pdlog.m3275i(LoRaManager.access$getTAG$p(LoRaManager.INSTANCE), "msg.send.res > " + z + ' ' + BaseMsg.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void preCall(LoraReceiveCall receiveCall) {
        if (!checkRobotState(receiveCall)) {
            Pdlog.m3275i(TAG, "preCall > reject " + receiveCall);
            sendMsg(new LoRaMsgCallRes(receiveCall.getDevAddr(), 0, 0.0d, 4, null));
            return;
        }
        String findMapPointByCall = findMapPointByCall(receiveCall);
        if (findMapPointByCall == null) {
            INSTANCE.sendMsg(new LoRaMsgCallRes(receiveCall.getDevAddr(), 3, 0.0d, 4, null));
            return;
        }
        double d = 0.0d;
        try {
            RobotMoveInterfaceDecorator moveActionInterface = RobotMoveManager.INSTANCE.getMoveActionInterface();
            if (moveActionInterface != null) {
                d = moveActionInterface.getDestinationRange(findMapPointByCall);
            }
        } catch (Exception e) {
            Pdlog.m3275i(TAG, Log.getStackTraceString(e));
        }
        Pdlog.m3273d(TAG, "preCall > target:" + findMapPointByCall + " distance:" + d);
        sendMsg(new LoRaMsgCallRes(receiveCall.getDevAddr(), 2, d));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelCall(LoraReceiveCall receiveCall) {
        String str;
        try {
            str = getTargetByCall(receiveCall);
        } catch (Exception e) {
            Pdlog.m3275i(TAG, Log.getStackTraceString(e));
            str = null;
        }
        if (str != null) {
            BuildersKt__Builders_commonKt.launch$default(scope, Dispatchers.getMain(), null, new LoRaManager$cancelCall$1$1(str, null), 2, null);
        }
        sendMsg(new ArriveCancelMsg(false, receiveCall.getDevAddr()));
        crtCall = (LoraReceiveCall) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void call(LoraReceiveCall receiveCall) {
        if (crtCall != null) {
            byte[] devAddr = receiveCall.getDevAddr();
            LoraReceiveCall loraReceiveCall = crtCall;
            if (loraReceiveCall == null) {
                Intrinsics.throwNpe();
            }
            if (Arrays.equals(devAddr, loraReceiveCall.getDevAddr())) {
                Pdlog.m3273d(TAG, "checkRobotState: same call");
                sendMsg(new LoRaMsgCallRes(receiveCall.getDevAddr(), 1, 0.0d, 4, null));
                return;
            }
        }
        if (!checkRobotState(receiveCall)) {
            Pdlog.m3275i(TAG, "call > reject " + receiveCall);
            sendMsg(new LoRaMsgCallRes(receiveCall.getDevAddr(), 0, 0.0d, 4, null));
            return;
        }
        String findMapPointByCall = findMapPointByCall(receiveCall);
        if (findMapPointByCall == null) {
            INSTANCE.sendMsg(new LoRaMsgCallRes(receiveCall.getDevAddr(), 3, 0.0d, 4, null));
            return;
        }
        if (callback != null) {
            BuildersKt__Builders_commonKt.launch$default(scope, Dispatchers.getMain(), null, new LoRaManager$call$1(findMapPointByCall, null), 2, null);
            crtCall = receiveCall;
            sendMsg(new LoRaMsgCallRes(receiveCall.getDevAddr(), 1, 0.0d, 4, null));
        } else {
            Pdlog.m3275i(TAG, "call > callback==null");
            sendMsg(new LoRaMsgCallRes(receiveCall.getDevAddr(), 0, 0.0d, 4, null));
        }
    }

    private final boolean checkRobotState(LoraReceiveCall receiveCall) {
        if (crtCall != null) {
            byte[] devAddr = receiveCall.getDevAddr();
            LoraReceiveCall loraReceiveCall = crtCall;
            if (loraReceiveCall == null) {
                Intrinsics.throwNpe();
            }
            if (Arrays.equals(devAddr, loraReceiveCall.getDevAddr())) {
                Pdlog.m3273d(TAG, "checkRobotState: same call");
                return false;
            }
        }
        if (isBusy()) {
            Pdlog.m3273d(TAG, "checkRobotState: isBusy");
            return false;
        }
        if (CheckLocationHelper.INSTANCE.isLocated()) {
            return true;
        }
        Pdlog.m3273d(TAG, "checkRobotState: lost location");
        return false;
    }

    private final String findMapPointByCall(LoraReceiveCall receiveCall) {
        try {
            return getTargetByCall(receiveCall);
        } catch (Exception e) {
            Pdlog.m3275i(TAG, Log.getStackTraceString(e));
            return null;
        }
    }

    private final boolean isBusy() {
        if (isFreeze || isBusy) {
            return true;
        }
        Job job = completeJob;
        return job != null && job.isActive();
    }

    private final String getTargetByCall(LoraReceiveCall receiveCall) {
        Object obj;
        Object obj2;
        if (getCrtGateway() == null) {
            throw new IllegalArgumentException("no gateway config");
        }
        List<KeyBtnWithDestination> crtKeyBtnList2 = getCrtKeyBtnList();
        if (crtKeyBtnList2 == null || crtKeyBtnList2.isEmpty()) {
            throw new IllegalArgumentException("no keyBtn list");
        }
        String access$addrHexStr = LoRaManagerKt.access$addrHexStr(receiveCall);
        List<KeyBtnWithDestination> crtKeyBtnList3 = getCrtKeyBtnList();
        if (crtKeyBtnList3 == null) {
            Intrinsics.throwNpe();
        }
        Iterator<T> it = crtKeyBtnList3.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            String dev_addr = ((KeyBtnWithDestination) obj).getDev_addr();
            if ((dev_addr != null ? Boolean.valueOf(StringsKt.equals(dev_addr, access$addrHexStr, true)) : null).booleanValue()) {
                break;
            }
        }
        KeyBtnWithDestination keyBtnWithDestination = (KeyBtnWithDestination) obj;
        if (keyBtnWithDestination == null) {
            throw new IllegalArgumentException("no keyBtn ：" + access$addrHexStr);
        }
        Iterator<T> it2 = RobotMapManager.INSTANCE.getAllDestination().iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it2.next();
            if (Intrinsics.areEqual(((Destination) obj2).getName(), keyBtnWithDestination.getMap_point())) {
                break;
            }
        }
        if (((Destination) obj2) == null) {
            throw new IllegalArgumentException("map no same point");
        }
        String defaultPdmap = RobotMapManager.INSTANCE.getDefaultPdmap();
        if (!Intrinsics.areEqual(defaultPdmap, keyBtnWithDestination.getMap())) {
            throw new IllegalArgumentException("not same map>robotMap:" + defaultPdmap + ' ');
        }
        return keyBtnWithDestination.getMap_point();
    }

    private final void completeTask(String target) {
        Job launch$default;
        Pdlog.m3275i(TAG, "arrive >>  " + target + ' ' + crtCall + ' ' + getCrtKeyBtnList());
        if (checkIfNeedReport(target)) {
            FreezeStateManger.INSTANCE.setLastFinishTime(SystemClock.elapsedRealtime());
            Job job = completeJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new LoRaManager$completeTask$1(null), 3, null);
            completeJob = launch$default;
            return;
        }
        crtCall = (LoraReceiveCall) null;
    }

    private final void cancelTask(String target) {
        Pdlog.m3275i(TAG, "cancel >>  " + target + ' ' + crtCall + ' ' + getCrtKeyBtnList());
        if (checkIfNeedReport(target)) {
            LoraReceiveCall loraReceiveCall = crtCall;
            if (loraReceiveCall == null) {
                Intrinsics.throwNpe();
            }
            sendMsg(new ArriveCancelMsg(false, loraReceiveCall.getDevAddr()));
        }
        crtCall = (LoraReceiveCall) null;
    }

    private final boolean checkIfNeedReport(String target) {
        Object obj;
        Pdlog.m3275i(TAG, "checkIfNeedReport >>  " + target);
        if (crtCall != null && getCrtKeyBtnList() != null) {
            LoraReceiveCall loraReceiveCall = crtCall;
            if (loraReceiveCall == null) {
                Intrinsics.throwNpe();
            }
            String access$addrHexStr = LoRaManagerKt.access$addrHexStr(loraReceiveCall);
            List<KeyBtnWithDestination> crtKeyBtnList2 = getCrtKeyBtnList();
            if (crtKeyBtnList2 != null) {
                Iterator<T> it = crtKeyBtnList2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (StringsKt.equals(((KeyBtnWithDestination) obj).getDev_addr(), access$addrHexStr, true)) {
                        break;
                    }
                }
                KeyBtnWithDestination keyBtnWithDestination = (KeyBtnWithDestination) obj;
                if (keyBtnWithDestination != null) {
                    return StringsKt.equals(keyBtnWithDestination.getMap_point(), target, true);
                }
            }
        }
        return false;
    }

    public final Object connect(GatewayConfig gatewayConfig, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        Pdlog.m3275i(access$getTAG$p(INSTANCE), "connect.setLoRaConfig.start " + gatewayConfig);
        LoRaClient companion = LoRaClient.INSTANCE.getInstance();
        byte[] codingData = gatewayConfig.toLoRaConfig$robot_ui_robotRelease().codingData();
        if (codingData == null) {
            codingData = new byte[0];
        }
        companion.setLoRaConfig(codingData, new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.LoRaManager$connect$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                Pdlog.m3275i(LoRaManager.access$getTAG$p(LoRaManager.INSTANCE), "connect.setLoRaConfig.res > " + z);
                CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                Boolean valueOf = Boolean.valueOf(z);
                Result.Companion companion2 = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m4510constructorimpl(valueOf));
                LoRaManager.INSTANCE.sendMsg(new LoRaMsgRefreshMap());
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object checkConnectState(Continuation<? super LoRaConnectState> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        LoRaClient.INSTANCE.getInstance().getLoRaNetState(new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.LoRaManager$checkConnectState$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                MutableStateFlow mutableStateFlow;
                Pdlog.m3275i(LoRaManager.access$getTAG$p(LoRaManager.INSTANCE), "checkConnectState > " + i);
                LoRaConnectState fromCode = LoRaConnectState.INSTANCE.fromCode(i);
                LoRaManager loRaManager = LoRaManager.INSTANCE;
                mutableStateFlow = LoRaManager._loraConnectStateFL;
                mutableStateFlow.setValue(fromCode);
                CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m4510constructorimpl(fromCode));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object loraInfo(Continuation<? super LoRaInfo> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        Pdlog.m3275i(access$getTAG$p(INSTANCE), "loraInfo");
        LoRaClient.INSTANCE.getInstance().getLoRaVersion(new Function1<LoRaVersionParam, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.LoRaManager$loraInfo$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoRaVersionParam loRaVersionParam) {
                invoke2(loRaVersionParam);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoRaVersionParam it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                LoRaInfo fromLoRaVersionParam = LoRaInfo.INSTANCE.fromLoRaVersionParam(it);
                Pdlog.m3275i(LoRaManager.access$getTAG$p(LoRaManager.INSTANCE), "loraInfo " + fromLoRaVersionParam + "  " + LoRaClient.INSTANCE.getInstance().isExistLora());
                CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m4510constructorimpl(fromLoRaVersionParam));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object startUpdate(File file, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        Pdlog.m3275i(access$getTAG$p(INSTANCE), "startUpdate > " + file.getPath());
        LoRaClient companion = LoRaClient.INSTANCE.getInstance();
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkExpressionValueIsNotNull(absolutePath, "file.absolutePath");
        companion.startUpdate(absolutePath, new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.LoRaManager$startUpdate$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                Pdlog.m3275i(LoRaManager.access$getTAG$p(LoRaManager.INSTANCE), "startUpdate " + z);
                CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                Boolean valueOf = Boolean.valueOf(z);
                Result.Companion companion2 = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m4510constructorimpl(valueOf));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
