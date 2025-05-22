package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "ListBucketResult", strict = false)
/* loaded from: classes7.dex */
public class ListBucketResultV2 extends ListObjectsResult {

    @ElementList(inline = true, name = "Contents", required = false)
    private List<Contents> contents;

    @Element(name = "ContinuationToken", required = false)
    private String continuationToken;

    @Element(name = "KeyCount", required = false)
    private int keyCount;

    @Element(name = "NextContinuationToken", required = false)
    private String nextContinuationToken;

    @Element(name = "StartAfter", required = false)
    private String startAfter;

    public int keyCount() {
        return this.keyCount;
    }

    public String startAfter() {
        return decodeIfNeeded(this.startAfter);
    }

    public String continuationToken() {
        return this.continuationToken;
    }

    public String nextContinuationToken() {
        return this.nextContinuationToken;
    }

    @Override // io.minio.messages.ListObjectsResult
    public List<Contents> contents() {
        return emptyIfNull(this.contents);
    }
}
