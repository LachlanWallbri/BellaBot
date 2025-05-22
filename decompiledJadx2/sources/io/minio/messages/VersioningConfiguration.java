package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import com.amazonaws.services.p048s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.p048s3.model.BucketVersioningConfiguration;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "VersioningConfiguration", strict = false)
/* loaded from: classes7.dex */
public class VersioningConfiguration {

    @Element(name = "MFADelete", required = false)
    private String mfaDelete;

    @Element(name = "Status", required = false)
    private String status;

    public VersioningConfiguration() {
    }

    public VersioningConfiguration(@Nonnull Status status, @Nullable Boolean bool) {
        Objects.requireNonNull(status, "Status must not be null");
        if (status == Status.OFF) {
            throw new IllegalArgumentException("Status must be ENABLED or SUSPENDED");
        }
        this.status = status.toString();
        if (bool != null) {
            this.mfaDelete = bool.booleanValue() ? "Enabled" : BucketLifecycleConfiguration.DISABLED;
        }
    }

    public Status status() {
        return Status.fromString(this.status);
    }

    public Boolean isMfaDeleteEnabled() {
        String str = this.mfaDelete;
        if (str != null) {
            return Boolean.valueOf("Enabled".equals(str));
        }
        return null;
    }

    /* loaded from: classes7.dex */
    public enum Status {
        OFF(""),
        ENABLED("Enabled"),
        SUSPENDED(BucketVersioningConfiguration.SUSPENDED);

        private final String value;

        Status(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }

        public static Status fromString(String str) {
            if ("Enabled".equals(str)) {
                return ENABLED;
            }
            if (BucketVersioningConfiguration.SUSPENDED.equals(str)) {
                return SUSPENDED;
            }
            return OFF;
        }
    }
}
