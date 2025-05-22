package com.pudutech.module_robot_selfcheck.repository;

import androidx.collection.ArrayMap;
import com.pudutech.disinfect.baselib.base.BaseRepository;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.module_robot_selfcheck.RobotInitProcessor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelfCheckRepository.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042%\u0010\u0005\u001a!\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00040\u0006j\u0002`\u000bJb\u0010\f\u001a\u00020\u00042%\u0010\r\u001a!\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00040\u0006j\u0002`\u001023\u0010\u0011\u001a/\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0012¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00040\u0006j\u0002`\u0016¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/repository/SelfCheckRepository;", "Lcom/pudutech/disinfect/baselib/base/BaseRepository;", "()V", "checkLocation", "", "onLocationStateListener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isInit", "Lcom/pudutech/module_robot_selfcheck/domain/request/OnLocationStateListener;", "checkRobotInit", "onInitStateListener", "Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor$InitStatus;", "state", "Lcom/pudutech/module_robot_selfcheck/domain/request/OnInitStateListener;", "onSelfCheckFailListener", "Landroidx/collection/ArrayMap;", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "", "errorMap", "Lcom/pudutech/module_robot_selfcheck/OnSelfCheckFailListener;", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelfCheckRepository extends BaseRepository {

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[RobotInitProcessor.InitStatus.values().length];

        static {
            $EnumSwitchMapping$0[RobotInitProcessor.InitStatus.INIT.ordinal()] = 1;
        }
    }

    public final void checkRobotInit(Function1<? super RobotInitProcessor.InitStatus, Unit> onInitStateListener, Function1<? super ArrayMap<InitStep, String>, Unit> onSelfCheckFailListener) {
        Intrinsics.checkParameterIsNotNull(onInitStateListener, "onInitStateListener");
        Intrinsics.checkParameterIsNotNull(onSelfCheckFailListener, "onSelfCheckFailListener");
        if (WhenMappings.$EnumSwitchMapping$0[RobotInitProcessor.INSTANCE.getINSTANCE().getInitStatus().ordinal()] == 1) {
            RobotInitProcessor instance = RobotInitProcessor.INSTANCE.getINSTANCE();
            instance.addOnInitStateListener(onInitStateListener);
            instance.addOnSelfCheckFailureListener(onSelfCheckFailListener);
        } else {
            onInitStateListener.invoke(RobotInitProcessor.INSTANCE.getINSTANCE().getInitStatus());
            if (RobotInitProcessor.INSTANCE.getINSTANCE().getInitStatus() != RobotInitProcessor.InitStatus.SUCCESS) {
                onSelfCheckFailListener.invoke(RobotInitProcessor.INSTANCE.getINSTANCE().getErrorMsgs());
            }
        }
    }

    public final void checkLocation(final Function1<? super Boolean, Unit> onLocationStateListener) {
        Intrinsics.checkParameterIsNotNull(onLocationStateListener, "onLocationStateListener");
        RobotMapManager.INSTANCE.checkLocationInit(new Function1<Boolean, Unit>() { // from class: com.pudutech.module_robot_selfcheck.repository.SelfCheckRepository$checkLocation$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                Function1.this.invoke(Boolean.valueOf(z));
            }
        }, 10000L);
    }
}
