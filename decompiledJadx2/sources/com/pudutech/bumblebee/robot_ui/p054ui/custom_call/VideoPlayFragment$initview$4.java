package com.pudutech.bumblebee.robot_ui.p054ui.custom_call;

import android.app.Activity;
import android.media.MediaPlayer;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: VideoPlayFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\n¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "", "mp", "Landroid/media/MediaPlayer;", "kotlin.jvm.PlatformType", "what", "", "extra", "onError"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
final class VideoPlayFragment$initview$4 implements MediaPlayer.OnErrorListener {
    final /* synthetic */ VideoPlayFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VideoPlayFragment$initview$4(VideoPlayFragment videoPlayFragment) {
        this.this$0 = videoPlayFragment;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Activity mActivity;
        mActivity = this.this$0.getMActivity();
        if (mActivity == null) {
            return true;
        }
        new VideoErrorDialog(mActivity).setOnCloseListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.custom_call.VideoPlayFragment$initview$4$$special$$inlined$let$lambda$1
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
                VideoPlayFragment$initview$4.this.this$0.startVideo(false);
                Pdlog.m3274e(VideoPlayFragment$initview$4.this.this$0.getTAG(), "OnError重新加载视频");
            }
        });
        return true;
    }
}
