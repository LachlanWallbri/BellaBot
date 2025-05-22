package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public enum OutputAudioEncoding implements ProtocolMessageEnum {
    OUTPUT_AUDIO_ENCODING_UNSPECIFIED(0),
    OUTPUT_AUDIO_ENCODING_LINEAR_16(1),
    OUTPUT_AUDIO_ENCODING_MP3(2),
    OUTPUT_AUDIO_ENCODING_OGG_OPUS(3),
    UNRECOGNIZED(-1);

    public static final int OUTPUT_AUDIO_ENCODING_LINEAR_16_VALUE = 1;
    public static final int OUTPUT_AUDIO_ENCODING_MP3_VALUE = 2;
    public static final int OUTPUT_AUDIO_ENCODING_OGG_OPUS_VALUE = 3;
    public static final int OUTPUT_AUDIO_ENCODING_UNSPECIFIED_VALUE = 0;
    private final int value;
    private static final Internal.EnumLiteMap<OutputAudioEncoding> internalValueMap = new Internal.EnumLiteMap<OutputAudioEncoding>() { // from class: com.google.cloud.dialogflow.v2.OutputAudioEncoding.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public OutputAudioEncoding findValueByNumber(int i) {
            return OutputAudioEncoding.forNumber(i);
        }
    };
    private static final OutputAudioEncoding[] VALUES = values();

    @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Deprecated
    public static OutputAudioEncoding valueOf(int i) {
        return forNumber(i);
    }

    public static OutputAudioEncoding forNumber(int i) {
        if (i == 0) {
            return OUTPUT_AUDIO_ENCODING_UNSPECIFIED;
        }
        if (i == 1) {
            return OUTPUT_AUDIO_ENCODING_LINEAR_16;
        }
        if (i == 2) {
            return OUTPUT_AUDIO_ENCODING_MP3;
        }
        if (i != 3) {
            return null;
        }
        return OUTPUT_AUDIO_ENCODING_OGG_OPUS;
    }

    public static Internal.EnumLiteMap<OutputAudioEncoding> internalGetValueMap() {
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
        return AudioConfigProto.getDescriptor().getEnumTypes().get(3);
    }

    public static OutputAudioEncoding valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
        if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[enumValueDescriptor.getIndex()];
    }

    OutputAudioEncoding(int i) {
        this.value = i;
    }
}
