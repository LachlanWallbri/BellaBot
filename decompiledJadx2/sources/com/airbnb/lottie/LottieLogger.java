package com.airbnb.lottie;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface LottieLogger {
    void debug(String str);

    void debug(String str, Throwable th);

    void error(String str, Throwable th);

    void warning(String str);

    void warning(String str, Throwable th);
}
