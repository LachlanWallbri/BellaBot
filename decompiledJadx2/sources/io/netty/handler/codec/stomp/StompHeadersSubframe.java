package io.netty.handler.codec.stomp;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface StompHeadersSubframe extends StompSubframe {
    StompCommand command();

    StompHeaders headers();
}
