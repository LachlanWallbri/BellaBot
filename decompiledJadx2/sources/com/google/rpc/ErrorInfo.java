package com.google.rpc;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class ErrorInfo extends GeneratedMessageV3 implements ErrorInfoOrBuilder {
    public static final int DOMAIN_FIELD_NUMBER = 2;
    public static final int METADATA_FIELD_NUMBER = 3;
    public static final int REASON_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private volatile Object domain_;
    private byte memoizedIsInitialized;
    private MapField<String, String> metadata_;
    private volatile Object reason_;
    private static final ErrorInfo DEFAULT_INSTANCE = new ErrorInfo();
    private static final Parser<ErrorInfo> PARSER = new AbstractParser<ErrorInfo>() { // from class: com.google.rpc.ErrorInfo.1
        @Override // com.google.protobuf.Parser
        public ErrorInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ErrorInfo(codedInputStream, extensionRegistryLite);
        }
    };

    private ErrorInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ErrorInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.reason_ = "";
        this.domain_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ErrorInfo();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ErrorInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.reason_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.domain_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                if (!(z2 & true)) {
                                    this.metadata_ = MapField.newMapField(MetadataDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(MetadataDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.metadata_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
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
        return ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected MapField internalGetMapField(int i) {
        if (i == 3) {
            return internalGetMetadata();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ErrorInfo.class, Builder.class);
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getReason() {
        Object obj = this.reason_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.reason_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public ByteString getReasonBytes() {
        Object obj = this.reason_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.reason_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getDomain() {
        Object obj = this.domain_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.domain_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public ByteString getDomainBytes() {
        Object obj = this.domain_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.domain_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public static final class MetadataDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_MetadataEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

        private MetadataDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, String> internalGetMetadata() {
        MapField<String, String> mapField = this.metadata_;
        return mapField == null ? MapField.emptyMapField(MetadataDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public int getMetadataCount() {
        return internalGetMetadata().getMap().size();
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public boolean containsMetadata(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        return internalGetMetadata().getMap().containsKey(str);
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    @Deprecated
    public Map<String, String> getMetadata() {
        return getMetadataMap();
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public Map<String, String> getMetadataMap() {
        return internalGetMetadata().getMap();
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getMetadataOrDefault(String str, String str2) {
        if (str == null) {
            throw new NullPointerException();
        }
        Map<String, String> map = internalGetMetadata().getMap();
        return map.containsKey(str) ? map.get(str) : str2;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getMetadataOrThrow(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        Map<String, String> map = internalGetMetadata().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return map.get(str);
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
        if (!getReasonBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.reason_);
        }
        if (!getDomainBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.domain_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetMetadata(), MetadataDefaultEntryHolder.defaultEntry, 3);
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = getReasonBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.reason_);
        if (!getDomainBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.domain_);
        }
        for (Map.Entry<String, String> entry : internalGetMetadata().getMap().entrySet()) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, MetadataDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ErrorInfo)) {
            return super.equals(obj);
        }
        ErrorInfo errorInfo = (ErrorInfo) obj;
        return getReason().equals(errorInfo.getReason()) && getDomain().equals(errorInfo.getDomain()) && internalGetMetadata().equals(errorInfo.internalGetMetadata()) && this.unknownFields.equals(errorInfo.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getReason().hashCode()) * 37) + 2) * 53) + getDomain().hashCode();
        if (!internalGetMetadata().getMap().isEmpty()) {
            hashCode = (((hashCode * 37) + 3) * 53) + internalGetMetadata().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ErrorInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ErrorInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ErrorInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ErrorInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(InputStream inputStream) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ErrorInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ErrorInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ErrorInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ErrorInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ErrorInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ErrorInfo errorInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(errorInfo);
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
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ErrorInfoOrBuilder {
        private int bitField0_;
        private Object domain_;
        private MapField<String, String> metadata_;
        private Object reason_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMapField(int i) {
            if (i == 3) {
                return internalGetMetadata();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMutableMapField(int i) {
            if (i == 3) {
                return internalGetMutableMetadata();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ErrorInfo.class, Builder.class);
        }

        private Builder() {
            this.reason_ = "";
            this.domain_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.reason_ = "";
            this.domain_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ErrorInfo.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.reason_ = "";
            this.domain_ = "";
            internalGetMutableMetadata().clear();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_ErrorInfo_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ErrorInfo getDefaultInstanceForType() {
            return ErrorInfo.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ErrorInfo build() {
            ErrorInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ErrorInfo buildPartial() {
            ErrorInfo errorInfo = new ErrorInfo(this);
            int i = this.bitField0_;
            errorInfo.reason_ = this.reason_;
            errorInfo.domain_ = this.domain_;
            errorInfo.metadata_ = internalGetMetadata();
            errorInfo.metadata_.makeImmutable();
            onBuilt();
            return errorInfo;
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
            if (message instanceof ErrorInfo) {
                return mergeFrom((ErrorInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ErrorInfo errorInfo) {
            if (errorInfo == ErrorInfo.getDefaultInstance()) {
                return this;
            }
            if (!errorInfo.getReason().isEmpty()) {
                this.reason_ = errorInfo.reason_;
                onChanged();
            }
            if (!errorInfo.getDomain().isEmpty()) {
                this.domain_ = errorInfo.domain_;
                onChanged();
            }
            internalGetMutableMetadata().mergeFrom(errorInfo.internalGetMetadata());
            mergeUnknownFields(errorInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ErrorInfo errorInfo = null;
            try {
                try {
                    ErrorInfo errorInfo2 = (ErrorInfo) ErrorInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (errorInfo2 != null) {
                        mergeFrom(errorInfo2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ErrorInfo errorInfo3 = (ErrorInfo) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        errorInfo = errorInfo3;
                        if (errorInfo != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (errorInfo != null) {
                    mergeFrom(errorInfo);
                }
                throw th;
            }
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getReason() {
            Object obj = this.reason_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.reason_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public ByteString getReasonBytes() {
            Object obj = this.reason_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.reason_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setReason(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.reason_ = str;
            onChanged();
            return this;
        }

        public Builder clearReason() {
            this.reason_ = ErrorInfo.getDefaultInstance().getReason();
            onChanged();
            return this;
        }

        public Builder setReasonBytes(ByteString byteString) {
            if (byteString != null) {
                ErrorInfo.checkByteStringIsUtf8(byteString);
                this.reason_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getDomain() {
            Object obj = this.domain_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.domain_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public ByteString getDomainBytes() {
            Object obj = this.domain_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.domain_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDomain(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.domain_ = str;
            onChanged();
            return this;
        }

        public Builder clearDomain() {
            this.domain_ = ErrorInfo.getDefaultInstance().getDomain();
            onChanged();
            return this;
        }

        public Builder setDomainBytes(ByteString byteString) {
            if (byteString != null) {
                ErrorInfo.checkByteStringIsUtf8(byteString);
                this.domain_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private MapField<String, String> internalGetMetadata() {
            MapField<String, String> mapField = this.metadata_;
            return mapField == null ? MapField.emptyMapField(MetadataDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableMetadata() {
            onChanged();
            if (this.metadata_ == null) {
                this.metadata_ = MapField.newMapField(MetadataDefaultEntryHolder.defaultEntry);
            }
            if (!this.metadata_.isMutable()) {
                this.metadata_ = this.metadata_.copy();
            }
            return this.metadata_;
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public int getMetadataCount() {
            return internalGetMetadata().getMap().size();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public boolean containsMetadata(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            return internalGetMetadata().getMap().containsKey(str);
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        @Deprecated
        public Map<String, String> getMetadata() {
            return getMetadataMap();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public Map<String, String> getMetadataMap() {
            return internalGetMetadata().getMap();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getMetadataOrDefault(String str, String str2) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map<String, String> map = internalGetMetadata().getMap();
            return map.containsKey(str) ? map.get(str) : str2;
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getMetadataOrThrow(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            Map<String, String> map = internalGetMetadata().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return map.get(str);
        }

        public Builder clearMetadata() {
            internalGetMutableMetadata().getMutableMap().clear();
            return this;
        }

        public Builder removeMetadata(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            internalGetMutableMetadata().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, String> getMutableMetadata() {
            return internalGetMutableMetadata().getMutableMap();
        }

        public Builder putMetadata(String str, String str2) {
            if (str == null) {
                throw new NullPointerException();
            }
            if (str2 == null) {
                throw new NullPointerException();
            }
            internalGetMutableMetadata().getMutableMap().put(str, str2);
            return this;
        }

        public Builder putAllMetadata(Map<String, String> map) {
            internalGetMutableMetadata().getMutableMap().putAll(map);
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

    public static ErrorInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ErrorInfo> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ErrorInfo> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ErrorInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
