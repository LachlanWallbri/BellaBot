package com.pudutech.bumblebee.robot_ui.p054ui.recycle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecycleVoice.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000bH\u0016J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0017\u001a\u00020\u000fH\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\b\u0010\u0019\u001a\u00020\u000fH\u0016J\u0010\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/recycle/RecycleVoice;", "Lcom/pudutech/bumblebee/robot_ui/ui/recycle/IRecycleVoice;", "()V", "handler", "Landroid/os/Handler;", "isPause", "", "messageWhatAdd", "", "messageWhatMin", AIUIConstant.KEY_TAG, "", "kotlin.jvm.PlatformType", "voiceTaskCount", TmpConstant.GROUP_OP_ADD, "", "forcePlay", "forceStop", "onPlayVoice", "onStopVoice", "play", "eventTag", "recordPause", "release", "remove", "resetCount", "setPause", "stop", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RecycleVoice implements IRecycleVoice {
    private volatile boolean isPause;
    private final int messageWhatAdd;
    private volatile int voiceTaskCount;
    private final String tag = getClass().getSimpleName();
    private final int messageWhatMin = 1;
    private final Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleVoice$handler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message it) {
            int i;
            int i2;
            int i3;
            String str;
            int i4;
            int i5;
            String str2;
            int i6;
            int i7 = it.what;
            i = RecycleVoice.this.messageWhatAdd;
            if (i7 == i) {
                RecycleVoice recycleVoice = RecycleVoice.this;
                i5 = recycleVoice.voiceTaskCount;
                recycleVoice.voiceTaskCount = i5 + 1;
                str2 = RecycleVoice.this.tag;
                StringBuilder sb = new StringBuilder();
                sb.append("Receive Add voiceTaskCount=");
                i6 = RecycleVoice.this.voiceTaskCount;
                sb.append(i6);
                Pdlog.m3275i(str2, sb.toString());
                RecycleVoice.this.stop();
            } else {
                int i8 = it.what;
                i2 = RecycleVoice.this.messageWhatMin;
                if (i8 == i2) {
                    RecycleVoice recycleVoice2 = RecycleVoice.this;
                    i3 = recycleVoice2.voiceTaskCount;
                    recycleVoice2.voiceTaskCount = i3 - 1;
                    str = RecycleVoice.this.tag;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Receive remove voiceTaskCount=");
                    i4 = RecycleVoice.this.voiceTaskCount;
                    sb2.append(i4);
                    Pdlog.m3275i(str, sb2.toString());
                    RecycleVoice recycleVoice3 = RecycleVoice.this;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("handler=");
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    Handler target = it.getTarget();
                    Intrinsics.checkExpressionValueIsNotNull(target, "it.target");
                    Looper looper = target.getLooper();
                    Intrinsics.checkExpressionValueIsNotNull(looper, "it.target.looper");
                    Thread thread = looper.getThread();
                    Intrinsics.checkExpressionValueIsNotNull(thread, "it.target.looper.thread");
                    sb3.append(thread.getName());
                    recycleVoice3.play(sb3.toString());
                }
            }
            return true;
        }
    });

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.recycle.IRecycleVoice
    public void recordPause(boolean isPause) {
        this.isPause = isPause;
        if (isPause) {
            this.voiceTaskCount = 0;
        }
        if (isPause) {
            stop();
        } else {
            play("recordPause");
        }
        Pdlog.m3275i(this.tag, "Update isPause status=" + isPause);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.recycle.IRecycleVoice
    public void setPause(boolean isPause) {
        this.isPause = isPause;
        if (isPause) {
            this.voiceTaskCount = 0;
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.recycle.IRecycleVoice
    public void resetCount() {
        this.voiceTaskCount = 0;
        this.isPause = false;
        play("resetCount");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.recycle.IRecycleVoice
    public void onPlayVoice() {
        add();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.recycle.IRecycleVoice
    public void onStopVoice() {
        remove();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.recycle.IRecycleVoice
    public void play(String eventTag) {
        Intrinsics.checkParameterIsNotNull(eventTag, "eventTag");
        Pdlog.m3275i(this.tag, "play: tag=" + eventTag + ", voiceTaskCount=" + this.voiceTaskCount + ", isPause=" + this.isPause);
        if (this.voiceTaskCount > 0 || this.isPause) {
            return;
        }
        Pdlog.m3275i(this.tag, "play music");
        MusicPlayerHelper.getInstance().startPlay(ModeEnum.RECYCLE);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.recycle.IRecycleVoice
    public void stop() {
        Pdlog.m3275i(this.tag, "stop music");
        MusicPlayerHelper.getInstance().pausePlay();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.recycle.IRecycleVoice
    public void forcePlay() {
        this.isPause = false;
        this.voiceTaskCount = 0;
        MusicPlayerHelper.getInstance().startPlay(ModeEnum.RECYCLE);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.recycle.IRecycleVoice
    public void forceStop() {
        this.isPause = true;
        this.voiceTaskCount = 0;
        MusicPlayerHelper.getInstance().pausePlay();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.recycle.IRecycleVoice
    public void release() {
        Pdlog.m3275i(this.tag, "release");
        this.isPause = true;
        this.voiceTaskCount = 0;
        stop();
        MusicPlayerHelper.getInstance().release();
    }

    private final void add() {
        this.handler.sendEmptyMessage(this.messageWhatAdd);
    }

    private final void remove() {
        this.handler.sendEmptyMessage(this.messageWhatMin);
    }
}
