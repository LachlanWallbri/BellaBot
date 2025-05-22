package io.netty.handler.codec.http2;

import io.netty.channel.ChannelId;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class Http2StreamChannelId implements ChannelId {
    private static final long serialVersionUID = -6642338822166867585L;

    /* renamed from: id */
    private final int f8511id;
    private final ChannelId parentId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2StreamChannelId(ChannelId channelId, int i) {
        this.parentId = channelId;
        this.f8511id = i;
    }

    @Override // io.netty.channel.ChannelId
    public String asShortText() {
        return this.parentId.asShortText() + '/' + this.f8511id;
    }

    @Override // io.netty.channel.ChannelId
    public String asLongText() {
        return this.parentId.asLongText() + '/' + this.f8511id;
    }

    @Override // java.lang.Comparable
    public int compareTo(ChannelId channelId) {
        if (channelId instanceof Http2StreamChannelId) {
            Http2StreamChannelId http2StreamChannelId = (Http2StreamChannelId) channelId;
            int compareTo = this.parentId.compareTo(http2StreamChannelId.parentId);
            return compareTo == 0 ? this.f8511id - http2StreamChannelId.f8511id : compareTo;
        }
        return this.parentId.compareTo(channelId);
    }

    public int hashCode() {
        return (this.f8511id * 31) + this.parentId.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Http2StreamChannelId)) {
            return false;
        }
        Http2StreamChannelId http2StreamChannelId = (Http2StreamChannelId) obj;
        return this.f8511id == http2StreamChannelId.f8511id && this.parentId.equals(http2StreamChannelId.parentId);
    }

    public String toString() {
        return asShortText();
    }
}
