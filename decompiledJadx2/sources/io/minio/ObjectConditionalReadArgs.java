package io.minio;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.amazonaws.services.p048s3.Headers;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import io.minio.ObjectReadArgs;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public abstract class ObjectConditionalReadArgs extends ObjectReadArgs {
    protected Long length;
    protected String matchETag;
    protected ZonedDateTime modifiedSince;
    protected String notMatchETag;
    protected Long offset;
    protected ZonedDateTime unmodifiedSince;

    public Long offset() {
        return this.offset;
    }

    public Long length() {
        return this.length;
    }

    public String matchETag() {
        return this.matchETag;
    }

    public String notMatchETag() {
        return this.notMatchETag;
    }

    public ZonedDateTime modifiedSince() {
        return this.modifiedSince;
    }

    public ZonedDateTime unmodifiedSince() {
        return this.unmodifiedSince;
    }

    public Multimap<String, String> getHeaders() {
        Long l = this.offset;
        Long l2 = this.length;
        if (l2 != null && l == null) {
            l = 0L;
        }
        String str = null;
        if (l != null) {
            str = "bytes=" + l + "-";
            if (l2 != null) {
                str = str + ((l.longValue() + l2.longValue()) - 1);
            }
        }
        HashMultimap create = HashMultimap.create();
        if (str != null) {
            create.put("Range", str);
        }
        String str2 = this.matchETag;
        if (str2 != null) {
            create.put("if-match", str2);
        }
        String str3 = this.notMatchETag;
        if (str3 != null) {
            create.put("if-none-match", str3);
        }
        ZonedDateTime zonedDateTime = this.modifiedSince;
        if (zonedDateTime != null) {
            create.put("if-modified-since", zonedDateTime.format(Time.HTTP_HEADER_DATE_FORMAT));
        }
        ZonedDateTime zonedDateTime2 = this.unmodifiedSince;
        if (zonedDateTime2 != null) {
            create.put("if-unmodified-since", zonedDateTime2.format(Time.HTTP_HEADER_DATE_FORMAT));
        }
        if (this.ssec != null) {
            create.putAll(Multimaps.forMap(this.ssec.headers()));
        }
        return create;
    }

    public Multimap<String, String> genCopyHeaders() {
        HashMultimap create = HashMultimap.create();
        String encodePath = S3Escaper.encodePath("/" + this.bucketName + "/" + this.objectName);
        if (this.versionId != null) {
            encodePath = encodePath + "?versionId=" + S3Escaper.encode(this.versionId);
        }
        create.put("x-amz-copy-source", encodePath);
        if (this.ssec != null) {
            create.putAll(Multimaps.forMap(this.ssec.copySourceHeaders()));
        }
        String str = this.matchETag;
        if (str != null) {
            create.put(Headers.COPY_SOURCE_IF_MATCH, str);
        }
        String str2 = this.notMatchETag;
        if (str2 != null) {
            create.put(Headers.COPY_SOURCE_IF_NO_MATCH, str2);
        }
        ZonedDateTime zonedDateTime = this.modifiedSince;
        if (zonedDateTime != null) {
            create.put(Headers.COPY_SOURCE_IF_MODIFIED_SINCE, zonedDateTime.format(Time.HTTP_HEADER_DATE_FORMAT));
        }
        ZonedDateTime zonedDateTime2 = this.unmodifiedSince;
        if (zonedDateTime2 != null) {
            create.put(Headers.COPY_SOURCE_IF_UNMODIFIED_SINCE, zonedDateTime2.format(Time.HTTP_HEADER_DATE_FORMAT));
        }
        return create;
    }

    /* loaded from: classes7.dex */
    public static abstract class Builder<B extends Builder<B, A>, A extends ObjectConditionalReadArgs> extends ObjectReadArgs.Builder<B, A> {
        private void validateLength(Long l) {
            if (l != null && l.longValue() <= 0) {
                throw new IllegalArgumentException("length should be greater than zero");
            }
        }

        private void validateOffset(Long l) {
            if (l != null && l.longValue() < 0) {
                throw new IllegalArgumentException("offset should be zero or greater");
            }
        }

        public B offset(final Long l) {
            validateOffset(l);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectConditionalReadArgs$Builder$s9fxo9pfu3buvGWBAbDNuLmffaA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectConditionalReadArgs) obj).offset = l;
                }
            });
            return this;
        }

        public B length(final Long l) {
            validateLength(l);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectConditionalReadArgs$Builder$3QBipcS86Bf_cviDnCP4zeAZF0o
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectConditionalReadArgs) obj).length = l;
                }
            });
            return this;
        }

        public B matchETag(final String str) {
            validateNullOrNotEmptyString(str, TransferTable.COLUMN_ETAG);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectConditionalReadArgs$Builder$qNxiNsiXS-X1DBTSVcCDM4R5Nsc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectConditionalReadArgs) obj).matchETag = str;
                }
            });
            return this;
        }

        public B notMatchETag(final String str) {
            validateNullOrNotEmptyString(str, TransferTable.COLUMN_ETAG);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectConditionalReadArgs$Builder$IzOkil23DGYhZ3MEyoa9icxFIlI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectConditionalReadArgs) obj).notMatchETag = str;
                }
            });
            return this;
        }

        public B modifiedSince(final ZonedDateTime zonedDateTime) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectConditionalReadArgs$Builder$MoOEm84TXC0WdD4HrRsjc319lAc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectConditionalReadArgs) obj).modifiedSince = zonedDateTime;
                }
            });
            return this;
        }

        public B unmodifiedSince(final ZonedDateTime zonedDateTime) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectConditionalReadArgs$Builder$SxwpgRozV3RJtnKW9Ad4NVGRFvQ
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectConditionalReadArgs) obj).unmodifiedSince = zonedDateTime;
                }
            });
            return this;
        }
    }

    @Override // io.minio.ObjectReadArgs, io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ObjectConditionalReadArgs) || !super.equals(obj)) {
            return false;
        }
        ObjectConditionalReadArgs objectConditionalReadArgs = (ObjectConditionalReadArgs) obj;
        return Objects.equals(this.offset, objectConditionalReadArgs.offset) && Objects.equals(this.length, objectConditionalReadArgs.length) && Objects.equals(this.matchETag, objectConditionalReadArgs.matchETag) && Objects.equals(this.notMatchETag, objectConditionalReadArgs.notMatchETag) && Objects.equals(this.modifiedSince, objectConditionalReadArgs.modifiedSince) && Objects.equals(this.unmodifiedSince, objectConditionalReadArgs.unmodifiedSince);
    }

    @Override // io.minio.ObjectReadArgs, io.minio.ObjectVersionArgs, io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.offset, this.length, this.matchETag, this.notMatchETag, this.modifiedSince, this.unmodifiedSince);
    }
}
