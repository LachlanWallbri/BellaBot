package com.google.api;

import com.google.api.Property;
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
public final class ProjectProperties extends GeneratedMessageV3 implements ProjectPropertiesOrBuilder {
    private static final ProjectProperties DEFAULT_INSTANCE = new ProjectProperties();
    private static final Parser<ProjectProperties> PARSER = new AbstractParser<ProjectProperties>() { // from class: com.google.api.ProjectProperties.1
        @Override // com.google.protobuf.Parser
        public ProjectProperties parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ProjectProperties(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PROPERTIES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<Property> properties_;

    private ProjectProperties(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ProjectProperties() {
        this.memoizedIsInitialized = (byte) -1;
        this.properties_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ProjectProperties();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ProjectProperties(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.properties_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.properties_.add(codedInputStream.readMessage(Property.parser(), extensionRegistryLite));
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
                    this.properties_ = Collections.unmodifiableList(this.properties_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ConsumerProto.internal_static_google_api_ProjectProperties_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ConsumerProto.internal_static_google_api_ProjectProperties_fieldAccessorTable.ensureFieldAccessorsInitialized(ProjectProperties.class, Builder.class);
    }

    @Override // com.google.api.ProjectPropertiesOrBuilder
    public List<Property> getPropertiesList() {
        return this.properties_;
    }

    @Override // com.google.api.ProjectPropertiesOrBuilder
    public List<? extends PropertyOrBuilder> getPropertiesOrBuilderList() {
        return this.properties_;
    }

    @Override // com.google.api.ProjectPropertiesOrBuilder
    public int getPropertiesCount() {
        return this.properties_.size();
    }

    @Override // com.google.api.ProjectPropertiesOrBuilder
    public Property getProperties(int i) {
        return this.properties_.get(i);
    }

    @Override // com.google.api.ProjectPropertiesOrBuilder
    public PropertyOrBuilder getPropertiesOrBuilder(int i) {
        return this.properties_.get(i);
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
        for (int i = 0; i < this.properties_.size(); i++) {
            codedOutputStream.writeMessage(1, this.properties_.get(i));
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
        for (int i3 = 0; i3 < this.properties_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.properties_.get(i3));
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
        if (!(obj instanceof ProjectProperties)) {
            return super.equals(obj);
        }
        ProjectProperties projectProperties = (ProjectProperties) obj;
        return getPropertiesList().equals(projectProperties.getPropertiesList()) && this.unknownFields.equals(projectProperties.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getPropertiesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getPropertiesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ProjectProperties parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ProjectProperties parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ProjectProperties parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ProjectProperties parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(InputStream inputStream) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ProjectProperties parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ProjectProperties parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ProjectProperties parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ProjectProperties parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProjectProperties projectProperties) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(projectProperties);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProjectPropertiesOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> propertiesBuilder_;
        private List<Property> properties_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ConsumerProto.internal_static_google_api_ProjectProperties_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ConsumerProto.internal_static_google_api_ProjectProperties_fieldAccessorTable.ensureFieldAccessorsInitialized(ProjectProperties.class, Builder.class);
        }

        private Builder() {
            this.properties_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.properties_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ProjectProperties.alwaysUseFieldBuilders) {
                getPropertiesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.properties_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ConsumerProto.internal_static_google_api_ProjectProperties_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ProjectProperties getDefaultInstanceForType() {
            return ProjectProperties.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ProjectProperties build() {
            ProjectProperties buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ProjectProperties buildPartial() {
            ProjectProperties projectProperties = new ProjectProperties(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.properties_ = Collections.unmodifiableList(this.properties_);
                    this.bitField0_ &= -2;
                }
                projectProperties.properties_ = this.properties_;
            } else {
                projectProperties.properties_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return projectProperties;
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
            if (message instanceof ProjectProperties) {
                return mergeFrom((ProjectProperties) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ProjectProperties projectProperties) {
            if (projectProperties == ProjectProperties.getDefaultInstance()) {
                return this;
            }
            if (this.propertiesBuilder_ == null) {
                if (!projectProperties.properties_.isEmpty()) {
                    if (this.properties_.isEmpty()) {
                        this.properties_ = projectProperties.properties_;
                        this.bitField0_ &= -2;
                    } else {
                        ensurePropertiesIsMutable();
                        this.properties_.addAll(projectProperties.properties_);
                    }
                    onChanged();
                }
            } else if (!projectProperties.properties_.isEmpty()) {
                if (!this.propertiesBuilder_.isEmpty()) {
                    this.propertiesBuilder_.addAllMessages(projectProperties.properties_);
                } else {
                    this.propertiesBuilder_.dispose();
                    this.propertiesBuilder_ = null;
                    this.properties_ = projectProperties.properties_;
                    this.bitField0_ &= -2;
                    this.propertiesBuilder_ = ProjectProperties.alwaysUseFieldBuilders ? getPropertiesFieldBuilder() : null;
                }
            }
            mergeUnknownFields(projectProperties.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ProjectProperties projectProperties = null;
            try {
                try {
                    ProjectProperties projectProperties2 = (ProjectProperties) ProjectProperties.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (projectProperties2 != null) {
                        mergeFrom(projectProperties2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ProjectProperties projectProperties3 = (ProjectProperties) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        projectProperties = projectProperties3;
                        if (projectProperties != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (projectProperties != null) {
                    mergeFrom(projectProperties);
                }
                throw th;
            }
        }

        private void ensurePropertiesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.properties_ = new ArrayList(this.properties_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.ProjectPropertiesOrBuilder
        public List<Property> getPropertiesList() {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.properties_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.ProjectPropertiesOrBuilder
        public int getPropertiesCount() {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.properties_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.ProjectPropertiesOrBuilder
        public Property getProperties(int i) {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.properties_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setProperties(int i, Property property) {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, property);
            } else {
                if (property == null) {
                    throw new NullPointerException();
                }
                ensurePropertiesIsMutable();
                this.properties_.set(i, property);
                onChanged();
            }
            return this;
        }

        public Builder setProperties(int i, Property.Builder builder) {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePropertiesIsMutable();
                this.properties_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addProperties(Property property) {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(property);
            } else {
                if (property == null) {
                    throw new NullPointerException();
                }
                ensurePropertiesIsMutable();
                this.properties_.add(property);
                onChanged();
            }
            return this;
        }

        public Builder addProperties(int i, Property property) {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, property);
            } else {
                if (property == null) {
                    throw new NullPointerException();
                }
                ensurePropertiesIsMutable();
                this.properties_.add(i, property);
                onChanged();
            }
            return this;
        }

        public Builder addProperties(Property.Builder builder) {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePropertiesIsMutable();
                this.properties_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addProperties(int i, Property.Builder builder) {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePropertiesIsMutable();
                this.properties_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllProperties(Iterable<? extends Property> iterable) {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePropertiesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.properties_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearProperties() {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.properties_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeProperties(int i) {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePropertiesIsMutable();
                this.properties_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Property.Builder getPropertiesBuilder(int i) {
            return getPropertiesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.ProjectPropertiesOrBuilder
        public PropertyOrBuilder getPropertiesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.properties_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.ProjectPropertiesOrBuilder
        public List<? extends PropertyOrBuilder> getPropertiesOrBuilderList() {
            RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.properties_);
        }

        public Property.Builder addPropertiesBuilder() {
            return getPropertiesFieldBuilder().addBuilder(Property.getDefaultInstance());
        }

        public Property.Builder addPropertiesBuilder(int i) {
            return getPropertiesFieldBuilder().addBuilder(i, Property.getDefaultInstance());
        }

        public List<Property.Builder> getPropertiesBuilderList() {
            return getPropertiesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Property, Property.Builder, PropertyOrBuilder> getPropertiesFieldBuilder() {
            if (this.propertiesBuilder_ == null) {
                this.propertiesBuilder_ = new RepeatedFieldBuilderV3<>(this.properties_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.properties_ = null;
            }
            return this.propertiesBuilder_;
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

    public static ProjectProperties getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProjectProperties> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ProjectProperties> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ProjectProperties getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
