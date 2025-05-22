package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "ObjectLockConfiguration", strict = false)
/* loaded from: classes7.dex */
public class ObjectLockConfiguration {

    @Element(name = "ObjectLockEnabled")
    private String objectLockEnabled = "Enabled";

    @Element(name = "Rule", required = false)
    private Rule rule;

    public ObjectLockConfiguration() {
    }

    public ObjectLockConfiguration(RetentionMode retentionMode, RetentionDuration retentionDuration) throws IllegalArgumentException {
        this.rule = new Rule(retentionMode, retentionDuration);
    }

    public RetentionMode mode() {
        Rule rule = this.rule;
        if (rule != null) {
            return rule.mode();
        }
        return null;
    }

    public RetentionDuration duration() {
        Rule rule = this.rule;
        if (rule != null) {
            return rule.duration();
        }
        return null;
    }
}
