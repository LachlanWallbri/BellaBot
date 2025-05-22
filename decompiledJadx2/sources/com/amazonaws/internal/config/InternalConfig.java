package com.amazonaws.internal.config;

import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.regions.ServiceAbbreviations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* loaded from: classes.dex */
public class InternalConfig {
    private static final String SERVICE_REGION_DELIMITOR = "/";
    private static final Log log = LogFactory.getLog((Class<?>) InternalConfig.class);
    private final SignerConfig defaultSignerConfig = getDefaultSigner();
    private final Map<String, SignerConfig> regionSigners = getDefaultRegionSigners();
    private final Map<String, SignerConfig> serviceSigners = getDefaultServiceSigners();
    private final Map<String, SignerConfig> serviceRegionSigners = getDefaultServiceRegionSigners();
    private final Map<String, HttpClientConfig> httpClients = getDefaultHttpClients();
    private final List<HostRegexToRegionMapping> hostRegexToRegionMappings = getDefaultHostRegexToRegionMappings();

    InternalConfig() {
    }

    public SignerConfig getSignerConfig(String str) {
        return getSignerConfig(str, null);
    }

    public HttpClientConfig getHttpClientConfig(String str) {
        return this.httpClients.get(str);
    }

    public SignerConfig getSignerConfig(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (str2 != null) {
            SignerConfig signerConfig = this.serviceRegionSigners.get(str + "/" + str2);
            if (signerConfig != null) {
                return signerConfig;
            }
            SignerConfig signerConfig2 = this.regionSigners.get(str2);
            if (signerConfig2 != null) {
                return signerConfig2;
            }
        }
        SignerConfig signerConfig3 = this.serviceSigners.get(str);
        return signerConfig3 == null ? this.defaultSignerConfig : signerConfig3;
    }

    public List<HostRegexToRegionMapping> getHostRegexToRegionMappings() {
        return Collections.unmodifiableList(this.hostRegexToRegionMappings);
    }

    private static Map<String, HttpClientConfig> getDefaultHttpClients() {
        HashMap hashMap = new HashMap();
        hashMap.put("AmazonCloudWatchClient", new HttpClientConfig(ServiceAbbreviations.CloudWatch));
        hashMap.put("AmazonCloudWatchLogsClient", new HttpClientConfig("logs"));
        hashMap.put("AmazonCognitoIdentityClient", new HttpClientConfig("cognito-identity"));
        hashMap.put("AmazonCognitoIdentityProviderClient", new HttpClientConfig("cognito-idp"));
        hashMap.put("AmazonCognitoSyncClient", new HttpClientConfig("cognito-sync"));
        hashMap.put("AmazonComprehendClient", new HttpClientConfig("comprehend"));
        hashMap.put("AmazonConnectClient", new HttpClientConfig(MqttServiceConstants.CONNECT_ACTION));
        hashMap.put("AmazonKinesisFirehoseClient", new HttpClientConfig("firehose"));
        hashMap.put("AWSKinesisVideoArchivedMediaClient", new HttpClientConfig("kinesisvideo"));
        hashMap.put("AWSKinesisVideoSignalingClient", new HttpClientConfig("kinesisvideo"));
        hashMap.put("AWSIotClient", new HttpClientConfig("execute-api"));
        hashMap.put("AmazonLexRuntimeClient", new HttpClientConfig("lex"));
        hashMap.put("AmazonPinpointClient", new HttpClientConfig("mobiletargeting"));
        hashMap.put("AmazonPinpointAnalyticsClient", new HttpClientConfig("mobileanalytics"));
        hashMap.put("AmazonSageMakerRuntimeClient", new HttpClientConfig("sagemaker"));
        hashMap.put("AmazonSimpleDBClient", new HttpClientConfig(ServiceAbbreviations.SimpleDB));
        hashMap.put("AmazonSimpleEmailServiceClient", new HttpClientConfig("email"));
        hashMap.put("AWSSecurityTokenServiceClient", new HttpClientConfig(ServiceAbbreviations.STS));
        hashMap.put("AmazonTextractClient", new HttpClientConfig("textract"));
        hashMap.put("AmazonTranscribeClient", new HttpClientConfig("transcribe"));
        hashMap.put("AmazonTranslateClient", new HttpClientConfig("translate"));
        return hashMap;
    }

    private static Map<String, SignerConfig> getDefaultRegionSigners() {
        HashMap hashMap = new HashMap();
        hashMap.put("eu-central-1", new SignerConfig("AWS4SignerType"));
        hashMap.put("cn-north-1", new SignerConfig("AWS4SignerType"));
        return hashMap;
    }

    private static Map<String, SignerConfig> getDefaultServiceRegionSigners() {
        HashMap hashMap = new HashMap();
        hashMap.put("s3/eu-central-1", new SignerConfig("AWSS3V4SignerType"));
        hashMap.put("s3/cn-north-1", new SignerConfig("AWSS3V4SignerType"));
        hashMap.put("s3/us-east-2", new SignerConfig("AWSS3V4SignerType"));
        hashMap.put("s3/ca-central-1", new SignerConfig("AWSS3V4SignerType"));
        hashMap.put("s3/ap-south-1", new SignerConfig("AWSS3V4SignerType"));
        hashMap.put("s3/ap-northeast-2", new SignerConfig("AWSS3V4SignerType"));
        hashMap.put("s3/eu-west-2", new SignerConfig("AWSS3V4SignerType"));
        hashMap.put("lex/eu-central-1", new SignerConfig("AmazonLexV4Signer"));
        hashMap.put("lex/cn-north-1", new SignerConfig("AmazonLexV4Signer"));
        hashMap.put("polly/eu-central-1", new SignerConfig("AmazonPollyCustomPresigner"));
        hashMap.put("polly/cn-north-1", new SignerConfig("AmazonPollyCustomPresigner"));
        return hashMap;
    }

    private static Map<String, SignerConfig> getDefaultServiceSigners() {
        HashMap hashMap = new HashMap();
        hashMap.put(ServiceAbbreviations.EC2, new SignerConfig("QueryStringSignerType"));
        hashMap.put("email", new SignerConfig("AWS4SignerType"));
        hashMap.put("s3", new SignerConfig("AWSS3V4SignerType"));
        hashMap.put(ServiceAbbreviations.SimpleDB, new SignerConfig("QueryStringSignerType"));
        hashMap.put("lex", new SignerConfig("AmazonLexV4Signer"));
        hashMap.put("polly", new SignerConfig("AmazonPollyCustomPresigner"));
        return hashMap;
    }

    private static SignerConfig getDefaultSigner() {
        return new SignerConfig("AWS4SignerType");
    }

    private static List<HostRegexToRegionMapping> getDefaultHostRegexToRegionMappings() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HostRegexToRegionMapping("(.+\\.)?s3\\.amazonaws\\.com", "us-east-1"));
        arrayList.add(new HostRegexToRegionMapping("(.+\\.)?s3-external-1\\.amazonaws\\.com", "us-east-1"));
        arrayList.add(new HostRegexToRegionMapping("(.+\\.)?s3-fips-us-gov-west-1\\.amazonaws\\.com", "us-gov-west-1"));
        return arrayList;
    }

    void dump() {
        log.debug("defaultSignerConfig: " + this.defaultSignerConfig + "\nserviceRegionSigners: " + this.serviceRegionSigners + "\nregionSigners: " + this.regionSigners + "\nserviceSigners: " + this.serviceSigners + "\nhostRegexToRegionMappings: " + this.hostRegexToRegionMappings);
    }

    /* loaded from: classes.dex */
    public static class Factory {
        private static final InternalConfig SINGELTON;

        static {
            try {
                SINGELTON = new InternalConfig();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new IllegalStateException("Fatal: Failed to load the internal config for AWS Android SDK", e2);
            }
        }

        public static InternalConfig getInternalConfig() {
            return SINGELTON;
        }
    }
}
