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
public class ListBucketResultV1 extends ListObjectsResult {

    @ElementList(inline = true, name = "Contents", required = false)
    private List<Contents> contents;

    @Element(name = "Marker", required = false)
    private String marker;

    @Element(name = "NextMarker", required = false)
    private String nextMarker;

    public String marker() {
        return decodeIfNeeded(this.marker);
    }

    public String nextMarker() {
        return decodeIfNeeded(this.nextMarker);
    }

    @Override // io.minio.messages.ListObjectsResult
    public List<Contents> contents() {
        return emptyIfNull(this.contents);
    }
}
