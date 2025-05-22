package com.pudutech.module_robot_selfcheck.domain.request;

import androidx.collection.ArrayMap;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.disinfect.baselib.base.BaseRequest;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.module_robot_selfcheck.RobotInitProcessor;
import com.pudutech.module_robot_selfcheck.repository.SelfCheckRepository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;

/* compiled from: SelfCheckRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010$\u001a\u00020\u0016H\u0014J\u0006\u0010%\u001a\u00020\u0016J\u0006\u0010&\u001a\u00020\u0016R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR!\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u000e\u0010\tR@\u0010\u0010\u001a4\u0012*\u0012( \u0015*\u0013\u0018\u00010\u0007¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u00140\u0007¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00160\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010\u0017\u001a4\u0012*\u0012( \u0015*\u0013\u0018\u00010\r¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u00140\r¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00160\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\\\u0010\u0018\u001aP\u0012F\u0012D\u0012\u0004\u0012\u00020\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b \u0015*!\u0012\u0004\u0012\u00020\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0018\u00010\u0019¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u00140\u0019¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00160\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\u001c\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u00190\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u000b\u001a\u0004\b\u001d\u0010\tR\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u000b\u001a\u0004\b!\u0010\"¨\u0006'"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/domain/request/SelfCheckRequest;", "Lcom/pudutech/disinfect/baselib/base/BaseRequest;", "VM", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "(Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;)V", "initStateLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor$InitStatus;", "getInitStateLiveData", "()Landroidx/lifecycle/MutableLiveData;", "initStateLiveData$delegate", "Lkotlin/Lazy;", "locationStateLiveData", "", "getLocationStateLiveData", "locationStateLiveData$delegate", "onInitStateListener", "Lkotlin/reflect/KFunction1;", "Lkotlin/ParameterName;", "name", "p0", "kotlin.jvm.PlatformType", "", "onLocationStateListener", "onSelfCheckListener", "Landroidx/collection/ArrayMap;", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "", "selfCheckFailLiveData", "getSelfCheckFailLiveData", "selfCheckFailLiveData$delegate", "selfCheckRepository", "Lcom/pudutech/module_robot_selfcheck/repository/SelfCheckRepository;", "getSelfCheckRepository", "()Lcom/pudutech/module_robot_selfcheck/repository/SelfCheckRepository;", "selfCheckRepository$delegate", "onCleared", "requestCheckLocation", "requestCheckRobotInit", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelfCheckRequest extends BaseRequest {

    /* renamed from: initStateLiveData$delegate, reason: from kotlin metadata */
    private final Lazy initStateLiveData;

    /* renamed from: locationStateLiveData$delegate, reason: from kotlin metadata */
    private final Lazy locationStateLiveData;
    private final KFunction<Unit> onInitStateListener;
    private final KFunction<Unit> onLocationStateListener;
    private final KFunction<Unit> onSelfCheckListener;

    /* renamed from: selfCheckFailLiveData$delegate, reason: from kotlin metadata */
    private final Lazy selfCheckFailLiveData;

    /* renamed from: selfCheckRepository$delegate, reason: from kotlin metadata */
    private final Lazy selfCheckRepository;

    private final SelfCheckRepository getSelfCheckRepository() {
        return (SelfCheckRepository) this.selfCheckRepository.getValue();
    }

    public final MutableLiveData<RobotInitProcessor.InitStatus> getInitStateLiveData() {
        return (MutableLiveData) this.initStateLiveData.getValue();
    }

    public final MutableLiveData<Boolean> getLocationStateLiveData() {
        return (MutableLiveData) this.locationStateLiveData.getValue();
    }

    public final MutableLiveData<ArrayMap<InitStep, String>> getSelfCheckFailLiveData() {
        return (MutableLiveData) this.selfCheckFailLiveData.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelfCheckRequest(BaseViewModel VM) {
        super(VM);
        Intrinsics.checkParameterIsNotNull(VM, "VM");
        this.selfCheckRepository = LazyKt.lazy(new Function0<SelfCheckRepository>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.SelfCheckRequest$selfCheckRepository$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SelfCheckRepository invoke() {
                return new SelfCheckRepository();
            }
        });
        this.initStateLiveData = LazyKt.lazy(new Function0<MutableLiveData<RobotInitProcessor.InitStatus>>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.SelfCheckRequest$initStateLiveData$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MutableLiveData<RobotInitProcessor.InitStatus> invoke() {
                return new MutableLiveData<>();
            }
        });
        this.locationStateLiveData = LazyKt.lazy(new Function0<MutableLiveData<Boolean>>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.SelfCheckRequest$locationStateLiveData$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MutableLiveData<Boolean> invoke() {
                return new MutableLiveData<>();
            }
        });
        this.selfCheckFailLiveData = LazyKt.lazy(new Function0<MutableLiveData<ArrayMap<InitStep, String>>>() { // from class: com.pudutech.module_robot_selfcheck.domain.request.SelfCheckRequest$selfCheckFailLiveData$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MutableLiveData<ArrayMap<InitStep, String>> invoke() {
                return new MutableLiveData<>();
            }
        });
        this.onInitStateListener = new SelfCheckRequest$onInitStateListener$1(getInitStateLiveData());
        this.onSelfCheckListener = new SelfCheckRequest$onSelfCheckListener$1(getSelfCheckFailLiveData());
        this.onLocationStateListener = new SelfCheckRequest$onLocationStateListener$1(getLocationStateLiveData());
    }

    public final void requestCheckRobotInit() {
        getSelfCheckRepository().checkRobotInit((Function1) this.onInitStateListener, (Function1) this.onSelfCheckListener);
    }

    public final void requestCheckLocation() {
        getSelfCheckRepository().checkLocation((Function1) this.onLocationStateListener);
    }

    @Override // com.pudutech.disinfect.baselib.base.BaseRequest
    protected void onCleared() {
        RobotInitProcessor.INSTANCE.getINSTANCE().removeOnInitStateListener((Function1) this.onInitStateListener);
        RobotInitProcessor.INSTANCE.getINSTANCE().removeSelfCheckFailureListener((Function1) this.onSelfCheckListener);
    }
}
