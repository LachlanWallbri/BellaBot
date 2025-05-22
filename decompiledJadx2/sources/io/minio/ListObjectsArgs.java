package io.minio;

import io.minio.BucketArgs;
import io.minio.ListObjectsArgs;
import java.util.Objects;
import java.util.function.Consumer;

/* loaded from: classes7.dex */
public class ListObjectsArgs extends BucketArgs {
    private String continuationToken;
    private boolean fetchOwner;
    private boolean includeUserMetadata;
    private boolean includeVersions;
    private String keyMarker;
    private boolean recursive;
    private boolean useApiVersion1;
    private String versionIdMarker;
    private String delimiter = "";
    private boolean useUrlEncodingType = true;
    private int maxKeys = 1000;
    private String prefix = "";

    public String delimiter() {
        return this.recursive ? "" : this.delimiter.isEmpty() ? "/" : this.delimiter;
    }

    public boolean useUrlEncodingType() {
        return this.useUrlEncodingType;
    }

    public String keyMarker() {
        return this.keyMarker;
    }

    public String marker() {
        return this.keyMarker;
    }

    public String startAfter() {
        return this.keyMarker;
    }

    public int maxKeys() {
        return this.maxKeys;
    }

    public String prefix() {
        return this.prefix;
    }

    public String continuationToken() {
        return this.continuationToken;
    }

    public boolean fetchOwner() {
        return this.fetchOwner;
    }

    public String versionIdMarker() {
        return this.versionIdMarker;
    }

    public boolean includeUserMetadata() {
        return this.includeUserMetadata;
    }

    public boolean recursive() {
        return this.recursive;
    }

    public boolean useApiVersion1() {
        return this.useApiVersion1;
    }

    public boolean includeVersions() {
        return this.includeVersions;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BucketArgs.Builder<Builder, ListObjectsArgs> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.minio.BucketArgs.Builder
        public void validate(ListObjectsArgs listObjectsArgs) {
            super.validate((Builder) listObjectsArgs);
            if ((!listObjectsArgs.useApiVersion1() && !listObjectsArgs.includeVersions()) || (listObjectsArgs.continuationToken() == null && !listObjectsArgs.fetchOwner() && !listObjectsArgs.includeUserMetadata())) {
                if (listObjectsArgs.versionIdMarker != null && listObjectsArgs.useApiVersion1()) {
                    throw new IllegalArgumentException("version ID marker is not supported for list objects version 1");
                }
                return;
            }
            throw new IllegalArgumentException("continuation token/fetch owner/include metadata are supported only for list objects version 2");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$delimiter$0(String str, ListObjectsArgs listObjectsArgs) {
            if (str == null) {
                str = "";
            }
            listObjectsArgs.delimiter = str;
        }

        public Builder delimiter(final String str) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$w6HeDlaj8YyXf7Yuk1-GiYIbFx0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ListObjectsArgs.Builder.lambda$delimiter$0(str, (ListObjectsArgs) obj);
                }
            });
            return this;
        }

        public Builder useUrlEncodingType(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$ArXmHV_yIxpFsQ3zdnX-gq9tB78
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).useUrlEncodingType = z;
                }
            });
            return this;
        }

        public Builder keyMarker(final String str) {
            validateNullOrNotEmptyString(str, "key marker");
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$ze1WEFEO-xSUL12uj_1NEzgUgAI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).keyMarker = str;
                }
            });
            return this;
        }

        public Builder marker(final String str) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$6hUAHdqEjH65funHjPloXjRwWvM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).keyMarker = str;
                }
            });
            return this;
        }

        public Builder startAfter(final String str) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$hdVh1nJdwc_JKh03fqGN6KTdm-w
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).keyMarker = str;
                }
            });
            return this;
        }

        public Builder maxKeys(final int i) {
            if (i < 1 || i > 1000) {
                throw new IllegalArgumentException("max keys must be between 1 and 1000");
            }
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$we32nowQRhLmP95uwkGRk6alF1c
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).maxKeys = i;
                }
            });
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$prefix$6(String str, ListObjectsArgs listObjectsArgs) {
            if (str == null) {
                str = "";
            }
            listObjectsArgs.prefix = str;
        }

        public Builder prefix(final String str) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$ij3DAvSkcGnnX48QWQbjA5KKCwk
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ListObjectsArgs.Builder.lambda$prefix$6(str, (ListObjectsArgs) obj);
                }
            });
            return this;
        }

        public Builder continuationToken(final String str) {
            validateNullOrNotEmptyString(str, "continuation token");
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$Nadn2RurXuTEGkZEbD6spsd6nyc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).continuationToken = str;
                }
            });
            return this;
        }

        public Builder fetchOwner(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$o_a0j2iXebHBD5YWuidw2gYoSOw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).fetchOwner = z;
                }
            });
            return this;
        }

        public Builder versionIdMarker(final String str) {
            validateNullOrNotEmptyString(str, "version ID marker");
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$eh0oMdmTASHAhJMbFP4NziQmCq0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).versionIdMarker = str;
                }
            });
            return this;
        }

        public Builder includeUserMetadata(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$zL7_85NMKZ0oP9QcjRRW7iVGcYc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).includeUserMetadata = z;
                }
            });
            return this;
        }

        public Builder recursive(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$F7LAF95wAKuLWsx1UHgjbQXtEHQ
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).recursive = z;
                }
            });
            return this;
        }

        public Builder useApiVersion1(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$UX8ADYAXpSCdADBy8fVaIA5TxOU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).useApiVersion1 = z;
                }
            });
            return this;
        }

        public Builder includeVersions(final boolean z) {
            this.operations.add(new Consumer() { // from class: io.minio.-$$Lambda$ListObjectsArgs$Builder$MuTR4cyehpyxvDhbqS0SUjDVQHs
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ListObjectsArgs) obj).includeVersions = z;
                }
            });
            return this;
        }
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListObjectsArgs) || !super.equals(obj)) {
            return false;
        }
        ListObjectsArgs listObjectsArgs = (ListObjectsArgs) obj;
        return this.useUrlEncodingType == listObjectsArgs.useUrlEncodingType && this.maxKeys == listObjectsArgs.maxKeys && this.fetchOwner == listObjectsArgs.fetchOwner && this.includeUserMetadata == listObjectsArgs.includeUserMetadata && this.recursive == listObjectsArgs.recursive && this.useApiVersion1 == listObjectsArgs.useApiVersion1 && this.includeVersions == listObjectsArgs.includeVersions && Objects.equals(this.delimiter, listObjectsArgs.delimiter) && Objects.equals(this.keyMarker, listObjectsArgs.keyMarker) && Objects.equals(this.prefix, listObjectsArgs.prefix) && Objects.equals(this.continuationToken, listObjectsArgs.continuationToken) && Objects.equals(this.versionIdMarker, listObjectsArgs.versionIdMarker);
    }

    @Override // io.minio.BucketArgs, io.minio.BaseArgs
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.delimiter, Boolean.valueOf(this.useUrlEncodingType), this.keyMarker, Integer.valueOf(this.maxKeys), this.prefix, this.continuationToken, Boolean.valueOf(this.fetchOwner), this.versionIdMarker, Boolean.valueOf(this.includeUserMetadata), Boolean.valueOf(this.recursive), Boolean.valueOf(this.useApiVersion1), Boolean.valueOf(this.includeVersions));
    }
}
