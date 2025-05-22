package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5;

import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSocks5PasswordAuthRequest extends AbstractSocks5Message implements Socks5PasswordAuthRequest {
    private final String password;
    private final String username;

    public DefaultSocks5PasswordAuthRequest(String str, String str2) {
        ObjectUtil.checkNotNull(str, CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME);
        ObjectUtil.checkNotNull(str2, CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD);
        if (str.length() > 255) {
            throw new IllegalArgumentException("username: **** (expected: less than 256 chars)");
        }
        if (str2.length() > 255) {
            throw new IllegalArgumentException("password: **** (expected: less than 256 chars)");
        }
        this.username = str;
        this.password = str2;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5.Socks5PasswordAuthRequest
    public String username() {
        return this.username;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5.Socks5PasswordAuthRequest
    public String password() {
        return this.password;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(StringUtil.simpleClassName(this));
        DecoderResult decoderResult = decoderResult();
        if (!decoderResult.isSuccess()) {
            sb.append("(decoderResult: ");
            sb.append(decoderResult);
            sb.append(", username: ");
        } else {
            sb.append("(username: ");
        }
        sb.append(username());
        sb.append(", password: ****)");
        return sb.toString();
    }
}
