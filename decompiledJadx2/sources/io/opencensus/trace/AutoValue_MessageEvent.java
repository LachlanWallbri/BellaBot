package io.opencensus.trace;

import io.opencensus.trace.MessageEvent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_MessageEvent extends MessageEvent {
    private final long compressedMessageSize;
    private final long messageId;
    private final MessageEvent.Type type;
    private final long uncompressedMessageSize;

    private AutoValue_MessageEvent(MessageEvent.Type type, long j, long j2, long j3) {
        this.type = type;
        this.messageId = j;
        this.uncompressedMessageSize = j2;
        this.compressedMessageSize = j3;
    }

    @Override // io.opencensus.trace.MessageEvent
    public MessageEvent.Type getType() {
        return this.type;
    }

    @Override // io.opencensus.trace.MessageEvent
    public long getMessageId() {
        return this.messageId;
    }

    @Override // io.opencensus.trace.MessageEvent
    public long getUncompressedMessageSize() {
        return this.uncompressedMessageSize;
    }

    @Override // io.opencensus.trace.MessageEvent
    public long getCompressedMessageSize() {
        return this.compressedMessageSize;
    }

    public String toString() {
        return "MessageEvent{type=" + this.type + ", messageId=" + this.messageId + ", uncompressedMessageSize=" + this.uncompressedMessageSize + ", compressedMessageSize=" + this.compressedMessageSize + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MessageEvent)) {
            return false;
        }
        MessageEvent messageEvent = (MessageEvent) obj;
        return this.type.equals(messageEvent.getType()) && this.messageId == messageEvent.getMessageId() && this.uncompressedMessageSize == messageEvent.getUncompressedMessageSize() && this.compressedMessageSize == messageEvent.getCompressedMessageSize();
    }

    public int hashCode() {
        long hashCode = (this.type.hashCode() ^ 1000003) * 1000003;
        long j = this.messageId;
        long j2 = ((int) (hashCode ^ (j ^ (j >>> 32)))) * 1000003;
        long j3 = this.uncompressedMessageSize;
        long j4 = this.compressedMessageSize;
        return (int) ((((int) (j2 ^ (j3 ^ (j3 >>> 32)))) * 1000003) ^ (j4 ^ (j4 >>> 32)));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    static final class Builder extends MessageEvent.Builder {
        private Long compressedMessageSize;
        private Long messageId;
        private MessageEvent.Type type;
        private Long uncompressedMessageSize;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.opencensus.trace.MessageEvent.Builder
        public MessageEvent.Builder setType(MessageEvent.Type type) {
            if (type == null) {
                throw new NullPointerException("Null type");
            }
            this.type = type;
            return this;
        }

        @Override // io.opencensus.trace.MessageEvent.Builder
        MessageEvent.Builder setMessageId(long j) {
            this.messageId = Long.valueOf(j);
            return this;
        }

        @Override // io.opencensus.trace.MessageEvent.Builder
        public MessageEvent.Builder setUncompressedMessageSize(long j) {
            this.uncompressedMessageSize = Long.valueOf(j);
            return this;
        }

        @Override // io.opencensus.trace.MessageEvent.Builder
        public MessageEvent.Builder setCompressedMessageSize(long j) {
            this.compressedMessageSize = Long.valueOf(j);
            return this;
        }

        @Override // io.opencensus.trace.MessageEvent.Builder
        public MessageEvent build() {
            String str = "";
            if (this.type == null) {
                str = " type";
            }
            if (this.messageId == null) {
                str = str + " messageId";
            }
            if (this.uncompressedMessageSize == null) {
                str = str + " uncompressedMessageSize";
            }
            if (this.compressedMessageSize == null) {
                str = str + " compressedMessageSize";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_MessageEvent(this.type, this.messageId.longValue(), this.uncompressedMessageSize.longValue(), this.compressedMessageSize.longValue());
        }
    }
}
