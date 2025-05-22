package org.yaml.snakeyaml.constructor;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlinx.coroutines.DebugKt;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
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
public class SafeConstructor extends BaseConstructor {
    private static final Pattern TIMESTAMP_REGEXP;
    private static final Pattern YMD_REGEXP;
    public static final ConstructUndefined undefinedConstructor = new ConstructUndefined();
    private static final Map<String, Boolean> BOOL_VALUES = new HashMap();

    static {
        BOOL_VALUES.put("yes", Boolean.TRUE);
        BOOL_VALUES.put("no", Boolean.FALSE);
        BOOL_VALUES.put("true", Boolean.TRUE);
        BOOL_VALUES.put("false", Boolean.FALSE);
        BOOL_VALUES.put(DebugKt.DEBUG_PROPERTY_VALUE_ON, Boolean.TRUE);
        BOOL_VALUES.put("off", Boolean.FALSE);
        TIMESTAMP_REGEXP = Pattern.compile("^([0-9][0-9][0-9][0-9])-([0-9][0-9]?)-([0-9][0-9]?)(?:(?:[Tt]|[ \t]+)([0-9][0-9]?):([0-9][0-9]):([0-9][0-9])(?:\\.([0-9]*))?(?:[ \t]*(?:Z|([-+][0-9][0-9]?)(?::([0-9][0-9])?)?))?)?$");
        YMD_REGEXP = Pattern.compile("^([0-9][0-9][0-9][0-9])-([0-9][0-9]?)-([0-9][0-9]?)$");
    }

    public SafeConstructor() {
        this.yamlConstructors.put(Tag.NULL, new ConstructYamlNull());
        this.yamlConstructors.put(Tag.BOOL, new ConstructYamlBool());
        this.yamlConstructors.put(Tag.INT, new ConstructYamlInt());
        this.yamlConstructors.put(Tag.FLOAT, new ConstructYamlFloat());
        this.yamlConstructors.put(Tag.BINARY, new ConstructYamlBinary());
        this.yamlConstructors.put(Tag.TIMESTAMP, new ConstructYamlTimestamp());
        this.yamlConstructors.put(Tag.OMAP, new ConstructYamlOmap());
        this.yamlConstructors.put(Tag.PAIRS, new ConstructYamlPairs());
        this.yamlConstructors.put(Tag.SET, new ConstructYamlSet());
        this.yamlConstructors.put(Tag.STR, new ConstructYamlStr());
        this.yamlConstructors.put(Tag.SEQ, new ConstructYamlSeq());
        this.yamlConstructors.put(Tag.MAP, new ConstructYamlMap());
        this.yamlConstructors.put(null, undefinedConstructor);
        this.yamlClassConstructors.put(NodeId.scalar, undefinedConstructor);
        this.yamlClassConstructors.put(NodeId.sequence, undefinedConstructor);
        this.yamlClassConstructors.put(NodeId.mapping, undefinedConstructor);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void flattenMapping(MappingNode mappingNode) {
        if (mappingNode.isMerged()) {
            mappingNode.setValue(mergeNode(mappingNode, true, new HashMap(), new ArrayList()));
        }
    }

    private List<NodeTuple> mergeNode(MappingNode mappingNode, boolean z, Map<Object, Integer> map, List<NodeTuple> list) {
        List<NodeTuple> value = mappingNode.getValue();
        Collections.reverse(value);
        Iterator<NodeTuple> it = value.iterator();
        while (it.hasNext()) {
            NodeTuple next = it.next();
            Node keyNode = next.getKeyNode();
            Node valueNode = next.getValueNode();
            if (keyNode.getTag().equals(Tag.MERGE)) {
                it.remove();
                int i = C89851.$SwitchMap$org$yaml$snakeyaml$nodes$NodeId[valueNode.getNodeId().ordinal()];
                if (i == 1) {
                    mergeNode((MappingNode) valueNode, false, map, list);
                } else if (i == 2) {
                    for (Node node : ((SequenceNode) valueNode).getValue()) {
                        if (!(node instanceof MappingNode)) {
                            throw new ConstructorException("while constructing a mapping", mappingNode.getStartMark(), "expected a mapping for merging, but found " + node.getNodeId(), node.getStartMark());
                        }
                        mergeNode((MappingNode) node, false, map, list);
                    }
                } else {
                    throw new ConstructorException("while constructing a mapping", mappingNode.getStartMark(), "expected a mapping or list of mappings for merging, but found " + valueNode.getNodeId(), valueNode.getStartMark());
                }
            } else {
                Object constructObject = constructObject(keyNode);
                if (!map.containsKey(constructObject)) {
                    list.add(next);
                    map.put(constructObject, Integer.valueOf(list.size() - 1));
                } else if (z) {
                    list.set(map.get(constructObject).intValue(), next);
                }
            }
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* renamed from: org.yaml.snakeyaml.constructor.SafeConstructor$1 */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class C89851 {
        static final /* synthetic */ int[] $SwitchMap$org$yaml$snakeyaml$nodes$NodeId = new int[NodeId.values().length];

        static {
            try {
                $SwitchMap$org$yaml$snakeyaml$nodes$NodeId[NodeId.mapping.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$yaml$snakeyaml$nodes$NodeId[NodeId.sequence.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.yaml.snakeyaml.constructor.BaseConstructor
    public void constructMapping2ndStep(MappingNode mappingNode, Map<Object, Object> map) {
        flattenMapping(mappingNode);
        super.constructMapping2ndStep(mappingNode, map);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.yaml.snakeyaml.constructor.BaseConstructor
    public void constructSet2ndStep(MappingNode mappingNode, Set<Object> set) {
        flattenMapping(mappingNode);
        super.constructSet2ndStep(mappingNode, set);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlNull extends AbstractConstruct {
        public ConstructYamlNull() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            SafeConstructor.this.constructScalar((ScalarNode) node);
            return null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlBool extends AbstractConstruct {
        public ConstructYamlBool() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            return SafeConstructor.BOOL_VALUES.get(((String) SafeConstructor.this.constructScalar((ScalarNode) node)).toLowerCase());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlInt extends AbstractConstruct {
        public ConstructYamlInt() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            int i;
            String substring;
            String replaceAll = SafeConstructor.this.constructScalar((ScalarNode) node).toString().replaceAll("_", "");
            char charAt = replaceAll.charAt(0);
            if (charAt == '-') {
                replaceAll = replaceAll.substring(1);
                i = -1;
            } else {
                if (charAt == '+') {
                    replaceAll = replaceAll.substring(1);
                }
                i = 1;
            }
            if ("0".equals(replaceAll)) {
                return 0;
            }
            int i2 = 2;
            if (replaceAll.startsWith("0b")) {
                substring = replaceAll.substring(2);
            } else if (replaceAll.startsWith("0x")) {
                substring = replaceAll.substring(2);
                i2 = 16;
            } else if (replaceAll.startsWith("0")) {
                substring = replaceAll.substring(1);
                i2 = 8;
            } else {
                if (replaceAll.indexOf(58) == -1) {
                    return SafeConstructor.this.createNumber(i, replaceAll, 10);
                }
                String[] split = replaceAll.split(":");
                int length = split.length;
                int i3 = 0;
                int i4 = 1;
                for (int i5 = 0; i5 < length; i5++) {
                    i3 = (int) (i3 + (Long.parseLong(split[(length - i5) - 1]) * i4));
                    i4 *= 60;
                }
                return SafeConstructor.this.createNumber(i, String.valueOf(i3), 10);
            }
            return SafeConstructor.this.createNumber(i, substring, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Number createNumber(int i, String str, int i2) {
        if (i < 0) {
            str = "-" + str;
        }
        try {
            try {
                return Integer.valueOf(str, i2);
            } catch (NumberFormatException unused) {
                return new BigInteger(str, i2);
            }
        } catch (NumberFormatException unused2) {
            return Long.valueOf(str, i2);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlFloat extends AbstractConstruct {
        public ConstructYamlFloat() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            int i;
            String replaceAll = SafeConstructor.this.constructScalar((ScalarNode) node).toString().replaceAll("_", "");
            char charAt = replaceAll.charAt(0);
            if (charAt == '-') {
                replaceAll = replaceAll.substring(1);
                i = -1;
            } else {
                if (charAt == '+') {
                    replaceAll = replaceAll.substring(1);
                }
                i = 1;
            }
            String lowerCase = replaceAll.toLowerCase();
            if (".inf".equals(lowerCase)) {
                return new Double(i == -1 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            if (".nan".equals(lowerCase)) {
                return new Double(Double.NaN);
            }
            if (replaceAll.indexOf(58) != -1) {
                String[] split = replaceAll.split(":");
                double d = 0.0d;
                int length = split.length;
                int i2 = 1;
                for (int i3 = 0; i3 < length; i3++) {
                    d += Double.parseDouble(split[(length - i3) - 1]) * i2;
                    i2 *= 60;
                }
                return new Double(i * d);
            }
            return new Double(Double.valueOf(replaceAll).doubleValue() * i);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlBinary extends AbstractConstruct {
        public ConstructYamlBinary() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            return Base64Coder.decode(SafeConstructor.this.constructScalar((ScalarNode) node).toString().toCharArray());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlNumber extends AbstractConstruct {

        /* renamed from: nf */
        private final NumberFormat f10301nf = NumberFormat.getInstance();

        public ConstructYamlNumber() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            ScalarNode scalarNode = (ScalarNode) node;
            try {
                return this.f10301nf.parse(scalarNode.getValue());
            } catch (ParseException unused) {
                String lowerCase = scalarNode.getValue().toLowerCase();
                if (lowerCase.contains("inf") || lowerCase.contains("nan")) {
                    return (Number) SafeConstructor.this.yamlConstructors.get(Tag.FLOAT).construct(node);
                }
                throw new IllegalArgumentException("Unable to parse as Number: " + scalarNode.getValue());
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public static class ConstructYamlTimestamp extends AbstractConstruct {
        private Calendar calendar;

        public Calendar getCalendar() {
            return this.calendar;
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            TimeZone timeZone;
            String str;
            String value = ((ScalarNode) node).getValue();
            Matcher matcher = SafeConstructor.YMD_REGEXP.matcher(value);
            if (!matcher.matches()) {
                Matcher matcher2 = SafeConstructor.TIMESTAMP_REGEXP.matcher(value);
                if (!matcher2.matches()) {
                    throw new YAMLException("Unexpected timestamp: " + value);
                }
                String group = matcher2.group(1);
                String group2 = matcher2.group(2);
                String group3 = matcher2.group(3);
                String group4 = matcher2.group(4);
                String group5 = matcher2.group(5);
                String group6 = matcher2.group(6);
                String group7 = matcher2.group(7);
                if (group7 != null) {
                    group6 = group6 + "." + group7;
                }
                double parseDouble = Double.parseDouble(group6);
                int round = (int) Math.round(Math.floor(parseDouble));
                int round2 = (int) Math.round((parseDouble - round) * 1000.0d);
                String group8 = matcher2.group(8);
                String group9 = matcher2.group(9);
                if (group8 != null) {
                    if (group9 != null) {
                        str = ":" + group9;
                    } else {
                        str = "00";
                    }
                    timeZone = TimeZone.getTimeZone("GMT" + group8 + str);
                } else {
                    timeZone = TimeZone.getTimeZone("UTC");
                }
                this.calendar = Calendar.getInstance(timeZone);
                this.calendar.set(1, Integer.parseInt(group));
                this.calendar.set(2, Integer.parseInt(group2) - 1);
                this.calendar.set(5, Integer.parseInt(group3));
                this.calendar.set(11, Integer.parseInt(group4));
                this.calendar.set(12, Integer.parseInt(group5));
                this.calendar.set(13, round);
                this.calendar.set(14, round2);
                return this.calendar.getTime();
            }
            String group10 = matcher.group(1);
            String group11 = matcher.group(2);
            String group12 = matcher.group(3);
            this.calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.calendar.clear();
            this.calendar.set(1, Integer.parseInt(group10));
            this.calendar.set(2, Integer.parseInt(group11) - 1);
            this.calendar.set(5, Integer.parseInt(group12));
            return this.calendar.getTime();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlOmap extends AbstractConstruct {
        public ConstructYamlOmap() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (!(node instanceof SequenceNode)) {
                throw new ConstructorException("while constructing an ordered map", node.getStartMark(), "expected a sequence, but found " + node.getNodeId(), node.getStartMark());
            }
            for (Node node2 : ((SequenceNode) node).getValue()) {
                if (!(node2 instanceof MappingNode)) {
                    throw new ConstructorException("while constructing an ordered map", node.getStartMark(), "expected a mapping of length 1, but found " + node2.getNodeId(), node2.getStartMark());
                }
                MappingNode mappingNode = (MappingNode) node2;
                if (mappingNode.getValue().size() != 1) {
                    throw new ConstructorException("while constructing an ordered map", node.getStartMark(), "expected a single mapping item, but found " + mappingNode.getValue().size() + " items", mappingNode.getStartMark());
                }
                linkedHashMap.put(SafeConstructor.this.constructObject(mappingNode.getValue().get(0).getKeyNode()), SafeConstructor.this.constructObject(mappingNode.getValue().get(0).getValueNode()));
            }
            return linkedHashMap;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlPairs extends AbstractConstruct {
        public ConstructYamlPairs() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            if (!(node instanceof SequenceNode)) {
                throw new ConstructorException("while constructing pairs", node.getStartMark(), "expected a sequence, but found " + node.getNodeId(), node.getStartMark());
            }
            SequenceNode sequenceNode = (SequenceNode) node;
            ArrayList arrayList = new ArrayList(sequenceNode.getValue().size());
            for (Node node2 : sequenceNode.getValue()) {
                if (!(node2 instanceof MappingNode)) {
                    throw new ConstructorException("while constructingpairs", node.getStartMark(), "expected a mapping of length 1, but found " + node2.getNodeId(), node2.getStartMark());
                }
                MappingNode mappingNode = (MappingNode) node2;
                if (mappingNode.getValue().size() != 1) {
                    throw new ConstructorException("while constructing pairs", node.getStartMark(), "expected a single mapping item, but found " + mappingNode.getValue().size() + " items", mappingNode.getStartMark());
                }
                arrayList.add(new Object[]{SafeConstructor.this.constructObject(mappingNode.getValue().get(0).getKeyNode()), SafeConstructor.this.constructObject(mappingNode.getValue().get(0).getValueNode())});
            }
            return arrayList;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlSet implements Construct {
        public ConstructYamlSet() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            if (node.isTwoStepsConstruction()) {
                return SafeConstructor.this.createDefaultSet();
            }
            return SafeConstructor.this.constructSet((MappingNode) node);
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public void construct2ndStep(Node node, Object obj) {
            if (node.isTwoStepsConstruction()) {
                SafeConstructor.this.constructSet2ndStep((MappingNode) node, (Set) obj);
                return;
            }
            throw new YAMLException("Unexpected recursive set structure. Node: " + node);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlStr extends AbstractConstruct {
        public ConstructYamlStr() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            return SafeConstructor.this.constructScalar((ScalarNode) node);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlSeq implements Construct {
        public ConstructYamlSeq() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            SequenceNode sequenceNode = (SequenceNode) node;
            if (node.isTwoStepsConstruction()) {
                return SafeConstructor.this.createDefaultList(sequenceNode.getValue().size());
            }
            return SafeConstructor.this.constructSequence(sequenceNode);
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public void construct2ndStep(Node node, Object obj) {
            if (node.isTwoStepsConstruction()) {
                SafeConstructor.this.constructSequenceStep2((SequenceNode) node, (List) obj);
                return;
            }
            throw new YAMLException("Unexpected recursive sequence structure. Node: " + node);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class ConstructYamlMap implements Construct {
        public ConstructYamlMap() {
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            if (node.isTwoStepsConstruction()) {
                return SafeConstructor.this.createDefaultMap();
            }
            return SafeConstructor.this.constructMapping((MappingNode) node);
        }

        @Override // org.yaml.snakeyaml.constructor.Construct
        public void construct2ndStep(Node node, Object obj) {
            if (node.isTwoStepsConstruction()) {
                SafeConstructor.this.constructMapping2ndStep((MappingNode) node, (Map) obj);
                return;
            }
            throw new YAMLException("Unexpected recursive mapping structure. Node: " + node);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public static final class ConstructUndefined extends AbstractConstruct {
        @Override // org.yaml.snakeyaml.constructor.Construct
        public Object construct(Node node) {
            throw new ConstructorException(null, null, "could not determine a constructor for the tag " + node.getTag(), node.getStartMark());
        }
    }
}
