package com.pudutech.bumblebee.robot_ui.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VolumeBroadcastReceiver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/VolumeBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VolumeBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = getClass().getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        if (intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == 2) {
            Pdlog.m3273d(this.TAG, "onReceive on change");
            int systemMaxVolume = SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getSystemMaxVolume();
            int systemCurrentVolume = SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().getSystemCurrentVolume();
            if (systemCurrentVolume != systemMaxVolume) {
                Pdlog.m3273d(this.TAG, "onReceive , systemMaxVolume need set , systemMaxVolume = " + systemMaxVolume + " , systemCurrentVolume " + systemCurrentVolume + ' ');
                SystemSoundManager.INSTANCE.getBtnVoiceAndMusicHelper().setVoice(systemMaxVolume);
            }
        }
    }
}
