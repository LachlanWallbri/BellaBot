package com.google.api;

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
import com.google.protobuf.MessageOrBuilder;
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
public final class Logging extends GeneratedMessageV3 implements LoggingOrBuilder {
    public static final int CONSUMER_DESTINATIONS_FIELD_NUMBER = 2;
    private static final Logging DEFAULT_INSTANCE = new Logging();
    private static final Parser<Logging> PARSER = new AbstractParser<Logging>() { // from class: com.google.api.Logging.1
        @Override // com.google.protobuf.Parser
        public Logging parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Logging(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PRODUCER_DESTINATIONS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<LoggingDestination> consumerDestinations_;
    private byte memoizedIsInitialized;
    private List<LoggingDestination> producerDestinations_;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface LoggingDestinationOrBuilder extends MessageOrBuilder {
        String getLogs(int i);

        ByteString getLogsBytes(int i);

        int getLogsCount();

        List<String> getLogsList();

        String getMonitoredResource();

        ByteString getMonitoredResourceBytes();
    }

    private Logging(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Logging() {
        this.memoizedIsInitialized = (byte) -1;
        this.producerDestinations_ = Collections.emptyList();
        this.consumerDestinations_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Logging();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Logging(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            if ((i & 1) == 0) {
                                this.producerDestinations_ = new ArrayList();
                                i |= 1;
                            }
                            this.producerDestinations_.add(codedInputStream.readMessage(LoggingDestination.parser(), extensionRegistryLite));
                        } else if (readTag == 18) {
                            if ((i & 2) == 0) {
                                this.consumerDestinations_ = new ArrayList();
                                i |= 2;
                            }
                            this.consumerDestinations_.add(codedInputStream.readMessage(LoggingDestination.parser(), extensionRegistryLite));
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
                if ((i & 1) != 0) {
                    this.producerDestinations_ = Collections.unmodifiableList(this.producerDestinations_);
                }
                if ((i & 2) != 0) {
                    this.consumerDestinations_ = Collections.unmodifiableList(this.consumerDestinations_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoggingProto.internal_static_google_api_Logging_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingProto.internal_static_google_api_Logging_fieldAccessorTable.ensureFieldAccessorsInitialized(Logging.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class LoggingDestination extends GeneratedMessageV3 implements LoggingDestinationOrBuilder {
        public static final int LOGS_FIELD_NUMBER = 1;
        public static final int MONITORED_RESOURCE_FIELD_NUMBER = 3;
        private static final long serialVersionUID = 0;
        private LazyStringList logs_;
        private byte memoizedIsInitialized;
        private volatile Object monitoredResource_;
        private static final LoggingDestination DEFAULT_INSTANCE = new LoggingDestination();
        private static final Parser<LoggingDestination> PARSER = new AbstractParser<LoggingDestination>() { // from class: com.google.api.Logging.LoggingDestination.1
            @Override // com.google.protobuf.Parser
            public LoggingDestination parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new LoggingDestination(codedInputStream, extensionRegistryLite);
            }
        };

        private LoggingDestination(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private LoggingDestination() {
            this.memoizedIsInitialized = (byte) -1;
            this.monitoredResource_ = "";
            this.logs_ = LazyStringArrayList.EMPTY;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new LoggingDestination();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private LoggingDestination(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                        this.logs_ = new LazyStringArrayList();
                                        z2 |= true;
                                    }
                                    this.logs_.add(readStringRequireUtf8);
                                } else if (readTag == 26) {
                                    this.monitoredResource_ = codedInputStream.readStringRequireUtf8();
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
                        this.logs_ = this.logs_.getUnmodifiableView();
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingProto.internal_static_google_api_Logging_LoggingDestination_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingProto.f1324xf0bafe21.ensureFieldAccessorsInitialized(LoggingDestination.class, Builder.class);
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public String getMonitoredResource() {
            Object obj = this.monitoredResource_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.monitoredResource_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public ByteString getMonitoredResourceBytes() {
            Object obj = this.monitoredResource_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.monitoredResource_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public ProtocolStringList getLogsList() {
            return this.logs_;
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public int getLogsCount() {
            return this.logs_.size();
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public String getLogs(int i) {
            return (String) this.logs_.get(i);
        }

        @Override // com.google.api.Logging.LoggingDestinationOrBuilder
        public ByteString getLogsBytes(int i) {
            return this.logs_.getByteString(i);
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
            for (int i = 0; i < this.logs_.size(); i++) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.logs_.getRaw(i));
            }
            if (!getMonitoredResourceBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.monitoredResource_);
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
            for (int i3 = 0; i3 < this.logs_.size(); i3++) {
                i2 += computeStringSizeNoTag(this.logs_.getRaw(i3));
            }
            int size = 0 + i2 + (getLogsList().size() * 1);
            if (!getMonitoredResourceBytes().isEmpty()) {
                size += GeneratedMessageV3.computeStringSize(3, this.monitoredResource_);
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
            if (!(obj instanceof LoggingDestination)) {
                return super.equals(obj);
            }
            LoggingDestination loggingDestination = (LoggingDestination) obj;
            return getMonitoredResource().equals(loggingDestination.getMonitoredResource()) && getLogsList().equals(loggingDestination.getLogsList()) && this.unknownFields.equals(loggingDestination.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 3) * 53) + getMonitoredResource().hashCode();
            if (getLogsCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getLogsList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static LoggingDestination parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static LoggingDestination parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static LoggingDestination parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static LoggingDestination parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(InputStream inputStream) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static LoggingDestination parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LoggingDestination parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static LoggingDestination parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static LoggingDestination parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(LoggingDestination loggingDestination) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(loggingDestination);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LoggingDestinationOrBuilder {
            private int bitField0_;
            private LazyStringList logs_;
            private Object monitoredResource_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return LoggingProto.internal_static_google_api_Logging_LoggingDestination_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return LoggingProto.f1324xf0bafe21.ensureFieldAccessorsInitialized(LoggingDestination.class, Builder.class);
            }

            private Builder() {
                this.monitoredResource_ = "";
                this.logs_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.monitoredResource_ = "";
                this.logs_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = LoggingDestination.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.monitoredResource_ = "";
                this.logs_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return LoggingProto.internal_static_google_api_Logging_LoggingDestination_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public LoggingDestination getDefaultInstanceForType() {
                return LoggingDestination.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LoggingDestination build() {
                LoggingDestination buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public LoggingDestination buildPartial() {
                LoggingDestination loggingDestination = new LoggingDestination(this);
                int i = this.bitField0_;
                loggingDestination.monitoredResource_ = this.monitoredResource_;
                if ((this.bitField0_ & 1) != 0) {
                    this.logs_ = this.logs_.getUnmodifiableView();
                    this.bitField0_ &= -2;
                }
                loggingDestination.logs_ = this.logs_;
                onBuilt();
                return loggingDestination;
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
                if (message instanceof LoggingDestination) {
                    return mergeFrom((LoggingDestination) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(LoggingDestination loggingDestination) {
                if (loggingDestination == LoggingDestination.getDefaultInstance()) {
                    return this;
                }
                if (!loggingDestination.getMonitoredResource().isEmpty()) {
                    this.monitoredResource_ = loggingDestination.monitoredResource_;
                    onChanged();
                }
                if (!loggingDestination.logs_.isEmpty()) {
                    if (this.logs_.isEmpty()) {
                        this.logs_ = loggingDestination.logs_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureLogsIsMutable();
                        this.logs_.addAll(loggingDestination.logs_);
                    }
                    onChanged();
                }
                mergeUnknownFields(loggingDestination.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                LoggingDestination loggingDestination = null;
                try {
                    try {
                        LoggingDestination loggingDestination2 = (LoggingDestination) LoggingDestination.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (loggingDestination2 != null) {
                            mergeFrom(loggingDestination2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        LoggingDestination loggingDestination3 = (LoggingDestination) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            loggingDestination = loggingDestination3;
                            if (loggingDestination != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (loggingDestination != null) {
                        mergeFrom(loggingDestination);
                    }
                    throw th;
                }
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public String getMonitoredResource() {
                Object obj = this.monitoredResource_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.monitoredResource_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public ByteString getMonitoredResourceBytes() {
                Object obj = this.monitoredResource_;
                if (obj instanceof String) {
                    ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.monitoredResource_ = copyFromUtf8;
                    return copyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setMonitoredResource(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.monitoredResource_ = str;
                onChanged();
                return this;
            }

            public Builder clearMonitoredResource() {
                this.monitoredResource_ = LoggingDestination.getDefaultInstance().getMonitoredResource();
                onChanged();
                return this;
            }

            public Builder setMonitoredResourceBytes(ByteString byteString) {
                if (byteString != null) {
                    LoggingDestination.checkByteStringIsUtf8(byteString);
                    this.monitoredResource_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private void ensureLogsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.logs_ = new LazyStringArrayList(this.logs_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public ProtocolStringList getLogsList() {
                return this.logs_.getUnmodifiableView();
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public int getLogsCount() {
                return this.logs_.size();
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public String getLogs(int i) {
                return (String) this.logs_.get(i);
            }

            @Override // com.google.api.Logging.LoggingDestinationOrBuilder
            public ByteString getLogsBytes(int i) {
                return this.logs_.getByteString(i);
            }

            public Builder setLogs(int i, String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureLogsIsMutable();
                this.logs_.set(i, str);
                onChanged();
                return this;
            }

            public Builder addLogs(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureLogsIsMutable();
                this.logs_.add(str);
                onChanged();
                return this;
            }

            public Builder addAllLogs(Iterable<String> iterable) {
                ensureLogsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.logs_);
                onChanged();
                return this;
            }

            public Builder clearLogs() {
                this.logs_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                onChanged();
                return this;
            }

            public Builder addLogsBytes(ByteString byteString) {
                if (byteString != null) {
                    LoggingDestination.checkByteStringIsUtf8(byteString);
                    ensureLogsIsMutable();
                    this.logs_.add(byteString);
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

        public static LoggingDestination getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LoggingDestination> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<LoggingDestination> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public LoggingDestination getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    @Override // com.google.api.LoggingOrBuilder
    public List<LoggingDestination> getProducerDestinationsList() {
        return this.producerDestinations_;
    }

    @Override // com.google.api.LoggingOrBuilder
    public List<? extends LoggingDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
        return this.producerDestinations_;
    }

    @Override // com.google.api.LoggingOrBuilder
    public int getProducerDestinationsCount() {
        return this.producerDestinations_.size();
    }

    @Override // com.google.api.LoggingOrBuilder
    public LoggingDestination getProducerDestinations(int i) {
        return this.producerDestinations_.get(i);
    }

    @Override // com.google.api.LoggingOrBuilder
    public LoggingDestinationOrBuilder getProducerDestinationsOrBuilder(int i) {
        return this.producerDestinations_.get(i);
    }

    @Override // com.google.api.LoggingOrBuilder
    public List<LoggingDestination> getConsumerDestinationsList() {
        return this.consumerDestinations_;
    }

    @Override // com.google.api.LoggingOrBuilder
    public List<? extends LoggingDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
        return this.consumerDestinations_;
    }

    @Override // com.google.api.LoggingOrBuilder
    public int getConsumerDestinationsCount() {
        return this.consumerDestinations_.size();
    }

    @Override // com.google.api.LoggingOrBuilder
    public LoggingDestination getConsumerDestinations(int i) {
        return this.consumerDestinations_.get(i);
    }

    @Override // com.google.api.LoggingOrBuilder
    public LoggingDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
        return this.consumerDestinations_.get(i);
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
        for (int i = 0; i < this.producerDestinations_.size(); i++) {
            codedOutputStream.writeMessage(1, this.producerDestinations_.get(i));
        }
        for (int i2 = 0; i2 < this.consumerDestinations_.size(); i2++) {
            codedOutputStream.writeMessage(2, this.consumerDestinations_.get(i2));
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
        for (int i3 = 0; i3 < this.producerDestinations_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.producerDestinations_.get(i3));
        }
        for (int i4 = 0; i4 < this.consumerDestinations_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(2, this.consumerDestinations_.get(i4));
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
        if (!(obj instanceof Logging)) {
            return super.equals(obj);
        }
        Logging logging = (Logging) obj;
        return getProducerDestinationsList().equals(logging.getProducerDestinationsList()) && getConsumerDestinationsList().equals(logging.getConsumerDestinationsList()) && this.unknownFields.equals(logging.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getProducerDestinationsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getProducerDestinationsList().hashCode();
        }
        if (getConsumerDestinationsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getConsumerDestinationsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Logging parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Logging parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Logging parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Logging parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Logging parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Logging parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Logging parseFrom(InputStream inputStream) throws IOException {
        return (Logging) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Logging parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Logging) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Logging parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Logging) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Logging parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Logging) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Logging parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Logging) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Logging parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Logging) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Logging logging) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(logging);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LoggingOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> consumerDestinationsBuilder_;
        private List<LoggingDestination> consumerDestinations_;
        private RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> producerDestinationsBuilder_;
        private List<LoggingDestination> producerDestinations_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoggingProto.internal_static_google_api_Logging_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingProto.internal_static_google_api_Logging_fieldAccessorTable.ensureFieldAccessorsInitialized(Logging.class, Builder.class);
        }

        private Builder() {
            this.producerDestinations_ = Collections.emptyList();
            this.consumerDestinations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.producerDestinations_ = Collections.emptyList();
            this.consumerDestinations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Logging.alwaysUseFieldBuilders) {
                getProducerDestinationsFieldBuilder();
                getConsumerDestinationsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.producerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV32 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.consumerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return LoggingProto.internal_static_google_api_Logging_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Logging getDefaultInstanceForType() {
            return Logging.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Logging build() {
            Logging buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Logging buildPartial() {
            Logging logging = new Logging(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.producerDestinations_ = Collections.unmodifiableList(this.producerDestinations_);
                    this.bitField0_ &= -2;
                }
                logging.producerDestinations_ = this.producerDestinations_;
            } else {
                logging.producerDestinations_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV32 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV32 != null) {
                logging.consumerDestinations_ = repeatedFieldBuilderV32.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.consumerDestinations_ = Collections.unmodifiableList(this.consumerDestinations_);
                    this.bitField0_ &= -3;
                }
                logging.consumerDestinations_ = this.consumerDestinations_;
            }
            onBuilt();
            return logging;
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
            if (message instanceof Logging) {
                return mergeFrom((Logging) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Logging logging) {
            if (logging == Logging.getDefaultInstance()) {
                return this;
            }
            if (this.producerDestinationsBuilder_ == null) {
                if (!logging.producerDestinations_.isEmpty()) {
                    if (this.producerDestinations_.isEmpty()) {
                        this.producerDestinations_ = logging.producerDestinations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureProducerDestinationsIsMutable();
                        this.producerDestinations_.addAll(logging.producerDestinations_);
                    }
                    onChanged();
                }
            } else if (!logging.producerDestinations_.isEmpty()) {
                if (!this.producerDestinationsBuilder_.isEmpty()) {
                    this.producerDestinationsBuilder_.addAllMessages(logging.producerDestinations_);
                } else {
                    this.producerDestinationsBuilder_.dispose();
                    this.producerDestinationsBuilder_ = null;
                    this.producerDestinations_ = logging.producerDestinations_;
                    this.bitField0_ &= -2;
                    this.producerDestinationsBuilder_ = Logging.alwaysUseFieldBuilders ? getProducerDestinationsFieldBuilder() : null;
                }
            }
            if (this.consumerDestinationsBuilder_ == null) {
                if (!logging.consumerDestinations_.isEmpty()) {
                    if (this.consumerDestinations_.isEmpty()) {
                        this.consumerDestinations_ = logging.consumerDestinations_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureConsumerDestinationsIsMutable();
                        this.consumerDestinations_.addAll(logging.consumerDestinations_);
                    }
                    onChanged();
                }
            } else if (!logging.consumerDestinations_.isEmpty()) {
                if (!this.consumerDestinationsBuilder_.isEmpty()) {
                    this.consumerDestinationsBuilder_.addAllMessages(logging.consumerDestinations_);
                } else {
                    this.consumerDestinationsBuilder_.dispose();
                    this.consumerDestinationsBuilder_ = null;
                    this.consumerDestinations_ = logging.consumerDestinations_;
                    this.bitField0_ &= -3;
                    this.consumerDestinationsBuilder_ = Logging.alwaysUseFieldBuilders ? getConsumerDestinationsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(logging.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Logging logging = null;
            try {
                try {
                    Logging logging2 = (Logging) Logging.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (logging2 != null) {
                        mergeFrom(logging2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Logging logging3 = (Logging) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        logging = logging3;
                        if (logging != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (logging != null) {
                    mergeFrom(logging);
                }
                throw th;
            }
        }

        private void ensureProducerDestinationsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.producerDestinations_ = new ArrayList(this.producerDestinations_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.LoggingOrBuilder
        public List<LoggingDestination> getProducerDestinationsList() {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.producerDestinations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.LoggingOrBuilder
        public int getProducerDestinationsCount() {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.producerDestinations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.LoggingOrBuilder
        public LoggingDestination getProducerDestinations(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.producerDestinations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setProducerDestinations(int i, LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, loggingDestination);
            } else {
                if (loggingDestination == null) {
                    throw new NullPointerException();
                }
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.set(i, loggingDestination);
                onChanged();
            }
            return this;
        }

        public Builder setProducerDestinations(int i, LoggingDestination.Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addProducerDestinations(LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(loggingDestination);
            } else {
                if (loggingDestination == null) {
                    throw new NullPointerException();
                }
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(loggingDestination);
                onChanged();
            }
            return this;
        }

        public Builder addProducerDestinations(int i, LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, loggingDestination);
            } else {
                if (loggingDestination == null) {
                    throw new NullPointerException();
                }
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(i, loggingDestination);
                onChanged();
            }
            return this;
        }

        public Builder addProducerDestinations(LoggingDestination.Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addProducerDestinations(int i, LoggingDestination.Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllProducerDestinations(Iterable<? extends LoggingDestination> iterable) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.producerDestinations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearProducerDestinations() {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.producerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeProducerDestinations(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public LoggingDestination.Builder getProducerDestinationsBuilder(int i) {
            return getProducerDestinationsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.LoggingOrBuilder
        public LoggingDestinationOrBuilder getProducerDestinationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.producerDestinations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.LoggingOrBuilder
        public List<? extends LoggingDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.producerDestinations_);
        }

        public LoggingDestination.Builder addProducerDestinationsBuilder() {
            return getProducerDestinationsFieldBuilder().addBuilder(LoggingDestination.getDefaultInstance());
        }

        public LoggingDestination.Builder addProducerDestinationsBuilder(int i) {
            return getProducerDestinationsFieldBuilder().addBuilder(i, LoggingDestination.getDefaultInstance());
        }

        public List<LoggingDestination.Builder> getProducerDestinationsBuilderList() {
            return getProducerDestinationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> getProducerDestinationsFieldBuilder() {
            if (this.producerDestinationsBuilder_ == null) {
                this.producerDestinationsBuilder_ = new RepeatedFieldBuilderV3<>(this.producerDestinations_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.producerDestinations_ = null;
            }
            return this.producerDestinationsBuilder_;
        }

        private void ensureConsumerDestinationsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.consumerDestinations_ = new ArrayList(this.consumerDestinations_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.api.LoggingOrBuilder
        public List<LoggingDestination> getConsumerDestinationsList() {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.consumerDestinations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.LoggingOrBuilder
        public int getConsumerDestinationsCount() {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.consumerDestinations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.LoggingOrBuilder
        public LoggingDestination getConsumerDestinations(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.consumerDestinations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setConsumerDestinations(int i, LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, loggingDestination);
            } else {
                if (loggingDestination == null) {
                    throw new NullPointerException();
                }
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.set(i, loggingDestination);
                onChanged();
            }
            return this;
        }

        public Builder setConsumerDestinations(int i, LoggingDestination.Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addConsumerDestinations(LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(loggingDestination);
            } else {
                if (loggingDestination == null) {
                    throw new NullPointerException();
                }
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(loggingDestination);
                onChanged();
            }
            return this;
        }

        public Builder addConsumerDestinations(int i, LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, loggingDestination);
            } else {
                if (loggingDestination == null) {
                    throw new NullPointerException();
                }
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(i, loggingDestination);
                onChanged();
            }
            return this;
        }

        public Builder addConsumerDestinations(LoggingDestination.Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addConsumerDestinations(int i, LoggingDestination.Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllConsumerDestinations(Iterable<? extends LoggingDestination> iterable) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.consumerDestinations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearConsumerDestinations() {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.consumerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeConsumerDestinations(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public LoggingDestination.Builder getConsumerDestinationsBuilder(int i) {
            return getConsumerDestinationsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.LoggingOrBuilder
        public LoggingDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.consumerDestinations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.LoggingOrBuilder
        public List<? extends LoggingDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
            RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.consumerDestinations_);
        }

        public LoggingDestination.Builder addConsumerDestinationsBuilder() {
            return getConsumerDestinationsFieldBuilder().addBuilder(LoggingDestination.getDefaultInstance());
        }

        public LoggingDestination.Builder addConsumerDestinationsBuilder(int i) {
            return getConsumerDestinationsFieldBuilder().addBuilder(i, LoggingDestination.getDefaultInstance());
        }

        public List<LoggingDestination.Builder> getConsumerDestinationsBuilderList() {
            return getConsumerDestinationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LoggingDestination, LoggingDestination.Builder, LoggingDestinationOrBuilder> getConsumerDestinationsFieldBuilder() {
            if (this.consumerDestinationsBuilder_ == null) {
                this.consumerDestinationsBuilder_ = new RepeatedFieldBuilderV3<>(this.consumerDestinations_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.consumerDestinations_ = null;
            }
            return this.consumerDestinationsBuilder_;
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

    public static Logging getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Logging> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Logging> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Logging getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
