package com.google.auth.oauth2;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class SystemEnvironmentProvider implements EnvironmentProvider {
    static final SystemEnvironmentProvider INSTANCE = new SystemEnvironmentProvider();

    private SystemEnvironmentProvider() {
    }

    @Override // com.google.auth.oauth2.EnvironmentProvider
    public String getEnv(String str) {
        return System.getenv(str);
    }

    public static SystemEnvironmentProvider getInstance() {
        return INSTANCE;
    }
}
