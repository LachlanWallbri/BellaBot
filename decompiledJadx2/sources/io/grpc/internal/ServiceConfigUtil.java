package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.RetriableStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ServiceConfigUtil {
    private ServiceConfigUtil() {
    }

    @Nullable
    public static Map<String, ?> getHealthCheckedService(@Nullable Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        return JsonUtil.getObject(map, "healthCheckConfig");
    }

    @Nullable
    public static String getHealthCheckedServiceName(@Nullable Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        return JsonUtil.getString(map, "serviceName");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static RetriableStream.Throttle getThrottlePolicy(@Nullable Map<String, ?> map) {
        Map<String, ?> object;
        if (map == null || (object = JsonUtil.getObject(map, "retryThrottling")) == null) {
            return null;
        }
        float floatValue = JsonUtil.getNumber(object, "maxTokens").floatValue();
        float floatValue2 = JsonUtil.getNumber(object, "tokenRatio").floatValue();
        Preconditions.checkState(floatValue > 0.0f, "maxToken should be greater than zero");
        Preconditions.checkState(floatValue2 > 0.0f, "tokenRatio should be greater than zero");
        return new RetriableStream.Throttle(floatValue, floatValue2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Integer getMaxAttemptsFromRetryPolicy(Map<String, ?> map) {
        return JsonUtil.getNumberAsInteger(map, "maxAttempts");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Long getInitialBackoffNanosFromRetryPolicy(Map<String, ?> map) {
        return JsonUtil.getStringAsDuration(map, "initialBackoff");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Long getMaxBackoffNanosFromRetryPolicy(Map<String, ?> map) {
        return JsonUtil.getStringAsDuration(map, "maxBackoff");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Double getBackoffMultiplierFromRetryPolicy(Map<String, ?> map) {
        return JsonUtil.getNumber(map, "backoffMultiplier");
    }

    private static Set<Status.Code> getListOfStatusCodesAsSet(Map<String, ?> map, String str) {
        List<?> list = JsonUtil.getList(map, str);
        if (list == null) {
            return null;
        }
        return getStatusCodesFromList(list);
    }

    private static Set<Status.Code> getStatusCodesFromList(List<?> list) {
        Status.Code valueOf;
        EnumSet noneOf = EnumSet.noneOf(Status.Code.class);
        for (Object obj : list) {
            if (obj instanceof Double) {
                Double d = (Double) obj;
                int intValue = d.intValue();
                Verify.verify(((double) intValue) == d.doubleValue(), "Status code %s is not integral", obj);
                valueOf = Status.fromCodeValue(intValue).getCode();
                Verify.verify(valueOf.value() == d.intValue(), "Status code %s is not valid", obj);
            } else if (obj instanceof String) {
                try {
                    valueOf = Status.Code.valueOf((String) obj);
                } catch (IllegalArgumentException e) {
                    throw new VerifyException("Status code " + obj + " is not valid", e);
                }
            } else {
                throw new VerifyException("Can not convert status code " + obj + " to Status.Code, because its type is " + obj.getClass());
            }
            noneOf.add(valueOf);
        }
        return Collections.unmodifiableSet(noneOf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set<Status.Code> getRetryableStatusCodesFromRetryPolicy(Map<String, ?> map) {
        Set<Status.Code> listOfStatusCodesAsSet = getListOfStatusCodesAsSet(map, "retryableStatusCodes");
        Verify.verify(listOfStatusCodesAsSet != null, "%s is required in retry policy", "retryableStatusCodes");
        Verify.verify(!listOfStatusCodesAsSet.isEmpty(), "%s must not be empty", "retryableStatusCodes");
        Verify.verify(true ^ listOfStatusCodesAsSet.contains(Status.Code.OK), "%s must not contain OK", "retryableStatusCodes");
        return listOfStatusCodesAsSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Integer getMaxAttemptsFromHedgingPolicy(Map<String, ?> map) {
        return JsonUtil.getNumberAsInteger(map, "maxAttempts");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Long getHedgingDelayNanosFromHedgingPolicy(Map<String, ?> map) {
        return JsonUtil.getStringAsDuration(map, "hedgingDelay");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set<Status.Code> getNonFatalStatusCodesFromHedgingPolicy(Map<String, ?> map) {
        Set<Status.Code> listOfStatusCodesAsSet = getListOfStatusCodesAsSet(map, "nonFatalStatusCodes");
        if (listOfStatusCodesAsSet == null) {
            return Collections.unmodifiableSet(EnumSet.noneOf(Status.Code.class));
        }
        Verify.verify(!listOfStatusCodesAsSet.contains(Status.Code.OK), "%s must not contain OK", "nonFatalStatusCodes");
        return listOfStatusCodesAsSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static String getServiceFromName(Map<String, ?> map) {
        return JsonUtil.getString(map, "service");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static String getMethodFromName(Map<String, ?> map) {
        return JsonUtil.getString(map, "method");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Map<String, ?> getRetryPolicyFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getObject(map, "retryPolicy");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Map<String, ?> getHedgingPolicyFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getObject(map, "hedgingPolicy");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static List<Map<String, ?>> getNameListFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getListOfObjects(map, "name");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Long getTimeoutFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getStringAsDuration(map, "timeout");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Boolean getWaitForReadyFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getBoolean(map, "waitForReady");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Integer getMaxRequestMessageBytesFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getNumberAsInteger(map, "maxRequestMessageBytes");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Integer getMaxResponseMessageBytesFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getNumberAsInteger(map, "maxResponseMessageBytes");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static List<Map<String, ?>> getMethodConfigFromServiceConfig(Map<String, ?> map) {
        return JsonUtil.getListOfObjects(map, "methodConfig");
    }

    public static List<Map<String, ?>> getLoadBalancingConfigsFromServiceConfig(Map<String, ?> map) {
        String string;
        ArrayList arrayList = new ArrayList();
        if (map.containsKey("loadBalancingConfig")) {
            arrayList.addAll(JsonUtil.getListOfObjects(map, "loadBalancingConfig"));
        }
        if (arrayList.isEmpty() && (string = JsonUtil.getString(map, "loadBalancingPolicy")) != null) {
            arrayList.add(Collections.singletonMap(string.toLowerCase(Locale.ROOT), Collections.emptyMap()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static LbConfig unwrapLoadBalancingConfig(Map<String, ?> map) {
        if (map.size() != 1) {
            throw new RuntimeException("There are " + map.size() + " fields in a LoadBalancingConfig object. Exactly one is expected. Config=" + map);
        }
        String key = map.entrySet().iterator().next().getKey();
        return new LbConfig(key, JsonUtil.getObject(map, key));
    }

    public static List<LbConfig> unwrapLoadBalancingConfigList(List<Map<String, ?>> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Map<String, ?>> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(unwrapLoadBalancingConfig(it.next()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static NameResolver.ConfigOrError selectLbPolicyFromList(List<LbConfig> list, LoadBalancerRegistry loadBalancerRegistry) {
        ArrayList arrayList = new ArrayList();
        for (LbConfig lbConfig : list) {
            String policyName = lbConfig.getPolicyName();
            LoadBalancerProvider provider = loadBalancerRegistry.getProvider(policyName);
            if (provider == null) {
                arrayList.add(policyName);
            } else {
                if (!arrayList.isEmpty()) {
                    Logger.getLogger(ServiceConfigUtil.class.getName()).log(Level.FINEST, "{0} specified by Service Config are not available", arrayList);
                }
                NameResolver.ConfigOrError parseLoadBalancingPolicyConfig = provider.parseLoadBalancingPolicyConfig(lbConfig.getRawConfigValue());
                return parseLoadBalancingPolicyConfig.getError() != null ? parseLoadBalancingPolicyConfig : NameResolver.ConfigOrError.fromConfig(new PolicySelection(provider, parseLoadBalancingPolicyConfig.getConfig()));
            }
        }
        return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("None of " + arrayList + " specified by Service Config are available."));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class LbConfig {
        private final String policyName;
        private final Map<String, ?> rawConfigValue;

        public LbConfig(String str, Map<String, ?> map) {
            this.policyName = (String) Preconditions.checkNotNull(str, "policyName");
            this.rawConfigValue = (Map) Preconditions.checkNotNull(map, "rawConfigValue");
        }

        public String getPolicyName() {
            return this.policyName;
        }

        public Map<String, ?> getRawConfigValue() {
            return this.rawConfigValue;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof LbConfig)) {
                return false;
            }
            LbConfig lbConfig = (LbConfig) obj;
            return this.policyName.equals(lbConfig.policyName) && this.rawConfigValue.equals(lbConfig.rawConfigValue);
        }

        public int hashCode() {
            return Objects.hashCode(this.policyName, this.rawConfigValue);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("policyName", this.policyName).add("rawConfigValue", this.rawConfigValue).toString();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class PolicySelection {

        @Nullable
        final Object config;
        final LoadBalancerProvider provider;

        public PolicySelection(LoadBalancerProvider loadBalancerProvider, @Nullable Object obj) {
            this.provider = (LoadBalancerProvider) Preconditions.checkNotNull(loadBalancerProvider, "provider");
            this.config = obj;
        }

        public LoadBalancerProvider getProvider() {
            return this.provider;
        }

        @Nullable
        public Object getConfig() {
            return this.config;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            PolicySelection policySelection = (PolicySelection) obj;
            return Objects.equal(this.provider, policySelection.provider) && Objects.equal(this.config, policySelection.config);
        }

        public int hashCode() {
            return Objects.hashCode(this.provider, this.config);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("provider", this.provider).add("config", this.config).toString();
        }
    }
}
