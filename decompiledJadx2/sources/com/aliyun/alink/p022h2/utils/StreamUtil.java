package com.aliyun.alink.p022h2.utils;

import com.aliyun.alink.p022h2.api.Http2StreamListener;
import com.aliyun.alink.p022h2.api.StreamServiceContext;
import com.aliyun.alink.p022h2.connection.Connection;
import io.netty.handler.codec.http2.Http2Headers;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class StreamUtil {
    public static void setupConnection(Connection connection, Http2StreamListener http2StreamListener) {
        connection.setDefaultStreamListener(http2StreamListener);
        connection.setProperty(connection.getPropertyKey("streams"), new ConcurrentHashMap());
    }

    public static Map<String, StreamServiceContext> getDataStreamMaps(Connection connection) {
        return (Map) connection.getProperty(connection.getPropertyKey("streams"));
    }

    public static StreamServiceContext getDataStreamContext(List<Connection> list, String str) {
        if (list == null || list.size() < 1) {
            throw new IllegalArgumentException("getDataStreamContext connections null");
        }
        if (StringUtil.isEmpty(str)) {
            throw new IllegalArgumentException("getDataStreamContext dataStreamId empty");
        }
        for (int i = 0; i < list.size(); i++) {
            Connection connection = list.get(i);
            if (hasDataStream(connection, str)) {
                return getDataStreamContext(connection, str);
            }
        }
        return null;
    }

    public static boolean hasDataStream(Connection connection, String str) {
        if (getDataStreamMaps(connection) == null) {
            return false;
        }
        return getDataStreamMaps(connection).containsKey(str);
    }

    public static void putDataStreamContext(Connection connection, String str, StreamServiceContext streamServiceContext) {
        getDataStreamMaps(connection).put(str, streamServiceContext);
    }

    public static StreamServiceContext getDataStreamContext(Connection connection, String str) {
        return getDataStreamMaps(connection).get(str);
    }

    public static void removeDataStreamContext(Connection connection, String str) {
        getDataStreamMaps(connection).remove(str);
    }

    public static String getDataStreamId(Http2Headers http2Headers) {
        if (http2Headers.contains(com.aliyun.alink.p022h2.stream.utils.StreamUtil.DATA_STREAM_ID)) {
            return http2Headers.get(com.aliyun.alink.p022h2.stream.utils.StreamUtil.DATA_STREAM_ID).toString();
        }
        return null;
    }

    public static void checkServiceName(String str) {
        if (!str.startsWith("/")) {
            throw new IllegalArgumentException("service name should start with '/'");
        }
    }
}
