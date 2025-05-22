package com.pudutech.disinfect.baselib.network.response;

import com.pudutech.mirsdk.compat.topo.ConfigJson;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotActiveStatusResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003JK\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010!\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\f\"\u0004\b\u0015\u0010\u000eR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000e¨\u0006&"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/TableGroupBean;", "", "code", "", "name", "min", "max", ConfigJson.ALIAS, "isSelect", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getAlias", "()Ljava/lang/String;", "setAlias", "(Ljava/lang/String;)V", "getCode", "setCode", "()Z", "setSelect", "(Z)V", "getMax", "setMax", "getMin", "setMin", "getName", "setName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TableGroupBean {
    private String alias;
    private String code;
    private boolean isSelect;
    private String max;
    private String min;
    private String name;

    public static /* synthetic */ TableGroupBean copy$default(TableGroupBean tableGroupBean, String str, String str2, String str3, String str4, String str5, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tableGroupBean.code;
        }
        if ((i & 2) != 0) {
            str2 = tableGroupBean.name;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = tableGroupBean.min;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = tableGroupBean.max;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = tableGroupBean.alias;
        }
        String str9 = str5;
        if ((i & 32) != 0) {
            z = tableGroupBean.isSelect;
        }
        return tableGroupBean.copy(str, str6, str7, str8, str9, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMin() {
        return this.min;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMax() {
        return this.max;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAlias() {
        return this.alias;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final TableGroupBean copy(String code, String name, String min, String max, String alias, boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(code, "code");
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new TableGroupBean(code, name, min, max, alias, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TableGroupBean)) {
            return false;
        }
        TableGroupBean tableGroupBean = (TableGroupBean) other;
        return Intrinsics.areEqual(this.code, tableGroupBean.code) && Intrinsics.areEqual(this.name, tableGroupBean.name) && Intrinsics.areEqual(this.min, tableGroupBean.min) && Intrinsics.areEqual(this.max, tableGroupBean.max) && Intrinsics.areEqual(this.alias, tableGroupBean.alias) && this.isSelect == tableGroupBean.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.code;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.min;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.max;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.alias;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode5 + i;
    }

    public String toString() {
        return "TableGroupBean(code=" + this.code + ", name=" + this.name + ", min=" + this.min + ", max=" + this.max + ", alias=" + this.alias + ", isSelect=" + this.isSelect + ")";
    }

    public TableGroupBean(String code, String name, String str, String str2, String str3, boolean z) {
        Intrinsics.checkParameterIsNotNull(code, "code");
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.code = code;
        this.name = name;
        this.min = str;
        this.max = str2;
        this.alias = str3;
        this.isSelect = z;
    }

    public /* synthetic */ TableGroupBean(String str, String str2, String str3, String str4, String str5, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? (String) null : str3, (i & 8) != 0 ? (String) null : str4, (i & 16) != 0 ? (String) null : str5, (i & 32) != 0 ? false : z);
    }

    public final String getAlias() {
        return this.alias;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getMax() {
        return this.max;
    }

    public final String getMin() {
        return this.min;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setAlias(String str) {
        this.alias = str;
    }

    public final void setCode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.code = str;
    }

    public final void setMax(String str) {
        this.max = str;
    }

    public final void setMin(String str) {
        this.min = str;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
