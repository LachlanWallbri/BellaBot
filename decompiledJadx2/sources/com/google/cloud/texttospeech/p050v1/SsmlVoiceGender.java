package com.google.cloud.texttospeech.p050v1;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public enum SsmlVoiceGender implements ProtocolMessageEnum {
    SSML_VOICE_GENDER_UNSPECIFIED(0),
    MALE(1),
    FEMALE(2),
    NEUTRAL(3),
    UNRECOGNIZED(-1);

    public static final int FEMALE_VALUE = 2;
    public static final int MALE_VALUE = 1;
    public static final int NEUTRAL_VALUE = 3;
    public static final int SSML_VOICE_GENDER_UNSPECIFIED_VALUE = 0;
    private final int value;
    private static final Internal.EnumLiteMap<SsmlVoiceGender> internalValueMap = new Internal.EnumLiteMap<SsmlVoiceGender>() { // from class: com.google.cloud.texttospeech.v1.SsmlVoiceGender.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public SsmlVoiceGender findValueByNumber(int i) {
            return SsmlVoiceGender.forNumber(i);
        }
    };
    private static final SsmlVoiceGender[] VALUES = values();

    @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Deprecated
    public static SsmlVoiceGender valueOf(int i) {
        return forNumber(i);
    }

    public static SsmlVoiceGender forNumber(int i) {
        if (i == 0) {
            return SSML_VOICE_GENDER_UNSPECIFIED;
        }
        if (i == 1) {
            return MALE;
        }
        if (i == 2) {
            return FEMALE;
        }
        if (i != 3) {
            return null;
        }
        return NEUTRAL;
    }

    public static Internal.EnumLiteMap<SsmlVoiceGender> internalGetValueMap() {
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
        return TextToSpeechProto.getDescriptor().getEnumTypes().get(0);
    }

    public static SsmlVoiceGender valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
        if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[enumValueDescriptor.getIndex()];
    }

    SsmlVoiceGender(int i) {
        this.value = i;
    }
}
