package org.nanohttpd.protocols.http.threading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.nanohttpd.protocols.http.ClientHandler;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class DefaultAsyncRunner implements IAsyncRunner {
    protected long requestCount;
    private final List<ClientHandler> running = Collections.synchronizedList(new ArrayList());

    public List<ClientHandler> getRunning() {
        return this.running;
    }

    @Override // org.nanohttpd.protocols.http.threading.IAsyncRunner
    public void closeAll() {
        Iterator it = new ArrayList(this.running).iterator();
        while (it.hasNext()) {
            ((ClientHandler) it.next()).close();
        }
    }

    @Override // org.nanohttpd.protocols.http.threading.IAsyncRunner
    public void closed(ClientHandler clientHandler) {
        this.running.remove(clientHandler);
    }

    @Override // org.nanohttpd.protocols.http.threading.IAsyncRunner
    public void exec(ClientHandler clientHandler) {
        this.requestCount++;
        this.running.add(clientHandler);
        createThread(clientHandler).start();
    }

    protected Thread createThread(ClientHandler clientHandler) {
        Thread thread = new Thread(clientHandler);
        thread.setDaemon(true);
        thread.setName("NanoHttpd Request Processor (#" + this.requestCount + ")");
        return thread;
    }
}
