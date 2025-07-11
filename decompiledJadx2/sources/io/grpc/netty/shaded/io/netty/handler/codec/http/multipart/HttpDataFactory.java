package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface HttpDataFactory {
    void cleanAllHttpData();

    @Deprecated
    void cleanAllHttpDatas();

    void cleanRequestHttpData(HttpRequest httpRequest);

    @Deprecated
    void cleanRequestHttpDatas(HttpRequest httpRequest);

    Attribute createAttribute(HttpRequest httpRequest, String str);

    Attribute createAttribute(HttpRequest httpRequest, String str, long j);

    Attribute createAttribute(HttpRequest httpRequest, String str, String str2);

    FileUpload createFileUpload(HttpRequest httpRequest, String str, String str2, String str3, String str4, Charset charset, long j);

    void removeHttpDataFromClean(HttpRequest httpRequest, InterfaceHttpData interfaceHttpData);

    void setMaxLimit(long j);
}
