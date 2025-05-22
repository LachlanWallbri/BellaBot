package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.grpc.CallOptions;
import io.grpc.InternalConfigSelector;
import io.grpc.LoadBalancer;
import io.grpc.MethodDescriptor;
import io.grpc.internal.RetriableStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ManagedChannelServiceConfig {

    @Nullable
    private final MethodInfo defaultMethodConfig;

    @Nullable
    private final Map<String, ?> healthCheckingConfig;

    @Nullable
    private final Object loadBalancingConfig;

    @Nullable
    private final RetriableStream.Throttle retryThrottling;
    private final Map<String, MethodInfo> serviceMap;
    private final Map<String, MethodInfo> serviceMethodMap;

    ManagedChannelServiceConfig(@Nullable MethodInfo methodInfo, Map<String, MethodInfo> map, Map<String, MethodInfo> map2, @Nullable RetriableStream.Throttle throttle, @Nullable Object obj, @Nullable Map<String, ?> map3) {
        this.defaultMethodConfig = methodInfo;
        this.serviceMethodMap = Collections.unmodifiableMap(new HashMap(map));
        this.serviceMap = Collections.unmodifiableMap(new HashMap(map2));
        this.retryThrottling = throttle;
        this.loadBalancingConfig = obj;
        this.healthCheckingConfig = map3 != null ? Collections.unmodifiableMap(new HashMap(map3)) : null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ManagedChannelServiceConfig empty() {
        return new ManagedChannelServiceConfig(null, new HashMap(), new HashMap(), null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ManagedChannelServiceConfig fromServiceConfig(Map<String, ?> map, boolean z, int i, int i2, @Nullable Object obj) {
        RetriableStream.Throttle throttlePolicy = z ? ServiceConfigUtil.getThrottlePolicy(map) : null;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Map<String, ?> healthCheckedService = ServiceConfigUtil.getHealthCheckedService(map);
        List<Map<String, ?>> methodConfigFromServiceConfig = ServiceConfigUtil.getMethodConfigFromServiceConfig(map);
        if (methodConfigFromServiceConfig == null) {
            return new ManagedChannelServiceConfig(null, hashMap, hashMap2, throttlePolicy, obj, healthCheckedService);
        }
        MethodInfo methodInfo = null;
        for (Map<String, ?> map2 : methodConfigFromServiceConfig) {
            MethodInfo methodInfo2 = new MethodInfo(map2, z, i, i2);
            List<Map<String, ?>> nameListFromMethodConfig = ServiceConfigUtil.getNameListFromMethodConfig(map2);
            if (nameListFromMethodConfig != null && !nameListFromMethodConfig.isEmpty()) {
                for (Map<String, ?> map3 : nameListFromMethodConfig) {
                    String serviceFromName = ServiceConfigUtil.getServiceFromName(map3);
                    String methodFromName = ServiceConfigUtil.getMethodFromName(map3);
                    if (Strings.isNullOrEmpty(serviceFromName)) {
                        Preconditions.checkArgument(Strings.isNullOrEmpty(methodFromName), "missing service name for method %s", methodFromName);
                        Preconditions.checkArgument(methodInfo == null, "Duplicate default method config in service config %s", map);
                        methodInfo = methodInfo2;
                    } else if (Strings.isNullOrEmpty(methodFromName)) {
                        Preconditions.checkArgument(!hashMap2.containsKey(serviceFromName), "Duplicate service %s", serviceFromName);
                        hashMap2.put(serviceFromName, methodInfo2);
                    } else {
                        String generateFullMethodName = MethodDescriptor.generateFullMethodName(serviceFromName, methodFromName);
                        Preconditions.checkArgument(!hashMap.containsKey(generateFullMethodName), "Duplicate method name %s", generateFullMethodName);
                        hashMap.put(generateFullMethodName, methodInfo2);
                    }
                }
            }
        }
        return new ManagedChannelServiceConfig(methodInfo, hashMap, hashMap2, throttlePolicy, obj, healthCheckedService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Map<String, ?> getHealthCheckingConfig() {
        return this.healthCheckingConfig;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public InternalConfigSelector getDefaultConfigSelector() {
        if (this.serviceMap.isEmpty() && this.serviceMethodMap.isEmpty() && this.defaultMethodConfig == null) {
            return null;
        }
        return new ServiceConfigConvertedSelector();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Object getLoadBalancingConfig() {
        return this.loadBalancingConfig;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public RetriableStream.Throttle getRetryThrottling() {
        return this.retryThrottling;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public MethodInfo getMethodConfig(MethodDescriptor<?, ?> methodDescriptor) {
        MethodInfo methodInfo = this.serviceMethodMap.get(methodDescriptor.getFullMethodName());
        if (methodInfo == null) {
            methodInfo = this.serviceMap.get(methodDescriptor.getServiceName());
        }
        return methodInfo == null ? this.defaultMethodConfig : methodInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ManagedChannelServiceConfig managedChannelServiceConfig = (ManagedChannelServiceConfig) obj;
        return Objects.equal(this.serviceMethodMap, managedChannelServiceConfig.serviceMethodMap) && Objects.equal(this.serviceMap, managedChannelServiceConfig.serviceMap) && Objects.equal(this.retryThrottling, managedChannelServiceConfig.retryThrottling) && Objects.equal(this.loadBalancingConfig, managedChannelServiceConfig.loadBalancingConfig);
    }

    public int hashCode() {
        return Objects.hashCode(this.serviceMethodMap, this.serviceMap, this.retryThrottling, this.loadBalancingConfig);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("serviceMethodMap", this.serviceMethodMap).add("serviceMap", this.serviceMap).add("retryThrottling", this.retryThrottling).add("loadBalancingConfig", this.loadBalancingConfig).toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class MethodInfo {
        static final CallOptions.Key<MethodInfo> KEY = CallOptions.Key.create("io.grpc.internal.ManagedChannelServiceConfig.MethodInfo");
        final HedgingPolicy hedgingPolicy;
        final Integer maxInboundMessageSize;
        final Integer maxOutboundMessageSize;
        final RetryPolicy retryPolicy;
        final Long timeoutNanos;
        final Boolean waitForReady;

        MethodInfo(Map<String, ?> map, boolean z, int i, int i2) {
            this.timeoutNanos = ServiceConfigUtil.getTimeoutFromMethodConfig(map);
            this.waitForReady = ServiceConfigUtil.getWaitForReadyFromMethodConfig(map);
            this.maxInboundMessageSize = ServiceConfigUtil.getMaxResponseMessageBytesFromMethodConfig(map);
            Integer num = this.maxInboundMessageSize;
            if (num != null) {
                Preconditions.checkArgument(num.intValue() >= 0, "maxInboundMessageSize %s exceeds bounds", this.maxInboundMessageSize);
            }
            this.maxOutboundMessageSize = ServiceConfigUtil.getMaxRequestMessageBytesFromMethodConfig(map);
            Integer num2 = this.maxOutboundMessageSize;
            if (num2 != null) {
                Preconditions.checkArgument(num2.intValue() >= 0, "maxOutboundMessageSize %s exceeds bounds", this.maxOutboundMessageSize);
            }
            Map<String, ?> retryPolicyFromMethodConfig = z ? ServiceConfigUtil.getRetryPolicyFromMethodConfig(map) : null;
            this.retryPolicy = retryPolicyFromMethodConfig == null ? null : retryPolicy(retryPolicyFromMethodConfig, i);
            Map<String, ?> hedgingPolicyFromMethodConfig = z ? ServiceConfigUtil.getHedgingPolicyFromMethodConfig(map) : null;
            this.hedgingPolicy = hedgingPolicyFromMethodConfig != null ? hedgingPolicy(hedgingPolicyFromMethodConfig, i2) : null;
        }

        public int hashCode() {
            return Objects.hashCode(this.timeoutNanos, this.waitForReady, this.maxInboundMessageSize, this.maxOutboundMessageSize, this.retryPolicy, this.hedgingPolicy);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MethodInfo)) {
                return false;
            }
            MethodInfo methodInfo = (MethodInfo) obj;
            return Objects.equal(this.timeoutNanos, methodInfo.timeoutNanos) && Objects.equal(this.waitForReady, methodInfo.waitForReady) && Objects.equal(this.maxInboundMessageSize, methodInfo.maxInboundMessageSize) && Objects.equal(this.maxOutboundMessageSize, methodInfo.maxOutboundMessageSize) && Objects.equal(this.retryPolicy, methodInfo.retryPolicy) && Objects.equal(this.hedgingPolicy, methodInfo.hedgingPolicy);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("timeoutNanos", this.timeoutNanos).add("waitForReady", this.waitForReady).add("maxInboundMessageSize", this.maxInboundMessageSize).add("maxOutboundMessageSize", this.maxOutboundMessageSize).add("retryPolicy", this.retryPolicy).add("hedgingPolicy", this.hedgingPolicy).toString();
        }

        private static RetryPolicy retryPolicy(Map<String, ?> map, int i) {
            int intValue = ((Integer) Preconditions.checkNotNull(ServiceConfigUtil.getMaxAttemptsFromRetryPolicy(map), "maxAttempts cannot be empty")).intValue();
            Preconditions.checkArgument(intValue >= 2, "maxAttempts must be greater than 1: %s", intValue);
            int min = Math.min(intValue, i);
            long longValue = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getInitialBackoffNanosFromRetryPolicy(map), "initialBackoff cannot be empty")).longValue();
            Preconditions.checkArgument(longValue > 0, "initialBackoffNanos must be greater than 0: %s", longValue);
            long longValue2 = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getMaxBackoffNanosFromRetryPolicy(map), "maxBackoff cannot be empty")).longValue();
            Preconditions.checkArgument(longValue2 > 0, "maxBackoff must be greater than 0: %s", longValue2);
            double doubleValue = ((Double) Preconditions.checkNotNull(ServiceConfigUtil.getBackoffMultiplierFromRetryPolicy(map), "backoffMultiplier cannot be empty")).doubleValue();
            Preconditions.checkArgument(doubleValue > 0.0d, "backoffMultiplier must be greater than 0: %s", Double.valueOf(doubleValue));
            return new RetryPolicy(min, longValue, longValue2, doubleValue, ServiceConfigUtil.getRetryableStatusCodesFromRetryPolicy(map));
        }

        private static HedgingPolicy hedgingPolicy(Map<String, ?> map, int i) {
            int intValue = ((Integer) Preconditions.checkNotNull(ServiceConfigUtil.getMaxAttemptsFromHedgingPolicy(map), "maxAttempts cannot be empty")).intValue();
            Preconditions.checkArgument(intValue >= 2, "maxAttempts must be greater than 1: %s", intValue);
            int min = Math.min(intValue, i);
            long longValue = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getHedgingDelayNanosFromHedgingPolicy(map), "hedgingDelay cannot be empty")).longValue();
            Preconditions.checkArgument(longValue >= 0, "hedgingDelay must not be negative: %s", longValue);
            return new HedgingPolicy(min, longValue, ServiceConfigUtil.getNonFatalStatusCodesFromHedgingPolicy(map));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class ServiceConfigConvertedSelector extends InternalConfigSelector {
        final ManagedChannelServiceConfig config;

        private ServiceConfigConvertedSelector(ManagedChannelServiceConfig managedChannelServiceConfig) {
            this.config = managedChannelServiceConfig;
        }

        @Override // io.grpc.InternalConfigSelector
        public InternalConfigSelector.Result selectConfig(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return InternalConfigSelector.Result.newBuilder().setConfig(this.config).build();
        }
    }
}
