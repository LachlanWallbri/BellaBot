package com.pudutech.bumblebee.robot_ui.track;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.robot_ui.manager.InitInterface;
import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.WorkRuler;
import com.pudutech.event_tracking._EventTrackingManagerBuilder;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.Shop_id_extKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrackInit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0006\u0010\t\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/TrackInit;", "Lcom/pudutech/bumblebee/robot_ui/manager/InitInterface;", "()V", "TAG", "", "init", "", "context", "Landroid/content/Context;", "updateShopId", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TrackInit implements InitInterface {
    public static final TrackInit INSTANCE = new TrackInit();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private TrackInit() {
    }

    @Override // com.pudutech.bumblebee.robot_ui.manager.InitInterface
    public void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        PuduEventTrackingManager.INSTANCE.init(context, new Function1<_EventTrackingManagerBuilder, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.track.TrackInit$init$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(_EventTrackingManagerBuilder _eventtrackingmanagerbuilder) {
                invoke2(_eventtrackingmanagerbuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(_EventTrackingManagerBuilder receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.setHardwareVersion(SDK.INSTANCE.getHardwareVersion());
                receiver.workRuler(new Function1<WorkRuler, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.track.TrackInit$init$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(WorkRuler workRuler) {
                        invoke2(workRuler);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(WorkRuler receiver2) {
                        Intrinsics.checkParameterIsNotNull(receiver2, "$receiver");
                        receiver2.setRepeatInterval(15000L);
                    }
                });
            }
        });
        updateShopId();
    }

    public final void updateShopId() {
        int shopId = Constant.INSTANCE.getShopId();
        Pdlog.m3273d(TAG, "updateShopId shopId:" + shopId + ' ');
        if (shopId != -1) {
            Shop_id_extKt.onShopIdChange(TrackingReportManager.INSTANCE, String.valueOf(shopId));
        }
    }
}
