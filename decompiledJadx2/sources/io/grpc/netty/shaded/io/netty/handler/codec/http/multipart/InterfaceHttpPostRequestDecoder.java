package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface InterfaceHttpPostRequestDecoder {
    void cleanFiles();

    InterfaceHttpData currentPartialHttpData();

    void destroy();

    InterfaceHttpData getBodyHttpData(String str);

    List<InterfaceHttpData> getBodyHttpDatas();

    List<InterfaceHttpData> getBodyHttpDatas(String str);

    int getDiscardThreshold();

    boolean hasNext();

    boolean isMultipart();

    InterfaceHttpData next();

    InterfaceHttpPostRequestDecoder offer(HttpContent httpContent);

    void removeHttpDataFromClean(InterfaceHttpData interfaceHttpData);

    void setDiscardThreshold(int i);
}
