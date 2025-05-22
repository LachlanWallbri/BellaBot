package com.pudutech.peanut.robot_ui.bean;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeComeModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/bean/SolicitBean;", "", AIUIConstant.KEY_CONTENT, "", "path", "(Ljava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getPath", "setPath", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class SolicitBean {
    private String content;
    private String path;

    public static /* synthetic */ SolicitBean copy$default(SolicitBean solicitBean, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = solicitBean.content;
        }
        if ((i & 2) != 0) {
            str2 = solicitBean.path;
        }
        return solicitBean.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    public final SolicitBean copy(String content, String path) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(path, "path");
        return new SolicitBean(content, path);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SolicitBean)) {
            return false;
        }
        SolicitBean solicitBean = (SolicitBean) other;
        return Intrinsics.areEqual(this.content, solicitBean.content) && Intrinsics.areEqual(this.path, solicitBean.path);
    }

    public int hashCode() {
        String str = this.content;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.path;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SolicitBean(content=" + this.content + ", path=" + this.path + ")";
    }

    public SolicitBean(String content, String path) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(path, "path");
        this.content = content;
        this.path = path;
    }

    public final String getContent() {
        return this.content;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final void setPath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.path = str;
    }
}
