package io.minio.credentials;

/* loaded from: classes7.dex */
public class MinioEnvironmentProvider extends EnvironmentProvider {
    @Override // io.minio.credentials.Provider
    public Credentials fetch() {
        return new Credentials(getProperty("MINIO_ACCESS_KEY"), getProperty("MINIO_SECRET_KEY"), null, null);
    }
}
