package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public class InvalidProtocolBufferException extends IOException {
    private MessageLite unfinishedMessage;

    public InvalidProtocolBufferException(String str) {
        super(str);
        this.unfinishedMessage = null;
    }

    public InvalidProtocolBufferException setUnfinishedMessage(MessageLite messageLite) {
        this.unfinishedMessage = messageLite;
        return this;
    }

    public MessageLite getUnfinishedMessage() {
        return this.unfinishedMessage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InvalidProtocolBufferException truncatedMessage() {
        return new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InvalidProtocolBufferException negativeSize() {
        return new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InvalidProtocolBufferException malformedVarint() {
        return new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InvalidProtocolBufferException invalidTag() {
        return new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InvalidProtocolBufferException invalidEndTag() {
        return new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InvalidProtocolBufferException invalidWireType() {
        return new InvalidProtocolBufferException("Protocol message tag had invalid wire type.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InvalidProtocolBufferException recursionLimitExceeded() {
        return new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InvalidProtocolBufferException sizeLimitExceeded() {
        return new InvalidProtocolBufferException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InvalidProtocolBufferException invalidUtf8() {
        return new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
    }
}
