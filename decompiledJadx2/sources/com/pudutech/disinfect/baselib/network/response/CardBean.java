package com.pudutech.disinfect.baselib.network.response;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotActiveStatusResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J%\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/CardBean;", "", "count", "", "list", "", "Lcom/pudutech/disinfect/baselib/network/response/CardInfo;", "(ILjava/util/List;)V", "getCount", "()I", "setCount", "(I)V", "getList", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class CardBean {
    private int count;
    private final List<CardInfo> list;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CardBean copy$default(CardBean cardBean, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = cardBean.count;
        }
        if ((i2 & 2) != 0) {
            list = cardBean.list;
        }
        return cardBean.copy(i, list);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCount() {
        return this.count;
    }

    public final List<CardInfo> component2() {
        return this.list;
    }

    public final CardBean copy(int count, List<CardInfo> list) {
        return new CardBean(count, list);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CardBean)) {
            return false;
        }
        CardBean cardBean = (CardBean) other;
        return this.count == cardBean.count && Intrinsics.areEqual(this.list, cardBean.list);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.count) * 31;
        List<CardInfo> list = this.list;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "CardBean(count=" + this.count + ", list=" + this.list + ")";
    }

    public CardBean(int i, List<CardInfo> list) {
        this.count = i;
        this.list = list;
    }

    public final int getCount() {
        return this.count;
    }

    public final List<CardInfo> getList() {
        return this.list;
    }

    public final void setCount(int i) {
        this.count = i;
    }
}
