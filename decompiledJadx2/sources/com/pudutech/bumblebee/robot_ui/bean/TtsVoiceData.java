package com.pudutech.bumblebee.robot_ui.bean;

import kotlin.Metadata;

/* compiled from: TtsVoiceData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010+\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u000eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\f\"\u0004\b!\u0010\u000eR\u001e\u0010\"\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR\u001c\u0010%\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\f\"\u0004\b'\u0010\u000eR\u001c\u0010(\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\f\"\u0004\b*\u0010\u000e¨\u0006,"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/bean/TtsVoiceData;", "", "()V", "currentTime", "", "getCurrentTime", "()J", "setCurrentTime", "(J)V", "expand", "", "getExpand", "()Ljava/lang/String;", "setExpand", "(Ljava/lang/String;)V", "extraInfo", "getExtraInfo", "setExtraInfo", "fliePath", "getFliePath", "setFliePath", "isOldData", "", "()Z", "setOldData", "(Z)V", "isSelect", "setSelect", "locale", "getLocale", "setLocale", "md5", "getMd5", "setMd5", "mid", "getMid", "setMid", "name", "getName", "setName", "ttsType", "getTtsType", "setTtsType", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class TtsVoiceData {
    private long currentTime;
    private String expand;
    private String extraInfo;
    private String fliePath;
    private boolean isOldData;
    private boolean isSelect;
    private String locale;
    private String md5;
    private long mid;
    private String name;
    private String ttsType;

    public final long getMid() {
        return this.mid;
    }

    public final void setMid(long j) {
        this.mid = j;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final void setMd5(String str) {
        this.md5 = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getFliePath() {
        return this.fliePath;
    }

    public final void setFliePath(String str) {
        this.fliePath = str;
    }

    public final long getCurrentTime() {
        return this.currentTime;
    }

    public final void setCurrentTime(long j) {
        this.currentTime = j;
    }

    /* renamed from: isSelect, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }

    public final String getLocale() {
        return this.locale;
    }

    public final void setLocale(String str) {
        this.locale = str;
    }

    public final String getTtsType() {
        return this.ttsType;
    }

    public final void setTtsType(String str) {
        this.ttsType = str;
    }

    /* renamed from: isOldData, reason: from getter */
    public final boolean getIsOldData() {
        return this.isOldData;
    }

    public final void setOldData(boolean z) {
        this.isOldData = z;
    }

    public final String getExtraInfo() {
        return this.extraInfo;
    }

    public final void setExtraInfo(String str) {
        this.extraInfo = str;
    }

    public final String getExpand() {
        return this.expand;
    }

    public final void setExpand(String str) {
        this.expand = str;
    }

    public String toString() {
        return "TtsVoiceData(mid=" + this.mid + ", md5=" + this.md5 + ", name=" + this.name + ", fliePath=" + this.fliePath + ", currentTime=" + this.currentTime + ", isSelect=" + this.isSelect + ", locale=" + this.locale + ", ttsType=" + this.ttsType + ", isOldData=" + this.isOldData + ", extraInfo=" + this.extraInfo + ", expand=" + this.expand + ')';
    }
}
