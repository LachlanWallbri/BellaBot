package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ValidationDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.custom_call.CustomCallActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.FaceAnimationDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.HomeSettingDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.OnHomeSettingDialogClickListener;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CustomCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.LeaseHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MyStatusBarLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.track.report.ClickReport;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.FlowCardHelper;
import com.pudutech.bumblebee.robot_ui.util.FragmentUtils;
import com.pudutech.bumblebee.robot_ui.util.InputMethodUtil;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.FourGWifiFragment;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: SettingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u00ad\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n*\u0001*\u0018\u0000 a2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002abB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010.\u001a\u00020(H\u0002J\u0010\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020\u0006H\u0002J\u0012\u00101\u001a\u00020\u001c2\b\u00102\u001a\u0004\u0018\u000103H\u0016J\u0010\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u0006H\u0002J\u0010\u00106\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u0006H\u0002J\b\u00107\u001a\u00020(H\u0002J\u0006\u00108\u001a\u00020(J\b\u00109\u001a\u00020\u001cH\u0016J\u0010\u0010:\u001a\u00020(2\u0006\u0010;\u001a\u00020<H\u0016J\u0012\u0010=\u001a\u00020(2\b\u0010>\u001a\u0004\u0018\u00010?H\u0014J\b\u0010@\u001a\u00020(H\u0014J\b\u0010A\u001a\u00020(H\u0002J\b\u0010B\u001a\u00020(H\u0014J\b\u0010C\u001a\u00020(H\u0014J\b\u0010D\u001a\u00020(H\u0002J\b\u0010E\u001a\u00020(H\u0014J\b\u0010F\u001a\u00020(H\u0014J\u0010\u0010G\u001a\u00020(2\u0006\u0010H\u001a\u00020\u001cH\u0016J\b\u0010I\u001a\u00020(H\u0002J\u0010\u0010J\u001a\u00020(2\u0006\u0010K\u001a\u00020LH\u0002J\u0010\u0010M\u001a\u00020(2\u0006\u0010N\u001a\u00020OH\u0016J\u0010\u0010P\u001a\u00020(2\u0006\u0010Q\u001a\u00020RH\u0002J\u0010\u0010S\u001a\u00020(2\u0006\u0010T\u001a\u00020\rH\u0016J\b\u0010U\u001a\u00020(H\u0016J\u0010\u0010V\u001a\u00020(2\u0006\u0010W\u001a\u00020\u0006H\u0016J\u0010\u0010X\u001a\u00020(2\u0006\u0010N\u001a\u00020YH\u0016J\b\u0010Z\u001a\u00020(H\u0002J\b\u0010[\u001a\u00020(H\u0002J\b\u0010\\\u001a\u00020(H\u0002J\b\u0010]\u001a\u00020(H\u0002J\b\u0010^\u001a\u00020(H\u0002J\b\u0010_\u001a\u00020(H\u0002J\b\u0010`\u001a\u00020(H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001fR\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(0&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0004\n\u0002\u0010+R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/SettingActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewInterface;", "()V", "RESET_ADVANCED_CLICK_COUNT", "", "TAG", "", "advancedShowClickCount", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "currentIdleEvent", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewEvent;", "currentSelectIndex", "faceAnimationDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/FaceAnimationDialog;", "handler", "Landroid/os/Handler;", "homeSettingDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/HomeSettingDialog;", "idleMovePresenter", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "getIdleMovePresenter", "()Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "idleMovePresenter$delegate", "Lkotlin/Lazy;", "isBind", "", "()Z", "setBind", "(Z)V", "isRelease", "isUnBind", "setUnBind", "leaseHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/LeaseHelper;", "onCustomCallListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "", "onHomeSettingDialogClickListener", "com/pudutech/bumblebee/robot_ui/module/setting/ui/SettingActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/SettingActivity$onHomeSettingDialogClickListener$1;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "bindPresenter", "changeFragemnt", "index", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "getItemBg", "item", "getItemTextColor", "hideFaceDialog", "initView", "isBusyState", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onFragmentChange", "onPause", "onResume", "onSettingItemSelectChange", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "release", "replaceFragment", "fragment", "Landroidx/fragment/app/Fragment;", "showChargerEvent", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showFaceDialog", "animations", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "showIdleEvent", "event", "showLowerNotify", "showPowerChange", "i", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "showSettingDialog", "showSleepAnimation", "showStandbyAnimation", "stopStandby", "unBindPresenter", "updateAboutSetting", "updateSettingTab", "Companion", "DefFourGListener", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SettingActivity extends MyBaseActivity implements BatteryContract2.ViewInterface, IdleMoveContract.ViewInterface {
    private static final int SET_BASIC_SETUP = 0;
    private static boolean isSwichlanguage;
    private HashMap _$_findViewCache;
    private int advancedShowClickCount;
    private FaceAnimationDialog faceAnimationDialog;
    private HomeSettingDialog homeSettingDialog;
    private boolean isBind;
    private boolean isRelease;
    private boolean isUnBind;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int SET_WIFI = 1;
    private static final int SET_VOLUME = 2;
    private static final int SET_SPEED = 3;
    private static final int SET_UPDATE_VERSION = 4;
    private static final int SET_DEBUG = 5;
    private static final int SET_PALLET_SENSOR = 6;
    private static final int SET_LATTICE_TEXT = 7;
    private static final int SET_MULTI_MAP = 8;
    private static final int SET_PALLET_COUNT = 9;
    private static final int SET_VOICE_SETTING = 10;
    private static final int SET_ADVANCED = 11;
    private static final int SET_ITERACTIVE = 12;
    private static final int SET_ABOUT = 13;
    private static final int SET_BOX = 14;
    private static final int SET_FUNCTION = 15;
    private static final int SET_LED = 16;
    private static final int SET_SECURITY = 17;
    private static final int SET_SLEEP = 18;
    private static final int SET_CALL = 19;
    private static final int SET_MUSIC = 20;
    private final String TAG = "SettingActivity";
    private int currentSelectIndex = -1;

    /* renamed from: idleMovePresenter$delegate, reason: from kotlin metadata */
    private final Lazy idleMovePresenter = LazyKt.lazy(new Function0<IdleMovePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$idleMovePresenter$2
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
    private final int RESET_ADVANCED_CLICK_COUNT = 101;
    private final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            int i2 = message.what;
            i = SettingActivity.this.RESET_ADVANCED_CLICK_COUNT;
            if (i2 == i) {
                Pdlog.m3273d(SettingActivity.this.TAG, "RESET_ADVANCED_CLICK_COUNT");
                SettingActivity.this.advancedShowClickCount = 0;
            }
            return true;
        }
    });
    private Function1<? super CustomCallTargetBean, Unit> onCustomCallListener = new Function1<CustomCallTargetBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$onCustomCallListener$1
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
            Intrinsics.checkParameterIsNotNull(it, "it");
            Pdlog.m3273d(SettingActivity.this.TAG, "onCustomCallListener task: " + it);
            SettingActivity.this.jumpAndFinish(CustomCallActivity.INSTANCE.createIntent(SettingActivity.this, it));
        }
    };
    private final SettingActivity$onHomeSettingDialogClickListener$1 onHomeSettingDialogClickListener = new OnHomeSettingDialogClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$onHomeSettingDialogClickListener$1
        @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.OnHomeSettingDialogClickListener
        public void onFunClick(HomeSettingDialog.FunctionType type, Intent intent) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            if (type == HomeSettingDialog.FunctionType.SETTING || intent == null) {
                return;
            }
            SettingActivity.this.jumpAndFinish(intent);
        }
    };
    private final TouchSensorEventHelper touchSensorEventHelper = new TouchSensorEventHelper();
    private IdleMoveContract.ViewEvent currentIdleEvent = IdleMoveContract.ViewEvent.READY;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[IdleMoveContract.ViewEvent.values().length];

        static {
            $EnumSwitchMapping$0[IdleMoveContract.ViewEvent.READY.ordinal()] = 1;
            $EnumSwitchMapping$0[IdleMoveContract.ViewEvent.STAND_BY.ordinal()] = 2;
            $EnumSwitchMapping$0[IdleMoveContract.ViewEvent.SLEEP.ordinal()] = 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IdleMovePresenter getIdleMovePresenter() {
        return (IdleMovePresenter) this.idleMovePresenter.getValue();
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

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showLowerNotify() {
    }

    /* compiled from: SettingActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b+\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0014\u0010\u001f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0014\u0010!\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0014\u0010#\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0014\u0010%\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0014\u0010'\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u0014\u0010)\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0006R\u0014\u0010+\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0014\u0010-\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u001a\u0010/\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00101\"\u0004\b2\u00103¨\u00064"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/SettingActivity$Companion;", "", "()V", "SET_ABOUT", "", "getSET_ABOUT", "()I", "SET_ADVANCED", "getSET_ADVANCED", "SET_BASIC_SETUP", "getSET_BASIC_SETUP", "SET_BOX", "getSET_BOX", "SET_CALL", "getSET_CALL", "SET_DEBUG", "getSET_DEBUG", "SET_FUNCTION", "getSET_FUNCTION", "SET_ITERACTIVE", "getSET_ITERACTIVE", "SET_LATTICE_TEXT", "getSET_LATTICE_TEXT", "SET_LED", "getSET_LED", "SET_MULTI_MAP", "getSET_MULTI_MAP", "SET_MUSIC", "getSET_MUSIC", "SET_PALLET_COUNT", "getSET_PALLET_COUNT", "SET_PALLET_SENSOR", "getSET_PALLET_SENSOR", "SET_SECURITY", "getSET_SECURITY", "SET_SLEEP", "getSET_SLEEP", "SET_SPEED", "getSET_SPEED", "SET_UPDATE_VERSION", "getSET_UPDATE_VERSION", "SET_VOICE_SETTING", "getSET_VOICE_SETTING", "SET_VOLUME", "getSET_VOLUME", "SET_WIFI", "getSET_WIFI", "isSwichlanguage", "", "()Z", "setSwichlanguage", "(Z)V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isSwichlanguage() {
            return SettingActivity.isSwichlanguage;
        }

        public final void setSwichlanguage(boolean z) {
            SettingActivity.isSwichlanguage = z;
        }

        public final int getSET_BASIC_SETUP() {
            return SettingActivity.SET_BASIC_SETUP;
        }

        public final int getSET_WIFI() {
            return SettingActivity.SET_WIFI;
        }

        public final int getSET_VOLUME() {
            return SettingActivity.SET_VOLUME;
        }

        public final int getSET_SPEED() {
            return SettingActivity.SET_SPEED;
        }

        public final int getSET_UPDATE_VERSION() {
            return SettingActivity.SET_UPDATE_VERSION;
        }

        public final int getSET_DEBUG() {
            return SettingActivity.SET_DEBUG;
        }

        public final int getSET_PALLET_SENSOR() {
            return SettingActivity.SET_PALLET_SENSOR;
        }

        public final int getSET_LATTICE_TEXT() {
            return SettingActivity.SET_LATTICE_TEXT;
        }

        public final int getSET_MULTI_MAP() {
            return SettingActivity.SET_MULTI_MAP;
        }

        public final int getSET_PALLET_COUNT() {
            return SettingActivity.SET_PALLET_COUNT;
        }

        public final int getSET_VOICE_SETTING() {
            return SettingActivity.SET_VOICE_SETTING;
        }

        public final int getSET_ADVANCED() {
            return SettingActivity.SET_ADVANCED;
        }

        public final int getSET_ITERACTIVE() {
            return SettingActivity.SET_ITERACTIVE;
        }

        public final int getSET_ABOUT() {
            return SettingActivity.SET_ABOUT;
        }

        public final int getSET_BOX() {
            return SettingActivity.SET_BOX;
        }

        public final int getSET_FUNCTION() {
            return SettingActivity.SET_FUNCTION;
        }

        public final int getSET_LED() {
            return SettingActivity.SET_LED;
        }

        public final int getSET_SECURITY() {
            return SettingActivity.SET_SECURITY;
        }

        public final int getSET_SLEEP() {
            return SettingActivity.SET_SLEEP;
        }

        public final int getSET_CALL() {
            return SettingActivity.SET_CALL;
        }

        public final int getSET_MUSIC() {
            return SettingActivity.SET_MUSIC;
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_layout_setting);
        initView();
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isSwichlanguage) {
            try {
                InputMethodUtil.changeInputMethodIfNeed(RobotContext.INSTANCE.getContext(), "gokey");
                isSwichlanguage = false;
                Pdlog.m3274e(this.TAG, "changeInputMethodIfNeed : " + isSwichlanguage);
            } catch (Exception e) {
                Pdlog.m3274e(this.TAG, "changeInputMethodIfNeed : " + e);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public final void initView() {
        changeFragemnt(SET_BASIC_SETUP);
        ((TextView) _$_findCachedViewById(C4188R.id.base_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_BASIC_SETUP());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.wifi_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_WIFI());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.volume_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_VOLUME());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.speed_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_SPEED());
            }
        });
        if (AppUpdateManager.INSTANCE.needTipRedPoint()) {
            Drawable drawable = getResources().getDrawable(C4188R.drawable.icon_settings_updatetips);
            Drawable drawable1 = getResources().getDrawable(C4188R.drawable.home_upgrade);
            Intrinsics.checkExpressionValueIsNotNull(drawable, "drawable");
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            Intrinsics.checkExpressionValueIsNotNull(drawable1, "drawable1");
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
            ((TextView) _$_findCachedViewById(C4188R.id.version_setting)).setCompoundDrawables(drawable1, null, drawable, null);
        }
        ((TextView) _$_findCachedViewById(C4188R.id.version_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_UPDATE_VERSION());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.debug_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_DEBUG());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.deliver_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_PALLET_SENSOR());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.lattice_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_LATTICE_TEXT());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.multi_map_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$9
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_MULTI_MAP());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.pallet_count_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$10
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_PALLET_COUNT());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.voice_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$11
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_VOICE_SETTING());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.interactive_mode_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$12
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_ITERACTIVE());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.advanced_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$13
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_ADVANCED());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.about_info_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$14
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_ABOUT());
            }
        });
        ((ImageView) _$_findCachedViewById(C4188R.id.img_back)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$15
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                LeaseHelper leaseHelper;
                LeaseHelper leaseHelper2;
                leaseHelper = SettingActivity.this.leaseHelper;
                if (leaseHelper.getIsLeaseExpire()) {
                    leaseHelper2 = SettingActivity.this.leaseHelper;
                    leaseHelper2.showExpireDialog(SettingActivity.this);
                } else {
                    SettingActivity.this.showSettingDialog();
                }
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.box_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$16
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_BOX());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.bar_title_tv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i;
                Handler handler;
                int i2;
                int i3;
                int i4;
                Handler handler2;
                int i5;
                TextView advanced_setting = (TextView) SettingActivity.this._$_findCachedViewById(C4188R.id.advanced_setting);
                Intrinsics.checkExpressionValueIsNotNull(advanced_setting, "advanced_setting");
                if (advanced_setting.getVisibility() == 0) {
                    return;
                }
                String str = SettingActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("bar_title_tv add advanced click count ");
                i = SettingActivity.this.advancedShowClickCount;
                sb.append(i);
                Pdlog.m3273d(str, sb.toString());
                handler = SettingActivity.this.handler;
                i2 = SettingActivity.this.RESET_ADVANCED_CLICK_COUNT;
                handler.removeMessages(i2);
                SettingActivity settingActivity = SettingActivity.this;
                i3 = settingActivity.advancedShowClickCount;
                settingActivity.advancedShowClickCount = i3 + 1;
                i4 = SettingActivity.this.advancedShowClickCount;
                if (i4 == 5) {
                    final ValidationDialog validationDialog = new ValidationDialog(SettingActivity.this);
                    validationDialog.setMPasswordConfig(CollectionsKt.arrayListOf(Constans.INSTANCE.getSettingPassword(), "pudupw"));
                    validationDialog.setOnPermissionCheckResult(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$17.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                            invoke(bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z) {
                            if (z) {
                                ToastUtils.show(RobotContext.INSTANCE.getContext(), SettingActivity.this.getString(C4188R.string.pdStr7_95), new Object[0]);
                                TextView advanced_setting2 = (TextView) SettingActivity.this._$_findCachedViewById(C4188R.id.advanced_setting);
                                Intrinsics.checkExpressionValueIsNotNull(advanced_setting2, "advanced_setting");
                                advanced_setting2.setVisibility(0);
                                validationDialog.dismiss();
                                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_ADVANCED());
                                ((ScrollView) SettingActivity.this._$_findCachedViewById(C4188R.id.tag_scroll_view)).fullScroll(130);
                            }
                        }
                    });
                    validationDialog.show();
                }
                handler2 = SettingActivity.this.handler;
                i5 = SettingActivity.this.RESET_ADVANCED_CLICK_COUNT;
                handler2.sendEmptyMessageDelayed(i5, 1000L);
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.function_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$18
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_FUNCTION());
            }
        });
        ImageView led_setting_new = (ImageView) _$_findCachedViewById(C4188R.id.led_setting_new);
        Intrinsics.checkExpressionValueIsNotNull(led_setting_new, "led_setting_new");
        led_setting_new.setVisibility(Constans.INSTANCE.isLedSettingNew() ? 0 : 8);
        ((TextView) _$_findCachedViewById(C4188R.id.pallet_led_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$19
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                if (Constans.INSTANCE.isLedSettingNew()) {
                    Constans.INSTANCE.setLedSettingNew(false);
                    ImageView led_setting_new2 = (ImageView) SettingActivity.this._$_findCachedViewById(C4188R.id.led_setting_new);
                    Intrinsics.checkExpressionValueIsNotNull(led_setting_new2, "led_setting_new");
                    led_setting_new2.setVisibility(8);
                }
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_LED());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.security_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$20
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_SECURITY());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.sleep_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$21
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_SLEEP());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.callSetting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$22
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_CALL());
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.music_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$23
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SettingActivity.this.changeFragemnt(SettingActivity.INSTANCE.getSET_MUSIC());
            }
        });
        View dummyView = _$_findCachedViewById(C4188R.id.dummyView);
        Intrinsics.checkExpressionValueIsNotNull(dummyView, "dummyView");
        ViewExtKt.setFastClickListener$default(dummyView, 5, 0L, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$initView$24
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
                i = SettingActivity.this.currentSelectIndex;
                if (i == SettingActivity.INSTANCE.getSET_SECURITY()) {
                    Fragment topShow = FragmentUtils.getTopShow(SettingActivity.this.getSupportFragmentManager());
                    if (topShow instanceof SecuritySettingsFragment) {
                        ((SecuritySettingsFragment) topShow).resetPassword();
                    }
                }
            }
        }, 2, null);
        bindPresenter();
        updateSettingTab();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateSettingTab() {
        if (MachineInfoHelper.INSTANCE.getLoraType() != MachineInfo.LoraType.NoDevice && Constans.INSTANCE.getSettingBoxSwitch()) {
            TextView box_setting = (TextView) _$_findCachedViewById(C4188R.id.box_setting);
            Intrinsics.checkExpressionValueIsNotNull(box_setting, "box_setting");
            box_setting.setVisibility(0);
        } else {
            TextView box_setting2 = (TextView) _$_findCachedViewById(C4188R.id.box_setting);
            Intrinsics.checkExpressionValueIsNotNull(box_setting2, "box_setting");
            box_setting2.setVisibility(8);
        }
        updateAboutSetting();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAboutSetting() {
        if (Constans.INSTANCE.getSettingAboutInfoSwitch()) {
            TextView about_info_setting = (TextView) _$_findCachedViewById(C4188R.id.about_info_setting);
            Intrinsics.checkExpressionValueIsNotNull(about_info_setting, "about_info_setting");
            about_info_setting.setVisibility(0);
        } else {
            TextView about_info_setting2 = (TextView) _$_findCachedViewById(C4188R.id.about_info_setting);
            Intrinsics.checkExpressionValueIsNotNull(about_info_setting2, "about_info_setting");
            about_info_setting2.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeFragemnt(int index) {
        Pdlog.m3273d(this.TAG, "changeFragemnt " + index);
        if (this.currentSelectIndex != index) {
            this.currentSelectIndex = index;
            onSettingItemSelectChange();
            onFragmentChange();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SettingActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006H\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0006H\u0016J\b\u0010\f\u001a\u00020\u0004H\u0016¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/SettingActivity$DefFourGListener;", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment$OnFourGPageListener;", "(Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/SettingActivity;)V", "on4GActivateResult", "", "suceesss", "", "on4GDataEnable", "isChecked", "onClickEvent", "view", "Landroid/view/View;", "onPlayClick", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final class DefFourGListener implements FourGWifiFragment.OnFourGPageListener {
        public DefFourGListener() {
        }

        @Override // com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment.OnFourGPageListener
        public void on4GActivateStart() {
            FourGWifiFragment.OnFourGPageListener.DefaultImpls.on4GActivateStart(this);
        }

        @Override // com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment.OnFourGPageListener
        public void onSkipWifi() {
            FourGWifiFragment.OnFourGPageListener.DefaultImpls.onSkipWifi(this);
        }

        @Override // com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment.OnFourGPageListener
        public void on4GActivateResult(boolean suceesss) {
            int i = suceesss ? C4188R.string.sim_activation_success : C4188R.string.sim_activation_failed;
            ToastUtils.show(BaseApplication.INSTANCE.getInstance(), i, 1);
            Pdlog.m3273d(SettingActivity.this.TAG, "on4GActivateResult  = " + i);
        }

        @Override // com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment.OnFourGPageListener
        public void on4GDataEnable(boolean isChecked) {
            SignalView signal_view = (SignalView) SettingActivity.this._$_findCachedViewById(C4188R.id.signal_view);
            Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
            signal_view.setVisibility(isChecked ? 0 : 8);
        }

        @Override // com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment.OnFourGPageListener
        public void onPlayClick() {
            PlaySound.playBtnVoice(BaseApplication.INSTANCE.getInstance(), C4188R.raw.btn_click_1);
        }

        @Override // com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment.OnFourGPageListener
        public void onClickEvent(View view, boolean isChecked) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            FourGWifiFragment.OnFourGPageListener.DefaultImpls.onClickEvent(this, view, isChecked);
            ClickReport.INSTANCE.onClickEvent(view, MapsKt.emptyMap(), 0);
            Pdlog.m3273d(SettingActivity.this.TAG, "onClickEvent view = " + view);
        }
    }

    private final void onFragmentChange() {
        Pdlog.m3273d(this.TAG, "onFragmentChange " + this.currentSelectIndex);
        Fragment topShow = FragmentUtils.getTopShow(getSupportFragmentManager());
        if (!(topShow instanceof BaseVmFragment)) {
            topShow = null;
        }
        BaseVmFragment baseVmFragment = (BaseVmFragment) topShow;
        if (baseVmFragment != null) {
            baseVmFragment.release();
        }
        int i = this.currentSelectIndex;
        if (i == SET_BASIC_SETUP) {
            replaceFragment(new BasicSetupFragment());
            return;
        }
        if (i == SET_WIFI) {
            FourGWifiFragment newInstance$default = FourGWifiFragment.Companion.newInstance$default(FourGWifiFragment.INSTANCE, FlowCardHelper.INSTANCE.isFourG(), false, false, 2, null);
            newInstance$default.setOnPageListener(new DefFourGListener());
            replaceFragment(newInstance$default);
            return;
        }
        if (i == SET_VOLUME) {
            replaceFragment(new SoundFragment());
            return;
        }
        if (i == SET_SPEED) {
            replaceFragment(new SpeedFragment());
            return;
        }
        if (i == SET_UPDATE_VERSION) {
            replaceFragment(new VersionFragment());
            return;
        }
        if (i == SET_DEBUG) {
            replaceFragment(new DebugFragment());
            return;
        }
        if (i == SET_PALLET_SENSOR) {
            replaceFragment(new DeliverSettingFragment());
            return;
        }
        if (i == SET_LATTICE_TEXT) {
            replaceFragment(new LatticeScreenFragment());
            return;
        }
        if (i == SET_MULTI_MAP) {
            replaceFragment(new MultiMapFragment());
            return;
        }
        if (i == SET_PALLET_COUNT) {
            return;
        }
        if (i == SET_VOICE_SETTING) {
            replaceFragment(new VoiceFragment());
            return;
        }
        if (i == SET_ITERACTIVE) {
            replaceFragment(new LotteryFragment());
            return;
        }
        if (i == SET_ADVANCED) {
            AdvancedSettingsFragment advancedSettingsFragment = new AdvancedSettingsFragment();
            advancedSettingsFragment.setOnAboutInfoSwitchChange(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$onFragmentChange$1
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
                    SettingActivity.this.updateAboutSetting();
                }
            });
            advancedSettingsFragment.setOnBoxNoticeSwitchChange(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$onFragmentChange$2
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
                    SettingActivity.this.updateSettingTab();
                }
            });
            replaceFragment(advancedSettingsFragment);
            return;
        }
        if (i == SET_ABOUT) {
            replaceFragment(new AboutInfoFragment());
            return;
        }
        if (i == SET_BOX) {
            replaceFragment(new BoxSettingsFragment());
            return;
        }
        if (i == SET_FUNCTION) {
            replaceFragment(new FunctionFragment());
            return;
        }
        if (i == SET_LED) {
            replaceFragment(new LedSettingsFragment());
            return;
        }
        if (i == SET_SECURITY) {
            replaceFragment(new SecuritySettingsFragment());
            return;
        }
        if (i == SET_SLEEP) {
            replaceFragment(new SleepSettingFragment());
        } else if (i == SET_CALL) {
            replaceFragment(new CallSettingFragment());
        } else if (i == SET_MUSIC) {
            replaceFragment(MusicFragment.INSTANCE.newInstance());
        }
    }

    private final void replaceFragment(Fragment fragment) {
        FragmentUtils.replace(getSupportFragmentManager(), fragment, C4188R.id.fragment_container);
    }

    private final void onSettingItemSelectChange() {
        ((TextView) _$_findCachedViewById(C4188R.id.deliver_setting)).setBackgroundResource(getItemBg(SET_PALLET_SENSOR));
        ((TextView) _$_findCachedViewById(C4188R.id.debug_setting)).setBackgroundResource(getItemBg(SET_DEBUG));
        ((TextView) _$_findCachedViewById(C4188R.id.base_setting)).setBackgroundResource(getItemBg(SET_BASIC_SETUP));
        ((TextView) _$_findCachedViewById(C4188R.id.wifi_setting)).setBackgroundResource(getItemBg(SET_WIFI));
        ((TextView) _$_findCachedViewById(C4188R.id.volume_setting)).setBackgroundResource(getItemBg(SET_VOLUME));
        ((TextView) _$_findCachedViewById(C4188R.id.speed_setting)).setBackgroundResource(getItemBg(SET_SPEED));
        ((TextView) _$_findCachedViewById(C4188R.id.version_setting)).setBackgroundResource(getItemBg(SET_UPDATE_VERSION));
        ((TextView) _$_findCachedViewById(C4188R.id.lattice_setting)).setBackgroundResource(getItemBg(SET_LATTICE_TEXT));
        ((TextView) _$_findCachedViewById(C4188R.id.multi_map_setting)).setBackgroundResource(getItemBg(SET_MULTI_MAP));
        ((TextView) _$_findCachedViewById(C4188R.id.pallet_count_setting)).setBackgroundResource(getItemBg(SET_PALLET_COUNT));
        ((TextView) _$_findCachedViewById(C4188R.id.voice_setting)).setBackgroundResource(getItemBg(SET_VOICE_SETTING));
        ((TextView) _$_findCachedViewById(C4188R.id.interactive_mode_setting)).setBackgroundResource(getItemBg(SET_ITERACTIVE));
        ((TextView) _$_findCachedViewById(C4188R.id.advanced_setting)).setBackgroundResource(getItemBg(SET_ADVANCED));
        ((TextView) _$_findCachedViewById(C4188R.id.about_info_setting)).setBackgroundResource(getItemBg(SET_ABOUT));
        ((TextView) _$_findCachedViewById(C4188R.id.box_setting)).setBackgroundResource(getItemBg(SET_BOX));
        ((TextView) _$_findCachedViewById(C4188R.id.function_setting)).setBackgroundResource(getItemBg(SET_FUNCTION));
        ((TextView) _$_findCachedViewById(C4188R.id.pallet_led_setting)).setBackgroundResource(getItemBg(SET_LED));
        ((TextView) _$_findCachedViewById(C4188R.id.security_setting)).setBackgroundResource(getItemBg(SET_SECURITY));
        ((TextView) _$_findCachedViewById(C4188R.id.sleep_setting)).setBackgroundResource(getItemBg(SET_SLEEP));
        ((TextView) _$_findCachedViewById(C4188R.id.callSetting)).setBackgroundResource(getItemBg(SET_CALL));
        ((TextView) _$_findCachedViewById(C4188R.id.music_setting)).setBackgroundResource(getItemBg(SET_MUSIC));
        ((TextView) _$_findCachedViewById(C4188R.id.deliver_setting)).setTextColor(getItemTextColor(SET_PALLET_SENSOR));
        ((TextView) _$_findCachedViewById(C4188R.id.debug_setting)).setTextColor(getItemTextColor(SET_DEBUG));
        ((TextView) _$_findCachedViewById(C4188R.id.base_setting)).setTextColor(getItemTextColor(SET_BASIC_SETUP));
        ((TextView) _$_findCachedViewById(C4188R.id.wifi_setting)).setTextColor(getItemTextColor(SET_WIFI));
        ((TextView) _$_findCachedViewById(C4188R.id.volume_setting)).setTextColor(getItemTextColor(SET_VOLUME));
        ((TextView) _$_findCachedViewById(C4188R.id.speed_setting)).setTextColor(getItemTextColor(SET_SPEED));
        ((TextView) _$_findCachedViewById(C4188R.id.version_setting)).setTextColor(getItemTextColor(SET_UPDATE_VERSION));
        ((TextView) _$_findCachedViewById(C4188R.id.lattice_setting)).setTextColor(getItemTextColor(SET_LATTICE_TEXT));
        ((TextView) _$_findCachedViewById(C4188R.id.multi_map_setting)).setTextColor(getItemTextColor(SET_MULTI_MAP));
        ((TextView) _$_findCachedViewById(C4188R.id.pallet_count_setting)).setTextColor(getItemTextColor(SET_PALLET_COUNT));
        ((TextView) _$_findCachedViewById(C4188R.id.voice_setting)).setTextColor(getItemTextColor(SET_VOICE_SETTING));
        ((TextView) _$_findCachedViewById(C4188R.id.interactive_mode_setting)).setTextColor(getItemTextColor(SET_ITERACTIVE));
        ((TextView) _$_findCachedViewById(C4188R.id.advanced_setting)).setTextColor(getItemTextColor(SET_ADVANCED));
        ((TextView) _$_findCachedViewById(C4188R.id.about_info_setting)).setTextColor(getItemTextColor(SET_ABOUT));
        ((TextView) _$_findCachedViewById(C4188R.id.box_setting)).setTextColor(getItemTextColor(SET_BOX));
        ((TextView) _$_findCachedViewById(C4188R.id.function_setting)).setTextColor(getItemTextColor(SET_FUNCTION));
        ((TextView) _$_findCachedViewById(C4188R.id.pallet_led_setting)).setTextColor(getItemTextColor(SET_LED));
        ((TextView) _$_findCachedViewById(C4188R.id.security_setting)).setTextColor(getItemTextColor(SET_SECURITY));
        ((TextView) _$_findCachedViewById(C4188R.id.sleep_setting)).setTextColor(getItemTextColor(SET_SLEEP));
        ((TextView) _$_findCachedViewById(C4188R.id.callSetting)).setTextColor(getItemTextColor(SET_CALL));
        ((TextView) _$_findCachedViewById(C4188R.id.music_setting)).setTextColor(getItemTextColor(SET_MUSIC));
    }

    private final int getItemBg(int item) {
        if (this.currentSelectIndex == item) {
            return C4188R.drawable.rectangle_setting_item_corners;
        }
        return C4188R.drawable.rectangle_setting_item_unselect_corners;
    }

    private final int getItemTextColor(int item) {
        if (this.currentSelectIndex == item) {
            return -1;
        }
        return getColor(C4188R.color.font_color_1);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(intent);
        finish();
    }

    private final void release() {
        this.isRelease = true;
        unBindPresenter();
        Fragment topShow = FragmentUtils.getTopShow(getSupportFragmentManager());
        if (!(topShow instanceof BaseVmFragment)) {
            topShow = null;
        }
        BaseVmFragment baseVmFragment = (BaseVmFragment) topShow;
        if (baseVmFragment != null) {
            baseVmFragment.release();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (!this.isBind || this.isUnBind) {
            return;
        }
        Pdlog.m3273d(this.TAG, "onResume start actionTimerCount");
        getIdleMovePresenter().actionTimerCount(true);
        this.beeperCallHelper.receiverCallTask(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.isUnBind) {
            return;
        }
        Pdlog.m3273d(this.TAG, "onPause stop actionTimerCount");
        getIdleMovePresenter().actionTimerCount(false);
        this.beeperCallHelper.stopReceiverCallTask();
    }

    /* renamed from: isUnBind, reason: from getter */
    public final boolean getIsUnBind() {
        return this.isUnBind;
    }

    public final void setUnBind(boolean z) {
        this.isUnBind = z;
    }

    /* renamed from: isBind, reason: from getter */
    public final boolean getIsBind() {
        return this.isBind;
    }

    public final void setBind(boolean z) {
        this.isBind = z;
    }

    private final void unBindPresenter() {
        this.isUnBind = true;
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this);
        getIdleMovePresenter().actionTimerCount(false);
        getIdleMovePresenter().removeView(this);
        this.beeperCallHelper.unbind();
        this.leaseHelper.unbind();
        CustomCallHelper.INSTANCE.removeCallListener(this.onCustomCallListener);
        this.touchSensorEventHelper.unBindPresent();
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
        this.faceAnimationDialog = new FaceAnimationDialog();
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceAnimationDialog faceAnimationDialog = this.faceAnimationDialog;
        if (faceAnimationDialog == null) {
            Intrinsics.throwNpe();
        }
        touchSensorEventHelper.bindPresenter(faceAnimationDialog, this);
        showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this);
        showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
        getIdleMovePresenter().replaceView(this);
        getIdleMovePresenter().actionTimerCount(true);
        this.leaseHelper.bind(null);
        LeaseHelper leaseHelper = this.leaseHelper;
        Application application = getApplication();
        Intrinsics.checkExpressionValueIsNotNull(application, "this.application");
        leaseHelper.startCheck(application);
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
        this.isBind = true;
        CustomCallHelper.INSTANCE.addCallListener(this.onCustomCallListener);
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3275i(this.TAG, "showChargerEvent " + model);
        if (model.getEvent() != BatteryContract2.ViewEvent.CHARGER_DISCONNECT) {
            PlayerModuleActivityManager.getInstance().finishAllModulesActivities();
            jumpAndFinish(new Intent(this, (Class<?>) RobotChargingActivity.class));
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
        Pdlog.m3273d(this.TAG, "showPowerChange : i = " + i + "; ");
        ((MyStatusBarLayout) _$_findCachedViewById(C4188R.id.layout_my_status_bar)).setBattery(i);
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3275i(this.TAG, "showPowerEvent " + model);
    }

    private final void showSleepAnimation() {
        Pdlog.m3273d(this.TAG, "showSleepAnimation");
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        showFaceDialog(SceneAnimationResources.INSTANCE.getSleep());
        PeripheralsSceneUtil.INSTANCE.sleep();
        setScreenDark();
    }

    private final void showStandbyAnimation() {
        Pdlog.m3273d(this.TAG, "showStandbyAnimation");
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        showFaceDialog(SceneAnimationResources.INSTANCE.getStandby());
        PeripheralsSceneUtil.INSTANCE.standby();
        setScreenDark();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged : hasFocus = " + hasFocus + ';');
        if (!this.isRelease && !hasFocus && this.currentIdleEvent == IdleMoveContract.ViewEvent.READY) {
            Pdlog.m3273d(this.TAG, "onWindowFocusChanged : hasFocus = " + hasFocus + "; not need timer count");
            getIdleMovePresenter().actionTimerCount(false);
            return;
        }
        if (!this.isRelease && hasFocus && this.currentIdleEvent == IdleMoveContract.ViewEvent.READY) {
            Pdlog.m3273d(this.TAG, "onWindowFocusChanged : hasFocus = " + hasFocus + "; need timer count");
            getIdleMovePresenter().actionTimerCount(true);
        }
    }

    private final void showFaceDialog(FaceVideoAnimation animations) {
        Pdlog.m3273d(this.TAG, "showFaceDialog ");
        FaceAnimationDialog faceAnimationDialog = this.faceAnimationDialog;
        if (faceAnimationDialog == null) {
            Intrinsics.throwNpe();
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        faceAnimationDialog.show(supportFragmentManager, "face_animation_dialog");
        FaceAnimationDialog faceAnimationDialog2 = this.faceAnimationDialog;
        if (faceAnimationDialog2 == null) {
            Intrinsics.throwNpe();
        }
        faceAnimationDialog2.playAnimation(animations);
        FaceAnimationDialog faceAnimationDialog3 = this.faceAnimationDialog;
        if (faceAnimationDialog3 == null) {
            Intrinsics.throwNpe();
        }
        faceAnimationDialog3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.SettingActivity$showFaceDialog$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IdleMovePresenter idleMovePresenter;
                Pdlog.m3273d(SettingActivity.this.TAG, "faceAnimationDialog OnClick ");
                idleMovePresenter = SettingActivity.this.getIdleMovePresenter();
                idleMovePresenter.actionTimerCount(true);
            }
        });
    }

    private final void hideFaceDialog() {
        Dialog dialog;
        FaceAnimationDialog faceAnimationDialog;
        Pdlog.m3273d(this.TAG, "hideFaceDialog ");
        FaceAnimationDialog faceAnimationDialog2 = this.faceAnimationDialog;
        if (faceAnimationDialog2 == null || (dialog = faceAnimationDialog2.getDialog()) == null || !dialog.isShowing() || (faceAnimationDialog = this.faceAnimationDialog) == null) {
            return;
        }
        faceAnimationDialog.dismissAllowingStateLoss();
    }

    private final void stopStandby() {
        Pdlog.m3273d(this.TAG, "stopStandby");
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        hideFaceDialog();
        PeripheralsSceneUtil.idleState$default(PeripheralsSceneUtil.INSTANCE, false, 1, null);
        resetScreenLight();
    }

    @Override // com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract.ViewInterface
    public void showIdleEvent(IdleMoveContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.currentIdleEvent = event;
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
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

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop ");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart ");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (!this.isBind || this.isUnBind) {
            return;
        }
        release();
    }
}
