package com.pudu.library.loracall.dao;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.List;
import kotlin.Metadata;

/* compiled from: TableBindDao.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH'J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\f\u001a\u00020\bH'J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\r\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\nH'¨\u0006\u000e"}, m3961d2 = {"Lcom/pudu/library/loracall/dao/TableBindDao;", "", RequestParameters.SUBRESOURCE_DELETE, "", "item", "Lcom/pudu/library/loracall/dao/TableMatchBean;", "getTable", "devAdder", "", "getTableList", "", "getTableNameList", "tableName", "insert", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface TableBindDao {
    void delete(TableMatchBean item);

    TableMatchBean getTable(String devAdder);

    List<TableMatchBean> getTableList();

    List<TableMatchBean> getTableNameList(String tableName);

    void insert(TableMatchBean item);

    void insert(List<TableMatchBean> item);
}
