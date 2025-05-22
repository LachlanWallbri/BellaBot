package io.grpc;

import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Drainable {
    int drainTo(OutputStream outputStream) throws IOException;
}
