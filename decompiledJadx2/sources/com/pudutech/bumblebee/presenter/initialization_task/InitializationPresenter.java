package com.pudutech.bumblebee.presenter.initialization_task;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.led_task.LEDAnimationParser;
import com.pudutech.bumblebee.business.robotsdk.RobotPeripherals;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.InitStepListener;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemConfig;
import com.pudutech.bumblebee.presenter.initialization_task.InitializationContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.net.IOTHubServerManager;
import com.pudutech.bumblebee.presenter.net.IotServerApiManager;
import com.pudutech.bumblebee.presenter.report_cloud.task.PrivateCloudReportRun;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportCharging;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportError;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportRun;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportSelfCheck;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportUpgrade;
import com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager;
import com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDScreenMode;
import com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.resources.resource.ResUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: InitializationPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0005\u000f\u0012\u0015\u0018\u001b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u000bH\u0016J\u0010\u0010!\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u000bH\u0016J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010#\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u000bH\u0016J\u0010\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\rH\u0002J\b\u0010&\u001a\u00020\u001eH\u0002J&\u0010'\u001a\u00020\u001e2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010\u0007H\u0016J$\u0010-\u001a\u00020\u001e2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020\u0007H\u0002J$\u0010-\u001a\u00020\u001e2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010.\u001a\u00020/H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u0010\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u0010\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001c¨\u00060"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitializationContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitializationContract$PresenterInterface;", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/InitStepListener;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "context", "Landroid/content/Context;", "isPrivateCloud", "", "onPowerChangeListener", "com/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter$onPowerChangeListener$1", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter$onPowerChangeListener$1;", "onRobotMoveTargetChangeListener", "com/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter$onRobotMoveTargetChangeListener$1", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter$onRobotMoveTargetChangeListener$1;", "robotPoseListener", "com/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter$robotPoseListener$1", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter$robotPoseListener$1;", "robotSpeedListener", "com/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter$robotSpeedListener$1", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter$robotSpeedListener$1;", "switchMapResultListener", "com/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter$switchMapResultListener$1", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitializationPresenter$switchMapResultListener$1;", "actionCheckLocationInitDone", "", "actionInit", "appContext", "actionInitLanguageAndVoice", "actionInitNetCloud", "actionInitSdk", "enableCPU", "onOrOff", "initRemoteMaintenance", "onInitStep", "initStep", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "initState", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "string", "parseAndPostToUI", "json", "Lorg/json/JSONObject;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class InitializationPresenter extends BaseOneViewPresenter<InitializationContract.ViewInterface> implements InitializationContract.PresenterInterface, InitStepListener {
    private Context context;
    private volatile boolean isPrivateCloud;
    private final String TAG = "InitializationPresenter";
    private final InitializationPresenter$onPowerChangeListener$1 onPowerChangeListener = new BatteryInfoManager.OnPowerChangeListener() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$onPowerChangeListener$1
        @Override // com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager.OnPowerChangeListener
        public void onPowerChanger(int power) {
            RemoteMaintenanceManager.INSTANCE.getRemoteInterface().setPower(power);
        }
    };
    private final InitializationPresenter$robotSpeedListener$1 robotSpeedListener = new RobotMoveManager.RobotSpeedListener() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$robotSpeedListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotSpeedListener
        public void onMove(double left, double right) {
            RemoteMaintenanceManager.INSTANCE.getRemoteInterface().setSpeed(left);
        }
    };
    private final InitializationPresenter$robotPoseListener$1 robotPoseListener = new RobotMoveManager.RobotPoseListener() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$robotPoseListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.RobotPoseListener
        public void onPose(double x, double y, double yaw) {
            RemoteMaintenanceManager.INSTANCE.getRemoteInterface().setCoordinateOrientation(x, y, yaw);
        }
    };
    private final InitializationPresenter$switchMapResultListener$1 switchMapResultListener = new RobotMapManager.SwitchMapResultListener() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$switchMapResultListener$1
        @Override // com.pudutech.mirsdkwrap.lib.map.RobotMapManager.SwitchMapResultListener
        public void onResult(boolean b, String errorMsg) {
            RemoteMaintenanceManager.INSTANCE.getRemoteInterface().setMapPath(RobotMapManager.INSTANCE.getCurrentMapAtlasPath());
        }
    };
    private final InitializationPresenter$onRobotMoveTargetChangeListener$1 onRobotMoveTargetChangeListener = new RobotMoveManager.OnRobotMoveTargetChangeListener() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$onRobotMoveTargetChangeListener$1
        @Override // com.pudutech.mirsdkwrap.lib.move.RobotMoveManager.OnRobotMoveTargetChangeListener
        public void onMovingTarget(String i) {
            Intrinsics.checkParameterIsNotNull(i, "i");
            Pdlog.m3273d(InitializationPresenter.this.getTAG(), "onMovingTarget " + i);
            RemoteMaintenanceManager.INSTANCE.getRemoteInterface().setTask(i);
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.InitializationContract.PresenterInterface
    public void actionInitLanguageAndVoice(Context appContext) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        LanguageUtils languageUtils = new LanguageUtils(appContext);
        languageUtils.setLocale(appContext, languageUtils.getCurrent(), false);
        VoicePackageHelper.INSTANCE.init(appContext);
        VoicePlayer.INSTANCE.init(appContext);
        ResUtil.INSTANCE.init(appContext);
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.InitializationContract.PresenterInterface
    public void actionInitNetCloud(boolean isPrivateCloud) {
        this.isPrivateCloud = isPrivateCloud;
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.InitializationContract.PresenterInterface
    public void actionInit(Context appContext) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        enableCPU(true);
        this.context = appContext;
        Pdlog.m3275i(getTAG(), "actionInit " + WifiUtil.INSTANCE.getMac());
        BusinessSetting.INSTANCE.initConfig$module_bumblebee_presenter_robotRelease(appContext);
        ReportSelfCheck.INSTANCE.createNewTask();
        IOTHubServerManager.INSTANCE.init(appContext);
        IotServerApiManager.INSTANCE.init(appContext);
        AuthorizationPackHelp authorizationPackHelp = AuthorizationPackHelp.INSTANCE;
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        authorizationPackHelp.m3282x69b2ebe5(context);
        ReportUpgrade reportUpgrade = ReportUpgrade.INSTANCE;
        Context context2 = this.context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        reportUpgrade.reportAppUpgrade(context2);
        ReportUpgrade reportUpgrade2 = ReportUpgrade.INSTANCE;
        Context context3 = this.context;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        reportUpgrade2.reportSystemUpgrade(context3);
        InformationSystemConfig informationSystemConfig = InformationSystemConfig.INSTANCE;
        Context context4 = this.context;
        if (context4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        Context applicationContext = context4.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        informationSystemConfig.init(applicationContext);
        initRemoteMaintenance();
    }

    private final void initRemoteMaintenance() {
        if (Intrinsics.areEqual("robot", "mock")) {
            return;
        }
        RemoteMaintenanceManager.INSTANCE.start();
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.InitializationContract.PresenterInterface
    public void actionInitSdk(Context appContext) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        LEDAnimationParser.INSTANCE.loadFromFiles(appContext);
        RobotPeripherals.INSTANCE.setOnLEDInitDone(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$actionInitSdk$1
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
                InitializationPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$actionInitSdk$1.1
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
                        InitializationContract.ViewInterface theView;
                        theView = InitializationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showComponentInitDone(InitializationContract.Component.LED);
                        }
                    }
                });
            }
        });
        SDK.INSTANCE.getInitStepListeners().addListener(this);
        SDK.INSTANCE.connect(appContext);
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.InitStepListener
    public void onInitStep(InitStep initStep, StepState initState, String string) {
        boolean z = true;
        Pdlog.m3275i(getTAG(), "initStep=" + initStep + " initState=" + initState + ' ' + string + " when mView=" + getTheView());
        if (initStep == InitStep.CheckCAN && (initState == StepState.Success || initState == StepState.Fail)) {
            Pdlog.m3275i(getTAG(), "get hardwareVersion=" + SDK.INSTANCE.getHardwareVersion());
        }
        if (initState == StepState.Success) {
            if (initStep != null) {
                ReportSelfCheck.INSTANCE.addStep(initStep.name(), true, string);
            }
        } else if (initState == StepState.Fail && initStep != null) {
            ReportSelfCheck.INSTANCE.addStep(initStep.name(), false, string);
        }
        if (initStep == InitStep.Finish && (initState == StepState.Fail || initState == StepState.Success)) {
            ReportSelfCheck.INSTANCE.toCloud();
            ReportError.INSTANCE.createNewTask();
            ReportCharging.INSTANCE.createNewTask();
            if (this.isPrivateCloud) {
                PrivateCloudReportRun.INSTANCE.createNewTask();
            } else {
                ReportRun.INSTANCE.createNewTask();
            }
            RobotOpenManager robotOpenManager = RobotOpenManager.INSTANCE;
            Context context = this.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            robotOpenManager.init$module_bumblebee_presenter_robotRelease(context);
            RobotNewOpenManager robotNewOpenManager = RobotNewOpenManager.INSTANCE;
            Context context2 = this.context;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            robotNewOpenManager.init(context2);
        }
        if (initState == StepState.Fail) {
            Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.ERROR);
        } else {
            Peripherals.INSTANCE.getLedScreen().control(LEDScreenMode.INIT);
        }
        final InitStepViewModel initStepViewModel = new InitStepViewModel();
        initStepViewModel.setStep(initStep);
        initStepViewModel.setState(initState);
        if (initState == StepState.Fail) {
            String str = string;
            if (!(str == null || str.length() == 0)) {
                parseAndPostToUI(initStep, initState, string);
                return;
            }
        }
        if (initState == StepState.Fail) {
            String str2 = string;
            if (str2 != null && str2.length() != 0) {
                z = false;
            }
            if (z) {
                initStepViewModel.setDescription("Fail");
                runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$onInitStep$3
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
                        InitializationContract.ViewInterface theView;
                        theView = InitializationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showStep(initStepViewModel);
                        }
                    }
                });
                return;
            }
        }
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$onInitStep$4
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
                InitializationContract.ViewInterface theView;
                theView = InitializationPresenter.this.getTheView();
                if (theView != null) {
                    theView.showStep(initStepViewModel);
                }
            }
        });
    }

    private final void parseAndPostToUI(InitStep initStep, StepState initState, String string) {
        try {
            if (string.charAt(0) == '{') {
                parseAndPostToUI(initStep, initState, new JSONObject(string));
                return;
            }
            if (string.charAt(0) == '[') {
                JSONArray jSONArray = new JSONArray(string);
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject == null) {
                        Intrinsics.throwNpe();
                    }
                    parseAndPostToUI(initStep, initState, jSONObject);
                }
                return;
            }
            final InitStepViewModel initStepViewModel = new InitStepViewModel();
            initStepViewModel.setStep(initStep);
            initStepViewModel.setState(initState);
            initStepViewModel.setDescription(string);
            runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$parseAndPostToUI$1
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
                    InitializationContract.ViewInterface theView;
                    theView = InitializationPresenter.this.getTheView();
                    if (theView != null) {
                        theView.showStep(initStepViewModel);
                    }
                }
            });
            Pdlog.m3274e(getTAG(), "not json string=" + string);
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(getTAG(), "wrong description string=" + string + " exception=" + e);
        }
    }

    private final void parseAndPostToUI(InitStep initStep, StepState initState, JSONObject json) {
        final InitStepViewModel initStepViewModel = new InitStepViewModel();
        initStepViewModel.setStep(initStep);
        initStepViewModel.setState(initState);
        try {
            initStepViewModel.setDescription(json.getString("error"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$parseAndPostToUI$2
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
                InitializationPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$parseAndPostToUI$2.1
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
                        InitializationContract.ViewInterface theView;
                        theView = InitializationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showStep(initStepViewModel);
                        }
                    }
                });
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.InitializationContract.PresenterInterface
    public void actionCheckLocationInitDone() {
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.InitializationPresenter$actionCheckLocationInitDone$1
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
                InitializationContract.ViewInterface theView;
                theView = InitializationPresenter.this.getTheView();
                if (theView != null) {
                    theView.showLocationInitDone(SDK.INSTANCE.isLocalizationSuccess());
                }
            }
        });
    }

    private final void enableCPU(boolean onOrOff) {
        Pdlog.m3273d(getTAG(), "enableCPU onOrOff=" + onOrOff);
        try {
            Tools.execCommand("echo " + (onOrOff ? 1 : 0) + " > /sys/devices/system/cpu/cpu5/online", true);
            Tools.execCommand("echo " + (onOrOff ? 1 : 0) + " > /sys/devices/system/cpu/cpu4/online", true);
            Tools.execCommand("echo " + (onOrOff ? 1 : 0) + " > /sys/devices/system/cpu/cpu3/online", true);
        } catch (Exception e) {
            Pdlog.m3277w(getTAG(), "check your device support enableCPU or not. " + e);
        }
    }
}
