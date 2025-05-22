package com.pudutech.mqtt.component.client.config;

import com.pudutech.mqtt.component.client.callback.LoginStateCallback;
import com.pudutech.mqtt.component.client.callback.MessageReceiverListener;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class MqttParamOptions {
    private boolean hasPassword;
    private boolean hasUserName;
    private String host;
    private LoginStateCallback loginStateCallback;
    private MessageReceiverListener messageReceiverListener;
    private String password;
    private int port;
    private String userName;

    private MqttParamOptions(Builder builder) {
        if (builder != null) {
            this.hasUserName = builder.hasUserName;
            this.hasPassword = builder.hasPassword;
            this.userName = builder.userName;
            this.password = builder.password;
            this.host = builder.host;
            this.port = builder.port;
            this.loginStateCallback = builder.loginStateCallback;
            this.messageReceiverListener = builder.messageReceiverListener;
        }
    }

    public boolean isHasUserName() {
        return this.hasUserName;
    }

    public boolean isHasPassword() {
        return this.hasPassword;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public LoginStateCallback getLoginStateCallback() {
        return this.loginStateCallback;
    }

    public MessageReceiverListener getMessageReceiverListener() {
        return this.messageReceiverListener;
    }

    public String toString() {
        return "MqttParamOptions{hasUserName=" + this.hasUserName + ", hasPassword=" + this.hasPassword + ", userName='" + this.userName + "', password='" + this.password + "', host='" + this.host + "', port=" + this.port + ", loginStateCallback=" + this.loginStateCallback + ", messageReceiverListener=" + this.messageReceiverListener + '}';
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class Builder {
        private boolean hasPassword;
        private boolean hasUserName;
        private String host;
        private LoginStateCallback loginStateCallback;
        private MessageReceiverListener messageReceiverListener;
        private String password;
        private int port;
        private String userName;

        public Builder hasUserName(boolean z) {
            this.hasUserName = z;
            return this;
        }

        public Builder hasPassword(boolean z) {
            this.hasPassword = z;
            return this;
        }

        public Builder setUserName(String str) {
            this.userName = str;
            return this;
        }

        public Builder setPassword(String str) {
            this.password = str;
            return this;
        }

        public Builder setHost(String str) {
            this.host = str;
            return this;
        }

        public Builder setPort(int i) {
            this.port = i;
            return this;
        }

        public Builder setLoginStateCallback(LoginStateCallback loginStateCallback) {
            this.loginStateCallback = loginStateCallback;
            return this;
        }

        public Builder setMessageReceiverListener(MessageReceiverListener messageReceiverListener) {
            this.messageReceiverListener = messageReceiverListener;
            return this;
        }

        public MqttParamOptions build() {
            return new MqttParamOptions(this);
        }
    }
}
