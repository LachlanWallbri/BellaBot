package com.pudutech.mqtt.component.client.netty;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class MessageIdGenerate {
    private static volatile MessageIdGenerate instance;
    private AtomicInteger messageId = new AtomicInteger(0);

    private MessageIdGenerate() {
    }

    public static MessageIdGenerate getInstance() {
        if (instance == null) {
            synchronized (MessageIdGenerate.class) {
                if (instance == null) {
                    instance = new MessageIdGenerate();
                }
            }
        }
        return instance;
    }

    public int getMessageId() {
        if (this.messageId.get() > 65534) {
            this.messageId.set(0);
        }
        return this.messageId.incrementAndGet();
    }

    public static void main(String[] strArr) {
        for (int i = 0; i < 70000; i++) {
            new Thread(new Runnable() { // from class: com.pudutech.mqtt.component.client.netty.MessageIdGenerate.1
                @Override // java.lang.Runnable
                public void run() {
                    System.out.println(MessageIdGenerate.getInstance().getMessageId());
                }
            }).start();
        }
    }
}
