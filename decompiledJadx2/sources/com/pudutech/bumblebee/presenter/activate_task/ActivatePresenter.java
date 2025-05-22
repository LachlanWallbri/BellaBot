package com.pudutech.bumblebee.presenter.activate_task;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.activate_task.ActivateContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.RobotActiveStatusReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.RobotActiveStatusResp;
import com.pudutech.disinfect.baselib.state.AppException;
import com.pudutech.pd_network.IGateway;
import com.pudutech.pd_network.OnGatewayAction;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.ServiceGatewayConfig;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: ActivatePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001+B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0006H\u0016J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0015H\u0002J\u001c\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#H\u0002J \u0010$\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010 \u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010&\u001a\u00020\u0011H\u0014J\b\u0010'\u001a\u00020\u0011H\u0014J\u0010\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020*H\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D¢\u0006\u0002\n\u0000¨\u0006,"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/activate_task/ActivatePresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivateContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivateContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "mScop", "Lkotlinx/coroutines/CoroutineScope;", "mScopJob", "Lkotlinx/coroutines/Job;", "testActiveTimeout", "testMaxKeepActiveTime", "", "checkActiveStatus", "", "context", "Landroid/content/Context;", "isClickActivate", "", "getActiveTimeout", "", "overTime", "getPreActiveType", "isActiveLocal", "localActive", "code", "notifyLocalStatus", "isNetError", "notifyUi", "status", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivateContract$RobotActiveStatus;", "info", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivateContract$ActiveRobotInfo;", "notifyUiStatus", "Lcom/pudutech/disinfect/baselib/network/response/RobotActiveStatusResp;", "onViewAttach", "onViewRemoved", "setPreActiveType", "type", "Lcom/pudutech/bumblebee/presenter/activate_task/ActivatePresenter$ActiveType;", "ActiveType", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ActivatePresenter extends BaseOneViewPresenter<ActivateContract.ViewInterface> implements ActivateContract.PresenterInterface {
    private CoroutineScope mScop;
    private volatile Job mScopJob;
    private final String TAG = "ActivatePresenter";
    private final String testActiveTimeout = "test_active_timeout";
    private final int testMaxKeepActiveTime = 36000;

    /* compiled from: ActivatePresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/activate_task/ActivatePresenter$ActiveType;", "", "(Ljava/lang/String;I)V", "OFFICIAL_TYPE", "TEST_TYPE", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum ActiveType {
        OFFICIAL_TYPE,
        TEST_TYPE
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        this.mScop = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        Job job = this.mScopJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        CoroutineScope coroutineScope = this.mScop;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.mScopJob = (Job) null;
        this.mScop = (CoroutineScope) null;
    }

    @Override // com.pudutech.bumblebee.presenter.activate_task.ActivateContract.PresenterInterface
    public void checkActiveStatus(final Context context, final boolean isClickActivate) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(getTAG(), "checkActiveStatus isClickActivate = " + isClickActivate);
        if (this.mScopJob != null) {
            Job job = this.mScopJob;
            if (job == null) {
                Intrinsics.throwNpe();
            }
            if (!job.isCompleted()) {
                Pdlog.m3273d(getTAG(), "checkActiveStatus , is requesting ");
                return;
            }
        }
        final CoroutineScope coroutineScope = this.mScop;
        if (coroutineScope != null) {
            Pdlog.m3273d(getTAG(), "checkActiveStatus 获取数据");
            IGateway.DefaultImpls.refreshGateway$default(PdNetworkManager.f10310INSTANCE, null, 1, null);
            PdNetworkManager.f10310INSTANCE.addOnGatewayAction(new OnGatewayAction() { // from class: com.pudutech.bumblebee.presenter.activate_task.ActivatePresenter$checkActiveStatus$$inlined$let$lambda$1
                @Override // com.pudutech.pd_network.OnGatewayAction
                public void onSuccess(ServiceGatewayConfig bean) {
                    Intrinsics.checkParameterIsNotNull(bean, "bean");
                    Pdlog.m3273d(this.getTAG(), "checkActiveStatus.addOnGatewayAction = " + bean);
                    PdNetworkManager.f10310INSTANCE.removeOnGatewayAction(this);
                    this.mScopJob = BaseViewModelExtKt.requestNetworkData(CoroutineScope.this, new C40301(null), new Function1<RobotActiveStatusResp, Unit>() { // from class: com.pudutech.bumblebee.presenter.activate_task.ActivatePresenter$checkActiveStatus$$inlined$let$lambda$1.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(RobotActiveStatusResp robotActiveStatusResp) {
                            invoke2(robotActiveStatusResp);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(RobotActiveStatusResp it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Pdlog.m3273d(this.getTAG(), "checkActiveStatus success = " + it);
                            this.notifyUiStatus(context, it, isClickActivate);
                        }
                    }, new Function1<AppException, Unit>() { // from class: com.pudutech.bumblebee.presenter.activate_task.ActivatePresenter$checkActiveStatus$$inlined$let$lambda$1.3
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                            invoke2(appException);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(AppException it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Pdlog.m3274e(this.getTAG(), "checkActiveStatus from server : " + it + '}');
                            this.notifyLocalStatus(context, true);
                        }
                    }, "checkActiveStatus");
                }

                /* compiled from: ActivatePresenter.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/disinfect/baselib/network/response/ApiResponse;", "Lcom/pudutech/disinfect/baselib/network/response/RobotActiveStatusResp;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/presenter/activate_task/ActivatePresenter$checkActiveStatus$1$1$onSuccess$1"}, m3962k = 3, m3963mv = {1, 1, 16})
                /* renamed from: com.pudutech.bumblebee.presenter.activate_task.ActivatePresenter$checkActiveStatus$$inlined$let$lambda$1$1 */
                /* loaded from: classes4.dex */
                static final class C40301 extends SuspendLambda implements Function1<Continuation<? super ApiResponse<RobotActiveStatusResp>>, Object> {
                    Object L$0;
                    Object L$1;
                    int label;

                    C40301(Continuation continuation) {
                        super(1, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        return new C40301(completion);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Continuation<? super ApiResponse<RobotActiveStatusResp>> continuation) {
                        return ((C40301) create(continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        RobotActiveStatusReq robotActiveStatusReq;
                        RobotActiveStatusReq robotActiveStatusReq2;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            robotActiveStatusReq = new RobotActiveStatusReq();
                            PdNetworkManager pdNetworkManager = PdNetworkManager.f10310INSTANCE;
                            this.L$0 = robotActiveStatusReq;
                            this.L$1 = robotActiveStatusReq;
                            this.label = 1;
                            obj = pdNetworkManager.hardID(this);
                            if (obj == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            robotActiveStatusReq2 = robotActiveStatusReq;
                        } else {
                            if (i != 1) {
                                if (i == 2) {
                                    ResultKt.throwOnFailure(obj);
                                    return (ApiResponse) obj;
                                }
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            robotActiveStatusReq = (RobotActiveStatusReq) this.L$1;
                            robotActiveStatusReq2 = (RobotActiveStatusReq) this.L$0;
                            ResultKt.throwOnFailure(obj);
                        }
                        robotActiveStatusReq.setHard_id((String) obj);
                        NetWorkApiManager.CloudApiService cloudApi = NetWorkApiManager.INSTANCE.getCloudApi();
                        this.L$0 = robotActiveStatusReq2;
                        this.label = 2;
                        obj = cloudApi.getRobotActiveStatus(robotActiveStatusReq2, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return (ApiResponse) obj;
                    }
                }

                @Override // com.pudutech.pd_network.OnGatewayAction
                public void onException(Throwable e) {
                    Intrinsics.checkParameterIsNotNull(e, "e");
                    Pdlog.m3273d(this.getTAG(), "checkActiveStatus.error = " + e);
                    PdNetworkManager.f10310INSTANCE.removeOnGatewayAction(this);
                    this.notifyLocalStatus(context, true);
                }
            });
        }
    }

    @Override // com.pudutech.bumblebee.presenter.activate_task.ActivateContract.PresenterInterface
    public void checkActiveStatus(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        checkActiveStatus(context, false);
    }

    @Override // com.pudutech.bumblebee.presenter.activate_task.ActivateContract.PresenterInterface
    public void localActive(String code) {
        Intrinsics.checkParameterIsNotNull(code, "code");
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.activate_task.ActivatePresenter$localActive$1
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
                ActivateContract.ViewInterface theView;
                theView = ActivatePresenter.this.getTheView();
                if (theView != null) {
                    theView.showLocalActiveResult(false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyUiStatus(Context context, RobotActiveStatusResp status, boolean isClickActivate) {
        Pdlog.m3273d(getTAG(), "notifyUiStatus status = " + status);
        if (Intrinsics.areEqual(status.getRobot_type(), "active")) {
            if (isClickActivate) {
                IGateway.DefaultImpls.refreshGateway$default(PdNetworkManager.f10310INSTANCE, null, 1, null);
                Pdlog.m3273d(getTAG(), "checkActiveStatus refreshGateway");
            }
            Constant.INSTANCE.setRobotActive(true);
            setPreActiveType(ActiveType.OFFICIAL_TYPE);
            notifyUi$default(this, ActivateContract.RobotActiveStatus.ACTIVE, null, 2, null);
            Pdlog.m3273d(getTAG(), "notifyUiStatus active = " + status);
            return;
        }
        if (Intrinsics.areEqual(status.getRobot_type(), "testing")) {
            SDK.INSTANCE.closeMirsdkCheck();
            setPreActiveType(ActiveType.TEST_TYPE);
            Constant.INSTANCE.setRobotActive(status.getOver_time() > 0);
            SpUtils.set(context, this.testActiveTimeout, getActiveTimeout(status.getOver_time()));
            if (status.getOver_time() <= 0) {
                notifyUi(ActivateContract.RobotActiveStatus.INACTIVE, new ActivateContract.ActiveRobotInfo(ActivateContract.InactiveType.TESTING_TIMEOUT));
            } else {
                notifyUi$default(this, ActivateContract.RobotActiveStatus.ACTIVE, null, 2, null);
            }
            Pdlog.m3273d(getTAG(), "notifyUiStatus testing = " + status);
            return;
        }
        Constant.INSTANCE.setRobotActive(false);
        notifyUi(ActivateContract.RobotActiveStatus.INACTIVE, new ActivateContract.ActiveRobotInfo(ActivateContract.InactiveType.SERVER_STATUS));
    }

    static /* synthetic */ void notifyUi$default(ActivatePresenter activatePresenter, ActivateContract.RobotActiveStatus robotActiveStatus, ActivateContract.ActiveRobotInfo activeRobotInfo, int i, Object obj) {
        if ((i & 2) != 0) {
            activeRobotInfo = (ActivateContract.ActiveRobotInfo) null;
        }
        activatePresenter.notifyUi(robotActiveStatus, activeRobotInfo);
    }

    private final void notifyUi(final ActivateContract.RobotActiveStatus status, final ActivateContract.ActiveRobotInfo info) {
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.activate_task.ActivatePresenter$notifyUi$1
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
                ActivateContract.ViewInterface theView;
                ActivateContract.ViewInterface theView2;
                theView = ActivatePresenter.this.getTheView();
                if (theView != null) {
                    theView.showActiveStatus(status, info);
                }
                theView2 = ActivatePresenter.this.getTheView();
                if (theView2 == null) {
                    Pdlog.m3273d(ActivatePresenter.this.getTAG(), "notifyUi  theView is null");
                } else {
                    Pdlog.m3273d(ActivatePresenter.this.getTAG(), "notifyUi  is success");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyLocalStatus(Context context, boolean isNetError) {
        if (isActiveLocal(context)) {
            Pdlog.m3273d(getTAG(), "notifyLocalStatus : isActiveLocal");
            notifyUi$default(this, ActivateContract.RobotActiveStatus.ACTIVE, null, 2, null);
        } else if (!WifiUtil.INSTANCE.isNetworkAvailable(context) || isNetError) {
            Pdlog.m3273d(getTAG(), "notifyLocalStatus : isNetworkAvailable is false");
            notifyUi(ActivateContract.RobotActiveStatus.INACTIVE, new ActivateContract.ActiveRobotInfo(ActivateContract.InactiveType.WIFI_NOT_CONNECT));
        } else {
            Pdlog.m3273d(getTAG(), "notifyLocalStatus : isNetworkAvailable is true");
            notifyUi(ActivateContract.RobotActiveStatus.INACTIVE, new ActivateContract.ActiveRobotInfo(ActivateContract.InactiveType.REQUEST_ERROR));
        }
    }

    private final boolean isActiveLocal(Context context) {
        if (!Constant.INSTANCE.getRobotActive()) {
            return false;
        }
        int preActiveType = getPreActiveType(context);
        if (preActiveType == ActiveType.OFFICIAL_TYPE.ordinal()) {
            return true;
        }
        if (preActiveType == ActiveType.TEST_TYPE.ordinal()) {
            r1 = System.currentTimeMillis() <= SpUtils.get(context, this.testActiveTimeout, 0L);
            if (r1) {
                SDK.INSTANCE.closeMirsdkCheck();
            }
        }
        return r1;
    }

    private final int getPreActiveType(Context context) {
        return Constant.INSTANCE.getPreActiveType();
    }

    private final void setPreActiveType(ActiveType type) {
        Constant.INSTANCE.setPreActiveType(type.ordinal());
    }

    private final long getActiveTimeout(long overTime) {
        if (overTime <= 0) {
            return 0L;
        }
        if (overTime >= this.testMaxKeepActiveTime) {
            return (this.testMaxKeepActiveTime * 1000) + System.currentTimeMillis();
        }
        return System.currentTimeMillis() + (overTime * 1000);
    }
}
