package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "LegalHold", strict = false)
/* loaded from: classes7.dex */
public class LegalHold {

    @Element(name = "Status", required = false)
    private String status;

    public LegalHold() {
    }

    public LegalHold(boolean z) {
        if (z) {
            this.status = "ON";
        } else {
            this.status = "OFF";
        }
    }

    public boolean status() {
        String str = this.status;
        return str != null && str.equals("ON");
    }
}
