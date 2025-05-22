package io.minio.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Tag")
/* loaded from: classes7.dex */
public class Tag {

    @Element(name = "Key")
    private String key;

    @Element(name = "Value")
    private String value;

    public Tag(@Element(name = "Key") @Nonnull String str, @Element(name = "Value") @Nonnull String str2) {
        Objects.requireNonNull(str, "Key must not be null");
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Key must not be empty");
        }
        this.key = str;
        this.value = (String) Objects.requireNonNull(str2, "Value must not be null");
    }

    public String key() {
        return this.key;
    }

    public String value() {
        return this.value;
    }
}
