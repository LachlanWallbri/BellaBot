package io.minio.credentials;

import com.aliyun.alink.p022h2.api.Constraint;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.ProviderException;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes7.dex */
public class MinioClientConfigProvider extends EnvironmentProvider {
    private final String alias;
    private final String filename;
    private final ObjectMapper mapper;

    public MinioClientConfigProvider(@Nullable String str, @Nullable String str2) {
        if (str != null && str.isEmpty()) {
            throw new IllegalArgumentException("Filename must not be empty");
        }
        if (str2 != null && str2.isEmpty()) {
            throw new IllegalArgumentException("Alias must not be empty");
        }
        this.filename = str;
        this.alias = str2;
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @Override // io.minio.credentials.Provider
    public Credentials fetch() {
        String str = this.filename;
        if (str == null) {
            str = getProperty("MINIO_SHARED_CREDENTIALS_FILE");
        }
        if (str == null) {
            str = Paths.get(System.getProperty("user.home"), System.getProperty("os.name").toLowerCase(Locale.US).contains("windows") ? "mc" : ".mc", "config.json").toString();
        }
        String str2 = this.alias;
        if (str2 == null) {
            str2 = getProperty("MINIO_ALIAS");
        }
        if (str2 == null) {
            str2 = "s3";
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                Map<String, String> map = ((McConfig) this.mapper.readValue(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8), McConfig.class)).get(str2);
                if (map == null) {
                    throw new ProviderException("Alias " + str2 + " does not exist in MinioClient configuration file");
                }
                String str3 = map.get(Constraint.PARAM_ACCESS_KEY);
                String str4 = map.get("secretKey");
                if (str3 == null) {
                    throw new ProviderException("Access key does not exist in alias " + str2 + " in MinioClient configuration file");
                }
                if (str4 == null) {
                    throw new ProviderException("Secret key does not exist in alias " + str2 + " in MinioClient configuration file");
                }
                Credentials credentials = new Credentials(str3, str4, null, null);
                fileInputStream.close();
                return credentials;
            } finally {
            }
        } catch (IOException e) {
            throw new ProviderException("Unable to read MinioClient configuration file", e);
        }
    }

    /* loaded from: classes7.dex */
    public static class McConfig {
        private Map<String, Map<String, String>> hosts;

        public Map<String, String> get(String str) {
            return this.hosts.get(str);
        }
    }
}
