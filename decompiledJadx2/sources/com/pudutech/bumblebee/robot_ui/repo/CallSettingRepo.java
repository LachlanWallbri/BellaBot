package com.pudutech.bumblebee.robot_ui.repo;

import android.util.Log;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.bean.ReturnPointBean;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.GatewayConfig;
import com.pudutech.bumblebee.robot_ui.manager.LoRaConnectState;
import com.pudutech.bumblebee.robot_ui.manager.LoRaInfo;
import com.pudutech.bumblebee.robot_ui.manager.LoRaManager;
import com.pudutech.bumblebee.robot_ui.manager.LoRaUpdateState;
import com.pudutech.bumblebee.robot_ui.util.BeeperUtils;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.response.G4CodeData;
import com.pudutech.disinfect.baselib.network.response.Gateway;
import com.pudutech.disinfect.baselib.network.response.GatewayContainer;
import com.pudutech.disinfect.baselib.network.response.KeyBtnWithDestination;
import com.pudutech.disinfect.baselib.network.response.KeyBtnWithDestinationContainer;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.lib_update.UpdateManager;
import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.PdNetworkManagerKt;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.ServiceGatewayConfig;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.storage.ExtKt;
import com.pudutech.pd_network.utils.NetDataUtils;
import com.pudutech.robot.opensdk.RemoteConnectState;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.aliyun.bean.BindCodeData;
import com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack;
import com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: CallSettingRepo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010=\u001a\u00020>H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010?J\u0011\u0010@\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010?J\u0019\u0010A\u001a\u00020\u00102\u0006\u0010B\u001a\u00020CH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010DJ1\u0010E\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,2\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\b2\u0006\u0010H\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010IJ)\u0010J\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010,2\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u001b\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010N\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010OJ\u0011\u0010P\u001a\u00020QH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010?J#\u0010R\u001a\u0004\u0018\u00010S2\u0006\u0010F\u001a\u00020\b2\u0006\u0010T\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010KJ\u0011\u0010U\u001a\u00020VH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010?J\u0006\u0010W\u001a\u00020\bJ\u0006\u0010X\u001a\u00020\u0010J\u0006\u0010Y\u001a\u00020\u0010J\u0006\u0010Z\u001a\u00020[J\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020 0,J\f\u0010]\u001a\b\u0012\u0004\u0012\u00020>0^J\u0006\u0010_\u001a\u00020\u0010J\u0011\u0010`\u001a\u00020aH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010?J\u001c\u0010b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020d\u0012\u0004\u0012\u00020V0cj\u0002`e0^J\u0006\u0010F\u001a\u00020\bJ\u000e\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020iJ\u0006\u0010j\u001a\u00020\bJ\u000e\u0010k\u001a\u00020g2\u0006\u0010l\u001a\u00020\bJ\u0018\u0010m\u001a\u00020g2\u0006\u0010n\u001a\u00020\u00102\b\b\u0002\u0010o\u001a\u00020\u0010J\u000e\u0010p\u001a\u00020g2\u0006\u0010h\u001a\u00020iJ\u0006\u0010G\u001a\u00020VJF\u0010q\u001a\u00020\b2\u0006\u0010r\u001a\u00020\b2+\b\u0002\u0010s\u001a%\u0012\u0013\u0012\u00110V¢\u0006\f\bu\u0012\b\bv\u0012\u0004\b\b(s\u0012\u0004\u0012\u00020g\u0018\u00010tj\u0004\u0018\u0001`wH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010xJ\u000e\u0010y\u001a\u00020g2\u0006\u0010v\u001a\u00020\bJ\u0019\u0010z\u001a\u00020\u00102\u0006\u0010{\u001a\u00020|H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010}J\u0006\u0010~\u001a\u00020gR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\u0019R$\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\u0019R$\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R(\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\u000f\u001a\u0004\u0018\u00010 8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R(\u0010'\u001a\u0004\u0018\u00010&2\b\u0010\u000f\u001a\u0004\u0018\u00010&8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R4\u0010.\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010,8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b/\u00100\"\u0004\b1\u00102R$\u00103\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b4\u0010\u0013\"\u0004\b5\u0010\u0015R$\u00106\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b7\u0010\u0013\"\u0004\b8\u0010\u0015R\u0010\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020<X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u007f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/repo/CallSettingRepo;", "", "()V", "DefaultCallAutoReachedTime", "", "getDefaultCallAutoReachedTime", "()J", "TAG", "", "api", "Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$CloudApiService;", "getApi", "()Lcom/pudutech/disinfect/baselib/network/NetWorkApiManager$CloudApiService;", "api$delegate", "Lkotlin/Lazy;", ES6Iterator.VALUE_PROPERTY, "", "callAutoReachedSwitch", "getCallAutoReachedSwitch", "()Z", "setCallAutoReachedSwitch", "(Z)V", "callAutoReachedTime", "getCallAutoReachedTime", "setCallAutoReachedTime", "(J)V", "callFreezeTime", "getCallFreezeTime", "setCallFreezeTime", "callReachedSwitch", "getCallReachedSwitch", "setCallReachedSwitch", "Lcom/pudutech/bumblebee/robot_ui/bean/ReturnPointBean;", "callReturnPoint", "getCallReturnPoint", "()Lcom/pudutech/bumblebee/robot_ui/bean/ReturnPointBean;", "setCallReturnPoint", "(Lcom/pudutech/bumblebee/robot_ui/bean/ReturnPointBean;)V", "Lcom/pudutech/disinfect/baselib/network/response/Gateway;", "crtGateway", "getCrtGateway", "()Lcom/pudutech/disinfect/baselib/network/response/Gateway;", "setCrtGateway", "(Lcom/pudutech/disinfect/baselib/network/response/Gateway;)V", "", "Lcom/pudutech/disinfect/baselib/network/response/KeyBtnWithDestination;", "crtKeyBtnList", "getCrtKeyBtnList", "()Ljava/util/List;", "setCrtKeyBtnList", "(Ljava/util/List;)V", "cruiseCanCallSwitch", "getCruiseCanCallSwitch", "setCruiseCanCallSwitch", "greeterPointCanCallSwitch", "getGreeterPointCanCallSwitch", "setGreeterPointCanCallSwitch", "reportArriveJob", "Lkotlinx/coroutines/Job;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "checkLoraConnectState", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clusterID", MqttServiceConstants.CONNECT_ACTION, "config", "Lcom/pudutech/bumblebee/robot_ui/manager/GatewayConfig;", "(Lcom/pudutech/bumblebee/robot_ui/manager/GatewayConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetch1KeyGatewayKeyBtnList", "mac", "shopID", "gatewayPid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetch1KeyGatewayList", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetch4GCode", "Lcom/pudutech/disinfect/baselib/network/response/G4CodeData;", "shopId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchLinkCode", "Lcom/pudutech/robot/opensdk/aliyun/bean/BindCodeData;", "fetchLoRaNewestVersion", "Lcom/pudutech/lib_update/module/model/Version;", "language", "fetchLoRaRssi", "", "getHost", "getLinkSwitch", "getLocalCallSwitch", "getLocalConnectState", "Lcom/pudutech/robot/opensdk/RemoteConnectState;", "getReturnPointList", "loraConnectState", "Lkotlinx/coroutines/flow/Flow;", "loraExit", "loraInfo", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaInfo;", "loraUpdateState", "Lkotlin/Pair;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateState;", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaUpdateStateWithProgress;", "removeOpenConnectStateListener", "", "listener", "Lcom/pudutech/robot/opensdk/interf/IRemoteConnectStateListener;", "selectMapName", "setHost", "host", "setLinkSwitch", "b", "local", "setOpenConnectStateListener", "startDownload", "url", "progress", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lcom/pudutech/pd_network/storage/ProgressCB;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startReportArrive", "startUpdate", "file", "Ljava/io/File;", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopReportArrive", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CallSettingRepo {
    private static Job reportArriveJob;
    public static final CallSettingRepo INSTANCE = new CallSettingRepo();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());

    /* renamed from: api$delegate, reason: from kotlin metadata */
    private static final Lazy api = LazyKt.lazy(new Function0<NetWorkApiManager.CloudApiService>() { // from class: com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo$api$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final NetWorkApiManager.CloudApiService invoke() {
            return NetWorkApiManager.INSTANCE.getCloudApi();
        }
    });
    private static final long DefaultCallAutoReachedTime = 120000;

    private final NetWorkApiManager.CloudApiService getApi() {
        return (NetWorkApiManager.CloudApiService) api.getValue();
    }

    private CallSettingRepo() {
    }

    public static final /* synthetic */ String access$getTAG$p(CallSettingRepo callSettingRepo) {
        return TAG;
    }

    public final void setOpenConnectStateListener(IRemoteConnectStateListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        RobotOpenSdk.INSTANCE.addConnectStateListener(listener);
    }

    public final void removeOpenConnectStateListener(IRemoteConnectStateListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        RobotOpenSdk.INSTANCE.removeConnectStateListener(listener);
    }

    public final RemoteConnectState getLocalConnectState() {
        return RobotOpenSdk.INSTANCE.getConnectState();
    }

    public final void setHost(String host) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        RobotOpenManager.INSTANCE.setHost(host);
    }

    public final String getHost() {
        return RobotOpenManager.INSTANCE.getHost();
    }

    public final boolean getLocalCallSwitch() {
        return RobotOpenManager.INSTANCE.getSwitch() && RobotOpenManager.INSTANCE.isLocal();
    }

    public final boolean getLinkSwitch() {
        return RobotOpenManager.INSTANCE.getSwitch();
    }

    public static /* synthetic */ void setLinkSwitch$default(CallSettingRepo callSettingRepo, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        callSettingRepo.setLinkSwitch(z, z2);
    }

    public final void setLinkSwitch(boolean b, boolean local) {
        Pdlog.m3273d(TAG, "setCallSwitch : b = " + b + "; ");
        RobotOpenManager.INSTANCE.setSwitch(b, local);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object fetch4GCode(String str, Continuation<? super G4CodeData> continuation) {
        CallSettingRepo$fetch4GCode$1 callSettingRepo$fetch4GCode$1;
        int i;
        if (continuation instanceof CallSettingRepo$fetch4GCode$1) {
            callSettingRepo$fetch4GCode$1 = (CallSettingRepo$fetch4GCode$1) continuation;
            if ((callSettingRepo$fetch4GCode$1.label & Integer.MIN_VALUE) != 0) {
                callSettingRepo$fetch4GCode$1.label -= Integer.MIN_VALUE;
                Object obj = callSettingRepo$fetch4GCode$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = callSettingRepo$fetch4GCode$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetWorkApiManager.CloudApiService api2 = getApi();
                    Map<String, String> mapOf = MapsKt.mapOf(TuplesKt.m3968to("shop_id", str));
                    callSettingRepo$fetch4GCode$1.L$0 = this;
                    callSettingRepo$fetch4GCode$1.L$1 = str;
                    callSettingRepo$fetch4GCode$1.label = 1;
                    obj = api2.fetch4GCode(mapOf, callSettingRepo$fetch4GCode$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return ((BaseResponse) obj).getData();
            }
        }
        callSettingRepo$fetch4GCode$1 = new CallSettingRepo$fetch4GCode$1(this, continuation);
        Object obj2 = callSettingRepo$fetch4GCode$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = callSettingRepo$fetch4GCode$1.label;
        if (i != 0) {
        }
        return ((BaseResponse) obj2).getData();
    }

    public final int shopID() {
        return Constant.INSTANCE.getShopId();
    }

    public final String mac() {
        return NetDataUtils.INSTANCE.mac();
    }

    public final String selectMapName() {
        return RobotMapManager.INSTANCE.getDefaultPdmap();
    }

    public final void startReportArrive(String name) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(name, "name");
        stopReportArrive();
        launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new CallSettingRepo$startReportArrive$1(name, null), 3, null);
        reportArriveJob = launch$default;
    }

    public final void stopReportArrive() {
        Job job = reportArriveJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    public final void setCallReachedSwitch(boolean z) {
        BeeperUtils.INSTANCE.setCallReachedoConfirmationSwitch(z);
    }

    public final boolean getCallReachedSwitch() {
        return BeeperUtils.INSTANCE.isCallReachedoConfirmationSwitch();
    }

    public final void setCallAutoReachedSwitch(boolean z) {
        BeeperUtils.INSTANCE.setAutoCompleteCallTimeSwitch(z);
    }

    public final boolean getCallAutoReachedSwitch() {
        return BeeperUtils.INSTANCE.isAutoCompleteCallTimeSwitch();
    }

    public final void setCallAutoReachedTime(long j) {
        BeeperUtils.INSTANCE.setGetAutoCompleteCallTime(j);
    }

    public final long getCallAutoReachedTime() {
        return BeeperUtils.INSTANCE.getGetAutoCompleteCallTime();
    }

    public final long getDefaultCallAutoReachedTime() {
        return DefaultCallAutoReachedTime;
    }

    public final void setGreeterPointCanCallSwitch(boolean z) {
        Constant.INSTANCE.setGreeterPointCanCallSwitch(z);
    }

    public final boolean getGreeterPointCanCallSwitch() {
        return Constant.INSTANCE.getGreeterPointCanCallSwitch();
    }

    public final void setCruiseCanCallSwitch(boolean z) {
        Constant.INSTANCE.setCruiseCanCallSwitch(z);
    }

    public final boolean getCruiseCanCallSwitch() {
        return Constant.INSTANCE.getCruiseCanCallSwitch();
    }

    public final void setCallFreezeTime(long j) {
        BusinessSetting.INSTANCE.setCallFreezeTime_ms(j);
    }

    public final long getCallFreezeTime() {
        return BusinessSetting.INSTANCE.getCallFreezeTime_ms();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x007b A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object fetch1KeyGatewayList(String str, String str2, Continuation<? super List<Gateway>> continuation) {
        CallSettingRepo$fetch1KeyGatewayList$1 callSettingRepo$fetch1KeyGatewayList$1;
        int i;
        GatewayContainer gatewayContainer;
        if (continuation instanceof CallSettingRepo$fetch1KeyGatewayList$1) {
            callSettingRepo$fetch1KeyGatewayList$1 = (CallSettingRepo$fetch1KeyGatewayList$1) continuation;
            if ((callSettingRepo$fetch1KeyGatewayList$1.label & Integer.MIN_VALUE) != 0) {
                callSettingRepo$fetch1KeyGatewayList$1.label -= Integer.MIN_VALUE;
                Object obj = callSettingRepo$fetch1KeyGatewayList$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = callSettingRepo$fetch1KeyGatewayList$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetWorkApiManager.CloudApiService api2 = getApi();
                    Map<String, String> mapOf = MapsKt.mapOf(TuplesKt.m3968to("shop_id", str2), TuplesKt.m3968to("mac", str));
                    callSettingRepo$fetch1KeyGatewayList$1.L$0 = this;
                    callSettingRepo$fetch1KeyGatewayList$1.L$1 = str;
                    callSettingRepo$fetch1KeyGatewayList$1.L$2 = str2;
                    callSettingRepo$fetch1KeyGatewayList$1.label = 1;
                    obj = api2.fetchGatewayList(mapOf, callSettingRepo$fetch1KeyGatewayList$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                gatewayContainer = (GatewayContainer) ((BaseResponse) obj).getData();
                if (gatewayContainer == null) {
                    return gatewayContainer.getGateway_list();
                }
                return null;
            }
        }
        callSettingRepo$fetch1KeyGatewayList$1 = new CallSettingRepo$fetch1KeyGatewayList$1(this, continuation);
        Object obj2 = callSettingRepo$fetch1KeyGatewayList$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = callSettingRepo$fetch1KeyGatewayList$1.label;
        if (i != 0) {
        }
        gatewayContainer = (GatewayContainer) ((BaseResponse) obj2).getData();
        if (gatewayContainer == null) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x008a A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object fetch1KeyGatewayKeyBtnList(String str, String str2, String str3, Continuation<? super List<KeyBtnWithDestination>> continuation) {
        CallSettingRepo$fetch1KeyGatewayKeyBtnList$1 callSettingRepo$fetch1KeyGatewayKeyBtnList$1;
        int i;
        KeyBtnWithDestinationContainer keyBtnWithDestinationContainer;
        if (continuation instanceof CallSettingRepo$fetch1KeyGatewayKeyBtnList$1) {
            callSettingRepo$fetch1KeyGatewayKeyBtnList$1 = (CallSettingRepo$fetch1KeyGatewayKeyBtnList$1) continuation;
            if ((callSettingRepo$fetch1KeyGatewayKeyBtnList$1.label & Integer.MIN_VALUE) != 0) {
                callSettingRepo$fetch1KeyGatewayKeyBtnList$1.label -= Integer.MIN_VALUE;
                Object obj = callSettingRepo$fetch1KeyGatewayKeyBtnList$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = callSettingRepo$fetch1KeyGatewayKeyBtnList$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetWorkApiManager.CloudApiService api2 = getApi();
                    Map<String, String> mapOf = MapsKt.mapOf(TuplesKt.m3968to("shop_id", str2), TuplesKt.m3968to("mac", str), TuplesKt.m3968to("gateway_pid", str3));
                    callSettingRepo$fetch1KeyGatewayKeyBtnList$1.L$0 = this;
                    callSettingRepo$fetch1KeyGatewayKeyBtnList$1.L$1 = str;
                    callSettingRepo$fetch1KeyGatewayKeyBtnList$1.L$2 = str2;
                    callSettingRepo$fetch1KeyGatewayKeyBtnList$1.L$3 = str3;
                    callSettingRepo$fetch1KeyGatewayKeyBtnList$1.label = 1;
                    obj = api2.fetchGatewayKyBtnList(mapOf, callSettingRepo$fetch1KeyGatewayKeyBtnList$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                keyBtnWithDestinationContainer = (KeyBtnWithDestinationContainer) ((BaseResponse) obj).getData();
                if (keyBtnWithDestinationContainer == null) {
                    return keyBtnWithDestinationContainer.getCall_list();
                }
                return null;
            }
        }
        callSettingRepo$fetch1KeyGatewayKeyBtnList$1 = new CallSettingRepo$fetch1KeyGatewayKeyBtnList$1(this, continuation);
        Object obj2 = callSettingRepo$fetch1KeyGatewayKeyBtnList$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = callSettingRepo$fetch1KeyGatewayKeyBtnList$1.label;
        if (i != 0) {
        }
        keyBtnWithDestinationContainer = (KeyBtnWithDestinationContainer) ((BaseResponse) obj2).getData();
        if (keyBtnWithDestinationContainer == null) {
        }
    }

    public final Object loraInfo(Continuation<? super LoRaInfo> continuation) {
        return LoRaManager.INSTANCE.loraInfo(continuation);
    }

    public final Flow<LoRaConnectState> loraConnectState() {
        return LoRaManager.INSTANCE.getLoraConnectStateFL();
    }

    public final Flow<Pair<LoRaUpdateState, Integer>> loraUpdateState() {
        return LoRaManager.INSTANCE.getLoRaUpdateStateWithProgressFL();
    }

    public final Object checkLoraConnectState(Continuation<? super LoRaConnectState> continuation) {
        return LoRaManager.INSTANCE.checkConnectState(continuation);
    }

    public final Object connect(GatewayConfig gatewayConfig, Continuation<? super Boolean> continuation) {
        return LoRaManager.INSTANCE.connect(gatewayConfig, continuation);
    }

    public final boolean loraExit() {
        return LoRaManager.INSTANCE.loraExit();
    }

    public final Object fetchLoRaRssi(Continuation<? super Integer> continuation) {
        return LoRaManager.INSTANCE.fetchRssi(continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object startDownload$default(CallSettingRepo callSettingRepo, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        return callSettingRepo.startDownload(str, function1, continuation);
    }

    public final Object startDownload(String str, Function1<? super Integer, Unit> function1, Continuation<? super String> continuation) {
        return ExtKt.download(PdNetworkManager.f10310INSTANCE, str, function1, continuation);
    }

    public final Object startUpdate(File file, Continuation<? super Boolean> continuation) {
        return LoRaManager.INSTANCE.startUpdate(file, continuation);
    }

    public final Gateway getCrtGateway() {
        return LoRaManager.INSTANCE.getCrtGateway();
    }

    public final void setCrtGateway(Gateway gateway) {
        LoRaManager.INSTANCE.setCrtGateway(gateway);
    }

    public final List<KeyBtnWithDestination> getCrtKeyBtnList() {
        return LoRaManager.INSTANCE.getCrtKeyBtnList();
    }

    public final void setCrtKeyBtnList(List<KeyBtnWithDestination> list) {
        LoRaManager.INSTANCE.setCrtKeyBtnList(list);
    }

    public final ReturnPointBean getCallReturnPoint() {
        try {
            if (Constans.INSTANCE.getCallReturnDestination().length() == 0) {
                return null;
            }
            return (ReturnPointBean) GsonSingleton.INSTANCE.getINSTANCE().getMGson().fromJson(Constans.INSTANCE.getCallReturnDestination(), new TypeToken<ReturnPointBean>() { // from class: com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo$callReturnPoint$1
            }.getType());
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "get callReturnPoint error: " + e);
            return null;
        }
    }

    public final void setCallReturnPoint(ReturnPointBean returnPointBean) {
        try {
            if (returnPointBean == null) {
                Constans.INSTANCE.setCallReturnDestination("");
            } else {
                Constans constans = Constans.INSTANCE;
                String json = GsonSingleton.INSTANCE.getINSTANCE().getMGson().toJson(returnPointBean);
                Intrinsics.checkExpressionValueIsNotNull(json, "GsonSingleton.INSTANCE.getGson().toJson(value)");
                constans.setCallReturnDestination(json);
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "set callReturnPoint error: " + e);
            Constans.INSTANCE.setCallReturnDestination("");
        }
    }

    public final List<ReturnPointBean> getReturnPointList() {
        String defaultMapName = RobotMapManager.INSTANCE.getDefaultMapName();
        String currentMapDiningOutLetChosen = RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen();
        String currentMapUsherChosen = RobotMapManager.INSTANCE.getCurrentMapUsherChosen();
        ArrayList arrayList = new ArrayList();
        ReturnPointBean callReturnPoint = getCallReturnPoint();
        arrayList.add(new ReturnPointBean(defaultMapName != null ? defaultMapName : "", CommonExtKt.getSting(C4188R.string.call_stay), callReturnPoint == null));
        String str = currentMapDiningOutLetChosen;
        if (!(str == null || str.length() == 0)) {
            arrayList.add(new ReturnPointBean(defaultMapName != null ? defaultMapName : "", currentMapDiningOutLetChosen, callReturnPoint != null && Intrinsics.areEqual(callReturnPoint.getMapName(), defaultMapName) && Intrinsics.areEqual(callReturnPoint.getPointName(), currentMapDiningOutLetChosen)));
        }
        String str2 = currentMapUsherChosen;
        if (!(str2 == null || str2.length() == 0)) {
            arrayList.add(new ReturnPointBean(defaultMapName != null ? defaultMapName : "", currentMapUsherChosen, callReturnPoint != null && Intrinsics.areEqual(callReturnPoint.getMapName(), defaultMapName) && Intrinsics.areEqual(callReturnPoint.getPointName(), currentMapUsherChosen)));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((ReturnPointBean) obj).getSelect()) {
                arrayList2.add(obj);
            }
        }
        if (arrayList2.isEmpty()) {
            ((ReturnPointBean) arrayList.get(0)).setSelect(true);
        }
        Pdlog.m3273d(TAG, "getReturnPointList: " + arrayList);
        return arrayList;
    }

    public final Object fetchLinkCode(Continuation<? super BindCodeData> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        Pdlog.m3273d(access$getTAG$p(INSTANCE), "fetchLinkCode");
        RobotOpenManager.INSTANCE.startBindBeeper(new IGenBindCodeCallBack() { // from class: com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo$fetchLinkCode$2$1
            @Override // com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack
            public void onFailed(Exception e) {
                Intrinsics.checkParameterIsNotNull(e, "e");
                String access$getTAG$p = CallSettingRepo.access$getTAG$p(CallSettingRepo.INSTANCE);
                StringBuilder sb = new StringBuilder();
                sb.append("fetchLinkCode.onFailed ");
                Exception exc = e;
                sb.append(Log.getStackTraceString(exc));
                Pdlog.m3273d(access$getTAG$p, sb.toString());
                CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m4510constructorimpl(ResultKt.createFailure(exc)));
            }

            @Override // com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack
            public void onSuccess(BindCodeData code) {
                Intrinsics.checkParameterIsNotNull(code, "code");
                Pdlog.m3273d(CallSettingRepo.access$getTAG$p(CallSettingRepo.INSTANCE), "fetchLinkCode.onSuccess " + code);
                CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m4510constructorimpl(code));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object clusterID(Continuation<? super String> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        PdNetworkManagerKt.setOnGatewayAction(PdNetworkManager.f10310INSTANCE, new Function1<ServiceGatewayConfig, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo$clusterID$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ServiceGatewayConfig serviceGatewayConfig) {
                invoke2(serviceGatewayConfig);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ServiceGatewayConfig it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                String cluster_id = it.getCluster_id();
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m4510constructorimpl(cluster_id));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object fetchLoRaNewestVersion(String str, String str2, Continuation<? super Version> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        UpdateManager.INSTANCE.checkVersion(new CheckUpdateRequestParams(null, null, str, "pd_bella_lora_firm", null, null, null, str2, 115, null), new Function1<VerionResult, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo$fetchLoRaNewestVersion$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VerionResult verionResult) {
                invoke2(verionResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VerionResult it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                NetWorkLog.INSTANCE.mo3280i(CallSettingRepo.access$getTAG$p(CallSettingRepo.INSTANCE), "UpdateManager.checkVersion " + it);
                if (it.getAvailable() && it.getVersion() != null) {
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    Version version = it.getVersion();
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m4510constructorimpl(version));
                    return;
                }
                CancellableContinuation cancellableContinuation2 = CancellableContinuation.this;
                Result.Companion companion2 = Result.INSTANCE;
                cancellableContinuation2.resumeWith(Result.m4510constructorimpl(null));
            }
        }, new Function1<Throwable, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo$fetchLoRaNewestVersion$2$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                NetWorkLog.INSTANCE.mo3279e(CallSettingRepo.access$getTAG$p(CallSettingRepo.INSTANCE), "fetchLoRaNewestVersion > " + Log.getStackTraceString(it));
                CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m4510constructorimpl(null));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
