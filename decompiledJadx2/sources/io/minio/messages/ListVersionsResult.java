package io.minio.messages;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.amazonaws.services.p048s3.internal.Constants;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "ListVersionsResult", strict = false)
/* loaded from: classes7.dex */
public class ListVersionsResult extends ListObjectsResult {

    @ElementList(inline = true, name = JsonDocumentFields.VERSION, required = false)
    private List<Version> contents;

    @ElementList(inline = true, name = "DeleteMarker", required = false)
    private List<DeleteMarker> deleteMarkers;

    @Element(name = "KeyMarker", required = false)
    private String keyMarker;

    @Element(name = "NextKeyMarker", required = false)
    private String nextKeyMarker;

    @Element(name = "NextVersionIdMarker", required = false)
    private String nextVersionIdMarker;

    @Element(name = "VersionIdMarker", required = false)
    private String versionIdMarker;

    public String keyMarker() {
        return decodeIfNeeded(this.keyMarker);
    }

    public String nextKeyMarker() {
        return decodeIfNeeded(this.nextKeyMarker);
    }

    public String versionIdMarker() {
        return this.versionIdMarker;
    }

    public String nextVersionIdMarker() {
        return this.nextVersionIdMarker;
    }

    @Override // io.minio.messages.ListObjectsResult
    public List<Version> contents() {
        return emptyIfNull(this.contents);
    }

    @Override // io.minio.messages.ListObjectsResult
    public List<DeleteMarker> deleteMarkers() {
        return emptyIfNull(this.deleteMarkers);
    }
}
