package io.netty.handler.codec;

import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public final class ProtocolDetectionResult<T> {
    private final T result;
    private final ProtocolDetectionState state;
    private static final ProtocolDetectionResult NEEDS_MORE_DATE = new ProtocolDetectionResult(ProtocolDetectionState.NEEDS_MORE_DATA, null);
    private static final ProtocolDetectionResult INVALID = new ProtocolDetectionResult(ProtocolDetectionState.INVALID, null);

    public static <T> ProtocolDetectionResult<T> needsMoreData() {
        return NEEDS_MORE_DATE;
    }

    public static <T> ProtocolDetectionResult<T> invalid() {
        return INVALID;
    }

    public static <T> ProtocolDetectionResult<T> detected(T t) {
        return new ProtocolDetectionResult<>(ProtocolDetectionState.DETECTED, ObjectUtil.checkNotNull(t, "protocol"));
    }

    private ProtocolDetectionResult(ProtocolDetectionState protocolDetectionState, T t) {
        this.state = protocolDetectionState;
        this.result = t;
    }

    public ProtocolDetectionState state() {
        return this.state;
    }

    public T detectedProtocol() {
        return this.result;
    }
}
