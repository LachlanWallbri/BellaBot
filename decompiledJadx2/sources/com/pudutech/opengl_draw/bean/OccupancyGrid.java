package com.pudutech.opengl_draw.bean;

import com.pudutech.opengl_draw.geometry.Quaternion;
import com.pudutech.opengl_draw.geometry.Vector3;

/* loaded from: classes5.dex */
public class OccupancyGrid {
    private Quaternion quaternion;
    private Vector3 vector3;

    public OccupancyGrid() {
    }

    public OccupancyGrid(Vector3 vector3, Quaternion quaternion) {
        this.vector3 = vector3;
        this.quaternion = quaternion;
    }

    public Vector3 getVector3() {
        return this.vector3;
    }

    public void setVector3(Vector3 vector3) {
        this.vector3 = vector3;
    }

    public Quaternion getQuaternion() {
        return this.quaternion;
    }

    public void setQuaternion(Quaternion quaternion) {
        this.quaternion = quaternion;
    }
}
