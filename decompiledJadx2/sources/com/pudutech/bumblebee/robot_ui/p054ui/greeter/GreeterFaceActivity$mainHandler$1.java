package com.pudutech.bumblebee.robot_ui.p054ui.greeter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: GreeterFaceActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/greeter/GreeterFaceActivity$mainHandler$1", "Landroid/os/Handler;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class GreeterFaceActivity$mainHandler$1 extends Handler {
    final /* synthetic */ GreeterFaceActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GreeterFaceActivity$mainHandler$1(GreeterFaceActivity greeterFaceActivity, Looper looper) {
        super(looper);
        this.this$0 = greeterFaceActivity;
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        int i;
        int i2;
        int i3;
        CoroutineScope coroutineScope;
        int i4;
        long j;
        String str;
        int i5;
        int i6;
        String str2;
        int i7;
        String str3;
        String str4;
        super.handleMessage(msg);
        Integer valueOf = msg != null ? Integer.valueOf(msg.what) : null;
        i = this.this$0.WHAT_GREETER_TYPE;
        if (valueOf != null && valueOf.intValue() == i) {
            str3 = this.this$0.TAG;
            Pdlog.m3273d(str3, "WHAT_GREETER--进行播报");
            if (Constans.INSTANCE.isLockedMachine()) {
                this.this$0.stopGreeterVoice();
                str4 = this.this$0.TAG;
                Pdlog.m3273d(str4, "WHAT_GREETER_TYPE locked haved");
                return;
            }
            if (this.this$0.getVoiceTaskWrapper() == null) {
                this.this$0.setVoiceTaskWrapper(new TtsVoiceWrapperPlayer.VoiceTaskWrapper(new VoiceTask(-1L, VoiceItem.voice32_1), TtsVoiceWrapperPlayer.PlayType.GREETER_TYPE, null, 4, null));
            }
            TtsVoiceWrapperPlayer ttsVoiceWrapperPlayer = TtsVoiceWrapperPlayer.INSTANCE;
            TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.this$0.getVoiceTaskWrapper();
            if (voiceTaskWrapper == null) {
                Intrinsics.throwNpe();
            }
            ttsVoiceWrapperPlayer.play(voiceTaskWrapper, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.greeter.GreeterFaceActivity$mainHandler$1$handleMessage$1
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
                    boolean z;
                    boolean z2;
                    boolean z3;
                    TouchSensorEventHelper touchSensorEventHelper;
                    int i8;
                    long j2;
                    String str5;
                    long j3;
                    String str6;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    z = GreeterFaceActivity$mainHandler$1.this.this$0.isStopGreeterVoice;
                    if (z) {
                        GreeterFaceActivity$mainHandler$1.this.this$0.stopGreeterVoice();
                        str6 = GreeterFaceActivity$mainHandler$1.this.this$0.TAG;
                        Pdlog.m3273d(str6, "handleMessage释放语音的播放");
                        return;
                    }
                    if (it == AudioTrackUtils.AudioPlayEvent.COMPLETE) {
                        z2 = GreeterFaceActivity$mainHandler$1.this.this$0.isOnDestroy;
                        if (z2) {
                            return;
                        }
                        z3 = GreeterFaceActivity$mainHandler$1.this.this$0.isLooperPlay;
                        if (!z3) {
                            touchSensorEventHelper = GreeterFaceActivity$mainHandler$1.this.this$0.touchSensorEventHelper;
                            touchSensorEventHelper.setCanHandle(true, true);
                            AiVoiceManager.INSTANCE.startWakeUp(0, 0);
                            return;
                        }
                        GreeterFaceActivity$mainHandler$1 greeterFaceActivity$mainHandler$1 = GreeterFaceActivity$mainHandler$1.this;
                        i8 = greeterFaceActivity$mainHandler$1.this$0.WHAT_GREETER_TYPE;
                        j2 = GreeterFaceActivity$mainHandler$1.this.this$0.mGreeterTypeInterval;
                        greeterFaceActivity$mainHandler$1.sendEmptyMessageDelayed(i8, j2);
                        str5 = GreeterFaceActivity$mainHandler$1.this.this$0.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("handleMessage完成之后一直轮播：");
                        j3 = GreeterFaceActivity$mainHandler$1.this.this$0.mGreeterTypeInterval;
                        sb.append(j3);
                        Pdlog.m3273d(str5, sb.toString());
                    }
                }
            });
            return;
        }
        i2 = this.this$0.WHAT_LOCK_MOTOR;
        if (valueOf != null && valueOf.intValue() == i2) {
            i3 = this.this$0.mLockMotorCount;
            if (i3 == 5) {
                str2 = this.this$0.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("WHAT_LOCK_MOTOR锁电机已经次数用完:");
                i7 = this.this$0.mLockMotorCount;
                sb.append(i7);
                Pdlog.m3273d(str2, sb.toString());
                return;
            }
            coroutineScope = this.this$0.mCurScope;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new GreeterFaceActivity$mainHandler$1$handleMessage$2(null), 3, null);
            i4 = this.this$0.WHAT_LOCK_MOTOR;
            j = this.this$0.mLockMotorInterval;
            sendEmptyMessageDelayed(i4, j);
            str = this.this$0.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("WHAT_LOCK_MOTOR锁住电机:");
            i5 = this.this$0.mLockMotorCount;
            sb2.append(i5);
            Pdlog.m3273d(str, sb2.toString());
            GreeterFaceActivity greeterFaceActivity = this.this$0;
            i6 = greeterFaceActivity.mLockMotorCount;
            greeterFaceActivity.mLockMotorCount = i6 + 1;
        }
    }
}
