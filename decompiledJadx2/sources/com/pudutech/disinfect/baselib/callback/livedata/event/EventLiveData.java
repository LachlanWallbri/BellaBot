package com.pudutech.disinfect.baselib.callback.livedata.event;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: EventLiveData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0014\u001a\u00020\u0015H\u0002J \u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u000e\u0010\u0019\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\bH\u0016J\u0018\u0010\u001a\u001a\u00020\u00152\u000e\u0010\u0019\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\bH\u0017J\u0018\u0010\u001b\u001a\u00020\u00152\u000e\u0010\u0019\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\bH\u0016J\u0015\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000RJ\u0010\u0006\u001a>\u0012\f\u0012\n\u0012\u0006\b\u0000\u0012\u00028\u00000\b\u0012\f\u0012\n\u0012\u0006\b\u0000\u0012\u00028\u00000\b0\u0007j\u001e\u0012\f\u0012\n\u0012\u0006\b\u0000\u0012\u00028\u00000\b\u0012\f\u0012\n\u0012\u0006\b\u0000\u0012\u00028\u00000\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/callback/livedata/event/EventLiveData;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/MutableLiveData;", "()V", "DELAY_TO_CLEAR_EVENT", "", "foreverObservers", "Ljava/util/HashMap;", "Landroidx/lifecycle/Observer;", "Lkotlin/collections/HashMap;", "hasHandled", "", "isAllowNullValue", "isAllowToClear", "isCleaning", "isDelaying", "mTask", "Ljava/util/TimerTask;", "mTimer", "Ljava/util/Timer;", "clear", "", "observe", "owner", "Landroidx/lifecycle/LifecycleOwner;", "observer", "observeForever", "removeObserver", "setValue", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "Builder", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class EventLiveData<T> extends MutableLiveData<T> {
    private boolean isAllowNullValue;
    private boolean isCleaning;
    private boolean isDelaying;
    private TimerTask mTask;
    private final HashMap<Observer<? super T>, Observer<? super T>> foreverObservers = new HashMap<>();
    private boolean hasHandled = true;
    private int DELAY_TO_CLEAR_EVENT = 1000;
    private final Timer mTimer = new Timer();
    private boolean isAllowToClear = true;

    @Override // androidx.lifecycle.LiveData
    public void observe(LifecycleOwner owner, final Observer<? super T> observer) {
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        super.observe(owner, new Observer<T>() { // from class: com.pudutech.disinfect.baselib.callback.livedata.event.EventLiveData$observe$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                boolean z;
                boolean z2;
                boolean z3;
                z = EventLiveData.this.isCleaning;
                if (z) {
                    EventLiveData.this.hasHandled = true;
                    EventLiveData.this.isDelaying = false;
                    EventLiveData.this.isCleaning = false;
                    return;
                }
                z2 = EventLiveData.this.hasHandled;
                if (!z2) {
                    EventLiveData.this.hasHandled = true;
                    EventLiveData.this.isDelaying = true;
                    observer.onChanged(t);
                } else {
                    z3 = EventLiveData.this.isDelaying;
                    if (z3) {
                        observer.onChanged(t);
                    }
                }
            }
        });
    }

    @Override // androidx.lifecycle.LiveData
    public void observeForever(final Observer<? super T> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Observer<T> observer2 = new Observer<T>() { // from class: com.pudutech.disinfect.baselib.callback.livedata.event.EventLiveData$observeForever$ob$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                boolean z;
                boolean z2;
                boolean z3;
                z = EventLiveData.this.isCleaning;
                if (z) {
                    EventLiveData.this.hasHandled = true;
                    EventLiveData.this.isDelaying = false;
                    EventLiveData.this.isCleaning = false;
                    return;
                }
                z2 = EventLiveData.this.hasHandled;
                if (!z2) {
                    EventLiveData.this.hasHandled = true;
                    EventLiveData.this.isDelaying = true;
                    observer.onChanged(t);
                } else {
                    z3 = EventLiveData.this.isDelaying;
                    if (z3) {
                        observer.onChanged(t);
                    }
                }
            }
        };
        this.foreverObservers.put(observer, observer2);
        super.observeForever(observer2);
    }

    @Override // androidx.lifecycle.LiveData
    public void removeObserver(Observer<? super T> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        Observer<? super T> remove = this.foreverObservers.remove(observer);
        if (remove != null) {
            super.removeObserver(remove);
        }
    }

    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    public void setValue(T value) {
        if (this.isAllowNullValue || value != null || this.isCleaning) {
            this.hasHandled = false;
            this.isDelaying = false;
            super.setValue(value);
            TimerTask timerTask = this.mTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.mTimer.purge();
            }
            this.mTask = new TimerTask() { // from class: com.pudutech.disinfect.baselib.callback.livedata.event.EventLiveData$setValue$2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    EventLiveData.this.clear();
                }
            };
            this.mTimer.schedule(this.mTask, this.DELAY_TO_CLEAR_EVENT);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clear() {
        if (this.isAllowToClear) {
            this.isCleaning = true;
            super.postValue(null);
        } else {
            this.hasHandled = true;
            this.isDelaying = false;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: EventLiveData.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\f\u001a\u00020\u0007J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u000e\u001a\u00020\u0007J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/callback/livedata/event/EventLiveData$Builder;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "eventSurvivalTime", "", "isAllowNullValue", "", "isAllowToClear", "create", "Lcom/pudutech/disinfect/baselib/callback/livedata/event/EventLiveData;", "setAllowNullValue", "allowNullValue", "setAllowToClear", "allowToClear", "setEventSurvivalTime", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Builder<T> {
        private boolean isAllowNullValue;
        private int eventSurvivalTime = 1000;
        private boolean isAllowToClear = true;

        public final Builder<T> setEventSurvivalTime(int eventSurvivalTime) {
            this.eventSurvivalTime = eventSurvivalTime;
            return this;
        }

        public final Builder<T> setAllowNullValue(boolean allowNullValue) {
            this.isAllowNullValue = allowNullValue;
            return this;
        }

        public final Builder<T> setAllowToClear(boolean allowToClear) {
            this.isAllowToClear = allowToClear;
            return this;
        }

        public final EventLiveData<T> create() {
            EventLiveData<T> eventLiveData = new EventLiveData<>();
            ((EventLiveData) eventLiveData).DELAY_TO_CLEAR_EVENT = this.eventSurvivalTime;
            ((EventLiveData) eventLiveData).isAllowNullValue = this.isAllowNullValue;
            ((EventLiveData) eventLiveData).isAllowToClear = this.isAllowToClear;
            return eventLiveData;
        }
    }
}
