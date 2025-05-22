package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.util.collection.CharObjectHashMap;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Http2Settings extends CharObjectHashMap<Long> {
    private static final int DEFAULT_CAPACITY = 13;
    private static final Long FALSE = 0L;
    private static final Long TRUE = 1L;

    public Http2Settings() {
        this(13);
    }

    public Http2Settings(int i, float f) {
        super(i, f);
    }

    public Http2Settings(int i) {
        super(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.util.collection.CharObjectHashMap, io.grpc.netty.shaded.io.netty.util.collection.CharObjectMap
    public Long put(char c, Long l) {
        verifyStandardSetting(c, l);
        return (Long) super.put(c, (char) l);
    }

    public Long headerTableSize() {
        return get((char) 1);
    }

    public Http2Settings headerTableSize(long j) {
        put((char) 1, Long.valueOf(j));
        return this;
    }

    public Boolean pushEnabled() {
        Long l = get((char) 2);
        if (l == null) {
            return null;
        }
        return Boolean.valueOf(TRUE.equals(l));
    }

    public Http2Settings pushEnabled(boolean z) {
        put((char) 2, z ? TRUE : FALSE);
        return this;
    }

    public Long maxConcurrentStreams() {
        return get((char) 3);
    }

    public Http2Settings maxConcurrentStreams(long j) {
        put((char) 3, Long.valueOf(j));
        return this;
    }

    public Integer initialWindowSize() {
        return getIntValue((char) 4);
    }

    public Http2Settings initialWindowSize(int i) {
        put((char) 4, Long.valueOf(i));
        return this;
    }

    public Integer maxFrameSize() {
        return getIntValue((char) 5);
    }

    public Http2Settings maxFrameSize(int i) {
        put((char) 5, Long.valueOf(i));
        return this;
    }

    public Long maxHeaderListSize() {
        return get((char) 6);
    }

    public Http2Settings maxHeaderListSize(long j) {
        put((char) 6, Long.valueOf(j));
        return this;
    }

    public Http2Settings copyFrom(Http2Settings http2Settings) {
        clear();
        putAll(http2Settings);
        return this;
    }

    public Integer getIntValue(char c) {
        Long l = get(c);
        if (l == null) {
            return null;
        }
        return Integer.valueOf(l.intValue());
    }

    private static void verifyStandardSetting(int i, Long l) {
        ObjectUtil.checkNotNull(l, ES6Iterator.VALUE_PROPERTY);
        switch (i) {
            case 1:
                if (l.longValue() < 0 || l.longValue() > 4294967295L) {
                    throw new IllegalArgumentException("Setting HEADER_TABLE_SIZE is invalid: " + l);
                }
                return;
            case 2:
                if (l.longValue() == 0 || l.longValue() == 1) {
                    return;
                }
                throw new IllegalArgumentException("Setting ENABLE_PUSH is invalid: " + l);
            case 3:
                if (l.longValue() < 0 || l.longValue() > 4294967295L) {
                    throw new IllegalArgumentException("Setting MAX_CONCURRENT_STREAMS is invalid: " + l);
                }
                return;
            case 4:
                if (l.longValue() < 0 || l.longValue() > 2147483647L) {
                    throw new IllegalArgumentException("Setting INITIAL_WINDOW_SIZE is invalid: " + l);
                }
                return;
            case 5:
                if (Http2CodecUtil.isMaxFrameSizeValid(l.intValue())) {
                    return;
                }
                throw new IllegalArgumentException("Setting MAX_FRAME_SIZE is invalid: " + l);
            case 6:
                if (l.longValue() < 0 || l.longValue() > 4294967295L) {
                    throw new IllegalArgumentException("Setting MAX_HEADER_LIST_SIZE is invalid: " + l);
                }
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.util.collection.CharObjectHashMap
    public String keyToString(char c) {
        switch (c) {
            case 1:
                return "HEADER_TABLE_SIZE";
            case 2:
                return "ENABLE_PUSH";
            case 3:
                return "MAX_CONCURRENT_STREAMS";
            case 4:
                return "INITIAL_WINDOW_SIZE";
            case 5:
                return "MAX_FRAME_SIZE";
            case 6:
                return "MAX_HEADER_LIST_SIZE";
            default:
                return super.keyToString(c);
        }
    }

    public static Http2Settings defaultSettings() {
        return new Http2Settings().maxHeaderListSize(8192L);
    }
}
