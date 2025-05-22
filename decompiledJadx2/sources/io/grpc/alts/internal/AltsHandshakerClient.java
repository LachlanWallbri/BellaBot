package io.grpc.alts.internal;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.UnmodifiableIterator;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.alts.internal.HandshakerReq;
import io.grpc.alts.internal.HandshakerServiceGrpc;
import io.grpc.alts.internal.StartClientHandshakeReq;
import io.grpc.alts.internal.StartServerHandshakeReq;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class AltsHandshakerClient {
    private static final String APPLICATION_PROTOCOL = "grpc";
    private static final String RECORD_PROTOCOL = "ALTSRP_GCM_AES128_REKEY";
    private boolean closed = false;
    private final AltsHandshakerOptions handshakerOptions;
    private final AltsHandshakerStub handshakerStub;
    private HandshakerResult result;
    private HandshakerStatus status;
    private static final Logger logger = Logger.getLogger(AltsHandshakerClient.class.getName());
    private static final int KEY_LENGTH = AltsChannelCrypter.getKeyLength();

    static String getApplicationProtocol() {
        return APPLICATION_PROTOCOL;
    }

    static String getRecordProtocol() {
        return RECORD_PROTOCOL;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AltsHandshakerClient(HandshakerServiceGrpc.HandshakerServiceStub handshakerServiceStub, AltsHandshakerOptions altsHandshakerOptions) {
        this.handshakerStub = new AltsHandshakerStub(handshakerServiceStub);
        this.handshakerOptions = altsHandshakerOptions;
    }

    AltsHandshakerClient(AltsHandshakerStub altsHandshakerStub, AltsHandshakerOptions altsHandshakerOptions) {
        this.handshakerStub = altsHandshakerStub;
        this.handshakerOptions = altsHandshakerOptions;
    }

    private void setStartClientFields(HandshakerReq.Builder builder) {
        StartClientHandshakeReq.Builder addRecordProtocols = StartClientHandshakeReq.newBuilder().setHandshakeSecurityProtocol(HandshakeProtocol.ALTS).addApplicationProtocols(APPLICATION_PROTOCOL).addRecordProtocols(RECORD_PROTOCOL);
        if (this.handshakerOptions.getRpcProtocolVersions() != null) {
            addRecordProtocols.setRpcVersions(this.handshakerOptions.getRpcProtocolVersions());
        }
        AltsHandshakerOptions altsHandshakerOptions = this.handshakerOptions;
        if (altsHandshakerOptions instanceof AltsClientOptions) {
            AltsClientOptions altsClientOptions = (AltsClientOptions) altsHandshakerOptions;
            if (!Strings.isNullOrEmpty(altsClientOptions.getTargetName())) {
                addRecordProtocols.setTargetName(altsClientOptions.getTargetName());
            }
            UnmodifiableIterator<String> it = altsClientOptions.getTargetServiceAccounts().iterator();
            while (it.hasNext()) {
                addRecordProtocols.addTargetIdentitiesBuilder().setServiceAccount(it.next());
            }
        }
        addRecordProtocols.setMaxFrameSize(AltsTsiFrameProtector.getMaxFrameSize());
        builder.setClientStart(addRecordProtocols);
    }

    private void setStartServerFields(HandshakerReq.Builder builder, ByteBuffer byteBuffer) {
        StartServerHandshakeReq.Builder inBytes = StartServerHandshakeReq.newBuilder().addApplicationProtocols(APPLICATION_PROTOCOL).putHandshakeParameters(HandshakeProtocol.ALTS.getNumber(), ServerHandshakeParameters.newBuilder().addRecordProtocols(RECORD_PROTOCOL).build()).setInBytes(ByteString.copyFrom(byteBuffer.duplicate()));
        if (this.handshakerOptions.getRpcProtocolVersions() != null) {
            inBytes.setRpcVersions(this.handshakerOptions.getRpcProtocolVersions());
        }
        inBytes.setMaxFrameSize(AltsTsiFrameProtector.getMaxFrameSize());
        builder.setServerStart(inBytes);
    }

    public boolean isFinished() {
        if (this.result != null) {
            return true;
        }
        HandshakerStatus handshakerStatus = this.status;
        return (handshakerStatus == null || handshakerStatus.getCode() == Status.Code.OK.value()) ? false : true;
    }

    public HandshakerStatus getStatus() {
        return this.status;
    }

    public HandshakerResult getResult() {
        return this.result;
    }

    public byte[] getKey() {
        HandshakerResult handshakerResult = this.result;
        if (handshakerResult == null) {
            return null;
        }
        int size = handshakerResult.getKeyData().size();
        int i = KEY_LENGTH;
        if (size < i) {
            throw new IllegalStateException("Could not get enough key data from the handshake.");
        }
        byte[] bArr = new byte[i];
        this.result.getKeyData().substring(0, KEY_LENGTH).copyTo(bArr, 0);
        return bArr;
    }

    private void handleResponse(HandshakerResp handshakerResp) throws GeneralSecurityException {
        this.status = handshakerResp.getStatus();
        if (handshakerResp.hasResult()) {
            this.result = handshakerResp.getResult();
            close();
        }
        if (this.status.getCode() == Status.Code.OK.value()) {
            return;
        }
        String str = "Handshaker service error: " + this.status.getDetails();
        logger.log(Level.INFO, str);
        close();
        throw new GeneralSecurityException(str);
    }

    public ByteBuffer startClientHandshake() throws GeneralSecurityException {
        Preconditions.checkState(!isFinished(), "Handshake has already finished.");
        HandshakerReq.Builder newBuilder = HandshakerReq.newBuilder();
        setStartClientFields(newBuilder);
        try {
            HandshakerResp send = this.handshakerStub.send(newBuilder.build());
            handleResponse(send);
            return send.getOutFrames().asReadOnlyByteBuffer();
        } catch (IOException | InterruptedException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public ByteBuffer startServerHandshake(ByteBuffer byteBuffer) throws GeneralSecurityException {
        Preconditions.checkState(!isFinished(), "Handshake has already finished.");
        HandshakerReq.Builder newBuilder = HandshakerReq.newBuilder();
        setStartServerFields(newBuilder, byteBuffer);
        try {
            HandshakerResp send = this.handshakerStub.send(newBuilder.build());
            handleResponse(send);
            byteBuffer.position(byteBuffer.position() + send.getBytesConsumed());
            return send.getOutFrames().asReadOnlyByteBuffer();
        } catch (IOException | InterruptedException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public ByteBuffer next(ByteBuffer byteBuffer) throws GeneralSecurityException {
        Preconditions.checkState(!isFinished(), "Handshake has already finished.");
        try {
            HandshakerResp send = this.handshakerStub.send(HandshakerReq.newBuilder().setNext(NextHandshakeMessageReq.newBuilder().setInBytes(ByteString.copyFrom(byteBuffer.duplicate())).build()).build());
            handleResponse(send);
            byteBuffer.position(byteBuffer.position() + send.getBytesConsumed());
            return send.getOutFrames().asReadOnlyByteBuffer();
        } catch (IOException | InterruptedException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.handshakerStub.close();
    }
}
