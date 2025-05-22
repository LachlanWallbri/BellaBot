package com.google.cloud.texttospeech.p050v1;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public enum AudioEncoding implements ProtocolMessageEnum {
    AUDIO_ENCODING_UNSPECIFIED(0),
    LINEAR16(1),
    MP3(2),
    OGG_OPUS(3),
    UNRECOGNIZED(-1);

    public static final int AUDIO_ENCODING_UNSPECIFIED_VALUE = 0;
    public static final int LINEAR16_VALUE = 1;
    public static final int MP3_VALUE = 2;
    public static final int OGG_OPUS_VALUE = 3;
    private final int value;
    private static final Internal.EnumLiteMap<AudioEncoding> internalValueMap = new Internal.EnumLiteMap<AudioEncoding>() { // from class: com.google.cloud.texttospeech.v1.AudioEncoding.1
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
        if (i == 0) {
            return AUDIO_ENCODING_UNSPECIFIED;
        }
        if (i == 1) {
            return LINEAR16;
        }
        if (i == 2) {
            return MP3;
        }
        if (i != 3) {
            return null;
        }
        return OGG_OPUS;
    }

    public static Internal.EnumLiteMap<AudioEncoding> internalGetValueMap() {
        return internalValueMap;
    }

    @Override // com.google.protobuf.ProtocolMessageEnum
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        if (this == UNRECOGNIZED) {
            throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
        }
        return getDescriptor().getValues().get(ordinal());
    }

    @Override // com.google.protobuf.ProtocolMessageEnum
    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return TextToSpeechProto.getDescriptor().getEnumTypes().get(1);
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
