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
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
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
  classes2.dex
 */
/* loaded from: classes4.dex */
public final class PreconditionFailure extends GeneratedMessageV3 implements PreconditionFailureOrBuilder {
    private static final PreconditionFailure DEFAULT_INSTANCE = new PreconditionFailure();
    private static final Parser<PreconditionFailure> PARSER = new AbstractParser<PreconditionFailure>() { // from class: com.google.rpc.PreconditionFailure.1
        @Override // com.google.protobuf.Parser
        public PreconditionFailure parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new PreconditionFailure(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VIOLATIONS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<Violation> violations_;

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public interface ViolationOrBuilder extends MessageOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getSubject();

        ByteString getSubjectBytes();

        String getType();

        ByteString getTypeBytes();
    }

    private PreconditionFailure(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private PreconditionFailure() {
        this.memoizedIsInitialized = (byte) -1;
        this.violations_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new PreconditionFailure();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private PreconditionFailure(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.violations_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.violations_.add(codedInputStream.readMessage(Violation.parser(), extensionRegistryLite));
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
                    this.violations_ = Collections.unmodifiableList(this.violations_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.f2064x68e0fceb.ensureFieldAccessorsInitialized(PreconditionFailure.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes4.dex */
    public static final class Violation extends GeneratedMessageV3 implements ViolationOrBuilder {
        public static final int DESCRIPTION_FIELD_NUMBER = 3;
        public static final int SUBJECT_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object description_;
        private byte memoizedIsInitialized;
        private volatile Object subject_;
        private volatile Object type_;
        private static final Violation DEFAULT_INSTANCE = new Violation();
        private static final Parser<Violation> PARSER = new AbstractParser<Violation>() { // from class: com.google.rpc.PreconditionFailure.Violation.1
            @Override // com.google.protobuf.Parser
            public Violation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Violation(codedInputStream, extensionRegistryLite);
            }
        };

        private Violation(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Violation() {
            this.memoizedIsInitialized = (byte) -1;
            this.type_ = "";
            this.subject_ = "";
            this.description_ = "";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Violation();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Violation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
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
                                this.type_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.subject_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.description_ = codedInputStream.readStringRequireUtf8();
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
            return ErrorDetailsProto.f2062xcaaf1d67;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.f2063x3739ece5.ensureFieldAccessorsInitialized(Violation.class, Builder.class);
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
        public String getType() {
            Object obj = this.type_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.type_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
        public ByteString getTypeBytes() {
            Object obj = this.type_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.type_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
        public String getSubject() {
            Object obj = this.subject_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.subject_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
        public ByteString getSubjectBytes() {
            Object obj = this.subject_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.subject_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.description_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = copyFromUtf8;
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
            if (!getTypeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.type_);
            }
            if (!getSubjectBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.subject_);
            }
            if (!getDescriptionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.description_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = getTypeBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.type_);
            if (!getSubjectBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(2, this.subject_);
            }
            if (!getDescriptionBytes().isEmpty()) {
                computeStringSize += GeneratedMessageV3.computeStringSize(3, this.description_);
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
            if (!(obj instanceof Violation)) {
                return super.equals(obj);
            }
            Violation violation = (Violation) obj;
            return getType().equals(violation.getType()) && getSubject().equals(violation.getSubject()) && getDescription().equals(violation.getDescription()) && this.unknownFields.equals(violation.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getType().hashCode()) * 37) + 2) * 53) + getSubject().hashCode()) * 37) + 3) * 53) + getDescription().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Violation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Violation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Violation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Violation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Violation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Violation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Violation parseFrom(InputStream inputStream) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Violation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Violation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Violation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Violation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Violation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Violation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Violation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Violation violation) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(violation);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ViolationOrBuilder {
            private Object description_;
            private Object subject_;
            private Object type_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ErrorDetailsProto.f2062xcaaf1d67;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ErrorDetailsProto.f2063x3739ece5.ensureFieldAccessorsInitialized(Violation.class, Builder.class);
            }

            private Builder() {
                this.type_ = "";
                this.subject_ = "";
                this.description_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.type_ = "";
                this.subject_ = "";
                this.description_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Violation.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.type_ = "";
                this.subject_ = "";
                this.description_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ErrorDetailsProto.f2062xcaaf1d67;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Violation getDefaultInstanceForType() {
                return Violation.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Violation build() {
                Violation buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Violation buildPartial() {
                Violation violation = new Violation(this);
                violation.type_ = this.type_;
                violation.subject_ = this.subject_;
                violation.description_ = this.description_;
                onBuilt();
                return violation;
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
                if (message instanceof Violation) {
                    return mergeFrom((Violation) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Violation violation) {
                if (violation == Violation.getDefaultInstance()) {
                    return this;
                }
                if (!violation.getType().isEmpty()) {
                    this.type_ = violation.type_;
                    onChanged();
                }
                if (!violation.getSubject().isEmpty()) {
                    this.subject_ = violation.subject_;
                    onChanged();
                }
                if (!violation.getDescription().isEmpty()) {
                    this.description_ = violation.description_;
                    onChanged();
                }
                mergeUnknownFields(violation.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Violation violation = null;
                try {
                    try {
                        Violation violation2 = (Violation) Violation.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (violation2 != null) {
                            mergeFrom(violation2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Violation violation3 = (Violation) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            violation = violation3;
                            if (violation != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (violation != null) {
                        mergeFrom(violation);
                    }
                    throw th;
                }
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
            public String getType() {
                Object obj = this.type_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.type_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
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
                this.type_ = Violation.getDefaultInstance().getType();
                onChanged();
                return this;
            }

            public Builder setTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    Violation.checkByteStringIsUtf8(byteString);
                    this.type_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
            public String getSubject() {
                Object obj = this.subject_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.subject_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
            public ByteString getSubjectBytes() {
                Object obj = this.subject_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.subject_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setSubject(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.subject_ = str;
                onChanged();
                return this;
            }

            public Builder clearSubject() {
                this.subject_ = Violation.getDefaultInstance().getSubject();
                onChanged();
                return this;
            }

            public Builder setSubjectBytes(ByteString byteString) {
                if (byteString != null) {
                    Violation.checkByteStringIsUtf8(byteString);
                    this.subject_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
            public String getDescription() {
                Object obj = this.description_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.description_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
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
                this.description_ = Violation.getDefaultInstance().getDescription();
                onChanged();
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                if (byteString != null) {
                    Violation.checkByteStringIsUtf8(byteString);
                    this.description_ = byteString;
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

        public static Violation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Violation> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Violation> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Violation getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    @Override // com.google.rpc.PreconditionFailureOrBuilder
    public List<Violation> getViolationsList() {
        return this.violations_;
    }

    @Override // com.google.rpc.PreconditionFailureOrBuilder
    public List<? extends ViolationOrBuilder> getViolationsOrBuilderList() {
        return this.violations_;
    }

    @Override // com.google.rpc.PreconditionFailureOrBuilder
    public int getViolationsCount() {
        return this.violations_.size();
    }

    @Override // com.google.rpc.PreconditionFailureOrBuilder
    public Violation getViolations(int i) {
        return this.violations_.get(i);
    }

    @Override // com.google.rpc.PreconditionFailureOrBuilder
    public ViolationOrBuilder getViolationsOrBuilder(int i) {
        return this.violations_.get(i);
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
        for (int i = 0; i < this.violations_.size(); i++) {
            codedOutputStream.writeMessage(1, this.violations_.get(i));
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
        for (int i3 = 0; i3 < this.violations_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.violations_.get(i3));
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
        if (!(obj instanceof PreconditionFailure)) {
            return super.equals(obj);
        }
        PreconditionFailure preconditionFailure = (PreconditionFailure) obj;
        return getViolationsList().equals(preconditionFailure.getViolationsList()) && this.unknownFields.equals(preconditionFailure.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getViolationsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getViolationsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static PreconditionFailure parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static PreconditionFailure parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static PreconditionFailure parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static PreconditionFailure parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(InputStream inputStream) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static PreconditionFailure parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PreconditionFailure parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static PreconditionFailure parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static PreconditionFailure parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PreconditionFailure preconditionFailure) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(preconditionFailure);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PreconditionFailureOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> violationsBuilder_;
        private List<Violation> violations_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.f2064x68e0fceb.ensureFieldAccessorsInitialized(PreconditionFailure.class, Builder.class);
        }

        private Builder() {
            this.violations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.violations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (PreconditionFailure.alwaysUseFieldBuilders) {
                getViolationsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.violations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public PreconditionFailure getDefaultInstanceForType() {
            return PreconditionFailure.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public PreconditionFailure build() {
            PreconditionFailure buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public PreconditionFailure buildPartial() {
            PreconditionFailure preconditionFailure = new PreconditionFailure(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.violations_ = Collections.unmodifiableList(this.violations_);
                    this.bitField0_ &= -2;
                }
                preconditionFailure.violations_ = this.violations_;
            } else {
                preconditionFailure.violations_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return preconditionFailure;
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
            if (message instanceof PreconditionFailure) {
                return mergeFrom((PreconditionFailure) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(PreconditionFailure preconditionFailure) {
            if (preconditionFailure == PreconditionFailure.getDefaultInstance()) {
                return this;
            }
            if (this.violationsBuilder_ == null) {
                if (!preconditionFailure.violations_.isEmpty()) {
                    if (this.violations_.isEmpty()) {
                        this.violations_ = preconditionFailure.violations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureViolationsIsMutable();
                        this.violations_.addAll(preconditionFailure.violations_);
                    }
                    onChanged();
                }
            } else if (!preconditionFailure.violations_.isEmpty()) {
                if (!this.violationsBuilder_.isEmpty()) {
                    this.violationsBuilder_.addAllMessages(preconditionFailure.violations_);
                } else {
                    this.violationsBuilder_.dispose();
                    this.violationsBuilder_ = null;
                    this.violations_ = preconditionFailure.violations_;
                    this.bitField0_ &= -2;
                    this.violationsBuilder_ = PreconditionFailure.alwaysUseFieldBuilders ? getViolationsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(preconditionFailure.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            PreconditionFailure preconditionFailure = null;
            try {
                try {
                    PreconditionFailure preconditionFailure2 = (PreconditionFailure) PreconditionFailure.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (preconditionFailure2 != null) {
                        mergeFrom(preconditionFailure2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    PreconditionFailure preconditionFailure3 = (PreconditionFailure) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        preconditionFailure = preconditionFailure3;
                        if (preconditionFailure != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (preconditionFailure != null) {
                    mergeFrom(preconditionFailure);
                }
                throw th;
            }
        }

        private void ensureViolationsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.violations_ = new ArrayList(this.violations_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.rpc.PreconditionFailureOrBuilder
        public List<Violation> getViolationsList() {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.violations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.rpc.PreconditionFailureOrBuilder
        public int getViolationsCount() {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.violations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.rpc.PreconditionFailureOrBuilder
        public Violation getViolations(int i) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.violations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setViolations(int i, Violation violation) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, violation);
            } else {
                if (violation == null) {
                    throw new NullPointerException();
                }
                ensureViolationsIsMutable();
                this.violations_.set(i, violation);
                onChanged();
            }
            return this;
        }

        public Builder setViolations(int i, Violation.Builder builder) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addViolations(Violation violation) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(violation);
            } else {
                if (violation == null) {
                    throw new NullPointerException();
                }
                ensureViolationsIsMutable();
                this.violations_.add(violation);
                onChanged();
            }
            return this;
        }

        public Builder addViolations(int i, Violation violation) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, violation);
            } else {
                if (violation == null) {
                    throw new NullPointerException();
                }
                ensureViolationsIsMutable();
                this.violations_.add(i, violation);
                onChanged();
            }
            return this;
        }

        public Builder addViolations(Violation.Builder builder) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addViolations(int i, Violation.Builder builder) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllViolations(Iterable<? extends Violation> iterable) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.violations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearViolations() {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.violations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeViolations(int i) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Violation.Builder getViolationsBuilder(int i) {
            return getViolationsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.rpc.PreconditionFailureOrBuilder
        public ViolationOrBuilder getViolationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.violations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.rpc.PreconditionFailureOrBuilder
        public List<? extends ViolationOrBuilder> getViolationsOrBuilderList() {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.violations_);
        }

        public Violation.Builder addViolationsBuilder() {
            return getViolationsFieldBuilder().addBuilder(Violation.getDefaultInstance());
        }

        public Violation.Builder addViolationsBuilder(int i) {
            return getViolationsFieldBuilder().addBuilder(i, Violation.getDefaultInstance());
        }

        public List<Violation.Builder> getViolationsBuilderList() {
            return getViolationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> getViolationsFieldBuilder() {
            if (this.violationsBuilder_ == null) {
                this.violationsBuilder_ = new RepeatedFieldBuilderV3<>(this.violations_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.violations_ = null;
            }
            return this.violationsBuilder_;
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

    public static PreconditionFailure getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PreconditionFailure> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<PreconditionFailure> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public PreconditionFailure getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
