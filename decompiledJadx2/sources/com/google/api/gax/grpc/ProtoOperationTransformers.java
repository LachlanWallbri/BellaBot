package com.google.api.gax.grpc;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.longrunning.OperationSnapshot;
import com.google.api.gax.rpc.ApiExceptionFactory;
import com.google.api.gax.rpc.StatusCode;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for use by generated code is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class ProtoOperationTransformers {
    private ProtoOperationTransformers() {
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class ResponseTransformer<ResponseT extends Message> implements ApiFunction<OperationSnapshot, ResponseT> {
        private final AnyTransformer<ResponseT> transformer;

        private ResponseTransformer(Class<ResponseT> cls) {
            this.transformer = new AnyTransformer<>(cls);
        }

        @Override // com.google.api.core.ApiFunction
        public ResponseT apply(OperationSnapshot operationSnapshot) {
            if (!operationSnapshot.getErrorCode().getCode().equals(StatusCode.Code.OK)) {
                throw ApiExceptionFactory.createException("Operation with name \"" + operationSnapshot.getName() + "\" failed with status = " + operationSnapshot.getErrorCode() + " and message = " + operationSnapshot.getErrorMessage(), null, operationSnapshot.getErrorCode(), false);
            }
            try {
                return this.transformer.apply((Any) operationSnapshot.getResponse());
            } catch (RuntimeException e) {
                throw ApiExceptionFactory.createException("Operation with name \"" + operationSnapshot.getName() + "\" succeeded, but encountered a problem unpacking it.", e, operationSnapshot.getErrorCode(), false);
            }
        }

        public static <ResponseT extends Message> ResponseTransformer<ResponseT> create(Class<ResponseT> cls) {
            return new ResponseTransformer<>(cls);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class MetadataTransformer<MetadataT extends Message> implements ApiFunction<OperationSnapshot, MetadataT> {
        private final AnyTransformer<MetadataT> transformer;

        private MetadataTransformer(Class<MetadataT> cls) {
            this.transformer = new AnyTransformer<>(cls);
        }

        @Override // com.google.api.core.ApiFunction
        public MetadataT apply(OperationSnapshot operationSnapshot) {
            try {
                return this.transformer.apply(operationSnapshot.getMetadata() != null ? (Any) operationSnapshot.getMetadata() : null);
            } catch (RuntimeException e) {
                throw ApiExceptionFactory.createException("Polling operation with name \"" + operationSnapshot.getName() + "\" succeeded, but encountered a problem unpacking it.", e, operationSnapshot.getErrorCode(), false);
            }
        }

        public static <ResponseT extends Message> MetadataTransformer<ResponseT> create(Class<ResponseT> cls) {
            return new MetadataTransformer<>(cls);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class AnyTransformer<PackedT extends Message> implements ApiFunction<Any, PackedT> {
        private final Class<PackedT> packedClass;

        public AnyTransformer(Class<PackedT> cls) {
            this.packedClass = cls;
        }

        @Override // com.google.api.core.ApiFunction
        public PackedT apply(Any any) {
            if (any != null) {
                try {
                    if (this.packedClass != null) {
                        return (PackedT) any.unpack(this.packedClass);
                    }
                } catch (InvalidProtocolBufferException | ClassCastException unused) {
                    throw new IllegalStateException("Failed to unpack object from 'any' field. Expected " + this.packedClass.getName() + ", found " + any.getTypeUrl());
                }
            }
            return null;
        }
    }
}
