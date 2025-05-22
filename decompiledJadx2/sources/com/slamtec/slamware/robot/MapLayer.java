package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public abstract class MapLayer {
    private MapMetaData metaData_;
    private String name_;
    private String usage_;

    public MapMetaData getMetaData() {
        return this.metaData_;
    }

    public void setMetaData(MapMetaData mapMetaData) {
        this.metaData_ = mapMetaData;
    }

    public String getName() {
        return this.name_;
    }

    public void setName(String str) {
        this.name_ = str;
    }

    public String getUsage() {
        return this.usage_;
    }

    public void setUsage(String str) {
        this.usage_ = str;
    }

    public void clear() {
        this.metaData_.clear();
        this.name_ = "";
        this.usage_ = "";
    }
}
