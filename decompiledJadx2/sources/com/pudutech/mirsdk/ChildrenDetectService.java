package com.pudutech.mirsdk;

import com.aliyun.linksdk.alcs.AlcsConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.aidl.IChildrenDetectService;
import com.pudutech.mirsdk.aidl.OnChildrenDetectedListener;
import com.pudutech.mirsdk.mircore.ChildrenListener;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: ChildrenDetectService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000=\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t*\u0001\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0010H\u0016J\u0014\u0010\u0015\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0012\u0010\u0017\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0018\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/mirsdk/ChildrenDetectService;", "Lcom/pudutech/mirsdk/aidl/IChildrenDetectService$Stub;", "()V", "LISTENER_NAME", "", "TAG", "childrenListener", "com/pudutech/mirsdk/ChildrenDetectService$childrenListener$1", "Lcom/pudutech/mirsdk/ChildrenDetectService$childrenListener$1;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "detectStatus", "", "mDetectListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/OnChildrenDetectedListener;", "addChildrenDetectListener", "", "name", "l", "init", AlcsConstant.METHOD_DOMAIN_CORE, "removeChildrenDetectListener", "startDetect", "stopDetect", "Companion", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ChildrenDetectService extends IChildrenDetectService.Stub {
    private static final int DETECTING = 1;
    private static final int IDLE = 0;
    private static final int STOPPING = 2;
    private AIDLConnection<MirCoreInterface> coreService;
    private int detectStatus;
    private final String TAG = "ChildrenDetectService";
    private ThreadSafeListener<OnChildrenDetectedListener> mDetectListeners = new ThreadSafeListener<>();
    private final String LISTENER_NAME = "SDKChildrenDetectServiceName";
    private final ChildrenDetectService$childrenListener$1 childrenListener = new ChildrenListener.Stub() { // from class: com.pudutech.mirsdk.ChildrenDetectService$childrenListener$1
        @Override // com.pudutech.mirsdk.mircore.ChildrenListener
        public void onDetection(final int flag, final double px, final double py, final double distance, final double degree) {
            String str;
            ThreadSafeListener threadSafeListener;
            ThreadSafeListener threadSafeListener2;
            str = ChildrenDetectService.this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onDetection  flag:");
            sb.append(flag);
            sb.append(",px:");
            sb.append(px);
            sb.append(",py:");
            sb.append(py);
            sb.append(",distance:");
            sb.append(distance);
            sb.append(",degree:");
            sb.append(degree);
            sb.append(",mDetectListeners.size=");
            threadSafeListener = ChildrenDetectService.this.mDetectListeners;
            sb.append(threadSafeListener.counts());
            Pdlog.m3273d(str, sb.toString());
            threadSafeListener2 = ChildrenDetectService.this.mDetectListeners;
            threadSafeListener2.notify(new Function2<OnChildrenDetectedListener, String, Unit>() { // from class: com.pudutech.mirsdk.ChildrenDetectService$childrenListener$1$onDetection$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(OnChildrenDetectedListener onChildrenDetectedListener, String str2) {
                    invoke2(onChildrenDetectedListener, str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(OnChildrenDetectedListener it, String str2) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                    it.onChildrenDetected(flag, px, py, distance, degree);
                }
            });
        }
    };

    public final void init(AIDLConnection<MirCoreInterface> core) {
        Intrinsics.checkParameterIsNotNull(core, "core");
        this.coreService = core;
        Pdlog.m3273d(this.TAG, "init -----" + this.coreService);
    }

    @Override // com.pudutech.mirsdk.aidl.IChildrenDetectService
    public int startDetect() {
        int i = this.detectStatus;
        if (i == 1) {
            Pdlog.m3273d(this.TAG, "detect already has started");
            return 1;
        }
        if (i == 2) {
            Pdlog.m3273d(this.TAG, "startDetect fail because detect is stopping");
            return -1;
        }
        this.detectStatus = 1;
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ChildrenDetectService$startDetect$1(this, null), 3, null);
        return 0;
    }

    @Override // com.pudutech.mirsdk.aidl.IChildrenDetectService
    public void addChildrenDetectListener(String name, OnChildrenDetectedListener l) {
        boolean z = true;
        Pdlog.m3273d(this.TAG, "addChildrenDetectListener name=" + name + ' ');
        String str = name;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (z || l == null) {
            return;
        }
        this.mDetectListeners.add(name, l);
    }

    @Override // com.pudutech.mirsdk.aidl.IChildrenDetectService
    public int stopDetect() {
        int i = this.detectStatus;
        if (i == 0) {
            Pdlog.m3273d(this.TAG, "stopDetect fail because detect is not start");
            return -1;
        }
        if (i == 2) {
            Pdlog.m3273d(this.TAG, "detect already execute stop");
            return 1;
        }
        this.detectStatus = 2;
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new ChildrenDetectService$stopDetect$1(this, null), 3, null);
        return 0;
    }

    @Override // com.pudutech.mirsdk.aidl.IChildrenDetectService
    public void removeChildrenDetectListener(String name) {
        boolean z = true;
        Pdlog.m3273d(this.TAG, "removePersonDetectListener name=" + name);
        String str = name;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (z) {
            return;
        }
        this.mDetectListeners.remove(name);
    }
}
