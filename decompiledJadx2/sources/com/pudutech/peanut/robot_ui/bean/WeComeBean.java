package com.pudutech.peanut.robot_ui.bean;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeComeModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001c\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0015JD\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0003HÖ\u0001J\t\u0010$\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\b\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010¨\u0006%"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/bean/WeComeBean;", "", "id", "", "type", AIUIConstant.KEY_CONTENT, "", "img", "isFinish", "", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getId", "()I", "setId", "(I)V", "getImg", "setImg", "()Ljava/lang/Boolean;", "setFinish", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getType", "component1", "component2", "component3", "component4", "component5", "copy", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/pudutech/peanut/robot_ui/bean/WeComeBean;", "equals", "other", "hashCode", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class WeComeBean {
    private String content;
    private int id;
    private String img;
    private Boolean isFinish;
    private final int type;

    public static /* synthetic */ WeComeBean copy$default(WeComeBean weComeBean, int i, int i2, String str, String str2, Boolean bool, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = weComeBean.id;
        }
        if ((i3 & 2) != 0) {
            i2 = weComeBean.type;
        }
        int i4 = i2;
        if ((i3 & 4) != 0) {
            str = weComeBean.content;
        }
        String str3 = str;
        if ((i3 & 8) != 0) {
            str2 = weComeBean.img;
        }
        String str4 = str2;
        if ((i3 & 16) != 0) {
            bool = weComeBean.isFinish;
        }
        return weComeBean.copy(i, i4, str3, str4, bool);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component4, reason: from getter */
    public final String getImg() {
        return this.img;
    }

    /* renamed from: component5, reason: from getter */
    public final Boolean getIsFinish() {
        return this.isFinish;
    }

    public final WeComeBean copy(int id, int type, String content, String img, Boolean isFinish) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        return new WeComeBean(id, type, content, img, isFinish);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WeComeBean)) {
            return false;
        }
        WeComeBean weComeBean = (WeComeBean) other;
        return this.id == weComeBean.id && this.type == weComeBean.type && Intrinsics.areEqual(this.content, weComeBean.content) && Intrinsics.areEqual(this.img, weComeBean.img) && Intrinsics.areEqual(this.isFinish, weComeBean.isFinish);
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.id) * 31) + Integer.hashCode(this.type)) * 31;
        String str = this.content;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.img;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Boolean bool = this.isFinish;
        return hashCode3 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "WeComeBean(id=" + this.id + ", type=" + this.type + ", content=" + this.content + ", img=" + this.img + ", isFinish=" + this.isFinish + ")";
    }

    public WeComeBean(int i, int i2, String content, String str, Boolean bool) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.id = i;
        this.type = i2;
        this.content = content;
        this.img = str;
        this.isFinish = bool;
    }

    public /* synthetic */ WeComeBean(int i, int i2, String str, String str2, Boolean bool, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i3 & 2) != 0 ? 1 : i2, str, (i3 & 8) != 0 ? (String) null : str2, (i3 & 16) != 0 ? false : bool);
    }

    public final String getContent() {
        return this.content;
    }

    public final int getId() {
        return this.id;
    }

    public final String getImg() {
        return this.img;
    }

    public final int getType() {
        return this.type;
    }

    public final Boolean isFinish() {
        return this.isFinish;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final void setFinish(Boolean bool) {
        this.isFinish = bool;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final void setImg(String str) {
        this.img = str;
    }
}
