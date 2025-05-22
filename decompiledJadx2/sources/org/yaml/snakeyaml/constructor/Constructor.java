package org.yaml.snakeyaml.constructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.error.YAMLException;
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
public class Constructor extends SafeConstructor {
    protected final Map<Class<? extends Object>, TypeDescription> typeDefinitions;
    private final Map<Tag, Class<? extends Object>> typeTags;

    public Constructor() {
        this((Class<? extends Object>) Object.class);
    }

    public Constructor(Class<? extends Object> cls) {
        this(new TypeDescription(checkRoot(cls)));
    }

    private static Class<? extends Object> checkRoot(Class<? extends Object> cls) {
        if (cls != null) {
            return cls;
        }
        throw new NullPointerException("Root class must be provided.");
    }

    public Constructor(TypeDescription typeDescription) {
        if (typeDescription == null) {
            throw new NullPointerException("Root type must be provided.");
        }
        this.yamlConstructors.put(null, new ConstructYamlObject());
        if (!Object.class.equals(typeDescription.getType())) {
            this.rootTag = new Tag(typeDescription.getType());
        }
        this.typeTags = new HashMap();
        this.typeDefinitions = new HashMap();
        this.yamlClassConstructors.put(NodeId.scalar, new ConstructScalar());
        this.yamlClassConstructors.put(NodeId.mapping, new ConstructMapping());
        this.yamlClassConstructors.put(NodeId.sequence, new ConstructSequence());
        addTypeDescription(typeDescription);
    }

    public Constructor(String str) throws ClassNotFoundException {
        this((Class<? extends Object>) Class.forName(check(str)));
    }

    private static final String check(String str) {
        if (str == null) {
            throw new NullPointerException("Root type must be provided.");
        }
        if (str.trim().length() != 0) {
            return str;
        }
        throw new YAMLException("Root type must be provided.");
    }

    public TypeDescription addTypeDescription(TypeDescription typeDescription) {
        if (typeDescription == null) {
            throw new NullPointerException("TypeDescription is required.");
        }
        this.typeTags.put(typeDescription.getTag(), typeDescription.getType());
        return this.typeDefinitions.put(typeDescription.getType(), typeDescription);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class ConstructMapping implements Construct {
        /* JADX INFO: Access modifiers changed from: protected */
        public ConstructMapping() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            MappingNode mappingNode = (MappingNode) node;
            if (Properties.class.isAssignableFrom(node.getType())) {
                Properties properties = new Properties();
                if (!node.isTwoStepsConstruction()) {
                    Constructor.this.constructMapping2ndStep(mappingNode, properties);
                    return properties;
                }
                throw new YAMLException("Properties must not be recursive.");
            }
            if (SortedMap.class.isAssignableFrom(node.getType())) {
                TreeMap treeMap = new TreeMap();
                if (!node.isTwoStepsConstruction()) {
                    Constructor.this.constructMapping2ndStep(mappingNode, treeMap);
                }
                return treeMap;
            }
            if (Map.class.isAssignableFrom(node.getType())) {
                if (node.isTwoStepsConstruction()) {
                    return Constructor.this.createDefaultMap();
                }
                return Constructor.this.constructMapping(mappingNode);
            }
            if (SortedSet.class.isAssignableFrom(node.getType())) {
                TreeSet treeSet = new TreeSet();
                Constructor.this.constructSet2ndStep(mappingNode, treeSet);
                return treeSet;
            }
            if (Collection.class.isAssignableFrom(node.getType())) {
                if (node.isTwoStepsConstruction()) {
                    return Constructor.this.createDefaultSet();
                }
                return Constructor.this.constructSet(mappingNode);
            }
            if (node.isTwoStepsConstruction()) {
                return createEmptyJavaBean(mappingNode);
            }
            return constructJavaBean2ndStep(mappingNode, createEmptyJavaBean(mappingNode));
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public void construct2ndStep(Node node, Object obj) {
            if (Map.class.isAssignableFrom(node.getType())) {
                Constructor.this.constructMapping2ndStep((MappingNode) node, (Map) obj);
            } else if (Set.class.isAssignableFrom(node.getType())) {
                Constructor.this.constructSet2ndStep((MappingNode) node, (Set) obj);
            } else {
                constructJavaBean2ndStep((MappingNode) node, obj);
            }
        }

        protected Object createEmptyJavaBean(MappingNode mappingNode) {
            try {
                java.lang.reflect.Constructor<? extends Object> declaredConstructor = mappingNode.getType().getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                return declaredConstructor.newInstance(new Object[0]);
            } catch (Exception e) {
                throw new YAMLException(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00b8 A[Catch: Exception -> 0x0145, TryCatch #0 {Exception -> 0x0145, blocks: (B:8:0x003c, B:10:0x0055, B:15:0x0067, B:17:0x0070, B:20:0x009f, B:22:0x00a7, B:24:0x00ad, B:26:0x00b0, B:28:0x00b8, B:29:0x00c1, B:31:0x00cd, B:32:0x00dd, B:34:0x00e9, B:35:0x00fa, B:37:0x0108, B:39:0x011e, B:41:0x0126, B:43:0x0132, B:45:0x0136, B:47:0x0140, B:51:0x0110, B:53:0x0114, B:54:0x0078, B:56:0x0081, B:57:0x0086, B:59:0x0090), top: B:7:0x003c }] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00c1 A[Catch: Exception -> 0x0145, TryCatch #0 {Exception -> 0x0145, blocks: (B:8:0x003c, B:10:0x0055, B:15:0x0067, B:17:0x0070, B:20:0x009f, B:22:0x00a7, B:24:0x00ad, B:26:0x00b0, B:28:0x00b8, B:29:0x00c1, B:31:0x00cd, B:32:0x00dd, B:34:0x00e9, B:35:0x00fa, B:37:0x0108, B:39:0x011e, B:41:0x0126, B:43:0x0132, B:45:0x0136, B:47:0x0140, B:51:0x0110, B:53:0x0114, B:54:0x0078, B:56:0x0081, B:57:0x0086, B:59:0x0090), top: B:7:0x003c }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Object constructJavaBean2ndStep(MappingNode mappingNode, Object obj) {
            boolean z;
            Object constructObject;
            Class<?>[] actualTypeArguments;
            Constructor.this.flattenMapping(mappingNode);
            Class<? extends Object> type = mappingNode.getType();
            for (NodeTuple nodeTuple : mappingNode.getValue()) {
                if (nodeTuple.getKeyNode() instanceof ScalarNode) {
                    ScalarNode scalarNode = (ScalarNode) nodeTuple.getKeyNode();
                    Node valueNode = nodeTuple.getValueNode();
                    scalarNode.setType(String.class);
                    String str = (String) Constructor.this.constructObject(scalarNode);
                    try {
                        Property property = getProperty(type, str);
                        valueNode.setType(property.getType());
                        TypeDescription typeDescription = Constructor.this.typeDefinitions.get(type);
                        if (typeDescription != null) {
                            int i = C89841.$SwitchMap$org$yaml$snakeyaml$nodes$NodeId[valueNode.getNodeId().ordinal()];
                            if (i == 1) {
                                SequenceNode sequenceNode = (SequenceNode) valueNode;
                                Class<? extends Object> listPropertyType = typeDescription.getListPropertyType(str);
                                if (listPropertyType != null) {
                                    sequenceNode.setListType(listPropertyType);
                                } else if (property.getType().isArray()) {
                                    sequenceNode.setListType(property.getType().getComponentType());
                                }
                                z = true;
                            } else if (i == 2) {
                                MappingNode mappingNode2 = (MappingNode) valueNode;
                                Class<? extends Object> mapKeyType = typeDescription.getMapKeyType(str);
                                if (mapKeyType != null) {
                                    mappingNode2.setTypes(mapKeyType, typeDescription.getMapValueType(str));
                                    z = true;
                                }
                            }
                            if (!z && valueNode.getNodeId() != NodeId.scalar && (actualTypeArguments = property.getActualTypeArguments()) != null && actualTypeArguments.length > 0) {
                                if (valueNode.getNodeId() != NodeId.sequence) {
                                    ((SequenceNode) valueNode).setListType(actualTypeArguments[0]);
                                } else if (valueNode.getTag().equals(Tag.SET)) {
                                    MappingNode mappingNode3 = (MappingNode) valueNode;
                                    mappingNode3.setOnlyKeyType(actualTypeArguments[0]);
                                    mappingNode3.setUseClassConstructor(true);
                                } else if (property.getType().isAssignableFrom(Map.class)) {
                                    MappingNode mappingNode4 = (MappingNode) valueNode;
                                    mappingNode4.setTypes(actualTypeArguments[0], actualTypeArguments[1]);
                                    mappingNode4.setUseClassConstructor(true);
                                }
                            }
                            constructObject = Constructor.this.constructObject(valueNode);
                            if ((property.getType() != Float.TYPE || property.getType() == Float.class) && (constructObject instanceof Double)) {
                                constructObject = Float.valueOf(((Double) constructObject).floatValue());
                            }
                            if (property.getType() == String.class && Tag.BINARY.equals(valueNode.getTag()) && (constructObject instanceof byte[])) {
                                constructObject = new String((byte[]) constructObject);
                            }
                            property.set(obj, constructObject);
                        }
                        z = false;
                        if (!z) {
                            if (valueNode.getNodeId() != NodeId.sequence) {
                            }
                        }
                        constructObject = Constructor.this.constructObject(valueNode);
                        if (property.getType() != Float.TYPE) {
                        }
                        constructObject = Float.valueOf(((Double) constructObject).floatValue());
                        if (property.getType() == String.class) {
                            constructObject = new String((byte[]) constructObject);
                        }
                        property.set(obj, constructObject);
                    } catch (Exception e) {
                        throw new ConstructorException("Cannot create property=" + str + " for JavaBean=" + obj, mappingNode.getStartMark(), e.getMessage(), valueNode.getStartMark(), e);
                    }
                } else {
                    throw new YAMLException("Keys must be scalars but found: " + nodeTuple.getKeyNode());
                }
            }
            return obj;
        }

        protected Property getProperty(Class<? extends Object> cls, String str) {
            return Constructor.this.getPropertyUtils().getProperty(cls, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* renamed from: org.yaml.snakeyaml.constructor.Constructor$1 */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class C89841 {
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
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class ConstructYamlObject implements Construct {
        protected ConstructYamlObject() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Construct getConstructor(Node node) {
            node.setType(Constructor.this.getClassForNode(node));
            return Constructor.this.yamlClassConstructors.get(node.getNodeId());
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            try {
                return getConstructor(node).construct(node);
            } catch (ConstructorException e) {
                throw e;
            } catch (Exception e2) {
                throw new ConstructorException(null, null, "Can't construct a java object for " + node.getTag() + "; exception=" + e2.getMessage(), node.getStartMark(), e2);
            }
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public void construct2ndStep(Node node, Object obj) {
            try {
                getConstructor(node).construct2ndStep(node, obj);
            } catch (Exception e) {
                throw new ConstructorException(null, null, "Can't construct a second step for a java object for " + node.getTag() + "; exception=" + e.getMessage(), node.getStartMark(), e);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class ConstructScalar extends AbstractConstruct {
        protected ConstructScalar() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            Object obj;
            ScalarNode scalarNode = (ScalarNode) node;
            Class<? extends Object> type = scalarNode.getType();
            if (type.isPrimitive() || type == String.class || Number.class.isAssignableFrom(type) || type == Boolean.class || Date.class.isAssignableFrom(type) || type == Character.class || type == BigInteger.class || type == BigDecimal.class || Enum.class.isAssignableFrom(type) || Tag.BINARY.equals(scalarNode.getTag()) || Calendar.class.isAssignableFrom(type) || type == UUID.class) {
                return constructStandardJavaInstance(type, scalarNode);
            }
            java.lang.reflect.Constructor<?> constructor = null;
            int i = 0;
            for (java.lang.reflect.Constructor<?> constructor2 : type.getDeclaredConstructors()) {
                if (constructor2.getParameterTypes().length == 1) {
                    i++;
                    constructor = constructor2;
                }
            }
            if (constructor == null) {
                throw new YAMLException("No single argument constructor found for " + type);
            }
            if (i == 1) {
                obj = constructStandardJavaInstance(constructor.getParameterTypes()[0], scalarNode);
            } else {
                Object constructScalar = Constructor.this.constructScalar(scalarNode);
                try {
                    constructor = type.getDeclaredConstructor(String.class);
                    obj = constructScalar;
                } catch (Exception e) {
                    throw new YAMLException("Can't construct a java object for scalar " + scalarNode.getTag() + "; No String constructor found. Exception=" + e.getMessage(), e);
                }
            }
            try {
                constructor.setAccessible(true);
                return constructor.newInstance(obj);
            } catch (Exception e2) {
                throw new ConstructorException(null, null, "Can't construct a java object for scalar " + scalarNode.getTag() + "; exception=" + e2.getMessage(), scalarNode.getStartMark(), e2);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:116:0x01fa, code lost:
        
            if (r6 == java.lang.Float.TYPE) goto L109;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private Object constructStandardJavaInstance(Class cls, ScalarNode scalarNode) {
            Object obj;
            if (cls == String.class) {
                return Constructor.this.yamlConstructors.get(Tag.STR).construct(scalarNode);
            }
            if (cls == Boolean.class || cls == Boolean.TYPE) {
                return Constructor.this.yamlConstructors.get(Tag.BOOL).construct(scalarNode);
            }
            if (cls == Character.class || cls == Character.TYPE) {
                String str = (String) Constructor.this.yamlConstructors.get(Tag.STR).construct(scalarNode);
                if (str.length() == 0) {
                    return null;
                }
                if (str.length() != 1) {
                    throw new YAMLException("Invalid node Character: '" + str + "'; length: " + str.length());
                }
                return Character.valueOf(str.charAt(0));
            }
            if (Date.class.isAssignableFrom(cls)) {
                Date date = (Date) Constructor.this.yamlConstructors.get(Tag.TIMESTAMP).construct(scalarNode);
                obj = date;
                if (cls != Date.class) {
                    try {
                        return cls.getConstructor(Long.TYPE).newInstance(Long.valueOf(date.getTime()));
                    } catch (RuntimeException e) {
                        throw e;
                    } catch (Exception unused) {
                        throw new YAMLException("Cannot construct: '" + cls + "'");
                    }
                }
            } else {
                if (cls == Float.class || cls == Double.class || cls == Float.TYPE || cls == Double.TYPE || cls == BigDecimal.class) {
                    if (cls == BigDecimal.class) {
                        return new BigDecimal(scalarNode.getValue());
                    }
                    Object construct = Constructor.this.yamlConstructors.get(Tag.FLOAT).construct(scalarNode);
                    if (cls != Float.class) {
                        obj = construct;
                    }
                    return new Float(((Double) construct).doubleValue());
                }
                if (cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == BigInteger.class || cls == Byte.TYPE || cls == Short.TYPE || cls == Integer.TYPE || cls == Long.TYPE) {
                    Object construct2 = Constructor.this.yamlConstructors.get(Tag.INT).construct(scalarNode);
                    if (cls == Byte.class || cls == Byte.TYPE) {
                        return Byte.valueOf(construct2.toString());
                    }
                    if (cls == Short.class || cls == Short.TYPE) {
                        return Short.valueOf(construct2.toString());
                    }
                    if (cls == Integer.class || cls == Integer.TYPE) {
                        return Integer.valueOf(Integer.parseInt(construct2.toString()));
                    }
                    if (cls == Long.class || cls == Long.TYPE) {
                        return Long.valueOf(construct2.toString());
                    }
                    return new BigInteger(construct2.toString());
                }
                if (Enum.class.isAssignableFrom(cls)) {
                    String value = scalarNode.getValue();
                    try {
                        return Enum.valueOf(cls, value);
                    } catch (Exception unused2) {
                        throw new YAMLException("Unable to find enum value '" + value + "' for enum class: " + cls.getName());
                    }
                }
                if (Calendar.class.isAssignableFrom(cls)) {
                    SafeConstructor.ConstructYamlTimestamp constructYamlTimestamp = new SafeConstructor.ConstructYamlTimestamp();
                    constructYamlTimestamp.construct(scalarNode);
                    return constructYamlTimestamp.getCalendar();
                }
                if (Number.class.isAssignableFrom(cls)) {
                    return new SafeConstructor.ConstructYamlNumber().construct(scalarNode);
                }
                if (UUID.class == cls) {
                    return UUID.fromString(scalarNode.getValue());
                }
                if (Constructor.this.yamlConstructors.containsKey(scalarNode.getTag())) {
                    return Constructor.this.yamlConstructors.get(scalarNode.getTag()).construct(scalarNode);
                }
                throw new YAMLException("Unsupported class: " + cls);
            }
            return obj;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class ConstructSequence implements Construct {
        protected ConstructSequence() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            boolean z;
            SequenceNode sequenceNode = (SequenceNode) node;
            if (Set.class.isAssignableFrom(node.getType())) {
                if (node.isTwoStepsConstruction()) {
                    throw new YAMLException("Set cannot be recursive.");
                }
                return Constructor.this.constructSet(sequenceNode);
            }
            if (Collection.class.isAssignableFrom(node.getType())) {
                if (node.isTwoStepsConstruction()) {
                    return Constructor.this.createDefaultList(sequenceNode.getValue().size());
                }
                return Constructor.this.constructSequence(sequenceNode);
            }
            if (node.getType().isArray()) {
                if (node.isTwoStepsConstruction()) {
                    return Constructor.this.createArray(node.getType(), sequenceNode.getValue().size());
                }
                return Constructor.this.constructArray(sequenceNode);
            }
            ArrayList<java.lang.reflect.Constructor> arrayList = new ArrayList(sequenceNode.getValue().size());
            int i = 0;
            for (java.lang.reflect.Constructor<?> constructor : node.getType().getDeclaredConstructors()) {
                if (sequenceNode.getValue().size() == constructor.getParameterTypes().length) {
                    arrayList.add(constructor);
                }
            }
            if (!arrayList.isEmpty()) {
                if (arrayList.size() == 1) {
                    Object[] objArr = new Object[sequenceNode.getValue().size()];
                    java.lang.reflect.Constructor constructor2 = (java.lang.reflect.Constructor) arrayList.get(0);
                    for (Node node2 : sequenceNode.getValue()) {
                        node2.setType(constructor2.getParameterTypes()[i]);
                        objArr[i] = Constructor.this.constructObject(node2);
                        i++;
                    }
                    try {
                        constructor2.setAccessible(true);
                        return constructor2.newInstance(objArr);
                    } catch (Exception e) {
                        throw new YAMLException(e);
                    }
                }
                List<? extends Object> constructSequence = Constructor.this.constructSequence(sequenceNode);
                Class<?>[] clsArr = new Class[constructSequence.size()];
                Iterator<? extends Object> it = constructSequence.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    clsArr[i2] = it.next().getClass();
                    i2++;
                }
                for (java.lang.reflect.Constructor constructor3 : arrayList) {
                    Class<?>[] parameterTypes = constructor3.getParameterTypes();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= parameterTypes.length) {
                            z = true;
                            break;
                        }
                        if (!wrapIfPrimitive(parameterTypes[i3]).isAssignableFrom(clsArr[i3])) {
                            z = false;
                            break;
                        }
                        i3++;
                    }
                    if (z) {
                        try {
                            constructor3.setAccessible(true);
                            return constructor3.newInstance(constructSequence.toArray());
                        } catch (Exception e2) {
                            throw new YAMLException(e2);
                        }
                    }
                }
            }
            throw new YAMLException("No suitable constructor with " + String.valueOf(sequenceNode.getValue().size()) + " arguments found for " + node.getType());
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final Class<? extends Object> wrapIfPrimitive(Class<?> cls) {
            if (!cls.isPrimitive()) {
                return cls;
            }
            if (cls == Integer.TYPE) {
                return Integer.class;
            }
            if (cls == Float.TYPE) {
                return Float.class;
            }
            if (cls == Double.TYPE) {
                return Double.class;
            }
            if (cls == Boolean.TYPE) {
                return Boolean.class;
            }
            if (cls == Long.TYPE) {
                return Long.class;
            }
            if (cls == Character.TYPE) {
                return Character.class;
            }
            if (cls == Short.TYPE) {
                return Short.class;
            }
            if (cls == Byte.TYPE) {
                return Byte.class;
            }
            throw new YAMLException("Unexpected primitive " + cls);
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public void construct2ndStep(Node node, Object obj) {
            SequenceNode sequenceNode = (SequenceNode) node;
            if (List.class.isAssignableFrom(node.getType())) {
                Constructor.this.constructSequenceStep2(sequenceNode, (List) obj);
            } else {
                if (node.getType().isArray()) {
                    Constructor.this.constructArrayStep2(sequenceNode, obj);
                    return;
                }
                throw new YAMLException("Immutable objects cannot be recursive.");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected Class<?> getClassForNode(Node node) {
        Class<? extends Object> cls = this.typeTags.get(node.getTag());
        if (cls != null) {
            return cls;
        }
        String className = node.getTag().getClassName();
        try {
            Class<?> classForName = getClassForName(className);
            this.typeTags.put(node.getTag(), classForName);
            return classForName;
        } catch (ClassNotFoundException unused) {
            throw new YAMLException("Class not found: " + className);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Class<?> getClassForName(String str) throws ClassNotFoundException {
        try {
            return Class.forName(str, true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException unused) {
            return Class.forName(str);
        }
    }
}
