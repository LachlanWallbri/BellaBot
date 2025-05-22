package com.aliyun.alink.linksdk.tmp.devicemodel.specs;

import com.aliyun.alink.linksdk.tmp.devicemodel.DataType;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class StructSpec {
    private DataType<MetaSpec> dataType;
    private String identifier;
    private String name;

    public String getName() {
        return this.name;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public DataType<MetaSpec> getDataType() {
        return this.dataType;
    }
}
