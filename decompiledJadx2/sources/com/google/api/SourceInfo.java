package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class SourceInfo extends GeneratedMessageV3 implements SourceInfoOrBuilder {
    private static final SourceInfo DEFAULT_INSTANCE = new SourceInfo();
    private static final Parser<SourceInfo> PARSER = new AbstractParser<SourceInfo>() { // from class: com.google.api.SourceInfo.1
        @Override // com.google.protobuf.Parser
        public SourceInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SourceInfo(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SOURCE_FILES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<Any> sourceFiles_;

    private SourceInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SourceInfo() {
        this.memoizedIsInitialized = (byte) -1;
        this.sourceFiles_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SourceInfo();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private SourceInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                if (!(z2 & true)) {
                                    this.sourceFiles_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.sourceFiles_.add(codedInputStream.readMessage(Any.parser(), extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.sourceFiles_ = Collections.unmodifiableList(this.sourceFiles_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SourceInfoProto.internal_static_google_api_SourceInfo_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SourceInfoProto.internal_static_google_api_SourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceInfo.class, Builder.class);
    }

    @Override // com.google.api.SourceInfoOrBuilder
    public List<Any> getSourceFilesList() {
        return this.sourceFiles_;
    }

    @Override // com.google.api.SourceInfoOrBuilder
    public List<? extends AnyOrBuilder> getSourceFilesOrBuilderList() {
        return this.sourceFiles_;
    }

    @Override // com.google.api.SourceInfoOrBuilder
    public int getSourceFilesCount() {
        return this.sourceFiles_.size();
    }

    @Override // com.google.api.SourceInfoOrBuilder
    public Any getSourceFiles(int i) {
        return this.sourceFiles_.get(i);
    }

    @Override // com.google.api.SourceInfoOrBuilder
    public AnyOrBuilder getSourceFilesOrBuilder(int i) {
        return this.sourceFiles_.get(i);
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
        for (int i = 0; i < this.sourceFiles_.size(); i++) {
            codedOutputStream.writeMessage(1, this.sourceFiles_.get(i));
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
        for (int i3 = 0; i3 < this.sourceFiles_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.sourceFiles_.get(i3));
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SourceInfo)) {
            return super.equals(obj);
        }
        SourceInfo sourceInfo = (SourceInfo) obj;
        return getSourceFilesList().equals(sourceInfo.getSourceFilesList()) && this.unknownFields.equals(sourceInfo.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getSourceFilesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getSourceFilesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static SourceInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SourceInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SourceInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SourceInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(InputStream inputStream) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SourceInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SourceInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SourceInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SourceInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SourceInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SourceInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SourceInfo sourceInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(sourceInfo);
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
      
     */
    /* loaded from: classes2.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SourceInfoOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> sourceFilesBuilder_;
        private List<Any> sourceFiles_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SourceInfoProto.internal_static_google_api_SourceInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SourceInfoProto.internal_static_google_api_SourceInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SourceInfo.class, Builder.class);
        }

        private Builder() {
            this.sourceFiles_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.sourceFiles_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (SourceInfo.alwaysUseFieldBuilders) {
                getSourceFilesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.sourceFiles_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SourceInfoProto.internal_static_google_api_SourceInfo_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SourceInfo getDefaultInstanceForType() {
            return SourceInfo.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SourceInfo build() {
            SourceInfo buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SourceInfo buildPartial() {
            SourceInfo sourceInfo = new SourceInfo(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.sourceFiles_ = Collections.unmodifiableList(this.sourceFiles_);
                    this.bitField0_ &= -2;
                }
                sourceInfo.sourceFiles_ = this.sourceFiles_;
            } else {
                sourceInfo.sourceFiles_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return sourceInfo;
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
            if (message instanceof SourceInfo) {
                return mergeFrom((SourceInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SourceInfo sourceInfo) {
            if (sourceInfo == SourceInfo.getDefaultInstance()) {
                return this;
            }
            if (this.sourceFilesBuilder_ == null) {
                if (!sourceInfo.sourceFiles_.isEmpty()) {
                    if (this.sourceFiles_.isEmpty()) {
                        this.sourceFiles_ = sourceInfo.sourceFiles_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureSourceFilesIsMutable();
                        this.sourceFiles_.addAll(sourceInfo.sourceFiles_);
                    }
                    onChanged();
                }
            } else if (!sourceInfo.sourceFiles_.isEmpty()) {
                if (!this.sourceFilesBuilder_.isEmpty()) {
                    this.sourceFilesBuilder_.addAllMessages(sourceInfo.sourceFiles_);
                } else {
                    this.sourceFilesBuilder_.dispose();
                    this.sourceFilesBuilder_ = null;
                    this.sourceFiles_ = sourceInfo.sourceFiles_;
                    this.bitField0_ &= -2;
                    this.sourceFilesBuilder_ = SourceInfo.alwaysUseFieldBuilders ? getSourceFilesFieldBuilder() : null;
                }
            }
            mergeUnknownFields(sourceInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SourceInfo sourceInfo = null;
            try {
                try {
                    SourceInfo sourceInfo2 = (SourceInfo) SourceInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (sourceInfo2 != null) {
                        mergeFrom(sourceInfo2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SourceInfo sourceInfo3 = (SourceInfo) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        sourceInfo = sourceInfo3;
                        if (sourceInfo != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (sourceInfo != null) {
                    mergeFrom(sourceInfo);
                }
                throw th;
            }
        }

        private void ensureSourceFilesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.sourceFiles_ = new ArrayList(this.sourceFiles_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.SourceInfoOrBuilder
        public List<Any> getSourceFilesList() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.sourceFiles_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.SourceInfoOrBuilder
        public int getSourceFilesCount() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sourceFiles_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.SourceInfoOrBuilder
        public Any getSourceFiles(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sourceFiles_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setSourceFiles(int i, Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                ensureSourceFilesIsMutable();
                this.sourceFiles_.set(i, any);
                onChanged();
            }
            return this;
        }

        public Builder setSourceFiles(int i, Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourceFilesIsMutable();
                this.sourceFiles_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addSourceFiles(Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                ensureSourceFilesIsMutable();
                this.sourceFiles_.add(any);
                onChanged();
            }
            return this;
        }

        public Builder addSourceFiles(int i, Any any) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, any);
            } else {
                if (any == null) {
                    throw new NullPointerException();
                }
                ensureSourceFilesIsMutable();
                this.sourceFiles_.add(i, any);
                onChanged();
            }
            return this;
        }

        public Builder addSourceFiles(Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourceFilesIsMutable();
                this.sourceFiles_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addSourceFiles(int i, Any.Builder builder) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourceFilesIsMutable();
                this.sourceFiles_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllSourceFiles(Iterable<? extends Any> iterable) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourceFilesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.sourceFiles_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearSourceFiles() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.sourceFiles_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeSourceFiles(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSourceFilesIsMutable();
                this.sourceFiles_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Any.Builder getSourceFilesBuilder(int i) {
            return getSourceFilesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.SourceInfoOrBuilder
        public AnyOrBuilder getSourceFilesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.sourceFiles_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.SourceInfoOrBuilder
        public List<? extends AnyOrBuilder> getSourceFilesOrBuilderList() {
            RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> repeatedFieldBuilderV3 = this.sourceFilesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.sourceFiles_);
        }

        public Any.Builder addSourceFilesBuilder() {
            return getSourceFilesFieldBuilder().addBuilder(Any.getDefaultInstance());
        }

        public Any.Builder addSourceFilesBuilder(int i) {
            return getSourceFilesFieldBuilder().addBuilder(i, Any.getDefaultInstance());
        }

        public List<Any.Builder> getSourceFilesBuilderList() {
            return getSourceFilesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getSourceFilesFieldBuilder() {
            if (this.sourceFilesBuilder_ == null) {
                this.sourceFilesBuilder_ = new RepeatedFieldBuilderV3<>(this.sourceFiles_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.sourceFiles_ = null;
            }
            return this.sourceFilesBuilder_;
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

    public static SourceInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SourceInfo> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SourceInfo> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SourceInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
