package com.pudutech.robot.opensdk.bean;

import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: PageBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/PageBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "pageSize", "", "pageIndex", "(II)V", "getPageIndex", "()I", "getPageSize", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class PageBody implements IBody {
    private final int pageIndex;
    private final int pageSize;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PageBody() {
        this(r0, r0, 3, null);
        int i = 0;
    }

    public static /* synthetic */ PageBody copy$default(PageBody pageBody, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = pageBody.pageSize;
        }
        if ((i3 & 2) != 0) {
            i2 = pageBody.pageIndex;
        }
        return pageBody.copy(i, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getPageSize() {
        return this.pageSize;
    }

    /* renamed from: component2, reason: from getter */
    public final int getPageIndex() {
        return this.pageIndex;
    }

    public final PageBody copy(int pageSize, int pageIndex) {
        return new PageBody(pageSize, pageIndex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PageBody)) {
            return false;
        }
        PageBody pageBody = (PageBody) other;
        return this.pageSize == pageBody.pageSize && this.pageIndex == pageBody.pageIndex;
    }

    public int hashCode() {
        return (Integer.hashCode(this.pageSize) * 31) + Integer.hashCode(this.pageIndex);
    }

    public String toString() {
        return "PageBody(pageSize=" + this.pageSize + ", pageIndex=" + this.pageIndex + ")";
    }

    public PageBody(int i, int i2) {
        this.pageSize = i;
        this.pageIndex = i2;
    }

    public /* synthetic */ PageBody(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public final int getPageIndex() {
        return this.pageIndex;
    }

    public final int getPageSize() {
        return this.pageSize;
    }
}
