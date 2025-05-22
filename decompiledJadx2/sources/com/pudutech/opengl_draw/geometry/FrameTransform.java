package com.pudutech.opengl_draw.geometry;

import androidx.core.util.Preconditions;
import com.pudutech.opengl_draw.namespace.GraphName;
import java.sql.Time;

/* loaded from: classes5.dex */
public class FrameTransform {
    private final GraphName source;
    private final GraphName target;
    private final Time time;
    private final Transform transform;

    public FrameTransform(Transform transform, GraphName graphName, GraphName graphName2, Time time) {
        Preconditions.checkNotNull(transform);
        Preconditions.checkNotNull(graphName);
        Preconditions.checkNotNull(graphName2);
        this.transform = transform;
        this.source = graphName.toRelative();
        this.target = graphName2.toRelative();
        this.time = time;
    }

    public Transform getTransform() {
        return this.transform;
    }

    public GraphName getSourceFrame() {
        return this.source;
    }

    public GraphName getTargetFrame() {
        return this.target;
    }

    public FrameTransform invert() {
        return new FrameTransform(this.transform.invert(), this.target, this.source, this.time);
    }

    public Time getTime() {
        return this.time;
    }

    public String toString() {
        return String.format("FrameTransform<Source: %s, Target: %s, %s, Time: %s>", this.source, this.target, this.transform, this.time);
    }

    public int hashCode() {
        GraphName graphName = this.source;
        int hashCode = ((graphName == null ? 0 : graphName.hashCode()) + 31) * 31;
        GraphName graphName2 = this.target;
        int hashCode2 = (hashCode + (graphName2 == null ? 0 : graphName2.hashCode())) * 31;
        Time time = this.time;
        int hashCode3 = (hashCode2 + (time == null ? 0 : time.hashCode())) * 31;
        Transform transform = this.transform;
        return hashCode3 + (transform != null ? transform.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FrameTransform frameTransform = (FrameTransform) obj;
        GraphName graphName = this.source;
        if (graphName == null) {
            if (frameTransform.source != null) {
                return false;
            }
        } else if (!graphName.equals(frameTransform.source)) {
            return false;
        }
        GraphName graphName2 = this.target;
        if (graphName2 == null) {
            if (frameTransform.target != null) {
                return false;
            }
        } else if (!graphName2.equals(frameTransform.target)) {
            return false;
        }
        Time time = this.time;
        if (time == null) {
            if (frameTransform.time != null) {
                return false;
            }
        } else if (!time.equals(frameTransform.time)) {
            return false;
        }
        Transform transform = this.transform;
        if (transform == null) {
            if (frameTransform.transform != null) {
                return false;
            }
        } else if (!transform.equals(frameTransform.transform)) {
            return false;
        }
        return true;
    }
}
