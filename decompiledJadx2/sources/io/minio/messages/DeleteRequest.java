package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.util.Collections;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "Delete")
/* loaded from: classes7.dex */
public class DeleteRequest {

    @ElementList(inline = true, name = "Object")
    private List<DeleteObject> objectList;

    @Element(name = "Quiet", required = false)
    private boolean quiet;

    public DeleteRequest(List<DeleteObject> list, boolean z) {
        this.objectList = Collections.unmodifiableList(list);
        this.quiet = z;
    }
}
