package okhttp3.internal.cache;

import java.io.IOException;
import okio.Sink;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface CacheRequest {
    void abort();

    Sink body() throws IOException;
}
