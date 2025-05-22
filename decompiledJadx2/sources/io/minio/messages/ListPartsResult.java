package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "ListPartsResult", strict = false)
/* loaded from: classes7.dex */
public class ListPartsResult {

    @Element(name = "Bucket")
    private String bucketName;

    @Element(name = "Initiator")
    private Initiator initiator;

    @Element(name = "IsTruncated")
    private boolean isTruncated;

    @Element(name = "MaxParts")
    private int maxParts;

    @Element(name = "NextPartNumberMarker")
    private int nextPartNumberMarker;

    @Element(name = "Key")
    private String objectName;

    @Element(name = "Owner")
    private Owner owner;

    @ElementList(inline = true, name = "Part", required = false)
    private List<Part> partList;

    @Element(name = "PartNumberMarker")
    private int partNumberMarker;

    @Element(name = "StorageClass")
    private String storageClass;

    public String bucketName() {
        return this.bucketName;
    }

    public String objectName() {
        return this.objectName;
    }

    public String storageClass() {
        return this.storageClass;
    }

    public Initiator initiator() {
        return this.initiator;
    }

    public Owner owner() {
        return this.owner;
    }

    public int maxParts() {
        return this.maxParts;
    }

    public boolean isTruncated() {
        return this.isTruncated;
    }

    public int partNumberMarker() {
        return this.partNumberMarker;
    }

    public int nextPartNumberMarker() {
        return this.nextPartNumberMarker;
    }

    public List<Part> partList() {
        List<Part> list = this.partList;
        if (list == null) {
            return Collections.unmodifiableList(new LinkedList());
        }
        return Collections.unmodifiableList(list);
    }
}
