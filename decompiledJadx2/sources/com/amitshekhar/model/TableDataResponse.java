package com.amitshekhar.model;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TableDataResponse {
    public String errorMessage;
    public boolean isEditable;
    public boolean isSelectQuery;
    public boolean isSuccessful;
    public List<Object> rows;
    public List<TableInfo> tableInfos;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ColumnData {
        public String dataType;
        public Object value;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class TableInfo {
        public boolean isPrimary;
        public String title;
    }
}
