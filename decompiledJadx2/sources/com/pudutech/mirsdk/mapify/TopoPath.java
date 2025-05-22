package com.pudutech.mirsdk.mapify;

import com.pudutech.mirsdk.map.elements.Node;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TopoPath.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/TopoPath;", "", "()V", "illegal_service_point", "", "Lcom/pudutech/mirsdk/mapify/Destination;", "getIllegal_service_point", "()[Lcom/pudutech/mirsdk/mapify/Destination;", "setIllegal_service_point", "([Lcom/pudutech/mirsdk/mapify/Destination;)V", "[Lcom/pudutech/mirsdk/mapify/Destination;", "nodes", "Lcom/pudutech/mirsdk/map/elements/Node;", "getNodes", "()[Lcom/pudutech/mirsdk/map/elements/Node;", "setNodes", "([Lcom/pudutech/mirsdk/map/elements/Node;)V", "[Lcom/pudutech/mirsdk/map/elements/Node;", "tracks", "Lcom/pudutech/mirsdk/mapify/TopoTrack;", "getTracks", "()[Lcom/pudutech/mirsdk/mapify/TopoTrack;", "setTracks", "([Lcom/pudutech/mirsdk/mapify/TopoTrack;)V", "[Lcom/pudutech/mirsdk/mapify/TopoTrack;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TopoPath {
    private TopoTrack[] tracks = new TopoTrack[0];
    private Node[] nodes = new Node[0];
    private Destination[] illegal_service_point = new Destination[0];

    public final TopoTrack[] getTracks() {
        return this.tracks;
    }

    public final void setTracks(TopoTrack[] topoTrackArr) {
        Intrinsics.checkParameterIsNotNull(topoTrackArr, "<set-?>");
        this.tracks = topoTrackArr;
    }

    public final Node[] getNodes() {
        return this.nodes;
    }

    public final void setNodes(Node[] nodeArr) {
        Intrinsics.checkParameterIsNotNull(nodeArr, "<set-?>");
        this.nodes = nodeArr;
    }

    public final Destination[] getIllegal_service_point() {
        return this.illegal_service_point;
    }

    public final void setIllegal_service_point(Destination[] destinationArr) {
        Intrinsics.checkParameterIsNotNull(destinationArr, "<set-?>");
        this.illegal_service_point = destinationArr;
    }
}
