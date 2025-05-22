package org.eclipse.paho.client.mqttv3.internal;

import com.pudutech.mirsdk.SolicitService;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttInputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRec;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class CommsReceiver implements Runnable {
    private static final String CLASS_NAME;
    private static final Logger log;
    private ClientComms clientComms;
    private ClientState clientState;

    /* renamed from: in */
    private MqttInputStream f9996in;
    private Future receiverFuture;
    private volatile boolean receiving;
    private String threadName;
    private CommsTokenStore tokenStore;
    private volatile boolean running = false;
    private Object lifecycle = new Object();
    private Thread recThread = null;
    private final Semaphore runningSemaphore = new Semaphore(1);

    /* loaded from: classes9.dex */
    private enum State {
        STOPPED,
        RUNNING,
        STARTING,
        RECEIVING;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static State[] valuesCustom() {
            State[] valuesCustom = values();
            int length = valuesCustom.length;
            State[] stateArr = new State[length];
            System.arraycopy(valuesCustom, 0, stateArr, 0, length);
            return stateArr;
        }
    }

    static {
        String name = CommsReceiver.class.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public CommsReceiver(ClientComms clientComms, ClientState clientState, CommsTokenStore commsTokenStore, InputStream inputStream) {
        this.clientState = null;
        this.clientComms = null;
        this.tokenStore = null;
        this.f9996in = new MqttInputStream(clientState, inputStream);
        this.clientComms = clientComms;
        this.clientState = clientState;
        this.tokenStore = commsTokenStore;
        log.setResourceName(clientComms.getClient().getClientId());
    }

    public void start(String str, ExecutorService executorService) {
        this.threadName = str;
        log.fine(CLASS_NAME, "start", "855");
        synchronized (this.lifecycle) {
            if (!this.running) {
                this.running = true;
                this.receiverFuture = executorService.submit(this);
            }
        }
    }

    public void stop() {
        Semaphore semaphore;
        synchronized (this.lifecycle) {
            if (this.receiverFuture != null) {
                this.receiverFuture.cancel(true);
            }
            log.fine(CLASS_NAME, "stop", "850");
            if (this.running) {
                this.running = false;
                this.receiving = false;
                if (!Thread.currentThread().equals(this.recThread)) {
                    try {
                        this.runningSemaphore.tryAcquire(SolicitService.CAMERA_OPEN_TIME_OUT, TimeUnit.MILLISECONDS);
                        semaphore = this.runningSemaphore;
                    } catch (InterruptedException unused) {
                        semaphore = this.runningSemaphore;
                    } catch (Throwable th) {
                        this.runningSemaphore.release();
                        throw th;
                    }
                    semaphore.release();
                }
            }
        }
        this.recThread = null;
        log.fine(CLASS_NAME, "stop", "851");
    }

    @Override // java.lang.Runnable
    public void run() {
        Thread currentThread = Thread.currentThread();
        this.recThread = currentThread;
        currentThread.setName(this.threadName);
        try {
            this.runningSemaphore.acquire();
            MqttToken mqttToken = null;
            while (this.running && this.f9996in != null) {
                try {
                    try {
                        try {
                            log.fine(CLASS_NAME, "run", "852");
                            this.receiving = this.f9996in.available() > 0;
                            MqttWireMessage readMqttWireMessage = this.f9996in.readMqttWireMessage();
                            this.receiving = false;
                            if (readMqttWireMessage instanceof MqttAck) {
                                mqttToken = this.tokenStore.getToken(readMqttWireMessage);
                                if (mqttToken != null) {
                                    synchronized (mqttToken) {
                                        this.clientState.notifyReceivedAck((MqttAck) readMqttWireMessage);
                                    }
                                } else {
                                    if (!(readMqttWireMessage instanceof MqttPubRec) && !(readMqttWireMessage instanceof MqttPubComp) && !(readMqttWireMessage instanceof MqttPubAck)) {
                                        throw new MqttException(6);
                                    }
                                    log.fine(CLASS_NAME, "run", "857");
                                }
                            } else if (readMqttWireMessage != null) {
                                this.clientState.notifyReceivedMsg(readMqttWireMessage);
                            }
                        } catch (IOException e) {
                            log.fine(CLASS_NAME, "run", "853");
                            this.running = false;
                            if (!this.clientComms.isDisconnecting()) {
                                this.clientComms.shutdownConnection(mqttToken, new MqttException(32109, e));
                            }
                        } catch (MqttException e2) {
                            log.fine(CLASS_NAME, "run", "856", null, e2);
                            this.running = false;
                            this.clientComms.shutdownConnection(mqttToken, e2);
                        }
                    } finally {
                        this.receiving = false;
                    }
                } catch (Throwable th) {
                    this.runningSemaphore.release();
                    throw th;
                }
            }
            this.runningSemaphore.release();
            log.fine(CLASS_NAME, "run", "854");
        } catch (InterruptedException unused) {
            this.running = false;
        }
    }

    public boolean isRunning() {
        return this.running;
    }

    public boolean isReceiving() {
        return this.receiving;
    }
}
