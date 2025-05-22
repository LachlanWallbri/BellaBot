package com.google.cloud.dialogflow.v2beta1;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public enum SpeechModelVariant implements ProtocolMessageEnum {
    SPEECH_MODEL_VARIANT_UNSPECIFIED(0),
    USE_BEST_AVAILABLE(1),
    USE_STANDARD(2),
    USE_ENHANCED(3),
    UNRECOGNIZED(-1);

    public static final int SPEECH_MODEL_VARIANT_UNSPECIFIED_VALUE = 0;
    public static final int USE_BEST_AVAILABLE_VALUE = 1;
    public static final int USE_ENHANCED_VALUE = 3;
    public static final int USE_STANDARD_VALUE = 2;
    private final int value;
    private static final Internal.EnumLiteMap<SpeechModelVariant> internalValueMap = new Internal.EnumLiteMap<SpeechModelVariant>() { // from class: com.google.cloud.dialogflow.v2beta1.SpeechModelVariant.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public SpeechModelVariant findValueByNumber(int i) {
            return SpeechModelVariant.forNumber(i);
        }
    };
    private static final SpeechModelVariant[] VALUES = values();

    @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Deprecated
    public static SpeechModelVariant valueOf(int i) {
        return forNumber(i);
    }

    public static SpeechModelVariant forNumber(int i) {
        if (i == 0) {
            return SPEECH_MODEL_VARIANT_UNSPECIFIED;
        }
        if (i == 1) {
            return USE_BEST_AVAILABLE;
        }
        if (i == 2) {
            return USE_STANDARD;
        }
        if (i != 3) {
            return null;
        }
        return USE_ENHANCED;
    }

    public static Internal.EnumLiteMap<SpeechModelVariant> internalGetValueMap() {
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
        return AudioConfigProto.getDescriptor().getEnumTypes().get(1);
    }

    public static SpeechModelVariant valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
        if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[enumValueDescriptor.getIndex()];
    }

    SpeechModelVariant(int i) {
        this.value = i;
    }
}
