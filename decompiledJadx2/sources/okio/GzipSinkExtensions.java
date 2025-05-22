package okio;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: GzipSink.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b¨\u0006\u0003"}, m3961d2 = {"gzip", "Lokio/GzipSink;", "Lokio/Sink;", "jvm"}, m3962k = 2, m3963mv = {1, 1, 11})
/* renamed from: okio.-GzipSinkExtensions, reason: invalid class name */
/* loaded from: classes2.dex */
public final class GzipSinkExtensions {
    public static final GzipSink gzip(Sink receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new GzipSink(receiver);
    }
}
