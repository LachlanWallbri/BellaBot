package okhttp3.internal.connection;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.http.ExchangeCodec;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: ExchangeFinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0010J0\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u0010H\u0002J8\u0010%\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0010H\u0002J\u0006\u0010&\u001a\u00020\u0010J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010'\u001a\u00020\u0010H\u0002J\u0006\u0010(\u001a\u00020)R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, m3961d2 = {"Lokhttp3/internal/connection/ExchangeFinder;", "", "transmitter", "Lokhttp3/internal/connection/Transmitter;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "address", "Lokhttp3/Address;", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "eventListener", "Lokhttp3/EventListener;", "(Lokhttp3/internal/connection/Transmitter;Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Address;Lokhttp3/Call;Lokhttp3/EventListener;)V", "connectingConnection", "Lokhttp3/internal/connection/RealConnection;", "hasStreamFailure", "", "nextRouteToTry", "Lokhttp3/Route;", "routeSelection", "Lokhttp3/internal/connection/RouteSelector$Selection;", "routeSelector", "Lokhttp3/internal/connection/RouteSelector;", "find", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "chain", "Lokhttp3/Interceptor$Chain;", "doExtensiveHealthChecks", "findConnection", "connectTimeout", "", "readTimeout", "writeTimeout", "pingIntervalMillis", "connectionRetryEnabled", "findHealthyConnection", "hasRouteToTry", "retryCurrentRoute", "trackFailure", "", "okhttp"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class ExchangeFinder {
    private final Address address;
    private final Call call;
    private RealConnection connectingConnection;
    private final RealConnectionPool connectionPool;
    private final EventListener eventListener;
    private boolean hasStreamFailure;
    private Route nextRouteToTry;
    private RouteSelector.Selection routeSelection;
    private final RouteSelector routeSelector;
    private final Transmitter transmitter;

    public ExchangeFinder(Transmitter transmitter, RealConnectionPool connectionPool, Address address, Call call, EventListener eventListener) {
        Intrinsics.checkParameterIsNotNull(transmitter, "transmitter");
        Intrinsics.checkParameterIsNotNull(connectionPool, "connectionPool");
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(call, "call");
        Intrinsics.checkParameterIsNotNull(eventListener, "eventListener");
        this.transmitter = transmitter;
        this.connectionPool = connectionPool;
        this.address = address;
        this.call = call;
        this.eventListener = eventListener;
        this.routeSelector = new RouteSelector(this.address, this.connectionPool.getRouteDatabase(), this.call, this.eventListener);
    }

    public final ExchangeCodec find(OkHttpClient client, Interceptor.Chain chain, boolean doExtensiveHealthChecks) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        try {
            return findHealthyConnection(chain.connectTimeoutMillis(), chain.readTimeoutMillis(), chain.writeTimeoutMillis(), client.pingIntervalMillis(), client.retryOnConnectionFailure(), doExtensiveHealthChecks).newCodec$okhttp(client, chain);
        } catch (IOException e) {
            trackFailure();
            throw new RouteException(e);
        } catch (RouteException e2) {
            trackFailure();
            throw e2;
        }
    }

    private final RealConnection findHealthyConnection(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled, boolean doExtensiveHealthChecks) throws IOException {
        while (true) {
            RealConnection findConnection = findConnection(connectTimeout, readTimeout, writeTimeout, pingIntervalMillis, connectionRetryEnabled);
            synchronized (this.connectionPool) {
                if (findConnection.getSuccessCount$okhttp() == 0) {
                    return findConnection;
                }
                Unit unit = Unit.INSTANCE;
                if (findConnection.isHealthy(doExtensiveHealthChecks)) {
                    return findConnection;
                }
                findConnection.noNewExchanges();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00d9, code lost:
    
        if (r4.hasNext() == false) goto L59;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004d A[Catch: all -> 0x01f3, TryCatch #0 {, blocks: (B:4:0x0011, B:6:0x0019, B:8:0x002c, B:10:0x0034, B:11:0x0037, B:13:0x003d, B:14:0x0045, B:16:0x004d, B:19:0x005b, B:21:0x0067, B:22:0x0091, B:124:0x006f, B:126:0x0073, B:127:0x007b, B:129:0x0081, B:131:0x0089, B:132:0x008c, B:135:0x01e9, B:136:0x01f2), top: B:3:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005b A[Catch: all -> 0x01f3, TryCatch #0 {, blocks: (B:4:0x0011, B:6:0x0019, B:8:0x002c, B:10:0x0034, B:11:0x0037, B:13:0x003d, B:14:0x0045, B:16:0x004d, B:19:0x005b, B:21:0x0067, B:22:0x0091, B:124:0x006f, B:126:0x0073, B:127:0x007b, B:129:0x0081, B:131:0x0089, B:132:0x008c, B:135:0x01e9, B:136:0x01f2), top: B:3:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ec A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v1, types: [T, okhttp3.internal.connection.RealConnection] */
    /* JADX WARN: Type inference failed for: r8v4, types: [T, okhttp3.internal.connection.RealConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final RealConnection findConnection(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled) throws IOException {
        Socket socket;
        boolean z;
        boolean z2;
        RealConnection realConnection = (RealConnection) null;
        Route route = (Route) null;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        synchronized (this.connectionPool) {
            if (this.transmitter.isCanceled()) {
                throw new IOException("Canceled");
            }
            this.hasStreamFailure = false;
            objectRef.element = this.transmitter.getConnection();
            if (this.transmitter.getConnection() != null) {
                RealConnection connection = this.transmitter.getConnection();
                if (connection == null) {
                    Intrinsics.throwNpe();
                }
                if (connection.getNoNewExchanges()) {
                    socket = this.transmitter.releaseConnectionNoEvents();
                    if (this.transmitter.getConnection() != null) {
                        realConnection = this.transmitter.getConnection();
                        objectRef.element = (RealConnection) 0;
                    }
                    if (realConnection == null) {
                        if (this.connectionPool.transmitterAcquirePooledConnection(this.address, this.transmitter, null, false)) {
                            realConnection = this.transmitter.getConnection();
                            z = true;
                            Unit unit = Unit.INSTANCE;
                        } else if (this.nextRouteToTry != null) {
                            route = this.nextRouteToTry;
                            this.nextRouteToTry = (Route) null;
                        } else if (retryCurrentRoute()) {
                            RealConnection connection2 = this.transmitter.getConnection();
                            if (connection2 == null) {
                                Intrinsics.throwNpe();
                            }
                            route = connection2.route();
                        }
                    }
                    z = false;
                    Unit unit2 = Unit.INSTANCE;
                }
            }
            socket = null;
            if (this.transmitter.getConnection() != null) {
            }
            if (realConnection == null) {
            }
            z = false;
            Unit unit22 = Unit.INSTANCE;
        }
        if (socket != null) {
            Util.closeQuietly(socket);
        }
        if (((RealConnection) objectRef.element) != null) {
            EventListener eventListener = this.eventListener;
            Call call = this.call;
            RealConnection realConnection2 = (RealConnection) objectRef.element;
            if (realConnection2 == null) {
                Intrinsics.throwNpe();
            }
            eventListener.connectionReleased(call, realConnection2);
        }
        if (z) {
            EventListener eventListener2 = this.eventListener;
            Call call2 = this.call;
            if (realConnection == null) {
                Intrinsics.throwNpe();
            }
            eventListener2.connectionAcquired(call2, realConnection);
        }
        if (realConnection != null) {
            if (realConnection == null) {
                Intrinsics.throwNpe();
            }
            return realConnection;
        }
        if (route == null) {
            RouteSelector.Selection selection = this.routeSelection;
            if (selection != null) {
                if (selection == null) {
                    Intrinsics.throwNpe();
                }
            }
            this.routeSelection = this.routeSelector.next();
            z2 = true;
            List list = (List) null;
            synchronized (this.connectionPool) {
                if (this.transmitter.isCanceled()) {
                    throw new IOException("Canceled");
                }
                if (z2) {
                    RouteSelector.Selection selection2 = this.routeSelection;
                    if (selection2 == null) {
                        Intrinsics.throwNpe();
                    }
                    list = selection2.getRoutes();
                    if (this.connectionPool.transmitterAcquirePooledConnection(this.address, this.transmitter, list, false)) {
                        realConnection = this.transmitter.getConnection();
                        z = true;
                    }
                }
                if (!z) {
                    if (route == null) {
                        RouteSelector.Selection selection3 = this.routeSelection;
                        if (selection3 == null) {
                            Intrinsics.throwNpe();
                        }
                        route = selection3.next();
                    }
                    RealConnectionPool realConnectionPool = this.connectionPool;
                    if (route == null) {
                        Intrinsics.throwNpe();
                    }
                    realConnection = new RealConnection(realConnectionPool, route);
                    this.connectingConnection = realConnection;
                }
                Unit unit3 = Unit.INSTANCE;
            }
            if (z) {
                EventListener eventListener3 = this.eventListener;
                Call call3 = this.call;
                if (realConnection == null) {
                    Intrinsics.throwNpe();
                }
                eventListener3.connectionAcquired(call3, realConnection);
                if (realConnection == null) {
                    Intrinsics.throwNpe();
                }
                return realConnection;
            }
            if (realConnection == null) {
                Intrinsics.throwNpe();
            }
            realConnection.connect(connectTimeout, readTimeout, writeTimeout, pingIntervalMillis, connectionRetryEnabled, this.call, this.eventListener);
            RouteDatabase routeDatabase = this.connectionPool.getRouteDatabase();
            if (realConnection == null) {
                Intrinsics.throwNpe();
            }
            routeDatabase.connected(realConnection.route());
            Socket socket2 = (Socket) null;
            synchronized (this.connectionPool) {
                this.connectingConnection = (RealConnection) null;
                if (this.connectionPool.transmitterAcquirePooledConnection(this.address, this.transmitter, list, true)) {
                    if (realConnection == null) {
                        Intrinsics.throwNpe();
                    }
                    realConnection.setNoNewExchanges(true);
                    if (realConnection == null) {
                        Intrinsics.throwNpe();
                    }
                    socket2 = realConnection.socket();
                    realConnection = this.transmitter.getConnection();
                    this.nextRouteToTry = route;
                } else {
                    RealConnectionPool realConnectionPool2 = this.connectionPool;
                    if (realConnection == null) {
                        Intrinsics.throwNpe();
                    }
                    realConnectionPool2.put(realConnection);
                    Transmitter transmitter = this.transmitter;
                    if (realConnection == null) {
                        Intrinsics.throwNpe();
                    }
                    transmitter.acquireConnectionNoEvents(realConnection);
                }
                Unit unit4 = Unit.INSTANCE;
            }
            if (socket2 != null) {
                Util.closeQuietly(socket2);
            }
            EventListener eventListener4 = this.eventListener;
            Call call4 = this.call;
            if (realConnection == null) {
                Intrinsics.throwNpe();
            }
            eventListener4.connectionAcquired(call4, realConnection);
            if (realConnection == null) {
                Intrinsics.throwNpe();
            }
            return realConnection;
        }
        z2 = false;
        List list2 = (List) null;
        synchronized (this.connectionPool) {
        }
    }

    public final RealConnection connectingConnection() {
        boolean holdsLock = Thread.holdsLock(this.connectionPool);
        if (!_Assertions.ENABLED || holdsLock) {
            return this.connectingConnection;
        }
        throw new AssertionError("Assertion failed");
    }

    public final void trackFailure() {
        boolean z = !Thread.holdsLock(this.connectionPool);
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Assertion failed");
        }
        synchronized (this.connectionPool) {
            this.hasStreamFailure = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean hasStreamFailure() {
        boolean z;
        synchronized (this.connectionPool) {
            z = this.hasStreamFailure;
        }
        return z;
    }

    public final boolean hasRouteToTry() {
        synchronized (this.connectionPool) {
            boolean z = true;
            if (this.nextRouteToTry != null) {
                return true;
            }
            if (retryCurrentRoute()) {
                RealConnection connection = this.transmitter.getConnection();
                if (connection == null) {
                    Intrinsics.throwNpe();
                }
                this.nextRouteToTry = connection.route();
                return true;
            }
            RouteSelector.Selection selection = this.routeSelection;
            if (!(selection != null ? selection.hasNext() : false) && !this.routeSelector.hasNext()) {
                z = false;
            }
            return z;
        }
    }

    private final boolean retryCurrentRoute() {
        if (this.transmitter.getConnection() != null) {
            RealConnection connection = this.transmitter.getConnection();
            if (connection == null) {
                Intrinsics.throwNpe();
            }
            if (connection.getRouteFailureCount$okhttp() == 0) {
                RealConnection connection2 = this.transmitter.getConnection();
                if (connection2 == null) {
                    Intrinsics.throwNpe();
                }
                if (Util.canReuseConnectionFor(connection2.route().address().url(), this.address.url())) {
                    return true;
                }
            }
        }
        return false;
    }
}
