package com.slamtec.slamware.robot;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public final class CompositeMap {
    private ArrayList<MapLayer> maps_;
    private MapMetaData metaData_;

    public CompositeMap() {
        this.metaData_ = new MapMetaData();
        this.maps_ = new ArrayList<>();
    }

    public CompositeMap(CompositeMap compositeMap) {
        this.metaData_ = new MapMetaData(compositeMap.getMetaData());
        this.maps_ = new ArrayList<>(compositeMap.getMaps());
    }

    public MapMetaData getMetaData() {
        return this.metaData_;
    }

    public void setMetaData(MapMetaData mapMetaData) {
        this.metaData_ = mapMetaData;
    }

    public ArrayList<MapLayer> getMaps() {
        return this.maps_;
    }

    public void setMaps(ArrayList<MapLayer> arrayList) {
        this.maps_ = arrayList;
    }
}
