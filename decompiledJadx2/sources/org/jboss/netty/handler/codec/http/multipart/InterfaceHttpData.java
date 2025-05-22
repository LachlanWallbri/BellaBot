package org.jboss.netty.handler.codec.http.multipart;

/* loaded from: classes7.dex */
public interface InterfaceHttpData extends Comparable<InterfaceHttpData> {

    /* loaded from: classes7.dex */
    public enum HttpDataType {
        Attribute,
        FileUpload,
        InternalAttribute
    }

    HttpDataType getHttpDataType();

    String getName();
}
