package com.pudutech.bumblebee.robot_ui.p054ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.cruise_task.CruiseMapContract;
import com.pudutech.bumblebee.presenter.cruise_task.CruiseMapPresenter;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.advertise.AdSceneConfig;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestHelper;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.SettingActivity;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.CruiseRouteEditDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.CruiseSelectAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.CruiseSelectItem;
import com.pudutech.bumblebee.robot_ui.p054ui.custom_call.CustomCallActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.HomeSettingDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CustomCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.LeaseHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.LockMachineHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MapView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MyStatusBarLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.RobotSpeedUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.bumblebee.robot_ui.viewmodel.CruiseSelectVm;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdkwrap.lib.map.MapCruiseLine;
import com.pudutech.mirsdkwrap.lib.map.MapLine;
import com.pudutech.mirsdkwrap.lib.map.StayPoint;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;
import com.pudutech.resources.voice.VoiceItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: CruiseSelectActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0002\"=\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010B\u001a\u00020;H\u0002J\b\u0010C\u001a\u00020,H\u0002J\b\u0010D\u001a\u00020;H\u0002J\u0010\u0010E\u001a\u00020;2\u0006\u0010E\u001a\u00020,H\u0016J\b\u0010F\u001a\u00020;H\u0002J\b\u0010G\u001a\u00020,H\u0016J\u0010\u0010H\u001a\u00020;2\u0006\u0010I\u001a\u00020JH\u0016J\u0012\u0010K\u001a\u00020;2\b\u0010L\u001a\u0004\u0018\u00010MH\u0014J\b\u0010N\u001a\u00020;H\u0014J\b\u0010O\u001a\u00020;H\u0014J\b\u0010P\u001a\u00020;H\u0014J\b\u0010Q\u001a\u00020;H\u0014J\b\u0010R\u001a\u00020;H\u0014J\u0010\u0010S\u001a\u00020;2\u0006\u0010T\u001a\u00020,H\u0016J\b\u0010U\u001a\u00020;H\u0002J \u0010V\u001a\u00020;2\u0016\u0010W\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013H\u0002J\u0010\u0010X\u001a\u00020;2\u0006\u0010Y\u001a\u00020,H\u0002J \u0010Z\u001a\u00020;2\u0016\u0010[\u001a\u0012\u0012\u0004\u0012\u00020\\0\u0011j\b\u0012\u0004\u0012\u00020\\`\u0013H\u0016J@\u0010]\u001a\u00020;2\u0006\u0010^\u001a\u00020\u001e2\u0006\u0010_\u001a\u00020\u001e2\u0006\u0010`\u001a\u00020\u001e2\u0006\u0010a\u001a\u00020\u001e2\u0016\u0010b\u001a\u0012\u0012\u0004\u0012\u00020c0\u0011j\b\u0012\u0004\u0012\u00020c`\u0013H\u0016J\u0010\u0010d\u001a\u00020;2\u0006\u0010b\u001a\u00020eH\u0016J \u0010f\u001a\u00020;2\u0006\u0010g\u001a\u00020\u00072\u0006\u0010h\u001a\u00020\u00072\u0006\u0010i\u001a\u00020\u001eH\u0002J\u0010\u0010j\u001a\u00020;2\u0006\u0010k\u001a\u00020lH\u0016J\b\u0010m\u001a\u00020;H\u0016J\u0010\u0010n\u001a\u00020;2\u0006\u0010o\u001a\u00020\u001eH\u0016J\u0010\u0010p\u001a\u00020;2\u0006\u0010b\u001a\u00020qH\u0016J\b\u0010r\u001a\u00020;H\u0002J\b\u0010s\u001a\u00020;H\u0002J\b\u0010t\u001a\u00020;H\u0002J\b\u0010u\u001a\u00020;H\u0002J0\u0010v\u001a\u00020;2\u0006\u0010w\u001a\u00020\u001e2\u0006\u0010b\u001a\u00020\\2\u0016\u0010x\u001a\u0012\u0012\u0004\u0012\u00020y0\u0011j\b\u0012\u0004\u0012\u00020y`\u0013H\u0016J\b\u0010z\u001a\u00020;H\u0002J\b\u0010{\u001a\u00020;H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\"\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011j\n\u0012\u0004\u0012\u00020\u0012\u0018\u0001`\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u000f\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0004\n\u0002\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010&\u001a\u00020'8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\u000f\u001a\u0004\b(\u0010)R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u00101\u001a\u0002028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u0010\u000f\u001a\u0004\b3\u00104R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00108\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020;09X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0004\n\u0002\u0010>R\u001e\u0010?\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0011j\b\u0012\u0004\u0012\u00020\u0007`\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006|"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/CruiseSelectActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseMapContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewInterface;", "()V", "KEY_LAST_SELECT_WAY", "", "TAG", "kotlin.jvm.PlatformType", "_cruiseSelectVm", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/CruiseSelectVm;", "get_cruiseSelectVm", "()Lcom/pudutech/bumblebee/robot_ui/viewmodel/CruiseSelectVm;", "_cruiseSelectVm$delegate", "Lkotlin/Lazy;", "allMapData", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/CruiseSelectItem;", "Lkotlin/collections/ArrayList;", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "cruiseMapPresenter", "Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseMapPresenter;", "getCruiseMapPresenter", "()Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseMapPresenter;", "cruiseMapPresenter$delegate", "cruiseSelectAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/CruiseSelectAdapter;", "currentSelectIndex", "", "faceAnimationView", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView;", "functionButton", "com/pudutech/bumblebee/robot_ui/ui/CruiseSelectActivity$functionButton$1", "Lcom/pudutech/bumblebee/robot_ui/ui/CruiseSelectActivity$functionButton$1;", "homeSettingDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/HomeSettingDialog;", "idleMovePresenter", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "getIdleMovePresenter", "()Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "idleMovePresenter$delegate", "isFirstStart", "", "isRelease", "isShowLowPowerDialog", "leaseHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/LeaseHelper;", "mAgentTestHelper", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestHelper;", "getMAgentTestHelper", "()Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestHelper;", "mAgentTestHelper$delegate", "mLockMachineHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/LockMachineHelper;", "onCustomCallListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "", "onHomeSettingDialogClickListener", "com/pudutech/bumblebee/robot_ui/ui/CruiseSelectActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/CruiseSelectActivity$onHomeSettingDialogClickListener$1;", "stops", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "bindPresenter", "getIsSteady", "goToCruise", "hasStops", "initView", "isBusyState", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "release", "setNewData", "list", "setSteadySwitchLayout", "switch", "showAll", "mapModels", "Lcom/pudutech/mirsdkwrap/lib/map/MapCruiseLine;", "showBackGround", "maxX", "minX", "maxY", "minY", "model", "Lcom/pudutech/mirsdkwrap/lib/map/MapLine;", "showChargerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showCruiseRouteDialog", "name", "curId", RequestParameters.POSITION, "showIdleEvent", "event", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewEvent;", "showLowerNotify", "showPowerChange", "i", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "showSettingDialog", "showSleepAnimation", "showStandbyAnimation", "showSteadyBtnIfNeed", "showTheSelected", "selectedIndex", "stopDots", "Lcom/pudutech/mirsdkwrap/lib/map/StayPoint;", "stopStandby", "unBindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CruiseSelectActivity extends MyBaseActivity implements BatteryContract2.ViewInterface, CruiseMapContract.ViewInterface, IdleMoveContract.ViewInterface {
    private HashMap _$_findViewCache;
    private ArrayList<CruiseSelectItem> allMapData;
    private CruiseSelectAdapter cruiseSelectAdapter;
    private FaceVideoView faceAnimationView;
    private HomeSettingDialog homeSettingDialog;
    private boolean isRelease;
    private boolean isShowLowPowerDialog;
    private LockMachineHelper mLockMachineHelper;
    private final String TAG = getClass().getSimpleName();
    private final String KEY_LAST_SELECT_WAY = "KEY_LAST_SELECT_WAY";
    private boolean isFirstStart = true;

    /* renamed from: cruiseMapPresenter$delegate, reason: from kotlin metadata */
    private final Lazy cruiseMapPresenter = LazyKt.lazy(new Function0<CruiseMapPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$cruiseMapPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CruiseMapPresenter invoke() {
            CruiseMapPresenter cruiseMapPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(CruiseMapPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(CruiseMapPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                cruiseMapPresenter = new CruiseMapPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(CruiseMapPresenter.class).toString(), cruiseMapPresenter);
            } else {
                if (!(basePresenterInterface instanceof CruiseMapPresenter)) {
                    basePresenterInterface = null;
                }
                cruiseMapPresenter = (CruiseMapPresenter) basePresenterInterface;
            }
            if (cruiseMapPresenter != null) {
                return cruiseMapPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.cruise_task.CruiseMapPresenter");
        }
    });

    /* renamed from: idleMovePresenter$delegate, reason: from kotlin metadata */
    private final Lazy idleMovePresenter = LazyKt.lazy(new Function0<IdleMovePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$idleMovePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IdleMovePresenter invoke() {
            IdleMovePresenter idleMovePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(IdleMovePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(IdleMovePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                idleMovePresenter = new IdleMovePresenter();
            } else {
                presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(IdleMovePresenter.class).toString());
                if (!(basePresenterInterface instanceof IdleMovePresenter)) {
                    basePresenterInterface = null;
                }
                idleMovePresenter = (IdleMovePresenter) basePresenterInterface;
            }
            if (idleMovePresenter != null) {
                return idleMovePresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter");
        }
    });
    private final LeaseHelper leaseHelper = new LeaseHelper();
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();
    private int currentSelectIndex = -1;
    private ArrayList<String> stops = new ArrayList<>();

    /* renamed from: mAgentTestHelper$delegate, reason: from kotlin metadata */
    private final Lazy mAgentTestHelper = LazyKt.lazy(new Function0<AgentTestHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$mAgentTestHelper$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AgentTestHelper invoke() {
            return new AgentTestHelper();
        }
    });

    /* renamed from: _cruiseSelectVm$delegate, reason: from kotlin metadata */
    private final Lazy _cruiseSelectVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(CruiseSelectVm.class), new Function0<ViewModelStore>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$$special$$inlined$viewModels$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$$special$$inlined$viewModels$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
            Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    });
    private final CruiseSelectActivity$onHomeSettingDialogClickListener$1 onHomeSettingDialogClickListener = new CruiseSelectActivity$onHomeSettingDialogClickListener$1(this);
    private final CruiseSelectActivity$functionButton$1 functionButton = new CruiseSelectActivity$functionButton$1(this);
    private Function1<? super CustomCallTargetBean, Unit> onCustomCallListener = new Function1<CustomCallTargetBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$onCustomCallListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CustomCallTargetBean customCallTargetBean) {
            invoke2(customCallTargetBean);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(CustomCallTargetBean it) {
            String str;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = CruiseSelectActivity.this.TAG;
            Pdlog.m3273d(str, "onCustomCallListener task: " + it);
            CruiseSelectActivity.this.jumpAndFinish(CustomCallActivity.Companion.createIntent(CruiseSelectActivity.this, it));
        }
    };
    private final TouchSensorEventHelper touchSensorEventHelper = new TouchSensorEventHelper();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[BatteryContract2.ViewEvent.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[BatteryContract2.ViewEvent.POWER_LOW_2_5.ordinal()] = 1;
            $EnumSwitchMapping$0[BatteryContract2.ViewEvent.POWER_EMPTY.ordinal()] = 2;
            $EnumSwitchMapping$0[BatteryContract2.ViewEvent.POWER_LOW_5_10.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[IdleMoveContract.ViewEvent.values().length];
            $EnumSwitchMapping$1[IdleMoveContract.ViewEvent.READY.ordinal()] = 1;
            $EnumSwitchMapping$1[IdleMoveContract.ViewEvent.STAND_BY.ordinal()] = 2;
            $EnumSwitchMapping$1[IdleMoveContract.ViewEvent.SLEEP.ordinal()] = 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CruiseMapPresenter getCruiseMapPresenter() {
        return (CruiseMapPresenter) this.cruiseMapPresenter.getValue();
    }

    private final IdleMovePresenter getIdleMovePresenter() {
        return (IdleMovePresenter) this.idleMovePresenter.getValue();
    }

    private final AgentTestHelper getMAgentTestHelper() {
        return (AgentTestHelper) this.mAgentTestHelper.getValue();
    }

    private final CruiseSelectVm get_cruiseSelectVm() {
        return (CruiseSelectVm) this._cruiseSelectVm.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public boolean isBusyState() {
        return false;
    }

    public CruiseSelectActivity() {
    }

    public static final /* synthetic */ CruiseSelectAdapter access$getCruiseSelectAdapter$p(CruiseSelectActivity cruiseSelectActivity) {
        CruiseSelectAdapter cruiseSelectAdapter = cruiseSelectActivity.cruiseSelectAdapter;
        if (cruiseSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseSelectAdapter");
        }
        return cruiseSelectAdapter;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        setContentView(C4188R.layout.activity_cruise_select);
        initView();
        bindPresenter();
        PeripheralsSceneUtil.INSTANCE.setPlayMode(PeripheralsSceneUtil.Mode.Cruise);
        PeripheralsSceneUtil.idleState$default(PeripheralsSceneUtil.INSTANCE, false, 1, null);
        this.mLockMachineHelper = new LockMachineHelper(this, this);
        LockMachineHelper lockMachineHelper = this.mLockMachineHelper;
        if (lockMachineHelper != null) {
            getLifecycle().addObserver(lockMachineHelper);
        }
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
        getMAgentTestHelper().bindLifecycle(this);
    }

    private final void showSteadyBtnIfNeed() {
        if (Constans.INSTANCE.getSettingSteadyModeSwitch()) {
            LinearLayout steady_mode_switch_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.steady_mode_switch_layout);
            Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch_layout, "steady_mode_switch_layout");
            steady_mode_switch_layout.setVisibility(0);
            int steadyModeType = Constans.INSTANCE.getSteadyModeType();
            if (steadyModeType == 1) {
                Switch steady_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
                Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch, "steady_mode_switch");
                steady_mode_switch.setChecked(true);
                Switch steady_mode_switch2 = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
                Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch2, "steady_mode_switch");
                steady_mode_switch2.setVisibility(8);
            } else if (steadyModeType == 2) {
                Switch steady_mode_switch3 = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
                Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch3, "steady_mode_switch");
                steady_mode_switch3.setChecked(Constans.INSTANCE.isSteadyRunning());
            }
            Pdlog.m3273d(this.TAG, "showSteadyBtnIfNeed steadyModeType mode = " + Constans.INSTANCE.getSteadyModeType());
        } else {
            LinearLayout steady_mode_switch_layout2 = (LinearLayout) _$_findCachedViewById(C4188R.id.steady_mode_switch_layout);
            Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch_layout2, "steady_mode_switch_layout");
            steady_mode_switch_layout2.setVisibility(8);
        }
        Switch steady_mode_switch4 = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch4, "steady_mode_switch");
        setSteadySwitchLayout(steady_mode_switch4.isChecked());
    }

    private final void initView() {
        get_cruiseSelectVm().getMAdverVm().reqNetAdList(AdSceneConfig.UN_KNOWN_MODE, true);
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        this.faceAnimationView = face_animation_view;
        ((TextView) _$_findCachedViewById(C4188R.id.btn_start)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                CruiseSelectActivity.this.goToCruise();
            }
        });
        RecyclerView cruise_ways_recycler_view = (RecyclerView) _$_findCachedViewById(C4188R.id.cruise_ways_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(cruise_ways_recycler_view, "cruise_ways_recycler_view");
        CruiseSelectActivity cruiseSelectActivity = this;
        cruise_ways_recycler_view.setLayoutManager(new LinearLayoutManager(cruiseSelectActivity));
        this.cruiseSelectAdapter = new CruiseSelectAdapter(cruiseSelectActivity);
        CruiseSelectAdapter cruiseSelectAdapter = this.cruiseSelectAdapter;
        if (cruiseSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseSelectAdapter");
        }
        cruiseSelectAdapter.bindToRecyclerView((RecyclerView) _$_findCachedViewById(C4188R.id.cruise_ways_recycler_view));
        ((ImageView) _$_findCachedViewById(C4188R.id.setting_info)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                CruiseSelectActivity.this.showSettingDialog();
            }
        });
        ((ImageView) _$_findCachedViewById(C4188R.id.setting_btn)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                CruiseSelectActivity cruiseSelectActivity2 = CruiseSelectActivity.this;
                cruiseSelectActivity2.startActivity(new Intent(cruiseSelectActivity2, (Class<?>) StayPointSettingsActivity.class));
            }
        });
        CruiseSelectAdapter cruiseSelectAdapter2 = this.cruiseSelectAdapter;
        if (cruiseSelectAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseSelectAdapter");
        }
        cruiseSelectAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                String str;
                String str2;
                CruiseMapPresenter cruiseMapPresenter;
                String str3;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                try {
                    Object obj = adapter.getData().get(position);
                    if (!(obj instanceof CruiseSelectItem)) {
                        obj = null;
                    }
                    CruiseSelectItem cruiseSelectItem = (CruiseSelectItem) obj;
                    if (cruiseSelectItem == null || !cruiseSelectItem.isSelect()) {
                        str2 = CruiseSelectActivity.this.TAG;
                        Pdlog.m3273d(str2, "cruiseSelectAdapter setOnItemChildClickListener " + position);
                        List<?> data = adapter.getData();
                        Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                        int i = 0;
                        for (Object obj2 : data) {
                            int i2 = i + 1;
                            if (i < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            if (obj2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.CruiseSelectItem");
                            }
                            ((CruiseSelectItem) obj2).setSelect(i == position);
                            i = i2;
                        }
                        cruiseMapPresenter = CruiseSelectActivity.this.getCruiseMapPresenter();
                        cruiseMapPresenter.select(position);
                        CruiseSelectActivity cruiseSelectActivity2 = CruiseSelectActivity.this;
                        str3 = CruiseSelectActivity.this.KEY_LAST_SELECT_WAY;
                        SpUtils.set((Context) cruiseSelectActivity2, str3, position);
                        adapter.notifyDataSetChanged();
                        CruiseSelectActivity.this.currentSelectIndex = position;
                        return;
                    }
                    String cruiseRouteName = Constans.INSTANCE.getCruiseRouteName(String.valueOf(cruiseSelectItem.getMapModel().getId()), "");
                    String valueOf = String.valueOf(cruiseSelectItem.getMapModel().getId());
                    CruiseSelectActivity cruiseSelectActivity3 = CruiseSelectActivity.this;
                    String str4 = cruiseRouteName;
                    if (str4.length() == 0) {
                        str4 = CruiseSelectActivity.this.getString(C4188R.string.pdStr3_40) + cruiseSelectItem.getMapModel().getName();
                    }
                    cruiseSelectActivity3.showCruiseRouteDialog(str4, valueOf, position);
                } catch (Exception e) {
                    str = CruiseSelectActivity.this.TAG;
                    Pdlog.m3274e(str, "onSingleItemClick e: " + e);
                }
            }
        });
        showSteadyBtnIfNeed();
        ((Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$initView$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                CruiseSelectActivity.this.setSteadySwitchLayout(z);
                if (z) {
                    CruiseSelectActivity cruiseSelectActivity2 = CruiseSelectActivity.this;
                    ToastUtils.show(cruiseSelectActivity2, cruiseSelectActivity2.getString(C4188R.string.pdStr7_153), new Object[0]);
                }
                if (RobotSpeedUtil.INSTANCE.isSteadyRepeatLast()) {
                    Constans.INSTANCE.setSteadyRunning(z);
                }
            }
        }, 7, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCruiseRouteDialog(final String name, final String curId, final int position) {
        CruiseRouteEditDialog cruiseRouteEditDialog = new CruiseRouteEditDialog(this);
        cruiseRouteEditDialog.setOldName(name);
        cruiseRouteEditDialog.setOnConfirm(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$showCruiseRouteDialog$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String text) {
                Intrinsics.checkParameterIsNotNull(text, "text");
                Constans.INSTANCE.setCruiseRouteName(curId, text);
                CruiseSelectActivity.access$getCruiseSelectAdapter$p(CruiseSelectActivity.this).notifyItemChanged(position);
            }
        });
        cruiseRouteEditDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSteadySwitchLayout(boolean r5) {
        Pdlog.m3273d(this.TAG, "setSteadySwitchLayout : switch = " + r5 + "; ");
        if (r5) {
            ((TextView) _$_findCachedViewById(C4188R.id.steady_btn_tv)).setTextColor(getColor(C4188R.color.steady_mode_switch_layout_on_color));
        } else {
            ((TextView) _$_findCachedViewById(C4188R.id.steady_btn_tv)).setTextColor(getColor(C4188R.color.font_color_1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goToCruise() {
        CruiseSelectItem cruiseSelectItem;
        MapCruiseLine mapModel;
        String id;
        if (this.isRelease) {
            Pdlog.m3274e(this.TAG, "goToCruise failed isRelease ");
            return;
        }
        if (BatteryInfoManager.INSTANCE.checkAllowedForMove()) {
            int i = this.currentSelectIndex;
            int i2 = -1;
            if (i == -1) {
                String string = getString(C4188R.string.pdStr3_7);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr3_7)");
                MyBaseActivity.showTipDialog$default(this, string, null, null, null, 14, null);
                return;
            }
            try {
                ArrayList<CruiseSelectItem> arrayList = this.allMapData;
                if (arrayList != null && (cruiseSelectItem = arrayList.get(i)) != null && (mapModel = cruiseSelectItem.getMapModel()) != null && (id = mapModel.getId()) != null) {
                    i2 = Integer.parseInt(id);
                }
                if (i2 < 0) {
                    String string2 = getString(C4188R.string.pdStr3_7);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr3_7)");
                    MyBaseActivity.showTipDialog$default(this, string2, null, null, null, 14, null);
                    return;
                } else {
                    Intent intent = new Intent(this, (Class<?>) CruiseActivity.class);
                    intent.putExtra("CRUISE_ID_KEY", i2);
                    intent.putExtra(CruiseActivity.STEADY_MODE_KEY, getIsSteady());
                    intent.putStringArrayListExtra(CruiseActivity.STOP_KEY, this.stops);
                    DeliverTaskEditActivity.INSTANCE.setCanFillIn(false);
                    jumpAndFinish(intent);
                    return;
                }
            } catch (Exception e) {
                Pdlog.m3274e(this.TAG, "goToCruise: " + Log.getStackTraceString(e));
                return;
            }
        }
        Pdlog.m3273d(this.TAG, "batteryPresenter check Allowed for move is false");
        String string3 = getString(C4188R.string.pdStr2_19);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr2_19)");
        MyBaseActivity.showTipDialog$default(this, string3, null, null, null, 14, null);
        VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice14_2));
    }

    private final boolean getIsSteady() {
        if (!Constans.INSTANCE.getSettingSteadyModeSwitch()) {
            return false;
        }
        Switch steady_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch, "steady_mode_switch");
        return steady_mode_switch.isChecked();
    }

    private final void setNewData(ArrayList<CruiseSelectItem> list) {
        Pdlog.m3273d(this.TAG, "setNewData = " + list);
        if (!list.isEmpty()) {
            int i = SpUtils.get((Context) this, this.KEY_LAST_SELECT_WAY, 0);
            if (i > list.size() - 1) {
                i = 0;
            }
            list.get(i).setSelect(true);
            this.currentSelectIndex = i;
            getCruiseMapPresenter().select(i);
            this.allMapData = list;
        }
        CruiseSelectAdapter cruiseSelectAdapter = this.cruiseSelectAdapter;
        if (cruiseSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseSelectAdapter");
        }
        cruiseSelectAdapter.setNewData(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSettingDialog() {
        Pdlog.m3273d(this.TAG, "showSettingDialog");
        if (this.homeSettingDialog == null) {
            this.homeSettingDialog = new HomeSettingDialog(this);
            HomeSettingDialog homeSettingDialog = this.homeSettingDialog;
            if (homeSettingDialog == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog.setOnHomeSettingDialogClickListener(this.onHomeSettingDialogClickListener);
        }
        HomeSettingDialog homeSettingDialog2 = this.homeSettingDialog;
        if (homeSettingDialog2 == null) {
            Intrinsics.throwNpe();
        }
        homeSettingDialog2.show();
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this);
        getCruiseMapPresenter().replaceView((CruiseMapContract.ViewInterface) this);
        getIdleMovePresenter().replaceView(this);
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
        getIdleMovePresenter().actionIDLE();
        getIdleMovePresenter().actionTimerCount(true);
        this.leaseHelper.setOnLeaseExpireForceCloseCallback(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$bindPresenter$1
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
                String str;
                str = CruiseSelectActivity.this.TAG;
                Pdlog.m3273d(str, "onLeaseExpireForceCloseCallback");
                CruiseSelectActivity cruiseSelectActivity = CruiseSelectActivity.this;
                cruiseSelectActivity.jumpAndFinish(new Intent(cruiseSelectActivity, (Class<?>) SettingActivity.class));
            }
        });
        this.leaseHelper.bind(this);
        LeaseHelper leaseHelper = this.leaseHelper;
        Application application = getApplication();
        Intrinsics.checkExpressionValueIsNotNull(application, "this.application");
        leaseHelper.startCheck(application);
        showPowerEvent(BatteryInfoManager.INSTANCE.getPowerEvent());
        showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
        showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        if (BatteryInfoManager.INSTANCE.getNeedShowLowPowerNotify()) {
            showLowerNotify();
        }
        Peripherals.INSTANCE.getFunctionButton().addListener(this.functionButton);
        Peripherals.INSTANCE.getFunctionButton().setMute(false);
        CustomCallHelper.INSTANCE.addCallListener(this.onCustomCallListener);
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        touchSensorEventHelper.bindPresenter(faceVideoView);
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unBindPresenter");
        Peripherals.INSTANCE.getFunctionButton().removeListener(this.functionButton);
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this);
        CruiseSelectActivity cruiseSelectActivity = this;
        getCruiseMapPresenter().removeView(cruiseSelectActivity);
        getIdleMovePresenter().actionTimerCount(false);
        getIdleMovePresenter().removeView(cruiseSelectActivity);
        this.beeperCallHelper.unbind();
        this.leaseHelper.unbind();
        CustomCallHelper.INSTANCE.removeCallListener(this.onCustomCallListener);
        this.touchSensorEventHelper.unBindPresent();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(intent);
        finish();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
        }
    }

    private final void release() {
        this.isRelease = true;
        unBindPresenter();
    }

    @Override // com.pudutech.bumblebee.presenter.cruise_task.CruiseMapContract.ViewInterface
    public void hasStops(boolean hasStops) {
        if (hasStops) {
            LinearLayout cruise_point_notice = (LinearLayout) _$_findCachedViewById(C4188R.id.cruise_point_notice);
            Intrinsics.checkExpressionValueIsNotNull(cruise_point_notice, "cruise_point_notice");
            cruise_point_notice.setVisibility(0);
        } else {
            LinearLayout cruise_point_notice2 = (LinearLayout) _$_findCachedViewById(C4188R.id.cruise_point_notice);
            Intrinsics.checkExpressionValueIsNotNull(cruise_point_notice2, "cruise_point_notice");
            cruise_point_notice2.setVisibility(4);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.cruise_task.CruiseMapContract.ViewInterface
    public void showAll(ArrayList<MapCruiseLine> mapModels) {
        Intrinsics.checkParameterIsNotNull(mapModels, "mapModels");
        Pdlog.m3273d(this.TAG, "showAll " + mapModels.size());
        ArrayList<MapCruiseLine> arrayList = mapModels;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new CruiseSelectItem((MapCruiseLine) it.next(), false, 2, null));
        }
        setNewData(arrayList2);
    }

    @Override // com.pudutech.bumblebee.presenter.cruise_task.CruiseMapContract.ViewInterface
    public void showBackGround(final int maxX, final int minX, final int maxY, final int minY, final ArrayList<MapLine> model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showBackGround maxX = " + maxX + " , minX = " + minX + " , maxY = " + maxY + " , minY " + minY + " ; ");
        MapView mapView = (MapView) _$_findCachedViewById(C4188R.id.map_view);
        if (mapView != null) {
            mapView.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$showBackGround$1
                @Override // java.lang.Runnable
                public final void run() {
                    MapView mapView2 = (MapView) CruiseSelectActivity.this._$_findCachedViewById(C4188R.id.map_view);
                    if (mapView2 != null) {
                        mapView2.setMapData(maxX, maxY, minX, minY, model);
                    }
                }
            });
        }
    }

    @Override // com.pudutech.bumblebee.presenter.cruise_task.CruiseMapContract.ViewInterface
    public void showTheSelected(int selectedIndex, final MapCruiseLine model, final ArrayList<StayPoint> stopDots) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Intrinsics.checkParameterIsNotNull(stopDots, "stopDots");
        Pdlog.m3273d(this.TAG, "showTheSelected selectedIndex=" + selectedIndex + " stopDots.size=" + stopDots.size());
        MapView mapView = (MapView) _$_findCachedViewById(C4188R.id.map_view);
        if (mapView != null) {
            mapView.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseSelectActivity$showTheSelected$1
                @Override // java.lang.Runnable
                public final void run() {
                    ArrayList arrayList;
                    ArrayList arrayList2;
                    MapView mapView2 = (MapView) CruiseSelectActivity.this._$_findCachedViewById(C4188R.id.map_view);
                    if (mapView2 != null) {
                        mapView2.setSelectLinesData(model.getLines());
                    }
                    MapView mapView3 = (MapView) CruiseSelectActivity.this._$_findCachedViewById(C4188R.id.map_view);
                    if (mapView3 != null) {
                        mapView3.setStays(stopDots);
                    }
                    arrayList = CruiseSelectActivity.this.stops;
                    arrayList.clear();
                    for (StayPoint stayPoint : stopDots) {
                        arrayList2 = CruiseSelectActivity.this.stops;
                        arrayList2.add(stayPoint.getName());
                    }
                }
            });
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showChargerEvent " + model);
        if (model.getEvent() != BatteryContract2.ViewEvent.CHARGER_DISCONNECT) {
            PlayerModuleActivityManager.getInstance().finishAllModulesActivities();
            jumpAndFinish(new Intent(this, (Class<?>) RobotChargingActivity.class));
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showLowerNotify() {
        Pdlog.m3273d(this.TAG, "showLowerNotify ");
        if (this.isShowLowPowerDialog) {
            return;
        }
        BatteryInfoManager.INSTANCE.clearFirstTimeWarning();
        Pdlog.m3273d(this.TAG, "showBatteryEvent ShowLowPowerDialog");
        this.isShowLowPowerDialog = true;
        String string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr2_17);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_17)");
        MyBaseActivity.showTipDialog$default(this, string, null, null, null, 14, null);
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
        Pdlog.m3273d(this.TAG, "showPowerChange : i = " + i + "; ");
        ((MyStatusBarLayout) _$_findCachedViewById(C4188R.id.layout_my_status_bar)).setBattery(i);
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showPowerEvent " + model);
        PeripheralsSceneUtil.idleState$default(PeripheralsSceneUtil.INSTANCE, false, 1, null);
        int i = WhenMappings.$EnumSwitchMapping$0[model.getEvent().ordinal()];
        if (i == 1) {
            VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice14_2));
        } else if (i == 2) {
            VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice14_3));
        } else {
            if (i != 3) {
                return;
            }
            VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice14_1));
        }
    }

    @Override // com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract.ViewInterface
    public void showIdleEvent(IdleMoveContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$1[event.ordinal()];
        if (i == 1) {
            stopStandby();
        } else if (i == 2) {
            showStandbyAnimation();
        } else {
            if (i != 3) {
                return;
            }
            showSleepAnimation();
        }
    }

    private final void showSleepAnimation() {
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        Pdlog.m3273d(this.TAG, "showSleepAnimation");
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getSleep());
        PeripheralsSceneUtil.INSTANCE.sleep();
        setScreenDark();
    }

    private final void showStandbyAnimation() {
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        Pdlog.m3273d(this.TAG, "showStandbyAnimation");
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getStandby());
        PeripheralsSceneUtil.INSTANCE.standby();
        setScreenDark();
    }

    private final void stopStandby() {
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        Pdlog.m3273d(this.TAG, "stopStandby");
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.stopPlay();
        PeripheralsSceneUtil.idleState$default(PeripheralsSceneUtil.INSTANCE, false, 1, null);
        resetScreenLight();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        get_cruiseSelectVm().getMAdverVm().onCleared();
        Pdlog.m3273d(this.TAG, "onDestroy");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Pdlog.m3273d(this.TAG, "onResume");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Pdlog.m3273d(this.TAG, "onPause");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop");
    }
}
