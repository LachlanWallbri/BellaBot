package com.pudutech.robot.module.voice.data;

import kotlin.Metadata;

/* compiled from: VoicePlayTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H&J\u0006\u0010\u0017\u001a\u00020\u0013J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/data/BaseVoice;", "", "()V", "curPlayCount", "", "getCurPlayCount", "()I", "setCurPlayCount", "(I)V", "delayPlay", "", "getDelayPlay", "()J", "setDelayPlay", "(J)V", "playCount", "getPlayCount", "setPlayCount", "addPlayCount", "", "canPlay", "", "isFirstVoice", "resetPlayCount", "toString", "", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class BaseVoice {
    private int curPlayCount;
    private long delayPlay;
    private int playCount = 1;

    public abstract boolean isFirstVoice();

    public final long getDelayPlay() {
        return this.delayPlay;
    }

    public final void setDelayPlay(long j) {
        this.delayPlay = j;
    }

    public final int getPlayCount() {
        return this.playCount;
    }

    public final void setPlayCount(int i) {
        this.playCount = i;
    }

    protected final int getCurPlayCount() {
        return this.curPlayCount;
    }

    protected final void setCurPlayCount(int i) {
        this.curPlayCount = i;
    }

    public boolean canPlay() {
        int i = this.playCount;
        return i < 0 || i > this.curPlayCount;
    }

    public final void addPlayCount() {
        this.curPlayCount++;
    }

    public final void resetPlayCount() {
        this.playCount = 0;
    }

    public String toString() {
        return "delayPlay=" + this.delayPlay + ", playCount=" + this.playCount + ", curPlayCount=" + this.curPlayCount;
    }
}
