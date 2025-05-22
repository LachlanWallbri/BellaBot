package io.grpc.alts.internal;

import com.google.protobuf.MessageOrBuilder;
import io.grpc.alts.internal.HandshakerReq;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface HandshakerReqOrBuilder extends MessageOrBuilder {
    StartClientHandshakeReq getClientStart();

    StartClientHandshakeReqOrBuilder getClientStartOrBuilder();

    NextHandshakeMessageReq getNext();

    NextHandshakeMessageReqOrBuilder getNextOrBuilder();

    HandshakerReq.ReqOneofCase getReqOneofCase();

    StartServerHandshakeReq getServerStart();

    StartServerHandshakeReqOrBuilder getServerStartOrBuilder();

    boolean hasClientStart();

    boolean hasNext();

    boolean hasServerStart();
}
