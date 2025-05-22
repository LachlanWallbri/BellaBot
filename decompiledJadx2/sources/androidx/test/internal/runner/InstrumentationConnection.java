package androidx.test.internal.runner;

import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.test.internal.util.Checks;
import androidx.test.internal.util.LogUtil;
import androidx.test.internal.util.ParcelableIBinder;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.MonitoringInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class InstrumentationConnection {
    public static final String BROADCAST_FILTER = "androidx.test.runner.InstrumentationConnection.event";
    static final String BUNDLE_BR_NEW_BINDER = "new_instrumentation_binder";
    private static final String BUNDLE_KEY_CLIENTS = "instr_clients";
    private static final String BUNDLE_KEY_CLIENT_MESSENGER = "instr_client_msgr";
    private static final String BUNDLE_KEY_CLIENT_TYPE = "instr_client_type";
    private static final String BUNDLE_KEY_UUID = "instr_uuid";
    private static final InstrumentationConnection DEFAULT_INSTANCE = new InstrumentationConnection(InstrumentationRegistry.getInstrumentation().getTargetContext());
    static final int MSG_ADD_CLIENTS_IN_BUNDLE = 6;
    static final int MSG_ADD_INSTRUMENTATION = 4;
    private static final int MSG_HANDLE_INSTRUMENTATION_FROM_BROADCAST = 3;
    private static final int MSG_PERFORM_CLEANUP = 11;
    private static final int MSG_PERFORM_CLEANUP_FINISHED = 12;
    private static final int MSG_REG_CLIENT = 8;
    private static final int MSG_REMOTE_ADD_CLIENT = 0;
    static final int MSG_REMOTE_CLEANUP_REQUEST = 10;
    private static final int MSG_REMOTE_REMOVE_CLIENT = 1;
    private static final int MSG_REMOVE_CLIENTS_IN_BUNDLE = 7;
    private static final int MSG_REMOVE_INSTRUMENTATION = 5;
    private static final int MSG_TERMINATE = 2;
    private static final int MSG_UN_REG_CLIENT = 9;
    private static final String TAG = "InstrConnection";
    private static MonitoringInstrumentation.ActivityFinisher activityFinisher;
    private static Instrumentation instrumentation;
    IncomingHandler incomingHandler;
    final BroadcastReceiver messengerReceiver = new MessengerReceiver();
    private Context targetContext;

    InstrumentationConnection(Context context) {
        this.targetContext = (Context) Checks.checkNotNull(context, "Context can't be null");
    }

    public static InstrumentationConnection getInstance() {
        return DEFAULT_INSTANCE;
    }

    public synchronized void init(Instrumentation instrumentation2, MonitoringInstrumentation.ActivityFinisher finisher) {
        LogUtil.logDebugWithProcess(TAG, "init", new Object[0]);
        if (this.incomingHandler == null) {
            instrumentation = instrumentation2;
            activityFinisher = finisher;
            HandlerThread handlerThread = new HandlerThread("InstrumentationConnectionThread");
            handlerThread.start();
            this.incomingHandler = new IncomingHandler(handlerThread.getLooper());
            Intent intent = new Intent(BROADCAST_FILTER);
            Bundle bundle = new Bundle();
            bundle.putParcelable(BUNDLE_BR_NEW_BINDER, new ParcelableIBinder(this.incomingHandler.messengerHandler.getBinder()));
            intent.putExtra(BUNDLE_BR_NEW_BINDER, bundle);
            try {
                this.targetContext.sendBroadcast(intent);
                this.targetContext.registerReceiver(this.messengerReceiver, new IntentFilter(BROADCAST_FILTER));
            } catch (SecurityException unused) {
                Log.i(TAG, "Could not send broadcast or register receiver (isolatedProcess?)");
            }
        }
    }

    public synchronized void terminate() {
        LogUtil.logDebugWithProcess(TAG, "Terminate is called", new Object[0]);
        if (this.incomingHandler != null) {
            this.incomingHandler.runSyncTask(new Callable<Void>() { // from class: androidx.test.internal.runner.InstrumentationConnection.1
                @Override // java.util.concurrent.Callable
                public Void call() {
                    InstrumentationConnection.this.incomingHandler.doDie();
                    return null;
                }
            });
            this.targetContext.unregisterReceiver(this.messengerReceiver);
            this.incomingHandler = null;
        }
    }

    public synchronized void requestRemoteInstancesActivityCleanup() {
        IncomingHandler incomingHandler;
        Checks.checkState(this.incomingHandler != null, "Instrumentation Connection in not yet initialized");
        UUID randomUUID = UUID.randomUUID();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.incomingHandler.associateLatch(randomUUID, countDownLatch);
        Message obtainMessage = this.incomingHandler.obtainMessage(10);
        obtainMessage.replyTo = this.incomingHandler.messengerHandler;
        Bundle data = obtainMessage.getData();
        data.putSerializable(BUNDLE_KEY_UUID, randomUUID);
        obtainMessage.setData(data);
        this.incomingHandler.sendMessage(obtainMessage);
        try {
            try {
                if (!countDownLatch.await(2L, TimeUnit.SECONDS)) {
                    String valueOf = String.valueOf(randomUUID);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 60);
                    sb.append("Timed out while attempting to perform activity clean up for ");
                    sb.append(valueOf);
                    Log.w(TAG, sb.toString());
                }
                incomingHandler = this.incomingHandler;
            } catch (Throwable th) {
                this.incomingHandler.disassociateLatch(randomUUID);
                throw th;
            }
        } catch (InterruptedException e) {
            String valueOf2 = String.valueOf(randomUUID);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 61);
            sb2.append("Interrupted while waiting for response from message with id: ");
            sb2.append(valueOf2);
            Log.e(TAG, sb2.toString(), e);
            incomingHandler = this.incomingHandler;
        }
        incomingHandler.disassociateLatch(randomUUID);
    }

    public synchronized void registerClient(String type, Messenger messenger) {
        Checks.checkState(this.incomingHandler != null, "Instrumentation Connection in not yet initialized");
        String valueOf = String.valueOf(type);
        Log.i(TAG, valueOf.length() != 0 ? "Register client of type: ".concat(valueOf) : new String("Register client of type: "));
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_CLIENT_TYPE, type);
        bundle.putParcelable(BUNDLE_KEY_CLIENT_MESSENGER, messenger);
        Message obtainMessage = this.incomingHandler.obtainMessage(8);
        obtainMessage.setData(bundle);
        this.incomingHandler.sendMessage(obtainMessage);
    }

    public synchronized Set<Messenger> getClientsForType(final String type) {
        return this.incomingHandler.getClientsForType(type);
    }

    public synchronized void unregisterClient(String type, Messenger messenger) {
        Checks.checkState(this.incomingHandler != null, "Instrumentation Connection in not yet initialized");
        String valueOf = String.valueOf(type);
        Log.i(TAG, valueOf.length() != 0 ? "Unregister client of type: ".concat(valueOf) : new String("Unregister client of type: "));
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_CLIENT_TYPE, type);
        bundle.putParcelable(BUNDLE_KEY_CLIENT_MESSENGER, messenger);
        Message obtainMessage = this.incomingHandler.obtainMessage(9);
        obtainMessage.setData(bundle);
        this.incomingHandler.sendMessage(obtainMessage);
    }

    /* loaded from: classes.dex */
    class MessengerReceiver extends BroadcastReceiver {
        MessengerReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "Broadcast received", new Object[0]);
            Bundle bundleExtra = intent.getBundleExtra(InstrumentationConnection.BUNDLE_BR_NEW_BINDER);
            if (bundleExtra == null) {
                Log.w(InstrumentationConnection.TAG, "Broadcast intent doesn't contain any extras, ignoring it..");
                return;
            }
            ParcelableIBinder parcelableIBinder = (ParcelableIBinder) bundleExtra.getParcelable(InstrumentationConnection.BUNDLE_BR_NEW_BINDER);
            if (parcelableIBinder != null) {
                Messenger messenger = new Messenger(parcelableIBinder.getIBinder());
                Message obtainMessage = InstrumentationConnection.this.incomingHandler.obtainMessage(3);
                obtainMessage.replyTo = messenger;
                InstrumentationConnection.this.incomingHandler.sendMessage(obtainMessage);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class IncomingHandler extends Handler {
        private final Map<UUID, CountDownLatch> latches;
        Messenger messengerHandler;
        Set<Messenger> otherInstrumentations;
        Map<String, Set<Messenger>> typedClients;

        public IncomingHandler(Looper looper) {
            super(looper);
            this.messengerHandler = new Messenger(this);
            this.otherInstrumentations = new HashSet();
            this.typedClients = new HashMap();
            this.latches = new HashMap();
            if (Looper.getMainLooper() == looper || Looper.myLooper() == looper) {
                throw new IllegalStateException("This handler should not be using the main thread looper nor the instrumentation thread looper.");
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REMOTE_ADD_CLIENT)", new Object[0]);
                    registerClient(msg.getData().getString(InstrumentationConnection.BUNDLE_KEY_CLIENT_TYPE), (Messenger) msg.getData().getParcelable(InstrumentationConnection.BUNDLE_KEY_CLIENT_MESSENGER));
                    return;
                case 1:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REMOTE_REMOVE_CLIENT)", new Object[0]);
                    unregisterClient(msg.getData().getString(InstrumentationConnection.BUNDLE_KEY_CLIENT_TYPE), msg.replyTo);
                    return;
                case 2:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_TERMINATE)", new Object[0]);
                    doDie();
                    return;
                case 3:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_HANDLE_INSTRUMENTATION_FROM_BROADCAST)", new Object[0]);
                    if (this.otherInstrumentations.add(msg.replyTo)) {
                        sendMessageWithReply(msg.replyTo, 4, null);
                        return;
                    } else {
                        Log.w(InstrumentationConnection.TAG, "Broadcast with existing binder was received, ignoring it..");
                        return;
                    }
                case 4:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_ADD_INSTRUMENTATION)", new Object[0]);
                    if (this.otherInstrumentations.add(msg.replyTo)) {
                        if (!this.typedClients.isEmpty()) {
                            sendMessageWithReply(msg.replyTo, 6, null);
                        }
                        clientsRegistrationFromBundle(msg.getData(), true);
                        return;
                    }
                    Log.w(InstrumentationConnection.TAG, "Message with existing binder was received, ignoring it..");
                    return;
                case 5:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REMOVE_INSTRUMENTATION)", new Object[0]);
                    if (this.otherInstrumentations.remove(msg.replyTo)) {
                        return;
                    }
                    Log.w(InstrumentationConnection.TAG, "Attempting to remove a non-existent binder!");
                    return;
                case 6:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_ADD_CLIENTS_IN_BUNDLE)", new Object[0]);
                    clientsRegistrationFromBundle(msg.getData(), true);
                    return;
                case 7:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REMOVE_CLIENTS_IN_BUNDLE)", new Object[0]);
                    clientsRegistrationFromBundle(msg.getData(), false);
                    return;
                case 8:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REG_CLIENT)", new Object[0]);
                    registerClient(msg.getData().getString(InstrumentationConnection.BUNDLE_KEY_CLIENT_TYPE), (Messenger) msg.getData().getParcelable(InstrumentationConnection.BUNDLE_KEY_CLIENT_MESSENGER));
                    sendMessageToOtherInstr(0, msg.getData());
                    return;
                case 9:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_UN_REG_CLIENT)", new Object[0]);
                    unregisterClient(msg.getData().getString(InstrumentationConnection.BUNDLE_KEY_CLIENT_TYPE), (Messenger) msg.getData().getParcelable(InstrumentationConnection.BUNDLE_KEY_CLIENT_MESSENGER));
                    sendMessageToOtherInstr(1, msg.getData());
                    return;
                case 10:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_REMOTE_CLEANUP_REQUEST)", new Object[0]);
                    if (this.otherInstrumentations.isEmpty()) {
                        Message obtainMessage = obtainMessage(12);
                        obtainMessage.setData(msg.getData());
                        sendMessage(obtainMessage);
                        return;
                    }
                    sendMessageToOtherInstr(11, msg.getData());
                    return;
                case 11:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_PERFORM_CLEANUP)", new Object[0]);
                    InstrumentationConnection.instrumentation.runOnMainSync(InstrumentationConnection.activityFinisher);
                    sendMessageWithReply(msg.replyTo, 12, msg.getData());
                    return;
                case 12:
                    LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "handleMessage(MSG_PERFORM_CLEANUP_FINISHED)", new Object[0]);
                    notifyLatch((UUID) msg.getData().getSerializable(InstrumentationConnection.BUNDLE_KEY_UUID));
                    return;
                default:
                    int i = msg.what;
                    StringBuilder sb = new StringBuilder(42);
                    sb.append("Unknown message code received: ");
                    sb.append(i);
                    Log.w(InstrumentationConnection.TAG, sb.toString());
                    super.handleMessage(msg);
                    return;
            }
        }

        private void notifyLatch(UUID uuid) {
            if (uuid != null && this.latches.containsKey(uuid)) {
                this.latches.get(uuid).countDown();
                return;
            }
            String valueOf = String.valueOf(uuid);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16);
            sb.append("Latch not found ");
            sb.append(valueOf);
            Log.w(InstrumentationConnection.TAG, sb.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void associateLatch(final UUID latchId, final CountDownLatch latch) {
            runSyncTask(new Callable<Void>() { // from class: androidx.test.internal.runner.InstrumentationConnection.IncomingHandler.1
                @Override // java.util.concurrent.Callable
                public Void call() {
                    IncomingHandler.this.latches.put(latchId, latch);
                    return null;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void disassociateLatch(final UUID latchId) {
            runSyncTask(new Callable<Void>() { // from class: androidx.test.internal.runner.InstrumentationConnection.IncomingHandler.2
                @Override // java.util.concurrent.Callable
                public Void call() {
                    IncomingHandler.this.latches.remove(latchId);
                    return null;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T> T runSyncTask(Callable<T> callable) {
            FutureTask futureTask = new FutureTask(callable);
            post(futureTask);
            try {
                return (T) futureTask.get();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e.getCause());
            } catch (ExecutionException e2) {
                throw new IllegalStateException(e2.getCause());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void doDie() {
            Log.i(InstrumentationConnection.TAG, "terminating process");
            sendMessageToOtherInstr(5, null);
            this.otherInstrumentations.clear();
            this.typedClients.clear();
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "quitting looper...", new Object[0]);
            getLooper().quit();
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "finishing instrumentation...", new Object[0]);
            InstrumentationConnection.instrumentation.finish(0, null);
            Instrumentation unused = InstrumentationConnection.instrumentation = null;
            MonitoringInstrumentation.ActivityFinisher unused2 = InstrumentationConnection.activityFinisher = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Set<Messenger> getClientsForType(final String type) {
            FutureTask futureTask = new FutureTask(new Callable<Set<Messenger>>() { // from class: androidx.test.internal.runner.InstrumentationConnection.IncomingHandler.3
                @Override // java.util.concurrent.Callable
                public Set<Messenger> call() {
                    return IncomingHandler.this.typedClients.get(type);
                }
            });
            post(futureTask);
            try {
                return (Set) futureTask.get();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } catch (ExecutionException e2) {
                throw new IllegalStateException(e2.getCause());
            }
        }

        private void sendMessageWithReply(Messenger toMessenger, int what, Bundle data) {
            StringBuilder sb = new StringBuilder(45);
            sb.append("sendMessageWithReply type: ");
            sb.append(what);
            sb.append(" called");
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, sb.toString(), new Object[0]);
            Message obtainMessage = obtainMessage(what);
            obtainMessage.replyTo = this.messengerHandler;
            if (data != null) {
                obtainMessage.setData(data);
            }
            if (!this.typedClients.isEmpty()) {
                Bundle data2 = obtainMessage.getData();
                data2.putStringArrayList(InstrumentationConnection.BUNDLE_KEY_CLIENTS, new ArrayList<>(this.typedClients.keySet()));
                for (Map.Entry<String, Set<Messenger>> entry : this.typedClients.entrySet()) {
                    data2.putParcelableArray(String.valueOf(entry.getKey()), (Messenger[]) entry.getValue().toArray(new Messenger[entry.getValue().size()]));
                }
                obtainMessage.setData(data2);
            }
            try {
                toMessenger.send(obtainMessage);
            } catch (RemoteException e) {
                Log.w(InstrumentationConnection.TAG, "The remote process is terminated unexpectedly", e);
                instrBinderDied(toMessenger);
            }
        }

        private void sendMessageToOtherInstr(int what, Bundle data) {
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "sendMessageToOtherInstr() called with: what = [%s], data = [%s]", Integer.valueOf(what), data);
            Iterator<Messenger> it = this.otherInstrumentations.iterator();
            while (it.hasNext()) {
                sendMessageWithReply(it.next(), what, data);
            }
        }

        private void clientsRegistrationFromBundle(Bundle clientsBundle, boolean shouldRegister) {
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "clientsRegistrationFromBundle called", new Object[0]);
            if (clientsBundle == null) {
                Log.w(InstrumentationConnection.TAG, "The client bundle is null, ignoring...");
                return;
            }
            ArrayList<String> stringArrayList = clientsBundle.getStringArrayList(InstrumentationConnection.BUNDLE_KEY_CLIENTS);
            if (stringArrayList == null) {
                Log.w(InstrumentationConnection.TAG, "No clients found in the given bundle");
                return;
            }
            Iterator<String> it = stringArrayList.iterator();
            while (it.hasNext()) {
                String next = it.next();
                Parcelable[] parcelableArray = clientsBundle.getParcelableArray(String.valueOf(next));
                if (parcelableArray != null) {
                    for (Parcelable parcelable : parcelableArray) {
                        if (shouldRegister) {
                            registerClient(next, (Messenger) parcelable);
                        } else {
                            unregisterClient(next, (Messenger) parcelable);
                        }
                    }
                }
            }
        }

        private void registerClient(String type, Messenger client) {
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "registerClient called with type = [%s] client = [%s]", type, client);
            Checks.checkNotNull(type, "type cannot be null!");
            Checks.checkNotNull(client, "client cannot be null!");
            Set<Messenger> set = this.typedClients.get(type);
            if (set == null) {
                HashSet hashSet = new HashSet();
                hashSet.add(client);
                this.typedClients.put(type, hashSet);
                return;
            }
            set.add(client);
        }

        private void unregisterClient(String type, Messenger client) {
            LogUtil.logDebugWithProcess(InstrumentationConnection.TAG, "unregisterClient called with type = [%s] client = [%s]", type, client);
            Checks.checkNotNull(type, "type cannot be null!");
            Checks.checkNotNull(client, "client cannot be null!");
            if (!this.typedClients.containsKey(type)) {
                String valueOf = String.valueOf(type);
                Log.w(InstrumentationConnection.TAG, valueOf.length() != 0 ? "There are no registered clients for type: ".concat(valueOf) : new String("There are no registered clients for type: "));
                return;
            }
            Set<Messenger> set = this.typedClients.get(type);
            if (!set.contains(client)) {
                StringBuilder sb = new StringBuilder(String.valueOf(type).length() + 78);
                sb.append("Could not unregister client for type ");
                sb.append(type);
                sb.append(" because it doesn't seem to be registered");
                Log.w(InstrumentationConnection.TAG, sb.toString());
                return;
            }
            set.remove(client);
            if (set.isEmpty()) {
                this.typedClients.remove(type);
            }
        }

        private void instrBinderDied(Messenger instrMessenger) {
            Message obtainMessage = obtainMessage(5);
            obtainMessage.replyTo = instrMessenger;
            sendMessage(obtainMessage);
        }
    }
}
