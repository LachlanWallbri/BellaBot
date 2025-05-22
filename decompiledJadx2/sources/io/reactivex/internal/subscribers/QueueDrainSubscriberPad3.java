package io.reactivex.internal.subscribers;

import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: QueueDrainSubscriber.java */
/* loaded from: classes2.dex */
class QueueDrainSubscriberPad3 extends QueueDrainSubscriberPad2 {
    final AtomicLong requested = new AtomicLong();
}
