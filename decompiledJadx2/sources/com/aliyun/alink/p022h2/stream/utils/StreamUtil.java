package com.aliyun.alink.p022h2.stream.utils;

import com.aliyun.alink.p022h2.api.Http2StreamListener;
import com.aliyun.alink.p022h2.api.StreamServiceContext;
import com.aliyun.alink.p022h2.connection.Connection;
import io.netty.handler.codec.http2.Http2Headers;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class StreamUtil {
    public static final String CONTENT_LENGTH = "content-length";
    public static final String DATA_STREAM_ID = "x-data-stream-id";
    public static final String FILE_UPLOAD_ID = "x-file-upload-id";
    public static final String PATH_STREAM_CLOSE = "/stream/close";
    public static final String PATH_STREAM_OPEN = "/stream/open";
    public static final String PATH_STREAM_REGISTER = "/stream/register";
    public static final String PATH_STREAM_SEND = "/stream/send";
    public static final String PATH_STREAM_UNREGISTER = "/stream/unregister";

    public static void setupConnection(Connection connection, Http2StreamListener http2StreamListener) {
        com.aliyun.alink.p022h2.utils.StreamUtil.setupConnection(connection, http2StreamListener);
    }

    public static Map<String, StreamServiceContext> getDataStreamMaps(Connection connection) {
        return com.aliyun.alink.p022h2.utils.StreamUtil.getDataStreamMaps(connection);
    }

    public static StreamServiceContext getDataStreamContext(List<Connection> list, String str) {
        return com.aliyun.alink.p022h2.utils.StreamUtil.getDataStreamContext(list, str);
    }

    public static StreamServiceContext getDataStreamContext(Connection connection, String str) {
        return com.aliyun.alink.p022h2.utils.StreamUtil.getDataStreamContext(connection, str);
    }

    public static void putDataStreamContext(Connection connection, String str, StreamServiceContext streamServiceContext) {
        com.aliyun.alink.p022h2.utils.StreamUtil.putDataStreamContext(connection, str, streamServiceContext);
    }

    public static void removeDataStreamContext(Connection connection, String str) {
        com.aliyun.alink.p022h2.utils.StreamUtil.removeDataStreamContext(connection, str);
    }

    public static String getDataStreamId(Http2Headers http2Headers) {
        return com.aliyun.alink.p022h2.utils.StreamUtil.getDataStreamId(http2Headers);
    }

    public static String getFileUploadId(Http2Headers http2Headers) {
        if (http2Headers.contains(FILE_UPLOAD_ID)) {
            return http2Headers.get(FILE_UPLOAD_ID).toString();
        }
        return null;
    }

    public static String getNextAppendPosition(Http2Headers http2Headers) {
        if (http2Headers.contains("x-next-append-position")) {
            return http2Headers.get("x-next-append-position").toString();
        }
        return null;
    }

    public static void checkServiceName(String str) {
        com.aliyun.alink.p022h2.utils.StreamUtil.checkServiceName(str);
    }
}
