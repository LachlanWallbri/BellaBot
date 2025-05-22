package com.pudutech.mirsdkwrap.lib.map;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Floor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bHÆ\u0003J=\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/map/Floor;", "", "def", "", "fname", "index", "", "maps", "", "(Ljava/lang/String;Ljava/lang/String;ILjava/util/List;)V", "getDef", "()Ljava/lang/String;", "setDef", "(Ljava/lang/String;)V", "getFname", "getIndex", "()I", "getMaps", "()Ljava/util/List;", "setMaps", "(Ljava/util/List;)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class Floor {
    private String def;
    private final String fname;
    private final int index;
    private List<String> maps;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Floor copy$default(Floor floor, String str, String str2, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = floor.def;
        }
        if ((i2 & 2) != 0) {
            str2 = floor.fname;
        }
        if ((i2 & 4) != 0) {
            i = floor.index;
        }
        if ((i2 & 8) != 0) {
            list = floor.maps;
        }
        return floor.copy(str, str2, i, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDef() {
        return this.def;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFname() {
        return this.fname;
    }

    /* renamed from: component3, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    public final List<String> component4() {
        return this.maps;
    }

    public final Floor copy(String def, String fname, int index, List<String> maps) {
        return new Floor(def, fname, index, maps);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Floor)) {
            return false;
        }
        Floor floor = (Floor) other;
        return Intrinsics.areEqual(this.def, floor.def) && Intrinsics.areEqual(this.fname, floor.fname) && this.index == floor.index && Intrinsics.areEqual(this.maps, floor.maps);
    }

    public int hashCode() {
        String str = this.def;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.fname;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.index) * 31;
        List<String> list = this.maps;
        return hashCode2 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "Floor(def=" + this.def + ", fname=" + this.fname + ", index=" + this.index + ", maps=" + this.maps + ")";
    }

    public Floor(String str, String str2, int i, List<String> list) {
        this.def = str;
        this.fname = str2;
        this.index = i;
        this.maps = list;
    }

    public final String getDef() {
        return this.def;
    }

    public final String getFname() {
        return this.fname;
    }

    public final int getIndex() {
        return this.index;
    }

    public final List<String> getMaps() {
        return this.maps;
    }

    public final void setDef(String str) {
        this.def = str;
    }

    public final void setMaps(List<String> list) {
        this.maps = list;
    }
}
