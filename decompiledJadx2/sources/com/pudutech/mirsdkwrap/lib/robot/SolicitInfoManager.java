package com.pudutech.mirsdkwrap.lib.robot;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.ISolicitListener;
import com.pudutech.mirsdk.aidl.IWelcomeListener;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdkwrap.lib.interf.ListenerList;
import com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: SolicitInfoManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000]\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f*\u0003\u0013\u001e!\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004789:B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\tJ\u000e\u0010&\u001a\u00020$2\u0006\u0010%\u001a\u00020\u000fJ\u000e\u0010'\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001aJ\u000e\u0010(\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0016J\b\u0010)\u001a\u0004\u0018\u00010\u0006J\u001d\u0010*\u001a\u00020$2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010+\u001a\u00020,H\u0000¢\u0006\u0002\b-J\u0006\u0010.\u001a\u00020$J\u000e\u0010/\u001a\u00020$2\u0006\u0010%\u001a\u00020\tJ\u000e\u00100\u001a\u00020$2\u0006\u0010%\u001a\u00020\u000fJ\u000e\u00101\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001aJ\u000e\u00102\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0016J\u0006\u00103\u001a\u00020$J\u0006\u00104\u001a\u00020$J\u0006\u00105\u001a\u00020$J\u0006\u00106\u001a\u00020$R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u0010\u0010\u000bR\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\r\u001a\u0004\b\u0017\u0010\u000bR!\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u001b\u0010\u000bR\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001fR\u0010\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\"¨\u0006;"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager;", "", "()V", "TAG", "", "mirSdk", "Lcom/pudutech/mirsdk/aidl/SDKInterface;", "onFaceChangerListeners", "Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnFaceChangeListener;", "getOnFaceChangerListeners", "()Lcom/pudutech/mirsdkwrap/lib/interf/ListenerList;", "onFaceChangerListeners$delegate", "Lkotlin/Lazy;", "onFaceDetectListeners", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnFaceDetectChangeListener;", "getOnFaceDetectListeners", "onFaceDetectListeners$delegate", "onFaceListener", "com/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$onFaceListener$1", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$onFaceListener$1;", "onPersonDetectionListeners", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnPersonDetectionListener;", "getOnPersonDetectionListeners", "onPersonDetectionListeners$delegate", "onWeComeApproachListeners", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnWeComeApproachListener;", "getOnWeComeApproachListeners", "onWeComeApproachListeners$delegate", "solicitListener", "com/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$solicitListener$1", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$solicitListener$1;", "welcomeListener", "com/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$welcomeListener$1", "Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$welcomeListener$1;", "addFaceChangeListener", "", "l", "addFaceDetectListener", "addPersonApproachListener", "addPersonDetectionListener", "getMirSdk", "init", "mirsdkListener", "Lcom/pudutech/mirsdkwrap/lib/robot/MirSdkListenerWrap;", "init$module_robot_mirsdk_wrapper_release", "openFace", "removeFaceChangeListener", "removeFaceDetectListener", "removePersonApproachListener", "removePersonDetectionListener", "startSolicit", "startWeCome", "stopSolicit", "stopWeCome", "OnFaceChangeListener", "OnFaceDetectChangeListener", "OnPersonDetectionListener", "OnWeComeApproachListener", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SolicitInfoManager {
    private static SDKInterface mirSdk;
    public static final SolicitInfoManager INSTANCE = new SolicitInfoManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* renamed from: onFaceDetectListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onFaceDetectListeners = LazyKt.lazy(new Function0<ListenerList<OnFaceDetectChangeListener>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$onFaceDetectListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<SolicitInfoManager.OnFaceDetectChangeListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: onPersonDetectionListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onPersonDetectionListeners = LazyKt.lazy(new Function0<ListenerList<OnPersonDetectionListener>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$onPersonDetectionListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<SolicitInfoManager.OnPersonDetectionListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: onWeComeApproachListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onWeComeApproachListeners = LazyKt.lazy(new Function0<ListenerList<OnWeComeApproachListener>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$onWeComeApproachListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<SolicitInfoManager.OnWeComeApproachListener> invoke() {
            return new ListenerList<>();
        }
    });

    /* renamed from: onFaceChangerListeners$delegate, reason: from kotlin metadata */
    private static final Lazy onFaceChangerListeners = LazyKt.lazy(new Function0<ListenerList<OnFaceChangeListener>>() { // from class: com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$onFaceChangerListeners$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ListenerList<SolicitInfoManager.OnFaceChangeListener> invoke() {
            return new ListenerList<>();
        }
    });
    private static final SolicitInfoManager$onFaceListener$1 onFaceListener = new Function4<Integer, Double, Double, Double, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$onFaceListener$1
        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(Integer num, Double d, Double d2, Double d3) {
            invoke(num.intValue(), d.doubleValue(), d2.doubleValue(), d3.doubleValue());
            return Unit.INSTANCE;
        }

        public void invoke(final int p0, final double p1, final double p2, final double p3) {
            ListenerList onFaceChangerListeners2;
            onFaceChangerListeners2 = SolicitInfoManager.INSTANCE.getOnFaceChangerListeners();
            if (onFaceChangerListeners2 != null) {
                onFaceChangerListeners2.forEach(new Function1<SolicitInfoManager.OnFaceChangeListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$onFaceListener$1$invoke$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SolicitInfoManager.OnFaceChangeListener onFaceChangeListener) {
                        invoke2(onFaceChangeListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SolicitInfoManager.OnFaceChangeListener it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        it.onFaceChanger(p0, p1, p2, p3);
                    }
                });
            }
        }
    };
    private static SolicitInfoManager$solicitListener$1 solicitListener = new ISolicitListener.Stub() { // from class: com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$solicitListener$1
        @Override // com.pudutech.mirsdk.aidl.ISolicitListener
        public void onFaceDetectResult(final int flag, final double yaw, final double pitch, final double distance) {
            ListenerList onFaceDetectListeners2;
            onFaceDetectListeners2 = SolicitInfoManager.INSTANCE.getOnFaceDetectListeners();
            onFaceDetectListeners2.forEach(new Function1<SolicitInfoManager.OnFaceDetectChangeListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$solicitListener$1$onFaceDetectResult$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SolicitInfoManager.OnFaceDetectChangeListener onFaceDetectChangeListener) {
                    invoke2(onFaceDetectChangeListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SolicitInfoManager.OnFaceDetectChangeListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onFaceDetectResult(flag, yaw, pitch, distance);
                }
            });
        }

        @Override // com.pudutech.mirsdk.aidl.ISolicitListener
        public void onPersonDetection(final int result, final double degree, final double distance, final int roteStatus) {
            ListenerList onPersonDetectionListeners2;
            onPersonDetectionListeners2 = SolicitInfoManager.INSTANCE.getOnPersonDetectionListeners();
            onPersonDetectionListeners2.forEach(new Function1<SolicitInfoManager.OnPersonDetectionListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$solicitListener$1$onPersonDetection$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SolicitInfoManager.OnPersonDetectionListener onPersonDetectionListener) {
                    invoke2(onPersonDetectionListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SolicitInfoManager.OnPersonDetectionListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onPersonDetection(result, degree, distance, roteStatus);
                }
            });
        }
    };
    private static SolicitInfoManager$welcomeListener$1 welcomeListener = new IWelcomeListener.Stub() { // from class: com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$welcomeListener$1
        @Override // com.pudutech.mirsdk.aidl.IWelcomeListener
        public void onPersonApproaching(final int id) {
            ListenerList onWeComeApproachListeners2;
            onWeComeApproachListeners2 = SolicitInfoManager.INSTANCE.getOnWeComeApproachListeners();
            onWeComeApproachListeners2.forEach(new Function1<SolicitInfoManager.OnWeComeApproachListener, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.SolicitInfoManager$welcomeListener$1$onPersonApproaching$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SolicitInfoManager.OnWeComeApproachListener onWeComeApproachListener) {
                    invoke2(onWeComeApproachListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SolicitInfoManager.OnWeComeApproachListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onPersonApproach(id);
                }
            });
        }
    };

    /* compiled from: SolicitInfoManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnFaceChangeListener;", "", "onFaceChanger", "", "p0", "", "p1", "", "p2", "p3", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface OnFaceChangeListener {
        void onFaceChanger(int p0, double p1, double p2, double p3);
    }

    /* compiled from: SolicitInfoManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnFaceDetectChangeListener;", "", "onFaceDetectResult", "", "p0", "", "p1", "", "p2", "p3", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface OnFaceDetectChangeListener {
        void onFaceDetectResult(int p0, double p1, double p2, double p3);
    }

    /* compiled from: SolicitInfoManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnPersonDetectionListener;", "", "onPersonDetection", "", "p0", "", "p1", "", "p2", "p3", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface OnPersonDetectionListener {
        void onPersonDetection(int p0, double p1, double p2, int p3);
    }

    /* compiled from: SolicitInfoManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/SolicitInfoManager$OnWeComeApproachListener;", "", "onPersonApproach", "", "p0", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface OnWeComeApproachListener {
        void onPersonApproach(int p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<OnFaceChangeListener> getOnFaceChangerListeners() {
        return (ListenerList) onFaceChangerListeners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<OnFaceDetectChangeListener> getOnFaceDetectListeners() {
        return (ListenerList) onFaceDetectListeners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<OnPersonDetectionListener> getOnPersonDetectionListeners() {
        return (ListenerList) onPersonDetectionListeners.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ListenerList<OnWeComeApproachListener> getOnWeComeApproachListeners() {
        return (ListenerList) onWeComeApproachListeners.getValue();
    }

    private SolicitInfoManager() {
    }

    public final void addFaceChangeListener(OnFaceChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnFaceChangerListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
        Pdlog.m3273d(TAG, "addFaceChangeListener : l = " + l + "; size = " + getOnFaceChangerListeners().size());
    }

    public final void removeFaceChangeListener(OnFaceChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(TAG, "closeFace :  mirSdk = " + mirSdk);
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            sDKInterface.controlFaceDetect(false);
        }
        getOnFaceChangerListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void openFace() {
        Pdlog.m3273d(TAG, "openFace :  mirSdk = " + mirSdk);
        SDKInterface sDKInterface = mirSdk;
        if (sDKInterface != null) {
            sDKInterface.controlFaceDetect(true);
        }
    }

    public final void addFaceDetectListener(OnFaceDetectChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnFaceDetectListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
        Pdlog.m3273d(TAG, "OnFaceDetectChangeListener : l = " + l + "; size = " + getOnFaceDetectListeners().size());
    }

    public final void removeFaceDetectListener(OnFaceDetectChangeListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnFaceDetectListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void addPersonDetectionListener(OnPersonDetectionListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnPersonDetectionListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
        Pdlog.m3273d(TAG, "addPersonDetectionListener : l = " + l + "; size = " + getOnPersonDetectionListeners().size());
    }

    public final void removePersonDetectionListener(OnPersonDetectionListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnPersonDetectionListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void addPersonApproachListener(OnWeComeApproachListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnWeComeApproachListeners().addNotSame$module_robot_mirsdk_wrapper_release(l);
        Pdlog.m3273d(TAG, "addPersonApproachListener : l = " + l + "; size = " + getOnPersonDetectionListeners().size());
    }

    public final void removePersonApproachListener(OnWeComeApproachListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        getOnWeComeApproachListeners().remove$module_robot_mirsdk_wrapper_release(l);
    }

    public final void init$module_robot_mirsdk_wrapper_release(SDKInterface mirSdk2, MirSdkListenerWrap mirsdkListener) {
        Intrinsics.checkParameterIsNotNull(mirSdk2, "mirSdk");
        Intrinsics.checkParameterIsNotNull(mirsdkListener, "mirsdkListener");
        mirSdk = mirSdk2;
        mirsdkListener.getRobotFaceListeners().addNotSame$module_robot_mirsdk_wrapper_release(onFaceListener);
    }

    public final SDKInterface getMirSdk() {
        return mirSdk;
    }

    public final void startSolicit() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SolicitInfoManager$startSolicit$1(null), 3, null);
    }

    public final void startWeCome() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SolicitInfoManager$startWeCome$1(null), 3, null);
    }

    public final void stopSolicit() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SolicitInfoManager$stopSolicit$1(null), 3, null);
    }

    public final void stopWeCome() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SolicitInfoManager$stopWeCome$1(null), 3, null);
    }
}
