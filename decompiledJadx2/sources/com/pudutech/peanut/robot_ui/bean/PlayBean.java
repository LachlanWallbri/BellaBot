package com.pudutech.peanut.robot_ui.bean;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeComeModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\fJ.\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u0005\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/bean/PlayBean;", "", AIUIConstant.KEY_CONTENT, "", "playState", "isPlay", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "()Ljava/lang/Boolean;", "setPlay", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getPlayState", "setPlayState", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/pudutech/peanut/robot_ui/bean/PlayBean;", "equals", "other", "hashCode", "", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class PlayBean {
    private String content;
    private Boolean isPlay;
    private String playState;

    public static /* synthetic */ PlayBean copy$default(PlayBean playBean, String str, String str2, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = playBean.content;
        }
        if ((i & 2) != 0) {
            str2 = playBean.playState;
        }
        if ((i & 4) != 0) {
            bool = playBean.isPlay;
        }
        return playBean.copy(str, str2, bool);
    }

    /* renamed from: component1, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPlayState() {
        return this.playState;
    }

    /* renamed from: component3, reason: from getter */
    public final Boolean getIsPlay() {
        return this.isPlay;
    }

    public final PlayBean copy(String content, String playState, Boolean isPlay) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(playState, "playState");
        return new PlayBean(content, playState, isPlay);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayBean)) {
            return false;
        }
        PlayBean playBean = (PlayBean) other;
        return Intrinsics.areEqual(this.content, playBean.content) && Intrinsics.areEqual(this.playState, playBean.playState) && Intrinsics.areEqual(this.isPlay, playBean.isPlay);
    }

    public int hashCode() {
        String str = this.content;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.playState;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Boolean bool = this.isPlay;
        return hashCode2 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "PlayBean(content=" + this.content + ", playState=" + this.playState + ", isPlay=" + this.isPlay + ")";
    }

    public PlayBean(String content, String playState, Boolean bool) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(playState, "playState");
        this.content = content;
        this.playState = playState;
        this.isPlay = bool;
    }

    public /* synthetic */ PlayBean(String str, String str2, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? false : bool);
    }

    public final String getContent() {
        return this.content;
    }

    public final String getPlayState() {
        return this.playState;
    }

    public final Boolean isPlay() {
        return this.isPlay;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final void setPlay(Boolean bool) {
        this.isPlay = bool;
    }

    public final void setPlayState(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.playState = str;
    }
}
