package org.eclipse.paho.android.service;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class MqttTokenAndroid implements IMqttToken {
    private MqttAndroidClient client;
    private IMqttToken delegate;
    private volatile boolean isComplete;
    private volatile MqttException lastException;
    private IMqttActionListener listener;
    private MqttException pendingException;
    private String[] topics;
    private Object userContext;
    private Object waitObject;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MqttTokenAndroid(MqttAndroidClient mqttAndroidClient, Object obj, IMqttActionListener iMqttActionListener) {
        this(mqttAndroidClient, obj, iMqttActionListener, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MqttTokenAndroid(MqttAndroidClient mqttAndroidClient, Object obj, IMqttActionListener iMqttActionListener, String[] strArr) {
        this.waitObject = new Object();
        this.client = mqttAndroidClient;
        this.userContext = obj;
        this.listener = iMqttActionListener;
        this.topics = strArr;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public void waitForCompletion() throws MqttException, MqttSecurityException {
        synchronized (this.waitObject) {
            try {
                this.waitObject.wait();
            } catch (InterruptedException unused) {
            }
        }
        MqttException mqttException = this.pendingException;
        if (mqttException != null) {
            throw mqttException;
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public void waitForCompletion(long j) throws MqttException, MqttSecurityException {
        synchronized (this.waitObject) {
            try {
                this.waitObject.wait(j);
            } catch (InterruptedException unused) {
            }
            if (!this.isComplete) {
                throw new MqttException(32000);
            }
            if (this.pendingException != null) {
                throw this.pendingException;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyComplete() {
        synchronized (this.waitObject) {
            this.isComplete = true;
            this.waitObject.notifyAll();
            if (this.listener != null) {
                this.listener.onSuccess(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyFailure(Throwable th) {
        synchronized (this.waitObject) {
            this.isComplete = true;
            if (th instanceof MqttException) {
                this.pendingException = (MqttException) th;
            } else {
                this.pendingException = new MqttException(th);
            }
            this.waitObject.notifyAll();
            if (th instanceof MqttException) {
                this.lastException = (MqttException) th;
            }
            if (this.listener != null) {
                this.listener.onFailure(this, th);
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public boolean isComplete() {
        return this.isComplete;
    }

    void setComplete(boolean z) {
        this.isComplete = z;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public MqttException getException() {
        return this.lastException;
    }

    void setException(MqttException mqttException) {
        this.lastException = mqttException;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public IMqttAsyncClient getClient() {
        return this.client;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.listener = iMqttActionListener;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public IMqttActionListener getActionCallback() {
        return this.listener;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public String[] getTopics() {
        return this.topics;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public void setUserContext(Object obj) {
        this.userContext = obj;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public Object getUserContext() {
        return this.userContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDelegate(IMqttToken iMqttToken) {
        this.delegate = iMqttToken;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public int getMessageId() {
        IMqttToken iMqttToken = this.delegate;
        if (iMqttToken != null) {
            return iMqttToken.getMessageId();
        }
        return 0;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public MqttWireMessage getResponse() {
        return this.delegate.getResponse();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public boolean getSessionPresent() {
        return this.delegate.getSessionPresent();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttToken
    public int[] getGrantedQos() {
        return this.delegate.getGrantedQos();
    }
}
