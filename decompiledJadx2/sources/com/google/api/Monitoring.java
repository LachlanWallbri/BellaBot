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
public final class Monitoring extends GeneratedMessageV3 implements MonitoringOrBuilder {
    public static final int CONSUMER_DESTINATIONS_FIELD_NUMBER = 2;
    private static final Monitoring DEFAULT_INSTANCE = new Monitoring();
    private static final Parser<Monitoring> PARSER = new AbstractParser<Monitoring>() { // from class: com.google.api.Monitoring.1
        @Override // com.google.protobuf.Parser
        public Monitoring parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Monitoring(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PRODUCER_DESTINATIONS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<MonitoringDestination> consumerDestinations_;
    private byte memoizedIsInitialized;
    private List<MonitoringDestination> producerDestinations_;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface MonitoringDestinationOrBuilder extends MessageOrBuilder {
        String getMetrics(int i);

        ByteString getMetricsBytes(int i);

        int getMetricsCount();

        List<String> getMetricsList();

        String getMonitoredResource();

        ByteString getMonitoredResourceBytes();
    }

    private Monitoring(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Monitoring() {
        this.memoizedIsInitialized = (byte) -1;
        this.producerDestinations_ = Collections.emptyList();
        this.consumerDestinations_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Monitoring();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Monitoring(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.producerDestinations_.add(codedInputStream.readMessage(MonitoringDestination.parser(), extensionRegistryLite));
                        } else if (readTag == 18) {
                            if ((i & 2) == 0) {
                                this.consumerDestinations_ = new ArrayList();
                                i |= 2;
                            }
                            this.consumerDestinations_.add(codedInputStream.readMessage(MonitoringDestination.parser(), extensionRegistryLite));
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
        return MonitoringProto.internal_static_google_api_Monitoring_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MonitoringProto.internal_static_google_api_Monitoring_fieldAccessorTable.ensureFieldAccessorsInitialized(Monitoring.class, Builder.class);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static final class MonitoringDestination extends GeneratedMessageV3 implements MonitoringDestinationOrBuilder {
        public static final int METRICS_FIELD_NUMBER = 2;
        public static final int MONITORED_RESOURCE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private LazyStringList metrics_;
        private volatile Object monitoredResource_;
        private static final MonitoringDestination DEFAULT_INSTANCE = new MonitoringDestination();
        private static final Parser<MonitoringDestination> PARSER = new AbstractParser<MonitoringDestination>() { // from class: com.google.api.Monitoring.MonitoringDestination.1
            @Override // com.google.protobuf.Parser
            public MonitoringDestination parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MonitoringDestination(codedInputStream, extensionRegistryLite);
            }
        };

        private MonitoringDestination(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private MonitoringDestination() {
            this.memoizedIsInitialized = (byte) -1;
            this.monitoredResource_ = "";
            this.metrics_ = LazyStringArrayList.EMPTY;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new MonitoringDestination();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MonitoringDestination(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.monitoredResource_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    if (!(z2 & true)) {
                                        this.metrics_ = new LazyStringArrayList();
                                        z2 |= true;
                                    }
                                    this.metrics_.add(readStringRequireUtf8);
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
                        this.metrics_ = this.metrics_.getUnmodifiableView();
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MonitoringProto.f1334xc7be603;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoringProto.f1335xbd725981.ensureFieldAccessorsInitialized(MonitoringDestination.class, Builder.class);
        }

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public String getMonitoredResource() {
            Object obj = this.monitoredResource_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.monitoredResource_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public ByteString getMonitoredResourceBytes() {
            Object obj = this.monitoredResource_;
            if (obj instanceof String) {
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.monitoredResource_ = copyFromUtf8;
                return copyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public ProtocolStringList getMetricsList() {
            return this.metrics_;
        }

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public int getMetricsCount() {
            return this.metrics_.size();
        }

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public String getMetrics(int i) {
            return (String) this.metrics_.get(i);
        }

        @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
        public ByteString getMetricsBytes(int i) {
            return this.metrics_.getByteString(i);
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
            if (!getMonitoredResourceBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.monitoredResource_);
            }
            for (int i = 0; i < this.metrics_.size(); i++) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.metrics_.getRaw(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = !getMonitoredResourceBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.monitoredResource_) + 0 : 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.metrics_.size(); i3++) {
                i2 += computeStringSizeNoTag(this.metrics_.getRaw(i3));
            }
            int size = computeStringSize + i2 + (getMetricsList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MonitoringDestination)) {
                return super.equals(obj);
            }
            MonitoringDestination monitoringDestination = (MonitoringDestination) obj;
            return getMonitoredResource().equals(monitoringDestination.getMonitoredResource()) && getMetricsList().equals(monitoringDestination.getMetricsList()) && this.unknownFields.equals(monitoringDestination.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMonitoredResource().hashCode();
            if (getMetricsCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getMetricsList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static MonitoringDestination parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static MonitoringDestination parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static MonitoringDestination parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static MonitoringDestination parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(InputStream inputStream) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static MonitoringDestination parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MonitoringDestination parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static MonitoringDestination parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static MonitoringDestination parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MonitoringDestination monitoringDestination) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(monitoringDestination);
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
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MonitoringDestinationOrBuilder {
            private int bitField0_;
            private LazyStringList metrics_;
            private Object monitoredResource_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MonitoringProto.f1334xc7be603;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MonitoringProto.f1335xbd725981.ensureFieldAccessorsInitialized(MonitoringDestination.class, Builder.class);
            }

            private Builder() {
                this.monitoredResource_ = "";
                this.metrics_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.monitoredResource_ = "";
                this.metrics_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = MonitoringDestination.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.monitoredResource_ = "";
                this.metrics_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return MonitoringProto.f1334xc7be603;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MonitoringDestination getDefaultInstanceForType() {
                return MonitoringDestination.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MonitoringDestination build() {
                MonitoringDestination buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException((Message) buildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MonitoringDestination buildPartial() {
                MonitoringDestination monitoringDestination = new MonitoringDestination(this);
                int i = this.bitField0_;
                monitoringDestination.monitoredResource_ = this.monitoredResource_;
                if ((this.bitField0_ & 1) != 0) {
                    this.metrics_ = this.metrics_.getUnmodifiableView();
                    this.bitField0_ &= -2;
                }
                monitoringDestination.metrics_ = this.metrics_;
                onBuilt();
                return monitoringDestination;
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
                if (message instanceof MonitoringDestination) {
                    return mergeFrom((MonitoringDestination) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(MonitoringDestination monitoringDestination) {
                if (monitoringDestination == MonitoringDestination.getDefaultInstance()) {
                    return this;
                }
                if (!monitoringDestination.getMonitoredResource().isEmpty()) {
                    this.monitoredResource_ = monitoringDestination.monitoredResource_;
                    onChanged();
                }
                if (!monitoringDestination.metrics_.isEmpty()) {
                    if (this.metrics_.isEmpty()) {
                        this.metrics_ = monitoringDestination.metrics_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureMetricsIsMutable();
                        this.metrics_.addAll(monitoringDestination.metrics_);
                    }
                    onChanged();
                }
                mergeUnknownFields(monitoringDestination.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                MonitoringDestination monitoringDestination = null;
                try {
                    try {
                        MonitoringDestination monitoringDestination2 = (MonitoringDestination) MonitoringDestination.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (monitoringDestination2 != null) {
                            mergeFrom(monitoringDestination2);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        MonitoringDestination monitoringDestination3 = (MonitoringDestination) e.getUnfinishedMessage();
                        try {
                            throw e.unwrapIOException();
                        } catch (Throwable th) {
                            th = th;
                            monitoringDestination = monitoringDestination3;
                            if (monitoringDestination != null) {
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (monitoringDestination != null) {
                        mergeFrom(monitoringDestination);
                    }
                    throw th;
                }
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
            public String getMonitoredResource() {
                Object obj = this.monitoredResource_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.monitoredResource_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
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
                this.monitoredResource_ = MonitoringDestination.getDefaultInstance().getMonitoredResource();
                onChanged();
                return this;
            }

            public Builder setMonitoredResourceBytes(ByteString byteString) {
                if (byteString != null) {
                    MonitoringDestination.checkByteStringIsUtf8(byteString);
                    this.monitoredResource_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private void ensureMetricsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.metrics_ = new LazyStringArrayList(this.metrics_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
            public ProtocolStringList getMetricsList() {
                return this.metrics_.getUnmodifiableView();
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
            public int getMetricsCount() {
                return this.metrics_.size();
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
            public String getMetrics(int i) {
                return (String) this.metrics_.get(i);
            }

            @Override // com.google.api.Monitoring.MonitoringDestinationOrBuilder
            public ByteString getMetricsBytes(int i) {
                return this.metrics_.getByteString(i);
            }

            public Builder setMetrics(int i, String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureMetricsIsMutable();
                this.metrics_.set(i, str);
                onChanged();
                return this;
            }

            public Builder addMetrics(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureMetricsIsMutable();
                this.metrics_.add(str);
                onChanged();
                return this;
            }

            public Builder addAllMetrics(Iterable<String> iterable) {
                ensureMetricsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.metrics_);
                onChanged();
                return this;
            }

            public Builder clearMetrics() {
                this.metrics_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -2;
                onChanged();
                return this;
            }

            public Builder addMetricsBytes(ByteString byteString) {
                if (byteString != null) {
                    MonitoringDestination.checkByteStringIsUtf8(byteString);
                    ensureMetricsIsMutable();
                    this.metrics_.add(byteString);
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

        public static MonitoringDestination getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MonitoringDestination> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<MonitoringDestination> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MonitoringDestination getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    @Override // com.google.api.MonitoringOrBuilder
    public List<MonitoringDestination> getProducerDestinationsList() {
        return this.producerDestinations_;
    }

    @Override // com.google.api.MonitoringOrBuilder
    public List<? extends MonitoringDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
        return this.producerDestinations_;
    }

    @Override // com.google.api.MonitoringOrBuilder
    public int getProducerDestinationsCount() {
        return this.producerDestinations_.size();
    }

    @Override // com.google.api.MonitoringOrBuilder
    public MonitoringDestination getProducerDestinations(int i) {
        return this.producerDestinations_.get(i);
    }

    @Override // com.google.api.MonitoringOrBuilder
    public MonitoringDestinationOrBuilder getProducerDestinationsOrBuilder(int i) {
        return this.producerDestinations_.get(i);
    }

    @Override // com.google.api.MonitoringOrBuilder
    public List<MonitoringDestination> getConsumerDestinationsList() {
        return this.consumerDestinations_;
    }

    @Override // com.google.api.MonitoringOrBuilder
    public List<? extends MonitoringDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
        return this.consumerDestinations_;
    }

    @Override // com.google.api.MonitoringOrBuilder
    public int getConsumerDestinationsCount() {
        return this.consumerDestinations_.size();
    }

    @Override // com.google.api.MonitoringOrBuilder
    public MonitoringDestination getConsumerDestinations(int i) {
        return this.consumerDestinations_.get(i);
    }

    @Override // com.google.api.MonitoringOrBuilder
    public MonitoringDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
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
        if (!(obj instanceof Monitoring)) {
            return super.equals(obj);
        }
        Monitoring monitoring = (Monitoring) obj;
        return getProducerDestinationsList().equals(monitoring.getProducerDestinationsList()) && getConsumerDestinationsList().equals(monitoring.getConsumerDestinationsList()) && this.unknownFields.equals(monitoring.unknownFields);
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

    public static Monitoring parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Monitoring parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Monitoring parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Monitoring parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Monitoring parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Monitoring parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Monitoring parseFrom(InputStream inputStream) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Monitoring parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Monitoring parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Monitoring parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Monitoring parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Monitoring parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Monitoring monitoring) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(monitoring);
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
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MonitoringOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> consumerDestinationsBuilder_;
        private List<MonitoringDestination> consumerDestinations_;
        private RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> producerDestinationsBuilder_;
        private List<MonitoringDestination> producerDestinations_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MonitoringProto.internal_static_google_api_Monitoring_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoringProto.internal_static_google_api_Monitoring_fieldAccessorTable.ensureFieldAccessorsInitialized(Monitoring.class, Builder.class);
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
            if (Monitoring.alwaysUseFieldBuilders) {
                getProducerDestinationsFieldBuilder();
                getConsumerDestinationsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.producerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV32 = this.consumerDestinationsBuilder_;
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
            return MonitoringProto.internal_static_google_api_Monitoring_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Monitoring getDefaultInstanceForType() {
            return Monitoring.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Monitoring build() {
            Monitoring buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Monitoring buildPartial() {
            Monitoring monitoring = new Monitoring(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.producerDestinations_ = Collections.unmodifiableList(this.producerDestinations_);
                    this.bitField0_ &= -2;
                }
                monitoring.producerDestinations_ = this.producerDestinations_;
            } else {
                monitoring.producerDestinations_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV32 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV32 != null) {
                monitoring.consumerDestinations_ = repeatedFieldBuilderV32.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.consumerDestinations_ = Collections.unmodifiableList(this.consumerDestinations_);
                    this.bitField0_ &= -3;
                }
                monitoring.consumerDestinations_ = this.consumerDestinations_;
            }
            onBuilt();
            return monitoring;
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
            if (message instanceof Monitoring) {
                return mergeFrom((Monitoring) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Monitoring monitoring) {
            if (monitoring == Monitoring.getDefaultInstance()) {
                return this;
            }
            if (this.producerDestinationsBuilder_ == null) {
                if (!monitoring.producerDestinations_.isEmpty()) {
                    if (this.producerDestinations_.isEmpty()) {
                        this.producerDestinations_ = monitoring.producerDestinations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureProducerDestinationsIsMutable();
                        this.producerDestinations_.addAll(monitoring.producerDestinations_);
                    }
                    onChanged();
                }
            } else if (!monitoring.producerDestinations_.isEmpty()) {
                if (!this.producerDestinationsBuilder_.isEmpty()) {
                    this.producerDestinationsBuilder_.addAllMessages(monitoring.producerDestinations_);
                } else {
                    this.producerDestinationsBuilder_.dispose();
                    this.producerDestinationsBuilder_ = null;
                    this.producerDestinations_ = monitoring.producerDestinations_;
                    this.bitField0_ &= -2;
                    this.producerDestinationsBuilder_ = Monitoring.alwaysUseFieldBuilders ? getProducerDestinationsFieldBuilder() : null;
                }
            }
            if (this.consumerDestinationsBuilder_ == null) {
                if (!monitoring.consumerDestinations_.isEmpty()) {
                    if (this.consumerDestinations_.isEmpty()) {
                        this.consumerDestinations_ = monitoring.consumerDestinations_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureConsumerDestinationsIsMutable();
                        this.consumerDestinations_.addAll(monitoring.consumerDestinations_);
                    }
                    onChanged();
                }
            } else if (!monitoring.consumerDestinations_.isEmpty()) {
                if (!this.consumerDestinationsBuilder_.isEmpty()) {
                    this.consumerDestinationsBuilder_.addAllMessages(monitoring.consumerDestinations_);
                } else {
                    this.consumerDestinationsBuilder_.dispose();
                    this.consumerDestinationsBuilder_ = null;
                    this.consumerDestinations_ = monitoring.consumerDestinations_;
                    this.bitField0_ &= -3;
                    this.consumerDestinationsBuilder_ = Monitoring.alwaysUseFieldBuilders ? getConsumerDestinationsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(monitoring.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            Monitoring monitoring = null;
            try {
                try {
                    Monitoring monitoring2 = (Monitoring) Monitoring.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (monitoring2 != null) {
                        mergeFrom(monitoring2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    Monitoring monitoring3 = (Monitoring) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        monitoring = monitoring3;
                        if (monitoring != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (monitoring != null) {
                    mergeFrom(monitoring);
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

        @Override // com.google.api.MonitoringOrBuilder
        public List<MonitoringDestination> getProducerDestinationsList() {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.producerDestinations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.MonitoringOrBuilder
        public int getProducerDestinationsCount() {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.producerDestinations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.MonitoringOrBuilder
        public MonitoringDestination getProducerDestinations(int i) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.producerDestinations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setProducerDestinations(int i, MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, monitoringDestination);
            } else {
                if (monitoringDestination == null) {
                    throw new NullPointerException();
                }
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.set(i, monitoringDestination);
                onChanged();
            }
            return this;
        }

        public Builder setProducerDestinations(int i, MonitoringDestination.Builder builder) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addProducerDestinations(MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(monitoringDestination);
            } else {
                if (monitoringDestination == null) {
                    throw new NullPointerException();
                }
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(monitoringDestination);
                onChanged();
            }
            return this;
        }

        public Builder addProducerDestinations(int i, MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, monitoringDestination);
            } else {
                if (monitoringDestination == null) {
                    throw new NullPointerException();
                }
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(i, monitoringDestination);
                onChanged();
            }
            return this;
        }

        public Builder addProducerDestinations(MonitoringDestination.Builder builder) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addProducerDestinations(int i, MonitoringDestination.Builder builder) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllProducerDestinations(Iterable<? extends MonitoringDestination> iterable) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
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
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
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
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public MonitoringDestination.Builder getProducerDestinationsBuilder(int i) {
            return getProducerDestinationsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.MonitoringOrBuilder
        public MonitoringDestinationOrBuilder getProducerDestinationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.producerDestinations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.MonitoringOrBuilder
        public List<? extends MonitoringDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.producerDestinations_);
        }

        public MonitoringDestination.Builder addProducerDestinationsBuilder() {
            return getProducerDestinationsFieldBuilder().addBuilder(MonitoringDestination.getDefaultInstance());
        }

        public MonitoringDestination.Builder addProducerDestinationsBuilder(int i) {
            return getProducerDestinationsFieldBuilder().addBuilder(i, MonitoringDestination.getDefaultInstance());
        }

        public List<MonitoringDestination.Builder> getProducerDestinationsBuilderList() {
            return getProducerDestinationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> getProducerDestinationsFieldBuilder() {
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

        @Override // com.google.api.MonitoringOrBuilder
        public List<MonitoringDestination> getConsumerDestinationsList() {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.consumerDestinations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.MonitoringOrBuilder
        public int getConsumerDestinationsCount() {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.consumerDestinations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.MonitoringOrBuilder
        public MonitoringDestination getConsumerDestinations(int i) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.consumerDestinations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setConsumerDestinations(int i, MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, monitoringDestination);
            } else {
                if (monitoringDestination == null) {
                    throw new NullPointerException();
                }
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.set(i, monitoringDestination);
                onChanged();
            }
            return this;
        }

        public Builder setConsumerDestinations(int i, MonitoringDestination.Builder builder) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addConsumerDestinations(MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(monitoringDestination);
            } else {
                if (monitoringDestination == null) {
                    throw new NullPointerException();
                }
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(monitoringDestination);
                onChanged();
            }
            return this;
        }

        public Builder addConsumerDestinations(int i, MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, monitoringDestination);
            } else {
                if (monitoringDestination == null) {
                    throw new NullPointerException();
                }
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(i, monitoringDestination);
                onChanged();
            }
            return this;
        }

        public Builder addConsumerDestinations(MonitoringDestination.Builder builder) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addConsumerDestinations(int i, MonitoringDestination.Builder builder) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllConsumerDestinations(Iterable<? extends MonitoringDestination> iterable) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
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
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
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
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public MonitoringDestination.Builder getConsumerDestinationsBuilder(int i) {
            return getConsumerDestinationsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.MonitoringOrBuilder
        public MonitoringDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.consumerDestinations_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.MonitoringOrBuilder
        public List<? extends MonitoringDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
            RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.consumerDestinations_);
        }

        public MonitoringDestination.Builder addConsumerDestinationsBuilder() {
            return getConsumerDestinationsFieldBuilder().addBuilder(MonitoringDestination.getDefaultInstance());
        }

        public MonitoringDestination.Builder addConsumerDestinationsBuilder(int i) {
            return getConsumerDestinationsFieldBuilder().addBuilder(i, MonitoringDestination.getDefaultInstance());
        }

        public List<MonitoringDestination.Builder> getConsumerDestinationsBuilderList() {
            return getConsumerDestinationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MonitoringDestination, MonitoringDestination.Builder, MonitoringDestinationOrBuilder> getConsumerDestinationsFieldBuilder() {
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

    public static Monitoring getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Monitoring> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Monitoring> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Monitoring getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
