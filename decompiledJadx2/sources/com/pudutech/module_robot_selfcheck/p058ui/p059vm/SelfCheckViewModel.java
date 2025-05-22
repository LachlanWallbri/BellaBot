package com.pudutech.module_robot_selfcheck.p058ui.p059vm;

import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest;
import com.pudutech.module_robot_selfcheck.domain.request.SelfCheckRequest;
import com.pudutech.module_robot_selfcheck.domain.request.UpdateSoftwareRequest;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: SelfCheckViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/vm/SelfCheckViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "activeRequest", "Lcom/pudutech/module_robot_selfcheck/domain/request/ActiveRequest;", "getActiveRequest", "()Lcom/pudutech/module_robot_selfcheck/domain/request/ActiveRequest;", "activeRequest$delegate", "Lkotlin/Lazy;", "selfCheckRequest", "Lcom/pudutech/module_robot_selfcheck/domain/request/SelfCheckRequest;", "getSelfCheckRequest", "()Lcom/pudutech/module_robot_selfcheck/domain/request/SelfCheckRequest;", "selfCheckRequest$delegate", "updateSoftwareRequest", "Lcom/pudutech/module_robot_selfcheck/domain/request/UpdateSoftwareRequest;", "getUpdateSoftwareRequest", "()Lcom/pudutech/module_robot_selfcheck/domain/request/UpdateSoftwareRequest;", "updateSoftwareRequest$delegate", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelfCheckViewModel extends BaseViewModel {

    /* renamed from: activeRequest$delegate, reason: from kotlin metadata */
    private final Lazy activeRequest = LazyKt.lazy(new Function0<ActiveRequest>() { // from class: com.pudutech.module_robot_selfcheck.ui.vm.SelfCheckViewModel$activeRequest$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActiveRequest invoke() {
            return new ActiveRequest(SelfCheckViewModel.this);
        }
    });

    /* renamed from: selfCheckRequest$delegate, reason: from kotlin metadata */
    private final Lazy selfCheckRequest = LazyKt.lazy(new Function0<SelfCheckRequest>() { // from class: com.pudutech.module_robot_selfcheck.ui.vm.SelfCheckViewModel$selfCheckRequest$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SelfCheckRequest invoke() {
            return new SelfCheckRequest(SelfCheckViewModel.this);
        }
    });

    /* renamed from: updateSoftwareRequest$delegate, reason: from kotlin metadata */
    private final Lazy updateSoftwareRequest = LazyKt.lazy(new Function0<UpdateSoftwareRequest>() { // from class: com.pudutech.module_robot_selfcheck.ui.vm.SelfCheckViewModel$updateSoftwareRequest$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final UpdateSoftwareRequest invoke() {
            return new UpdateSoftwareRequest(SelfCheckViewModel.this);
        }
    });

    public final ActiveRequest getActiveRequest() {
        return (ActiveRequest) this.activeRequest.getValue();
    }

    public final SelfCheckRequest getSelfCheckRequest() {
        return (SelfCheckRequest) this.selfCheckRequest.getValue();
    }

    public final UpdateSoftwareRequest getUpdateSoftwareRequest() {
        return (UpdateSoftwareRequest) this.updateSoftwareRequest.getValue();
    }
}
