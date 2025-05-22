package io.minio.messages;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Map;
import org.simpleframework.xml.Element;

/* loaded from: classes7.dex */
public abstract class Item {
    private static final String UTF_8 = StandardCharsets.UTF_8.toString();
    private String encodingType;

    @Element(name = "ETag", required = false)
    private String etag;
    private boolean isDir;

    @Element(name = "IsLatest", required = false)
    private boolean isLatest;

    @Element(name = "LastModified")
    private ResponseDate lastModified;

    @Element(name = "Key")
    private String objectName;

    @Element(name = "Owner", required = false)
    private Owner owner;

    @Element(name = "Size", required = false)
    private long size;

    @Element(name = "StorageClass", required = false)
    private String storageClass;

    @Element(name = "UserMetadata", required = false)
    private Metadata userMetadata;

    @Element(name = "VersionId", required = false)
    private String versionId;

    public Item() {
        this.isDir = false;
        this.encodingType = null;
    }

    public Item(String str) {
        this.isDir = false;
        this.encodingType = null;
        this.objectName = str;
        this.isDir = true;
    }

    public void setEncodingType(String str) {
        this.encodingType = str;
    }

    public String objectName() {
        try {
            return "url".equals(this.encodingType) ? URLDecoder.decode(this.objectName, UTF_8) : this.objectName;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public ZonedDateTime lastModified() {
        return this.lastModified.zonedDateTime();
    }

    public String etag() {
        return this.etag;
    }

    public long size() {
        return this.size;
    }

    public String storageClass() {
        return this.storageClass;
    }

    public Owner owner() {
        return this.owner;
    }

    public Map<String, String> userMetadata() {
        Metadata metadata = this.userMetadata;
        if (metadata == null) {
            return null;
        }
        return metadata.get();
    }

    public boolean isLatest() {
        return this.isLatest;
    }

    public String versionId() {
        return this.versionId;
    }

    public boolean isDir() {
        return this.isDir;
    }

    public boolean isDeleteMarker() {
        return this instanceof DeleteMarker;
    }
}
