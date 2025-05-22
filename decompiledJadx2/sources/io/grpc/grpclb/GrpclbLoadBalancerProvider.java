package io.grpc.grpclb;

import com.google.common.base.Stopwatch;
import io.grpc.Context;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.grpclb.GrpclbState;
import io.grpc.internal.ExponentialBackoffPolicy;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.JsonUtil;
import io.grpc.internal.ServiceConfigUtil;
import io.grpc.internal.TimeProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class GrpclbLoadBalancerProvider extends LoadBalancerProvider {
    private static final GrpclbState.Mode DEFAULT_MODE = GrpclbState.Mode.ROUND_ROBIN;

    @Override // io.grpc.LoadBalancerProvider
    public String getPolicyName() {
        return "grpclb";
    }

    @Override // io.grpc.LoadBalancerProvider
    public int getPriority() {
        return 5;
    }

    @Override // io.grpc.LoadBalancerProvider
    public boolean isAvailable() {
        return true;
    }

    @Override // io.grpc.LoadBalancer.Factory
    public LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new GrpclbLoadBalancer(helper, Context.ROOT, new CachedSubchannelPool(helper), TimeProvider.SYSTEM_TIME_PROVIDER, Stopwatch.createUnstarted(), new ExponentialBackoffPolicy.Provider());
    }

    @Override // io.grpc.LoadBalancerProvider
    public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map<String, ?> map) {
        try {
            return parseLoadBalancingConfigPolicyInternal(map);
        } catch (RuntimeException e) {
            return NameResolver.ConfigOrError.fromError(Status.fromThrowable(e).withDescription("Failed to parse GRPCLB config: " + map));
        }
    }

    NameResolver.ConfigOrError parseLoadBalancingConfigPolicyInternal(Map<String, ?> map) {
        if (map == null) {
            return NameResolver.ConfigOrError.fromConfig(GrpclbConfig.create(DEFAULT_MODE));
        }
        String string = JsonUtil.getString(map, "serviceName");
        List<?> list = JsonUtil.getList(map, "childPolicy");
        List<ServiceConfigUtil.LbConfig> unwrapLoadBalancingConfigList = list != null ? ServiceConfigUtil.unwrapLoadBalancingConfigList(JsonUtil.checkObjectList(list)) : null;
        if (unwrapLoadBalancingConfigList == null || unwrapLoadBalancingConfigList.isEmpty()) {
            return NameResolver.ConfigOrError.fromConfig(GrpclbConfig.create(DEFAULT_MODE, string));
        }
        ArrayList arrayList = new ArrayList();
        Iterator<ServiceConfigUtil.LbConfig> it = unwrapLoadBalancingConfigList.iterator();
        while (it.hasNext()) {
            String policyName = it.next().getPolicyName();
            char c = 65535;
            int hashCode = policyName.hashCode();
            if (hashCode != -1603446510) {
                if (hashCode == -230843463 && policyName.equals("round_robin")) {
                    c = 0;
                }
            } else if (policyName.equals(GrpcUtil.DEFAULT_LB_POLICY)) {
                c = 1;
            }
            if (c == 0) {
                return NameResolver.ConfigOrError.fromConfig(GrpclbConfig.create(GrpclbState.Mode.ROUND_ROBIN, string));
            }
            if (c == 1) {
                return NameResolver.ConfigOrError.fromConfig(GrpclbConfig.create(GrpclbState.Mode.PICK_FIRST, string));
            }
            arrayList.add(policyName);
        }
        return NameResolver.ConfigOrError.fromError(Status.INVALID_ARGUMENT.withDescription("None of " + arrayList + " specified child policies are available."));
    }
}
