package com.pudutech.mirsdk.mapify.mapdata;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Vertex.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00000\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\t\"\u0004\b\u0019\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001b\"\u0004\b\u001f\u0010\u001d¨\u0006 "}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/mapdata/Vertex;", "", "x", "", "y", "(DD)V", "id", "", "getId", "()I", "setId", "(I)V", "isNode", "", "()Z", "setNode", "(Z)V", "linkedVertex", "", "getLinkedVertex", "()Ljava/util/List;", "setLinkedVertex", "(Ljava/util/List;)V", "nodeId", "getNodeId", "setNodeId", "getX", "()D", "setX", "(D)V", "getY", "setY", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Vertex {
    private int id;
    private boolean isNode;
    private List<Vertex> linkedVertex;
    private int nodeId;
    private double x;
    private double y;

    public Vertex() {
        this(0.0d, 0.0d, 3, null);
    }

    public Vertex(double d, double d2) {
        this.x = d;
        this.y = d2;
        this.linkedVertex = new ArrayList();
        this.nodeId = -1;
    }

    public /* synthetic */ Vertex(double d, double d2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.0d : d, (i & 2) != 0 ? 0.0d : d2);
    }

    public final double getX() {
        return this.x;
    }

    public final double getY() {
        return this.y;
    }

    public final void setX(double d) {
        this.x = d;
    }

    public final void setY(double d) {
        this.y = d;
    }

    public final List<Vertex> getLinkedVertex() {
        return this.linkedVertex;
    }

    public final void setLinkedVertex(List<Vertex> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.linkedVertex = list;
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    /* renamed from: isNode, reason: from getter */
    public final boolean getIsNode() {
        return this.isNode;
    }

    public final void setNode(boolean z) {
        this.isNode = z;
    }

    public final int getNodeId() {
        return this.nodeId;
    }

    public final void setNodeId(int i) {
        this.nodeId = i;
    }
}
