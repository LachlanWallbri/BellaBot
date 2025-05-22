package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "ReplicationConfiguration")
/* loaded from: classes7.dex */
public class ReplicationConfiguration {

    @Element(name = "Role", required = false)
    private String role;

    @ElementList(inline = true, name = "Rule")
    private List<ReplicationRule> rules;

    public ReplicationConfiguration(@Element(name = "Role", required = false) @Nullable String str, @Nonnull @ElementList(inline = true, name = "Rule") List<ReplicationRule> list) {
        this.role = str;
        this.rules = Collections.unmodifiableList((List) Objects.requireNonNull(list, "Rules must not be null"));
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Rules must not be empty");
        }
        if (list.size() > 1000) {
            throw new IllegalArgumentException("More than 1000 rules are not supported");
        }
    }

    public String role() {
        return this.role;
    }

    public List<ReplicationRule> rules() {
        List list = this.rules;
        if (list == null) {
            list = new LinkedList();
        }
        return Collections.unmodifiableList(list);
    }
}
