package com.pudutech.peanut.robot_ui.bean;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeComeModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\fJ.\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u0005\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/bean/VoiceBean;", "", AIUIConstant.KEY_CONTENT, "", "path", "isSelect", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "()Ljava/lang/Boolean;", "setSelect", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getPath", "setPath", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/pudutech/peanut/robot_ui/bean/VoiceBean;", "equals", "other", "hashCode", "", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class VoiceBean {
    private String content;
    private Boolean isSelect;
    private String path;

    public static /* synthetic */ VoiceBean copy$default(VoiceBean voiceBean, String str, String str2, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = voiceBean.content;
        }
        if ((i & 2) != 0) {
            str2 = voiceBean.path;
        }
        if ((i & 4) != 0) {
            bool = voiceBean.isSelect;
        }
        return voiceBean.copy(str, str2, bool);
    }

    /* renamed from: component1, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* renamed from: component3, reason: from getter */
    public final Boolean getIsSelect() {
        return this.isSelect;
    }

    public final VoiceBean copy(String content, String path, Boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(path, "path");
        return new VoiceBean(content, path, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VoiceBean)) {
            return false;
        }
        VoiceBean voiceBean = (VoiceBean) other;
        return Intrinsics.areEqual(this.content, voiceBean.content) && Intrinsics.areEqual(this.path, voiceBean.path) && Intrinsics.areEqual(this.isSelect, voiceBean.isSelect);
    }

    public int hashCode() {
        String str = this.content;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.path;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Boolean bool = this.isSelect;
        return hashCode2 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "VoiceBean(content=" + this.content + ", path=" + this.path + ", isSelect=" + this.isSelect + ")";
    }

    public VoiceBean(String content, String path, Boolean bool) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(path, "path");
        this.content = content;
        this.path = path;
        this.isSelect = bool;
    }

    public /* synthetic */ VoiceBean(String str, String str2, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? false : bool);
    }

    public final String getContent() {
        return this.content;
    }

    public final String getPath() {
        return this.path;
    }

    public final Boolean isSelect() {
        return this.isSelect;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final void setPath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.path = str;
    }

    public final void setSelect(Boolean bool) {
        this.isSelect = bool;
    }
}
