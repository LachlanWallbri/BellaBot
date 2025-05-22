package io.grpc.alts.internal;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ServerHandshakeParametersOrBuilder extends MessageOrBuilder {
    Identity getLocalIdentities(int i);

    int getLocalIdentitiesCount();

    List<Identity> getLocalIdentitiesList();

    IdentityOrBuilder getLocalIdentitiesOrBuilder(int i);

    List<? extends IdentityOrBuilder> getLocalIdentitiesOrBuilderList();

    String getRecordProtocols(int i);

    ByteString getRecordProtocolsBytes(int i);

    int getRecordProtocolsCount();

    List<String> getRecordProtocolsList();
}
