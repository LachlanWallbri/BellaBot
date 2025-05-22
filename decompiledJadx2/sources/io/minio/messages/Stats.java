package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "Stats", strict = false)
/* loaded from: classes7.dex */
public class Stats {

    @Element(name = "BytesScanned")
    private long bytesScanned = -1;

    @Element(name = "BytesProcessed")
    private long bytesProcessed = -1;

    @Element(name = "BytesReturned")
    private long bytesReturned = -1;

    public long bytesScanned() {
        return this.bytesScanned;
    }

    public long bytesProcessed() {
        return this.bytesProcessed;
    }

    public long bytesReturned() {
        return this.bytesReturned;
    }
}
