package com.pudutech.opengl_draw.concurrent;

import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class ListenerGroup<T> {
    private static final int DEFAULT_QUEUE_CAPACITY = 128;
    private final Collection<EventDispatcher<T>> eventDispatchers = new CopyOnWriteArrayList();
    private final ExecutorService executorService;

    public ListenerGroup(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public EventDispatcher<T> add(T t, int i) {
        EventDispatcher<T> eventDispatcher = new EventDispatcher<>(t, i);
        this.eventDispatchers.add(eventDispatcher);
        this.executorService.execute(eventDispatcher);
        return eventDispatcher;
    }

    public EventDispatcher<T> add(T t) {
        return add(t, 128);
    }

    public Collection<EventDispatcher<T>> addAll(Collection<T> collection, int i) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(add(it.next(), i));
        }
        return arrayList;
    }

    public Collection<EventDispatcher<T>> addAll(Collection<T> collection) {
        return addAll(collection, 128);
    }

    public boolean remove(T t) {
        Preconditions.checkNotNull(t);
        for (EventDispatcher<T> eventDispatcher : this.eventDispatchers) {
            if (t.equals(eventDispatcher.getListener())) {
                eventDispatcher.cancel();
                this.eventDispatchers.remove(eventDispatcher);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.eventDispatchers.size();
    }

    public void signal(SignalRunnable<T> signalRunnable) {
        Iterator<EventDispatcher<T>> it = this.eventDispatchers.iterator();
        while (it.hasNext()) {
            it.next().signal(signalRunnable);
        }
    }

    public boolean signal(final SignalRunnable<T> signalRunnable, long j, TimeUnit timeUnit) throws InterruptedException {
        ArrayList arrayList = new ArrayList(this.eventDispatchers);
        final CountDownLatch countDownLatch = new CountDownLatch(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((EventDispatcher) it.next()).signal(new SignalRunnable<T>() { // from class: com.pudutech.opengl_draw.concurrent.ListenerGroup.1
                @Override // com.pudutech.opengl_draw.concurrent.SignalRunnable
                public void run(T t) {
                    signalRunnable.run(t);
                    countDownLatch.countDown();
                }
            });
        }
        return countDownLatch.await(j, timeUnit);
    }

    public void shutdown() {
        Iterator<EventDispatcher<T>> it = this.eventDispatchers.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
        this.eventDispatchers.clear();
    }
}
