package com.google.protobuf.util;

import com.google.common.base.Splitter;
import com.google.protobuf.Descriptors;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Message;
import com.google.protobuf.util.FieldMaskUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
final class FieldMaskTree {
    private static final String FIELD_PATH_SEPARATOR_REGEX = "\\.";
    private static final Logger logger = Logger.getLogger(FieldMaskTree.class.getName());
    private final Node root = new Node();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public static final class Node {
        final SortedMap<String, Node> children;

        private Node() {
            this.children = new TreeMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldMaskTree() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldMaskTree(FieldMask fieldMask) {
        mergeFromFieldMask(fieldMask);
    }

    public String toString() {
        return FieldMaskUtil.toString(toFieldMask());
    }

    FieldMaskTree addFieldPath(String str) {
        String[] split = str.split(FIELD_PATH_SEPARATOR_REGEX);
        if (split.length == 0) {
            return this;
        }
        Node node = this.root;
        boolean z = false;
        for (String str2 : split) {
            if (!z && node != this.root && node.children.isEmpty()) {
                return this;
            }
            if (node.children.containsKey(str2)) {
                node = node.children.get(str2);
            } else {
                Node node2 = new Node();
                node.children.put(str2, node2);
                z = true;
                node = node2;
            }
        }
        node.children.clear();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldMaskTree mergeFromFieldMask(FieldMask fieldMask) {
        Iterator<String> it = fieldMask.getPathsList().iterator();
        while (it.hasNext()) {
            addFieldPath(it.next());
        }
        return this;
    }

    FieldMaskTree removeFieldPath(String str) {
        List<String> splitToList = Splitter.onPattern(FIELD_PATH_SEPARATOR_REGEX).splitToList(str);
        if (splitToList.isEmpty()) {
            return this;
        }
        removeFieldPath(this.root, splitToList, 0);
        return this;
    }

    private static boolean removeFieldPath(Node node, List<String> list, int i) {
        String str = list.get(i);
        if (!node.children.containsKey(str)) {
            return false;
        }
        if (i == list.size() - 1) {
            node.children.remove(str);
            return node.children.isEmpty();
        }
        if (removeFieldPath(node.children.get(str), list, i + 1)) {
            node.children.remove(str);
        }
        return node.children.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldMaskTree removeFromFieldMask(FieldMask fieldMask) {
        Iterator<String> it = fieldMask.getPathsList().iterator();
        while (it.hasNext()) {
            removeFieldPath(it.next());
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldMask toFieldMask() {
        if (this.root.children.isEmpty()) {
            return FieldMask.getDefaultInstance();
        }
        ArrayList arrayList = new ArrayList();
        getFieldPaths(this.root, "", arrayList);
        return FieldMask.newBuilder().addAllPaths(arrayList).build();
    }

    private static void getFieldPaths(Node node, String str, List<String> list) {
        if (node.children.isEmpty()) {
            list.add(str);
            return;
        }
        for (Map.Entry<String, Node> entry : node.children.entrySet()) {
            getFieldPaths(entry.getValue(), str.isEmpty() ? entry.getKey() : str + "." + entry.getKey(), list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void intersectFieldPath(String str, FieldMaskTree fieldMaskTree) {
        if (this.root.children.isEmpty()) {
            return;
        }
        String[] split = str.split(FIELD_PATH_SEPARATOR_REGEX);
        if (split.length == 0) {
            return;
        }
        Node node = this.root;
        for (String str2 : split) {
            if (node != this.root && node.children.isEmpty()) {
                fieldMaskTree.addFieldPath(str);
                return;
            } else {
                if (!node.children.containsKey(str2)) {
                    return;
                }
                node = node.children.get(str2);
            }
        }
        ArrayList arrayList = new ArrayList();
        getFieldPaths(node, str, arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            fieldMaskTree.addFieldPath((String) it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void merge(Message message, Message.Builder builder, FieldMaskUtil.MergeOptions mergeOptions) {
        if (message.getDescriptorForType() != builder.getDescriptorForType()) {
            throw new IllegalArgumentException("Cannot merge messages of different types.");
        }
        if (this.root.children.isEmpty()) {
            return;
        }
        merge(this.root, "", message, builder, mergeOptions);
    }

    private static void merge(Node node, String str, Message message, Message.Builder builder, FieldMaskUtil.MergeOptions mergeOptions) {
        if (message.getDescriptorForType() != builder.getDescriptorForType()) {
            throw new IllegalArgumentException(String.format("source (%s) and destination (%s) descriptor must be equal", message.getDescriptorForType(), builder.getDescriptorForType()));
        }
        Descriptors.Descriptor descriptorForType = message.getDescriptorForType();
        for (Map.Entry<String, Node> entry : node.children.entrySet()) {
            Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName(entry.getKey());
            if (findFieldByName == null) {
                logger.warning("Cannot find field \"" + entry.getKey() + "\" in message type " + descriptorForType.getFullName());
            } else if (!entry.getValue().children.isEmpty()) {
                if (findFieldByName.isRepeated() || findFieldByName.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    logger.warning("Field \"" + findFieldByName.getFullName() + "\" is not a singular message field and cannot have sub-fields.");
                } else if (message.hasField(findFieldByName) || builder.hasField(findFieldByName)) {
                    String key = str.isEmpty() ? entry.getKey() : str + "." + entry.getKey();
                    Message.Builder builder2 = ((Message) builder.getField(findFieldByName)).toBuilder();
                    merge(entry.getValue(), key, (Message) message.getField(findFieldByName), builder2, mergeOptions);
                    builder.setField(findFieldByName, builder2.buildPartial());
                }
            } else if (findFieldByName.isRepeated()) {
                if (mergeOptions.replaceRepeatedFields()) {
                    builder.setField(findFieldByName, message.getField(findFieldByName));
                } else {
                    Iterator it = ((List) message.getField(findFieldByName)).iterator();
                    while (it.hasNext()) {
                        builder.addRepeatedField(findFieldByName, it.next());
                    }
                }
            } else if (findFieldByName.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (mergeOptions.replaceMessageFields()) {
                    if (!message.hasField(findFieldByName)) {
                        builder.clearField(findFieldByName);
                    } else {
                        builder.setField(findFieldByName, message.getField(findFieldByName));
                    }
                } else if (message.hasField(findFieldByName)) {
                    builder.setField(findFieldByName, ((Message) builder.getField(findFieldByName)).toBuilder().mergeFrom((Message) message.getField(findFieldByName)).build());
                }
            } else if (message.hasField(findFieldByName) || !mergeOptions.replacePrimitiveFields()) {
                builder.setField(findFieldByName, message.getField(findFieldByName));
            } else {
                builder.clearField(findFieldByName);
            }
        }
    }
}
