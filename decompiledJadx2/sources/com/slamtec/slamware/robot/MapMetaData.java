package com.slamtec.slamware.robot;

import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public final class MapMetaData {
    private HashMap<String, String> dict_;

    public MapMetaData() {
        this.dict_ = new HashMap<>();
    }

    public MapMetaData(MapMetaData mapMetaData) {
        this.dict_ = new HashMap<>(mapMetaData.getData());
    }

    public HashMap<String, String> getData() {
        return this.dict_;
    }

    public void setData(HashMap<String, String> hashMap) {
        this.dict_ = hashMap;
    }

    public String getValue(String str) {
        return this.dict_.get(str);
    }

    public void setValue(String str, String str2) {
        this.dict_.put(str, str2);
    }

    public void swap(MapMetaData mapMetaData) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.putAll(this.dict_);
        this.dict_.clear();
        this.dict_.putAll(mapMetaData.getData());
        mapMetaData.setData(hashMap);
    }

    public void clear() {
        this.dict_.clear();
    }
}
