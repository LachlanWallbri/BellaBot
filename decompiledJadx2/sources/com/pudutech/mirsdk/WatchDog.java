package com.pudutech.mirsdk;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: WatchDog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J\u001e\u0010\u0012\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00140\u0013J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004J\u0006\u0010\u0017\u001a\u00020\u0010J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u0004J\u001e\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/mirsdk/WatchDog;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "dogs", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/pudutech/mirsdk/WatchDog$Dog;", "errorList", "", "getErrorList", "()Ljava/util/List;", "setErrorList", "(Ljava/util/List;)V", "addError", "", "name", "check", "Lkotlin/Pair;", "", "removeError", "removeWatch", "resetAll", "tick", "", "watch", "warningTimeout", "", "errorTimeout", "Dog", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WatchDog {
    private final String TAG = getClass().getSimpleName();
    private final ConcurrentHashMap<String, Dog> dogs = new ConcurrentHashMap<>();
    private List<String> errorList = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: WatchDog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f¨\u0006 "}, m3961d2 = {"Lcom/pudutech/mirsdk/WatchDog$Dog;", "", "name", "", "warningTimeout", "", "errorTimeout", "lastTick", "(Ljava/lang/String;JJJ)V", "getErrorTimeout", "()J", "setErrorTimeout", "(J)V", "getLastTick", "setLastTick", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getWarningTimeout", "setWarningTimeout", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class Dog {
        private long errorTimeout;
        private long lastTick;
        private String name;
        private long warningTimeout;

        public static /* synthetic */ Dog copy$default(Dog dog, String str, long j, long j2, long j3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = dog.name;
            }
            if ((i & 2) != 0) {
                j = dog.warningTimeout;
            }
            long j4 = j;
            if ((i & 4) != 0) {
                j2 = dog.errorTimeout;
            }
            long j5 = j2;
            if ((i & 8) != 0) {
                j3 = dog.lastTick;
            }
            return dog.copy(str, j4, j5, j3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final long getWarningTimeout() {
            return this.warningTimeout;
        }

        /* renamed from: component3, reason: from getter */
        public final long getErrorTimeout() {
            return this.errorTimeout;
        }

        /* renamed from: component4, reason: from getter */
        public final long getLastTick() {
            return this.lastTick;
        }

        public final Dog copy(String name, long warningTimeout, long errorTimeout, long lastTick) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new Dog(name, warningTimeout, errorTimeout, lastTick);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Dog)) {
                return false;
            }
            Dog dog = (Dog) other;
            return Intrinsics.areEqual(this.name, dog.name) && this.warningTimeout == dog.warningTimeout && this.errorTimeout == dog.errorTimeout && this.lastTick == dog.lastTick;
        }

        public int hashCode() {
            String str = this.name;
            int hashCode = str != null ? str.hashCode() : 0;
            long j = this.warningTimeout;
            int i = ((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31;
            long j2 = this.errorTimeout;
            int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
            long j3 = this.lastTick;
            return i2 + ((int) (j3 ^ (j3 >>> 32)));
        }

        public String toString() {
            return "Dog(name=" + this.name + ", warningTimeout=" + this.warningTimeout + ", errorTimeout=" + this.errorTimeout + ", lastTick=" + this.lastTick + ")";
        }

        public Dog(String name, long j, long j2, long j3) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.name = name;
            this.warningTimeout = j;
            this.errorTimeout = j2;
            this.lastTick = j3;
        }

        public final long getErrorTimeout() {
            return this.errorTimeout;
        }

        public final long getLastTick() {
            return this.lastTick;
        }

        public final String getName() {
            return this.name;
        }

        public final long getWarningTimeout() {
            return this.warningTimeout;
        }

        public final void setErrorTimeout(long j) {
            this.errorTimeout = j;
        }

        public final void setLastTick(long j) {
            this.lastTick = j;
        }

        public final void setName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.name = str;
        }

        public final void setWarningTimeout(long j) {
            this.warningTimeout = j;
        }
    }

    public final List<String> getErrorList() {
        return this.errorList;
    }

    public final void setErrorList(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.errorList = list;
    }

    public final void watch(String name, long warningTimeout, long errorTimeout) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        synchronized (this.dogs) {
            this.dogs.put(name, new Dog(name, warningTimeout, errorTimeout, SystemClock.elapsedRealtime()));
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void removeWatch(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        synchronized (this.dogs) {
            this.dogs.remove(name);
        }
    }

    private final void addError(String name) {
        synchronized (this.errorList) {
            if (!this.errorList.contains(name)) {
                this.errorList.add(name);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void removeError(String name) {
        synchronized (this.errorList) {
            if (this.errorList.contains(name)) {
                this.errorList.remove(name);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean tick(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        synchronized (this.dogs) {
            Dog dog = this.dogs.get(name);
            if (dog != null) {
                dog.setLastTick(SystemClock.elapsedRealtime());
            }
            Unit unit = Unit.INSTANCE;
        }
        return true;
    }

    public final Pair<List<String>, List<String>> check() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<String, Dog> entry : this.dogs.entrySet()) {
            String key = entry.getKey();
            Dog value = entry.getValue();
            long elapsedRealtime = SystemClock.elapsedRealtime() - value.getLastTick();
            if (elapsedRealtime > value.getErrorTimeout()) {
                Pdlog.m3274e(this.TAG, "error " + key + " data, time pass:" + elapsedRealtime + " over error timeout limit:" + value.getErrorTimeout());
                arrayList.add(key);
            } else if (elapsedRealtime > value.getWarningTimeout()) {
                arrayList2.add(key);
                Pdlog.m3277w(this.TAG, "warning " + key + " data, time pass :" + elapsedRealtime + " over warning timeout limit:" + value.getWarningTimeout());
            }
        }
        return new Pair<>(arrayList2, arrayList);
    }

    public final void resetAll() {
        synchronized (this.dogs) {
            Iterator<Map.Entry<String, Dog>> it = this.dogs.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().setLastTick(SystemClock.elapsedRealtime());
            }
            Unit unit = Unit.INSTANCE;
        }
        synchronized (this.errorList) {
            this.errorList.clear();
            Unit unit2 = Unit.INSTANCE;
        }
    }
}
