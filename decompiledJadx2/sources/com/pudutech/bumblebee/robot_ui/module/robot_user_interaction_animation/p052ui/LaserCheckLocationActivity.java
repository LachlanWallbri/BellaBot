package com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract;
import com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.DeliverTaskEditActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import com.pudutech.mirsdkwrap.lib.map.MapLine;
import com.pudutech.resources.voice.VoiceItem;
import com.warkiz.widget.SizeUtils;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.jetbrains.anko.Sdk27PropertiesKt;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: LaserCheckLocationActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001f\u001a\u00020\u001aH\u0002J\u0012\u0010 \u001a\u00020\r2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u001aH\u0002J\b\u0010$\u001a\u00020\u001aH\u0002J\b\u0010%\u001a\u00020\u001aH\u0002J\b\u0010&\u001a\u00020\u001aH\u0002J\"\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\u0012\u0010,\u001a\u00020\u001a2\b\u0010-\u001a\u0004\u0018\u00010.H\u0014J\b\u0010/\u001a\u00020\u001aH\u0014J\u0012\u00100\u001a\u00020\u001a2\b\u00101\u001a\u0004\u0018\u00010+H\u0014J\b\u00102\u001a\u00020\u001aH\u0014J\b\u00103\u001a\u00020\u001aH\u0002J\b\u00104\u001a\u00020\u001aH\u0002J(\u00105\u001a\u00020\u001a2\u0006\u00106\u001a\u0002072\u0016\u00108\u001a\u0012\u0012\u0004\u0012\u00020\b09j\b\u0012\u0004\u0012\u00020\b`:H\u0016J@\u0010;\u001a\u00020\u001a2\u0006\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u00062\u0006\u0010?\u001a\u00020\u00062\u0016\u0010@\u001a\u0012\u0012\u0004\u0012\u00020A09j\b\u0012\u0004\u0012\u00020A`:H\u0016J\u0010\u0010B\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020CH\u0016J\u001a\u0010D\u001a\u00020\u001a2\u0006\u00106\u001a\u0002072\b\u0010\u0018\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010E\u001a\u00020\u001aH\u0016J\u0010\u0010F\u001a\u00020\u001a2\u0006\u0010G\u001a\u00020\u0006H\u0016J\u0010\u0010H\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020IH\u0016J.\u0010J\u001a\u00020\u001a2\b\u0010\u0018\u001a\u0004\u0018\u00010\b2\b\u0010K\u001a\u0004\u0018\u00010L2\b\u0010M\u001a\u0004\u0018\u00010\bH\u0016ø\u0001\u0000¢\u0006\u0002\bNJ\b\u0010O\u001a\u00020\u001aH\u0002J\b\u0010P\u001a\u00020\u001aH\u0002J\b\u0010Q\u001a\u00020\u001aH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R)\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006R"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/robot_user_interaction_animation/ui/LaserCheckLocationActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "()V", "REQUESTCODE_SELECT_MAP", "", "TAG", "", "TASK_CHEACK_LOCATION", "handler", "Landroid/os/Handler;", "isFirstTryRelocation", "", "mSkipType", "mapSwitchPresenter", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter;", "getMapSwitchPresenter", "()Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter;", "mapSwitchPresenter$delegate", "Lkotlin/Lazy;", "onLocationInitListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "b", "", "touchJob", "Lkotlinx/coroutines/Job;", "voiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "bindPresenter", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "initData", "initView", "jumpNext", "jumpToCharging", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", "intent", "onResume", "playSound", "setListeners", "showAll", "type", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchContract$Type;", "names", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "showBackGround", "maxX", "minX", "maxY", "minY", "model", "Lcom/pudutech/mirsdkwrap/lib/map/MapLine;", "showChargerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showChosen", "showLowerNotify", "showPowerChange", "i", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "showSwitchError", "code", "Lkotlin/UByte;", "description", "showSwitchError-olJ16Uo", "startCheckLocation", "stopCheckLocation", "unBindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LaserCheckLocationActivity extends MyBaseActivity implements MapSwitchContract.ViewInterface, BatteryContract2.ViewInterface {
    private HashMap _$_findViewCache;
    private int mSkipType;
    private Job touchJob;
    private final String TAG = "LaserCheckLocationActivity";

    /* renamed from: mapSwitchPresenter$delegate, reason: from kotlin metadata */
    private final Lazy mapSwitchPresenter = LazyKt.lazy(new Function0<MapSwitchPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.LaserCheckLocationActivity$mapSwitchPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MapSwitchPresenter invoke() {
            MapSwitchPresenter mapSwitchPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(MapSwitchPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(MapSwitchPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                mapSwitchPresenter = new MapSwitchPresenter();
            } else {
                presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(MapSwitchPresenter.class).toString());
                if (!(basePresenterInterface instanceof MapSwitchPresenter)) {
                    basePresenterInterface = null;
                }
                mapSwitchPresenter = (MapSwitchPresenter) basePresenterInterface;
            }
            if (mapSwitchPresenter != null) {
                return mapSwitchPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchPresenter");
        }
    });
    private final int TASK_CHEACK_LOCATION = 100;
    private final int REQUESTCODE_SELECT_MAP = 1001;
    private boolean isFirstTryRelocation = true;
    private final Function1<Boolean, Unit> onLocationInitListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.LaserCheckLocationActivity$onLocationInitListener$1
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
            boolean z2;
            Handler handler;
            int i;
            String str2;
            str = LaserCheckLocationActivity.this.TAG;
            Pdlog.m3273d(str, "onLocationInitListener is:" + z);
            if (z) {
                handler = LaserCheckLocationActivity.this.handler;
                i = LaserCheckLocationActivity.this.TASK_CHEACK_LOCATION;
                handler.removeMessages(i);
                str2 = LaserCheckLocationActivity.this.TAG;
                Pdlog.m3273d(str2, "onLocationInitListener is:" + z + " go to HomeMenuActivity");
                LaserCheckLocationActivity.this.jumpNext();
                return;
            }
            RelativeLayout slam_checking_location_layout = (RelativeLayout) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.slam_checking_location_layout);
            Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
            slam_checking_location_layout.setVisibility(8);
            RelativeLayout slam_checking_location_result_layout = (RelativeLayout) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.slam_checking_location_result_layout);
            Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
            slam_checking_location_result_layout.setVisibility(0);
            z2 = LaserCheckLocationActivity.this.isFirstTryRelocation;
            if (z2) {
                TextView relocation_tip = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.relocation_tip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_tip, "relocation_tip");
                Sdk27PropertiesKt.setBackgroundResource(relocation_tip, C4188R.drawable.selector_check_location_result_try_tip_btn);
                TextView relocation_tip2 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.relocation_tip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_tip2, "relocation_tip");
                relocation_tip2.setText(LaserCheckLocationActivity.this.getString(C4188R.string.pdStr1_27));
                TextView relocation_descrip = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.relocation_descrip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_descrip, "relocation_descrip");
                relocation_descrip.setText(LaserCheckLocationActivity.this.getString(C4188R.string.pdStr1_26));
                TextView relocation_descrip2 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.relocation_descrip);
                Intrinsics.checkExpressionValueIsNotNull(relocation_descrip2, "relocation_descrip");
                Sdk27PropertiesKt.setTextColor(relocation_descrip2, LaserCheckLocationActivity.this.getColor(C4188R.color.white));
                return;
            }
            TextView relocation_tip3 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.relocation_tip);
            Intrinsics.checkExpressionValueIsNotNull(relocation_tip3, "relocation_tip");
            Sdk27PropertiesKt.setBackgroundResource(relocation_tip3, C4188R.drawable.selector_check_location_result_fail_btn);
            TextView relocation_tip4 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.relocation_tip);
            Intrinsics.checkExpressionValueIsNotNull(relocation_tip4, "relocation_tip");
            relocation_tip4.setText(LaserCheckLocationActivity.this.getString(C4188R.string.pdStr1_29));
            TextView relocation_descrip3 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.relocation_descrip);
            Intrinsics.checkExpressionValueIsNotNull(relocation_descrip3, "relocation_descrip");
            relocation_descrip3.setText(LaserCheckLocationActivity.this.getString(C4188R.string.pdStr1_28));
            TextView relocation_descrip4 = (TextView) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.relocation_descrip);
            Intrinsics.checkExpressionValueIsNotNull(relocation_descrip4, "relocation_descrip");
            Sdk27PropertiesKt.setTextColor(relocation_descrip4, LaserCheckLocationActivity.this.getColor(C4188R.color.red_alert));
        }
    };
    private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.LaserCheckLocationActivity$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            int i2 = message.what;
            i = LaserCheckLocationActivity.this.TASK_CHEACK_LOCATION;
            if (i2 != i) {
                return true;
            }
            LaserCheckLocationActivity.this.startCheckLocation();
            return true;
        }
    });
    private VoiceTask voiceTask = new VoiceTask(-1, VoiceItem.voice17_1);

    private final MapSwitchPresenter getMapSwitchPresenter() {
        return (MapSwitchPresenter) this.mapSwitchPresenter.getValue();
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

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    public void showBackGround(int maxX, int minX, int maxY, int minY, ArrayList<MapLine> model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    public void showChosen(MapSwitchContract.Type type, String name) {
        Intrinsics.checkParameterIsNotNull(type, "type");
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showLowerNotify() {
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_layout_laser_check_location);
        ((TextView) _$_findCachedViewById(C4188R.id.switch_map_tip)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.LaserCheckLocationActivity$onCreate$1
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
                int i;
                Intrinsics.checkParameterIsNotNull(it, "it");
                LaserCheckLocationActivity.this.stopCheckLocation();
                Intent intent = new Intent(LaserCheckLocationActivity.this, (Class<?>) MapSelectActivity.class);
                LaserCheckLocationActivity laserCheckLocationActivity = LaserCheckLocationActivity.this;
                i = laserCheckLocationActivity.REQUESTCODE_SELECT_MAP;
                laserCheckLocationActivity.startActivityForResult(intent, i);
            }
        }, 3, null));
        bindPresenter();
        initView();
        setListeners();
        initData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Pdlog.m3273d(this.TAG, "onNewIntent");
        bindPresenter();
        startCheckLocation();
    }

    private final void bindPresenter() {
        if (getIntent() != null) {
            getIntent().getIntExtra(Constans.SKIP_KEY, 0);
        }
        getMapSwitchPresenter().replaceView(this);
        CheckLocationHelper.INSTANCE.addLocationInitDoneListener(this.onLocationInitListener);
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this);
    }

    private final void unBindPresenter() {
        getMapSwitchPresenter().removeView(this);
        CheckLocationHelper.INSTANCE.removeLocationInitDoneListener(this.onLocationInitListener);
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this);
    }

    private final void initData() {
        getMapSwitchPresenter().loadMap();
        playSound();
        PeripheralsSceneUtil.INSTANCE.lostLocation();
        this.handler.sendEmptyMessageDelayed(this.TASK_CHEACK_LOCATION, 5000L);
    }

    private final void initView() {
        RelativeLayout slam_checking_location_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.slam_checking_location_layout);
        Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
        slam_checking_location_layout.setVisibility(0);
        RelativeLayout slam_checking_location_result_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.slam_checking_location_result_layout);
        Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
        slam_checking_location_result_layout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LaserCheckLocationActivity laserCheckLocationActivity = this;
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C4188R.id.checking_tip), (int) ((SizeUtils.dp2px(laserCheckLocationActivity, getResources().getDimension(C4188R.dimen.checking_location_tip_width)) - 10) * 1.7d), getString(C4188R.string.pdStr1_25));
        String string = getString(C4188R.string.pdStr1_26);
        if (string.length() < getString(C4188R.string.pdStr1_28).length()) {
            string = getString(C4188R.string.pdStr1_28);
        }
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C4188R.id.relocation_descrip), (int) ((SizeUtils.dp2px(laserCheckLocationActivity, getResources().getDimension(C4188R.dimen.checking_location_tip_width)) - 10) * 1.9d), string);
    }

    private final void setListeners() {
        ((TextView) _$_findCachedViewById(C4188R.id.relocation_tip)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.LaserCheckLocationActivity$setListeners$1
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
                String str;
                Handler handler;
                int i;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = LaserCheckLocationActivity.this.TAG;
                Pdlog.m3273d(str, "click relocation btn");
                LaserCheckLocationActivity.this.stopCheckLocation();
                RelativeLayout slam_checking_location_layout = (RelativeLayout) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.slam_checking_location_layout);
                Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_layout, "slam_checking_location_layout");
                slam_checking_location_layout.setVisibility(0);
                RelativeLayout slam_checking_location_result_layout = (RelativeLayout) LaserCheckLocationActivity.this._$_findCachedViewById(C4188R.id.slam_checking_location_result_layout);
                Intrinsics.checkExpressionValueIsNotNull(slam_checking_location_result_layout, "slam_checking_location_result_layout");
                slam_checking_location_result_layout.setVisibility(8);
                LaserCheckLocationActivity.this.isFirstTryRelocation = false;
                CheckLocationHelper.INSTANCE.reCheckLocation();
                handler = LaserCheckLocationActivity.this.handler;
                i = LaserCheckLocationActivity.this.TASK_CHEACK_LOCATION;
                handler.sendEmptyMessageDelayed(i, 5000L);
            }
        }, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Pdlog.m3273d(this.TAG, "onActivityResult requestCode = " + requestCode + " , resultCode " + resultCode);
        if (requestCode == this.REQUESTCODE_SELECT_MAP) {
            startCheckLocation();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C4188R.id.checking_location_anim);
        if (lottieAnimationView != null && lottieAnimationView.isAnimating()) {
            lottieAnimationView.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView2 = (LottieAnimationView) _$_findCachedViewById(C4188R.id.location_checking_result_anim);
        if (lottieAnimationView2 != null && lottieAnimationView2.isAnimating()) {
            lottieAnimationView2.cancelAnimation();
        }
        VoicePlayer.INSTANCE.stop(this.voiceTask);
        stopCheckLocation();
        this.isFirstTryRelocation = true;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent event) {
        Job launch$default;
        Integer valueOf = event != null ? Integer.valueOf(event.getActionMasked()) : null;
        if (valueOf != null && valueOf.intValue() == 5) {
            if (this.touchJob == null) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LaserCheckLocationActivity$dispatchTouchEvent$1(this, null), 3, null);
                this.touchJob = launch$default;
            }
        } else if ((valueOf != null && valueOf.intValue() == 6) || (valueOf != null && valueOf.intValue() == 3)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LaserCheckLocationActivity$dispatchTouchEvent$2(this, null), 3, null);
        }
        return super.dispatchTouchEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpNext() {
        Pdlog.m3273d(this.TAG, " go to HomeMenuActivity");
        unBindPresenter();
        stopCheckLocation();
        AnkoInternals.internalStartActivity(this, DeliverTaskEditActivity.class, new Pair[0]);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCheckLocation() {
        Pdlog.m3273d(this.TAG, "startCheckLocation");
        stopCheckLocation();
        CheckLocationHelper.INSTANCE.checkLocationInitResult();
        this.handler.sendEmptyMessageDelayed(this.TASK_CHEACK_LOCATION, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopCheckLocation() {
        Pdlog.m3273d(this.TAG, "stopCheckLocation");
        this.handler.removeMessages(this.TASK_CHEACK_LOCATION);
    }

    private final void playSound() {
        VoicePlayer.INSTANCE.play(this.voiceTask);
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    /* renamed from: showSwitchError-olJ16Uo */
    public void mo4294showSwitchErrorolJ16Uo(String name, UByte code, String description) {
        Pdlog.m3274e(this.TAG, "showSwitchError " + name + ',' + description);
    }

    @Override // com.pudutech.bumblebee.presenter.map_switch_task.MapSwitchContract.ViewInterface
    public void showAll(MapSwitchContract.Type type, ArrayList<String> names) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(names, "names");
        Pdlog.m3273d(this.TAG, "showAll " + type + " , " + names + ' ');
        if (type != MapSwitchContract.Type.MAP || names.size() <= 1) {
            return;
        }
        if (this.mSkipType == 1) {
            TextView switch_map_tip = (TextView) _$_findCachedViewById(C4188R.id.switch_map_tip);
            Intrinsics.checkExpressionValueIsNotNull(switch_map_tip, "switch_map_tip");
            switch_map_tip.setVisibility(8);
        } else {
            TextView switch_map_tip2 = (TextView) _$_findCachedViewById(C4188R.id.switch_map_tip);
            Intrinsics.checkExpressionValueIsNotNull(switch_map_tip2, "switch_map_tip");
            switch_map_tip2.setVisibility(0);
        }
    }

    private final void jumpToCharging() {
        Pdlog.m3275i(this.TAG, "jump to Charging");
        stopCheckLocation();
        unBindPresenter();
        Intent intent = new Intent(this, (Class<?>) RobotChargingActivity.class);
        intent.putExtra("LOST_LOCATION_IN_LASER_CASE", true);
        startActivity(intent);
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showChargerEvent " + model);
        if (model.getEvent() != BatteryContract2.ViewEvent.CHARGER_DISCONNECT) {
            jumpToCharging();
        }
    }
}
