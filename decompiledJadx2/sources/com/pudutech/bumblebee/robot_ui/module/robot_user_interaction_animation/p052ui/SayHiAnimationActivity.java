package com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui;

import android.os.Bundle;
import android.view.View;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.ui_helpers.SceneRecord;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.resources.voice.VoiceItem;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SayHiAnimationActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010\f\u001a\u00020\bH\u0014J\b\u0010\r\u001a\u00020\bH\u0014J\b\u0010\u000e\u001a\u00020\bH\u0002J\b\u0010\u000f\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/robot_user_interaction_animation/ui/SayHiAnimationActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "()V", "TAG", "", "voiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "initData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onStop", "playSound", "showAnimation", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SayHiAnimationActivity extends MyBaseActivity {
    private HashMap _$_findViewCache;
    private final String TAG = "SayHiAnimationActivity";
    private VoiceTask voiceTask = new VoiceTask(-1, VoiceItem.voice1_1);

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_layout_say_hi_animation);
        initData();
    }

    private final void initData() {
        showAnimation();
        playSound();
    }

    private final void showAnimation() {
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getSayHi());
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.animation_view)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.SayHiAnimationActivity$showAnimation$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                VoiceTask voiceTask;
                VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                voiceTask = SayHiAnimationActivity.this.voiceTask;
                voicePlayer.stop(voiceTask);
            }
        });
        PeripheralsSceneUtil.INSTANCE.playSayHi();
    }

    private final void playSound() {
        VoicePlayer.INSTANCE.play(this.voiceTask.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.ui.SayHiAnimationActivity$playSound$1
            @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
            public void onStateChange(PlayEvent event) {
                String str;
                String str2;
                Intrinsics.checkParameterIsNotNull(event, "event");
                str = SayHiAnimationActivity.this.TAG;
                Pdlog.m3273d(str, "onStateChange : event = " + event + "; ");
                if (event == PlayEvent.STOP || event == PlayEvent.COMPLETION_ONCE) {
                    SceneRecord.INSTANCE.startLastSceneActivity(SayHiAnimationActivity.this);
                    str2 = SayHiAnimationActivity.this.TAG;
                    Pdlog.m3273d(str2, "onStateChange : start jump ");
                    SayHiAnimationActivity.this.finish();
                }
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy ");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop ");
        VoicePlayer.INSTANCE.stop(this.voiceTask);
    }
}
