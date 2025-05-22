package com.pudutech.mirsdkwrap.lib.move.bean;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveErrors.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\n\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u0005HÆ\u0003J'\u0010\u000b\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u0005HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R.\u0010\u0002\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveErrors;", "", "list", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveError;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getList", "()Ljava/util/ArrayList;", "setList", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class MoveErrors {
    private ArrayList<MoveError> list;

    public MoveErrors() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MoveErrors copy$default(MoveErrors moveErrors, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = moveErrors.list;
        }
        return moveErrors.copy(arrayList);
    }

    public final ArrayList<MoveError> component1() {
        return this.list;
    }

    public final MoveErrors copy(ArrayList<MoveError> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        return new MoveErrors(list);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof MoveErrors) && Intrinsics.areEqual(this.list, ((MoveErrors) other).list);
        }
        return true;
    }

    public int hashCode() {
        ArrayList<MoveError> arrayList = this.list;
        if (arrayList != null) {
            return arrayList.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "MoveErrors(list=" + this.list + ")";
    }

    public MoveErrors(ArrayList<MoveError> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        this.list = list;
    }

    public /* synthetic */ MoveErrors(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : arrayList);
    }

    public final ArrayList<MoveError> getList() {
        return this.list;
    }

    public final void setList(ArrayList<MoveError> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.list = arrayList;
    }
}
