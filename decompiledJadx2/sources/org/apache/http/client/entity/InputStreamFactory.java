package org.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface InputStreamFactory {
    InputStream create(InputStream inputStream) throws IOException;
}
