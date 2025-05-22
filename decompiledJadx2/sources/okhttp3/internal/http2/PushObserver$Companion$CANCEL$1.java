package okhttp3.internal.http2;

import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;

/* compiled from: PushObserver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0003H\u0016J&\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u001e\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0016¨\u0006\u0014"}, m3961d2 = {"okhttp3/internal/http2/PushObserver$Companion$CANCEL$1", "Lokhttp3/internal/http2/PushObserver;", "onData", "", "streamId", "", MapElement.Source.SOURCE, "Lokio/BufferedSource;", "byteCount", "last", "onHeaders", "responseHeaders", "", "Lokhttp3/internal/http2/Header;", "onRequest", "requestHeaders", "onReset", "", AUserTrack.UTKEY_ERROR_CODE, "Lokhttp3/internal/http2/ErrorCode;", "okhttp"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class PushObserver$Companion$CANCEL$1 implements PushObserver {
    @Override // okhttp3.internal.http2.PushObserver
    public boolean onHeaders(int streamId, List<Header> responseHeaders, boolean last) {
        Intrinsics.checkParameterIsNotNull(responseHeaders, "responseHeaders");
        return true;
    }

    @Override // okhttp3.internal.http2.PushObserver
    public boolean onRequest(int streamId, List<Header> requestHeaders) {
        Intrinsics.checkParameterIsNotNull(requestHeaders, "requestHeaders");
        return true;
    }

    @Override // okhttp3.internal.http2.PushObserver
    public void onReset(int streamId, ErrorCode errorCode) {
        Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
    }

    PushObserver$Companion$CANCEL$1() {
    }

    @Override // okhttp3.internal.http2.PushObserver
    public boolean onData(int streamId, BufferedSource source, int byteCount, boolean last) throws IOException {
        Intrinsics.checkParameterIsNotNull(source, "source");
        source.skip(byteCount);
        return true;
    }
}
