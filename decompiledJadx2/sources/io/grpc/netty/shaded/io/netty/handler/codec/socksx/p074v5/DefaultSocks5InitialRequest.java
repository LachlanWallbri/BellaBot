package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5;

import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSocks5InitialRequest extends AbstractSocks5Message implements Socks5InitialRequest {
    private final List<Socks5AuthMethod> authMethods;

    public DefaultSocks5InitialRequest(Socks5AuthMethod... socks5AuthMethodArr) {
        ObjectUtil.checkNotNull(socks5AuthMethodArr, "authMethods");
        ArrayList arrayList = new ArrayList(socks5AuthMethodArr.length);
        for (Socks5AuthMethod socks5AuthMethod : socks5AuthMethodArr) {
            if (socks5AuthMethod == null) {
                break;
            }
            arrayList.add(socks5AuthMethod);
        }
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("authMethods is empty");
        }
        this.authMethods = Collections.unmodifiableList(arrayList);
    }

    public DefaultSocks5InitialRequest(Iterable<Socks5AuthMethod> iterable) {
        Socks5AuthMethod next;
        ObjectUtil.checkNotNull(iterable, "authSchemes");
        ArrayList arrayList = new ArrayList();
        Iterator<Socks5AuthMethod> it = iterable.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            arrayList.add(next);
        }
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("authMethods is empty");
        }
        this.authMethods = Collections.unmodifiableList(arrayList);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5.Socks5InitialRequest
    public List<Socks5AuthMethod> authMethods() {
        return this.authMethods;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(StringUtil.simpleClassName(this));
        DecoderResult decoderResult = decoderResult();
        if (!decoderResult.isSuccess()) {
            sb.append("(decoderResult: ");
            sb.append(decoderResult);
            sb.append(", authMethods: ");
        } else {
            sb.append("(authMethods: ");
        }
        sb.append(authMethods());
        sb.append(')');
        return sb.toString();
    }
}
