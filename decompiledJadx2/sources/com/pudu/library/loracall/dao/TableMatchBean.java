package com.pudu.library.loracall.dao;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.pudu.loracall.library.BR;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TableMatchBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R&\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00038G@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0014"}, m3961d2 = {"Lcom/pudu/library/loracall/dao/TableMatchBean;", "Landroidx/databinding/BaseObservable;", "tableName", "", "(Ljava/lang/String;)V", ES6Iterator.VALUE_PROPERTY, "devAdder", "getDevAdder", "()Ljava/lang/String;", "setDevAdder", "getTableName", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class TableMatchBean extends BaseObservable {
    private String devAdder;
    private final String tableName;

    public static /* synthetic */ TableMatchBean copy$default(TableMatchBean tableMatchBean, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tableMatchBean.tableName;
        }
        return tableMatchBean.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTableName() {
        return this.tableName;
    }

    public final TableMatchBean copy(String tableName) {
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        return new TableMatchBean(tableName);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof TableMatchBean) && Intrinsics.areEqual(this.tableName, ((TableMatchBean) other).tableName);
        }
        return true;
    }

    public int hashCode() {
        String str = this.tableName;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "TableMatchBean(tableName=" + this.tableName + ")";
    }

    public final String getTableName() {
        return this.tableName;
    }

    public TableMatchBean(String tableName) {
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        this.tableName = tableName;
        this.devAdder = "";
    }

    @Bindable
    public final String getDevAdder() {
        return this.devAdder;
    }

    public final void setDevAdder(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.devAdder = value;
        notifyPropertyChanged(BR.devAdder);
    }
}
