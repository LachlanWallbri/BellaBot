package com.pudutech.bumblebee.presenter.rotation_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.PoseListener;
import com.pudutech.bumblebee.presenter.rotation_task.RotationContract;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RotationPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RotationPresenter$rotate$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ double $diff;
    final /* synthetic */ RotationPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RotationPresenter$rotate$2(RotationPresenter rotationPresenter, double d) {
        super(0);
        this.this$0 = rotationPresenter;
        this.$diff = d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RotationPresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.presenter.rotation_task.RotationPresenter$rotate$2$2 */
    /* loaded from: classes4.dex */
    public static final class C41272 extends Lambda implements Function0<Unit> {
        C41272() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            Pdlog.m3277w(RotationPresenter$rotate$2.this.this$0.getTAG(), "rotate overtime");
            RotationPresenter$rotate$2.this.this$0.runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.rotation_task.RotationPresenter.rotate.2.2.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    RotationPresenter$rotate$2.this.this$0.endListenerAndTimer();
                    RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
                    if (moveAction != null) {
                        moveAction.rotate(0.0d);
                    }
                    RotationPresenter$rotate$2.this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.rotation_task.RotationPresenter.rotate.2.2.1.1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            RotationContract.ViewInterface theView;
                            theView = RotationPresenter$rotate$2.this.this$0.getTheView();
                            if (theView != null) {
                                theView.showRotateEvent(RotationContract.ViewEvent.OVERTIME);
                            }
                        }
                    });
                }
            });
        }
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        RotationPresenter$listener$1 rotationPresenter$listener$1;
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.rotation_task.RotationPresenter$rotate$2.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                RotationContract.ViewInterface theView;
                theView = RotationPresenter$rotate$2.this.this$0.getTheView();
                if (theView != null) {
                    theView.showRotateEvent(RotationContract.ViewEvent.ROTATING);
                }
            }
        });
        BaseMultiListenerImpl<PoseListener> poseListeners = SDK.INSTANCE.getPoseListeners();
        rotationPresenter$listener$1 = this.this$0.listener;
        poseListeners.addListener(rotationPresenter$listener$1);
        TimerThread.INSTANCE.rePost(this.this$0.toString(), this.this$0.getTAG(), (r21 & 4) != 0 ? -1L : 10000L, (r21 & 8) != 0 ? -1L : 0L, (r21 & 16) != 0 ? false : false, new C41272());
        RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
        if (moveAction != null) {
            moveAction.rotate(this.$diff);
        }
    }
}
