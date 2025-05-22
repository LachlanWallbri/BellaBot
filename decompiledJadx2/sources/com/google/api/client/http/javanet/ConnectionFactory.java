package com.google.api.client.http.javanet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ConnectionFactory {
    HttpURLConnection openConnection(URL url) throws IOException, ClassCastException;
}
