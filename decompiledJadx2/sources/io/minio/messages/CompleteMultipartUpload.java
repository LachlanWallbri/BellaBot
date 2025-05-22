package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "CompleteMultipartUpload")
/* loaded from: classes7.dex */
public class CompleteMultipartUpload {

    @ElementList(inline = true, name = "Part")
    private List<Part> partList;

    public CompleteMultipartUpload(Part[] partArr) throws IllegalArgumentException {
        if (partArr == null || partArr.length == 0) {
            throw new IllegalArgumentException("null or empty parts");
        }
        this.partList = Collections.unmodifiableList(Arrays.asList(partArr));
    }
}
