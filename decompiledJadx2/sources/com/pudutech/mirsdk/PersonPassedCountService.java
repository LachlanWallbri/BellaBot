package com.pudutech.mirsdk;

import com.aliyun.linksdk.alcs.AlcsConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.aidl.IPersonPassCountService;
import com.pudutech.mirsdk.aidl.OnPersonPassedListener;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.PersonCounterListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PersonPassedCountService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u0016\u0010\u0013\u001a\u00020\u00102\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006J\u0012\u0010\u0015\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/mirsdk/PersonPassedCountService;", "Lcom/pudutech/mirsdk/aidl/IPersonPassCountService$Stub;", "()V", "TAG", "", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "mOnPersonPassedListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/OnPersonPassedListener;", "personPassedCountListener", "com/pudutech/mirsdk/PersonPassedCountService$personPassedCountListener$1", "Lcom/pudutech/mirsdk/PersonPassedCountService$personPassedCountListener$1;", "personPassedCountListenerName", "addOnPersonPassedListener", "", "name", "listener", "init", AlcsConstant.METHOD_DOMAIN_CORE, "removeOnPersonPassedListener", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PersonPassedCountService extends IPersonPassCountService.Stub {
    private AIDLConnection<MirCoreInterface> coreService;
    private final String TAG = "PersonPassedCountService";
    private final String personPassedCountListenerName = this.TAG;
    private ThreadSafeListener<OnPersonPassedListener> mOnPersonPassedListeners = new ThreadSafeListener<>();
    private final PersonPassedCountService$personPassedCountListener$1 personPassedCountListener = new PersonCounterListener.Stub() { // from class: com.pudutech.mirsdk.PersonPassedCountService$personPassedCountListener$1
        @Override // com.pudutech.mirsdk.mircore.PersonCounterListener
        public void onCountPerson(final int count) {
            String str;
            ThreadSafeListener threadSafeListener;
            str = PersonPassedCountService.this.TAG;
            Pdlog.m3273d(str, "onCountPerson " + count);
            threadSafeListener = PersonPassedCountService.this.mOnPersonPassedListeners;
            threadSafeListener.notify(new Function2<OnPersonPassedListener, String, Unit>() { // from class: com.pudutech.mirsdk.PersonPassedCountService$personPassedCountListener$1$onCountPerson$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(OnPersonPassedListener onPersonPassedListener, String str2) {
                    invoke2(onPersonPassedListener, str2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(OnPersonPassedListener it, String str2) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                    it.onPersonPassed(count);
                }
            });
        }
    };

    public final void init(AIDLConnection<MirCoreInterface> core) {
        this.coreService = core;
    }

    @Override // com.pudutech.mirsdk.aidl.IPersonPassCountService
    public void removeOnPersonPassedListener(String name) {
        MirCoreInterface mirCoreInterface;
        Pdlog.m3273d(this.TAG, "removeOnPersonPassedListener name=" + name);
        AIDLConnection<MirCoreInterface> aIDLConnection = this.coreService;
        if (aIDLConnection != null && (mirCoreInterface = aIDLConnection.getInterface()) != null) {
            mirCoreInterface.removePersonCounterListener(this.personPassedCountListenerName);
        }
        ThreadSafeListener<OnPersonPassedListener> threadSafeListener = this.mOnPersonPassedListeners;
        if (name == null) {
            name = "";
        }
        threadSafeListener.remove(name);
    }

    @Override // com.pudutech.mirsdk.aidl.IPersonPassCountService
    public void addOnPersonPassedListener(String name, OnPersonPassedListener listener) {
        MirCoreInterface mirCoreInterface;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(this.TAG, "addOnPersonPassedListener name=" + name);
        AIDLConnection<MirCoreInterface> aIDLConnection = this.coreService;
        if (aIDLConnection != null && (mirCoreInterface = aIDLConnection.getInterface()) != null) {
            mirCoreInterface.addPersonCounterListener(this.personPassedCountListenerName, this.personPassedCountListener);
        }
        this.mOnPersonPassedListeners.add(name, listener);
    }
}
