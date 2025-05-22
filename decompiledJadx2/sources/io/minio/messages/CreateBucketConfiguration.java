package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "CreateBucketConfiguration")
/* loaded from: classes7.dex */
public class CreateBucketConfiguration {

    @Element(name = "LocationConstraint")
    private String locationConstraint;

    public CreateBucketConfiguration(String str) {
        this.locationConstraint = str;
    }
}
