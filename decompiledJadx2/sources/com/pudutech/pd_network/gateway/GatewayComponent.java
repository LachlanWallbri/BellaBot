package com.pudutech.pd_network.gateway;

import android.util.Log;
import com.pudutech.pd_network.IGateway;
import com.pudutech.pd_network.OnGatewayAction;
import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.DeviceType;
import com.pudutech.pd_network.bean.GatewayBean;
import com.pudutech.pd_network.bean.GatewayName;
import com.pudutech.pd_network.bean.GatewayPlace;
import com.pudutech.pd_network.bean.ServiceGatewayConfig;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.utils.NetSpUtils;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: GatewayComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\tH\u0016J\b\u0010\u0019\u001a\u00020\u0017H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0018\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0004H\u0016J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0015H\u0002J\u000e\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u0006J\u0006\u0010&\u001a\u00020\u0017J\u0018\u0010'\u001a\u00020\u00172\u000e\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010)H\u0016J\u0010\u0010*\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\tH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, m3961d2 = {"Lcom/pudutech/pd_network/gateway/GatewayComponent;", "Lcom/pudutech/pd_network/IGateway;", "()V", "SERVICE_GATEWAY", "", "TAG", "", "actionList", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lcom/pudutech/pd_network/OnGatewayAction;", "api", "Lcom/pudutech/pd_network/gateway/Api;", "getApi", "()Lcom/pudutech/pd_network/gateway/Api;", "api$delegate", "Lkotlin/Lazy;", "job", "Lkotlinx/coroutines/Job;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "serviceConfigBean", "Lcom/pudutech/pd_network/bean/ServiceGatewayConfig;", "addOnGatewayAction", "", "action", "fillGateway", "gatewayPlace", "Lcom/pudutech/pd_network/bean/GatewayPlace;", "getGateway", "Lcom/pudutech/pd_network/bean/GatewayBean;", "mGatewayName", "Lcom/pudutech/pd_network/bean/GatewayName;", "getGatewayExecute", "throwException", "handlerData", "bean", "host2AuthHost", "host", "init", "refreshGateway", "block", "Lkotlin/Function0;", "removeOnGatewayAction", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class GatewayComponent implements IGateway {
    private static boolean SERVICE_GATEWAY = false;
    private static Job job;
    private static ServiceGatewayConfig serviceConfigBean;
    public static final GatewayComponent INSTANCE = new GatewayComponent();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));

    /* renamed from: api$delegate, reason: from kotlin metadata */
    private static final Lazy api = LazyKt.lazy(new Function0<Api>() { // from class: com.pudutech.pd_network.gateway.GatewayComponent$api$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Api invoke() {
            return (Api) PdNetworkManager.f10310INSTANCE.createService(Api.class);
        }
    });
    private static final ConcurrentLinkedQueue<OnGatewayAction> actionList = new ConcurrentLinkedQueue<>();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DeviceType.values().length];

        static {
            $EnumSwitchMapping$0[DeviceType.Robot.ordinal()] = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Api getApi() {
        return (Api) api.getValue();
    }

    private GatewayComponent() {
    }

    public final void init() {
        fillGateway();
    }

    private final void fillGateway() {
        Job launch$default;
        SERVICE_GATEWAY = false;
        launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new GatewayComponent$fillGateway$1(null), 3, null);
        job = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handlerData(ServiceGatewayConfig bean) {
        List<GatewayBean> list = bean.getList();
        if (list == null || list.isEmpty()) {
            return;
        }
        for (GatewayBean gatewayBean : bean.getList()) {
            if (!StringsKt.contains$default((CharSequence) gatewayBean.getHost(), (CharSequence) OSSConfig.PREFIX_HTTPS, false, 2, (Object) null)) {
                gatewayBean.setHost(OSSConfig.PREFIX_HTTPS + gatewayBean.getHost());
            }
        }
    }

    @Override // com.pudutech.pd_network.IGateway
    public void refreshGateway(Function0<Unit> block) {
        NetWorkLog.INSTANCE.mo3280i(TAG, "refreshGateway > " + block);
        Job job2 = job;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        serviceConfigBean = (ServiceGatewayConfig) null;
        NetSpUtils.INSTANCE.putServiceConfigIsp(null);
        if (block != null) {
            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new GatewayComponent$refreshGateway$1$1(block, null), 3, null);
        }
        PdNetworkManager.f10310INSTANCE.updateAbility();
        fillGateway();
    }

    @Override // com.pudutech.pd_network.IGateway
    public GatewayBean getGateway(GatewayName mGatewayName) {
        Object obj;
        List<GatewayBean> list;
        Object obj2;
        List<GatewayBean> list2;
        Intrinsics.checkParameterIsNotNull(mGatewayName, "mGatewayName");
        NetWorkLog.INSTANCE.mo3280i(TAG, "getGateway > mGatewayName:" + mGatewayName + ' ');
        ServiceGatewayConfig serviceGatewayConfig = serviceConfigBean;
        if (serviceGatewayConfig == null || !(serviceGatewayConfig == null || (list2 = serviceGatewayConfig.getList()) == null || !list2.isEmpty())) {
            return null;
        }
        ServiceGatewayConfig serviceGatewayConfig2 = serviceConfigBean;
        if (serviceGatewayConfig2 != null && (list = serviceGatewayConfig2.getList()) != null) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj2 = null;
                    break;
                }
                obj2 = it.next();
                if (Intrinsics.areEqual(((GatewayBean) obj2).getName(), mGatewayName.getTag())) {
                    break;
                }
            }
            GatewayBean gatewayBean = (GatewayBean) obj2;
            if (gatewayBean != null) {
                return gatewayBean;
            }
        }
        ServiceGatewayConfig serviceGatewayConfig3 = serviceConfigBean;
        if (serviceGatewayConfig3 == null) {
            Intrinsics.throwNpe();
        }
        List<GatewayBean> list3 = serviceGatewayConfig3.getList();
        if (!list3.isEmpty()) {
            Iterator<T> it2 = list3.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                if (Intrinsics.areEqual(((GatewayBean) obj).getName(), mGatewayName.getTag())) {
                    break;
                }
            }
            GatewayBean gatewayBean2 = (GatewayBean) obj;
            if (gatewayBean2 != null) {
                return gatewayBean2;
            }
            Job job2 = job;
            if (job2 != null && job2.isActive()) {
                return null;
            }
        }
        throw new IOException("cant find gateway:" + mGatewayName);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Throwable, T] */
    @Override // com.pudutech.pd_network.IGateway
    public GatewayBean getGatewayExecute(GatewayName mGatewayName, final boolean throwException) {
        Intrinsics.checkParameterIsNotNull(mGatewayName, "mGatewayName");
        NetWorkLog.INSTANCE.mo3280i(TAG, "getGatewayExecute > mGatewayName:" + mGatewayName + ' ' + throwException);
        GatewayBean gateway = getGateway(mGatewayName);
        if (gateway != null) {
            return gateway;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (Throwable) 0;
        OnGatewayAction onGatewayAction = new OnGatewayAction() { // from class: com.pudutech.pd_network.gateway.GatewayComponent$getGatewayExecute$action$1
            @Override // com.pudutech.pd_network.OnGatewayAction
            public void onSuccess(ServiceGatewayConfig bean) {
                String str;
                Intrinsics.checkParameterIsNotNull(bean, "bean");
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                GatewayComponent gatewayComponent = GatewayComponent.INSTANCE;
                str = GatewayComponent.TAG;
                netWorkLog.mo3280i(str, "getGatewayExecute.onSuccess");
                countDownLatch.countDown();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.pudutech.pd_network.OnGatewayAction
            public void onException(Throwable e) {
                String str;
                Intrinsics.checkParameterIsNotNull(e, "e");
                if (throwException) {
                    NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                    GatewayComponent gatewayComponent = GatewayComponent.INSTANCE;
                    str = GatewayComponent.TAG;
                    netWorkLog.mo3280i(str, "getGatewayExecute.onException " + e);
                    objectRef.element = e;
                    countDownLatch.countDown();
                }
            }
        };
        addOnGatewayAction(onGatewayAction);
        if (throwException) {
            try {
                countDownLatch.await(15L, TimeUnit.SECONDS);
            } catch (Exception e) {
                NetWorkLog.INSTANCE.mo3280i(TAG, "getGatewayExecute.awaitTimeout " + e);
                removeOnGatewayAction(onGatewayAction);
                throw e;
            }
        } else {
            countDownLatch.await();
        }
        removeOnGatewayAction(onGatewayAction);
        if (((Throwable) objectRef.element) != null) {
            Throwable th = (Throwable) objectRef.element;
            if (th != null) {
                throw th;
            }
            Intrinsics.throwNpe();
            throw th;
        }
        NetWorkLog.INSTANCE.mo3280i(TAG, "getGatewayExecute.end " + serviceConfigBean);
        GatewayBean gateway2 = getGateway(mGatewayName);
        if (gateway2 != null) {
            return gateway2;
        }
        throw new IOException("获取服务器配置信息失败");
    }

    @Override // com.pudutech.pd_network.IGateway
    public void addOnGatewayAction(OnGatewayAction action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        NetWorkLog.INSTANCE.mo3280i(TAG, "setOnGatewayAction > " + serviceConfigBean);
        ServiceGatewayConfig serviceGatewayConfig = serviceConfigBean;
        if (serviceGatewayConfig == null) {
            actionList.add(action);
            return;
        }
        if (serviceGatewayConfig == null) {
            Intrinsics.throwNpe();
        }
        action.onSuccess(serviceGatewayConfig);
    }

    @Override // com.pudutech.pd_network.IGateway
    public void removeOnGatewayAction(OnGatewayAction action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        actionList.remove(action);
    }

    @Override // com.pudutech.pd_network.IGateway
    public GatewayPlace gatewayPlace() {
        return NetSpUtils.INSTANCE.getGatewayPlace();
    }

    public final String host2AuthHost(String host) {
        List<GatewayBean> list;
        Object obj;
        Intrinsics.checkParameterIsNotNull(host, "host");
        ServiceGatewayConfig serviceGatewayConfig = serviceConfigBean;
        if (serviceGatewayConfig != null && (list = serviceGatewayConfig.getList()) != null) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (StringsKt.contains$default((CharSequence) host, (CharSequence) ((GatewayBean) obj).getHost(), false, 2, (Object) null)) {
                    break;
                }
            }
            GatewayBean gatewayBean = (GatewayBean) obj;
            if (gatewayBean != null) {
                String auth_host = gatewayBean.getAuth_host();
                if (auth_host != null) {
                    if (auth_host.length() > 0) {
                        String auth_host2 = gatewayBean.getAuth_host();
                        if (auth_host2 == null) {
                            Intrinsics.throwNpe();
                        }
                        return auth_host2;
                    }
                }
                try {
                    if (INSTANCE.getGateway(GatewayName.ROBOT_BIZ.INSTANCE) != null) {
                        return host;
                    }
                } catch (Exception e) {
                    NetWorkLog.INSTANCE.mo3279e(TAG, "host2AuthHost.error " + Log.getStackTraceString(e));
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        NetWorkLog.INSTANCE.mo3280i(TAG, "host2AuthHost > host:" + host + ' ' + serviceConfigBean);
        String auth_host3 = PdNetConfig.INSTANCE.getBaseGatewayBean().getAuth_host();
        if (auth_host3 == null) {
            Intrinsics.throwNpe();
        }
        return auth_host3;
    }
}
