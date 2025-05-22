package com.pudutech.peanut.presenter.net.req;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PostTcslScidReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J#\u0010\u000b\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R*\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/net/req/PostTcslScidReq;", "", "scIds", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getScIds", "()Ljava/util/ArrayList;", "setScIds", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class PostTcslScidReq {
    private ArrayList<String> scIds;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PostTcslScidReq copy$default(PostTcslScidReq postTcslScidReq, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = postTcslScidReq.scIds;
        }
        return postTcslScidReq.copy(arrayList);
    }

    public final ArrayList<String> component1() {
        return this.scIds;
    }

    public final PostTcslScidReq copy(ArrayList<String> scIds) {
        Intrinsics.checkParameterIsNotNull(scIds, "scIds");
        return new PostTcslScidReq(scIds);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof PostTcslScidReq) && Intrinsics.areEqual(this.scIds, ((PostTcslScidReq) other).scIds);
        }
        return true;
    }

    public int hashCode() {
        ArrayList<String> arrayList = this.scIds;
        if (arrayList != null) {
            return arrayList.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "PostTcslScidReq(scIds=" + this.scIds + ")";
    }

    public PostTcslScidReq(ArrayList<String> scIds) {
        Intrinsics.checkParameterIsNotNull(scIds, "scIds");
        this.scIds = scIds;
    }

    public final ArrayList<String> getScIds() {
        return this.scIds;
    }

    public final void setScIds(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.scIds = arrayList;
    }
}
