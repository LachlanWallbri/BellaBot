package com.amazonaws.services.p048s3.metrics;

import com.amazonaws.metrics.ServiceMetricType;
import com.amazonaws.metrics.SimpleMetricType;
import com.amazonaws.metrics.ThroughputMetricType;
import com.amazonaws.services.p048s3.internal.Constants;

/* loaded from: classes.dex */
public class S3ServiceMetric extends SimpleMetricType implements ServiceMetricType {
    static final String SERVICE_NAME_PREFIX = "S3";
    private final String name;
    public static final S3ThroughputMetric S3_DOWLOAD_THROUGHPUT = new S3ThroughputMetric(metricName(ServiceMetricType.DOWNLOAD_THROUGHPUT_NAME_SUFFIX)) { // from class: com.amazonaws.services.s3.metrics.S3ServiceMetric.1
        @Override // com.amazonaws.metrics.ThroughputMetricType
        public ServiceMetricType getByteCountMetricType() {
            return S3_DOWNLOAD_BYTE_COUNT;
        }
    };
    public static final S3ServiceMetric S3_DOWNLOAD_BYTE_COUNT = new S3ServiceMetric(metricName(ServiceMetricType.DOWNLOAD_BYTE_COUNT_NAME_SUFFIX));
    public static final S3ThroughputMetric S3_UPLOAD_THROUGHPUT = new S3ThroughputMetric(metricName(ServiceMetricType.UPLOAD_THROUGHPUT_NAME_SUFFIX)) { // from class: com.amazonaws.services.s3.metrics.S3ServiceMetric.2
        @Override // com.amazonaws.metrics.ThroughputMetricType
        public ServiceMetricType getByteCountMetricType() {
            return S3_UPLOAD_BYTE_COUNT;
        }
    };
    public static final S3ServiceMetric S3_UPLOAD_BYTE_COUNT = new S3ServiceMetric(metricName(ServiceMetricType.UPLOAD_BYTE_COUNT_NAME_SUFFIX));
    private static final S3ServiceMetric[] VALUES = {S3_DOWLOAD_THROUGHPUT, S3_DOWNLOAD_BYTE_COUNT, S3_UPLOAD_THROUGHPUT, S3_UPLOAD_BYTE_COUNT};

    @Override // com.amazonaws.metrics.ServiceMetricType
    public String getServiceName() {
        return Constants.S3_SERVICE_DISPLAY_NAME;
    }

    private static final String metricName(String str) {
        return SERVICE_NAME_PREFIX + str;
    }

    private S3ServiceMetric(String str) {
        this.name = str;
    }

    @Override // com.amazonaws.metrics.SimpleMetricType, com.amazonaws.metrics.MetricType
    public String name() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class S3ThroughputMetric extends S3ServiceMetric implements ThroughputMetricType {
        private S3ThroughputMetric(String str) {
            super(str);
        }
    }

    public static S3ServiceMetric[] values() {
        return (S3ServiceMetric[]) VALUES.clone();
    }

    public static S3ServiceMetric valueOf(String str) {
        for (S3ServiceMetric s3ServiceMetric : values()) {
            if (s3ServiceMetric.name().equals(str)) {
                return s3ServiceMetric;
            }
        }
        throw new IllegalArgumentException("No S3ServiceMetric defined for the name " + str);
    }
}
