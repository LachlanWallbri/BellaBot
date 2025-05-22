package com.pudutech.bumblebee.robot_ui.p054ui.recycle;

import kotlin.Metadata;

/* compiled from: IRecycleVoice.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\u0010\u001a\u00020\u0003H&¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/recycle/IRecycleVoice;", "", "forcePlay", "", "forceStop", "onPlayVoice", "onStopVoice", "play", "eventTag", "", "recordPause", "isPause", "", "release", "resetCount", "setPause", "stop", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface IRecycleVoice {
    void forcePlay();

    void forceStop();

    void onPlayVoice();

    void onStopVoice();

    void play(String eventTag);

    void recordPause(boolean isPause);

    void release();

    void resetCount();

    void setPause(boolean isPause);

    void stop();

    /* compiled from: IRecycleVoice.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void play$default(IRecycleVoice iRecycleVoice, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: play");
            }
            if ((i & 1) != 0) {
                str = "";
            }
            iRecycleVoice.play(str);
        }
    }
}
