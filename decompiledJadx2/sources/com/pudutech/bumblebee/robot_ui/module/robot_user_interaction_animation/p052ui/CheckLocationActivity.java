package com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
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
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.ui_helpers.SceneRecord;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.mirsdkwrap.lib.map.MapLine;
import com.pudutech.resources.voice.VoiceItem;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
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

/* compiled from: CheckLocationActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\u0012\u0010\u001f\u001a\u00020\u00152\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u0019H\u0002J\b\u0010#\u001a\u00020\u0019H\u0002J\b\u0010$\u001a\u00020\u0019H\u0002J\"\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\u0012\u0010*\u001a\u00020\u00192\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020\u0019H\u0014J\u0012\u0010.\u001a\u00020\u00192\b\u0010/\u001a\u0004\u0018\u00010)H\u0014J\b\u00100\u001a\u00020\u0019H\u0002J(\u00101\u001a\u00020\u00192\u0006\u00102\u001a\u0002032\u0016\u00104\u001a\u0012\u0012\u0004\u0012\u00020\b05j\b\u0012\u0004\u0012\u00020\b`6H\u0016J\b\u00107\u001a\u00020\u0019H\u0002J@\u00108\u001a\u00020\u00192\u0006\u00109\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u00062\u0006\u0010<\u001a\u00020\u00062\u0016\u0010=\u001a\u0012\u0012\u0004\u0012\u00020>05j\b\u0012\u0004\u0012\u00020>`6H\u0016J\u0010\u0010?\u001a\u00020\u00192\u0006\u0010=\u001a\u00020@H\u0016J\u001a\u0010A\u001a\u00020\u00192\u0006\u00102\u001a\u0002032\b\u0010\u0017\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010B\u001a\u00020\u0019H\u0016J\u0010\u0010C\u001a\u00020\u00192\u0006\u0010D\u001a\u00020\u0006H\u0016J\u0010\u0010E\u001a\u00020\u00192\u0006\u0010=\u001a\u00020FH\u0016J.\u0010G\u001a\u00020\u00192\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\b\u0010H\u001a\u0004\u0018\u00010I2\b\u0010J\u001a\u0004\u0018\u00010\bH\u0016ø\u0001\u0000¢\u0006\u0002\bKJ\b\u0010L\u001a\u00020\u0019H\u0002J\b\u0010M\u001a\u00020\u0019H\u0002J\b\u0010N\u001a\u00020\u0019H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R)\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006O"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/robot_user_interaction_animation/ui/CheckLocationActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "()V", "REQUESTCODE_SELECT_MAP", "", "TAG", "", "TASK_CHEACK_LOCATION", "handler", "Landroid/os/Handler;", "mSkipType", "mapSwitchPresenter", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter;", "getMapSwitchPresenter", "()Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchPresenter;", "mapSwitchPresenter$delegate", "Lkotlin/Lazy;", "onLocationInitListener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "b", "", "touchJob", "Lkotlinx/coroutines/Job;", "voiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "bindPresenter", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "initData", "jumpNext", "jumpToCharging", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", "intent", "playSound", "showAll", "type", "Lcom/pudutech/bumblebee/presenter/map_switch_task/MapSwitchContract$Type;", "names", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "showAnimation", "showBackGround", "maxX", "minX", "maxY", "minY", "model", "Lcom/pudutech/mirsdkwrap/lib/map/MapLine;", "showChargerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showChosen", "showLowerNotify", "showPowerChange", "i", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "showSwitchError", "code", "Lkotlin/UByte;", "description", "showSwitchError-olJ16Uo", "startCheckLocation", "stopCheckLocation", "unBindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CheckLocationActivity extends MyBaseActivity implements MapSwitchContract.ViewInterface, BatteryContract2.ViewInterface {
    private HashMap _$_findViewCache;
    private int mSkipType;
    private Job touchJob;
    private final String TAG = "CheckLocationActivity";

    /* renamed from: mapSwitchPresenter$delegate, reason: from kotlin metadata */
    private final Lazy mapSwitchPresenter = LazyKt.lazy(new Function0<MapSwitchPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$mapSwitchPresenter$2
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
    private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            int i2 = message.what;
            i = CheckLocationActivity.this.TASK_CHEACK_LOCATION;
            if (i2 != i) {
                return true;
            }
            CheckLocationActivity.this.startCheckLocation();
            return true;
        }
    });
    private VoiceTask voiceTask = new VoiceTask(-1, VoiceItem.voice17_1);
    private final Function1<Boolean, Unit> onLocationInitListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$onLocationInitListener$1
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
            Handler handler;
            if (z) {
                str = CheckLocationActivity.this.TAG;
                Pdlog.m3273d(str, "showLocationInitDone is:" + z + " go to HomeMenuActivity");
                handler = CheckLocationActivity.this.handler;
                handler.removeCallbacksAndMessages(null);
                CheckLocationActivity.this.jumpNext();
            }
        }
    };

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
        setContentView(C4188R.layout.activity_layout_check_location_animation);
        ((TextView) _$_findCachedViewById(C4188R.id.map_select_iv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$onCreate$1
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
                CheckLocationActivity.this.stopCheckLocation();
                Intent intent = new Intent(CheckLocationActivity.this, (Class<?>) MapSelectActivity.class);
                CheckLocationActivity checkLocationActivity = CheckLocationActivity.this;
                i = checkLocationActivity.REQUESTCODE_SELECT_MAP;
                checkLocationActivity.startActivityForResult(intent, i);
            }
        }, 3, null));
        bindPresenter();
        initData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Pdlog.m3273d(this.TAG, "onNewIntent");
        bindPresenter();
        startCheckLocation();
        showAnimation();
    }

    private final void bindPresenter() {
        if (getIntent() != null) {
            this.mSkipType = getIntent().getIntExtra(Constans.SKIP_KEY, 0);
        }
        CheckLocationHelper.INSTANCE.addLocationInitDoneListener(this.onLocationInitListener);
        getMapSwitchPresenter().replaceView(this);
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this);
    }

    private final void unBindPresenter() {
        CheckLocationHelper.INSTANCE.removeLocationInitDoneListener(this.onLocationInitListener);
        getMapSwitchPresenter().removeView(this);
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this);
    }

    private final void initData() {
        getMapSwitchPresenter().loadMap();
        showAnimation();
        playSound();
        PeripheralsSceneUtil.INSTANCE.lostLocation();
        this.handler.sendEmptyMessageDelayed(this.TASK_CHEACK_LOCATION, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Pdlog.m3273d(this.TAG, "onActivityResult requestCode = " + requestCode + " , resultCode " + resultCode);
        if (requestCode == this.REQUESTCODE_SELECT_MAP) {
            startCheckLocation();
            showAnimation();
        }
    }

    private final void showAnimation() {
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        VoicePlayer.INSTANCE.stop(this.voiceTask);
        this.handler.removeMessages(0);
        stopCheckLocation();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent event) {
        Job launch$default;
        Integer valueOf = event != null ? Integer.valueOf(event.getActionMasked()) : null;
        if (valueOf != null && valueOf.intValue() == 5) {
            if (this.touchJob == null) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CheckLocationActivity$dispatchTouchEvent$1(this, null), 3, null);
                this.touchJob = launch$default;
            }
        } else if ((valueOf != null && valueOf.intValue() == 6) || (valueOf != null && valueOf.intValue() == 3)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CheckLocationActivity$dispatchTouchEvent$2(this, null), 3, null);
        }
        return super.dispatchTouchEvent(event);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpNext() {
        Pdlog.m3273d(this.TAG, " go to HomeMenuActivity");
        unBindPresenter();
        stopCheckLocation();
        SceneRecord.INSTANCE.startLastSceneActivity(this);
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
            TextView map_select_iv = (TextView) _$_findCachedViewById(C4188R.id.map_select_iv);
            Intrinsics.checkExpressionValueIsNotNull(map_select_iv, "map_select_iv");
            map_select_iv.setVisibility(8);
        } else {
            TextView map_select_iv2 = (TextView) _$_findCachedViewById(C4188R.id.map_select_iv);
            Intrinsics.checkExpressionValueIsNotNull(map_select_iv2, "map_select_iv");
            map_select_iv2.setVisibility(0);
        }
    }

    private final void jumpToCharging() {
        Pdlog.m3275i(this.TAG, "jump to Charging");
        stopCheckLocation();
        unBindPresenter();
        Intent intent = new Intent(this, (Class<?>) RobotChargingActivity.class);
        intent.putExtra("LOST_LOCATION_IN_MARKER_CASE", true);
        startActivity(intent);
        finish();
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
