package com.squareup.okhttp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Dns {
    public static final Dns SYSTEM = new Dns() { // from class: com.squareup.okhttp.Dns.1
        @Override // com.squareup.okhttp.Dns
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            if (str == null) {
                throw new UnknownHostException("hostname == null");
            }
            return Arrays.asList(InetAddress.getAllByName(str));
        }
    };

    List<InetAddress> lookup(String str) throws UnknownHostException;
}
