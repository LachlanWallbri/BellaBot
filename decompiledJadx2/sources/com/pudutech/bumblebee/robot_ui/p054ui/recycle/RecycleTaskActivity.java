package com.pudutech.bumblebee.robot_ui.p054ui.recycle;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryTaskSettingModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.manager.TableTaskManager;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.SmartInputTableFragment;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.RecycleTaskAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.RecycleTaskItem;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.HomeSettingDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.RecycleTaskDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment;
import com.pudutech.bumblebee.robot_ui.p054ui.fragment.SelectTableFragment;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MyStatusBarLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.track.task.PeripheralsTrack;
import com.pudutech.bumblebee.robot_ui.ui_helpers.SceneRecord;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.FragmentUtils;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.viewmodel.RecyclerTaskViewModel;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.disinfect.baselib.util.KeyboardUtils;
import com.pudutech.disinfect.baselib.util.SizeUtils;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
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
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: RecycleTaskActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n*\u0002\f\u001b\u0018\u0000 S2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001SB\u0005¢\u0006\u0002\u0010\u0005J\b\u0010.\u001a\u00020%H\u0002J\b\u0010/\u001a\u00020%H\u0016J\b\u00100\u001a\u000201H\u0002J\b\u00102\u001a\u00020%H\u0002J\u0012\u00103\u001a\u00020%2\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u0010\u00106\u001a\u00020%2\u0006\u00107\u001a\u000208H\u0016J\b\u00109\u001a\u00020\u0019H\u0016J\b\u0010:\u001a\u00020%H\u0014J\b\u0010;\u001a\u00020%H\u0014J\b\u0010<\u001a\u00020%H\u0002J\b\u0010=\u001a\u00020%H\u0002J\u0010\u0010>\u001a\u00020%2\u0006\u0010?\u001a\u00020@H\u0016J\u0010\u0010A\u001a\u00020%2\u0006\u0010B\u001a\u00020CH\u0002J\u0010\u0010D\u001a\u00020%2\u0006\u0010$\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020%H\u0016J\u0010\u0010G\u001a\u00020%2\u0006\u0010H\u001a\u00020\u0019H\u0016J\u0010\u0010I\u001a\u00020%2\u0006\u0010?\u001a\u00020JH\u0016J\b\u0010K\u001a\u00020%H\u0002J\b\u0010L\u001a\u00020%H\u0002J\b\u0010M\u001a\u00020%H\u0002J\b\u0010N\u001a\u00020%H\u0002J\b\u0010O\u001a\u00020%H\u0002J\b\u0010P\u001a\u00020%H\u0002J\b\u0010Q\u001a\u00020%H\u0002J\b\u0010R\u001a\u00020%H\u0002R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001cR>\u0010\u001d\u001a2\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110#¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b($\u0012\u0004\u0012\u00020%0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006T"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/recycle/RecycleTaskActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseVmActivity;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/RecyclerTaskViewModel;", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "faceAnimationView", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView;", "functionButton", "com/pudutech/bumblebee/robot_ui/ui/recycle/RecycleTaskActivity$functionButton$1", "Lcom/pudutech/bumblebee/robot_ui/ui/recycle/RecycleTaskActivity$functionButton$1;", "homeSettingDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/HomeSettingDialog;", "idleMovePresenter", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "getIdleMovePresenter", "()Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "idleMovePresenter$delegate", "Lkotlin/Lazy;", "jumpClickListener", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleVoiceClickListener;", "mode", "", "onHomeSettingDialogClickListener", "com/pudutech/bumblebee/robot_ui/ui/recycle/RecycleTaskActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/recycle/RecycleTaskActivity$onHomeSettingDialogClickListener$1;", "onTouchSensorPlaceListener", "Lkotlin/Function2;", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "Lkotlin/ParameterName;", "name", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "", "selectTableFragment", "Lcom/pudutech/bumblebee/robot_ui/ui/fragment/ISelectTableFragment;", "taskAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTaskAdapter;", "tipsDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "bindPresenter", "createObserver", "getMovePerformance", "Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;", "initTaskList", "initView", "saveInstanceState", "Landroid/os/Bundle;", "jumpAndFinish", "intent", "Landroid/content/Intent;", "layoutId", "onPause", "onResume", "prepareMusic", "setSelectTableFragment", "showChargerEvent", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showFaceDialog", "animations", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "showIdleEvent", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewEvent;", "showLowerNotify", "showPowerChange", "i", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "showSettingDialog", "showSleepAnimation", "showStandbyAnimation", "showTableLayout", "startGo", "stopStandby", "unbindPresenter", "updateTaskSize", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RecycleTaskActivity extends MyBaseVmActivity<RecyclerTaskViewModel> implements IdleMoveContract.ViewInterface, BatteryContract2.ViewInterface {
    public static final String MODE_TYPE = "MODE_TYPE";
    public static final int MODIFY_MODE = 1;
    public static final int NEW_MODE = 0;
    private HashMap _$_findViewCache;
    private FaceVideoView faceAnimationView;
    private HomeSettingDialog homeSettingDialog;
    private int mode;
    private ISelectTableFragment selectTableFragment;
    private RecycleTaskAdapter taskAdapter;
    private ShowTipMsgDialog tipsDialog;
    private final String TAG = getClass().getSimpleName();
    private final TouchSensorEventHelper touchSensorEventHelper = new TouchSensorEventHelper();

    /* renamed from: idleMovePresenter$delegate, reason: from kotlin metadata */
    private final Lazy idleMovePresenter = LazyKt.lazy(new Function0<IdleMovePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleTaskActivity$idleMovePresenter$2
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
    private final Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit> onTouchSensorPlaceListener = new Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleTaskActivity$onTouchSensorPlaceListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(TouchSensorContract.Place place, TouchSensorContract.Event event) {
            invoke2(place, event);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(TouchSensorContract.Place place, TouchSensorContract.Event event) {
            Intrinsics.checkParameterIsNotNull(place, "place");
            Intrinsics.checkParameterIsNotNull(event, "event");
            PeripheralsTrack peripheralsTrack = PeripheralsTrack.INSTANCE;
            int ordinal = place.ordinal();
            int value = event.getValue();
            String simpleName = RecycleTaskActivity.this.getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@RecycleTaskActivity::class.java.simpleName");
            PeripheralsTrack.onTouchPeripherals$default(peripheralsTrack, ordinal, value, simpleName, null, 8, null);
        }
    };
    private final RecycleTaskActivity$functionButton$1 functionButton = new RecycleTaskActivity$functionButton$1(this);
    private final SingleVoiceClickListener jumpClickListener = new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleTaskActivity$jumpClickListener$1
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
            int id = it.getId();
            if (id == C4188R.id.setting_btn) {
                RecycleTaskActivity recycleTaskActivity = RecycleTaskActivity.this;
                recycleTaskActivity.jump(new Intent(recycleTaskActivity, (Class<?>) RecycleSettingActivity.class));
            } else if (id == C4188R.id.btn_start) {
                RecycleTaskActivity.this.startGo();
            }
        }
    }, 3, null);
    private final RecycleTaskActivity$onHomeSettingDialogClickListener$1 onHomeSettingDialogClickListener = new RecycleTaskActivity$onHomeSettingDialogClickListener$1(this);

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[IdleMoveContract.ViewEvent.values().length];

        static {
            $EnumSwitchMapping$0[IdleMoveContract.ViewEvent.READY.ordinal()] = 1;
            $EnumSwitchMapping$0[IdleMoveContract.ViewEvent.STAND_BY.ordinal()] = 2;
            $EnumSwitchMapping$0[IdleMoveContract.ViewEvent.SLEEP.ordinal()] = 3;
        }
    }

    private final IdleMovePresenter getIdleMovePresenter() {
        return (IdleMovePresenter) this.idleMovePresenter.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
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

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showLowerNotify() {
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
    }

    public static final /* synthetic */ FaceVideoView access$getFaceAnimationView$p(RecycleTaskActivity recycleTaskActivity) {
        FaceVideoView faceVideoView = recycleTaskActivity.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        return faceVideoView;
    }

    public static final /* synthetic */ RecycleTaskAdapter access$getTaskAdapter$p(RecycleTaskActivity recycleTaskActivity) {
        RecycleTaskAdapter recycleTaskAdapter = recycleTaskActivity.taskAdapter;
        if (recycleTaskAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("taskAdapter");
        }
        return recycleTaskAdapter;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C4188R.layout.activity_recycler_task;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        this.faceAnimationView = face_animation_view;
        this.mode = getIntent().getIntExtra("MODE_TYPE", 0);
        if (this.mode == 1) {
            ImageView setting_info = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
            Intrinsics.checkExpressionValueIsNotNull(setting_info, "setting_info");
            setting_info.setVisibility(8);
            TextView btn_start = (TextView) _$_findCachedViewById(C4188R.id.btn_start);
            Intrinsics.checkExpressionValueIsNotNull(btn_start, "btn_start");
            Sdk27PropertiesKt.setBackgroundResource(btn_start, C4188R.drawable.modify_go_on_btn);
            TextView btn_start2 = (TextView) _$_findCachedViewById(C4188R.id.btn_start);
            Intrinsics.checkExpressionValueIsNotNull(btn_start2, "btn_start");
            btn_start2.setText(getString(C4188R.string.pdStr2_13));
            LinearLayout setting_container = (LinearLayout) _$_findCachedViewById(C4188R.id.setting_container);
            Intrinsics.checkExpressionValueIsNotNull(setting_container, "setting_container");
            setting_container.setVisibility(4);
        }
        SceneRecord.INSTANCE.saveScene(this, SceneRecord.Scene.RECYCLE_SCENE);
        setSelectTableFragment();
        showTableLayout();
        initTaskList();
        ImageView setting_info2 = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
        Intrinsics.checkExpressionValueIsNotNull(setting_info2, "setting_info");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(setting_info2, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleTaskActivity$initView$1
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
                RecycleTaskActivity.this.showSettingDialog();
            }
        }, 3, null);
        RelativeLayout container_go_recycler_point = (RelativeLayout) _$_findCachedViewById(C4188R.id.container_go_recycler_point);
        Intrinsics.checkExpressionValueIsNotNull(container_go_recycler_point, "container_go_recycler_point");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(container_go_recycler_point, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleTaskActivity$initView$2
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
                ShowTipMsgDialog showTipMsgDialog;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (RobotMapManager.INSTANCE.getRecyclePoints().isEmpty()) {
                    showTipMsgDialog = RecycleTaskActivity.this.tipsDialog;
                    if (showTipMsgDialog == null || !showTipMsgDialog.isShowing()) {
                        RecycleTaskActivity recycleTaskActivity = RecycleTaskActivity.this;
                        String string = recycleTaskActivity.getString(C4188R.string.tips_none_recycle_point);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.tips_none_recycle_point)");
                        recycleTaskActivity.tipsDialog = MyBaseVmActivity.showTipDialog$default(recycleTaskActivity, string, null, null, null, 14, null);
                        return;
                    }
                    return;
                }
                RecycleTaskActivity recycleTaskActivity2 = RecycleTaskActivity.this;
                recycleTaskActivity2.jumpAndFinish(new Intent(recycleTaskActivity2, (Class<?>) ToRecyclingPointActivity.class));
                RecycleTaskActivity.this.prepareMusic();
            }
        }, 3, null);
        ((TextView) _$_findCachedViewById(C4188R.id.btn_start)).setOnClickListener(this.jumpClickListener);
        ((Button) _$_findCachedViewById(C4188R.id.setting_btn)).setOnClickListener(this.jumpClickListener);
        Button history_btn = (Button) _$_findCachedViewById(C4188R.id.history_btn);
        Intrinsics.checkExpressionValueIsNotNull(history_btn, "history_btn");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(history_btn, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleTaskActivity$initView$3
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
                RecycleTaskDialog recycleTaskDialog = new RecycleTaskDialog(RecycleTaskActivity.this);
                recycleTaskDialog.setHistory(true);
                List<String> historyRecycleTask = Constans.INSTANCE.getHistoryRecycleTask();
                if (historyRecycleTask == null) {
                    historyRecycleTask = CollectionsKt.emptyList();
                }
                List<String> list = historyRecycleTask;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList.add(new RecycleTaskItem((String) it2.next(), false, 2, null));
                }
                recycleTaskDialog.setTasks(arrayList);
                recycleTaskDialog.show();
            }
        }, 3, null);
        ImageView task_edit = (ImageView) _$_findCachedViewById(C4188R.id.task_edit);
        Intrinsics.checkExpressionValueIsNotNull(task_edit, "task_edit");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(task_edit, null, 0, new RecycleTaskActivity$initView$4(this), 3, null);
        PeripheralsSceneUtil.INSTANCE.setPlayMode(PeripheralsSceneUtil.Mode.Recycle);
        bindPresenter();
    }

    private final void bindPresenter() {
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        touchSensorEventHelper.bindPresenter(faceVideoView);
        this.touchSensorEventHelper.setOnTouchSensorPlaceListener(this.onTouchSensorPlaceListener);
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        getIdleMovePresenter().replaceView(this);
        getIdleMovePresenter().actionTimerCount(true);
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this);
    }

    private final void unbindPresenter() {
        this.touchSensorEventHelper.unBindPresent();
        getIdleMovePresenter().actionTimerCount(false);
        getIdleMovePresenter().removeView(this);
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Peripherals.INSTANCE.getFunctionButton().addListener(this.functionButton);
        PeripheralsSceneUtil.idleState$default(PeripheralsSceneUtil.INSTANCE, false, 1, null);
        updateTaskSize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Peripherals.INSTANCE.getFunctionButton().removeListener(this.functionButton);
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
            HomeSettingDialog homeSettingDialog2 = this.homeSettingDialog;
            if (homeSettingDialog2 == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog2.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleTaskActivity$showSettingDialog$1
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    String str;
                    TouchSensorEventHelper touchSensorEventHelper;
                    str = RecycleTaskActivity.this.TAG;
                    Pdlog.m3273d(str, "showSettingDialog setOnShowListener");
                    touchSensorEventHelper = RecycleTaskActivity.this.touchSensorEventHelper;
                    touchSensorEventHelper.stopCurrentAnimation();
                }
            });
        }
        HomeSettingDialog homeSettingDialog3 = this.homeSettingDialog;
        if (homeSettingDialog3 == null) {
            Intrinsics.throwNpe();
        }
        homeSettingDialog3.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTaskSize() {
        RecycleTaskAdapter recycleTaskAdapter = this.taskAdapter;
        if (recycleTaskAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("taskAdapter");
        }
        int realSize = recycleTaskAdapter.getRealSize();
        String string = getString(C4188R.string.title_add_table_task);
        String str = string + ' ' + getString(C4188R.string.add_table_task_count, new Object[]{Integer.valueOf(realSize), 20});
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        ColorStateList valueOf = ColorStateList.valueOf(getColor(realSize == 20 ? C4188R.color.red_alert : C4188R.color.font_color_1));
        RecycleTaskActivity recycleTaskActivity = this;
        spannableStringBuilder.setSpan(new TextAppearanceSpan(null, 1, SizeUtils.dp2px(recycleTaskActivity, 24.0f), ColorStateList.valueOf(getColor(C4188R.color.font_color_1)), null), 0, string.length(), 33);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(null, 0, SizeUtils.dp2px(recycleTaskActivity, 18.0f), valueOf, null), string.length(), str.length(), 33);
        TextView add_task_tips = (TextView) _$_findCachedViewById(C4188R.id.add_task_tips);
        Intrinsics.checkExpressionValueIsNotNull(add_task_tips, "add_task_tips");
        add_task_tips.setText(spannableStringBuilder);
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, realSize == 0, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startGo() {
        if (!BatteryInfoManager.INSTANCE.checkAllowedForMove() && this.mode != 1) {
            Pdlog.m3273d(this.TAG, "batteryPresenter check Allowed for move is false");
            String string = getString(C4188R.string.pdStr2_19);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_19)");
            MyBaseVmActivity.showTipDialog$default(this, string, null, null, null, 14, null);
            VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice14_2));
            return;
        }
        Pdlog.m3273d(this.TAG, "startGo");
        RecycleTaskAdapter recycleTaskAdapter = this.taskAdapter;
        if (recycleTaskAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("taskAdapter");
        }
        List<RecycleTaskItem> data = recycleTaskAdapter.getData();
        Intrinsics.checkExpressionValueIsNotNull(data, "taskAdapter.data");
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            if (((RecycleTaskItem) obj).getDestination().length() > 0) {
                arrayList.add(obj);
            }
        }
        ArrayList<RecycleTaskItem> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (RecycleTaskItem recycleTaskItem : arrayList2) {
            TrayModel trayModel = new TrayModel();
            trayModel.getAllDestinations().add(new DeliveryModel(recycleTaskItem.getDestination(), null, null, null, 12, null));
            arrayList3.add(trayModel);
        }
        ArrayList arrayList4 = arrayList3;
        if (RobotMapManager.INSTANCE.getRecyclePoints().isEmpty()) {
            ShowTipMsgDialog showTipMsgDialog = this.tipsDialog;
            if (showTipMsgDialog == null || !showTipMsgDialog.isShowing()) {
                String string2 = getString(C4188R.string.tips_none_recycle_point);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.tips_none_recycle_point)");
                this.tipsDialog = MyBaseVmActivity.showTipDialog$default(this, string2, null, null, null, 14, null);
                return;
            }
            return;
        }
        if (arrayList4.isEmpty()) {
            String string3 = getString(C4188R.string.pdStr16_30);
            Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr16_30)");
            MyBaseVmActivity.showTipDialog$default(this, string3, null, null, null, 14, null);
        } else {
            TableTaskManager.INSTANCE.setRecycleTasks(arrayList4, Constans.INSTANCE.isOrderRecycle() ? SortType.FIXED : SortType.AUTO, getMovePerformance(), this.mode == 0);
            if (this.mode == 0) {
                Constans.INSTANCE.saveLastRecycleTask(arrayList4);
            }
            jumpAndFinish(new Intent(this, (Class<?>) RecyclingPlatesActivity.class));
            prepareMusic();
        }
    }

    private final MovePerformance getMovePerformance() {
        return MovePerformance.NORMAL;
    }

    private final void showTableLayout() {
        boolean haveTableGroup = new DeliveryTaskSettingModel().haveTableGroup();
        if (Constans.INSTANCE.getTableInputType() == 1) {
            View view_ghost_1 = _$_findCachedViewById(C4188R.id.view_ghost_1);
            Intrinsics.checkExpressionValueIsNotNull(view_ghost_1, "view_ghost_1");
            ViewExtKt.show(view_ghost_1);
            View view_ghost_2 = _$_findCachedViewById(C4188R.id.view_ghost_2);
            Intrinsics.checkExpressionValueIsNotNull(view_ghost_2, "view_ghost_2");
            ViewExtKt.gone(view_ghost_2);
            return;
        }
        View view_ghost_12 = _$_findCachedViewById(C4188R.id.view_ghost_1);
        Intrinsics.checkExpressionValueIsNotNull(view_ghost_12, "view_ghost_1");
        view_ghost_12.setVisibility(haveTableGroup ? 0 : 8);
        View view_ghost_22 = _$_findCachedViewById(C4188R.id.view_ghost_2);
        Intrinsics.checkExpressionValueIsNotNull(view_ghost_22, "view_ghost_2");
        com.pudutech.disinfect.baselib.ext.view.ViewExtKt.visibleOrGone(view_ghost_22, !haveTableGroup);
        LinearLayout recycle_task_container = (LinearLayout) _$_findCachedViewById(C4188R.id.recycle_task_container);
        Intrinsics.checkExpressionValueIsNotNull(recycle_task_container, "recycle_task_container");
        LinearLayout linearLayout = recycle_task_container;
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
        }
        if (haveTableGroup) {
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            }
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = 0;
        } else if (layoutParams != null) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = CommonExtKt.dp2px(this, 45.0f);
        } else {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        linearLayout.setLayoutParams(layoutParams);
    }

    private final void initTaskList() {
        ArrayList arrayList;
        this.taskAdapter = new RecycleTaskAdapter();
        if (this.mode == 0) {
            if (Constans.INSTANCE.isRepeatLastRecycle()) {
                ArrayList<String> lastRecycleTask = Constans.INSTANCE.getLastRecycleTask();
                Pdlog.m3273d(this.TAG, "initTaskList repeat last tasks: " + lastRecycleTask);
                Iterable<String> emptyList = lastRecycleTask != null ? lastRecycleTask : CollectionsKt.emptyList();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(emptyList, 10));
                for (String it : emptyList) {
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    arrayList2.add(new RecycleTaskItem(it, false, 2, null));
                }
                arrayList = arrayList2;
            } else {
                arrayList = CollectionsKt.emptyList();
            }
        } else {
            Pdlog.m3273d(this.TAG, "initTaskList modify tasks: " + TableTaskManager.INSTANCE.getAllTask());
            ArrayList<TrayModel> allTask = TableTaskManager.INSTANCE.getAllTask();
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : allTask) {
                if (!((TrayModel) obj).getAllDestinations().isEmpty()) {
                    arrayList3.add(obj);
                }
            }
            ArrayList arrayList4 = arrayList3;
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                arrayList5.add(new RecycleTaskItem(((TrayModel) it2.next()).getAllDestinations().get(0).getDestination(), false, 2, null));
            }
            arrayList = arrayList5;
        }
        RecycleTaskAdapter recycleTaskAdapter = this.taskAdapter;
        if (recycleTaskAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("taskAdapter");
        }
        recycleTaskAdapter.setOnDataSizeChanged(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleTaskActivity$initTaskList$1
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
                RecycleTaskActivity.this.updateTaskSize();
            }
        });
        RecyclerView rv_task = (RecyclerView) _$_findCachedViewById(C4188R.id.rv_task);
        Intrinsics.checkExpressionValueIsNotNull(rv_task, "rv_task");
        rv_task.setLayoutManager(new LinearLayoutManager(this));
        RecycleTaskAdapter recycleTaskAdapter2 = this.taskAdapter;
        if (recycleTaskAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("taskAdapter");
        }
        recycleTaskAdapter2.bindToRecyclerView((RecyclerView) _$_findCachedViewById(C4188R.id.rv_task));
        RecycleTaskAdapter recycleTaskAdapter3 = this.taskAdapter;
        if (recycleTaskAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("taskAdapter");
        }
        recycleTaskAdapter3.setData(arrayList);
    }

    private final void setSelectTableFragment() {
        int tableInputType = Constans.INSTANCE.getTableInputType();
        if (tableInputType == 0 && !(this.selectTableFragment instanceof SelectTableFragment)) {
            this.selectTableFragment = new SelectTableFragment();
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
            if (iSelectTableFragment == null) {
                Intrinsics.throwNpe();
            }
            FragmentUtils.replace(supportFragmentManager, iSelectTableFragment, C4188R.id.select_table_container);
        } else if (tableInputType == 1 && !(this.selectTableFragment instanceof SmartInputTableFragment)) {
            this.selectTableFragment = new SmartInputTableFragment();
            FragmentManager supportFragmentManager2 = getSupportFragmentManager();
            ISelectTableFragment iSelectTableFragment2 = this.selectTableFragment;
            if (iSelectTableFragment2 == null) {
                Intrinsics.throwNpe();
            }
            FragmentUtils.replace(supportFragmentManager2, iSelectTableFragment2, C4188R.id.select_table_container);
        }
        ISelectTableFragment iSelectTableFragment3 = this.selectTableFragment;
        if (iSelectTableFragment3 != null) {
            iSelectTableFragment3.setOnSelectTable(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleTaskActivity$setSelectTableFragment$1
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
                    RecycleTaskActivity.access$getTaskAdapter$p(RecycleTaskActivity.this).setData(new RecycleTaskItem(it, false, 2, null));
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
    public void showPowerChange(int i) {
        ((MyStatusBarLayout) _$_findCachedViewById(C4188R.id.layout_my_status_bar)).setBattery(i);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        unbindPresenter();
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareMusic() {
        MusicPlayerHelper.getInstance().startPlay(ModeEnum.RECYCLE);
    }

    @Override // com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract.ViewInterface
    public void showIdleEvent(IdleMoveContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
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

    private final void showFaceDialog(FaceVideoAnimation animations) {
        Window window = getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "window");
        KeyboardUtils.hideSoftInput(window.getDecorView());
        Pdlog.m3273d(this.TAG, "showFaceDialog ");
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(animations);
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

    private final void stopStandby() {
        Pdlog.m3273d(this.TAG, "stopStandby");
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.stopPlay();
        PeripheralsSceneUtil.idleState$default(PeripheralsSceneUtil.INSTANCE, false, 1, null);
        resetScreenLight();
    }
}
