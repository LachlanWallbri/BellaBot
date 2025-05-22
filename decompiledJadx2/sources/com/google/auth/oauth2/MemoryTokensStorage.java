package com.google.auth.oauth2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class MemoryTokensStorage implements TokenStore {
    private final Map<String, String> tokensStorage = new HashMap();

    @Override // com.google.auth.oauth2.TokenStore
    public String load(String str) throws IOException {
        return this.tokensStorage.get(str);
    }

    @Override // com.google.auth.oauth2.TokenStore
    public void store(String str, String str2) throws IOException {
        this.tokensStorage.put(str, str2);
    }

    @Override // com.google.auth.oauth2.TokenStore
    public void delete(String str) throws IOException {
        this.tokensStorage.remove(str);
    }
}
