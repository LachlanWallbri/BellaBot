package com.pudutech.freeinstall_ui.adapter;

import com.pudutech.opengl_draw.geometry.Vector3;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddDoublePathAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J-\u0010\u0018\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/DoublePathListItem;", "", "topoTrack", "", "Lcom/pudutech/opengl_draw/geometry/Vector3;", "name", "", "isSelect", "", "(Ljava/util/List;Ljava/lang/String;Z)V", "()Z", "setSelect", "(Z)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getTopoTrack", "()Ljava/util/List;", "setTopoTrack", "(Ljava/util/List;)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class DoublePathListItem {
    private boolean isSelect;
    private String name;
    private List<Vector3> topoTrack;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DoublePathListItem copy$default(DoublePathListItem doublePathListItem, List list, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            list = doublePathListItem.topoTrack;
        }
        if ((i & 2) != 0) {
            str = doublePathListItem.name;
        }
        if ((i & 4) != 0) {
            z = doublePathListItem.isSelect;
        }
        return doublePathListItem.copy(list, str, z);
    }

    public final List<Vector3> component1() {
        return this.topoTrack;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final DoublePathListItem copy(List<Vector3> topoTrack, String name, boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(topoTrack, "topoTrack");
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new DoublePathListItem(topoTrack, name, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DoublePathListItem)) {
            return false;
        }
        DoublePathListItem doublePathListItem = (DoublePathListItem) other;
        return Intrinsics.areEqual(this.topoTrack, doublePathListItem.topoTrack) && Intrinsics.areEqual(this.name, doublePathListItem.name) && this.isSelect == doublePathListItem.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        List<Vector3> list = this.topoTrack;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        String str = this.name;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "DoublePathListItem(topoTrack=" + this.topoTrack + ", name=" + this.name + ", isSelect=" + this.isSelect + ")";
    }

    public DoublePathListItem(List<Vector3> topoTrack, String name, boolean z) {
        Intrinsics.checkParameterIsNotNull(topoTrack, "topoTrack");
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.topoTrack = topoTrack;
        this.name = name;
        this.isSelect = z;
    }

    public /* synthetic */ DoublePathListItem(List list, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, str, (i & 4) != 0 ? false : z);
    }

    public final String getName() {
        return this.name;
    }

    public final List<Vector3> getTopoTrack() {
        return this.topoTrack;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }

    public final void setTopoTrack(List<Vector3> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.topoTrack = list;
    }
}
