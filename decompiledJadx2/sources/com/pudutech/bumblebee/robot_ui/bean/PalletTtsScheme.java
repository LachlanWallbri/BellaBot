package com.pudutech.bumblebee.robot_ui.bean;

import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import kotlin.Metadata;

/* compiled from: PalletTtsScheme.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020\u0011H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!¨\u0006&"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/bean/PalletTtsScheme;", "", "()V", "interval", "", "getInterval", "()J", "setInterval", "(J)V", "isTtsPalletArrivedOn", "", "()Z", "setTtsPalletArrivedOn", "(Z)V", "isTtsPalletMovingOn", "setTtsPalletMovingOn", "locale", "", "getLocale", "()Ljava/lang/String;", "setLocale", "(Ljava/lang/String;)V", "mid", "getMid", "setMid", "taskName", "getTaskName", "setTaskName", "ttsPalletArrived", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "getTtsPalletArrived", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "setTtsPalletArrived", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;)V", "ttsPalletMoving", "getTtsPalletMoving", "setTtsPalletMoving", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class PalletTtsScheme {
    private boolean isTtsPalletArrivedOn;
    private boolean isTtsPalletMovingOn;
    private String locale;
    private long mid;
    private TtsVoiceHelper.TtsConfigData ttsPalletArrived;
    private TtsVoiceHelper.TtsConfigData ttsPalletMoving;
    private String taskName = "";
    private long interval = 15;

    public final long getMid() {
        return this.mid;
    }

    public final void setMid(long j) {
        this.mid = j;
    }

    public final String getLocale() {
        return this.locale;
    }

    public final void setLocale(String str) {
        this.locale = str;
    }

    public final String getTaskName() {
        return this.taskName;
    }

    public final void setTaskName(String str) {
        this.taskName = str;
    }

    public final TtsVoiceHelper.TtsConfigData getTtsPalletArrived() {
        return this.ttsPalletArrived;
    }

    public final void setTtsPalletArrived(TtsVoiceHelper.TtsConfigData ttsConfigData) {
        this.ttsPalletArrived = ttsConfigData;
    }

    /* renamed from: isTtsPalletArrivedOn, reason: from getter */
    public final boolean getIsTtsPalletArrivedOn() {
        return this.isTtsPalletArrivedOn;
    }

    public final void setTtsPalletArrivedOn(boolean z) {
        this.isTtsPalletArrivedOn = z;
    }

    public final TtsVoiceHelper.TtsConfigData getTtsPalletMoving() {
        return this.ttsPalletMoving;
    }

    public final void setTtsPalletMoving(TtsVoiceHelper.TtsConfigData ttsConfigData) {
        this.ttsPalletMoving = ttsConfigData;
    }

    /* renamed from: isTtsPalletMovingOn, reason: from getter */
    public final boolean getIsTtsPalletMovingOn() {
        return this.isTtsPalletMovingOn;
    }

    public final void setTtsPalletMovingOn(boolean z) {
        this.isTtsPalletMovingOn = z;
    }

    public final long getInterval() {
        return this.interval;
    }

    public final void setInterval(long j) {
        this.interval = j;
    }

    public String toString() {
        return "PalletTtsScheme(mid=" + this.mid + ", locale=" + this.locale + ", taskName=" + this.taskName + ", ttsPalletArrived=" + String.valueOf(this.ttsPalletArrived) + ", isTtsPalletArrivedOn=" + this.isTtsPalletArrivedOn + ", ttsPalletMoving=" + String.valueOf(this.ttsPalletMoving) + ", isTtsPalletMovingOn=" + this.isTtsPalletMovingOn + ", interval=" + this.interval + ')';
    }
}
