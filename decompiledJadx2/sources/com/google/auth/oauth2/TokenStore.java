package com.google.auth.oauth2;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface TokenStore {
    void delete(String str) throws IOException;

    String load(String str) throws IOException;

    void store(String str, String str2) throws IOException;
}
