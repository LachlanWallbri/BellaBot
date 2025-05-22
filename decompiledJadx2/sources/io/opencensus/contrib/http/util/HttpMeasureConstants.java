package io.opencensus.contrib.http.util;

import io.opencensus.stats.Measure;
import io.opencensus.tags.TagKey;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class HttpMeasureConstants {
    private static final String UNIT_SIZE_BYTE = "By";
    public static final Measure.MeasureLong HTTP_CLIENT_SENT_BYTES = Measure.MeasureLong.create("opencensus.io/http/client/sent_bytes", "Client-side total bytes sent in request body (uncompressed)", UNIT_SIZE_BYTE);
    public static final Measure.MeasureLong HTTP_CLIENT_RECEIVED_BYTES = Measure.MeasureLong.create("opencensus.io/http/client/received_bytes", "Client-side total bytes received in response bodies (uncompressed)", UNIT_SIZE_BYTE);
    private static final String UNIT_LATENCY_MS = "ms";
    public static final Measure.MeasureDouble HTTP_CLIENT_ROUNDTRIP_LATENCY = Measure.MeasureDouble.create("opencensus.io/http/client/roundtrip_latency", "Client-side time between first byte of request headers sent to last byte of response received, or terminal error", UNIT_LATENCY_MS);
    public static final Measure.MeasureLong HTTP_SERVER_RECEIVED_BYTES = Measure.MeasureLong.create("opencensus.io/http/server/received_bytes", "Server-side total bytes received in request body (uncompressed)", UNIT_SIZE_BYTE);
    public static final Measure.MeasureLong HTTP_SERVER_SENT_BYTES = Measure.MeasureLong.create("opencensus.io/http/server/sent_bytes", "Server-side total bytes sent in response bodies (uncompressed)", UNIT_SIZE_BYTE);
    public static final Measure.MeasureDouble HTTP_SERVER_LATENCY = Measure.MeasureDouble.create("opencensus.io/http/server/server_latency", "Server-side time between first byte of request headers received to last byte of response sent, or terminal error", UNIT_LATENCY_MS);
    public static final TagKey HTTP_CLIENT_HOST = TagKey.create("http_client_host");
    public static final TagKey HTTP_SERVER_HOST = TagKey.create("http_server_host");
    public static final TagKey HTTP_CLIENT_STATUS = TagKey.create("http_client_status");
    public static final TagKey HTTP_SERVER_STATUS = TagKey.create("http_server_status");
    public static final TagKey HTTP_CLIENT_PATH = TagKey.create("http_client_path");
    public static final TagKey HTTP_SERVER_PATH = TagKey.create("http_server_path");
    public static final TagKey HTTP_CLIENT_METHOD = TagKey.create("http_client_method");
    public static final TagKey HTTP_SERVER_METHOD = TagKey.create("http_server_method");
    public static final TagKey HTTP_SERVER_ROUTE = TagKey.create("http_server_route");

    private HttpMeasureConstants() {
    }
}
