package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.bean.ReturnPointBean;
import com.pudutech.bumblebee.robot_ui.manager.LoRaConnectState;
import com.pudutech.bumblebee.robot_ui.manager.LoRaInfo;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.LoRaSignalDialog;
import com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo;
import com.pudutech.bumblebee.robot_ui.repo.Gateway2;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.network.response.Gateway;
import com.pudutech.disinfect.baselib.network.response.KeyBtnWithDestination;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.robot.opensdk.RemoteConnectState;
import com.pudutech.robot.opensdk.aliyun.bean.BindCodeData;
import com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: CallSettingVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u009f\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u001f*\u0001c\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010g\u001a\u00020\u0007H\u0002J\u0006\u0010h\u001a\u00020iJ\u0006\u0010j\u001a\u00020iJ\u0006\u0010k\u001a\u00020iJ\u0006\u0010l\u001a\u00020iJ\u0006\u0010m\u001a\u00020iJ\u0006\u0010n\u001a\u00020iJ\b\u0010o\u001a\u0004\u0018\u00010\u000fJ\b\u0010p\u001a\u00020iH\u0014J\u0010\u0010q\u001a\u00020i2\b\b\u0002\u0010r\u001a\u00020\u0007J\u000e\u0010s\u001a\u00020i2\u0006\u0010t\u001a\u00020\u0013J\u000e\u0010u\u001a\u00020i2\u0006\u0010v\u001a\u00020'J\u0010\u0010w\u001a\u00020i2\b\u0010x\u001a\u0004\u0018\u00010\u0004J\u0010\u0010y\u001a\u00020i2\b\u0010x\u001a\u0004\u0018\u00010\u0004J\u000e\u0010z\u001a\u00020i2\u0006\u0010{\u001a\u00020\u0004J\u0006\u0010|\u001a\u00020iJ\u000e\u0010}\u001a\u00020i2\u0006\u0010~\u001a\u00020$J\u0006\u0010\u007f\u001a\u00020iJ\u0010\u0010\u0080\u0001\u001a\u00020i2\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0082\u0001\u001a\u00020i2\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0083\u0001\u001a\u00020i2\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0084\u0001\u001a\u00020i2\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0085\u0001\u001a\u00020i2\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0086\u0001\u001a\u00020i2\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\t\u0010\u0087\u0001\u001a\u00020iH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\n0\n0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\n0\n0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000e0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00040\u00040\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0019\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u001a0\u001a0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001d\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u000e0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010%\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020'\u0018\u00010&0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010)\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020'0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010+\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u001f\u0010/\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\n0\n0,¢\u0006\b\n\u0000\u001a\u0004\b0\u0010.R\u001f\u00101\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\n0\n0,¢\u0006\b\n\u0000\u001a\u0004\b2\u0010.R\u001f\u00103\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070,¢\u0006\b\n\u0000\u001a\u0004\b4\u0010.R\u000e\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0,¢\u0006\b\n\u0000\u001a\u0004\b8\u0010.R\u0019\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130,¢\u0006\b\n\u0000\u001a\u0004\b:\u0010.R\u001f\u0010;\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000e0,¢\u0006\b\n\u0000\u001a\u0004\b<\u0010.R\u001f\u0010=\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070,¢\u0006\b\n\u0000\u001a\u0004\b>\u0010.R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110,¢\u0006\b\n\u0000\u001a\u0004\bC\u0010.R\u001f\u0010D\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070,¢\u0006\b\n\u0000\u001a\u0004\bE\u0010.R\u001f\u0010F\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00040\u00040,¢\u0006\b\n\u0000\u001a\u0004\bG\u0010.R\u001f\u0010H\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u001a0\u001a0,¢\u0006\b\n\u0000\u001a\u0004\bI\u0010.R\u001f\u0010J\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070,¢\u0006\b\n\u0000\u001a\u0004\bK\u0010.R\u0017\u0010L\u001a\b\u0012\u0004\u0012\u00020M0,¢\u0006\b\n\u0000\u001a\u0004\bN\u0010.R\u001f\u0010O\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070,¢\u0006\b\n\u0000\u001a\u0004\bP\u0010.R\u001f\u0010Q\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u000e0,¢\u0006\b\n\u0000\u001a\u0004\bR\u0010.R\u0019\u0010S\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0,¢\u0006\b\n\u0000\u001a\u0004\bT\u0010.R\u0017\u0010U\u001a\b\u0012\u0004\u0012\u00020\"0,¢\u0006\b\n\u0000\u001a\u0004\bV\u0010.R\u0019\u0010W\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0,¢\u0006\b\n\u0000\u001a\u0004\bX\u0010.R\u0010\u0010Y\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R%\u0010Z\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020'\u0018\u00010&0\u0006¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\\R\u0010\u0010]\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010^\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110,¢\u0006\b\n\u0000\u001a\u0004\b_\u0010.R\u001f\u0010`\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070,¢\u0006\b\n\u0000\u001a\u0004\ba\u0010.R\u0010\u0010b\u001a\u00020cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010dR\u0017\u0010e\u001a\b\u0012\u0004\u0012\u00020'0,¢\u0006\b\n\u0000\u001a\u0004\bf\u0010.¨\u0006\u0088\u0001"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "TAG", "", "_callAutoReachedSwitchLD", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "_callAutoReachedTimeLD", "", "_callFreezeTimeLD", "_callReachedSwitchLD", "_callReturnPlaceLD", "", "Lcom/pudutech/bumblebee/robot_ui/bean/ReturnPointBean;", "_crt4gCodeLD", "Lcom/pudutech/robot/opensdk/aliyun/bean/BindCodeData;", "_crtGatewayLD", "Lcom/pudutech/disinfect/baselib/network/response/Gateway;", "_crtKeyBtnListLD", "Lcom/pudutech/disinfect/baselib/network/response/KeyBtnWithDestination;", "_cruiseCanCallSwitchLD", "_greeterPointCanCallSwitchLD", "_localHostLD", "_localServerConnectStateLD", "Lcom/pudutech/robot/opensdk/RemoteConnectState;", "_localServerSwitchLD", "_loraExit", "_loraGatewayListLD", "Lcom/pudutech/bumblebee/robot_ui/repo/Gateway2;", "_loraInfo", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaInfo;", "_loraRssiLD", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/LoRaSignalDialog$SignType;", "_loraServerVersionLD", "Lcom/pudutech/lib_update/module/model/Version;", "_loraUpdateState", "Lkotlin/Pair;", "", "_openApiCodeLD", "_openApiSwitchLD", "_toastLD", "callAutoReachedSwitchLD", "Landroidx/lifecycle/LiveData;", "getCallAutoReachedSwitchLD", "()Landroidx/lifecycle/LiveData;", "callAutoReachedTimeLD", "getCallAutoReachedTimeLD", "callFreezeTimeLD", "getCallFreezeTimeLD", "callReachedSwitchLD", "getCallReachedSwitchLD", "callRepo", "Lcom/pudutech/bumblebee/robot_ui/repo/CallSettingRepo;", "callReturnPlaceLD", "getCallReturnPlaceLD", "crtGatewayLD", "getCrtGatewayLD", "crtKeyBtnListLD", "getCrtKeyBtnListLD", "cruiseCanCallSwitchLD", "getCruiseCanCallSwitchLD", "fetch4GCodeJob", "Lkotlinx/coroutines/Job;", "fetchOpenApiCodeJob", "g4CodeLD", "getG4CodeLD", "greeterPointCanCallSwitchLD", "getGreeterPointCanCallSwitchLD", "localHostLD", "getLocalHostLD", "localServerConnectStateLD", "getLocalServerConnectStateLD", "localServerSwitchLD", "getLocalServerSwitchLD", "loraConnectStateLD", "Lcom/pudutech/bumblebee/robot_ui/manager/LoRaConnectState;", "getLoraConnectStateLD", "loraExitLD", "getLoraExitLD", "loraGatewayListLD", "getLoraGatewayListLD", "loraInfoLD", "getLoraInfoLD", "loraRssiLD", "getLoraRssiLD", "loraServerVersionLD", "getLoraServerVersionLD", "loraUpdateJob", "loraUpdateStateLD", "getLoraUpdateStateLD", "()Landroidx/lifecycle/MutableLiveData;", "mFetchLoraRssiJob", "openApiCodeLD", "getOpenApiCodeLD", "openApiSwitch", "getOpenApiSwitch", "openConnectStateListener", "com/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM$openConnectStateListener$1", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM$openConnectStateListener$1;", "toastLD", "getToastLD", "checkGateway", "checkLoRaUpdate", "", "checkLoraConnectState", "fetch1KeyGateWay", "fetch4GCode", "fetchKeyBtnList", "fetchOpenApiCode", "getCallReturnPoint", "onCleared", "refreshKeyBtnList", "withUI", "select1keyGateway", "gateway", "selectCallReturnPlace", "returnPointPosition", "setCallAutoReachedTime", "time", "setCallFreezeTime", "setHost", "host", "startFetchLoraRssi", "startLoRaUpdate", "version", "stopFetchLoraRssi", "switchCallAutoReached", "open", "switchCallReached", "switchCruiseCanCall", "switchGreeterPointCanCall", "switchLinkCall", "switchLocalCall", "trueFetchLinkCode", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CallSettingVM extends BaseViewModel {
    private final MutableLiveData<Long> _callAutoReachedTimeLD;
    private final MutableLiveData<Long> _callFreezeTimeLD;
    private final LiveData<Long> callAutoReachedTimeLD;
    private final LiveData<Long> callFreezeTimeLD;
    private Job fetch4GCodeJob;
    private Job fetchOpenApiCodeJob;
    private Job loraUpdateJob;
    private Job mFetchLoraRssiJob;
    private final String TAG = "CallSettingVM";
    private final CallSettingRepo callRepo = CallSettingRepo.INSTANCE;
    private final MutableLiveData<Boolean> _openApiSwitchLD = new MutableLiveData<>(Boolean.valueOf(this.callRepo.getLinkSwitch()));
    private final MutableLiveData<Boolean> _localServerSwitchLD = new MutableLiveData<>(Boolean.valueOf(this.callRepo.getLocalCallSwitch()));
    private final MutableLiveData<RemoteConnectState> _localServerConnectStateLD = new MutableLiveData<>(this.callRepo.getLocalConnectState());
    private final MutableLiveData<String> _localHostLD = new MutableLiveData<>(this.callRepo.getHost());
    private final MutableLiveData<BindCodeData> _openApiCodeLD = new MutableLiveData<>();
    private final MutableLiveData<BindCodeData> _crt4gCodeLD = new MutableLiveData<>();
    private final MutableLiveData<Integer> _toastLD = new MutableLiveData<>(0);
    private final MutableLiveData<Boolean> _callReachedSwitchLD = new MutableLiveData<>(Boolean.valueOf(this.callRepo.getCallReachedSwitch()));
    private final MutableLiveData<Boolean> _callAutoReachedSwitchLD = new MutableLiveData<>(Boolean.valueOf(this.callRepo.getCallAutoReachedSwitch()));
    private final MutableLiveData<Boolean> _greeterPointCanCallSwitchLD = new MutableLiveData<>(Boolean.valueOf(this.callRepo.getGreeterPointCanCallSwitch()));
    private final MutableLiveData<Boolean> _cruiseCanCallSwitchLD = new MutableLiveData<>(Boolean.valueOf(this.callRepo.getCruiseCanCallSwitch()));
    private final MutableLiveData<LoRaInfo> _loraInfo = new MutableLiveData<>(null);
    private final MutableLiveData<Boolean> _loraExit = new MutableLiveData<>(Boolean.valueOf(this.callRepo.loraExit()));
    private final MutableLiveData<List<Gateway2>> _loraGatewayListLD = new MutableLiveData<>(null);
    private final MutableLiveData<Gateway> _crtGatewayLD = new MutableLiveData<>(this.callRepo.getCrtGateway());
    private final MutableLiveData<List<KeyBtnWithDestination>> _crtKeyBtnListLD = new MutableLiveData<>(null);
    private final MutableLiveData<Version> _loraServerVersionLD = new MutableLiveData<>(null);
    private final MutableLiveData<List<ReturnPointBean>> _callReturnPlaceLD = new MutableLiveData<>();
    private final MutableLiveData<LoRaSignalDialog.SignType> _loraRssiLD = new MutableLiveData<>();
    private final MutableLiveData<Pair<Integer, Integer>> _loraUpdateState = new MutableLiveData<>(null);
    private final LiveData<Integer> toastLD = VMExtKt.toLiveData(this._toastLD);
    private final LiveData<Boolean> openApiSwitch = VMExtKt.toLiveData(this._openApiSwitchLD);
    private final LiveData<Boolean> localServerSwitchLD = VMExtKt.toLiveData(this._localServerSwitchLD);
    private final LiveData<String> localHostLD = VMExtKt.toLiveData(this._localHostLD);
    private final LiveData<RemoteConnectState> localServerConnectStateLD = VMExtKt.toLiveData(this._localServerConnectStateLD);
    private final LiveData<BindCodeData> openApiCodeLD = VMExtKt.toLiveData(this._openApiCodeLD);
    private final LiveData<BindCodeData> g4CodeLD = VMExtKt.toLiveData(this._crt4gCodeLD);
    private final LiveData<Boolean> callReachedSwitchLD = VMExtKt.toLiveData(this._callReachedSwitchLD);
    private final LiveData<Boolean> callAutoReachedSwitchLD = VMExtKt.toLiveData(this._callAutoReachedSwitchLD);
    private final LiveData<Boolean> greeterPointCanCallSwitchLD = VMExtKt.toLiveData(this._greeterPointCanCallSwitchLD);
    private final LiveData<Boolean> cruiseCanCallSwitchLD = VMExtKt.toLiveData(this._cruiseCanCallSwitchLD);
    private final LiveData<LoRaConnectState> loraConnectStateLD = FlowLiveDataConversions.asLiveData$default(this.callRepo.loraConnectState(), (CoroutineContext) null, 0, 3, (Object) null);
    private final MutableLiveData<Pair<Integer, Integer>> loraUpdateStateLD = this._loraUpdateState;
    private final LiveData<LoRaInfo> loraInfoLD = VMExtKt.toLiveData(this._loraInfo);
    private final LiveData<Boolean> loraExitLD = VMExtKt.toLiveData(this._loraExit);
    private final LiveData<List<Gateway2>> loraGatewayListLD = VMExtKt.toLiveData(this._loraGatewayListLD);
    private final LiveData<Gateway> crtGatewayLD = VMExtKt.toLiveData(this._crtGatewayLD);
    private final LiveData<List<KeyBtnWithDestination>> crtKeyBtnListLD = VMExtKt.toLiveData(this._crtKeyBtnListLD);
    private final LiveData<Version> loraServerVersionLD = VMExtKt.toLiveData(this._loraServerVersionLD);
    private final LiveData<List<ReturnPointBean>> callReturnPlaceLD = VMExtKt.toLiveData(this._callReturnPlaceLD);
    private final LiveData<LoRaSignalDialog.SignType> loraRssiLD = VMExtKt.toLiveData(this._loraRssiLD);
    private final CallSettingVM$openConnectStateListener$1 openConnectStateListener = new IRemoteConnectStateListener() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$openConnectStateListener$1
        @Override // com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener
        public void onConnectState(RemoteConnectState state) {
            MutableLiveData mutableLiveData;
            Intrinsics.checkParameterIsNotNull(state, "state");
            mutableLiveData = CallSettingVM.this._localServerConnectStateLD;
            mutableLiveData.postValue(state);
        }
    };

    /* JADX WARN: Type inference failed for: r0v69, types: [com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$openConnectStateListener$1] */
    public CallSettingVM() {
        long j = 1000;
        this._callAutoReachedTimeLD = new MutableLiveData<>(Long.valueOf(this.callRepo.getCallAutoReachedTime() / j));
        this._callFreezeTimeLD = new MutableLiveData<>(Long.valueOf(this.callRepo.getCallFreezeTime() / j));
        this.callAutoReachedTimeLD = VMExtKt.toLiveData(this._callAutoReachedTimeLD);
        this.callFreezeTimeLD = VMExtKt.toLiveData(this._callFreezeTimeLD);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C44101(null), 3, null);
        this.callRepo.setOpenConnectStateListener(this.openConnectStateListener);
    }

    public final LiveData<Integer> getToastLD() {
        return this.toastLD;
    }

    public final LiveData<Boolean> getOpenApiSwitch() {
        return this.openApiSwitch;
    }

    public final LiveData<Boolean> getLocalServerSwitchLD() {
        return this.localServerSwitchLD;
    }

    public final LiveData<String> getLocalHostLD() {
        return this.localHostLD;
    }

    public final LiveData<RemoteConnectState> getLocalServerConnectStateLD() {
        return this.localServerConnectStateLD;
    }

    public final LiveData<BindCodeData> getOpenApiCodeLD() {
        return this.openApiCodeLD;
    }

    public final LiveData<BindCodeData> getG4CodeLD() {
        return this.g4CodeLD;
    }

    public final LiveData<Boolean> getCallReachedSwitchLD() {
        return this.callReachedSwitchLD;
    }

    public final LiveData<Boolean> getCallAutoReachedSwitchLD() {
        return this.callAutoReachedSwitchLD;
    }

    public final LiveData<Long> getCallAutoReachedTimeLD() {
        return this.callAutoReachedTimeLD;
    }

    public final LiveData<Boolean> getGreeterPointCanCallSwitchLD() {
        return this.greeterPointCanCallSwitchLD;
    }

    public final LiveData<Boolean> getCruiseCanCallSwitchLD() {
        return this.cruiseCanCallSwitchLD;
    }

    public final LiveData<Long> getCallFreezeTimeLD() {
        return this.callFreezeTimeLD;
    }

    public final LiveData<LoRaConnectState> getLoraConnectStateLD() {
        return this.loraConnectStateLD;
    }

    public final MutableLiveData<Pair<Integer, Integer>> getLoraUpdateStateLD() {
        return this.loraUpdateStateLD;
    }

    public final LiveData<LoRaInfo> getLoraInfoLD() {
        return this.loraInfoLD;
    }

    public final LiveData<Boolean> getLoraExitLD() {
        return this.loraExitLD;
    }

    public final LiveData<List<Gateway2>> getLoraGatewayListLD() {
        return this.loraGatewayListLD;
    }

    public final LiveData<Gateway> getCrtGatewayLD() {
        return this.crtGatewayLD;
    }

    public final LiveData<List<KeyBtnWithDestination>> getCrtKeyBtnListLD() {
        return this.crtKeyBtnListLD;
    }

    public final LiveData<Version> getLoraServerVersionLD() {
        return this.loraServerVersionLD;
    }

    public final LiveData<List<ReturnPointBean>> getCallReturnPlaceLD() {
        return this.callReturnPlaceLD;
    }

    public final LiveData<LoRaSignalDialog.SignType> getLoraRssiLD() {
        return this.loraRssiLD;
    }

    /* compiled from: CallSettingVM.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$1", m3970f = "CallSettingVM.kt", m3971i = {0}, m3972l = {101}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$1 */
    /* loaded from: classes4.dex */
    static final class C44101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4974p$;

        C44101(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C44101 c44101 = new C44101(completion);
            c44101.f4974p$ = (CoroutineScope) obj;
            return c44101;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C44101) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableLiveData mutableLiveData;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f4974p$;
                MutableLiveData mutableLiveData2 = CallSettingVM.this._loraInfo;
                CallSettingRepo callSettingRepo = CallSettingVM.this.callRepo;
                this.L$0 = coroutineScope;
                this.L$1 = mutableLiveData2;
                this.label = 1;
                obj = callSettingRepo.loraInfo(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableLiveData = mutableLiveData2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableLiveData = (MutableLiveData) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            mutableLiveData.setValue(obj);
            CallSettingVM.this._callReturnPlaceLD.setValue(CallSettingVM.this.callRepo.getReturnPointList());
            return Unit.INSTANCE;
        }
    }

    public final void switchLinkCall(boolean open) {
        Pdlog.m3275i(this.TAG, "switchLinkCall > " + open);
        this._openApiSwitchLD.setValue(Boolean.valueOf(open));
        this.callRepo.setLinkSwitch(open, false);
        this._openApiCodeLD.setValue(null);
    }

    public final void switchLocalCall(boolean open) {
        this._openApiSwitchLD.setValue(Boolean.valueOf(open));
        this.callRepo.setLinkSwitch(open, true);
        this._localServerSwitchLD.setValue(Boolean.valueOf(this.callRepo.getLocalCallSwitch()));
    }

    public final void setHost(String host) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        this.callRepo.setHost(host);
    }

    public final void fetch4GCode() {
        Job launch$default;
        Pdlog.m3275i(this.TAG, "fetch4GCode > ");
        Job job = this.fetch4GCodeJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CallSettingVM$fetch4GCode$1(this, null), 3, null);
        this.fetch4GCodeJob = launch$default;
    }

    public final void fetchOpenApiCode() {
        Pdlog.m3275i(this.TAG, "fetchOpenApiCode > ");
        if (!Intrinsics.areEqual((Object) this._openApiSwitchLD.getValue(), (Object) true)) {
            this._toastLD.setValue(Integer.valueOf(C4188R.string.pdStr7_151));
            this._openApiCodeLD.setValue(null);
        } else {
            trueFetchLinkCode();
        }
    }

    public final void switchCallReached(boolean open) {
        Pdlog.m3275i(this.TAG, "switchCallReached > " + open);
        this._callReachedSwitchLD.setValue(Boolean.valueOf(open));
        this.callRepo.setCallReachedSwitch(open);
        if (open) {
            return;
        }
        this._callAutoReachedSwitchLD.setValue(false);
        this._callAutoReachedTimeLD.setValue(Long.valueOf(this.callRepo.getDefaultCallAutoReachedTime() / 1000));
    }

    public final void switchGreeterPointCanCall(boolean open) {
        Pdlog.m3275i(this.TAG, "switchGreeterPointCanCall > " + open);
        this._greeterPointCanCallSwitchLD.setValue(Boolean.valueOf(open));
        this.callRepo.setGreeterPointCanCallSwitch(open);
    }

    public final void switchCruiseCanCall(boolean open) {
        Pdlog.m3275i(this.TAG, "switchCruiseCanCall > " + open);
        this._cruiseCanCallSwitchLD.setValue(Boolean.valueOf(open));
        this.callRepo.setCruiseCanCallSwitch(open);
    }

    public final void switchCallAutoReached(boolean open) {
        Pdlog.m3275i(this.TAG, "switchCallAutoReached > " + open);
        this._callAutoReachedSwitchLD.setValue(Boolean.valueOf(open));
        this.callRepo.setCallAutoReachedSwitch(open);
    }

    public final void setCallAutoReachedTime(String time) {
        long j;
        boolean z = false;
        Pdlog.m3275i(this.TAG, "setCallAutoReachedTime > " + time);
        String str = time;
        long j2 = 1;
        if (str == null || str.length() == 0) {
            j2 = this.callRepo.getDefaultCallAutoReachedTime() / 1000;
        } else {
            try {
                j = Long.parseLong(time);
            } catch (Exception unused) {
                j = 0;
            }
            if (j > 600) {
                j2 = 600;
            } else if (j >= 1) {
                j2 = j;
            }
            z = true;
        }
        if (time != null ? z : true) {
            this._callAutoReachedTimeLD.setValue(Long.valueOf(j2));
        }
        this.callRepo.setCallAutoReachedTime(j2 * 1000);
    }

    public final void setCallFreezeTime(String time) {
        long j;
        boolean z = false;
        Pdlog.m3275i(this.TAG, "setCallFreezeTime > " + time);
        String str = time;
        long j2 = 1;
        if (str == null || str.length() == 0) {
            j2 = 120;
        } else {
            try {
                j = Long.parseLong(time);
            } catch (Exception unused) {
                j = 0;
            }
            if (j > 180) {
                j2 = 180;
            } else if (j >= 1) {
                j2 = j;
            }
            z = true;
        }
        if (time != null ? z : true) {
            this._callFreezeTimeLD.setValue(Long.valueOf(j2));
        }
        this.callRepo.setCallFreezeTime(j2 * 1000);
    }

    private final void trueFetchLinkCode() {
        Job launch$default;
        Pdlog.m3275i(this.TAG, "trueFetchLinkCode ");
        Job job = this.fetchOpenApiCodeJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CallSettingVM$trueFetchLinkCode$1(this, null), 3, null);
        this.fetchOpenApiCodeJob = launch$default;
    }

    public final void checkLoraConnectState() {
        Pdlog.m3275i(this.TAG, "checkLoraConnectState ");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CallSettingVM$checkLoraConnectState$1(this, null), 3, null);
    }

    public final void fetch1KeyGateWay() {
        Pdlog.m3275i(this.TAG, "fetch1KeyGateWay ");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CallSettingVM$fetch1KeyGateWay$1(this, null), 3, null);
    }

    public final void select1keyGateway(Gateway gateway) {
        Intrinsics.checkParameterIsNotNull(gateway, "gateway");
        Pdlog.m3275i(this.TAG, "select1keyGateway > " + gateway);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CallSettingVM$select1keyGateway$1(this, gateway, null), 3, null);
    }

    public final void checkLoRaUpdate() {
        Pdlog.m3275i(this.TAG, "checkLoRaUpdate ");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CallSettingVM$checkLoRaUpdate$1(this, null), 3, null);
    }

    public final void selectCallReturnPlace(int returnPointPosition) {
        Pdlog.m3275i(this.TAG, "selectCallReturnPlace > " + returnPointPosition);
        List<ReturnPointBean> value = this._callReturnPlaceLD.getValue();
        if (value != null) {
            int i = 0;
            for (Object obj : value) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                ReturnPointBean returnPointBean = (ReturnPointBean) obj;
                returnPointBean.setSelect(returnPointPosition == i);
                if (returnPointPosition == 0) {
                    this.callRepo.setCallReturnPoint((ReturnPointBean) null);
                } else if (returnPointPosition == i) {
                    this.callRepo.setCallReturnPoint(returnPointBean);
                }
                i = i2;
            }
        }
        this._callReturnPlaceLD.setValue(value);
    }

    public final ReturnPointBean getCallReturnPoint() {
        return this.callRepo.getCallReturnPoint();
    }

    public final void startLoRaUpdate(Version version) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(version, "version");
        Pdlog.m3275i(this.TAG, "startLoRaUpdate > " + version);
        Job job = this.loraUpdateJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CallSettingVM$startLoRaUpdate$1(this, version, null), 3, null);
        this.loraUpdateJob = launch$default;
    }

    public final void fetchKeyBtnList() {
        Pdlog.m3275i(this.TAG, "fetchKeyBtnList > ");
        if (checkGateway()) {
            MutableLiveData<List<KeyBtnWithDestination>> mutableLiveData = this._crtKeyBtnListLD;
            List<KeyBtnWithDestination> crtKeyBtnList = this.callRepo.getCrtKeyBtnList();
            if (crtKeyBtnList == null) {
                crtKeyBtnList = CollectionsKt.emptyList();
            }
            mutableLiveData.setValue(crtKeyBtnList);
        }
    }

    public static /* synthetic */ void refreshKeyBtnList$default(CallSettingVM callSettingVM, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        callSettingVM.refreshKeyBtnList(z);
    }

    public final void refreshKeyBtnList(boolean withUI) {
        Pdlog.m3275i(this.TAG, "refreshKeyBtnList > " + withUI);
        if (checkGateway()) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CallSettingVM$refreshKeyBtnList$1(this, withUI, null), 3, null);
        }
    }

    public final void startFetchLoraRssi() {
        Job launch$default;
        stopFetchLoraRssi();
        Pdlog.m3275i(this.TAG, "startFetchLoraRssi");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CallSettingVM$startFetchLoraRssi$1(this, null), 3, null);
        this.mFetchLoraRssiJob = launch$default;
    }

    public final void stopFetchLoraRssi() {
        Pdlog.m3275i(this.TAG, "stopFetchLoraRssi");
        Job job = this.mFetchLoraRssiJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    private final boolean checkGateway() {
        Pdlog.m3275i(this.TAG, "checkGateway > " + this._crtGatewayLD.getValue() + ' ' + this._crtGatewayLD.getValue());
        if (this._crtGatewayLD.getValue() != null) {
            Gateway value = this._crtGatewayLD.getValue();
            String pid = value != null ? value.getPid() : null;
            if (!(pid == null || pid.length() == 0)) {
                return true;
            }
        }
        this._toastLD.setValue(Integer.valueOf(C4188R.string.call_key_btn_info));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        this.callRepo.removeOpenConnectStateListener(this.openConnectStateListener);
    }
}
