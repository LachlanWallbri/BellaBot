package com.pudutech.opengl_draw.bean;

import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.Vertices;
import com.pudutech.opengl_draw.geometry.Vector3;
import java.nio.FloatBuffer;
import java.util.List;

/* loaded from: classes5.dex */
public class Line {
    private Vector3 center;
    private Color color;
    private boolean isSelect;
    private String name;
    private List<Vector3> vector3List;
    private FloatBuffer vertexFrontBuffer;
    private float width;

    public Line() {
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float f) {
        this.width = f;
    }

    public Line(List<Vector3> list, String str) {
        this.vector3List = list;
        this.vertexFrontBuffer = createFloatBuffer(list);
        this.name = str;
        if (list.size() != 0) {
            this.center = list.get(list.size() / 2);
        }
    }

    public FloatBuffer createFloatBuffer(List<Vector3> list) {
        this.vector3List = list;
        FloatBuffer allocateBuffer = Vertices.allocateBuffer(list.size() * 3);
        allocateBuffer.clear();
        for (Vector3 vector3 : list) {
            allocateBuffer.put((float) vector3.getX());
            allocateBuffer.put((float) vector3.getY());
            allocateBuffer.put((float) vector3.getZ());
        }
        allocateBuffer.position(0);
        return allocateBuffer;
    }

    public Vector3 getCenter() {
        return this.center;
    }

    public void setCenter(Vector3 vector3) {
        this.center = vector3;
    }

    public FloatBuffer getVertexFrontBuffer() {
        return this.vertexFrontBuffer;
    }

    public void setVertexFrontBuffer(FloatBuffer floatBuffer) {
        this.vertexFrontBuffer = floatBuffer;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Vector3> getVector3List() {
        return this.vector3List;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public void setVector3List(List<Vector3> list) {
        this.vector3List = list;
        this.vertexFrontBuffer = createFloatBuffer(list);
        if (list.size() != 0) {
            this.center = list.get(list.size() / 2);
        }
    }
}
