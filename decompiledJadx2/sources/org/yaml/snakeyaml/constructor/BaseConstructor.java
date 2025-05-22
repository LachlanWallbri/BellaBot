package org.yaml.snakeyaml.constructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.yaml.snakeyaml.composer.Composer;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.introspector.PropertyUtils;
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
public abstract class BaseConstructor {
    protected Composer composer;
    private PropertyUtils propertyUtils;
    protected final Map<NodeId, Construct> yamlClassConstructors = new EnumMap(NodeId.class);
    protected final Map<Tag, Construct> yamlConstructors = new HashMap();
    protected final Map<String, Construct> yamlMultiConstructors = new HashMap();
    private final Map<Node, Object> constructedObjects = new HashMap();
    private final Set<Node> recursiveObjects = new HashSet();
    private final ArrayList<RecursiveTuple<Map<Object, Object>, RecursiveTuple<Object, Object>>> maps2fill = new ArrayList<>();
    private final ArrayList<RecursiveTuple<Set<Object>, Object>> sets2fill = new ArrayList<>();
    protected Tag rootTag = null;
    private boolean explicitPropertyUtils = false;

    public void setComposer(Composer composer) {
        this.composer = composer;
    }

    public boolean checkData() {
        return this.composer.checkNode();
    }

    public Object getData() {
        this.composer.checkNode();
        Node node = this.composer.getNode();
        Tag tag = this.rootTag;
        if (tag != null) {
            node.setTag(tag);
        }
        return constructDocument(node);
    }

    public Object getSingleData(Class<?> cls) {
        Node singleNode = this.composer.getSingleNode();
        if (singleNode == null) {
            return null;
        }
        if (Object.class != cls) {
            singleNode.setTag(new Tag((Class<? extends Object>) cls));
        } else {
            Tag tag = this.rootTag;
            if (tag != null) {
                singleNode.setTag(tag);
            }
        }
        return constructDocument(singleNode);
    }

    protected final Object constructDocument(Node node) {
        Object constructObject = constructObject(node);
        fillRecursive();
        this.constructedObjects.clear();
        this.recursiveObjects.clear();
        return constructObject;
    }

    private void fillRecursive() {
        if (!this.maps2fill.isEmpty()) {
            Iterator<RecursiveTuple<Map<Object, Object>, RecursiveTuple<Object, Object>>> it = this.maps2fill.iterator();
            while (it.hasNext()) {
                RecursiveTuple<Map<Object, Object>, RecursiveTuple<Object, Object>> next = it.next();
                RecursiveTuple<Object, Object> m4254_2 = next.m4254_2();
                next.m4253_1().put(m4254_2.m4253_1(), m4254_2.m4254_2());
            }
            this.maps2fill.clear();
        }
        if (this.sets2fill.isEmpty()) {
            return;
        }
        Iterator<RecursiveTuple<Set<Object>, Object>> it2 = this.sets2fill.iterator();
        while (it2.hasNext()) {
            RecursiveTuple<Set<Object>, Object> next2 = it2.next();
            next2.m4253_1().add(next2.m4254_2());
        }
        this.sets2fill.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object constructObject(Node node) {
        if (this.constructedObjects.containsKey(node)) {
            return this.constructedObjects.get(node);
        }
        if (this.recursiveObjects.contains(node)) {
            throw new ConstructorException(null, null, "found unconstructable recursive node", node.getStartMark());
        }
        this.recursiveObjects.add(node);
        Construct constructor = getConstructor(node);
        Object construct = constructor.construct(node);
        this.constructedObjects.put(node, construct);
        this.recursiveObjects.remove(node);
        if (node.isTwoStepsConstruction()) {
            constructor.construct2ndStep(node, construct);
        }
        return construct;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Construct getConstructor(Node node) {
        if (node.useClassConstructor()) {
            return this.yamlClassConstructors.get(node.getNodeId());
        }
        Construct construct = this.yamlConstructors.get(node.getTag());
        if (construct != null) {
            return construct;
        }
        for (String str : this.yamlMultiConstructors.keySet()) {
            if (node.getTag().startsWith(str)) {
                return this.yamlMultiConstructors.get(str);
            }
        }
        return this.yamlConstructors.get(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object constructScalar(ScalarNode scalarNode) {
        return scalarNode.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<Object> createDefaultList(int i) {
        return new ArrayList(i);
    }

    protected Set<Object> createDefaultSet(int i) {
        return new LinkedHashSet(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object createArray(Class<?> cls, int i) {
        return Array.newInstance(cls.getComponentType(), i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<? extends Object> constructSequence(SequenceNode sequenceNode) {
        List<? extends Object> createDefaultList;
        if (List.class.isAssignableFrom(sequenceNode.getType()) && !sequenceNode.getType().isInterface()) {
            try {
                createDefaultList = (List) sequenceNode.getType().newInstance();
            } catch (Exception e) {
                throw new YAMLException(e);
            }
        } else {
            createDefaultList = createDefaultList(sequenceNode.getValue().size());
        }
        constructSequenceStep2(sequenceNode, createDefaultList);
        return createDefaultList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Set<? extends Object> constructSet(SequenceNode sequenceNode) {
        Set<Object> set;
        if (!sequenceNode.getType().isInterface()) {
            try {
                set = (Set) sequenceNode.getType().newInstance();
            } catch (Exception e) {
                throw new YAMLException(e);
            }
        } else {
            set = createDefaultSet(sequenceNode.getValue().size());
        }
        constructSequenceStep2(sequenceNode, set);
        return set;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object constructArray(SequenceNode sequenceNode) {
        return constructArrayStep2(sequenceNode, createArray(sequenceNode.getType(), sequenceNode.getValue().size()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void constructSequenceStep2(SequenceNode sequenceNode, Collection<Object> collection) {
        Iterator<Node> it = sequenceNode.getValue().iterator();
        while (it.hasNext()) {
            collection.add(constructObject(it.next()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public Object constructArrayStep2(SequenceNode sequenceNode, Object obj) {
        Class<?> componentType = sequenceNode.getType().getComponentType();
        int i = 0;
        for (Node node : sequenceNode.getValue()) {
            if (node.getType() == Object.class) {
                node.setType(componentType);
            }
            Object constructObject = constructObject(node);
            if (componentType.isPrimitive()) {
                if (constructObject == null) {
                    throw new NullPointerException("Unable to construct element value for " + node);
                }
                if (Byte.TYPE.equals(componentType)) {
                    Array.setByte(obj, i, ((Number) constructObject).byteValue());
                } else if (Short.TYPE.equals(componentType)) {
                    Array.setShort(obj, i, ((Number) constructObject).shortValue());
                } else if (Integer.TYPE.equals(componentType)) {
                    Array.setInt(obj, i, ((Number) constructObject).intValue());
                } else if (Long.TYPE.equals(componentType)) {
                    Array.setLong(obj, i, ((Number) constructObject).longValue());
                } else if (Float.TYPE.equals(componentType)) {
                    Array.setFloat(obj, i, ((Number) constructObject).floatValue());
                } else if (Double.TYPE.equals(componentType)) {
                    Array.setDouble(obj, i, ((Number) constructObject).doubleValue());
                } else if (Character.TYPE.equals(componentType)) {
                    Array.setChar(obj, i, ((Character) constructObject).charValue());
                } else if (Boolean.TYPE.equals(componentType)) {
                    Array.setBoolean(obj, i, ((Boolean) constructObject).booleanValue());
                } else {
                    throw new YAMLException("unexpected primitive type");
                }
            } else {
                Array.set(obj, i, constructObject);
            }
            i++;
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<Object, Object> createDefaultMap() {
        return new LinkedHashMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Set<Object> createDefaultSet() {
        return new LinkedHashSet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Set<Object> constructSet(MappingNode mappingNode) {
        Set<Object> createDefaultSet = createDefaultSet();
        constructSet2ndStep(mappingNode, createDefaultSet);
        return createDefaultSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map<Object, Object> constructMapping(MappingNode mappingNode) {
        Map<Object, Object> createDefaultMap = createDefaultMap();
        constructMapping2ndStep(mappingNode, createDefaultMap);
        return createDefaultMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void constructMapping2ndStep(MappingNode mappingNode, Map<Object, Object> map) {
        for (NodeTuple nodeTuple : mappingNode.getValue()) {
            Node keyNode = nodeTuple.getKeyNode();
            Node valueNode = nodeTuple.getValueNode();
            Object constructObject = constructObject(keyNode);
            if (constructObject != null) {
                try {
                    constructObject.hashCode();
                } catch (Exception e) {
                    throw new ConstructorException("while constructing a mapping", mappingNode.getStartMark(), "found unacceptable key " + constructObject, nodeTuple.getKeyNode().getStartMark(), e);
                }
            }
            Object constructObject2 = constructObject(valueNode);
            if (keyNode.isTwoStepsConstruction()) {
                this.maps2fill.add(0, new RecursiveTuple<>(map, new RecursiveTuple(constructObject, constructObject2)));
            } else {
                map.put(constructObject, constructObject2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void constructSet2ndStep(MappingNode mappingNode, Set<Object> set) {
        for (NodeTuple nodeTuple : mappingNode.getValue()) {
            Node keyNode = nodeTuple.getKeyNode();
            Object constructObject = constructObject(keyNode);
            if (constructObject != null) {
                try {
                    constructObject.hashCode();
                } catch (Exception e) {
                    throw new ConstructorException("while constructing a Set", mappingNode.getStartMark(), "found unacceptable key " + constructObject, nodeTuple.getKeyNode().getStartMark(), e);
                }
            }
            if (keyNode.isTwoStepsConstruction()) {
                this.sets2fill.add(0, new RecursiveTuple<>(set, constructObject));
            } else {
                set.add(constructObject);
            }
        }
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public static class RecursiveTuple<T, K> {

        /* renamed from: _1 */
        private final T f10299_1;

        /* renamed from: _2 */
        private final K f10300_2;

        public RecursiveTuple(T t, K k) {
            this.f10299_1 = t;
            this.f10300_2 = k;
        }

        /* renamed from: _2 */
        public K m4254_2() {
            return this.f10300_2;
        }

        /* renamed from: _1 */
        public T m4253_1() {
            return this.f10299_1;
        }
    }

    public final boolean isExplicitPropertyUtils() {
        return this.explicitPropertyUtils;
    }
}
