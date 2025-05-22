package io.minio.credentials;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.annotation.Nonnull;

/* loaded from: classes7.dex */
public class Jwt {

    @JsonProperty("expires_in")
    private final int expiry;

    @JsonProperty("access_token")
    private final String token;

    public Jwt(@Nonnull String str, int i) {
        this.token = (String) Objects.requireNonNull(str);
        this.expiry = i;
    }

    public String token() {
        return this.token;
    }

    public int expiry() {
        return this.expiry;
    }
}
