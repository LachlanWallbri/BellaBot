package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public enum LaunchStage implements ProtocolMessageEnum {
    LAUNCH_STAGE_UNSPECIFIED(0),
    UNIMPLEMENTED(6),
    PRELAUNCH(7),
    EARLY_ACCESS(1),
    ALPHA(2),
    BETA(3),
    GA(4),
    DEPRECATED(5),
    UNRECOGNIZED(-1);

    public static final int ALPHA_VALUE = 2;
    public static final int BETA_VALUE = 3;
    public static final int DEPRECATED_VALUE = 5;
    public static final int EARLY_ACCESS_VALUE = 1;
    public static final int GA_VALUE = 4;
    public static final int LAUNCH_STAGE_UNSPECIFIED_VALUE = 0;
    public static final int PRELAUNCH_VALUE = 7;
    public static final int UNIMPLEMENTED_VALUE = 6;
    private final int value;
    private static final Internal.EnumLiteMap<LaunchStage> internalValueMap = new Internal.EnumLiteMap<LaunchStage>() { // from class: com.google.api.LaunchStage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public LaunchStage findValueByNumber(int i) {
            return LaunchStage.forNumber(i);
        }
    };
    private static final LaunchStage[] VALUES = values();

    @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Deprecated
    public static LaunchStage valueOf(int i) {
        return forNumber(i);
    }

    public static LaunchStage forNumber(int i) {
        switch (i) {
            case 0:
                return LAUNCH_STAGE_UNSPECIFIED;
            case 1:
                return EARLY_ACCESS;
            case 2:
                return ALPHA;
            case 3:
                return BETA;
            case 4:
                return GA;
            case 5:
                return DEPRECATED;
            case 6:
                return UNIMPLEMENTED;
            case 7:
                return PRELAUNCH;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<LaunchStage> internalGetValueMap() {
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
        return LaunchStageProto.getDescriptor().getEnumTypes().get(0);
    }

    public static LaunchStage valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
        if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[enumValueDescriptor.getIndex()];
    }

    LaunchStage(int i) {
        this.value = i;
    }
}
