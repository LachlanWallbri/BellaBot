package com.pudutech.bumblebee.robot_ui.module.charging;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckActivity;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.CheckLocationActivity;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserCheckLocationActivity;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserRunningLocationLostActivity;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ValidationDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.DeliverTaskEditActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.util.AppUtil;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.bumblebee.robot_ui.widget.CircleImageView;
import com.pudutech.bumblebee.robot_ui.widget.random_layout.AnimatedRandomLayout;
import com.pudutech.bumblebee.robot_ui.widget.random_layout.WaveLoadingView;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: RobotChargingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 :2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001:B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0006H\u0002J\u0006\u0010 \u001a\u00020\u001dJ\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0006H\u0002J\u0012\u0010\"\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020\u001dH\u0014J\b\u0010&\u001a\u00020\u001dH\u0014J\b\u0010'\u001a\u00020\u001dH\u0002J\u0018\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u0006H\u0002J\u0010\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020\u001dH\u0002J\u0010\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u0006H\u0002J\b\u00104\u001a\u00020\u001dH\u0016J\u0010\u00105\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u0006H\u0016J\u0010\u00106\u001a\u00020\u001d2\u0006\u0010,\u001a\u000207H\u0016J\b\u00108\u001a\u00020\u001dH\u0002J\b\u00109\u001a\u00020\u001dH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/charging/RobotChargingActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewInterface;", "()V", "PLAY_ERROR_VOICE", "", "TAG", "", "checkPermissionDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ValidationDialog;", "currentPower", "errorTipTitleClickCount", "errorTipTitleClickTime", "", "idleMovePresenter", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "getIdleMovePresenter", "()Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "idleMovePresenter$delegate", "Lkotlin/Lazy;", "isLostLaserLocation", "", "isLostMarkerLocation", "isLostRunningLocation", "isNeedRecheckLocation", "mainHandler", "Landroid/os/Handler;", "bindPresenter", "", "changeTitleIfNeed", "p", "initView", "isChangeLight", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onStart", "playErrorVoice", "showChargerError", NotificationCompat.CATEGORY_MESSAGE, "code", "showChargerEvent", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showCheckPermissionDialog", "showIdleEvent", "event", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewEvent;", "showLedLight", "i", "showLowerNotify", "showPowerChange", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "stopErrorVoice", "unBindPresenter", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RobotChargingActivity extends MyBaseActivity implements BatteryContract2.ViewInterface, IdleMoveContract.ViewInterface {
    public static final String LOST_LOCATION_IN_LASER_CASE = "LOST_LOCATION_IN_LASER_CASE";
    public static final String LOST_LOCATION_IN_LASER_CASE_RUNNING = "LOST_LOCATION_IN_LASER_CASE_RUNNING";
    public static final String LOST_LOCATION_IN_MARKER_CASE = "LOST_LOCATION_IN_MARKER_CASE";
    public static final String NEED_RECHECK_LOCATION = "NEED_RECHECK_LOCATION";
    private HashMap _$_findViewCache;
    private ValidationDialog checkPermissionDialog;
    private int currentPower;
    private int errorTipTitleClickCount;
    private long errorTipTitleClickTime;
    private boolean isLostLaserLocation;
    private boolean isLostMarkerLocation;
    private boolean isLostRunningLocation;
    private boolean isNeedRecheckLocation;
    private final String TAG = "RobotChargingActivity";

    /* renamed from: idleMovePresenter$delegate, reason: from kotlin metadata */
    private final Lazy idleMovePresenter = LazyKt.lazy(new Function0<IdleMovePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity$idleMovePresenter$2
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
    private final int PLAY_ERROR_VOICE = 100;
    private final Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity$mainHandler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            int i2 = message.what;
            i = RobotChargingActivity.this.PLAY_ERROR_VOICE;
            if (i2 != i) {
                return true;
            }
            RobotChargingActivity.this.playErrorVoice();
            return true;
        }
    });

    private final IdleMovePresenter getIdleMovePresenter() {
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

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showLowerNotify() {
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_layout_charging);
        initView();
        bindPresenter();
    }

    public final void initView() {
        this.isNeedRecheckLocation = getIntent().getBooleanExtra("NEED_RECHECK_LOCATION", false);
        this.isLostMarkerLocation = getIntent().getBooleanExtra("LOST_LOCATION_IN_MARKER_CASE", false);
        this.isLostLaserLocation = getIntent().getBooleanExtra("LOST_LOCATION_IN_LASER_CASE", false);
        this.isLostRunningLocation = getIntent().getBooleanExtra("LOST_LOCATION_IN_LASER_CASE_RUNNING", false);
        ((AnimatedRandomLayout) _$_findCachedViewById(C4188R.id.rl_cloud)).setRegularity(10, 10);
        ((AnimatedRandomLayout) _$_findCachedViewById(C4188R.id.rl_cloud)).setItemShowCount(10);
        ((AnimatedRandomLayout) _$_findCachedViewById(C4188R.id.rl_cloud)).setLooperDuration(2);
        ((AnimatedRandomLayout) _$_findCachedViewById(C4188R.id.rl_cloud)).setDefaultDruation(2000);
        ((AnimatedRandomLayout) _$_findCachedViewById(C4188R.id.rl_cloud)).setWaveLoadingView((WaveLoadingView) _$_findCachedViewById(C4188R.id.waveLoadingView));
        ((AnimatedRandomLayout) _$_findCachedViewById(C4188R.id.rl_cloud)).setOnCreateItemViewListener(new AnimatedRandomLayout.OnCreateItemViewListener() { // from class: com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity$initView$1
            @Override // com.pudutech.bumblebee.robot_ui.widget.random_layout.AnimatedRandomLayout.OnCreateItemViewListener
            public int getCount() {
                return 10;
            }

            @Override // com.pudutech.bumblebee.robot_ui.widget.random_layout.AnimatedRandomLayout.OnCreateItemViewListener
            public View createItemView(int position, View convertView) {
                if (convertView != null) {
                    return convertView;
                }
                CircleImageView circleImageView = new CircleImageView(RobotChargingActivity.this);
                circleImageView.setImageResource(C4188R.drawable.oldgreen);
                circleImageView.setColorFilter(RobotChargingActivity.this.getResources().getColor(C4188R.color.charging, null));
                return circleImageView;
            }
        });
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        ((AnimatedRandomLayout) _$_findCachedViewById(C4188R.id.rl_cloud)).start();
    }

    private final void bindPresenter() {
        getIdleMovePresenter().replaceView(this);
        getIdleMovePresenter().actionIDLE();
        showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
        showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this);
    }

    private final void unBindPresenter() {
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this);
        getIdleMovePresenter().removeView(this);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AnimatedRandomLayout animatedRandomLayout = (AnimatedRandomLayout) _$_findCachedViewById(C4188R.id.rl_cloud);
        if (animatedRandomLayout != null) {
            animatedRandomLayout.stop();
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3275i(this.TAG, "showChargerEvent model=" + model);
        switch (model.getEvent()) {
            case CHARGING:
            case CHARGE_FULL:
                return;
            case CHARGER_DISCONNECT:
                PeripheralsSceneUtil.INSTANCE.stopCharging();
                unBindPresenter();
                stopErrorVoice();
                if (this.isNeedRecheckLocation) {
                    Pdlog.m3273d(this.TAG, "showChargerEvent isNeedRecheckLocation");
                    AnkoInternals.internalStartActivity(this, SelfCheckActivity.class, new Pair[0]);
                } else if (this.isLostMarkerLocation) {
                    Pdlog.m3273d(this.TAG, "showChargerEvent isLostMarkerLocation");
                    AnkoInternals.internalStartActivity(this, CheckLocationActivity.class, new Pair[0]);
                } else if (this.isLostLaserLocation) {
                    Pdlog.m3273d(this.TAG, "showChargerEvent isLostLaserLocation");
                    AnkoInternals.internalStartActivity(this, LaserCheckLocationActivity.class, new Pair[0]);
                } else if (this.isLostRunningLocation) {
                    Pdlog.m3273d(this.TAG, "showChargerEvent isLostRunningLocation");
                    AnkoInternals.internalStartActivity(this, LaserRunningLocationLostActivity.class, new Pair[]{TuplesKt.m3968to(LaserRunningLocationLostActivity.CURRENT_MODE_TYPE, -1)});
                } else {
                    AnkoInternals.internalStartActivity(this, DeliverTaskEditActivity.class, new Pair[0]);
                }
                finish();
                return;
            case CHARGER_CONTACT_FAULT:
                String string = getString(C4188R.string.pdStr6_3);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr6_3)");
                showChargerError(string, model.getErrorCode());
                return;
            case BATTERY_I2C_FAULT:
                String string2 = getString(C4188R.string.pdStr6_4);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr6_4)");
                showChargerError(string2, model.getErrorCode());
                return;
            case OVER_VOLTAGE_FAULT:
                String string3 = getString(C4188R.string.pdStr6_5);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr6_5)");
                showChargerError(string3, model.getErrorCode());
                return;
            case OVER_ELECTRIC_FAULT:
                String string4 = getString(C4188R.string.pdStr6_6);
                Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.pdStr6_6)");
                showChargerError(string4, model.getErrorCode());
                return;
            case OVERHEATING_FAULT:
                String string5 = getString(C4188R.string.pdStr6_7);
                Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.pdStr6_7)");
                showChargerError(string5, model.getErrorCode());
                return;
            case CHARGE_OVERTIME_FAULT:
                String string6 = getString(C4188R.string.pdStr6_8);
                Intrinsics.checkExpressionValueIsNotNull(string6, "getString(R.string.pdStr6_8)");
                showChargerError(string6, model.getErrorCode());
                return;
            default:
                String string7 = getString(C4188R.string.pdStr6_9);
                Intrinsics.checkExpressionValueIsNotNull(string7, "getString(R.string.pdStr6_9)");
                showChargerError(string7, model.getErrorCode());
                return;
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
        Pdlog.m3275i(this.TAG, "showPowerChange =" + i);
        ((WaveLoadingView) _$_findCachedViewById(C4188R.id.waveLoadingView)).setProgressValue(i);
        WaveLoadingView waveLoadingView = (WaveLoadingView) _$_findCachedViewById(C4188R.id.waveLoadingView);
        Intrinsics.checkExpressionValueIsNotNull(waveLoadingView, "waveLoadingView");
        waveLoadingView.setCenterTitle(String.valueOf(i));
        changeTitleIfNeed(i);
    }

    private final void showChargerError(String msg, int code) {
        showErrorTipDialog(msg + " ERROR:" + code, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity$showChargerError$1
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
                RobotChargingActivity.this.showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
            }
        }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity$showChargerError$2
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
                long j;
                int i;
                int i2;
                long currentTimeMillis = System.currentTimeMillis();
                j = RobotChargingActivity.this.errorTipTitleClickTime;
                if (currentTimeMillis - j > 1000) {
                    RobotChargingActivity.this.errorTipTitleClickCount = 0;
                } else {
                    i = RobotChargingActivity.this.errorTipTitleClickCount;
                    if (i > 4) {
                        RobotChargingActivity.this.showCheckPermissionDialog();
                        RobotChargingActivity.this.errorTipTitleClickCount = 0;
                    } else {
                        RobotChargingActivity robotChargingActivity = RobotChargingActivity.this;
                        i2 = robotChargingActivity.errorTipTitleClickCount;
                        robotChargingActivity.errorTipTitleClickCount = i2 + 1;
                    }
                }
                RobotChargingActivity.this.errorTipTitleClickTime = System.currentTimeMillis();
            }
        });
        playErrorVoice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCheckPermissionDialog() {
        if (this.checkPermissionDialog == null) {
            this.checkPermissionDialog = new ValidationDialog(this);
        }
        Pdlog.m3273d(this.TAG, "showCheckPermissionDialog");
        ValidationDialog validationDialog = this.checkPermissionDialog;
        if (validationDialog == null) {
            Intrinsics.throwNpe();
        }
        validationDialog.setOnPermissionCheckResult(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity$showCheckPermissionDialog$1
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
                String str;
                ValidationDialog validationDialog2;
                str = RobotChargingActivity.this.TAG;
                Pdlog.m3273d(str, "showCheckPermissionDialog onPermissionCheckResult " + z);
                if (z) {
                    validationDialog2 = RobotChargingActivity.this.checkPermissionDialog;
                    if (validationDialog2 != null) {
                        validationDialog2.dismiss();
                    }
                    AppUtil.INSTANCE.startDebugFunction(RobotChargingActivity.this);
                }
            }
        });
        ValidationDialog validationDialog2 = this.checkPermissionDialog;
        if (validationDialog2 == null) {
            Intrinsics.throwNpe();
        }
        validationDialog2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playErrorVoice() {
        PlaySound.playChargingVoice();
        stopErrorVoice();
        this.mainHandler.sendEmptyMessageDelayed(this.PLAY_ERROR_VOICE, 15000L);
    }

    private final void stopErrorVoice() {
        this.mainHandler.removeMessages(this.PLAY_ERROR_VOICE);
    }

    private final void changeTitleIfNeed(int p) {
        if (p != this.currentPower) {
            if (isChangeLight(p)) {
                showLedLight(p);
            }
            this.currentPower = p;
            if (this.currentPower == 100) {
                TextView title_tv = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
                Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
                title_tv.setText(getString(C4188R.string.pdStr6_10));
            } else {
                TextView title_tv2 = (TextView) _$_findCachedViewById(C4188R.id.title_tv);
                Intrinsics.checkExpressionValueIsNotNull(title_tv2, "title_tv");
                title_tv2.setText(getString(C4188R.string.pdStr6_1));
            }
        }
    }

    private final boolean isChangeLight(int p) {
        int i = this.currentPower;
        return i == 0 || (i <= 19 && p >= 20) || ((this.currentPower >= 20 && p <= 19) || ((this.currentPower <= 39 && p >= 40) || ((this.currentPower >= 40 && p <= 39) || ((this.currentPower <= 59 && p >= 60) || ((this.currentPower >= 60 && p <= 59) || ((this.currentPower <= 79 && p >= 80) || ((this.currentPower >= 80 && p <= 79) || ((this.currentPower == 100 && p <= 99) || (this.currentPower <= 99 && p == 100)))))))));
    }

    private final void showLedLight(int i) {
        PeripheralsSceneUtil.INSTANCE.showCharging(i);
    }

    @Override // com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract.ViewInterface
    public void showIdleEvent(IdleMoveContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(this.TAG, "showIdleEvent event=" + event);
    }
}
