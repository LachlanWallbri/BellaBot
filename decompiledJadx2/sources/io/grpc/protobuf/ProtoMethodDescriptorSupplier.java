package io.grpc.protobuf;

import com.google.protobuf.Descriptors;
import javax.annotation.CheckReturnValue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ProtoMethodDescriptorSupplier extends ProtoServiceDescriptorSupplier {
    @CheckReturnValue
    Descriptors.MethodDescriptor getMethodDescriptor();
}
