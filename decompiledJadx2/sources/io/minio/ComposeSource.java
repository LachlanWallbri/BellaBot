package io.minio;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.amazonaws.services.p048s3.Headers;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import io.minio.ObjectConditionalReadArgs;
import io.minio.errors.InternalException;
import java.util.Objects;
import org.simpleframework.xml.strategy.Name;

/* loaded from: classes7.dex */
public class ComposeSource extends ObjectConditionalReadArgs {
    private Long objectSize = null;
    private Multimap<String, String> headers = null;

    /* loaded from: classes7.dex */
    public static final class Builder extends ObjectConditionalReadArgs.Builder<Builder, ComposeSource> {
    }

    protected ComposeSource() {
    }

    public ComposeSource(ObjectConditionalReadArgs objectConditionalReadArgs) {
        this.extraHeaders = objectConditionalReadArgs.extraHeaders;
        this.extraQueryParams = objectConditionalReadArgs.extraQueryParams;
        this.bucketName = objectConditionalReadArgs.bucketName;
        this.region = objectConditionalReadArgs.region;
        this.objectName = objectConditionalReadArgs.objectName;
        this.versionId = objectConditionalReadArgs.versionId;
        this.ssec = objectConditionalReadArgs.ssec;
        this.offset = objectConditionalReadArgs.offset;
        this.length = objectConditionalReadArgs.length;
        this.matchETag = objectConditionalReadArgs.matchETag;
        this.notMatchETag = objectConditionalReadArgs.notMatchETag;
        this.modifiedSince = objectConditionalReadArgs.modifiedSince;
        this.unmodifiedSince = objectConditionalReadArgs.unmodifiedSince;
    }

    private void throwException(long j, long j2, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("source ");
        sb.append(this.bucketName);
        sb.append("/");
        sb.append(this.objectName);
        if (this.versionId != null) {
            sb.append("?versionId=");
            sb.append(this.versionId);
        }
        sb.append(": ");
        sb.append(str);
        sb.append(" ");
        sb.append(j2);
        sb.append(" is beyond object size ");
        sb.append(this.objectSize);
        throw new IllegalArgumentException(sb.toString());
    }

    private void validateSize(long j) {
        if (this.offset != null && this.offset.longValue() >= j) {
            throwException(j, this.offset.longValue(), TypedValues.Cycle.S_WAVE_OFFSET);
        }
        if (this.length != null) {
            if (this.length.longValue() > j) {
                throwException(j, this.length.longValue(), Name.LENGTH);
            }
            if (this.offset.longValue() + this.length.longValue() > j) {
                throwException(j, this.offset.longValue() + this.length.longValue(), "compose size");
            }
        }
    }

    public void buildHeaders(long j, String str) {
        validateSize(j);
        this.objectSize = Long.valueOf(j);
        Multimap<String, String> genCopyHeaders = genCopyHeaders();
        if (!genCopyHeaders.containsKey(Headers.COPY_SOURCE_IF_MATCH)) {
            genCopyHeaders.put(Headers.COPY_SOURCE_IF_MATCH, str);
        }
        this.headers = Multimaps.unmodifiableMultimap(genCopyHeaders);
    }

    public long objectSize() throws InternalException {
        Long l = this.objectSize;
        if (l == null) {
            throw new InternalException("buildHeaders(long objectSize, String etag) must be called prior to this method invocation", null);
        }
        return l.longValue();
    }

    public Multimap<String, String> headers() throws InternalException {
        Multimap<String, String> multimap = this.headers;
        if (multimap != null) {
            return multimap;
        }
        throw new InternalException("buildHeaders(long objectSize, String etag) must be called prior to this method invocation", null);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // io.minio.ObjectConditionalReadArgs, io.minio.ObjectReadArgs, io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComposeSource) || !super.equals(obj)) {
            return false;
        }
        ComposeSource composeSource = (ComposeSource) obj;
        return Objects.equals(this.objectSize, composeSource.objectSize) && Objects.equals(this.headers, composeSource.headers);
    }

    @Override // io.minio.ObjectConditionalReadArgs, io.minio.ObjectReadArgs, io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.objectSize, this.headers);
    }
}
