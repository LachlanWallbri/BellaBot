package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.cruise_task.CruiseConfig;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.bean.ReturnPointBean;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.CruiseStayTtsVoiceAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.RadioBtnTvAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.custom_call.CustomCallActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.CustomCruiseCopyWriteDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.CustomCruiseTtsDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.FaceAnimationDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CustomCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MyStatusBarLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceIndicatorSeekBarChangeListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceItemLongClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import com.warkiz.widget.IndicatorSeekBar;
import java.util.ArrayList;
import java.util.Arrays;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: StayPointSettingsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u00103\u001a\u00020-H\u0002J\b\u00104\u001a\u00020-H\u0002J\b\u00105\u001a\u00020-H\u0002J\b\u00106\u001a\u00020-H\u0002J\b\u00107\u001a\u00020-H\u0002J\b\u00108\u001a\u00020'H\u0016J\u0010\u00109\u001a\u00020-2\u0006\u0010:\u001a\u00020;H\u0016J\u0012\u0010<\u001a\u00020-2\b\u0010=\u001a\u0004\u0018\u00010>H\u0014J\b\u0010?\u001a\u00020-H\u0014J\b\u0010@\u001a\u00020-H\u0014J\u0010\u0010A\u001a\u00020-2\u0006\u0010B\u001a\u00020'H\u0016J\b\u0010C\u001a\u00020-H\u0002J\b\u0010D\u001a\u00020-H\u0002J\b\u0010E\u001a\u00020-H\u0002J\u0010\u0010F\u001a\u00020-2\u0006\u0010G\u001a\u00020HH\u0016J\u0010\u0010I\u001a\u00020-2\u0006\u0010J\u001a\u00020'H\u0002J\b\u0010K\u001a\u00020-H\u0002J\u0010\u0010L\u001a\u00020-2\u0006\u0010M\u001a\u00020NH\u0002J\u0010\u0010O\u001a\u00020-2\u0006\u0010P\u001a\u00020\u000fH\u0016J\b\u0010Q\u001a\u00020-H\u0016J\u0010\u0010R\u001a\u00020-2\u0006\u0010S\u001a\u00020TH\u0016J\u0010\u0010U\u001a\u00020-2\u0006\u0010G\u001a\u00020VH\u0016J\b\u0010W\u001a\u00020-H\u0002J\b\u0010X\u001a\u00020-H\u0002J\b\u0010Y\u001a\u00020-H\u0002J\b\u0010Z\u001a\u00020-H\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\u0015\u001a\u0004\b#\u0010$R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010.\u001a\u0012\u0012\u0004\u0012\u00020\u00060/j\b\u0012\u0004\u0012\u00020\u0006`0X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006["}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/StayPointSettingsActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "cruiseReturnAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/radiobtntv/RadioBtnTvAdapter;", "cruiseTtsAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/CruiseStayTtsVoiceAdapter;", "currentIdleEvent", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewEvent;", "customCruiseCopyWriteDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/CustomCruiseCopyWriteDialog;", "getCustomCruiseCopyWriteDialog", "()Lcom/pudutech/bumblebee/robot_ui/ui/dialog/CustomCruiseCopyWriteDialog;", "customCruiseCopyWriteDialog$delegate", "Lkotlin/Lazy;", "customCruiseVoiceDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/CustomCruiseTtsDialog;", "getCustomCruiseVoiceDialog", "()Lcom/pudutech/bumblebee/robot_ui/ui/dialog/CustomCruiseTtsDialog;", "customCruiseVoiceDialog$delegate", "customVoiceType", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "faceAnimationDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/FaceAnimationDialog;", "faceAnimationView", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView;", "idleMovePresenter", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "getIdleMovePresenter", "()Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "idleMovePresenter$delegate", "isFirstStart", "", "isRelease", "isShowLowPowerDialog", "onCustomCallListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "", "playIntervalList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "bindPresenter", "hideFaceDialog", "initCruiseReturnRecyclerView", "initView", "initVoiceRecyclerView", "isBusyState", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "onWindowFocusChanged", "hasFocus", "release", "saveData", "setCruiseStayTtsAdapterData", "showChargerEvent", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showCruiseVoiceView", "show", "showCustomTTsDialog", "showFaceDialog", "animations", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "showIdleEvent", "event", "showLowerNotify", "showPowerChange", "i", "", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "showSleepAnimation", "showStandbyAnimation", "stopStandby", "unBindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class StayPointSettingsActivity extends MyBaseActivity implements BatteryContract2.ViewInterface, IdleMoveContract.ViewInterface {
    private HashMap _$_findViewCache;
    private RadioBtnTvAdapter cruiseReturnAdapter;
    private CruiseStayTtsVoiceAdapter cruiseTtsAdapter;
    private FaceAnimationDialog faceAnimationDialog;
    private FaceVideoView faceAnimationView;
    private boolean isRelease;
    private boolean isShowLowPowerDialog;

    /* renamed from: customCruiseVoiceDialog$delegate, reason: from kotlin metadata */
    private final Lazy customCruiseVoiceDialog = LazyKt.lazy(new Function0<CustomCruiseTtsDialog>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$customCruiseVoiceDialog$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CustomCruiseTtsDialog invoke() {
            return new CustomCruiseTtsDialog();
        }
    });

    /* renamed from: customCruiseCopyWriteDialog$delegate, reason: from kotlin metadata */
    private final Lazy customCruiseCopyWriteDialog = LazyKt.lazy(new Function0<CustomCruiseCopyWriteDialog>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$customCruiseCopyWriteDialog$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CustomCruiseCopyWriteDialog invoke() {
            return new CustomCruiseCopyWriteDialog();
        }
    });
    private String TAG = getClass().getSimpleName();
    private TtsVoiceHelper.TtsVoiceType customVoiceType = TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE;
    private final ArrayList<String> playIntervalList = CollectionsKt.arrayListOf("5s", "10s", "15s", "20s", "25s");
    private boolean isFirstStart = true;
    private IdleMoveContract.ViewEvent currentIdleEvent = IdleMoveContract.ViewEvent.READY;

    /* renamed from: idleMovePresenter$delegate, reason: from kotlin metadata */
    private final Lazy idleMovePresenter = LazyKt.lazy(new Function0<IdleMovePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$idleMovePresenter$2
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
    private final TouchSensorEventHelper touchSensorEventHelper = new TouchSensorEventHelper();
    private Function1<? super CustomCallTargetBean, Unit> onCustomCallListener = new Function1<CustomCallTargetBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$onCustomCallListener$1
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
            str = StayPointSettingsActivity.this.TAG;
            Pdlog.m3273d(str, "onCustomCallListener task: " + it);
            StayPointSettingsActivity.this.jumpAndFinish(CustomCallActivity.INSTANCE.createIntent(StayPointSettingsActivity.this, it));
        }
    };
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();

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
    public final CustomCruiseCopyWriteDialog getCustomCruiseCopyWriteDialog() {
        return (CustomCruiseCopyWriteDialog) this.customCruiseCopyWriteDialog.getValue();
    }

    private final CustomCruiseTtsDialog getCustomCruiseVoiceDialog() {
        return (CustomCruiseTtsDialog) this.customCruiseVoiceDialog.getValue();
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

    public static final /* synthetic */ RadioBtnTvAdapter access$getCruiseReturnAdapter$p(StayPointSettingsActivity stayPointSettingsActivity) {
        RadioBtnTvAdapter radioBtnTvAdapter = stayPointSettingsActivity.cruiseReturnAdapter;
        if (radioBtnTvAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseReturnAdapter");
        }
        return radioBtnTvAdapter;
    }

    public static final /* synthetic */ CruiseStayTtsVoiceAdapter access$getCruiseTtsAdapter$p(StayPointSettingsActivity stayPointSettingsActivity) {
        CruiseStayTtsVoiceAdapter cruiseStayTtsVoiceAdapter = stayPointSettingsActivity.cruiseTtsAdapter;
        if (cruiseStayTtsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseTtsAdapter");
        }
        return cruiseStayTtsVoiceAdapter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        NavigationBarUtil.hideNavigationBar(getWindow());
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_stay_point_settings);
        initView();
        bindPresenter();
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.isRelease) {
            return;
        }
        Pdlog.m3273d(this.TAG, "onResume start actionTimerCount");
        getIdleMovePresenter().actionTimerCount(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.isRelease) {
            Pdlog.m3273d(this.TAG, "onPause stop actionTimerCount");
            getIdleMovePresenter().actionTimerCount(false);
        }
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        touchSensorEventHelper.bindPresenter(faceVideoView);
        getIdleMovePresenter().replaceView(this);
        getIdleMovePresenter().actionTimerCount(true);
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this);
        showPowerEvent(BatteryInfoManager.INSTANCE.getPowerEvent());
        showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
        showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        if (BatteryInfoManager.INSTANCE.getNeedShowLowPowerNotify()) {
            showLowerNotify();
        }
        CustomCallHelper.INSTANCE.addCallListener(this.onCustomCallListener);
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
    }

    private final void initView() {
        ConstraintLayout tts_cl = (ConstraintLayout) _$_findCachedViewById(C4188R.id.tts_cl);
        Intrinsics.checkExpressionValueIsNotNull(tts_cl, "tts_cl");
        ViewExtKt.visibleOrGone(tts_cl, !LanguageUtils.INSTANCE.isNoSupportTts());
        LinearLayout back_ll = (LinearLayout) _$_findCachedViewById(C4188R.id.back_ll);
        Intrinsics.checkExpressionValueIsNotNull(back_ll, "back_ll");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(back_ll, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                StayPointSettingsActivity stayPointSettingsActivity = StayPointSettingsActivity.this;
                stayPointSettingsActivity.jumpAndFinish(new Intent(stayPointSettingsActivity, (Class<?>) CruiseSelectActivity.class));
            }
        }, 3, null);
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        this.faceAnimationView = face_animation_view;
        ImageView cruise_content_edit_iv = (ImageView) _$_findCachedViewById(C4188R.id.cruise_content_edit_iv);
        Intrinsics.checkExpressionValueIsNotNull(cruise_content_edit_iv, "cruise_content_edit_iv");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(cruise_content_edit_iv, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                CustomCruiseCopyWriteDialog customCruiseCopyWriteDialog;
                String TAG;
                Intrinsics.checkParameterIsNotNull(it, "it");
                customCruiseCopyWriteDialog = StayPointSettingsActivity.this.getCustomCruiseCopyWriteDialog();
                FragmentManager supportFragmentManager = StayPointSettingsActivity.this.getSupportFragmentManager();
                TAG = StayPointSettingsActivity.this.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                customCruiseCopyWriteDialog.showDialog(supportFragmentManager, TAG);
            }
        }, 3, null);
        TextView cruise_point_content_tv = (TextView) _$_findCachedViewById(C4188R.id.cruise_point_content_tv);
        Intrinsics.checkExpressionValueIsNotNull(cruise_point_content_tv, "cruise_point_content_tv");
        StayPointSettingsActivity stayPointSettingsActivity = this;
        String str = SpUtils.get(stayPointSettingsActivity, Constans.KEY_CRUISE_STAY_COPY_WRITE_TYPE, "");
        if (str.length() == 0) {
            str = getString(C4188R.string.cruise_point_content_edit_tip);
            Intrinsics.checkExpressionValueIsNotNull(str, "getString(R.string.cruise_point_content_edit_tip)");
        }
        cruise_point_content_tv.setText(str);
        getCustomCruiseCopyWriteDialog().setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initView$4
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                TextView cruise_point_content_tv2 = (TextView) StayPointSettingsActivity.this._$_findCachedViewById(C4188R.id.cruise_point_content_tv);
                Intrinsics.checkExpressionValueIsNotNull(cruise_point_content_tv2, "cruise_point_content_tv");
                String str2 = SpUtils.get(StayPointSettingsActivity.this, Constans.KEY_CRUISE_STAY_COPY_WRITE_TYPE, "");
                if (str2.length() == 0) {
                    str2 = StayPointSettingsActivity.this.getString(C4188R.string.cruise_point_content_edit_tip);
                    Intrinsics.checkExpressionValueIsNotNull(str2, "getString(R.string.cruise_point_content_edit_tip)");
                }
                cruise_point_content_tv2.setText(str2);
            }
        });
        EditText cruise_time_et = (EditText) _$_findCachedViewById(C4188R.id.cruise_time_et);
        Intrinsics.checkExpressionValueIsNotNull(cruise_time_et, "cruise_time_et");
        cruise_time_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initView$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str2;
                String str3;
                str2 = StayPointSettingsActivity.this.TAG;
                Pdlog.m3273d(str2, "cruise_time_et_1 it: " + ((Object) s));
                if (s != null) {
                    try {
                        if (s.length() > 0) {
                            long j = 600;
                            if (Long.parseLong(s.toString()) > j) {
                                ((EditText) StayPointSettingsActivity.this._$_findCachedViewById(C4188R.id.cruise_time_et)).setText("600");
                            }
                            long j2 = 5;
                            long parseLong = Long.parseLong(s.toString());
                            if (j2 <= parseLong && j >= parseLong) {
                                TextView cruise_time_title_tip_tv = (TextView) StayPointSettingsActivity.this._$_findCachedViewById(C4188R.id.cruise_time_title_tip_tv);
                                Intrinsics.checkExpressionValueIsNotNull(cruise_time_title_tip_tv, "cruise_time_title_tip_tv");
                                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                String string = StayPointSettingsActivity.this.getString(C4188R.string.call_cruise_info);
                                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.call_cruise_info)");
                                Object[] objArr = {Long.valueOf(Long.parseLong(s.toString()))};
                                String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                                cruise_time_title_tip_tv.setText(format);
                            }
                        }
                    } catch (Exception e) {
                        str3 = StayPointSettingsActivity.this.TAG;
                        Pdlog.m3274e(str3, "escorting_finish_time_et : " + Log.getStackTraceString(e));
                    }
                }
            }
        });
        ((EditText) _$_findCachedViewById(C4188R.id.cruise_time_et)).setText(String.valueOf(Constans.INSTANCE.getCustomCruiseTime()));
        TextView cruise_time_title_tip_tv = (TextView) _$_findCachedViewById(C4188R.id.cruise_time_title_tip_tv);
        Intrinsics.checkExpressionValueIsNotNull(cruise_time_title_tip_tv, "cruise_time_title_tip_tv");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(C4188R.string.call_cruise_info);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.call_cruise_info)");
        Object[] objArr = {Long.valueOf(Constans.INSTANCE.getCustomCruiseTime())};
        String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        cruise_time_title_tip_tv.setText(format);
        Switch cruise_time_switch = (Switch) _$_findCachedViewById(C4188R.id.cruise_time_switch);
        Intrinsics.checkExpressionValueIsNotNull(cruise_time_switch, "cruise_time_switch");
        cruise_time_switch.setChecked(Constans.INSTANCE.getCruiseReturnSwitch());
        ((Switch) _$_findCachedViewById(C4188R.id.cruise_time_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initView$6
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton compoundButton, boolean z) {
                Intrinsics.checkParameterIsNotNull(compoundButton, "<anonymous parameter 0>");
                Constans.INSTANCE.setCruiseReturnSwitch(z);
            }
        }, 7, null));
        ImageView add_iv = (ImageView) _$_findCachedViewById(C4188R.id.add_iv);
        Intrinsics.checkExpressionValueIsNotNull(add_iv, "add_iv");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(add_iv, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initView$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).getData().size() < 10) {
                    StayPointSettingsActivity.this.showCustomTTsDialog();
                } else {
                    StayPointSettingsActivity stayPointSettingsActivity2 = StayPointSettingsActivity.this;
                    ToastUtils.show(stayPointSettingsActivity2, stayPointSettingsActivity2.getString(C4188R.string.custom_voice_most_10), new Object[0]);
                }
            }
        }, 3, null);
        boolean z = TtsVoiceHelper.INSTANCE.checkTtsOpenType(stayPointSettingsActivity, this.customVoiceType) == TtsVoiceHelper.TtsVoiceOpenType.OPEN;
        Switch cruise_voice_switch = (Switch) _$_findCachedViewById(C4188R.id.cruise_voice_switch);
        Intrinsics.checkExpressionValueIsNotNull(cruise_voice_switch, "cruise_voice_switch");
        cruise_voice_switch.setChecked(z);
        ((Switch) _$_findCachedViewById(C4188R.id.cruise_voice_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initView$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton compoundButton, boolean z2) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                Intrinsics.checkParameterIsNotNull(compoundButton, "<anonymous parameter 0>");
                TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                StayPointSettingsActivity stayPointSettingsActivity2 = StayPointSettingsActivity.this;
                TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType = z2 ? TtsVoiceHelper.TtsVoiceOpenType.OPEN : TtsVoiceHelper.TtsVoiceOpenType.CLOSE;
                ttsVoiceType = StayPointSettingsActivity.this.customVoiceType;
                ttsVoiceHelper.setTtsType(stayPointSettingsActivity2, ttsVoiceOpenType, ttsVoiceType);
                StayPointSettingsActivity.this.showCruiseVoiceView(z2);
                StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).setEnable(z2);
            }
        }, 7, null));
        initVoiceRecyclerView();
        setCruiseStayTtsAdapterData();
        Switch cruise_voice_switch2 = (Switch) _$_findCachedViewById(C4188R.id.cruise_voice_switch);
        Intrinsics.checkExpressionValueIsNotNull(cruise_voice_switch2, "cruise_voice_switch");
        showCruiseVoiceView(cruise_voice_switch2.isChecked());
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_voice_time_isb);
        indicatorSeekBar.setTickCount(this.playIntervalList.size());
        Object[] array = this.playIntervalList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            indicatorSeekBar.setIndicatorTextFormat("${TICK_TEXT}");
            int timeInterval = TtsVoiceHelper.INSTANCE.getTimeInterval(TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE);
            ArrayList<String> arrayList = this.playIntervalList;
            StringBuilder sb = new StringBuilder();
            sb.append(timeInterval);
            sb.append('s');
            indicatorSeekBar.setProgress((arrayList.indexOf(sb.toString()) * 100.0f) / (this.playIntervalList.size() - 1));
            indicatorSeekBar.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new Function1<IndicatorSeekBar, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initView$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(IndicatorSeekBar indicatorSeekBar2) {
                    invoke2(indicatorSeekBar2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IndicatorSeekBar indicatorSeekBar2) {
                    ArrayList arrayList2;
                    ArrayList arrayList3;
                    if (indicatorSeekBar2 != null) {
                        float progressFloat = indicatorSeekBar2.getProgressFloat() / 100.0f;
                        arrayList2 = StayPointSettingsActivity.this.playIntervalList;
                        int rint = (int) Math.rint(progressFloat * (arrayList2.size() - 1));
                        arrayList3 = StayPointSettingsActivity.this.playIntervalList;
                        Object obj = arrayList3.get(rint);
                        Intrinsics.checkExpressionValueIsNotNull(obj, "playIntervalList[index]");
                        String str2 = (String) obj;
                        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                        int length = str2.length() - 1;
                        if (str2 != null) {
                            String substring = str2.substring(0, length);
                            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            ttsVoiceHelper.setTimeInterval(substring, TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE);
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                }
            }, 15, null));
            ((EditText) _$_findCachedViewById(C4188R.id.cruise_point_stay_time_et)).setText(String.valueOf(CruiseConfig.INSTANCE.getDelayAutoFinish_ms() / 1000));
            EditText cruise_point_stay_time_et = (EditText) _$_findCachedViewById(C4188R.id.cruise_point_stay_time_et);
            Intrinsics.checkExpressionValueIsNotNull(cruise_point_stay_time_et, "cruise_point_stay_time_et");
            cruise_point_stay_time_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initView$$inlined$doAfterTextChanged$2
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                    String str2;
                    String str3;
                    str2 = StayPointSettingsActivity.this.TAG;
                    Pdlog.m3273d(str2, "cruise_point_stay_time_et it: " + ((Object) s));
                    if (s != null) {
                        try {
                            if (!(s.length() > 0) || Long.parseLong(s.toString()) <= 600) {
                                return;
                            }
                            ((EditText) StayPointSettingsActivity.this._$_findCachedViewById(C4188R.id.cruise_time_et)).setText("600");
                        } catch (Exception e) {
                            str3 = StayPointSettingsActivity.this.TAG;
                            Pdlog.m3274e(str3, "cruise_point_stay_time_et : " + Log.getStackTraceString(e));
                        }
                    }
                }
            });
            initCruiseReturnRecyclerView();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCruiseVoiceView(boolean show) {
        CruiseStayTtsVoiceAdapter cruiseStayTtsVoiceAdapter = this.cruiseTtsAdapter;
        if (cruiseStayTtsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseTtsAdapter");
        }
        List<TtsVoiceHelper.TtsConfigData> data = cruiseStayTtsVoiceAdapter != null ? cruiseStayTtsVoiceAdapter.getData() : null;
        if (data == null || data.isEmpty()) {
            TextView no_cruise_voice_tv = (TextView) _$_findCachedViewById(C4188R.id.no_cruise_voice_tv);
            Intrinsics.checkExpressionValueIsNotNull(no_cruise_voice_tv, "no_cruise_voice_tv");
            ViewExtKt.visibleOrGone(no_cruise_voice_tv, show);
            RecyclerView cruise_voice_list_rv = (RecyclerView) _$_findCachedViewById(C4188R.id.cruise_voice_list_rv);
            Intrinsics.checkExpressionValueIsNotNull(cruise_voice_list_rv, "cruise_voice_list_rv");
            ViewExtKt.gone(cruise_voice_list_rv);
            ImageView cruise_time_iv = (ImageView) _$_findCachedViewById(C4188R.id.cruise_time_iv);
            Intrinsics.checkExpressionValueIsNotNull(cruise_time_iv, "cruise_time_iv");
            ViewExtKt.gone(cruise_time_iv);
            IndicatorSeekBar cruise_voice_time_isb = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_voice_time_isb);
            Intrinsics.checkExpressionValueIsNotNull(cruise_voice_time_isb, "cruise_voice_time_isb");
            ViewExtKt.gone(cruise_voice_time_isb);
            View line_view_3 = _$_findCachedViewById(C4188R.id.line_view_3);
            Intrinsics.checkExpressionValueIsNotNull(line_view_3, "line_view_3");
            ViewExtKt.gone(line_view_3);
        } else {
            RecyclerView cruise_voice_list_rv2 = (RecyclerView) _$_findCachedViewById(C4188R.id.cruise_voice_list_rv);
            Intrinsics.checkExpressionValueIsNotNull(cruise_voice_list_rv2, "cruise_voice_list_rv");
            ViewExtKt.visibleOrGone(cruise_voice_list_rv2, show);
            ImageView cruise_time_iv2 = (ImageView) _$_findCachedViewById(C4188R.id.cruise_time_iv);
            Intrinsics.checkExpressionValueIsNotNull(cruise_time_iv2, "cruise_time_iv");
            ViewExtKt.visibleOrGone(cruise_time_iv2, show);
            IndicatorSeekBar cruise_voice_time_isb2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_voice_time_isb);
            Intrinsics.checkExpressionValueIsNotNull(cruise_voice_time_isb2, "cruise_voice_time_isb");
            ViewExtKt.visibleOrGone(cruise_voice_time_isb2, show);
            View line_view_32 = _$_findCachedViewById(C4188R.id.line_view_3);
            Intrinsics.checkExpressionValueIsNotNull(line_view_32, "line_view_3");
            ViewExtKt.visibleOrGone(line_view_32, show);
            TextView no_cruise_voice_tv2 = (TextView) _$_findCachedViewById(C4188R.id.no_cruise_voice_tv);
            Intrinsics.checkExpressionValueIsNotNull(no_cruise_voice_tv2, "no_cruise_voice_tv");
            ViewExtKt.gone(no_cruise_voice_tv2);
        }
        ImageView add_iv = (ImageView) _$_findCachedViewById(C4188R.id.add_iv);
        Intrinsics.checkExpressionValueIsNotNull(add_iv, "add_iv");
        ViewExtKt.visibleOrGone(add_iv, show);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
        }
        TextView cruise_point_content_tv = (TextView) _$_findCachedViewById(C4188R.id.cruise_point_content_tv);
        Intrinsics.checkExpressionValueIsNotNull(cruise_point_content_tv, "cruise_point_content_tv");
        String str = SpUtils.get(this, Constans.KEY_CRUISE_STAY_COPY_WRITE_TYPE, "");
        if (str.length() == 0) {
            str = getString(C4188R.string.cruise_point_content_edit_tip);
            Intrinsics.checkExpressionValueIsNotNull(str, "getString(R.string.cruise_point_content_edit_tip)");
        }
        cruise_point_content_tv.setText(str);
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

    private final void initVoiceRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C4188R.id.cruise_voice_list_rv);
        StayPointSettingsActivity stayPointSettingsActivity = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(stayPointSettingsActivity));
        recyclerView.setHasFixedSize(true);
        final CruiseStayTtsVoiceAdapter cruiseStayTtsVoiceAdapter = new CruiseStayTtsVoiceAdapter(stayPointSettingsActivity);
        cruiseStayTtsVoiceAdapter.setOnItemLongClickListener(new VoiceItemLongClickListener(null, 0, new Function3<BaseQuickAdapter<?, ?>, View, Integer, Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initVoiceRecyclerView$1$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Boolean invoke(BaseQuickAdapter<?, ?> baseQuickAdapter, View view, Integer num) {
                return Boolean.valueOf(invoke(baseQuickAdapter, view, num.intValue()));
            }

            public final boolean invoke(BaseQuickAdapter<?, ?> baseQuickAdapter, View view, int i) {
                if (view == null) {
                    return true;
                }
                CruiseStayTtsVoiceAdapter.this.switchDeleteView(view);
                return true;
            }
        }, 3, null));
        cruiseStayTtsVoiceAdapter.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initVoiceRecyclerView$1$1$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                CruiseStayTtsVoiceAdapter.this.onItemClicked(view);
            }
        });
        this.cruiseTtsAdapter = cruiseStayTtsVoiceAdapter;
        CruiseStayTtsVoiceAdapter cruiseStayTtsVoiceAdapter2 = this.cruiseTtsAdapter;
        if (cruiseStayTtsVoiceAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseTtsAdapter");
        }
        cruiseStayTtsVoiceAdapter2.setDeleteViewListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initVoiceRecyclerView$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                StayPointSettingsActivity.this.showCruiseVoiceView(true);
            }
        });
        CruiseStayTtsVoiceAdapter cruiseStayTtsVoiceAdapter3 = this.cruiseTtsAdapter;
        if (cruiseStayTtsVoiceAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseTtsAdapter");
        }
        recyclerView.setAdapter(cruiseStayTtsVoiceAdapter3);
        CruiseStayTtsVoiceAdapter cruiseStayTtsVoiceAdapter4 = this.cruiseTtsAdapter;
        if (cruiseStayTtsVoiceAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseTtsAdapter");
        }
        Switch cruise_voice_switch = (Switch) _$_findCachedViewById(C4188R.id.cruise_voice_switch);
        Intrinsics.checkExpressionValueIsNotNull(cruise_voice_switch, "cruise_voice_switch");
        cruiseStayTtsVoiceAdapter4.setEnable(cruise_voice_switch.isChecked());
        CruiseStayTtsVoiceAdapter cruiseStayTtsVoiceAdapter5 = this.cruiseTtsAdapter;
        if (cruiseStayTtsVoiceAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseTtsAdapter");
        }
        cruiseStayTtsVoiceAdapter5.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initVoiceRecyclerView$$inlined$apply$lambda$2
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, View view, int i) {
                Intrinsics.checkExpressionValueIsNotNull(view, "view");
                if (view.getId() == C4188R.id.try_play_tv) {
                    TtsVoiceHelper.INSTANCE.stopCruiseTts();
                    if (StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).getData().get(i).isPlay()) {
                        List<TtsVoiceHelper.TtsConfigData> data = StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).getData();
                        Intrinsics.checkExpressionValueIsNotNull(data, "cruiseTtsAdapter.data");
                        Iterator<T> it = data.iterator();
                        while (it.hasNext()) {
                            ((TtsVoiceHelper.TtsConfigData) it.next()).setPlay(false);
                        }
                        StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).notifyDataSetChanged();
                        return;
                    }
                    List<TtsVoiceHelper.TtsConfigData> data2 = StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).getData();
                    Intrinsics.checkExpressionValueIsNotNull(data2, "cruiseTtsAdapter.data");
                    Iterator<T> it2 = data2.iterator();
                    while (it2.hasNext()) {
                        ((TtsVoiceHelper.TtsConfigData) it2.next()).setPlay(false);
                    }
                    StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).getData().get(i).setPlay(true);
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    TtsVoiceHelper.TtsConfigData ttsConfigData = StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).getData().get(i);
                    Intrinsics.checkExpressionValueIsNotNull(ttsConfigData, "cruiseTtsAdapter.data[position]");
                    ttsVoiceHelper.playPcm(ttsConfigData, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initVoiceRecyclerView$$inlined$apply$lambda$2.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                            invoke2(audioPlayEvent);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(AudioTrackUtils.AudioPlayEvent it3) {
                            Intrinsics.checkParameterIsNotNull(it3, "it");
                            if (it3 == AudioTrackUtils.AudioPlayEvent.COMPLETE) {
                                List<TtsVoiceHelper.TtsConfigData> data3 = StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).getData();
                                Intrinsics.checkExpressionValueIsNotNull(data3, "cruiseTtsAdapter.data");
                                Iterator<T> it4 = data3.iterator();
                                while (it4.hasNext()) {
                                    ((TtsVoiceHelper.TtsConfigData) it4.next()).setPlay(false);
                                }
                                StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).notifyDataSetChanged();
                            }
                        }
                    });
                    StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).notifyDataSetChanged();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCruiseStayTtsAdapterData() {
        ArrayList<TtsVoiceHelper.TtsConfigData> ttsConfigList = TtsVoiceHelper.INSTANCE.getTtsConfigList(this.customVoiceType);
        TtsVoiceHelper.INSTANCE.setOnChangeListener(new Function1<TtsVoiceHelper.TtsVoiceType, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$setCruiseStayTtsAdapterData$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
                invoke2(ttsVoiceType);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TtsVoiceHelper.TtsVoiceType it) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                Intrinsics.checkParameterIsNotNull(it, "it");
                TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                ttsVoiceType = StayPointSettingsActivity.this.customVoiceType;
                StayPointSettingsActivity.access$getCruiseTtsAdapter$p(StayPointSettingsActivity.this).setNewData(ttsVoiceHelper.getTtsConfigList(ttsVoiceType));
            }
        });
        CruiseStayTtsVoiceAdapter cruiseStayTtsVoiceAdapter = this.cruiseTtsAdapter;
        if (cruiseStayTtsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseTtsAdapter");
        }
        cruiseStayTtsVoiceAdapter.setNewData(ttsConfigList);
        Switch cruise_voice_switch = (Switch) _$_findCachedViewById(C4188R.id.cruise_voice_switch);
        Intrinsics.checkExpressionValueIsNotNull(cruise_voice_switch, "cruise_voice_switch");
        showCruiseVoiceView(cruise_voice_switch.isChecked());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCustomTTsDialog() {
        CustomCruiseTtsDialog customCruiseVoiceDialog = getCustomCruiseVoiceDialog();
        customCruiseVoiceDialog.setOnTtsAddSuccessListener(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$showCustomTTsDialog$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                StayPointSettingsActivity.this.setCruiseStayTtsAdapterData();
            }
        });
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        customCruiseVoiceDialog.showDialog(supportFragmentManager, TAG);
    }

    private final void initCruiseReturnRecyclerView() {
        ReturnPointBean returnPointBean;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C4188R.id.cruise_return_point_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        this.cruiseReturnAdapter = new RadioBtnTvAdapter();
        RadioBtnTvAdapter radioBtnTvAdapter = this.cruiseReturnAdapter;
        if (radioBtnTvAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseReturnAdapter");
        }
        recyclerView.setAdapter(radioBtnTvAdapter);
        String defaultMapName = RobotMapManager.INSTANCE.getDefaultMapName();
        String currentMapDiningOutLetChosen = RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen();
        String currentMapUsherChosen = RobotMapManager.INSTANCE.getCurrentMapUsherChosen();
        final ArrayList arrayList = new ArrayList();
        ReturnPointBean returnPointBean2 = (ReturnPointBean) null;
        boolean z = false;
        if (Constans.INSTANCE.getCruiseReturnDestination().length() > 0) {
            try {
                returnPointBean2 = (ReturnPointBean) GsonSingleton.INSTANCE.getINSTANCE().getMGson().fromJson(Constans.INSTANCE.getCruiseReturnDestination(), new TypeToken<ReturnPointBean>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initCruiseReturnRecyclerView$1$1
                }.getType());
            } catch (Exception e) {
                Pdlog.m3274e(this.TAG, "initCruiseReturnRecyclerView oldReturnPoint from json: " + e);
            }
        }
        String str = currentMapDiningOutLetChosen;
        if (!(str == null || str.length() == 0)) {
            arrayList.add(new ReturnPointBean(defaultMapName != null ? defaultMapName : "", currentMapDiningOutLetChosen, returnPointBean2 == null || (Intrinsics.areEqual(returnPointBean2.getMapName(), defaultMapName) && Intrinsics.areEqual(returnPointBean2.getPointName(), currentMapDiningOutLetChosen))));
        }
        String str2 = currentMapUsherChosen;
        if (!(str2 == null || str2.length() == 0)) {
            String str3 = defaultMapName != null ? defaultMapName : "";
            if (returnPointBean2 != null && Intrinsics.areEqual(returnPointBean2.getMapName(), defaultMapName) && Intrinsics.areEqual(returnPointBean2.getPointName(), currentMapUsherChosen)) {
                z = true;
            }
            arrayList.add(new ReturnPointBean(str3, currentMapUsherChosen, z));
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : arrayList2) {
            if (((ReturnPointBean) obj).getSelect()) {
                arrayList3.add(obj);
            }
        }
        if (arrayList3.isEmpty() && (returnPointBean = (ReturnPointBean) CollectionsKt.firstOrNull((List) arrayList)) != null) {
            returnPointBean.setSelect(true);
            Constans constans = Constans.INSTANCE;
            String json = GsonSingleton.INSTANCE.getINSTANCE().getMGson().toJson(returnPointBean);
            Intrinsics.checkExpressionValueIsNotNull(json, "GsonSingleton.INSTANCE.getGson().toJson(this)");
            constans.setCruiseReturnDestination(json);
        }
        RadioBtnTvAdapter radioBtnTvAdapter2 = this.cruiseReturnAdapter;
        if (radioBtnTvAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseReturnAdapter");
        }
        radioBtnTvAdapter2.setNewData(CollectionsKt.toList(arrayList2));
        RadioBtnTvAdapter radioBtnTvAdapter3 = this.cruiseReturnAdapter;
        if (radioBtnTvAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cruiseReturnAdapter");
        }
        OnLazyItemClickListenerKt.onSingleItemClick(radioBtnTvAdapter3, new Function3<BaseQuickAdapter<?, ?>, View, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$initCruiseReturnRecyclerView$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(BaseQuickAdapter<?, ?> baseQuickAdapter, View view, Integer num) {
                invoke(baseQuickAdapter, view, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(BaseQuickAdapter<?, ?> baseQuickAdapter, View view, int i) {
                Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "<anonymous parameter 0>");
                Intrinsics.checkParameterIsNotNull(view, "<anonymous parameter 1>");
                Constans constans2 = Constans.INSTANCE;
                String json2 = GsonSingleton.INSTANCE.getINSTANCE().getMGson().toJson(arrayList.get(i));
                Intrinsics.checkExpressionValueIsNotNull(json2, "GsonSingleton.INSTANCE.g…uiseReturnList[position])");
                constans2.setCruiseReturnDestination(json2);
                int i2 = 0;
                for (Object obj2 : arrayList) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    ((ReturnPointBean) obj2).setSelect(i2 == i);
                    i2 = i3;
                }
                StayPointSettingsActivity.access$getCruiseReturnAdapter$p(this).setNewData(CollectionsKt.toList(arrayList));
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        saveData();
        release();
        startActivity(intent);
        finish();
    }

    private final void release() {
        this.isRelease = true;
        unBindPresenter();
        TtsVoiceHelper.INSTANCE.setOnChangeListener((Function1) null);
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
        Iterator<T> it = TtsVoiceHelper.INSTANCE.getTtsConfigList(this.customVoiceType).iterator();
        while (it.hasNext()) {
            ((TtsVoiceHelper.TtsConfigData) it.next()).setPlay(false);
        }
    }

    private final void saveData() {
        String obj;
        String obj2;
        Constans constans = Constans.INSTANCE;
        EditText cruise_time_et = (EditText) _$_findCachedViewById(C4188R.id.cruise_time_et);
        Intrinsics.checkExpressionValueIsNotNull(cruise_time_et, "cruise_time_et");
        Editable text = cruise_time_et.getText();
        long j = 30;
        if (text != null && (obj2 = text.toString()) != null) {
            if (!(obj2.length() == 0) && Long.parseLong(obj2) >= 5) {
                j = Long.parseLong(obj2) > ((long) 600) ? 600L : Long.parseLong(obj2);
            }
        }
        constans.setCustomCruiseTime(j);
        CruiseConfig cruiseConfig = CruiseConfig.INSTANCE;
        EditText cruise_point_stay_time_et = (EditText) _$_findCachedViewById(C4188R.id.cruise_point_stay_time_et);
        Intrinsics.checkExpressionValueIsNotNull(cruise_point_stay_time_et, "cruise_point_stay_time_et");
        Editable text2 = cruise_point_stay_time_et.getText();
        long j2 = 20000;
        if (text2 != null && (obj = text2.toString()) != null) {
            if (!(obj.length() == 0) && Long.parseLong(obj) >= 0) {
                j2 = Long.parseLong(obj) > ((long) 600) ? 600000L : Long.parseLong(obj) * 1000;
            }
        }
        cruiseConfig.setDelayAutoFinish_ms(j2);
    }

    private final void unBindPresenter() {
        getIdleMovePresenter().actionTimerCount(false);
        getIdleMovePresenter().removeView(this);
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this);
        this.touchSensorEventHelper.unBindPresent();
        CustomCallHelper.INSTANCE.removeCallListener(this.onCustomCallListener);
        this.beeperCallHelper.unbind();
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

    private final void showSleepAnimation() {
        Pdlog.m3273d(this.TAG, "showSleepAnimation");
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getSleep());
        PeripheralsSceneUtil.INSTANCE.sleep();
        setScreenDark();
    }

    private final void showStandbyAnimation() {
        Pdlog.m3273d(this.TAG, "showStandbyAnimation");
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getStandby());
        PeripheralsSceneUtil.INSTANCE.standby();
        setScreenDark();
    }

    private final void showFaceDialog(FaceVideoAnimation animations) {
        Pdlog.m3273d(this.TAG, "showFaceDialog ");
        if (this.faceAnimationDialog == null) {
            this.faceAnimationDialog = new FaceAnimationDialog();
        }
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
        faceAnimationDialog3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointSettingsActivity$showFaceDialog$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                IdleMovePresenter idleMovePresenter;
                str = StayPointSettingsActivity.this.TAG;
                Pdlog.m3273d(str, "faceAnimationDialog OnClick ");
                idleMovePresenter = StayPointSettingsActivity.this.getIdleMovePresenter();
                idleMovePresenter.actionTimerCount(true);
            }
        });
    }

    private final void hideFaceDialog() {
        Pdlog.m3273d(this.TAG, "hideFaceDialog ");
        FaceAnimationDialog faceAnimationDialog = this.faceAnimationDialog;
        if (faceAnimationDialog != null) {
            faceAnimationDialog.dismissAllowingStateLoss();
        }
    }

    private final void stopStandby() {
        Pdlog.m3273d(this.TAG, "stopStandby");
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.stopPlay();
        PeripheralsSceneUtil.idleState$default(PeripheralsSceneUtil.INSTANCE, false, 1, null);
        resetScreenLight();
    }

    @Override // com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract.ViewInterface
    public void showIdleEvent(IdleMoveContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(this.TAG, "showIdleEvent event:" + event);
        this.currentIdleEvent = event;
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
}
