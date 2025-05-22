package org.yaml.snakeyaml.representer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.nodes.AnchorNode;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class BaseRepresenter {
    protected Character defaultScalarStyle;
    protected Represent nullRepresenter;
    protected Object objectToRepresent;
    private PropertyUtils propertyUtils;
    protected final Map<Class<?>, Represent> representers = new HashMap();
    protected final Map<Class<?>, Represent> multiRepresenters = new LinkedHashMap();
    protected DumperOptions.FlowStyle defaultFlowStyle = DumperOptions.FlowStyle.AUTO;
    protected final Map<Object, Node> representedObjects = new IdentityHashMap<Object, Node>() { // from class: org.yaml.snakeyaml.representer.BaseRepresenter.1
        private static final long serialVersionUID = -5576159264232131854L;

        @Override // java.util.IdentityHashMap, java.util.AbstractMap, java.util.Map
        public Node put(Object obj, Node node) {
            return (Node) super.put((C89911) obj, (Object) new AnchorNode(node));
        }
    };
    private boolean explicitPropertyUtils = false;

    public Node represent(Object obj) {
        Node representData = representData(obj);
        this.representedObjects.clear();
        this.objectToRepresent = null;
        return representData;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Node representData(Object obj) {
        this.objectToRepresent = obj;
        if (this.representedObjects.containsKey(this.objectToRepresent)) {
            return this.representedObjects.get(this.objectToRepresent);
        }
        if (obj == null) {
            return this.nullRepresenter.representData(null);
        }
        Class<?> cls = obj.getClass();
        if (this.representers.containsKey(cls)) {
            return this.representers.get(cls).representData(obj);
        }
        for (Class<?> cls2 : this.multiRepresenters.keySet()) {
            if (cls2.isInstance(obj)) {
                return this.multiRepresenters.get(cls2).representData(obj);
            }
        }
        if (this.multiRepresenters.containsKey(null)) {
            return this.multiRepresenters.get(null).representData(obj);
        }
        return this.representers.get(null).representData(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node representScalar(Tag tag, String str, Character ch) {
        if (ch == null) {
            ch = this.defaultScalarStyle;
        }
        return new ScalarNode(tag, str, null, null, ch);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node representScalar(Tag tag, String str) {
        return representScalar(tag, str, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node representSequence(Tag tag, Iterable<?> iterable, Boolean bool) {
        ArrayList arrayList = new ArrayList(iterable instanceof List ? ((List) iterable).size() : 10);
        SequenceNode sequenceNode = new SequenceNode(tag, arrayList, bool);
        this.representedObjects.put(this.objectToRepresent, sequenceNode);
        boolean z = true;
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            Node representData = representData(it.next());
            if (!(representData instanceof ScalarNode) || ((ScalarNode) representData).getStyle() != null) {
                z = false;
            }
            arrayList.add(representData);
        }
        if (bool == null) {
            if (this.defaultFlowStyle != DumperOptions.FlowStyle.AUTO) {
                sequenceNode.setFlowStyle(this.defaultFlowStyle.getStyleBoolean());
            } else {
                sequenceNode.setFlowStyle(Boolean.valueOf(z));
            }
        }
        return sequenceNode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node representMapping(Tag tag, Map<?, ?> map, Boolean bool) {
        ArrayList arrayList = new ArrayList(map.size());
        MappingNode mappingNode = new MappingNode(tag, arrayList, bool);
        this.representedObjects.put(this.objectToRepresent, mappingNode);
        boolean z = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Node representData = representData(entry.getKey());
            Node representData2 = representData(entry.getValue());
            if (!(representData instanceof ScalarNode) || ((ScalarNode) representData).getStyle() != null) {
                z = false;
            }
            if (!(representData2 instanceof ScalarNode) || ((ScalarNode) representData2).getStyle() != null) {
                z = false;
            }
            arrayList.add(new NodeTuple(representData, representData2));
        }
        if (bool == null) {
            if (this.defaultFlowStyle != DumperOptions.FlowStyle.AUTO) {
                mappingNode.setFlowStyle(this.defaultFlowStyle.getStyleBoolean());
            } else {
                mappingNode.setFlowStyle(Boolean.valueOf(z));
            }
        }
        return mappingNode;
    }

    public void setDefaultScalarStyle(DumperOptions.ScalarStyle scalarStyle) {
        this.defaultScalarStyle = scalarStyle.getChar();
    }

    public void setDefaultFlowStyle(DumperOptions.FlowStyle flowStyle) {
        this.defaultFlowStyle = flowStyle;
    }

    public DumperOptions.FlowStyle getDefaultFlowStyle() {
        return this.defaultFlowStyle;
    }

    public void setPropertyUtils(PropertyUtils propertyUtils) {
        this.propertyUtils = propertyUtils;
        this.explicitPropertyUtils = true;
    }

    public final PropertyUtils getPropertyUtils() {
        if (this.propertyUtils == null) {
            this.propertyUtils = new PropertyUtils();
        }
        return this.propertyUtils;
    }

    public final boolean isExplicitPropertyUtils() {
        return this.explicitPropertyUtils;
    }
}
