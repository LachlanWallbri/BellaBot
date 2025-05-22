package com.google.api.gax.rpc;

import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.rpc.UnaryCallSettings;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
/* loaded from: classes2.dex */
public final class PagedCallSettings<RequestT, ResponseT, PagedListResponseT> extends UnaryCallSettings<RequestT, ResponseT> {
    private final PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory;

    public PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> getPagedListResponseFactory() {
        return this.pagedListResponseFactory;
    }

    public static <RequestT, ResponseT, PagedListResponseT> Builder<RequestT, ResponseT, PagedListResponseT> newBuilder(PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory) {
        return new Builder<>(pagedListResponseFactory);
    }

    @Override // com.google.api.gax.rpc.UnaryCallSettings
    public final Builder<RequestT, ResponseT, PagedListResponseT> toBuilder() {
        return new Builder<>(this);
    }

    private PagedCallSettings(Builder<RequestT, ResponseT, PagedListResponseT> builder) {
        super(builder);
        this.pagedListResponseFactory = ((Builder) builder).pagedListResponseFactory;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder<RequestT, ResponseT, PagedListResponseT> extends UnaryCallSettings.Builder<RequestT, ResponseT> {
        private PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory;

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public /* bridge */ /* synthetic */ UnaryCallSettings.Builder setRetryableCodes(Set set) {
            return setRetryableCodes((Set<StatusCode.Code>) set);
        }

        public Builder(PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory) {
            this.pagedListResponseFactory = pagedListResponseFactory;
        }

        public Builder(PagedCallSettings<RequestT, ResponseT, PagedListResponseT> pagedCallSettings) {
            super(pagedCallSettings);
            this.pagedListResponseFactory = ((PagedCallSettings) pagedCallSettings).pagedListResponseFactory;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public Builder<RequestT, ResponseT, PagedListResponseT> setRetryableCodes(Set<StatusCode.Code> set) {
            super.setRetryableCodes(set);
            return this;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public Builder<RequestT, ResponseT, PagedListResponseT> setRetryableCodes(StatusCode.Code... codeArr) {
            super.setRetryableCodes(codeArr);
            return this;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public Builder<RequestT, ResponseT, PagedListResponseT> setRetrySettings(RetrySettings retrySettings) {
            super.setRetrySettings(retrySettings);
            return this;
        }

        public PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> getPagedListResponseFactory() {
            return this.pagedListResponseFactory;
        }

        @Override // com.google.api.gax.rpc.UnaryCallSettings.Builder
        public PagedCallSettings<RequestT, ResponseT, PagedListResponseT> build() {
            return new PagedCallSettings<>(this);
        }
    }
}
