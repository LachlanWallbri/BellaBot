package com.pudutech.disinfect.baselib.network.response;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotActiveStatusResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\u0002\u0010\rJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u0011\u0010$\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nHÆ\u0003J\u0011\u0010%\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nHÆ\u0003Ja\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nHÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\u0003HÖ\u0001J\t\u0010+\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0013\"\u0004\b\u001c\u0010\u0015R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015¨\u0006,"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/FeatBean;", "", "state", "", "count", "answer", "", "extend", "type", "rows", "", "Lcom/pudutech/disinfect/baselib/network/response/FeaturedBean;", "list", "(IILjava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;)V", "getAnswer", "()Ljava/lang/String;", "setAnswer", "(Ljava/lang/String;)V", "getCount", "()I", "setCount", "(I)V", "getExtend", "setExtend", "getList", "()Ljava/util/List;", "getRows", "getState", "setState", "getType", "setType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class FeatBean {
    private String answer;
    private int count;
    private String extend;
    private final List<FeaturedBean> list;
    private final List<FeaturedBean> rows;
    private int state;
    private int type;

    public static /* synthetic */ FeatBean copy$default(FeatBean featBean, int i, int i2, String str, String str2, int i3, List list, List list2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = featBean.state;
        }
        if ((i4 & 2) != 0) {
            i2 = featBean.count;
        }
        int i5 = i2;
        if ((i4 & 4) != 0) {
            str = featBean.answer;
        }
        String str3 = str;
        if ((i4 & 8) != 0) {
            str2 = featBean.extend;
        }
        String str4 = str2;
        if ((i4 & 16) != 0) {
            i3 = featBean.type;
        }
        int i6 = i3;
        if ((i4 & 32) != 0) {
            list = featBean.rows;
        }
        List list3 = list;
        if ((i4 & 64) != 0) {
            list2 = featBean.list;
        }
        return featBean.copy(i, i5, str3, str4, i6, list3, list2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getState() {
        return this.state;
    }

    /* renamed from: component2, reason: from getter */
    public final int getCount() {
        return this.count;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAnswer() {
        return this.answer;
    }

    /* renamed from: component4, reason: from getter */
    public final String getExtend() {
        return this.extend;
    }

    /* renamed from: component5, reason: from getter */
    public final int getType() {
        return this.type;
    }

    public final List<FeaturedBean> component6() {
        return this.rows;
    }

    public final List<FeaturedBean> component7() {
        return this.list;
    }

    public final FeatBean copy(int state, int count, String answer, String extend, int type, List<FeaturedBean> rows, List<FeaturedBean> list) {
        Intrinsics.checkParameterIsNotNull(answer, "answer");
        return new FeatBean(state, count, answer, extend, type, rows, list);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeatBean)) {
            return false;
        }
        FeatBean featBean = (FeatBean) other;
        return this.state == featBean.state && this.count == featBean.count && Intrinsics.areEqual(this.answer, featBean.answer) && Intrinsics.areEqual(this.extend, featBean.extend) && this.type == featBean.type && Intrinsics.areEqual(this.rows, featBean.rows) && Intrinsics.areEqual(this.list, featBean.list);
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.state) * 31) + Integer.hashCode(this.count)) * 31;
        String str = this.answer;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.extend;
        int hashCode3 = (((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.type)) * 31;
        List<FeaturedBean> list = this.rows;
        int hashCode4 = (hashCode3 + (list != null ? list.hashCode() : 0)) * 31;
        List<FeaturedBean> list2 = this.list;
        return hashCode4 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "FeatBean(state=" + this.state + ", count=" + this.count + ", answer=" + this.answer + ", extend=" + this.extend + ", type=" + this.type + ", rows=" + this.rows + ", list=" + this.list + ")";
    }

    public FeatBean(int i, int i2, String answer, String str, int i3, List<FeaturedBean> list, List<FeaturedBean> list2) {
        Intrinsics.checkParameterIsNotNull(answer, "answer");
        this.state = i;
        this.count = i2;
        this.answer = answer;
        this.extend = str;
        this.type = i3;
        this.rows = list;
        this.list = list2;
    }

    public final String getAnswer() {
        return this.answer;
    }

    public final int getCount() {
        return this.count;
    }

    public final String getExtend() {
        return this.extend;
    }

    public final List<FeaturedBean> getList() {
        return this.list;
    }

    public final List<FeaturedBean> getRows() {
        return this.rows;
    }

    public final int getState() {
        return this.state;
    }

    public final int getType() {
        return this.type;
    }

    public final void setAnswer(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.answer = str;
    }

    public final void setCount(int i) {
        this.count = i;
    }

    public final void setExtend(String str) {
        this.extend = str;
    }

    public final void setState(int i) {
        this.state = i;
    }

    public final void setType(int i) {
        this.type = i;
    }
}
