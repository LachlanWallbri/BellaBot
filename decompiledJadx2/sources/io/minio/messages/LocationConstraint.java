package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "LocationConstraint", strict = false)
/* loaded from: classes7.dex */
public class LocationConstraint {

    @Text(required = false)
    private String location = "";

    public String location() {
        return this.location;
    }
}
