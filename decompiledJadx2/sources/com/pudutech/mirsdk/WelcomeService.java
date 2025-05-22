package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.aidl.IWelcomeListener;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.PersonListener;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: WelcomeService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Q\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b*\u0001\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0012J\b\u0010\u001b\u001a\u00020\u0018H\u0002J\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007J\b\u0010\u001d\u001a\u00020\u0018H\u0002J\u0006\u0010\u001e\u001a\u00020\u0018J\u0006\u0010\u001f\u001a\u00020\u0018R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000e0\u0014j\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000e`\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/mirsdk/WelcomeService;", "", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "(Lcom/pudutech/base/architecture/AIDLConnection;)V", "TAG", "", "mPersonApproaching", "", "mPersonListener", "com/pudutech/mirsdk/WelcomeService$mPersonListener$1", "Lcom/pudutech/mirsdk/WelcomeService$mPersonListener$1;", "mStartStatus", "", "mStopStatus", "mWelcomeListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/IWelcomeListener;", MapElement.Key.MAP, "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "addWelcomeListener", "", "name", "l", "closePersonDetect", "removeWelcomeListener", "resetFlag", "startWelcome", "stopWelcome", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class WelcomeService {
    private final String TAG;
    private final AIDLConnection<MirCoreInterface> coreService;
    private double mPersonApproaching;
    private WelcomeService$mPersonListener$1 mPersonListener;
    private boolean mStartStatus;
    private boolean mStopStatus;
    private ThreadSafeListener<IWelcomeListener> mWelcomeListeners;
    private HashMap<Integer, Boolean> map;

    /* JADX WARN: Type inference failed for: r3v4, types: [com.pudutech.mirsdk.WelcomeService$mPersonListener$1] */
    public WelcomeService(AIDLConnection<MirCoreInterface> coreService) {
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        this.coreService = coreService;
        this.TAG = "WelcomeService";
        this.mWelcomeListeners = new ThreadSafeListener<>();
        this.map = new HashMap<>();
        this.mPersonApproaching = 1.5d;
        this.mPersonListener = new PersonListener.Stub() { // from class: com.pudutech.mirsdk.WelcomeService$mPersonListener$1
            @Override // com.pudutech.mirsdk.mircore.PersonListener
            public void onDetection(int result, final int id, double degree, double distance, double direction, double vx, double vy) {
                String str;
                double d;
                HashMap hashMap;
                String str2;
                String str3;
                HashMap hashMap2;
                ThreadSafeListener threadSafeListener;
                String str4;
                HashMap hashMap3;
                String str5;
                String str6;
                str = WelcomeService.this.TAG;
                Pdlog.m3273d(str, "onDetection result=" + result + ",id=" + id + ",degree=" + degree + ",distance=" + distance + ",direction=" + direction);
                d = WelcomeService.this.mPersonApproaching;
                if (distance > d) {
                    str6 = WelcomeService.this.TAG;
                    Pdlog.m3273d(str6, "onDetection distance is far");
                    return;
                }
                hashMap = WelcomeService.this.map;
                Boolean bool = (Boolean) hashMap.get(Integer.valueOf(id));
                str2 = WelcomeService.this.TAG;
                Pdlog.m3273d(str2, "onDetection hasNotify=" + bool);
                if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                    str5 = WelcomeService.this.TAG;
                    Pdlog.m3273d(str5, "onDetection the person id hasNotify ");
                    return;
                }
                if (bool == null) {
                    str4 = WelcomeService.this.TAG;
                    Pdlog.m3273d(str4, "onDetection clear person id table");
                    hashMap3 = WelcomeService.this.map;
                    hashMap3.clear();
                }
                double d2 = (direction * 180) / 3.14f;
                if (d2 < 0) {
                    d2 += 360;
                }
                str3 = WelcomeService.this.TAG;
                Pdlog.m3273d(str3, "onDetection angle=" + d2);
                if (d2 < 150.0d || d2 > 210.0d) {
                    return;
                }
                hashMap2 = WelcomeService.this.map;
                hashMap2.put(Integer.valueOf(id), true);
                threadSafeListener = WelcomeService.this.mWelcomeListeners;
                threadSafeListener.notify(new Function2<IWelcomeListener, String, Unit>() { // from class: com.pudutech.mirsdk.WelcomeService$mPersonListener$1$onDetection$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IWelcomeListener iWelcomeListener, String str7) {
                        invoke2(iWelcomeListener, str7);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IWelcomeListener it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.onPersonApproaching(id);
                    }
                });
            }
        };
    }

    public final void addWelcomeListener(String name, IWelcomeListener l) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(l, "l");
        Pdlog.m3273d(this.TAG, "addWelcomeListener name=" + name);
        this.mWelcomeListeners.add(name, l);
    }

    public final void removeWelcomeListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(this.TAG, "removeWelcomeListener name=" + name);
        this.mWelcomeListeners.remove(name);
    }

    public final void startWelcome() {
        if (this.mStartStatus) {
            Pdlog.m3273d(this.TAG, "startWelcome has started");
            return;
        }
        Pdlog.m3273d(this.TAG, "startWelcome  ");
        this.mStartStatus = true;
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new WelcomeService$startWelcome$1(this, null), 3, null);
    }

    public final void stopWelcome() {
        Pdlog.m3273d(this.TAG, "stopWelcome  ");
        if (this.mStopStatus) {
            Pdlog.m3273d(this.TAG, "stopWelcome is stopping please check");
        } else {
            this.mStopStatus = true;
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new WelcomeService$stopWelcome$1(this, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closePersonDetect() {
        MirCoreInterface mirCoreInterface;
        MirCoreInterface mirCoreInterface2;
        AIDLConnection<MirCoreInterface> aIDLConnection = this.coreService;
        if (aIDLConnection != null && (mirCoreInterface2 = aIDLConnection.getInterface()) != null) {
            mirCoreInterface2.enablePersonDetect(false);
        }
        AIDLConnection<MirCoreInterface> aIDLConnection2 = this.coreService;
        if (aIDLConnection2 == null || (mirCoreInterface = aIDLConnection2.getInterface()) == null) {
            return;
        }
        mirCoreInterface.removePersonListener("welcomePersonDetect");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetFlag() {
        this.mStopStatus = false;
        this.mStartStatus = false;
    }
}
