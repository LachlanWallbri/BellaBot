package org.yaml.snakeyaml.composer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.yaml.snakeyaml.events.AliasEvent;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.NodeEvent;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.events.SequenceStartEvent;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.parser.Parser;
import org.yaml.snakeyaml.resolver.Resolver;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class Composer {
    protected final Parser parser;
    private final Resolver resolver;
    private final Map<String, Node> anchors = new HashMap();
    private final Set<Node> recursiveNodes = new HashSet();

    public Composer(Parser parser, Resolver resolver) {
        this.parser = parser;
        this.resolver = resolver;
    }

    public boolean checkNode() {
        if (this.parser.checkEvent(Event.EnumC8987ID.StreamStart)) {
            this.parser.getEvent();
        }
        return !this.parser.checkEvent(Event.EnumC8987ID.StreamEnd);
    }

    public Node getNode() {
        if (this.parser.checkEvent(Event.EnumC8987ID.StreamEnd)) {
            return null;
        }
        return composeDocument();
    }

    public Node getSingleNode() {
        this.parser.getEvent();
        Node composeDocument = !this.parser.checkEvent(Event.EnumC8987ID.StreamEnd) ? composeDocument() : null;
        if (!this.parser.checkEvent(Event.EnumC8987ID.StreamEnd)) {
            throw new ComposerException("expected a single document in the stream", composeDocument.getStartMark(), "but found another document", this.parser.getEvent().getStartMark());
        }
        this.parser.getEvent();
        return composeDocument;
    }

    private Node composeDocument() {
        this.parser.getEvent();
        Node composeNode = composeNode(null);
        this.parser.getEvent();
        this.anchors.clear();
        this.recursiveNodes.clear();
        return composeNode;
    }

    private Node composeNode(Node node) {
        Node composeMappingNode;
        this.recursiveNodes.add(node);
        if (this.parser.checkEvent(Event.EnumC8987ID.Alias)) {
            AliasEvent aliasEvent = (AliasEvent) this.parser.getEvent();
            String anchor = aliasEvent.getAnchor();
            if (!this.anchors.containsKey(anchor)) {
                throw new ComposerException(null, null, "found undefined alias " + anchor, aliasEvent.getStartMark());
            }
            Node node2 = this.anchors.get(anchor);
            if (this.recursiveNodes.remove(node2)) {
                node2.setTwoStepsConstruction(true);
            }
            return node2;
        }
        String anchor2 = ((NodeEvent) this.parser.peekEvent()).getAnchor();
        if (this.parser.checkEvent(Event.EnumC8987ID.Scalar)) {
            composeMappingNode = composeScalarNode(anchor2);
        } else if (this.parser.checkEvent(Event.EnumC8987ID.SequenceStart)) {
            composeMappingNode = composeSequenceNode(anchor2);
        } else {
            composeMappingNode = composeMappingNode(anchor2);
        }
        this.recursiveNodes.remove(node);
        return composeMappingNode;
    }

    protected Node composeScalarNode(String str) {
        Tag resolve;
        boolean z;
        ScalarEvent scalarEvent = (ScalarEvent) this.parser.getEvent();
        String tag = scalarEvent.getTag();
        if (tag == null || tag.equals("!")) {
            resolve = this.resolver.resolve(NodeId.scalar, scalarEvent.getValue(), scalarEvent.getImplicit().canOmitTagInPlainScalar());
            z = true;
        } else {
            resolve = new Tag(tag);
            z = false;
        }
        ScalarNode scalarNode = new ScalarNode(resolve, z, scalarEvent.getValue(), scalarEvent.getStartMark(), scalarEvent.getEndMark(), scalarEvent.getStyle());
        if (str != null) {
            this.anchors.put(str, scalarNode);
        }
        return scalarNode;
    }

    protected Node composeSequenceNode(String str) {
        Tag resolve;
        boolean z;
        SequenceStartEvent sequenceStartEvent = (SequenceStartEvent) this.parser.getEvent();
        String tag = sequenceStartEvent.getTag();
        if (tag == null || tag.equals("!")) {
            resolve = this.resolver.resolve(NodeId.sequence, null, sequenceStartEvent.getImplicit());
            z = true;
        } else {
            resolve = new Tag(tag);
            z = false;
        }
        boolean z2 = z;
        ArrayList arrayList = new ArrayList();
        SequenceNode sequenceNode = new SequenceNode(resolve, z2, arrayList, sequenceStartEvent.getStartMark(), null, sequenceStartEvent.getFlowStyle());
        if (str != null) {
            this.anchors.put(str, sequenceNode);
        }
        while (!this.parser.checkEvent(Event.EnumC8987ID.SequenceEnd)) {
            arrayList.add(composeNode(sequenceNode));
        }
        sequenceNode.setEndMark(this.parser.getEvent().getEndMark());
        return sequenceNode;
    }

    protected Node composeMappingNode(String str) {
        Tag resolve;
        boolean z;
        MappingStartEvent mappingStartEvent = (MappingStartEvent) this.parser.getEvent();
        String tag = mappingStartEvent.getTag();
        if (tag == null || tag.equals("!")) {
            resolve = this.resolver.resolve(NodeId.mapping, null, mappingStartEvent.getImplicit());
            z = true;
        } else {
            resolve = new Tag(tag);
            z = false;
        }
        boolean z2 = z;
        ArrayList arrayList = new ArrayList();
        MappingNode mappingNode = new MappingNode(resolve, z2, arrayList, mappingStartEvent.getStartMark(), null, mappingStartEvent.getFlowStyle());
        if (str != null) {
            this.anchors.put(str, mappingNode);
        }
        while (!this.parser.checkEvent(Event.EnumC8987ID.MappingEnd)) {
            composeMappingChildren(arrayList, mappingNode);
        }
        mappingNode.setEndMark(this.parser.getEvent().getEndMark());
        return mappingNode;
    }

    protected void composeMappingChildren(List<NodeTuple> list, MappingNode mappingNode) {
        Node composeKeyNode = composeKeyNode(mappingNode);
        if (composeKeyNode.getTag().equals(Tag.MERGE)) {
            mappingNode.setMerged(true);
        }
        list.add(new NodeTuple(composeKeyNode, composeValueNode(mappingNode)));
    }

    protected Node composeKeyNode(MappingNode mappingNode) {
        return composeNode(mappingNode);
    }

    protected Node composeValueNode(MappingNode mappingNode) {
        return composeNode(mappingNode);
    }
}
