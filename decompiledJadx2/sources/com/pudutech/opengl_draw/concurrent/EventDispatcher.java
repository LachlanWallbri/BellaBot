package com.pudutech.opengl_draw.concurrent;

/* loaded from: classes5.dex */
public class EventDispatcher<T> extends CancellableLoop {
    private final CircularBlockingDeque<SignalRunnable<T>> events;
    private final T listener;

    public EventDispatcher(T t, int i) {
        this.listener = t;
        this.events = new CircularBlockingDeque<>(i);
    }

    public void signal(SignalRunnable<T> signalRunnable) {
        this.events.addLast(signalRunnable);
    }

    @Override // com.pudutech.opengl_draw.concurrent.CancellableLoop
    public void loop() throws InterruptedException {
        this.events.takeFirst().run(this.listener);
    }

    public T getListener() {
        return this.listener;
    }
}
