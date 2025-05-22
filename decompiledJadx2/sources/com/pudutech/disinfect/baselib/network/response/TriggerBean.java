package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotActiveStatusResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003JC\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0006HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006#"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/TriggerBean;", "", "text", "", "answer", "type", "", "image_url", "extend", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getAnswer", "()Ljava/lang/String;", "setAnswer", "(Ljava/lang/String;)V", "getExtend", "setExtend", "getImage_url", "setImage_url", "getText", "setText", "getType", "()I", "setType", "(I)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TriggerBean {
    private String answer;
    private String extend;
    private String image_url;
    private String text;
    private int type;

    public static /* synthetic */ TriggerBean copy$default(TriggerBean triggerBean, String str, String str2, int i, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = triggerBean.text;
        }
        if ((i2 & 2) != 0) {
            str2 = triggerBean.answer;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            i = triggerBean.type;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str3 = triggerBean.image_url;
        }
        String str6 = str3;
        if ((i2 & 16) != 0) {
            str4 = triggerBean.extend;
        }
        return triggerBean.copy(str, str5, i3, str6, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAnswer() {
        return this.answer;
    }

    /* renamed from: component3, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component4, reason: from getter */
    public final String getImage_url() {
        return this.image_url;
    }

    /* renamed from: component5, reason: from getter */
    public final String getExtend() {
        return this.extend;
    }

    public final TriggerBean copy(String text, String answer, int type, String image_url, String extend) {
        return new TriggerBean(text, answer, type, image_url, extend);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TriggerBean)) {
            return false;
        }
        TriggerBean triggerBean = (TriggerBean) other;
        return Intrinsics.areEqual(this.text, triggerBean.text) && Intrinsics.areEqual(this.answer, triggerBean.answer) && this.type == triggerBean.type && Intrinsics.areEqual(this.image_url, triggerBean.image_url) && Intrinsics.areEqual(this.extend, triggerBean.extend);
    }

    public int hashCode() {
        String str = this.text;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.answer;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.type)) * 31;
        String str3 = this.image_url;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.extend;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "TriggerBean(text=" + this.text + ", answer=" + this.answer + ", type=" + this.type + ", image_url=" + this.image_url + ", extend=" + this.extend + ")";
    }

    public TriggerBean(String str, String str2, int i, String str3, String str4) {
        this.text = str;
        this.answer = str2;
        this.type = i;
        this.image_url = str3;
        this.extend = str4;
    }

    public /* synthetic */ TriggerBean(String str, String str2, int i, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? -1 : i, str3, str4);
    }

    public final String getAnswer() {
        return this.answer;
    }

    public final String getExtend() {
        return this.extend;
    }

    public final String getImage_url() {
        return this.image_url;
    }

    public final String getText() {
        return this.text;
    }

    public final int getType() {
        return this.type;
    }

    public final void setAnswer(String str) {
        this.answer = str;
    }

    public final void setExtend(String str) {
        this.extend = str;
    }

    public final void setImage_url(String str) {
        this.image_url = str;
    }

    public final void setText(String str) {
        this.text = str;
    }

    public final void setType(int i) {
        this.type = i;
    }
}
