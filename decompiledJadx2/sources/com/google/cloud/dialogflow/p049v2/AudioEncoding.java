package com.google.cloud.dialogflow.p049v2;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public enum AudioEncoding implements ProtocolMessageEnum {
    AUDIO_ENCODING_UNSPECIFIED(0),
    AUDIO_ENCODING_LINEAR_16(1),
    AUDIO_ENCODING_FLAC(2),
    AUDIO_ENCODING_MULAW(3),
    AUDIO_ENCODING_AMR(4),
    AUDIO_ENCODING_AMR_WB(5),
    AUDIO_ENCODING_OGG_OPUS(6),
    AUDIO_ENCODING_SPEEX_WITH_HEADER_BYTE(7),
    UNRECOGNIZED(-1);

    public static final int AUDIO_ENCODING_AMR_VALUE = 4;
    public static final int AUDIO_ENCODING_AMR_WB_VALUE = 5;
    public static final int AUDIO_ENCODING_FLAC_VALUE = 2;
    public static final int AUDIO_ENCODING_LINEAR_16_VALUE = 1;
    public static final int AUDIO_ENCODING_MULAW_VALUE = 3;
    public static final int AUDIO_ENCODING_OGG_OPUS_VALUE = 6;
    public static final int AUDIO_ENCODING_SPEEX_WITH_HEADER_BYTE_VALUE = 7;
    public static final int AUDIO_ENCODING_UNSPECIFIED_VALUE = 0;
    private final int value;
    private static final Internal.EnumLiteMap<AudioEncoding> internalValueMap = new Internal.EnumLiteMap<AudioEncoding>() { // from class: com.google.cloud.dialogflow.v2.AudioEncoding.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public AudioEncoding findValueByNumber(int i) {
            return AudioEncoding.forNumber(i);
        }
    };
    private static final AudioEncoding[] VALUES = values();

    @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Deprecated
    public static AudioEncoding valueOf(int i) {
        return forNumber(i);
    }

    public static AudioEncoding forNumber(int i) {
        switch (i) {
            case 0:
                return AUDIO_ENCODING_UNSPECIFIED;
            case 1:
                return AUDIO_ENCODING_LINEAR_16;
            case 2:
                return AUDIO_ENCODING_FLAC;
            case 3:
                return AUDIO_ENCODING_MULAW;
            case 4:
                return AUDIO_ENCODING_AMR;
            case 5:
                return AUDIO_ENCODING_AMR_WB;
            case 6:
                return AUDIO_ENCODING_OGG_OPUS;
            case 7:
                return AUDIO_ENCODING_SPEEX_WITH_HEADER_BYTE;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<AudioEncoding> internalGetValueMap() {
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
        return AudioConfigProto.getDescriptor().getEnumTypes().get(0);
    }

    public static AudioEncoding valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
        if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[enumValueDescriptor.getIndex()];
    }

    AudioEncoding(int i) {
        this.value = i;
    }
}
