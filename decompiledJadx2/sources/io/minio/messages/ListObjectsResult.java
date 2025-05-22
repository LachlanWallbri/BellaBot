package io.minio.messages;

import com.google.common.base.MoreObjects;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/* loaded from: classes7.dex */
public abstract class ListObjectsResult {
    private static final List<DeleteMarker> deleteMarkers = Collections.unmodifiableList(new LinkedList());

    @ElementList(inline = true, name = "CommonPrefixes", required = false)
    private List<Prefix> commonPrefixes;

    @Element(name = "Delimiter", required = false)
    private String delimiter;

    @Element(name = "EncodingType", required = false)
    private String encodingType;

    @Element(name = "IsTruncated", required = false)
    private boolean isTruncated;

    @Element(name = "MaxKeys", required = false)
    private int maxKeys;

    @Element(name = "Name")
    private String name;

    @Element(name = "Prefix", required = false)
    private String prefix;

    public abstract List<? extends Item> contents();

    /* JADX INFO: Access modifiers changed from: protected */
    public String decodeIfNeeded(String str) {
        if (str == null) {
            return str;
        }
        try {
            return "url".equals(this.encodingType) ? URLDecoder.decode(str, StandardCharsets.UTF_8.name()) : str;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String name() {
        return this.name;
    }

    public String encodingType() {
        return this.encodingType;
    }

    public String prefix() {
        return decodeIfNeeded(this.prefix);
    }

    public String delimiter() {
        return this.delimiter;
    }

    public boolean isTruncated() {
        return this.isTruncated;
    }

    public int maxKeys() {
        return this.maxKeys;
    }

    public List<Prefix> commonPrefixes() {
        List list = this.commonPrefixes;
        if (list == null) {
            list = new LinkedList();
        }
        return Collections.unmodifiableList(list);
    }

    public List<DeleteMarker> deleteMarkers() {
        return deleteMarkers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T extends Item> List<T> emptyIfNull(List<T> list) {
        return Collections.unmodifiableList((List) MoreObjects.firstNonNull(list, new LinkedList()));
    }
}
