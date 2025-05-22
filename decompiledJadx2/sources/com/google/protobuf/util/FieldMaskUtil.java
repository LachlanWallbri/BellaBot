package com.google.protobuf.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.primitives.Ints;
import com.google.protobuf.Descriptors;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Internal;
import com.google.protobuf.Message;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class FieldMaskUtil {
    private static final String FIELD_PATH_SEPARATOR = ",";
    private static final String FIELD_PATH_SEPARATOR_REGEX = ",";
    private static final String FIELD_SEPARATOR_REGEX = "\\.";

    private FieldMaskUtil() {
    }

    public static String toString(FieldMask fieldMask) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : fieldMask.getPathsList()) {
            if (!str.isEmpty()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static FieldMask fromString(String str) {
        return fromStringList(Arrays.asList(str.split(",")));
    }

    public static FieldMask fromString(Class<? extends Message> cls, String str) {
        return fromStringList(cls, Arrays.asList(str.split(",")));
    }

    public static FieldMask fromStringList(Class<? extends Message> cls, Iterable<String> iterable) {
        return fromStringList(((Message) Internal.getDefaultInstance(cls)).getDescriptorForType(), iterable);
    }

    public static FieldMask fromStringList(Descriptors.Descriptor descriptor, Iterable<String> iterable) {
        return fromStringList((Optional<Descriptors.Descriptor>) Optional.m610of(descriptor), iterable);
    }

    public static FieldMask fromStringList(Iterable<String> iterable) {
        return fromStringList((Optional<Descriptors.Descriptor>) Optional.absent(), iterable);
    }

    private static FieldMask fromStringList(Optional<Descriptors.Descriptor> optional, Iterable<String> iterable) {
        FieldMask.Builder newBuilder = FieldMask.newBuilder();
        for (String str : iterable) {
            if (!str.isEmpty()) {
                if (optional.isPresent() && !isValid(optional.get(), str)) {
                    throw new IllegalArgumentException(str + " is not a valid path for " + optional.get().getFullName());
                }
                newBuilder.addPaths(str);
            }
        }
        return newBuilder.build();
    }

    public static FieldMask fromFieldNumbers(Class<? extends Message> cls, int... iArr) {
        return fromFieldNumbers(cls, Ints.asList(iArr));
    }

    public static FieldMask fromFieldNumbers(Class<? extends Message> cls, Iterable<Integer> iterable) {
        Descriptors.Descriptor descriptorForType = ((Message) Internal.getDefaultInstance(cls)).getDescriptorForType();
        FieldMask.Builder newBuilder = FieldMask.newBuilder();
        for (Integer num : iterable) {
            Descriptors.FieldDescriptor findFieldByNumber = descriptorForType.findFieldByNumber(num.intValue());
            Preconditions.checkArgument(findFieldByNumber != null, String.format("%s is not a valid field number for %s.", num, cls));
            newBuilder.addPaths(findFieldByNumber.getName());
        }
        return newBuilder.build();
    }

    public static String toJsonString(FieldMask fieldMask) {
        ArrayList arrayList = new ArrayList(fieldMask.getPathsCount());
        for (String str : fieldMask.getPathsList()) {
            if (!str.isEmpty()) {
                arrayList.add(CaseFormat.LOWER_UNDERSCORE.m605to(CaseFormat.LOWER_CAMEL, str));
            }
        }
        return Joiner.m609on(",").join(arrayList);
    }

    public static FieldMask fromJsonString(String str) {
        Iterable<String> split = Splitter.m618on(",").split(str);
        FieldMask.Builder newBuilder = FieldMask.newBuilder();
        for (String str2 : split) {
            if (!str2.isEmpty()) {
                newBuilder.addPaths(CaseFormat.LOWER_CAMEL.m605to(CaseFormat.LOWER_UNDERSCORE, str2));
            }
        }
        return newBuilder.build();
    }

    public static boolean isValid(Class<? extends Message> cls, FieldMask fieldMask) {
        return isValid(((Message) Internal.getDefaultInstance(cls)).getDescriptorForType(), fieldMask);
    }

    public static boolean isValid(Descriptors.Descriptor descriptor, FieldMask fieldMask) {
        Iterator<String> it = fieldMask.getPathsList().iterator();
        while (it.hasNext()) {
            if (!isValid(descriptor, it.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValid(Class<? extends Message> cls, String str) {
        return isValid(((Message) Internal.getDefaultInstance(cls)).getDescriptorForType(), str);
    }

    public static boolean isValid(Descriptors.Descriptor descriptor, String str) {
        Descriptors.FieldDescriptor findFieldByName;
        String[] split = str.split(FIELD_SEPARATOR_REGEX);
        if (split.length == 0) {
            return false;
        }
        Descriptors.Descriptor descriptor2 = descriptor;
        for (String str2 : split) {
            if (descriptor2 == null || (findFieldByName = descriptor2.findFieldByName(str2)) == null) {
                return false;
            }
            descriptor2 = (findFieldByName.isRepeated() || findFieldByName.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) ? null : findFieldByName.getMessageType();
        }
        return true;
    }

    public static FieldMask normalize(FieldMask fieldMask) {
        return new FieldMaskTree(fieldMask).toFieldMask();
    }

    public static FieldMask union(FieldMask fieldMask, FieldMask fieldMask2, FieldMask... fieldMaskArr) {
        FieldMaskTree mergeFromFieldMask = new FieldMaskTree(fieldMask).mergeFromFieldMask(fieldMask2);
        for (FieldMask fieldMask3 : fieldMaskArr) {
            mergeFromFieldMask.mergeFromFieldMask(fieldMask3);
        }
        return mergeFromFieldMask.toFieldMask();
    }

    public static FieldMask subtract(FieldMask fieldMask, FieldMask fieldMask2, FieldMask... fieldMaskArr) {
        FieldMaskTree removeFromFieldMask = new FieldMaskTree(fieldMask).removeFromFieldMask(fieldMask2);
        for (FieldMask fieldMask3 : fieldMaskArr) {
            removeFromFieldMask.removeFromFieldMask(fieldMask3);
        }
        return removeFromFieldMask.toFieldMask();
    }

    public static FieldMask intersection(FieldMask fieldMask, FieldMask fieldMask2) {
        FieldMaskTree fieldMaskTree = new FieldMaskTree(fieldMask);
        FieldMaskTree fieldMaskTree2 = new FieldMaskTree();
        Iterator<String> it = fieldMask2.getPathsList().iterator();
        while (it.hasNext()) {
            fieldMaskTree.intersectFieldPath(it.next(), fieldMaskTree2);
        }
        return fieldMaskTree2.toFieldMask();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public static final class MergeOptions {
        private boolean replaceMessageFields = false;
        private boolean replaceRepeatedFields = false;
        private boolean replacePrimitiveFields = false;

        public boolean replaceMessageFields() {
            return this.replaceMessageFields;
        }

        public boolean replaceRepeatedFields() {
            return this.replaceRepeatedFields;
        }

        public boolean replacePrimitiveFields() {
            return this.replacePrimitiveFields;
        }

        public MergeOptions setReplaceMessageFields(boolean z) {
            this.replaceMessageFields = z;
            return this;
        }

        public MergeOptions setReplaceRepeatedFields(boolean z) {
            this.replaceRepeatedFields = z;
            return this;
        }

        public MergeOptions setReplacePrimitiveFields(boolean z) {
            this.replacePrimitiveFields = z;
            return this;
        }
    }

    public static void merge(FieldMask fieldMask, Message message, Message.Builder builder, MergeOptions mergeOptions) {
        new FieldMaskTree(fieldMask).merge(message, builder, mergeOptions);
    }

    public static void merge(FieldMask fieldMask, Message message, Message.Builder builder) {
        merge(fieldMask, message, builder, new MergeOptions());
    }
}
