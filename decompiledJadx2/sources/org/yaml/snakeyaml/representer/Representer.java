package org.yaml.snakeyaml.representer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class Representer extends SafeRepresenter {
    @Override // org.yaml.snakeyaml.representer.SafeRepresenter
    public /* bridge */ /* synthetic */ Tag addClassTag(Class cls, Tag tag) {
        return super.addClassTag(cls, tag);
    }

    @Override // org.yaml.snakeyaml.representer.SafeRepresenter
    public /* bridge */ /* synthetic */ TimeZone getTimeZone() {
        return super.getTimeZone();
    }

    @Override // org.yaml.snakeyaml.representer.SafeRepresenter
    public /* bridge */ /* synthetic */ void setTimeZone(TimeZone timeZone) {
        super.setTimeZone(timeZone);
    }

    public Representer() {
        this.representers.put(null, new RepresentJavaBean());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentJavaBean implements Represent {
        protected RepresentJavaBean() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            Representer representer = Representer.this;
            return representer.representJavaBean(representer.getProperties(obj.getClass()), obj);
        }
    }

    protected MappingNode representJavaBean(Set<Property> set, Object obj) {
        ArrayList arrayList = new ArrayList(set.size());
        Tag tag = this.classTags.get(obj.getClass());
        if (tag == null) {
            tag = new Tag((Class<? extends Object>) obj.getClass());
        }
        MappingNode mappingNode = new MappingNode(tag, arrayList, null);
        this.representedObjects.put(obj, mappingNode);
        boolean z = true;
        for (Property property : set) {
            Object obj2 = property.get(obj);
            NodeTuple representJavaBeanProperty = representJavaBeanProperty(obj, property, obj2, obj2 == null ? null : this.classTags.get(obj2.getClass()));
            if (representJavaBeanProperty != null) {
                if (((ScalarNode) representJavaBeanProperty.getKeyNode()).getStyle() != null) {
                    z = false;
                }
                Node valueNode = representJavaBeanProperty.getValueNode();
                if (!(valueNode instanceof ScalarNode) || ((ScalarNode) valueNode).getStyle() != null) {
                    z = false;
                }
                arrayList.add(representJavaBeanProperty);
            }
        }
        if (this.defaultFlowStyle != DumperOptions.FlowStyle.AUTO) {
            mappingNode.setFlowStyle(this.defaultFlowStyle.getStyleBoolean());
        } else {
            mappingNode.setFlowStyle(Boolean.valueOf(z));
        }
        return mappingNode;
    }

    protected NodeTuple representJavaBeanProperty(Object obj, Property property, Object obj2, Tag tag) {
        ScalarNode scalarNode = (ScalarNode) representData(property.getName());
        boolean containsKey = this.representedObjects.containsKey(obj2);
        Node representData = representData(obj2);
        if (obj2 != null && !containsKey) {
            NodeId nodeId = representData.getNodeId();
            if (tag == null) {
                if (nodeId == NodeId.scalar) {
                    if (obj2 instanceof Enum) {
                        representData.setTag(Tag.STR);
                    }
                } else {
                    if (nodeId == NodeId.mapping && property.getType() == obj2.getClass() && !(obj2 instanceof Map) && !representData.getTag().equals(Tag.SET)) {
                        representData.setTag(Tag.MAP);
                    }
                    checkGlobalTag(property, representData, obj2);
                }
            }
        }
        return new NodeTuple(scalarNode, representData);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void checkGlobalTag(Property property, Node node, Object obj) {
        Class<?>[] actualTypeArguments;
        if ((obj.getClass().isArray() && obj.getClass().getComponentType().isPrimitive()) || (actualTypeArguments = property.getActualTypeArguments()) == null) {
            return;
        }
        if (node.getNodeId() == NodeId.sequence) {
            Class<?> cls = actualTypeArguments[0];
            SequenceNode sequenceNode = (SequenceNode) node;
            Iterable iterable = Collections.EMPTY_LIST;
            if (obj.getClass().isArray()) {
                iterable = Arrays.asList((Object[]) obj);
            } else if (obj instanceof Iterable) {
                iterable = (Iterable) obj;
            }
            Iterator it = iterable.iterator();
            if (it.hasNext()) {
                for (Node node2 : sequenceNode.getValue()) {
                    Object next = it.next();
                    if (next != null && cls.equals(next.getClass()) && node2.getNodeId() == NodeId.mapping) {
                        node2.setTag(Tag.MAP);
                    }
                }
                return;
            }
            return;
        }
        if (obj instanceof Set) {
            Class<?> cls2 = actualTypeArguments[0];
            Iterator<NodeTuple> it2 = ((MappingNode) node).getValue().iterator();
            for (Object obj2 : (Set) obj) {
                Node keyNode = it2.next().getKeyNode();
                if (cls2.equals(obj2.getClass()) && keyNode.getNodeId() == NodeId.mapping) {
                    keyNode.setTag(Tag.MAP);
                }
            }
            return;
        }
        if (obj instanceof Map) {
            Class<?> cls3 = actualTypeArguments[0];
            Class<?> cls4 = actualTypeArguments[1];
            for (NodeTuple nodeTuple : ((MappingNode) node).getValue()) {
                resetTag(cls3, nodeTuple.getKeyNode());
                resetTag(cls4, nodeTuple.getValueNode());
            }
        }
    }

    private void resetTag(Class<? extends Object> cls, Node node) {
        if (node.getTag().matches(cls)) {
            if (Enum.class.isAssignableFrom(cls)) {
                node.setTag(Tag.STR);
            } else {
                node.setTag(Tag.MAP);
            }
        }
    }

    protected Set<Property> getProperties(Class<? extends Object> cls) {
        return getPropertyUtils().getProperties(cls);
    }
}
