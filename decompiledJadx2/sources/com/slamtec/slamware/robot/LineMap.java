package com.slamtec.slamware.robot;

import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public final class LineMap extends MapLayer {
    private HashMap<String, CompositeLine> lines_ = new HashMap<>();

    public HashMap<String, CompositeLine> getLines() {
        return this.lines_;
    }

    public void setLines(HashMap<String, CompositeLine> hashMap) {
        this.lines_ = hashMap;
    }

    @Override // com.slamtec.slamware.robot.MapLayer
    public void clear() {
        super.clear();
        this.lines_.clear();
    }
}
