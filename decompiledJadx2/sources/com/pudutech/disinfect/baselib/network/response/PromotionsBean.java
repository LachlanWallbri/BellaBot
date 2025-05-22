package com.pudutech.disinfect.baselib.network.response;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotActiveStatusResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J5\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\n\"\u0004\b\u0013\u0010\f¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/PromotionsBean;", "", "title", "", AIUIConstant.KEY_CONTENT, "image_url", "isSelect", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getImage_url", "setImage_url", "()Z", "setSelect", "(Z)V", "getTitle", "setTitle", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class PromotionsBean {
    private String content;
    private String image_url;
    private boolean isSelect;
    private String title;

    public static /* synthetic */ PromotionsBean copy$default(PromotionsBean promotionsBean, String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = promotionsBean.title;
        }
        if ((i & 2) != 0) {
            str2 = promotionsBean.content;
        }
        if ((i & 4) != 0) {
            str3 = promotionsBean.image_url;
        }
        if ((i & 8) != 0) {
            z = promotionsBean.isSelect;
        }
        return promotionsBean.copy(str, str2, str3, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component3, reason: from getter */
    public final String getImage_url() {
        return this.image_url;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final PromotionsBean copy(String title, String content, String image_url, boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        return new PromotionsBean(title, content, image_url, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PromotionsBean)) {
            return false;
        }
        PromotionsBean promotionsBean = (PromotionsBean) other;
        return Intrinsics.areEqual(this.title, promotionsBean.title) && Intrinsics.areEqual(this.content, promotionsBean.content) && Intrinsics.areEqual(this.image_url, promotionsBean.image_url) && this.isSelect == promotionsBean.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.title;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.content;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.image_url;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "PromotionsBean(title=" + this.title + ", content=" + this.content + ", image_url=" + this.image_url + ", isSelect=" + this.isSelect + ")";
    }

    public PromotionsBean(String str, String content, String str2, boolean z) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.title = str;
        this.content = content;
        this.image_url = str2;
        this.isSelect = z;
    }

    public /* synthetic */ PromotionsBean(String str, String str2, String str3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? false : z);
    }

    public final String getContent() {
        return this.content;
    }

    public final String getImage_url() {
        return this.image_url;
    }

    public final String getTitle() {
        return this.title;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final void setImage_url(String str) {
        this.image_url = str;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }

    public final void setTitle(String str) {
        this.title = str;
    }
}
