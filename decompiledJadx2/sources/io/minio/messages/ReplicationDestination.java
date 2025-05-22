package io.minio.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.http.HttpHeaders;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = HttpHeaders.DESTINATION)
/* loaded from: classes7.dex */
public class ReplicationDestination {

    @Element(name = "AccessControlTranslation", required = false)
    private AccessControlTranslation accessControlTranslation;

    @Element(name = "Account", required = false)
    private String account;

    @Element(name = "Bucket")
    private String bucketArn;

    @Element(name = "EncryptionConfiguration", required = false)
    private EncryptionConfiguration encryptionConfiguration;

    @Element(name = "Metrics", required = false)
    private Metrics metrics;

    @Element(name = "ReplicationTime", required = false)
    private ReplicationTime replicationTime;

    @Element(name = "StorageClass", required = false)
    private String storageClass;

    public ReplicationDestination(@Element(name = "AccessControlTranslation", required = false) @Nullable AccessControlTranslation accessControlTranslation, @Element(name = "Account", required = false) @Nullable String str, @Element(name = "Bucket") @Nonnull String str2, @Element(name = "EncryptionConfiguration", required = false) @Nullable EncryptionConfiguration encryptionConfiguration, @Element(name = "Metrics", required = false) @Nullable Metrics metrics, @Element(name = "ReplicationTime", required = false) @Nullable ReplicationTime replicationTime, @Element(name = "StorageClass", required = false) @Nullable String str3) {
        this.accessControlTranslation = accessControlTranslation;
        this.account = str;
        this.bucketArn = (String) Objects.requireNonNull(str2, "Bucket ARN must not be null");
        this.encryptionConfiguration = encryptionConfiguration;
        this.metrics = metrics;
        this.replicationTime = replicationTime;
        this.storageClass = str3;
    }

    public AccessControlTranslation accessControlTranslation() {
        return this.accessControlTranslation;
    }

    public String account() {
        return this.account;
    }

    public String bucketArn() {
        return this.bucketArn;
    }

    public EncryptionConfiguration encryptionConfiguration() {
        return this.encryptionConfiguration;
    }

    public Metrics metrics() {
        return this.metrics;
    }

    public ReplicationTime replicationTime() {
        return this.replicationTime;
    }

    public String storageClass() {
        return this.storageClass;
    }
}
