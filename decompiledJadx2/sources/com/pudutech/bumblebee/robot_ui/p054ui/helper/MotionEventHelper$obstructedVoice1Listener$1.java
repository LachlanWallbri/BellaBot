package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.motion_task.MotionContract;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MotionEventHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper$obstructedVoice1Listener$1", "Lcom/pudutech/bumblebee/presenter/robot_voices/Listener;", "onStateChange", "", "event", "Lcom/pudutech/bumblebee/presenter/robot_voices/PlayEvent;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MotionEventHelper$obstructedVoice1Listener$1 implements Listener {
    final /* synthetic */ MotionEventHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MotionEventHelper$obstructedVoice1Listener$1(MotionEventHelper motionEventHelper) {
        this.this$0 = motionEventHelper;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
    public void onStateChange(PlayEvent event) {
        int i;
        int i2;
        FaceVideoView faceVideoView;
        int i3;
        int i4;
        boolean z;
        MotionContract.Event event2;
        String str;
        VoiceTask obstructedVoice2;
        MotionEventHelper$obstructedVoice2Listener$1 motionEventHelper$obstructedVoice2Listener$1;
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event == PlayEvent.COMPLETION_ONCE) {
            MotionEventHelper motionEventHelper = this.this$0;
            i3 = motionEventHelper.obstructedVoicePlayCount;
            motionEventHelper.obstructedVoicePlayCount = i3 + 1;
            i4 = this.this$0.obstructedVoicePlayCount;
            if (i4 >= 3) {
                event2 = this.this$0.currentMoveEvent;
                if (event2 != MotionContract.Event.OBSTRUCTED) {
                    return;
                }
                str = this.this$0.TAG;
                Pdlog.m3273d(str, "OBSTRUCTED playBtnVoice obstructedVoice2");
                MotionEventHelper motionEventHelper2 = this.this$0;
                obstructedVoice2 = motionEventHelper2.getObstructedVoice2();
                motionEventHelper$obstructedVoice2Listener$1 = this.this$0.obstructedVoice2Listener;
                motionEventHelper2.playVoice(obstructedVoice2.withListener(motionEventHelper$obstructedVoice2Listener$1));
                PeripheralsSceneUtil.INSTANCE.showMoveObstructed2();
            }
            z = this.this$0.isNeedRestartMovingVoice;
            if (z) {
                this.this$0.playMovingVoice();
            }
        } else {
            if (event == PlayEvent.PLAYING) {
                i2 = this.this$0.obstructedVoicePlayCount;
                if (i2 < 2) {
                    faceVideoView = this.this$0.faceAnimationView;
                    if (faceVideoView != null) {
                        faceVideoView.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.MotionEventHelper$obstructedVoice1Listener$1$onStateChange$1
                            /* JADX WARN: Code restructure failed: missing block: B:9:0x003d, code lost:
                            
                                r0 = r4.this$0.this$0.faceAnimationView;
                             */
                            @Override // java.lang.Runnable
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                            */
                            public final void run() {
                                String str2;
                                boolean z2;
                                MotionContract.Event event3;
                                boolean z3;
                                FaceVideoView faceVideoView2;
                                FaceVideoAnimation animationListener;
                                str2 = MotionEventHelper$obstructedVoice1Listener$1.this.this$0.TAG;
                                Pdlog.m3273d(str2, "OBSTRUCTED play obstru face");
                                z2 = MotionEventHelper$obstructedVoice1Listener$1.this.this$0.isCanHandle;
                                if (z2) {
                                    event3 = MotionEventHelper$obstructedVoice1Listener$1.this.this$0.currentMoveEvent;
                                    if (event3 == MotionContract.Event.OBSTRUCTED && MotionEventHelper$obstructedVoice1Listener$1.this.this$0.getOnTheWayAnimation() == null) {
                                        z3 = MotionEventHelper$obstructedVoice1Listener$1.this.this$0.isCanPlayObstructedVideo;
                                        if (!z3 || faceVideoView2 == null) {
                                            return;
                                        }
                                        animationListener = MotionEventHelper$obstructedVoice1Listener$1.this.this$0.setAnimationListener(SceneAnimationResources.INSTANCE.getAvoid());
                                        faceVideoView2.playAnimation(animationListener);
                                    }
                                }
                            }
                        });
                    }
                    this.this$0.obstructedVoice1Playing = true;
                }
            }
            if (event == PlayEvent.PLAYING) {
                i = this.this$0.obstructedVoicePlayCount;
                if (i == 2) {
                    this.this$0.obstructedVoice1Playing = true;
                }
            }
        }
        if (event == PlayEvent.STOP) {
            this.this$0.obstructedVoicePlayCount = 0;
            this.this$0.obstructedVoice1Playing = false;
        }
        Function1<PlayEvent, Unit> onVoicePlayListener = this.this$0.getOnVoicePlayListener();
        if (onVoicePlayListener != null) {
            onVoicePlayListener.invoke(event);
        }
    }
}
