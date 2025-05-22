package io.grpc.alts.internal;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public enum NetworkProtocol implements ProtocolMessageEnum {
    NETWORK_PROTOCOL_UNSPECIFIED(0),
    TCP(1),
    UDP(2),
    UNRECOGNIZED(-1);

    public static final int NETWORK_PROTOCOL_UNSPECIFIED_VALUE = 0;
    public static final int TCP_VALUE = 1;
    public static final int UDP_VALUE = 2;
    private final int value;
    private static final Internal.EnumLiteMap<NetworkProtocol> internalValueMap = new Internal.EnumLiteMap<NetworkProtocol>() { // from class: io.grpc.alts.internal.NetworkProtocol.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public NetworkProtocol findValueByNumber(int i) {
            return NetworkProtocol.forNumber(i);
        }
    };
    private static final NetworkProtocol[] VALUES = values();

    @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Deprecated
    public static NetworkProtocol valueOf(int i) {
        return forNumber(i);
    }

    public static NetworkProtocol forNumber(int i) {
        if (i == 0) {
            return NETWORK_PROTOCOL_UNSPECIFIED;
        }
        if (i == 1) {
            return TCP;
        }
        if (i != 2) {
            return null;
        }
        return UDP;
    }

    public static Internal.EnumLiteMap<NetworkProtocol> internalGetValueMap() {
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
        return HandshakerProto.getDescriptor().getEnumTypes().get(1);
    }

    public static NetworkProtocol valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
        if (enumValueDescriptor.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
        if (enumValueDescriptor.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[enumValueDescriptor.getIndex()];
    }

    NetworkProtocol(int i) {
        this.value = i;
    }
}
