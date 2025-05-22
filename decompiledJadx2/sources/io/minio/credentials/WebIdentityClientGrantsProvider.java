package io.minio.credentials;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/* loaded from: classes7.dex */
public abstract class WebIdentityClientGrantsProvider extends AssumeRoleBaseProvider {
    protected final Integer durationSeconds;
    protected final String policy;
    protected final String roleArn;
    protected final String roleSessionName;
    protected final HttpUrl stsEndpoint;
    private final Supplier<Jwt> supplier;
    public static final int MIN_DURATION_SECONDS = (int) TimeUnit.MINUTES.toSeconds(15);
    public static final int MAX_DURATION_SECONDS = (int) TimeUnit.DAYS.toSeconds(7);
    private static final RequestBody EMPTY_BODY = RequestBody.create(new byte[0], MediaType.parse("application/octet-stream"));

    protected abstract HttpUrl.Builder newUrlBuilder(Jwt jwt);

    public WebIdentityClientGrantsProvider(@Nonnull Supplier<Jwt> supplier, @Nonnull String str, @Nullable Integer num, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable OkHttpClient okHttpClient) {
        super(okHttpClient);
        this.supplier = (Supplier) Objects.requireNonNull(supplier, "JWT token supplier must not be null");
        this.stsEndpoint = (HttpUrl) Objects.requireNonNull(HttpUrl.parse((String) Objects.requireNonNull(str, "STS endpoint cannot be empty")), "Invalid STS endpoint");
        this.durationSeconds = num;
        this.policy = str2;
        this.roleArn = str3;
        this.roleSessionName = str4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getDurationSeconds(int i) {
        int i2;
        Integer num = this.durationSeconds;
        if (num != null && num.intValue() > 0) {
            i = this.durationSeconds.intValue();
        }
        int i3 = MAX_DURATION_SECONDS;
        return i > i3 ? i3 : (i > 0 && i < (i2 = MIN_DURATION_SECONDS)) ? i2 : i;
    }

    @Override // io.minio.credentials.AssumeRoleBaseProvider
    protected Request getRequest() {
        return new Request.Builder().url(newUrlBuilder(this.supplier.get()).build()).method("POST", EMPTY_BODY).build();
    }
}
