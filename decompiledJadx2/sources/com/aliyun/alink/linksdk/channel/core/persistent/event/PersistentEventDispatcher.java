package com.aliyun.alink.linksdk.channel.core.persistent.event;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alibaba.fastjson.JSON;
import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import com.aliyun.alink.linksdk.tools.AError;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PersistentEventDispatcher {

    /* renamed from: a */
    protected HashMap<IOnPushListener, Boolean> f891a;

    /* renamed from: b */
    protected HashMap<IConnectionStateListener, Boolean> f892b;

    /* renamed from: c */
    protected HashMap<INetSessionStateListener, Boolean> f893c;

    /* renamed from: d */
    protected HandlerC0972b f894d;

    private PersistentEventDispatcher() {
        this.f891a = null;
        this.f892b = null;
        this.f893c = null;
        this.f894d = null;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.event.PersistentEventDispatcher$a */
    /* loaded from: classes.dex */
    private static class C0971a {

        /* renamed from: a */
        private static final PersistentEventDispatcher f895a;

        static {
            PersistentEventDispatcher persistentEventDispatcher = new PersistentEventDispatcher();
            f895a = persistentEventDispatcher;
            persistentEventDispatcher.m401a();
        }
    }

    public static PersistentEventDispatcher getInstance() {
        return C0971a.f895a;
    }

    /* renamed from: a */
    void m401a() {
        if (this.f894d == null) {
            this.f894d = new HandlerC0972b();
        }
    }

    public void registerOnPushListener(IOnPushListener iOnPushListener, boolean z) {
        C0969a.m393a("PersistentEventDispatch", "registerOnPushListener()");
        synchronized (this) {
            if (iOnPushListener == null) {
                return;
            }
            if (this.f891a == null) {
                this.f891a = new HashMap<>();
            }
            this.f891a.put(iOnPushListener, Boolean.valueOf(z));
        }
    }

    public void unregisterOnPushListener(IOnPushListener iOnPushListener) {
        C0969a.m393a("PersistentEventDispatch", "unregisterOnPushListener()");
        synchronized (this) {
            if (iOnPushListener != null) {
                if (this.f891a != null && this.f891a.size() > 0) {
                    this.f891a.remove(iOnPushListener);
                }
            }
        }
    }

    public void registerOnTunnelStateListener(IConnectionStateListener iConnectionStateListener, boolean z) {
        C0969a.m393a("PersistentEventDispatch", "registerOnTunnelStateListener()");
        synchronized (this) {
            if (iConnectionStateListener == null) {
                return;
            }
            if (this.f892b == null) {
                this.f892b = new HashMap<>();
            }
            this.f892b.put(iConnectionStateListener, Boolean.valueOf(z));
        }
    }

    public void unregisterOnTunnelStateListener(IConnectionStateListener iConnectionStateListener) {
        C0969a.m393a("PersistentEventDispatch", "unregisterOnTunnelStateListener()");
        synchronized (this) {
            if (iConnectionStateListener != null) {
                if (this.f892b != null && this.f892b.size() > 0) {
                    this.f892b.remove(iConnectionStateListener);
                }
            }
        }
    }

    public void registerNetSessionStateListener(INetSessionStateListener iNetSessionStateListener, boolean z) {
        C0969a.m393a("PersistentEventDispatch", "registerNetSessionStateListener()");
        synchronized (this) {
            if (iNetSessionStateListener == null) {
                return;
            }
            if (this.f893c == null) {
                this.f893c = new HashMap<>();
            }
            this.f893c.put(iNetSessionStateListener, Boolean.valueOf(z));
        }
    }

    public void unregisterNetSessionStateListener(INetSessionStateListener iNetSessionStateListener) {
        C0969a.m393a("PersistentEventDispatch", "unregisterNetSessionStateListener()");
        synchronized (this) {
            if (iNetSessionStateListener != null) {
                if (this.f893c != null && this.f893c.size() > 0) {
                    this.f893c.remove(iNetSessionStateListener);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00e4, code lost:
    
        com.aliyun.alink.linksdk.channel.core.p041b.C0969a.m394b("PersistentEventDispatch", "mqtt connected, method = [" + r5 + "], content = [" + r6 + "], errorCode = [" + r7 + "], message = [" + r8 + "]");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void broadcastMessage(int i, String str, byte[] bArr, int i2, String str2) {
        C0969a.m393a("PersistentEventDispatch", "broadcastMessage() called with: what = [" + i + "]");
        synchronized (this) {
            if (i == 3) {
                try {
                    if (this.f891a != null) {
                        this.f891a.keySet();
                        for (IOnPushListener iOnPushListener : this.f891a.keySet()) {
                            if (iOnPushListener.shouldHandle(str)) {
                                if (this.f891a.get(iOnPushListener).booleanValue()) {
                                    this.f894d.m404a(3, iOnPushListener, str, bArr);
                                } else {
                                    iOnPushListener.onCommand(str, bArr);
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (i != 1 && i != 2 && i != 7) {
                if (i == 5 || i == 6 || i == 4) {
                    C0969a.m396d("PersistentEventDispatch", "mqtt session fail, what = [" + i + "], method = [" + str + "], content = [" + bArr + "], errorCode = [" + i2 + "], message = [" + str2 + "]");
                    if (this.f893c != null) {
                        for (INetSessionStateListener iNetSessionStateListener : this.f893c.keySet()) {
                            if (this.f893c.get(iNetSessionStateListener).booleanValue()) {
                                this.f894d.m403a(i, iNetSessionStateListener, i2, (String) null);
                            } else {
                                m400a(i, iNetSessionStateListener);
                            }
                        }
                    }
                }
            }
            if (i == 2) {
                C0969a.m395c("PersistentEventDispatch", "mqtt disconnected, method = [" + str + "], content = [" + bArr + "], errorCode = [" + i2 + "], message = [" + str2 + "]");
            } else {
                C0969a.m396d("PersistentEventDispatch", "mqtt connect fail, method = [" + str + "], content = [" + bArr + "], errorCode = [" + i2 + "], message = [" + str2 + "]");
            }
            if (this.f892b != null) {
                for (IConnectionStateListener iConnectionStateListener : this.f892b.keySet()) {
                    if (this.f892b.get(iConnectionStateListener).booleanValue()) {
                        this.f894d.m403a(i, iConnectionStateListener, i2, str2);
                    } else {
                        m399a(i, iConnectionStateListener, i2, str2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.event.PersistentEventDispatcher$b */
    /* loaded from: classes.dex */
    public static class HandlerC0972b extends Handler {
        public HandlerC0972b() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null || message.obj == null || !(message.obj instanceof a)) {
                return;
            }
            a aVar = (a) message.obj;
            if (aVar.f896a instanceof IOnPushListener) {
                IOnPushListener iOnPushListener = (IOnPushListener) aVar.f896a;
                if (message.what == 3) {
                    iOnPushListener.onCommand(aVar.f899d, aVar.f900e);
                    return;
                }
                return;
            }
            if (aVar.f896a instanceof IConnectionStateListener) {
                PersistentEventDispatcher.m399a(message.what, (IConnectionStateListener) aVar.f896a, aVar.f898c, aVar.f897b);
            } else if (aVar.f896a instanceof INetSessionStateListener) {
                PersistentEventDispatcher.m400a(message.what, (INetSessionStateListener) aVar.f896a);
            }
        }

        /* renamed from: a */
        public void m403a(int i, Object obj, int i2, String str) {
            Message obtainMessage = obtainMessage();
            obtainMessage.what = i;
            obtainMessage.obj = new a(obj, i2, str);
            sendMessageDelayed(obtainMessage, 10L);
        }

        /* renamed from: a */
        public void m404a(int i, Object obj, String str, byte[] bArr) {
            Message obtainMessage = obtainMessage();
            obtainMessage.what = i;
            obtainMessage.obj = new a(obj, str, bArr);
            sendMessageDelayed(obtainMessage, 10L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.event.PersistentEventDispatcher$b$a */
        /* loaded from: classes.dex */
        public static class a {

            /* renamed from: a */
            public Object f896a;

            /* renamed from: b */
            public String f897b;

            /* renamed from: c */
            public int f898c;

            /* renamed from: d */
            public String f899d;

            /* renamed from: e */
            public byte[] f900e;

            public a(Object obj, int i, String str) {
                this.f896a = obj;
                this.f898c = i;
                this.f897b = str;
            }

            public a(Object obj, String str, byte[] bArr) {
                this.f896a = obj;
                this.f899d = str;
                this.f900e = bArr;
            }
        }
    }

    /* renamed from: a */
    static void m399a(int i, IConnectionStateListener iConnectionStateListener, int i2, String str) {
        C0969a.m393a("PersistentEventDispatch", "OnTunnelState()");
        if (iConnectionStateListener != null) {
            try {
                if (i == 1) {
                    iConnectionStateListener.onConnected();
                } else if (i == 2) {
                    iConnectionStateListener.onDisconnect();
                } else {
                    if (i != 7) {
                        return;
                    }
                    AError aError = new AError();
                    aError.setCode(i2);
                    aError.setMsg(str);
                    iConnectionStateListener.onConnectFail(JSON.toJSONString(aError));
                }
            } catch (Exception unused) {
                C0969a.m396d("PersistentEventDispatch", "catch exception from IConnectionStateListener");
            }
        }
    }

    /* renamed from: a */
    static void m400a(int i, INetSessionStateListener iNetSessionStateListener) {
        C0969a.m393a("PersistentEventDispatch", "OnSessionState()");
        if (iNetSessionStateListener != null) {
            try {
                if (i == 5) {
                    iNetSessionStateListener.onSessionEffective();
                } else if (i == 6) {
                    iNetSessionStateListener.onSessionInvalid();
                } else if (i != 4) {
                } else {
                    iNetSessionStateListener.onNeedLogin();
                }
            } catch (Exception unused) {
                C0969a.m396d("PersistentEventDispatch", "catch exception from INetSessionStateListener");
            }
        }
    }
}
