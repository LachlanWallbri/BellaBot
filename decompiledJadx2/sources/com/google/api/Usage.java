package com.google.api;

import com.google.api.UsageRule;
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
public final class Usage extends GeneratedMessageV3 implements UsageOrBuilder {
    private static final Usage DEFAULT_INSTANCE = new Usage();
    private static final Parser<Usage> PARSER = new AbstractParser<Usage>() { // from class: com.google.api.Usage.1
        @Override // com.google.protobuf.Parser
        public Usage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Usage(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PRODUCER_NOTIFICATION_CHANNEL_FIELD_NUMBER = 7;
    public static final int REQUIREMENTS_FIELD_NUMBER = 1;
    public static final int RULES_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object producerNotificationChannel_;
    private LazyStringList requirements_;
    private List<UsageRule> rules_;

    private Usage(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Usage() {
        this.memoizedIsInitialized = (byte) -1;
        this.requirements_ = LazyStringArrayList.EMPTY;
        this.rules_ = Collections.emptyList();
        this.producerNotificationChannel_ = "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Usage();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Usage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if ((i & 1) == 0) {
                                    this.requirements_ = new LazyStringArrayList();
                                    i |= 1;
                                }
                                this.requirements_.add(readStringRequireUtf8);
                            } else if (readTag == 50) {
                                if ((i & 2) == 0) {
                                    this.rules_ = new ArrayList();
                                    i |= 2;
                                }
                                this.rules_.add(codedInputStream.readMessage(UsageRule.parser(), extensionRegistryLite));
                            } else if (readTag == 58) {
                                this.producerNotificationChannel_ = codedInputStream.readStringRequireUtf8();
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
                if ((i & 1) != 0) {
                    this.requirements_ = this.requirements_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return UsageProto.internal_static_google_api_Usage_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return UsageProto.internal_static_google_api_Usage_fieldAccessorTable.ensureFieldAccessorsInitialized(Usage.class, Builder.class);
    }

    @Override // com.google.api.UsageOrBuilder
    public ProtocolStringList getRequirementsList() {
        return this.requirements_;
    }

    @Override // com.google.api.UsageOrBuilder
    public int getRequirementsCount() {
        return this.requirements_.size();
    }

    @Override // com.google.api.UsageOrBuilder
    public String getRequirements(int i) {
        return (String) this.requirements_.get(i);
    }

    @Override // com.google.api.UsageOrBuilder
    public ByteString getRequirementsBytes(int i) {
        return this.requirements_.getByteString(i);
    }

    @Override // com.google.api.UsageOrBuilder
    public List<UsageRule> getRulesList() {
        return this.rules_;
    }

    @Override // com.google.api.UsageOrBuilder
    public List<? extends UsageRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    @Override // com.google.api.UsageOrBuilder
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override // com.google.api.UsageOrBuilder
    public UsageRule getRules(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.UsageOrBuilder
    public UsageRuleOrBuilder getRulesOrBuilder(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.UsageOrBuilder
    public String getProducerNotificationChannel() {
        Object obj = this.producerNotificationChannel_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.producerNotificationChannel_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.UsageOrBuilder
    public ByteString getProducerNotificationChannelBytes() {
        Object obj = this.producerNotificationChannel_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.producerNotificationChannel_ = copyFromUtf8;
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
        for (int i = 0; i < this.requirements_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.requirements_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.rules_.size(); i2++) {
            codedOutputStream.writeMessage(6, this.rules_.get(i2));
        }
        if (!getProducerNotificationChannelBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.producerNotificationChannel_);
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
        for (int i3 = 0; i3 < this.requirements_.size(); i3++) {
            i2 += computeStringSizeNoTag(this.requirements_.getRaw(i3));
        }
        int size = i2 + 0 + (getRequirementsList().size() * 1);
        for (int i4 = 0; i4 < this.rules_.size(); i4++) {
            size += CodedOutputStream.computeMessageSize(6, this.rules_.get(i4));
        }
        if (!getProducerNotificationChannelBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(7, this.producerNotificationChannel_);
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
        if (!(obj instanceof Usage)) {
            return super.equals(obj);
        }
        Usage usage = (Usage) obj;
        return getRequirementsList().equals(usage.getRequirementsList()) && getRulesList().equals(usage.getRulesList()) && getProducerNotificationChannel().equals(usage.getProducerNotificationChannel()) && this.unknownFields.equals(usage.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getRequirementsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getRequirementsList().hashCode();
        }
        if (getRulesCount() > 0) {
            hashCode = (((hashCode * 37) + 6) * 53) + getRulesList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 7) * 53) + getProducerNotificationChannel().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Usage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Usage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Usage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Usage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Usage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Usage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Usage parseFrom(InputStream inputStream) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Usage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Usage parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Usage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Usage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Usage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Usage parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Usage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Usage usage) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(usage);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UsageOrBuilder {
        private int bitField0_;
        private Object producerNotificationChannel_;
        private LazyStringList requirements_;
        private RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> rulesBuilder_;
        private List<UsageRule> rules_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return UsageProto.internal_static_google_api_Usage_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return UsageProto.internal_static_google_api_Usage_fieldAccessorTable.ensureFieldAccessorsInitialized(Usage.class, Builder.class);
        }

        private Builder() {
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.rules_ = Collections.emptyList();
            this.producerNotificationChannel_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.rules_ = Collections.emptyList();
            this.producerNotificationChannel_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Usage.alwaysUseFieldBuilders) {
                getRulesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.producerNotificationChannel_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return UsageProto.internal_static_google_api_Usage_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Usage getDefaultInstanceForType() {
            return Usage.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Usage build() {
            Usage buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Usage buildPartial() {
            Usage usage = new Usage(this);
            if ((this.bitField0_ & 1) != 0) {
                this.requirements_ = this.requirements_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            usage.requirements_ = this.requirements_;
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                usage.rules_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= -3;
                }
                usage.rules_ = this.rules_;
            }
            usage.producerNotificationChannel_ = this.producerNotificationChannel_;
            onBuilt();
            return usage;
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
            if (message instanceof Usage) {
                return mergeFrom((Usage) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Usage usage) {
            if (usage == Usage.getDefaultInstance()) {
                return this;
            }
            if (!usage.requirements_.isEmpty()) {
                if (this.requirements_.isEmpty()) {
                    this.requirements_ = usage.requirements_;
                    this.bitField0_ &= -2;
                } else {
                    ensureRequirementsIsMutable();
                    this.requirements_.addAll(usage.requirements_);
                }
                onChanged();
            }
            if (this.rulesBuilder_ == null) {
                if (!usage.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = usage.rules_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureRulesIsMutable();
                        this.rules_.addAll(usage.rules_);
                    }
                    onChanged();
                }
            } else if (!usage.rules_.isEmpty()) {
                if (!this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.addAllMessages(usage.rules_);
                } else {
                    this.rulesBuilder_.dispose();
                    this.rulesBuilder_ = null;
                    this.rules_ = usage.rules_;
                    this.bitField0_ &= -3;
                    this.rulesBuilder_ = Usage.alwaysUseFieldBuilders ? getRulesFieldBuilder() : null;
                }
            }
            if (!usage.getProducerNotificationChannel().isEmpty()) {
                this.producerNotificationChannel_ = usage.producerNotificationChannel_;
                onChanged();
            }
            mergeUnknownFields(usage.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Usage usage = null;
            try {
                try {
                    Usage usage2 = (Usage) Usage.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (usage2 != null) {
                        mergeFrom(usage2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Usage usage3 = (Usage) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        usage = usage3;
                        if (usage != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (usage != null) {
                    mergeFrom(usage);
                }
                throw th;
            }
        }

        private void ensureRequirementsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.requirements_ = new LazyStringArrayList(this.requirements_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.UsageOrBuilder
        public ProtocolStringList getRequirementsList() {
            return this.requirements_.getUnmodifiableView();
        }

        @Override // com.google.api.UsageOrBuilder
        public int getRequirementsCount() {
            return this.requirements_.size();
        }

        @Override // com.google.api.UsageOrBuilder
        public String getRequirements(int i) {
            return (String) this.requirements_.get(i);
        }

        @Override // com.google.api.UsageOrBuilder
        public ByteString getRequirementsBytes(int i) {
            return this.requirements_.getByteString(i);
        }

        public Builder setRequirements(int i, String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureRequirementsIsMutable();
            this.requirements_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addRequirements(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            ensureRequirementsIsMutable();
            this.requirements_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllRequirements(Iterable<String> iterable) {
            ensureRequirementsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.requirements_);
            onChanged();
            return this;
        }

        public Builder clearRequirements() {
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addRequirementsBytes(ByteString byteString) {
            if (byteString != null) {
                Usage.checkByteStringIsUtf8(byteString);
                ensureRequirementsIsMutable();
                this.requirements_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.rules_ = new ArrayList(this.rules_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.api.UsageOrBuilder
        public List<UsageRule> getRulesList() {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.UsageOrBuilder
        public int getRulesCount() {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.UsageOrBuilder
        public UsageRule getRules(int i) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRules(int i, UsageRule usageRule) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, usageRule);
            } else {
                if (usageRule == null) {
                    throw new NullPointerException();
                }
                ensureRulesIsMutable();
                this.rules_.set(i, usageRule);
                onChanged();
            }
            return this;
        }

        public Builder setRules(int i, UsageRule.Builder builder) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRules(UsageRule usageRule) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(usageRule);
            } else {
                if (usageRule == null) {
                    throw new NullPointerException();
                }
                ensureRulesIsMutable();
                this.rules_.add(usageRule);
                onChanged();
            }
            return this;
        }

        public Builder addRules(int i, UsageRule usageRule) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, usageRule);
            } else {
                if (usageRule == null) {
                    throw new NullPointerException();
                }
                ensureRulesIsMutable();
                this.rules_.add(i, usageRule);
                onChanged();
            }
            return this;
        }

        public Builder addRules(UsageRule.Builder builder) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRules(int i, UsageRule.Builder builder) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends UsageRule> iterable) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.rules_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRules() {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRules(int i) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public UsageRule.Builder getRulesBuilder(int i) {
            return getRulesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.UsageOrBuilder
        public UsageRuleOrBuilder getRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.UsageOrBuilder
        public List<? extends UsageRuleOrBuilder> getRulesOrBuilderList() {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public UsageRule.Builder addRulesBuilder() {
            return getRulesFieldBuilder().addBuilder(UsageRule.getDefaultInstance());
        }

        public UsageRule.Builder addRulesBuilder(int i) {
            return getRulesFieldBuilder().addBuilder(i, UsageRule.getDefaultInstance());
        }

        public List<UsageRule.Builder> getRulesBuilderList() {
            return getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                this.rulesBuilder_ = new RepeatedFieldBuilderV3<>(this.rules_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        @Override // com.google.api.UsageOrBuilder
        public String getProducerNotificationChannel() {
            Object obj = this.producerNotificationChannel_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.producerNotificationChannel_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.UsageOrBuilder
        public ByteString getProducerNotificationChannelBytes() {
            Object obj = this.producerNotificationChannel_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.producerNotificationChannel_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setProducerNotificationChannel(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.producerNotificationChannel_ = str;
            onChanged();
            return this;
        }

        public Builder clearProducerNotificationChannel() {
            this.producerNotificationChannel_ = Usage.getDefaultInstance().getProducerNotificationChannel();
            onChanged();
            return this;
        }

        public Builder setProducerNotificationChannelBytes(ByteString byteString) {
            if (byteString != null) {
                Usage.checkByteStringIsUtf8(byteString);
                this.producerNotificationChannel_ = byteString;
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

    public static Usage getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Usage> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Usage> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Usage getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
