package io.minio.credentials;

import io.minio.messages.ResponseDate;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Credentials", strict = false)
/* loaded from: classes7.dex */
public class Credentials {

    @Element(name = "AccessKeyId")
    private final String accessKey;

    @Element(name = "Expiration")
    private final ResponseDate expiration;

    @Element(name = "SecretAccessKey")
    private final String secretKey;

    @Element(name = "SessionToken")
    private final String sessionToken;

    public Credentials(@Element(name = "AccessKeyId") @Nonnull String str, @Element(name = "SecretAccessKey") @Nonnull String str2, @Element(name = "SessionToken") @Nullable String str3, @Element(name = "Expiration") @Nullable ResponseDate responseDate) {
        this.accessKey = (String) Objects.requireNonNull(str, "AccessKey must not be null");
        this.secretKey = (String) Objects.requireNonNull(str2, "SecretKey must not be null");
        if (str.isEmpty() || str2.isEmpty()) {
            throw new IllegalArgumentException("AccessKey and SecretKey must not be empty");
        }
        this.sessionToken = str3;
        this.expiration = responseDate;
    }

    public String accessKey() {
        return this.accessKey;
    }

    public String secretKey() {
        return this.secretKey;
    }

    public String sessionToken() {
        return this.sessionToken;
    }

    public boolean isExpired() {
        if (this.expiration == null) {
            return false;
        }
        return ZonedDateTime.now().plus((TemporalAmount) Duration.ofSeconds(10L)).isAfter(this.expiration.zonedDateTime());
    }
}
