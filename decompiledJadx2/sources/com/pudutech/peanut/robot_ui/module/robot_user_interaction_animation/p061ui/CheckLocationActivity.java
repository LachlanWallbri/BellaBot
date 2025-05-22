package com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.LostLocalizationListener;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: CheckLocationActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0002J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\u0012\u0010\u001b\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u0014H\u0016J1\u0010\u001d\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010!J\"\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\b\u0010%\u001a\u0004\u0018\u00010\u0014H\u0014J\u0012\u0010&\u001a\u00020\u00122\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\b\u0010)\u001a\u00020\u0012H\u0014J\u0012\u0010*\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010+\u001a\u00020\u0012H\u0014J\b\u0010,\u001a\u00020\u0012H\u0014J\b\u0010-\u001a\u00020\u0012H\u0002J\b\u0010.\u001a\u00020\u0012H\u0002J\b\u0010/\u001a\u00020\u0012H\u0002J\b\u00100\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/robot_user_interaction_animation/ui/CheckLocationActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/BatteryBaseActivity;", "()V", "LOCATE_STATUS", "", "REQUESTCODE_SELECT_MAP", "", "TAG", "TASK_CHEACK_LOCATION", "TASK_RESTATR", "handler", "Landroid/os/Handler;", "isFinish", "state", "touchJob", "Lkotlinx/coroutines/Job;", "type", "chargingFromIntent", "", "i", "Landroid/content/Intent;", "dispatchTouchEvent", "", "event", "Landroid/view/MotionEvent;", "initData", "initView", "jumpAndFinish", "intent", "notifyBatteryInfo", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "onActivityResult", "requestCode", "resultCode", "data", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", "onPause", "onStop", "playSound", "showAnimation", "startCheckLocation", "stopCheckLocation", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CheckLocationActivity extends BatteryBaseActivity {
    private HashMap _$_findViewCache;
    private int isFinish;
    private int state;
    private Job touchJob;
    private int type;
    private final String TAG = "CheckLocationActivity";
    private final String LOCATE_STATUS = "locate_status";
    private final int TASK_CHEACK_LOCATION = 100;
    private final int TASK_RESTATR = 1002;
    private final int REQUESTCODE_SELECT_MAP = 1001;
    private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            int i2;
            int i3 = message.what;
            i = CheckLocationActivity.this.TASK_CHEACK_LOCATION;
            if (i3 == i) {
                CheckLocationActivity.this.startCheckLocation();
                return true;
            }
            i2 = CheckLocationActivity.this.TASK_RESTATR;
            if (i3 != i2) {
                return true;
            }
            CheckLocationActivity.this.onResume();
            CheckLocationActivity.this.showAnimation();
            return true;
        }
    });

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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.state = getIntent().getIntExtra("state", 0);
        setContentView(C5508R.layout.activity_layout_check_location_animation);
        TextView map_select_iv = (TextView) _$_findCachedViewById(C5508R.id.map_select_iv);
        Intrinsics.checkExpressionValueIsNotNull(map_select_iv, "map_select_iv");
        ViewExtKt.onSingleClick(map_select_iv, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$onCreate$1
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
                int i2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                CheckLocationActivity.this.stopCheckLocation();
                Intent intent = new Intent(CheckLocationActivity.this, (Class<?>) MapSelectActivity.class);
                i = CheckLocationActivity.this.state;
                intent.putExtra("state", i);
                CheckLocationActivity checkLocationActivity = CheckLocationActivity.this;
                i2 = checkLocationActivity.REQUESTCODE_SELECT_MAP;
                checkLocationActivity.startActivityForResult(intent, i2);
            }
        });
        initView();
        initData();
    }

    private final void initView() {
        this.type = getIntent().getIntExtra("type", 0);
        if (this.type == 1) {
            if (RobotMapManager.INSTANCE.getCameraType() == CameraType.FACE_CAMERA) {
                RobotMapManager.INSTANCE.addLocateStatusListener(this.LOCATE_STATUS, new LostLocalizationListener.Stub() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$initView$1
                    @Override // com.pudutech.mirsdk.aidl.LostLocalizationListener
                    public void LostLocalization(LocalizationStatus p0) {
                    }
                });
                FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view);
                String string = getString(C5508R.string.hint_marker_laser_lost);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.hint_marker_laser_lost)");
                faceVideoView.changeHintContent(string);
                return;
            }
            FaceVideoView faceVideoView2 = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view);
            if (faceVideoView2 != null) {
                String string2 = getString(C5508R.string.pdStr8_11);
                if (string2 == null) {
                    string2 = "";
                }
                faceVideoView2.changeHintContent(string2);
                return;
            }
            return;
        }
        FaceVideoView faceVideoView3 = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view);
        String string3 = getString(C5508R.string.pdStr8_11);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr8_11)");
        faceVideoView3.changeHintContent(string3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Pdlog.m3273d(this.TAG, "onNewIntent");
        initView();
        startCheckLocation();
        showAnimation();
    }

    private final void initData() {
        showAnimation();
        playSound();
        LightPlayManager.INSTANCE.playLostLocation();
        this.handler.sendEmptyMessageDelayed(this.TASK_CHEACK_LOCATION, 1000L);
        RobotMapManager.INSTANCE.getMaps(new Function1<ArrayList<String>, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$initData$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ArrayList<String> arrayList) {
                invoke2(arrayList);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ArrayList<String> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it.size() > 1) {
                    TextView map_select_iv = (TextView) CheckLocationActivity.this._$_findCachedViewById(C5508R.id.map_select_iv);
                    Intrinsics.checkExpressionValueIsNotNull(map_select_iv, "map_select_iv");
                    map_select_iv.setVisibility(0);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Pdlog.m3273d(this.TAG, "onActivityResult requestCode = " + requestCode + " , resultCode " + resultCode);
        if (requestCode == this.REQUESTCODE_SELECT_MAP) {
            if (resultCode == 1001) {
                finish();
            } else {
                startCheckLocation();
                showAnimation();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showAnimation() {
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view);
        if (faceVideoView != null) {
            faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ((FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view)).stopPlay();
        Pdlog.m3273d(this.TAG, "onDestroy");
        if (RobotMapManager.INSTANCE.getCameraType() == CameraType.FACE_CAMERA && this.type == 1) {
            RobotMapManager.INSTANCE.removeLocateStatusListener(this.LOCATE_STATUS);
        }
        this.handler.removeMessages(0);
        this.handler.removeMessages(this.TASK_RESTATR);
        stopCheckLocation();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.handler.sendEmptyMessageDelayed(this.TASK_RESTATR, 200L);
        Pdlog.m3273d(this.TAG, "onStop");
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
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

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        if (this.isFinish == 1) {
            return;
        }
        this.isFinish = 1;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new CheckLocationActivity$jumpAndFinish$1(this, null), 3, null);
        stopCheckLocation();
        startActivity(intent);
        VoicePlayTasks.INSTANCE.finishStop();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCheckLocation() {
        Pdlog.m3273d(this.TAG, "startCheckLocation");
        stopCheckLocation();
        if (this.state == 0) {
            RobotMapManager.INSTANCE.checkLocationInit(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$startCheckLocation$1
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
                        CheckLocationActivity checkLocationActivity = CheckLocationActivity.this;
                        checkLocationActivity.jumpAndFinish(new Intent(checkLocationActivity, (Class<?>) SayHiAnimationActivity.class));
                    }
                }
            }, 500L);
        } else {
            RobotMapManager.INSTANCE.checkLocationSelftInit(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.CheckLocationActivity$startCheckLocation$2
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
                        Pdlog.m3273d(str, "checkLocationSelftInit is:" + z + " go to HomeMenuActivity");
                        handler = CheckLocationActivity.this.handler;
                        handler.removeCallbacksAndMessages(null);
                        CheckLocationActivity checkLocationActivity = CheckLocationActivity.this;
                        checkLocationActivity.jumpAndFinish(new Intent(checkLocationActivity, (Class<?>) SayHiAnimationActivity.class));
                    }
                }
            }, 500L);
        }
        this.handler.sendEmptyMessageDelayed(this.TASK_CHEACK_LOCATION, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopCheckLocation() {
        Pdlog.m3273d(this.TAG, "stopCheckLocation");
        this.handler.removeMessages(this.TASK_CHEACK_LOCATION);
    }

    private final void playSound() {
        VoicePlayTasks.INSTANCE.playMarkerLostLocation();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state != 4) {
            return;
        }
        VoicePlayTasks.INSTANCE.stop();
        stopCheckLocation();
        Pdlog.m3273d(this.TAG, "showChargerEvent " + model);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void chargingFromIntent(Intent i) {
        Intrinsics.checkParameterIsNotNull(i, "i");
        i.putExtra("LOST_LOCATION_IN_MARKER_CASE", true);
    }
}
