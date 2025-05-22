package com.pudutech.mirsdk;

import com.aliyun.linksdk.alcs.AlcsConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.aidl.IPersonDetect;
import com.pudutech.mirsdk.aidl.PersonDetectListener;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.PersonListener;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* compiled from: PersonDetectService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000M\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\b*\u0001\u0012\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0010H\u0016J\u0014\u0010\u001a\u001a\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J \u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001fH\u0016J\b\u0010\"\u001a\u00020\u0017H\u0016J\u001e\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, m3961d2 = {"Lcom/pudutech/mirsdk/PersonDetectService;", "Lcom/pudutech/mirsdk/aidl/IPersonDetect$Stub;", "()V", "LISTENER_NAME", "", "TAG", "getTAG", "()Ljava/lang/String;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "isClosePersonDetect", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isStartPersonDetect", "mDetectListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/PersonDetectListener;", "personDetectListener", "com/pudutech/mirsdk/PersonDetectService$personDetectListener$1", "Lcom/pudutech/mirsdk/PersonDetectService$personDetectListener$1;", "robotPose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "addPersonDetectListener", "", "name", "l", "init", AlcsConstant.METHOD_DOMAIN_CORE, "removePersonDetectListener", "startDetect", "startDegree", "", "endDegree", "distance", "stopDetect", "updateRobotPos", "x", "y", CompressorStreamFactory.f8930Z, "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PersonDetectService extends IPersonDetect.Stub {
    private AIDLConnection<MirCoreInterface> coreService;
    private final String TAG = "PersonDetectService";
    private AtomicBoolean isClosePersonDetect = new AtomicBoolean(false);
    private AtomicBoolean isStartPersonDetect = new AtomicBoolean(false);
    private ThreadSafeListener<PersonDetectListener> mDetectListeners = new ThreadSafeListener<>();
    private final String LISTENER_NAME = "SDKPersonDetectServiceName";
    private Vector3d robotPose = new Vector3d(-100.0d, -100.0d, -100.0d);
    private final PersonDetectService$personDetectListener$1 personDetectListener = new PersonListener.Stub() { // from class: com.pudutech.mirsdk.PersonDetectService$personDetectListener$1
        @Override // com.pudutech.mirsdk.mircore.PersonListener
        public void onDetection(final int result, final int id, final double degree, final double distance, final double direction, final double vx, final double vy) {
            ThreadSafeListener threadSafeListener;
            Pdlog.m3273d(PersonDetectService.this.getTAG(), "onDetection result=" + result + ",degree=" + degree + ",distance" + distance);
            threadSafeListener = PersonDetectService.this.mDetectListeners;
            threadSafeListener.notify(new Function2<PersonDetectListener, String, Unit>() { // from class: com.pudutech.mirsdk.PersonDetectService$personDetectListener$1$onDetection$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(PersonDetectListener personDetectListener, String str) {
                    invoke2(personDetectListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PersonDetectListener it, String name) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    it.onPersonDetection(result, id, degree, distance, direction, vx, vy);
                }
            });
        }
    };

    public final String getTAG() {
        return this.TAG;
    }

    public final void init(AIDLConnection<MirCoreInterface> core) {
        Intrinsics.checkParameterIsNotNull(core, "core");
        this.coreService = core;
    }

    public final void updateRobotPos(double x, double y, double z) {
        this.robotPose.setX(x);
        this.robotPose.setY(y);
        this.robotPose.setZ(z);
    }

    @Override // com.pudutech.mirsdk.aidl.IPersonDetect
    public void addPersonDetectListener(String name, PersonDetectListener l) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(this.TAG, "addPersonDetectListener name=" + name);
        this.mDetectListeners.add(name, l);
    }

    @Override // com.pudutech.mirsdk.aidl.IPersonDetect
    public void removePersonDetectListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(this.TAG, "removePersonDetectListener name=" + name);
        this.mDetectListeners.remove(name);
    }

    @Override // com.pudutech.mirsdk.aidl.IPersonDetect
    public void startDetect(double startDegree, double endDegree, double distance) {
        if (this.isStartPersonDetect.get()) {
            Pdlog.m3273d(this.TAG, "detect is started");
        } else {
            this.isStartPersonDetect.set(true);
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new PersonDetectService$startDetect$1(this, startDegree, endDegree, distance, null), 3, null);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.IPersonDetect
    public void stopDetect() {
        if (this.isClosePersonDetect.get()) {
            Pdlog.m3273d(this.TAG, "closePersonDetect has closed");
        } else {
            this.isClosePersonDetect.set(true);
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new PersonDetectService$stopDetect$1(this, null), 3, null);
        }
    }
}
