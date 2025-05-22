package com.pudutech.peanut.robot_ui.p063ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.robot.TouchListenerManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.manager.AiVoiceManager;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui.LaserRunningLocationLostActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.WelComeDialogueActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.LocationLostDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.RunningErrorHelper;
import com.pudutech.peanut.robot_ui.viewmodel.CruiseVm;
import com.pudutech.peanut.robot_ui.viewmodel.WelComeDialogMoveVm;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: WelComeDialogueActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 42\u00020\u0001:\u00014B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001d\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0012H\u0007J\b\u0010!\u001a\u00020\u0012H\u0002J1\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020\u00072\b\u0010(\u001a\u0004\u0018\u00010$H\u0016¢\u0006\u0002\u0010)J\u0012\u0010*\u001a\u00020\u00122\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020\u0012H\u0014J\u0010\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020$H\u0002J\u001c\u00100\u001a\u0004\u0018\u00010\u000b2\u0006\u00101\u001a\u00020\u00042\b\b\u0002\u00102\u001a\u00020\u0007H\u0002J\b\u00103\u001a\u00020\u0012H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001a¨\u00065"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/WelComeDialogueActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "isShowLowPowerDialog", "", "locationLostDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/LocationLostDialog;", "lowerPowerDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isNeedFinish", "", "onLostLocationLostCallback", "Lkotlin/Function0;", "runningErrorHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/RunningErrorHelper;", "welComeDialogMoveVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeDialogMoveVm;", "getWelComeDialogMoveVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeDialogMoveVm;", "welComeDialogMoveVm$delegate", "Lkotlin/Lazy;", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "getTableNumber", "initVm", "notifyBatteryInfo", "state", "", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onFeatureChange", "type", "showDialog", NotificationCompat.CATEGORY_MESSAGE, "needCloseSensorFace", "showLowerNotify", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WelComeDialogueActivity extends BatteryBaseActivity {
    public static final String CLEAR_HANDLER = "CLEAR_HANDLER";
    public static final String RESTART_HANDLER = "RESTART_HANDLER";
    private HashMap _$_findViewCache;
    private boolean isShowLowPowerDialog;
    private LocationLostDialog locationLostDialog;
    private ShowTipMsgDialog lowerPowerDialog;
    private String TAG = getClass().getSimpleName();

    /* renamed from: welComeDialogMoveVm$delegate, reason: from kotlin metadata */
    private final Lazy welComeDialogMoveVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(WelComeDialogMoveVm.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$$special$$inlined$viewModels$2
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$$special$$inlined$viewModels$1
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
    private final RunningErrorHelper runningErrorHelper = new RunningErrorHelper();
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$onErrorDialogShowStatus$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Boolean bool2) {
            invoke(bool.booleanValue(), bool2.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z, boolean z2) {
            if (z) {
                WelComeDialogueActivity.this.getWelComeDialogMoveVm().getHandlerConnectionLD().postValue(WelComeDialogueActivity.CLEAR_HANDLER);
            } else {
                WelComeDialogueActivity.this.getWelComeDialogMoveVm().getHandlerConnectionLD().postValue(WelComeDialogueActivity.RESTART_HANDLER);
            }
        }
    };
    private Function0<Unit> onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$onLostLocationLostCallback$1
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
            String str2;
            String str3;
            LocationLostDialog locationLostDialog;
            LocationLostDialog locationLostDialog2;
            String str4;
            LocationLostDialog locationLostDialog3;
            LocationLostDialog locationLostDialog4;
            LocateCase locateCase = RobotMapManager.INSTANCE.getLocateCase();
            WelComeDialogueActivity.this.getWelComeDialogMoveVm().getHandlerConnectionLD().postValue(WelComeDialogueActivity.CLEAR_HANDLER);
            str = WelComeDialogueActivity.this.TAG;
            Pdlog.m3273d(str, WelComeDialogueActivity.this.getWelComeDialogMoveVm(), WelComeDialogueActivity.this.getWelComeDialogMoveVm().getHandlerConnectionLD().getValue());
            str2 = WelComeDialogueActivity.this.TAG;
            Pdlog.m3273d(str2, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
            if (locateCase == LocateCase.Marker) {
                str4 = WelComeDialogueActivity.this.TAG;
                Pdlog.m3273d(str4, "onLostLocationLostCallback");
                locationLostDialog3 = WelComeDialogueActivity.this.locationLostDialog;
                if (locationLostDialog3 == null) {
                    WelComeDialogueActivity welComeDialogueActivity = WelComeDialogueActivity.this;
                    welComeDialogueActivity.locationLostDialog = new LocationLostDialog(welComeDialogueActivity);
                }
                locationLostDialog4 = WelComeDialogueActivity.this.locationLostDialog;
                if (locationLostDialog4 != null) {
                    locationLostDialog4.show();
                    return;
                }
                return;
            }
            if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                WelComeDialogueActivity welComeDialogueActivity2 = WelComeDialogueActivity.this;
                welComeDialogueActivity2.jumpAndFinish(new Intent(welComeDialogueActivity2, (Class<?>) LaserRunningLocationLostActivity.class));
                return;
            }
            if (locateCase == LocateCase.LaserMark) {
                str3 = WelComeDialogueActivity.this.TAG;
                Pdlog.m3273d(str3, "onLostLocationLostCallback = LaserMark");
                locationLostDialog = WelComeDialogueActivity.this.locationLostDialog;
                if (locationLostDialog == null) {
                    WelComeDialogueActivity welComeDialogueActivity3 = WelComeDialogueActivity.this;
                    welComeDialogueActivity3.locationLostDialog = new LocationLostDialog(welComeDialogueActivity3, "1");
                }
                locationLostDialog2 = WelComeDialogueActivity.this.locationLostDialog;
                if (locationLostDialog2 != null) {
                    locationLostDialog2.show();
                }
            }
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CruiseVm.CruiseStatus.values().length];

        static {
            $EnumSwitchMapping$0[CruiseVm.CruiseStatus.Moving.ordinal()] = 1;
            $EnumSwitchMapping$0[CruiseVm.CruiseStatus.Pause.ordinal()] = 2;
            $EnumSwitchMapping$0[CruiseVm.CruiseStatus.Cancel.ordinal()] = 3;
            $EnumSwitchMapping$0[CruiseVm.CruiseStatus.Finish.ordinal()] = 4;
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    public final WelComeDialogMoveVm getWelComeDialogMoveVm() {
        return (WelComeDialogMoveVm) this.welComeDialogMoveVm.getValue();
    }

    public WelComeDialogueActivity() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.wecome_dialogue_acitvity);
        initVm();
        getTableNumber();
        LightPlayManager.INSTANCE.playNormalStatus();
    }

    private final void initVm() {
        getWelComeDialogMoveVm().initMoveSolicit();
        WelComeDialogueActivity welComeDialogueActivity = this;
        getWelComeDialogMoveVm().getMoveErrorHelperLiveData().observe(welComeDialogueActivity, new Observer<MoveErrorHelper>() { // from class: com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$initVm$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(MoveErrorHelper moveErrorHelper) {
                RunningErrorHelper runningErrorHelper;
                if (moveErrorHelper == null) {
                    return;
                }
                runningErrorHelper = WelComeDialogueActivity.this.runningErrorHelper;
                runningErrorHelper.showSuggestion(moveErrorHelper);
            }
        });
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$initVm$2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
        getWelComeDialogMoveVm().getCruiseStatusState().observe(welComeDialogueActivity, new Observer<CruiseVm.CruiseStatus>() { // from class: com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$initVm$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(CruiseVm.CruiseStatus cruiseStatus) {
                String str;
                int i;
                LocationLostDialog locationLostDialog;
                str = WelComeDialogueActivity.this.TAG;
                Pdlog.m3273d(str, "initVM " + cruiseStatus);
                if (cruiseStatus == null || (i = WelComeDialogueActivity.WhenMappings.$EnumSwitchMapping$0[cruiseStatus.ordinal()]) == 1) {
                    return;
                }
                if (i != 2) {
                    if (i != 3) {
                    }
                } else {
                    locationLostDialog = WelComeDialogueActivity.this.locationLostDialog;
                    if (locationLostDialog != null) {
                        locationLostDialog.dismiss();
                    }
                    WelComeDialogueActivity.this.getWelComeDialogMoveVm().getHandlerConnectionLD().postValue(WelComeDialogueActivity.RESTART_HANDLER);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onFeatureChange " + type);
        if (type == 0) {
            AiVoiceManager.INSTANCE.startAiRecording();
        } else {
            if (type == 1 || type == 2 || type == 3 || type != 4) {
                return;
            }
            AiVoiceManager.INSTANCE.stopAiRecording();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(final MotionEvent event) {
        ListenerList<TouchListenerManager.OnTouchListener> touchListeners = TouchListenerManager.INSTANCE.getTouchListeners();
        if (touchListeners != null) {
            touchListeners.forEach(Dispatchers.getMain(), new Function1<TouchListenerManager.OnTouchListener, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$dispatchTouchEvent$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TouchListenerManager.OnTouchListener onTouchListener) {
                    invoke2(onTouchListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(TouchListenerManager.OnTouchListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onTouchListener(event);
                }
            });
        }
        return super.dispatchTouchEvent(event);
    }

    private final void showLowerNotify() {
        Pdlog.m3273d(this.TAG, "showLowerNotify ");
        if (this.isShowLowPowerDialog) {
            return;
        }
        Pdlog.m3273d(this.TAG, "showBatteryEvent ShowLowPowerDialog");
        ShowTipMsgDialog showTipMsgDialog = this.lowerPowerDialog;
        if (showTipMsgDialog != null && showTipMsgDialog.isShowing()) {
            this.isShowLowPowerDialog = true;
            return;
        }
        this.isShowLowPowerDialog = true;
        String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr2_17);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_17)");
        this.lowerPowerDialog = showDialog$default(this, string, false, 2, null);
    }

    static /* synthetic */ ShowTipMsgDialog showDialog$default(WelComeDialogueActivity welComeDialogueActivity, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return welComeDialogueActivity.showDialog(str, z);
    }

    private final ShowTipMsgDialog showDialog(String msg, final boolean needCloseSensorFace) {
        return MyBaseActivity.showTipDialog$default(this, msg, new DialogInterface.OnShowListener() { // from class: com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$showDialog$1
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                WelComeDialogueActivity.this.onFeatureChange(4);
                boolean z = needCloseSensorFace;
            }
        }, new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.WelComeDialogueActivity$showDialog$2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                WelComeDialogueActivity.this.onFeatureChange(0);
            }
        }, null, 8, null);
    }

    public final void getTableNumber() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new WelComeDialogueActivity$getTableNumber$1(this, null), 3, null);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state == 1) {
            if (this.isShowLowPowerDialog) {
                return;
            }
            showLowerNotify();
        } else if (state == 3 && i != null && i.intValue() <= 5 && i.intValue() <= 2) {
            i.intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LocationLostDialog locationLostDialog = this.locationLostDialog;
        if (locationLostDialog != null) {
            locationLostDialog.dismiss();
        }
        this.runningErrorHelper.unbind();
    }
}
