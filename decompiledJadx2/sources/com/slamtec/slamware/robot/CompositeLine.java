package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public final class CompositeLine {
    private Location end;
    private MapMetaData metaData;
    private String name;
    private Location start;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Location getStart() {
        return this.start;
    }

    public void setStart(Location location) {
        this.start = location;
    }

    public Location getEnd() {
        return this.end;
    }

    public void setEnd(Location location) {
        this.end = location;
    }

    public MapMetaData getMetaData() {
        return this.metaData;
    }

    public void setMetaData(MapMetaData mapMetaData) {
        this.metaData = mapMetaData;
    }
}
