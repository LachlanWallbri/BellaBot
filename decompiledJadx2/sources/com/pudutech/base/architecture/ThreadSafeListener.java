package com.pudutech.base.architecture;

import android.os.DeadObjectException;
import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* compiled from: ThreadSafeListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001b\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00028\u0000¢\u0006\u0002\u0010\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J>\u0010\u0012\u001a\u00020\f26\u0010\u0013\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0015\u0012\b\b\r\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0015\u0012\b\b\r\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\f0\u0014J\u000e\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005J\u0006\u0010\u0018\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\tj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u0000`\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/base/architecture/ThreadSafeListener;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "TAG", "", "deadObjectList", "", "sensorListeners", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", TmpConstant.GROUP_OP_ADD, "", "name", "listener", "(Ljava/lang/String;Ljava/lang/Object;)V", "counts", "", "notify", "provider", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "it", "remove", "showNames", "pudubase_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ThreadSafeListener<T> {
    private final String TAG = "ThreadSafeListener";
    private HashMap<String, T> sensorListeners = new HashMap<>();
    private final List<String> deadObjectList = new ArrayList();

    public final void add(String name, T listener) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        synchronized (this.sensorListeners) {
            this.sensorListeners.put(name, listener);
            Pdlog.m3275i(this.TAG, "add " + name + " listeners.size:" + this.sensorListeners.size());
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void remove(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        synchronized (this.sensorListeners) {
            Pdlog.m3275i(this.TAG, "remove " + name);
            if (this.sensorListeners.containsKey(name)) {
                this.sensorListeners.remove(name);
            } else {
                Pdlog.m3274e(this.TAG, "remove error, cannot find listener");
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final void notify(Function2<? super T, ? super String, Unit> provider) {
        Intrinsics.checkParameterIsNotNull(provider, "provider");
        synchronized (this.sensorListeners) {
            for (Map.Entry<String, T> entry : this.sensorListeners.entrySet()) {
                String key = entry.getKey();
                try {
                    provider.invoke(entry.getValue(), key);
                } catch (DeadObjectException unused) {
                    Pdlog.m3277w(this.TAG, "DeadObjectException, name:" + key + ", remove from list");
                    synchronized (this.deadObjectList) {
                        this.deadObjectList.add(key);
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        synchronized (this.deadObjectList) {
            Iterator<T> it = this.deadObjectList.iterator();
            while (it.hasNext()) {
                remove((String) it.next());
            }
            this.deadObjectList.clear();
            Unit unit2 = Unit.INSTANCE;
        }
    }

    public final int counts() {
        int size;
        synchronized (this.sensorListeners) {
            size = this.sensorListeners.size();
        }
        return size;
    }

    public final void showNames() {
        synchronized (this.sensorListeners) {
            Pdlog.m3273d(this.TAG, "sensor listners name " + Arrays.asList(this.sensorListeners.keySet()));
            Unit unit = Unit.INSTANCE;
        }
    }
}
