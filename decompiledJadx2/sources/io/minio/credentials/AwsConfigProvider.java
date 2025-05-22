package io.minio.credentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.ProviderException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Nullable;

/* loaded from: classes7.dex */
public class AwsConfigProvider extends EnvironmentProvider {
    private final String filename;
    private final String profile;

    public AwsConfigProvider(@Nullable String str, @Nullable String str2) {
        if (str != null && str.isEmpty()) {
            throw new IllegalArgumentException("Filename must not be empty");
        }
        if (str2 != null && str2.isEmpty()) {
            throw new IllegalArgumentException("Profile must not be empty");
        }
        this.filename = str;
        this.profile = str2;
    }

    @Override // io.minio.credentials.Provider
    public Credentials fetch() {
        String str = this.filename;
        if (str == null) {
            str = getProperty("AWS_SHARED_CREDENTIALS_FILE");
        }
        if (str == null) {
            str = Paths.get(System.getProperty("user.home"), ".aws", "credentials").toString();
        }
        String str2 = this.profile;
        if (str2 == null) {
            str2 = getProperty("AWS_PROFILE");
        }
        if (str2 == null) {
            str2 = "default";
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                Properties properties = unmarshal(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8)).get(str2);
                if (properties == null) {
                    throw new ProviderException("Profile " + str2 + " does not exist in AWS credential file");
                }
                String property = properties.getProperty("aws_access_key_id");
                String property2 = properties.getProperty("aws_secret_access_key");
                String property3 = properties.getProperty("aws_session_token");
                if (property == null) {
                    throw new ProviderException("Access key does not exist in profile " + str2 + " in AWS credential file");
                }
                if (property2 == null) {
                    throw new ProviderException("Secret key does not exist in profile " + str2 + " in AWS credential file");
                }
                Credentials credentials = new Credentials(property, property2, property3, null);
                fileInputStream.close();
                return credentials;
            } finally {
            }
        } catch (IOException e) {
            throw new ProviderException("Unable to read AWS credential file", e);
        }
    }

    private Map<String, Properties> unmarshal(Reader reader) throws IOException {
        return new Ini().unmarshal(reader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class Ini {
        private Map<String, Properties> result;

        private Ini() {
            this.result = new HashMap();
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [io.minio.credentials.AwsConfigProvider$Ini$1] */
        public Map<String, Properties> unmarshal(Reader reader) throws IOException {
            new Properties() { // from class: io.minio.credentials.AwsConfigProvider.Ini.1
                private Properties section;

                @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
                public Object put(Object obj, Object obj2) {
                    String trim = (((String) obj) + " " + obj2).trim();
                    if (trim.startsWith("[") && trim.endsWith("]")) {
                        this.section = new Properties();
                        return Ini.this.result.put(trim.substring(1, trim.length() - 1), this.section);
                    }
                    return this.section.put(obj, obj2);
                }

                @Override // java.util.Hashtable, java.util.Map
                public boolean equals(Object obj) {
                    return super.equals(obj);
                }

                @Override // java.util.Hashtable, java.util.Map
                public int hashCode() {
                    return super.hashCode();
                }
            }.load(reader);
            return this.result;
        }
    }
}
