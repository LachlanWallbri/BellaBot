package com.pudutech.robot.opensdk.bean.resp;

import com.pudutech.robot.opensdk.bean.Destination;
import com.pudutech.robot.opensdk.interf.IBody;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: RespDestinationsBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\u0019\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003JA\u0010\u001b\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u0007HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R*\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012¨\u0006#"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/resp/RespDestinationsBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "destinations", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/opensdk/bean/Destination;", "Lkotlin/collections/ArrayList;", "total", "", "pageSize", "pageIndex", "(Ljava/util/ArrayList;III)V", "getDestinations", "()Ljava/util/ArrayList;", "setDestinations", "(Ljava/util/ArrayList;)V", "getPageIndex", "()I", "setPageIndex", "(I)V", "getPageSize", "setPageSize", "getTotal", "setTotal", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class RespDestinationsBody implements IBody {
    private ArrayList<Destination> destinations;
    private int pageIndex;
    private int pageSize;
    private int total;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RespDestinationsBody copy$default(RespDestinationsBody respDestinationsBody, ArrayList arrayList, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            arrayList = respDestinationsBody.destinations;
        }
        if ((i4 & 2) != 0) {
            i = respDestinationsBody.total;
        }
        if ((i4 & 4) != 0) {
            i2 = respDestinationsBody.pageSize;
        }
        if ((i4 & 8) != 0) {
            i3 = respDestinationsBody.pageIndex;
        }
        return respDestinationsBody.copy(arrayList, i, i2, i3);
    }

    public final ArrayList<Destination> component1() {
        return this.destinations;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTotal() {
        return this.total;
    }

    /* renamed from: component3, reason: from getter */
    public final int getPageSize() {
        return this.pageSize;
    }

    /* renamed from: component4, reason: from getter */
    public final int getPageIndex() {
        return this.pageIndex;
    }

    public final RespDestinationsBody copy(ArrayList<Destination> destinations, int total, int pageSize, int pageIndex) {
        Intrinsics.checkParameterIsNotNull(destinations, "destinations");
        return new RespDestinationsBody(destinations, total, pageSize, pageIndex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RespDestinationsBody)) {
            return false;
        }
        RespDestinationsBody respDestinationsBody = (RespDestinationsBody) other;
        return Intrinsics.areEqual(this.destinations, respDestinationsBody.destinations) && this.total == respDestinationsBody.total && this.pageSize == respDestinationsBody.pageSize && this.pageIndex == respDestinationsBody.pageIndex;
    }

    public int hashCode() {
        ArrayList<Destination> arrayList = this.destinations;
        return ((((((arrayList != null ? arrayList.hashCode() : 0) * 31) + Integer.hashCode(this.total)) * 31) + Integer.hashCode(this.pageSize)) * 31) + Integer.hashCode(this.pageIndex);
    }

    public String toString() {
        return "RespDestinationsBody(destinations=" + this.destinations + ", total=" + this.total + ", pageSize=" + this.pageSize + ", pageIndex=" + this.pageIndex + ")";
    }

    public RespDestinationsBody(ArrayList<Destination> destinations, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(destinations, "destinations");
        this.destinations = destinations;
        this.total = i;
        this.pageSize = i2;
        this.pageIndex = i3;
    }

    public final ArrayList<Destination> getDestinations() {
        return this.destinations;
    }

    public final void setDestinations(ArrayList<Destination> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.destinations = arrayList;
    }

    public final int getTotal() {
        return this.total;
    }

    public final void setTotal(int i) {
        this.total = i;
    }

    public final int getPageSize() {
        return this.pageSize;
    }

    public final void setPageSize(int i) {
        this.pageSize = i;
    }

    public /* synthetic */ RespDestinationsBody(ArrayList arrayList, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(arrayList, (i4 & 2) != 0 ? 0 : i, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    public final int getPageIndex() {
        return this.pageIndex;
    }

    public final void setPageIndex(int i) {
        this.pageIndex = i;
    }
}
