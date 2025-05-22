package com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.p061ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.p063ui.HomeActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SayHiAnimationActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010\u000e\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\nH\u0014J\b\u0010\u0012\u001a\u00020\nH\u0014J\b\u0010\u0013\u001a\u00020\nH\u0002J\b\u0010\u0014\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/robot_user_interaction_animation/ui/SayHiAnimationActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "()V", "TAG", "", "isFinish", "", "mHandler", "Landroid/os/Handler;", "initData", "", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onStop", "playSound", "showAnimation", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SayHiAnimationActivity extends MyBaseActivity {
    private HashMap _$_findViewCache;
    private boolean isFinish;
    private final String TAG = "SayHiAnimationActivity";
    private final Handler mHandler = new Handler() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.SayHiAnimationActivity$mHandler$1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            SayHiAnimationActivity sayHiAnimationActivity = SayHiAnimationActivity.this;
            sayHiAnimationActivity.jumpAndFinish(new Intent(sayHiAnimationActivity, (Class<?>) HomeActivity.class));
        }
    };

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_layout_say_hi_animation);
        initData();
        LightPlayManager.INSTANCE.playNormalStatus();
    }

    private final void initData() {
        showAnimation();
        playSound();
        this.mHandler.sendEmptyMessageDelayed(1, 2000L);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        Pdlog.m3273d(this.TAG, "jumpAndFinish : intent = " + intent + "; ");
        if (this.isFinish) {
            return;
        }
        this.isFinish = true;
        startActivity(intent);
        finish();
    }

    private final void showAnimation() {
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C5508R.id.animation_view);
        if (faceVideoView != null) {
            faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getSayHi());
        }
        FaceVideoView faceVideoView2 = (FaceVideoView) _$_findCachedViewById(C5508R.id.animation_view);
        if (faceVideoView2 != null) {
            faceVideoView2.setOnPlayFinishListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.SayHiAnimationActivity$showAnimation$1
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
                    str = SayHiAnimationActivity.this.TAG;
                    Pdlog.m3273d(str, "showAnimation onPlayFinishListener");
                    SayHiAnimationActivity sayHiAnimationActivity = SayHiAnimationActivity.this;
                    sayHiAnimationActivity.jumpAndFinish(new Intent(sayHiAnimationActivity, (Class<?>) HomeActivity.class));
                }
            });
        }
        FaceVideoView faceVideoView3 = (FaceVideoView) _$_findCachedViewById(C5508R.id.animation_view);
        if (faceVideoView3 != null) {
            faceVideoView3.setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.module.robot_user_interaction_animation.ui.SayHiAnimationActivity$showAnimation$2
                @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
                public void onSingleClick() {
                    SayHiAnimationActivity sayHiAnimationActivity = SayHiAnimationActivity.this;
                    sayHiAnimationActivity.jumpAndFinish(new Intent(sayHiAnimationActivity, (Class<?>) HomeActivity.class));
                    VoicePlayTasks.INSTANCE.stop();
                }
            });
        }
        LightPlayManager.INSTANCE.playSayHi();
    }

    private final void playSound() {
        VoicePlayTasks.INSTANCE.playSayHi();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy ");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.mHandler.removeCallbacksAndMessages(null);
        Pdlog.m3273d(this.TAG, "onStop ");
    }
}
