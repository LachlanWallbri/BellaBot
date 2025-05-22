package com.slamtec.slamware.action;

import com.slamtec.slamware.robot.Location;
import java.util.Iterator;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class Path {
    private Vector<Location> points = new Vector<>();

    public Path() {
    }

    public Path(Vector<Location> vector) {
        copyLocations(vector);
    }

    public Path(Path path) {
        copyLocations(path.points);
    }

    public Vector<Location> getPoints() {
        return this.points;
    }

    public void setPoints(Vector<Location> vector) {
        this.points = new Vector<>();
        copyLocations(vector);
    }

    private void copyLocations(Vector<Location> vector) {
        Iterator<Location> it = vector.iterator();
        while (it.hasNext()) {
            this.points.add(it.next());
        }
    }
}
