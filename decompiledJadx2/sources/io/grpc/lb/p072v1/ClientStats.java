package io.grpc.lb.p072v1;

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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.lb.p072v1.ClientStatsPerToken;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ClientStats extends GeneratedMessageV3 implements ClientStatsOrBuilder {
    public static final int CALLS_FINISHED_WITH_DROP_FIELD_NUMBER = 8;
    public static final int NUM_CALLS_FINISHED_FIELD_NUMBER = 3;
    public static final int NUM_CALLS_FINISHED_KNOWN_RECEIVED_FIELD_NUMBER = 7;
    public static final int NUM_CALLS_FINISHED_WITH_CLIENT_FAILED_TO_SEND_FIELD_NUMBER = 6;
    public static final int NUM_CALLS_STARTED_FIELD_NUMBER = 2;
    public static final int TIMESTAMP_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<ClientStatsPerToken> callsFinishedWithDrop_;
    private byte memoizedIsInitialized;
    private long numCallsFinishedKnownReceived_;
    private long numCallsFinishedWithClientFailedToSend_;
    private long numCallsFinished_;
    private long numCallsStarted_;
    private Timestamp timestamp_;
    private static final ClientStats DEFAULT_INSTANCE = new ClientStats();
    private static final Parser<ClientStats> PARSER = new AbstractParser<ClientStats>() { // from class: io.grpc.lb.v1.ClientStats.1
        @Override // com.google.protobuf.Parser
        public ClientStats parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ClientStats(codedInputStream, extensionRegistryLite);
        }
    };

    private ClientStats(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ClientStats() {
        this.memoizedIsInitialized = (byte) -1;
        this.callsFinishedWithDrop_ = Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ClientStats();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ClientStats(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                Timestamp.Builder builder = this.timestamp_ != null ? this.timestamp_.toBuilder() : null;
                                this.timestamp_ = (Timestamp) codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.timestamp_);
                                    this.timestamp_ = builder.buildPartial();
                                }
                            } else if (readTag == 16) {
                                this.numCallsStarted_ = codedInputStream.readInt64();
                            } else if (readTag == 24) {
                                this.numCallsFinished_ = codedInputStream.readInt64();
                            } else if (readTag == 48) {
                                this.numCallsFinishedWithClientFailedToSend_ = codedInputStream.readInt64();
                            } else if (readTag == 56) {
                                this.numCallsFinishedKnownReceived_ = codedInputStream.readInt64();
                            } else if (readTag == 66) {
                                if (!(z2 & true)) {
                                    this.callsFinishedWithDrop_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.callsFinishedWithDrop_.add(codedInputStream.readMessage(ClientStatsPerToken.parser(), extensionRegistryLite));
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
                    this.callsFinishedWithDrop_ = Collections.unmodifiableList(this.callsFinishedWithDrop_);
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_ClientStats_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LoadBalancerProto.internal_static_grpc_lb_v1_ClientStats_fieldAccessorTable.ensureFieldAccessorsInitialized(ClientStats.class, Builder.class);
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public boolean hasTimestamp() {
        return this.timestamp_ != null;
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public Timestamp getTimestamp() {
        Timestamp timestamp = this.timestamp_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public TimestampOrBuilder getTimestampOrBuilder() {
        return getTimestamp();
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public long getNumCallsStarted() {
        return this.numCallsStarted_;
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public long getNumCallsFinished() {
        return this.numCallsFinished_;
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public long getNumCallsFinishedWithClientFailedToSend() {
        return this.numCallsFinishedWithClientFailedToSend_;
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public long getNumCallsFinishedKnownReceived() {
        return this.numCallsFinishedKnownReceived_;
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public List<ClientStatsPerToken> getCallsFinishedWithDropList() {
        return this.callsFinishedWithDrop_;
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public List<? extends ClientStatsPerTokenOrBuilder> getCallsFinishedWithDropOrBuilderList() {
        return this.callsFinishedWithDrop_;
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public int getCallsFinishedWithDropCount() {
        return this.callsFinishedWithDrop_.size();
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public ClientStatsPerToken getCallsFinishedWithDrop(int i) {
        return this.callsFinishedWithDrop_.get(i);
    }

    @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
    public ClientStatsPerTokenOrBuilder getCallsFinishedWithDropOrBuilder(int i) {
        return this.callsFinishedWithDrop_.get(i);
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
        if (this.timestamp_ != null) {
            codedOutputStream.writeMessage(1, getTimestamp());
        }
        long j = this.numCallsStarted_;
        if (j != 0) {
            codedOutputStream.writeInt64(2, j);
        }
        long j2 = this.numCallsFinished_;
        if (j2 != 0) {
            codedOutputStream.writeInt64(3, j2);
        }
        long j3 = this.numCallsFinishedWithClientFailedToSend_;
        if (j3 != 0) {
            codedOutputStream.writeInt64(6, j3);
        }
        long j4 = this.numCallsFinishedKnownReceived_;
        if (j4 != 0) {
            codedOutputStream.writeInt64(7, j4);
        }
        for (int i = 0; i < this.callsFinishedWithDrop_.size(); i++) {
            codedOutputStream.writeMessage(8, this.callsFinishedWithDrop_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeMessageSize = this.timestamp_ != null ? CodedOutputStream.computeMessageSize(1, getTimestamp()) + 0 : 0;
        long j = this.numCallsStarted_;
        if (j != 0) {
            computeMessageSize += CodedOutputStream.computeInt64Size(2, j);
        }
        long j2 = this.numCallsFinished_;
        if (j2 != 0) {
            computeMessageSize += CodedOutputStream.computeInt64Size(3, j2);
        }
        long j3 = this.numCallsFinishedWithClientFailedToSend_;
        if (j3 != 0) {
            computeMessageSize += CodedOutputStream.computeInt64Size(6, j3);
        }
        long j4 = this.numCallsFinishedKnownReceived_;
        if (j4 != 0) {
            computeMessageSize += CodedOutputStream.computeInt64Size(7, j4);
        }
        for (int i2 = 0; i2 < this.callsFinishedWithDrop_.size(); i2++) {
            computeMessageSize += CodedOutputStream.computeMessageSize(8, this.callsFinishedWithDrop_.get(i2));
        }
        int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientStats)) {
            return super.equals(obj);
        }
        ClientStats clientStats = (ClientStats) obj;
        if (hasTimestamp() != clientStats.hasTimestamp()) {
            return false;
        }
        return (!hasTimestamp() || getTimestamp().equals(clientStats.getTimestamp())) && getNumCallsStarted() == clientStats.getNumCallsStarted() && getNumCallsFinished() == clientStats.getNumCallsFinished() && getNumCallsFinishedWithClientFailedToSend() == clientStats.getNumCallsFinishedWithClientFailedToSend() && getNumCallsFinishedKnownReceived() == clientStats.getNumCallsFinishedKnownReceived() && getCallsFinishedWithDropList().equals(clientStats.getCallsFinishedWithDropList()) && this.unknownFields.equals(clientStats.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasTimestamp()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getTimestamp().hashCode();
        }
        int hashLong = (((((((((((((((hashCode * 37) + 2) * 53) + Internal.hashLong(getNumCallsStarted())) * 37) + 3) * 53) + Internal.hashLong(getNumCallsFinished())) * 37) + 6) * 53) + Internal.hashLong(getNumCallsFinishedWithClientFailedToSend())) * 37) + 7) * 53) + Internal.hashLong(getNumCallsFinishedKnownReceived());
        if (getCallsFinishedWithDropCount() > 0) {
            hashLong = (((hashLong * 37) + 8) * 53) + getCallsFinishedWithDropList().hashCode();
        }
        int hashCode2 = (hashLong * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ClientStats parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ClientStats parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ClientStats parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ClientStats parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ClientStats parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ClientStats parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ClientStats parseFrom(InputStream inputStream) throws IOException {
        return (ClientStats) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ClientStats parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ClientStats) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ClientStats parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ClientStats) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ClientStats parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ClientStats) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ClientStats parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ClientStats) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ClientStats parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ClientStats) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ClientStats clientStats) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(clientStats);
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
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ClientStatsOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> callsFinishedWithDropBuilder_;
        private List<ClientStatsPerToken> callsFinishedWithDrop_;
        private long numCallsFinishedKnownReceived_;
        private long numCallsFinishedWithClientFailedToSend_;
        private long numCallsFinished_;
        private long numCallsStarted_;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> timestampBuilder_;
        private Timestamp timestamp_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_ClientStats_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_ClientStats_fieldAccessorTable.ensureFieldAccessorsInitialized(ClientStats.class, Builder.class);
        }

        private Builder() {
            this.callsFinishedWithDrop_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.callsFinishedWithDrop_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ClientStats.alwaysUseFieldBuilders) {
                getCallsFinishedWithDropFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            if (this.timestampBuilder_ == null) {
                this.timestamp_ = null;
            } else {
                this.timestamp_ = null;
                this.timestampBuilder_ = null;
            }
            this.numCallsStarted_ = 0L;
            this.numCallsFinished_ = 0L;
            this.numCallsFinishedWithClientFailedToSend_ = 0L;
            this.numCallsFinishedKnownReceived_ = 0L;
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.callsFinishedWithDrop_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return LoadBalancerProto.internal_static_grpc_lb_v1_ClientStats_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ClientStats getDefaultInstanceForType() {
            return ClientStats.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ClientStats build() {
            ClientStats buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException((Message) buildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ClientStats buildPartial() {
            ClientStats clientStats = new ClientStats(this);
            int i = this.bitField0_;
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                clientStats.timestamp_ = this.timestamp_;
            } else {
                clientStats.timestamp_ = singleFieldBuilderV3.build();
            }
            clientStats.numCallsStarted_ = this.numCallsStarted_;
            clientStats.numCallsFinished_ = this.numCallsFinished_;
            clientStats.numCallsFinishedWithClientFailedToSend_ = this.numCallsFinishedWithClientFailedToSend_;
            clientStats.numCallsFinishedKnownReceived_ = this.numCallsFinishedKnownReceived_;
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                clientStats.callsFinishedWithDrop_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.callsFinishedWithDrop_ = Collections.unmodifiableList(this.callsFinishedWithDrop_);
                    this.bitField0_ &= -2;
                }
                clientStats.callsFinishedWithDrop_ = this.callsFinishedWithDrop_;
            }
            onBuilt();
            return clientStats;
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
            if (message instanceof ClientStats) {
                return mergeFrom((ClientStats) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ClientStats clientStats) {
            if (clientStats == ClientStats.getDefaultInstance()) {
                return this;
            }
            if (clientStats.hasTimestamp()) {
                mergeTimestamp(clientStats.getTimestamp());
            }
            if (clientStats.getNumCallsStarted() != 0) {
                setNumCallsStarted(clientStats.getNumCallsStarted());
            }
            if (clientStats.getNumCallsFinished() != 0) {
                setNumCallsFinished(clientStats.getNumCallsFinished());
            }
            if (clientStats.getNumCallsFinishedWithClientFailedToSend() != 0) {
                setNumCallsFinishedWithClientFailedToSend(clientStats.getNumCallsFinishedWithClientFailedToSend());
            }
            if (clientStats.getNumCallsFinishedKnownReceived() != 0) {
                setNumCallsFinishedKnownReceived(clientStats.getNumCallsFinishedKnownReceived());
            }
            if (this.callsFinishedWithDropBuilder_ == null) {
                if (!clientStats.callsFinishedWithDrop_.isEmpty()) {
                    if (this.callsFinishedWithDrop_.isEmpty()) {
                        this.callsFinishedWithDrop_ = clientStats.callsFinishedWithDrop_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureCallsFinishedWithDropIsMutable();
                        this.callsFinishedWithDrop_.addAll(clientStats.callsFinishedWithDrop_);
                    }
                    onChanged();
                }
            } else if (!clientStats.callsFinishedWithDrop_.isEmpty()) {
                if (!this.callsFinishedWithDropBuilder_.isEmpty()) {
                    this.callsFinishedWithDropBuilder_.addAllMessages(clientStats.callsFinishedWithDrop_);
                } else {
                    this.callsFinishedWithDropBuilder_.dispose();
                    this.callsFinishedWithDropBuilder_ = null;
                    this.callsFinishedWithDrop_ = clientStats.callsFinishedWithDrop_;
                    this.bitField0_ &= -2;
                    this.callsFinishedWithDropBuilder_ = ClientStats.alwaysUseFieldBuilders ? getCallsFinishedWithDropFieldBuilder() : null;
                }
            }
            mergeUnknownFields(clientStats.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            ClientStats clientStats = null;
            try {
                try {
                    ClientStats clientStats2 = (ClientStats) ClientStats.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (clientStats2 != null) {
                        mergeFrom(clientStats2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ClientStats clientStats3 = (ClientStats) e.getUnfinishedMessage();
                    try {
                        throw e.unwrapIOException();
                    } catch (Throwable th) {
                        th = th;
                        clientStats = clientStats3;
                        if (clientStats != null) {
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (clientStats != null) {
                    mergeFrom(clientStats);
                }
                throw th;
            }
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public boolean hasTimestamp() {
            return (this.timestampBuilder_ == null && this.timestamp_ == null) ? false : true;
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public Timestamp getTimestamp() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp = this.timestamp_;
                return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(timestamp);
            } else {
                if (timestamp == null) {
                    throw new NullPointerException();
                }
                this.timestamp_ = timestamp;
                onChanged();
            }
            return this;
        }

        public Builder setTimestamp(Timestamp.Builder builder) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.timestamp_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp2 = this.timestamp_;
                if (timestamp2 != null) {
                    this.timestamp_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
                } else {
                    this.timestamp_ = timestamp;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(timestamp);
            }
            return this;
        }

        public Builder clearTimestamp() {
            if (this.timestampBuilder_ == null) {
                this.timestamp_ = null;
                onChanged();
            } else {
                this.timestamp_ = null;
                this.timestampBuilder_ = null;
            }
            return this;
        }

        public Timestamp.Builder getTimestampBuilder() {
            onChanged();
            return getTimestampFieldBuilder().getBuilder();
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public TimestampOrBuilder getTimestampOrBuilder() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.timestampBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Timestamp timestamp = this.timestamp_;
            return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
        }

        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getTimestampFieldBuilder() {
            if (this.timestampBuilder_ == null) {
                this.timestampBuilder_ = new SingleFieldBuilderV3<>(getTimestamp(), getParentForChildren(), isClean());
                this.timestamp_ = null;
            }
            return this.timestampBuilder_;
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public long getNumCallsStarted() {
            return this.numCallsStarted_;
        }

        public Builder setNumCallsStarted(long j) {
            this.numCallsStarted_ = j;
            onChanged();
            return this;
        }

        public Builder clearNumCallsStarted() {
            this.numCallsStarted_ = 0L;
            onChanged();
            return this;
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public long getNumCallsFinished() {
            return this.numCallsFinished_;
        }

        public Builder setNumCallsFinished(long j) {
            this.numCallsFinished_ = j;
            onChanged();
            return this;
        }

        public Builder clearNumCallsFinished() {
            this.numCallsFinished_ = 0L;
            onChanged();
            return this;
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public long getNumCallsFinishedWithClientFailedToSend() {
            return this.numCallsFinishedWithClientFailedToSend_;
        }

        public Builder setNumCallsFinishedWithClientFailedToSend(long j) {
            this.numCallsFinishedWithClientFailedToSend_ = j;
            onChanged();
            return this;
        }

        public Builder clearNumCallsFinishedWithClientFailedToSend() {
            this.numCallsFinishedWithClientFailedToSend_ = 0L;
            onChanged();
            return this;
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public long getNumCallsFinishedKnownReceived() {
            return this.numCallsFinishedKnownReceived_;
        }

        public Builder setNumCallsFinishedKnownReceived(long j) {
            this.numCallsFinishedKnownReceived_ = j;
            onChanged();
            return this;
        }

        public Builder clearNumCallsFinishedKnownReceived() {
            this.numCallsFinishedKnownReceived_ = 0L;
            onChanged();
            return this;
        }

        private void ensureCallsFinishedWithDropIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.callsFinishedWithDrop_ = new ArrayList(this.callsFinishedWithDrop_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public List<ClientStatsPerToken> getCallsFinishedWithDropList() {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.callsFinishedWithDrop_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public int getCallsFinishedWithDropCount() {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.callsFinishedWithDrop_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public ClientStatsPerToken getCallsFinishedWithDrop(int i) {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.callsFinishedWithDrop_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setCallsFinishedWithDrop(int i, ClientStatsPerToken clientStatsPerToken) {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, clientStatsPerToken);
            } else {
                if (clientStatsPerToken == null) {
                    throw new NullPointerException();
                }
                ensureCallsFinishedWithDropIsMutable();
                this.callsFinishedWithDrop_.set(i, clientStatsPerToken);
                onChanged();
            }
            return this;
        }

        public Builder setCallsFinishedWithDrop(int i, ClientStatsPerToken.Builder builder) {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureCallsFinishedWithDropIsMutable();
                this.callsFinishedWithDrop_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addCallsFinishedWithDrop(ClientStatsPerToken clientStatsPerToken) {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(clientStatsPerToken);
            } else {
                if (clientStatsPerToken == null) {
                    throw new NullPointerException();
                }
                ensureCallsFinishedWithDropIsMutable();
                this.callsFinishedWithDrop_.add(clientStatsPerToken);
                onChanged();
            }
            return this;
        }

        public Builder addCallsFinishedWithDrop(int i, ClientStatsPerToken clientStatsPerToken) {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, clientStatsPerToken);
            } else {
                if (clientStatsPerToken == null) {
                    throw new NullPointerException();
                }
                ensureCallsFinishedWithDropIsMutable();
                this.callsFinishedWithDrop_.add(i, clientStatsPerToken);
                onChanged();
            }
            return this;
        }

        public Builder addCallsFinishedWithDrop(ClientStatsPerToken.Builder builder) {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureCallsFinishedWithDropIsMutable();
                this.callsFinishedWithDrop_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addCallsFinishedWithDrop(int i, ClientStatsPerToken.Builder builder) {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureCallsFinishedWithDropIsMutable();
                this.callsFinishedWithDrop_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllCallsFinishedWithDrop(Iterable<? extends ClientStatsPerToken> iterable) {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureCallsFinishedWithDropIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.callsFinishedWithDrop_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearCallsFinishedWithDrop() {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.callsFinishedWithDrop_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeCallsFinishedWithDrop(int i) {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureCallsFinishedWithDropIsMutable();
                this.callsFinishedWithDrop_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public ClientStatsPerToken.Builder getCallsFinishedWithDropBuilder(int i) {
            return getCallsFinishedWithDropFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public ClientStatsPerTokenOrBuilder getCallsFinishedWithDropOrBuilder(int i) {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.callsFinishedWithDrop_.get(i);
            }
            return repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.lb.p072v1.ClientStatsOrBuilder
        public List<? extends ClientStatsPerTokenOrBuilder> getCallsFinishedWithDropOrBuilderList() {
            RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> repeatedFieldBuilderV3 = this.callsFinishedWithDropBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.callsFinishedWithDrop_);
        }

        public ClientStatsPerToken.Builder addCallsFinishedWithDropBuilder() {
            return getCallsFinishedWithDropFieldBuilder().addBuilder(ClientStatsPerToken.getDefaultInstance());
        }

        public ClientStatsPerToken.Builder addCallsFinishedWithDropBuilder(int i) {
            return getCallsFinishedWithDropFieldBuilder().addBuilder(i, ClientStatsPerToken.getDefaultInstance());
        }

        public List<ClientStatsPerToken.Builder> getCallsFinishedWithDropBuilderList() {
            return getCallsFinishedWithDropFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<ClientStatsPerToken, ClientStatsPerToken.Builder, ClientStatsPerTokenOrBuilder> getCallsFinishedWithDropFieldBuilder() {
            if (this.callsFinishedWithDropBuilder_ == null) {
                this.callsFinishedWithDropBuilder_ = new RepeatedFieldBuilderV3<>(this.callsFinishedWithDrop_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.callsFinishedWithDrop_ = null;
            }
            return this.callsFinishedWithDropBuilder_;
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

    public static ClientStats getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ClientStats> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ClientStats> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ClientStats getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
