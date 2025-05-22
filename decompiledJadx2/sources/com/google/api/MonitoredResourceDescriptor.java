package com.google.api;

import com.google.api.LabelDescriptor;
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
public final class MonitoredResourceDescriptor extends GeneratedMessageV3 implements MonitoredResourceDescriptorOrBuilder {
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 2;
    public static final int LABELS_FIELD_NUMBER = 4;
    public static final int LAUNCH_STAGE_FIELD_NUMBER = 7;
    public static final int NAME_FIELD_NUMBER = 5;
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private volatile Object description_;
    private volatile Object displayName_;
    private List<LabelDescriptor> labels_;
    private int launchStage_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private volatile Object type_;
    private static final MonitoredResourceDescriptor DEFAULT_INSTANCE = new MonitoredResourceDescriptor();
    private static final Parser<MonitoredResourceDescriptor> PARSER = new AbstractParser<MonitoredResourceDescriptor>() { // from class: com.google.api.MonitoredResourceDescriptor.1
        @Override // com.google.protobuf.Parser
        public MonitoredResourceDescriptor parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MonitoredResourceDescriptor(codedInputStream, extensionRegistryLite);
        }
    };

    private MonitoredResourceDescriptor(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private MonitoredResourceDescriptor() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.type_ = "";
        this.displayName_ = "";
        this.description_ = "";
        this.labels_ = Collections.emptyList();
        this.launchStage_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new MonitoredResourceDescriptor();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MonitoredResourceDescriptor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.type_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.displayName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.description_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                if (!(z2 & true)) {
                                    this.labels_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.labels_.add(codedInputStream.readMessage(LabelDescriptor.parser(), extensionRegistryLite));
                            } else if (readTag == 42) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 56) {
                                this.launchStage_ = codedInputStream.readEnum();
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
                    this.labels_ = Collections.unmodifiableList(this.labels_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MonitoredResourceProto.f1327x25127934;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MonitoredResourceProto.f1328x8cdf2bb2.ensureFieldAccessorsInitialized(MonitoredResourceDescriptor.class, Builder.class);
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public String getType() {
        Object obj = this.type_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.type_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public ByteString getTypeBytes() {
        Object obj = this.type_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.type_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public String getDisplayName() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.displayName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public ByteString getDisplayNameBytes() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.displayName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public List<LabelDescriptor> getLabelsList() {
        return this.labels_;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
        return this.labels_;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public int getLabelsCount() {
        return this.labels_.size();
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public LabelDescriptor getLabels(int i) {
        return this.labels_.get(i);
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public LabelDescriptorOrBuilder getLabelsOrBuilder(int i) {
        return this.labels_.get(i);
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public int getLaunchStageValue() {
        return this.launchStage_;
    }

    @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
    public LaunchStage getLaunchStage() {
        LaunchStage valueOf = LaunchStage.valueOf(this.launchStage_);
        return valueOf == null ? LaunchStage.UNRECOGNIZED : valueOf;
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
        if (!getTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.type_);
        }
        if (!getDisplayNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.displayName_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.description_);
        }
        for (int i = 0; i < this.labels_.size(); i++) {
            codedOutputStream.writeMessage(4, this.labels_.get(i));
        }
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.name_);
        }
        if (this.launchStage_ != LaunchStage.LAUNCH_STAGE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(7, this.launchStage_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getTypeBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.type_) + 0 : 0;
        if (!getDisplayNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.displayName_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.description_);
        }
        for (int i2 = 0; i2 < this.labels_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(4, this.labels_.get(i2));
        }
        if (!getNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(5, this.name_);
        }
        if (this.launchStage_ != LaunchStage.LAUNCH_STAGE_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(7, this.launchStage_);
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
        if (!(obj instanceof MonitoredResourceDescriptor)) {
            return super.equals(obj);
        }
        MonitoredResourceDescriptor monitoredResourceDescriptor = (MonitoredResourceDescriptor) obj;
        return getName().equals(monitoredResourceDescriptor.getName()) && getType().equals(monitoredResourceDescriptor.getType()) && getDisplayName().equals(monitoredResourceDescriptor.getDisplayName()) && getDescription().equals(monitoredResourceDescriptor.getDescription()) && getLabelsList().equals(monitoredResourceDescriptor.getLabelsList()) && this.launchStage_ == monitoredResourceDescriptor.launchStage_ && this.unknownFields.equals(monitoredResourceDescriptor.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 5) * 53) + getName().hashCode()) * 37) + 1) * 53) + getType().hashCode()) * 37) + 2) * 53) + getDisplayName().hashCode()) * 37) + 3) * 53) + getDescription().hashCode();
        if (getLabelsCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getLabelsList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 7) * 53) + this.launchStage_) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static MonitoredResourceDescriptor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static MonitoredResourceDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(InputStream inputStream) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MonitoredResourceDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MonitoredResourceDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MonitoredResourceDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MonitoredResourceDescriptor monitoredResourceDescriptor) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(monitoredResourceDescriptor);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MonitoredResourceDescriptorOrBuilder {
        private int bitField0_;
        private Object description_;
        private Object displayName_;
        private RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> labelsBuilder_;
        private List<LabelDescriptor> labels_;
        private int launchStage_;
        private Object name_;
        private Object type_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MonitoredResourceProto.f1327x25127934;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoredResourceProto.f1328x8cdf2bb2.ensureFieldAccessorsInitialized(MonitoredResourceDescriptor.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.type_ = "";
            this.displayName_ = "";
            this.description_ = "";
            this.labels_ = Collections.emptyList();
            this.launchStage_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.type_ = "";
            this.displayName_ = "";
            this.description_ = "";
            this.labels_ = Collections.emptyList();
            this.launchStage_ = 0;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (MonitoredResourceDescriptor.alwaysUseFieldBuilders) {
                getLabelsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.type_ = "";
            this.displayName_ = "";
            this.description_ = "";
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.labels_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.launchStage_ = 0;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return MonitoredResourceProto.f1327x25127934;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MonitoredResourceDescriptor getDefaultInstanceForType() {
            return MonitoredResourceDescriptor.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public MonitoredResourceDescriptor build() {
            MonitoredResourceDescriptor buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public MonitoredResourceDescriptor buildPartial() {
            MonitoredResourceDescriptor monitoredResourceDescriptor = new MonitoredResourceDescriptor(this);
            int i = this.bitField0_;
            monitoredResourceDescriptor.name_ = this.name_;
            monitoredResourceDescriptor.type_ = this.type_;
            monitoredResourceDescriptor.displayName_ = this.displayName_;
            monitoredResourceDescriptor.description_ = this.description_;
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                monitoredResourceDescriptor.labels_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.labels_ = Collections.unmodifiableList(this.labels_);
                    this.bitField0_ &= -2;
                }
                monitoredResourceDescriptor.labels_ = this.labels_;
            }
            monitoredResourceDescriptor.launchStage_ = this.launchStage_;
            onBuilt();
            return monitoredResourceDescriptor;
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
            if (message instanceof MonitoredResourceDescriptor) {
                return mergeFrom((MonitoredResourceDescriptor) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MonitoredResourceDescriptor monitoredResourceDescriptor) {
            if (monitoredResourceDescriptor == MonitoredResourceDescriptor.getDefaultInstance()) {
                return this;
            }
            if (!monitoredResourceDescriptor.getName().isEmpty()) {
                this.name_ = monitoredResourceDescriptor.name_;
                onChanged();
            }
            if (!monitoredResourceDescriptor.getType().isEmpty()) {
                this.type_ = monitoredResourceDescriptor.type_;
                onChanged();
            }
            if (!monitoredResourceDescriptor.getDisplayName().isEmpty()) {
                this.displayName_ = monitoredResourceDescriptor.displayName_;
                onChanged();
            }
            if (!monitoredResourceDescriptor.getDescription().isEmpty()) {
                this.description_ = monitoredResourceDescriptor.description_;
                onChanged();
            }
            if (this.labelsBuilder_ == null) {
                if (!monitoredResourceDescriptor.labels_.isEmpty()) {
                    if (this.labels_.isEmpty()) {
                        this.labels_ = monitoredResourceDescriptor.labels_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureLabelsIsMutable();
                        this.labels_.addAll(monitoredResourceDescriptor.labels_);
                    }
                    onChanged();
                }
            } else if (!monitoredResourceDescriptor.labels_.isEmpty()) {
                if (!this.labelsBuilder_.isEmpty()) {
                    this.labelsBuilder_.addAllMessages(monitoredResourceDescriptor.labels_);
                } else {
                    this.labelsBuilder_.dispose();
                    this.labelsBuilder_ = null;
                    this.labels_ = monitoredResourceDescriptor.labels_;
                    this.bitField0_ &= -2;
                    this.labelsBuilder_ = MonitoredResourceDescriptor.alwaysUseFieldBuilders ? getLabelsFieldBuilder() : null;
                }
            }
            if (monitoredResourceDescriptor.launchStage_ != 0) {
                setLaunchStageValue(monitoredResourceDescriptor.getLaunchStageValue());
            }
            mergeUnknownFields(monitoredResourceDescriptor.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            MonitoredResourceDescriptor monitoredResourceDescriptor = null;
            try {
                try {
                    MonitoredResourceDescriptor monitoredResourceDescriptor2 = (MonitoredResourceDescriptor) MonitoredResourceDescriptor.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (monitoredResourceDescriptor2 != null) {
                        mergeFrom(monitoredResourceDescriptor2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    MonitoredResourceDescriptor monitoredResourceDescriptor3 = (MonitoredResourceDescriptor) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        monitoredResourceDescriptor = monitoredResourceDescriptor3;
                        if (monitoredResourceDescriptor != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (monitoredResourceDescriptor != null) {
                    mergeFrom(monitoredResourceDescriptor);
                }
                throw th;
            }
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.name_ = str;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = MonitoredResourceDescriptor.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                MonitoredResourceDescriptor.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public String getType() {
            Object obj = this.type_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.type_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public ByteString getTypeBytes() {
            Object obj = this.type_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.type_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setType(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.type_ = str;
            onChanged();
            return this;
        }

        public Builder clearType() {
            this.type_ = MonitoredResourceDescriptor.getDefaultInstance().getType();
            onChanged();
            return this;
        }

        public Builder setTypeBytes(ByteString byteString) {
            if (byteString != null) {
                MonitoredResourceDescriptor.checkByteStringIsUtf8(byteString);
                this.type_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public String getDisplayName() {
            Object obj = this.displayName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.displayName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public ByteString getDisplayNameBytes() {
            Object obj = this.displayName_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.displayName_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDisplayName(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.displayName_ = str;
            onChanged();
            return this;
        }

        public Builder clearDisplayName() {
            this.displayName_ = MonitoredResourceDescriptor.getDefaultInstance().getDisplayName();
            onChanged();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            if (byteString != null) {
                MonitoredResourceDescriptor.checkByteStringIsUtf8(byteString);
                this.displayName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.description_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setDescription(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.description_ = str;
            onChanged();
            return this;
        }

        public Builder clearDescription() {
            this.description_ = MonitoredResourceDescriptor.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                MonitoredResourceDescriptor.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureLabelsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.labels_ = new ArrayList(this.labels_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public List<LabelDescriptor> getLabelsList() {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.labels_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public int getLabelsCount() {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labels_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public LabelDescriptor getLabels(int i) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labels_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLabels(int i, LabelDescriptor labelDescriptor) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, labelDescriptor);
            } else {
                if (labelDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureLabelsIsMutable();
                this.labels_.set(i, labelDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder setLabels(int i, LabelDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addLabels(LabelDescriptor labelDescriptor) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(labelDescriptor);
            } else {
                if (labelDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureLabelsIsMutable();
                this.labels_.add(labelDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder addLabels(int i, LabelDescriptor labelDescriptor) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, labelDescriptor);
            } else {
                if (labelDescriptor == null) {
                    throw new NullPointerException();
                }
                ensureLabelsIsMutable();
                this.labels_.add(i, labelDescriptor);
                onChanged();
            }
            return this;
        }

        public Builder addLabels(LabelDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addLabels(int i, LabelDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllLabels(Iterable<? extends LabelDescriptor> iterable) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.labels_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLabels() {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.labels_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLabels(int i) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public LabelDescriptor.Builder getLabelsBuilder(int i) {
            return getLabelsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public LabelDescriptorOrBuilder getLabelsOrBuilder(int i) {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labels_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
            RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.labels_);
        }

        public LabelDescriptor.Builder addLabelsBuilder() {
            return getLabelsFieldBuilder().addBuilder(LabelDescriptor.getDefaultInstance());
        }

        public LabelDescriptor.Builder addLabelsBuilder(int i) {
            return getLabelsFieldBuilder().addBuilder(i, LabelDescriptor.getDefaultInstance());
        }

        public List<LabelDescriptor.Builder> getLabelsBuilderList() {
            return getLabelsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LabelDescriptor, LabelDescriptor.Builder, LabelDescriptorOrBuilder> getLabelsFieldBuilder() {
            if (this.labelsBuilder_ == null) {
                this.labelsBuilder_ = new RepeatedFieldBuilderV3<>(this.labels_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.labels_ = null;
            }
            return this.labelsBuilder_;
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public int getLaunchStageValue() {
            return this.launchStage_;
        }

        public Builder setLaunchStageValue(int i) {
            this.launchStage_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.api.MonitoredResourceDescriptorOrBuilder
        public LaunchStage getLaunchStage() {
            LaunchStage valueOf = LaunchStage.valueOf(this.launchStage_);
            return valueOf == null ? LaunchStage.UNRECOGNIZED : valueOf;
        }

        public Builder setLaunchStage(LaunchStage launchStage) {
            if (launchStage == null) {
                throw new NullPointerException();
            }
            this.launchStage_ = launchStage.getNumber();
            onChanged();
            return this;
        }

        public Builder clearLaunchStage() {
            this.launchStage_ = 0;
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

    public static MonitoredResourceDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MonitoredResourceDescriptor> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<MonitoredResourceDescriptor> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public MonitoredResourceDescriptor getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
