package io.minio.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.apache.http.HttpHeaders;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "AccessControlTranslation")
/* loaded from: classes7.dex */
public class AccessControlTranslation {

    @Element(name = "Owner")
    private String owner;

    public AccessControlTranslation(@Element(name = "Owner") @Nonnull String str) {
        this.owner = HttpHeaders.DESTINATION;
        this.owner = (String) Objects.requireNonNull(str, "Owner must not be null");
    }

    public String owner() {
        return this.owner;
    }
}
