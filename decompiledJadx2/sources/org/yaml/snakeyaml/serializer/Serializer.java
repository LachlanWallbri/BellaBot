package org.yaml.snakeyaml.serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.emitter.Emitable;
import org.yaml.snakeyaml.events.AliasEvent;
import org.yaml.snakeyaml.events.DocumentEndEvent;
import org.yaml.snakeyaml.events.DocumentStartEvent;
import org.yaml.snakeyaml.events.ImplicitTuple;
import org.yaml.snakeyaml.events.MappingEndEvent;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.events.SequenceEndEvent;
import org.yaml.snakeyaml.events.SequenceStartEvent;
import org.yaml.snakeyaml.events.StreamEndEvent;
import org.yaml.snakeyaml.events.StreamStartEvent;
import org.yaml.snakeyaml.nodes.AnchorNode;
import org.yaml.snakeyaml.nodes.CollectionNode;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.resolver.Resolver;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class Serializer {
    private AnchorGenerator anchorGenerator;
    private Map<Node, String> anchors;
    private Boolean closed;
    private final Emitable emitter;
    private boolean explicitEnd;
    private Tag explicitRoot;
    private boolean explicitStart;
    private final Resolver resolver;
    private Set<Node> serializedNodes;
    private Map<String, String> useTags;
    private DumperOptions.Version useVersion;

    public Serializer(Emitable emitable, Resolver resolver, DumperOptions dumperOptions, Tag tag) {
        this.emitter = emitable;
        this.resolver = resolver;
        this.explicitStart = dumperOptions.isExplicitStart();
        this.explicitEnd = dumperOptions.isExplicitEnd();
        if (dumperOptions.getVersion() != null) {
            this.useVersion = dumperOptions.getVersion();
        }
        this.useTags = dumperOptions.getTags();
        this.serializedNodes = new HashSet();
        this.anchors = new HashMap();
        this.anchorGenerator = dumperOptions.getAnchorGenerator();
        this.closed = null;
        this.explicitRoot = tag;
    }

    public void open() throws IOException {
        if (this.closed == null) {
            this.emitter.emit(new StreamStartEvent(null, null));
            this.closed = Boolean.FALSE;
        } else {
            if (Boolean.TRUE.equals(this.closed)) {
                throw new SerializerException("serializer is closed");
            }
            throw new SerializerException("serializer is already opened");
        }
    }

    public void close() throws IOException {
        if (this.closed == null) {
            throw new SerializerException("serializer is not opened");
        }
        if (Boolean.TRUE.equals(this.closed)) {
            return;
        }
        this.emitter.emit(new StreamEndEvent(null, null));
        this.closed = Boolean.TRUE;
    }

    public void serialize(Node node) throws IOException {
        Boolean bool = this.closed;
        if (bool == null) {
            throw new SerializerException("serializer is not opened");
        }
        if (bool.booleanValue()) {
            throw new SerializerException("serializer is closed");
        }
        this.emitter.emit(new DocumentStartEvent(null, null, this.explicitStart, this.useVersion, this.useTags));
        anchorNode(node);
        Tag tag = this.explicitRoot;
        if (tag != null) {
            node.setTag(tag);
        }
        serializeNode(node, null);
        this.emitter.emit(new DocumentEndEvent(null, null, this.explicitEnd));
        this.serializedNodes.clear();
        this.anchors.clear();
    }

    private void anchorNode(Node node) {
        if (node.getNodeId() == NodeId.anchor) {
            node = ((AnchorNode) node).getRealNode();
        }
        if (this.anchors.containsKey(node)) {
            if (this.anchors.get(node) == null) {
                this.anchors.put(node, this.anchorGenerator.nextAnchor(node));
                return;
            }
            return;
        }
        this.anchors.put(node, null);
        int i = C89931.$SwitchMap$org$yaml$snakeyaml$nodes$NodeId[node.getNodeId().ordinal()];
        if (i == 1) {
            Iterator<Node> it = ((SequenceNode) node).getValue().iterator();
            while (it.hasNext()) {
                anchorNode(it.next());
            }
        } else {
            if (i != 2) {
                return;
            }
            for (NodeTuple nodeTuple : ((MappingNode) node).getValue()) {
                Node keyNode = nodeTuple.getKeyNode();
                Node valueNode = nodeTuple.getValueNode();
                anchorNode(keyNode);
                anchorNode(valueNode);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* renamed from: org.yaml.snakeyaml.serializer.Serializer$1 */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class C89931 {
        static final /* synthetic */ int[] $SwitchMap$org$yaml$snakeyaml$nodes$NodeId = new int[NodeId.values().length];

        static {
            try {
                $SwitchMap$org$yaml$snakeyaml$nodes$NodeId[NodeId.sequence.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$yaml$snakeyaml$nodes$NodeId[NodeId.mapping.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$yaml$snakeyaml$nodes$NodeId[NodeId.scalar.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void serializeNode(Node node, Node node2) throws IOException {
        if (node.getNodeId() == NodeId.anchor) {
            node = ((AnchorNode) node).getRealNode();
        }
        String str = this.anchors.get(node);
        if (this.serializedNodes.contains(node)) {
            this.emitter.emit(new AliasEvent(str, null, null));
            return;
        }
        this.serializedNodes.add(node);
        int i = C89931.$SwitchMap$org$yaml$snakeyaml$nodes$NodeId[node.getNodeId().ordinal()];
        if (i == 1) {
            SequenceNode sequenceNode = (SequenceNode) node;
            this.emitter.emit(new SequenceStartEvent(str, node.getTag().getValue(), node.getTag().equals(this.resolver.resolve(NodeId.sequence, null, true)), null, null, sequenceNode.getFlowStyle()));
            Iterator<Node> it = sequenceNode.getValue().iterator();
            while (it.hasNext()) {
                serializeNode(it.next(), node);
            }
            this.emitter.emit(new SequenceEndEvent(null, null));
            return;
        }
        if (i == 3) {
            ScalarNode scalarNode = (ScalarNode) node;
            this.emitter.emit(new ScalarEvent(str, node.getTag().getValue(), new ImplicitTuple(node.getTag().equals(this.resolver.resolve(NodeId.scalar, scalarNode.getValue(), true)), node.getTag().equals(this.resolver.resolve(NodeId.scalar, scalarNode.getValue(), false))), scalarNode.getValue(), null, null, scalarNode.getStyle()));
            return;
        }
        this.emitter.emit(new MappingStartEvent(str, node.getTag().getValue(), node.getTag().equals(this.resolver.resolve(NodeId.mapping, null, true)), null, null, ((CollectionNode) node).getFlowStyle()));
        MappingNode mappingNode = (MappingNode) node;
        for (NodeTuple nodeTuple : mappingNode.getValue()) {
            Node keyNode = nodeTuple.getKeyNode();
            Node valueNode = nodeTuple.getValueNode();
            serializeNode(keyNode, mappingNode);
            serializeNode(valueNode, mappingNode);
        }
        this.emitter.emit(new MappingEndEvent(null, null));
    }
}
