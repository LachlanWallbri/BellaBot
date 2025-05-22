package com.pudutech.bumblebee.business.peripherals_task.pallet_task;

import android.content.Context;
import android.content.SharedPreferences;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.robotsdk.RobotSetting;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.PalletStateListener;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: PalletTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000f\u0018\u0000 62\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u00016B\u0005¢\u0006\u0002\u0010\u0004J\u000e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0013J\b\u0010&\u001a\u00020\u0010H\u0002J\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010(J\u0010\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020\u0013H\u0002J\u0006\u0010+\u001a\u00020\u0010J\u0010\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020\u001dH\u0002J\u0018\u0010.\u001a\u00020$2\u000e\u0010/\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cH\u0016J\u0006\u00100\u001a\u00020$J\u0006\u00101\u001a\u00020$J\u0006\u00102\u001a\u00020$J\u000e\u00103\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0013J\u000e\u00104\u001a\u00020$2\u0006\u00105\u001a\u00020\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012j\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0010@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u001e\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012j\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001d0 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010!\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\"¨\u00067"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletTask;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletTaskListener;", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/PalletStateListener;", "()V", "FILTER_TIME_MS", "", "TAG", "", "controller", "Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "getController$module_bumblebee_business_robotRelease", "()Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "setController$module_bumblebee_business_robotRelease", "(Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;)V", "delayEmptyEvent", "", "delayEmptyEvents", "Ljava/util/HashMap;", "", "Ljava/lang/Runnable;", "Lkotlin/collections/HashMap;", ES6Iterator.VALUE_PROPERTY, "isArrival", "()Z", "setArrival", "(Z)V", "last", "", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet;", "removeEventFilter", "shouldSets", "", "shouldTakePalletId", "Ljava/lang/Integer;", "addPalletId", "", "palletId", "checkValid", "getLastPalletState", "", "handleRemove", "id", "isFinishTakePallet", "onPalletsEvent", "pallet", "onPalletsResponse", "p0", "pauseEmptyEvent", "requestPalletState", "resumeEmptyEvent", "setTargetPalletId", "turnSensorsOn", "onOrOff", "Companion", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PalletTask extends BaseMultiListenerImpl<PalletTaskListener> implements PalletStateListener {
    private static boolean isMute;
    private static SharedPreferences sharedPreferences;
    private RobotInterface controller;
    private boolean delayEmptyEvent;
    private final HashMap<Integer, Runnable> delayEmptyEvents;
    private boolean isArrival;
    private List<Pallet> last;
    private Integer shouldTakePalletId;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FileName = FileName;
    private static final String FileName = FileName;
    private static final String KEY = KEY;
    private static final String KEY = KEY;
    private final String TAG = "PalletTask";
    private final long FILTER_TIME_MS = 5000;
    private Set<Pallet> shouldSets = new LinkedHashSet();
    private final HashMap<Integer, Runnable> removeEventFilter = new HashMap<>();

    public PalletTask() {
        for (final int i = 1; i <= 4; i++) {
            this.removeEventFilter.put(Integer.valueOf(i), new Runnable() { // from class: com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTask.1
                @Override // java.lang.Runnable
                public final void run() {
                    PalletTask.this.handleRemove(i);
                }
            });
        }
        this.delayEmptyEvents = new HashMap<>();
    }

    /* renamed from: isArrival, reason: from getter */
    public final boolean getIsArrival() {
        return this.isArrival;
    }

    public final void setArrival(boolean z) {
        this.isArrival = z;
        Pdlog.m3275i(this.TAG, "set isArrival=" + z);
        if (z) {
            Set<Pallet> set = this.shouldSets;
            ArrayList arrayList = new ArrayList();
            for (Object obj : set) {
                if (((Pallet) obj).getIsPlaced()) {
                    arrayList.add(obj);
                }
            }
            this.shouldSets = CollectionsKt.toMutableSet(arrayList);
            return;
        }
        Iterator<Map.Entry<Integer, Runnable>> it = this.removeEventFilter.entrySet().iterator();
        while (it.hasNext()) {
            TimerThread.INSTANCE.remove(it.next().getValue());
        }
    }

    public final void requestPalletState() {
        RobotInterface robotInterface = this.controller;
        if (robotInterface != null) {
            robotInterface.requestPallets();
        }
    }

    public final List<Pallet> getLastPalletState() {
        return this.last;
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.PalletStateListener
    public void onPalletsResponse(List<Pallet> p0) {
        int i = 0;
        Pdlog.m3275i(this.TAG, "onPalletsResponse " + p0);
        if (this.last == null) {
            this.last = p0;
            return;
        }
        if (p0 != null) {
            for (Object obj : p0) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Pallet pallet = (Pallet) obj;
                List<Pallet> list = this.last;
                if (list == null) {
                    Intrinsics.throwNpe();
                }
                Pallet pallet2 = list.get(i);
                if (pallet2.getIsPowerOn() && pallet.getIsPowerOn() && pallet2.getIsInstalled() && pallet.getIsInstalled()) {
                    List<Pallet> list2 = this.last;
                    if (list2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (list2.get(i).getIsPlaced() != pallet.getIsPlaced()) {
                        onPalletsEvent(pallet);
                    }
                }
                i = i2;
            }
        }
        this.last = p0;
    }

    private final void onPalletsEvent(final Pallet pallet) {
        Runnable it;
        Pdlog.m3275i(this.TAG, "on PalletsEvent " + pallet);
        if (pallet.getIsPlaced()) {
            getListeners().forEach(new Function1<PalletTaskListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTask$onPalletsEvent$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PalletTaskListener palletTaskListener) {
                    invoke2(palletTaskListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PalletTaskListener it2) {
                    Intrinsics.checkParameterIsNotNull(it2, "it");
                    it2.onEvent(Pallet.this.getPalletId(), PalletTaskEvent.PLACE);
                }
            });
        } else if (!pallet.getIsPlaced()) {
            getListeners().forEach(new Function1<PalletTaskListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTask$onPalletsEvent$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PalletTaskListener palletTaskListener) {
                    invoke2(palletTaskListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PalletTaskListener it2) {
                    Intrinsics.checkParameterIsNotNull(it2, "it");
                    it2.onEvent(Pallet.this.getPalletId(), PalletTaskEvent.TAKE);
                }
            });
        }
        if (checkValid()) {
            if (!pallet.getIsPlaced()) {
                Runnable it2 = this.removeEventFilter.get(Integer.valueOf(pallet.getPalletId()));
                if (it2 != null) {
                    TimerThread timerThread = TimerThread.INSTANCE;
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    timerThread.remove(it2);
                    if (!this.delayEmptyEvent) {
                        TimerThread.INSTANCE.post(it2, RobotSetting.INSTANCE.getPalletCheckEmptyDelay());
                    }
                    this.delayEmptyEvents.put(Integer.valueOf(pallet.getPalletId()), it2);
                    return;
                }
                return;
            }
            if (!pallet.getIsPlaced() || (it = this.removeEventFilter.get(Integer.valueOf(pallet.getPalletId()))) == null) {
                return;
            }
            TimerThread timerThread2 = TimerThread.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            timerThread2.remove(it);
            this.delayEmptyEvents.remove(Integer.valueOf(pallet.getPalletId()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleRemove(final int id) {
        Pdlog.m3275i(this.TAG, "handleRemove " + id + " listeners.size=" + getListeners().size());
        if (checkValid()) {
            getListeners().forEach(new Function1<PalletTaskListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTask$handleRemove$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PalletTaskListener palletTaskListener) {
                    invoke2(palletTaskListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PalletTaskListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onEvent(id, PalletTaskEvent.EMPTY);
                }
            });
            this.delayEmptyEvents.remove(Integer.valueOf(id));
        }
    }

    private final boolean checkValid() {
        if (!isMute) {
            return true;
        }
        Pdlog.m3275i(this.TAG, "isMust=" + isMute + " . return isArrival=" + this.isArrival);
        return false;
    }

    public final void pauseEmptyEvent() {
        this.delayEmptyEvent = true;
        Iterator<Map.Entry<Integer, Runnable>> it = this.delayEmptyEvents.entrySet().iterator();
        while (it.hasNext()) {
            TimerThread.INSTANCE.remove(it.next().getValue());
        }
    }

    public final void resumeEmptyEvent() {
        for (Map.Entry<Integer, Runnable> entry : this.delayEmptyEvents.entrySet()) {
            TimerThread.INSTANCE.remove(entry.getValue());
            TimerThread.INSTANCE.post(entry.getValue(), this.FILTER_TIME_MS);
        }
        this.delayEmptyEvent = false;
    }

    /* compiled from: PalletTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletTask$Companion;", "", "()V", "FileName", "", "KEY", ES6Iterator.VALUE_PROPERTY, "", "isMute", "()Z", "setMute", "(Z)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "initConfig", "", "context", "Landroid/content/Context;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isMute() {
            return PalletTask.isMute;
        }

        public final void setMute(boolean z) {
            SharedPreferences.Editor edit;
            SharedPreferences.Editor putBoolean;
            PalletTask.isMute = z;
            Pdlog.m3275i("PalletTask", "set isMute=" + z);
            SharedPreferences sharedPreferences = PalletTask.sharedPreferences;
            if (sharedPreferences == null || (edit = sharedPreferences.edit()) == null || (putBoolean = edit.putBoolean(PalletTask.KEY, z)) == null) {
                return;
            }
            putBoolean.apply();
        }

        public final void initConfig(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Pdlog.m3275i("PalletTask", "initConfig " + context);
            PalletTask.sharedPreferences = context.getSharedPreferences(PalletTask.FileName, 0);
            Companion companion = this;
            SharedPreferences sharedPreferences = PalletTask.sharedPreferences;
            if (sharedPreferences == null) {
                Intrinsics.throwNpe();
            }
            companion.setMute(sharedPreferences.getBoolean(PalletTask.KEY, false));
        }
    }

    /* renamed from: getController$module_bumblebee_business_robotRelease, reason: from getter */
    public final RobotInterface getController() {
        return this.controller;
    }

    public final void setController$module_bumblebee_business_robotRelease(RobotInterface robotInterface) {
        this.controller = robotInterface;
    }

    public final void turnSensorsOn(boolean onOrOff) {
        RobotInterface robotInterface = this.controller;
        if (robotInterface != null) {
            robotInterface.setPeripheralDevicePower(PeripheralDevice.Pallet1, onOrOff);
            robotInterface.setPeripheralDevicePower(PeripheralDevice.Pallet2, onOrOff);
            robotInterface.setPeripheralDevicePower(PeripheralDevice.Pallet3, onOrOff);
            robotInterface.setPeripheralDevicePower(PeripheralDevice.Pallet4, onOrOff);
        }
    }

    public final void setTargetPalletId(int palletId) {
        Object obj;
        this.shouldTakePalletId = Integer.valueOf(palletId);
        List<Pallet> list = this.last;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (((Pallet) obj).getPalletId() == palletId) {
                        break;
                    }
                }
            }
            Pallet pallet = (Pallet) obj;
            if (pallet != null) {
                this.shouldSets.remove(pallet);
            }
        }
    }

    public final void addPalletId(int palletId) {
        Object obj;
        List<Pallet> list = this.last;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (((Pallet) obj).getPalletId() == palletId) {
                        break;
                    }
                }
            }
            Pallet pallet = (Pallet) obj;
            if (pallet != null) {
                this.shouldSets.add(pallet);
            }
        }
    }

    public final boolean isFinishTakePallet() {
        Object obj;
        if (this.shouldSets.size() == 0) {
            return true;
        }
        Set<Pallet> set = this.shouldSets;
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : set) {
            Pallet pallet = (Pallet) obj2;
            List<Pallet> list = this.last;
            Boolean bool = null;
            if (list != null) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((Pallet) obj).getPalletId() == pallet.getPalletId()) {
                        break;
                    }
                }
                Pallet pallet2 = (Pallet) obj;
                if (pallet2 != null) {
                    bool = Boolean.valueOf(pallet2.getIsPlaced());
                }
            }
            if (Intrinsics.areEqual(bool, Boolean.valueOf(pallet.getIsPlaced()))) {
                arrayList.add(obj2);
            }
        }
        return arrayList.size() == this.shouldSets.size();
    }
}
