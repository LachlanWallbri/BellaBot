package io.netty.handler.codec.socks;

import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import java.nio.charset.CharsetEncoder;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class SocksAuthRequest extends SocksRequest {
    private final String password;
    private final String username;
    private static final CharsetEncoder asciiEncoder = CharsetUtil.encoder(CharsetUtil.US_ASCII);
    private static final SocksSubnegotiationVersion SUBNEGOTIATION_VERSION = SocksSubnegotiationVersion.AUTH_PASSWORD;

    public SocksAuthRequest(String str, String str2) {
        super(SocksRequestType.AUTH);
        if (str == null) {
            throw new NullPointerException(CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME);
        }
        if (str2 == null) {
            throw new NullPointerException(CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME);
        }
        if (!asciiEncoder.canEncode(str) || !asciiEncoder.canEncode(str2)) {
            throw new IllegalArgumentException("username: " + str + " or password: **** values should be in pure ascii");
        }
        if (str.length() > 255) {
            throw new IllegalArgumentException("username: " + str + " exceeds 255 char limit");
        }
        if (str2.length() > 255) {
            throw new IllegalArgumentException("password: **** exceeds 255 char limit");
        }
        this.username = str;
        this.password = str2;
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    @Override // io.netty.handler.codec.socks.SocksMessage
    public void encodeAsByteBuf(ByteBuf byteBuf) {
        byteBuf.writeByte(SUBNEGOTIATION_VERSION.byteValue());
        byteBuf.writeByte(this.username.length());
        byteBuf.writeCharSequence(this.username, CharsetUtil.US_ASCII);
        byteBuf.writeByte(this.password.length());
        byteBuf.writeCharSequence(this.password, CharsetUtil.US_ASCII);
    }
}
