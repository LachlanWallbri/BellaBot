package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "LifecycleConfiguration")
/* loaded from: classes7.dex */
public class LifecycleConfiguration {

    @ElementList(inline = true, name = "Rule")
    private List<LifecycleRule> rules;

    public LifecycleConfiguration(@Nonnull @ElementList(inline = true, name = "Rule") List<LifecycleRule> list) {
        this.rules = Collections.unmodifiableList((List) Objects.requireNonNull(list, "Rules must not be null"));
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Rules must not be empty");
        }
    }

    public List<LifecycleRule> rules() {
        return this.rules;
    }
}
