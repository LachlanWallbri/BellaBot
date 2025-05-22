package org.nanohttpd.protocols.http.threading;

import org.nanohttpd.protocols.http.ClientHandler;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface IAsyncRunner {
    void closeAll();

    void closed(ClientHandler clientHandler);

    void exec(ClientHandler clientHandler);
}
