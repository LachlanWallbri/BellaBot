package okio;

import com.iflytek.cloud.SpeechEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Okio.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\b\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0001\u001a\n\u0010\u0003\u001a\u00020\u0005*\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"blackholeSink", "Lokio/Sink;", "blackhole", SpeechEvent.KEY_EVENT_TTS_BUFFER, "Lokio/BufferedSink;", "Lokio/BufferedSource;", "Lokio/Source;", "okio"}, m3962k = 5, m3963mv = {1, 1, 16}, m3966xs = "okio/Okio")
/* loaded from: classes8.dex */
final /* synthetic */ class Okio__OkioKt {
    public static final BufferedSource buffer(Source buffer) {
        Intrinsics.checkParameterIsNotNull(buffer, "$this$buffer");
        return new RealBufferedSource(buffer);
    }

    public static final BufferedSink buffer(Sink buffer) {
        Intrinsics.checkParameterIsNotNull(buffer, "$this$buffer");
        return new RealBufferedSink(buffer);
    }

    public static final Sink blackhole() {
        return new BlackholeSink();
    }
}
