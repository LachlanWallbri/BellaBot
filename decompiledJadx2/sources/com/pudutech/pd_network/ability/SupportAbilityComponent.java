package com.pudutech.pd_network.ability;

import com.pudutech.pd_network.ISupportAbility;
import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.DeviceType;
import com.pudutech.pd_network.log.NetWorkLog;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: SupportAbilityComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\fH\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\fH\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\fJ\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\fH\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/pd_network/ability/SupportAbilityComponent;", "Lcom/pudutech/pd_network/ISupportAbility;", "()V", "TAG", "", "api", "Lcom/pudutech/pd_network/ability/Api;", "getApi", "()Lcom/pudutech/pd_network/ability/Api;", "api$delegate", "Lkotlin/Lazy;", "crtAbility", "", "defaultAbility", "scope", "Lkotlinx/coroutines/CoroutineScope;", "updateJob", "Lkotlinx/coroutines/Job;", "abilities", "addAbilities", "", "ability", "checkNeedReport", "", "init", "setAbilities", "updateAbility", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SupportAbilityComponent implements ISupportAbility {
    private static Job updateJob;
    public static final SupportAbilityComponent INSTANCE = new SupportAbilityComponent();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final int defaultAbility = 199431;
    private static int crtAbility = defaultAbility;
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)).plus(new C5471xd3fc7690(CoroutineExceptionHandler.INSTANCE)));

    /* renamed from: api$delegate, reason: from kotlin metadata */
    private static final Lazy api = LazyKt.lazy(new Function0<Api>() { // from class: com.pudutech.pd_network.ability.SupportAbilityComponent$api$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Api invoke() {
            return (Api) PdNetworkManager.f10310INSTANCE.createService(Api.class);
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final Api getApi() {
        return (Api) api.getValue();
    }

    private SupportAbilityComponent() {
    }

    public final void init(int ability) {
        if (ability > 0) {
            crtAbility = ability;
        }
        updateAbility();
    }

    private final boolean checkNeedReport() {
        return PdNetConfig.INSTANCE.getReportAbility() && PdNetConfig.INSTANCE.isMainProcess() && PdNetConfig.INSTANCE.getDeviceType() == DeviceType.Robot && crtAbility > 0;
    }

    @Override // com.pudutech.pd_network.ISupportAbility
    public int abilities() {
        return crtAbility;
    }

    @Override // com.pudutech.pd_network.ISupportAbility
    public void addAbilities(int ability) {
        crtAbility |= ability;
        NetWorkLog.INSTANCE.mo3280i(TAG, "addAbilities > ability:" + ability + " crtAbility:" + crtAbility);
        updateAbility();
    }

    @Override // com.pudutech.pd_network.ISupportAbility
    public void setAbilities(int ability) {
        crtAbility = ability;
        NetWorkLog.INSTANCE.mo3280i(TAG, "setAbilities > ability:" + ability + ' ');
        updateAbility();
    }

    @Override // com.pudutech.pd_network.ISupportAbility
    public void updateAbility() {
        Job launch$default;
        NetWorkLog.INSTANCE.mo3280i(TAG, "updateAbility > " + crtAbility);
        if (!checkNeedReport()) {
            NetWorkLog.INSTANCE.mo3280i(TAG, "updateAbility > false");
            return;
        }
        Job job = updateJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new SupportAbilityComponent$updateAbility$1(null), 3, null);
        updateJob = launch$default;
    }
}
