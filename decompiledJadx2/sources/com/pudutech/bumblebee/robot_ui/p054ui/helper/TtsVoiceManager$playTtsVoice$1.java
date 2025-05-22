package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.tts_sdk.PdTtsSdk;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import com.pudutech.tts_sdk.utils.CMediaPlayer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: TtsVoiceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\bH\u0016¨\u0006\f"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceManager$playTtsVoice$1", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "onComplete", "", "filePath", "", "onError", "code", "", NotificationCompat.CATEGORY_MESSAGE, "onProgress", "proses", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TtsVoiceManager$playTtsVoice$1 implements OnTtsListener {
    final /* synthetic */ boolean $isPlayFile;
    final /* synthetic */ Function0 $playFinish;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TtsVoiceManager$playTtsVoice$1(boolean z, Function0 function0) {
        this.$isPlayFile = z;
        this.$playFinish = function0;
    }

    @Override // com.pudutech.tts_sdk.tts.OnTtsListener
    public void onComplete(String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Pdlog.m3273d("TtsVoiceManager", "playTtsVoice() onComplete =" + filePath);
        if (this.$isPlayFile) {
            if (StringsKt.endsWith$default(filePath, ".mp3", false, 2, (Object) null)) {
                PdTtsSdk.INSTANCE.playTtsMp3File(filePath, new CMediaPlayer.OnPlayStateChangedListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceManager$playTtsVoice$1$onComplete$1
                    @Override // com.pudutech.tts_sdk.utils.CMediaPlayer.OnPlayStateChangedListener
                    public void onPlayFinished() {
                        Function0 function0 = TtsVoiceManager$playTtsVoice$1.this.$playFinish;
                        if (function0 != null) {
                        }
                        Pdlog.m3273d("TtsVoiceManager", "playTtsMp3File() onPlayFinished");
                    }

                    @Override // com.pudutech.tts_sdk.utils.CMediaPlayer.OnPlayStateChangedListener
                    public void onPlaying() {
                        Pdlog.m3273d("TtsVoiceManager", "playTtsMp3File() onPlaying");
                    }

                    @Override // com.pudutech.tts_sdk.utils.CMediaPlayer.OnPlayStateChangedListener
                    public void onPlayStop() {
                        Function0 function0 = TtsVoiceManager$playTtsVoice$1.this.$playFinish;
                        if (function0 != null) {
                        }
                        Pdlog.m3273d("TtsVoiceManager", "playTtsMp3File() onPlayStop()");
                    }
                });
            } else {
                PdTtsSdk.INSTANCE.playTtsPcmFile(filePath, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceManager$playTtsVoice$1$onComplete$2
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                        invoke2(audioPlayEvent);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AudioTrackUtils.AudioPlayEvent it) {
                        Function0 function0;
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        if ((it.ordinal() == AudioTrackUtils.AudioPlayEvent.STOP.ordinal() || it.ordinal() == AudioTrackUtils.AudioPlayEvent.COMPLETE.ordinal()) && (function0 = TtsVoiceManager$playTtsVoice$1.this.$playFinish) != null) {
                        }
                        Pdlog.m3273d("TtsVoiceManager", "playTtsPcmFile() AudioPlayEvent =" + it);
                    }
                });
            }
        }
    }

    @Override // com.pudutech.tts_sdk.tts.OnTtsListener
    public void onError(int code, String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Function0 function0 = this.$playFinish;
        if (function0 != null) {
        }
        Pdlog.m3273d("TtsVoiceManager", "playTtsVoice() onError =" + code + "--" + msg);
    }

    @Override // com.pudutech.tts_sdk.tts.OnTtsListener
    public void onProgress(int proses) {
        Pdlog.m3273d("TtsVoiceManager", "playTtsVoice() onProgress =" + proses);
    }
}
