package com.google.api.gax.rpc;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.common.util.concurrent.MoreExecutors;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class TranslatingUnaryCallable<InnerRequestT, InnerResponseT, OuterRequestT, OuterResponseT> extends UnaryCallable<OuterRequestT, OuterResponseT> {
    private final UnaryCallable<InnerRequestT, InnerResponseT> innerUnaryCallable;
    private final ApiFunction<OuterRequestT, InnerRequestT> requestTransformer;
    private final ApiFunction<InnerResponseT, OuterResponseT> responseTransformer;

    private TranslatingUnaryCallable(UnaryCallable<InnerRequestT, InnerResponseT> unaryCallable, ApiFunction<OuterRequestT, InnerRequestT> apiFunction, ApiFunction<InnerResponseT, OuterResponseT> apiFunction2) {
        this.innerUnaryCallable = unaryCallable;
        this.requestTransformer = apiFunction;
        this.responseTransformer = apiFunction2;
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<OuterResponseT> futureCall(OuterRequestT outerrequestt, ApiCallContext apiCallContext) {
        return ApiFutures.transform(this.innerUnaryCallable.futureCall(this.requestTransformer.apply(outerrequestt), apiCallContext), this.responseTransformer, MoreExecutors.directExecutor());
    }

    public static <InnerRequestT, InnerResponseT, OuterRequestT, OuterResponseT> TranslatingUnaryCallable<InnerRequestT, InnerResponseT, OuterRequestT, OuterResponseT> create(UnaryCallable<InnerRequestT, InnerResponseT> unaryCallable, ApiFunction<OuterRequestT, InnerRequestT> apiFunction, ApiFunction<InnerResponseT, OuterResponseT> apiFunction2) {
        return new TranslatingUnaryCallable<>(unaryCallable, apiFunction, apiFunction2);
    }
}
