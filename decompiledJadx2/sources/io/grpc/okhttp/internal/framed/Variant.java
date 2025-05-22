package io.grpc.okhttp.internal.framed;

import io.grpc.okhttp.internal.Protocol;
import okio.BufferedSink;
import okio.BufferedSource;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Variant {
    Protocol getProtocol();

    FrameReader newReader(BufferedSource bufferedSource, boolean z);

    FrameWriter newWriter(BufferedSink bufferedSink, boolean z);
}
