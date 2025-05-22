package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class DebugInfo extends GeneratedMessageV3 implements DebugInfoOrBuilder {
    public static final int DETAIL_FIELD_NUMBER = 2;
    public static final int STACK_ENTRIES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private volatile Object detail_;
    private byte memoizedIsInitialized;
    private LazyStringList stackEntries_;
    private static final DebugInfo DEFAULT_INSTANCE = new DebugInfo();
    private static final Parser<DebugInfo> PARSER = new AbstractParser<DebugInfo>() { // from class: com.google.rpc.DebugInfo.1
        @Override // com.google.protobuf.Parser
        public DebugInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new DebugInfo(codedInputStream, extensionRegistryLite);
        }
    };

    private DebugInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private DebugInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.stackEntries_ = LazyStringArrayList.EMPTY;
        this.detail_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new DebugInfo();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private DebugInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if (!(z2 & true)) {
                                    this.stackEntries_ = new LazyStringArrayList();
                                    z2 |= true;
                                }
                                this.stackEntries_.add(readStringRequireUtf8);
                            } else if (readTag == 18) {
                                this.detail_ = codedInputStream.readStringRequireUtf8();
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
                if (z2 & true) {
                    this.stackEntries_ = this.stackEntries_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DebugInfo.class, Builder.class);
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public ProtocolStringList getStackEntriesList() {
        return this.stackEntries_;
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public int getStackEntriesCount() {
        return this.stackEntries_.size();
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public String getStackEntries(int i) {
        return (String) this.stackEntries_.get(i);
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public ByteString getStackEntriesBytes(int i) {
        return this.stackEntries_.getByteString(i);
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public String getDetail() {
        Object obj = this.detail_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.detail_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.rpc.DebugInfoOrBuilder
    public ByteString getDetailBytes() {
        Object obj = this.detail_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.detail_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
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
        for (int i = 0; i < this.stackEntries_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.stackEntries_.getRaw(i));
        }
        if (!getDetailBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.detail_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.stackEntries_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.stackEntries_.getRaw(i3));
        }
        int size = 0 + i2 + (getStackEntriesList().size() * 1);
        if (!getDetailBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(2, this.detail_);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DebugInfo)) {
            return super.equals(obj);
        }
        DebugInfo debugInfo = (DebugInfo) obj;
        return getStackEntriesList().equals(debugInfo.getStackEntriesList()) && getDetail().equals(debugInfo.getDetail()) && this.unknownFields.equals(debugInfo.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getStackEntriesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getStackEntriesList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 2) * 53) + getDetail().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static DebugInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static DebugInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static DebugInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static DebugInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(InputStream inputStream) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static DebugInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DebugInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static DebugInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static DebugInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DebugInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DebugInfo debugInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(debugInfo);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DebugInfoOrBuilder {
        private int bitField0_;
        private Object detail_;
        private LazyStringList stackEntries_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DebugInfo.class, Builder.class);
        }

        private Builder() {
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.detail_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.detail_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = DebugInfo.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            this.detail_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_DebugInfo_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DebugInfo getDefaultInstanceForType() {
            return DebugInfo.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DebugInfo build() {
            DebugInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public DebugInfo buildPartial() {
            DebugInfo debugInfo = new DebugInfo(this);
            if ((this.bitField0_ & 1) != 0) {
                this.stackEntries_ = this.stackEntries_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            debugInfo.stackEntries_ = this.stackEntries_;
            debugInfo.detail_ = this.detail_;
            onBuilt();
            return debugInfo;
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
            if (message instanceof DebugInfo) {
                return mergeFrom((DebugInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(DebugInfo debugInfo) {
            if (debugInfo == DebugInfo.getDefaultInstance()) {
                return this;
            }
            if (!debugInfo.stackEntries_.isEmpty()) {
                if (this.stackEntries_.isEmpty()) {
                    this.stackEntries_ = debugInfo.stackEntries_;
                    this.bitField0_ &= -2;
                } else {
                    ensureStackEntriesIsMutable();
                    this.stackEntries_.addAll(debugInfo.stackEntries_);
                }
                onChanged();
            }
            if (!debugInfo.getDetail().isEmpty()) {
                this.detail_ = debugInfo.detail_;
                onChanged();
            }
            mergeUnknownFields(debugInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            DebugInfo debugInfo = null;
            try {
                try {
                    DebugInfo debugInfo2 = (DebugInfo) DebugInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (debugInfo2 != null) {
                        mergeFrom(debugInfo2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    DebugInfo debugInfo3 = (DebugInfo) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        debugInfo = debugInfo3;
                        if (debugInfo != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (debugInfo != null) {
                    mergeFrom(debugInfo);
                }
                throw th;
            }
        }

        private void ensureStackEntriesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.stackEntries_ = new LazyStringArrayList(this.stackEntries_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public ProtocolStringList getStackEntriesList() {
            return this.stackEntries_.getUnmodifiableView();
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public int getStackEntriesCount() {
            return this.stackEntries_.size();
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public String getStackEntries(int i) {
            return (String) this.stackEntries_.get(i);
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public ByteString getStackEntriesBytes(int i) {
            return this.stackEntries_.getByteString(i);
        }

        public Builder setStackEntries(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureStackEntriesIsMutable();
            this.stackEntries_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addStackEntries(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureStackEntriesIsMutable();
            this.stackEntries_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllStackEntries(Iterable<String> iterable) {
            ensureStackEntriesIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.stackEntries_);
            onChanged();
            return this;
        }

        public Builder clearStackEntries() {
            this.stackEntries_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addStackEntriesBytes(ByteString byteString) {
            if (byteString != null) {
                DebugInfo.checkByteStringIsUtf8(byteString);
                ensureStackEntriesIsMutable();
                this.stackEntries_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public String getDetail() {
            Object obj = this.detail_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.detail_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.rpc.DebugInfoOrBuilder
        public ByteString getDetailBytes() {
            Object obj = this.detail_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.detail_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDetail(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.detail_ = str;
            onChanged();
            return this;
        }

        public Builder clearDetail() {
            this.detail_ = DebugInfo.getDefaultInstance().getDetail();
            onChanged();
            return this;
        }

        public Builder setDetailBytes(ByteString byteString) {
            if (byteString != null) {
                DebugInfo.checkByteStringIsUtf8(byteString);
                this.detail_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
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

    public static DebugInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DebugInfo> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<DebugInfo> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public DebugInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
