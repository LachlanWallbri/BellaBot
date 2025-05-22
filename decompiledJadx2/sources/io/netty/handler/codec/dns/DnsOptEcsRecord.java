package io.netty.handler.codec.dns;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public interface DnsOptEcsRecord extends DnsOptPseudoRecord {
    byte[] address();

    int scopePrefixLength();

    int sourcePrefixLength();
}
