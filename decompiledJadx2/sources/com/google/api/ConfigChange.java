package com.google.api;

import com.google.api.Advice;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
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
public final class ConfigChange extends GeneratedMessageV3 implements ConfigChangeOrBuilder {
    public static final int ADVICES_FIELD_NUMBER = 5;
    public static final int CHANGE_TYPE_FIELD_NUMBER = 4;
    public static final int ELEMENT_FIELD_NUMBER = 1;
    public static final int NEW_VALUE_FIELD_NUMBER = 3;
    public static final int OLD_VALUE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private List<Advice> advices_;
    private int changeType_;
    private volatile Object element_;
    private byte memoizedIsInitialized;
    private volatile Object newValue_;
    private volatile Object oldValue_;
    private static final ConfigChange DEFAULT_INSTANCE = new ConfigChange();
    private static final Parser<ConfigChange> PARSER = new AbstractParser<ConfigChange>() { // from class: com.google.api.ConfigChange.1
        @Override // com.google.protobuf.Parser
        public ConfigChange parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ConfigChange(codedInputStream, extensionRegistryLite);
        }
    };

    private ConfigChange(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ConfigChange() {
        this.memoizedIsInitialized = (byte) -1;
        this.element_ = "";
        this.oldValue_ = "";
        this.newValue_ = "";
        this.changeType_ = 0;
        this.advices_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ConfigChange();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ConfigChange(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            this.element_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            this.oldValue_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 26) {
                            this.newValue_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 32) {
                            this.changeType_ = codedInputStream.readEnum();
                        } else if (readTag == 42) {
                            if (!(z2 & true)) {
                                this.advices_ = new ArrayList();
                                z2 |= true;
                            }
                            this.advices_.add(codedInputStream.readMessage(Advice.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.advices_ = Collections.unmodifiableList(this.advices_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ConfigChangeProto.internal_static_google_api_ConfigChange_fieldAccessorTable.ensureFieldAccessorsInitialized(ConfigChange.class, Builder.class);
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getElement() {
        Object obj = this.element_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.element_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getElementBytes() {
        Object obj = this.element_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.element_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getOldValue() {
        Object obj = this.oldValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.oldValue_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getOldValueBytes() {
        Object obj = this.oldValue_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.oldValue_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public String getNewValue() {
        Object obj = this.newValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.newValue_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ByteString getNewValueBytes() {
        Object obj = this.newValue_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.newValue_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public int getChangeTypeValue() {
        return this.changeType_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public ChangeType getChangeType() {
        ChangeType valueOf = ChangeType.valueOf(this.changeType_);
        return valueOf == null ? ChangeType.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public List<Advice> getAdvicesList() {
        return this.advices_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
        return this.advices_;
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public int getAdvicesCount() {
        return this.advices_.size();
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public Advice getAdvices(int i) {
        return this.advices_.get(i);
    }

    @Override // com.google.api.ConfigChangeOrBuilder
    public AdviceOrBuilder getAdvicesOrBuilder(int i) {
        return this.advices_.get(i);
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
        if (!getElementBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.element_);
        }
        if (!getOldValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.oldValue_);
        }
        if (!getNewValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.newValue_);
        }
        if (this.changeType_ != ChangeType.CHANGE_TYPE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(4, this.changeType_);
        }
        for (int i = 0; i < this.advices_.size(); i++) {
            codedOutputStream.writeMessage(5, this.advices_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getElementBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.element_) + 0 : 0;
        if (!getOldValueBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.oldValue_);
        }
        if (!getNewValueBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.newValue_);
        }
        if (this.changeType_ != ChangeType.CHANGE_TYPE_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(4, this.changeType_);
        }
        for (int i2 = 0; i2 < this.advices_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(5, this.advices_.get(i2));
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
        if (!(obj instanceof ConfigChange)) {
            return super.equals(obj);
        }
        ConfigChange configChange = (ConfigChange) obj;
        return getElement().equals(configChange.getElement()) && getOldValue().equals(configChange.getOldValue()) && getNewValue().equals(configChange.getNewValue()) && this.changeType_ == configChange.changeType_ && getAdvicesList().equals(configChange.getAdvicesList()) && this.unknownFields.equals(configChange.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getElement().hashCode()) * 37) + 2) * 53) + getOldValue().hashCode()) * 37) + 3) * 53) + getNewValue().hashCode()) * 37) + 4) * 53) + this.changeType_;
        if (getAdvicesCount() > 0) {
            hashCode = (((hashCode * 37) + 5) * 53) + getAdvicesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ConfigChange parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ConfigChange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ConfigChange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ConfigChange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(InputStream inputStream) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ConfigChange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ConfigChange parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ConfigChange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ConfigChange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ConfigChange configChange) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(configChange);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ConfigChangeOrBuilder {
        private RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> advicesBuilder_;
        private List<Advice> advices_;
        private int bitField0_;
        private int changeType_;
        private Object element_;
        private Object newValue_;
        private Object oldValue_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_fieldAccessorTable.ensureFieldAccessorsInitialized(ConfigChange.class, Builder.class);
        }

        private Builder() {
            this.element_ = "";
            this.oldValue_ = "";
            this.newValue_ = "";
            this.changeType_ = 0;
            this.advices_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.element_ = "";
            this.oldValue_ = "";
            this.newValue_ = "";
            this.changeType_ = 0;
            this.advices_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ConfigChange.alwaysUseFieldBuilders) {
                getAdvicesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.element_ = "";
            this.oldValue_ = "";
            this.newValue_ = "";
            this.changeType_ = 0;
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.advices_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ConfigChange getDefaultInstanceForType() {
            return ConfigChange.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ConfigChange build() {
            ConfigChange buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ConfigChange buildPartial() {
            ConfigChange configChange = new ConfigChange(this);
            int i = this.bitField0_;
            configChange.element_ = this.element_;
            configChange.oldValue_ = this.oldValue_;
            configChange.newValue_ = this.newValue_;
            configChange.changeType_ = this.changeType_;
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                configChange.advices_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.advices_ = Collections.unmodifiableList(this.advices_);
                    this.bitField0_ &= -2;
                }
                configChange.advices_ = this.advices_;
            }
            onBuilt();
            return configChange;
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
            if (message instanceof ConfigChange) {
                return mergeFrom((ConfigChange) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ConfigChange configChange) {
            if (configChange == ConfigChange.getDefaultInstance()) {
                return this;
            }
            if (!configChange.getElement().isEmpty()) {
                this.element_ = configChange.element_;
                onChanged();
            }
            if (!configChange.getOldValue().isEmpty()) {
                this.oldValue_ = configChange.oldValue_;
                onChanged();
            }
            if (!configChange.getNewValue().isEmpty()) {
                this.newValue_ = configChange.newValue_;
                onChanged();
            }
            if (configChange.changeType_ != 0) {
                setChangeTypeValue(configChange.getChangeTypeValue());
            }
            if (this.advicesBuilder_ == null) {
                if (!configChange.advices_.isEmpty()) {
                    if (this.advices_.isEmpty()) {
                        this.advices_ = configChange.advices_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureAdvicesIsMutable();
                        this.advices_.addAll(configChange.advices_);
                    }
                    onChanged();
                }
            } else if (!configChange.advices_.isEmpty()) {
                if (!this.advicesBuilder_.isEmpty()) {
                    this.advicesBuilder_.addAllMessages(configChange.advices_);
                } else {
                    this.advicesBuilder_.dispose();
                    this.advicesBuilder_ = null;
                    this.advices_ = configChange.advices_;
                    this.bitField0_ &= -2;
                    this.advicesBuilder_ = ConfigChange.alwaysUseFieldBuilders ? getAdvicesFieldBuilder() : null;
                }
            }
            mergeUnknownFields(configChange.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ConfigChange configChange = null;
            try {
                try {
                    ConfigChange configChange2 = (ConfigChange) ConfigChange.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (configChange2 != null) {
                        mergeFrom(configChange2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ConfigChange configChange3 = (ConfigChange) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        configChange = configChange3;
                        if (configChange != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (configChange != null) {
                    mergeFrom(configChange);
                }
                throw th;
            }
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getElement() {
            Object obj = this.element_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.element_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getElementBytes() {
            Object obj = this.element_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.element_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setElement(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.element_ = str;
            onChanged();
            return this;
        }

        public Builder clearElement() {
            this.element_ = ConfigChange.getDefaultInstance().getElement();
            onChanged();
            return this;
        }

        public Builder setElementBytes(ByteString byteString) {
            if (byteString != null) {
                ConfigChange.checkByteStringIsUtf8(byteString);
                this.element_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getOldValue() {
            Object obj = this.oldValue_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.oldValue_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getOldValueBytes() {
            Object obj = this.oldValue_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.oldValue_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setOldValue(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.oldValue_ = str;
            onChanged();
            return this;
        }

        public Builder clearOldValue() {
            this.oldValue_ = ConfigChange.getDefaultInstance().getOldValue();
            onChanged();
            return this;
        }

        public Builder setOldValueBytes(ByteString byteString) {
            if (byteString != null) {
                ConfigChange.checkByteStringIsUtf8(byteString);
                this.oldValue_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public String getNewValue() {
            Object obj = this.newValue_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.newValue_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ByteString getNewValueBytes() {
            Object obj = this.newValue_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.newValue_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNewValue(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.newValue_ = str;
            onChanged();
            return this;
        }

        public Builder clearNewValue() {
            this.newValue_ = ConfigChange.getDefaultInstance().getNewValue();
            onChanged();
            return this;
        }

        public Builder setNewValueBytes(ByteString byteString) {
            if (byteString != null) {
                ConfigChange.checkByteStringIsUtf8(byteString);
                this.newValue_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public int getChangeTypeValue() {
            return this.changeType_;
        }

        public Builder setChangeTypeValue(int i) {
            this.changeType_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public ChangeType getChangeType() {
            ChangeType valueOf = ChangeType.valueOf(this.changeType_);
            return valueOf == null ? ChangeType.UNRECOGNIZED : valueOf;
        }

        public Builder setChangeType(ChangeType changeType) {
            if (changeType == null) {
                throw new NullPointerException();
            }
            this.changeType_ = changeType.getNumber();
            onChanged();
            return this;
        }

        public Builder clearChangeType() {
            this.changeType_ = 0;
            onChanged();
            return this;
        }

        private void ensureAdvicesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.advices_ = new ArrayList(this.advices_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public List<Advice> getAdvicesList() {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.advices_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public int getAdvicesCount() {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.advices_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public Advice getAdvices(int i) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.advices_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAdvices(int i, Advice advice) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, advice);
            } else {
                if (advice == null) {
                    throw new NullPointerException();
                }
                ensureAdvicesIsMutable();
                this.advices_.set(i, advice);
                onChanged();
            }
            return this;
        }

        public Builder setAdvices(int i, Advice.Builder builder) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAdvices(Advice advice) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(advice);
            } else {
                if (advice == null) {
                    throw new NullPointerException();
                }
                ensureAdvicesIsMutable();
                this.advices_.add(advice);
                onChanged();
            }
            return this;
        }

        public Builder addAdvices(int i, Advice advice) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, advice);
            } else {
                if (advice == null) {
                    throw new NullPointerException();
                }
                ensureAdvicesIsMutable();
                this.advices_.add(i, advice);
                onChanged();
            }
            return this;
        }

        public Builder addAdvices(Advice.Builder builder) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addAdvices(int i, Advice.Builder builder) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllAdvices(Iterable<? extends Advice> iterable) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.advices_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAdvices() {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.advices_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAdvices(int i) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Advice.Builder getAdvicesBuilder(int i) {
            return getAdvicesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public AdviceOrBuilder getAdvicesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.advices_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.ConfigChangeOrBuilder
        public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
            RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.advices_);
        }

        public Advice.Builder addAdvicesBuilder() {
            return getAdvicesFieldBuilder().addBuilder(Advice.getDefaultInstance());
        }

        public Advice.Builder addAdvicesBuilder(int i) {
            return getAdvicesFieldBuilder().addBuilder(i, Advice.getDefaultInstance());
        }

        public List<Advice.Builder> getAdvicesBuilderList() {
            return getAdvicesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Advice, Advice.Builder, AdviceOrBuilder> getAdvicesFieldBuilder() {
            if (this.advicesBuilder_ == null) {
                this.advicesBuilder_ = new RepeatedFieldBuilderV3<>(this.advices_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.advices_ = null;
            }
            return this.advicesBuilder_;
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

    public static ConfigChange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConfigChange> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ConfigChange> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ConfigChange getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
