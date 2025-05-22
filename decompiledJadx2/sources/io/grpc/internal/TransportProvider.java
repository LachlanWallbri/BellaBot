package io.grpc.internal;

import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface TransportProvider {
    @Nullable
    ClientTransport obtainActiveTransport();
}
