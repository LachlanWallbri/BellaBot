package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public enum IntentView implements ProtocolMessageEnum {
    INTENT_VIEW_UNSPECIFIED(0),
    INTENT_VIEW_FULL(1),
    UNRECOGNIZED(-1);

    public static final int INTENT_VIEW_FULL_VALUE = 1;
    public static final int INTENT_VIEW_UNSPECIFIED_VALUE = 0;
    private final int value;
    private static final Internal.EnumLiteMap<IntentView> internalValueMap = new Internal.EnumLiteMap<IntentView>() { // from class: com.google.cloud.dialogflow.v2.IntentView.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public IntentView findValueByNumber(int i) {
            return IntentView.forNumber(i);
        }
    };
    private static final IntentView[] VALUES = values();

    @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Deprecated
    public static IntentView valueOf(int i) {
        return forNumber(i);
    }

    public static IntentView forNumber(int i) {
        if (i == 0) {
            return INTENT_VIEW_UNSPECIFIED;
        }
        if (i != 1) {
            return null;
        }
        return INTENT_VIEW_FULL;
    }

    public static Internal.EnumLiteMap<IntentView> internalGetValueMap() {
        return internalValueMap;
    }

    @Override // com.google.protobuf.ProtocolMessageEnum
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        return getDescriptor().getValues().get(ordinal());
    }

    @Override // com.google.protobuf.ProtocolMessageEnum
    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return IntentProto.getDescriptor().getEnumTypes().get(0);
    }

    public static IntentView valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
        if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[enumValueDescriptor.getIndex()];
    }

    IntentView(int i) {
        this.value = i;
    }
}
