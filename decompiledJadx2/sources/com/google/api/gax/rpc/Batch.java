package com.google.api.gax.rpc;

import com.google.api.core.InternalApi;
import com.google.api.gax.batching.BatchMerger;
import com.google.api.gax.batching.ElementCounter;
import com.google.api.gax.batching.RequestBuilder;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
public class Batch<RequestT, ResponseT> {
    private long byteCount;
    private UnaryCallable<RequestT, ResponseT> callable;
    private final RequestBuilder<RequestT> requestBuilder;
    private final List<BatchedRequestIssuer<ResponseT>> requestIssuerList = new ArrayList();

    public Batch(BatchingDescriptor<RequestT, ResponseT> batchingDescriptor, RequestT requestt, UnaryCallable<RequestT, ResponseT> unaryCallable, BatchedFuture<ResponseT> batchedFuture) {
        this.requestBuilder = batchingDescriptor.getRequestBuilder();
        this.requestBuilder.appendRequest(requestt);
        this.callable = unaryCallable;
        this.requestIssuerList.add(new BatchedRequestIssuer<>(batchedFuture, batchingDescriptor.countElements(requestt)));
        this.byteCount = batchingDescriptor.countBytes(requestt);
    }

    public RequestT getRequest() {
        return this.requestBuilder.build();
    }

    public UnaryCallable<RequestT, ResponseT> getCallable() {
        return this.callable;
    }

    public List<BatchedRequestIssuer<ResponseT>> getRequestIssuerList() {
        return this.requestIssuerList;
    }

    public long getByteCount() {
        return this.byteCount;
    }

    public void merge(Batch<RequestT, ResponseT> batch) {
        this.requestBuilder.appendRequest(batch.getRequest());
        this.requestIssuerList.addAll(batch.requestIssuerList);
        if (this.callable == null) {
            this.callable = batch.callable;
        }
        this.byteCount += batch.byteCount;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class BatchElementCounter<RequestT, ResponseT> implements ElementCounter<Batch<RequestT, ResponseT>> {
        private final BatchingDescriptor<RequestT, ResponseT> batchingDescriptor;

        /* JADX INFO: Access modifiers changed from: package-private */
        public BatchElementCounter(BatchingDescriptor<RequestT, ResponseT> batchingDescriptor) {
            this.batchingDescriptor = batchingDescriptor;
        }

        @Override // com.google.api.gax.batching.ElementCounter
        public long count(Batch<RequestT, ResponseT> batch) {
            return this.batchingDescriptor.countElements(batch.getRequest());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class BatchByteCounter<RequestT, ResponseT> implements ElementCounter<Batch<RequestT, ResponseT>> {
        @Override // com.google.api.gax.batching.ElementCounter
        public long count(Batch<RequestT, ResponseT> batch) {
            return batch.getByteCount();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static class BatchMergerImpl<RequestT, ResponseT> implements BatchMerger<Batch<RequestT, ResponseT>> {
        @Override // com.google.api.gax.batching.BatchMerger
        public void merge(Batch<RequestT, ResponseT> batch, Batch<RequestT, ResponseT> batch2) {
            batch.merge(batch2);
        }
    }
}
