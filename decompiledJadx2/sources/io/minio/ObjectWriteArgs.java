package io.minio;

import com.amazonaws.services.p048s3.Headers;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import io.minio.ObjectArgs;
import io.minio.messages.Retention;
import io.minio.messages.Tags;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import okhttp3.HttpUrl;

/* loaded from: classes7.dex */
public abstract class ObjectWriteArgs extends ObjectArgs {
    public static final int MAX_MULTIPART_COUNT = 10000;
    public static final long MAX_OBJECT_SIZE = 5497558138880L;
    public static final long MAX_PART_SIZE = 5368709120L;
    public static final int MIN_MULTIPART_SIZE = 5242880;
    protected boolean legalHold;
    protected Retention retention;
    protected ServerSideEncryption sse;
    protected Multimap<String, String> headers = Multimaps.unmodifiableMultimap(HashMultimap.create());
    protected Multimap<String, String> userMetadata = Multimaps.unmodifiableMultimap(HashMultimap.create());
    protected Tags tags = new Tags();

    public Multimap<String, String> headers() {
        return this.headers;
    }

    public Multimap<String, String> userMetadata() {
        return this.userMetadata;
    }

    public ServerSideEncryption sse() {
        return this.sse;
    }

    public Tags tags() {
        return this.tags;
    }

    public Retention retention() {
        return this.retention;
    }

    public boolean legalHold() {
        return this.legalHold;
    }

    public Multimap<String, String> genHeaders() {
        HashMultimap create = HashMultimap.create();
        create.putAll(this.headers);
        create.putAll(this.userMetadata);
        ServerSideEncryption serverSideEncryption = this.sse;
        if (serverSideEncryption != null) {
            create.putAll(Multimaps.forMap(serverSideEncryption.headers()));
        }
        String str = (String) this.tags.get().entrySet().stream().map(new Function() { // from class: io.minio.-$$Lambda$ObjectWriteArgs$44eF_XzPpogWqq_ybpWdMPusewE
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ObjectWriteArgs.lambda$genHeaders$0((Map.Entry) obj);
            }
        }).collect(Collectors.joining("&"));
        if (!str.isEmpty()) {
            create.put(Headers.S3_TAGGING, str);
        }
        Retention retention = this.retention;
        if (retention != null && retention.mode() != null) {
            create.put("x-amz-object-lock-mode", this.retention.mode().name());
            create.put("x-amz-object-lock-retain-until-date", this.retention.retainUntilDate().format(Time.RESPONSE_DATE_FORMAT));
        }
        if (this.legalHold) {
            create.put("x-amz-object-lock-legal-hold", "ON");
        }
        return create;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$genHeaders$0(Map.Entry entry) {
        return S3Escaper.encode((String) entry.getKey()) + "=" + S3Escaper.encode((String) entry.getValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateSse(HttpUrl httpUrl) {
        checkSse(this.sse, httpUrl);
    }

    /* loaded from: classes7.dex */
    public static abstract class Builder<B extends Builder<B, A>, A extends ObjectWriteArgs> extends ObjectArgs.Builder<B, A> {
        public B headers(Map<String, String> map) {
            final Multimap<String, String> multimap = toMultimap(map);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectWriteArgs$Builder$8WUcazxLnEyo4nF-NzE8ZQZJVQ8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectWriteArgs) obj).headers = Multimap.this;
                }
            });
            return this;
        }

        public B headers(Multimap<String, String> multimap) {
            final Multimap<String, String> copyMultimap = copyMultimap(multimap);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectWriteArgs$Builder$Dqmi0uiqGHnNqmczZRhIscj_y3U
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectWriteArgs) obj).headers = Multimap.this;
                }
            });
            return this;
        }

        public B userMetadata(Map<String, String> map) {
            return userMetadata(map == null ? null : Multimaps.forMap(map));
        }

        public B userMetadata(Multimap<String, String> multimap) {
            HashMultimap create = HashMultimap.create();
            if (multimap != null) {
                for (String str : multimap.keySet()) {
                    StringBuilder sb = new StringBuilder();
                    String lowerCase = str.toLowerCase(Locale.US);
                    String str2 = Headers.S3_USER_METADATA_PREFIX;
                    if (lowerCase.startsWith(Headers.S3_USER_METADATA_PREFIX)) {
                        str2 = "";
                    }
                    sb.append(str2);
                    sb.append(str);
                    create.putAll(sb.toString(), multimap.get(str));
                }
            }
            final Multimap unmodifiableMultimap = Multimaps.unmodifiableMultimap(create);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectWriteArgs$Builder$p-m3pnx-zOC8leUvbhxyrlLYPGs
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectWriteArgs) obj).userMetadata = Multimap.this;
                }
            });
            return this;
        }

        public B sse(final ServerSideEncryption serverSideEncryption) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectWriteArgs$Builder$aGCTL570HKKYNOPH5bUu6vfX6DE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectWriteArgs) obj).sse = ServerSideEncryption.this;
                }
            });
            return this;
        }

        public B tags(Map<String, String> map) {
            final Tags tags = map == null ? new Tags() : Tags.newObjectTags(map);
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectWriteArgs$Builder$DmUSlTCnZtaXvOTEHjb4-vTr3NM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectWriteArgs) obj).tags = Tags.this;
                }
            });
            return this;
        }

        public B tags(Tags tags) {
            final Tags tags2 = tags == null ? new Tags() : Tags.newObjectTags(tags.get());
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectWriteArgs$Builder$HsBVhpfM_O4aX7BLrpqXFp2Ke4g
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectWriteArgs) obj).tags = Tags.this;
                }
            });
            return this;
        }

        public B retention(final Retention retention) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectWriteArgs$Builder$VJhusFMYjGEnOsX_T0bfeO3wVc8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectWriteArgs) obj).retention = Retention.this;
                }
            });
            return this;
        }

        public B legalHold(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ObjectWriteArgs$Builder$Lm14xeO1mfNH-gSKumR2e9i5sv0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ObjectWriteArgs) obj).legalHold = z;
                }
            });
            return this;
        }
    }

    @Override // io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ObjectWriteArgs) || !super.equals(obj)) {
            return false;
        }
        ObjectWriteArgs objectWriteArgs = (ObjectWriteArgs) obj;
        return this.legalHold == objectWriteArgs.legalHold && Objects.equals(this.headers, objectWriteArgs.headers) && Objects.equals(this.userMetadata, objectWriteArgs.userMetadata) && Objects.equals(this.sse, objectWriteArgs.sse) && Objects.equals(this.tags, objectWriteArgs.tags) && Objects.equals(this.retention, objectWriteArgs.retention);
    }

    @Override // io.minio.ObjectArgs, io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.headers, this.userMetadata, this.sse, this.tags, this.retention, Boolean.valueOf(this.legalHold));
    }
}
