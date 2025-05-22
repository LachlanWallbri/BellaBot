package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MotionEventHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper$obstructedVoice2Listener$1", "Lcom/pudutech/bumblebee/presenter/robot_voices/Listener;", "onStateChange", "", "event", "Lcom/pudutech/bumblebee/presenter/robot_voices/PlayEvent;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MotionEventHelper$obstructedVoice2Listener$1 implements Listener {
    final /* synthetic */ MotionEventHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MotionEventHelper$obstructedVoice2Listener$1(MotionEventHelper motionEventHelper) {
        this.this$0 = motionEventHelper;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
    public void onStateChange(PlayEvent event) {
        boolean z;
        FaceVideoView faceVideoView;
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event == PlayEvent.PLAYING) {
            faceVideoView = this.this$0.faceAnimationView;
            if (faceVideoView != null) {
                faceVideoView.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.MotionEventHelper$obstructedVoice2Listener$1$onStateChange$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        MotionEventHelper$obstructedVoice2Listener$1.this.this$0.playAngryFaceAnimation();
                    }
                });
            }
            this.this$0.obstructedVoice2Playing = true;
        } else if (event == PlayEvent.COMPLETION_ONCE) {
            z = this.this$0.isNeedRestartMovingVoice;
            if (z) {
                this.this$0.playMovingVoice();
            }
        } else if (event == PlayEvent.STOP) {
            this.this$0.obstructedVoice2Playing = false;
        }
        Function1<PlayEvent, Unit> onVoicePlayListener = this.this$0.getOnVoicePlayListener();
        if (onVoicePlayListener != null) {
            onVoicePlayListener.invoke(event);
        }
    }
}
