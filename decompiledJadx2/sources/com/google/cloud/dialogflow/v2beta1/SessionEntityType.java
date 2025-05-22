package com.google.cloud.dialogflow.v2beta1;

import com.google.cloud.dialogflow.v2beta1.EntityType;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
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
/* loaded from: classes3.dex */
public final class SessionEntityType extends GeneratedMessageV3 implements SessionEntityTypeOrBuilder {
    public static final int ENTITIES_FIELD_NUMBER = 3;
    public static final int ENTITY_OVERRIDE_MODE_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<EntityType.Entity> entities_;
    private int entityOverrideMode_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private static final SessionEntityType DEFAULT_INSTANCE = new SessionEntityType();
    private static final Parser<SessionEntityType> PARSER = new AbstractParser<SessionEntityType>() { // from class: com.google.cloud.dialogflow.v2beta1.SessionEntityType.1
        @Override // com.google.protobuf.Parser
        public SessionEntityType parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SessionEntityType(codedInputStream, extensionRegistryLite);
        }
    };

    private SessionEntityType(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SessionEntityType() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.entityOverrideMode_ = 0;
        this.entities_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SessionEntityType();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private SessionEntityType(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 16) {
                                this.entityOverrideMode_ = codedInputStream.readEnum();
                            } else if (readTag == 26) {
                                if (!(z2 & true)) {
                                    this.entities_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.entities_.add(codedInputStream.readMessage(EntityType.Entity.parser(), extensionRegistryLite));
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
                    this.entities_ = Collections.unmodifiableList(this.entities_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SessionEntityTypeProto.f1832x7d94d95d;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SessionEntityTypeProto.f1833xabf752db.ensureFieldAccessorsInitialized(SessionEntityType.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum EntityOverrideMode implements ProtocolMessageEnum {
        ENTITY_OVERRIDE_MODE_UNSPECIFIED(0),
        ENTITY_OVERRIDE_MODE_OVERRIDE(1),
        ENTITY_OVERRIDE_MODE_SUPPLEMENT(2),
        UNRECOGNIZED(-1);

        public static final int ENTITY_OVERRIDE_MODE_OVERRIDE_VALUE = 1;
        public static final int ENTITY_OVERRIDE_MODE_SUPPLEMENT_VALUE = 2;
        public static final int ENTITY_OVERRIDE_MODE_UNSPECIFIED_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<EntityOverrideMode> internalValueMap = new Internal.EnumLiteMap<EntityOverrideMode>() { // from class: com.google.cloud.dialogflow.v2beta1.SessionEntityType.EntityOverrideMode.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public EntityOverrideMode findValueByNumber(int i) {
                return EntityOverrideMode.forNumber(i);
            }
        };
        private static final EntityOverrideMode[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static EntityOverrideMode valueOf(int i) {
            return forNumber(i);
        }

        public static EntityOverrideMode forNumber(int i) {
            if (i == 0) {
                return ENTITY_OVERRIDE_MODE_UNSPECIFIED;
            }
            if (i == 1) {
                return ENTITY_OVERRIDE_MODE_OVERRIDE;
            }
            if (i != 2) {
                return null;
            }
            return ENTITY_OVERRIDE_MODE_SUPPLEMENT;
        }

        public static Internal.EnumLiteMap<EntityOverrideMode> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return SessionEntityType.getDescriptor().getEnumTypes().get(0);
        }

        public static EntityOverrideMode valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        EntityOverrideMode(int i) {
            this.value = i;
        }
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
    public int getEntityOverrideModeValue() {
        return this.entityOverrideMode_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
    public EntityOverrideMode getEntityOverrideMode() {
        EntityOverrideMode valueOf = EntityOverrideMode.valueOf(this.entityOverrideMode_);
        return valueOf == null ? EntityOverrideMode.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
    public List<EntityType.Entity> getEntitiesList() {
        return this.entities_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
    public List<? extends EntityType.EntityOrBuilder> getEntitiesOrBuilderList() {
        return this.entities_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
    public int getEntitiesCount() {
        return this.entities_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
    public EntityType.Entity getEntities(int i) {
        return this.entities_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
    public EntityType.EntityOrBuilder getEntitiesOrBuilder(int i) {
        return this.entities_.get(i);
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (this.entityOverrideMode_ != EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(2, this.entityOverrideMode_);
        }
        for (int i = 0; i < this.entities_.size(); i++) {
            codedOutputStream.writeMessage(3, this.entities_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
        if (this.entityOverrideMode_ != EntityOverrideMode.ENTITY_OVERRIDE_MODE_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(2, this.entityOverrideMode_);
        }
        for (int i2 = 0; i2 < this.entities_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, this.entities_.get(i2));
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
        if (!(obj instanceof SessionEntityType)) {
            return super.equals(obj);
        }
        SessionEntityType sessionEntityType = (SessionEntityType) obj;
        return getName().equals(sessionEntityType.getName()) && this.entityOverrideMode_ == sessionEntityType.entityOverrideMode_ && getEntitiesList().equals(sessionEntityType.getEntitiesList()) && this.unknownFields.equals(sessionEntityType.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + this.entityOverrideMode_;
        if (getEntitiesCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getEntitiesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static SessionEntityType parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SessionEntityType parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SessionEntityType parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SessionEntityType parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SessionEntityType parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SessionEntityType parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SessionEntityType parseFrom(InputStream inputStream) throws IOException {
        return (SessionEntityType) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SessionEntityType parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SessionEntityType) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SessionEntityType parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SessionEntityType) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SessionEntityType parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SessionEntityType) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SessionEntityType parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SessionEntityType) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SessionEntityType parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SessionEntityType) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SessionEntityType sessionEntityType) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(sessionEntityType);
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
    /* loaded from: classes3.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SessionEntityTypeOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> entitiesBuilder_;
        private List<EntityType.Entity> entities_;
        private int entityOverrideMode_;
        private Object name_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SessionEntityTypeProto.f1832x7d94d95d;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SessionEntityTypeProto.f1833xabf752db.ensureFieldAccessorsInitialized(SessionEntityType.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.entityOverrideMode_ = 0;
            this.entities_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.entityOverrideMode_ = 0;
            this.entities_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (SessionEntityType.alwaysUseFieldBuilders) {
                getEntitiesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.entityOverrideMode_ = 0;
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.entities_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SessionEntityTypeProto.f1832x7d94d95d;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SessionEntityType getDefaultInstanceForType() {
            return SessionEntityType.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SessionEntityType build() {
            SessionEntityType buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SessionEntityType buildPartial() {
            SessionEntityType sessionEntityType = new SessionEntityType(this);
            int i = this.bitField0_;
            sessionEntityType.name_ = this.name_;
            sessionEntityType.entityOverrideMode_ = this.entityOverrideMode_;
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                sessionEntityType.entities_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.entities_ = Collections.unmodifiableList(this.entities_);
                    this.bitField0_ &= -2;
                }
                sessionEntityType.entities_ = this.entities_;
            }
            onBuilt();
            return sessionEntityType;
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
            if (message instanceof SessionEntityType) {
                return mergeFrom((SessionEntityType) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SessionEntityType sessionEntityType) {
            if (sessionEntityType == SessionEntityType.getDefaultInstance()) {
                return this;
            }
            if (!sessionEntityType.getName().isEmpty()) {
                this.name_ = sessionEntityType.name_;
                onChanged();
            }
            if (sessionEntityType.entityOverrideMode_ != 0) {
                setEntityOverrideModeValue(sessionEntityType.getEntityOverrideModeValue());
            }
            if (this.entitiesBuilder_ == null) {
                if (!sessionEntityType.entities_.isEmpty()) {
                    if (this.entities_.isEmpty()) {
                        this.entities_ = sessionEntityType.entities_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureEntitiesIsMutable();
                        this.entities_.addAll(sessionEntityType.entities_);
                    }
                    onChanged();
                }
            } else if (!sessionEntityType.entities_.isEmpty()) {
                if (!this.entitiesBuilder_.isEmpty()) {
                    this.entitiesBuilder_.addAllMessages(sessionEntityType.entities_);
                } else {
                    this.entitiesBuilder_.dispose();
                    this.entitiesBuilder_ = null;
                    this.entities_ = sessionEntityType.entities_;
                    this.bitField0_ &= -2;
                    this.entitiesBuilder_ = SessionEntityType.alwaysUseFieldBuilders ? getEntitiesFieldBuilder() : null;
                }
            }
            mergeUnknownFields(sessionEntityType.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            SessionEntityType sessionEntityType = null;
            try {
                try {
                    SessionEntityType sessionEntityType2 = (SessionEntityType) SessionEntityType.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (sessionEntityType2 != null) {
                        mergeFrom(sessionEntityType2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    SessionEntityType sessionEntityType3 = (SessionEntityType) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        sessionEntityType = sessionEntityType3;
                        if (sessionEntityType != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (sessionEntityType != null) {
                    mergeFrom(sessionEntityType);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
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
            this.name_ = SessionEntityType.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                SessionEntityType.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
        public int getEntityOverrideModeValue() {
            return this.entityOverrideMode_;
        }

        public Builder setEntityOverrideModeValue(int i) {
            this.entityOverrideMode_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
        public EntityOverrideMode getEntityOverrideMode() {
            EntityOverrideMode valueOf = EntityOverrideMode.valueOf(this.entityOverrideMode_);
            return valueOf == null ? EntityOverrideMode.UNRECOGNIZED : valueOf;
        }

        public Builder setEntityOverrideMode(EntityOverrideMode entityOverrideMode) {
            if (entityOverrideMode == null) {
                throw new NullPointerException();
            }
            this.entityOverrideMode_ = entityOverrideMode.getNumber();
            onChanged();
            return this;
        }

        public Builder clearEntityOverrideMode() {
            this.entityOverrideMode_ = 0;
            onChanged();
            return this;
        }

        private void ensureEntitiesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.entities_ = new ArrayList(this.entities_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
        public List<EntityType.Entity> getEntitiesList() {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.entities_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
        public int getEntitiesCount() {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entities_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
        public EntityType.Entity getEntities(int i) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setEntities(int i, EntityType.Entity entity) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, entity);
            } else {
                if (entity == null) {
                    throw new NullPointerException();
                }
                ensureEntitiesIsMutable();
                this.entities_.set(i, entity);
                onChanged();
            }
            return this;
        }

        public Builder setEntities(int i, EntityType.Entity.Builder builder) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addEntities(EntityType.Entity entity) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(entity);
            } else {
                if (entity == null) {
                    throw new NullPointerException();
                }
                ensureEntitiesIsMutable();
                this.entities_.add(entity);
                onChanged();
            }
            return this;
        }

        public Builder addEntities(int i, EntityType.Entity entity) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, entity);
            } else {
                if (entity == null) {
                    throw new NullPointerException();
                }
                ensureEntitiesIsMutable();
                this.entities_.add(i, entity);
                onChanged();
            }
            return this;
        }

        public Builder addEntities(EntityType.Entity.Builder builder) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addEntities(int i, EntityType.Entity.Builder builder) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllEntities(Iterable<? extends EntityType.Entity> iterable) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.entities_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearEntities() {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.entities_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeEntities(int i) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public EntityType.Entity.Builder getEntitiesBuilder(int i) {
            return getEntitiesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
        public EntityType.EntityOrBuilder getEntitiesOrBuilder(int i) {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.SessionEntityTypeOrBuilder
        public List<? extends EntityType.EntityOrBuilder> getEntitiesOrBuilderList() {
            RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.entities_);
        }

        public EntityType.Entity.Builder addEntitiesBuilder() {
            return getEntitiesFieldBuilder().addBuilder(EntityType.Entity.getDefaultInstance());
        }

        public EntityType.Entity.Builder addEntitiesBuilder(int i) {
            return getEntitiesFieldBuilder().addBuilder(i, EntityType.Entity.getDefaultInstance());
        }

        public List<EntityType.Entity.Builder> getEntitiesBuilderList() {
            return getEntitiesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<EntityType.Entity, EntityType.Entity.Builder, EntityType.EntityOrBuilder> getEntitiesFieldBuilder() {
            if (this.entitiesBuilder_ == null) {
                this.entitiesBuilder_ = new RepeatedFieldBuilderV3<>(this.entities_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.entities_ = null;
            }
            return this.entitiesBuilder_;
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

    public static SessionEntityType getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SessionEntityType> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SessionEntityType> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SessionEntityType getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
