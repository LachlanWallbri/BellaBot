package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "ListAllMyBucketsResult", strict = false)
/* loaded from: classes7.dex */
public class ListAllMyBucketsResult {

    @ElementList(name = "Buckets")
    private List<Bucket> buckets;

    @Element(name = "Owner")
    private Owner owner;

    public Owner owner() {
        return this.owner;
    }

    public List<Bucket> buckets() {
        List<Bucket> list = this.buckets;
        if (list == null) {
            return Collections.unmodifiableList(new LinkedList());
        }
        return Collections.unmodifiableList(list);
    }
}
