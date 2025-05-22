package io.netty.handler.codec.mqtt;

import io.netty.util.CharsetUtil;
import io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class MqttConnectPayload {
    private final String clientIdentifier;
    private final byte[] password;
    private final String userName;
    private final byte[] willMessage;
    private final String willTopic;

    @Deprecated
    public MqttConnectPayload(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3.getBytes(CharsetUtil.UTF_8), str4, str5.getBytes(CharsetUtil.UTF_8));
    }

    public MqttConnectPayload(String str, String str2, byte[] bArr, String str3, byte[] bArr2) {
        this.clientIdentifier = str;
        this.willTopic = str2;
        this.willMessage = bArr;
        this.userName = str3;
        this.password = bArr2;
    }

    public String clientIdentifier() {
        return this.clientIdentifier;
    }

    public String willTopic() {
        return this.willTopic;
    }

    @Deprecated
    public String willMessage() {
        if (this.willMessage == null) {
            return null;
        }
        return new String(this.willMessage, CharsetUtil.UTF_8);
    }

    public byte[] willMessageInBytes() {
        return this.willMessage;
    }

    public String userName() {
        return this.userName;
    }

    @Deprecated
    public String password() {
        if (this.password == null) {
            return null;
        }
        return new String(this.password, CharsetUtil.UTF_8);
    }

    public byte[] passwordInBytes() {
        return this.password;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + "[clientIdentifier=" + this.clientIdentifier + ", willTopic=" + this.willTopic + ", willMessage=" + this.willMessage + ", userName=" + this.userName + ", password=" + this.password + ']';
    }
}
