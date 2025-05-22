package com.pudutech.disinfect.baselib.callback;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: UnPeekLiveData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0002J \u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u000f2\u000e\u0010\u0013\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0014H\u0016J\u0015\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/callback/UnPeekLiveData;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/MutableLiveData;", "()V", "DELAY_TO_CLEAR_EVENT", "", "hasHandled", "", "isCleaning", "isDelaying", "mTask", "Ljava/util/TimerTask;", "mTimer", "Ljava/util/Timer;", "clear", "", "observe", "owner", "Landroidx/lifecycle/LifecycleOwner;", "observer", "Landroidx/lifecycle/Observer;", "observeForever", "setValue", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class UnPeekLiveData<T> extends MutableLiveData<T> {
    private boolean isCleaning;
    private boolean isDelaying;
    private TimerTask mTask;
    private boolean hasHandled = true;
    private final Timer mTimer = new Timer();
    private int DELAY_TO_CLEAR_EVENT = 100;

    @Override // androidx.lifecycle.LiveData
    public void observe(LifecycleOwner owner, final Observer<? super T> observer) {
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        super.observe(owner, new Observer<T>() { // from class: com.pudutech.disinfect.baselib.callback.UnPeekLiveData$observe$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                boolean z;
                boolean z2;
                boolean z3;
                z = UnPeekLiveData.this.isCleaning;
                if (z) {
                    UnPeekLiveData.this.hasHandled = true;
                    UnPeekLiveData.this.isDelaying = false;
                    UnPeekLiveData.this.isCleaning = false;
                    return;
                }
                z2 = UnPeekLiveData.this.hasHandled;
                if (!z2) {
                    UnPeekLiveData.this.hasHandled = true;
                    UnPeekLiveData.this.isDelaying = true;
                    observer.onChanged(t);
                } else {
                    z3 = UnPeekLiveData.this.isDelaying;
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
        super.observeForever(new Observer<T>() { // from class: com.pudutech.disinfect.baselib.callback.UnPeekLiveData$observeForever$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                boolean z;
                boolean z2;
                boolean z3;
                z = UnPeekLiveData.this.isCleaning;
                if (z) {
                    UnPeekLiveData.this.hasHandled = true;
                    UnPeekLiveData.this.isDelaying = false;
                    UnPeekLiveData.this.isCleaning = false;
                    return;
                }
                z2 = UnPeekLiveData.this.hasHandled;
                if (!z2) {
                    UnPeekLiveData.this.hasHandled = true;
                    UnPeekLiveData.this.isDelaying = true;
                    observer.onChanged(t);
                } else {
                    z3 = UnPeekLiveData.this.isDelaying;
                    if (z3) {
                        observer.onChanged(t);
                    }
                }
            }
        });
    }

    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    public void setValue(T value) {
        this.hasHandled = false;
        this.isDelaying = false;
        super.setValue(value);
        TimerTask timerTask = this.mTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.mTimer.purge();
        }
        this.mTask = new TimerTask() { // from class: com.pudutech.disinfect.baselib.callback.UnPeekLiveData$setValue$2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                UnPeekLiveData.this.clear();
            }
        };
        this.mTimer.schedule(this.mTask, this.DELAY_TO_CLEAR_EVENT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clear() {
        this.hasHandled = true;
        this.isDelaying = false;
    }
}
