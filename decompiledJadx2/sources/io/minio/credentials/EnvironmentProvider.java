package io.minio.credentials;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes7.dex */
public abstract class EnvironmentProvider implements Provider {
    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getProperty(@Nonnull String str) {
        String property = System.getProperty(str);
        return property != null ? property : System.getenv(str);
    }
}
