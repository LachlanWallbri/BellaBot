package io.grpc.alts.internal;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class RpcProtocolVersions extends GeneratedMessageV3 implements RpcProtocolVersionsOrBuilder {
    public static final int MAX_RPC_VERSION_FIELD_NUMBER = 1;
    public static final int MIN_RPC_VERSION_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private Version maxRpcVersion_;
    private byte memoizedIsInitialized;
    private Version minRpcVersion_;
    private static final RpcProtocolVersions DEFAULT_INSTANCE = new RpcProtocolVersions();
    private static final Parser<RpcProtocolVersions> PARSER = new AbstractParser<RpcProtocolVersions>() { // from class: io.grpc.alts.internal.RpcProtocolVersions.1
        @Override // com.google.protobuf.Parser
        public RpcProtocolVersions parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RpcProtocolVersions(codedInputStream, extensionRegistryLite);
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface VersionOrBuilder extends MessageOrBuilder {
        int getMajor();

        int getMinor();
    }

    private RpcProtocolVersions(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RpcProtocolVersions() {
        this.memoizedIsInitialized = (byte) -1;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new RpcProtocolVersions();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private RpcProtocolVersions(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        Version.Builder builder;
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            builder = this.maxRpcVersion_ != null ? this.maxRpcVersion_.toBuilder() : null;
                            this.maxRpcVersion_ = (Version) codedInputStream.readMessage(Version.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.maxRpcVersion_);
                                this.maxRpcVersion_ = builder.buildPartial();
                            }
                        } else if (readTag == 18) {
                            builder = this.minRpcVersion_ != null ? this.minRpcVersion_.toBuilder() : null;
                            this.minRpcVersion_ = (Version) codedInputStream.readMessage(Version.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.minRpcVersion_);
                                this.minRpcVersion_ = builder.buildPartial();
                            }
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return TransportSecurityCommonProto.internal_static_grpc_gcp_RpcProtocolVersions_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return TransportSecurityCommonProto.internal_static_grpc_gcp_RpcProtocolVersions_fieldAccessorTable.ensureFieldAccessorsInitialized(RpcProtocolVersions.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Version extends GeneratedMessageV3 implements VersionOrBuilder {
        public static final int MAJOR_FIELD_NUMBER = 1;
        public static final int MINOR_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private int major_;
        private byte memoizedIsInitialized;
        private int minor_;
        private static final Version DEFAULT_INSTANCE = new Version();
        private static final Parser<Version> PARSER = new AbstractParser<Version>() { // from class: io.grpc.alts.internal.RpcProtocolVersions.Version.1
            @Override // com.google.protobuf.Parser
            public Version parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Version(codedInputStream, extensionRegistryLite);
            }
        };

        private Version(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Version() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Version();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Version(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite == null) {
                throw new NullPointerException();
            }
            UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.major_ = codedInputStream.readUInt32();
                                } else if (readTag == 16) {
                                    this.minor_ = codedInputStream.readUInt32();
                                } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TransportSecurityCommonProto.internal_static_grpc_gcp_RpcProtocolVersions_Version_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TransportSecurityCommonProto.f8317xc49d9678.ensureFieldAccessorsInitialized(Version.class, Builder.class);
        }

        @Override // io.grpc.alts.internal.RpcProtocolVersions.VersionOrBuilder
        public int getMajor() {
            return this.major_;
        }

        @Override // io.grpc.alts.internal.RpcProtocolVersions.VersionOrBuilder
        public int getMinor() {
            return this.minor_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.major_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            int i2 = this.minor_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(2, i2);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = this.major_;
            int computeUInt32Size = i2 != 0 ? 0 + CodedOutputStream.computeUInt32Size(1, i2) : 0;
            int i3 = this.minor_;
            if (i3 != 0) {
                computeUInt32Size += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int serializedSize = computeUInt32Size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Version)) {
                return super.equals(obj);
            }
            Version version = (Version) obj;
            return getMajor() == version.getMajor() && getMinor() == version.getMinor() && this.unknownFields.equals(version.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMajor()) * 37) + 2) * 53) + getMinor()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Version parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Version parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Version parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Version parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Version parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Version parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Version parseFrom(InputStream inputStream) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Version parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Version parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Version) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Version parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Version) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Version parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Version parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Version) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Version version) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(version);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VersionOrBuilder {
            private int major_;
            private int minor_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return TransportSecurityCommonProto.internal_static_grpc_gcp_RpcProtocolVersions_Version_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return TransportSecurityCommonProto.f8317xc49d9678.ensureFieldAccessorsInitialized(Version.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Version.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.major_ = 0;
                this.minor_ = 0;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return TransportSecurityCommonProto.internal_static_grpc_gcp_RpcProtocolVersions_Version_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Version getDefaultInstanceForType() {
                return Version.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Version build() {
                Version buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Version buildPartial() {
                Version version = new Version(this);
                version.major_ = this.major_;
                version.minor_ = this.minor_;
                onBuilt();
                return version;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* renamed from: clone */
            public Builder mo4275clone() {
                return (Builder) super.mo4275clone();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof Version) {
                    return mergeFrom((Version) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Version version) {
                if (version == Version.getDefaultInstance()) {
                    return this;
                }
                if (version.getMajor() != 0) {
                    setMajor(version.getMajor());
                }
                if (version.getMinor() != 0) {
                    setMinor(version.getMinor());
                }
                mergeUnknownFields(version.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Version version = null;
                try {
                    try {
                        Version version2 = (Version) Version.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (version2 != null) {
                            mergeFrom(version2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Version version3 = (Version) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            version = version3;
                            if (version != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (version != null) {
                        mergeFrom(version);
                    }
                    throw th;
                }
            }

            @Override // io.grpc.alts.internal.RpcProtocolVersions.VersionOrBuilder
            public int getMajor() {
                return this.major_;
            }

            public Builder setMajor(int i) {
                this.major_ = i;
                onChanged();
                return this;
            }

            public Builder clearMajor() {
                this.major_ = 0;
                onChanged();
                return this;
            }

            @Override // io.grpc.alts.internal.RpcProtocolVersions.VersionOrBuilder
            public int getMinor() {
                return this.minor_;
            }

            public Builder setMinor(int i) {
                this.minor_ = i;
                onChanged();
                return this;
            }

            public Builder clearMinor() {
                this.minor_ = 0;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }

        public static Version getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Version> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Version> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Version getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
    public boolean hasMaxRpcVersion() {
        return this.maxRpcVersion_ != null;
    }

    @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
    public Version getMaxRpcVersion() {
        Version version = this.maxRpcVersion_;
        return version == null ? Version.getDefaultInstance() : version;
    }

    @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
    public VersionOrBuilder getMaxRpcVersionOrBuilder() {
        return getMaxRpcVersion();
    }

    @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
    public boolean hasMinRpcVersion() {
        return this.minRpcVersion_ != null;
    }

    @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
    public Version getMinRpcVersion() {
        Version version = this.minRpcVersion_;
        return version == null ? Version.getDefaultInstance() : version;
    }

    @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
    public VersionOrBuilder getMinRpcVersionOrBuilder() {
        return getMinRpcVersion();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.maxRpcVersion_ != null) {
            codedOutputStream.writeMessage(1, getMaxRpcVersion());
        }
        if (this.minRpcVersion_ != null) {
            codedOutputStream.writeMessage(2, getMinRpcVersion());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.maxRpcVersion_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getMaxRpcVersion()) : 0;
        if (this.minRpcVersion_ != null) {
            computeMessageSize += CodedOutputStream.computeMessageSize(2, getMinRpcVersion());
        }
        int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RpcProtocolVersions)) {
            return super.equals(obj);
        }
        RpcProtocolVersions rpcProtocolVersions = (RpcProtocolVersions) obj;
        if (hasMaxRpcVersion() != rpcProtocolVersions.hasMaxRpcVersion()) {
            return false;
        }
        if ((!hasMaxRpcVersion() || getMaxRpcVersion().equals(rpcProtocolVersions.getMaxRpcVersion())) && hasMinRpcVersion() == rpcProtocolVersions.hasMinRpcVersion()) {
            return (!hasMinRpcVersion() || getMinRpcVersion().equals(rpcProtocolVersions.getMinRpcVersion())) && this.unknownFields.equals(rpcProtocolVersions.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasMaxRpcVersion()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getMaxRpcVersion().hashCode();
        }
        if (hasMinRpcVersion()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getMinRpcVersion().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static RpcProtocolVersions parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static RpcProtocolVersions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RpcProtocolVersions parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static RpcProtocolVersions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RpcProtocolVersions parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static RpcProtocolVersions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RpcProtocolVersions parseFrom(InputStream inputStream) throws IOException {
        return (RpcProtocolVersions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RpcProtocolVersions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RpcProtocolVersions) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RpcProtocolVersions parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RpcProtocolVersions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RpcProtocolVersions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RpcProtocolVersions) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RpcProtocolVersions parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RpcProtocolVersions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RpcProtocolVersions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RpcProtocolVersions) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RpcProtocolVersions rpcProtocolVersions) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(rpcProtocolVersions);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RpcProtocolVersionsOrBuilder {
        private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> maxRpcVersionBuilder_;
        private Version maxRpcVersion_;
        private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> minRpcVersionBuilder_;
        private Version minRpcVersion_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return TransportSecurityCommonProto.internal_static_grpc_gcp_RpcProtocolVersions_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return TransportSecurityCommonProto.internal_static_grpc_gcp_RpcProtocolVersions_fieldAccessorTable.ensureFieldAccessorsInitialized(RpcProtocolVersions.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RpcProtocolVersions.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.maxRpcVersionBuilder_ == null) {
                this.maxRpcVersion_ = null;
            } else {
                this.maxRpcVersion_ = null;
                this.maxRpcVersionBuilder_ = null;
            }
            if (this.minRpcVersionBuilder_ == null) {
                this.minRpcVersion_ = null;
            } else {
                this.minRpcVersion_ = null;
                this.minRpcVersionBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return TransportSecurityCommonProto.internal_static_grpc_gcp_RpcProtocolVersions_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public RpcProtocolVersions getDefaultInstanceForType() {
            return RpcProtocolVersions.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public RpcProtocolVersions build() {
            RpcProtocolVersions buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public RpcProtocolVersions buildPartial() {
            RpcProtocolVersions rpcProtocolVersions = new RpcProtocolVersions(this);
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.maxRpcVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                rpcProtocolVersions.maxRpcVersion_ = this.maxRpcVersion_;
            } else {
                rpcProtocolVersions.maxRpcVersion_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV32 = this.minRpcVersionBuilder_;
            if (singleFieldBuilderV32 == null) {
                rpcProtocolVersions.minRpcVersion_ = this.minRpcVersion_;
            } else {
                rpcProtocolVersions.minRpcVersion_ = singleFieldBuilderV32.build();
            }
            onBuilt();
            return rpcProtocolVersions;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* renamed from: clone */
        public Builder mo4275clone() {
            return (Builder) super.mo4275clone();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(Message message) {
            if (message instanceof RpcProtocolVersions) {
                return mergeFrom((RpcProtocolVersions) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RpcProtocolVersions rpcProtocolVersions) {
            if (rpcProtocolVersions == RpcProtocolVersions.getDefaultInstance()) {
                return this;
            }
            if (rpcProtocolVersions.hasMaxRpcVersion()) {
                mergeMaxRpcVersion(rpcProtocolVersions.getMaxRpcVersion());
            }
            if (rpcProtocolVersions.hasMinRpcVersion()) {
                mergeMinRpcVersion(rpcProtocolVersions.getMinRpcVersion());
            }
            mergeUnknownFields(rpcProtocolVersions.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            RpcProtocolVersions rpcProtocolVersions = null;
            try {
                try {
                    RpcProtocolVersions rpcProtocolVersions2 = (RpcProtocolVersions) RpcProtocolVersions.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (rpcProtocolVersions2 != null) {
                        mergeFrom(rpcProtocolVersions2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    RpcProtocolVersions rpcProtocolVersions3 = (RpcProtocolVersions) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        rpcProtocolVersions = rpcProtocolVersions3;
                        if (rpcProtocolVersions != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (rpcProtocolVersions != null) {
                    mergeFrom(rpcProtocolVersions);
                }
                throw th;
            }
        }

        @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
        public boolean hasMaxRpcVersion() {
            return (this.maxRpcVersionBuilder_ == null && this.maxRpcVersion_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
        public Version getMaxRpcVersion() {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.maxRpcVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                Version version = this.maxRpcVersion_;
                return version == null ? Version.getDefaultInstance() : version;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setMaxRpcVersion(Version version) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.maxRpcVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(version);
            } else {
                if (version == null) {
                    throw new NullPointerException();
                }
                this.maxRpcVersion_ = version;
                onChanged();
            }
            return this;
        }

        public Builder setMaxRpcVersion(Version.Builder builder) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.maxRpcVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.maxRpcVersion_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMaxRpcVersion(Version version) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.maxRpcVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                Version version2 = this.maxRpcVersion_;
                if (version2 != null) {
                    this.maxRpcVersion_ = Version.newBuilder(version2).mergeFrom(version).buildPartial();
                } else {
                    this.maxRpcVersion_ = version;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(version);
            }
            return this;
        }

        public Builder clearMaxRpcVersion() {
            if (this.maxRpcVersionBuilder_ == null) {
                this.maxRpcVersion_ = null;
                onChanged();
            } else {
                this.maxRpcVersion_ = null;
                this.maxRpcVersionBuilder_ = null;
            }
            return this;
        }

        public Version.Builder getMaxRpcVersionBuilder() {
            onChanged();
            return getMaxRpcVersionFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
        public VersionOrBuilder getMaxRpcVersionOrBuilder() {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.maxRpcVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Version version = this.maxRpcVersion_;
            return version == null ? Version.getDefaultInstance() : version;
        }

        private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> getMaxRpcVersionFieldBuilder() {
            if (this.maxRpcVersionBuilder_ == null) {
                this.maxRpcVersionBuilder_ = new SingleFieldBuilderV3<>(getMaxRpcVersion(), getParentForChildren(), isClean());
                this.maxRpcVersion_ = null;
            }
            return this.maxRpcVersionBuilder_;
        }

        @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
        public boolean hasMinRpcVersion() {
            return (this.minRpcVersionBuilder_ == null && this.minRpcVersion_ == null) ? false : true;
        }

        @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
        public Version getMinRpcVersion() {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.minRpcVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                Version version = this.minRpcVersion_;
                return version == null ? Version.getDefaultInstance() : version;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setMinRpcVersion(Version version) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.minRpcVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(version);
            } else {
                if (version == null) {
                    throw new NullPointerException();
                }
                this.minRpcVersion_ = version;
                onChanged();
            }
            return this;
        }

        public Builder setMinRpcVersion(Version.Builder builder) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.minRpcVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.minRpcVersion_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMinRpcVersion(Version version) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.minRpcVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                Version version2 = this.minRpcVersion_;
                if (version2 != null) {
                    this.minRpcVersion_ = Version.newBuilder(version2).mergeFrom(version).buildPartial();
                } else {
                    this.minRpcVersion_ = version;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(version);
            }
            return this;
        }

        public Builder clearMinRpcVersion() {
            if (this.minRpcVersionBuilder_ == null) {
                this.minRpcVersion_ = null;
                onChanged();
            } else {
                this.minRpcVersion_ = null;
                this.minRpcVersionBuilder_ = null;
            }
            return this;
        }

        public Version.Builder getMinRpcVersionBuilder() {
            onChanged();
            return getMinRpcVersionFieldBuilder().getBuilder();
        }

        @Override // io.grpc.alts.internal.RpcProtocolVersionsOrBuilder
        public VersionOrBuilder getMinRpcVersionOrBuilder() {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.minRpcVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Version version = this.minRpcVersion_;
            return version == null ? Version.getDefaultInstance() : version;
        }

        private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> getMinRpcVersionFieldBuilder() {
            if (this.minRpcVersionBuilder_ == null) {
                this.minRpcVersionBuilder_ = new SingleFieldBuilderV3<>(getMinRpcVersion(), getParentForChildren(), isClean());
                this.minRpcVersion_ = null;
            }
            return this.minRpcVersionBuilder_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static RpcProtocolVersions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RpcProtocolVersions> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<RpcProtocolVersions> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public RpcProtocolVersions getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
