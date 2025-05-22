package com.slamtec.slamware.robot;

import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public final class PoseMap extends MapLayer {
    private HashMap<String, CompositePose> poses = new HashMap<>();

    public HashMap<String, CompositePose> getPoses() {
        return this.poses;
    }

    public void setPoses(HashMap<String, CompositePose> hashMap) {
        this.poses = hashMap;
    }

    @Override // com.slamtec.slamware.robot.MapLayer
    public void clear() {
        super.clear();
        this.poses.clear();
    }
}
