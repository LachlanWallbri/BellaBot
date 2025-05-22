package com.google.cloud.dialogflow.v2beta1;

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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
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
/* loaded from: classes3.dex */
public final class EntityType extends GeneratedMessageV3 implements EntityTypeOrBuilder {
    public static final int AUTO_EXPANSION_MODE_FIELD_NUMBER = 4;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 2;
    public static final int ENABLE_FUZZY_EXTRACTION_FIELD_NUMBER = 7;
    public static final int ENTITIES_FIELD_NUMBER = 6;
    public static final int KIND_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private int autoExpansionMode_;
    private volatile Object displayName_;
    private boolean enableFuzzyExtraction_;
    private List<Entity> entities_;
    private int kind_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private static final EntityType DEFAULT_INSTANCE = new EntityType();
    private static final Parser<EntityType> PARSER = new AbstractParser<EntityType>() { // from class: com.google.cloud.dialogflow.v2beta1.EntityType.1
        @Override // com.google.protobuf.Parser
        public EntityType parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new EntityType(codedInputStream, extensionRegistryLite);
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public interface EntityOrBuilder extends MessageOrBuilder {
        String getSynonyms(int i);

        ByteString getSynonymsBytes(int i);

        int getSynonymsCount();

        List<String> getSynonymsList();

        String getValue();

        ByteString getValueBytes();
    }

    private EntityType(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private EntityType() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.displayName_ = "";
        this.kind_ = 0;
        this.autoExpansionMode_ = 0;
        this.entities_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new EntityType();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private EntityType(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            } else if (readTag == 18) {
                                this.displayName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 24) {
                                this.kind_ = codedInputStream.readEnum();
                            } else if (readTag == 32) {
                                this.autoExpansionMode_ = codedInputStream.readEnum();
                            } else if (readTag == 50) {
                                if (!(z2 & true)) {
                                    this.entities_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.entities_.add(codedInputStream.readMessage(Entity.parser(), extensionRegistryLite));
                            } else if (readTag == 56) {
                                this.enableFuzzyExtraction_ = codedInputStream.readBool();
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
        return EntityTypeProto.f1672xfb5a364f;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return EntityTypeProto.f1673x55519dcd.ensureFieldAccessorsInitialized(EntityType.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum Kind implements ProtocolMessageEnum {
        KIND_UNSPECIFIED(0),
        KIND_MAP(1),
        KIND_LIST(2),
        KIND_REGEXP(3),
        UNRECOGNIZED(-1);

        public static final int KIND_LIST_VALUE = 2;
        public static final int KIND_MAP_VALUE = 1;
        public static final int KIND_REGEXP_VALUE = 3;
        public static final int KIND_UNSPECIFIED_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<Kind> internalValueMap = new Internal.EnumLiteMap<Kind>() { // from class: com.google.cloud.dialogflow.v2beta1.EntityType.Kind.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Kind findValueByNumber(int i) {
                return Kind.forNumber(i);
            }
        };
        private static final Kind[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static Kind valueOf(int i) {
            return forNumber(i);
        }

        public static Kind forNumber(int i) {
            if (i == 0) {
                return KIND_UNSPECIFIED;
            }
            if (i == 1) {
                return KIND_MAP;
            }
            if (i == 2) {
                return KIND_LIST;
            }
            if (i != 3) {
                return null;
            }
            return KIND_REGEXP;
        }

        public static Internal.EnumLiteMap<Kind> internalGetValueMap() {
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
            return EntityType.getDescriptor().getEnumTypes().get(0);
        }

        public static Kind valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        Kind(int i) {
            this.value = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public enum AutoExpansionMode implements ProtocolMessageEnum {
        AUTO_EXPANSION_MODE_UNSPECIFIED(0),
        AUTO_EXPANSION_MODE_DEFAULT(1),
        UNRECOGNIZED(-1);

        public static final int AUTO_EXPANSION_MODE_DEFAULT_VALUE = 1;
        public static final int AUTO_EXPANSION_MODE_UNSPECIFIED_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<AutoExpansionMode> internalValueMap = new Internal.EnumLiteMap<AutoExpansionMode>() { // from class: com.google.cloud.dialogflow.v2beta1.EntityType.AutoExpansionMode.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public AutoExpansionMode findValueByNumber(int i) {
                return AutoExpansionMode.forNumber(i);
            }
        };
        private static final AutoExpansionMode[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static AutoExpansionMode valueOf(int i) {
            return forNumber(i);
        }

        public static AutoExpansionMode forNumber(int i) {
            if (i == 0) {
                return AUTO_EXPANSION_MODE_UNSPECIFIED;
            }
            if (i != 1) {
                return null;
            }
            return AUTO_EXPANSION_MODE_DEFAULT;
        }

        public static Internal.EnumLiteMap<AutoExpansionMode> internalGetValueMap() {
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
            return EntityType.getDescriptor().getEnumTypes().get(1);
        }

        public static AutoExpansionMode valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        AutoExpansionMode(int i) {
            this.value = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static final class Entity extends GeneratedMessageV3 implements EntityOrBuilder {
        private static final Entity DEFAULT_INSTANCE = new Entity();
        private static final Parser<Entity> PARSER = new AbstractParser<Entity>() { // from class: com.google.cloud.dialogflow.v2beta1.EntityType.Entity.1
            @Override // com.google.protobuf.Parser
            public Entity parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Entity(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SYNONYMS_FIELD_NUMBER = 2;
        public static final int VALUE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private LazyStringList synonyms_;
        private volatile Object value_;

        private Entity(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Entity() {
            this.memoizedIsInitialized = (byte) -1;
            this.value_ = "";
            this.synonyms_ = LazyStringArrayList.EMPTY;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Entity();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Entity(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.value_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    if (!(z2 & true)) {
                                        this.synonyms_ = new LazyStringArrayList();
                                        z2 |= true;
                                    }
                                    this.synonyms_.add(readStringRequireUtf8);
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
                        this.synonyms_ = this.synonyms_.getUnmodifiableView();
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EntityTypeProto.f1670xd312ea4b;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EntityTypeProto.f1671xc20495c9.ensureFieldAccessorsInitialized(Entity.class, Builder.class);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
        public String getValue() {
            Object obj = this.value_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.value_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
        public ByteString getValueBytes() {
            Object obj = this.value_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.value_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
        public ProtocolStringList getSynonymsList() {
            return this.synonyms_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
        public int getSynonymsCount() {
            return this.synonyms_.size();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
        public String getSynonyms(int i) {
            return (String) this.synonyms_.get(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
        public ByteString getSynonymsBytes(int i) {
            return this.synonyms_.getByteString(i);
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
            if (!getValueBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.value_);
            }
            for (int i = 0; i < this.synonyms_.size(); i++) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.synonyms_.getRaw(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = !getValueBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.value_) + 0 : 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.synonyms_.size(); i3++) {
                i2 += computeStringSizeNoTag(this.synonyms_.getRaw(i3));
            }
            int size = computeStringSize + i2 + (getSynonymsList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Entity)) {
                return super.equals(obj);
            }
            Entity entity = (Entity) obj;
            return getValue().equals(entity.getValue()) && getSynonymsList().equals(entity.getSynonymsList()) && this.unknownFields.equals(entity.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getValue().hashCode();
            if (getSynonymsCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getSynonymsList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static Entity parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Entity parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Entity parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Entity parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Entity parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Entity parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Entity parseFrom(InputStream inputStream) throws IOException {
            return (Entity) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Entity parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Entity) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Entity parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Entity) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Entity parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Entity) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Entity parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Entity) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Entity parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Entity) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Entity entity) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(entity);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EntityOrBuilder {
            private int bitField0_;
            private LazyStringList synonyms_;
            private Object value_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return EntityTypeProto.f1670xd312ea4b;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return EntityTypeProto.f1671xc20495c9.ensureFieldAccessorsInitialized(Entity.class, Builder.class);
            }

            private Builder() {
                this.value_ = "";
                this.synonyms_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.value_ = "";
                this.synonyms_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Entity.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.value_ = "";
                this.synonyms_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return EntityTypeProto.f1670xd312ea4b;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Entity getDefaultInstanceForType() {
                return Entity.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Entity build() {
                Entity buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Entity buildPartial() {
                Entity entity = new Entity(this);
                int i = this.bitField0_;
                entity.value_ = this.value_;
                if ((this.bitField0_ & 1) != 0) {
                    this.synonyms_ = this.synonyms_.getUnmodifiableView();
                    this.bitField0_ &= -2;
                }
                entity.synonyms_ = this.synonyms_;
                onBuilt();
                return entity;
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
                if (message instanceof Entity) {
                    return mergeFrom((Entity) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Entity entity) {
                if (entity == Entity.getDefaultInstance()) {
                    return this;
                }
                if (!entity.getValue().isEmpty()) {
                    this.value_ = entity.value_;
                    onChanged();
                }
                if (!entity.synonyms_.isEmpty()) {
                    if (this.synonyms_.isEmpty()) {
                        this.synonyms_ = entity.synonyms_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureSynonymsIsMutable();
                        this.synonyms_.addAll(entity.synonyms_);
                    }
                    onChanged();
                }
                mergeUnknownFields(entity.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Entity entity = null;
                try {
                    try {
                        Entity entity2 = (Entity) Entity.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (entity2 != null) {
                            mergeFrom(entity2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Entity entity3 = (Entity) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            entity = entity3;
                            if (entity != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (entity != null) {
                        mergeFrom(entity);
                    }
                    throw th;
                }
            }

            @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
            public String getValue() {
                Object obj = this.value_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.value_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
            public ByteString getValueBytes() {
                Object obj = this.value_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.value_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setValue(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.value_ = str;
                onChanged();
                return this;
            }

            public Builder clearValue() {
                this.value_ = Entity.getDefaultInstance().getValue();
                onChanged();
                return this;
            }

            public Builder setValueBytes(ByteString byteString) {
                if (byteString != null) {
                    Entity.checkByteStringIsUtf8(byteString);
                    this.value_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private void ensureSynonymsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.synonyms_ = new LazyStringArrayList(this.synonyms_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
            public ProtocolStringList getSynonymsList() {
                return this.synonyms_.getUnmodifiableView();
            }

            @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
            public int getSynonymsCount() {
                return this.synonyms_.size();
            }

            @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
            public String getSynonyms(int i) {
                return (String) this.synonyms_.get(i);
            }

            @Override // com.google.cloud.dialogflow.v2beta1.EntityType.EntityOrBuilder
            public ByteString getSynonymsBytes(int i) {
                return this.synonyms_.getByteString(i);
            }

            public Builder setSynonyms(int i, String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureSynonymsIsMutable();
                this.synonyms_.set(i, str);
                onChanged();
                return this;
            }

            public Builder addSynonyms(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureSynonymsIsMutable();
                this.synonyms_.add(str);
                onChanged();
                return this;
            }

            public Builder addAllSynonyms(Iterable<String> iterable) {
                ensureSynonymsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.synonyms_);
                onChanged();
                return this;
            }

            public Builder clearSynonyms() {
                this.synonyms_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                onChanged();
                return this;
            }

            public Builder addSynonymsBytes(ByteString byteString) {
                if (byteString != null) {
                    Entity.checkByteStringIsUtf8(byteString);
                    ensureSynonymsIsMutable();
                    this.synonyms_.add(byteString);
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

        public static Entity getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Entity> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Entity> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Entity getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public String getDisplayName() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.displayName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public ByteString getDisplayNameBytes() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.displayName_ = copyFromUtf8;
            return copyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public int getKindValue() {
        return this.kind_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public Kind getKind() {
        Kind valueOf = Kind.valueOf(this.kind_);
        return valueOf == null ? Kind.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public int getAutoExpansionModeValue() {
        return this.autoExpansionMode_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public AutoExpansionMode getAutoExpansionMode() {
        AutoExpansionMode valueOf = AutoExpansionMode.valueOf(this.autoExpansionMode_);
        return valueOf == null ? AutoExpansionMode.UNRECOGNIZED : valueOf;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public List<Entity> getEntitiesList() {
        return this.entities_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public List<? extends EntityOrBuilder> getEntitiesOrBuilderList() {
        return this.entities_;
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public int getEntitiesCount() {
        return this.entities_.size();
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public Entity getEntities(int i) {
        return this.entities_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public EntityOrBuilder getEntitiesOrBuilder(int i) {
        return this.entities_.get(i);
    }

    @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
    public boolean getEnableFuzzyExtraction() {
        return this.enableFuzzyExtraction_;
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
        if (!getDisplayNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.displayName_);
        }
        if (this.kind_ != Kind.KIND_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(3, this.kind_);
        }
        if (this.autoExpansionMode_ != AutoExpansionMode.AUTO_EXPANSION_MODE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(4, this.autoExpansionMode_);
        }
        for (int i = 0; i < this.entities_.size(); i++) {
            codedOutputStream.writeMessage(6, this.entities_.get(i));
        }
        boolean z = this.enableFuzzyExtraction_;
        if (z) {
            codedOutputStream.writeBool(7, z);
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
        if (!getDisplayNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.displayName_);
        }
        if (this.kind_ != Kind.KIND_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(3, this.kind_);
        }
        if (this.autoExpansionMode_ != AutoExpansionMode.AUTO_EXPANSION_MODE_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(4, this.autoExpansionMode_);
        }
        for (int i2 = 0; i2 < this.entities_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(6, this.entities_.get(i2));
        }
        boolean z = this.enableFuzzyExtraction_;
        if (z) {
            computeStringSize += CodedOutputStream.computeBoolSize(7, z);
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
        if (!(obj instanceof EntityType)) {
            return super.equals(obj);
        }
        EntityType entityType = (EntityType) obj;
        return getName().equals(entityType.getName()) && getDisplayName().equals(entityType.getDisplayName()) && this.kind_ == entityType.kind_ && this.autoExpansionMode_ == entityType.autoExpansionMode_ && getEntitiesList().equals(entityType.getEntitiesList()) && getEnableFuzzyExtraction() == entityType.getEnableFuzzyExtraction() && this.unknownFields.equals(entityType.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getDisplayName().hashCode()) * 37) + 3) * 53) + this.kind_) * 37) + 4) * 53) + this.autoExpansionMode_;
        if (getEntitiesCount() > 0) {
            hashCode = (((hashCode * 37) + 6) * 53) + getEntitiesList().hashCode();
        }
        int hashBoolean = (((((hashCode * 37) + 7) * 53) + Internal.hashBoolean(getEnableFuzzyExtraction())) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashBoolean;
        return hashBoolean;
    }

    public static EntityType parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static EntityType parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static EntityType parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static EntityType parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static EntityType parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static EntityType parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static EntityType parseFrom(InputStream inputStream) throws IOException {
        return (EntityType) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static EntityType parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EntityType) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EntityType parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EntityType) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static EntityType parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EntityType) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static EntityType parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EntityType) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static EntityType parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EntityType) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EntityType entityType) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(entityType);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EntityTypeOrBuilder {
        private int autoExpansionMode_;
        private int bitField0_;
        private Object displayName_;
        private boolean enableFuzzyExtraction_;
        private RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> entitiesBuilder_;
        private List<Entity> entities_;
        private int kind_;
        private Object name_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return EntityTypeProto.f1672xfb5a364f;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return EntityTypeProto.f1673x55519dcd.ensureFieldAccessorsInitialized(EntityType.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.displayName_ = "";
            this.kind_ = 0;
            this.autoExpansionMode_ = 0;
            this.entities_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.displayName_ = "";
            this.kind_ = 0;
            this.autoExpansionMode_ = 0;
            this.entities_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (EntityType.alwaysUseFieldBuilders) {
                getEntitiesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.displayName_ = "";
            this.kind_ = 0;
            this.autoExpansionMode_ = 0;
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.entities_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.enableFuzzyExtraction_ = false;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return EntityTypeProto.f1672xfb5a364f;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public EntityType getDefaultInstanceForType() {
            return EntityType.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public EntityType build() {
            EntityType buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public EntityType buildPartial() {
            EntityType entityType = new EntityType(this);
            int i = this.bitField0_;
            entityType.name_ = this.name_;
            entityType.displayName_ = this.displayName_;
            entityType.kind_ = this.kind_;
            entityType.autoExpansionMode_ = this.autoExpansionMode_;
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                entityType.entities_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.entities_ = Collections.unmodifiableList(this.entities_);
                    this.bitField0_ &= -2;
                }
                entityType.entities_ = this.entities_;
            }
            entityType.enableFuzzyExtraction_ = this.enableFuzzyExtraction_;
            onBuilt();
            return entityType;
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
            if (message instanceof EntityType) {
                return mergeFrom((EntityType) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(EntityType entityType) {
            if (entityType == EntityType.getDefaultInstance()) {
                return this;
            }
            if (!entityType.getName().isEmpty()) {
                this.name_ = entityType.name_;
                onChanged();
            }
            if (!entityType.getDisplayName().isEmpty()) {
                this.displayName_ = entityType.displayName_;
                onChanged();
            }
            if (entityType.kind_ != 0) {
                setKindValue(entityType.getKindValue());
            }
            if (entityType.autoExpansionMode_ != 0) {
                setAutoExpansionModeValue(entityType.getAutoExpansionModeValue());
            }
            if (this.entitiesBuilder_ == null) {
                if (!entityType.entities_.isEmpty()) {
                    if (this.entities_.isEmpty()) {
                        this.entities_ = entityType.entities_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureEntitiesIsMutable();
                        this.entities_.addAll(entityType.entities_);
                    }
                    onChanged();
                }
            } else if (!entityType.entities_.isEmpty()) {
                if (!this.entitiesBuilder_.isEmpty()) {
                    this.entitiesBuilder_.addAllMessages(entityType.entities_);
                } else {
                    this.entitiesBuilder_.dispose();
                    this.entitiesBuilder_ = null;
                    this.entities_ = entityType.entities_;
                    this.bitField0_ &= -2;
                    this.entitiesBuilder_ = EntityType.alwaysUseFieldBuilders ? getEntitiesFieldBuilder() : null;
                }
            }
            if (entityType.getEnableFuzzyExtraction()) {
                setEnableFuzzyExtraction(entityType.getEnableFuzzyExtraction());
            }
            mergeUnknownFields(entityType.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            EntityType entityType = null;
            try {
                try {
                    EntityType entityType2 = (EntityType) EntityType.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (entityType2 != null) {
                        mergeFrom(entityType2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    EntityType entityType3 = (EntityType) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        entityType = entityType3;
                        if (entityType != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (entityType != null) {
                    mergeFrom(entityType);
                }
                throw th;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
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
            this.name_ = EntityType.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                EntityType.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public String getDisplayName() {
            Object obj = this.displayName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.displayName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
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
            this.displayName_ = EntityType.getDefaultInstance().getDisplayName();
            onChanged();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            if (byteString != null) {
                EntityType.checkByteStringIsUtf8(byteString);
                this.displayName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public int getKindValue() {
            return this.kind_;
        }

        public Builder setKindValue(int i) {
            this.kind_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public Kind getKind() {
            Kind valueOf = Kind.valueOf(this.kind_);
            return valueOf == null ? Kind.UNRECOGNIZED : valueOf;
        }

        public Builder setKind(Kind kind) {
            if (kind == null) {
                throw new NullPointerException();
            }
            this.kind_ = kind.getNumber();
            onChanged();
            return this;
        }

        public Builder clearKind() {
            this.kind_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public int getAutoExpansionModeValue() {
            return this.autoExpansionMode_;
        }

        public Builder setAutoExpansionModeValue(int i) {
            this.autoExpansionMode_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public AutoExpansionMode getAutoExpansionMode() {
            AutoExpansionMode valueOf = AutoExpansionMode.valueOf(this.autoExpansionMode_);
            return valueOf == null ? AutoExpansionMode.UNRECOGNIZED : valueOf;
        }

        public Builder setAutoExpansionMode(AutoExpansionMode autoExpansionMode) {
            if (autoExpansionMode == null) {
                throw new NullPointerException();
            }
            this.autoExpansionMode_ = autoExpansionMode.getNumber();
            onChanged();
            return this;
        }

        public Builder clearAutoExpansionMode() {
            this.autoExpansionMode_ = 0;
            onChanged();
            return this;
        }

        private void ensureEntitiesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.entities_ = new ArrayList(this.entities_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public List<Entity> getEntitiesList() {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.entities_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public int getEntitiesCount() {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entities_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public Entity getEntities(int i) {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setEntities(int i, Entity entity) {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
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

        public Builder setEntities(int i, Entity.Builder builder) {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addEntities(Entity entity) {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
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

        public Builder addEntities(int i, Entity entity) {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
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

        public Builder addEntities(Entity.Builder builder) {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addEntities(int i, Entity.Builder builder) {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllEntities(Iterable<? extends Entity> iterable) {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
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
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
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
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEntitiesIsMutable();
                this.entities_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Entity.Builder getEntitiesBuilder(int i) {
            return getEntitiesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public EntityOrBuilder getEntitiesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.entities_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public List<? extends EntityOrBuilder> getEntitiesOrBuilderList() {
            RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> repeatedFieldBuilderV3 = this.entitiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.entities_);
        }

        public Entity.Builder addEntitiesBuilder() {
            return getEntitiesFieldBuilder().addBuilder(Entity.getDefaultInstance());
        }

        public Entity.Builder addEntitiesBuilder(int i) {
            return getEntitiesFieldBuilder().addBuilder(i, Entity.getDefaultInstance());
        }

        public List<Entity.Builder> getEntitiesBuilderList() {
            return getEntitiesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Entity, Entity.Builder, EntityOrBuilder> getEntitiesFieldBuilder() {
            if (this.entitiesBuilder_ == null) {
                this.entitiesBuilder_ = new RepeatedFieldBuilderV3<>(this.entities_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.entities_ = null;
            }
            return this.entitiesBuilder_;
        }

        @Override // com.google.cloud.dialogflow.v2beta1.EntityTypeOrBuilder
        public boolean getEnableFuzzyExtraction() {
            return this.enableFuzzyExtraction_;
        }

        public Builder setEnableFuzzyExtraction(boolean z) {
            this.enableFuzzyExtraction_ = z;
            onChanged();
            return this;
        }

        public Builder clearEnableFuzzyExtraction() {
            this.enableFuzzyExtraction_ = false;
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

    public static EntityType getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EntityType> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<EntityType> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public EntityType getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
